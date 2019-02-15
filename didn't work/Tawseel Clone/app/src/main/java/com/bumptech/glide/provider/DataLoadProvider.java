package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import java.io.File;

public abstract interface DataLoadProvider<T, Z>
{
  public abstract ResourceDecoder<File, Z> getCacheDecoder();
  
  public abstract ResourceEncoder<Z> getEncoder();
  
  public abstract ResourceDecoder<T, Z> getSourceDecoder();
  
  public abstract Encoder<T> getSourceEncoder();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\provider\DataLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */