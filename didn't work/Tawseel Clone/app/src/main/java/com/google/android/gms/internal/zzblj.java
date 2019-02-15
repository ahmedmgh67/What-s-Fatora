package com.google.android.gms.internal;

import java.util.Comparator;

public class zzblj<A extends Comparable<A>>
  implements Comparator<A>
{
  private static zzblj zzbZg = new zzblj();
  
  public static <T extends Comparable<T>> zzblj<T> zzh(Class<T> paramClass)
  {
    return zzbZg;
  }
  
  public int zza(A paramA1, A paramA2)
  {
    return paramA1.compareTo(paramA2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */