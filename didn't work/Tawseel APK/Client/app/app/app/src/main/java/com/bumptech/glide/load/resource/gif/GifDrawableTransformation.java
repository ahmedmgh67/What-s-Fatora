package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

public class GifDrawableTransformation
  implements Transformation<GifDrawable>
{
  private final BitmapPool bitmapPool;
  private final Transformation<Bitmap> wrapped;
  
  public GifDrawableTransformation(Transformation<Bitmap> paramTransformation, BitmapPool paramBitmapPool)
  {
    this.wrapped = paramTransformation;
    this.bitmapPool = paramBitmapPool;
  }
  
  public String getId()
  {
    return this.wrapped.getId();
  }
  
  public Resource<GifDrawable> transform(Resource<GifDrawable> paramResource, int paramInt1, int paramInt2)
  {
    GifDrawable localGifDrawable = (GifDrawable)paramResource.get();
    Bitmap localBitmap = ((GifDrawable)paramResource.get()).getFirstFrame();
    Object localObject = new BitmapResource(localBitmap, this.bitmapPool);
    localObject = (Bitmap)this.wrapped.transform((Resource)localObject, paramInt1, paramInt2).get();
    if (!localObject.equals(localBitmap)) {
      paramResource = new GifDrawableResource(new GifDrawable(localGifDrawable, (Bitmap)localObject, this.wrapped));
    }
    return paramResource;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifDrawableTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */