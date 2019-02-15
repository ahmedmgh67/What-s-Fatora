package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.zzc;

public class zzadf
  extends zzc<Api.ApiOptions.NoOptions>
{
  public zzadf(Context paramContext)
  {
    super(paramContext, zzacy.API, null, new zzzr());
  }
  
  public PendingResult<zzacz.zzb> zza(zzacz.zza paramzza)
  {
    return zzacy.zzaHm.zza(asGoogleApiClient(), paramzza);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */