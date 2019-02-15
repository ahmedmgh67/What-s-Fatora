package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Objects.ToStringHelper;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Beta
public final class StoredChannel
  implements Serializable
{
  public static final String DEFAULT_DATA_STORE_ID = StoredChannel.class.getSimpleName();
  private static final long serialVersionUID = 1L;
  private String clientToken;
  private Long expiration;
  private final String id;
  private final Lock lock = new ReentrantLock();
  private final UnparsedNotificationCallback notificationCallback;
  private String topicId;
  
  public StoredChannel(UnparsedNotificationCallback paramUnparsedNotificationCallback)
  {
    this(paramUnparsedNotificationCallback, NotificationUtils.randomUuidString());
  }
  
  public StoredChannel(UnparsedNotificationCallback paramUnparsedNotificationCallback, String paramString)
  {
    this.notificationCallback = ((UnparsedNotificationCallback)Preconditions.checkNotNull(paramUnparsedNotificationCallback));
    this.id = ((String)Preconditions.checkNotNull(paramString));
  }
  
  public static DataStore<StoredChannel> getDefaultDataStore(DataStoreFactory paramDataStoreFactory)
    throws IOException
  {
    return paramDataStoreFactory.getDataStore(DEFAULT_DATA_STORE_ID);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof StoredChannel)) {
      return false;
    }
    paramObject = (StoredChannel)paramObject;
    return getId().equals(((StoredChannel)paramObject).getId());
  }
  
  public String getClientToken()
  {
    this.lock.lock();
    try
    {
      String str = this.clientToken;
      return str;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public Long getExpiration()
  {
    this.lock.lock();
    try
    {
      Long localLong = this.expiration;
      return localLong;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public String getId()
  {
    this.lock.lock();
    try
    {
      String str = this.id;
      return str;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public UnparsedNotificationCallback getNotificationCallback()
  {
    this.lock.lock();
    try
    {
      UnparsedNotificationCallback localUnparsedNotificationCallback = this.notificationCallback;
      return localUnparsedNotificationCallback;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public String getTopicId()
  {
    this.lock.lock();
    try
    {
      String str = this.topicId;
      return str;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public int hashCode()
  {
    return getId().hashCode();
  }
  
  public StoredChannel setClientToken(String paramString)
  {
    this.lock.lock();
    try
    {
      this.clientToken = paramString;
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public StoredChannel setExpiration(Long paramLong)
  {
    this.lock.lock();
    try
    {
      this.expiration = paramLong;
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public StoredChannel setTopicId(String paramString)
  {
    this.lock.lock();
    try
    {
      this.topicId = paramString;
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public StoredChannel store(DataStore<StoredChannel> paramDataStore)
    throws IOException
  {
    this.lock.lock();
    try
    {
      paramDataStore.set(getId(), this);
      return this;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public StoredChannel store(DataStoreFactory paramDataStoreFactory)
    throws IOException
  {
    return store(getDefaultDataStore(paramDataStoreFactory));
  }
  
  public String toString()
  {
    return Objects.toStringHelper(StoredChannel.class).add("notificationCallback", getNotificationCallback()).add("clientToken", getClientToken()).add("expiration", getExpiration()).add("id", getId()).add("topicId", getTopicId()).toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\StoredChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */