package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzboz
  implements Iterable<zzbpd>
{
  private static final zzblc<zzbpd> zzchi = new zzblc(Collections.emptyList(), null);
  private final zzboy zzcfT;
  private final zzbpe zzchj;
  private zzblc<zzbpd> zzchk;
  
  private zzboz(zzbpe paramzzbpe, zzboy paramzzboy)
  {
    this.zzcfT = paramzzboy;
    this.zzchj = paramzzbpe;
    this.zzchk = null;
  }
  
  private zzboz(zzbpe paramzzbpe, zzboy paramzzboy, zzblc<zzbpd> paramzzblc)
  {
    this.zzcfT = paramzzboy;
    this.zzchj = paramzzbpe;
    this.zzchk = paramzzblc;
  }
  
  private void zzZt()
  {
    if (this.zzchk == null)
    {
      if (this.zzcfT.equals(zzbpa.zzZw())) {
        this.zzchk = zzchi;
      }
    }
    else {
      return;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzchj.iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      zzbpd localzzbpd = (zzbpd)localIterator.next();
      if ((i != 0) || (this.zzcfT.zzm(localzzbpd.zzUY()))) {}
      for (i = 1;; i = 0)
      {
        localArrayList.add(new zzbpd(localzzbpd.zzZz(), localzzbpd.zzUY()));
        break;
      }
    }
    if (i != 0)
    {
      this.zzchk = new zzblc(localArrayList, this.zzcfT);
      return;
    }
    this.zzchk = zzchi;
  }
  
  public static zzboz zza(zzbpe paramzzbpe, zzboy paramzzboy)
  {
    return new zzboz(paramzzbpe, paramzzboy);
  }
  
  public static zzboz zzn(zzbpe paramzzbpe)
  {
    return new zzboz(paramzzbpe, zzbph.zzZA());
  }
  
  public Iterator<zzbpd> iterator()
  {
    zzZt();
    if (this.zzchk == zzchi) {
      return this.zzchj.iterator();
    }
    return this.zzchk.iterator();
  }
  
  public zzbpe zzUY()
  {
    return this.zzchj;
  }
  
  public Iterator<zzbpd> zzVl()
  {
    zzZt();
    if (this.zzchk == zzchi) {
      return this.zzchj.zzVl();
    }
    return this.zzchk.zzVl();
  }
  
  public zzbpd zzZu()
  {
    if (!(this.zzchj instanceof zzbot)) {
      return null;
    }
    zzZt();
    if (this.zzchk == zzchi)
    {
      zzbos localzzbos = ((zzbot)this.zzchj).zzZf();
      return new zzbpd(localzzbos, this.zzchj.zzm(localzzbos));
    }
    return (zzbpd)this.zzchk.zzVn();
  }
  
  public zzbpd zzZv()
  {
    if (!(this.zzchj instanceof zzbot)) {
      return null;
    }
    zzZt();
    if (this.zzchk == zzchi)
    {
      zzbos localzzbos = ((zzbot)this.zzchj).zzZg();
      return new zzbpd(localzzbos, this.zzchj.zzm(localzzbos));
    }
    return (zzbpd)this.zzchk.zzVo();
  }
  
  public zzbos zza(zzbos paramzzbos, zzbpe paramzzbpe, zzboy paramzzboy)
  {
    if ((!this.zzcfT.equals(zzbpa.zzZw())) && (!this.zzcfT.equals(paramzzboy))) {
      throw new IllegalArgumentException("Index not available in IndexedNode!");
    }
    zzZt();
    if (this.zzchk == zzchi) {
      return this.zzchj.zzl(paramzzbos);
    }
    paramzzbos = (zzbpd)this.zzchk.zzal(new zzbpd(paramzzbos, paramzzbpe));
    if (paramzzbos != null) {
      return paramzzbos.zzZz();
    }
    return null;
  }
  
  public boolean zzb(zzboy paramzzboy)
  {
    return this.zzcfT.equals(paramzzboy);
  }
  
  public zzboz zzh(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    zzbpe localzzbpe = this.zzchj.zze(paramzzbos, paramzzbpe);
    if ((this.zzchk == zzchi) && (!this.zzcfT.zzm(paramzzbpe))) {
      return new zzboz(localzzbpe, this.zzcfT, zzchi);
    }
    if ((this.zzchk == null) || (this.zzchk == zzchi)) {
      return new zzboz(localzzbpe, this.zzcfT, null);
    }
    Object localObject = this.zzchj.zzm(paramzzbos);
    zzblc localzzblc = this.zzchk.zzaj(new zzbpd(paramzzbos, (zzbpe)localObject));
    localObject = localzzblc;
    if (!paramzzbpe.isEmpty()) {
      localObject = localzzblc.zzak(new zzbpd(paramzzbos, paramzzbpe));
    }
    return new zzboz(localzzbpe, this.zzcfT, (zzblc)localObject);
  }
  
  public zzboz zzo(zzbpe paramzzbpe)
  {
    return new zzboz(this.zzchj.zzg(paramzzbpe), this.zzcfT, this.zzchk);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */