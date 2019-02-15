package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzg;

public final class zzaab<O extends Api.ApiOptions>
  extends zzc<O>
{
  private final Api.zza<? extends zzaxn, zzaxo> zzaxY;
  private final Api.zze zzazq;
  private final zzzy zzazr;
  private final zzg zzazs;
  
  public zzaab(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper, @NonNull Api.zze paramzze, @NonNull zzzy paramzzzy, zzg paramzzg, Api.zza<? extends zzaxn, zzaxo> paramzza)
  {
    super(paramContext, paramApi, paramLooper);
    this.zzazq = paramzze;
    this.zzazr = paramzzzy;
    this.zzazs = paramzzg;
    this.zzaxY = paramzza;
    this.zzaxK.zza(this);
  }
  
  public Api.zze buildApiClient(Looper paramLooper, zzaap.zza<O> paramzza)
  {
    this.zzazr.zza(paramzza);
    return this.zzazq;
  }
  
  public zzabj createSignInCoordinator(Context paramContext, Handler paramHandler)
  {
    return new zzabj(paramContext, paramHandler, this.zzazs, this.zzaxY);
  }
  
  public Api.zze zzvr()
  {
    return this.zzazq;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */