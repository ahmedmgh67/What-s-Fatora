package com.google.firebase.remoteconfig;

public class FirebaseRemoteConfigFetchThrottledException
  extends FirebaseRemoteConfigFetchException
{
  private final long zzaHx;
  
  public FirebaseRemoteConfigFetchThrottledException(long paramLong)
  {
    this.zzaHx = paramLong;
  }
  
  public long getThrottleEndTimeMillis()
  {
    return this.zzaHx;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\remoteconfig\FirebaseRemoteConfigFetchThrottledException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */