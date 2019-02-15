package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;

public class GifBitmapWrapperTransformation
  implements Transformation<GifBitmapWrapper>
{
  private final Transformation<Bitmap> bitmapTransformation;
  private final Transformation<GifDrawable> gifDataTransformation;
  
  GifBitmapWrapperTransformation(Transformation<Bitmap> paramTransformation, Transformation<GifDrawable> paramTransformation1)
  {
    this.bitmapTransformation = paramTransformation;
    this.gifDataTransformation = paramTransformation1;
  }
  
  public GifBitmapWrapperTransformation(BitmapPool paramBitmapPool, Transformation<Bitmap> paramTransformation)
  {
    this(paramTransformation, new GifDrawableTransformation(paramTransformation, paramBitmapPool));
  }
  
  public String getId()
  {
    return this.bitmapTransformation.getId();
  }
  
  public Resource<GifBitmapWrapper> transform(Resource<GifBitmapWrapper> paramResource, int paramInt1, int paramInt2)
  {
    Resource localResource1 = ((GifBitmapWrapper)paramResource.get()).getBitmapResource();
    Resource localResource2 = ((GifBitmapWrapper)paramResource.get()).getGifResource();
    Object localObject;
    if ((localResource1 != null) && (this.bitmapTransformation != null))
    {
      localResource2 = this.bitmapTransformation.transform(localResource1, paramInt1, paramInt2);
      localObject = paramResource;
      if (!localResource1.equals(localResource2)) {
        localObject = new GifBitmapWrapperResource(new GifBitmapWrapper(localResource2, ((GifBitmapWrapper)paramResource.get()).getGifResource()));
      }
    }
    do
    {
      do
      {
        do
        {
          return (Resource<GifBitmapWrapper>)localObject;
          localObject = paramResource;
        } while (localResource2 == null);
        localObject = paramResource;
      } while (this.gifDataTransformation == null);
      localResource1 = this.gifDataTransformation.transform(localResource2, paramInt1, paramInt2);
      localObject = paramResource;
    } while (localResource2.equals(localResource1));
    return new GifBitmapWrapperResource(new GifBitmapWrapper(((GifBitmapWrapper)paramResource.get()).getBitmapResource(), localResource1));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\GifBitmapWrapperTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */