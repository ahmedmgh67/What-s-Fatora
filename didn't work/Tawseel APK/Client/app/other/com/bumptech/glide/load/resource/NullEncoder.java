package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.Encoder;
import java.io.OutputStream;

public class NullEncoder<T>
  implements Encoder<T>
{
  private static final NullEncoder<?> NULL_ENCODER = new NullEncoder();
  
  public static <T> Encoder<T> get()
  {
    return NULL_ENCODER;
  }
  
  public boolean encode(T paramT, OutputStream paramOutputStream)
  {
    return false;
  }
  
  public String getId()
  {
    return "";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\NullEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */