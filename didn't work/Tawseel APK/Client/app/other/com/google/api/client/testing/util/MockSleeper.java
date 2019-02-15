package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Sleeper;

@Beta
public class MockSleeper
  implements Sleeper
{
  private int count;
  private long lastMillis;
  
  public final int getCount()
  {
    return this.count;
  }
  
  public final long getLastMillis()
  {
    return this.lastMillis;
  }
  
  public void sleep(long paramLong)
    throws InterruptedException
  {
    this.count += 1;
    this.lastMillis = paramLong;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\util\MockSleeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */