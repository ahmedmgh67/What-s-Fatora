package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class UserProfileChangeRequest
  extends com.google.android.gms.common.internal.safeparcel.zza
{
  public static final Parcelable.Creator<UserProfileChangeRequest> CREATOR = new zza();
  public final int mVersionCode;
  private String zzaPq;
  private String zzaiX;
  private boolean zzbVK;
  private boolean zzbVL;
  private Uri zzbVM;
  
  UserProfileChangeRequest(int paramInt, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzaiX = paramString1;
    this.zzaPq = paramString2;
    this.zzbVK = paramBoolean1;
    this.zzbVL = paramBoolean2;
    if (TextUtils.isEmpty(paramString2)) {}
    for (paramString1 = null;; paramString1 = Uri.parse(paramString2))
    {
      this.zzbVM = paramString1;
      return;
    }
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzaiX;
  }
  
  @Nullable
  public Uri getPhotoUri()
  {
    return this.zzbVM;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public String zzUb()
  {
    return this.zzaPq;
  }
  
  public boolean zzUc()
  {
    return this.zzbVK;
  }
  
  public boolean zzUd()
  {
    return this.zzbVL;
  }
  
  public static class Builder
  {
    private String zzaiX;
    private boolean zzbVK;
    private boolean zzbVL;
    private Uri zzbVM;
    
    public UserProfileChangeRequest build()
    {
      String str2 = this.zzaiX;
      if (this.zzbVM == null) {}
      for (String str1 = null;; str1 = this.zzbVM.toString()) {
        return new UserProfileChangeRequest(1, str2, str1, this.zzbVK, this.zzbVL);
      }
    }
    
    public Builder setDisplayName(@Nullable String paramString)
    {
      if (paramString == null)
      {
        this.zzbVK = true;
        return this;
      }
      this.zzaiX = paramString;
      return this;
    }
    
    public Builder setPhotoUri(@Nullable Uri paramUri)
    {
      if (paramUri == null)
      {
        this.zzbVL = true;
        return this;
      }
      this.zzbVM = paramUri;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\UserProfileChangeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */