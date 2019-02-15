package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.util.Util;

public class GlideBitmapDrawableResource
  extends DrawableResource<GlideBitmapDrawable>
{
  private final BitmapPool bitmapPool;
  
  public GlideBitmapDrawableResource(GlideBitmapDrawable paramGlideBitmapDrawable, BitmapPool paramBitmapPool)
  {
    super(paramGlideBitmapDrawable);
    this.bitmapPool = paramBitmapPool;
  }
  
  public int getSize()
  {
    return Util.getBitmapByteSize(((GlideBitmapDrawable)this.drawable).getBitmap());
  }
  
  public void recycle()
  {
    this.bitmapPool.put(((GlideBitmapDrawable)this.drawable).getBitmap());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\GlideBitmapDrawableResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */