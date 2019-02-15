package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzbjr
  extends zza
{
  public static final Parcelable.Creator<zzbjr> CREATOR = new zzbjs();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("photoUrl")
  private String zzaPq;
  @zzbsg("displayName")
  private String zzaiX;
  @zzbsg("providerId")
  private String zzbWp;
  @zzbsg("federatedId")
  private String zzbWy;
  @zzbsg("rawUserInfo")
  private String zzbWz;
  
  public zzbjr()
  {
    this.mVersionCode = 1;
  }
  
  zzbjr(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.mVersionCode = paramInt;
    this.zzbWy = paramString1;
    this.zzaiX = paramString2;
    this.zzaPq = paramString3;
    this.zzbWp = paramString4;
    this.zzbWz = paramString5;
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzaiX;
  }
  
  @Nullable
  public Uri getPhotoUri()
  {
    if (!TextUtils.isEmpty(this.zzaPq)) {
      return Uri.parse(this.zzaPq);
    }
    return null;
  }
  
  public String getProviderId()
  {
    return this.zzbWp;
  }
  
  @Nullable
  public String getRawUserInfo()
  {
    return this.zzbWz;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjs.zza(this, paramParcel, paramInt);
  }
  
  @Nullable
  public String zzUb()
  {
    return this.zzaPq;
  }
  
  public String zzUw()
  {
    return this.zzbWy;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */