package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class zzbke
  implements AuthResult
{
  private zzbkh zzbWJ;
  
  public zzbke(@NonNull zzbkh paramzzbkh)
  {
    this.zzbWJ = ((zzbkh)zzac.zzw(paramzzbkh));
  }
  
  @Nullable
  public FirebaseUser getUser()
  {
    return this.zzbWJ;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */