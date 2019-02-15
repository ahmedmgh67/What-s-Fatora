package com.google.firebase.remoteconfig;

public abstract interface FirebaseRemoteConfigInfo
{
  public abstract FirebaseRemoteConfigSettings getConfigSettings();
  
  public abstract long getFetchTimeMillis();
  
  public abstract int getLastFetchStatus();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\remoteconfig\FirebaseRemoteConfigInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */