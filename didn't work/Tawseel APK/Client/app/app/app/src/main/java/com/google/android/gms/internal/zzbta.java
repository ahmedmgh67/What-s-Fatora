package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzbta
  implements zzbse
{
  private final zzbsl zzcmu;
  private final boolean zzcoj;
  
  public zzbta(zzbsl paramzzbsl, boolean paramBoolean)
  {
    this.zzcmu = paramzzbsl;
    this.zzcoj = paramBoolean;
  }
  
  private zzbsd<?> zza(zzbrl paramzzbrl, Type paramType)
  {
    if ((paramType == Boolean.TYPE) || (paramType == Boolean.class)) {
      return zzbtg.zzcoF;
    }
    return paramzzbrl.zza(zzbth.zzl(paramType));
  }
  
  public <T> zzbsd<T> zza(zzbrl paramzzbrl, zzbth<T> paramzzbth)
  {
    Object localObject = paramzzbth.zzacc();
    if (!Map.class.isAssignableFrom(paramzzbth.zzacb())) {
      return null;
    }
    localObject = zzbsk.zzb((Type)localObject, zzbsk.zzf((Type)localObject));
    zzbsd localzzbsd1 = zza(paramzzbrl, localObject[0]);
    zzbsd localzzbsd2 = paramzzbrl.zza(zzbth.zzl(localObject[1]));
    paramzzbth = this.zzcmu.zzb(paramzzbth);
    return new zza(paramzzbrl, localObject[0], localzzbsd1, localObject[1], localzzbsd2, paramzzbth);
  }
  
  private final class zza<K, V>
    extends zzbsd<Map<K, V>>
  {
    private final zzbsq<? extends Map<K, V>> zzcob;
    private final zzbsd<K> zzcok;
    private final zzbsd<V> zzcol;
    
    public zza(Type paramType1, zzbsd<K> paramzzbsd, Type paramType2, zzbsd<V> paramzzbsd1, zzbsq<? extends Map<K, V>> paramzzbsq)
    {
      this.zzcok = new zzbtf(paramType1, paramType2, paramzzbsd);
      this.zzcol = new zzbtf(paramType1, paramzzbsq, paramzzbsd1);
      zzbsq localzzbsq;
      this.zzcob = localzzbsq;
    }
    
    private String zze(zzbrr paramzzbrr)
    {
      if (paramzzbrr.zzabx())
      {
        paramzzbrr = paramzzbrr.zzabB();
        if (paramzzbrr.zzabE()) {
          return String.valueOf(paramzzbrr.zzabt());
        }
        if (paramzzbrr.zzabD()) {
          return Boolean.toString(paramzzbrr.getAsBoolean());
        }
        if (paramzzbrr.zzabF()) {
          return paramzzbrr.zzabu();
        }
        throw new AssertionError();
      }
      if (paramzzbrr.zzaby()) {
        return "null";
      }
      throw new AssertionError();
    }
    
    public void zza(zzbtk paramzzbtk, Map<K, V> paramMap)
      throws IOException
    {
      int m = 0;
      int k = 0;
      if (paramMap == null)
      {
        paramzzbtk.zzaca();
        return;
      }
      if (!zzbta.zza(zzbta.this))
      {
        paramzzbtk.zzabY();
        paramMap = paramMap.entrySet().iterator();
        while (paramMap.hasNext())
        {
          localObject = (Map.Entry)paramMap.next();
          paramzzbtk.zzjW(String.valueOf(((Map.Entry)localObject).getKey()));
          this.zzcol.zza(paramzzbtk, ((Map.Entry)localObject).getValue());
        }
        paramzzbtk.zzabZ();
        return;
      }
      Object localObject = new ArrayList(paramMap.size());
      ArrayList localArrayList = new ArrayList(paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      int i = 0;
      if (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        zzbrr localzzbrr = this.zzcok.zzaL(localEntry.getKey());
        ((List)localObject).add(localzzbrr);
        localArrayList.add(localEntry.getValue());
        if ((localzzbrr.zzabv()) || (localzzbrr.zzabw())) {}
        for (int j = 1;; j = 0)
        {
          i = j | i;
          break;
        }
      }
      if (i != 0)
      {
        paramzzbtk.zzabW();
        i = k;
        while (i < ((List)localObject).size())
        {
          paramzzbtk.zzabW();
          zzbss.zzb((zzbrr)((List)localObject).get(i), paramzzbtk);
          this.zzcol.zza(paramzzbtk, localArrayList.get(i));
          paramzzbtk.zzabX();
          i += 1;
        }
        paramzzbtk.zzabX();
        return;
      }
      paramzzbtk.zzabY();
      i = m;
      while (i < ((List)localObject).size())
      {
        paramzzbtk.zzjW(zze((zzbrr)((List)localObject).get(i)));
        this.zzcol.zza(paramzzbtk, localArrayList.get(i));
        i += 1;
      }
      paramzzbtk.zzabZ();
    }
    
    public Map<K, V> zzl(zzbti paramzzbti)
      throws IOException
    {
      Object localObject = paramzzbti.zzabQ();
      if (localObject == zzbtj.zzcqa)
      {
        paramzzbti.nextNull();
        return null;
      }
      Map localMap = (Map)this.zzcob.zzabJ();
      if (localObject == zzbtj.zzcpS)
      {
        paramzzbti.beginArray();
        while (paramzzbti.hasNext())
        {
          paramzzbti.beginArray();
          localObject = this.zzcok.zzb(paramzzbti);
          if (localMap.put(localObject, this.zzcol.zzb(paramzzbti)) != null)
          {
            paramzzbti = String.valueOf(localObject);
            throw new zzbsa(String.valueOf(paramzzbti).length() + 15 + "duplicate key: " + paramzzbti);
          }
          paramzzbti.endArray();
        }
        paramzzbti.endArray();
        return localMap;
      }
      paramzzbti.beginObject();
      while (paramzzbti.hasNext())
      {
        zzbsn.zzcny.zzi(paramzzbti);
        localObject = this.zzcok.zzb(paramzzbti);
        if (localMap.put(localObject, this.zzcol.zzb(paramzzbti)) != null)
        {
          paramzzbti = String.valueOf(localObject);
          throw new zzbsa(String.valueOf(paramzzbti).length() + 15 + "duplicate key: " + paramzzbti);
        }
      }
      paramzzbti.endObject();
      return localMap;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */