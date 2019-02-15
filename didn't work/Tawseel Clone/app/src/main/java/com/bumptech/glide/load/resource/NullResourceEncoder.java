package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.OutputStream;

public class NullResourceEncoder<T>
  implements ResourceEncoder<T>
{
  private static final NullResourceEncoder<?> NULL_ENCODER = new NullResourceEncoder();
  
  public static <T> NullResourceEncoder<T> get()
  {
    return NULL_ENCODER;
  }
  
  public boolean encode(Resource<T> paramResource, OutputStream paramOutputStream)
  {
    return false;
  }
  
  public String getId()
  {
    return "";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\NullResourceEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */