package com.google.api.client.util;

public abstract interface Clock
{
  public static final Clock SYSTEM = new Clock()
  {
    public long currentTimeMillis()
    {
      return System.currentTimeMillis();
    }
  };
  
  public abstract long currentTimeMillis();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Clock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */