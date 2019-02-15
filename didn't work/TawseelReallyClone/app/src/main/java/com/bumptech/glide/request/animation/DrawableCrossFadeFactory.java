package com.bumptech.glide.request.animation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class DrawableCrossFadeFactory<T extends Drawable>
  implements GlideAnimationFactory<T>
{
  private static final int DEFAULT_DURATION_MS = 300;
  private final ViewAnimationFactory<T> animationFactory;
  private final int duration;
  private DrawableCrossFadeViewAnimation<T> firstResourceAnimation;
  private DrawableCrossFadeViewAnimation<T> secondResourceAnimation;
  
  public DrawableCrossFadeFactory()
  {
    this(300);
  }
  
  public DrawableCrossFadeFactory(int paramInt)
  {
    this(new ViewAnimationFactory(new DefaultAnimationFactory(paramInt)), paramInt);
  }
  
  public DrawableCrossFadeFactory(Context paramContext, int paramInt1, int paramInt2)
  {
    this(new ViewAnimationFactory(paramContext, paramInt1), paramInt2);
  }
  
  public DrawableCrossFadeFactory(Animation paramAnimation, int paramInt)
  {
    this(new ViewAnimationFactory(paramAnimation), paramInt);
  }
  
  DrawableCrossFadeFactory(ViewAnimationFactory<T> paramViewAnimationFactory, int paramInt)
  {
    this.animationFactory = paramViewAnimationFactory;
    this.duration = paramInt;
  }
  
  private GlideAnimation<T> getFirstResourceAnimation()
  {
    if (this.firstResourceAnimation == null) {
      this.firstResourceAnimation = new DrawableCrossFadeViewAnimation(this.animationFactory.build(false, true), this.duration);
    }
    return this.firstResourceAnimation;
  }
  
  private GlideAnimation<T> getSecondResourceAnimation()
  {
    if (this.secondResourceAnimation == null) {
      this.secondResourceAnimation = new DrawableCrossFadeViewAnimation(this.animationFactory.build(false, false), this.duration);
    }
    return this.secondResourceAnimation;
  }
  
  public GlideAnimation<T> build(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      return NoAnimation.get();
    }
    if (paramBoolean2) {
      return getFirstResourceAnimation();
    }
    return getSecondResourceAnimation();
  }
  
  private static class DefaultAnimationFactory
    implements ViewAnimation.AnimationFactory
  {
    private final int duration;
    
    DefaultAnimationFactory(int paramInt)
    {
      this.duration = paramInt;
    }
    
    public Animation build()
    {
      AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
      localAlphaAnimation.setDuration(this.duration);
      return localAlphaAnimation;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\DrawableCrossFadeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */