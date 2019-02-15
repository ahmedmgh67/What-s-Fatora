package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public final class zzbsu<E>
  extends zzbsd<Object>
{
  public static final zzbse zzcnX = new zzbse()
  {
    public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
    {
      paramAnonymouszzbth = paramAnonymouszzbth.zzacc();
      if ((!(paramAnonymouszzbth instanceof GenericArrayType)) && ((!(paramAnonymouszzbth instanceof Class)) || (!((Class)paramAnonymouszzbth).isArray()))) {
        return null;
      }
      paramAnonymouszzbth = zzbsk.zzh(paramAnonymouszzbth);
      return new zzbsu(paramAnonymouszzbrl, paramAnonymouszzbrl.zza(zzbth.zzl(paramAnonymouszzbth)), zzbsk.zzf(paramAnonymouszzbth));
    }
  };
  private final Class<E> zzcnY;
  private final zzbsd<E> zzcnZ;
  
  public zzbsu(zzbrl paramzzbrl, zzbsd<E> paramzzbsd, Class<E> paramClass)
  {
    this.zzcnZ = new zzbtf(paramzzbrl, paramzzbsd, paramClass);
    this.zzcnY = paramClass;
  }
  
  public void zza(zzbtk paramzzbtk, Object paramObject)
    throws IOException
  {
    if (paramObject == null)
    {
      paramzzbtk.zzaca();
      return;
    }
    paramzzbtk.zzabW();
    int i = 0;
    int j = Array.getLength(paramObject);
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      this.zzcnZ.zza(paramzzbtk, localObject);
      i += 1;
    }
    paramzzbtk.zzabX();
  }
  
  public Object zzb(zzbti paramzzbti)
    throws IOException
  {
    if (paramzzbti.zzabQ() == zzbtj.zzcqa)
    {
      paramzzbti.nextNull();
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramzzbti.beginArray();
    while (paramzzbti.hasNext()) {
      localArrayList.add(this.zzcnZ.zzb(paramzzbti));
    }
    paramzzbti.endArray();
    paramzzbti = Array.newInstance(this.zzcnY, localArrayList.size());
    int i = 0;
    while (i < localArrayList.size())
    {
      Array.set(paramzzbti, i, localArrayList.get(i));
      i += 1;
    }
    return paramzzbti;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */