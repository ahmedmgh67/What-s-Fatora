package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;

public class EmailAuthCredential
  extends AuthCredential
{
  private String zzaiW;
  private String zzaig;
  
  EmailAuthCredential(@NonNull String paramString1, @NonNull String paramString2)
  {
    this.zzaiW = zzac.zzdv(paramString1);
    this.zzaig = zzac.zzdv(paramString2);
  }
  
  @NonNull
  public String getEmail()
  {
    return this.zzaiW;
  }
  
  @NonNull
  public String getPassword()
  {
    return this.zzaig;
  }
  
  @NonNull
  public String getProvider()
  {
    return "password";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\EmailAuthCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */