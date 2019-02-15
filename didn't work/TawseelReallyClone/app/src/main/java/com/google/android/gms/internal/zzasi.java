package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;

@Deprecated
public class zzasi
  extends zza
{
  public static final Parcelable.Creator<zzasi> CREATOR = new zzasj();
  public static final zzasi zzbmK = new zzasi(0, "Home");
  public static final zzasi zzbmL = new zzasi(0, "Work");
  final int mVersionCode;
  private final String zzbmM;
  
  zzasi(int paramInt, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzbmM = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzasi)) {
      return false;
    }
    paramObject = (zzasi)paramObject;
    return zzaa.equal(this.zzbmM, ((zzasi)paramObject).zzbmM);
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzbmM });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("alias", this.zzbmM).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasj.zza(this, paramParcel, paramInt);
  }
  
  public String zzIt()
  {
    return this.zzbmM;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */