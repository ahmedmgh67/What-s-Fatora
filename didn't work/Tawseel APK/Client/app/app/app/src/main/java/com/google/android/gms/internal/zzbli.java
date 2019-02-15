package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzbli<K, V>
  extends zzbla<K, V>
{
  private Comparator<K> zzbYG;
  private zzblf<K, V> zzbYX;
  
  private zzbli(zzblf<K, V> paramzzblf, Comparator<K> paramComparator)
  {
    this.zzbYX = paramzzblf;
    this.zzbYG = paramComparator;
  }
  
  private zzblf<K, V> zzam(K paramK)
  {
    zzblf localzzblf = this.zzbYX;
    while (!localzzblf.isEmpty())
    {
      int i = this.zzbYG.compare(paramK, localzzblf.getKey());
      if (i < 0)
      {
        localzzblf = localzzblf.zzVs();
      }
      else
      {
        if (i == 0) {
          return localzzblf;
        }
        localzzblf = localzzblf.zzVt();
      }
    }
    return null;
  }
  
  public static <A, B, C> zzbli<A, C> zzc(List<A> paramList, Map<B, C> paramMap, zzbla.zza.zza<A, B> paramzza, Comparator<A> paramComparator)
  {
    return zza.zzc(paramList, paramMap, paramzza, paramComparator);
  }
  
  public static <A, B> zzbli<A, B> zzc(Map<A, B> paramMap, Comparator<A> paramComparator)
  {
    return zza.zzc(new ArrayList(paramMap.keySet()), paramMap, zzbla.zza.zzVm(), paramComparator);
  }
  
  public boolean containsKey(K paramK)
  {
    return zzam(paramK) != null;
  }
  
  public V get(K paramK)
  {
    paramK = zzam(paramK);
    if (paramK != null) {
      return (V)paramK.getValue();
    }
    return null;
  }
  
  public Comparator<K> getComparator()
  {
    return this.zzbYG;
  }
  
  public boolean isEmpty()
  {
    return this.zzbYX.isEmpty();
  }
  
  public Iterator<Map.Entry<K, V>> iterator()
  {
    return new zzblb(this.zzbYX, null, this.zzbYG, false);
  }
  
  public int size()
  {
    return this.zzbYX.zzVw();
  }
  
  public K zzVj()
  {
    return (K)this.zzbYX.zzVu().getKey();
  }
  
  public K zzVk()
  {
    return (K)this.zzbYX.zzVv().getKey();
  }
  
  public Iterator<Map.Entry<K, V>> zzVl()
  {
    return new zzblb(this.zzbYX, null, this.zzbYG, true);
  }
  
  public void zza(zzblf.zzb<K, V> paramzzb)
  {
    this.zzbYX.zza(paramzzb);
  }
  
  public zzbla<K, V> zzae(K paramK)
  {
    if (!containsKey(paramK)) {
      return this;
    }
    return new zzbli(this.zzbYX.zza(paramK, this.zzbYG).zza(null, null, zzblf.zza.zzbYS, null, null), this.zzbYG);
  }
  
  public K zzaf(K paramK)
  {
    Object localObject3 = null;
    Object localObject1 = this.zzbYX;
    Object localObject2 = null;
    while (!((zzblf)localObject1).isEmpty())
    {
      int i = this.zzbYG.compare(paramK, ((zzblf)localObject1).getKey());
      if (i == 0)
      {
        if (!((zzblf)localObject1).zzVs().isEmpty())
        {
          for (paramK = ((zzblf)localObject1).zzVs(); !paramK.zzVt().isEmpty(); paramK = paramK.zzVt()) {}
          paramK = paramK.getKey();
        }
        do
        {
          return paramK;
          paramK = (K)localObject3;
        } while (localObject2 == null);
        return (K)((zzblf)localObject2).getKey();
      }
      if (i < 0)
      {
        localObject1 = ((zzblf)localObject1).zzVs();
      }
      else
      {
        zzblf localzzblf = ((zzblf)localObject1).zzVt();
        localObject2 = localObject1;
        localObject1 = localzzblf;
      }
    }
    paramK = String.valueOf(paramK);
    throw new IllegalArgumentException(String.valueOf(paramK).length() + 50 + "Couldn't find predecessor key of non-present key: " + paramK);
  }
  
  public zzbla<K, V> zzj(K paramK, V paramV)
  {
    return new zzbli(this.zzbYX.zza(paramK, paramV, this.zzbYG).zza(null, null, zzblf.zza.zzbYS, null, null), this.zzbYG);
  }
  
  private static class zza<A, B, C>
  {
    private final Map<B, C> values;
    private final List<A> zzbYY;
    private final zzbla.zza.zza<A, B> zzbYZ;
    private zzblh<A, C> zzbZa;
    private zzblh<A, C> zzbZb;
    
    private zza(List<A> paramList, Map<B, C> paramMap, zzbla.zza.zza<A, B> paramzza)
    {
      this.zzbYY = paramList;
      this.values = paramMap;
      this.zzbYZ = paramzza;
    }
    
    private zzblf<A, C> zzB(int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0) {
        return zzble.zzVr();
      }
      if (paramInt2 == 1)
      {
        localObject1 = this.zzbYY.get(paramInt1);
        return new zzbld(localObject1, zzan(localObject1), null, null);
      }
      paramInt2 /= 2;
      int i = paramInt1 + paramInt2;
      Object localObject1 = zzB(paramInt1, paramInt2);
      zzblf localzzblf = zzB(i + 1, paramInt2);
      Object localObject2 = this.zzbYY.get(i);
      return new zzbld(localObject2, zzan(localObject2), (zzblf)localObject1, localzzblf);
    }
    
    private void zza(zzblf.zza paramzza, int paramInt1, int paramInt2)
    {
      zzblf localzzblf = zzB(paramInt2 + 1, paramInt1 - 1);
      Object localObject = this.zzbYY.get(paramInt2);
      if (paramzza == zzblf.zza.zzbYR) {}
      for (paramzza = new zzblg(localObject, zzan(localObject), null, localzzblf); this.zzbZa == null; paramzza = new zzbld(localObject, zzan(localObject), null, localzzblf))
      {
        this.zzbZa = paramzza;
        this.zzbZb = paramzza;
        return;
      }
      this.zzbZb.zzb(paramzza);
      this.zzbZb = paramzza;
    }
    
    private C zzan(A paramA)
    {
      return (C)this.values.get(this.zzbYZ.zzai(paramA));
    }
    
    public static <A, B, C> zzbli<A, C> zzc(List<A> paramList, Map<B, C> paramMap, zzbla.zza.zza<A, B> paramzza, Comparator<A> paramComparator)
    {
      paramMap = new zza(paramList, paramMap, paramzza);
      Collections.sort(paramList, paramComparator);
      paramzza = new zza(paramList.size()).iterator();
      int i = paramList.size();
      if (paramzza.hasNext())
      {
        paramList = (zzb)paramzza.next();
        i -= paramList.zzbZf;
        if (paramList.zzbZe) {
          paramMap.zza(zzblf.zza.zzbYS, paramList.zzbZf, i);
        }
        for (;;)
        {
          break;
          paramMap.zza(zzblf.zza.zzbYS, paramList.zzbZf, i);
          i -= paramList.zzbZf;
          paramMap.zza(zzblf.zza.zzbYR, paramList.zzbZf, i);
        }
      }
      if (paramMap.zzbZa == null) {}
      for (paramList = zzble.zzVr();; paramList = paramMap.zzbZa) {
        return new zzbli(paramList, paramComparator, null);
      }
    }
    
    static class zza
      implements Iterable<zzbli.zza.zzb>
    {
      private final int length;
      private long value;
      
      public zza(int paramInt)
      {
        paramInt += 1;
        this.length = ((int)Math.floor(Math.log(paramInt) / Math.log(2.0D)));
        long l = Math.pow(2.0D, this.length);
        this.value = (paramInt & l - 1L);
      }
      
      public Iterator<zzbli.zza.zzb> iterator()
      {
        new Iterator()
        {
          private int zzbZc = zzbli.zza.zza.zza(zzbli.zza.zza.this) - 1;
          
          public boolean hasNext()
          {
            return this.zzbZc >= 0;
          }
          
          public void remove() {}
          
          public zzbli.zza.zzb zzVE()
          {
            boolean bool = true;
            long l1 = zzbli.zza.zza.zzb(zzbli.zza.zza.this);
            long l2 = 1 << this.zzbZc;
            zzbli.zza.zzb localzzb = new zzbli.zza.zzb();
            if ((l1 & l2) == 0L) {}
            for (;;)
            {
              localzzb.zzbZe = bool;
              localzzb.zzbZf = ((int)Math.pow(2.0D, this.zzbZc));
              this.zzbZc -= 1;
              return localzzb;
              bool = false;
            }
          }
        };
      }
    }
    
    static class zzb
    {
      public boolean zzbZe;
      public int zzbZf;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbli.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */