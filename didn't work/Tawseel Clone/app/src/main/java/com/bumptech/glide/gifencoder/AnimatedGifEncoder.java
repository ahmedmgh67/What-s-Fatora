package com.bumptech.glide.gifencoder;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AnimatedGifEncoder
{
  private static final double MIN_TRANSPARENT_PERCENTAGE = 4.0D;
  private static final String TAG = "AnimatedGifEncoder";
  private boolean closeStream = false;
  private int colorDepth;
  private byte[] colorTab;
  private int delay = 0;
  private int dispose = -1;
  private boolean firstFrame = true;
  private boolean hasTransparentPixels;
  private int height;
  private Bitmap image;
  private byte[] indexedPixels;
  private OutputStream out;
  private int palSize = 7;
  private byte[] pixels;
  private int repeat = -1;
  private int sample = 10;
  private boolean sizeSet = false;
  private boolean started = false;
  private int transIndex;
  private Integer transparent = null;
  private boolean[] usedEntry = new boolean['Ā'];
  private int width;
  
  private void analyzePixels()
  {
    int j = this.pixels.length;
    int m = j / 3;
    this.indexedPixels = new byte[m];
    NeuQuant localNeuQuant = new NeuQuant(this.pixels, j, this.sample);
    this.colorTab = localNeuQuant.process();
    j = 0;
    while (j < this.colorTab.length)
    {
      int i = this.colorTab[j];
      this.colorTab[j] = this.colorTab[(j + 2)];
      this.colorTab[(j + 2)] = i;
      this.usedEntry[(j / 3)] = false;
      j += 3;
    }
    j = 0;
    int k = 0;
    while (j < m)
    {
      byte[] arrayOfByte = this.pixels;
      int n = k + 1;
      int i1 = arrayOfByte[k];
      arrayOfByte = this.pixels;
      k = n + 1;
      n = localNeuQuant.map(i1 & 0xFF, arrayOfByte[n] & 0xFF, this.pixels[k] & 0xFF);
      this.usedEntry[n] = true;
      this.indexedPixels[j] = ((byte)n);
      j += 1;
      k += 1;
    }
    this.pixels = null;
    this.colorDepth = 8;
    this.palSize = 7;
    if (this.transparent != null) {
      this.transIndex = findClosest(this.transparent.intValue());
    }
    while (!this.hasTransparentPixels) {
      return;
    }
    this.transIndex = findClosest(0);
  }
  
  private int findClosest(int paramInt)
  {
    int j;
    if (this.colorTab == null)
    {
      j = -1;
      return j;
    }
    int i1 = Color.red(paramInt);
    int i2 = Color.green(paramInt);
    int i3 = Color.blue(paramInt);
    paramInt = 0;
    int i = 16777216;
    int i4 = this.colorTab.length;
    int k = 0;
    for (;;)
    {
      j = paramInt;
      if (k >= i4) {
        break;
      }
      byte[] arrayOfByte = this.colorTab;
      int m = k + 1;
      j = i1 - (arrayOfByte[k] & 0xFF);
      arrayOfByte = this.colorTab;
      int i5 = m + 1;
      k = i2 - (arrayOfByte[m] & 0xFF);
      m = i3 - (this.colorTab[i5] & 0xFF);
      int n = j * j + k * k + m * m;
      m = i5 / 3;
      k = i;
      j = paramInt;
      if (this.usedEntry[m] != 0)
      {
        k = i;
        j = paramInt;
        if (n < i)
        {
          k = n;
          j = m;
        }
      }
      paramInt = i5 + 1;
      i = k;
      k = paramInt;
      paramInt = j;
    }
  }
  
  private void getImagePixels()
  {
    int i = this.image.getWidth();
    int j = this.image.getHeight();
    if ((i != this.width) || (j != this.height))
    {
      localObject = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
      new Canvas((Bitmap)localObject).drawBitmap((Bitmap)localObject, 0.0F, 0.0F, null);
      this.image = ((Bitmap)localObject);
    }
    Object localObject = new int[i * j];
    this.image.getPixels((int[])localObject, 0, i, 0, 0, i, j);
    this.pixels = new byte[localObject.length * 3];
    this.hasTransparentPixels = false;
    int m = 0;
    int n = localObject.length;
    i = 0;
    int k = 0;
    while (i < n)
    {
      int i1 = localObject[i];
      j = m;
      if (i1 == 0) {
        j = m + 1;
      }
      byte[] arrayOfByte = this.pixels;
      m = k + 1;
      arrayOfByte[k] = ((byte)(i1 & 0xFF));
      arrayOfByte = this.pixels;
      k = m + 1;
      arrayOfByte[m] = ((byte)(i1 >> 8 & 0xFF));
      this.pixels[k] = ((byte)(i1 >> 16 & 0xFF));
      i += 1;
      k += 1;
      m = j;
    }
    double d = m * 100 / localObject.length;
    if (d > 4.0D) {}
    for (boolean bool = true;; bool = false)
    {
      this.hasTransparentPixels = bool;
      if (Log.isLoggable("AnimatedGifEncoder", 3)) {
        Log.d("AnimatedGifEncoder", "got pixels for frame with " + d + "% transparent pixels");
      }
      return;
    }
  }
  
  private void writeGraphicCtrlExt()
    throws IOException
  {
    this.out.write(33);
    this.out.write(249);
    this.out.write(4);
    int j;
    if ((this.transparent == null) && (!this.hasTransparentPixels)) {
      j = 0;
    }
    for (int i = 0;; i = 2)
    {
      if (this.dispose >= 0) {
        i = this.dispose & 0x7;
      }
      this.out.write(i << 2 | 0x0 | 0x0 | j);
      writeShort(this.delay);
      this.out.write(this.transIndex);
      this.out.write(0);
      return;
      j = 1;
    }
  }
  
  private void writeImageDesc()
    throws IOException
  {
    this.out.write(44);
    writeShort(0);
    writeShort(0);
    writeShort(this.width);
    writeShort(this.height);
    if (this.firstFrame)
    {
      this.out.write(0);
      return;
    }
    this.out.write(this.palSize | 0x80);
  }
  
  private void writeLSD()
    throws IOException
  {
    writeShort(this.width);
    writeShort(this.height);
    this.out.write(this.palSize | 0xF0);
    this.out.write(0);
    this.out.write(0);
  }
  
  private void writeNetscapeExt()
    throws IOException
  {
    this.out.write(33);
    this.out.write(255);
    this.out.write(11);
    writeString("NETSCAPE2.0");
    this.out.write(3);
    this.out.write(1);
    writeShort(this.repeat);
    this.out.write(0);
  }
  
  private void writePalette()
    throws IOException
  {
    this.out.write(this.colorTab, 0, this.colorTab.length);
    int j = this.colorTab.length;
    int i = 0;
    while (i < 768 - j)
    {
      this.out.write(0);
      i += 1;
    }
  }
  
  private void writePixels()
    throws IOException
  {
    new LZWEncoder(this.width, this.height, this.indexedPixels, this.colorDepth).encode(this.out);
  }
  
  private void writeShort(int paramInt)
    throws IOException
  {
    this.out.write(paramInt & 0xFF);
    this.out.write(paramInt >> 8 & 0xFF);
  }
  
  private void writeString(String paramString)
    throws IOException
  {
    int i = 0;
    while (i < paramString.length())
    {
      this.out.write((byte)paramString.charAt(i));
      i += 1;
    }
  }
  
  public boolean addFrame(Bitmap paramBitmap)
  {
    if ((paramBitmap == null) || (!this.started)) {
      return false;
    }
    try
    {
      if (!this.sizeSet) {
        setSize(paramBitmap.getWidth(), paramBitmap.getHeight());
      }
      this.image = paramBitmap;
      getImagePixels();
      analyzePixels();
      if (this.firstFrame)
      {
        writeLSD();
        writePalette();
        if (this.repeat >= 0) {
          writeNetscapeExt();
        }
      }
      writeGraphicCtrlExt();
      writeImageDesc();
      if (!this.firstFrame) {
        writePalette();
      }
      writePixels();
      this.firstFrame = false;
      return true;
    }
    catch (IOException paramBitmap) {}
    return false;
  }
  
  public boolean finish()
  {
    if (!this.started) {
      return false;
    }
    boolean bool2 = true;
    this.started = false;
    try
    {
      this.out.write(59);
      this.out.flush();
      bool1 = bool2;
      if (this.closeStream)
      {
        this.out.close();
        bool1 = bool2;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        boolean bool1 = false;
      }
    }
    this.transIndex = 0;
    this.out = null;
    this.image = null;
    this.pixels = null;
    this.indexedPixels = null;
    this.colorTab = null;
    this.closeStream = false;
    this.firstFrame = true;
    return bool1;
  }
  
  public void setDelay(int paramInt)
  {
    this.delay = Math.round(paramInt / 10.0F);
  }
  
  public void setDispose(int paramInt)
  {
    if (paramInt >= 0) {
      this.dispose = paramInt;
    }
  }
  
  public void setFrameRate(float paramFloat)
  {
    if (paramFloat != 0.0F) {
      this.delay = Math.round(100.0F / paramFloat);
    }
  }
  
  public void setQuality(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1) {
      i = 1;
    }
    this.sample = i;
  }
  
  public void setRepeat(int paramInt)
  {
    if (paramInt >= 0) {
      this.repeat = paramInt;
    }
  }
  
  public void setSize(int paramInt1, int paramInt2)
  {
    if ((this.started) && (!this.firstFrame)) {
      return;
    }
    this.width = paramInt1;
    this.height = paramInt2;
    if (this.width < 1) {
      this.width = 320;
    }
    if (this.height < 1) {
      this.height = 240;
    }
    this.sizeSet = true;
  }
  
  public void setTransparent(int paramInt)
  {
    this.transparent = Integer.valueOf(paramInt);
  }
  
  public boolean start(OutputStream paramOutputStream)
  {
    if (paramOutputStream == null) {
      return false;
    }
    boolean bool = true;
    this.closeStream = false;
    this.out = paramOutputStream;
    try
    {
      writeString("GIF89a");
      this.started = bool;
      return bool;
    }
    catch (IOException paramOutputStream)
    {
      for (;;)
      {
        bool = false;
      }
    }
  }
  
  public boolean start(String paramString)
  {
    try
    {
      this.out = new BufferedOutputStream(new FileOutputStream(paramString));
      bool = start(this.out);
      this.closeStream = true;
      this.started = bool;
      return bool;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\gifencoder\AnimatedGifEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */