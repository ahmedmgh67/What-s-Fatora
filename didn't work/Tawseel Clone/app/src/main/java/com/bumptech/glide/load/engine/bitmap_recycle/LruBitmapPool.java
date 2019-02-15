package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool
  implements BitmapPool
{
  private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
  private static final String TAG = "LruBitmapPool";
  private final Set<Bitmap.Config> allowedConfigs;
  private int currentSize;
  private int evictions;
  private int hits;
  private final int initialMaxSize;
  private int maxSize;
  private int misses;
  private int puts;
  private final LruPoolStrategy strategy;
  private final BitmapTracker tracker;
  
  public LruBitmapPool(int paramInt)
  {
    this(paramInt, getDefaultStrategy(), getDefaultAllowedConfigs());
  }
  
  LruBitmapPool(int paramInt, LruPoolStrategy paramLruPoolStrategy, Set<Bitmap.Config> paramSet)
  {
    this.initialMaxSize = paramInt;
    this.maxSize = paramInt;
    this.strategy = paramLruPoolStrategy;
    this.allowedConfigs = paramSet;
    this.tracker = new NullBitmapTracker(null);
  }
  
  public LruBitmapPool(int paramInt, Set<Bitmap.Config> paramSet)
  {
    this(paramInt, getDefaultStrategy(), paramSet);
  }
  
  private void dump()
  {
    if (Log.isLoggable("LruBitmapPool", 2)) {
      dumpUnchecked();
    }
  }
  
  private void dumpUnchecked()
  {
    Log.v("LruBitmapPool", "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy);
  }
  
  private void evict()
  {
    trimToSize(this.maxSize);
  }
  
  private static Set<Bitmap.Config> getDefaultAllowedConfigs()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(Arrays.asList(Bitmap.Config.values()));
    if (Build.VERSION.SDK_INT >= 19) {
      localHashSet.add(null);
    }
    return Collections.unmodifiableSet(localHashSet);
  }
  
  private static LruPoolStrategy getDefaultStrategy()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return new SizeConfigStrategy();
    }
    return new AttributeStrategy();
  }
  
  /* Error */
  private void trimToSize(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 120	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:currentSize	I
    //   6: iload_1
    //   7: if_icmple +43 -> 50
    //   10: aload_0
    //   11: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   14: invokeinterface 186 1 0
    //   19: astore_2
    //   20: aload_2
    //   21: ifnonnull +32 -> 53
    //   24: ldc 23
    //   26: iconst_5
    //   27: invokestatic 87	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   30: ifeq +15 -> 45
    //   33: ldc 23
    //   35: ldc -68
    //   37: invokestatic 191	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: aload_0
    //   42: invokespecial 90	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:dumpUnchecked	()V
    //   45: aload_0
    //   46: iconst_0
    //   47: putfield 120	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:currentSize	I
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: aload_0
    //   54: getfield 76	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:tracker	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool$BitmapTracker;
    //   57: aload_2
    //   58: invokeinterface 195 2 0
    //   63: aload_0
    //   64: aload_0
    //   65: getfield 120	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:currentSize	I
    //   68: aload_0
    //   69: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   72: aload_2
    //   73: invokeinterface 199 2 0
    //   78: isub
    //   79: putfield 120	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:currentSize	I
    //   82: aload_2
    //   83: invokevirtual 204	android/graphics/Bitmap:recycle	()V
    //   86: aload_0
    //   87: aload_0
    //   88: getfield 116	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:evictions	I
    //   91: iconst_1
    //   92: iadd
    //   93: putfield 116	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:evictions	I
    //   96: ldc 23
    //   98: iconst_3
    //   99: invokestatic 87	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   102: ifeq +37 -> 139
    //   105: ldc 23
    //   107: new 92	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   114: ldc -50
    //   116: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: aload_0
    //   120: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   123: aload_2
    //   124: invokeinterface 210 2 0
    //   129: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokestatic 213	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   138: pop
    //   139: aload_0
    //   140: invokespecial 215	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:dump	()V
    //   143: goto -141 -> 2
    //   146: astore_2
    //   147: aload_0
    //   148: monitorexit
    //   149: aload_2
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	LruBitmapPool
    //   0	151	1	paramInt	int
    //   19	105	2	localBitmap	Bitmap
    //   146	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	20	146	finally
    //   24	45	146	finally
    //   45	50	146	finally
    //   53	139	146	finally
    //   139	143	146	finally
  }
  
  public void clearMemory()
  {
    if (Log.isLoggable("LruBitmapPool", 3)) {
      Log.d("LruBitmapPool", "clearMemory");
    }
    trimToSize(0);
  }
  
  public Bitmap get(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    try
    {
      paramConfig = getDirty(paramInt1, paramInt2, paramConfig);
      if (paramConfig != null) {
        paramConfig.eraseColor(0);
      }
      return paramConfig;
    }
    finally {}
  }
  
  /* Error */
  @android.annotation.TargetApi(12)
  public Bitmap getDirty(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   6: astore 5
    //   8: aload_3
    //   9: ifnull +133 -> 142
    //   12: aload_3
    //   13: astore 4
    //   15: aload 5
    //   17: iload_1
    //   18: iload_2
    //   19: aload 4
    //   21: invokeinterface 230 4 0
    //   26: astore 4
    //   28: aload 4
    //   30: ifnonnull +120 -> 150
    //   33: ldc 23
    //   35: iconst_3
    //   36: invokestatic 87	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   39: ifeq +39 -> 78
    //   42: ldc 23
    //   44: new 92	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   51: ldc -24
    //   53: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: aload_0
    //   57: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   60: iload_1
    //   61: iload_2
    //   62: aload_3
    //   63: invokeinterface 235 4 0
    //   68: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokestatic 213	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   77: pop
    //   78: aload_0
    //   79: aload_0
    //   80: getfield 108	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:misses	I
    //   83: iconst_1
    //   84: iadd
    //   85: putfield 108	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:misses	I
    //   88: ldc 23
    //   90: iconst_2
    //   91: invokestatic 87	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   94: ifeq +39 -> 133
    //   97: ldc 23
    //   99: new 92	java/lang/StringBuilder
    //   102: dup
    //   103: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   106: ldc -19
    //   108: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: aload_0
    //   112: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   115: iload_1
    //   116: iload_2
    //   117: aload_3
    //   118: invokeinterface 235 4 0
    //   123: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   129: invokestatic 135	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   132: pop
    //   133: aload_0
    //   134: invokespecial 215	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:dump	()V
    //   137: aload_0
    //   138: monitorexit
    //   139: aload 4
    //   141: areturn
    //   142: getstatic 47	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:DEFAULT_CONFIG	Landroid/graphics/Bitmap$Config;
    //   145: astore 4
    //   147: goto -132 -> 15
    //   150: aload_0
    //   151: aload_0
    //   152: getfield 101	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:hits	I
    //   155: iconst_1
    //   156: iadd
    //   157: putfield 101	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:hits	I
    //   160: aload_0
    //   161: aload_0
    //   162: getfield 120	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:currentSize	I
    //   165: aload_0
    //   166: getfield 69	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:strategy	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruPoolStrategy;
    //   169: aload 4
    //   171: invokeinterface 199 2 0
    //   176: isub
    //   177: putfield 120	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:currentSize	I
    //   180: aload_0
    //   181: getfield 76	com/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool:tracker	Lcom/bumptech/glide/load/engine/bitmap_recycle/LruBitmapPool$BitmapTracker;
    //   184: aload 4
    //   186: invokeinterface 195 2 0
    //   191: getstatic 163	android/os/Build$VERSION:SDK_INT	I
    //   194: bipush 12
    //   196: if_icmplt -108 -> 88
    //   199: aload 4
    //   201: iconst_1
    //   202: invokevirtual 241	android/graphics/Bitmap:setHasAlpha	(Z)V
    //   205: goto -117 -> 88
    //   208: astore_3
    //   209: aload_0
    //   210: monitorexit
    //   211: aload_3
    //   212: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	213	0	this	LruBitmapPool
    //   0	213	1	paramInt1	int
    //   0	213	2	paramInt2	int
    //   0	213	3	paramConfig	Bitmap.Config
    //   13	187	4	localObject	Object
    //   6	10	5	localLruPoolStrategy	LruPoolStrategy
    // Exception table:
    //   from	to	target	type
    //   2	8	208	finally
    //   15	28	208	finally
    //   33	78	208	finally
    //   78	88	208	finally
    //   88	133	208	finally
    //   133	137	208	finally
    //   142	147	208	finally
    //   150	205	208	finally
  }
  
  public int getMaxSize()
  {
    return this.maxSize;
  }
  
  public boolean put(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      try
      {
        throw new NullPointerException("Bitmap must not be null");
      }
      finally {}
    }
    if ((!paramBitmap.isMutable()) || (this.strategy.getSize(paramBitmap) > this.maxSize) || (!this.allowedConfigs.contains(paramBitmap.getConfig()))) {
      if (Log.isLoggable("LruBitmapPool", 2)) {
        Log.v("LruBitmapPool", "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(paramBitmap) + ", is mutable: " + paramBitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(paramBitmap.getConfig()));
      }
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      int i = this.strategy.getSize(paramBitmap);
      this.strategy.put(paramBitmap);
      this.tracker.add(paramBitmap);
      this.puts += 1;
      this.currentSize += i;
      if (Log.isLoggable("LruBitmapPool", 2)) {
        Log.v("LruBitmapPool", "Put bitmap in pool=" + this.strategy.logBitmap(paramBitmap));
      }
      dump();
      evict();
    }
  }
  
  public void setSizeMultiplier(float paramFloat)
  {
    try
    {
      this.maxSize = Math.round(this.initialMaxSize * paramFloat);
      evict();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  @SuppressLint({"InlinedApi"})
  public void trimMemory(int paramInt)
  {
    if (Log.isLoggable("LruBitmapPool", 3)) {
      Log.d("LruBitmapPool", "trimMemory, level=" + paramInt);
    }
    if (paramInt >= 60) {
      clearMemory();
    }
    while (paramInt < 40) {
      return;
    }
    trimToSize(this.maxSize / 2);
  }
  
  private static abstract interface BitmapTracker
  {
    public abstract void add(Bitmap paramBitmap);
    
    public abstract void remove(Bitmap paramBitmap);
  }
  
  private static class NullBitmapTracker
    implements LruBitmapPool.BitmapTracker
  {
    public void add(Bitmap paramBitmap) {}
    
    public void remove(Bitmap paramBitmap) {}
  }
  
  private static class ThrowingBitmapTracker
    implements LruBitmapPool.BitmapTracker
  {
    private final Set<Bitmap> bitmaps = Collections.synchronizedSet(new HashSet());
    
    public void add(Bitmap paramBitmap)
    {
      if (this.bitmaps.contains(paramBitmap)) {
        throw new IllegalStateException("Can't add already added bitmap: " + paramBitmap + " [" + paramBitmap.getWidth() + "x" + paramBitmap.getHeight() + "]");
      }
      this.bitmaps.add(paramBitmap);
    }
    
    public void remove(Bitmap paramBitmap)
    {
      if (!this.bitmaps.contains(paramBitmap)) {
        throw new IllegalStateException("Cannot remove bitmap not in tracker");
      }
      this.bitmaps.remove(paramBitmap);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\LruBitmapPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */