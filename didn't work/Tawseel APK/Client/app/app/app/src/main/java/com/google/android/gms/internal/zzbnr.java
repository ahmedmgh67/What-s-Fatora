package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbnr
{
  private static final zzbnt<Map<zzbod, zzbnq>> zzcfi;
  private static final zzbnt<Map<zzbod, zzbnq>> zzcfj;
  private static final zzbnt<zzbnq> zzcfk;
  private static final zzbnt<zzbnq> zzcfl;
  private final zzbop zzbYx;
  private final zzbno zzceT;
  private zzbns<Map<zzbod, zzbnq>> zzcfm;
  private final zzbpy zzcfn;
  private long zzcfo = 0L;
  
  static
  {
    if (!zzbnr.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcfi = new zzbnt()
      {
        public boolean zzaC(Map<zzbod, zzbnq> paramAnonymousMap)
        {
          paramAnonymousMap = (zzbnq)paramAnonymousMap.get(zzbod.zzcfX);
          return (paramAnonymousMap != null) && (paramAnonymousMap.zzcfg);
        }
      };
      zzcfj = new zzbnt()
      {
        public boolean zzaC(Map<zzbod, zzbnq> paramAnonymousMap)
        {
          paramAnonymousMap = (zzbnq)paramAnonymousMap.get(zzbod.zzcfX);
          return (paramAnonymousMap != null) && (paramAnonymousMap.zzcfh);
        }
      };
      zzcfk = new zzbnt()
      {
        public boolean zzc(zzbnq paramAnonymouszzbnq)
        {
          return !paramAnonymouszzbnq.zzcfh;
        }
      };
      zzcfl = new zzbnt()
      {
        public boolean zzc(zzbnq paramAnonymouszzbnq)
        {
          return !zzbnr.zzYc().zzaq(paramAnonymouszzbnq);
        }
      };
      return;
    }
  }
  
  public zzbnr(zzbno paramzzbno, zzbop paramzzbop, zzbpy paramzzbpy)
  {
    this.zzceT = paramzzbno;
    this.zzbYx = paramzzbop;
    this.zzcfn = paramzzbpy;
    this.zzcfm = new zzbns(null);
    zzYa();
    paramzzbno = this.zzceT.zzVg().iterator();
    while (paramzzbno.hasNext())
    {
      paramzzbop = (zzbnq)paramzzbno.next();
      this.zzcfo = Math.max(paramzzbop.id + 1L, this.zzcfo);
      zzb(paramzzbop);
    }
  }
  
  private boolean zzE(zzbmj paramzzbmj)
  {
    return this.zzcfm.zza(paramzzbmj, zzcfi) != null;
  }
  
  private Set<Long> zzF(zzbmj paramzzbmj)
  {
    HashSet localHashSet = new HashSet();
    paramzzbmj = (Map)this.zzcfm.zzK(paramzzbmj);
    if (paramzzbmj != null)
    {
      paramzzbmj = paramzzbmj.values().iterator();
      while (paramzzbmj.hasNext())
      {
        zzbnq localzzbnq = (zzbnq)paramzzbmj.next();
        if (!localzzbnq.zzcfe.zzYD()) {
          localHashSet.add(Long.valueOf(localzzbnq.id));
        }
      }
    }
    return localHashSet;
  }
  
  private void zzYa()
  {
    try
    {
      this.zzceT.beginTransaction();
      this.zzceT.zzaC(this.zzcfn.zzZY());
      this.zzceT.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.zzceT.endTransaction();
    }
  }
  
  private static long zza(zzbnj paramzzbnj, long paramLong)
  {
    return paramLong - Math.min(Math.floor((1.0F - paramzzbnj.zzXV()) * (float)paramLong), paramzzbnj.zzXW());
  }
  
  private List<zzbnq> zza(zzbnt<zzbnq> paramzzbnt)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.zzcfm.iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((Map)((Map.Entry)localIterator1.next()).getValue()).values().iterator();
      while (localIterator2.hasNext())
      {
        zzbnq localzzbnq = (zzbnq)localIterator2.next();
        if (paramzzbnt.zzaq(localzzbnq)) {
          localArrayList.add(localzzbnq);
        }
      }
    }
    return localArrayList;
  }
  
  private void zza(zzbnq paramzzbnq)
  {
    zzb(paramzzbnq);
    this.zzceT.zza(paramzzbnq);
  }
  
  private void zzb(zzbnq paramzzbnq)
  {
    zzj(paramzzbnq.zzcfe);
    Object localObject = (Map)this.zzcfm.zzK(paramzzbnq.zzcfe.zzVc());
    if (localObject == null)
    {
      localObject = new HashMap();
      this.zzcfm = this.zzcfm.zzb(paramzzbnq.zzcfe.zzVc(), localObject);
    }
    for (;;)
    {
      zzbnq localzzbnq = (zzbnq)((Map)localObject).get(paramzzbnq.zzcfe.zzYG());
      if ((localzzbnq == null) || (localzzbnq.id == paramzzbnq.id)) {}
      for (boolean bool = true;; bool = false)
      {
        zzbqg.zzaW(bool);
        ((Map)localObject).put(paramzzbnq.zzcfe.zzYG(), paramzzbnq);
        return;
      }
    }
  }
  
  private void zzb(zzboe paramzzboe, boolean paramBoolean)
  {
    paramzzboe = zzk(paramzzboe);
    zzbnq localzzbnq = zzl(paramzzboe);
    long l1 = this.zzcfn.zzZY();
    if (localzzbnq != null) {}
    long l2;
    for (paramzzboe = localzzbnq.zzaO(l1).zzbb(paramBoolean);; paramzzboe = new zzbnq(l2, paramzzboe, l1, false, paramBoolean))
    {
      zza(paramzzboe);
      return;
      assert (paramBoolean) : "If we're setting the query to inactive, we should already be tracking it!";
      l2 = this.zzcfo;
      this.zzcfo = (1L + l2);
    }
  }
  
  private static void zzj(zzboe paramzzboe)
  {
    if ((!paramzzboe.zzYD()) || (paramzzboe.isDefault())) {}
    for (boolean bool = true;; bool = false)
    {
      zzbqg.zzb(bool, "Can't have tracked non-default query that loads all data");
      return;
    }
  }
  
  private static zzboe zzk(zzboe paramzzboe)
  {
    zzboe localzzboe = paramzzboe;
    if (paramzzboe.zzYD()) {
      localzzboe = zzboe.zzN(paramzzboe.zzVc());
    }
    return localzzboe;
  }
  
  public void zzA(zzbmj paramzzbmj)
  {
    this.zzcfm.zzI(paramzzbmj).zza(new zzbns.zza()
    {
      public Void zza(zzbmj paramAnonymouszzbmj, Map<zzbod, zzbnq> paramAnonymousMap, Void paramAnonymousVoid)
      {
        paramAnonymouszzbmj = paramAnonymousMap.entrySet().iterator();
        while (paramAnonymouszzbmj.hasNext())
        {
          paramAnonymousMap = (zzbnq)((Map.Entry)paramAnonymouszzbmj.next()).getValue();
          if (!paramAnonymousMap.zzcfg) {
            zzbnr.zza(zzbnr.this, paramAnonymousMap.zzXZ());
          }
        }
        return null;
      }
    });
  }
  
  public Set<zzbos> zzB(zzbmj paramzzbmj)
  {
    assert (!zzo(zzboe.zzN(paramzzbmj))) : "Path is fully complete.";
    HashSet localHashSet = new HashSet();
    Object localObject1 = zzF(paramzzbmj);
    if (!((Set)localObject1).isEmpty()) {
      localHashSet.addAll(this.zzceT.zzg((Set)localObject1));
    }
    paramzzbmj = this.zzcfm.zzI(paramzzbmj).zzYe().iterator();
    while (paramzzbmj.hasNext())
    {
      Object localObject2 = (Map.Entry)paramzzbmj.next();
      localObject1 = (zzbos)((Map.Entry)localObject2).getKey();
      localObject2 = (zzbns)((Map.Entry)localObject2).getValue();
      if ((((zzbns)localObject2).getValue() != null) && (zzcfi.zzaq((Map)((zzbns)localObject2).getValue()))) {
        localHashSet.add(localObject1);
      }
    }
    return localHashSet;
  }
  
  public void zzC(zzbmj paramzzbmj)
  {
    zzbnq localzzbnq;
    long l;
    if (!zzE(paramzzbmj))
    {
      paramzzbmj = zzboe.zzN(paramzzbmj);
      localzzbnq = zzl(paramzzbmj);
      if (localzzbnq != null) {
        break label64;
      }
      l = this.zzcfo;
      this.zzcfo = (1L + l);
    }
    for (paramzzbmj = new zzbnq(l, paramzzbmj, this.zzcfn.zzZY(), true, false);; paramzzbmj = localzzbnq.zzXZ())
    {
      zza(paramzzbmj);
      return;
      label64:
      assert (!localzzbnq.zzcfg) : "This should have been handled above!";
    }
  }
  
  public boolean zzD(zzbmj paramzzbmj)
  {
    return this.zzcfm.zzb(paramzzbmj, zzcfj) != null;
  }
  
  public long zzYb()
  {
    return zza(zzcfk).size();
  }
  
  public zzbnp zza(zzbnj paramzzbnj)
  {
    Object localObject1 = zza(zzcfk);
    long l = zza(paramzzbnj, ((List)localObject1).size());
    paramzzbnj = new zzbnp();
    Object localObject2;
    if (this.zzbYx.zzYT())
    {
      localObject2 = this.zzbYx;
      i = ((List)localObject1).size();
      ((zzbop)localObject2).zzi(80 + "Pruning old queries.  Prunable: " + i + " Count to prune: " + l, new Object[0]);
    }
    Collections.sort((List)localObject1, new Comparator()
    {
      public int zza(zzbnq paramAnonymouszzbnq1, zzbnq paramAnonymouszzbnq2)
      {
        return zzbqg.zzj(paramAnonymouszzbnq1.zzcff, paramAnonymouszzbnq2.zzcff);
      }
    });
    int i = 0;
    while (i < l)
    {
      localObject2 = (zzbnq)((List)localObject1).get(i);
      paramzzbnj = paramzzbnj.zzy(((zzbnq)localObject2).zzcfe.zzVc());
      zzm(((zzbnq)localObject2).zzcfe);
      i += 1;
    }
    i = (int)l;
    while (i < ((List)localObject1).size())
    {
      paramzzbnj = paramzzbnj.zzz(((zzbnq)((List)localObject1).get(i)).zzcfe.zzVc());
      i += 1;
    }
    localObject1 = zza(zzcfl);
    if (this.zzbYx.zzYT())
    {
      localObject2 = this.zzbYx;
      i = ((List)localObject1).size();
      ((zzbop)localObject2).zzi(31 + "Unprunable queries: " + i, new Object[0]);
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      paramzzbnj = paramzzbnj.zzz(((zzbnq)((Iterator)localObject1).next()).zzcfe.zzVc());
    }
    return paramzzbnj;
  }
  
  public void zzg(zzboe paramzzboe)
  {
    zzb(paramzzboe, true);
  }
  
  public void zzh(zzboe paramzzboe)
  {
    zzb(paramzzboe, false);
  }
  
  public zzbnq zzl(zzboe paramzzboe)
  {
    paramzzboe = zzk(paramzzboe);
    Map localMap = (Map)this.zzcfm.zzK(paramzzboe.zzVc());
    if (localMap != null) {
      return (zzbnq)localMap.get(paramzzboe.zzYG());
    }
    return null;
  }
  
  public void zzm(zzboe paramzzboe)
  {
    paramzzboe = zzk(paramzzboe);
    Object localObject = zzl(paramzzboe);
    assert (localObject != null) : "Query must exist to be removed.";
    this.zzceT.zzaB(((zzbnq)localObject).id);
    localObject = (Map)this.zzcfm.zzK(paramzzboe.zzVc());
    ((Map)localObject).remove(paramzzboe.zzYG());
    if (((Map)localObject).isEmpty()) {
      this.zzcfm = this.zzcfm.zzJ(paramzzboe.zzVc());
    }
  }
  
  public void zzn(zzboe paramzzboe)
  {
    paramzzboe = zzl(zzk(paramzzboe));
    if ((paramzzboe != null) && (!paramzzboe.zzcfg)) {
      zza(paramzzboe.zzXZ());
    }
  }
  
  public boolean zzo(zzboe paramzzboe)
  {
    if (zzE(paramzzboe.zzVc())) {
      return true;
    }
    if (paramzzboe.zzYD()) {
      return false;
    }
    Map localMap = (Map)this.zzcfm.zzK(paramzzboe.zzVc());
    if ((localMap != null) && (localMap.containsKey(paramzzboe.zzYG())) && (((zzbnq)localMap.get(paramzzboe.zzYG())).zzcfg)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */