package com.bumptech.glide.load.resource.transcode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawableResource;

public class GlideBitmapDrawableTranscoder
  implements ResourceTranscoder<Bitmap, GlideBitmapDrawable>
{
  private final BitmapPool bitmapPool;
  private final Resources resources;
  
  public GlideBitmapDrawableTranscoder(Context paramContext)
  {
    this(paramContext.getResources(), Glide.get(paramContext).getBitmapPool());
  }
  
  public GlideBitmapDrawableTranscoder(Resources paramResources, BitmapPool paramBitmapPool)
  {
    this.resources = paramResources;
    this.bitmapPool = paramBitmapPool;
  }
  
  public String getId()
  {
    return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
  }
  
  public Resource<GlideBitmapDrawable> transcode(Resource<Bitmap> paramResource)
  {
    return new GlideBitmapDrawableResource(new GlideBitmapDrawable(this.resources, (Bitmap)paramResource.get()), this.bitmapPool);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\transcode\GlideBitmapDrawableTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */