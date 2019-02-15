package com.google.api.client.util;

public final class Throwables
{
  public static RuntimeException propagate(Throwable paramThrowable)
  {
    return com.google.api.client.repackaged.com.google.common.base.Throwables.propagate(paramThrowable);
  }
  
  public static void propagateIfPossible(Throwable paramThrowable)
  {
    com.google.api.client.repackaged.com.google.common.base.Throwables.propagateIfPossible(paramThrowable);
  }
  
  public static <X extends Throwable> void propagateIfPossible(Throwable paramThrowable, Class<X> paramClass)
    throws Throwable
  {
    com.google.api.client.repackaged.com.google.common.base.Throwables.propagateIfPossible(paramThrowable, paramClass);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Throwables.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */