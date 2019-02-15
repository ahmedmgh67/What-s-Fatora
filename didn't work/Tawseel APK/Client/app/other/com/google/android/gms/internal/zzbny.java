package com.google.android.gms.internal;

public class zzbny
{
  private final zzboz zzcfB;
  private final zzboa.zza zzcfG;
  private final zzboz zzcfH;
  private final zzbos zzcfI;
  private final zzbos zzcfJ;
  
  private zzbny(zzboa.zza paramzza, zzboz paramzzboz1, zzbos paramzzbos1, zzbos paramzzbos2, zzboz paramzzboz2)
  {
    this.zzcfG = paramzza;
    this.zzcfB = paramzzboz1;
    this.zzcfI = paramzzbos1;
    this.zzcfJ = paramzzbos2;
    this.zzcfH = paramzzboz2;
  }
  
  public static zzbny zza(zzbos paramzzbos, zzboz paramzzboz)
  {
    return new zzbny(zzboa.zza.zzcfN, paramzzboz, paramzzbos, null, null);
  }
  
  public static zzbny zza(zzbos paramzzbos, zzboz paramzzboz1, zzboz paramzzboz2)
  {
    return new zzbny(zzboa.zza.zzcfP, paramzzboz1, paramzzbos, null, paramzzboz2);
  }
  
  public static zzbny zza(zzbos paramzzbos, zzbpe paramzzbpe1, zzbpe paramzzbpe2)
  {
    return zza(paramzzbos, zzboz.zzn(paramzzbpe1), zzboz.zzn(paramzzbpe2));
  }
  
  public static zzbny zza(zzboz paramzzboz)
  {
    return new zzbny(zzboa.zza.zzcfQ, paramzzboz, null, null, null);
  }
  
  public static zzbny zzb(zzbos paramzzbos, zzboz paramzzboz)
  {
    return new zzbny(zzboa.zza.zzcfM, paramzzboz, paramzzbos, null, null);
  }
  
  public static zzbny zzc(zzbos paramzzbos, zzboz paramzzboz)
  {
    return new zzbny(zzboa.zza.zzcfO, paramzzboz, paramzzbos, null, null);
  }
  
  public static zzbny zzc(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    return zza(paramzzbos, zzboz.zzn(paramzzbpe));
  }
  
  public static zzbny zzd(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    return zzb(paramzzbos, zzboz.zzn(paramzzbpe));
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzcfG);
    String str2 = String.valueOf(this.zzcfI);
    return String.valueOf(str1).length() + 9 + String.valueOf(str2).length() + "Change: " + str1 + " " + str2;
  }
  
  public zzboz zzYi()
  {
    return this.zzcfB;
  }
  
  public zzbos zzYk()
  {
    return this.zzcfI;
  }
  
  public zzboa.zza zzYl()
  {
    return this.zzcfG;
  }
  
  public zzbos zzYm()
  {
    return this.zzcfJ;
  }
  
  public zzboz zzYn()
  {
    return this.zzcfH;
  }
  
  public zzbny zzg(zzbos paramzzbos)
  {
    return new zzbny(this.zzcfG, this.zzcfB, this.zzcfI, paramzzbos, this.zzcfH);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbny.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */