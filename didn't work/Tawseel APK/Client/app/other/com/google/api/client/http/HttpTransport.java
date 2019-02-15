package com.google.api.client.http;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public abstract class HttpTransport
{
  static final Logger LOGGER = Logger.getLogger(HttpTransport.class.getName());
  private static final String[] SUPPORTED_METHODS = { "DELETE", "GET", "POST", "PUT" };
  
  static
  {
    Arrays.sort(SUPPORTED_METHODS);
  }
  
  HttpRequest buildRequest()
  {
    return new HttpRequest(this, null);
  }
  
  protected abstract LowLevelHttpRequest buildRequest(String paramString1, String paramString2)
    throws IOException;
  
  public final HttpRequestFactory createRequestFactory()
  {
    return createRequestFactory(null);
  }
  
  public final HttpRequestFactory createRequestFactory(HttpRequestInitializer paramHttpRequestInitializer)
  {
    return new HttpRequestFactory(this, paramHttpRequestInitializer);
  }
  
  public void shutdown()
    throws IOException
  {}
  
  public boolean supportsMethod(String paramString)
    throws IOException
  {
    return Arrays.binarySearch(SUPPORTED_METHODS, paramString) >= 0;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */