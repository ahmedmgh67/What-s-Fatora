package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Beta
public class TestableByteArrayOutputStream
  extends ByteArrayOutputStream
{
  private boolean closed;
  
  public void close()
    throws IOException
  {
    this.closed = true;
  }
  
  public final byte[] getBuffer()
  {
    return this.buf;
  }
  
  public final boolean isClosed()
  {
    return this.closed;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\util\TestableByteArrayOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */