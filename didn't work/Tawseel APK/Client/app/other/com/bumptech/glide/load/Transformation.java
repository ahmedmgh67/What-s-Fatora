package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;

public abstract interface Transformation<T>
{
  public abstract String getId();
  
  public abstract Resource<T> transform(Resource<T> paramResource, int paramInt1, int paramInt2);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\Transformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */