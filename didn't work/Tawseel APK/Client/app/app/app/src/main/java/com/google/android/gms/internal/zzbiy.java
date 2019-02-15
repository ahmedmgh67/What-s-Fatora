package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.io.IOException;
import java.util.List;

public class zzbiy
  extends zzbsd<zzbjn>
{
  private zzbrl zzbVW;
  
  public zzbjn zza(zzbti paramzzbti)
    throws IOException
  {
    if (paramzzbti.zzabQ() == zzbtj.zzcqa)
    {
      paramzzbti.nextNull();
      return null;
    }
    zzbjn localzzbjn = new zzbjn();
    zzbsd localzzbsd = this.zzbVW.zzj(zzbjl.class);
    paramzzbti.beginArray();
    while (paramzzbti.hasNext())
    {
      zzbjl localzzbjl = (zzbjl)localzzbsd.zzb(paramzzbti);
      localzzbjn.zzUr().add(localzzbjl);
    }
    paramzzbti.endArray();
    return localzzbjn;
  }
  
  public void zza(@NonNull zzbrl paramzzbrl)
  {
    this.zzbVW = ((zzbrl)zzac.zzw(paramzzbrl));
  }
  
  public void zza(zzbtk paramzzbtk, zzbjn paramzzbjn)
    throws IOException
  {
    int j = 0;
    if (paramzzbjn == null)
    {
      paramzzbtk.zzaca();
      return;
    }
    zzbsd localzzbsd = this.zzbVW.zzj(zzbjl.class);
    paramzzbtk.zzabW();
    paramzzbjn = paramzzbjn.zzUr();
    int i;
    if (paramzzbjn != null) {
      i = paramzzbjn.size();
    }
    while (j < i)
    {
      localzzbsd.zza(paramzzbtk, (zzbjl)paramzzbjn.get(j));
      j += 1;
      continue;
      i = 0;
    }
    paramzzbtk.zzabX();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbiy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */