package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Objects.ToStringHelper;

@Beta
public class TypedNotification<T>
  extends AbstractNotification
{
  private T content;
  
  public TypedNotification(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramLong, paramString1, paramString2, paramString3, paramString4);
  }
  
  public TypedNotification(UnparsedNotification paramUnparsedNotification)
  {
    super(paramUnparsedNotification);
  }
  
  public final T getContent()
  {
    return (T)this.content;
  }
  
  public TypedNotification<T> setChanged(String paramString)
  {
    return (TypedNotification)super.setChanged(paramString);
  }
  
  public TypedNotification<T> setChannelExpiration(String paramString)
  {
    return (TypedNotification)super.setChannelExpiration(paramString);
  }
  
  public TypedNotification<T> setChannelId(String paramString)
  {
    return (TypedNotification)super.setChannelId(paramString);
  }
  
  public TypedNotification<T> setChannelToken(String paramString)
  {
    return (TypedNotification)super.setChannelToken(paramString);
  }
  
  public TypedNotification<T> setContent(T paramT)
  {
    this.content = paramT;
    return this;
  }
  
  public TypedNotification<T> setMessageNumber(long paramLong)
  {
    return (TypedNotification)super.setMessageNumber(paramLong);
  }
  
  public TypedNotification<T> setResourceId(String paramString)
  {
    return (TypedNotification)super.setResourceId(paramString);
  }
  
  public TypedNotification<T> setResourceState(String paramString)
  {
    return (TypedNotification)super.setResourceState(paramString);
  }
  
  public TypedNotification<T> setResourceUri(String paramString)
  {
    return (TypedNotification)super.setResourceUri(paramString);
  }
  
  public String toString()
  {
    return super.toStringHelper().add("content", this.content).toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\TypedNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */