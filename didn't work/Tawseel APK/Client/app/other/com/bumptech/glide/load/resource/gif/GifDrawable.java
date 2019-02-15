package com.bumptech.glide.load.resource.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.view.Gravity;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class GifDrawable
  extends GlideDrawable
  implements GifFrameLoader.FrameCallback
{
  private boolean applyGravity;
  private final GifDecoder decoder;
  private final Rect destRect = new Rect();
  private final GifFrameLoader frameLoader;
  private boolean isRecycled;
  private boolean isRunning;
  private boolean isStarted;
  private boolean isVisible = true;
  private int loopCount;
  private int maxLoopCount = -1;
  private final Paint paint;
  private final GifState state;
  
  public GifDrawable(Context paramContext, GifDecoder.BitmapProvider paramBitmapProvider, BitmapPool paramBitmapPool, Transformation<Bitmap> paramTransformation, int paramInt1, int paramInt2, GifHeader paramGifHeader, byte[] paramArrayOfByte, Bitmap paramBitmap)
  {
    this(new GifState(paramGifHeader, paramArrayOfByte, paramContext, paramTransformation, paramInt1, paramInt2, paramBitmapProvider, paramBitmapPool, paramBitmap));
  }
  
  GifDrawable(GifDecoder paramGifDecoder, GifFrameLoader paramGifFrameLoader, Bitmap paramBitmap, BitmapPool paramBitmapPool, Paint paramPaint)
  {
    this.decoder = paramGifDecoder;
    this.frameLoader = paramGifFrameLoader;
    this.state = new GifState(null);
    this.paint = paramPaint;
    this.state.bitmapPool = paramBitmapPool;
    this.state.firstFrame = paramBitmap;
  }
  
  GifDrawable(GifState paramGifState)
  {
    if (paramGifState == null) {
      throw new NullPointerException("GifState must not be null");
    }
    this.state = paramGifState;
    this.decoder = new GifDecoder(paramGifState.bitmapProvider);
    this.paint = new Paint();
    this.decoder.setData(paramGifState.gifHeader, paramGifState.data);
    this.frameLoader = new GifFrameLoader(paramGifState.context, this, this.decoder, paramGifState.targetWidth, paramGifState.targetHeight);
    this.frameLoader.setFrameTransformation(paramGifState.frameTransformation);
  }
  
  public GifDrawable(GifDrawable paramGifDrawable, Bitmap paramBitmap, Transformation<Bitmap> paramTransformation)
  {
    this(new GifState(paramGifDrawable.state.gifHeader, paramGifDrawable.state.data, paramGifDrawable.state.context, paramTransformation, paramGifDrawable.state.targetWidth, paramGifDrawable.state.targetHeight, paramGifDrawable.state.bitmapProvider, paramGifDrawable.state.bitmapPool, paramBitmap));
  }
  
  private void reset()
  {
    this.frameLoader.clear();
    invalidateSelf();
  }
  
  private void resetLoopCount()
  {
    this.loopCount = 0;
  }
  
  private void startRunning()
  {
    if (this.decoder.getFrameCount() == 1) {
      invalidateSelf();
    }
    while (this.isRunning) {
      return;
    }
    this.isRunning = true;
    this.frameLoader.start();
    invalidateSelf();
  }
  
  private void stopRunning()
  {
    this.isRunning = false;
    this.frameLoader.stop();
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (this.isRecycled) {
      return;
    }
    if (this.applyGravity)
    {
      Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.destRect);
      this.applyGravity = false;
    }
    Bitmap localBitmap = this.frameLoader.getCurrentFrame();
    if (localBitmap != null) {}
    for (;;)
    {
      paramCanvas.drawBitmap(localBitmap, null, this.destRect, this.paint);
      return;
      localBitmap = this.state.firstFrame;
    }
  }
  
  public Drawable.ConstantState getConstantState()
  {
    return this.state;
  }
  
  public byte[] getData()
  {
    return this.state.data;
  }
  
  public GifDecoder getDecoder()
  {
    return this.decoder;
  }
  
  public Bitmap getFirstFrame()
  {
    return this.state.firstFrame;
  }
  
  public int getFrameCount()
  {
    return this.decoder.getFrameCount();
  }
  
  public Transformation<Bitmap> getFrameTransformation()
  {
    return this.state.frameTransformation;
  }
  
  public int getIntrinsicHeight()
  {
    return this.state.firstFrame.getHeight();
  }
  
  public int getIntrinsicWidth()
  {
    return this.state.firstFrame.getWidth();
  }
  
  public int getOpacity()
  {
    return -2;
  }
  
  public boolean isAnimated()
  {
    return true;
  }
  
  boolean isRecycled()
  {
    return this.isRecycled;
  }
  
  public boolean isRunning()
  {
    return this.isRunning;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    this.applyGravity = true;
  }
  
  @TargetApi(11)
  public void onFrameReady(int paramInt)
  {
    if ((Build.VERSION.SDK_INT >= 11) && (getCallback() == null))
    {
      stop();
      reset();
    }
    do
    {
      return;
      invalidateSelf();
      if (paramInt == this.decoder.getFrameCount() - 1) {
        this.loopCount += 1;
      }
    } while ((this.maxLoopCount == -1) || (this.loopCount < this.maxLoopCount));
    stop();
  }
  
  public void recycle()
  {
    this.isRecycled = true;
    this.state.bitmapPool.put(this.state.firstFrame);
    this.frameLoader.clear();
    this.frameLoader.stop();
  }
  
  public void setAlpha(int paramInt)
  {
    this.paint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.paint.setColorFilter(paramColorFilter);
  }
  
  public void setFrameTransformation(Transformation<Bitmap> paramTransformation, Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      throw new NullPointerException("The first frame of the GIF must not be null");
    }
    if (paramTransformation == null) {
      throw new NullPointerException("The frame transformation must not be null");
    }
    this.state.frameTransformation = paramTransformation;
    this.state.firstFrame = paramBitmap;
    this.frameLoader.setFrameTransformation(paramTransformation);
  }
  
  void setIsRunning(boolean paramBoolean)
  {
    this.isRunning = paramBoolean;
  }
  
  public void setLoopCount(int paramInt)
  {
    if ((paramInt <= 0) && (paramInt != -1) && (paramInt != 0)) {
      throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
    }
    if (paramInt == 0)
    {
      this.maxLoopCount = this.decoder.getLoopCount();
      return;
    }
    this.maxLoopCount = paramInt;
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.isVisible = paramBoolean1;
    if (!paramBoolean1) {
      stopRunning();
    }
    for (;;)
    {
      return super.setVisible(paramBoolean1, paramBoolean2);
      if (this.isStarted) {
        startRunning();
      }
    }
  }
  
  public void start()
  {
    this.isStarted = true;
    resetLoopCount();
    if (this.isVisible) {
      startRunning();
    }
  }
  
  public void stop()
  {
    this.isStarted = false;
    stopRunning();
    if (Build.VERSION.SDK_INT < 11) {
      reset();
    }
  }
  
  static class GifState
    extends Drawable.ConstantState
  {
    private static final int GRAVITY = 119;
    BitmapPool bitmapPool;
    GifDecoder.BitmapProvider bitmapProvider;
    Context context;
    byte[] data;
    Bitmap firstFrame;
    Transformation<Bitmap> frameTransformation;
    GifHeader gifHeader;
    int targetHeight;
    int targetWidth;
    
    public GifState(GifHeader paramGifHeader, byte[] paramArrayOfByte, Context paramContext, Transformation<Bitmap> paramTransformation, int paramInt1, int paramInt2, GifDecoder.BitmapProvider paramBitmapProvider, BitmapPool paramBitmapPool, Bitmap paramBitmap)
    {
      if (paramBitmap == null) {
        throw new NullPointerException("The first frame of the GIF must not be null");
      }
      this.gifHeader = paramGifHeader;
      this.data = paramArrayOfByte;
      this.bitmapPool = paramBitmapPool;
      this.firstFrame = paramBitmap;
      this.context = paramContext.getApplicationContext();
      this.frameTransformation = paramTransformation;
      this.targetWidth = paramInt1;
      this.targetHeight = paramInt2;
      this.bitmapProvider = paramBitmapProvider;
    }
    
    public GifState(GifState paramGifState)
    {
      if (paramGifState != null)
      {
        this.gifHeader = paramGifState.gifHeader;
        this.data = paramGifState.data;
        this.context = paramGifState.context;
        this.frameTransformation = paramGifState.frameTransformation;
        this.targetWidth = paramGifState.targetWidth;
        this.targetHeight = paramGifState.targetHeight;
        this.bitmapProvider = paramGifState.bitmapProvider;
        this.bitmapPool = paramGifState.bitmapPool;
        this.firstFrame = paramGifState.firstFrame;
      }
    }
    
    public int getChangingConfigurations()
    {
      return 0;
    }
    
    public Drawable newDrawable()
    {
      return new GifDrawable(this);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return newDrawable();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */