package com.crashlytics.android.core;

class TrimmedThrowableData
{
  public final TrimmedThrowableData cause;
  public final String className;
  public final String localizedMessage;
  public final StackTraceElement[] stacktrace;
  
  public TrimmedThrowableData(Throwable paramThrowable, StackTraceTrimmingStrategy paramStackTraceTrimmingStrategy)
  {
    this.localizedMessage = paramThrowable.getLocalizedMessage();
    this.className = paramThrowable.getClass().getName();
    this.stacktrace = paramStackTraceTrimmingStrategy.getTrimmedStackTrace(paramThrowable.getStackTrace());
    paramThrowable = paramThrowable.getCause();
    if (paramThrowable != null) {}
    for (paramThrowable = new TrimmedThrowableData(paramThrowable, paramStackTraceTrimmingStrategy);; paramThrowable = null)
    {
      this.cause = paramThrowable;
      return;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\TrimmedThrowableData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */