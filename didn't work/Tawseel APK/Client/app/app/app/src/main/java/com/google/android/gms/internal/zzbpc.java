package com.google.android.gms.internal;

public class zzbpc
  extends zzbpb<zzbpc>
{
  private final long value;
  
  public zzbpc(Long paramLong, zzbpe paramzzbpe)
  {
    super(paramzzbpe);
    this.value = paramLong.longValue();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbpc)) {}
    do
    {
      return false;
      paramObject = (zzbpc)paramObject;
    } while ((this.value != ((zzbpc)paramObject).value) || (!this.zzcgQ.equals(((zzbpc)paramObject).zzcgQ)));
    return true;
  }
  
  public Object getValue()
  {
    return Long.valueOf(this.value);
  }
  
  public int hashCode()
  {
    return (int)(this.value ^ this.value >>> 32) + this.zzcgQ.hashCode();
  }
  
  protected zzbpb.zza zzYV()
  {
    return zzbpb.zza.zzchp;
  }
  
  protected int zza(zzbpc paramzzbpc)
  {
    return zzbqg.zzj(this.value, paramzzbpc.value);
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    paramzza = String.valueOf(String.valueOf(zzb(paramzza)).concat("number:"));
    String str = String.valueOf(zzbqg.zzl(this.value));
    if (str.length() != 0) {
      return paramzza.concat(str);
    }
    return new String(paramzza);
  }
  
  public zzbpc zzp(zzbpe paramzzbpe)
  {
    return new zzbpc(Long.valueOf(this.value), paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */