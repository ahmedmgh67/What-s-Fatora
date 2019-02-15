package com.google.api.client.util;

import java.io.IOException;

public class ExponentialBackOff
  implements BackOff
{
  public static final int DEFAULT_INITIAL_INTERVAL_MILLIS = 500;
  public static final int DEFAULT_MAX_ELAPSED_TIME_MILLIS = 900000;
  public static final int DEFAULT_MAX_INTERVAL_MILLIS = 60000;
  public static final double DEFAULT_MULTIPLIER = 1.5D;
  public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5D;
  private int currentIntervalMillis;
  private final int initialIntervalMillis;
  private final int maxElapsedTimeMillis;
  private final int maxIntervalMillis;
  private final double multiplier;
  private final NanoClock nanoClock;
  private final double randomizationFactor;
  long startTimeNanos;
  
  public ExponentialBackOff()
  {
    this(new Builder());
  }
  
  protected ExponentialBackOff(Builder paramBuilder)
  {
    this.initialIntervalMillis = paramBuilder.initialIntervalMillis;
    this.randomizationFactor = paramBuilder.randomizationFactor;
    this.multiplier = paramBuilder.multiplier;
    this.maxIntervalMillis = paramBuilder.maxIntervalMillis;
    this.maxElapsedTimeMillis = paramBuilder.maxElapsedTimeMillis;
    this.nanoClock = paramBuilder.nanoClock;
    if (this.initialIntervalMillis > 0)
    {
      bool1 = true;
      Preconditions.checkArgument(bool1);
      if ((0.0D > this.randomizationFactor) || (this.randomizationFactor >= 1.0D)) {
        break label146;
      }
      bool1 = true;
      label87:
      Preconditions.checkArgument(bool1);
      if (this.multiplier < 1.0D) {
        break label151;
      }
      bool1 = true;
      label102:
      Preconditions.checkArgument(bool1);
      if (this.maxIntervalMillis < this.initialIntervalMillis) {
        break label156;
      }
      bool1 = true;
      label119:
      Preconditions.checkArgument(bool1);
      if (this.maxElapsedTimeMillis <= 0) {
        break label161;
      }
    }
    label146:
    label151:
    label156:
    label161:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      Preconditions.checkArgument(bool1);
      reset();
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label87;
      bool1 = false;
      break label102;
      bool1 = false;
      break label119;
    }
  }
  
  static int getRandomValueFromInterval(double paramDouble1, double paramDouble2, int paramInt)
  {
    paramDouble1 *= paramInt;
    double d = paramInt - paramDouble1;
    return (int)((paramInt + paramDouble1 - d + 1.0D) * paramDouble2 + d);
  }
  
  private void incrementCurrentInterval()
  {
    if (this.currentIntervalMillis >= this.maxIntervalMillis / this.multiplier)
    {
      this.currentIntervalMillis = this.maxIntervalMillis;
      return;
    }
    this.currentIntervalMillis = ((int)(this.currentIntervalMillis * this.multiplier));
  }
  
  public final int getCurrentIntervalMillis()
  {
    return this.currentIntervalMillis;
  }
  
  public final long getElapsedTimeMillis()
  {
    return (this.nanoClock.nanoTime() - this.startTimeNanos) / 1000000L;
  }
  
  public final int getInitialIntervalMillis()
  {
    return this.initialIntervalMillis;
  }
  
  public final int getMaxElapsedTimeMillis()
  {
    return this.maxElapsedTimeMillis;
  }
  
  public final int getMaxIntervalMillis()
  {
    return this.maxIntervalMillis;
  }
  
  public final double getMultiplier()
  {
    return this.multiplier;
  }
  
  public final double getRandomizationFactor()
  {
    return this.randomizationFactor;
  }
  
  public long nextBackOffMillis()
    throws IOException
  {
    if (getElapsedTimeMillis() > this.maxElapsedTimeMillis) {
      return -1L;
    }
    int i = getRandomValueFromInterval(this.randomizationFactor, Math.random(), this.currentIntervalMillis);
    incrementCurrentInterval();
    return i;
  }
  
  public final void reset()
  {
    this.currentIntervalMillis = this.initialIntervalMillis;
    this.startTimeNanos = this.nanoClock.nanoTime();
  }
  
  public static class Builder
  {
    int initialIntervalMillis = 500;
    int maxElapsedTimeMillis = 900000;
    int maxIntervalMillis = 60000;
    double multiplier = 1.5D;
    NanoClock nanoClock = NanoClock.SYSTEM;
    double randomizationFactor = 0.5D;
    
    public ExponentialBackOff build()
    {
      return new ExponentialBackOff(this);
    }
    
    public final int getInitialIntervalMillis()
    {
      return this.initialIntervalMillis;
    }
    
    public final int getMaxElapsedTimeMillis()
    {
      return this.maxElapsedTimeMillis;
    }
    
    public final int getMaxIntervalMillis()
    {
      return this.maxIntervalMillis;
    }
    
    public final double getMultiplier()
    {
      return this.multiplier;
    }
    
    public final NanoClock getNanoClock()
    {
      return this.nanoClock;
    }
    
    public final double getRandomizationFactor()
    {
      return this.randomizationFactor;
    }
    
    public Builder setInitialIntervalMillis(int paramInt)
    {
      this.initialIntervalMillis = paramInt;
      return this;
    }
    
    public Builder setMaxElapsedTimeMillis(int paramInt)
    {
      this.maxElapsedTimeMillis = paramInt;
      return this;
    }
    
    public Builder setMaxIntervalMillis(int paramInt)
    {
      this.maxIntervalMillis = paramInt;
      return this;
    }
    
    public Builder setMultiplier(double paramDouble)
    {
      this.multiplier = paramDouble;
      return this;
    }
    
    public Builder setNanoClock(NanoClock paramNanoClock)
    {
      this.nanoClock = ((NanoClock)Preconditions.checkNotNull(paramNanoClock));
      return this;
    }
    
    public Builder setRandomizationFactor(double paramDouble)
    {
      this.randomizationFactor = paramDouble;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ExponentialBackOff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */