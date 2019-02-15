package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public class FitCenter
  extends BitmapTransformation
{
  public FitCenter(Context paramContext)
  {
    super(paramContext);
  }
  
  public FitCenter(BitmapPool paramBitmapPool)
  {
    super(paramBitmapPool);
  }
  
  public String getId()
  {
    return "FitCenter.com.bumptech.glide.load.resource.bitmap";
  }
  
  protected Bitmap transform(BitmapPool paramBitmapPool, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return TransformationUtils.fitCenter(paramBitmap, paramBitmapPool, paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\FitCenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */