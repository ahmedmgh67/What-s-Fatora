package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;

public class zzuh
  extends zzl<zzuk>
{
  private final String zzahp;
  
  public zzuh(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, zzg paramzzg)
  {
    super(paramContext, paramLooper, 77, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzahp = paramzzg.zzxh();
  }
  
  private Bundle zzqm()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("authPackage", this.zzahp);
    return localBundle;
  }
  
  public void zza(zzuj paramzzuj)
  {
    try
    {
      ((zzuk)zzwW()).zza(paramzzuj);
      return;
    }
    catch (RemoteException paramzzuj) {}
  }
  
  public void zza(zzuj paramzzuj, String paramString)
  {
    try
    {
      ((zzuk)zzwW()).zza(paramzzuj, paramString);
      return;
    }
    catch (RemoteException paramzzuj) {}
  }
  
  protected zzuk zzaq(IBinder paramIBinder)
  {
    return zzuk.zza.zzas(paramIBinder);
  }
  
  public void zzb(zzuj paramzzuj, String paramString)
  {
    try
    {
      ((zzuk)zzwW()).zzb(paramzzuj, paramString);
      return;
    }
    catch (RemoteException paramzzuj) {}
  }
  
  protected String zzeu()
  {
    return "com.google.android.gms.appinvite.service.START";
  }
  
  protected String zzev()
  {
    return "com.google.android.gms.appinvite.internal.IAppInviteService";
  }
  
  protected Bundle zzql()
  {
    return zzqm();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzuh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */