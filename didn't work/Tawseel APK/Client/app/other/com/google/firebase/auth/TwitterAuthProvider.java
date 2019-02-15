package com.google.firebase.auth;

import android.support.annotation.NonNull;

public class TwitterAuthProvider
{
  public static final String PROVIDER_ID = "twitter.com";
  
  public static AuthCredential getCredential(@NonNull String paramString1, @NonNull String paramString2)
  {
    return new TwitterAuthCredential(paramString1, paramString2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\TwitterAuthProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */