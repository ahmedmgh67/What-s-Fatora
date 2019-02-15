package com.google.android.gms.internal;

import java.util.Iterator;

public class zzbok
  implements zzbol
{
  private final int limit;
  private final zzboy zzcfT;
  private final zzbom zzcgx;
  private final boolean zzcgy;
  
  static
  {
    if (!zzbok.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbok(zzbod paramzzbod)
  {
    this.zzcgx = new zzbom(paramzzbod);
    this.zzcfT = paramzzbod.zzYz();
    this.limit = paramzzbod.getLimit();
    if (!paramzzbod.zzYB()) {}
    for (boolean bool = true;; bool = false)
    {
      this.zzcgy = bool;
      return;
    }
  }
  
  private zzboz zza(zzboz paramzzboz, zzbos paramzzbos, zzbpe paramzzbpe, zzbol.zza paramzza, zzboi paramzzboi)
  {
    assert (paramzzboz.zzUY().getChildCount() == this.limit);
    zzbpd localzzbpd2 = new zzbpd(paramzzbos, paramzzbpe);
    if (this.zzcgy) {}
    boolean bool;
    zzbpe localzzbpe;
    for (zzbpd localzzbpd1 = paramzzboz.zzZu();; localzzbpd1 = paramzzboz.zzZv())
    {
      bool = this.zzcgx.zza(localzzbpd2);
      if (!paramzzboz.zzUY().zzk(paramzzbos)) {
        break label360;
      }
      localzzbpe = paramzzboz.zzUY().zzm(paramzzbos);
      for (localzzbpd1 = paramzza.zza(this.zzcfT, localzzbpd1, this.zzcgy); (localzzbpd1 != null) && ((localzzbpd1.zzZz().equals(paramzzbos)) || (paramzzboz.zzUY().zzk(localzzbpd1.zzZz()))); localzzbpd1 = paramzza.zza(this.zzcfT, localzzbpd1, this.zzcgy)) {}
    }
    int i;
    if (localzzbpd1 == null)
    {
      i = 1;
      if ((!bool) || (paramzzbpe.isEmpty()) || (i < 0)) {
        break label257;
      }
      i = 1;
      label204:
      if (i == 0) {
        break label263;
      }
      if (paramzzboi != null) {
        paramzzboi.zza(zzbny.zza(paramzzbos, paramzzbpe, localzzbpe));
      }
      paramzza = paramzzboz.zzh(paramzzbos, paramzzbpe);
    }
    label257:
    label263:
    label360:
    do
    {
      do
      {
        do
        {
          return paramzza;
          i = this.zzcfT.zza(localzzbpd1, localzzbpd2, this.zzcgy);
          break;
          i = 0;
          break label204;
          if (paramzzboi != null) {
            paramzzboi.zza(zzbny.zzd(paramzzbos, localzzbpe));
          }
          paramzzboz = paramzzboz.zzh(paramzzbos, zzbox.zzZp());
          if ((localzzbpd1 != null) && (this.zzcgx.zza(localzzbpd1))) {}
          for (i = 1;; i = 0)
          {
            paramzza = paramzzboz;
            if (i == 0) {
              break;
            }
            if (paramzzboi != null) {
              paramzzboi.zza(zzbny.zzc(localzzbpd1.zzZz(), localzzbpd1.zzUY()));
            }
            return paramzzboz.zzh(localzzbpd1.zzZz(), localzzbpd1.zzUY());
          }
          paramzza = paramzzboz;
        } while (paramzzbpe.isEmpty());
        paramzza = paramzzboz;
      } while (!bool);
      paramzza = paramzzboz;
    } while (this.zzcfT.zza(localzzbpd1, localzzbpd2, this.zzcgy) < 0);
    if (paramzzboi != null)
    {
      paramzzboi.zza(zzbny.zzd(localzzbpd1.zzZz(), localzzbpd1.zzUY()));
      paramzzboi.zza(zzbny.zzc(paramzzbos, paramzzbpe));
    }
    return paramzzboz.zzh(paramzzbos, paramzzbpe).zzh(localzzbpd1.zzZz(), zzbox.zzZp());
  }
  
  public zzbol zzYP()
  {
    return this.zzcgx.zzYP();
  }
  
  public boolean zzYQ()
  {
    return true;
  }
  
  public zzboy zzYz()
  {
    return this.zzcfT;
  }
  
  public zzboz zza(zzboz paramzzboz, zzbos paramzzbos, zzbpe paramzzbpe, zzbmj paramzzbmj, zzbol.zza paramzza, zzboi paramzzboi)
  {
    if (!this.zzcgx.zza(new zzbpd(paramzzbos, paramzzbpe))) {
      paramzzbpe = zzbox.zzZp();
    }
    for (;;)
    {
      if (paramzzboz.zzUY().zzm(paramzzbos).equals(paramzzbpe)) {
        return paramzzboz;
      }
      if (paramzzboz.zzUY().getChildCount() < this.limit) {
        return this.zzcgx.zzYP().zza(paramzzboz, paramzzbos, paramzzbpe, paramzzbmj, paramzza, paramzzboi);
      }
      return zza(paramzzboz, paramzzbos, paramzzbpe, paramzza, paramzzboi);
    }
  }
  
  public zzboz zza(zzboz paramzzboz1, zzboz paramzzboz2, zzboi paramzzboi)
  {
    if ((paramzzboz2.zzUY().zzZd()) || (paramzzboz2.zzUY().isEmpty())) {
      localObject = zzboz.zza(zzbox.zzZp(), this.zzcfT);
    }
    Iterator localIterator;
    zzbpd localzzbpd2;
    zzbpd localzzbpd1;
    int j;
    int i;
    int m;
    label105:
    do
    {
      return this.zzcgx.zzYP().zza(paramzzboz1, (zzboz)localObject, paramzzboi);
      localObject = paramzzboz2.zzo(zzbpi.zzZB());
      if (!this.zzcgy) {
        break;
      }
      localIterator = paramzzboz2.zzVl();
      localzzbpd2 = this.zzcgx.zzYS();
      localzzbpd1 = this.zzcgx.zzYR();
      j = -1;
      i = 0;
      paramzzboz2 = (zzboz)localObject;
      m = 0;
      localObject = paramzzboz2;
    } while (!localIterator.hasNext());
    Object localObject = (zzbpd)localIterator.next();
    int k = m;
    if (m == 0)
    {
      k = m;
      if (this.zzcfT.compare(localzzbpd2, localObject) * j <= 0) {
        k = 1;
      }
    }
    if ((k != 0) && (i < this.limit) && (this.zzcfT.compare(localObject, localzzbpd1) * j <= 0))
    {
      m = 1;
      label197:
      if (m == 0) {
        break label251;
      }
      i += 1;
    }
    for (;;)
    {
      m = k;
      break label105;
      localIterator = paramzzboz2.iterator();
      localzzbpd2 = this.zzcgx.zzYR();
      localzzbpd1 = this.zzcgx.zzYS();
      j = 1;
      break;
      m = 0;
      break label197;
      label251:
      paramzzboz2 = paramzzboz2.zzh(((zzbpd)localObject).zzZz(), zzbox.zzZp());
    }
  }
  
  public zzboz zza(zzboz paramzzboz, zzbpe paramzzbpe)
  {
    return paramzzboz;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbok.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */