package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzabb
  extends zzzw
{
  private TaskCompletionSource<Void> zzayo = new TaskCompletionSource();
  
  private zzabb(zzaax paramzzaax)
  {
    super(paramzzaax);
    this.zzaBs.zza("GmsAvailabilityHelper", this);
  }
  
  public static zzabb zzu(Activity paramActivity)
  {
    paramActivity = zzs(paramActivity);
    zzabb localzzabb = (zzabb)paramActivity.zza("GmsAvailabilityHelper", zzabb.class);
    if (localzzabb != null)
    {
      if (localzzabb.zzayo.getTask().isComplete()) {
        localzzabb.zzayo = new TaskCompletionSource();
      }
      return localzzabb;
    }
    return new zzabb(paramActivity);
  }
  
  public Task<Void> getTask()
  {
    return this.zzayo.getTask();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.zzayo.setException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
  }
  
  protected void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    this.zzayo.setException(zzb.zzl(paramConnectionResult));
  }
  
  public void zzk(ConnectionResult paramConnectionResult)
  {
    zzb(paramConnectionResult, 0);
  }
  
  protected void zzuW()
  {
    int i = this.zzaxX.isGooglePlayServicesAvailable(this.zzaBs.zzwo());
    if (i == 0)
    {
      this.zzayo.setResult(null);
      return;
    }
    zzk(new ConnectionResult(i, null));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzabb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */