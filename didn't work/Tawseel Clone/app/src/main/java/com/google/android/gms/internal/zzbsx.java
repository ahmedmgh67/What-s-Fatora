package com.google.android.gms.internal;

public final class zzbsx
  implements zzbse
{
  private final zzbsl zzcmu;
  
  public zzbsx(zzbsl paramzzbsl)
  {
    this.zzcmu = paramzzbsl;
  }
  
  static zzbsd<?> zza(zzbsl paramzzbsl, zzbrl paramzzbrl, zzbth<?> paramzzbth, zzbsf paramzzbsf)
  {
    paramzzbsf = paramzzbsf.value();
    if (zzbsd.class.isAssignableFrom(paramzzbsf)) {
      return (zzbsd)paramzzbsl.zzb(zzbth.zzq(paramzzbsf)).zzabJ();
    }
    if (zzbse.class.isAssignableFrom(paramzzbsf)) {
      return ((zzbse)paramzzbsl.zzb(zzbth.zzq(paramzzbsf)).zzabJ()).zza(paramzzbrl, paramzzbth);
    }
    throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
  }
  
  public <T> zzbsd<T> zza(zzbrl paramzzbrl, zzbth<T> paramzzbth)
  {
    zzbsf localzzbsf = (zzbsf)paramzzbth.zzacb().getAnnotation(zzbsf.class);
    if (localzzbsf == null) {
      return null;
    }
    return zza(this.zzcmu, paramzzbrl, paramzzbth, localzzbsf);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */