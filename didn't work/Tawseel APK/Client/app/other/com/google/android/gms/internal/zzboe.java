package com.google.android.gms.internal;

import java.util.Map;

public class zzboe
{
  private final zzbmj zzbXY;
  private final zzbod zzbYc;
  
  public zzboe(zzbmj paramzzbmj, zzbod paramzzbod)
  {
    this.zzbXY = paramzzbmj;
    this.zzbYc = paramzzbod;
  }
  
  public static zzboe zzN(zzbmj paramzzbmj)
  {
    return new zzboe(paramzzbmj, zzbod.zzcfX);
  }
  
  public static zzboe zzb(zzbmj paramzzbmj, Map<String, Object> paramMap)
  {
    return new zzboe(paramzzbmj, zzbod.zzaD(paramMap));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzboe)paramObject;
      if (!this.zzbXY.equals(((zzboe)paramObject).zzbXY)) {
        return false;
      }
    } while (this.zzbYc.equals(((zzboe)paramObject).zzbYc));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzbXY.hashCode() * 31 + this.zzbYc.hashCode();
  }
  
  public boolean isDefault()
  {
    return this.zzbYc.isDefault();
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzbXY);
    String str2 = String.valueOf(this.zzbYc);
    return String.valueOf(str1).length() + 1 + String.valueOf(str2).length() + str1 + ":" + str2;
  }
  
  public zzbmj zzVc()
  {
    return this.zzbXY;
  }
  
  public boolean zzYD()
  {
    return this.zzbYc.zzYD();
  }
  
  public zzbod zzYG()
  {
    return this.zzbYc;
  }
  
  public zzboy zzYz()
  {
    return this.zzbYc.zzYz();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */