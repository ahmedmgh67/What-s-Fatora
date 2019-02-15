package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AbstractMemoryDataStore<V extends Serializable>
  extends AbstractDataStore<V>
{
  HashMap<String, byte[]> keyValueMap = Maps.newHashMap();
  private final Lock lock = new ReentrantLock();
  
  protected AbstractMemoryDataStore(DataStoreFactory paramDataStoreFactory, String paramString)
  {
    super(paramDataStoreFactory, paramString);
  }
  
  public final DataStore<V> clear()
    throws IOException
  {
    this.lock.lock();
    try
    {
      this.keyValueMap.clear();
      save();
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public boolean containsKey(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return false;
    }
    this.lock.lock();
    try
    {
      boolean bool = this.keyValueMap.containsKey(paramString);
      return bool;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public boolean containsValue(V paramV)
    throws IOException
  {
    if (paramV == null) {
      return false;
    }
    this.lock.lock();
    try
    {
      paramV = IOUtils.serialize(paramV);
      Iterator localIterator = this.keyValueMap.values().iterator();
      while (localIterator.hasNext())
      {
        boolean bool = Arrays.equals(paramV, (byte[])localIterator.next());
        if (bool) {
          return true;
        }
      }
      return false;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public DataStore<V> delete(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return this;
    }
    this.lock.lock();
    try
    {
      this.keyValueMap.remove(paramString);
      save();
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public final V get(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return null;
    }
    this.lock.lock();
    try
    {
      paramString = IOUtils.deserialize((byte[])this.keyValueMap.get(paramString));
      return paramString;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public boolean isEmpty()
    throws IOException
  {
    this.lock.lock();
    try
    {
      boolean bool = this.keyValueMap.isEmpty();
      return bool;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public final Set<String> keySet()
    throws IOException
  {
    this.lock.lock();
    try
    {
      Set localSet = Collections.unmodifiableSet(this.keyValueMap.keySet());
      return localSet;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  void save()
    throws IOException
  {}
  
  public final DataStore<V> set(String paramString, V paramV)
    throws IOException
  {
    Preconditions.checkNotNull(paramString);
    Preconditions.checkNotNull(paramV);
    this.lock.lock();
    try
    {
      this.keyValueMap.put(paramString, IOUtils.serialize(paramV));
      save();
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public int size()
    throws IOException
  {
    this.lock.lock();
    try
    {
      int i = this.keyValueMap.size();
      return i;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public String toString()
  {
    return DataStoreUtils.toString(this);
  }
  
  public final Collection<V> values()
    throws IOException
  {
    this.lock.lock();
    try
    {
      ArrayList localArrayList = Lists.newArrayList();
      Iterator localIterator = this.keyValueMap.values().iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(IOUtils.deserialize((byte[])localIterator.next()));
      }
      localList2 = Collections.unmodifiableList(localList1);
    }
    finally
    {
      this.lock.unlock();
    }
    List localList2;
    this.lock.unlock();
    return localList2;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\AbstractMemoryDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */