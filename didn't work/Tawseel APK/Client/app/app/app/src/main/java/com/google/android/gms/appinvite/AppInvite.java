package com.google.android.gms.appinvite;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzug;
import com.google.android.gms.internal.zzuh;

public final class AppInvite
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("AppInvite.API", zzahd, zzahc);
  public static final AppInviteApi AppInviteApi = new zzug();
  public static final Api.zzf<zzuh> zzahc = new Api.zzf();
  private static final Api.zza<zzuh, Api.ApiOptions.NoOptions> zzahd = new Api.zza()
  {
    public zzuh zzb(Context paramAnonymousContext, Looper paramAnonymousLooper, zzg paramAnonymouszzg, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzuh(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymouszzg);
    }
  };
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\appinvite\AppInvite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */