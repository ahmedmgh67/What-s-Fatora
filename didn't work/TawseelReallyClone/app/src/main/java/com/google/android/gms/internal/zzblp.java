package com.google.android.gms.internal;

import java.net.URI;

public class zzblp
{
  private final String zzaFs;
  private final String zzbZA;
  private final boolean zzbZB;
  
  public zzblp(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.zzbZA = paramString1;
    this.zzaFs = paramString2;
    this.zzbZB = paramBoolean;
  }
  
  public static URI zza(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    if (paramBoolean) {}
    for (String str1 = "wss";; str1 = "ws")
    {
      String str2 = String.valueOf("v");
      String str3 = String.valueOf("5");
      paramString2 = String.valueOf(str1).length() + 13 + String.valueOf(paramString1).length() + String.valueOf(paramString2).length() + String.valueOf(str2).length() + String.valueOf(str3).length() + str1 + "://" + paramString1 + "/.ws?ns=" + paramString2 + "&" + str2 + "=" + str3;
      paramString1 = paramString2;
      if (paramString3 != null)
      {
        paramString1 = String.valueOf(paramString2);
        paramString2 = String.valueOf("&ls=");
        paramString1 = String.valueOf(paramString1).length() + 0 + String.valueOf(paramString2).length() + String.valueOf(paramString3).length() + paramString1 + paramString2 + paramString3;
      }
      return URI.create(paramString1);
    }
  }
  
  public String getHost()
  {
    return this.zzbZA;
  }
  
  public String getNamespace()
  {
    return this.zzaFs;
  }
  
  public boolean isSecure()
  {
    return this.zzbZB;
  }
  
  public String toString()
  {
    if (this.zzbZB) {}
    for (String str1 = "s";; str1 = "")
    {
      String str2 = this.zzbZA;
      return String.valueOf(str1).length() + 7 + String.valueOf(str2).length() + "http" + str1 + "://" + str2;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */