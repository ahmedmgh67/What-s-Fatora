package com.google.android.gms.internal;

public class zzbmx
{
  private final zzbmj zzbXY;
  private final long zzceh;
  private final zzbpe zzcei;
  private final zzbma zzcej;
  private final boolean zzcek;
  
  public zzbmx(long paramLong, zzbmj paramzzbmj, zzbma paramzzbma)
  {
    this.zzceh = paramLong;
    this.zzbXY = paramzzbmj;
    this.zzcei = null;
    this.zzcej = paramzzbma;
    this.zzcek = true;
  }
  
  public zzbmx(long paramLong, zzbmj paramzzbmj, zzbpe paramzzbpe, boolean paramBoolean)
  {
    this.zzceh = paramLong;
    this.zzbXY = paramzzbmj;
    this.zzcei = paramzzbpe;
    this.zzcej = null;
    this.zzcek = paramBoolean;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    label93:
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzbmx)paramObject;
      if (this.zzceh != ((zzbmx)paramObject).zzceh) {
        return false;
      }
      if (!this.zzbXY.equals(((zzbmx)paramObject).zzbXY)) {
        return false;
      }
      if (this.zzcek != ((zzbmx)paramObject).zzcek) {
        return false;
      }
      if (this.zzcei == null) {
        break;
      }
      if (!this.zzcei.equals(((zzbmx)paramObject).zzcei)) {
        break label123;
      }
      if (this.zzcej == null) {
        break label125;
      }
    } while (this.zzcej.equals(((zzbmx)paramObject).zzcej));
    for (;;)
    {
      return false;
      if (((zzbmx)paramObject).zzcei == null) {
        break label93;
      }
      label123:
      return false;
      label125:
      if (((zzbmx)paramObject).zzcej == null) {
        break;
      }
    }
  }
  
  public int hashCode()
  {
    int j = 0;
    int k = Long.valueOf(this.zzceh).hashCode();
    int m = Boolean.valueOf(this.zzcek).hashCode();
    int n = this.zzbXY.hashCode();
    if (this.zzcei != null) {}
    for (int i = this.zzcei.hashCode();; i = 0)
    {
      if (this.zzcej != null) {
        j = this.zzcej.hashCode();
      }
      return (i + ((k * 31 + m) * 31 + n) * 31) * 31 + j;
    }
  }
  
  public boolean isVisible()
  {
    return this.zzcek;
  }
  
  public String toString()
  {
    long l = this.zzceh;
    String str1 = String.valueOf(this.zzbXY);
    boolean bool = this.zzcek;
    String str2 = String.valueOf(this.zzcei);
    String str3 = String.valueOf(this.zzcej);
    return String.valueOf(str1).length() + 78 + String.valueOf(str2).length() + String.valueOf(str3).length() + "UserWriteRecord{id=" + l + " path=" + str1 + " visible=" + bool + " overwrite=" + str2 + " merge=" + str3 + "}";
  }
  
  public zzbmj zzVc()
  {
    return this.zzbXY;
  }
  
  public long zzXC()
  {
    return this.zzceh;
  }
  
  public zzbpe zzXD()
  {
    if (this.zzcei == null) {
      throw new IllegalArgumentException("Can't access overwrite when write is a merge!");
    }
    return this.zzcei;
  }
  
  public zzbma zzXE()
  {
    if (this.zzcej == null) {
      throw new IllegalArgumentException("Can't access merge when write is an overwrite!");
    }
    return this.zzcej;
  }
  
  public boolean zzXF()
  {
    return this.zzcei != null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */