package com.google.firebase.storage;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executor;

public abstract class StorageTask<TResult extends ProvideError>
  extends ControllableTask<TResult>
{
  private static final HashMap<Integer, HashSet<Integer>> zzckU = new HashMap();
  private static final HashMap<Integer, HashSet<Integer>> zzckV = new HashMap();
  protected final Object mSyncObject = new Object();
  private volatile int zzMf = 1;
  @VisibleForTesting
  final zze<OnSuccessListener<? super TResult>, TResult> zzckW = new zze(this, 128, new zze.zza()
  {
    public void zza(@NonNull OnSuccessListener<? super TResult> paramAnonymousOnSuccessListener, @NonNull TResult paramAnonymousTResult)
    {
      zzc.zzaaV().zzc(StorageTask.this);
      paramAnonymousOnSuccessListener.onSuccess(paramAnonymousTResult);
    }
  });
  @VisibleForTesting
  final zze<OnFailureListener, TResult> zzckX = new zze(this, 320, new zze.zza()
  {
    public void zza(@NonNull OnFailureListener paramAnonymousOnFailureListener, @NonNull TResult paramAnonymousTResult)
    {
      zzc.zzaaV().zzc(StorageTask.this);
      paramAnonymousOnFailureListener.onFailure(paramAnonymousTResult.getError());
    }
  });
  @VisibleForTesting
  final zze<OnCompleteListener<TResult>, TResult> zzckY = new zze(this, 448, new zze.zza()
  {
    public void zza(@NonNull OnCompleteListener<TResult> paramAnonymousOnCompleteListener, @NonNull TResult paramAnonymousTResult)
    {
      zzc.zzaaV().zzc(StorageTask.this);
      paramAnonymousOnCompleteListener.onComplete(StorageTask.this);
    }
  });
  @VisibleForTesting
  final zze<OnProgressListener<? super TResult>, TResult> zzckZ = new zze(this, 65071, new zze.zza()
  {
    public void zza(@NonNull OnProgressListener<? super TResult> paramAnonymousOnProgressListener, @NonNull TResult paramAnonymousTResult)
    {
      paramAnonymousOnProgressListener.onProgress(paramAnonymousTResult);
    }
  });
  @VisibleForTesting
  final zze<OnPausedListener<? super TResult>, TResult> zzcla = new zze(this, 16, new zze.zza()
  {
    public void zza(@NonNull OnPausedListener<? super TResult> paramAnonymousOnPausedListener, @NonNull TResult paramAnonymousTResult)
    {
      paramAnonymousOnPausedListener.onPaused(paramAnonymousTResult);
    }
  });
  private TResult zzclb;
  
  static
  {
    zzckU.put(Integer.valueOf(1), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(16), Integer.valueOf(256) })));
    zzckU.put(Integer.valueOf(2), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(8), Integer.valueOf(32) })));
    zzckU.put(Integer.valueOf(4), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(8), Integer.valueOf(32) })));
    zzckU.put(Integer.valueOf(16), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(2), Integer.valueOf(256) })));
    zzckU.put(Integer.valueOf(64), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(2), Integer.valueOf(256) })));
    zzckV.put(Integer.valueOf(1), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(2), Integer.valueOf(64) })));
    zzckV.put(Integer.valueOf(2), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(4), Integer.valueOf(64), Integer.valueOf(128) })));
    zzckV.put(Integer.valueOf(4), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(4), Integer.valueOf(64), Integer.valueOf(128) })));
    zzckV.put(Integer.valueOf(8), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(16), Integer.valueOf(64), Integer.valueOf(128) })));
    zzckV.put(Integer.valueOf(32), new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(256), Integer.valueOf(64), Integer.valueOf(128) })));
  }
  
  @NonNull
  private <TContinuationResult> Task<TContinuationResult> zza(@Nullable Executor paramExecutor, @NonNull final Continuation<TResult, TContinuationResult> paramContinuation)
  {
    final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    this.zzckY.zza(null, paramExecutor, new OnCompleteListener()
    {
      public void onComplete(@NonNull Task<TResult> paramAnonymousTask)
      {
        try
        {
          paramAnonymousTask = paramContinuation.then(StorageTask.this);
          if (!localTaskCompletionSource.getTask().isComplete()) {
            localTaskCompletionSource.setResult(paramAnonymousTask);
          }
          return;
        }
        catch (RuntimeExecutionException paramAnonymousTask)
        {
          if ((paramAnonymousTask.getCause() instanceof Exception))
          {
            localTaskCompletionSource.setException((Exception)paramAnonymousTask.getCause());
            return;
          }
          localTaskCompletionSource.setException(paramAnonymousTask);
          return;
        }
        catch (Exception paramAnonymousTask)
        {
          localTaskCompletionSource.setException(paramAnonymousTask);
        }
      }
    });
    return localTaskCompletionSource.getTask();
  }
  
  private TResult zzaaT()
  {
    if (this.zzclb != null) {
      return this.zzclb;
    }
    if (!isComplete()) {
      return null;
    }
    if (this.zzclb == null) {
      this.zzclb = zzaaS();
    }
    return this.zzclb;
  }
  
  private void zzaaU()
  {
    if ((!isComplete()) && (!isPaused()) && (zzaaQ() != 2) && (!zzf(256, false))) {
      zzf(64, false);
    }
  }
  
  @NonNull
  private <TContinuationResult> Task<TContinuationResult> zzb(@Nullable Executor paramExecutor, @NonNull final Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    this.zzckY.zza(null, paramExecutor, new OnCompleteListener()
    {
      public void onComplete(@NonNull Task<TResult> paramAnonymousTask)
      {
        try
        {
          paramAnonymousTask = (Task)paramContinuation.then(StorageTask.this);
          if (!localTaskCompletionSource.getTask().isComplete())
          {
            if (paramAnonymousTask == null) {
              localTaskCompletionSource.setException(new NullPointerException("Continuation returned null"));
            }
          }
          else {
            return;
          }
        }
        catch (RuntimeExecutionException paramAnonymousTask)
        {
          if ((paramAnonymousTask.getCause() instanceof Exception))
          {
            localTaskCompletionSource.setException((Exception)paramAnonymousTask.getCause());
            return;
          }
          localTaskCompletionSource.setException(paramAnonymousTask);
          return;
        }
        catch (Exception paramAnonymousTask)
        {
          localTaskCompletionSource.setException(paramAnonymousTask);
          return;
        }
        paramAnonymousTask.addOnSuccessListener(new OnSuccessListener()
        {
          public void onSuccess(TContinuationResult paramAnonymous2TContinuationResult)
          {
            StorageTask.7.this.zzaFb.setResult(paramAnonymous2TContinuationResult);
          }
        });
        paramAnonymousTask.addOnFailureListener(new OnFailureListener()
        {
          public void onFailure(@NonNull Exception paramAnonymous2Exception)
          {
            StorageTask.7.this.zzaFb.setException(paramAnonymous2Exception);
          }
        });
      }
    });
    return localTaskCompletionSource.getTask();
  }
  
  private String zzpZ(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "Unknown Internal State!";
    case 1: 
      return "INTERNAL_STATE_NOT_STARTED";
    case 2: 
      return "INTERNAL_STATE_QUEUED";
    case 4: 
      return "INTERNAL_STATE_IN_PROGRESS";
    case 8: 
      return "INTERNAL_STATE_PAUSING";
    case 16: 
      return "INTERNAL_STATE_PAUSED";
    case 32: 
      return "INTERNAL_STATE_CANCELING";
    case 64: 
      return "INTERNAL_STATE_FAILURE";
    case 128: 
      return "INTERNAL_STATE_SUCCESS";
    }
    return "INTERNAL_STATE_CANCELED";
  }
  
  @NonNull
  public StorageTask<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    zzac.zzw(paramOnCompleteListener);
    zzac.zzw(paramActivity);
    this.zzckY.zza(paramActivity, null, paramOnCompleteListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    zzac.zzw(paramOnCompleteListener);
    this.zzckY.zza(null, null, paramOnCompleteListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    zzac.zzw(paramOnCompleteListener);
    zzac.zzw(paramExecutor);
    this.zzckY.zza(null, paramExecutor, paramOnCompleteListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener)
  {
    zzac.zzw(paramOnFailureListener);
    zzac.zzw(paramActivity);
    this.zzckX.zza(paramActivity, null, paramOnFailureListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener)
  {
    zzac.zzw(paramOnFailureListener);
    this.zzckX.zza(null, null, paramOnFailureListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    zzac.zzw(paramOnFailureListener);
    zzac.zzw(paramExecutor);
    this.zzckX.zza(null, paramExecutor, paramOnFailureListener);
    return this;
  }
  
  public StorageTask<TResult> addOnPausedListener(@NonNull Activity paramActivity, @NonNull OnPausedListener<? super TResult> paramOnPausedListener)
  {
    zzac.zzw(paramOnPausedListener);
    zzac.zzw(paramActivity);
    this.zzcla.zza(paramActivity, null, paramOnPausedListener);
    return this;
  }
  
  public StorageTask<TResult> addOnPausedListener(@NonNull OnPausedListener<? super TResult> paramOnPausedListener)
  {
    zzac.zzw(paramOnPausedListener);
    this.zzcla.zza(null, null, paramOnPausedListener);
    return this;
  }
  
  public StorageTask<TResult> addOnPausedListener(@NonNull Executor paramExecutor, @NonNull OnPausedListener<? super TResult> paramOnPausedListener)
  {
    zzac.zzw(paramOnPausedListener);
    zzac.zzw(paramExecutor);
    this.zzcla.zza(null, paramExecutor, paramOnPausedListener);
    return this;
  }
  
  public StorageTask<TResult> addOnProgressListener(@NonNull Activity paramActivity, @NonNull OnProgressListener<? super TResult> paramOnProgressListener)
  {
    zzac.zzw(paramOnProgressListener);
    zzac.zzw(paramActivity);
    this.zzckZ.zza(paramActivity, null, paramOnProgressListener);
    return this;
  }
  
  public StorageTask<TResult> addOnProgressListener(@NonNull OnProgressListener<? super TResult> paramOnProgressListener)
  {
    zzac.zzw(paramOnProgressListener);
    this.zzckZ.zza(null, null, paramOnProgressListener);
    return this;
  }
  
  public StorageTask<TResult> addOnProgressListener(@NonNull Executor paramExecutor, @NonNull OnProgressListener<? super TResult> paramOnProgressListener)
  {
    zzac.zzw(paramOnProgressListener);
    zzac.zzw(paramExecutor);
    this.zzckZ.zza(null, paramExecutor, paramOnProgressListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    zzac.zzw(paramActivity);
    zzac.zzw(paramOnSuccessListener);
    this.zzckW.zza(paramActivity, null, paramOnSuccessListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    zzac.zzw(paramOnSuccessListener);
    this.zzckW.zza(null, null, paramOnSuccessListener);
    return this;
  }
  
  @NonNull
  public StorageTask<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    zzac.zzw(paramExecutor);
    zzac.zzw(paramOnSuccessListener);
    this.zzckW.zza(null, paramExecutor, paramOnSuccessListener);
    return this;
  }
  
  public boolean cancel()
  {
    return (zzf(256, true)) || (zzf(32, true));
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return zza(null, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return zza(paramExecutor, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return zzb(null, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return zzb(paramExecutor, paramContinuation);
  }
  
  @Nullable
  public Exception getException()
  {
    if (zzaaT() == null) {
      return null;
    }
    return zzaaT().getError();
  }
  
  public TResult getResult()
  {
    if (zzaaT() == null) {
      throw new IllegalStateException();
    }
    Exception localException = zzaaT().getError();
    if (localException != null) {
      throw new RuntimeExecutionException(localException);
    }
    return zzaaT();
  }
  
  public <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass)
    throws Throwable
  {
    if (zzaaT() == null) {
      throw new IllegalStateException();
    }
    if (paramClass.isInstance(zzaaT().getError())) {
      throw ((Throwable)paramClass.cast(zzaaT().getError()));
    }
    paramClass = zzaaT().getError();
    if (paramClass != null) {
      throw new RuntimeExecutionException(paramClass);
    }
    return zzaaT();
  }
  
  public TResult getSnapshot()
  {
    return zzaaS();
  }
  
  @VisibleForTesting
  abstract StorageReference getStorage();
  
  public boolean isCanceled()
  {
    return zzaaQ() == 256;
  }
  
  public boolean isComplete()
  {
    return ((zzaaQ() & 0x80) != 0) || ((zzaaQ() & 0x140) != 0);
  }
  
  public boolean isInProgress()
  {
    return (zzaaQ() & 0xFE2F) != 0;
  }
  
  public boolean isPaused()
  {
    return (zzaaQ() & 0x10) != 0;
  }
  
  public boolean isSuccessful()
  {
    return (zzaaQ() & 0x80) != 0;
  }
  
  protected void onCanceled() {}
  
  protected void onFailure() {}
  
  protected void onPaused() {}
  
  protected void onProgress() {}
  
  protected void onQueued() {}
  
  protected void onSuccess() {}
  
  public boolean pause()
  {
    return (zzf(16, true)) || (zzf(8, true));
  }
  
  public StorageTask<TResult> removeOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    zzac.zzw(paramOnCompleteListener);
    this.zzckY.zzaG(paramOnCompleteListener);
    return this;
  }
  
  public StorageTask<TResult> removeOnFailureListener(@NonNull OnFailureListener paramOnFailureListener)
  {
    zzac.zzw(paramOnFailureListener);
    this.zzckX.zzaG(paramOnFailureListener);
    return this;
  }
  
  public StorageTask<TResult> removeOnPausedListener(@NonNull OnPausedListener<? super TResult> paramOnPausedListener)
  {
    zzac.zzw(paramOnPausedListener);
    this.zzcla.zzaG(paramOnPausedListener);
    return this;
  }
  
  public StorageTask<TResult> removeOnProgressListener(@NonNull OnProgressListener<? super TResult> paramOnProgressListener)
  {
    zzac.zzw(paramOnProgressListener);
    this.zzckZ.zzaG(paramOnProgressListener);
    return this;
  }
  
  public StorageTask<TResult> removeOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    zzac.zzw(paramOnSuccessListener);
    this.zzckW.zzaG(paramOnSuccessListener);
    return this;
  }
  
  @VisibleForTesting
  void resetState() {}
  
  public boolean resume()
  {
    if (zzf(2, true))
    {
      resetState();
      schedule();
      return true;
    }
    return false;
  }
  
  @VisibleForTesting
  abstract void run();
  
  @VisibleForTesting
  abstract void schedule();
  
  @VisibleForTesting
  Runnable zzTj()
  {
    new Runnable()
    {
      public void run()
      {
        try
        {
          StorageTask.this.run();
          return;
        }
        finally
        {
          StorageTask.zza(StorageTask.this);
        }
      }
    };
  }
  
  @NonNull
  @VisibleForTesting
  abstract TResult zzaaK();
  
  @VisibleForTesting
  boolean zzaaP()
  {
    boolean bool = false;
    if (zzf(2, false))
    {
      schedule();
      bool = true;
    }
    return bool;
  }
  
  @VisibleForTesting
  int zzaaQ()
  {
    return this.zzMf;
  }
  
  @VisibleForTesting
  Object zzaaR()
  {
    return this.mSyncObject;
  }
  
  @NonNull
  @VisibleForTesting
  TResult zzaaS()
  {
    synchronized (this.mSyncObject)
    {
      ProvideError localProvideError = zzaaK();
      return localProvideError;
    }
  }
  
  @VisibleForTesting
  boolean zzf(int paramInt, boolean paramBoolean)
  {
    for (;;)
    {
      synchronized (this.mSyncObject)
      {
        Object localObject1;
        if (Log.isLoggable("StorageTask", 3))
        {
          localObject1 = String.valueOf(zzpZ(paramInt));
          str2 = String.valueOf(zzpZ(this.zzMf));
          Log.d("StorageTask", String.valueOf(localObject1).length() + 54 + String.valueOf(str2).length() + "changing internal state to: " + (String)localObject1 + " isUser: " + paramBoolean + " from state:" + str2);
        }
        if (paramBoolean)
        {
          localObject1 = zzckU;
          localObject1 = (HashSet)((HashMap)localObject1).get(Integer.valueOf(zzaaQ()));
          if ((localObject1 == null) || (!((HashSet)localObject1).contains(Integer.valueOf(paramInt)))) {
            break label314;
          }
          this.zzMf = paramInt;
        }
        switch (this.zzMf)
        {
        case 2: 
          this.zzckW.zzaaZ();
          this.zzckX.zzaaZ();
          this.zzckY.zzaaZ();
          this.zzcla.zzaaZ();
          this.zzckZ.zzaaZ();
          return true;
          localObject1 = zzckV;
          continue;
          zzc.zzaaV().zzb(this);
          onQueued();
        }
      }
      onProgress();
      continue;
      onPaused();
      continue;
      onFailure();
      continue;
      onSuccess();
      continue;
      onCanceled();
      continue;
      label314:
      String str1 = String.valueOf(zzpZ(paramInt));
      String str2 = String.valueOf(zzpZ(this.zzMf));
      Log.w("StorageTask", String.valueOf(str1).length() + 62 + String.valueOf(str2).length() + "unable to change internal state to: " + str1 + " isUser: " + paramBoolean + " from state:" + str2);
      return false;
    }
  }
  
  protected static abstract interface ProvideError
  {
    public abstract Exception getError();
  }
  
  @VisibleForTesting
  class SnapshotBase
    implements StorageTask.ProvideError
  {
    private final Exception zzclf;
    
    public SnapshotBase(Exception paramException)
    {
      if (paramException == null)
      {
        if (StorageTask.this.isCanceled())
        {
          this.zzclf = StorageException.fromErrorStatus(Status.zzayl);
          return;
        }
        if (StorageTask.this.zzaaQ() == 64)
        {
          this.zzclf = StorageException.fromErrorStatus(Status.zzayj);
          return;
        }
        this.zzclf = null;
        return;
      }
      this.zzclf = paramException;
    }
    
    @Nullable
    public Exception getError()
    {
      return this.zzclf;
    }
    
    @NonNull
    public StorageReference getStorage()
    {
      return getTask().getStorage();
    }
    
    @NonNull
    public StorageTask<TResult> getTask()
    {
      return StorageTask.this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\StorageTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */