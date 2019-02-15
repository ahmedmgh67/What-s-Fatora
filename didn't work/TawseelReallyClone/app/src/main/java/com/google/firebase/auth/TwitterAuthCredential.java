package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbjz;

public class TwitterAuthCredential
  extends AuthCredential
{
  private String zzahI;
  private String zzbVJ;
  
  TwitterAuthCredential(@NonNull String paramString1, @NonNull String paramString2)
  {
    this.zzahI = zzac.zzdv(paramString1);
    this.zzbVJ = zzac.zzdv(paramString2);
  }
  
  public static zzbjz zza(@NonNull TwitterAuthCredential paramTwitterAuthCredential)
  {
    zzac.zzw(paramTwitterAuthCredential);
    return new zzbjz(null, paramTwitterAuthCredential.getToken(), paramTwitterAuthCredential.getProvider(), null, paramTwitterAuthCredential.zzUa());
  }
  
  public String getProvider()
  {
    return "twitter.com";
  }
  
  @NonNull
  public String getToken()
  {
    return this.zzahI;
  }
  
  @NonNull
  public String zzUa()
  {
    return this.zzbVJ;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\TwitterAuthCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */