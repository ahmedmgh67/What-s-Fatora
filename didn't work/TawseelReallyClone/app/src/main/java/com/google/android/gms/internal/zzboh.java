package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzboh
{
  private static zzbol.zza zzcgs;
  private final zzbol zzcgr;
  
  static
  {
    if (!zzboh.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcgs = new zzbol.zza()
      {
        public zzbpd zza(zzboy paramAnonymouszzboy, zzbpd paramAnonymouszzbpd, boolean paramAnonymousBoolean)
        {
          return null;
        }
        
        public zzbpe zzh(zzbos paramAnonymouszzbos)
        {
          return null;
        }
      };
      return;
    }
  }
  
  public zzboh(zzbol paramzzbol)
  {
    this.zzcgr = paramzzbol;
  }
  
  private zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbma paramzzbma, zzbnb paramzzbnb, zzbpe paramzzbpe, zzboi paramzzboi)
  {
    assert (paramzzbma.zzWF() == null) : "Can't have a merge that is an overwrite";
    Object localObject1 = paramzzbma.iterator();
    zzbog localzzbog = paramzzbog;
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      zzbmj localzzbmj = paramzzbmj.zzh((zzbmj)((Map.Entry)localObject2).getKey());
      if (zza(paramzzbog, localzzbmj.zzXi())) {
        localzzbog = zza(localzzbog, localzzbmj, (zzbpe)((Map.Entry)localObject2).getValue(), paramzzbnb, paramzzbpe, paramzzboi);
      }
    }
    paramzzbma = paramzzbma.iterator();
    while (paramzzbma.hasNext())
    {
      localObject1 = (Map.Entry)paramzzbma.next();
      localObject2 = paramzzbmj.zzh((zzbmj)((Map.Entry)localObject1).getKey());
      if (!zza(paramzzbog, ((zzbmj)localObject2).zzXi())) {
        localzzbog = zza(localzzbog, (zzbmj)localObject2, (zzbpe)((Map.Entry)localObject1).getValue(), paramzzbnb, paramzzbpe, paramzzboi);
      }
    }
    return localzzbog;
  }
  
  private zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbma paramzzbma, zzbnb paramzzbnb, zzbpe paramzzbpe, boolean paramBoolean, zzboi paramzzboi)
  {
    if ((paramzzbog.zzYM().zzUY().isEmpty()) && (!paramzzbog.zzYM().zzYg())) {
      return paramzzbog;
    }
    assert (paramzzbma.zzWF() == null) : "Can't have a merge that is an overwrite";
    if (paramzzbmj.isEmpty()) {}
    zzbpe localzzbpe1;
    Object localObject1;
    Object localObject3;
    Object localObject2;
    for (;;)
    {
      localzzbpe1 = paramzzbog.zzYM().zzUY();
      paramzzbma = paramzzbma.zzWH();
      localObject1 = paramzzbma.entrySet().iterator();
      paramzzbmj = paramzzbog;
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject1).next();
        localObject2 = (zzbos)((Map.Entry)localObject3).getKey();
        if (localzzbpe1.zzk((zzbos)localObject2))
        {
          zzbpe localzzbpe2 = localzzbpe1.zzm((zzbos)localObject2);
          localObject3 = ((zzbma)((Map.Entry)localObject3).getValue()).zzb(localzzbpe2);
          paramzzbmj = zza(paramzzbmj, new zzbmj(new zzbos[] { localObject2 }), (zzbpe)localObject3, paramzzbnb, paramzzbpe, paramBoolean, paramzzboi);
        }
      }
      paramzzbma = zzbma.zzWE().zzb(paramzzbmj, paramzzbma);
    }
    paramzzbma = paramzzbma.entrySet().iterator();
    label370:
    while (paramzzbma.hasNext())
    {
      localObject2 = (Map.Entry)paramzzbma.next();
      localObject1 = (zzbos)((Map.Entry)localObject2).getKey();
      localObject3 = (zzbma)((Map.Entry)localObject2).getValue();
      if ((!paramzzbog.zzYM().zzf((zzbos)localObject1)) && (((zzbma)localObject3).zzWF() == null)) {}
      for (int i = 1;; i = 0)
      {
        if ((localzzbpe1.zzk((zzbos)localObject1)) || (i != 0)) {
          break label370;
        }
        localObject3 = localzzbpe1.zzm((zzbos)localObject1);
        localObject2 = ((zzbma)((Map.Entry)localObject2).getValue()).zzb((zzbpe)localObject3);
        paramzzbmj = zza(paramzzbmj, new zzbmj(new zzbos[] { localObject1 }), (zzbpe)localObject2, paramzzbnb, paramzzbpe, paramBoolean, paramzzboi);
        break;
      }
    }
    return paramzzbmj;
  }
  
  private zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbnb paramzzbnb, zzbol.zza paramzza, zzboi paramzzboi)
  {
    zzbnw localzzbnw = paramzzbog.zzYK();
    if (paramzzbnb.zzv(paramzzbmj) != null) {
      return paramzzbog;
    }
    if (paramzzbmj.isEmpty())
    {
      assert (paramzzbog.zzYM().zzYg()) : "If change path is empty, we must have complete server data";
      if (paramzzbog.zzYM().zzYh())
      {
        paramzza = paramzzbog.zzYN();
        if ((paramzza instanceof zzbot))
        {
          paramzzbnb = paramzzbnb.zzd(paramzza);
          label80:
          paramzzbnb = zzboz.zza(paramzzbnb, this.zzcgr.zzYz());
          paramzzbnb = this.zzcgr.zza(paramzzbog.zzYK().zzYi(), paramzzbnb, paramzzboi);
          label114:
          if ((!localzzbnw.zzYg()) && (!paramzzbmj.isEmpty())) {
            break label390;
          }
        }
      }
    }
    label381:
    label390:
    for (boolean bool = true;; bool = false)
    {
      return paramzzbog.zza(paramzzbnb, bool, this.zzcgr.zzYQ());
      paramzza = zzbox.zzZp();
      break;
      paramzzbnb = paramzzbnb.zzc(paramzzbog.zzYN());
      break label80;
      zzbos localzzbos = paramzzbmj.zzXi();
      if (localzzbos.zzZa())
      {
        assert (paramzzbmj.size() == 1) : "Can't have a priority with additional path components";
        paramzzbnb = paramzzbnb.zza(paramzzbmj, localzzbnw.zzUY(), paramzzbog.zzYM().zzUY());
        if (paramzzbnb != null)
        {
          paramzzbnb = this.zzcgr.zza(localzzbnw.zzYi(), paramzzbnb);
          break label114;
        }
        paramzzbnb = localzzbnw.zzYi();
        break label114;
      }
      zzbmj localzzbmj = paramzzbmj.zzXj();
      if (localzzbnw.zzf(localzzbos))
      {
        zzbpe localzzbpe = paramzzbog.zzYM().zzUY();
        paramzzbnb = paramzzbnb.zza(paramzzbmj, localzzbnw.zzUY(), localzzbpe);
        if (paramzzbnb != null) {
          paramzzbnb = localzzbnw.zzUY().zzm(localzzbos).zzl(localzzbmj, paramzzbnb);
        }
      }
      for (;;)
      {
        if (paramzzbnb == null) {
          break label381;
        }
        paramzzbnb = this.zzcgr.zza(localzzbnw.zzYi(), localzzbos, paramzzbnb, localzzbmj, paramzza, paramzzboi);
        break;
        paramzzbnb = localzzbnw.zzUY().zzm(localzzbos);
        continue;
        paramzzbnb = paramzzbnb.zza(localzzbos, paramzzbog.zzYM());
      }
      paramzzbnb = localzzbnw.zzYi();
      break label114;
    }
  }
  
  private zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbns<Boolean> paramzzbns, zzbnb paramzzbnb, zzbpe paramzzbpe, zzboi paramzzboi)
  {
    if (paramzzbnb.zzv(paramzzbmj) != null) {}
    boolean bool;
    Object localObject2;
    do
    {
      return paramzzbog;
      bool = paramzzbog.zzYM().zzYh();
      localObject2 = paramzzbog.zzYM();
      if (paramzzbns.getValue() == null) {
        break;
      }
      if (((paramzzbmj.isEmpty()) && (((zzbnw)localObject2).zzYg())) || (((zzbnw)localObject2).zzM(paramzzbmj))) {
        return zza(paramzzbog, paramzzbmj, ((zzbnw)localObject2).zzUY().zzO(paramzzbmj), paramzzbnb, paramzzbpe, bool, paramzzboi);
      }
    } while (!paramzzbmj.isEmpty());
    paramzzbns = zzbma.zzWE();
    Object localObject1 = ((zzbnw)localObject2).zzUY().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzbpd)((Iterator)localObject1).next();
      paramzzbns = paramzzbns.zza(((zzbpd)localObject2).zzZz(), ((zzbpd)localObject2).zzUY());
    }
    return zza(paramzzbog, paramzzbmj, paramzzbns, paramzzbnb, paramzzbpe, bool, paramzzboi);
    localObject1 = zzbma.zzWE();
    Iterator localIterator = paramzzbns.iterator();
    paramzzbns = (zzbns<Boolean>)localObject1;
    while (localIterator.hasNext())
    {
      localObject1 = (zzbmj)((Map.Entry)localIterator.next()).getKey();
      zzbmj localzzbmj = paramzzbmj.zzh((zzbmj)localObject1);
      if (((zzbnw)localObject2).zzM(localzzbmj)) {
        paramzzbns = paramzzbns.zze((zzbmj)localObject1, ((zzbnw)localObject2).zzUY().zzO(localzzbmj));
      }
    }
    return zza(paramzzbog, paramzzbmj, paramzzbns, paramzzbnb, paramzzbpe, bool, paramzzboi);
  }
  
  private zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbpe paramzzbpe1, zzbnb paramzzbnb, zzbpe paramzzbpe2, zzboi paramzzboi)
  {
    zzbnw localzzbnw = paramzzbog.zzYK();
    paramzzbpe2 = new zzb(paramzzbnb, paramzzbog, paramzzbpe2);
    if (paramzzbmj.isEmpty())
    {
      paramzzbmj = zzboz.zza(paramzzbpe1, this.zzcgr.zzYz());
      paramzzbpe1 = paramzzbog.zza(this.zzcgr.zza(paramzzbog.zzYK().zzYi(), paramzzbmj, paramzzboi), true, this.zzcgr.zzYQ());
      return paramzzbpe1;
    }
    zzbos localzzbos = paramzzbmj.zzXi();
    if (localzzbos.zzZa()) {
      return paramzzbog.zza(this.zzcgr.zza(paramzzbog.zzYK().zzYi(), paramzzbpe1), localzzbnw.zzYg(), localzzbnw.zzYh());
    }
    zzbmj localzzbmj = paramzzbmj.zzXj();
    zzbpe localzzbpe = localzzbnw.zzUY().zzm(localzzbos);
    if (localzzbmj.isEmpty()) {
      paramzzbmj = paramzzbpe1;
    }
    for (;;)
    {
      paramzzbpe1 = paramzzbog;
      if (localzzbpe.equals(paramzzbmj)) {
        break;
      }
      return paramzzbog.zza(this.zzcgr.zza(localzzbnw.zzYi(), localzzbos, paramzzbmj, localzzbmj, paramzzbpe2, paramzzboi), localzzbnw.zzYg(), this.zzcgr.zzYQ());
      paramzzbnb = paramzzbpe2.zzh(localzzbos);
      if (paramzzbnb != null)
      {
        if (localzzbmj.zzXl().zzZa())
        {
          paramzzbmj = paramzzbnb;
          if (paramzzbnb.zzO(localzzbmj.zzXk()).isEmpty()) {}
        }
        else
        {
          paramzzbmj = paramzzbnb.zzl(localzzbmj, paramzzbpe1);
        }
      }
      else {
        paramzzbmj = zzbox.zzZp();
      }
    }
  }
  
  private zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbpe paramzzbpe1, zzbnb paramzzbnb, zzbpe paramzzbpe2, boolean paramBoolean, zzboi paramzzboi)
  {
    zzbnw localzzbnw = paramzzbog.zzYM();
    zzbol localzzbol;
    if (paramBoolean)
    {
      localzzbol = this.zzcgr;
      if (!paramzzbmj.isEmpty()) {
        break label123;
      }
      paramzzbpe1 = localzzbol.zza(localzzbnw.zzYi(), zzboz.zza(paramzzbpe1, localzzbol.zzYz()), null);
      label49:
      if ((!localzzbnw.zzYg()) && (!paramzzbmj.isEmpty())) {
        break label331;
      }
    }
    label123:
    label254:
    label331:
    for (paramBoolean = true;; paramBoolean = false)
    {
      paramzzbog = paramzzbog.zzb(paramzzbpe1, paramBoolean, localzzbol.zzYQ());
      Object localObject1 = zza(paramzzbog, paramzzbmj, paramzzbnb, new zzb(paramzzbnb, paramzzbog, paramzzbpe2), paramzzboi);
      Object localObject2;
      do
      {
        return (zzbog)localObject1;
        localzzbol = this.zzcgr.zzYP();
        break;
        if ((localzzbol.zzYQ()) && (!localzzbnw.zzYh()))
        {
          assert (!paramzzbmj.isEmpty()) : "An empty path should have been caught in the other branch";
          localObject1 = paramzzbmj.zzXi();
          localObject2 = paramzzbmj.zzXj();
          paramzzbpe1 = localzzbnw.zzUY().zzm((zzbos)localObject1).zzl((zzbmj)localObject2, paramzzbpe1);
          paramzzbpe1 = localzzbnw.zzYi().zzh((zzbos)localObject1, paramzzbpe1);
          paramzzbpe1 = localzzbol.zza(localzzbnw.zzYi(), paramzzbpe1, null);
          break label49;
        }
        localObject2 = paramzzbmj.zzXi();
        if (localzzbnw.zzM(paramzzbmj)) {
          break label254;
        }
        localObject1 = paramzzbog;
      } while (paramzzbmj.size() > 1);
      localObject1 = paramzzbmj.zzXj();
      paramzzbpe1 = localzzbnw.zzUY().zzm((zzbos)localObject2).zzl((zzbmj)localObject1, paramzzbpe1);
      if (((zzbos)localObject2).zzZa())
      {
        paramzzbpe1 = localzzbol.zza(localzzbnw.zzYi(), paramzzbpe1);
        break label49;
      }
      paramzzbpe1 = localzzbol.zza(localzzbnw.zzYi(), (zzbos)localObject2, paramzzbpe1, (zzbmj)localObject1, zzcgs, null);
      break label49;
    }
  }
  
  private void zza(zzbog paramzzbog1, zzbog paramzzbog2, List<zzbny> paramList)
  {
    paramzzbog2 = paramzzbog2.zzYK();
    if (paramzzbog2.zzYg()) {
      if ((!paramzzbog2.zzUY().zzZd()) && (!paramzzbog2.zzUY().isEmpty())) {
        break label116;
      }
    }
    label116:
    for (int i = 1;; i = 0)
    {
      if ((!paramList.isEmpty()) || (!paramzzbog1.zzYK().zzYg()) || ((i != 0) && (!paramzzbog2.zzUY().equals(paramzzbog1.zzYL()))) || (!paramzzbog2.zzUY().zzZe().equals(paramzzbog1.zzYL().zzZe()))) {
        paramList.add(zzbny.zza(paramzzbog2.zzYi()));
      }
      return;
    }
  }
  
  private static boolean zza(zzbog paramzzbog, zzbos paramzzbos)
  {
    return paramzzbog.zzYK().zzf(paramzzbos);
  }
  
  private zzbog zzb(zzbog paramzzbog, zzbmj paramzzbmj, zzbnb paramzzbnb, zzbpe paramzzbpe, zzboi paramzzboi)
  {
    paramzzbpe = paramzzbog.zzYM();
    zzboz localzzboz = paramzzbpe.zzYi();
    if ((paramzzbpe.zzYg()) || (paramzzbmj.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return zza(paramzzbog.zzb(localzzboz, bool, paramzzbpe.zzYh()), paramzzbmj, paramzzbnb, zzcgs, paramzzboi);
    }
  }
  
  public zzbog zza(zzbog paramzzbog, zzbmj paramzzbmj, zzbnb paramzzbnb, zzbpe paramzzbpe, zzboi paramzzboi)
  {
    if (paramzzbnb.zzv(paramzzbmj) != null) {
      return paramzzbog;
    }
    zzb localzzb = new zzb(paramzzbnb, paramzzbog, paramzzbpe);
    zzboz localzzboz = paramzzbog.zzYK().zzYi();
    if ((paramzzbmj.isEmpty()) || (paramzzbmj.zzXi().zzZa())) {
      if (paramzzbog.zzYM().zzYg())
      {
        paramzzbmj = paramzzbnb.zzc(paramzzbog.zzYN());
        paramzzbmj = zzboz.zza(paramzzbmj, this.zzcgr.zzYz());
        paramzzbmj = this.zzcgr.zza(localzzboz, paramzzbmj, paramzzboi);
        if ((!paramzzbog.zzYM().zzYg()) && (paramzzbnb.zzv(zzbmj.zzXf()) == null)) {
          break label383;
        }
      }
    }
    label383:
    for (boolean bool = true;; bool = false)
    {
      return paramzzbog.zza(paramzzbmj, bool, this.zzcgr.zzYQ());
      paramzzbmj = paramzzbnb.zzd(paramzzbog.zzYM().zzUY());
      break;
      zzbos localzzbos = paramzzbmj.zzXi();
      paramzzbpe = paramzzbnb.zza(localzzbos, paramzzbog.zzYM());
      zzbpe localzzbpe = paramzzbpe;
      if (paramzzbpe == null)
      {
        localzzbpe = paramzzbpe;
        if (paramzzbog.zzYM().zzf(localzzbos)) {
          localzzbpe = localzzboz.zzUY().zzm(localzzbos);
        }
      }
      if (localzzbpe != null) {
        paramzzbpe = this.zzcgr.zza(localzzboz, localzzbos, localzzbpe, paramzzbmj.zzXj(), localzzb, paramzzboi);
      }
      for (;;)
      {
        paramzzbmj = paramzzbpe;
        if (!paramzzbpe.zzUY().isEmpty()) {
          break;
        }
        paramzzbmj = paramzzbpe;
        if (!paramzzbog.zzYM().zzYg()) {
          break;
        }
        localzzbpe = paramzzbnb.zzc(paramzzbog.zzYN());
        paramzzbmj = paramzzbpe;
        if (!localzzbpe.zzZd()) {
          break;
        }
        paramzzbmj = zzboz.zza(localzzbpe, this.zzcgr.zzYz());
        paramzzbmj = this.zzcgr.zza(paramzzbpe, paramzzbmj, paramzzboi);
        break;
        paramzzbpe = localzzboz;
        if (localzzbpe == null)
        {
          paramzzbpe = localzzboz;
          if (paramzzbog.zzYK().zzUY().zzk(localzzbos)) {
            paramzzbpe = this.zzcgr.zza(localzzboz, localzzbos, zzbox.zzZp(), paramzzbmj.zzXj(), localzzb, paramzzboi);
          }
        }
      }
    }
  }
  
  public zza zza(zzbog paramzzbog, zzbng paramzzbng, zzbnb paramzzbnb, zzbpe paramzzbpe)
  {
    zzboi localzzboi = new zzboi();
    switch (2.zzcgt[paramzzbng.zzXP().ordinal()])
    {
    default: 
      paramzzbog = String.valueOf(paramzzbng.zzXP());
      throw new AssertionError(String.valueOf(paramzzbog).length() + 19 + "Unknown operation: " + paramzzbog);
    case 1: 
      paramzzbng = (zzbni)paramzzbng;
      if (paramzzbng.zzXO().zzXQ()) {
        paramzzbng = zza(paramzzbog, paramzzbng.zzVc(), paramzzbng.zzXU(), paramzzbnb, paramzzbpe, localzzboi);
      }
      break;
    }
    for (;;)
    {
      paramzzbnb = new ArrayList(localzzboi.zzYO());
      zza(paramzzbog, paramzzbng, paramzzbnb);
      return new zza(paramzzbng, paramzzbnb);
      assert (paramzzbng.zzXO().zzXR());
      if ((paramzzbng.zzXO().zzXS()) || ((paramzzbog.zzYM().zzYh()) && (!paramzzbng.zzVc().isEmpty()))) {}
      for (boolean bool = true;; bool = false)
      {
        paramzzbng = zza(paramzzbog, paramzzbng.zzVc(), paramzzbng.zzXU(), paramzzbnb, paramzzbpe, bool, localzzboi);
        break;
      }
      paramzzbng = (zzbnf)paramzzbng;
      if (paramzzbng.zzXO().zzXQ())
      {
        paramzzbng = zza(paramzzbog, paramzzbng.zzVc(), paramzzbng.zzXN(), paramzzbnb, paramzzbpe, localzzboi);
      }
      else
      {
        assert (paramzzbng.zzXO().zzXR());
        if ((paramzzbng.zzXO().zzXS()) || (paramzzbog.zzYM().zzYh())) {}
        for (bool = true;; bool = false)
        {
          paramzzbng = zza(paramzzbog, paramzzbng.zzVc(), paramzzbng.zzXN(), paramzzbnb, paramzzbpe, bool, localzzboi);
          break;
        }
        paramzzbng = (zzbnd)paramzzbng;
        if (!paramzzbng.zzXM())
        {
          paramzzbng = zza(paramzzbog, paramzzbng.zzVc(), paramzzbng.zzXL(), paramzzbnb, paramzzbpe, localzzboi);
        }
        else
        {
          paramzzbng = zza(paramzzbog, paramzzbng.zzVc(), paramzzbnb, paramzzbpe, localzzboi);
          continue;
          paramzzbng = zzb(paramzzbog, paramzzbng.zzVc(), paramzzbnb, paramzzbpe, localzzboi);
        }
      }
    }
  }
  
  public static class zza
  {
    public final zzbog zzcgk;
    public final List<zzbny> zzcgo;
    
    public zza(zzbog paramzzbog, List<zzbny> paramList)
    {
      this.zzcgk = paramzzbog;
      this.zzcgo = paramList;
    }
  }
  
  private static class zzb
    implements zzbol.zza
  {
    private final zzbog zzcgk;
    private final zzbnb zzcgu;
    private final zzbpe zzcgv;
    
    public zzb(zzbnb paramzzbnb, zzbog paramzzbog, zzbpe paramzzbpe)
    {
      this.zzcgu = paramzzbnb;
      this.zzcgk = paramzzbog;
      this.zzcgv = paramzzbpe;
    }
    
    public zzbpd zza(zzboy paramzzboy, zzbpd paramzzbpd, boolean paramBoolean)
    {
      if (this.zzcgv != null) {}
      for (zzbpe localzzbpe = this.zzcgv;; localzzbpe = this.zzcgk.zzYN()) {
        return this.zzcgu.zza(localzzbpe, paramzzbpd, paramBoolean, paramzzboy);
      }
    }
    
    public zzbpe zzh(zzbos paramzzbos)
    {
      zzbnw localzzbnw = this.zzcgk.zzYK();
      if (localzzbnw.zzf(paramzzbos)) {
        return localzzbnw.zzUY().zzm(paramzzbos);
      }
      if (this.zzcgv != null) {}
      for (localzzbnw = new zzbnw(zzboz.zza(this.zzcgv, zzbpa.zzZw()), true, false);; localzzbnw = this.zzcgk.zzYM()) {
        return this.zzcgu.zza(paramzzbos, localzzbnw);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */