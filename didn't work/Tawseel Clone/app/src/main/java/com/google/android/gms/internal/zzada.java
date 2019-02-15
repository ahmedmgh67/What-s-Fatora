package com.google.android.gms.internal;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class zzada
  extends CommonStatusCodes
{
  public static String getStatusCodeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return CommonStatusCodes.getStatusCodeString(paramInt);
    case 6500: 
      return "NOT_AUTHORIZED_TO_FETCH";
    case 6501: 
      return "ANOTHER_FETCH_INFLIGHT";
    case 6502: 
      return "FETCH_THROTTLED";
    case 6503: 
      return "NOT_AVAILABLE";
    case 6504: 
      return "FAILURE_CACHE";
    case -6505: 
      return "SUCCESS_FRESH";
    case -6506: 
      return "SUCCESS_CACHE";
    case 6507: 
      return "FETCH_THROTTLED_STALE";
    }
    return "SUCCESS_CACHE_STALE";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzada.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */