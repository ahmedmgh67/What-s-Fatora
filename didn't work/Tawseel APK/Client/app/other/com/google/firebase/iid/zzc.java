package com.google.firebase.iid;

import android.support.annotation.Nullable;

@Deprecated
public class zzc
{
  private final FirebaseInstanceId zzciP;
  
  private zzc(FirebaseInstanceId paramFirebaseInstanceId)
  {
    this.zzciP = paramFirebaseInstanceId;
  }
  
  public static zzc zzaab()
  {
    return new zzc(FirebaseInstanceId.getInstance());
  }
  
  public String getId()
  {
    return this.zzciP.getId();
  }
  
  @Nullable
  public String getToken()
  {
    return this.zzciP.getToken();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\iid\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */