package com.google.android.gms.internal;

import java.util.Comparator;

public abstract class zzblh<K, V>
  implements zzblf<K, V>
{
  private final V value;
  private final K zzbYU;
  private zzblf<K, V> zzbYV;
  private final zzblf<K, V> zzbYW;
  
  zzblh(K paramK, V paramV, zzblf<K, V> paramzzblf1, zzblf<K, V> paramzzblf2)
  {
    this.zzbYU = paramK;
    this.value = paramV;
    paramK = paramzzblf1;
    if (paramzzblf1 == null) {
      paramK = zzble.zzVr();
    }
    this.zzbYV = paramK;
    paramK = paramzzblf2;
    if (paramzzblf2 == null) {
      paramK = zzble.zzVr();
    }
    this.zzbYW = paramK;
  }
  
  private zzblh<K, V> zzVA()
  {
    Object localObject2 = this;
    if (this.zzbYW.zzVq())
    {
      localObject2 = this;
      if (!this.zzbYV.zzVq()) {
        localObject2 = zzVB();
      }
    }
    Object localObject1 = localObject2;
    if (((zzblh)localObject2).zzbYV.zzVq())
    {
      localObject1 = localObject2;
      if (((zzblh)((zzblh)localObject2).zzbYV).zzbYV.zzVq()) {
        localObject1 = ((zzblh)localObject2).zzVC();
      }
    }
    localObject2 = localObject1;
    if (((zzblh)localObject1).zzbYV.zzVq())
    {
      localObject2 = localObject1;
      if (((zzblh)localObject1).zzbYW.zzVq()) {
        localObject2 = ((zzblh)localObject1).zzVD();
      }
    }
    return (zzblh<K, V>)localObject2;
  }
  
  private zzblh<K, V> zzVB()
  {
    zzblh localzzblh = (zzblh)zza(null, null, zzblf.zza.zzbYR, null, ((zzblh)this.zzbYW).zzbYV);
    return (zzblh)this.zzbYW.zza(null, null, zzVp(), localzzblh, null);
  }
  
  private zzblh<K, V> zzVC()
  {
    zzblh localzzblh = (zzblh)zza(null, null, zzblf.zza.zzbYR, ((zzblh)this.zzbYV).zzbYW, null);
    return (zzblh)this.zzbYV.zza(null, null, zzVp(), null, localzzblh);
  }
  
  private zzblh<K, V> zzVD()
  {
    zzblf localzzblf1 = this.zzbYV.zza(null, null, zza(this.zzbYV), null, null);
    zzblf localzzblf2 = this.zzbYW.zza(null, null, zza(this.zzbYW), null, null);
    return (zzblh)zza(null, null, zza(this), localzzblf1, localzzblf2);
  }
  
  private zzblf<K, V> zzVx()
  {
    if (this.zzbYV.isEmpty()) {
      return zzble.zzVr();
    }
    zzblh localzzblh = this;
    if (!zzVs().zzVq())
    {
      localzzblh = this;
      if (!zzVs().zzVs().zzVq()) {
        localzzblh = zzVy();
      }
    }
    return localzzblh.zza(null, null, ((zzblh)localzzblh.zzbYV).zzVx(), null).zzVA();
  }
  
  private zzblh<K, V> zzVy()
  {
    zzblh localzzblh = zzVD();
    if (localzzblh.zzVt().zzVs().zzVq()) {
      return localzzblh.zza(null, null, null, ((zzblh)localzzblh.zzVt()).zzVC()).zzVB().zzVD();
    }
    return localzzblh;
  }
  
  private zzblh<K, V> zzVz()
  {
    zzblh localzzblh2 = zzVD();
    zzblh localzzblh1 = localzzblh2;
    if (localzzblh2.zzVs().zzVs().zzVq()) {
      localzzblh1 = localzzblh2.zzVC().zzVD();
    }
    return localzzblh1;
  }
  
  private static zzblf.zza zza(zzblf paramzzblf)
  {
    if (paramzzblf.zzVq()) {
      return zzblf.zza.zzbYS;
    }
    return zzblf.zza.zzbYR;
  }
  
  public K getKey()
  {
    return (K)this.zzbYU;
  }
  
  public V getValue()
  {
    return (V)this.value;
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  protected abstract zzblf.zza zzVp();
  
  public zzblf<K, V> zzVs()
  {
    return this.zzbYV;
  }
  
  public zzblf<K, V> zzVt()
  {
    return this.zzbYW;
  }
  
  public zzblf<K, V> zzVu()
  {
    if (this.zzbYV.isEmpty()) {
      return this;
    }
    return this.zzbYV.zzVu();
  }
  
  public zzblf<K, V> zzVv()
  {
    if (this.zzbYW.isEmpty()) {
      return this;
    }
    return this.zzbYW.zzVv();
  }
  
  public int zzVw()
  {
    return this.zzbYV.zzVw() + 1 + this.zzbYW.zzVw();
  }
  
  public zzblf<K, V> zza(K paramK, V paramV, Comparator<K> paramComparator)
  {
    int i = paramComparator.compare(paramK, this.zzbYU);
    if (i < 0) {
      paramK = zza(null, null, this.zzbYV.zza(paramK, paramV, paramComparator), null);
    }
    for (;;)
    {
      return paramK.zzVA();
      if (i == 0) {
        paramK = zza(paramK, paramV, null, null);
      } else {
        paramK = zza(null, null, null, this.zzbYW.zza(paramK, paramV, paramComparator));
      }
    }
  }
  
  public zzblf<K, V> zza(K paramK, Comparator<K> paramComparator)
  {
    Object localObject1;
    if (paramComparator.compare(paramK, this.zzbYU) < 0)
    {
      localObject1 = this;
      if (!this.zzbYV.isEmpty())
      {
        localObject1 = this;
        if (!this.zzbYV.zzVq())
        {
          localObject1 = this;
          if (!((zzblh)this.zzbYV).zzbYV.zzVq()) {
            localObject1 = zzVy();
          }
        }
      }
    }
    Object localObject2;
    for (paramK = ((zzblh)localObject1).zza(null, null, ((zzblh)localObject1).zzbYV.zza(paramK, paramComparator), null);; paramK = ((zzblh)localObject2).zza(null, null, null, ((zzblh)localObject2).zzbYW.zza(paramK, paramComparator)))
    {
      return paramK.zzVA();
      localObject2 = this;
      if (this.zzbYV.zzVq()) {
        localObject2 = zzVC();
      }
      localObject1 = localObject2;
      if (!((zzblh)localObject2).zzbYW.isEmpty())
      {
        localObject1 = localObject2;
        if (!((zzblh)localObject2).zzbYW.zzVq())
        {
          localObject1 = localObject2;
          if (!((zzblh)((zzblh)localObject2).zzbYW).zzbYV.zzVq()) {
            localObject1 = ((zzblh)localObject2).zzVz();
          }
        }
      }
      localObject2 = localObject1;
      if (paramComparator.compare(paramK, ((zzblh)localObject1).zzbYU) == 0)
      {
        if (((zzblh)localObject1).zzbYW.isEmpty()) {
          return zzble.zzVr();
        }
        localObject2 = ((zzblh)localObject1).zzbYW.zzVu();
        localObject2 = ((zzblh)localObject1).zza(((zzblf)localObject2).getKey(), ((zzblf)localObject2).getValue(), null, ((zzblh)((zzblh)localObject1).zzbYW).zzVx());
      }
    }
  }
  
  protected abstract zzblh<K, V> zza(K paramK, V paramV, zzblf<K, V> paramzzblf1, zzblf<K, V> paramzzblf2);
  
  public void zza(zzblf.zzb<K, V> paramzzb)
  {
    this.zzbYV.zza(paramzzb);
    paramzzb.zzk(this.zzbYU, this.value);
    this.zzbYW.zza(paramzzb);
  }
  
  public zzblh<K, V> zzb(K paramK, V paramV, zzblf.zza paramzza, zzblf<K, V> paramzzblf1, zzblf<K, V> paramzzblf2)
  {
    Object localObject = paramK;
    if (paramK == null) {
      localObject = this.zzbYU;
    }
    paramK = paramV;
    if (paramV == null) {
      paramK = this.value;
    }
    paramV = paramzzblf1;
    if (paramzzblf1 == null) {
      paramV = this.zzbYV;
    }
    paramzzblf1 = paramzzblf2;
    if (paramzzblf2 == null) {
      paramzzblf1 = this.zzbYW;
    }
    if (paramzza == zzblf.zza.zzbYR) {
      return new zzblg(localObject, paramK, paramV, paramzzblf1);
    }
    return new zzbld(localObject, paramK, paramV, paramzzblf1);
  }
  
  void zzb(zzblf<K, V> paramzzblf)
  {
    this.zzbYV = paramzzblf;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */