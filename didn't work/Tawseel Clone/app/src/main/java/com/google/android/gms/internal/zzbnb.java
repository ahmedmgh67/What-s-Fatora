package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public class zzbnb
{
  private final zzbmj zzcev;
  private final zzbna zzcew;
  
  public zzbnb(zzbmj paramzzbmj, zzbna paramzzbna)
  {
    this.zzcev = paramzzbmj;
    this.zzcew = paramzzbna;
  }
  
  public zzbpd zza(zzbpe paramzzbpe, zzbpd paramzzbpd, boolean paramBoolean, zzboy paramzzboy)
  {
    return this.zzcew.zza(this.zzcev, paramzzbpe, paramzzbpd, paramBoolean, paramzzboy);
  }
  
  public zzbpe zza(zzbmj paramzzbmj, zzbpe paramzzbpe1, zzbpe paramzzbpe2)
  {
    return this.zzcew.zza(this.zzcev, paramzzbmj, paramzzbpe1, paramzzbpe2);
  }
  
  public zzbpe zza(zzbos paramzzbos, zzbnw paramzzbnw)
  {
    return this.zzcew.zza(this.zzcev, paramzzbos, paramzzbnw);
  }
  
  public zzbpe zza(zzbpe paramzzbpe, List<Long> paramList)
  {
    return zza(paramzzbpe, paramList, false);
  }
  
  public zzbpe zza(zzbpe paramzzbpe, List<Long> paramList, boolean paramBoolean)
  {
    return this.zzcew.zza(this.zzcev, paramzzbpe, paramList, paramBoolean);
  }
  
  public zzbnb zzb(zzbos paramzzbos)
  {
    return new zzbnb(this.zzcev.zza(paramzzbos), this.zzcew);
  }
  
  public zzbpe zzc(zzbpe paramzzbpe)
  {
    return zza(paramzzbpe, Collections.emptyList());
  }
  
  public zzbpe zzd(zzbpe paramzzbpe)
  {
    return this.zzcew.zzj(this.zzcev, paramzzbpe);
  }
  
  public zzbpe zzv(zzbmj paramzzbmj)
  {
    return this.zzcew.zzv(this.zzcev.zzh(paramzzbmj));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */