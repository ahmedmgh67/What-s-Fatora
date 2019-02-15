package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzbtb
  extends zzbsd<Object>
{
  public static final zzbse zzcnX = new zzbse()
  {
    public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
    {
      if (paramAnonymouszzbth.zzacb() == Object.class) {
        return new zzbtb(paramAnonymouszzbrl, null);
      }
      return null;
    }
  };
  private final zzbrl zzcmT;
  
  private zzbtb(zzbrl paramzzbrl)
  {
    this.zzcmT = paramzzbrl;
  }
  
  public void zza(zzbtk paramzzbtk, Object paramObject)
    throws IOException
  {
    if (paramObject == null)
    {
      paramzzbtk.zzaca();
      return;
    }
    zzbsd localzzbsd = this.zzcmT.zzj(paramObject.getClass());
    if ((localzzbsd instanceof zzbtb))
    {
      paramzzbtk.zzabY();
      paramzzbtk.zzabZ();
      return;
    }
    localzzbsd.zza(paramzzbtk, paramObject);
  }
  
  public Object zzb(zzbti paramzzbti)
    throws IOException
  {
    Object localObject = paramzzbti.zzabQ();
    switch (2.zzcon[localObject.ordinal()])
    {
    default: 
      throw new IllegalStateException();
    case 1: 
      localObject = new ArrayList();
      paramzzbti.beginArray();
      while (paramzzbti.hasNext()) {
        ((List)localObject).add(zzb(paramzzbti));
      }
      paramzzbti.endArray();
      return localObject;
    case 2: 
      localObject = new zzbsp();
      paramzzbti.beginObject();
      while (paramzzbti.hasNext()) {
        ((Map)localObject).put(paramzzbti.nextName(), zzb(paramzzbti));
      }
      paramzzbti.endObject();
      return localObject;
    case 3: 
      return paramzzbti.nextString();
    case 4: 
      return Double.valueOf(paramzzbti.nextDouble());
    case 5: 
      return Boolean.valueOf(paramzzbti.nextBoolean());
    }
    paramzzbti.nextNull();
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbtb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */