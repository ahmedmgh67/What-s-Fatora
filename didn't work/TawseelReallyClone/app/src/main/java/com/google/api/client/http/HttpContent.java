package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

public abstract interface HttpContent
  extends StreamingContent
{
  public abstract long getLength()
    throws IOException;
  
  public abstract String getType();
  
  public abstract boolean retrySupported();
  
  public abstract void writeTo(OutputStream paramOutputStream)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */