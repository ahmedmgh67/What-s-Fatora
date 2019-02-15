package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.List;

@Deprecated
public final class zzs
  extends zza
{
  public static final Parcelable.Creator<zzs> CREATOR = new zzt();
  public final String address;
  public final String name;
  public final int versionCode;
  public final String zzbmk;
  public final String zzbml;
  public final List<String> zzbmm;
  
  public zzs(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.address = paramString2;
    this.zzbmk = paramString3;
    this.zzbml = paramString4;
    this.zzbmm = paramList;
  }
  
  public static zzs zza(String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    return new zzs(0, paramString1, paramString2, paramString3, paramString4, paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzs)) {
        return false;
      }
      paramObject = (zzs)paramObject;
    } while ((zzaa.equal(this.name, ((zzs)paramObject).name)) && (zzaa.equal(this.address, ((zzs)paramObject).address)) && (zzaa.equal(this.zzbmk, ((zzs)paramObject).zzbmk)) && (zzaa.equal(this.zzbml, ((zzs)paramObject).zzbml)) && (zzaa.equal(this.zzbmm, ((zzs)paramObject).zzbmm)));
    return false;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.name, this.address, this.zzbmk, this.zzbml });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zzbmk).zzg("regularOpenHours", this.zzbml).zzg("attributions", this.zzbmm).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzt.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */