package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GifDecoder
{
  private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
  private static final int DISPOSAL_BACKGROUND = 2;
  private static final int DISPOSAL_NONE = 1;
  private static final int DISPOSAL_PREVIOUS = 3;
  private static final int DISPOSAL_UNSPECIFIED = 0;
  private static final int INITIAL_FRAME_POINTER = -1;
  private static final int MAX_STACK_SIZE = 4096;
  private static final int NULL_CODE = -1;
  public static final int STATUS_FORMAT_ERROR = 1;
  public static final int STATUS_OK = 0;
  public static final int STATUS_OPEN_ERROR = 2;
  public static final int STATUS_PARTIAL_DECODE = 3;
  private static final String TAG = GifDecoder.class.getSimpleName();
  private int[] act;
  private BitmapProvider bitmapProvider;
  private final byte[] block = new byte['Ā'];
  private byte[] data;
  private int framePointer;
  private GifHeader header;
  private byte[] mainPixels;
  private int[] mainScratch;
  private GifHeaderParser parser;
  private byte[] pixelStack;
  private short[] prefix;
  private Bitmap previousImage;
  private ByteBuffer rawData;
  private boolean savePrevious;
  private int status;
  private byte[] suffix;
  
  public GifDecoder(BitmapProvider paramBitmapProvider)
  {
    this.bitmapProvider = paramBitmapProvider;
    this.header = new GifHeader();
  }
  
  private void decodeBitmapData(GifFrame paramGifFrame)
  {
    if (paramGifFrame != null) {
      this.rawData.position(paramGifFrame.bufferFrameStart);
    }
    if (paramGifFrame == null) {}
    int i14;
    int i15;
    int i10;
    int j;
    int i2;
    int i1;
    for (int i6 = this.header.width * this.header.height;; i6 = paramGifFrame.iw * paramGifFrame.ih)
    {
      if ((this.mainPixels == null) || (this.mainPixels.length < i6)) {
        this.mainPixels = new byte[i6];
      }
      if (this.prefix == null) {
        this.prefix = new short['က'];
      }
      if (this.suffix == null) {
        this.suffix = new byte['က'];
      }
      if (this.pixelStack == null) {
        this.pixelStack = new byte['ခ'];
      }
      i14 = read();
      i15 = 1 << i14;
      i10 = i15 + 2;
      j = -1;
      i2 = i14 + 1;
      i1 = (1 << i2) - 1;
      i = 0;
      while (i < i15)
      {
        this.prefix[i] = 0;
        this.suffix[i] = ((byte)i);
        i += 1;
      }
    }
    int i3 = 0;
    int n = 0;
    int i = 0;
    int i4 = 0;
    int m = 0;
    int k = 0;
    int i5 = 0;
    int i7 = 0;
    for (;;)
    {
      if (i7 < i6)
      {
        i8 = i3;
        i3 = m;
        if (m != 0) {
          break label274;
        }
        i3 = readBlock();
        if (i3 <= 0) {
          this.status = 3;
        }
      }
      else
      {
        while (n < i6)
        {
          this.mainPixels[n] = 0;
          n += 1;
        }
      }
      int i8 = 0;
      label274:
      i5 += ((this.block[i8] & 0xFF) << k);
      m = k + 8;
      int i12 = i8 + 1;
      int i11 = i3 - 1;
      i3 = i10;
      for (;;)
      {
        if (m < i2) {
          break label766;
        }
        k = i5 & i1;
        i8 = i5 >> i2;
        int i9 = m - i2;
        if (k == i15)
        {
          i2 = i14 + 1;
          i1 = (1 << i2) - 1;
          i3 = i15 + 2;
          j = -1;
          m = i9;
          i5 = i8;
        }
        else
        {
          if (k > i3)
          {
            this.status = 3;
            i10 = i3;
            i3 = i12;
            k = i9;
            m = i11;
            i5 = i8;
            break;
          }
          if (k == i15 + 1)
          {
            i10 = i3;
            i3 = i12;
            k = i9;
            m = i11;
            i5 = i8;
            break;
          }
          if (j == -1)
          {
            this.pixelStack[i] = this.suffix[k];
            j = k;
            i += 1;
            m = i9;
            i5 = i8;
            i4 = k;
          }
          else
          {
            m = k;
            i5 = i;
            if (k >= i3)
            {
              this.pixelStack[i] = ((byte)i4);
              m = j;
              i5 = i + 1;
            }
            while (m >= i15)
            {
              this.pixelStack[i5] = this.suffix[m];
              m = this.prefix[m];
              i5 += 1;
            }
            int i13 = this.suffix[m] & 0xFF;
            paramGifFrame = this.pixelStack;
            i10 = i5 + 1;
            paramGifFrame[i5] = ((byte)i13);
            m = i3;
            i5 = i1;
            i4 = i2;
            if (i3 < 4096)
            {
              this.prefix[i3] = ((short)j);
              this.suffix[i3] = ((byte)i13);
              i = i3 + 1;
              m = i;
              i5 = i1;
              i4 = i2;
              if ((i & i1) == 0)
              {
                m = i;
                i5 = i1;
                i4 = i2;
                if (i < 4096)
                {
                  i4 = i2 + 1;
                  i5 = i1 + i;
                  m = i;
                }
              }
            }
            i = n;
            j = i10;
            while (j > 0)
            {
              j -= 1;
              this.mainPixels[i] = this.pixelStack[j];
              i7 += 1;
              i += 1;
              continue;
              return;
            }
            i10 = j;
            i3 = m;
            m = i9;
            i1 = i5;
            i2 = i4;
            i5 = i8;
            i4 = i13;
            j = k;
            n = i;
            i = i10;
          }
        }
      }
      label766:
      i10 = i3;
      i3 = i12;
      k = m;
      m = i11;
    }
  }
  
  private GifHeaderParser getHeaderParser()
  {
    if (this.parser == null) {
      this.parser = new GifHeaderParser();
    }
    return this.parser;
  }
  
  private Bitmap getNextBitmap()
  {
    Bitmap localBitmap2 = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
    Bitmap localBitmap1 = localBitmap2;
    if (localBitmap2 == null) {
      localBitmap1 = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
    }
    setAlpha(localBitmap1);
    return localBitmap1;
  }
  
  private int read()
  {
    try
    {
      int i = this.rawData.get();
      return i & 0xFF;
    }
    catch (Exception localException)
    {
      this.status = 1;
    }
    return 0;
  }
  
  private int readBlock()
  {
    int k = read();
    int j = 0;
    int i = 0;
    if (k > 0) {
      for (;;)
      {
        j = i;
        if (i < k)
        {
          j = k - i;
          try
          {
            this.rawData.get(this.block, i, j);
            i += j;
          }
          catch (Exception localException)
          {
            Log.w(TAG, "Error Reading Block", localException);
            this.status = 1;
            j = i;
          }
        }
      }
    }
    return j;
  }
  
  @TargetApi(12)
  private static void setAlpha(Bitmap paramBitmap)
  {
    if (Build.VERSION.SDK_INT >= 12) {
      paramBitmap.setHasAlpha(true);
    }
  }
  
  private Bitmap setPixels(GifFrame paramGifFrame1, GifFrame paramGifFrame2)
  {
    int i4 = this.header.width;
    int i5 = this.header.height;
    int[] arrayOfInt = this.mainScratch;
    int i;
    int n;
    int m;
    int i1;
    int k;
    if ((paramGifFrame2 != null) && (paramGifFrame2.dispose > 0))
    {
      if (paramGifFrame2.dispose == 2)
      {
        i = 0;
        if (!paramGifFrame1.transparency) {
          i = this.header.bgColor;
        }
        Arrays.fill(arrayOfInt, i);
      }
    }
    else
    {
      decodeBitmapData(paramGifFrame1);
      n = 1;
      m = 8;
      i1 = 0;
      k = 0;
    }
    for (;;)
    {
      if (k >= paramGifFrame1.ih) {
        break label411;
      }
      int i3 = k;
      i = i1;
      int j = m;
      int i2 = n;
      if (paramGifFrame1.interlace)
      {
        i = i1;
        j = m;
        i2 = n;
        if (i1 >= paramGifFrame1.ih)
        {
          i2 = n + 1;
          switch (i2)
          {
          default: 
            j = m;
            i = i1;
          }
        }
      }
      for (;;)
      {
        i3 = i;
        i += j;
        m = i3 + paramGifFrame1.iy;
        if (m >= this.header.height) {
          break label391;
        }
        i3 = m * this.header.width;
        i1 = i3 + paramGifFrame1.ix;
        n = i1 + paramGifFrame1.iw;
        m = n;
        if (this.header.width + i3 < n) {
          m = i3 + this.header.width;
        }
        n = k * paramGifFrame1.iw;
        while (i1 < m)
        {
          i3 = this.mainPixels[n];
          i3 = this.act[(i3 & 0xFF)];
          if (i3 != 0) {
            arrayOfInt[i1] = i3;
          }
          i1 += 1;
          n += 1;
        }
        if ((paramGifFrame2.dispose != 3) || (this.previousImage == null)) {
          break;
        }
        this.previousImage.getPixels(arrayOfInt, 0, i4, 0, 0, i4, i5);
        break;
        i = 4;
        j = m;
        continue;
        i = 2;
        j = 4;
        continue;
        i = 1;
        j = 2;
      }
      label391:
      k += 1;
      i1 = i;
      m = j;
      n = i2;
    }
    label411:
    if ((this.savePrevious) && ((paramGifFrame1.dispose == 0) || (paramGifFrame1.dispose == 1)))
    {
      if (this.previousImage == null) {
        this.previousImage = getNextBitmap();
      }
      this.previousImage.setPixels(arrayOfInt, 0, i4, 0, 0, i4, i5);
    }
    paramGifFrame1 = getNextBitmap();
    paramGifFrame1.setPixels(arrayOfInt, 0, i4, 0, 0, i4, i5);
    return paramGifFrame1;
  }
  
  public void advance()
  {
    this.framePointer = ((this.framePointer + 1) % this.header.frameCount);
  }
  
  public void clear()
  {
    this.header = null;
    this.data = null;
    this.mainPixels = null;
    this.mainScratch = null;
    if (this.previousImage != null) {
      this.bitmapProvider.release(this.previousImage);
    }
    this.previousImage = null;
    this.rawData = null;
  }
  
  public int getCurrentFrameIndex()
  {
    return this.framePointer;
  }
  
  public byte[] getData()
  {
    return this.data;
  }
  
  public int getDelay(int paramInt)
  {
    int j = -1;
    int i = j;
    if (paramInt >= 0)
    {
      i = j;
      if (paramInt < this.header.frameCount) {
        i = ((GifFrame)this.header.frames.get(paramInt)).delay;
      }
    }
    return i;
  }
  
  public int getFrameCount()
  {
    return this.header.frameCount;
  }
  
  public int getHeight()
  {
    return this.header.height;
  }
  
  public int getLoopCount()
  {
    return this.header.loopCount;
  }
  
  public int getNextDelay()
  {
    if ((this.header.frameCount <= 0) || (this.framePointer < 0)) {
      return -1;
    }
    return getDelay(this.framePointer);
  }
  
  public Bitmap getNextFrame()
  {
    Bitmap localBitmap2 = null;
    for (;;)
    {
      GifFrame localGifFrame2;
      int i;
      try
      {
        if ((this.header.frameCount <= 0) || (this.framePointer < 0))
        {
          if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
          }
          this.status = 1;
        }
        if ((this.status == 1) || (this.status == 2))
        {
          localObject = localBitmap2;
          if (Log.isLoggable(TAG, 3))
          {
            Log.d(TAG, "Unable to decode frame, status=" + this.status);
            localObject = localBitmap2;
          }
          return (Bitmap)localObject;
        }
        this.status = 0;
        localGifFrame2 = (GifFrame)this.header.frames.get(this.framePointer);
        Object localObject = null;
        i = this.framePointer - 1;
        if (i >= 0) {
          localObject = (GifFrame)this.header.frames.get(i);
        }
        if (localGifFrame2.lct == null)
        {
          this.act = this.header.gct;
          i = 0;
          if (localGifFrame2.transparency)
          {
            i = this.act[localGifFrame2.transIndex];
            this.act[localGifFrame2.transIndex] = 0;
          }
          if (this.act != null) {
            break label330;
          }
          if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "No Valid Color Table");
          }
          this.status = 1;
          localObject = localBitmap2;
          continue;
        }
        this.act = localGifFrame2.lct;
      }
      finally {}
      if (this.header.bgIndex == localGifFrame2.transIndex)
      {
        this.header.bgColor = 0;
        continue;
        label330:
        localBitmap2 = setPixels(localGifFrame2, localGifFrame1);
        Bitmap localBitmap1 = localBitmap2;
        if (localGifFrame2.transparency)
        {
          this.act[localGifFrame2.transIndex] = i;
          localBitmap1 = localBitmap2;
        }
      }
    }
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public int getWidth()
  {
    return this.header.width;
  }
  
  public int read(InputStream paramInputStream, int paramInt)
  {
    int i = 16384;
    if (paramInputStream != null)
    {
      if (paramInt > 0) {
        i = paramInt + 4096;
      }
      try
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
        byte[] arrayOfByte = new byte['䀀'];
        for (;;)
        {
          paramInt = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
          if (paramInt == -1) {
            break;
          }
          localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
        }
        if (paramInputStream == null) {}
      }
      catch (IOException localIOException)
      {
        Log.w(TAG, "Error reading data from stream", localIOException);
      }
    }
    try
    {
      for (;;)
      {
        paramInputStream.close();
        return this.status;
        localIOException.flush();
        read(localIOException.toByteArray());
      }
      this.status = 2;
    }
    catch (IOException paramInputStream)
    {
      for (;;)
      {
        Log.w(TAG, "Error closing stream", paramInputStream);
      }
    }
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
    this.header = getHeaderParser().setData(paramArrayOfByte).parseHeader();
    if (paramArrayOfByte != null)
    {
      this.rawData = ByteBuffer.wrap(paramArrayOfByte);
      this.rawData.rewind();
      this.rawData.order(ByteOrder.LITTLE_ENDIAN);
      this.mainPixels = new byte[this.header.width * this.header.height];
      this.mainScratch = new int[this.header.width * this.header.height];
      this.savePrevious = false;
      paramArrayOfByte = this.header.frames.iterator();
      while (paramArrayOfByte.hasNext()) {
        if (((GifFrame)paramArrayOfByte.next()).dispose == 3) {
          this.savePrevious = true;
        }
      }
    }
    return this.status;
  }
  
  public void resetFrameIndex()
  {
    this.framePointer = -1;
  }
  
  public void setData(GifHeader paramGifHeader, byte[] paramArrayOfByte)
  {
    this.header = paramGifHeader;
    this.data = paramArrayOfByte;
    this.status = 0;
    this.framePointer = -1;
    this.rawData = ByteBuffer.wrap(paramArrayOfByte);
    this.rawData.rewind();
    this.rawData.order(ByteOrder.LITTLE_ENDIAN);
    this.savePrevious = false;
    paramArrayOfByte = paramGifHeader.frames.iterator();
    while (paramArrayOfByte.hasNext()) {
      if (((GifFrame)paramArrayOfByte.next()).dispose == 3) {
        this.savePrevious = true;
      }
    }
    this.mainPixels = new byte[paramGifHeader.width * paramGifHeader.height];
    this.mainScratch = new int[paramGifHeader.width * paramGifHeader.height];
  }
  
  public static abstract interface BitmapProvider
  {
    public abstract Bitmap obtain(int paramInt1, int paramInt2, Bitmap.Config paramConfig);
    
    public abstract void release(Bitmap paramBitmap);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\gifdecoder\GifDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */