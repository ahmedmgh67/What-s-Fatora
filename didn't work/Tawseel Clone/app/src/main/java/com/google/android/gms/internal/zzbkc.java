package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.GithubAuthCredential;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.TwitterAuthCredential;

public class zzbkc
{
  @NonNull
  public static zzbjz zza(@NonNull AuthCredential paramAuthCredential)
  {
    zzac.zzw(paramAuthCredential);
    if (GoogleAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass())) {
      return GoogleAuthCredential.zza((GoogleAuthCredential)paramAuthCredential);
    }
    if (FacebookAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass())) {
      return FacebookAuthCredential.zza((FacebookAuthCredential)paramAuthCredential);
    }
    if (TwitterAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass())) {
      return TwitterAuthCredential.zza((TwitterAuthCredential)paramAuthCredential);
    }
    if (GithubAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass())) {
      return GithubAuthCredential.zza((GithubAuthCredential)paramAuthCredential);
    }
    throw new IllegalArgumentException("Unsupported credential type.");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */