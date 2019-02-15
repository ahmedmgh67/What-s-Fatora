package com.google.api.client.testing.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import java.util.concurrent.atomic.AtomicLong;

@Beta
public class FixedClock
  implements Clock
{
  private AtomicLong currentTime;
  
  public FixedClock()
  {
    this(0L);
  }
  
  public FixedClock(long paramLong)
  {
    this.currentTime = new AtomicLong(paramLong);
  }
  
  public long currentTimeMillis()
  {
    return this.currentTime.get();
  }
  
  public FixedClock setTime(long paramLong)
  {
    this.currentTime.set(paramLong);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\FixedClock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */