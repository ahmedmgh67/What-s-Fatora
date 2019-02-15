package com.google.api.client.http;

import java.io.IOException;
import java.io.InputStream;

public abstract class LowLevelHttpResponse
{
  public void disconnect()
    throws IOException
  {}
  
  public abstract InputStream getContent()
    throws IOException;
  
  public abstract String getContentEncoding()
    throws IOException;
  
  public abstract long getContentLength()
    throws IOException;
  
  public abstract String getContentType()
    throws IOException;
  
  public abstract int getHeaderCount()
    throws IOException;
  
  public abstract String getHeaderName(int paramInt)
    throws IOException;
  
  public abstract String getHeaderValue(int paramInt)
    throws IOException;
  
  public abstract String getReasonPhrase()
    throws IOException;
  
  public abstract int getStatusCode()
    throws IOException;
  
  public abstract String getStatusLine()
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\LowLevelHttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */