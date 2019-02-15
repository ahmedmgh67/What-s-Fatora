package com.google.api.client.googleapis.services;

import java.io.IOException;

public class CommonGoogleClientRequestInitializer
  implements GoogleClientRequestInitializer
{
  private final String key;
  private final String userIp;
  
  public CommonGoogleClientRequestInitializer()
  {
    this(null);
  }
  
  public CommonGoogleClientRequestInitializer(String paramString)
  {
    this(paramString, null);
  }
  
  public CommonGoogleClientRequestInitializer(String paramString1, String paramString2)
  {
    this.key = paramString1;
    this.userIp = paramString2;
  }
  
  public final String getKey()
  {
    return this.key;
  }
  
  public final String getUserIp()
  {
    return this.userIp;
  }
  
  public void initialize(AbstractGoogleClientRequest<?> paramAbstractGoogleClientRequest)
    throws IOException
  {
    if (this.key != null) {
      paramAbstractGoogleClientRequest.put("key", this.key);
    }
    if (this.userIp != null) {
      paramAbstractGoogleClientRequest.put("userIp", this.userIp);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\CommonGoogleClientRequestInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */