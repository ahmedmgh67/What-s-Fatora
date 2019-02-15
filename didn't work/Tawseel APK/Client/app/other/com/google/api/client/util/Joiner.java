package com.google.api.client.util;

public final class Joiner
{
  private final com.google.api.client.repackaged.com.google.common.base.Joiner wrapped;
  
  private Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner paramJoiner)
  {
    this.wrapped = paramJoiner;
  }
  
  public static Joiner on(char paramChar)
  {
    return new Joiner(com.google.api.client.repackaged.com.google.common.base.Joiner.on(paramChar));
  }
  
  public final String join(Iterable<?> paramIterable)
  {
    return this.wrapped.join(paramIterable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Joiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */