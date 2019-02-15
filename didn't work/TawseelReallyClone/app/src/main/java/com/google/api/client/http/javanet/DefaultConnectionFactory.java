package com.google.api.client.http.javanet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public class DefaultConnectionFactory
  implements ConnectionFactory
{
  private final Proxy proxy;
  
  public DefaultConnectionFactory()
  {
    this(null);
  }
  
  public DefaultConnectionFactory(Proxy paramProxy)
  {
    this.proxy = paramProxy;
  }
  
  public HttpURLConnection openConnection(URL paramURL)
    throws IOException
  {
    if (this.proxy == null) {}
    for (paramURL = paramURL.openConnection();; paramURL = paramURL.openConnection(this.proxy)) {
      return (HttpURLConnection)paramURL;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\javanet\DefaultConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */