package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class zzbna
{
  private static final zzbnt<zzbmx> zzcer;
  private zzbma zzceo = zzbma.zzWE();
  private List<zzbmx> zzcep = new ArrayList();
  private Long zzceq = Long.valueOf(-1L);
  
  static
  {
    if (!zzbna.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcer = new zzbnt()
      {
        public boolean zza(zzbmx paramAnonymouszzbmx)
        {
          return paramAnonymouszzbmx.isVisible();
        }
      };
      return;
    }
  }
  
  private void zzXJ()
  {
    this.zzceo = zza(this.zzcep, zzcer, zzbmj.zzXf());
    if (this.zzcep.size() > 0)
    {
      this.zzceq = Long.valueOf(((zzbmx)this.zzcep.get(this.zzcep.size() - 1)).zzXC());
      return;
    }
    this.zzceq = Long.valueOf(-1L);
  }
  
  private static zzbma zza(List<zzbmx> paramList, zzbnt<zzbmx> paramzzbnt, zzbmj paramzzbmj)
  {
    Object localObject = zzbma.zzWE();
    Iterator localIterator = paramList.iterator();
    paramList = (List<zzbmx>)localObject;
    zzbmj localzzbmj;
    if (localIterator.hasNext())
    {
      localObject = (zzbmx)localIterator.next();
      if (!paramzzbnt.zzaq(localObject)) {
        break label215;
      }
      localzzbmj = ((zzbmx)localObject).zzVc();
      if (((zzbmx)localObject).zzXF()) {
        if (paramzzbmj.zzi(localzzbmj)) {
          paramList = paramList.zze(zzbmj.zza(paramzzbmj, localzzbmj), ((zzbmx)localObject).zzXD());
        }
      }
    }
    label215:
    for (;;)
    {
      break;
      if (localzzbmj.zzi(paramzzbmj))
      {
        paramList = paramList.zze(zzbmj.zzXf(), ((zzbmx)localObject).zzXD().zzO(zzbmj.zza(localzzbmj, paramzzbmj)));
        continue;
        if (paramzzbmj.zzi(localzzbmj))
        {
          paramList = paramList.zzb(zzbmj.zza(paramzzbmj, localzzbmj), ((zzbmx)localObject).zzXE());
        }
        else if (localzzbmj.zzi(paramzzbmj))
        {
          localzzbmj = zzbmj.zza(localzzbmj, paramzzbmj);
          if (localzzbmj.isEmpty())
          {
            paramList = paramList.zzb(zzbmj.zzXf(), ((zzbmx)localObject).zzXE());
          }
          else
          {
            localObject = ((zzbmx)localObject).zzXE().zzf(localzzbmj);
            if (localObject != null)
            {
              paramList = paramList.zze(zzbmj.zzXf(), (zzbpe)localObject);
              continue;
              return paramList;
            }
          }
        }
      }
    }
  }
  
  private boolean zza(zzbmx paramzzbmx, zzbmj paramzzbmj)
  {
    if (paramzzbmx.zzXF()) {
      return paramzzbmx.zzVc().zzi(paramzzbmj);
    }
    Iterator localIterator = paramzzbmx.zzXE().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramzzbmx.zzVc().zzh((zzbmj)localEntry.getKey()).zzi(paramzzbmj)) {
        return true;
      }
    }
    return false;
  }
  
  public List<zzbmx> zzXI()
  {
    ArrayList localArrayList = new ArrayList(this.zzcep);
    this.zzceo = zzbma.zzWE();
    this.zzcep = new ArrayList();
    return localArrayList;
  }
  
  public zzbpd zza(zzbmj paramzzbmj, zzbpe paramzzbpe, zzbpd paramzzbpd, boolean paramBoolean, zzboy paramzzboy)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    Object localObject3 = this.zzceo.zzg(paramzzbmj);
    paramzzbmj = ((zzbma)localObject3).zzf(zzbmj.zzXf());
    if (paramzzbmj != null)
    {
      localObject3 = paramzzbmj.iterator();
      paramzzbmj = (zzbmj)localObject2;
      label40:
      localObject1 = paramzzbmj;
      if (!((Iterator)localObject3).hasNext()) {
        break label120;
      }
      localObject1 = (zzbpd)((Iterator)localObject3).next();
      if (paramzzboy.zza((zzbpd)localObject1, paramzzbpd, paramBoolean) <= 0) {
        break label123;
      }
      paramzzbpe = (zzbpe)localObject1;
      if (paramzzbmj != null) {
        if (paramzzboy.zza((zzbpd)localObject1, paramzzbmj, paramBoolean) >= 0) {
          break label123;
        }
      }
    }
    label120:
    label123:
    for (paramzzbpe = (zzbpe)localObject1;; paramzzbpe = paramzzbmj)
    {
      paramzzbmj = paramzzbpe;
      break label40;
      if (paramzzbpe != null)
      {
        paramzzbmj = ((zzbma)localObject3).zzb(paramzzbpe);
        break;
      }
      return (zzbpd)localObject1;
    }
  }
  
  public zzbpe zza(zzbmj paramzzbmj1, zzbmj paramzzbmj2, zzbpe paramzzbpe1, zzbpe paramzzbpe2)
  {
    assert ((paramzzbpe1 != null) || (paramzzbpe2 != null)) : "Either existingEventSnap or existingServerSnap must exist";
    paramzzbmj1 = paramzzbmj1.zzh(paramzzbmj2);
    if (this.zzceo.zze(paramzzbmj1)) {
      return null;
    }
    paramzzbmj1 = this.zzceo.zzg(paramzzbmj1);
    if (paramzzbmj1.isEmpty()) {
      return paramzzbpe2.zzO(paramzzbmj2);
    }
    return paramzzbmj1.zzb(paramzzbpe2.zzO(paramzzbmj2));
  }
  
  public zzbpe zza(zzbmj paramzzbmj, zzbos paramzzbos, zzbnw paramzzbnw)
  {
    paramzzbmj = paramzzbmj.zza(paramzzbos);
    zzbpe localzzbpe = this.zzceo.zzf(paramzzbmj);
    if (localzzbpe != null) {
      return localzzbpe;
    }
    if (paramzzbnw.zzf(paramzzbos)) {
      return this.zzceo.zzg(paramzzbmj).zzb(paramzzbnw.zzUY().zzm(paramzzbos));
    }
    return null;
  }
  
  public zzbpe zza(final zzbmj paramzzbmj, zzbpe paramzzbpe, final List<Long> paramList, final boolean paramBoolean)
  {
    zzbpe localzzbpe;
    if ((paramList.isEmpty()) && (!paramBoolean))
    {
      localzzbpe = this.zzceo.zzf(paramzzbmj);
      if (localzzbpe == null) {}
    }
    zzbma localzzbma;
    do
    {
      do
      {
        return localzzbpe;
        paramzzbmj = this.zzceo.zzg(paramzzbmj);
        localzzbpe = paramzzbpe;
      } while (paramzzbmj.isEmpty());
      if ((paramzzbpe == null) && (!paramzzbmj.zze(zzbmj.zzXf()))) {
        return null;
      }
      if (paramzzbpe != null) {}
      for (;;)
      {
        return paramzzbmj.zzb(paramzzbpe);
        paramzzbpe = zzbox.zzZp();
      }
      localzzbma = this.zzceo.zzg(paramzzbmj);
      if (paramBoolean) {
        break;
      }
      localzzbpe = paramzzbpe;
    } while (localzzbma.isEmpty());
    if ((!paramBoolean) && (paramzzbpe == null) && (!localzzbma.zze(zzbmj.zzXf()))) {
      return null;
    }
    paramList = new zzbnt()
    {
      public boolean zza(zzbmx paramAnonymouszzbmx)
      {
        return ((paramAnonymouszzbmx.isVisible()) || (paramBoolean)) && (!paramList.contains(Long.valueOf(paramAnonymouszzbmx.zzXC()))) && ((paramAnonymouszzbmx.zzVc().zzi(paramzzbmj)) || (paramzzbmj.zzi(paramAnonymouszzbmx.zzVc())));
      }
    };
    paramzzbmj = zza(this.zzcep, paramList, paramzzbmj);
    if (paramzzbpe != null) {}
    for (;;)
    {
      return paramzzbmj.zzb(paramzzbpe);
      paramzzbpe = zzbox.zzZp();
    }
  }
  
  public void zza(zzbmj paramzzbmj, zzbma paramzzbma, Long paramLong)
  {
    assert (paramLong.longValue() > this.zzceq.longValue());
    this.zzcep.add(new zzbmx(paramLong.longValue(), paramzzbmj, paramzzbma));
    this.zzceo = this.zzceo.zzb(paramzzbmj, paramzzbma);
    this.zzceq = paramLong;
  }
  
  public void zza(zzbmj paramzzbmj, zzbpe paramzzbpe, Long paramLong, boolean paramBoolean)
  {
    assert (paramLong.longValue() > this.zzceq.longValue());
    this.zzcep.add(new zzbmx(paramLong.longValue(), paramzzbmj, paramzzbpe, paramBoolean));
    if (paramBoolean) {
      this.zzceo = this.zzceo.zze(paramzzbmj, paramzzbpe);
    }
    this.zzceq = paramLong;
  }
  
  public zzbmx zzaL(long paramLong)
  {
    Iterator localIterator = this.zzcep.iterator();
    while (localIterator.hasNext())
    {
      zzbmx localzzbmx = (zzbmx)localIterator.next();
      if (localzzbmx.zzXC() == paramLong) {
        return localzzbmx;
      }
    }
    return null;
  }
  
  public boolean zzaM(long paramLong)
  {
    boolean bool2 = true;
    Object localObject2 = null;
    Object localObject3 = this.zzcep.iterator();
    int j = 0;
    Object localObject1;
    for (;;)
    {
      localObject1 = localObject2;
      if (((Iterator)localObject3).hasNext())
      {
        localObject1 = (zzbmx)((Iterator)localObject3).next();
        if (((zzbmx)localObject1).zzXC() != paramLong) {}
      }
      else
      {
        if (($assertionsDisabled) || (localObject1 != null)) {
          break;
        }
        throw new AssertionError("removeWrite called with nonexistent writeId");
      }
      j += 1;
    }
    this.zzcep.remove(localObject1);
    boolean bool1 = ((zzbmx)localObject1).isVisible();
    int k = this.zzcep.size() - 1;
    int i = 0;
    if ((bool1) && (k >= 0))
    {
      localObject2 = (zzbmx)this.zzcep.get(k);
      if (!((zzbmx)localObject2).isVisible()) {
        break label323;
      }
      if ((k >= j) && (zza((zzbmx)localObject2, ((zzbmx)localObject1).zzVc()))) {
        bool1 = false;
      }
    }
    label323:
    for (;;)
    {
      k -= 1;
      break;
      if (((zzbmx)localObject1).zzVc().zzi(((zzbmx)localObject2).zzVc()))
      {
        i = 1;
        continue;
        if (!bool1)
        {
          bool1 = false;
          return bool1;
        }
        if (i != 0)
        {
          zzXJ();
          return true;
        }
        if (((zzbmx)localObject1).zzXF())
        {
          this.zzceo = this.zzceo.zzd(((zzbmx)localObject1).zzVc());
          return true;
        }
        localObject2 = ((zzbmx)localObject1).zzXE().iterator();
        for (;;)
        {
          bool1 = bool2;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          localObject3 = (zzbmj)((Map.Entry)((Iterator)localObject2).next()).getKey();
          this.zzceo = this.zzceo.zzd(((zzbmx)localObject1).zzVc().zzh((zzbmj)localObject3));
        }
      }
    }
  }
  
  public zzbpe zzj(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    Object localObject1 = zzbox.zzZp();
    Object localObject2 = this.zzceo.zzf(paramzzbmj);
    if (localObject2 != null)
    {
      if (!((zzbpe)localObject2).zzZd())
      {
        localObject2 = ((zzbpe)localObject2).iterator();
        for (paramzzbpe = (zzbpe)localObject1;; paramzzbpe = paramzzbpe.zze(paramzzbmj.zzZz(), paramzzbmj.zzUY()))
        {
          paramzzbmj = paramzzbpe;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramzzbmj = (zzbpd)((Iterator)localObject2).next();
        }
      }
    }
    else
    {
      localObject2 = this.zzceo.zzg(paramzzbmj);
      paramzzbpe = paramzzbpe.iterator();
      zzbpe localzzbpe;
      for (paramzzbmj = (zzbmj)localObject1; paramzzbpe.hasNext(); paramzzbmj = paramzzbmj.zze(((zzbpd)localObject1).zzZz(), localzzbpe))
      {
        localObject1 = (zzbpd)paramzzbpe.next();
        localzzbpe = ((zzbma)localObject2).zzg(new zzbmj(new zzbos[] { ((zzbpd)localObject1).zzZz() })).zzb(((zzbpd)localObject1).zzUY());
      }
      localObject1 = ((zzbma)localObject2).zzWG().iterator();
      for (paramzzbpe = paramzzbmj;; paramzzbpe = paramzzbpe.zze(paramzzbmj.zzZz(), paramzzbmj.zzUY()))
      {
        paramzzbmj = paramzzbpe;
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        paramzzbmj = (zzbpd)((Iterator)localObject1).next();
      }
    }
    paramzzbmj = (zzbmj)localObject1;
    return paramzzbmj;
  }
  
  public zzbnb zzu(zzbmj paramzzbmj)
  {
    return new zzbnb(paramzzbmj, this);
  }
  
  public zzbpe zzv(zzbmj paramzzbmj)
  {
    return this.zzceo.zzf(paramzzbmj);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbna.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */