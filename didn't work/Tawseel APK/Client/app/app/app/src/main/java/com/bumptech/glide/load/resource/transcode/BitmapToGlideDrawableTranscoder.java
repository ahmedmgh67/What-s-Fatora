package com.bumptech.glide.load.resource.transcode;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class BitmapToGlideDrawableTranscoder
  implements ResourceTranscoder<Bitmap, GlideDrawable>
{
  private final GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder;
  
  public BitmapToGlideDrawableTranscoder(Context paramContext)
  {
    this(new GlideBitmapDrawableTranscoder(paramContext));
  }
  
  public BitmapToGlideDrawableTranscoder(GlideBitmapDrawableTranscoder paramGlideBitmapDrawableTranscoder)
  {
    this.glideBitmapDrawableTranscoder = paramGlideBitmapDrawableTranscoder;
  }
  
  public String getId()
  {
    return this.glideBitmapDrawableTranscoder.getId();
  }
  
  public Resource<GlideDrawable> transcode(Resource<Bitmap> paramResource)
  {
    return this.glideBitmapDrawableTranscoder.transcode(paramResource);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\transcode\BitmapToGlideDrawableTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */