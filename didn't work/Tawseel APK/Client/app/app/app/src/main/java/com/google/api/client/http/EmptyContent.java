package com.google.api.client.http;

import java.io.IOException;
import java.io.OutputStream;

public class EmptyContent
  implements HttpContent
{
  public long getLength()
    throws IOException
  {
    return 0L;
  }
  
  public String getType()
  {
    return null;
  }
  
  public boolean retrySupported()
  {
    return true;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.flush();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\EmptyContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */