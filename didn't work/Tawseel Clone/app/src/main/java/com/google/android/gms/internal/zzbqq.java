package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class zzbqq
{
  private long zzavX;
  private Map<String, Map<String, byte[]>> zzcjN;
  
  public zzbqq(Map<String, Map<String, byte[]>> paramMap, long paramLong)
  {
    this.zzcjN = paramMap;
    this.zzavX = paramLong;
  }
  
  public long getTimestamp()
  {
    return this.zzavX;
  }
  
  public void setTimestamp(long paramLong)
  {
    this.zzavX = paramLong;
  }
  
  public Map<String, Map<String, byte[]>> zzaav()
  {
    return this.zzcjN;
  }
  
  public boolean zzaaw()
  {
    return (this.zzcjN != null) && (!this.zzcjN.isEmpty());
  }
  
  public boolean zzav(String paramString1, String paramString2)
  {
    return (zzaaw()) && (zzjG(paramString2)) && (zzaw(paramString1, paramString2) != null);
  }
  
  public byte[] zzaw(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (!zzjG(paramString2))) {
      return null;
    }
    return (byte[])((Map)this.zzcjN.get(paramString2)).get(paramString1);
  }
  
  public Set<String> zzax(String paramString1, String paramString2)
  {
    TreeSet localTreeSet = new TreeSet();
    if (!zzjG(paramString2)) {
      return localTreeSet;
    }
    if ((paramString1 == null) || (paramString1.isEmpty())) {
      return ((Map)this.zzcjN.get(paramString2)).keySet();
    }
    paramString2 = ((Map)this.zzcjN.get(paramString2)).keySet().iterator();
    while (paramString2.hasNext())
    {
      String str = (String)paramString2.next();
      if (str.startsWith(paramString1)) {
        localTreeSet.add(str);
      }
    }
    return localTreeSet;
  }
  
  public void zzj(Map<String, byte[]> paramMap, String paramString)
  {
    if (this.zzcjN == null) {
      this.zzcjN = new HashMap();
    }
    this.zzcjN.put(paramString, paramMap);
  }
  
  public boolean zzjG(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    if ((zzaaw()) && (this.zzcjN.get(paramString) != null) && (!((Map)this.zzcjN.get(paramString)).isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */