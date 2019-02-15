package com.google.android.gms.internal;

import java.util.Random;

public class zzbqf
{
  private static final int[] zzciA;
  private static final Random zzciy;
  private static long zzciz;
  
  static
  {
    if (!zzbqf.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzciy = new Random();
      zzciz = 0L;
      zzciA = new int[12];
      return;
    }
  }
  
  public static String zzaQ(long paramLong)
  {
    int k = 0;
    for (;;)
    {
      StringBuilder localStringBuilder;
      try
      {
        if (paramLong != zzciz) {
          break label215;
        }
        i = 1;
        zzciz = paramLong;
        char[] arrayOfChar1 = new char[8];
        localStringBuilder = new StringBuilder(20);
        j = 7;
        if (j >= 0)
        {
          arrayOfChar1[j] = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt((int)(paramLong % 64L));
          paramLong /= 64L;
          j -= 1;
          continue;
        }
        if ((!$assertionsDisabled) && (paramLong != 0L)) {
          throw new AssertionError();
        }
      }
      finally {}
      localStringBuilder.append(arrayOfChar2);
      if (i == 0)
      {
        i = 0;
        for (;;)
        {
          j = k;
          if (i >= 12) {
            break;
          }
          zzciA[i] = zzciy.nextInt(64);
          i += 1;
        }
      }
      zzaaa();
      int j = k;
      while (j < 12)
      {
        localStringBuilder.append("-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt(zzciA[j]));
        j += 1;
      }
      assert (localStringBuilder.length() == 20);
      String str = localStringBuilder.toString();
      return str;
      label215:
      int i = 0;
    }
  }
  
  private static void zzaaa()
  {
    int i = 11;
    for (;;)
    {
      if (i >= 0)
      {
        if (zzciA[i] != 63) {
          zzciA[i] += 1;
        }
      }
      else {
        return;
      }
      zzciA[i] = 0;
      i -= 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */