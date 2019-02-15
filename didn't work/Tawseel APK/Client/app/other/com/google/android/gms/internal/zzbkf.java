package com.google.android.gms.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.auth.UserInfo;

public class zzbkf
  implements UserInfo
{
  @zzbsg("photoUrl")
  @Nullable
  private String zzaPq;
  @zzbsg("userId")
  @NonNull
  private String zzach;
  @zzbsg("email")
  @Nullable
  private String zzaiW;
  @zzbsg("displayName")
  @Nullable
  private String zzaiX;
  @zzbjd
  @Nullable
  private Uri zzbVM;
  @zzbsg("providerId")
  @NonNull
  private String zzbWp;
  @zzbsg("isEmailVerified")
  private boolean zzbWs;
  @zzbsg("rawUserInfo")
  @Nullable
  private String zzbWz;
  
  public zzbkf(@NonNull zzbjl paramzzbjl, @NonNull String paramString)
  {
    zzac.zzw(paramzzbjl);
    zzac.zzdv(paramString);
    this.zzach = zzac.zzdv(paramzzbjl.getLocalId());
    this.zzbWp = paramString;
    this.zzaiW = paramzzbjl.getEmail();
    this.zzaiX = paramzzbjl.getDisplayName();
    paramString = paramzzbjl.getPhotoUri();
    if (paramString != null)
    {
      this.zzaPq = paramString.toString();
      this.zzbVM = paramString;
    }
    this.zzbWs = paramzzbjl.isEmailVerified();
    this.zzbWz = null;
  }
  
  public zzbkf(@NonNull zzbjr paramzzbjr)
  {
    zzac.zzw(paramzzbjr);
    this.zzach = zzac.zzdv(paramzzbjr.zzUw());
    this.zzbWp = zzac.zzdv(paramzzbjr.getProviderId());
    this.zzaiX = paramzzbjr.getDisplayName();
    Uri localUri = paramzzbjr.getPhotoUri();
    if (localUri != null)
    {
      this.zzaPq = localUri.toString();
      this.zzbVM = localUri;
    }
    this.zzaiW = null;
    this.zzbWs = false;
    this.zzbWz = paramzzbjr.getRawUserInfo();
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzaiX;
  }
  
  @Nullable
  public String getEmail()
  {
    return this.zzaiW;
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    if ((!TextUtils.isEmpty(this.zzaPq)) && (this.zzbVM == null)) {
      this.zzbVM = Uri.parse(this.zzaPq);
    }
    return this.zzbVM;
  }
  
  @NonNull
  public String getProviderId()
  {
    return this.zzbWp;
  }
  
  @NonNull
  public String getUid()
  {
    return this.zzach;
  }
  
  public boolean isEmailVerified()
  {
    return this.zzbWs;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */