package com.google.android.gms.internal;

import java.io.IOException;

final class zzbsc<T>
  extends zzbsd<T>
{
  private zzbsd<T> zzcmC;
  private final zzbrz<T> zzcmR;
  private final zzbrq<T> zzcmS;
  private final zzbrl zzcmT;
  private final zzbth<T> zzcmU;
  private final zzbse zzcmV;
  
  private zzbsc(zzbrz<T> paramzzbrz, zzbrq<T> paramzzbrq, zzbrl paramzzbrl, zzbth<T> paramzzbth, zzbse paramzzbse)
  {
    this.zzcmR = paramzzbrz;
    this.zzcmS = paramzzbrq;
    this.zzcmT = paramzzbrl;
    this.zzcmU = paramzzbth;
    this.zzcmV = paramzzbse;
  }
  
  public static zzbse zza(zzbth<?> paramzzbth, Object paramObject)
  {
    return new zza(paramObject, paramzzbth, false, null, null);
  }
  
  private zzbsd<T> zzabG()
  {
    zzbsd localzzbsd = this.zzcmC;
    if (localzzbsd != null) {
      return localzzbsd;
    }
    localzzbsd = this.zzcmT.zza(this.zzcmV, this.zzcmU);
    this.zzcmC = localzzbsd;
    return localzzbsd;
  }
  
  public static zzbse zzb(zzbth<?> paramzzbth, Object paramObject)
  {
    if (paramzzbth.zzacc() == paramzzbth.zzacb()) {}
    for (boolean bool = true;; bool = false) {
      return new zza(paramObject, paramzzbth, bool, null, null);
    }
  }
  
  public void zza(zzbtk paramzzbtk, T paramT)
    throws IOException
  {
    if (this.zzcmR == null)
    {
      zzabG().zza(paramzzbtk, paramT);
      return;
    }
    if (paramT == null)
    {
      paramzzbtk.zzaca();
      return;
    }
    zzbss.zzb(this.zzcmR.zza(paramT, this.zzcmU.zzacc(), this.zzcmT.zzcmA), paramzzbtk);
  }
  
  public T zzb(zzbti paramzzbti)
    throws IOException
  {
    if (this.zzcmS == null) {
      return (T)zzabG().zzb(paramzzbti);
    }
    paramzzbti = zzbss.zzh(paramzzbti);
    if (paramzzbti.zzaby()) {
      return null;
    }
    try
    {
      paramzzbti = this.zzcmS.zzb(paramzzbti, this.zzcmU.zzacc(), this.zzcmT.zzcmz);
      return paramzzbti;
    }
    catch (zzbrv paramzzbti)
    {
      throw paramzzbti;
    }
    catch (Exception paramzzbti)
    {
      throw new zzbrv(paramzzbti);
    }
  }
  
  private static class zza
    implements zzbse
  {
    private final zzbrz<?> zzcmR;
    private final zzbrq<?> zzcmS;
    private final zzbth<?> zzcmW;
    private final boolean zzcmX;
    private final Class<?> zzcmY;
    
    private zza(Object paramObject, zzbth<?> paramzzbth, boolean paramBoolean, Class<?> paramClass)
    {
      zzbrz localzzbrz;
      if ((paramObject instanceof zzbrz))
      {
        localzzbrz = (zzbrz)paramObject;
        this.zzcmR = localzzbrz;
        if (!(paramObject instanceof zzbrq)) {
          break label85;
        }
        paramObject = (zzbrq)paramObject;
        label35:
        this.zzcmS = ((zzbrq)paramObject);
        if ((this.zzcmR == null) && (this.zzcmS == null)) {
          break label90;
        }
      }
      label85:
      label90:
      for (boolean bool = true;; bool = false)
      {
        zzbsj.zzas(bool);
        this.zzcmW = paramzzbth;
        this.zzcmX = paramBoolean;
        this.zzcmY = paramClass;
        return;
        localzzbrz = null;
        break;
        paramObject = null;
        break label35;
      }
    }
    
    public <T> zzbsd<T> zza(zzbrl paramzzbrl, zzbth<T> paramzzbth)
    {
      boolean bool;
      if (this.zzcmW != null) {
        if ((this.zzcmW.equals(paramzzbth)) || ((this.zzcmX) && (this.zzcmW.zzacc() == paramzzbth.zzacb()))) {
          bool = true;
        }
      }
      while (bool)
      {
        return new zzbsc(this.zzcmR, this.zzcmS, paramzzbrl, paramzzbth, this, null);
        bool = false;
        continue;
        bool = this.zzcmY.isAssignableFrom(paramzzbth.zzacb());
      }
      return null;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */