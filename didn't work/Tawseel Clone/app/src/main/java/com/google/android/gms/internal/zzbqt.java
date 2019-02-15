package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzbqt
{
  private boolean zzcjH;
  private int zzcjP;
  private long zzcjS;
  private Map<String, zzbqo> zzcjT;
  
  public zzbqt()
  {
    this(-1L);
  }
  
  public zzbqt(int paramInt, long paramLong, Map<String, zzbqo> paramMap, boolean paramBoolean)
  {
    this.zzcjP = paramInt;
    this.zzcjS = paramLong;
    if (paramMap != null) {}
    for (;;)
    {
      this.zzcjT = paramMap;
      this.zzcjH = paramBoolean;
      return;
      paramMap = new HashMap();
    }
  }
  
  public zzbqt(long paramLong)
  {
    this(0, paramLong, null, false);
  }
  
  public int getLastFetchStatus()
  {
    return this.zzcjP;
  }
  
  public boolean isDeveloperModeEnabled()
  {
    return this.zzcjH;
  }
  
  public void zza(String paramString, zzbqo paramzzbqo)
  {
    this.zzcjT.put(paramString, paramzzbqo);
  }
  
  public void zzaH(Map<String, zzbqo> paramMap)
  {
    if (paramMap != null) {}
    for (;;)
    {
      this.zzcjT = paramMap;
      return;
      paramMap = new HashMap();
    }
  }
  
  public void zzaS(long paramLong)
  {
    this.zzcjS = paramLong;
  }
  
  public Map<String, zzbqo> zzaay()
  {
    return this.zzcjT;
  }
  
  public long zzaaz()
  {
    return this.zzcjS;
  }
  
  public void zzbc(boolean paramBoolean)
  {
    this.zzcjH = paramBoolean;
  }
  
  public void zzjH(String paramString)
  {
    if (this.zzcjT.get(paramString) == null) {
      return;
    }
    this.zzcjT.remove(paramString);
  }
  
  public void zzpV(int paramInt)
  {
    this.zzcjP = paramInt;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */