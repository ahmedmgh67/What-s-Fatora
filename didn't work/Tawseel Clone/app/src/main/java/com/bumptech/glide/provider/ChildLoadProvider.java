package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;

public class ChildLoadProvider<A, T, Z, R>
  implements LoadProvider<A, T, Z, R>, Cloneable
{
  private ResourceDecoder<File, Z> cacheDecoder;
  private ResourceEncoder<Z> encoder;
  private final LoadProvider<A, T, Z, R> parent;
  private ResourceDecoder<T, Z> sourceDecoder;
  private Encoder<T> sourceEncoder;
  private ResourceTranscoder<Z, R> transcoder;
  
  public ChildLoadProvider(LoadProvider<A, T, Z, R> paramLoadProvider)
  {
    this.parent = paramLoadProvider;
  }
  
  public ChildLoadProvider<A, T, Z, R> clone()
  {
    try
    {
      ChildLoadProvider localChildLoadProvider = (ChildLoadProvider)super.clone();
      return localChildLoadProvider;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }
  
  public ResourceDecoder<File, Z> getCacheDecoder()
  {
    if (this.cacheDecoder != null) {
      return this.cacheDecoder;
    }
    return this.parent.getCacheDecoder();
  }
  
  public ResourceEncoder<Z> getEncoder()
  {
    if (this.encoder != null) {
      return this.encoder;
    }
    return this.parent.getEncoder();
  }
  
  public ModelLoader<A, T> getModelLoader()
  {
    return this.parent.getModelLoader();
  }
  
  public ResourceDecoder<T, Z> getSourceDecoder()
  {
    if (this.sourceDecoder != null) {
      return this.sourceDecoder;
    }
    return this.parent.getSourceDecoder();
  }
  
  public Encoder<T> getSourceEncoder()
  {
    if (this.sourceEncoder != null) {
      return this.sourceEncoder;
    }
    return this.parent.getSourceEncoder();
  }
  
  public ResourceTranscoder<Z, R> getTranscoder()
  {
    if (this.transcoder != null) {
      return this.transcoder;
    }
    return this.parent.getTranscoder();
  }
  
  public void setCacheDecoder(ResourceDecoder<File, Z> paramResourceDecoder)
  {
    this.cacheDecoder = paramResourceDecoder;
  }
  
  public void setEncoder(ResourceEncoder<Z> paramResourceEncoder)
  {
    this.encoder = paramResourceEncoder;
  }
  
  public void setSourceDecoder(ResourceDecoder<T, Z> paramResourceDecoder)
  {
    this.sourceDecoder = paramResourceDecoder;
  }
  
  public void setSourceEncoder(Encoder<T> paramEncoder)
  {
    this.sourceEncoder = paramEncoder;
  }
  
  public void setTranscoder(ResourceTranscoder<Z, R> paramResourceTranscoder)
  {
    this.transcoder = paramResourceTranscoder;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\provider\ChildLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */