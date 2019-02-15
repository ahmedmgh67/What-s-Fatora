package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class zzbns<T>
  implements Iterable<Map.Entry<zzbmj, T>>
{
  private static final zzbla zzcfr = zzbla.zza.zza(zzblj.zzh(zzbos.class));
  private static final zzbns zzcfs = new zzbns(null, zzcfr);
  private final T value;
  private final zzbla<zzbos, zzbns<T>> zzcfq;
  
  public zzbns(T paramT)
  {
    this(paramT, zzcfr);
  }
  
  public zzbns(T paramT, zzbla<zzbos, zzbns<T>> paramzzbla)
  {
    this.value = paramT;
    this.zzcfq = paramzzbla;
  }
  
  public static <V> zzbns<V> zzYd()
  {
    return zzcfs;
  }
  
  private <R> R zza(zzbmj paramzzbmj, zza<? super T, R> paramzza, R paramR)
  {
    Object localObject = this.zzcfq.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      paramR = ((zzbns)localEntry.getValue()).zza(paramzzbmj.zza((zzbos)localEntry.getKey()), paramzza, paramR);
    }
    localObject = paramR;
    if (this.value != null) {
      localObject = paramzza.zza(paramzzbmj, this.value, paramR);
    }
    return (R)localObject;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzbns)paramObject;
      if (this.zzcfq != null)
      {
        if (this.zzcfq.equals(((zzbns)paramObject).zzcfq)) {}
      }
      else {
        while (((zzbns)paramObject).zzcfq != null) {
          return false;
        }
      }
      if (this.value == null) {
        break;
      }
    } while (this.value.equals(((zzbns)paramObject).value));
    for (;;)
    {
      return false;
      if (((zzbns)paramObject).value == null) {
        break;
      }
    }
  }
  
  public T getValue()
  {
    return (T)this.value;
  }
  
  public int hashCode()
  {
    int j = 0;
    if (this.value != null) {}
    for (int i = this.value.hashCode();; i = 0)
    {
      if (this.zzcfq != null) {
        j = this.zzcfq.hashCode();
      }
      return i * 31 + j;
    }
  }
  
  public boolean isEmpty()
  {
    return (this.value == null) && (this.zzcfq.isEmpty());
  }
  
  public Iterator<Map.Entry<zzbmj, T>> iterator()
  {
    final ArrayList localArrayList = new ArrayList();
    zza(new zza()
    {
      public Void zza(zzbmj paramAnonymouszzbmj, T paramAnonymousT, Void paramAnonymousVoid)
      {
        localArrayList.add(new AbstractMap.SimpleImmutableEntry(paramAnonymouszzbmj, paramAnonymousT));
        return null;
      }
    });
    return localArrayList.iterator();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ImmutableTree { value=");
    localStringBuilder.append(getValue());
    localStringBuilder.append(", children={");
    Iterator localIterator = this.zzcfq.iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append(((zzbos)localEntry.getKey()).asString());
      localStringBuilder.append("=");
      localStringBuilder.append(localEntry.getValue());
    }
    localStringBuilder.append("} }");
    return localStringBuilder.toString();
  }
  
  public Collection<T> values()
  {
    final ArrayList localArrayList = new ArrayList();
    zza(new zza()
    {
      public Void zza(zzbmj paramAnonymouszzbmj, T paramAnonymousT, Void paramAnonymousVoid)
      {
        localArrayList.add(paramAnonymousT);
        return null;
      }
    });
    return localArrayList;
  }
  
  public zzbmj zzG(zzbmj paramzzbmj)
  {
    return zza(paramzzbmj, zzbnt.zzcfv);
  }
  
  public T zzH(zzbmj paramzzbmj)
  {
    return (T)zzc(paramzzbmj, zzbnt.zzcfv);
  }
  
  public zzbns<T> zzI(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return this;
    }
    Object localObject = paramzzbmj.zzXi();
    localObject = (zzbns)this.zzcfq.get(localObject);
    if (localObject != null) {
      return ((zzbns)localObject).zzI(paramzzbmj.zzXj());
    }
    return zzYd();
  }
  
  public zzbns<T> zzJ(zzbmj paramzzbmj)
  {
    zzbns localzzbns1;
    if (paramzzbmj.isEmpty()) {
      if (this.zzcfq.isEmpty()) {
        localzzbns1 = zzYd();
      }
    }
    zzbos localzzbos;
    zzbns localzzbns2;
    do
    {
      return localzzbns1;
      return new zzbns(null, this.zzcfq);
      localzzbos = paramzzbmj.zzXi();
      localzzbns2 = (zzbns)this.zzcfq.get(localzzbos);
      localzzbns1 = this;
    } while (localzzbns2 == null);
    paramzzbmj = localzzbns2.zzJ(paramzzbmj.zzXj());
    if (paramzzbmj.isEmpty()) {}
    for (paramzzbmj = this.zzcfq.zzae(localzzbos); (this.value == null) && (paramzzbmj.isEmpty()); paramzzbmj = this.zzcfq.zzj(localzzbos, paramzzbmj)) {
      return zzYd();
    }
    return new zzbns(this.value, paramzzbmj);
  }
  
  public T zzK(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return (T)this.value;
    }
    Object localObject = paramzzbmj.zzXi();
    localObject = (zzbns)this.zzcfq.get(localObject);
    if (localObject != null) {
      return (T)((zzbns)localObject).zzK(paramzzbmj.zzXj());
    }
    return null;
  }
  
  public zzbla<zzbos, zzbns<T>> zzYe()
  {
    return this.zzcfq;
  }
  
  public zzbmj zza(zzbmj paramzzbmj, zzbnt<? super T> paramzzbnt)
  {
    if ((this.value != null) && (paramzzbnt.zzaq(this.value))) {
      return zzbmj.zzXf();
    }
    if (paramzzbmj.isEmpty()) {
      return null;
    }
    zzbos localzzbos = paramzzbmj.zzXi();
    zzbns localzzbns = (zzbns)this.zzcfq.get(localzzbos);
    if (localzzbns != null)
    {
      paramzzbmj = localzzbns.zza(paramzzbmj.zzXj(), paramzzbnt);
      if (paramzzbmj != null) {
        return new zzbmj(new zzbos[] { localzzbos }).zzh(paramzzbmj);
      }
      return null;
    }
    return null;
  }
  
  public zzbns<T> zza(zzbmj paramzzbmj, zzbns<T> paramzzbns)
  {
    if (paramzzbmj.isEmpty()) {
      return paramzzbns;
    }
    zzbos localzzbos = paramzzbmj.zzXi();
    zzbns localzzbns2 = (zzbns)this.zzcfq.get(localzzbos);
    zzbns localzzbns1 = localzzbns2;
    if (localzzbns2 == null) {
      localzzbns1 = zzYd();
    }
    paramzzbmj = localzzbns1.zza(paramzzbmj.zzXj(), paramzzbns);
    if (paramzzbmj.isEmpty()) {}
    for (paramzzbmj = this.zzcfq.zzae(localzzbos);; paramzzbmj = this.zzcfq.zzj(localzzbos, paramzzbmj)) {
      return new zzbns(this.value, paramzzbmj);
    }
  }
  
  public void zza(zza<T, Void> paramzza)
  {
    zza(zzbmj.zzXf(), paramzza, null);
  }
  
  public zzbns<T> zzb(zzbmj paramzzbmj, T paramT)
  {
    if (paramzzbmj.isEmpty()) {
      return new zzbns(paramT, this.zzcfq);
    }
    zzbos localzzbos = paramzzbmj.zzXi();
    zzbns localzzbns2 = (zzbns)this.zzcfq.get(localzzbos);
    zzbns localzzbns1 = localzzbns2;
    if (localzzbns2 == null) {
      localzzbns1 = zzYd();
    }
    paramzzbmj = localzzbns1.zzb(paramzzbmj.zzXj(), paramT);
    paramzzbmj = this.zzcfq.zzj(localzzbos, paramzzbmj);
    return new zzbns(this.value, paramzzbmj);
  }
  
  public T zzb(zzbmj paramzzbmj, zzbnt<? super T> paramzzbnt)
  {
    if ((this.value != null) && (paramzzbnt.zzaq(this.value))) {
      return (T)this.value;
    }
    Iterator localIterator = paramzzbmj.iterator();
    paramzzbmj = this;
    while (localIterator.hasNext())
    {
      zzbos localzzbos = (zzbos)localIterator.next();
      paramzzbmj = (zzbns)paramzzbmj.zzcfq.get(localzzbos);
      if (paramzzbmj == null) {
        return null;
      }
      if ((paramzzbmj.value != null) && (paramzzbnt.zzaq(paramzzbmj.value))) {
        return (T)paramzzbmj.value;
      }
    }
    return null;
  }
  
  public <R> R zzb(R paramR, zza<? super T, R> paramzza)
  {
    return (R)zza(zzbmj.zzXf(), paramzza, paramR);
  }
  
  public boolean zzb(zzbnt<? super T> paramzzbnt)
  {
    if ((this.value != null) && (paramzzbnt.zzaq(this.value))) {
      return true;
    }
    Iterator localIterator = this.zzcfq.iterator();
    while (localIterator.hasNext()) {
      if (((zzbns)((Map.Entry)localIterator.next()).getValue()).zzb(paramzzbnt)) {
        return true;
      }
    }
    return false;
  }
  
  public T zzc(zzbmj paramzzbmj, zzbnt<? super T> paramzzbnt)
  {
    Iterator localIterator;
    if ((this.value != null) && (paramzzbnt.zzaq(this.value)))
    {
      localObject1 = this.value;
      localIterator = paramzzbmj.iterator();
      paramzzbmj = (zzbmj)localObject1;
    }
    Object localObject2;
    for (Object localObject1 = this;; localObject1 = localObject2)
    {
      if (localIterator.hasNext())
      {
        localObject2 = (zzbos)localIterator.next();
        localObject2 = (zzbns)((zzbns)localObject1).zzcfq.get(localObject2);
        if (localObject2 != null) {}
      }
      else
      {
        return paramzzbmj;
        localObject1 = null;
        break;
      }
      localObject1 = paramzzbmj;
      if (((zzbns)localObject2).value != null)
      {
        localObject1 = paramzzbmj;
        if (paramzzbnt.zzaq(((zzbns)localObject2).value)) {
          localObject1 = ((zzbns)localObject2).value;
        }
      }
      paramzzbmj = (zzbmj)localObject1;
    }
  }
  
  public zzbns<T> zze(zzbos paramzzbos)
  {
    paramzzbos = (zzbns)this.zzcfq.get(paramzzbos);
    if (paramzzbos != null) {
      return paramzzbos;
    }
    return zzYd();
  }
  
  public static abstract interface zza<T, R>
  {
    public abstract R zza(zzbmj paramzzbmj, T paramT, R paramR);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbns.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */