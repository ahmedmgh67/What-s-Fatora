package com.bumptech.glide.request.animation;

import android.graphics.drawable.Drawable;
import android.view.View;

public abstract interface GlideAnimation<R>
{
  public abstract boolean animate(R paramR, ViewAdapter paramViewAdapter);
  
  public static abstract interface ViewAdapter
  {
    public abstract Drawable getCurrentDrawable();
    
    public abstract View getView();
    
    public abstract void setDrawable(Drawable paramDrawable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\animation\GlideAnimation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */