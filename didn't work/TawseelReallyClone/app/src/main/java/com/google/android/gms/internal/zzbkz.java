package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzbkz<K, V>
  extends zzbla<K, V>
{
  private final K[] zzbYE;
  private final V[] zzbYF;
  private final Comparator<K> zzbYG;
  
  public zzbkz(Comparator<K> paramComparator)
  {
    this.zzbYE = new Object[0];
    this.zzbYF = new Object[0];
    this.zzbYG = paramComparator;
  }
  
  private zzbkz(Comparator<K> paramComparator, K[] paramArrayOfK, V[] paramArrayOfV)
  {
    this.zzbYE = paramArrayOfK;
    this.zzbYF = paramArrayOfV;
    this.zzbYG = paramComparator;
  }
  
  public static <A, B, C> zzbkz<A, C> zza(List<A> paramList, Map<B, C> paramMap, zzbla.zza.zza<A, B> paramzza, Comparator<A> paramComparator)
  {
    Collections.sort(paramList, paramComparator);
    int i = paramList.size();
    Object[] arrayOfObject1 = new Object[i];
    Object[] arrayOfObject2 = new Object[i];
    i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = paramList.next();
      arrayOfObject1[i] = localObject;
      arrayOfObject2[i] = paramMap.get(paramzza.zzai(localObject));
      i += 1;
    }
    return new zzbkz(paramComparator, arrayOfObject1, arrayOfObject2);
  }
  
  public static <K, V> zzbkz<K, V> zza(Map<K, V> paramMap, Comparator<K> paramComparator)
  {
    return zza(new ArrayList(paramMap.keySet()), paramMap, zzbla.zza.zzVm(), paramComparator);
  }
  
  private static <T> T[] zza(T[] paramArrayOfT, int paramInt)
  {
    int i = paramArrayOfT.length - 1;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, paramInt);
    System.arraycopy(paramArrayOfT, paramInt + 1, arrayOfObject, paramInt, i - paramInt);
    return arrayOfObject;
  }
  
  private static <T> T[] zza(T[] paramArrayOfT, int paramInt, T paramT)
  {
    int i = paramArrayOfT.length + 1;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, paramInt);
    arrayOfObject[paramInt] = paramT;
    System.arraycopy(paramArrayOfT, paramInt, arrayOfObject, paramInt + 1, i - paramInt - 1);
    return arrayOfObject;
  }
  
  private int zzag(K paramK)
  {
    int i = 0;
    while ((i < this.zzbYE.length) && (this.zzbYG.compare(this.zzbYE[i], paramK) < 0)) {
      i += 1;
    }
    return i;
  }
  
  private int zzah(K paramK)
  {
    int i = 0;
    Object[] arrayOfObject = this.zzbYE;
    int k = arrayOfObject.length;
    int j = 0;
    while (j < k)
    {
      Object localObject = arrayOfObject[j];
      if (this.zzbYG.compare(paramK, localObject) == 0) {
        return i;
      }
      j += 1;
      i += 1;
    }
    return -1;
  }
  
  private static <T> T[] zzb(T[] paramArrayOfT, int paramInt, T paramT)
  {
    int i = paramArrayOfT.length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, i);
    arrayOfObject[paramInt] = paramT;
    return arrayOfObject;
  }
  
  private Iterator<Map.Entry<K, V>> zze(final int paramInt, final boolean paramBoolean)
  {
    new Iterator()
    {
      int zzbYH = paramInt;
      
      public boolean hasNext()
      {
        if (paramBoolean) {
          if (this.zzbYH < 0) {}
        }
        while (this.zzbYH < zzbkz.zza(zzbkz.this).length)
        {
          return true;
          return false;
        }
        return false;
      }
      
      public Map.Entry<K, V> next()
      {
        Object localObject1 = zzbkz.zza(zzbkz.this)[this.zzbYH];
        Object localObject2 = zzbkz.zzb(zzbkz.this)[this.zzbYH];
        if (paramBoolean) {}
        for (int i = this.zzbYH - 1;; i = this.zzbYH + 1)
        {
          this.zzbYH = i;
          return new AbstractMap.SimpleImmutableEntry(localObject1, localObject2);
        }
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
      }
    };
  }
  
  public boolean containsKey(K paramK)
  {
    return zzah(paramK) != -1;
  }
  
  public V get(K paramK)
  {
    int i = zzah(paramK);
    if (i != -1) {
      return (V)this.zzbYF[i];
    }
    return null;
  }
  
  public Comparator<K> getComparator()
  {
    return this.zzbYG;
  }
  
  public boolean isEmpty()
  {
    return this.zzbYE.length == 0;
  }
  
  public Iterator<Map.Entry<K, V>> iterator()
  {
    return zze(0, false);
  }
  
  public int size()
  {
    return this.zzbYE.length;
  }
  
  public K zzVj()
  {
    if (this.zzbYE.length > 0) {
      return (K)this.zzbYE[0];
    }
    return null;
  }
  
  public K zzVk()
  {
    if (this.zzbYE.length > 0) {
      return (K)this.zzbYE[(this.zzbYE.length - 1)];
    }
    return null;
  }
  
  public Iterator<Map.Entry<K, V>> zzVl()
  {
    return zze(this.zzbYE.length - 1, true);
  }
  
  public void zza(zzblf.zzb<K, V> paramzzb)
  {
    int i = 0;
    while (i < this.zzbYE.length)
    {
      paramzzb.zzk(this.zzbYE[i], this.zzbYF[i]);
      i += 1;
    }
  }
  
  public zzbla<K, V> zzae(K paramK)
  {
    int i = zzah(paramK);
    if (i == -1) {
      return this;
    }
    paramK = zza(this.zzbYE, i);
    Object[] arrayOfObject = zza(this.zzbYF, i);
    return new zzbkz(this.zzbYG, paramK, arrayOfObject);
  }
  
  public K zzaf(K paramK)
  {
    int i = zzah(paramK);
    if (i == -1) {
      throw new IllegalArgumentException("Can't find predecessor of nonexistent key");
    }
    if (i > 0) {
      return (K)this.zzbYE[(i - 1)];
    }
    return null;
  }
  
  public zzbla<K, V> zzj(K paramK, V paramV)
  {
    int i = zzah(paramK);
    if (i != -1)
    {
      if ((this.zzbYE[i] == paramK) && (this.zzbYF[i] == paramV)) {
        return this;
      }
      paramK = zzb(this.zzbYE, i, paramK);
      paramV = zzb(this.zzbYF, i, paramV);
      return new zzbkz(this.zzbYG, paramK, paramV);
    }
    if (this.zzbYE.length > 25)
    {
      HashMap localHashMap = new HashMap(this.zzbYE.length + 1);
      i = 0;
      while (i < this.zzbYE.length)
      {
        localHashMap.put(this.zzbYE[i], this.zzbYF[i]);
        i += 1;
      }
      localHashMap.put(paramK, paramV);
      return zzbli.zzc(localHashMap, this.zzbYG);
    }
    i = zzag(paramK);
    paramK = zza(this.zzbYE, i, paramK);
    paramV = zza(this.zzbYF, i, paramV);
    return new zzbkz(this.zzbYG, paramK, paramV);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */