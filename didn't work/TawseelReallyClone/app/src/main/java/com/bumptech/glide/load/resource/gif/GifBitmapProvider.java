package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

class GifBitmapProvider
  implements GifDecoder.BitmapProvider
{
  private final BitmapPool bitmapPool;
  
  public GifBitmapProvider(BitmapPool paramBitmapPool)
  {
    this.bitmapPool = paramBitmapPool;
  }
  
  public Bitmap obtain(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return this.bitmapPool.getDirty(paramInt1, paramInt2, paramConfig);
  }
  
  public void release(Bitmap paramBitmap)
  {
    if (!this.bitmapPool.put(paramBitmap)) {
      paramBitmap.recycle();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifBitmapProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */