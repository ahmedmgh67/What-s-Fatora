package com.google.android.gms.common.api;

public abstract class OptionalPendingResult<R extends Result>
  extends PendingResult<R>
{
  public abstract R get();
  
  public abstract boolean isDone();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\api\OptionalPendingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */