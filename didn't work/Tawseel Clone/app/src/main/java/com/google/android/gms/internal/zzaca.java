package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzaca<R extends Result>
  extends zzzv.zza<R, zzacb>
{
  public zzaca(GoogleApiClient paramGoogleApiClient)
  {
    super(zzabx.API, paramGoogleApiClient);
  }
  
  static abstract class zza
    extends zzaca<Status>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */