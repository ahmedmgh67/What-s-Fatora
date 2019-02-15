package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

public class zzbnk
  implements zzbnn
{
  private final zzbop zzbYx;
  private final zzbno zzceT;
  private final zzbnr zzceU;
  private final zzbnj zzceV;
  private long zzceW = 0L;
  
  static
  {
    if (!zzbnk.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbnk(zzbmc paramzzbmc, zzbno paramzzbno, zzbnj paramzzbnj)
  {
    this(paramzzbmc, paramzzbno, paramzzbnj, new zzbpz());
  }
  
  public zzbnk(zzbmc paramzzbmc, zzbno paramzzbno, zzbnj paramzzbnj, zzbpy paramzzbpy)
  {
    this.zzceT = paramzzbno;
    this.zzbYx = paramzzbmc.zziW("Persistence");
    this.zzceU = new zzbnr(this.zzceT, this.zzbYx, paramzzbpy);
    this.zzceV = paramzzbnj;
  }
  
  private void zzXX()
  {
    this.zzceW += 1L;
    if (this.zzceV.zzaN(this.zzceW))
    {
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Reached prune check threshold.", new Object[0]);
      }
      this.zzceW = 0L;
      int j = 1;
      long l2 = this.zzceT.zzVf();
      int i = j;
      long l1 = l2;
      if (this.zzbYx.zzYT())
      {
        this.zzbYx.zzi(32 + "Cache size: " + l2, new Object[0]);
        l1 = l2;
        i = j;
      }
      if ((i != 0) && (this.zzceV.zzi(l1, this.zzceU.zzYb())))
      {
        zzbnp localzzbnp = this.zzceU.zza(this.zzceV);
        if (localzzbnp.zzXY()) {
          this.zzceT.zza(zzbmj.zzXf(), localzzbnp);
        }
        for (j = i;; j = 0)
        {
          l2 = this.zzceT.zzVf();
          i = j;
          l1 = l2;
          if (!this.zzbYx.zzYT()) {
            break;
          }
          this.zzbYx.zzi(44 + "Cache size after prune: " + l2, new Object[0]);
          i = j;
          l1 = l2;
          break;
        }
      }
    }
  }
  
  public List<zzbmx> zzVe()
  {
    return this.zzceT.zzVe();
  }
  
  public void zzVh()
  {
    this.zzceT.zzVh();
  }
  
  public void zza(zzbmj paramzzbmj, zzbma paramzzbma, long paramLong)
  {
    this.zzceT.zza(paramzzbmj, paramzzbma, paramLong);
  }
  
  public void zza(zzbmj paramzzbmj, zzbpe paramzzbpe, long paramLong)
  {
    this.zzceT.zza(paramzzbmj, paramzzbpe, paramLong);
  }
  
  public void zza(zzboe paramzzboe, zzbpe paramzzbpe)
  {
    if (paramzzboe.zzYD()) {
      this.zzceT.zza(paramzzboe.zzVc(), paramzzbpe);
    }
    for (;;)
    {
      zzi(paramzzboe);
      zzXX();
      return;
      this.zzceT.zzb(paramzzboe.zzVc(), paramzzbpe);
    }
  }
  
  public void zza(zzboe paramzzboe, Set<zzbos> paramSet)
  {
    assert (!paramzzboe.zzYD()) : "We should only track keys for filtered queries.";
    paramzzboe = this.zzceU.zzl(paramzzboe);
    assert ((paramzzboe != null) && (paramzzboe.zzcfh)) : "We only expect tracked keys for currently-active queries.";
    this.zzceT.zza(paramzzboe.id, paramSet);
  }
  
  public void zza(zzboe paramzzboe, Set<zzbos> paramSet1, Set<zzbos> paramSet2)
  {
    assert (!paramzzboe.zzYD()) : "We should only track keys for filtered queries.";
    paramzzboe = this.zzceU.zzl(paramzzboe);
    assert ((paramzzboe != null) && (paramzzboe.zzcfh)) : "We only expect tracked keys for currently-active queries.";
    this.zzceT.zza(paramzzboe.id, paramSet1, paramSet2);
  }
  
  public void zzaA(long paramLong)
  {
    this.zzceT.zzaA(paramLong);
  }
  
  public void zzc(zzbmj paramzzbmj, zzbma paramzzbma)
  {
    paramzzbma = paramzzbma.iterator();
    while (paramzzbma.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramzzbma.next();
      zzk(paramzzbmj.zzh((zzbmj)localEntry.getKey()), (zzbpe)localEntry.getValue());
    }
  }
  
  public void zzd(zzbmj paramzzbmj, zzbma paramzzbma)
  {
    this.zzceT.zza(paramzzbmj, paramzzbma);
    zzXX();
  }
  
  public zzbnw zzf(zzboe paramzzboe)
  {
    Object localObject1;
    if (this.zzceU.zzo(paramzzboe))
    {
      localObject1 = this.zzceU.zzl(paramzzboe);
      if ((!paramzzboe.zzYD()) && (localObject1 != null) && (((zzbnq)localObject1).zzcfg)) {
        localObject1 = this.zzceT.zzaD(((zzbnq)localObject1).id);
      }
    }
    zzbpe localzzbpe;
    for (boolean bool = true;; bool = false)
    {
      localzzbpe = this.zzceT.zza(paramzzboe.zzVc());
      if (localObject1 == null) {
        break label172;
      }
      Object localObject2 = zzbox.zzZp();
      Iterator localIterator = ((Set)localObject1).iterator();
      for (localObject1 = localObject2; localIterator.hasNext(); localObject1 = ((zzbpe)localObject1).zze((zzbos)localObject2, localzzbpe.zzm((zzbos)localObject2))) {
        localObject2 = (zzbos)localIterator.next();
      }
      localObject1 = null;
      break;
      localObject1 = this.zzceU.zzB(paramzzboe.zzVc());
    }
    return new zzbnw(zzboz.zza((zzbpe)localObject1, paramzzboe.zzYz()), bool, true);
    label172:
    return new zzbnw(zzboz.zza(localzzbpe, paramzzboe.zzYz()), true, false);
  }
  
  public <T> T zzf(Callable<T> paramCallable)
  {
    this.zzceT.beginTransaction();
    try
    {
      paramCallable = paramCallable.call();
      this.zzceT.setTransactionSuccessful();
      return paramCallable;
    }
    catch (Throwable paramCallable)
    {
      throw new RuntimeException(paramCallable);
    }
    finally
    {
      this.zzceT.endTransaction();
    }
  }
  
  public void zzg(zzboe paramzzboe)
  {
    this.zzceU.zzg(paramzzboe);
  }
  
  public void zzh(zzboe paramzzboe)
  {
    this.zzceU.zzh(paramzzboe);
  }
  
  public void zzi(zzboe paramzzboe)
  {
    if (paramzzboe.zzYD())
    {
      this.zzceU.zzA(paramzzboe.zzVc());
      return;
    }
    this.zzceU.zzn(paramzzboe);
  }
  
  public void zzk(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    if (!this.zzceU.zzD(paramzzbmj))
    {
      this.zzceT.zza(paramzzbmj, paramzzbpe);
      this.zzceU.zzC(paramzzbmj);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */