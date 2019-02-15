package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class GifBitmapWrapper
{
  private final Resource<Bitmap> bitmapResource;
  private final Resource<GifDrawable> gifResource;
  
  public GifBitmapWrapper(Resource<Bitmap> paramResource, Resource<GifDrawable> paramResource1)
  {
    if ((paramResource != null) && (paramResource1 != null)) {
      throw new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
    }
    if ((paramResource == null) && (paramResource1 == null)) {
      throw new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
    }
    this.bitmapResource = paramResource;
    this.gifResource = paramResource1;
  }
  
  public Resource<Bitmap> getBitmapResource()
  {
    return this.bitmapResource;
  }
  
  public Resource<GifDrawable> getGifResource()
  {
    return this.gifResource;
  }
  
  public int getSize()
  {
    if (this.bitmapResource != null) {
      return this.bitmapResource.getSize();
    }
    return this.gifResource.getSize();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\GifBitmapWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */