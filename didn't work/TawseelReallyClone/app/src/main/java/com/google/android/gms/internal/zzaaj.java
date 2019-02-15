package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzf.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzg.zza;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzc;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzaaj
  implements zzaam
{
  private final Context mContext;
  private final Api.zza<? extends zzaxn, zzaxo> zzaxY;
  private ConnectionResult zzazA;
  private final zzaan zzazK;
  private int zzazN;
  private int zzazO = 0;
  private int zzazP;
  private final Bundle zzazQ = new Bundle();
  private final Set<Api.zzc> zzazR = new HashSet();
  private zzaxn zzazS;
  private int zzazT;
  private boolean zzazU;
  private boolean zzazV;
  private zzr zzazW;
  private boolean zzazX;
  private boolean zzazY;
  private ArrayList<Future<?>> zzazZ = new ArrayList();
  private final Lock zzazn;
  private final zzg zzazs;
  private final Map<Api<?>, Integer> zzazu;
  private final zzc zzazw;
  
  public zzaaj(zzaan paramzzaan, zzg paramzzg, Map<Api<?>, Integer> paramMap, zzc paramzzc, Api.zza<? extends zzaxn, zzaxo> paramzza, Lock paramLock, Context paramContext)
  {
    this.zzazK = paramzzaan;
    this.zzazs = paramzzg;
    this.zzazu = paramMap;
    this.zzazw = paramzzc;
    this.zzaxY = paramzza;
    this.zzazn = paramLock;
    this.mContext = paramContext;
  }
  
  private void zza(zzayb paramzzayb)
  {
    if (!zzcv(0)) {
      return;
    }
    Object localObject = paramzzayb.zzxA();
    if (((ConnectionResult)localObject).isSuccess())
    {
      localObject = paramzzayb.zzOp();
      paramzzayb = ((zzaf)localObject).zzxA();
      if (!paramzzayb.isSuccess())
      {
        localObject = String.valueOf(paramzzayb);
        Log.wtf("GoogleApiClientConnecting", String.valueOf(localObject).length() + 48 + "Sign-in succeeded with resolve account failure: " + (String)localObject, new Exception());
        zzf(paramzzayb);
        return;
      }
      this.zzazV = true;
      this.zzazW = ((zzaf)localObject).zzxz();
      this.zzazX = ((zzaf)localObject).zzxB();
      this.zzazY = ((zzaf)localObject).zzxC();
      zzvC();
      return;
    }
    if (zze((ConnectionResult)localObject))
    {
      zzvF();
      zzvC();
      return;
    }
    zzf((ConnectionResult)localObject);
  }
  
  private boolean zza(int paramInt1, int paramInt2, ConnectionResult paramConnectionResult)
  {
    if ((paramInt2 == 1) && (!zzd(paramConnectionResult))) {}
    while ((this.zzazA != null) && (paramInt1 >= this.zzazN)) {
      return false;
    }
    return true;
  }
  
  private void zzaq(boolean paramBoolean)
  {
    if (this.zzazS != null)
    {
      if ((this.zzazS.isConnected()) && (paramBoolean)) {
        this.zzazS.zzOe();
      }
      this.zzazS.disconnect();
      this.zzazW = null;
    }
  }
  
  private void zzb(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt)
  {
    if (paramInt != 2)
    {
      int i = paramApi.zzuF().getPriority();
      if (zza(i, paramInt, paramConnectionResult))
      {
        this.zzazA = paramConnectionResult;
        this.zzazN = i;
      }
    }
    this.zzazK.zzaAG.put(paramApi.zzuH(), paramConnectionResult);
  }
  
  private boolean zzcv(int paramInt)
  {
    if (this.zzazO != paramInt)
    {
      Log.w("GoogleApiClientConnecting", this.zzazK.zzazd.zzvN());
      String str1 = String.valueOf(this);
      Log.w("GoogleApiClientConnecting", String.valueOf(str1).length() + 23 + "Unexpected callback in " + str1);
      int i = this.zzazP;
      Log.w("GoogleApiClientConnecting", 33 + "mRemainingConnections=" + i);
      str1 = String.valueOf(zzcw(this.zzazO));
      String str2 = String.valueOf(zzcw(paramInt));
      Log.wtf("GoogleApiClientConnecting", String.valueOf(str1).length() + 70 + String.valueOf(str2).length() + "GoogleApiClient connecting is in step " + str1 + " but received callback for step " + str2, new Exception());
      zzf(new ConnectionResult(8, null));
      return false;
    }
    return true;
  }
  
  private String zzcw(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 0: 
      return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }
    return "STEP_GETTING_REMOTE_SERVICE";
  }
  
  private boolean zzd(ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.hasResolution()) {}
    while (this.zzazw.zzcr(paramConnectionResult.getErrorCode()) != null) {
      return true;
    }
    return false;
  }
  
  private boolean zze(ConnectionResult paramConnectionResult)
  {
    return (this.zzazT == 2) || ((this.zzazT == 1) && (!paramConnectionResult.hasResolution()));
  }
  
  private void zzf(ConnectionResult paramConnectionResult)
  {
    zzvG();
    if (!paramConnectionResult.hasResolution()) {}
    for (boolean bool = true;; bool = false)
    {
      zzaq(bool);
      this.zzazK.zzh(paramConnectionResult);
      this.zzazK.zzaAK.zzc(paramConnectionResult);
      return;
    }
  }
  
  private boolean zzvB()
  {
    this.zzazP -= 1;
    if (this.zzazP > 0) {
      return false;
    }
    if (this.zzazP < 0)
    {
      Log.w("GoogleApiClientConnecting", this.zzazK.zzazd.zzvN());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
      zzf(new ConnectionResult(8, null));
      return false;
    }
    if (this.zzazA != null)
    {
      this.zzazK.zzaAJ = this.zzazN;
      zzf(this.zzazA);
      return false;
    }
    return true;
  }
  
  private void zzvC()
  {
    if (this.zzazP != 0) {}
    while ((this.zzazU) && (!this.zzazV)) {
      return;
    }
    zzvD();
  }
  
  private void zzvD()
  {
    ArrayList localArrayList = new ArrayList();
    this.zzazO = 1;
    this.zzazP = this.zzazK.zzaAr.size();
    Iterator localIterator = this.zzazK.zzaAr.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api.zzc localzzc = (Api.zzc)localIterator.next();
      if (this.zzazK.zzaAG.containsKey(localzzc))
      {
        if (zzvB()) {
          zzvE();
        }
      }
      else {
        localArrayList.add((Api.zze)this.zzazK.zzaAr.get(localzzc));
      }
    }
    if (!localArrayList.isEmpty()) {
      this.zzazZ.add(zzaao.zzvR().submit(new zzc(localArrayList)));
    }
  }
  
  private void zzvE()
  {
    this.zzazK.zzvP();
    zzaao.zzvR().execute(new Runnable()
    {
      public void run()
      {
        zzaaj.zzb(zzaaj.this).zzan(zzaaj.zza(zzaaj.this));
      }
    });
    if (this.zzazS != null)
    {
      if (this.zzazX) {
        this.zzazS.zza(this.zzazW, this.zzazY);
      }
      zzaq(false);
    }
    Object localObject = this.zzazK.zzaAG.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Api.zzc localzzc = (Api.zzc)((Iterator)localObject).next();
      ((Api.zze)this.zzazK.zzaAr.get(localzzc)).disconnect();
    }
    if (this.zzazQ.isEmpty()) {}
    for (localObject = null;; localObject = this.zzazQ)
    {
      this.zzazK.zzaAK.zzo((Bundle)localObject);
      return;
    }
  }
  
  private void zzvF()
  {
    this.zzazU = false;
    this.zzazK.zzazd.zzaAs = Collections.emptySet();
    Iterator localIterator = this.zzazR.iterator();
    while (localIterator.hasNext())
    {
      Api.zzc localzzc = (Api.zzc)localIterator.next();
      if (!this.zzazK.zzaAG.containsKey(localzzc)) {
        this.zzazK.zzaAG.put(localzzc, new ConnectionResult(17, null));
      }
    }
  }
  
  private void zzvG()
  {
    Iterator localIterator = this.zzazZ.iterator();
    while (localIterator.hasNext()) {
      ((Future)localIterator.next()).cancel(true);
    }
    this.zzazZ.clear();
  }
  
  private Set<Scope> zzvH()
  {
    if (this.zzazs == null) {
      return Collections.emptySet();
    }
    HashSet localHashSet = new HashSet(this.zzazs.zzxe());
    Map localMap = this.zzazs.zzxg();
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      if (!this.zzazK.zzaAG.containsKey(localApi.zzuH())) {
        localHashSet.addAll(((zzg.zza)localMap.get(localApi)).zzajm);
      }
    }
    return localHashSet;
  }
  
  public void begin()
  {
    this.zzazK.zzaAG.clear();
    this.zzazU = false;
    this.zzazA = null;
    this.zzazO = 0;
    this.zzazT = 2;
    this.zzazV = false;
    this.zzazX = false;
    HashMap localHashMap = new HashMap();
    Object localObject = this.zzazu.keySet().iterator();
    int i = 0;
    if (((Iterator)localObject).hasNext())
    {
      Api localApi = (Api)((Iterator)localObject).next();
      Api.zze localzze = (Api.zze)this.zzazK.zzaAr.get(localApi.zzuH());
      int k = ((Integer)this.zzazu.get(localApi)).intValue();
      if (localApi.zzuF().getPriority() == 1) {}
      for (int j = 1;; j = 0)
      {
        if (localzze.zzqD())
        {
          this.zzazU = true;
          if (k < this.zzazT) {
            this.zzazT = k;
          }
          if (k != 0) {
            this.zzazR.add(localApi.zzuH());
          }
        }
        localHashMap.put(localzze, new zza(this, localApi, k));
        i = j | i;
        break;
      }
    }
    if (i != 0) {
      this.zzazU = false;
    }
    if (this.zzazU)
    {
      this.zzazs.zzc(Integer.valueOf(this.zzazK.zzazd.getSessionId()));
      localObject = new zze(null);
      this.zzazS = ((zzaxn)this.zzaxY.zza(this.mContext, this.zzazK.zzazd.getLooper(), this.zzazs, this.zzazs.zzxk(), (GoogleApiClient.ConnectionCallbacks)localObject, (GoogleApiClient.OnConnectionFailedListener)localObject));
    }
    this.zzazP = this.zzazK.zzaAr.size();
    this.zzazZ.add(zzaao.zzvR().submit(new zzb(localHashMap)));
  }
  
  public void connect() {}
  
  public boolean disconnect()
  {
    zzvG();
    zzaq(true);
    this.zzazK.zzh(null);
    return true;
  }
  
  public void onConnected(Bundle paramBundle)
  {
    if (!zzcv(1)) {}
    do
    {
      return;
      if (paramBundle != null) {
        this.zzazQ.putAll(paramBundle);
      }
    } while (!zzvB());
    zzvE();
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    zzf(new ConnectionResult(8, null));
  }
  
  public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(T paramT)
  {
    this.zzazK.zzazd.zzaAl.add(paramT);
    return paramT;
  }
  
  public void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt)
  {
    if (!zzcv(1)) {}
    do
    {
      return;
      zzb(paramConnectionResult, paramApi, paramInt);
    } while (!zzvB());
    zzvE();
  }
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(T paramT)
  {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  private static class zza
    implements zzf.zzf
  {
    private final WeakReference<zzaaj> zzaAb;
    private final Api<?> zzawb;
    private final int zzazb;
    
    public zza(zzaaj paramzzaaj, Api<?> paramApi, int paramInt)
    {
      this.zzaAb = new WeakReference(paramzzaaj);
      this.zzawb = paramApi;
      this.zzazb = paramInt;
    }
    
    public void zzg(@NonNull ConnectionResult paramConnectionResult)
    {
      boolean bool = false;
      zzaaj localzzaaj = (zzaaj)this.zzaAb.get();
      if (localzzaaj == null) {
        return;
      }
      if (Looper.myLooper() == zzaaj.zzd(localzzaaj).zzazd.getLooper()) {
        bool = true;
      }
      zzac.zza(bool, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
      zzaaj.zzc(localzzaaj).lock();
      try
      {
        bool = zzaaj.zza(localzzaaj, 0);
        if (!bool) {
          return;
        }
        if (!paramConnectionResult.isSuccess()) {
          zzaaj.zza(localzzaaj, paramConnectionResult, this.zzawb, this.zzazb);
        }
        if (zzaaj.zzk(localzzaaj)) {
          zzaaj.zzj(localzzaaj);
        }
        return;
      }
      finally
      {
        zzaaj.zzc(localzzaaj).unlock();
      }
    }
  }
  
  private class zzb
    extends zzaaj.zzf
  {
    private final Map<Api.zze, zzaaj.zza> zzaAc;
    
    public zzb()
    {
      super(null);
      Map localMap;
      this.zzaAc = localMap;
    }
    
    @WorkerThread
    public void zzvA()
    {
      int n = 1;
      int m = 0;
      final Object localObject = this.zzaAc.keySet().iterator();
      int j = 1;
      int i = 0;
      Api.zze localzze;
      int k;
      if (((Iterator)localObject).hasNext())
      {
        localzze = (Api.zze)((Iterator)localObject).next();
        if (localzze.zzuI())
        {
          if (zzaaj.zza.zza((zzaaj.zza)this.zzaAc.get(localzze)) != 0) {
            break label301;
          }
          i = 1;
          k = n;
        }
      }
      for (;;)
      {
        if (k != 0) {
          m = zzaaj.zzb(zzaaj.this).isGooglePlayServicesAvailable(zzaaj.zza(zzaaj.this));
        }
        if ((m != 0) && ((i != 0) || (j != 0)))
        {
          localObject = new ConnectionResult(m, null);
          zzaaj.zzd(zzaaj.this).zza(new zzaan.zza(zzaaj.this)
          {
            public void zzvA()
            {
              zzaaj.zza(zzaaj.this, localObject);
            }
          });
          label155:
          return;
          k = 0;
          j = i;
          i = k;
        }
        for (;;)
        {
          k = j;
          j = i;
          i = k;
          break;
          if (zzaaj.zze(zzaaj.this)) {
            zzaaj.zzf(zzaaj.this).connect();
          }
          localObject = this.zzaAc.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            localzze = (Api.zze)((Iterator)localObject).next();
            final zzf.zzf localzzf = (zzf.zzf)this.zzaAc.get(localzze);
            if ((localzze.zzuI()) && (m != 0)) {
              zzaaj.zzd(zzaaj.this).zza(new zzaan.zza(zzaaj.this)
              {
                public void zzvA()
                {
                  localzzf.zzg(new ConnectionResult(16, null));
                }
              });
            } else {
              localzze.zza(localzzf);
            }
          }
          break label155;
          label301:
          i = j;
          j = 1;
        }
        k = i;
        i = 0;
      }
    }
  }
  
  private class zzc
    extends zzaaj.zzf
  {
    private final ArrayList<Api.zze> zzaAg;
    
    public zzc()
    {
      super(null);
      ArrayList localArrayList;
      this.zzaAg = localArrayList;
    }
    
    @WorkerThread
    public void zzvA()
    {
      zzaaj.zzd(zzaaj.this).zzazd.zzaAs = zzaaj.zzg(zzaaj.this);
      Iterator localIterator = this.zzaAg.iterator();
      while (localIterator.hasNext()) {
        ((Api.zze)localIterator.next()).zza(zzaaj.zzh(zzaaj.this), zzaaj.zzd(zzaaj.this).zzazd.zzaAs);
      }
    }
  }
  
  private static class zzd
    extends zzaxr
  {
    private final WeakReference<zzaaj> zzaAb;
    
    zzd(zzaaj paramzzaaj)
    {
      this.zzaAb = new WeakReference(paramzzaaj);
    }
    
    @BinderThread
    public void zzb(final zzayb paramzzayb)
    {
      final zzaaj localzzaaj = (zzaaj)this.zzaAb.get();
      if (localzzaaj == null) {
        return;
      }
      zzaaj.zzd(localzzaaj).zza(new zzaan.zza(localzzaaj)
      {
        public void zzvA()
        {
          zzaaj.zza(localzzaaj, paramzzayb);
        }
      });
    }
  }
  
  private class zze
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
  {
    private zze() {}
    
    public void onConnected(Bundle paramBundle)
    {
      zzaaj.zzf(zzaaj.this).zza(new zzaaj.zzd(zzaaj.this));
    }
    
    /* Error */
    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   4: invokestatic 46	com/google/android/gms/internal/zzaaj:zzc	(Lcom/google/android/gms/internal/zzaaj;)Ljava/util/concurrent/locks/Lock;
      //   7: invokeinterface 51 1 0
      //   12: aload_0
      //   13: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   16: aload_1
      //   17: invokestatic 55	com/google/android/gms/internal/zzaaj:zzb	(Lcom/google/android/gms/internal/zzaaj;Lcom/google/android/gms/common/ConnectionResult;)Z
      //   20: ifeq +30 -> 50
      //   23: aload_0
      //   24: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   27: invokestatic 58	com/google/android/gms/internal/zzaaj:zzi	(Lcom/google/android/gms/internal/zzaaj;)V
      //   30: aload_0
      //   31: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   34: invokestatic 61	com/google/android/gms/internal/zzaaj:zzj	(Lcom/google/android/gms/internal/zzaaj;)V
      //   37: aload_0
      //   38: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   41: invokestatic 46	com/google/android/gms/internal/zzaaj:zzc	(Lcom/google/android/gms/internal/zzaaj;)Ljava/util/concurrent/locks/Lock;
      //   44: invokeinterface 64 1 0
      //   49: return
      //   50: aload_0
      //   51: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   54: aload_1
      //   55: invokestatic 67	com/google/android/gms/internal/zzaaj:zza	(Lcom/google/android/gms/internal/zzaaj;Lcom/google/android/gms/common/ConnectionResult;)V
      //   58: goto -21 -> 37
      //   61: astore_1
      //   62: aload_0
      //   63: getfield 17	com/google/android/gms/internal/zzaaj$zze:zzaAa	Lcom/google/android/gms/internal/zzaaj;
      //   66: invokestatic 46	com/google/android/gms/internal/zzaaj:zzc	(Lcom/google/android/gms/internal/zzaaj;)Ljava/util/concurrent/locks/Lock;
      //   69: invokeinterface 64 1 0
      //   74: aload_1
      //   75: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	76	0	this	zze
      //   0	76	1	paramConnectionResult	ConnectionResult
      // Exception table:
      //   from	to	target	type
      //   12	37	61	finally
      //   50	58	61	finally
    }
    
    public void onConnectionSuspended(int paramInt) {}
  }
  
  private abstract class zzf
    implements Runnable
  {
    private zzf() {}
    
    @WorkerThread
    public void run()
    {
      zzaaj.zzc(zzaaj.this).lock();
      try
      {
        boolean bool = Thread.interrupted();
        if (bool) {
          return;
        }
        zzvA();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        zzaaj.zzd(zzaaj.this).zza(localRuntimeException);
        return;
      }
      finally
      {
        zzaaj.zzc(zzaaj.this).unlock();
      }
    }
    
    @WorkerThread
    protected abstract void zzvA();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */