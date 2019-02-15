package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzac;

public class zzzy
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  public final Api<?> zzawb;
  private final int zzazb;
  private zzzz zzazc;
  
  public zzzy(Api<?> paramApi, int paramInt)
  {
    this.zzawb = paramApi;
    this.zzazb = paramInt;
  }
  
  private void zzvi()
  {
    zzac.zzb(this.zzazc, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
  }
  
  public void onConnected(@Nullable Bundle paramBundle)
  {
    zzvi();
    this.zzazc.onConnected(paramBundle);
  }
  
  public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
  {
    zzvi();
    this.zzazc.zza(paramConnectionResult, this.zzawb, this.zzazb);
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    zzvi();
    this.zzazc.onConnectionSuspended(paramInt);
  }
  
  public void zza(zzzz paramzzzz)
  {
    this.zzazc = paramzzzz;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */