package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzblp;

class zzf
  extends zza
{
  public static final Parcelable.Creator<zzf> CREATOR = new zzg();
  final int versionCode;
  final String zzaFs;
  final String zzbZA;
  final boolean zzbZB;
  
  public zzf(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.versionCode = paramInt;
    this.zzbZA = paramString1;
    this.zzaFs = paramString2;
    this.zzbZB = paramBoolean;
  }
  
  public static zzblp zza(zzf paramzzf)
  {
    return new zzblp(paramzzf.zzbZA, paramzzf.zzaFs, paramzzf.zzbZB);
  }
  
  public static zzf zza(zzblp paramzzblp)
  {
    return new zzf(1, paramzzblp.getHost(), paramzzblp.getNamespace(), paramzzblp.isSecure());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */