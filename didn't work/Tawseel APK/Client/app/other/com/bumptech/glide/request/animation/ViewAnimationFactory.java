package com.bumptech.glide.request.animation;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ViewAnimationFactory<R>
  implements GlideAnimationFactory<R>
{
  private final ViewAnimation.AnimationFactory animationFactory;
  private GlideAnimation<R> glideAnimation;
  
  public ViewAnimationFactory(Context paramContext, int paramInt)
  {
    this(new ResourceAnimationFactory(paramContext, paramInt));
  }
  
  public ViewAnimationFactory(Animation paramAnimation)
  {
    this(new ConcreteAnimationFactory(paramAnimation));
  }
  
  ViewAnimationFactory(ViewAnimation.AnimationFactory paramAnimationFactory)
  {
    this.animationFactory = paramAnimationFactory;
  }
  
  public GlideAnimation<R> build(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) || (!paramBoolean2)) {
      return NoAnimation.get();
    }
    if (this.glideAnimation == null) {
      this.glideAnimation = new ViewAnimation(this.animationFactory);
    }
    return this.glideAnimation;
  }
  
  private static class ConcreteAnimationFactory
    implements ViewAnimation.AnimationFactory
  {
    private final Animation animation;
    
    public ConcreteAnimationFactory(Animation paramAnimation)
    {
      this.animation = paramAnimation;
    }
    
    public Animation build()
    {
      return this.animation;
    }
  }
  
  private static class ResourceAnimationFactory
    implements ViewAnimation.AnimationFactory
  {
    private final int animationId;
    private final Context context;
    
    public ResourceAnimationFactory(Context paramContext, int paramInt)
    {
      this.context = paramContext.getApplicationContext();
      this.animationId = paramInt;
    }
    
    public Animation build()
    {
      return AnimationUtils.loadAnimation(this.context, this.animationId);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\ViewAnimationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */