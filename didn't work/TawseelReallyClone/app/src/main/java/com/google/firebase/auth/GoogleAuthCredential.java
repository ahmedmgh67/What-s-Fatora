package com.google.firebase.auth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbjz;

public class GoogleAuthCredential
  extends AuthCredential
{
  private final String zzaix;
  private final String zzbBR;
  
  GoogleAuthCredential(@Nullable String paramString1, @Nullable String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }
    this.zzaix = zzaj(paramString1, "idToken");
    this.zzbBR = zzaj(paramString2, "accessToken");
  }
  
  public static zzbjz zza(@NonNull GoogleAuthCredential paramGoogleAuthCredential)
  {
    zzac.zzw(paramGoogleAuthCredential);
    return new zzbjz(paramGoogleAuthCredential.getIdToken(), paramGoogleAuthCredential.getAccessToken(), paramGoogleAuthCredential.getProvider(), null, null);
  }
  
  private static String zzaj(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (TextUtils.isEmpty(paramString1))) {
      throw new IllegalArgumentException(String.valueOf(paramString2).concat(" must not be empty"));
    }
    return paramString1;
  }
  
  @Nullable
  public String getAccessToken()
  {
    return this.zzbBR;
  }
  
  @Nullable
  public String getIdToken()
  {
    return this.zzaix;
  }
  
  public String getProvider()
  {
    return "google.com";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\GoogleAuthCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */