package com.bumptech.glide.load.model;

import android.content.Context;

public abstract interface ModelLoaderFactory<T, Y>
{
  public abstract ModelLoader<T, Y> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory);
  
  public abstract void teardown();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\ModelLoaderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */