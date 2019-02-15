package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

class GifFrameResourceDecoder
  implements ResourceDecoder<GifDecoder, Bitmap>
{
  private final BitmapPool bitmapPool;
  
  public GifFrameResourceDecoder(BitmapPool paramBitmapPool)
  {
    this.bitmapPool = paramBitmapPool;
  }
  
  public Resource<Bitmap> decode(GifDecoder paramGifDecoder, int paramInt1, int paramInt2)
  {
    return BitmapResource.obtain(paramGifDecoder.getNextFrame(), this.bitmapPool);
  }
  
  public String getId()
  {
    return "GifFrameResourceDecoder.com.bumptech.glide.load.resource.gif";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifFrameResourceDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */