package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

public class zzbmt
{
  private final zzbop zzbYx;
  private final zzbnn zzcdB;
  private zzbns<zzbms> zzcdC = zzbns.zzYd();
  private final zzbna zzcdD = new zzbna();
  private final Map<zzbmu, zzboe> zzcdE = new HashMap();
  private final Map<zzboe, zzbmu> zzcdF = new HashMap();
  private final Set<zzboe> zzcdG = new HashSet();
  private final zzd zzcdH;
  private long zzcdI = 1L;
  
  static
  {
    if (!zzbmt.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbmt(zzbmc paramzzbmc, zzbnn paramzzbnn, zzd paramzzd)
  {
    this.zzcdH = paramzzd;
    this.zzcdB = paramzzbnn;
    this.zzbYx = paramzzbmc.zziW("SyncTree");
  }
  
  private void zzX(List<zzboe> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzboe localzzboe = (zzboe)paramList.next();
      if (!localzzboe.zzYD())
      {
        zzbmu localzzbmu = zze(localzzboe);
        assert (localzzbmu != null);
        this.zzcdF.remove(localzzboe);
        this.zzcdE.remove(localzzbmu);
      }
    }
  }
  
  private zzbmu zzXA()
  {
    long l = this.zzcdI;
    this.zzcdI = (1L + l);
    return new zzbmu(l);
  }
  
  private List<zzboa> zza(zzbng paramzzbng)
  {
    return zza(paramzzbng, this.zzcdC, null, this.zzcdD.zzu(zzbmj.zzXf()));
  }
  
  private List<zzboa> zza(zzbng paramzzbng, zzbns<zzbms> paramzzbns, zzbpe paramzzbpe, zzbnb paramzzbnb)
  {
    if (paramzzbng.zzVc().isEmpty()) {
      return zzb(paramzzbng, paramzzbns, paramzzbpe, paramzzbnb);
    }
    zzbms localzzbms = (zzbms)paramzzbns.getValue();
    zzbpe localzzbpe = paramzzbpe;
    if (paramzzbpe == null)
    {
      localzzbpe = paramzzbpe;
      if (localzzbms != null) {
        localzzbpe = localzzbms.zzs(zzbmj.zzXf());
      }
    }
    paramzzbpe = new ArrayList();
    zzbos localzzbos = paramzzbng.zzVc().zzXi();
    zzbng localzzbng = paramzzbng.zzc(localzzbos);
    zzbns localzzbns = (zzbns)paramzzbns.zzYe().get(localzzbos);
    if ((localzzbns != null) && (localzzbng != null)) {
      if (localzzbpe == null) {
        break label165;
      }
    }
    label165:
    for (paramzzbns = localzzbpe.zzm(localzzbos);; paramzzbns = null)
    {
      paramzzbpe.addAll(zza(localzzbng, localzzbns, paramzzbns, paramzzbnb.zzb(localzzbos)));
      if (localzzbms != null) {
        paramzzbpe.addAll(localzzbms.zza(paramzzbng, paramzzbnb, localzzbpe));
      }
      return paramzzbpe;
    }
  }
  
  private List<zzbof> zza(zzbns<zzbms> paramzzbns)
  {
    ArrayList localArrayList = new ArrayList();
    zza(paramzzbns, localArrayList);
    return localArrayList;
  }
  
  private List<? extends zzboa> zza(zzboe paramzzboe, zzbng paramzzbng)
  {
    paramzzboe = paramzzboe.zzVc();
    zzbms localzzbms = (zzbms)this.zzcdC.zzK(paramzzboe);
    assert (localzzbms != null) : "Missing sync point for query tag that we're tracking";
    return localzzbms.zza(paramzzbng, this.zzcdD.zzu(paramzzboe), null);
  }
  
  private void zza(zzbns<zzbms> paramzzbns, List<zzbof> paramList)
  {
    zzbms localzzbms = (zzbms)paramzzbns.getValue();
    if ((localzzbms != null) && (localzzbms.zzXx())) {
      paramList.add(localzzbms.zzXy());
    }
    for (;;)
    {
      return;
      if (localzzbms != null) {
        paramList.addAll(localzzbms.zzXw());
      }
      paramzzbns = paramzzbns.zzYe().iterator();
      while (paramzzbns.hasNext()) {
        zza((zzbns)((Map.Entry)paramzzbns.next()).getValue(), paramList);
      }
    }
  }
  
  private void zza(zzboe paramzzboe, zzbof paramzzbof)
  {
    zzbmj localzzbmj = paramzzboe.zzVc();
    zzbmu localzzbmu = zze(paramzzboe);
    paramzzbof = new zzc(paramzzbof);
    this.zzcdH.zza(zzd(paramzzboe), localzzbmu, paramzzbof, paramzzbof);
    paramzzboe = this.zzcdC.zzI(localzzbmj);
    if (localzzbmu != null)
    {
      if ((!$assertionsDisabled) && (((zzbms)paramzzboe.getValue()).zzXx())) {
        throw new AssertionError("If we're adding a query, it shouldn't be shadowed");
      }
    }
    else {
      paramzzboe.zza(new zzbns.zza()
      {
        public Void zza(zzbmj paramAnonymouszzbmj, zzbms paramAnonymouszzbms, Void paramAnonymousVoid)
        {
          if ((!paramAnonymouszzbmj.isEmpty()) && (paramAnonymouszzbms.zzXx()))
          {
            paramAnonymouszzbmj = paramAnonymouszzbms.zzXy().zzYH();
            zzbmt.zzh(zzbmt.this).zza(zzbmt.zzb(zzbmt.this, paramAnonymouszzbmj), zzbmt.zza(zzbmt.this, paramAnonymouszzbmj));
          }
          for (;;)
          {
            return null;
            paramAnonymouszzbmj = paramAnonymouszzbms.zzXw().iterator();
            while (paramAnonymouszzbmj.hasNext())
            {
              paramAnonymouszzbms = ((zzbof)paramAnonymouszzbmj.next()).zzYH();
              zzbmt.zzh(zzbmt.this).zza(zzbmt.zzb(zzbmt.this, paramAnonymouszzbms), zzbmt.zza(zzbmt.this, paramAnonymouszzbms));
            }
          }
        }
      });
    }
  }
  
  private zzboe zzb(zzbmu paramzzbmu)
  {
    return (zzboe)this.zzcdE.get(paramzzbmu);
  }
  
  private List<zzboa> zzb(final zzbng paramzzbng, zzbns<zzbms> paramzzbns, final zzbpe paramzzbpe, final zzbnb paramzzbnb)
  {
    zzbms localzzbms = (zzbms)paramzzbns.getValue();
    if ((paramzzbpe == null) && (localzzbms != null)) {
      paramzzbpe = localzzbms.zzs(zzbmj.zzXf());
    }
    for (;;)
    {
      final ArrayList localArrayList = new ArrayList();
      paramzzbns.zzYe().zza(new zzblf.zzb()
      {
        public void zza(zzbos paramAnonymouszzbos, zzbns<zzbms> paramAnonymouszzbns)
        {
          zzbpe localzzbpe = null;
          if (paramzzbpe != null) {
            localzzbpe = paramzzbpe.zzm(paramAnonymouszzbos);
          }
          zzbnb localzzbnb = paramzzbnb.zzb(paramAnonymouszzbos);
          paramAnonymouszzbos = paramzzbng.zzc(paramAnonymouszzbos);
          if (paramAnonymouszzbos != null) {
            localArrayList.addAll(zzbmt.zza(zzbmt.this, paramAnonymouszzbos, paramAnonymouszzbns, localzzbpe, localzzbnb));
          }
        }
      });
      if (localzzbms != null) {
        localArrayList.addAll(localzzbms.zza(paramzzbng, paramzzbnb, paramzzbpe));
      }
      return localArrayList;
    }
  }
  
  private List<zzboa> zzb(final zzboe paramzzboe, final zzbme paramzzbme, final DatabaseError paramDatabaseError)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      static
      {
        if (!zzbmt.class.desiredAssertionStatus()) {}
        for (boolean bool = true;; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }
      
      public List<zzboa> zzLO()
      {
        Object localObject4 = paramzzboe.zzVc();
        Object localObject3 = (zzbms)zzbmt.zzd(zzbmt.this).zzK((zzbmj)localObject4);
        Object localObject2 = new ArrayList();
        Object localObject1 = localObject2;
        int k;
        Object localObject5;
        int i;
        if (localObject3 != null) {
          if (!paramzzboe.isDefault())
          {
            localObject1 = localObject2;
            if (!((zzbms)localObject3).zzc(paramzzboe)) {}
          }
          else
          {
            localObject1 = ((zzbms)localObject3).zza(paramzzboe, paramzzbme, paramDatabaseError);
            if (((zzbms)localObject3).isEmpty()) {
              zzbmt.zza(zzbmt.this, zzbmt.zzd(zzbmt.this).zzJ((zzbmj)localObject4));
            }
            localObject3 = (List)((zzbqd)localObject1).getFirst();
            localObject2 = (List)((zzbqd)localObject1).zzZZ();
            localObject1 = ((List)localObject3).iterator();
            k = 0;
            if (((Iterator)localObject1).hasNext())
            {
              localObject5 = (zzboe)((Iterator)localObject1).next();
              zzbmt.zzb(zzbmt.this).zzh(paramzzboe);
              if ((k != 0) || (((zzboe)localObject5).zzYD())) {}
              for (i = 1;; i = 0)
              {
                k = i;
                break;
              }
            }
            localObject1 = zzbmt.zzd(zzbmt.this);
            if ((((zzbns)localObject1).getValue() != null) && (((zzbms)((zzbns)localObject1).getValue()).zzXx()))
            {
              i = 1;
              localObject5 = ((zzbmj)localObject4).iterator();
              label250:
              j = i;
              if (((Iterator)localObject5).hasNext())
              {
                localObject1 = ((zzbns)localObject1).zze((zzbos)((Iterator)localObject5).next());
                if ((i == 0) && ((((zzbns)localObject1).getValue() == null) || (!((zzbms)((zzbns)localObject1).getValue()).zzXx()))) {
                  break label449;
                }
                i = 1;
                label307:
                if (i != 0) {
                  break label609;
                }
                if (!((zzbns)localObject1).isEmpty()) {
                  break label454;
                }
              }
            }
          }
        }
        label449:
        label454:
        label522:
        label609:
        for (int j = i;; j = i)
        {
          if ((k != 0) && (j == 0))
          {
            localObject1 = zzbmt.zzd(zzbmt.this).zzI((zzbmj)localObject4);
            if (!((zzbns)localObject1).isEmpty())
            {
              localObject1 = zzbmt.zzb(zzbmt.this, (zzbns)localObject1).iterator();
              for (;;)
              {
                if (((Iterator)localObject1).hasNext())
                {
                  localObject5 = (zzbof)((Iterator)localObject1).next();
                  localObject4 = new zzbmt.zzc(zzbmt.this, (zzbof)localObject5);
                  localObject5 = ((zzbof)localObject5).zzYH();
                  zzbmt.zzh(zzbmt.this).zza(zzbmt.zzb(zzbmt.this, (zzboe)localObject5), zzbmt.zzc.zza((zzbmt.zzc)localObject4), (zzblq)localObject4, (zzbmt.zza)localObject4);
                  continue;
                  i = 0;
                  break;
                  i = 0;
                  break label307;
                  break label250;
                }
              }
            }
          }
          if ((j == 0) && (!((List)localObject3).isEmpty()) && (paramDatabaseError == null))
          {
            if (k == 0) {
              break label522;
            }
            zzbmt.zzh(zzbmt.this).zza(zzbmt.zzb(zzbmt.this, paramzzboe), null);
          }
          for (;;)
          {
            zzbmt.zza(zzbmt.this, (List)localObject3);
            localObject1 = localObject2;
            return (List<zzboa>)localObject1;
            localObject1 = ((List)localObject3).iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject4 = (zzboe)((Iterator)localObject1).next();
              localObject5 = zzbmt.zza(zzbmt.this, (zzboe)localObject4);
              assert (localObject5 != null);
              zzbmt.zzh(zzbmt.this).zza(zzbmt.zzb(zzbmt.this, (zzboe)localObject4), (zzbmu)localObject5);
            }
          }
        }
      }
    });
  }
  
  private zzboe zzd(zzboe paramzzboe)
  {
    zzboe localzzboe = paramzzboe;
    if (paramzzboe.zzYD())
    {
      localzzboe = paramzzboe;
      if (!paramzzboe.isDefault()) {
        localzzboe = zzboe.zzN(paramzzboe.zzVc());
      }
    }
    return localzzboe;
  }
  
  private zzbmu zze(zzboe paramzzboe)
  {
    return (zzbmu)this.zzcdF.get(paramzzboe);
  }
  
  public boolean isEmpty()
  {
    return this.zzcdC.isEmpty();
  }
  
  public List<? extends zzboa> zzXz()
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
        throws Exception
      {
        zzbmt.zzb(zzbmt.this).zzVh();
        if (zzbmt.zzc(zzbmt.this).zzXI().isEmpty()) {
          return Collections.emptyList();
        }
        zzbns localzzbns = new zzbns(Boolean.valueOf(true));
        return zzbmt.zza(zzbmt.this, new zzbnd(zzbmj.zzXf(), localzzbns, true));
      }
    });
  }
  
  public List<? extends zzboa> zza(final long paramLong, boolean paramBoolean1, final boolean paramBoolean2, final zzbpy paramzzbpy)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        if (paramBoolean2) {
          zzbmt.zzb(zzbmt.this).zzaA(paramLong);
        }
        zzbmx localzzbmx = zzbmt.zzc(zzbmt.this).zzaL(paramLong);
        boolean bool = zzbmt.zzc(zzbmt.this).zzaM(paramLong);
        if ((localzzbmx.isVisible()) && (!paramzzbpy))
        {
          localObject1 = zzbmp.zza(this.zzcdY);
          if (!localzzbmx.zzXF()) {
            break label121;
          }
          localObject1 = zzbmp.zza(localzzbmx.zzXD(), (Map)localObject1);
          zzbmt.zzb(zzbmt.this).zzk(localzzbmx.zzVc(), (zzbpe)localObject1);
        }
        while (!bool)
        {
          return Collections.emptyList();
          label121:
          localObject1 = zzbmp.zza(localzzbmx.zzXE(), (Map)localObject1);
          zzbmt.zzb(zzbmt.this).zzc(localzzbmx.zzVc(), (zzbma)localObject1);
        }
        Object localObject1 = zzbns.zzYd();
        Object localObject2;
        if (localzzbmx.zzXF())
        {
          localObject2 = ((zzbns)localObject1).zzb(zzbmj.zzXf(), Boolean.valueOf(true));
          return zzbmt.zza(zzbmt.this, new zzbnd(localzzbmx.zzVc(), (zzbns)localObject2, paramzzbpy));
        }
        Iterator localIterator = localzzbmx.zzXE().iterator();
        for (;;)
        {
          localObject2 = localObject1;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = ((zzbns)localObject1).zzb((zzbmj)((Map.Entry)localIterator.next()).getKey(), Boolean.valueOf(true));
        }
      }
    });
  }
  
  public List<? extends zzboa> zza(final zzbmj paramzzbmj, final zzbma paramzzbma1, zzbma paramzzbma2, final long paramLong, final boolean paramBoolean)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
        throws Exception
      {
        if (paramBoolean) {
          zzbmt.zzb(zzbmt.this).zza(paramzzbmj, paramzzbma1, paramLong);
        }
        zzbmt.zzc(zzbmt.this).zza(paramzzbmj, this.zzcdW, Long.valueOf(paramLong));
        return zzbmt.zza(zzbmt.this, new zzbnf(zzbnh.zzceJ, paramzzbmj, this.zzcdW));
      }
    });
  }
  
  public List<? extends zzboa> zza(final zzbmj paramzzbmj, final zzbpe paramzzbpe, final zzbmu paramzzbmu)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        zzboe localzzboe = zzbmt.zza(zzbmt.this, paramzzbmu);
        if (localzzboe != null)
        {
          zzbmj localzzbmj = zzbmj.zza(localzzboe.zzVc(), paramzzbmj);
          if (localzzbmj.isEmpty()) {}
          for (Object localObject = localzzboe;; localObject = zzboe.zzN(paramzzbmj))
          {
            zzbmt.zzb(zzbmt.this).zza((zzboe)localObject, paramzzbpe);
            localObject = new zzbni(zzbnh.zzc(localzzboe.zzYG()), localzzbmj, paramzzbpe);
            return zzbmt.zza(zzbmt.this, localzzboe, (zzbng)localObject);
          }
        }
        return Collections.emptyList();
      }
    });
  }
  
  public List<? extends zzboa> zza(final zzbmj paramzzbmj, final zzbpe paramzzbpe1, zzbpe paramzzbpe2, final long paramLong, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    if ((paramBoolean1) || (!paramBoolean2)) {}
    for (boolean bool = true;; bool = false)
    {
      zzbqg.zzb(bool, "We shouldn't be persisting non-visible writes.");
      (List)this.zzcdB.zzf(new Callable()
      {
        public List<? extends zzboa> zzLO()
        {
          if (paramBoolean2) {
            zzbmt.zzb(zzbmt.this).zza(paramzzbmj, paramzzbpe1, paramLong);
          }
          zzbmt.zzc(zzbmt.this).zza(paramzzbmj, paramBoolean1, Long.valueOf(paramLong), this.zzcdM);
          if (!this.zzcdM) {
            return Collections.emptyList();
          }
          return zzbmt.zza(zzbmt.this, new zzbni(zzbnh.zzceJ, paramzzbmj, paramBoolean1));
        }
      });
    }
  }
  
  public List<? extends zzboa> zza(zzbmj paramzzbmj, List<zzbpj> paramList, zzbmu paramzzbmu)
  {
    Object localObject1 = zzb(paramzzbmu);
    if (localObject1 != null)
    {
      assert (paramzzbmj.equals(((zzboe)localObject1).zzVc()));
      Object localObject2 = (zzbms)this.zzcdC.zzK(((zzboe)localObject1).zzVc());
      assert (localObject2 != null) : "Missing sync point for query tag that we're tracking";
      localObject1 = ((zzbms)localObject2).zzb((zzboe)localObject1);
      assert (localObject1 != null) : "Missing view for query tag that we're tracking";
      localObject1 = ((zzbof)localObject1).zzYI();
      localObject2 = paramList.iterator();
      for (paramList = (List<zzbpj>)localObject1; ((Iterator)localObject2).hasNext(); paramList = ((zzbpj)((Iterator)localObject2).next()).zzr(paramList)) {}
      return zza(paramzzbmj, paramList, paramzzbmu);
    }
    return Collections.emptyList();
  }
  
  public List<? extends zzboa> zza(final zzbmj paramzzbmj, final Map<zzbmj, zzbpe> paramMap)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        zzbma localzzbma = zzbma.zzaB(paramMap);
        zzbmt.zzb(zzbmt.this).zzd(paramzzbmj, localzzbma);
        return zzbmt.zza(zzbmt.this, new zzbnf(zzbnh.zzceK, paramzzbmj, localzzbma));
      }
    });
  }
  
  public List<? extends zzboa> zza(final zzbmj paramzzbmj, final Map<zzbmj, zzbpe> paramMap, final zzbmu paramzzbmu)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        zzboe localzzboe = zzbmt.zza(zzbmt.this, paramzzbmu);
        if (localzzboe != null)
        {
          Object localObject = zzbmj.zza(localzzboe.zzVc(), paramzzbmj);
          zzbma localzzbma = zzbma.zzaB(paramMap);
          zzbmt.zzb(zzbmt.this).zzd(paramzzbmj, localzzbma);
          localObject = new zzbnf(zzbnh.zzc(localzzboe.zzYG()), (zzbmj)localObject, localzzbma);
          return zzbmt.zza(zzbmt.this, localzzboe, (zzbng)localObject);
        }
        return Collections.emptyList();
      }
    });
  }
  
  public List<? extends zzboa> zza(final zzbmu paramzzbmu)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        zzboe localzzboe = zzbmt.zza(zzbmt.this, paramzzbmu);
        if (localzzboe != null)
        {
          zzbmt.zzb(zzbmt.this).zzi(localzzboe);
          zzbne localzzbne = new zzbne(zzbnh.zzc(localzzboe.zzYG()), zzbmj.zzXf());
          return zzbmt.zza(zzbmt.this, localzzboe, localzzbne);
        }
        return Collections.emptyList();
      }
    });
  }
  
  public List<zzboa> zza(zzboe paramzzboe, DatabaseError paramDatabaseError)
  {
    return zzb(paramzzboe, null, paramDatabaseError);
  }
  
  public void zza(zzboe paramzzboe, boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.zzcdG.contains(paramzzboe)))
    {
      zzg(new zzb(paramzzboe));
      this.zzcdG.add(paramzzboe);
    }
    while ((paramBoolean) || (!this.zzcdG.contains(paramzzboe))) {
      return;
    }
    zzh(new zzb(paramzzboe));
    this.zzcdG.remove(paramzzboe);
  }
  
  public List<? extends zzboa> zzb(zzbmj paramzzbmj, List<zzbpj> paramList)
  {
    Object localObject = (zzbms)this.zzcdC.zzK(paramzzbmj);
    if (localObject == null) {
      return Collections.emptyList();
    }
    localObject = ((zzbms)localObject).zzXy();
    if (localObject != null)
    {
      localObject = ((zzbof)localObject).zzYI();
      Iterator localIterator = paramList.iterator();
      for (paramList = (List<zzbpj>)localObject; localIterator.hasNext(); paramList = ((zzbpj)localIterator.next()).zzr(paramList)) {}
      return zzi(paramzzbmj, paramList);
    }
    return Collections.emptyList();
  }
  
  public zzbpe zzc(zzbmj paramzzbmj, List<Long> paramList)
  {
    zzbns localzzbns = this.zzcdC;
    localzzbns.getValue();
    zzbpe localzzbpe = null;
    zzbmj localzzbmj1 = zzbmj.zzXf();
    zzbmj localzzbmj2 = paramzzbmj;
    label122:
    label125:
    for (;;)
    {
      Object localObject = localzzbmj2.zzXi();
      localzzbmj2 = localzzbmj2.zzXj();
      localzzbmj1 = localzzbmj1.zza((zzbos)localObject);
      zzbmj localzzbmj3 = zzbmj.zza(localzzbmj1, paramzzbmj);
      if (localObject != null)
      {
        localzzbns = localzzbns.zze((zzbos)localObject);
        localObject = (zzbms)localzzbns.getValue();
        if (localObject == null) {
          break label122;
        }
        localzzbpe = ((zzbms)localObject).zzs(localzzbmj3);
      }
      for (;;)
      {
        if ((!localzzbmj2.isEmpty()) && (localzzbpe == null)) {
          break label125;
        }
        return this.zzcdD.zza(paramzzbmj, localzzbpe, paramList, true);
        localzzbns = zzbns.zzYd();
        break;
      }
    }
  }
  
  public List<? extends zzboa> zzg(final zzbme paramzzbme)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      static
      {
        if (!zzbmt.class.desiredAssertionStatus()) {}
        for (boolean bool = true;; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }
      
      public List<? extends zzboa> zzLO()
      {
        zzboe localzzboe = paramzzbme.zzWD();
        zzbmj localzzbmj = localzzboe.zzVc();
        Object localObject1 = null;
        Object localObject3 = zzbmt.zzd(zzbmt.this);
        Object localObject2 = localzzbmj;
        int i = 0;
        Object localObject4;
        if (!((zzbns)localObject3).isEmpty())
        {
          localObject4 = (zzbms)((zzbns)localObject3).getValue();
          if (localObject4 == null) {
            break label651;
          }
          if (localObject1 != null)
          {
            label60:
            if ((i == 0) && (!((zzbms)localObject4).zzXx())) {
              break label119;
            }
            i = 1;
          }
        }
        label74:
        label119:
        label298:
        label310:
        label322:
        label352:
        label376:
        label548:
        label591:
        label648:
        label651:
        for (;;)
        {
          if (((zzbmj)localObject2).isEmpty()) {}
          for (localObject4 = zzbos.zzjb("");; localObject4 = ((zzbmj)localObject2).zzXi())
          {
            localObject3 = ((zzbns)localObject3).zze((zzbos)localObject4);
            localObject2 = ((zzbmj)localObject2).zzXj();
            break;
            localObject1 = ((zzbms)localObject4).zzs((zzbmj)localObject2);
            break label60;
            i = 0;
            break label74;
          }
          localObject2 = (zzbms)zzbmt.zzd(zzbmt.this).zzK(localzzbmj);
          if (localObject2 == null)
          {
            localObject2 = new zzbms(zzbmt.zzb(zzbmt.this));
            zzbmt.zza(zzbmt.this, zzbmt.zzd(zzbmt.this).zzb(localzzbmj, localObject2));
            zzbmt.zzb(zzbmt.this).zzg(localzzboe);
            if (localObject1 == null) {
              break label322;
            }
          }
          boolean bool;
          for (localObject1 = new zzbnw(zzboz.zza((zzbpe)localObject1, localzzboe.zzYz()), true, false);; localObject1 = localObject3)
          {
            bool = ((zzbms)localObject2).zzc(localzzboe);
            if ((bool) || (localzzboe.zzYD())) {
              break label591;
            }
            if (($assertionsDisabled) || (!zzbmt.zze(zzbmt.this).containsKey(localzzboe))) {
              break label548;
            }
            throw new AssertionError("View does not exist but we have a tag");
            if ((i != 0) || (((zzbms)localObject2).zzXx()))
            {
              i = 1;
              if (localObject1 == null) {
                break label310;
              }
            }
            for (;;)
            {
              break;
              i = 0;
              break label298;
              localObject1 = ((zzbms)localObject2).zzs(zzbmj.zzXf());
            }
            localObject3 = zzbmt.zzb(zzbmt.this).zzf(localzzboe);
            if (!((zzbnw)localObject3).zzYg()) {
              break label352;
            }
          }
          localObject1 = zzbox.zzZp();
          localObject4 = zzbmt.zzd(zzbmt.this).zzI(localzzbmj).zzYe().iterator();
          if (((Iterator)localObject4).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject4).next();
            Object localObject5 = (zzbms)((zzbns)localEntry.getValue()).getValue();
            if (localObject5 == null) {
              break label648;
            }
            localObject5 = ((zzbms)localObject5).zzs(zzbmj.zzXf());
            if (localObject5 == null) {
              break label648;
            }
            localObject1 = ((zzbpe)localObject1).zze((zzbos)localEntry.getKey(), (zzbpe)localObject5);
          }
          for (;;)
          {
            break label376;
            localObject3 = ((zzbnw)localObject3).zzUY().iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject4 = (zzbpd)((Iterator)localObject3).next();
              if (!((zzbpe)localObject1).zzk(((zzbpd)localObject4).zzZz())) {
                localObject1 = ((zzbpe)localObject1).zze(((zzbpd)localObject4).zzZz(), ((zzbpd)localObject4).zzUY());
              }
            }
            localObject1 = new zzbnw(zzboz.zza((zzbpe)localObject1, localzzboe.zzYz()), false, false);
            break;
            localObject3 = zzbmt.zzf(zzbmt.this);
            zzbmt.zze(zzbmt.this).put(localzzboe, localObject3);
            zzbmt.zzg(zzbmt.this).put(localObject3, localzzboe);
            localObject3 = zzbmt.zzc(zzbmt.this).zzu(localzzbmj);
            localObject1 = ((zzbms)localObject2).zza(paramzzbme, (zzbnb)localObject3, (zzbnw)localObject1);
            if ((!bool) && (i == 0))
            {
              localObject2 = ((zzbms)localObject2).zzb(localzzboe);
              zzbmt.zza(zzbmt.this, localzzboe, (zzbof)localObject2);
            }
            return (List<? extends zzboa>)localObject1;
          }
        }
      }
    });
  }
  
  public List<zzboa> zzh(zzbme paramzzbme)
  {
    return zzb(paramzzbme.zzWD(), paramzzbme, null);
  }
  
  public List<? extends zzboa> zzi(final zzbmj paramzzbmj, final zzbpe paramzzbpe)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        zzbmt.zzb(zzbmt.this).zza(zzboe.zzN(paramzzbmj), paramzzbpe);
        return zzbmt.zza(zzbmt.this, new zzbni(zzbnh.zzceK, paramzzbmj, paramzzbpe));
      }
    });
  }
  
  public List<? extends zzboa> zzt(final zzbmj paramzzbmj)
  {
    (List)this.zzcdB.zzf(new Callable()
    {
      public List<? extends zzboa> zzLO()
      {
        zzbmt.zzb(zzbmt.this).zzi(zzboe.zzN(paramzzbmj));
        return zzbmt.zza(zzbmt.this, new zzbne(zzbnh.zzceK, paramzzbmj));
      }
    });
  }
  
  public static abstract interface zza
  {
    public abstract List<? extends zzboa> zzb(DatabaseError paramDatabaseError);
  }
  
  private static class zzb
    extends zzbme
  {
    private zzboe zzcbz;
    
    public zzb(zzboe paramzzboe)
    {
      this.zzcbz = paramzzboe;
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof zzb)) && (((zzb)paramObject).zzcbz.equals(this.zzcbz));
    }
    
    public int hashCode()
    {
      return this.zzcbz.hashCode();
    }
    
    public zzboe zzWD()
    {
      return this.zzcbz;
    }
    
    public zzbme zza(zzboe paramzzboe)
    {
      return new zzb(paramzzboe);
    }
    
    public zzbnz zza(zzbny paramzzbny, zzboe paramzzboe)
    {
      return null;
    }
    
    public void zza(zzbnz paramzzbnz) {}
    
    public void zza(DatabaseError paramDatabaseError) {}
    
    public boolean zza(zzboa.zza paramzza)
    {
      return false;
    }
    
    public boolean zzc(zzbme paramzzbme)
    {
      return paramzzbme instanceof zzb;
    }
  }
  
  private class zzc
    implements zzblq, zzbmt.zza
  {
    private final zzbof zzcea;
    private final zzbmu zzceb;
    
    public zzc(zzbof paramzzbof)
    {
      this.zzcea = paramzzbof;
      this.zzceb = zzbmt.zza(zzbmt.this, paramzzbof.zzYH());
    }
    
    public String zzVM()
    {
      return this.zzcea.zzYI().zzZc();
    }
    
    public boolean zzVN()
    {
      return zzbqb.zzt(this.zzcea.zzYI()) > 1024L;
    }
    
    public zzblk zzVO()
    {
      zzbou localzzbou = zzbou.zzi(this.zzcea.zzYI());
      Object localObject = localzzbou.zzVF();
      ArrayList localArrayList = new ArrayList(((List)localObject).size());
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add(((zzbmj)((Iterator)localObject).next()).zzXh());
      }
      return new zzblk(localArrayList, localzzbou.zzVG());
    }
    
    public List<? extends zzboa> zzb(DatabaseError paramDatabaseError)
    {
      if (paramDatabaseError == null)
      {
        paramDatabaseError = this.zzcea.zzYH();
        if (this.zzceb != null) {
          return zzbmt.this.zza(this.zzceb);
        }
        return zzbmt.this.zzt(paramDatabaseError.zzVc());
      }
      zzbop localzzbop = zzbmt.zza(zzbmt.this);
      String str1 = String.valueOf(this.zzcea.zzYH().zzVc());
      String str2 = String.valueOf(paramDatabaseError.toString());
      localzzbop.warn(String.valueOf(str1).length() + 19 + String.valueOf(str2).length() + "Listen at " + str1 + " failed: " + str2);
      return zzbmt.this.zza(this.zzcea.zzYH(), paramDatabaseError);
    }
  }
  
  public static abstract interface zzd
  {
    public abstract void zza(zzboe paramzzboe, zzbmu paramzzbmu);
    
    public abstract void zza(zzboe paramzzboe, zzbmu paramzzbmu, zzblq paramzzblq, zzbmt.zza paramzza);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */