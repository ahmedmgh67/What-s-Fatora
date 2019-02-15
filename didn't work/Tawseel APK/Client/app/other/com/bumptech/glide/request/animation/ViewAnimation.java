package com.bumptech.glide.request.animation;

import android.view.View;
import android.view.animation.Animation;

public class ViewAnimation<R>
  implements GlideAnimation<R>
{
  private final AnimationFactory animationFactory;
  
  ViewAnimation(AnimationFactory paramAnimationFactory)
  {
    this.animationFactory = paramAnimationFactory;
  }
  
  public boolean animate(R paramR, GlideAnimation.ViewAdapter paramViewAdapter)
  {
    paramR = paramViewAdapter.getView();
    if (paramR != null)
    {
      paramR.clearAnimation();
      paramR.startAnimation(this.animationFactory.build());
    }
    return false;
  }
  
  static abstract interface AnimationFactory
  {
    public abstract Animation build();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\ViewAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */