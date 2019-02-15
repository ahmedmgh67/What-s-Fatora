package com.bumptech.glide.load.engine;

public abstract interface Resource<Z>
{
  public abstract Z get();
  
  public abstract int getSize();
  
  public abstract void recycle();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\Resource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */