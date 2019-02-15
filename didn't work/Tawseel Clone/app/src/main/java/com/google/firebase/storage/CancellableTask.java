package com.google.firebase.storage;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;

public abstract class CancellableTask<TState>
  extends Task<TState>
{
  public abstract CancellableTask<TState> addOnProgressListener(@NonNull Activity paramActivity, @NonNull OnProgressListener<? super TState> paramOnProgressListener);
  
  public abstract CancellableTask<TState> addOnProgressListener(@NonNull OnProgressListener<? super TState> paramOnProgressListener);
  
  public abstract CancellableTask<TState> addOnProgressListener(@NonNull Executor paramExecutor, @NonNull OnProgressListener<? super TState> paramOnProgressListener);
  
  public abstract boolean cancel();
  
  public abstract boolean isCanceled();
  
  public abstract boolean isInProgress();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\CancellableTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */