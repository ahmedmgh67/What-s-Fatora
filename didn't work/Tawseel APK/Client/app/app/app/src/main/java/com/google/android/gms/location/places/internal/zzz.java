package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.Locale;

public class zzz
  extends zza
{
  public static final Parcelable.Creator<zzz> CREATOR = new zzaa();
  public static final zzz zzbmD = new zzz("com.google.android.gms", Locale.getDefault(), null);
  public final int versionCode;
  public final String zzaIv;
  public final String zzblu;
  public final String zzbmE;
  public final String zzbmF;
  public final int zzbmG;
  public final int zzbmH;
  
  public zzz(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3)
  {
    this.versionCode = paramInt1;
    this.zzbmE = paramString1;
    this.zzbmF = paramString2;
    this.zzaIv = paramString3;
    this.zzblu = paramString4;
    this.zzbmG = paramInt2;
    this.zzbmH = paramInt3;
  }
  
  public zzz(String paramString1, Locale paramLocale, String paramString2)
  {
    this(3, paramString1, paramLocale.toString(), paramString2, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
  }
  
  public zzz(String paramString1, Locale paramLocale, String paramString2, String paramString3, int paramInt)
  {
    this(3, paramString1, paramLocale.toString(), paramString2, paramString3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof zzz))) {
        return false;
      }
      paramObject = (zzz)paramObject;
    } while ((this.zzbmG == ((zzz)paramObject).zzbmG) && (this.zzbmH == ((zzz)paramObject).zzbmH) && (this.zzbmF.equals(((zzz)paramObject).zzbmF)) && (this.zzbmE.equals(((zzz)paramObject).zzbmE)) && (com.google.android.gms.common.internal.zzaa.equal(this.zzaIv, ((zzz)paramObject).zzaIv)) && (com.google.android.gms.common.internal.zzaa.equal(this.zzblu, ((zzz)paramObject).zzblu)));
    return false;
  }
  
  public int hashCode()
  {
    return com.google.android.gms.common.internal.zzaa.hashCode(new Object[] { this.zzbmE, this.zzbmF, this.zzaIv, this.zzblu, Integer.valueOf(this.zzbmG), Integer.valueOf(this.zzbmH) });
  }
  
  @SuppressLint({"DefaultLocale"})
  public String toString()
  {
    return com.google.android.gms.common.internal.zzaa.zzv(this).zzg("clientPackageName", this.zzbmE).zzg("locale", this.zzbmF).zzg("accountName", this.zzaIv).zzg("gCoreClientName", this.zzblu).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzaa.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */