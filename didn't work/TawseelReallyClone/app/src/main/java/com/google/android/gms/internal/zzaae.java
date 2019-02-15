package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zza;

public class zzaae
  extends zzzw
{
  private zzaap zzaxK;
  private final zza<zzzs<?>> zzazH = new zza();
  
  private zzaae(zzaax paramzzaax)
  {
    super(paramzzaax);
    this.zzaBs.zza("ConnectionlessLifecycleHelper", this);
  }
  
  public static void zza(Activity paramActivity, zzaap paramzzaap, zzzs<?> paramzzzs)
  {
    zzaax localzzaax = zzs(paramActivity);
    zzaae localzzaae = (zzaae)localzzaax.zza("ConnectionlessLifecycleHelper", zzaae.class);
    paramActivity = localzzaae;
    if (localzzaae == null) {
      paramActivity = new zzaae(localzzaax);
    }
    paramActivity.zzaxK = paramzzaap;
    paramActivity.zza(paramzzzs);
    paramzzaap.zza(paramActivity);
  }
  
  private void zza(zzzs<?> paramzzzs)
  {
    zzac.zzb(paramzzzs, "ApiKey cannot be null");
    this.zzazH.add(paramzzzs);
  }
  
  public void onStart()
  {
    super.onStart();
    if (!this.zzazH.isEmpty()) {
      this.zzaxK.zza(this);
    }
  }
  
  public void onStop()
  {
    super.onStop();
    this.zzaxK.zzb(this);
  }
  
  protected void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    this.zzaxK.zza(paramConnectionResult, paramInt);
  }
  
  protected void zzuW()
  {
    this.zzaxK.zzuW();
  }
  
  zza<zzzs<?>> zzvx()
  {
    return this.zzazH;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */