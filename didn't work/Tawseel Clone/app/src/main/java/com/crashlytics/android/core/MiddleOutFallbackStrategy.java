package com.crashlytics.android.core;

class MiddleOutFallbackStrategy
  implements StackTraceTrimmingStrategy
{
  private final int maximumStackSize;
  private final MiddleOutStrategy middleOutStrategy;
  private final StackTraceTrimmingStrategy[] trimmingStrategies;
  
  public MiddleOutFallbackStrategy(int paramInt, StackTraceTrimmingStrategy... paramVarArgs)
  {
    this.maximumStackSize = paramInt;
    this.trimmingStrategies = paramVarArgs;
    this.middleOutStrategy = new MiddleOutStrategy(paramInt);
  }
  
  public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] paramArrayOfStackTraceElement)
  {
    if (paramArrayOfStackTraceElement.length <= this.maximumStackSize) {
      return paramArrayOfStackTraceElement;
    }
    StackTraceElement[] arrayOfStackTraceElement = paramArrayOfStackTraceElement;
    StackTraceTrimmingStrategy[] arrayOfStackTraceTrimmingStrategy = this.trimmingStrategies;
    int j = arrayOfStackTraceTrimmingStrategy.length;
    int i = 0;
    for (;;)
    {
      StackTraceTrimmingStrategy localStackTraceTrimmingStrategy;
      if (i < j)
      {
        localStackTraceTrimmingStrategy = arrayOfStackTraceTrimmingStrategy[i];
        if (arrayOfStackTraceElement.length > this.maximumStackSize) {}
      }
      else
      {
        paramArrayOfStackTraceElement = arrayOfStackTraceElement;
        if (arrayOfStackTraceElement.length > this.maximumStackSize) {
          paramArrayOfStackTraceElement = this.middleOutStrategy.getTrimmedStackTrace(arrayOfStackTraceElement);
        }
        return paramArrayOfStackTraceElement;
      }
      arrayOfStackTraceElement = localStackTraceTrimmingStrategy.getTrimmedStackTrace(paramArrayOfStackTraceElement);
      i += 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\MiddleOutFallbackStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */