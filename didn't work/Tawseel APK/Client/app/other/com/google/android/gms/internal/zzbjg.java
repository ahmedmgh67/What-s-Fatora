package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.io.IOException;
import java.util.List;

public class zzbjg
  extends zzbsd<zzbjt>
{
  private zzbrl zzbVW;
  
  public void zza(@NonNull zzbrl paramzzbrl)
  {
    this.zzbVW = ((zzbrl)zzac.zzw(paramzzbrl));
  }
  
  public void zza(zzbtk paramzzbtk, zzbjt paramzzbjt)
    throws IOException
  {
    int j = 0;
    if (paramzzbjt == null)
    {
      paramzzbtk.zzaca();
      return;
    }
    zzbsd localzzbsd = this.zzbVW.zzj(zzbjr.class);
    paramzzbtk.zzabW();
    paramzzbjt = paramzzbjt.zzUp();
    int i;
    if (paramzzbjt != null) {
      i = paramzzbjt.size();
    }
    while (j < i)
    {
      localzzbsd.zza(paramzzbtk, (zzbjr)paramzzbjt.get(j));
      j += 1;
      continue;
      i = 0;
    }
    paramzzbtk.zzabX();
  }
  
  public zzbjt zzc(zzbti paramzzbti)
    throws IOException
  {
    if (paramzzbti.zzabQ() == zzbtj.zzcqa)
    {
      paramzzbti.nextNull();
      return null;
    }
    zzbjt localzzbjt = new zzbjt();
    zzbsd localzzbsd = this.zzbVW.zzj(zzbjr.class);
    paramzzbti.beginArray();
    while (paramzzbti.hasNext())
    {
      zzbjr localzzbjr = (zzbjr)localzzbsd.zzb(paramzzbti);
      localzzbjt.zzUp().add(localzzbjr);
    }
    paramzzbti.endArray();
    return localzzbjt;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */