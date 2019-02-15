package com.google.firebase.storage;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqv;
import com.google.android.gms.internal.zzbra;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

class zze<TListenerType, TResult extends StorageTask.ProvideError>
{
  private final HashMap<TListenerType, zzbra> zzclA = new HashMap();
  private StorageTask<TResult> zzclB;
  private int zzclC;
  private zza<TListenerType, TResult> zzclD;
  private final Queue<TListenerType> zzclz = new ConcurrentLinkedQueue();
  
  public zze(@NonNull StorageTask<TResult> paramStorageTask, int paramInt, @NonNull zza<TListenerType, TResult> paramzza)
  {
    this.zzclB = paramStorageTask;
    this.zzclC = paramInt;
    this.zzclD = paramzza;
  }
  
  public void zza(@Nullable Activity paramActivity, @Nullable Executor paramExecutor, @NonNull final TListenerType paramTListenerType)
  {
    boolean bool = true;
    zzac.zzw(paramTListenerType);
    for (;;)
    {
      synchronized (this.zzclB.zzaaR())
      {
        if ((this.zzclB.zzaaQ() & this.zzclC) != 0)
        {
          i = 1;
          this.zzclz.add(paramTListenerType);
          this.zzclA.put(paramTListenerType, new zzbra(paramExecutor));
          if (paramActivity != null)
          {
            if (Build.VERSION.SDK_INT >= 17)
            {
              if (!paramActivity.isDestroyed()) {
                zzac.zzb(bool, "Activity is already destroyed!");
              }
            }
            else {
              zzbqv.zzabf().zza(paramActivity, paramTListenerType, new Runnable()
              {
                public void run()
                {
                  zze.this.zzaG(paramTListenerType);
                }
              });
            }
          }
          else
          {
            if (i != 0) {
              this.zzclD.zzl(paramTListenerType, this.zzclB.zzaaS());
            }
            return;
          }
          bool = false;
        }
      }
      int i = 0;
    }
  }
  
  public void zzaG(@NonNull TListenerType paramTListenerType)
  {
    zzac.zzw(paramTListenerType);
    synchronized (this.zzclB.zzaaR())
    {
      this.zzclA.remove(paramTListenerType);
      this.zzclz.remove(paramTListenerType);
      zzbqv.zzabf().zzaH(paramTListenerType);
      return;
    }
  }
  
  public void zzaaZ()
  {
    if ((this.zzclB.zzaaQ() & this.zzclC) != 0)
    {
      final StorageTask.ProvideError localProvideError = this.zzclB.zzaaS();
      Iterator localIterator = this.zzclz.iterator();
      while (localIterator.hasNext())
      {
        final Object localObject = localIterator.next();
        zzbra localzzbra = (zzbra)this.zzclA.get(localObject);
        if (localzzbra != null) {
          localzzbra.zzx(new Runnable()
          {
            public void run()
            {
              zze.zza(zze.this).zzl(localObject, localProvideError);
            }
          });
        }
      }
    }
  }
  
  public static abstract interface zza<TListenerType, TResult>
  {
    public abstract void zzl(@NonNull TListenerType paramTListenerType, @NonNull TResult paramTResult);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */