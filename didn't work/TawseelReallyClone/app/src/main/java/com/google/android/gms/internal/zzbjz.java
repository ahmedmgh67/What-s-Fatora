package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public class zzbjz
  extends zza
{
  public static final Parcelable.Creator<zzbjz> CREATOR = new zzbka();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("postBody")
  private String zzHF;
  @zzbjd
  @Nullable
  private String zzaiW;
  @zzbjd
  private String zzaix;
  @zzbjd
  private String zzbBR;
  @zzbsg("requestUri")
  private String zzbWE;
  @zzbsg("idToken")
  private String zzbWF;
  @zzbsg("oauthTokenSecret")
  private String zzbWG;
  @zzbsg("returnSecureToken")
  private boolean zzbWH;
  @zzbjd
  private String zzbWp;
  
  public zzbjz()
  {
    this.mVersionCode = 2;
    this.zzbWH = true;
  }
  
  zzbjz(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    this.zzbWE = paramString1;
    this.zzbWF = paramString2;
    this.zzaix = paramString3;
    this.zzbBR = paramString4;
    this.zzbWp = paramString5;
    this.zzaiW = paramString6;
    this.zzHF = paramString7;
    this.zzbWG = paramString8;
    this.zzbWH = paramBoolean;
  }
  
  public zzbjz(@Nullable String paramString1, @Nullable String paramString2, String paramString3, @Nullable String paramString4, @Nullable String paramString5)
  {
    this.mVersionCode = 2;
    this.zzbWE = "http://localhost";
    this.zzaix = paramString1;
    this.zzbBR = paramString2;
    this.zzbWG = paramString5;
    this.zzbWH = true;
    if ((TextUtils.isEmpty(this.zzaix)) && (TextUtils.isEmpty(this.zzbBR))) {
      throw new IllegalArgumentException("Both idToken, and accessToken cannot be null");
    }
    this.zzbWp = zzac.zzdv(paramString3);
    this.zzaiW = paramString4;
    paramString1 = new StringBuilder();
    if (!TextUtils.isEmpty(this.zzaix)) {
      paramString1.append("id_token").append("=").append(this.zzaix).append("&");
    }
    if (!TextUtils.isEmpty(this.zzbBR)) {
      paramString1.append("access_token").append("=").append(this.zzbBR).append("&");
    }
    if (!TextUtils.isEmpty(this.zzaiW)) {
      paramString1.append("identifier").append("=").append(this.zzaiW).append("&");
    }
    if (!TextUtils.isEmpty(this.zzbWG)) {
      paramString1.append("oauth_token_secret").append("=").append(this.zzbWG).append("&");
    }
    paramString1.append("providerId").append("=").append(this.zzbWp);
    this.zzHF = paramString1.toString();
  }
  
  public String getAccessToken()
  {
    return this.zzbBR;
  }
  
  @Nullable
  public String getEmail()
  {
    return this.zzaiW;
  }
  
  public String getIdToken()
  {
    return this.zzaix;
  }
  
  public String getProviderId()
  {
    return this.zzbWp;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbka.zza(this, paramParcel, paramInt);
  }
  
  public String zzUC()
  {
    return this.zzbWE;
  }
  
  public String zzUD()
  {
    return this.zzbWF;
  }
  
  public String zzUE()
  {
    return this.zzbWG;
  }
  
  public boolean zzUF()
  {
    return this.zzbWH;
  }
  
  public String zzgc()
  {
    return this.zzHF;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */