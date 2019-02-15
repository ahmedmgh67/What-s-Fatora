package com.google.api.client.testing.util;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;

@Beta
public class MockBackOff
  implements BackOff
{
  private long backOffMillis;
  private int maxTries = 10;
  private int numTries;
  
  public final int getMaxTries()
  {
    return this.numTries;
  }
  
  public final int getNumberOfTries()
  {
    return this.numTries;
  }
  
  public long nextBackOffMillis()
    throws IOException
  {
    if ((this.numTries >= this.maxTries) || (this.backOffMillis == -1L)) {
      return -1L;
    }
    this.numTries += 1;
    return this.backOffMillis;
  }
  
  public void reset()
    throws IOException
  {
    this.numTries = 0;
  }
  
  public MockBackOff setBackOffMillis(long paramLong)
  {
    if ((paramLong == -1L) || (paramLong >= 0L)) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.backOffMillis = paramLong;
      return this;
    }
  }
  
  public MockBackOff setMaxTries(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.maxTries = paramInt;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\util\MockBackOff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */