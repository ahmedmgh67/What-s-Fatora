package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public abstract class BitmapTransformation
  implements Transformation<Bitmap>
{
  private BitmapPool bitmapPool;
  
  public BitmapTransformation(Context paramContext)
  {
    this(Glide.get(paramContext).getBitmapPool());
  }
  
  public BitmapTransformation(BitmapPool paramBitmapPool)
  {
    this.bitmapPool = paramBitmapPool;
  }
  
  protected abstract Bitmap transform(BitmapPool paramBitmapPool, Bitmap paramBitmap, int paramInt1, int paramInt2);
  
  public final Resource<Bitmap> transform(Resource<Bitmap> paramResource, int paramInt1, int paramInt2)
  {
    if (!Util.isValidDimensions(paramInt1, paramInt2)) {
      throw new IllegalArgumentException("Cannot apply transformation on width: " + paramInt1 + " or height: " + paramInt2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
    }
    Bitmap localBitmap1 = (Bitmap)paramResource.get();
    if (paramInt1 == Integer.MIN_VALUE)
    {
      paramInt1 = localBitmap1.getWidth();
      if (paramInt2 != Integer.MIN_VALUE) {
        break label113;
      }
      paramInt2 = localBitmap1.getHeight();
    }
    Bitmap localBitmap2;
    label113:
    for (;;)
    {
      localBitmap2 = transform(this.bitmapPool, localBitmap1, paramInt1, paramInt2);
      if (!localBitmap1.equals(localBitmap2)) {
        break label116;
      }
      return paramResource;
      break;
    }
    label116:
    return BitmapResource.obtain(localBitmap2, this.bitmapPool);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\BitmapTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */