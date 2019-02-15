package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.ExponentialBackOff.Builder;
import com.google.api.client.util.NanoClock;
import java.io.IOException;

@Deprecated
@Beta
public class ExponentialBackOffPolicy
  implements BackOffPolicy
{
  public static final int DEFAULT_INITIAL_INTERVAL_MILLIS = 500;
  public static final int DEFAULT_MAX_ELAPSED_TIME_MILLIS = 900000;
  public static final int DEFAULT_MAX_INTERVAL_MILLIS = 60000;
  public static final double DEFAULT_MULTIPLIER = 1.5D;
  public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.5D;
  private final ExponentialBackOff exponentialBackOff;
  
  public ExponentialBackOffPolicy()
  {
    this(new Builder());
  }
  
  protected ExponentialBackOffPolicy(Builder paramBuilder)
  {
    this.exponentialBackOff = paramBuilder.exponentialBackOffBuilder.build();
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public final int getCurrentIntervalMillis()
  {
    return this.exponentialBackOff.getCurrentIntervalMillis();
  }
  
  public final long getElapsedTimeMillis()
  {
    return this.exponentialBackOff.getElapsedTimeMillis();
  }
  
  public final int getInitialIntervalMillis()
  {
    return this.exponentialBackOff.getInitialIntervalMillis();
  }
  
  public final int getMaxElapsedTimeMillis()
  {
    return this.exponentialBackOff.getMaxElapsedTimeMillis();
  }
  
  public final int getMaxIntervalMillis()
  {
    return this.exponentialBackOff.getMaxIntervalMillis();
  }
  
  public final double getMultiplier()
  {
    return this.exponentialBackOff.getMultiplier();
  }
  
  public long getNextBackOffMillis()
    throws IOException
  {
    return this.exponentialBackOff.nextBackOffMillis();
  }
  
  public final double getRandomizationFactor()
  {
    return this.exponentialBackOff.getRandomizationFactor();
  }
  
  public boolean isBackOffRequired(int paramInt)
  {
    switch (paramInt)
    {
    case 501: 
    case 502: 
    default: 
      return false;
    }
    return true;
  }
  
  public final void reset()
  {
    this.exponentialBackOff.reset();
  }
  
  @Deprecated
  @Beta
  public static class Builder
  {
    final ExponentialBackOff.Builder exponentialBackOffBuilder = new ExponentialBackOff.Builder();
    
    public ExponentialBackOffPolicy build()
    {
      return new ExponentialBackOffPolicy(this);
    }
    
    public final int getInitialIntervalMillis()
    {
      return this.exponentialBackOffBuilder.getInitialIntervalMillis();
    }
    
    public final int getMaxElapsedTimeMillis()
    {
      return this.exponentialBackOffBuilder.getMaxElapsedTimeMillis();
    }
    
    public final int getMaxIntervalMillis()
    {
      return this.exponentialBackOffBuilder.getMaxIntervalMillis();
    }
    
    public final double getMultiplier()
    {
      return this.exponentialBackOffBuilder.getMultiplier();
    }
    
    public final NanoClock getNanoClock()
    {
      return this.exponentialBackOffBuilder.getNanoClock();
    }
    
    public final double getRandomizationFactor()
    {
      return this.exponentialBackOffBuilder.getRandomizationFactor();
    }
    
    public Builder setInitialIntervalMillis(int paramInt)
    {
      this.exponentialBackOffBuilder.setInitialIntervalMillis(paramInt);
      return this;
    }
    
    public Builder setMaxElapsedTimeMillis(int paramInt)
    {
      this.exponentialBackOffBuilder.setMaxElapsedTimeMillis(paramInt);
      return this;
    }
    
    public Builder setMaxIntervalMillis(int paramInt)
    {
      this.exponentialBackOffBuilder.setMaxIntervalMillis(paramInt);
      return this;
    }
    
    public Builder setMultiplier(double paramDouble)
    {
      this.exponentialBackOffBuilder.setMultiplier(paramDouble);
      return this;
    }
    
    public Builder setNanoClock(NanoClock paramNanoClock)
    {
      this.exponentialBackOffBuilder.setNanoClock(paramNanoClock);
      return this;
    }
    
    public Builder setRandomizationFactor(double paramDouble)
    {
      this.exponentialBackOffBuilder.setRandomizationFactor(paramDouble);
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\ExponentialBackOffPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */