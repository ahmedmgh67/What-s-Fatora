package com.bumptech.glide.request.animation;

import android.view.View;

public class ViewPropertyAnimation<R>
  implements GlideAnimation<R>
{
  private final Animator animator;
  
  public ViewPropertyAnimation(Animator paramAnimator)
  {
    this.animator = paramAnimator;
  }
  
  public boolean animate(R paramR, GlideAnimation.ViewAdapter paramViewAdapter)
  {
    if (paramViewAdapter.getView() != null) {
      this.animator.animate(paramViewAdapter.getView());
    }
    return false;
  }
  
  public static abstract interface Animator
  {
    public abstract void animate(View paramView);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\ViewPropertyAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */