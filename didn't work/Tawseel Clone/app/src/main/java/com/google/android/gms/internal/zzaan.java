package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zzc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzaan
  implements zzaau, zzzz
{
  private final Context mContext;
  private final Condition zzaAE;
  private final zzb zzaAF;
  final Map<Api.zzc<?>, ConnectionResult> zzaAG = new HashMap();
  private volatile zzaam zzaAH;
  private ConnectionResult zzaAI = null;
  int zzaAJ;
  final zzaau.zza zzaAK;
  final Map<Api.zzc<?>, Api.zze> zzaAr;
  final Api.zza<? extends zzaxn, zzaxo> zzaxY;
  final zzaal zzazd;
  private final Lock zzazn;
  final zzg zzazs;
  final Map<Api<?>, Integer> zzazu;
  private final zzc zzazw;
  
  public zzaan(Context paramContext, zzaal paramzzaal, Lock paramLock, Looper paramLooper, zzc paramzzc, Map<Api.zzc<?>, Api.zze> paramMap, zzg paramzzg, Map<Api<?>, Integer> paramMap1, Api.zza<? extends zzaxn, zzaxo> paramzza, ArrayList<zzzy> paramArrayList, zzaau.zza paramzza1)
  {
    this.mContext = paramContext;
    this.zzazn = paramLock;
    this.zzazw = paramzzc;
    this.zzaAr = paramMap;
    this.zzazs = paramzzg;
    this.zzazu = paramMap1;
    this.zzaxY = paramzza;
    this.zzazd = paramzzaal;
    this.zzaAK = paramzza1;
    paramContext = paramArrayList.iterator();
    while (paramContext.hasNext()) {
      ((zzzy)paramContext.next()).zza(this);
    }
    this.zzaAF = new zzb(paramLooper);
    this.zzaAE = paramLock.newCondition();
    this.zzaAH = new zzaak(this);
  }
  
  public ConnectionResult blockingConnect()
  {
    connect();
    while (isConnecting()) {
      try
      {
        this.zzaAE.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      }
    }
    if (isConnected()) {
      return ConnectionResult.zzawX;
    }
    if (this.zzaAI != null) {
      return this.zzaAI;
    }
    return new ConnectionResult(13, null);
  }
  
  public ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong); isConnecting(); paramLong = this.zzaAE.awaitNanos(paramLong))
    {
      if (paramLong <= 0L) {}
      try
      {
        disconnect();
        return new ConnectionResult(14, null);
      }
      catch (InterruptedException paramTimeUnit)
      {
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
      }
    }
    if (isConnected()) {
      return ConnectionResult.zzawX;
    }
    if (this.zzaAI != null) {
      return this.zzaAI;
    }
    return new ConnectionResult(13, null);
  }
  
  public void connect()
  {
    this.zzaAH.connect();
  }
  
  public void disconnect()
  {
    if (this.zzaAH.disconnect()) {
      this.zzaAG.clear();
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    String str = String.valueOf(paramString).concat("  ");
    paramPrintWriter.append(paramString).append("mState=").println(this.zzaAH);
    Iterator localIterator = this.zzazu.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      paramPrintWriter.append(paramString).append(localApi.getName()).println(":");
      ((Api.zze)this.zzaAr.get(localApi.zzuH())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  @Nullable
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi)
  {
    paramApi = paramApi.zzuH();
    if (this.zzaAr.containsKey(paramApi))
    {
      if (((Api.zze)this.zzaAr.get(paramApi)).isConnected()) {
        return ConnectionResult.zzawX;
      }
      if (this.zzaAG.containsKey(paramApi)) {
        return (ConnectionResult)this.zzaAG.get(paramApi);
      }
    }
    return null;
  }
  
  public boolean isConnected()
  {
    return this.zzaAH instanceof zzaai;
  }
  
  public boolean isConnecting()
  {
    return this.zzaAH instanceof zzaaj;
  }
  
  public void onConnected(@Nullable Bundle paramBundle)
  {
    this.zzazn.lock();
    try
    {
      this.zzaAH.onConnected(paramBundle);
      return;
    }
    finally
    {
      this.zzazn.unlock();
    }
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    this.zzazn.lock();
    try
    {
      this.zzaAH.onConnectionSuspended(paramInt);
      return;
    }
    finally
    {
      this.zzazn.unlock();
    }
  }
  
  public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T paramT)
  {
    paramT.zzvf();
    return this.zzaAH.zza(paramT);
  }
  
  public void zza(@NonNull ConnectionResult paramConnectionResult, @NonNull Api<?> paramApi, int paramInt)
  {
    this.zzazn.lock();
    try
    {
      this.zzaAH.zza(paramConnectionResult, paramApi, paramInt);
      return;
    }
    finally
    {
      this.zzazn.unlock();
    }
  }
  
  void zza(zza paramzza)
  {
    paramzza = this.zzaAF.obtainMessage(1, paramzza);
    this.zzaAF.sendMessage(paramzza);
  }
  
  void zza(RuntimeException paramRuntimeException)
  {
    paramRuntimeException = this.zzaAF.obtainMessage(2, paramRuntimeException);
    this.zzaAF.sendMessage(paramRuntimeException);
  }
  
  public boolean zza(zzabi paramzzabi)
  {
    return false;
  }
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    paramT.zzvf();
    return this.zzaAH.zzb(paramT);
  }
  
  void zzh(ConnectionResult paramConnectionResult)
  {
    this.zzazn.lock();
    try
    {
      this.zzaAI = paramConnectionResult;
      this.zzaAH = new zzaak(this);
      this.zzaAH.begin();
      this.zzaAE.signalAll();
      return;
    }
    finally
    {
      this.zzazn.unlock();
    }
  }
  
  public void zzuN() {}
  
  void zzvO()
  {
    this.zzazn.lock();
    try
    {
      this.zzaAH = new zzaaj(this, this.zzazs, this.zzazu, this.zzazw, this.zzaxY, this.zzazn, this.mContext);
      this.zzaAH.begin();
      this.zzaAE.signalAll();
      return;
    }
    finally
    {
      this.zzazn.unlock();
    }
  }
  
  void zzvP()
  {
    this.zzazn.lock();
    try
    {
      this.zzazd.zzvL();
      this.zzaAH = new zzaai(this);
      this.zzaAH.begin();
      this.zzaAE.signalAll();
      return;
    }
    finally
    {
      this.zzazn.unlock();
    }
  }
  
  void zzvQ()
  {
    Iterator localIterator = this.zzaAr.values().iterator();
    while (localIterator.hasNext()) {
      ((Api.zze)localIterator.next()).disconnect();
    }
  }
  
  public void zzvj()
  {
    if (isConnected()) {
      ((zzaai)this.zzaAH).zzvz();
    }
  }
  
  static abstract class zza
  {
    private final zzaam zzaAL;
    
    protected zza(zzaam paramzzaam)
    {
      this.zzaAL = paramzzaam;
    }
    
    public final void zzc(zzaan paramzzaan)
    {
      zzaan.zza(paramzzaan).lock();
      try
      {
        zzaam localzzaam1 = zzaan.zzb(paramzzaan);
        zzaam localzzaam2 = this.zzaAL;
        if (localzzaam1 != localzzaam2) {
          return;
        }
        zzvA();
        return;
      }
      finally
      {
        zzaan.zza(paramzzaan).unlock();
      }
    }
    
    protected abstract void zzvA();
  }
  
  final class zzb
    extends Handler
  {
    zzb(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        int i = paramMessage.what;
        Log.w("GACStateManager", 31 + "Unknown message id: " + i);
        return;
      case 1: 
        ((zzaan.zza)paramMessage.obj).zzc(zzaan.this);
        return;
      }
      throw ((RuntimeException)paramMessage.obj);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */