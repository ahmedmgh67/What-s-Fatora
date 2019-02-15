package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

public class zzab
{
  private static final zzb zzaEZ = new zzb()
  {
    public zza zzG(Status paramAnonymousStatus)
    {
      return zzb.zzF(paramAnonymousStatus);
    }
  };
  
  public static <R extends Result, T extends zze<R>> Task<T> zza(PendingResult<R> paramPendingResult, T paramT)
  {
    zza(paramPendingResult, new zza()
    {
      public T zze(R paramAnonymousR)
      {
        zzab.this.zzb(paramAnonymousR);
        return zzab.this;
      }
    });
  }
  
  public static <R extends Result, T> Task<T> zza(PendingResult<R> paramPendingResult, zza<R, T> paramzza)
  {
    return zza(paramPendingResult, paramzza, zzaEZ);
  }
  
  public static <R extends Result, T> Task<T> zza(PendingResult<R> paramPendingResult, final zza<R, T> paramzza, final zzb paramzzb)
  {
    final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramPendingResult.zza(new PendingResult.zza()
    {
      public void zzx(Status paramAnonymousStatus)
      {
        if (paramAnonymousStatus.isSuccess())
        {
          paramAnonymousStatus = zzab.this.await(0L, TimeUnit.MILLISECONDS);
          localTaskCompletionSource.setResult(paramzza.zzf(paramAnonymousStatus));
          return;
        }
        localTaskCompletionSource.setException(paramzzb.zzG(paramAnonymousStatus));
      }
    });
    return localTaskCompletionSource.getTask();
  }
  
  public static abstract interface zza<R extends Result, T>
  {
    public abstract T zzf(R paramR);
  }
  
  public static abstract interface zzb
  {
    public abstract zza zzG(Status paramStatus);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\internal\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */