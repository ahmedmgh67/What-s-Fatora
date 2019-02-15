package com.google.android.gms.internal;

import java.io.PrintStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zzboo
  implements zzboq
{
  private final Set<String> zzcgC;
  private final zzboq.zza zzcgD;
  
  public zzboo(zzboq.zza paramzza, List<String> paramList)
  {
    if (paramList != null) {}
    for (this.zzcgC = new HashSet(paramList);; this.zzcgC = null)
    {
      this.zzcgD = paramzza;
      return;
    }
  }
  
  public zzboq.zza zzWu()
  {
    return this.zzcgD;
  }
  
  protected String zza(zzboq.zza paramzza, String paramString1, String paramString2, long paramLong)
  {
    String str = String.valueOf(new Date(paramLong).toString());
    paramzza = String.valueOf(paramzza);
    return String.valueOf(str).length() + 6 + String.valueOf(paramzza).length() + String.valueOf(paramString1).length() + String.valueOf(paramString2).length() + str + " [" + paramzza + "] " + paramString1 + ": " + paramString2;
  }
  
  protected boolean zza(zzboq.zza paramzza, String paramString)
  {
    return (paramzza.ordinal() >= this.zzcgD.ordinal()) && ((this.zzcgC == null) || (paramzza.ordinal() > zzboq.zza.zzcgF.ordinal()) || (this.zzcgC.contains(paramString)));
  }
  
  protected void zzaq(String paramString1, String paramString2)
  {
    System.err.println(paramString2);
  }
  
  protected void zzar(String paramString1, String paramString2)
  {
    System.out.println(paramString2);
  }
  
  protected void zzas(String paramString1, String paramString2)
  {
    System.out.println(paramString2);
  }
  
  protected void zzat(String paramString1, String paramString2)
  {
    System.out.println(paramString2);
  }
  
  public void zzb(zzboq.zza paramzza, String paramString1, String paramString2, long paramLong)
  {
    if (zza(paramzza, paramString1)) {
      paramString2 = zza(paramzza, paramString1, paramString2, paramLong);
    }
    switch (1.zzcaU[paramzza.ordinal()])
    {
    default: 
      throw new RuntimeException("Should not reach here!");
    case 1: 
      zzaq(paramString1, paramString2);
      return;
    case 2: 
      zzar(paramString1, paramString2);
      return;
    case 3: 
      zzas(paramString1, paramString2);
      return;
    }
    zzat(paramString1, paramString2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */