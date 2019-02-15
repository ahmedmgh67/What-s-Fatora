package com.google.android.gms.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class zzbla<K, V>
  implements Iterable<Map.Entry<K, V>>
{
  public abstract boolean containsKey(K paramK);
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzbla)) {
      return false;
    }
    Object localObject = (zzbla)paramObject;
    if (!getComparator().equals(((zzbla)localObject).getComparator())) {
      return false;
    }
    if (size() != ((zzbla)localObject).size()) {
      return false;
    }
    paramObject = iterator();
    localObject = ((zzbla)localObject).iterator();
    while (((Iterator)paramObject).hasNext()) {
      if (!((Map.Entry)((Iterator)paramObject).next()).equals(((Iterator)localObject).next())) {
        return false;
      }
    }
    return true;
  }
  
  public abstract V get(K paramK);
  
  public abstract Comparator<K> getComparator();
  
  public int hashCode()
  {
    int i = getComparator().hashCode();
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      i = ((Map.Entry)localIterator.next()).hashCode() + i * 31;
    }
    return i;
  }
  
  public abstract boolean isEmpty();
  
  public abstract Iterator<Map.Entry<K, V>> iterator();
  
  public abstract int size();
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append("{");
    Iterator localIterator = iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (i != 0) {
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append("(");
        localStringBuilder.append(localEntry.getKey());
        localStringBuilder.append("=>");
        localStringBuilder.append(localEntry.getValue());
        localStringBuilder.append(")");
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("};");
    return localStringBuilder.toString();
  }
  
  public abstract K zzVj();
  
  public abstract K zzVk();
  
  public abstract Iterator<Map.Entry<K, V>> zzVl();
  
  public abstract void zza(zzblf.zzb<K, V> paramzzb);
  
  public abstract zzbla<K, V> zzae(K paramK);
  
  public abstract K zzaf(K paramK);
  
  public abstract zzbla<K, V> zzj(K paramK, V paramV);
  
  public static class zza
  {
    private static final zza zzbYL = new zza()
    {
      public Object zzai(Object paramAnonymousObject)
      {
        return paramAnonymousObject;
      }
    };
    
    public static <A> zza<A, A> zzVm()
    {
      return zzbYL;
    }
    
    public static <K, V> zzbla<K, V> zza(Comparator<K> paramComparator)
    {
      return new zzbkz(paramComparator);
    }
    
    public static <A, B, C> zzbla<A, C> zzb(List<A> paramList, Map<B, C> paramMap, zza<A, B> paramzza, Comparator<A> paramComparator)
    {
      if (paramList.size() < 25) {
        return zzbkz.zza(paramList, paramMap, paramzza, paramComparator);
      }
      return zzbli.zzc(paramList, paramMap, paramzza, paramComparator);
    }
    
    public static <A, B> zzbla<A, B> zzb(Map<A, B> paramMap, Comparator<A> paramComparator)
    {
      if (paramMap.size() < 25) {
        return zzbkz.zza(paramMap, paramComparator);
      }
      return zzbli.zzc(paramMap, paramComparator);
    }
    
    public static abstract interface zza<C, D>
    {
      public abstract D zzai(C paramC);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbla.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */