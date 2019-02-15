package com.google.api.client.util.store;

import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDataStoreFactory
  implements DataStoreFactory
{
  private static final Pattern ID_PATTERN = Pattern.compile("\\w{1,30}");
  private final Map<String, DataStore<? extends Serializable>> dataStoreMap = Maps.newHashMap();
  private final Lock lock = new ReentrantLock();
  
  protected abstract <V extends Serializable> DataStore<V> createDataStore(String paramString)
    throws IOException;
  
  public final <V extends Serializable> DataStore<V> getDataStore(String paramString)
    throws IOException
  {
    Preconditions.checkArgument(ID_PATTERN.matcher(paramString).matches(), "%s does not match pattern %s", new Object[] { paramString, ID_PATTERN });
    this.lock.lock();
    try
    {
      DataStore localDataStore2 = (DataStore)this.dataStoreMap.get(paramString);
      DataStore localDataStore1 = localDataStore2;
      if (localDataStore2 == null)
      {
        localDataStore1 = createDataStore(paramString);
        this.dataStoreMap.put(paramString, localDataStore1);
      }
      return localDataStore1;
    }
    finally
    {
      this.lock.unlock();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\AbstractDataStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */