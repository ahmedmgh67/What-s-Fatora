package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;

public final class zzbjc
{
  public static final Api.zzf<zzbiv> zzahc = new Api.zzf();
  private static final Api.zza<zzbiv, zza> zzbVX = new Api.zza()
  {
    public zzbiv zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzg paramAnonymouszzg, zzbjc.zza paramAnonymouszza, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzbiw(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzg, paramAnonymouszza, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  public static final Api<zza> zzbVY = new Api("InternalFirebaseAuth.FIREBASE_AUTH_API", zzbVX, zzahc);
  
  public static zzbiu zza(Context paramContext, zza paramzza)
  {
    return new zzbiu(paramContext, paramzza);
  }
  
  public static final class zza
    implements Api.ApiOptions.HasOptions
  {
    private final String zzbUL;
    
    private zza(@NonNull String paramString)
    {
      this.zzbUL = zzac.zzh(paramString, "A valid API key must be provided");
    }
    
    public String getApiKey()
    {
      return this.zzbUL;
    }
    
    public static final class zza
    {
      private String zzbUL;
      
      public zza(@NonNull String paramString)
      {
        this.zzbUL = zzac.zzdv(paramString);
      }
      
      public zzbjc.zza zzUj()
      {
        return new zzbjc.zza(this.zzbUL, null);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */