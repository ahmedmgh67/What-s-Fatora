package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbma
  implements Iterable<Map.Entry<zzbmj, zzbpe>>
{
  private static final zzbma zzcbB;
  private final zzbns<zzbpe> zzcbC;
  
  static
  {
    if (!zzbma.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcbB = new zzbma(new zzbns(null));
      return;
    }
  }
  
  private zzbma(zzbns<zzbpe> paramzzbns)
  {
    this.zzcbC = paramzzbns;
  }
  
  public static zzbma zzWE()
  {
    return zzcbB;
  }
  
  private zzbpe zza(zzbmj paramzzbmj, zzbns<zzbpe> paramzzbns, zzbpe paramzzbpe)
  {
    Object localObject1;
    if (paramzzbns.getValue() != null) {
      localObject1 = paramzzbpe.zzl(paramzzbmj, (zzbpe)paramzzbns.getValue());
    }
    do
    {
      do
      {
        return (zzbpe)localObject1;
        localObject1 = null;
        Iterator localIterator = paramzzbns.zzYe().iterator();
        paramzzbns = paramzzbpe;
        paramzzbpe = (zzbpe)localObject1;
        if (localIterator.hasNext())
        {
          Object localObject2 = (Map.Entry)localIterator.next();
          localObject1 = (zzbns)((Map.Entry)localObject2).getValue();
          localObject2 = (zzbos)((Map.Entry)localObject2).getKey();
          if (((zzbos)localObject2).zzZa())
          {
            assert (((zzbns)localObject1).getValue() != null) : "Priority writes must always be leaf nodes";
            paramzzbpe = (zzbpe)((zzbns)localObject1).getValue();
          }
          for (;;)
          {
            break;
            paramzzbns = zza(paramzzbmj.zza((zzbos)localObject2), (zzbns)localObject1, paramzzbns);
          }
        }
        localObject1 = paramzzbns;
      } while (paramzzbns.zzO(paramzzbmj).isEmpty());
      localObject1 = paramzzbns;
    } while (paramzzbpe == null);
    return paramzzbns.zzl(paramzzbmj.zza(zzbos.zzYY()), paramzzbpe);
  }
  
  public static zzbma zzaA(Map<String, Object> paramMap)
  {
    Object localObject = zzbns.zzYd();
    Iterator localIterator = paramMap.entrySet().iterator();
    zzbns localzzbns;
    for (paramMap = (Map<String, Object>)localObject; localIterator.hasNext(); paramMap = paramMap.zza(new zzbmj((String)((Map.Entry)localObject).getKey()), localzzbns))
    {
      localObject = (Map.Entry)localIterator.next();
      localzzbns = new zzbns(zzbpf.zzar(((Map.Entry)localObject).getValue()));
    }
    return new zzbma(paramMap);
  }
  
  public static zzbma zzaB(Map<zzbmj, zzbpe> paramMap)
  {
    Object localObject = zzbns.zzYd();
    Iterator localIterator = paramMap.entrySet().iterator();
    zzbns localzzbns;
    for (paramMap = (Map<zzbmj, zzbpe>)localObject; localIterator.hasNext(); paramMap = paramMap.zza((zzbmj)((Map.Entry)localObject).getKey(), localzzbns))
    {
      localObject = (Map.Entry)localIterator.next();
      localzzbns = new zzbns((zzbpe)((Map.Entry)localObject).getValue());
    }
    return new zzbma(paramMap);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      return false;
    }
    return ((zzbma)paramObject).zzaZ(true).equals(zzaZ(true));
  }
  
  public int hashCode()
  {
    return zzaZ(true).hashCode();
  }
  
  public boolean isEmpty()
  {
    return this.zzcbC.isEmpty();
  }
  
  public Iterator<Map.Entry<zzbmj, zzbpe>> iterator()
  {
    return this.zzcbC.iterator();
  }
  
  public String toString()
  {
    String str = String.valueOf(zzaZ(true).toString());
    return String.valueOf(str).length() + 15 + "CompoundWrite{" + str + "}";
  }
  
  public zzbpe zzWF()
  {
    return (zzbpe)this.zzcbC.getValue();
  }
  
  public List<zzbpd> zzWG()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject;
    if (this.zzcbC.getValue() != null)
    {
      localIterator = ((zzbpe)this.zzcbC.getValue()).iterator();
      while (localIterator.hasNext())
      {
        localObject = (zzbpd)localIterator.next();
        localArrayList.add(new zzbpd(((zzbpd)localObject).zzZz(), ((zzbpd)localObject).zzUY()));
      }
    }
    Iterator localIterator = this.zzcbC.zzYe().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Map.Entry)localIterator.next();
      zzbns localzzbns = (zzbns)((Map.Entry)localObject).getValue();
      if (localzzbns.getValue() != null) {
        localArrayList.add(new zzbpd((zzbos)((Map.Entry)localObject).getKey(), (zzbpe)localzzbns.getValue()));
      }
    }
    return localArrayList;
  }
  
  public Map<zzbos, zzbma> zzWH()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.zzcbC.zzYe().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put((zzbos)localEntry.getKey(), new zzbma((zzbns)localEntry.getValue()));
    }
    return localHashMap;
  }
  
  public zzbma zza(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    return zze(new zzbmj(new zzbos[] { paramzzbos }), paramzzbpe);
  }
  
  public Map<String, Object> zzaZ(final boolean paramBoolean)
  {
    final HashMap localHashMap = new HashMap();
    this.zzcbC.zza(new zzbns.zza()
    {
      public Void zza(zzbmj paramAnonymouszzbmj, zzbpe paramAnonymouszzbpe, Void paramAnonymousVoid)
      {
        localHashMap.put(paramAnonymouszzbmj.zzXg(), paramAnonymouszzbpe.getValue(paramBoolean));
        return null;
      }
    });
    return localHashMap;
  }
  
  public zzbma zzb(final zzbmj paramzzbmj, zzbma paramzzbma)
  {
    (zzbma)paramzzbma.zzcbC.zzb(this, new zzbns.zza()
    {
      public zzbma zza(zzbmj paramAnonymouszzbmj, zzbpe paramAnonymouszzbpe, zzbma paramAnonymouszzbma)
      {
        return paramAnonymouszzbma.zze(paramzzbmj.zzh(paramAnonymouszzbmj), paramAnonymouszzbpe);
      }
    });
  }
  
  public zzbpe zzb(zzbpe paramzzbpe)
  {
    return zza(zzbmj.zzXf(), this.zzcbC, paramzzbpe);
  }
  
  public zzbma zzd(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return zzcbB;
    }
    return new zzbma(this.zzcbC.zza(paramzzbmj, zzbns.zzYd()));
  }
  
  public zzbma zze(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    if (paramzzbmj.isEmpty()) {
      paramzzbmj = new zzbma(new zzbns(paramzzbpe));
    }
    zzbmj localzzbmj1;
    zzbmj localzzbmj2;
    zzbpe localzzbpe;
    do
    {
      return paramzzbmj;
      localzzbmj1 = this.zzcbC.zzG(paramzzbmj);
      if (localzzbmj1 == null) {
        break label125;
      }
      localzzbmj2 = zzbmj.zza(localzzbmj1, paramzzbmj);
      localzzbpe = (zzbpe)this.zzcbC.zzK(localzzbmj1);
      paramzzbmj = localzzbmj2.zzXl();
      if ((paramzzbmj == null) || (!paramzzbmj.zzZa())) {
        break;
      }
      paramzzbmj = this;
    } while (localzzbpe.zzO(localzzbmj2.zzXk()).isEmpty());
    paramzzbmj = localzzbpe.zzl(localzzbmj2, paramzzbpe);
    return new zzbma(this.zzcbC.zzb(localzzbmj1, paramzzbmj));
    label125:
    paramzzbpe = new zzbns(paramzzbpe);
    return new zzbma(this.zzcbC.zza(paramzzbmj, paramzzbpe));
  }
  
  public boolean zze(zzbmj paramzzbmj)
  {
    return zzf(paramzzbmj) != null;
  }
  
  public zzbpe zzf(zzbmj paramzzbmj)
  {
    zzbmj localzzbmj = this.zzcbC.zzG(paramzzbmj);
    if (localzzbmj != null) {
      return ((zzbpe)this.zzcbC.zzK(localzzbmj)).zzO(zzbmj.zza(localzzbmj, paramzzbmj));
    }
    return null;
  }
  
  public zzbma zzg(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return this;
    }
    zzbpe localzzbpe = zzf(paramzzbmj);
    if (localzzbpe != null) {
      return new zzbma(new zzbns(localzzbpe));
    }
    return new zzbma(this.zzcbC.zzI(paramzzbmj));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbma.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */