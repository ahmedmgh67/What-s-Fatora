package com.google.api.client.util.store;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public final class DataStoreUtils
{
  public static String toString(DataStore<?> paramDataStore)
  {
    StringBuilder localStringBuilder;
    for (;;)
    {
      try
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append('{');
        int i = 1;
        Iterator localIterator = paramDataStore.keySet().iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        String str = (String)localIterator.next();
        if (i != 0)
        {
          i = 0;
          localStringBuilder.append(str).append('=').append(paramDataStore.get(str));
        }
        else
        {
          localStringBuilder.append(", ");
        }
      }
      catch (IOException paramDataStore)
      {
        throw new RuntimeException(paramDataStore);
      }
    }
    paramDataStore = '}';
    return paramDataStore;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\DataStoreUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */