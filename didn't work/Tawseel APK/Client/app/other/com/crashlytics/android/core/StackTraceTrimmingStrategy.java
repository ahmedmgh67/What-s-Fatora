package com.crashlytics.android.core;

abstract interface StackTraceTrimmingStrategy
{
  public abstract StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] paramArrayOfStackTraceElement);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\StackTraceTrimmingStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */