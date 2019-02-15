package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;

public class zzacb
  extends zzl<zzacd>
{
  public zzacb(Context paramContext, Looper paramLooper, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 39, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected zzacd zzbz(IBinder paramIBinder)
  {
    return zzacd.zza.zzbB(paramIBinder);
  }
  
  public String zzeu()
  {
    return "com.google.android.gms.common.service.START";
  }
  
  protected String zzev()
  {
    return "com.google.android.gms.common.internal.service.ICommonService";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */