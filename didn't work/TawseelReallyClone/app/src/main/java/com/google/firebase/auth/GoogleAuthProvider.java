package com.google.firebase.auth;

import android.support.annotation.Nullable;

public class GoogleAuthProvider
{
  public static final String PROVIDER_ID = "google.com";
  
  public static AuthCredential getCredential(@Nullable String paramString1, @Nullable String paramString2)
  {
    return new GoogleAuthCredential(paramString1, paramString2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\GoogleAuthProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */