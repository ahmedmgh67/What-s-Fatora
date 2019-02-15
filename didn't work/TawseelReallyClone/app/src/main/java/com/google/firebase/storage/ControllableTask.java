package com.google.firebase.storage;

import android.app.Activity;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public abstract class ControllableTask<TState>
  extends CancellableTask<TState>
{
  public abstract ControllableTask<TState> addOnPausedListener(@NonNull Activity paramActivity, @NonNull OnPausedListener<? super TState> paramOnPausedListener);
  
  public abstract ControllableTask<TState> addOnPausedListener(@NonNull OnPausedListener<? super TState> paramOnPausedListener);
  
  public abstract ControllableTask<TState> addOnPausedListener(@NonNull Executor paramExecutor, @NonNull OnPausedListener<? super TState> paramOnPausedListener);
  
  public abstract boolean isPaused();
  
  public abstract boolean pause();
  
  public abstract boolean resume();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\ControllableTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */