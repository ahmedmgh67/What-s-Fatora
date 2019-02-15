package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class ImageViewTargetFactory
{
  public <Z> Target<Z> buildTarget(ImageView paramImageView, Class<Z> paramClass)
  {
    if (GlideDrawable.class.isAssignableFrom(paramClass)) {
      return new GlideDrawableImageViewTarget(paramImageView);
    }
    if (Bitmap.class.equals(paramClass)) {
      return new BitmapImageViewTarget(paramImageView);
    }
    if (Drawable.class.isAssignableFrom(paramClass)) {
      return new DrawableImageViewTarget(paramImageView);
    }
    throw new IllegalArgumentException("Unhandled class: " + paramClass + ", try .as*(Class).transcode(ResourceTranscoder)");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\ImageViewTargetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */