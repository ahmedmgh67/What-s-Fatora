package com.bumptech.glide;

import android.view.animation.Animation;

abstract interface DrawableOptions
{
  public abstract GenericRequestBuilder<?, ?, ?, ?> crossFade();
  
  public abstract GenericRequestBuilder<?, ?, ?, ?> crossFade(int paramInt);
  
  public abstract GenericRequestBuilder<?, ?, ?, ?> crossFade(int paramInt1, int paramInt2);
  
  @Deprecated
  public abstract GenericRequestBuilder<?, ?, ?, ?> crossFade(Animation paramAnimation, int paramInt);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\DrawableOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */