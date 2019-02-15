package com.bumptech.glide.request.animation;

public class NoAnimation<R>
  implements GlideAnimation<R>
{
  private static final NoAnimation<?> NO_ANIMATION = new NoAnimation();
  private static final GlideAnimationFactory<?> NO_ANIMATION_FACTORY = new NoAnimationFactory();
  
  public static <R> GlideAnimation<R> get()
  {
    return NO_ANIMATION;
  }
  
  public static <R> GlideAnimationFactory<R> getFactory()
  {
    return NO_ANIMATION_FACTORY;
  }
  
  public boolean animate(Object paramObject, GlideAnimation.ViewAdapter paramViewAdapter)
  {
    return false;
  }
  
  public static class NoAnimationFactory<R>
    implements GlideAnimationFactory<R>
  {
    public GlideAnimation<R> build(boolean paramBoolean1, boolean paramBoolean2)
    {
      return NoAnimation.NO_ANIMATION;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\NoAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */