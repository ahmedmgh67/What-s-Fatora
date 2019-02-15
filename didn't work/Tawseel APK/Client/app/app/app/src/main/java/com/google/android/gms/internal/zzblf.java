package com.google.android.gms.internal;

import java.util.Comparator;

public abstract interface zzblf<K, V>
{
  public abstract K getKey();
  
  public abstract V getValue();
  
  public abstract boolean isEmpty();
  
  public abstract boolean zzVq();
  
  public abstract zzblf<K, V> zzVs();
  
  public abstract zzblf<K, V> zzVt();
  
  public abstract zzblf<K, V> zzVu();
  
  public abstract zzblf<K, V> zzVv();
  
  public abstract int zzVw();
  
  public abstract zzblf<K, V> zza(K paramK, V paramV, zza paramzza, zzblf<K, V> paramzzblf1, zzblf<K, V> paramzzblf2);
  
  public abstract zzblf<K, V> zza(K paramK, V paramV, Comparator<K> paramComparator);
  
  public abstract zzblf<K, V> zza(K paramK, Comparator<K> paramComparator);
  
  public abstract void zza(zzb<K, V> paramzzb);
  
  public static enum zza
  {
    private zza() {}
  }
  
  public static abstract class zzb<K, V>
  {
    public abstract void zzk(K paramK, V paramV);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */