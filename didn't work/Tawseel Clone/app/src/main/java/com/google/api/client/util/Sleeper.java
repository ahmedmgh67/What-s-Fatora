package com.google.api.client.util;

public abstract interface Sleeper
{
  public static final Sleeper DEFAULT = new Sleeper()
  {
    public void sleep(long paramAnonymousLong)
      throws InterruptedException
    {
      Thread.sleep(paramAnonymousLong);
    }
  };
  
  public abstract void sleep(long paramLong)
    throws InterruptedException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Sleeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */