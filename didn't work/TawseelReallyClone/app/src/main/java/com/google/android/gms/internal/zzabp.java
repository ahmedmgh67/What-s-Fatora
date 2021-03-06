package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public class zzabp<R extends Result>
  extends TransformedResult<R>
  implements ResultCallback<R>
{
  private ResultTransform<? super R, ? extends Result> zzaBM = null;
  private zzabp<? extends Result> zzaBN = null;
  private volatile ResultCallbacks<? super R> zzaBO = null;
  private PendingResult<R> zzaBP = null;
  private Status zzaBQ = null;
  private final zza zzaBR;
  private boolean zzaBS = false;
  private final Object zzayO = new Object();
  private final WeakReference<GoogleApiClient> zzayQ;
  
  public zzabp(WeakReference<GoogleApiClient> paramWeakReference)
  {
    zzac.zzb(paramWeakReference, "GoogleApiClient reference must not be null");
    this.zzayQ = paramWeakReference;
    paramWeakReference = (GoogleApiClient)this.zzayQ.get();
    if (paramWeakReference != null) {}
    for (paramWeakReference = paramWeakReference.getLooper();; paramWeakReference = Looper.getMainLooper())
    {
      this.zzaBR = new zza(paramWeakReference);
      return;
    }
  }
  
  private void zzD(Status paramStatus)
  {
    synchronized (this.zzayO)
    {
      this.zzaBQ = paramStatus;
      zzE(this.zzaBQ);
      return;
    }
  }
  
  private void zzE(Status paramStatus)
  {
    synchronized (this.zzayO)
    {
      if (this.zzaBM != null)
      {
        paramStatus = this.zzaBM.onFailure(paramStatus);
        zzac.zzb(paramStatus, "onFailure must not return null");
        this.zzaBN.zzD(paramStatus);
      }
      while (!zzwv()) {
        return;
      }
      this.zzaBO.onFailure(paramStatus);
    }
  }
  
  private void zzd(Result paramResult)
  {
    if ((paramResult instanceof Releasable)) {}
    try
    {
      ((Releasable)paramResult).release();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramResult = String.valueOf(paramResult);
      Log.w("TransformedResultImpl", String.valueOf(paramResult).length() + 18 + "Unable to release " + paramResult, localRuntimeException);
    }
  }
  
  private void zzwt()
  {
    if ((this.zzaBM == null) && (this.zzaBO == null)) {}
    do
    {
      return;
      GoogleApiClient localGoogleApiClient = (GoogleApiClient)this.zzayQ.get();
      if ((!this.zzaBS) && (this.zzaBM != null) && (localGoogleApiClient != null))
      {
        localGoogleApiClient.zza(this);
        this.zzaBS = true;
      }
      if (this.zzaBQ != null)
      {
        zzE(this.zzaBQ);
        return;
      }
    } while (this.zzaBP == null);
    this.zzaBP.setResultCallback(this);
  }
  
  private boolean zzwv()
  {
    GoogleApiClient localGoogleApiClient = (GoogleApiClient)this.zzayQ.get();
    return (this.zzaBO != null) && (localGoogleApiClient != null);
  }
  
  public void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (this.zzayO)
      {
        if (this.zzaBO == null)
        {
          bool1 = true;
          zzac.zza(bool1, "Cannot call andFinally() twice.");
          if (this.zzaBM != null) {
            break label65;
          }
          bool1 = bool2;
          zzac.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          this.zzaBO = paramResultCallbacks;
          zzwt();
          return;
        }
      }
      boolean bool1 = false;
      continue;
      label65:
      bool1 = false;
    }
  }
  
  public void onResult(final R paramR)
  {
    for (;;)
    {
      synchronized (this.zzayO)
      {
        if (paramR.getStatus().isSuccess())
        {
          if (this.zzaBM != null)
          {
            zzabg.zzvR().submit(new Runnable()
            {
              @WorkerThread
              public void run()
              {
                try
                {
                  zzzx.zzayN.set(Boolean.valueOf(true));
                  Object localObject1 = zzabp.zzc(zzabp.this).onSuccess(paramR);
                  zzabp.zzd(zzabp.this).sendMessage(zzabp.zzd(zzabp.this).obtainMessage(0, localObject1));
                  zzzx.zzayN.set(Boolean.valueOf(false));
                  zzabp.zza(zzabp.this, paramR);
                  localObject1 = (GoogleApiClient)zzabp.zze(zzabp.this).get();
                  if (localObject1 != null) {
                    ((GoogleApiClient)localObject1).zzb(zzabp.this);
                  }
                  return;
                }
                catch (RuntimeException localRuntimeException)
                {
                  zzabp.zzd(zzabp.this).sendMessage(zzabp.zzd(zzabp.this).obtainMessage(1, localRuntimeException));
                  GoogleApiClient localGoogleApiClient1;
                  return;
                }
                finally
                {
                  zzzx.zzayN.set(Boolean.valueOf(false));
                  zzabp.zza(zzabp.this, paramR);
                  GoogleApiClient localGoogleApiClient2 = (GoogleApiClient)zzabp.zze(zzabp.this).get();
                  if (localGoogleApiClient2 != null) {
                    localGoogleApiClient2.zzb(zzabp.this);
                  }
                }
              }
            });
            return;
          }
          if (!zzwv()) {
            continue;
          }
          this.zzaBO.onSuccess(paramR);
        }
      }
      zzD(paramR.getStatus());
      zzd(paramR);
    }
  }
  
  @NonNull
  public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (this.zzayO)
      {
        if (this.zzaBM == null)
        {
          bool1 = true;
          zzac.zza(bool1, "Cannot call then() twice.");
          if (this.zzaBO != null) {
            break label83;
          }
          bool1 = bool2;
          zzac.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          this.zzaBM = paramResultTransform;
          paramResultTransform = new zzabp(this.zzayQ);
          this.zzaBN = paramResultTransform;
          zzwt();
          return paramResultTransform;
        }
      }
      boolean bool1 = false;
      continue;
      label83:
      bool1 = false;
    }
  }
  
  public void zza(PendingResult<?> paramPendingResult)
  {
    synchronized (this.zzayO)
    {
      this.zzaBP = paramPendingResult;
      zzwt();
      return;
    }
  }
  
  void zzwu()
  {
    this.zzaBO = null;
  }
  
  private final class zza
    extends Handler
  {
    public zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        int i = paramMessage.what;
        Log.e("TransformedResultImpl", 70 + "TransformationResultHandler received unknown message type: " + i);
        return;
      case 0: 
        PendingResult localPendingResult1 = (PendingResult)paramMessage.obj;
        paramMessage = zzabp.zzf(zzabp.this);
        if (localPendingResult1 == null) {}
        for (;;)
        {
          try
          {
            zzabp.zza(zzabp.zzg(zzabp.this), new Status(13, "Transform returned null"));
            return;
          }
          finally {}
          if ((localPendingResult2 instanceof zzabh)) {
            zzabp.zza(zzabp.zzg(zzabp.this), ((zzabh)localPendingResult2).getStatus());
          } else {
            zzabp.zzg(zzabp.this).zza(localPendingResult2);
          }
        }
      }
      RuntimeException localRuntimeException = (RuntimeException)paramMessage.obj;
      paramMessage = String.valueOf(localRuntimeException.getMessage());
      if (paramMessage.length() != 0) {}
      for (paramMessage = "Runtime exception on the transformation worker thread: ".concat(paramMessage);; paramMessage = new String("Runtime exception on the transformation worker thread: "))
      {
        Log.e("TransformedResultImpl", paramMessage);
        throw localRuntimeException;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzabp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */