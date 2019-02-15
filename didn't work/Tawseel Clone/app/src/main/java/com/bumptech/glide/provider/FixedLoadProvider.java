package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

public class FixedLoadProvider<A, T, Z, R>
  implements LoadProvider<A, T, Z, R>
{
  private final DataLoadProvider<T, Z> dataLoadProvider;
  private final ModelLoader<A, T> modelLoader;
  private final ResourceTranscoder<Z, R> transcoder;
  
  public FixedLoadProvider(ModelLoader<A, T> paramModelLoader, ResourceTranscoder<Z, R> paramResourceTranscoder, DataLoadProvider<T, Z> paramDataLoadProvider)
  {
    if (paramModelLoader == null) {
      throw new NullPointerException("ModelLoader must not be null");
    }
    this.modelLoader = paramModelLoader;
    if (paramResourceTranscoder == null) {
      throw new NullPointerException("Transcoder must not be null");
    }
    this.transcoder = paramResourceTranscoder;
    if (paramDataLoadProvider == null) {
      throw new NullPointerException("DataLoadProvider must not be null");
    }
    this.dataLoadProvider = paramDataLoadProvider;
  }
  
  public ResourceDecoder<File, Z> getCacheDecoder()
  {
    return this.dataLoadProvider.getCacheDecoder();
  }
  
  public ResourceEncoder<Z> getEncoder()
  {
    return this.dataLoadProvider.getEncoder();
  }
  
  public ModelLoader<A, T> getModelLoader()
  {
    return this.modelLoader;
  }
  
  public ResourceDecoder<T, Z> getSourceDecoder()
  {
    return this.dataLoadProvider.getSourceDecoder();
  }
  
  public Encoder<T> getSourceEncoder()
  {
    return this.dataLoadProvider.getSourceEncoder();
  }
  
  public ResourceTranscoder<Z, R> getTranscoder()
  {
    return this.transcoder;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\provider\FixedLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */