package com.google.android.gms.internal;

public class zzbor
  extends zzbpb<zzbor>
{
  private final boolean value;
  
  public zzbor(Boolean paramBoolean, zzbpe paramzzbpe)
  {
    super(paramzzbpe);
    this.value = paramBoolean.booleanValue();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbor)) {}
    do
    {
      return false;
      paramObject = (zzbor)paramObject;
    } while ((this.value != ((zzbor)paramObject).value) || (!this.zzcgQ.equals(((zzbor)paramObject).zzcgQ)));
    return true;
  }
  
  public Object getValue()
  {
    return Boolean.valueOf(this.value);
  }
  
  public int hashCode()
  {
    if (this.value) {}
    for (int i = 1;; i = 0) {
      return i + this.zzcgQ.hashCode();
    }
  }
  
  protected zzbpb.zza zzYV()
  {
    return zzbpb.zza.zzcho;
  }
  
  protected int zza(zzbor paramzzbor)
  {
    if (this.value == paramzzbor.value) {
      return 0;
    }
    if (this.value) {
      return 1;
    }
    return -1;
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    paramzza = String.valueOf(zzb(paramzza));
    boolean bool = this.value;
    return String.valueOf(paramzza).length() + 13 + paramzza + "boolean:" + bool;
  }
  
  public zzbor zzf(zzbpe paramzzbpe)
  {
    return new zzbor(Boolean.valueOf(this.value), paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */