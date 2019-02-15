package com.google.android.gms.internal;

import java.util.Comparator;

public abstract class zzboy
  implements Comparator<zzbpd>
{
  public static zzboy zzjc(String paramString)
  {
    if (paramString.equals(".value")) {
      return zzbpl.zzZC();
    }
    if (paramString.equals(".key")) {
      return zzbpa.zzZw();
    }
    if (paramString.equals(".priority")) {
      throw new IllegalStateException("queryDefinition shouldn't ever be .priority since it's the default");
    }
    return new zzbpg(new zzbmj(paramString));
  }
  
  public zzbpd zzZq()
  {
    return zzbpd.zzZx();
  }
  
  public abstract zzbpd zzZr();
  
  public abstract String zzZs();
  
  public int zza(zzbpd paramzzbpd1, zzbpd paramzzbpd2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return compare(paramzzbpd2, paramzzbpd1);
    }
    return compare(paramzzbpd1, paramzzbpd2);
  }
  
  public boolean zza(zzbpe paramzzbpe1, zzbpe paramzzbpe2)
  {
    return compare(new zzbpd(zzbos.zzYW(), paramzzbpe1), new zzbpd(zzbos.zzYW(), paramzzbpe2)) != 0;
  }
  
  public abstract zzbpd zzg(zzbos paramzzbos, zzbpe paramzzbpe);
  
  public abstract boolean zzm(zzbpe paramzzbpe);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */