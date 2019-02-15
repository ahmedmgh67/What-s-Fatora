package com.bumptech.glide.load.resource.gifbitmap;

import com.bumptech.glide.load.engine.Resource;

public class GifBitmapWrapperResource
  implements Resource<GifBitmapWrapper>
{
  private final GifBitmapWrapper data;
  
  public GifBitmapWrapperResource(GifBitmapWrapper paramGifBitmapWrapper)
  {
    if (paramGifBitmapWrapper == null) {
      throw new NullPointerException("Data must not be null");
    }
    this.data = paramGifBitmapWrapper;
  }
  
  public GifBitmapWrapper get()
  {
    return this.data;
  }
  
  public int getSize()
  {
    return this.data.getSize();
  }
  
  public void recycle()
  {
    Resource localResource = this.data.getBitmapResource();
    if (localResource != null) {
      localResource.recycle();
    }
    localResource = this.data.getGifResource();
    if (localResource != null) {
      localResource.recycle();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\GifBitmapWrapperResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */