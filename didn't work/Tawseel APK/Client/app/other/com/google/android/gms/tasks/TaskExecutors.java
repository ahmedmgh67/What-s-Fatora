package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors
{
  public static final Executor MAIN_THREAD = new zza();
  static final Executor zzbLG = new Executor()
  {
    public void execute(@NonNull Runnable paramAnonymousRunnable)
    {
      paramAnonymousRunnable.run();
    }
  };
  
  private static final class zza
    implements Executor
  {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    
    public void execute(@NonNull Runnable paramRunnable)
    {
      this.mHandler.post(paramRunnable);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\tasks\TaskExecutors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */