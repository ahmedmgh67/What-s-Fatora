package com.google.android.gms.internal;

import java.lang.reflect.Type;

public class zzbth<T>
{
  final Type zzcoz;
  final Class<? super T> zzcpD;
  final int zzcpE;
  
  protected zzbth()
  {
    this.zzcoz = zzp(getClass());
    this.zzcpD = zzbsk.zzf(this.zzcoz);
    this.zzcpE = this.zzcoz.hashCode();
  }
  
  zzbth(Type paramType)
  {
    this.zzcoz = zzbsk.zze((Type)zzbsj.zzw(paramType));
    this.zzcpD = zzbsk.zzf(this.zzcoz);
    this.zzcpE = this.zzcoz.hashCode();
  }
  
  public static zzbth<?> zzl(Type paramType)
  {
    return new zzbth(paramType);
  }
  
  static Type zzp(Class<?> paramClass)
  {
    paramClass = paramClass.getGenericSuperclass();
    if ((paramClass instanceof Class)) {
      throw new RuntimeException("Missing type parameter.");
    }
    return zzbsk.zze(((java.lang.reflect.ParameterizedType)paramClass).getActualTypeArguments()[0]);
  }
  
  public static <T> zzbth<T> zzq(Class<T> paramClass)
  {
    return new zzbth(paramClass);
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof zzbth)) && (zzbsk.zza(this.zzcoz, ((zzbth)paramObject).zzcoz));
  }
  
  public final int hashCode()
  {
    return this.zzcpE;
  }
  
  public final String toString()
  {
    return zzbsk.zzg(this.zzcoz);
  }
  
  public final Class<? super T> zzacb()
  {
    return this.zzcpD;
  }
  
  public final Type zzacc()
  {
    return this.zzcoz;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */