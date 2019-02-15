package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.OutputStream;

public class GifBitmapWrapperResourceEncoder
  implements ResourceEncoder<GifBitmapWrapper>
{
  private final ResourceEncoder<Bitmap> bitmapEncoder;
  private final ResourceEncoder<GifDrawable> gifEncoder;
  private String id;
  
  public GifBitmapWrapperResourceEncoder(ResourceEncoder<Bitmap> paramResourceEncoder, ResourceEncoder<GifDrawable> paramResourceEncoder1)
  {
    this.bitmapEncoder = paramResourceEncoder;
    this.gifEncoder = paramResourceEncoder1;
  }
  
  public boolean encode(Resource<GifBitmapWrapper> paramResource, OutputStream paramOutputStream)
  {
    paramResource = (GifBitmapWrapper)paramResource.get();
    Resource localResource = paramResource.getBitmapResource();
    if (localResource != null) {
      return this.bitmapEncoder.encode(localResource, paramOutputStream);
    }
    return this.gifEncoder.encode(paramResource.getGifResource(), paramOutputStream);
  }
  
  public String getId()
  {
    if (this.id == null) {
      this.id = (this.bitmapEncoder.getId() + this.gifEncoder.getId());
    }
    return this.id;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\GifBitmapWrapperResourceEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */