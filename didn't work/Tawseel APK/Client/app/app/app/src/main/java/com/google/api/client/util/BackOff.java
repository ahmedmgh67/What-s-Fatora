package com.google.api.client.util;

import java.io.IOException;

public abstract interface BackOff
{
  public static final long STOP = -1L;
  public static final BackOff STOP_BACKOFF = new BackOff()
  {
    public long nextBackOffMillis()
      throws IOException
    {
      return -1L;
    }
    
    public void reset()
      throws IOException
    {}
  };
  public static final BackOff ZERO_BACKOFF = new BackOff()
  {
    public long nextBackOffMillis()
      throws IOException
    {
      return 0L;
    }
    
    public void reset()
      throws IOException
    {}
  };
  
  public abstract long nextBackOffMillis()
    throws IOException;
  
  public abstract void reset()
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\BackOff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */