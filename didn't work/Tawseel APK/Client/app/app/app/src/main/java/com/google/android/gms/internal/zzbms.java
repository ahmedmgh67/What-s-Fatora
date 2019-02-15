package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbms
{
  private final Map<zzbod, zzbof> zzcdA = new HashMap();
  private final zzbnn zzcdB;
  
  static
  {
    if (!zzbms.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbms(zzbnn paramzzbnn)
  {
    this.zzcdB = paramzzbnn;
  }
  
  private List<zzbnz> zza(zzbof paramzzbof, zzbng paramzzbng, zzbnb paramzzbnb, zzbpe paramzzbpe)
  {
    paramzzbng = paramzzbof.zzb(paramzzbng, paramzzbnb, paramzzbpe);
    if (!paramzzbof.zzYH().zzYD())
    {
      paramzzbnb = new HashSet();
      paramzzbpe = new HashSet();
      Iterator localIterator = paramzzbng.zzcgo.iterator();
      while (localIterator.hasNext())
      {
        zzbny localzzbny = (zzbny)localIterator.next();
        zzboa.zza localzza = localzzbny.zzYl();
        if (localzza == zzboa.zza.zzcfN) {
          paramzzbpe.add(localzzbny.zzYk());
        } else if (localzza == zzboa.zza.zzcfM) {
          paramzzbnb.add(localzzbny.zzYk());
        }
      }
      if ((!paramzzbpe.isEmpty()) || (!paramzzbnb.isEmpty())) {
        this.zzcdB.zza(paramzzbof.zzYH(), paramzzbpe, paramzzbnb);
      }
    }
    return paramzzbng.zzcgn;
  }
  
  public boolean isEmpty()
  {
    return this.zzcdA.isEmpty();
  }
  
  public List<zzbof> zzXw()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzcdA.entrySet().iterator();
    while (localIterator.hasNext())
    {
      zzbof localzzbof = (zzbof)((Map.Entry)localIterator.next()).getValue();
      if (!localzzbof.zzYH().zzYD()) {
        localArrayList.add(localzzbof);
      }
    }
    return localArrayList;
  }
  
  public boolean zzXx()
  {
    return zzXy() != null;
  }
  
  public zzbof zzXy()
  {
    Iterator localIterator = this.zzcdA.entrySet().iterator();
    while (localIterator.hasNext())
    {
      zzbof localzzbof = (zzbof)((Map.Entry)localIterator.next()).getValue();
      if (localzzbof.zzYH().zzYD()) {
        return localzzbof;
      }
    }
    return null;
  }
  
  public zzbqd<List<zzboe>, List<zzboa>> zza(zzboe paramzzboe, zzbme paramzzbme, DatabaseError paramDatabaseError)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    boolean bool = zzXx();
    if (paramzzboe.isDefault())
    {
      localObject = this.zzcdA.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        zzbof localzzbof = (zzbof)((Map.Entry)((Iterator)localObject).next()).getValue();
        localArrayList2.addAll(localzzbof.zza(paramzzbme, paramDatabaseError));
        if (localzzbof.isEmpty())
        {
          ((Iterator)localObject).remove();
          if (!localzzbof.zzYH().zzYD()) {
            localArrayList1.add(localzzbof.zzYH());
          }
        }
      }
    }
    Object localObject = (zzbof)this.zzcdA.get(paramzzboe.zzYG());
    if (localObject != null)
    {
      localArrayList2.addAll(((zzbof)localObject).zza(paramzzbme, paramDatabaseError));
      if (((zzbof)localObject).isEmpty())
      {
        this.zzcdA.remove(paramzzboe.zzYG());
        if (!((zzbof)localObject).zzYH().zzYD()) {
          localArrayList1.add(((zzbof)localObject).zzYH());
        }
      }
    }
    if ((bool) && (!zzXx())) {
      localArrayList1.add(zzboe.zzN(paramzzboe.zzVc()));
    }
    return new zzbqd(localArrayList1, localArrayList2);
  }
  
  public List<zzbnz> zza(zzbme paramzzbme, zzbnb paramzzbnb, zzbnw paramzzbnw)
  {
    zzboe localzzboe = paramzzbme.zzWD();
    zzbof localzzbof = (zzbof)this.zzcdA.get(localzzboe.zzYG());
    Object localObject = localzzbof;
    if (localzzbof == null)
    {
      boolean bool;
      if (paramzzbnw.zzYg())
      {
        localObject = paramzzbnw.zzUY();
        localObject = paramzzbnb.zzc((zzbpe)localObject);
        if (localObject == null) {
          break label168;
        }
        bool = true;
        paramzzbnb = (zzbnb)localObject;
      }
      for (;;)
      {
        localObject = new zzbof(localzzboe, new zzbog(new zzbnw(zzboz.zza(paramzzbnb, localzzboe.zzYz()), bool, false), paramzzbnw));
        if (localzzboe.zzYD()) {
          break label195;
        }
        paramzzbnb = new HashSet();
        paramzzbnw = ((zzbof)localObject).zzYJ().iterator();
        while (paramzzbnw.hasNext()) {
          paramzzbnb.add(((zzbpd)paramzzbnw.next()).zzZz());
        }
        localObject = null;
        break;
        label168:
        paramzzbnb = paramzzbnb.zzd(paramzzbnw.zzUY());
        bool = false;
      }
      this.zzcdB.zza(localzzboe, paramzzbnb);
      label195:
      this.zzcdA.put(localzzboe.zzYG(), localObject);
    }
    ((zzbof)localObject).zzb(paramzzbme);
    return ((zzbof)localObject).zzl(paramzzbme);
  }
  
  public List<zzbnz> zza(zzbng paramzzbng, zzbnb paramzzbnb, zzbpe paramzzbpe)
  {
    Object localObject = paramzzbng.zzXO().zzXT();
    if (localObject != null)
    {
      localObject = (zzbof)this.zzcdA.get(localObject);
      assert (localObject != null);
      return zza((zzbof)localObject, paramzzbng, paramzzbnb, paramzzbpe);
    }
    localObject = new ArrayList();
    Iterator localIterator = this.zzcdA.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((List)localObject).addAll(zza((zzbof)((Map.Entry)localIterator.next()).getValue(), paramzzbng, paramzzbnb, paramzzbpe));
    }
    return (List<zzbnz>)localObject;
  }
  
  public zzbof zzb(zzboe paramzzboe)
  {
    if (paramzzboe.zzYD()) {
      return zzXy();
    }
    return (zzbof)this.zzcdA.get(paramzzboe.zzYG());
  }
  
  public boolean zzc(zzboe paramzzboe)
  {
    return zzb(paramzzboe) != null;
  }
  
  public zzbpe zzs(zzbmj paramzzbmj)
  {
    Iterator localIterator = this.zzcdA.values().iterator();
    while (localIterator.hasNext())
    {
      zzbof localzzbof = (zzbof)localIterator.next();
      if (localzzbof.zzs(paramzzbmj) != null) {
        return localzzbof.zzs(paramzzbmj);
      }
    }
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbms.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */