package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public abstract class zzbqa
  implements zzbmo
{
  private ScheduledThreadPoolExecutor zzcit = new ScheduledThreadPoolExecutor(1, new zza(null))
  {
    protected void afterExecute(Runnable paramAnonymousRunnable, Throwable paramAnonymousThrowable)
    {
      super.afterExecute(paramAnonymousRunnable, paramAnonymousThrowable);
      localThrowable = paramAnonymousThrowable;
      if (paramAnonymousThrowable == null)
      {
        localThrowable = paramAnonymousThrowable;
        if ((paramAnonymousRunnable instanceof Future))
        {
          paramAnonymousRunnable = (Future)paramAnonymousRunnable;
          localThrowable = paramAnonymousThrowable;
        }
      }
      try
      {
        if (paramAnonymousRunnable.isDone())
        {
          paramAnonymousRunnable.get();
          localThrowable = paramAnonymousThrowable;
        }
      }
      catch (ExecutionException paramAnonymousRunnable)
      {
        for (;;)
        {
          localThrowable = paramAnonymousRunnable.getCause();
        }
      }
      catch (InterruptedException paramAnonymousRunnable)
      {
        for (;;)
        {
          Thread.currentThread().interrupt();
          localThrowable = paramAnonymousThrowable;
        }
      }
      catch (CancellationException paramAnonymousRunnable)
      {
        for (;;)
        {
          localThrowable = paramAnonymousThrowable;
        }
      }
      if (localThrowable != null) {
        zzbqa.this.zzj(localThrowable);
      }
    }
  };
  
  public zzbqa()
  {
    this.zzcit.setKeepAliveTime(3L, TimeUnit.SECONDS);
  }
  
  public static String zzl(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof OutOfMemoryError)) {
      return "Firebase Database encountered an OutOfMemoryError. You may need to reduce the amount of data you are syncing to the client (e.g. by using queries or syncing a deeper path). See https://firebase.google.com/docs/database/ios/structure-data#best_practices_for_data_structure and https://firebase.google.com/docs/database/android/retrieve-data#filtering_data";
    }
    if ((paramThrowable instanceof DatabaseException)) {
      return "";
    }
    paramThrowable = String.valueOf(FirebaseDatabase.getSdkVersion());
    return String.valueOf(paramThrowable).length() + 104 + "Uncaught exception in Firebase Database runloop (" + paramThrowable + "). Please report to firebase-database-client@google.com";
  }
  
  protected ThreadFactory getThreadFactory()
  {
    return Executors.defaultThreadFactory();
  }
  
  public void restart()
  {
    this.zzcit.setCorePoolSize(1);
  }
  
  public void shutdown()
  {
    this.zzcit.setCorePoolSize(0);
  }
  
  public ScheduledExecutorService zzVJ()
  {
    return this.zzcit;
  }
  
  protected zzbmv zzXe()
  {
    return zzbmv.zzced;
  }
  
  public abstract void zzj(Throwable paramThrowable);
  
  public void zzs(Runnable paramRunnable)
  {
    this.zzcit.execute(paramRunnable);
  }
  
  private class zza
    implements ThreadFactory
  {
    private zza() {}
    
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = zzbqa.this.getThreadFactory().newThread(paramRunnable);
      zzbmv localzzbmv = zzbqa.this.zzXe();
      localzzbmv.zza(paramRunnable, "FirebaseDatabaseWorker");
      localzzbmv.zza(paramRunnable, true);
      localzzbmv.zza(paramRunnable, new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          zzbqa.this.zzj(paramAnonymousThrowable);
        }
      });
      return paramRunnable;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */