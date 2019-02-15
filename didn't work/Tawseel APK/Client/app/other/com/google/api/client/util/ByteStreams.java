package com.google.api.client.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ByteStreams
{
  private static final int BUF_SIZE = 4096;
  
  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(paramOutputStream);
    byte[] arrayOfByte = new byte['က'];
    int i;
    for (long l = 0L;; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return l;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public static InputStream limit(InputStream paramInputStream, long paramLong)
  {
    return new LimitedInputStream(paramInputStream, paramLong);
  }
  
  public static int read(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(paramArrayOfByte);
    if (paramInt2 < 0) {
      throw new IndexOutOfBoundsException("len is negative");
    }
    int i = 0;
    for (;;)
    {
      int j;
      if (i < paramInt2)
      {
        j = paramInputStream.read(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
        if (j != -1) {}
      }
      else
      {
        return i;
      }
      i += j;
    }
  }
  
  private static final class LimitedInputStream
    extends FilterInputStream
  {
    private long left;
    private long mark = -1L;
    
    LimitedInputStream(InputStream paramInputStream, long paramLong)
    {
      super();
      Preconditions.checkNotNull(paramInputStream);
      if (paramLong >= 0L) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool, "limit must be non-negative");
        this.left = paramLong;
        return;
      }
    }
    
    public int available()
      throws IOException
    {
      return (int)Math.min(this.in.available(), this.left);
    }
    
    public void mark(int paramInt)
    {
      try
      {
        this.in.mark(paramInt);
        this.mark = this.left;
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    public int read()
      throws IOException
    {
      int i;
      if (this.left == 0L) {
        i = -1;
      }
      int j;
      do
      {
        return i;
        j = this.in.read();
        i = j;
      } while (j == -1);
      this.left -= 1L;
      return j;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (this.left == 0L) {
        paramInt1 = -1;
      }
      do
      {
        return paramInt1;
        paramInt2 = (int)Math.min(paramInt2, this.left);
        paramInt2 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
        paramInt1 = paramInt2;
      } while (paramInt2 == -1);
      this.left -= paramInt2;
      return paramInt2;
    }
    
    public void reset()
      throws IOException
    {
      try
      {
        if (!this.in.markSupported()) {
          throw new IOException("Mark not supported");
        }
      }
      finally {}
      if (this.mark == -1L) {
        throw new IOException("Mark not set");
      }
      this.in.reset();
      this.left = this.mark;
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      paramLong = Math.min(paramLong, this.left);
      paramLong = this.in.skip(paramLong);
      this.left -= paramLong;
      return paramLong;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ByteStreams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */