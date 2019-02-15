package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbjz;

public class GithubAuthCredential
  extends AuthCredential
{
  private String zzahI;
  
  GithubAuthCredential(@NonNull String paramString)
  {
    this.zzahI = zzac.zzdv(paramString);
  }
  
  public static zzbjz zza(@NonNull GithubAuthCredential paramGithubAuthCredential)
  {
    zzac.zzw(paramGithubAuthCredential);
    return new zzbjz(null, paramGithubAuthCredential.getToken(), paramGithubAuthCredential.getProvider(), null, null);
  }
  
  public String getProvider()
  {
    return "github.com";
  }
  
  public String getToken()
  {
    return this.zzahI;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\GithubAuthCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */