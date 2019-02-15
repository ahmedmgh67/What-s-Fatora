package com.bumptech.glide.request.animation;

public class ViewPropertyAnimationFactory<R>
  implements GlideAnimationFactory<R>
{
  private ViewPropertyAnimation<R> animation;
  private final ViewPropertyAnimation.Animator animator;
  
  public ViewPropertyAnimationFactory(ViewPropertyAnimation.Animator paramAnimator)
  {
    this.animator = paramAnimator;
  }
  
  public GlideAnimation<R> build(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) || (!paramBoolean2)) {
      return NoAnimation.get();
    }
    if (this.animation == null) {
      this.animation = new ViewPropertyAnimation(this.animator);
    }
    return this.animation;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\ViewPropertyAnimationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */