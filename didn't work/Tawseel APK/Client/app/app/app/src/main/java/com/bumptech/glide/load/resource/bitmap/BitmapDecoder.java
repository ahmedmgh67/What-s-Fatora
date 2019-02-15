package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public abstract interface BitmapDecoder<T>
{
  public abstract Bitmap decode(T paramT, BitmapPool paramBitmapPool, int paramInt1, int paramInt2, DecodeFormat paramDecodeFormat)
    throws Exception;
  
  public abstract String getId();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */