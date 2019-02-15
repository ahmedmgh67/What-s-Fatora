package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class zzbqr
  implements FirebaseRemoteConfigInfo
{
  private long zzcjO;
  private int zzcjP;
  private FirebaseRemoteConfigSettings zzcjQ;
  
  public FirebaseRemoteConfigSettings getConfigSettings()
  {
    return this.zzcjQ;
  }
  
  public long getFetchTimeMillis()
  {
    return this.zzcjO;
  }
  
  public int getLastFetchStatus()
  {
    return this.zzcjP;
  }
  
  public void setConfigSettings(FirebaseRemoteConfigSettings paramFirebaseRemoteConfigSettings)
  {
    this.zzcjQ = paramFirebaseRemoteConfigSettings;
  }
  
  public void zzaR(long paramLong)
  {
    this.zzcjO = paramLong;
  }
  
  public void zzpV(int paramInt)
  {
    this.zzcjP = paramInt;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */