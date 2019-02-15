package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Objects.ToStringHelper;
import com.google.api.client.util.Preconditions;

@Beta
public abstract class AbstractNotification
{
  private String changed;
  private String channelExpiration;
  private String channelId;
  private String channelToken;
  private long messageNumber;
  private String resourceId;
  private String resourceState;
  private String resourceUri;
  
  protected AbstractNotification(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    setMessageNumber(paramLong);
    setResourceState(paramString1);
    setResourceId(paramString2);
    setResourceUri(paramString3);
    setChannelId(paramString4);
  }
  
  protected AbstractNotification(AbstractNotification paramAbstractNotification)
  {
    this(paramAbstractNotification.getMessageNumber(), paramAbstractNotification.getResourceState(), paramAbstractNotification.getResourceId(), paramAbstractNotification.getResourceUri(), paramAbstractNotification.getChannelId());
    setChannelExpiration(paramAbstractNotification.getChannelExpiration());
    setChannelToken(paramAbstractNotification.getChannelToken());
    setChanged(paramAbstractNotification.getChanged());
  }
  
  public final String getChanged()
  {
    return this.changed;
  }
  
  public final String getChannelExpiration()
  {
    return this.channelExpiration;
  }
  
  public final String getChannelId()
  {
    return this.channelId;
  }
  
  public final String getChannelToken()
  {
    return this.channelToken;
  }
  
  public final long getMessageNumber()
  {
    return this.messageNumber;
  }
  
  public final String getResourceId()
  {
    return this.resourceId;
  }
  
  public final String getResourceState()
  {
    return this.resourceState;
  }
  
  public final String getResourceUri()
  {
    return this.resourceUri;
  }
  
  public AbstractNotification setChanged(String paramString)
  {
    this.changed = paramString;
    return this;
  }
  
  public AbstractNotification setChannelExpiration(String paramString)
  {
    this.channelExpiration = paramString;
    return this;
  }
  
  public AbstractNotification setChannelId(String paramString)
  {
    this.channelId = ((String)Preconditions.checkNotNull(paramString));
    return this;
  }
  
  public AbstractNotification setChannelToken(String paramString)
  {
    this.channelToken = paramString;
    return this;
  }
  
  public AbstractNotification setMessageNumber(long paramLong)
  {
    if (paramLong >= 1L) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.messageNumber = paramLong;
      return this;
    }
  }
  
  public AbstractNotification setResourceId(String paramString)
  {
    this.resourceId = ((String)Preconditions.checkNotNull(paramString));
    return this;
  }
  
  public AbstractNotification setResourceState(String paramString)
  {
    this.resourceState = ((String)Preconditions.checkNotNull(paramString));
    return this;
  }
  
  public AbstractNotification setResourceUri(String paramString)
  {
    this.resourceUri = ((String)Preconditions.checkNotNull(paramString));
    return this;
  }
  
  public String toString()
  {
    return toStringHelper().toString();
  }
  
  protected Objects.ToStringHelper toStringHelper()
  {
    return Objects.toStringHelper(this).add("messageNumber", Long.valueOf(this.messageNumber)).add("resourceState", this.resourceState).add("resourceId", this.resourceId).add("resourceUri", this.resourceUri).add("channelId", this.channelId).add("channelExpiration", this.channelExpiration).add("channelToken", this.channelToken).add("changed", this.changed);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\AbstractNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */