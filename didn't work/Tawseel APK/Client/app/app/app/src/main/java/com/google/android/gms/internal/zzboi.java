package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zzboi
{
  private final Map<zzbos, zzbny> zzcgw = new HashMap();
  
  static
  {
    if (!zzboi.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public List<zzbny> zzYO()
  {
    return new ArrayList(this.zzcgw.values());
  }
  
  public void zza(zzbny paramzzbny)
  {
    zzboa.zza localzza1 = paramzzbny.zzYl();
    zzbos localzzbos = paramzzbny.zzYk();
    assert ((localzza1 == zzboa.zza.zzcfN) || (localzza1 == zzboa.zza.zzcfP) || (localzza1 == zzboa.zza.zzcfM)) : "Only child changes supported for tracking";
    assert (!paramzzbny.zzYk().zzZa());
    if (this.zzcgw.containsKey(localzzbos))
    {
      Object localObject = (zzbny)this.zzcgw.get(localzzbos);
      zzboa.zza localzza2 = ((zzbny)localObject).zzYl();
      if ((localzza1 == zzboa.zza.zzcfN) && (localzza2 == zzboa.zza.zzcfM))
      {
        this.zzcgw.put(paramzzbny.zzYk(), zzbny.zza(localzzbos, paramzzbny.zzYi(), ((zzbny)localObject).zzYi()));
        return;
      }
      if ((localzza1 == zzboa.zza.zzcfM) && (localzza2 == zzboa.zza.zzcfN))
      {
        this.zzcgw.remove(localzzbos);
        return;
      }
      if ((localzza1 == zzboa.zza.zzcfM) && (localzza2 == zzboa.zza.zzcfP))
      {
        this.zzcgw.put(localzzbos, zzbny.zzb(localzzbos, ((zzbny)localObject).zzYn()));
        return;
      }
      if ((localzza1 == zzboa.zza.zzcfP) && (localzza2 == zzboa.zza.zzcfN))
      {
        this.zzcgw.put(localzzbos, zzbny.zza(localzzbos, paramzzbny.zzYi()));
        return;
      }
      if ((localzza1 == zzboa.zza.zzcfP) && (localzza2 == zzboa.zza.zzcfP))
      {
        this.zzcgw.put(localzzbos, zzbny.zza(localzzbos, paramzzbny.zzYi(), ((zzbny)localObject).zzYn()));
        return;
      }
      paramzzbny = String.valueOf(paramzzbny);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(paramzzbny).length() + 48 + String.valueOf(localObject).length() + "Illegal combination of changes: " + paramzzbny + " occurred after " + (String)localObject);
    }
    this.zzcgw.put(paramzzbny.zzYk(), paramzzbny);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */