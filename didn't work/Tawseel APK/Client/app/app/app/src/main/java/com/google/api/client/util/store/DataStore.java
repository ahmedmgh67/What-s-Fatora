package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public abstract interface DataStore<V extends Serializable>
{
  public abstract DataStore<V> clear()
    throws IOException;
  
  public abstract boolean containsKey(String paramString)
    throws IOException;
  
  public abstract boolean containsValue(V paramV)
    throws IOException;
  
  public abstract DataStore<V> delete(String paramString)
    throws IOException;
  
  public abstract V get(String paramString)
    throws IOException;
  
  public abstract DataStoreFactory getDataStoreFactory();
  
  public abstract String getId();
  
  public abstract boolean isEmpty()
    throws IOException;
  
  public abstract Set<String> keySet()
    throws IOException;
  
  public abstract DataStore<V> set(String paramString, V paramV)
    throws IOException;
  
  public abstract int size()
    throws IOException;
  
  public abstract Collection<V> values()
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\DataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */