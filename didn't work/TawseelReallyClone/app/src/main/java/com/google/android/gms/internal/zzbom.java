package com.google.android.gms.internal;

import java.util.Iterator;

public class zzbom
  implements zzbol
{
  private final zzboy zzcfT;
  private final zzbpd zzcgA;
  private final zzbpd zzcgB;
  private final zzboj zzcgz;
  
  public zzbom(zzbod paramzzbod)
  {
    this.zzcgz = new zzboj(paramzzbod.zzYz());
    this.zzcfT = paramzzbod.zzYz();
    this.zzcgA = zzd(paramzzbod);
    this.zzcgB = zze(paramzzbod);
  }
  
  private static zzbpd zzd(zzbod paramzzbod)
  {
    if (paramzzbod.zzYr())
    {
      zzbos localzzbos = paramzzbod.zzYt();
      return paramzzbod.zzYz().zzg(localzzbos, paramzzbod.zzYs());
    }
    return paramzzbod.zzYz().zzZq();
  }
  
  private static zzbpd zze(zzbod paramzzbod)
  {
    if (paramzzbod.zzYu())
    {
      zzbos localzzbos = paramzzbod.zzYw();
      return paramzzbod.zzYz().zzg(localzzbos, paramzzbod.zzYv());
    }
    return paramzzbod.zzYz().zzZr();
  }
  
  public zzbol zzYP()
  {
    return this.zzcgz;
  }
  
  public boolean zzYQ()
  {
    return true;
  }
  
  public zzbpd zzYR()
  {
    return this.zzcgA;
  }
  
  public zzbpd zzYS()
  {
    return this.zzcgB;
  }
  
  public zzboy zzYz()
  {
    return this.zzcfT;
  }
  
  public zzboz zza(zzboz paramzzboz, zzbos paramzzbos, zzbpe paramzzbpe, zzbmj paramzzbmj, zzbol.zza paramzza, zzboi paramzzboi)
  {
    if (!zza(new zzbpd(paramzzbos, paramzzbpe))) {
      paramzzbpe = zzbox.zzZp();
    }
    for (;;)
    {
      return this.zzcgz.zza(paramzzboz, paramzzbos, paramzzbpe, paramzzbmj, paramzza, paramzzboi);
    }
  }
  
  public zzboz zza(zzboz paramzzboz1, zzboz paramzzboz2, zzboi paramzzboi)
  {
    if (paramzzboz2.zzUY().zzZd()) {
      localObject = zzboz.zza(zzbox.zzZp(), this.zzcfT);
    }
    Iterator localIterator;
    do
    {
      return this.zzcgz.zza(paramzzboz1, (zzboz)localObject, paramzzboi);
      localObject = paramzzboz2.zzo(zzbpi.zzZB());
      localIterator = paramzzboz2.iterator();
      paramzzboz2 = (zzboz)localObject;
      localObject = paramzzboz2;
    } while (!localIterator.hasNext());
    Object localObject = (zzbpd)localIterator.next();
    if (!zza((zzbpd)localObject)) {
      paramzzboz2 = paramzzboz2.zzh(((zzbpd)localObject).zzZz(), zzbox.zzZp());
    }
    for (;;)
    {
      break;
    }
  }
  
  public zzboz zza(zzboz paramzzboz, zzbpe paramzzbpe)
  {
    return paramzzboz;
  }
  
  public boolean zza(zzbpd paramzzbpd)
  {
    return (this.zzcfT.compare(zzYR(), paramzzbpd) <= 0) && (this.zzcfT.compare(paramzzbpd, zzYS()) <= 0);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */