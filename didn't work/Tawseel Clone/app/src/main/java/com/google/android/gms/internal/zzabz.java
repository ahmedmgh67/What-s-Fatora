package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzabz
  implements zzaby
{
  public PendingResult<Status> zzg(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zzaca.zza(paramGoogleApiClient)
    {
      protected void zza(zzacb paramAnonymouszzacb)
        throws RemoteException
      {
        ((zzacd)paramAnonymouszzacb.zzwW()).zza(new zzabz.zza(this));
      }
    });
  }
  
  private static class zza
    extends zzabw
  {
    private final zzzv.zzb<Status> zzaFq;
    
    public zza(zzzv.zzb<Status> paramzzb)
    {
      this.zzaFq = paramzzb;
    }
    
    public void zzcX(int paramInt)
      throws RemoteException
    {
      this.zzaFq.setResult(new Status(paramInt));
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzabz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */