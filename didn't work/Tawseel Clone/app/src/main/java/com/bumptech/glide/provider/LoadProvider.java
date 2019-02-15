package com.bumptech.glide.provider;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

public abstract interface LoadProvider<A, T, Z, R>
  extends DataLoadProvider<T, Z>
{
  public abstract ModelLoader<A, T> getModelLoader();
  
  public abstract ResourceTranscoder<Z, R> getTranscoder();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\provider\LoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */