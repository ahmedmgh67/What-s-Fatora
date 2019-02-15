package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

public class NullDecoder<T, Z>
  implements ResourceDecoder<T, Z>
{
  private static final NullDecoder<?, ?> NULL_DECODER = new NullDecoder();
  
  public static <T, Z> NullDecoder<T, Z> get()
  {
    return NULL_DECODER;
  }
  
  public Resource<Z> decode(T paramT, int paramInt1, int paramInt2)
  {
    return null;
  }
  
  public String getId()
  {
    return "";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\NullDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */