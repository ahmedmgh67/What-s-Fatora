package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;

public class GifBitmapWrapperDrawableTranscoder
  implements ResourceTranscoder<GifBitmapWrapper, GlideDrawable>
{
  private final ResourceTranscoder<Bitmap, GlideBitmapDrawable> bitmapDrawableResourceTranscoder;
  
  public GifBitmapWrapperDrawableTranscoder(ResourceTranscoder<Bitmap, GlideBitmapDrawable> paramResourceTranscoder)
  {
    this.bitmapDrawableResourceTranscoder = paramResourceTranscoder;
  }
  
  public String getId()
  {
    return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
  }
  
  public Resource<GlideDrawable> transcode(Resource<GifBitmapWrapper> paramResource)
  {
    paramResource = (GifBitmapWrapper)paramResource.get();
    Resource localResource = paramResource.getBitmapResource();
    if (localResource != null) {
      return this.bitmapDrawableResourceTranscoder.transcode(localResource);
    }
    return paramResource.getGifResource();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\transcode\GifBitmapWrapperDrawableTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */