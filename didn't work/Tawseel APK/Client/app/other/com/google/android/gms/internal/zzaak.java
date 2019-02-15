package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import java.util.Collections;
import java.util.Queue;

public class zzaak
  implements zzaam
{
  private final zzaan zzazK;
  
  public zzaak(zzaan paramzzaan)
  {
    this.zzazK = paramzzaan;
  }
  
  public void begin()
  {
    this.zzazK.zzvQ();
    this.zzazK.zzazd.zzaAs = Collections.emptySet();
  }
  
  public void connect()
  {
    this.zzazK.zzvO();
  }
  
  public boolean disconnect()
  {
    return true;
  }
  
  public void onConnected(Bundle paramBundle) {}
  
  public void onConnectionSuspended(int paramInt) {}
  
  public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(T paramT)
  {
    this.zzazK.zzazd.zzaAl.add(paramT);
    return paramT;
  }
  
  public void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt) {}
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(T paramT)
  {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */