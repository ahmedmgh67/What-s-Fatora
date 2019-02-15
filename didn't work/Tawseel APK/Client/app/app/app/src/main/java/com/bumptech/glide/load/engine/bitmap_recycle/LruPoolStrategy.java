package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

abstract interface LruPoolStrategy
{
  public abstract Bitmap get(int paramInt1, int paramInt2, Bitmap.Config paramConfig);
  
  public abstract int getSize(Bitmap paramBitmap);
  
  public abstract String logBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig);
  
  public abstract String logBitmap(Bitmap paramBitmap);
  
  public abstract void put(Bitmap paramBitmap);
  
  public abstract Bitmap removeLast();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\bitmap_recycle\LruPoolStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */