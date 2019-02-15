package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbjz;

public class FacebookAuthCredential
  extends AuthCredential
{
  private final String zzbBR;
  
  FacebookAuthCredential(@NonNull String paramString)
  {
    this.zzbBR = zzac.zzdv(paramString);
  }
  
  public static zzbjz zza(@NonNull FacebookAuthCredential paramFacebookAuthCredential)
  {
    zzac.zzw(paramFacebookAuthCredential);
    return new zzbjz(null, paramFacebookAuthCredential.getAccessToken(), paramFacebookAuthCredential.getProvider(), null, null);
  }
  
  public String getAccessToken()
  {
    return this.zzbBR;
  }
  
  public String getProvider()
  {
    return "facebook.com";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\FacebookAuthCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */