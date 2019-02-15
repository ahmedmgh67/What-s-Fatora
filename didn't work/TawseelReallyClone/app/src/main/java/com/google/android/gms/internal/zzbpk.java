package com.google.android.gms.internal;

public class zzbpk
  extends zzbpb<zzbpk>
{
  private final String value;
  
  public zzbpk(String paramString, zzbpe paramzzbpe)
  {
    super(paramzzbpe);
    this.value = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbpk)) {}
    do
    {
      return false;
      paramObject = (zzbpk)paramObject;
    } while ((!this.value.equals(((zzbpk)paramObject).value)) || (!this.zzcgQ.equals(((zzbpk)paramObject).zzcgQ)));
    return true;
  }
  
  public Object getValue()
  {
    return this.value;
  }
  
  public int hashCode()
  {
    return this.value.hashCode() + this.zzcgQ.hashCode();
  }
  
  protected zzbpb.zza zzYV()
  {
    return zzbpb.zza.zzchq;
  }
  
  protected int zza(zzbpk paramzzbpk)
  {
    return this.value.compareTo(paramzzbpk.value);
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    switch (1.zzchm[paramzza.ordinal()])
    {
    default: 
      paramzza = String.valueOf(paramzza);
      throw new IllegalArgumentException(String.valueOf(paramzza).length() + 38 + "Invalid hash version for string node: " + paramzza);
    case 1: 
      paramzza = String.valueOf(zzb(paramzza));
      str = this.value;
      return String.valueOf(paramzza).length() + 7 + String.valueOf(str).length() + paramzza + "string:" + str;
    }
    paramzza = String.valueOf(zzb(paramzza));
    String str = String.valueOf(zzbqg.zzjj(this.value));
    return String.valueOf(paramzza).length() + 7 + String.valueOf(str).length() + paramzza + "string:" + str;
  }
  
  public zzbpk zzs(zzbpe paramzzbpe)
  {
    return new zzbpk(this.value, paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */