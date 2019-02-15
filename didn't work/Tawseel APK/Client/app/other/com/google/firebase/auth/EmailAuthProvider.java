package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;

public class EmailAuthProvider
{
  public static final String PROVIDER_ID = "password";
  
  public static AuthCredential getCredential(@NonNull String paramString1, @NonNull String paramString2)
  {
    zzac.zzdv(paramString1);
    zzac.zzdv(paramString2);
    return new EmailAuthCredential(paramString1, paramString2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\EmailAuthProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */