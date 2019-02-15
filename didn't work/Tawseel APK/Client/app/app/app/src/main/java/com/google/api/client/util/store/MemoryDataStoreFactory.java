package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;

public class MemoryDataStoreFactory
  extends AbstractDataStoreFactory
{
  public static MemoryDataStoreFactory getDefaultInstance()
  {
    return InstanceHolder.INSTANCE;
  }
  
  protected <V extends Serializable> DataStore<V> createDataStore(String paramString)
    throws IOException
  {
    return new MemoryDataStore(this, paramString);
  }
  
  static class InstanceHolder
  {
    static final MemoryDataStoreFactory INSTANCE = new MemoryDataStoreFactory();
  }
  
  static class MemoryDataStore<V extends Serializable>
    extends AbstractMemoryDataStore<V>
  {
    MemoryDataStore(MemoryDataStoreFactory paramMemoryDataStoreFactory, String paramString)
    {
      super(paramString);
    }
    
    public MemoryDataStoreFactory getDataStoreFactory()
    {
      return (MemoryDataStoreFactory)super.getDataStoreFactory();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\MemoryDataStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */