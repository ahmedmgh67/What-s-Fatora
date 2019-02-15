package com.bumptech.glide.module;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

public abstract interface GlideModule
{
  public abstract void applyOptions(Context paramContext, GlideBuilder paramGlideBuilder);
  
  public abstract void registerComponents(Context paramContext, Glide paramGlide);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\module\GlideModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */