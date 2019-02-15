package com.google.api.client.testing.http;

import com.google.api.client.http.HttpContent;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;

@Beta
public class MockHttpContent
  implements HttpContent
{
  private byte[] content = new byte[0];
  private long length = -1L;
  private String type;
  
  public final byte[] getContent()
  {
    return this.content;
  }
  
  public long getLength()
    throws IOException
  {
    return this.length;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public boolean retrySupported()
  {
    return true;
  }
  
  public MockHttpContent setContent(byte[] paramArrayOfByte)
  {
    this.content = ((byte[])Preconditions.checkNotNull(paramArrayOfByte));
    return this;
  }
  
  public MockHttpContent setLength(long paramLong)
  {
    if (paramLong >= -1L) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.length = paramLong;
      return this;
    }
  }
  
  public MockHttpContent setType(String paramString)
  {
    this.type = paramString;
    return this;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.content);
    paramOutputStream.flush();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\MockHttpContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */