package com.bumptech.glide.request.target;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

public class GlideDrawableImageViewTarget
  extends ImageViewTarget<GlideDrawable>
{
  private static final float SQUARE_RATIO_MARGIN = 0.05F;
  private int maxLoopCount;
  private GlideDrawable resource;
  
  public GlideDrawableImageViewTarget(ImageView paramImageView)
  {
    this(paramImageView, -1);
  }
  
  public GlideDrawableImageViewTarget(ImageView paramImageView, int paramInt)
  {
    super(paramImageView);
    this.maxLoopCount = paramInt;
  }
  
  public void onResourceReady(GlideDrawable paramGlideDrawable, GlideAnimation<? super GlideDrawable> paramGlideAnimation)
  {
    Object localObject = paramGlideDrawable;
    if (!paramGlideDrawable.isAnimated())
    {
      float f1 = ((ImageView)this.view).getWidth() / ((ImageView)this.view).getHeight();
      float f2 = paramGlideDrawable.getIntrinsicWidth() / paramGlideDrawable.getIntrinsicHeight();
      localObject = paramGlideDrawable;
      if (Math.abs(f1 - 1.0F) <= 0.05F)
      {
        localObject = paramGlideDrawable;
        if (Math.abs(f2 - 1.0F) <= 0.05F) {
          localObject = new SquaringDrawable(paramGlideDrawable, ((ImageView)this.view).getWidth());
        }
      }
    }
    super.onResourceReady(localObject, paramGlideAnimation);
    this.resource = ((GlideDrawable)localObject);
    ((GlideDrawable)localObject).setLoopCount(this.maxLoopCount);
    ((GlideDrawable)localObject).start();
  }
  
  public void onStart()
  {
    if (this.resource != null) {
      this.resource.start();
    }
  }
  
  public void onStop()
  {
    if (this.resource != null) {
      this.resource.stop();
    }
  }
  
  protected void setResource(GlideDrawable paramGlideDrawable)
  {
    ((ImageView)this.view).setImageDrawable(paramGlideDrawable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\GlideDrawableImageViewTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */