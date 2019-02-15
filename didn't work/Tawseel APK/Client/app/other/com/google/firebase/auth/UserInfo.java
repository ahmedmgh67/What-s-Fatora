package com.google.firebase.auth;

import android.net.Uri;
import android.support.annotation.Nullable;

public abstract interface UserInfo
{
  @Nullable
  public abstract String getDisplayName();
  
  @Nullable
  public abstract String getEmail();
  
  @Nullable
  public abstract Uri getPhotoUrl();
  
  public abstract String getProviderId();
  
  public abstract String getUid();
  
  public abstract boolean isEmailVerified();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\UserInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */