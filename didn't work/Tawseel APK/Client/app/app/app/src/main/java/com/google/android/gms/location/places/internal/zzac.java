package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzac
  implements Parcelable.Creator<zza.zza>
{
  static void zza(zza.zza paramzza, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzza.mOffset);
    zzc.zzc(paramParcel, 2, paramzza.mLength);
    zzc.zzc(paramParcel, 1000, paramzza.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zza.zza zzhm(Parcel paramParcel)
  {
    int k = 0;
    int m = zzb.zzaU(paramParcel);
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(n))
      {
      default: 
        zzb.zzb(paramParcel, n);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, n);
        break;
      case 2: 
        k = zzb.zzg(paramParcel, n);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new zza.zza(i, j, k);
  }
  
  public zza.zza[] zzkP(int paramInt)
  {
    return new zza.zza[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */