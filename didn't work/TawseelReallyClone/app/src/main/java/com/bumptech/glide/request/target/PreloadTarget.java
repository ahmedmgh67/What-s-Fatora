package com.bumptech.glide.request.target;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;

public final class PreloadTarget<Z>
  extends SimpleTarget<Z>
{
  private PreloadTarget(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public static <Z> PreloadTarget<Z> obtain(int paramInt1, int paramInt2)
  {
    return new PreloadTarget(paramInt1, paramInt2);
  }
  
  public void onResourceReady(Z paramZ, GlideAnimation<? super Z> paramGlideAnimation)
  {
    Glide.clear(this);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\PreloadTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */