package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import java.io.File;

public class EmptyDataLoadProvider<T, Z>
  implements DataLoadProvider<T, Z>
{
  private static final DataLoadProvider<?, ?> EMPTY_DATA_LOAD_PROVIDER = new EmptyDataLoadProvider();
  
  public static <T, Z> DataLoadProvider<T, Z> get()
  {
    return EMPTY_DATA_LOAD_PROVIDER;
  }
  
  public ResourceDecoder<File, Z> getCacheDecoder()
  {
    return null;
  }
  
  public ResourceEncoder<Z> getEncoder()
  {
    return null;
  }
  
  public ResourceDecoder<T, Z> getSourceDecoder()
  {
    return null;
  }
  
  public Encoder<T> getSourceEncoder()
  {
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\provider\EmptyDataLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */