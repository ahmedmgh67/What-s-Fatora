package com.google.firebase.auth;

import android.support.annotation.NonNull;

public class FacebookAuthProvider
{
  public static final String PROVIDER_ID = "facebook.com";
  
  public static AuthCredential getCredential(@NonNull String paramString)
  {
    return new FacebookAuthCredential(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\FacebookAuthProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */