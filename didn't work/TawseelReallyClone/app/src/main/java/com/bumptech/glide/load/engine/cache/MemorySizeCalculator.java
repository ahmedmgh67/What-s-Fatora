package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

public class MemorySizeCalculator
{
  static final int BITMAP_POOL_TARGET_SCREENS = 4;
  static final int BYTES_PER_ARGB_8888_PIXEL = 4;
  static final float LOW_MEMORY_MAX_SIZE_MULTIPLIER = 0.33F;
  static final float MAX_SIZE_MULTIPLIER = 0.4F;
  static final int MEMORY_CACHE_TARGET_SCREENS = 2;
  private static final String TAG = "MemorySizeCalculator";
  private final int bitmapPoolSize;
  private final Context context;
  private final int memoryCacheSize;
  
  public MemorySizeCalculator(Context paramContext)
  {
    this(paramContext, (ActivityManager)paramContext.getSystemService("activity"), new DisplayMetricsScreenDimensions(paramContext.getResources().getDisplayMetrics()));
  }
  
  MemorySizeCalculator(Context paramContext, ActivityManager paramActivityManager, ScreenDimensions paramScreenDimensions)
  {
    this.context = paramContext;
    int i = getMaxSize(paramActivityManager);
    int k = paramScreenDimensions.getWidthPixels() * paramScreenDimensions.getHeightPixels() * 4;
    int j = k * 4;
    k *= 2;
    if (k + j <= i)
    {
      this.memoryCacheSize = k;
      this.bitmapPoolSize = j;
      if (Log.isLoggable("MemorySizeCalculator", 3))
      {
        paramContext = new StringBuilder().append("Calculated memory cache size: ").append(toMb(this.memoryCacheSize)).append(" pool size: ").append(toMb(this.bitmapPoolSize)).append(" memory class limited? ");
        if (k + j <= i) {
          break label217;
        }
      }
    }
    label217:
    for (boolean bool = true;; bool = false)
    {
      Log.d("MemorySizeCalculator", bool + " max size: " + toMb(i) + " memoryClass: " + paramActivityManager.getMemoryClass() + " isLowMemoryDevice: " + isLowMemoryDevice(paramActivityManager));
      return;
      int m = Math.round(i / 6.0F);
      this.memoryCacheSize = (m * 2);
      this.bitmapPoolSize = (m * 4);
      break;
    }
  }
  
  private static int getMaxSize(ActivityManager paramActivityManager)
  {
    int i = paramActivityManager.getMemoryClass();
    boolean bool = isLowMemoryDevice(paramActivityManager);
    float f2 = i * 1024 * 1024;
    if (bool) {}
    for (float f1 = 0.33F;; f1 = 0.4F) {
      return Math.round(f1 * f2);
    }
  }
  
  @TargetApi(19)
  private static boolean isLowMemoryDevice(ActivityManager paramActivityManager)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramActivityManager.isLowRamDevice();
    }
    return Build.VERSION.SDK_INT < 11;
  }
  
  private String toMb(int paramInt)
  {
    return Formatter.formatFileSize(this.context, paramInt);
  }
  
  public int getBitmapPoolSize()
  {
    return this.bitmapPoolSize;
  }
  
  public int getMemoryCacheSize()
  {
    return this.memoryCacheSize;
  }
  
  private static class DisplayMetricsScreenDimensions
    implements MemorySizeCalculator.ScreenDimensions
  {
    private final DisplayMetrics displayMetrics;
    
    public DisplayMetricsScreenDimensions(DisplayMetrics paramDisplayMetrics)
    {
      this.displayMetrics = paramDisplayMetrics;
    }
    
    public int getHeightPixels()
    {
      return this.displayMetrics.heightPixels;
    }
    
    public int getWidthPixels()
    {
      return this.displayMetrics.widthPixels;
    }
  }
  
  static abstract interface ScreenDimensions
  {
    public abstract int getHeightPixels();
    
    public abstract int getWidthPixels();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\cache\MemorySizeCalculator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */