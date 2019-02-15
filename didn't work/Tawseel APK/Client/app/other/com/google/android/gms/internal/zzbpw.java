package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Map;

public class zzbpw
{
  private final String zzbwP;
  private final Map<String, Object> zzcis;
  
  public zzbpw(String paramString, Map<String, Object> paramMap)
  {
    this.zzbwP = paramString;
    this.zzcis = paramMap;
  }
  
  public static zzbpw zzje(String paramString)
  {
    if (!paramString.startsWith("gauth|")) {
      return null;
    }
    paramString = paramString.substring("gauth|".length());
    try
    {
      paramString = zzbpx.zzjf(paramString);
      paramString = new zzbpw((String)paramString.get("token"), (Map)paramString.get("auth"));
      return paramString;
    }
    catch (IOException paramString)
    {
      throw new RuntimeException("Failed to parse gauth token", paramString);
    }
  }
  
  public String getToken()
  {
    return this.zzbwP;
  }
  
  public Map<String, Object> zzZX()
  {
    return this.zzcis;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */