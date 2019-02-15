package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GenericTranscodeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.RequestManager.GenericModelRequest;
import com.bumptech.glide.RequestManager.GenericModelRequest.GenericTypeRequest;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.NullEncoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

class GifFrameLoader
{
  private final FrameCallback callback;
  private DelayTarget current;
  private final GifDecoder gifDecoder;
  private final Handler handler;
  private boolean isCleared;
  private boolean isLoadPending = false;
  private boolean isRunning = false;
  private GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> requestBuilder;
  
  public GifFrameLoader(Context paramContext, FrameCallback paramFrameCallback, GifDecoder paramGifDecoder, int paramInt1, int paramInt2)
  {
    this(paramFrameCallback, paramGifDecoder, null, getRequestBuilder(paramContext, paramGifDecoder, paramInt1, paramInt2, Glide.get(paramContext).getBitmapPool()));
  }
  
  GifFrameLoader(FrameCallback paramFrameCallback, GifDecoder paramGifDecoder, Handler paramHandler, GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> paramGenericRequestBuilder)
  {
    Handler localHandler = paramHandler;
    if (paramHandler == null) {
      localHandler = new Handler(Looper.getMainLooper(), new FrameLoaderCallback(null));
    }
    this.callback = paramFrameCallback;
    this.gifDecoder = paramGifDecoder;
    this.handler = localHandler;
    this.requestBuilder = paramGenericRequestBuilder;
  }
  
  private static GenericRequestBuilder<GifDecoder, GifDecoder, Bitmap, Bitmap> getRequestBuilder(Context paramContext, GifDecoder paramGifDecoder, int paramInt1, int paramInt2, BitmapPool paramBitmapPool)
  {
    paramBitmapPool = new GifFrameResourceDecoder(paramBitmapPool);
    GifFrameModelLoader localGifFrameModelLoader = new GifFrameModelLoader();
    Encoder localEncoder = NullEncoder.get();
    return Glide.with(paramContext).using(localGifFrameModelLoader, GifDecoder.class).load(paramGifDecoder).as(Bitmap.class).sourceEncoder(localEncoder).decoder(paramBitmapPool).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).override(paramInt1, paramInt2);
  }
  
  private void loadNextFrame()
  {
    if ((!this.isRunning) || (this.isLoadPending)) {
      return;
    }
    this.isLoadPending = true;
    this.gifDecoder.advance();
    long l1 = SystemClock.uptimeMillis();
    long l2 = this.gifDecoder.getNextDelay();
    DelayTarget localDelayTarget = new DelayTarget(this.handler, this.gifDecoder.getCurrentFrameIndex(), l1 + l2);
    this.requestBuilder.signature(new FrameSignature()).into(localDelayTarget);
  }
  
  public void clear()
  {
    stop();
    if (this.current != null)
    {
      Glide.clear(this.current);
      this.current = null;
    }
    this.isCleared = true;
  }
  
  public Bitmap getCurrentFrame()
  {
    if (this.current != null) {
      return this.current.getResource();
    }
    return null;
  }
  
  void onFrameReady(DelayTarget paramDelayTarget)
  {
    if (this.isCleared)
    {
      this.handler.obtainMessage(2, paramDelayTarget).sendToTarget();
      return;
    }
    DelayTarget localDelayTarget = this.current;
    this.current = paramDelayTarget;
    this.callback.onFrameReady(paramDelayTarget.index);
    if (localDelayTarget != null) {
      this.handler.obtainMessage(2, localDelayTarget).sendToTarget();
    }
    this.isLoadPending = false;
    loadNextFrame();
  }
  
  public void setFrameTransformation(Transformation<Bitmap> paramTransformation)
  {
    if (paramTransformation == null) {
      throw new NullPointerException("Transformation must not be null");
    }
    this.requestBuilder = this.requestBuilder.transform(new Transformation[] { paramTransformation });
  }
  
  public void start()
  {
    if (this.isRunning) {
      return;
    }
    this.isRunning = true;
    this.isCleared = false;
    loadNextFrame();
  }
  
  public void stop()
  {
    this.isRunning = false;
  }
  
  static class DelayTarget
    extends SimpleTarget<Bitmap>
  {
    private final Handler handler;
    private final int index;
    private Bitmap resource;
    private final long targetTime;
    
    public DelayTarget(Handler paramHandler, int paramInt, long paramLong)
    {
      this.handler = paramHandler;
      this.index = paramInt;
      this.targetTime = paramLong;
    }
    
    public Bitmap getResource()
    {
      return this.resource;
    }
    
    public void onResourceReady(Bitmap paramBitmap, GlideAnimation<? super Bitmap> paramGlideAnimation)
    {
      this.resource = paramBitmap;
      paramBitmap = this.handler.obtainMessage(1, this);
      this.handler.sendMessageAtTime(paramBitmap, this.targetTime);
    }
  }
  
  public static abstract interface FrameCallback
  {
    public abstract void onFrameReady(int paramInt);
  }
  
  private class FrameLoaderCallback
    implements Handler.Callback
  {
    public static final int MSG_CLEAR = 2;
    public static final int MSG_DELAY = 1;
    
    private FrameLoaderCallback() {}
    
    public boolean handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        paramMessage = (GifFrameLoader.DelayTarget)paramMessage.obj;
        GifFrameLoader.this.onFrameReady(paramMessage);
        return true;
      }
      if (paramMessage.what == 2) {
        Glide.clear((GifFrameLoader.DelayTarget)paramMessage.obj);
      }
      return false;
    }
  }
  
  static class FrameSignature
    implements Key
  {
    private final UUID uuid;
    
    public FrameSignature()
    {
      this(UUID.randomUUID());
    }
    
    FrameSignature(UUID paramUUID)
    {
      this.uuid = paramUUID;
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof FrameSignature)) {
        return ((FrameSignature)paramObject).uuid.equals(this.uuid);
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.uuid.hashCode();
    }
    
    public void updateDiskCacheKey(MessageDigest paramMessageDigest)
      throws UnsupportedEncodingException
    {
      throw new UnsupportedOperationException("Not implemented");
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifFrameLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */