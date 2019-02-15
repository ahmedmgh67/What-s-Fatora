package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzbmr
{
  private zzbpe zzcdv = null;
  private Map<zzbos, zzbmr> zzcdw = null;
  
  public void zza(final zzbmj paramzzbmj, final zzb paramzzb)
  {
    if (this.zzcdv != null)
    {
      paramzzb.zzf(paramzzbmj, this.zzcdv);
      return;
    }
    zza(new zza()
    {
      public void zza(zzbos paramAnonymouszzbos, zzbmr paramAnonymouszzbmr)
      {
        paramAnonymouszzbmr.zza(paramzzbmj.zza(paramAnonymouszzbos), paramzzb);
      }
    });
  }
  
  public void zza(zza paramzza)
  {
    if (this.zzcdw != null)
    {
      Iterator localIterator = this.zzcdw.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramzza.zza((zzbos)localEntry.getKey(), (zzbmr)localEntry.getValue());
      }
    }
  }
  
  public void zzh(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    if (paramzzbmj.isEmpty())
    {
      this.zzcdv = paramzzbpe;
      this.zzcdw = null;
      return;
    }
    if (this.zzcdv != null)
    {
      this.zzcdv = this.zzcdv.zzl(paramzzbmj, paramzzbpe);
      return;
    }
    if (this.zzcdw == null) {
      this.zzcdw = new HashMap();
    }
    zzbos localzzbos = paramzzbmj.zzXi();
    if (!this.zzcdw.containsKey(localzzbos)) {
      this.zzcdw.put(localzzbos, new zzbmr());
    }
    ((zzbmr)this.zzcdw.get(localzzbos)).zzh(paramzzbmj.zzXj(), paramzzbpe);
  }
  
  public boolean zzr(final zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty())
    {
      this.zzcdv = null;
      this.zzcdw = null;
      return true;
    }
    Object localObject;
    if (this.zzcdv != null)
    {
      if (this.zzcdv.zzZd()) {
        return false;
      }
      localObject = (zzbot)this.zzcdv;
      this.zzcdv = null;
      ((zzbot)localObject).zza(new zzbot.zza()
      {
        public void zzb(zzbos paramAnonymouszzbos, zzbpe paramAnonymouszzbpe)
        {
          zzbmr.this.zzh(paramzzbmj.zza(paramAnonymouszzbos), paramAnonymouszzbpe);
        }
      });
      return zzr(paramzzbmj);
    }
    if (this.zzcdw != null)
    {
      localObject = paramzzbmj.zzXi();
      paramzzbmj = paramzzbmj.zzXj();
      if ((this.zzcdw.containsKey(localObject)) && (((zzbmr)this.zzcdw.get(localObject)).zzr(paramzzbmj))) {
        this.zzcdw.remove(localObject);
      }
      if (this.zzcdw.isEmpty())
      {
        this.zzcdw = null;
        return true;
      }
      return false;
    }
    return true;
  }
  
  public static abstract interface zza
  {
    public abstract void zza(zzbos paramzzbos, zzbmr paramzzbmr);
  }
  
  public static abstract interface zzb
  {
    public abstract void zzf(zzbmj paramzzbmj, zzbpe paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */