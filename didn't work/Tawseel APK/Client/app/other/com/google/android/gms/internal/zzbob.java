package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class zzbob
{
  private final zzboe zzcfS;
  private final zzboy zzcfT;
  
  public zzbob(zzboe paramzzboe)
  {
    this.zzcfS = paramzzboe;
    this.zzcfT = paramzzboe.zzYz();
  }
  
  private Comparator<zzbny> zzYq()
  {
    new Comparator()
    {
      static
      {
        if (!zzbob.class.desiredAssertionStatus()) {}
        for (boolean bool = true;; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }
      
      public int zza(zzbny paramAnonymouszzbny1, zzbny paramAnonymouszzbny2)
      {
        assert ((paramAnonymouszzbny1.zzYk() != null) && (paramAnonymouszzbny2.zzYk() != null));
        paramAnonymouszzbny1 = new zzbpd(paramAnonymouszzbny1.zzYk(), paramAnonymouszzbny1.zzYi().zzUY());
        paramAnonymouszzbny2 = new zzbpd(paramAnonymouszzbny2.zzYk(), paramAnonymouszzbny2.zzYi().zzUY());
        return zzbob.zza(zzbob.this).compare(paramAnonymouszzbny1, paramAnonymouszzbny2);
      }
    };
  }
  
  private zzbnz zza(zzbny paramzzbny, zzbme paramzzbme, zzboz paramzzboz)
  {
    zzbny localzzbny = paramzzbny;
    if (!paramzzbny.zzYl().equals(zzboa.zza.zzcfQ)) {
      if (!paramzzbny.zzYl().equals(zzboa.zza.zzcfM)) {
        break label43;
      }
    }
    label43:
    for (localzzbny = paramzzbny;; localzzbny = paramzzbny.zzg(paramzzboz.zza(paramzzbny.zzYk(), paramzzbny.zzYi().zzUY(), this.zzcfT))) {
      return paramzzbme.zza(localzzbny, this.zzcfS);
    }
  }
  
  private void zza(List<zzbnz> paramList, zzboa.zza paramzza, List<zzbny> paramList1, List<zzbme> paramList2, zzboz paramzzboz)
  {
    Object localObject1 = new ArrayList();
    paramList1 = paramList1.iterator();
    Object localObject2;
    while (paramList1.hasNext())
    {
      localObject2 = (zzbny)paramList1.next();
      if (((zzbny)localObject2).zzYl().equals(paramzza)) {
        ((List)localObject1).add(localObject2);
      }
    }
    Collections.sort((List)localObject1, zzYq());
    paramList1 = ((List)localObject1).iterator();
    while (paramList1.hasNext())
    {
      localObject1 = (zzbny)paramList1.next();
      localObject2 = paramList2.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        zzbme localzzbme = (zzbme)((Iterator)localObject2).next();
        if (localzzbme.zza(paramzza)) {
          paramList.add(zza((zzbny)localObject1, localzzbme, paramzzboz));
        }
      }
    }
  }
  
  public List<zzbnz> zza(List<zzbny> paramList, zzboz paramzzboz, List<zzbme> paramList1)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      zzbny localzzbny = (zzbny)localIterator.next();
      if ((localzzbny.zzYl().equals(zzboa.zza.zzcfP)) && (this.zzcfT.zza(localzzbny.zzYn().zzUY(), localzzbny.zzYi().zzUY()))) {
        localArrayList2.add(zzbny.zzc(localzzbny.zzYk(), localzzbny.zzYi()));
      }
    }
    zza(localArrayList1, zzboa.zza.zzcfM, paramList, paramList1, paramzzboz);
    zza(localArrayList1, zzboa.zza.zzcfN, paramList, paramList1, paramzzboz);
    zza(localArrayList1, zzboa.zza.zzcfO, localArrayList2, paramList1, paramzzboz);
    zza(localArrayList1, zzboa.zza.zzcfP, paramList, paramList1, paramzzboz);
    zza(localArrayList1, zzboa.zza.zzcfQ, paramList, paramList1, paramzzboz);
    return localArrayList1;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */