package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

 enum zzbmi
  implements zzbmk
{
  private zzbmi() {}
  
  public zzblr zza(zzbmc paramzzbmc, zzbln paramzzbln, zzblp paramzzblp, zzblr.zza paramzza)
  {
    return new zzbls(paramzzbmc.zzWO(), paramzzblp, paramzza);
  }
  
  public zzbly zza(ScheduledExecutorService paramScheduledExecutorService)
  {
    throw new RuntimeException("Authentication is not implemented yet");
  }
  
  public zzbmg zza(zzbmc paramzzbmc)
  {
    return new zzbmw(Executors.defaultThreadFactory(), zzbmv.zzced);
  }
  
  public zzbnn zza(zzbmc paramzzbmc, String paramString)
  {
    return null;
  }
  
  public zzboq zza(zzbmc paramzzbmc, zzboq.zza paramzza, List<String> paramList)
  {
    return new zzboo(paramzza, paramList);
  }
  
  public zzbmo zzb(zzbmc paramzzbmc)
  {
    new zzbqa()
    {
      public void zzj(Throwable paramAnonymousThrowable)
      {
        this.zzbYs.zzd(zzbqa.zzl(paramAnonymousThrowable), paramAnonymousThrowable);
      }
    };
  }
  
  public String zzc(zzbmc paramzzbmc)
  {
    paramzzbmc = System.getProperty("java.vm.name", "Unknown JVM");
    String str = System.getProperty("java.specification.version", "Unknown");
    return String.valueOf(str).length() + 1 + String.valueOf(paramzzbmc).length() + str + "/" + paramzzbmc;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */