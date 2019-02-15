package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.engine.Resource;

public class SimpleResource<T>
  implements Resource<T>
{
  protected final T data;
  
  public SimpleResource(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException("Data must not be null");
    }
    this.data = paramT;
  }
  
  public final T get()
  {
    return (T)this.data;
  }
  
  public final int getSize()
  {
    return 1;
  }
  
  public void recycle() {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\SimpleResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */