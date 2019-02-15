package com.crashlytics.android.core;

import java.util.HashMap;
import java.util.Map;

class RemoveRepeatsStrategy
  implements StackTraceTrimmingStrategy
{
  private final int maxRepetitions;
  
  public RemoveRepeatsStrategy()
  {
    this(1);
  }
  
  public RemoveRepeatsStrategy(int paramInt)
  {
    this.maxRepetitions = paramInt;
  }
  
  private static boolean isRepeatingSequence(StackTraceElement[] paramArrayOfStackTraceElement, int paramInt1, int paramInt2)
  {
    int j = paramInt2 - paramInt1;
    if (paramInt2 + j > paramArrayOfStackTraceElement.length) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label47;
      }
      if (!paramArrayOfStackTraceElement[(paramInt1 + i)].equals(paramArrayOfStackTraceElement[(paramInt2 + i)])) {
        break;
      }
      i += 1;
    }
    label47:
    return true;
  }
  
  private static StackTraceElement[] trimRepeats(StackTraceElement[] paramArrayOfStackTraceElement, int paramInt)
  {
    HashMap localHashMap = new HashMap();
    StackTraceElement[] arrayOfStackTraceElement = new StackTraceElement[paramArrayOfStackTraceElement.length];
    int m = 0;
    int k = 1;
    int i = 0;
    if (i < paramArrayOfStackTraceElement.length)
    {
      StackTraceElement localStackTraceElement = paramArrayOfStackTraceElement[i];
      Integer localInteger = (Integer)localHashMap.get(localStackTraceElement);
      int j;
      if ((localInteger == null) || (!isRepeatingSequence(paramArrayOfStackTraceElement, localInteger.intValue(), i)))
      {
        k = 1;
        arrayOfStackTraceElement[m] = paramArrayOfStackTraceElement[i];
        j = m + 1;
        m = i;
      }
      for (;;)
      {
        localHashMap.put(localStackTraceElement, Integer.valueOf(i));
        i = m + 1;
        m = j;
        break;
        int i1 = i - localInteger.intValue();
        int n = k;
        j = m;
        if (k < paramInt)
        {
          System.arraycopy(paramArrayOfStackTraceElement, i, arrayOfStackTraceElement, m, i1);
          j = m + i1;
          n = k + 1;
        }
        m = i + (i1 - 1);
        k = n;
      }
    }
    paramArrayOfStackTraceElement = new StackTraceElement[m];
    System.arraycopy(arrayOfStackTraceElement, 0, paramArrayOfStackTraceElement, 0, paramArrayOfStackTraceElement.length);
    return paramArrayOfStackTraceElement;
  }
  
  public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] paramArrayOfStackTraceElement)
  {
    StackTraceElement[] arrayOfStackTraceElement = trimRepeats(paramArrayOfStackTraceElement, this.maxRepetitions);
    if (arrayOfStackTraceElement.length < paramArrayOfStackTraceElement.length) {
      return arrayOfStackTraceElement;
    }
    return paramArrayOfStackTraceElement;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\RemoveRepeatsStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */