package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import java.util.Iterator;
import java.util.Set;

public abstract class zzl<T extends IInterface>
  extends zzf<T>
  implements Api.zze, zzm.zza
{
  private final Account zzagg;
  private final Set<Scope> zzajm;
  private final zzg zzazs;
  
  protected zzl(Context paramContext, Looper paramLooper, int paramInt, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, zzn.zzaC(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramzzg, (GoogleApiClient.ConnectionCallbacks)zzac.zzw(paramConnectionCallbacks), (GoogleApiClient.OnConnectionFailedListener)zzac.zzw(paramOnConnectionFailedListener));
  }
  
  protected zzl(Context paramContext, Looper paramLooper, zzn paramzzn, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramzzn, paramGoogleApiAvailability, paramInt, zza(paramConnectionCallbacks), zza(paramOnConnectionFailedListener), paramzzg.zzxi());
    this.zzazs = paramzzg;
    this.zzagg = paramzzg.getAccount();
    this.zzajm = zzb(paramzzg.zzxf());
  }
  
  @Nullable
  private static zzf.zzb zza(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    if (paramConnectionCallbacks == null) {
      return null;
    }
    new zzf.zzb()
    {
      public void onConnected(@Nullable Bundle paramAnonymousBundle)
      {
        zzl.this.onConnected(paramAnonymousBundle);
      }
      
      public void onConnectionSuspended(int paramAnonymousInt)
      {
        zzl.this.onConnectionSuspended(paramAnonymousInt);
      }
    };
  }
  
  @Nullable
  private static zzf.zzc zza(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    if (paramOnConnectionFailedListener == null) {
      return null;
    }
    new zzf.zzc()
    {
      public void onConnectionFailed(@NonNull ConnectionResult paramAnonymousConnectionResult)
      {
        zzl.this.onConnectionFailed(paramAnonymousConnectionResult);
      }
    };
  }
  
  private Set<Scope> zzb(@NonNull Set<Scope> paramSet)
  {
    Set localSet = zzc(paramSet);
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext()) {
      if (!paramSet.contains((Scope)localIterator.next())) {
        throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
      }
    }
    return localSet;
  }
  
  public final Account getAccount()
  {
    return this.zzagg;
  }
  
  @NonNull
  protected Set<Scope> zzc(@NonNull Set<Scope> paramSet)
  {
    return paramSet;
  }
  
  protected final Set<Scope> zzwY()
  {
    return this.zzajm;
  }
  
  protected final zzg zzxp()
  {
    return this.zzazs;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */