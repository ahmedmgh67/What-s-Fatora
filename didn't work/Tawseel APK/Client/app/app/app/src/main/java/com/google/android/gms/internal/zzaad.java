package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public class zzaad
{
  private final Map<zzzx<?>, Boolean> zzazC = Collections.synchronizedMap(new WeakHashMap());
  private final Map<TaskCompletionSource<?>, Boolean> zzazD = Collections.synchronizedMap(new WeakHashMap());
  
  private void zza(boolean paramBoolean, Status paramStatus)
  {
    Object localObject2;
    synchronized (this.zzazC)
    {
      localObject2 = new HashMap(this.zzazC);
    }
    synchronized (this.zzazD)
    {
      ??? = new HashMap(this.zzazD);
      localObject2 = ((Map)localObject2).entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ??? = (Map.Entry)((Iterator)localObject2).next();
        if ((paramBoolean) || (((Boolean)((Map.Entry)???).getValue()).booleanValue()))
        {
          ((zzzx)((Map.Entry)???).getKey()).zzB(paramStatus);
          continue;
          paramStatus = finally;
          throw paramStatus;
        }
      }
    }
    ??? = ((Map)???).entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      if ((paramBoolean) || (((Boolean)((Map.Entry)localObject2).getValue()).booleanValue())) {
        ((TaskCompletionSource)((Map.Entry)localObject2).getKey()).trySetException(new zza(paramStatus));
      }
    }
  }
  
  void zza(final zzzx<? extends Result> paramzzzx, boolean paramBoolean)
  {
    this.zzazC.put(paramzzzx, Boolean.valueOf(paramBoolean));
    paramzzzx.zza(new PendingResult.zza()
    {
      public void zzx(Status paramAnonymousStatus)
      {
        zzaad.zza(zzaad.this).remove(paramzzzx);
      }
    });
  }
  
  <TResult> void zza(final TaskCompletionSource<TResult> paramTaskCompletionSource, boolean paramBoolean)
  {
    this.zzazD.put(paramTaskCompletionSource, Boolean.valueOf(paramBoolean));
    paramTaskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener()
    {
      public void onComplete(@NonNull Task<TResult> paramAnonymousTask)
      {
        zzaad.zzb(zzaad.this).remove(paramTaskCompletionSource);
      }
    });
  }
  
  boolean zzvu()
  {
    return (!this.zzazC.isEmpty()) || (!this.zzazD.isEmpty());
  }
  
  public void zzvv()
  {
    zza(false, zzaap.zzaAO);
  }
  
  public void zzvw()
  {
    zza(true, zzabq.zzaBV);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */