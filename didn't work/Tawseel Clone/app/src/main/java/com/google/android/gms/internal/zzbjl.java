package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

public class zzbjl
  extends zza
{
  public static final Parcelable.Creator<zzbjl> CREATOR = new zzbjm();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("localId")
  private String zzaMA;
  @zzbsg("photoUrl")
  private String zzaPq;
  @zzbsg("email")
  private String zzaiW;
  @zzbsg("displayName")
  private String zzaiX;
  @zzbsg("passwordHash")
  private String zzaig;
  @zzbsg("emailVerified")
  private boolean zzbWs;
  @zzbsg("providerUserInfo")
  private zzbjt zzbWt;
  
  public zzbjl()
  {
    this.mVersionCode = 1;
    this.zzbWt = new zzbjt();
  }
  
  zzbjl(int paramInt, String paramString1, String paramString2, boolean paramBoolean, String paramString3, String paramString4, zzbjt paramzzbjt, String paramString5)
  {
    this.mVersionCode = paramInt;
    this.zzaMA = paramString1;
    this.zzaiW = paramString2;
    this.zzbWs = paramBoolean;
    this.zzaiX = paramString3;
    this.zzaPq = paramString4;
    if (paramzzbjt == null) {}
    for (paramString1 = zzbjt.zzUx();; paramString1 = zzbjt.zza(paramzzbjt))
    {
      this.zzbWt = paramString1;
      this.zzaig = paramString5;
      return;
    }
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
  
  @NonNull
  public String getLocalId()
  {
    return this.zzaMA;
  }
  
  @Nullable
  public String getPassword()
  {
    return this.zzaig;
  }
  
  @Nullable
  public Uri getPhotoUri()
  {
    if (!TextUtils.isEmpty(this.zzaPq)) {
      return Uri.parse(this.zzaPq);
    }
    return null;
  }
  
  public boolean isEmailVerified()
  {
    return this.zzbWs;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjm.zza(this, paramParcel, paramInt);
  }
  
  @Nullable
  public String zzUb()
  {
    return this.zzaPq;
  }
  
  @NonNull
  public List<zzbjr> zzUp()
  {
    return this.zzbWt.zzUp();
  }
  
  public zzbjt zzUq()
  {
    return this.zzbWt;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */