package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;

public abstract interface DataStoreFactory
{
  public abstract <V extends Serializable> DataStore<V> getDataStore(String paramString)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\DataStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */