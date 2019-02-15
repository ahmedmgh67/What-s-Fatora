package com.google.android.gms.internal;

public class zzbph
  extends zzboy
{
  private static final zzbph zzchz = new zzbph();
  
  public static zzbph zzZA()
  {
    return zzchz;
  }
  
  public boolean equals(Object paramObject)
  {
    return paramObject instanceof zzbph;
  }
  
  public int hashCode()
  {
    return 3155577;
  }
  
  public String toString()
  {
    return "PriorityIndex";
  }
  
  public zzbpd zzZr()
  {
    return zzg(zzbos.zzYX(), zzbpe.zzchu);
  }
  
  public String zzZs()
  {
    throw new IllegalArgumentException("Can't get query definition on priority index!");
  }
  
  public int zza(zzbpd paramzzbpd1, zzbpd paramzzbpd2)
  {
    zzbpe localzzbpe1 = paramzzbpd1.zzUY().zzZe();
    zzbpe localzzbpe2 = paramzzbpd2.zzUY().zzZe();
    return zzbpf.zza(paramzzbpd1.zzZz(), localzzbpe1, paramzzbpd2.zzZz(), localzzbpe2);
  }
  
  public zzbpd zzg(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    return new zzbpd(paramzzbos, new zzbpk("[PRIORITY-POST]", paramzzbpe));
  }
  
  public boolean zzm(zzbpe paramzzbpe)
  {
    return !paramzzbpe.zzZe().isEmpty();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */