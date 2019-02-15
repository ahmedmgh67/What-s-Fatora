package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class zzbmp
{
  public static zzbma zza(zzbma paramzzbma, Map<String, Object> paramMap)
  {
    Object localObject = zzbma.zzWE();
    Iterator localIterator = paramzzbma.iterator();
    for (paramzzbma = (zzbma)localObject; localIterator.hasNext(); paramzzbma = paramzzbma.zze((zzbmj)((Map.Entry)localObject).getKey(), zza((zzbpe)((Map.Entry)localObject).getValue(), paramMap))) {
      localObject = (Map.Entry)localIterator.next();
    }
    return paramzzbma;
  }
  
  public static zzbmr zza(zzbmr paramzzbmr, final Map<String, Object> paramMap)
  {
    zzbmr localzzbmr = new zzbmr();
    paramzzbmr.zza(new zzbmj(""), new zzbmr.zzb()
    {
      public void zzf(zzbmj paramAnonymouszzbmj, zzbpe paramAnonymouszzbpe)
      {
        zzbmp.this.zzh(paramAnonymouszzbmj, zzbmp.zza(paramAnonymouszzbpe, paramMap));
      }
    });
    return localzzbmr;
  }
  
  public static zzbpe zza(zzbpe paramzzbpe, Map<String, Object> paramMap)
  {
    Object localObject2 = paramzzbpe.zzZe().getValue();
    final Object localObject1 = localObject2;
    if ((localObject2 instanceof Map))
    {
      Map localMap = (Map)localObject2;
      localObject1 = localObject2;
      if (localMap.containsKey(".sv")) {
        localObject1 = paramMap.get((String)localMap.get(".sv"));
      }
    }
    localObject2 = zzbpi.zzas(localObject1);
    if (paramzzbpe.zzZd())
    {
      paramMap = zza(paramzzbpe.getValue(), paramMap);
      if (paramMap.equals(paramzzbpe.getValue()))
      {
        localObject1 = paramzzbpe;
        if (localObject2.equals(paramzzbpe.zzZe())) {}
      }
      else
      {
        localObject1 = zzbpf.zza(paramMap, (zzbpe)localObject2);
      }
    }
    do
    {
      return (zzbpe)localObject1;
      localObject1 = paramzzbpe;
    } while (paramzzbpe.isEmpty());
    paramzzbpe = (zzbot)paramzzbpe;
    localObject1 = new zzbmq(paramzzbpe);
    paramzzbpe.zza(new zzbot.zza()
    {
      public void zzb(zzbos paramAnonymouszzbos, zzbpe paramAnonymouszzbpe)
      {
        zzbpe localzzbpe = zzbmp.zza(paramAnonymouszzbpe, zzbmp.this);
        if (localzzbpe != paramAnonymouszzbpe) {
          localObject1.zzg(new zzbmj(paramAnonymouszzbos.asString()), localzzbpe);
        }
      }
    });
    if (!((zzbmq)localObject1).zzXv().zzZe().equals(localObject2)) {
      return ((zzbmq)localObject1).zzXv().zzg((zzbpe)localObject2);
    }
    return ((zzbmq)localObject1).zzXv();
  }
  
  public static Object zza(Object paramObject, Map<String, Object> paramMap)
  {
    Object localObject1 = paramObject;
    if ((paramObject instanceof Map))
    {
      Object localObject2 = (Map)paramObject;
      localObject1 = paramObject;
      if (((Map)localObject2).containsKey(".sv"))
      {
        localObject2 = (String)((Map)localObject2).get(".sv");
        localObject1 = paramObject;
        if (paramMap.containsKey(localObject2)) {
          localObject1 = paramMap.get(localObject2);
        }
      }
    }
    return localObject1;
  }
  
  public static Map<String, Object> zza(zzbpy paramzzbpy)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("timestamp", Long.valueOf(paramzzbpy.zzZY()));
    return localHashMap;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */