package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzf;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class zzabq
{
  public static final Status zzaBV = new Status(8, "The connection to Google Play services was lost");
  private static final zzzx<?>[] zzaBW = new zzzx[0];
  private final Map<Api.zzc<?>, Api.zze> zzaAr;
  final Set<zzzx<?>> zzaBX = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
  private final zzb zzaBY = new zzb()
  {
    public void zzc(zzzx<?> paramAnonymouszzzx)
    {
      zzabq.this.zzaBX.remove(paramAnonymouszzzx);
      if (paramAnonymouszzzx.zzuR() != null) {
        zzabq.zza(zzabq.this);
      }
    }
  };
  
  public zzabq(Map<Api.zzc<?>, Api.zze> paramMap)
  {
    this.zzaAr = paramMap;
  }
  
  private static void zza(zzzx<?> paramzzzx, zzf paramzzf, IBinder paramIBinder)
  {
    if (paramzzzx.isReady())
    {
      paramzzzx.zza(new zza(paramzzzx, paramzzf, paramIBinder, null));
      return;
    }
    if ((paramIBinder != null) && (paramIBinder.isBinderAlive()))
    {
      zza localzza = new zza(paramzzzx, paramzzf, paramIBinder, null);
      paramzzzx.zza(localzza);
      try
      {
        paramIBinder.linkToDeath(localzza, 0);
        return;
      }
      catch (RemoteException paramIBinder)
      {
        paramzzzx.cancel();
        paramzzf.remove(paramzzzx.zzuR().intValue());
        return;
      }
    }
    paramzzzx.zza(null);
    paramzzzx.cancel();
    paramzzf.remove(paramzzzx.zzuR().intValue());
  }
  
  public void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzaBX.size());
  }
  
  public void release()
  {
    zzzx[] arrayOfzzzx = (zzzx[])this.zzaBX.toArray(zzaBW);
    int j = arrayOfzzzx.length;
    int i = 0;
    if (i < j)
    {
      zzzx localzzzx = arrayOfzzzx[i];
      localzzzx.zza(null);
      if (localzzzx.zzuR() == null) {
        if (localzzzx.zzvc()) {
          this.zzaBX.remove(localzzzx);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        localzzzx.zzve();
        zza(localzzzx, null, ((Api.zze)this.zzaAr.get(((zzzv.zza)localzzzx).zzuH())).zzuJ());
        this.zzaBX.remove(localzzzx);
      }
    }
  }
  
  void zzb(zzzx<? extends Result> paramzzzx)
  {
    this.zzaBX.add(paramzzzx);
    paramzzzx.zza(this.zzaBY);
  }
  
  public void zzww()
  {
    zzzx[] arrayOfzzzx = (zzzx[])this.zzaBX.toArray(zzaBW);
    int j = arrayOfzzzx.length;
    int i = 0;
    while (i < j)
    {
      arrayOfzzzx[i].zzB(zzaBV);
      i += 1;
    }
  }
  
  private static class zza
    implements IBinder.DeathRecipient, zzabq.zzb
  {
    private final WeakReference<zzzx<?>> zzaCa;
    private final WeakReference<zzf> zzaCb;
    private final WeakReference<IBinder> zzaCc;
    
    private zza(zzzx<?> paramzzzx, zzf paramzzf, IBinder paramIBinder)
    {
      this.zzaCb = new WeakReference(paramzzf);
      this.zzaCa = new WeakReference(paramzzzx);
      this.zzaCc = new WeakReference(paramIBinder);
    }
    
    private void zzwx()
    {
      Object localObject = (zzzx)this.zzaCa.get();
      zzf localzzf = (zzf)this.zzaCb.get();
      if ((localzzf != null) && (localObject != null)) {
        localzzf.remove(((zzzx)localObject).zzuR().intValue());
      }
      localObject = (IBinder)this.zzaCc.get();
      if (localObject != null) {
        ((IBinder)localObject).unlinkToDeath(this, 0);
      }
    }
    
    public void binderDied()
    {
      zzwx();
    }
    
    public void zzc(zzzx<?> paramzzzx)
    {
      zzwx();
    }
  }
  
  static abstract interface zzb
  {
    public abstract void zzc(zzzx<?> paramzzzx);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzabq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */