package com.crashlytics.android.core;

class MiddleOutStrategy
  implements StackTraceTrimmingStrategy
{
  private final int trimmedSize;
  
  public MiddleOutStrategy(int paramInt)
  {
    this.trimmedSize = paramInt;
  }
  
  public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] paramArrayOfStackTraceElement)
  {
    if (paramArrayOfStackTraceElement.length <= this.trimmedSize) {
      return paramArrayOfStackTraceElement;
    }
    int i = this.trimmedSize / 2;
    int j = this.trimmedSize - i;
    StackTraceElement[] arrayOfStackTraceElement = new StackTraceElement[this.trimmedSize];
    System.arraycopy(paramArrayOfStackTraceElement, 0, arrayOfStackTraceElement, 0, j);
    System.arraycopy(paramArrayOfStackTraceElement, paramArrayOfStackTraceElement.length - i, arrayOfStackTraceElement, j, i);
    return arrayOfStackTraceElement;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\MiddleOutStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */