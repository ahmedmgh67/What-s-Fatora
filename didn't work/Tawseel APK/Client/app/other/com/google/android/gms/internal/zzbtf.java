package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class zzbtf<T>
  extends zzbsd<T>
{
  private final zzbsd<T> zzcmC;
  private final zzbrl zzcoy;
  private final Type zzcoz;
  
  zzbtf(zzbrl paramzzbrl, zzbsd<T> paramzzbsd, Type paramType)
  {
    this.zzcoy = paramzzbrl;
    this.zzcmC = paramzzbsd;
    this.zzcoz = paramType;
  }
  
  private Type zzb(Type paramType, Object paramObject)
  {
    Object localObject = paramType;
    if (paramObject != null) {
      if ((paramType != Object.class) && (!(paramType instanceof TypeVariable)))
      {
        localObject = paramType;
        if (!(paramType instanceof Class)) {}
      }
      else
      {
        localObject = paramObject.getClass();
      }
    }
    return (Type)localObject;
  }
  
  public void zza(zzbtk paramzzbtk, T paramT)
    throws IOException
  {
    zzbsd localzzbsd = this.zzcmC;
    Type localType = zzb(this.zzcoz, paramT);
    if (localType != this.zzcoz)
    {
      localzzbsd = this.zzcoy.zza(zzbth.zzl(localType));
      if ((localzzbsd instanceof zzbtc.zza)) {
        break label52;
      }
    }
    for (;;)
    {
      localzzbsd.zza(paramzzbtk, paramT);
      return;
      label52:
      if (!(this.zzcmC instanceof zzbtc.zza)) {
        localzzbsd = this.zzcmC;
      }
    }
  }
  
  public T zzb(zzbti paramzzbti)
    throws IOException
  {
    return (T)this.zzcmC.zzb(paramzzbti);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbtf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */