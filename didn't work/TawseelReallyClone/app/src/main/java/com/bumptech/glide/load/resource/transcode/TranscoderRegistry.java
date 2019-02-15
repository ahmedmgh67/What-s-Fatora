package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.util.MultiClassKey;
import java.util.HashMap;
import java.util.Map;

public class TranscoderRegistry
{
  private static final MultiClassKey GET_KEY = new MultiClassKey();
  private final Map<MultiClassKey, ResourceTranscoder<?, ?>> factories = new HashMap();
  
  public <Z, R> ResourceTranscoder<Z, R> get(Class<Z> paramClass, Class<R> paramClass1)
  {
    if (paramClass.equals(paramClass1)) {
      ??? = UnitTranscoder.get();
    }
    for (;;)
    {
      return (ResourceTranscoder<Z, R>)???;
      synchronized (GET_KEY)
      {
        GET_KEY.set(paramClass, paramClass1);
        ResourceTranscoder localResourceTranscoder = (ResourceTranscoder)this.factories.get(GET_KEY);
        ??? = localResourceTranscoder;
        if (localResourceTranscoder != null) {
          continue;
        }
        throw new IllegalArgumentException("No transcoder registered for " + paramClass + " and " + paramClass1);
      }
    }
  }
  
  public <Z, R> void register(Class<Z> paramClass, Class<R> paramClass1, ResourceTranscoder<Z, R> paramResourceTranscoder)
  {
    this.factories.put(new MultiClassKey(paramClass, paramClass1), paramResourceTranscoder);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\transcode\TranscoderRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */