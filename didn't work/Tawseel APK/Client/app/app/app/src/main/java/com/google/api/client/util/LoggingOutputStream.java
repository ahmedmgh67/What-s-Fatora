package com.google.api.client.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingOutputStream
  extends FilterOutputStream
{
  private final LoggingByteArrayOutputStream logStream;
  
  public LoggingOutputStream(OutputStream paramOutputStream, Logger paramLogger, Level paramLevel, int paramInt)
  {
    super(paramOutputStream);
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
  
  public void write(int paramInt)
    throws IOException
  {
    this.out.write(paramInt);
    this.logStream.write(paramInt);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    this.logStream.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\LoggingOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */