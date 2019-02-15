package com.google.firebase.auth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class FirebaseAuthWeakPasswordException
  extends FirebaseAuthInvalidCredentialsException
{
  private final String zzHI;
  
  public FirebaseAuthWeakPasswordException(@NonNull String paramString1, @NonNull String paramString2, @Nullable String paramString3)
  {
    super(paramString1, paramString2);
    this.zzHI = paramString3;
  }
  
  @Nullable
  public String getReason()
  {
    return this.zzHI;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\FirebaseAuthWeakPasswordException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */