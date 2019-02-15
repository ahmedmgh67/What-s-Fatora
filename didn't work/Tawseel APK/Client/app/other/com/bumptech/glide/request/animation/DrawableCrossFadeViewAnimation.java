package com.bumptech.glide.request.animation;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

public class DrawableCrossFadeViewAnimation<T extends Drawable>
  implements GlideAnimation<T>
{
  private final GlideAnimation<T> defaultAnimation;
  private final int duration;
  
  public DrawableCrossFadeViewAnimation(GlideAnimation<T> paramGlideAnimation, int paramInt)
  {
    this.defaultAnimation = paramGlideAnimation;
    this.duration = paramInt;
  }
  
  public boolean animate(T paramT, GlideAnimation.ViewAdapter paramViewAdapter)
  {
    Drawable localDrawable = paramViewAdapter.getCurrentDrawable();
    if (localDrawable != null)
    {
      paramT = new TransitionDrawable(new Drawable[] { localDrawable, paramT });
      paramT.setCrossFadeEnabled(true);
      paramT.startTransition(this.duration);
      paramViewAdapter.setDrawable(paramT);
      return true;
    }
    this.defaultAnimation.animate(paramT, paramViewAdapter);
    return false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\DrawableCrossFadeViewAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */