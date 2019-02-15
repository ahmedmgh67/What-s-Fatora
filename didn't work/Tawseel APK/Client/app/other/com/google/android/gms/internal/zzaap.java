package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzf.zzf;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class zzaap
  implements Handler.Callback
{
  public static final Status zzaAO = new Status(4, "Sign-out occurred while this API call was in progress.");
  private static final Status zzaAP = new Status(4, "The user must be signed in to make this API call.");
  private static zzaap zzaAR;
  private static final Object zztU = new Object();
  private final Context mContext;
  private final Handler mHandler;
  private long zzaAQ = 10000L;
  private int zzaAS = -1;
  private final AtomicInteger zzaAT = new AtomicInteger(1);
  private final AtomicInteger zzaAU = new AtomicInteger(0);
  private zzaae zzaAV = null;
  private final Set<zzzs<?>> zzaAW = new zza();
  private final Set<zzzs<?>> zzaAX = new zza();
  private long zzaAn = 120000L;
  private long zzaAo = 5000L;
  private final GoogleApiAvailability zzaxX;
  private final Map<zzzs<?>, zza<?>> zzazt = new ConcurrentHashMap(5, 0.75F, 1);
  
  private zzaap(Context paramContext, Looper paramLooper, GoogleApiAvailability paramGoogleApiAvailability)
  {
    this.mContext = paramContext;
    this.mHandler = new Handler(paramLooper, this);
    this.zzaxX = paramGoogleApiAvailability;
  }
  
  @WorkerThread
  private void zza(int paramInt, ConnectionResult paramConnectionResult)
  {
    Object localObject = this.zzazt.values().iterator();
    zza localzza;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      localzza = (zza)((Iterator)localObject).next();
    } while (localzza.getInstanceId() != paramInt);
    for (;;)
    {
      if (localzza != null)
      {
        localObject = String.valueOf(this.zzaxX.getErrorString(paramConnectionResult.getErrorCode()));
        paramConnectionResult = String.valueOf(paramConnectionResult.getErrorMessage());
        localzza.zzC(new Status(17, String.valueOf(localObject).length() + 69 + String.valueOf(paramConnectionResult).length() + "Error resolution was canceled by the user, original error message: " + (String)localObject + ": " + paramConnectionResult));
        return;
      }
      Log.wtf("GoogleApiManager", 76 + "Could not find API instance " + paramInt + " while trying to fail enqueued calls.", new Exception());
      return;
      localzza = null;
    }
  }
  
  @WorkerThread
  private void zza(zzabd paramzzabd)
  {
    zza localzza2 = (zza)this.zzazt.get(paramzzabd.zzaBF.getApiKey());
    zza localzza1 = localzza2;
    if (localzza2 == null)
    {
      zzb(paramzzabd.zzaBF);
      localzza1 = (zza)this.zzazt.get(paramzzabd.zzaBF.getApiKey());
    }
    if ((localzza1.zzqD()) && (this.zzaAU.get() != paramzzabd.zzaBE))
    {
      paramzzabd.zzaBD.zzy(zzaAO);
      localzza1.signOut();
      return;
    }
    localzza1.zza(paramzzabd.zzaBD);
  }
  
  @WorkerThread
  private void zza(zzzu paramzzzu)
  {
    Iterator localIterator = paramzzzu.zzuY().iterator();
    for (;;)
    {
      zzzs localzzzs;
      zza localzza;
      if (localIterator.hasNext())
      {
        localzzzs = (zzzs)localIterator.next();
        localzza = (zza)this.zzazt.get(localzzzs);
        if (localzza == null) {
          paramzzzu.zza(localzzzs, new ConnectionResult(13));
        }
      }
      else
      {
        return;
      }
      if (localzza.isConnected()) {
        paramzzzu.zza(localzzzs, ConnectionResult.zzawX);
      } else if (localzza.zzwe() != null) {
        paramzzzu.zza(localzzzs, localzza.zzwe());
      } else {
        localzza.zzb(paramzzzu);
      }
    }
  }
  
  public static zzaap zzax(Context paramContext)
  {
    synchronized (zztU)
    {
      if (zzaAR == null)
      {
        Looper localLooper = zzvT();
        zzaAR = new zzaap(paramContext.getApplicationContext(), localLooper, GoogleApiAvailability.getInstance());
      }
      paramContext = zzaAR;
      return paramContext;
    }
  }
  
  @WorkerThread
  private void zzb(zzc<?> paramzzc)
  {
    zzzs localzzzs = paramzzc.getApiKey();
    if (!this.zzazt.containsKey(localzzzs)) {
      this.zzazt.put(localzzzs, new zza(paramzzc));
    }
    paramzzc = (zza)this.zzazt.get(localzzzs);
    if (paramzzc.zzqD()) {
      this.zzaAX.add(localzzzs);
    }
    paramzzc.connect();
  }
  
  public static zzaap zzvS()
  {
    synchronized (zztU)
    {
      zzac.zzb(zzaAR, "Must guarantee manager is non-null before using getInstance");
      zzaap localzzaap = zzaAR;
      return localzzaap;
    }
  }
  
  private static Looper zzvT()
  {
    HandlerThread localHandlerThread = new HandlerThread("GoogleApiHandler", 9);
    localHandlerThread.start();
    return localHandlerThread.getLooper();
  }
  
  @WorkerThread
  private void zzvV()
  {
    Iterator localIterator = this.zzazt.values().iterator();
    while (localIterator.hasNext())
    {
      zza localzza = (zza)localIterator.next();
      localzza.zzwd();
      localzza.connect();
    }
  }
  
  @WorkerThread
  private void zzvW()
  {
    Iterator localIterator = this.zzaAX.iterator();
    while (localIterator.hasNext())
    {
      zzzs localzzzs = (zzzs)localIterator.next();
      ((zza)this.zzazt.remove(localzzzs)).signOut();
    }
    this.zzaAX.clear();
  }
  
  @WorkerThread
  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      int i = paramMessage.what;
      Log.w("GoogleApiManager", 31 + "Unknown message id: " + i);
      return false;
    case 1: 
      zza((zzzu)paramMessage.obj);
    }
    for (;;)
    {
      return true;
      zzb((zzc)paramMessage.obj);
      continue;
      zzvV();
      continue;
      zza((zzabd)paramMessage.obj);
      continue;
      zza(paramMessage.arg1, (ConnectionResult)paramMessage.obj);
      continue;
      if (this.zzazt.containsKey(paramMessage.obj))
      {
        ((zza)this.zzazt.get(paramMessage.obj)).resume();
        continue;
        zzvW();
        continue;
        if (this.zzazt.containsKey(paramMessage.obj))
        {
          ((zza)this.zzazt.get(paramMessage.obj)).zzvJ();
          continue;
          if (this.zzazt.containsKey(paramMessage.obj)) {
            ((zza)this.zzazt.get(paramMessage.obj)).zzwh();
          }
        }
      }
    }
  }
  
  public <O extends Api.ApiOptions> Task<Void> zza(@NonNull zzc<O> paramzzc, @NonNull zzaaz.zzb<?> paramzzb)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramzzb = new zzzq.zze(paramzzb, localTaskCompletionSource);
    this.mHandler.sendMessage(this.mHandler.obtainMessage(11, new zzabd(paramzzb, this.zzaAU.get(), paramzzc)));
    return localTaskCompletionSource.getTask();
  }
  
  public <O extends Api.ApiOptions> Task<Void> zza(@NonNull zzc<O> paramzzc, @NonNull zzabe<Api.zzb, ?> paramzzabe, @NonNull zzabr<Api.zzb, ?> paramzzabr)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramzzabe = new zzzq.zzc(new zzabf(paramzzabe, paramzzabr), localTaskCompletionSource);
    this.mHandler.sendMessage(this.mHandler.obtainMessage(6, new zzabd(paramzzabe, this.zzaAU.get(), paramzzc)));
    return localTaskCompletionSource.getTask();
  }
  
  public Task<Void> zza(Iterable<zzc<?>> paramIterable)
  {
    zzzu localzzzu = new zzzu(paramIterable);
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Object localObject = (zzc)paramIterable.next();
      localObject = (zza)this.zzazt.get(((zzc)localObject).getApiKey());
      if ((localObject == null) || (!((zza)localObject).isConnected()))
      {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, localzzzu));
        return localzzzu.getTask();
      }
    }
    localzzzu.zzuZ();
    return localzzzu.getTask();
  }
  
  public void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    if (!zzc(paramConnectionResult, paramInt)) {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(4, paramInt, 0, paramConnectionResult));
    }
  }
  
  public void zza(zzc<?> paramzzc)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(5, paramzzc));
  }
  
  public <O extends Api.ApiOptions, TResult> void zza(zzc<O> paramzzc, int paramInt, zzabn<Api.zzb, TResult> paramzzabn, TaskCompletionSource<TResult> paramTaskCompletionSource, zzabk paramzzabk)
  {
    paramzzabn = new zzzq.zzd(paramInt, paramzzabn, paramTaskCompletionSource, paramzzabk);
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzabd(paramzzabn, this.zzaAU.get(), paramzzc)));
  }
  
  public <O extends Api.ApiOptions> void zza(zzc<O> paramzzc, int paramInt, zzzv.zza<? extends Result, Api.zzb> paramzza)
  {
    paramzza = new zzzq.zzb(paramInt, paramzza);
    this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzabd(paramzza, this.zzaAU.get(), paramzzc)));
  }
  
  public void zza(@NonNull zzaae paramzzaae)
  {
    synchronized (zztU)
    {
      if (this.zzaAV != paramzzaae)
      {
        this.zzaAV = paramzzaae;
        this.zzaAW.clear();
        this.zzaAW.addAll(paramzzaae.zzvx());
      }
      return;
    }
  }
  
  void zzb(@NonNull zzaae paramzzaae)
  {
    synchronized (zztU)
    {
      if (this.zzaAV == paramzzaae)
      {
        this.zzaAV = null;
        this.zzaAW.clear();
      }
      return;
    }
  }
  
  boolean zzc(ConnectionResult paramConnectionResult, int paramInt)
  {
    if ((paramConnectionResult.hasResolution()) || (this.zzaxX.isUserResolvableError(paramConnectionResult.getErrorCode())))
    {
      this.zzaxX.zza(this.mContext, paramConnectionResult, paramInt);
      return true;
    }
    return false;
  }
  
  public void zzuW()
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
  }
  
  public int zzvU()
  {
    return this.zzaAT.getAndIncrement();
  }
  
  public class zza<O extends Api.ApiOptions>
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzzz
  {
    private final Queue<zzzq> zzaAY = new LinkedList();
    private final Api.zzb zzaAZ;
    private boolean zzaAm;
    private final zzaad zzaBa;
    private final Set<zzzu> zzaBb = new HashSet();
    private final Map<zzaaz.zzb<?>, zzabf> zzaBc = new HashMap();
    private final int zzaBd;
    private final zzabj zzaBe;
    private ConnectionResult zzaBf = null;
    private final zzzs<O> zzaxH;
    private final Api.zze zzazq;
    
    @WorkerThread
    public zza()
    {
      Object localObject;
      this.zzazq = ((zzc)localObject).buildApiClient(zzaap.zza(zzaap.this).getLooper(), this);
      if ((this.zzazq instanceof zzal)) {}
      for (this.zzaAZ = ((zzal)this.zzazq).zzxG();; this.zzaAZ = this.zzazq)
      {
        this.zzaxH = ((zzc)localObject).getApiKey();
        this.zzaBa = new zzaad();
        this.zzaBd = ((zzc)localObject).getInstanceId();
        if (!this.zzazq.zzqD()) {
          break;
        }
        this.zzaBe = ((zzc)localObject).createSignInCoordinator(zzaap.zzb(zzaap.this), zzaap.zza(zzaap.this));
        return;
      }
      this.zzaBe = null;
    }
    
    @WorkerThread
    private void zzb(zzzq paramzzzq)
    {
      paramzzzq.zza(this.zzaBa, zzqD());
      try
      {
        paramzzzq.zza(this);
        return;
      }
      catch (DeadObjectException paramzzzq)
      {
        onConnectionSuspended(1);
        this.zzazq.disconnect();
      }
    }
    
    @WorkerThread
    private void zzj(ConnectionResult paramConnectionResult)
    {
      Iterator localIterator = this.zzaBb.iterator();
      while (localIterator.hasNext()) {
        ((zzzu)localIterator.next()).zza(this.zzaxH, paramConnectionResult);
      }
      this.zzaBb.clear();
    }
    
    @WorkerThread
    private void zzvZ()
    {
      zzwd();
      zzj(ConnectionResult.zzawX);
      zzwf();
      Iterator localIterator = this.zzaBc.values().iterator();
      for (;;)
      {
        if (localIterator.hasNext()) {
          localIterator.next();
        }
        try
        {
          new TaskCompletionSource();
        }
        catch (DeadObjectException localDeadObjectException)
        {
          onConnectionSuspended(1);
          this.zzazq.disconnect();
          zzwb();
          zzwg();
          return;
        }
        catch (RemoteException localRemoteException) {}
      }
    }
    
    @WorkerThread
    private void zzwa()
    {
      zzwd();
      this.zzaAm = true;
      this.zzaBa.zzvw();
      zzaap.zza(zzaap.this).sendMessageDelayed(Message.obtain(zzaap.zza(zzaap.this), 7, this.zzaxH), zzaap.zzc(zzaap.this));
      zzaap.zza(zzaap.this).sendMessageDelayed(Message.obtain(zzaap.zza(zzaap.this), 9, this.zzaxH), zzaap.zzd(zzaap.this));
      zzaap.zza(zzaap.this, -1);
    }
    
    @WorkerThread
    private void zzwb()
    {
      while ((this.zzazq.isConnected()) && (!this.zzaAY.isEmpty())) {
        zzb((zzzq)this.zzaAY.remove());
      }
    }
    
    @WorkerThread
    private void zzwf()
    {
      if (this.zzaAm)
      {
        zzaap.zza(zzaap.this).removeMessages(9, this.zzaxH);
        zzaap.zza(zzaap.this).removeMessages(7, this.zzaxH);
        this.zzaAm = false;
      }
    }
    
    private void zzwg()
    {
      zzaap.zza(zzaap.this).removeMessages(10, this.zzaxH);
      zzaap.zza(zzaap.this).sendMessageDelayed(zzaap.zza(zzaap.this).obtainMessage(10, this.zzaxH), zzaap.zzh(zzaap.this));
    }
    
    @WorkerThread
    public void connect()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      if ((this.zzazq.isConnected()) || (this.zzazq.isConnecting())) {
        return;
      }
      if ((this.zzazq.zzuI()) && (zzaap.zzi(zzaap.this) != 0))
      {
        zzaap.zza(zzaap.this, zzaap.zzg(zzaap.this).isGooglePlayServicesAvailable(zzaap.zzb(zzaap.this)));
        if (zzaap.zzi(zzaap.this) != 0)
        {
          onConnectionFailed(new ConnectionResult(zzaap.zzi(zzaap.this), null));
          return;
        }
      }
      zzaap.zzb localzzb = new zzaap.zzb(zzaap.this, this.zzazq, this.zzaxH);
      if (this.zzazq.zzqD()) {
        this.zzaBe.zza(localzzb);
      }
      this.zzazq.zza(localzzb);
    }
    
    public int getInstanceId()
    {
      return this.zzaBd;
    }
    
    boolean isConnected()
    {
      return this.zzazq.isConnected();
    }
    
    public void onConnected(@Nullable Bundle paramBundle)
    {
      if (Looper.myLooper() == zzaap.zza(zzaap.this).getLooper())
      {
        zzvZ();
        return;
      }
      zzaap.zza(zzaap.this).post(new Runnable()
      {
        public void run()
        {
          zzaap.zza.zzc(zzaap.zza.this);
        }
      });
    }
    
    @WorkerThread
    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      zzac.zza(zzaap.zza(zzaap.this));
      if (this.zzaBe != null) {
        this.zzaBe.zzwr();
      }
      zzwd();
      zzaap.zza(zzaap.this, -1);
      zzj(paramConnectionResult);
      if (paramConnectionResult.getErrorCode() == 4) {
        zzC(zzaap.zzvX());
      }
      do
      {
        return;
        if (this.zzaAY.isEmpty())
        {
          this.zzaBf = paramConnectionResult;
          return;
        }
        synchronized (zzaap.zzvY())
        {
          if ((zzaap.zze(zzaap.this) != null) && (zzaap.zzf(zzaap.this).contains(this.zzaxH)))
          {
            zzaap.zze(zzaap.this).zzb(paramConnectionResult, this.zzaBd);
            return;
          }
        }
      } while (zzaap.this.zzc(paramConnectionResult, this.zzaBd));
      if (paramConnectionResult.getErrorCode() == 18) {
        this.zzaAm = true;
      }
      if (this.zzaAm)
      {
        zzaap.zza(zzaap.this).sendMessageDelayed(Message.obtain(zzaap.zza(zzaap.this), 7, this.zzaxH), zzaap.zzc(zzaap.this));
        return;
      }
      paramConnectionResult = String.valueOf(this.zzaxH.zzuV());
      zzC(new Status(17, String.valueOf(paramConnectionResult).length() + 38 + "API: " + paramConnectionResult + " is not available on this device."));
    }
    
    public void onConnectionSuspended(int paramInt)
    {
      if (Looper.myLooper() == zzaap.zza(zzaap.this).getLooper())
      {
        zzwa();
        return;
      }
      zzaap.zza(zzaap.this).post(new Runnable()
      {
        public void run()
        {
          zzaap.zza.zzd(zzaap.zza.this);
        }
      });
    }
    
    @WorkerThread
    public void resume()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      if (this.zzaAm) {
        connect();
      }
    }
    
    @WorkerThread
    public void signOut()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      zzC(zzaap.zzaAO);
      this.zzaBa.zzvv();
      Iterator localIterator = this.zzaBc.keySet().iterator();
      while (localIterator.hasNext()) {
        zza(new zzzq.zze((zzaaz.zzb)localIterator.next(), new TaskCompletionSource()));
      }
      this.zzazq.disconnect();
    }
    
    @WorkerThread
    public void zzC(Status paramStatus)
    {
      zzac.zza(zzaap.zza(zzaap.this));
      Iterator localIterator = this.zzaAY.iterator();
      while (localIterator.hasNext()) {
        ((zzzq)localIterator.next()).zzy(paramStatus);
      }
      this.zzaAY.clear();
    }
    
    public void zza(final ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt)
    {
      if (Looper.myLooper() == zzaap.zza(zzaap.this).getLooper())
      {
        onConnectionFailed(paramConnectionResult);
        return;
      }
      zzaap.zza(zzaap.this).post(new Runnable()
      {
        public void run()
        {
          zzaap.zza.this.onConnectionFailed(paramConnectionResult);
        }
      });
    }
    
    @WorkerThread
    public void zza(zzzq paramzzzq)
    {
      zzac.zza(zzaap.zza(zzaap.this));
      if (this.zzazq.isConnected())
      {
        zzb(paramzzzq);
        zzwg();
        return;
      }
      this.zzaAY.add(paramzzzq);
      if ((this.zzaBf != null) && (this.zzaBf.hasResolution()))
      {
        onConnectionFailed(this.zzaBf);
        return;
      }
      connect();
    }
    
    @WorkerThread
    public void zzb(zzzu paramzzzu)
    {
      zzac.zza(zzaap.zza(zzaap.this));
      this.zzaBb.add(paramzzzu);
    }
    
    @WorkerThread
    public void zzi(@NonNull ConnectionResult paramConnectionResult)
    {
      zzac.zza(zzaap.zza(zzaap.this));
      this.zzazq.disconnect();
      onConnectionFailed(paramConnectionResult);
    }
    
    public boolean zzqD()
    {
      return this.zzazq.zzqD();
    }
    
    @WorkerThread
    public void zzvJ()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      if (this.zzaAm)
      {
        zzwf();
        if (zzaap.zzg(zzaap.this).isGooglePlayServicesAvailable(zzaap.zzb(zzaap.this)) != 18) {
          break label71;
        }
      }
      label71:
      for (Status localStatus = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");; localStatus = new Status(8, "API failed to connect while resuming due to an unknown error."))
      {
        zzC(localStatus);
        this.zzazq.disconnect();
        return;
      }
    }
    
    public Api.zze zzvr()
    {
      return this.zzazq;
    }
    
    public Map<zzaaz.zzb<?>, zzabf> zzwc()
    {
      return this.zzaBc;
    }
    
    @WorkerThread
    public void zzwd()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      this.zzaBf = null;
    }
    
    @WorkerThread
    public ConnectionResult zzwe()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      return this.zzaBf;
    }
    
    @WorkerThread
    public void zzwh()
    {
      zzac.zza(zzaap.zza(zzaap.this));
      if ((this.zzazq.isConnected()) && (this.zzaBc.size() == 0))
      {
        if (this.zzaBa.zzvu()) {
          zzwg();
        }
      }
      else {
        return;
      }
      this.zzazq.disconnect();
    }
  }
  
  private class zzb
    implements zzf.zzf, zzabj.zza
  {
    private boolean zzaBj = false;
    private Set<Scope> zzajm = null;
    private final zzzs<?> zzaxH;
    private zzr zzazW = null;
    private final Api.zze zzazq;
    
    public zzb(zzzs<?> paramzzzs)
    {
      this.zzazq = paramzzzs;
      zzzs localzzzs;
      this.zzaxH = localzzzs;
    }
    
    @WorkerThread
    private void zzwi()
    {
      if ((this.zzaBj) && (this.zzazW != null)) {
        this.zzazq.zza(this.zzazW, this.zzajm);
      }
    }
    
    @WorkerThread
    public void zzb(zzr paramzzr, Set<Scope> paramSet)
    {
      if ((paramzzr == null) || (paramSet == null))
      {
        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
        zzi(new ConnectionResult(4));
        return;
      }
      this.zzazW = paramzzr;
      this.zzajm = paramSet;
      zzwi();
    }
    
    public void zzg(@NonNull final ConnectionResult paramConnectionResult)
    {
      zzaap.zza(zzaap.this).post(new Runnable()
      {
        public void run()
        {
          if (paramConnectionResult.isSuccess())
          {
            zzaap.zzb.zza(zzaap.zzb.this, true);
            if (zzaap.zzb.zza(zzaap.zzb.this).zzqD())
            {
              zzaap.zzb.zzb(zzaap.zzb.this);
              return;
            }
            zzaap.zzb.zza(zzaap.zzb.this).zza(null, Collections.emptySet());
            return;
          }
          ((zzaap.zza)zzaap.zzj(zzaap.this).get(zzaap.zzb.zzc(zzaap.zzb.this))).onConnectionFailed(paramConnectionResult);
        }
      });
    }
    
    @WorkerThread
    public void zzi(ConnectionResult paramConnectionResult)
    {
      ((zzaap.zza)zzaap.zzj(zzaap.this).get(this.zzaxH)).zzi(paramConnectionResult);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */