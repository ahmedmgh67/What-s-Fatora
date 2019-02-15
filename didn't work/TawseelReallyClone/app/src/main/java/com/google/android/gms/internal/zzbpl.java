package com.google.android.gms.internal;

public class zzbpl
  extends zzboy
{
  private static final zzbpl zzchD = new zzbpl();
  
  public static zzbpl zzZC()
  {
    return zzchD;
  }
  
  public boolean equals(Object paramObject)
  {
    return paramObject instanceof zzbpl;
  }
  
  public int hashCode()
  {
    return 4;
  }
  
  public String toString()
  {
    return "ValueIndex";
  }
  
  public zzbpd zzZr()
  {
    return new zzbpd(zzbos.zzYX(), zzbpe.zzchu);
  }
  
  public String zzZs()
  {
    return ".value";
  }
  
  public int zza(zzbpd paramzzbpd1, zzbpd paramzzbpd2)
  {
    int j = paramzzbpd1.zzUY().compareTo(paramzzbpd2.zzUY());
    int i = j;
    if (j == 0) {
      i = paramzzbpd1.zzZz().zzi(paramzzbpd2.zzZz());
    }
    return i;
  }
  
  public zzbpd zzg(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    return new zzbpd(paramzzbos, paramzzbpe);
  }
  
  public boolean zzm(zzbpe paramzzbpe)
  {
    return true;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */