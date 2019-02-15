package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class zzbsv
  implements zzbse
{
  private final zzbsl zzcmu;
  
  public zzbsv(zzbsl paramzzbsl)
  {
    this.zzcmu = paramzzbsl;
  }
  
  public <T> zzbsd<T> zza(zzbrl paramzzbrl, zzbth<T> paramzzbth)
  {
    Type localType = paramzzbth.zzacc();
    Class localClass = paramzzbth.zzacb();
    if (!Collection.class.isAssignableFrom(localClass)) {
      return null;
    }
    localType = zzbsk.zza(localType, localClass);
    return new zza(paramzzbrl, localType, paramzzbrl.zza(zzbth.zzl(localType)), this.zzcmu.zzb(paramzzbth));
  }
  
  private static final class zza<E>
    extends zzbsd<Collection<E>>
  {
    private final zzbsd<E> zzcoa;
    private final zzbsq<? extends Collection<E>> zzcob;
    
    public zza(zzbrl paramzzbrl, Type paramType, zzbsd<E> paramzzbsd, zzbsq<? extends Collection<E>> paramzzbsq)
    {
      this.zzcoa = new zzbtf(paramzzbrl, paramzzbsd, paramType);
      this.zzcob = paramzzbsq;
    }
    
    public void zza(zzbtk paramzzbtk, Collection<E> paramCollection)
      throws IOException
    {
      if (paramCollection == null)
      {
        paramzzbtk.zzaca();
        return;
      }
      paramzzbtk.zzabW();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Object localObject = paramCollection.next();
        this.zzcoa.zza(paramzzbtk, localObject);
      }
      paramzzbtk.zzabX();
    }
    
    public Collection<E> zzj(zzbti paramzzbti)
      throws IOException
    {
      if (paramzzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramzzbti.nextNull();
        return null;
      }
      Collection localCollection = (Collection)this.zzcob.zzabJ();
      paramzzbti.beginArray();
      while (paramzzbti.hasNext()) {
        localCollection.add(this.zzcoa.zzb(paramzzbti));
      }
      paramzzbti.endArray();
      return localCollection;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */