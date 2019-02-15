package com.bumptech.glide.provider;

import com.bumptech.glide.util.MultiClassKey;
import java.util.HashMap;
import java.util.Map;

public class DataLoadProviderRegistry
{
  private static final MultiClassKey GET_KEY = new MultiClassKey();
  private final Map<MultiClassKey, DataLoadProvider<?, ?>> providers = new HashMap();
  
  public <T, Z> DataLoadProvider<T, Z> get(Class<T> paramClass, Class<Z> paramClass1)
  {
    synchronized (GET_KEY)
    {
      GET_KEY.set(paramClass, paramClass1);
      paramClass1 = (DataLoadProvider)this.providers.get(GET_KEY);
      paramClass = paramClass1;
      if (paramClass1 == null) {
        paramClass = EmptyDataLoadProvider.get();
      }
      return paramClass;
    }
  }
  
  public <T, Z> void register(Class<T> paramClass, Class<Z> paramClass1, DataLoadProvider<T, Z> paramDataLoadProvider)
  {
    this.providers.put(new MultiClassKey(paramClass, paramClass1), paramDataLoadProvider);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\provider\DataLoadProviderRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */