package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Objects.ToStringHelper;
import java.io.InputStream;

@Beta
public class UnparsedNotification
  extends AbstractNotification
{
  private InputStream contentStream;
  private String contentType;
  
  public UnparsedNotification(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramLong, paramString1, paramString2, paramString3, paramString4);
  }
  
  public final InputStream getContentStream()
  {
    return this.contentStream;
  }
  
  public final String getContentType()
  {
    return this.contentType;
  }
  
  public UnparsedNotification setChanged(String paramString)
  {
    return (UnparsedNotification)super.setChanged(paramString);
  }
  
  public UnparsedNotification setChannelExpiration(String paramString)
  {
    return (UnparsedNotification)super.setChannelExpiration(paramString);
  }
  
  public UnparsedNotification setChannelId(String paramString)
  {
    return (UnparsedNotification)super.setChannelId(paramString);
  }
  
  public UnparsedNotification setChannelToken(String paramString)
  {
    return (UnparsedNotification)super.setChannelToken(paramString);
  }
  
  public UnparsedNotification setContentStream(InputStream paramInputStream)
  {
    this.contentStream = paramInputStream;
    return this;
  }
  
  public UnparsedNotification setContentType(String paramString)
  {
    this.contentType = paramString;
    return this;
  }
  
  public UnparsedNotification setMessageNumber(long paramLong)
  {
    return (UnparsedNotification)super.setMessageNumber(paramLong);
  }
  
  public UnparsedNotification setResourceId(String paramString)
  {
    return (UnparsedNotification)super.setResourceId(paramString);
  }
  
  public UnparsedNotification setResourceState(String paramString)
  {
    return (UnparsedNotification)super.setResourceState(paramString);
  }
  
  public UnparsedNotification setResourceUri(String paramString)
  {
    return (UnparsedNotification)super.setResourceUri(paramString);
  }
  
  public String toString()
  {
    return super.toStringHelper().add("contentType", this.contentType).toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\UnparsedNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */