package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.concurrent.atomic.AtomicBoolean;

class CrashlyticsUncaughtExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  private final CrashListener crashListener;
  private final Thread.UncaughtExceptionHandler defaultHandler;
  private final AtomicBoolean isHandlingException;
  
  public CrashlyticsUncaughtExceptionHandler(CrashListener paramCrashListener, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    this.crashListener = paramCrashListener;
    this.defaultHandler = paramUncaughtExceptionHandler;
    this.isHandlingException = new AtomicBoolean(false);
  }
  
  boolean isHandlingException()
  {
    return this.isHandlingException.get();
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    this.isHandlingException.set(true);
    try
    {
      this.crashListener.onUncaughtException(paramThread, paramThrowable);
      return;
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the uncaught exception handler", localException);
      return;
    }
    finally
    {
      Fabric.getLogger().d("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
      this.defaultHandler.uncaughtException(paramThread, paramThrowable);
      this.isHandlingException.set(false);
    }
  }
  
  static abstract interface CrashListener
  {
    public abstract void onUncaughtException(Thread paramThread, Throwable paramThrowable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\CrashlyticsUncaughtExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */