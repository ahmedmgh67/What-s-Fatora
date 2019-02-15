package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzg
  implements Parcelable.Creator<zzf>
{
  static void zza(zzf paramzzf, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzf.versionCode);
    zzc.zza(paramParcel, 2, paramzzf.zzbZA, false);
    zzc.zza(paramParcel, 3, paramzzf.zzaFs, false);
    zzc.zza(paramParcel, 4, paramzzf.zzbZB);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzf zzli(Parcel paramParcel)
  {
    String str2 = null;
    boolean bool = false;
    int j = zzb.zzaU(paramParcel);
    String str1 = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        bool = zzb.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzf(i, str1, str2, bool);
  }
  
  public zzf[] zzpM(int paramInt)
  {
    return new zzf[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */