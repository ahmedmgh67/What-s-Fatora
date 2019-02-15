package com.google.api.client.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingInputStream
  extends FilterInputStream
{
  private final LoggingByteArrayOutputStream logStream;
  
  public LoggingInputStream(InputStream paramInputStream, Logger paramLogger, Level paramLevel, int paramInt)
  {
    super(paramInputStream);
    this.logStream = new LoggingByteArrayOutputStream(paramLogger, paramLevel, paramInt);
  }
  
  public void close()
    throws IOException
  {
    this.logStream.close();
    super.close();
  }
  
  public final LoggingByteArrayOutputStream getLogStream()
  {
    return this.logStream;
  }
  
  public int read()
    throws IOException
  {
    int i = super.read();
    this.logStream.write(i);
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt2 = super.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt2 > 0) {
      this.logStream.write(paramArrayOfByte, paramInt1, paramInt2);
    }
    return paramInt2;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\LoggingInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */