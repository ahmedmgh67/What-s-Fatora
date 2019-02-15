package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;

public class UnitTransformation<T>
  implements Transformation<T>
{
  private static final Transformation<?> TRANSFORMATION = new UnitTransformation();
  
  public static <T> UnitTransformation<T> get()
  {
    return (UnitTransformation)TRANSFORMATION;
  }
  
  public String getId()
  {
    return "";
  }
  
  public Resource<T> transform(Resource<T> paramResource, int paramInt1, int paramInt2)
  {
    return paramResource;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\UnitTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */