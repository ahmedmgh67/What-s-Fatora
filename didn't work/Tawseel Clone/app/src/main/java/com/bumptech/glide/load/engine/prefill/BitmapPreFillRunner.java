package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

final class BitmapPreFillRunner
  implements Runnable
{
  static final int BACKOFF_RATIO = 4;
  private static final Clock DEFAULT_CLOCK = new Clock();
  static final long INITIAL_BACKOFF_MS = 40L;
  static final long MAX_BACKOFF_MS = TimeUnit.SECONDS.toMillis(1L);
  static final long MAX_DURATION_MS = 32L;
  private static final String TAG = "PreFillRunner";
  private final BitmapPool bitmapPool;
  private final Clock clock;
  private long currentDelay = 40L;
  private final Handler handler;
  private boolean isCancelled;
  private final MemoryCache memoryCache;
  private final Set<PreFillType> seenTypes = new HashSet();
  private final PreFillQueue toPrefill;
  
  public BitmapPreFillRunner(BitmapPool paramBitmapPool, MemoryCache paramMemoryCache, PreFillQueue paramPreFillQueue)
  {
    this(paramBitmapPool, paramMemoryCache, paramPreFillQueue, DEFAULT_CLOCK, new Handler(Looper.getMainLooper()));
  }
  
  BitmapPreFillRunner(BitmapPool paramBitmapPool, MemoryCache paramMemoryCache, PreFillQueue paramPreFillQueue, Clock paramClock, Handler paramHandler)
  {
    this.bitmapPool = paramBitmapPool;
    this.memoryCache = paramMemoryCache;
    this.toPrefill = paramPreFillQueue;
    this.clock = paramClock;
    this.handler = paramHandler;
  }
  
  private void addToBitmapPool(PreFillType paramPreFillType, Bitmap paramBitmap)
  {
    if (this.seenTypes.add(paramPreFillType))
    {
      paramPreFillType = this.bitmapPool.get(paramPreFillType.getWidth(), paramPreFillType.getHeight(), paramPreFillType.getConfig());
      if (paramPreFillType != null) {
        this.bitmapPool.put(paramPreFillType);
      }
    }
    this.bitmapPool.put(paramBitmap);
  }
  
  private boolean allocate()
  {
    long l = this.clock.now();
    label175:
    while ((!this.toPrefill.isEmpty()) && (!isGcDetected(l)))
    {
      PreFillType localPreFillType = this.toPrefill.remove();
      Bitmap localBitmap = Bitmap.createBitmap(localPreFillType.getWidth(), localPreFillType.getHeight(), localPreFillType.getConfig());
      if (getFreeMemoryCacheBytes() >= Util.getBitmapByteSize(localBitmap)) {
        this.memoryCache.put(new UniqueKey(null), BitmapResource.obtain(localBitmap, this.bitmapPool));
      }
      for (;;)
      {
        if (!Log.isLoggable("PreFillRunner", 3)) {
          break label175;
        }
        Log.d("PreFillRunner", "allocated [" + localPreFillType.getWidth() + "x" + localPreFillType.getHeight() + "] " + localPreFillType.getConfig() + " size: " + Util.getBitmapByteSize(localBitmap));
        break;
        addToBitmapPool(localPreFillType, localBitmap);
      }
    }
    return (!this.isCancelled) && (!this.toPrefill.isEmpty());
  }
  
  private int getFreeMemoryCacheBytes()
  {
    return this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize();
  }
  
  private long getNextDelay()
  {
    long l = this.currentDelay;
    this.currentDelay = Math.min(this.currentDelay * 4L, MAX_BACKOFF_MS);
    return l;
  }
  
  private boolean isGcDetected(long paramLong)
  {
    return this.clock.now() - paramLong >= 32L;
  }
  
  public void cancel()
  {
    this.isCancelled = true;
  }
  
  public void run()
  {
    if (allocate()) {
      this.handler.postDelayed(this, getNextDelay());
    }
  }
  
  static class Clock
  {
    public long now()
    {
      return SystemClock.currentThreadTimeMillis();
    }
  }
  
  private static class UniqueKey
    implements Key
  {
    public void updateDiskCacheKey(MessageDigest paramMessageDigest)
      throws UnsupportedEncodingException
    {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\prefill\BitmapPreFillRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */