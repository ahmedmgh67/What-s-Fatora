package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaae;
import com.google.android.gms.internal.zzaap;
import com.google.android.gms.internal.zzaap.zza;
import com.google.android.gms.internal.zzaaq;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzaaz.zzb;
import com.google.android.gms.internal.zzaba;
import com.google.android.gms.internal.zzabe;
import com.google.android.gms.internal.zzabj;
import com.google.android.gms.internal.zzabk;
import com.google.android.gms.internal.zzabn;
import com.google.android.gms.internal.zzabr;
import com.google.android.gms.internal.zzzr;
import com.google.android.gms.internal.zzzs;
import com.google.android.gms.internal.zzzv.zza;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzc<O extends Api.ApiOptions>
{
  private final Context mContext;
  private final int mId;
  private final Api<O> zzawb;
  private final O zzaxG;
  private final zzzs<O> zzaxH;
  private final GoogleApiClient zzaxI;
  private final zzabk zzaxJ;
  protected final zzaap zzaxK;
  private final Looper zzrx;
  
  @MainThread
  public zzc(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, Looper paramLooper, zzabk paramzzabk)
  {
    zzac.zzb(paramActivity, "Null activity is not permitted.");
    zzac.zzb(paramApi, "Api must not be null.");
    zzac.zzb(paramLooper, "Looper must not be null.");
    this.mContext = paramActivity.getApplicationContext();
    this.zzawb = paramApi;
    this.zzaxG = paramO;
    this.zzrx = paramLooper;
    this.zzaxH = zzzs.zza(this.zzawb, this.zzaxG);
    this.zzaxI = new zzaaq(this);
    this.zzaxK = zzaap.zzax(this.mContext);
    this.mId = this.zzaxK.zzvU();
    this.zzaxJ = paramzzabk;
    zzaae.zza(paramActivity, this.zzaxK, this.zzaxH);
    this.zzaxK.zza(this);
  }
  
  public zzc(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zzabk paramzzabk)
  {
    this(paramActivity, paramApi, paramO, paramActivity.getMainLooper(), paramzzabk);
  }
  
  protected zzc(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper)
  {
    zzac.zzb(paramContext, "Null context is not permitted.");
    zzac.zzb(paramApi, "Api must not be null.");
    zzac.zzb(paramLooper, "Looper must not be null.");
    this.mContext = paramContext.getApplicationContext();
    this.zzawb = paramApi;
    this.zzaxG = null;
    this.zzrx = paramLooper;
    this.zzaxH = zzzs.zzb(paramApi);
    this.zzaxI = new zzaaq(this);
    this.zzaxK = zzaap.zzax(this.mContext);
    this.mId = this.zzaxK.zzvU();
    this.zzaxJ = new zzzr();
  }
  
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO, Looper paramLooper, zzabk paramzzabk)
  {
    zzac.zzb(paramContext, "Null context is not permitted.");
    zzac.zzb(paramApi, "Api must not be null.");
    zzac.zzb(paramLooper, "Looper must not be null.");
    this.mContext = paramContext.getApplicationContext();
    this.zzawb = paramApi;
    this.zzaxG = paramO;
    this.zzrx = paramLooper;
    this.zzaxH = zzzs.zza(this.zzawb, this.zzaxG);
    this.zzaxI = new zzaaq(this);
    this.zzaxK = zzaap.zzax(this.mContext);
    this.mId = this.zzaxK.zzvU();
    this.zzaxJ = paramzzabk;
    this.zzaxK.zza(this);
  }
  
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO, zzabk paramzzabk) {}
  
  private <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zza(int paramInt, @NonNull T paramT)
  {
    paramT.zzvf();
    this.zzaxK.zza(this, paramInt, paramT);
    return paramT;
  }
  
  private <TResult, A extends Api.zzb> Task<TResult> zza(int paramInt, @NonNull zzabn<A, TResult> paramzzabn)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    this.zzaxK.zza(this, paramInt, paramzzabn, localTaskCompletionSource, this.zzaxJ);
    return localTaskCompletionSource.getTask();
  }
  
  public GoogleApiClient asGoogleApiClient()
  {
    return this.zzaxI;
  }
  
  @WorkerThread
  public Api.zze buildApiClient(Looper paramLooper, zzaap.zza<O> paramzza)
  {
    return this.zzawb.zzuG().zza(this.mContext, paramLooper, zzg.zzaA(this.mContext), this.zzaxG, paramzza, paramzza);
  }
  
  public zzabj createSignInCoordinator(Context paramContext, Handler paramHandler)
  {
    return new zzabj(paramContext, paramHandler);
  }
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T doBestEffortWrite(@NonNull T paramT)
  {
    return zza(2, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> doBestEffortWrite(zzabn<A, TResult> paramzzabn)
  {
    return zza(2, paramzzabn);
  }
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T doRead(@NonNull T paramT)
  {
    return zza(0, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> doRead(zzabn<A, TResult> paramzzabn)
  {
    return zza(0, paramzzabn);
  }
  
  public <A extends Api.zzb, T extends zzabe<A, ?>, U extends zzabr<A, ?>> Task<Void> doRegisterEventListener(@NonNull T paramT, U paramU)
  {
    zzac.zzw(paramT);
    zzac.zzw(paramU);
    zzac.zzb(paramT.zzwp(), "Listener has already been released.");
    zzac.zzb(paramU.zzwp(), "Listener has already been released.");
    zzac.zzb(paramT.zzwp().equals(paramU.zzwp()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
    return this.zzaxK.zza(this, paramT, paramU);
  }
  
  public Task<Void> doUnregisterEventListener(@NonNull zzaaz.zzb<?> paramzzb)
  {
    zzac.zzb(paramzzb, "Listener key cannot be null.");
    return this.zzaxK.zza(this, paramzzb);
  }
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T doWrite(@NonNull T paramT)
  {
    return zza(1, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> doWrite(zzabn<A, TResult> paramzzabn)
  {
    return zza(1, paramzzabn);
  }
  
  public Api<O> getApi()
  {
    return this.zzawb;
  }
  
  public zzzs<O> getApiKey()
  {
    return this.zzaxH;
  }
  
  public O getApiOptions()
  {
    return this.zzaxG;
  }
  
  public Context getApplicationContext()
  {
    return this.mContext;
  }
  
  public int getInstanceId()
  {
    return this.mId;
  }
  
  public Looper getLooper()
  {
    return this.zzrx;
  }
  
  public <L> zzaaz<L> registerListener(@NonNull L paramL, String paramString)
  {
    return zzaba.zzb(paramL, this.zzrx, paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\api\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */