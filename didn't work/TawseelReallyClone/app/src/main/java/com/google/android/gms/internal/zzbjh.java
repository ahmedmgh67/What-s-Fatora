package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.io.IOException;
import java.util.List;

public class zzbjh
  extends zzbsd<zzbjx>
{
  private zzbrl zzbVW;
  
  public void zza(@NonNull zzbrl paramzzbrl)
  {
    this.zzbVW = ((zzbrl)zzac.zzw(paramzzbrl));
  }
  
  public void zza(zzbtk paramzzbtk, zzbjx paramzzbjx)
    throws IOException
  {
    int j = 0;
    if (paramzzbjx == null)
    {
      paramzzbtk.zzaca();
      return;
    }
    zzbsd localzzbsd = this.zzbVW.zzj(String.class);
    paramzzbtk.zzabW();
    paramzzbjx = paramzzbjx.zzUA();
    int i;
    if (paramzzbjx != null) {
      i = paramzzbjx.size();
    }
    while (j < i)
    {
      localzzbsd.zza(paramzzbtk, (String)paramzzbjx.get(j));
      j += 1;
      continue;
      i = 0;
    }
    paramzzbtk.zzabX();
  }
  
  public zzbjx zzd(zzbti paramzzbti)
    throws IOException
  {
    if (paramzzbti.zzabQ() == zzbtj.zzcqa)
    {
      paramzzbti.nextNull();
      return null;
    }
    zzbjx localzzbjx = new zzbjx();
    zzbsd localzzbsd = this.zzbVW.zzj(String.class);
    paramzzbti.beginArray();
    while (paramzzbti.hasNext())
    {
      String str = (String)localzzbsd.zzb(paramzzbti);
      localzzbjx.zzUA().add(str);
    }
    paramzzbti.endArray();
    return localzzbjx;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */