package com.google.api.client.util;

import java.io.IOException;

@Beta
public final class BackOffUtils
{
  public static boolean next(Sleeper paramSleeper, BackOff paramBackOff)
    throws InterruptedException, IOException
  {
    long l = paramBackOff.nextBackOffMillis();
    if (l == -1L) {
      return false;
    }
    paramSleeper.sleep(l);
    return true;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\BackOffUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */