package com.google.android.gms.internal;

public class zzbpd
{
  private static final zzbpd zzchs = new zzbpd(zzbos.zzYW(), zzbox.zzZp());
  private static final zzbpd zzcht = new zzbpd(zzbos.zzYX(), zzbpe.zzchu);
  private final zzbos zzcfw;
  private final zzbpe zzchj;
  
  public zzbpd(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    this.zzcfw = paramzzbos;
    this.zzchj = paramzzbpe;
  }
  
  public static zzbpd zzZx()
  {
    return zzchs;
  }
  
  public static zzbpd zzZy()
  {
    return zzcht;
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
      paramObject = (zzbpd)paramObject;
      if (!this.zzcfw.equals(((zzbpd)paramObject).zzcfw)) {
        return false;
      }
    } while (this.zzchj.equals(((zzbpd)paramObject).zzchj));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzcfw.hashCode() * 31 + this.zzchj.hashCode();
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzcfw);
    String str2 = String.valueOf(this.zzchj);
    return String.valueOf(str1).length() + 23 + String.valueOf(str2).length() + "NamedNode{name=" + str1 + ", node=" + str2 + "}";
  }
  
  public zzbpe zzUY()
  {
    return this.zzchj;
  }
  
  public zzbos zzZz()
  {
    return this.zzcfw;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */