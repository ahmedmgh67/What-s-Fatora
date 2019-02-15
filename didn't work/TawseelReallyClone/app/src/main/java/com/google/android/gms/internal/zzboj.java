package com.google.android.gms.internal;

import java.util.Iterator;

public class zzboj
  implements zzbol
{
  private final zzboy zzcfT;
  
  static
  {
    if (!zzboj.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzboj(zzboy paramzzboy)
  {
    this.zzcfT = paramzzboy;
  }
  
  public zzbol zzYP()
  {
    return this;
  }
  
  public boolean zzYQ()
  {
    return false;
  }
  
  public zzboy zzYz()
  {
    return this.zzcfT;
  }
  
  public zzboz zza(zzboz paramzzboz, zzbos paramzzbos, zzbpe paramzzbpe, zzbmj paramzzbmj, zzbol.zza paramzza, zzboi paramzzboi)
  {
    assert (paramzzboz.zzb(this.zzcfT)) : "The index must match the filter";
    paramzza = paramzzboz.zzUY();
    zzbpe localzzbpe = paramzza.zzm(paramzzbos);
    if ((localzzbpe.zzO(paramzzbmj).equals(paramzzbpe.zzO(paramzzbmj))) && (localzzbpe.isEmpty() == paramzzbpe.isEmpty())) {}
    for (;;)
    {
      return paramzzboz;
      if (paramzzboi != null)
      {
        if (!paramzzbpe.isEmpty()) {
          break label172;
        }
        if (!paramzza.zzk(paramzzbos)) {
          break label146;
        }
        paramzzboi.zza(zzbny.zzd(paramzzbos, localzzbpe));
      }
      while ((!paramzza.zzZd()) || (!paramzzbpe.isEmpty()))
      {
        return paramzzboz.zzh(paramzzbos, paramzzbpe);
        label146:
        if ((!$assertionsDisabled) && (!paramzza.zzZd()))
        {
          throw new AssertionError("A child remove without an old child only makes sense on a leaf node");
          label172:
          if (localzzbpe.isEmpty()) {
            paramzzboi.zza(zzbny.zzc(paramzzbos, paramzzbpe));
          } else {
            paramzzboi.zza(zzbny.zza(paramzzbos, paramzzbpe, localzzbpe));
          }
        }
      }
    }
  }
  
  public zzboz zza(zzboz paramzzboz1, zzboz paramzzboz2, zzboi paramzzboi)
  {
    assert (paramzzboz2.zzb(this.zzcfT)) : "Can't use IndexedNode that doesn't have filter's index";
    if (paramzzboi != null)
    {
      Iterator localIterator = paramzzboz1.zzUY().iterator();
      zzbpd localzzbpd;
      while (localIterator.hasNext())
      {
        localzzbpd = (zzbpd)localIterator.next();
        if (!paramzzboz2.zzUY().zzk(localzzbpd.zzZz())) {
          paramzzboi.zza(zzbny.zzd(localzzbpd.zzZz(), localzzbpd.zzUY()));
        }
      }
      if (!paramzzboz2.zzUY().zzZd())
      {
        localIterator = paramzzboz2.zzUY().iterator();
        while (localIterator.hasNext())
        {
          localzzbpd = (zzbpd)localIterator.next();
          if (paramzzboz1.zzUY().zzk(localzzbpd.zzZz()))
          {
            zzbpe localzzbpe = paramzzboz1.zzUY().zzm(localzzbpd.zzZz());
            if (!localzzbpe.equals(localzzbpd.zzUY())) {
              paramzzboi.zza(zzbny.zza(localzzbpd.zzZz(), localzzbpd.zzUY(), localzzbpe));
            }
          }
          else
          {
            paramzzboi.zza(zzbny.zzc(localzzbpd.zzZz(), localzzbpd.zzUY()));
          }
        }
      }
    }
    return paramzzboz2;
  }
  
  public zzboz zza(zzboz paramzzboz, zzbpe paramzzbpe)
  {
    if (paramzzboz.zzUY().isEmpty()) {
      return paramzzboz;
    }
    return paramzzboz.zzo(paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */