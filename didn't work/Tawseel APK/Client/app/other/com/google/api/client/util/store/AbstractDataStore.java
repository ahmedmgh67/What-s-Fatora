package com.google.api.client.util.store;

import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public abstract class AbstractDataStore<V extends Serializable>
  implements DataStore<V>
{
  private final DataStoreFactory dataStoreFactory;
  private final String id;
  
  protected AbstractDataStore(DataStoreFactory paramDataStoreFactory, String paramString)
  {
    this.dataStoreFactory = ((DataStoreFactory)Preconditions.checkNotNull(paramDataStoreFactory));
    this.id = ((String)Preconditions.checkNotNull(paramString));
  }
  
  public boolean containsKey(String paramString)
    throws IOException
  {
    return get(paramString) != null;
  }
  
  public boolean containsValue(V paramV)
    throws IOException
  {
    return values().contains(paramV);
  }
  
  public DataStoreFactory getDataStoreFactory()
  {
    return this.dataStoreFactory;
  }
  
  public final String getId()
  {
    return this.id;
  }
  
  public boolean isEmpty()
    throws IOException
  {
    return size() == 0;
  }
  
  public int size()
    throws IOException
  {
    return keySet().size();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\AbstractDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */