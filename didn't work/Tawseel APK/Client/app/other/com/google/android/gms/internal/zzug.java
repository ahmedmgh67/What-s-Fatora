package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteApi;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class zzug
  implements AppInviteApi
{
  public PendingResult<Status> convertInvitation(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.zza(new zzd(paramGoogleApiClient, paramString));
  }
  
  public PendingResult<AppInviteInvitationResult> getInvitation(GoogleApiClient paramGoogleApiClient, Activity paramActivity, boolean paramBoolean)
  {
    return paramGoogleApiClient.zza(new zze(paramGoogleApiClient, paramActivity, paramBoolean));
  }
  
  public PendingResult<Status> updateInvitationOnInstall(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.zza(new zzc(paramGoogleApiClient, paramString));
  }
  
  static class zza
    extends zzuj.zza
  {
    public void zza(Status paramStatus, Intent paramIntent)
    {
      throw new UnsupportedOperationException();
    }
    
    public void zzd(Status paramStatus)
      throws RemoteException
    {
      throw new UnsupportedOperationException();
    }
  }
  
  static abstract class zzb<R extends Result>
    extends zzzv.zza<R, zzuh>
  {
    public zzb(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  final class zzc
    extends zzug.zzb<Status>
  {
    private final String zzahi;
    
    public zzc(GoogleApiClient paramGoogleApiClient, String paramString)
    {
      super();
      this.zzahi = paramString;
    }
    
    protected void zza(zzuh paramzzuh)
      throws RemoteException
    {
      paramzzuh.zzb(new zzug.zza()
      {
        public void zzd(Status paramAnonymousStatus)
          throws RemoteException
        {
          zzug.zzc.this.zzb(paramAnonymousStatus);
        }
      }, this.zzahi);
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  final class zzd
    extends zzug.zzb<Status>
  {
    private final String zzahi;
    
    public zzd(GoogleApiClient paramGoogleApiClient, String paramString)
    {
      super();
      this.zzahi = paramString;
    }
    
    protected void zza(zzuh paramzzuh)
      throws RemoteException
    {
      paramzzuh.zza(new zzug.zza()
      {
        public void zzd(Status paramAnonymousStatus)
          throws RemoteException
        {
          zzug.zzd.this.zzb(paramAnonymousStatus);
        }
      }, this.zzahi);
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  final class zze
    extends zzug.zzb<AppInviteInvitationResult>
  {
    private final Activity zzahl;
    private final boolean zzahm;
    
    public zze(GoogleApiClient paramGoogleApiClient, Activity paramActivity, boolean paramBoolean)
    {
      super();
      this.zzahl = paramActivity;
      this.zzahm = paramBoolean;
      if (this.zzahl != null) {}
      for (this$1 = this.zzahl.getIntent();; this$1 = null) {
        return;
      }
    }
    
    protected void zza(zzuh paramzzuh)
      throws RemoteException
    {
      if (AppInviteReferral.hasReferral(zzug.this))
      {
        zzb(new zzui(Status.zzayh, zzug.this));
        paramzzuh.zza(null);
        return;
      }
      paramzzuh.zza(new zzug.zza()
      {
        public void zza(Status paramAnonymousStatus, Intent paramAnonymousIntent)
        {
          zzug.zze.this.zzb(new zzui(paramAnonymousStatus, paramAnonymousIntent));
          if ((AppInviteReferral.hasReferral(paramAnonymousIntent)) && (zzug.zze.zza(zzug.zze.this)) && (zzug.zze.zzb(zzug.zze.this) != null)) {
            zzug.zze.zzb(zzug.zze.this).startActivity(paramAnonymousIntent);
          }
        }
      });
    }
    
    protected AppInviteInvitationResult zze(Status paramStatus)
    {
      return new zzui(paramStatus, new Intent());
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */