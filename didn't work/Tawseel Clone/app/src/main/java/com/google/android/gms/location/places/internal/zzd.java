package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzd
  implements Parcelable.Creator<zzu.zza>
{
  static void zza(zzu.zza paramzza, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzza.zzbmp);
    zzc.zzc(paramParcel, 2, paramzza.zzbmq);
    zzc.zzc(paramParcel, 1000, paramzza.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzu.zza zzhf(Parcel paramParcel)
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
    return new zzu.zza(i, j, k);
  }
  
  public zzu.zza[] zzkH(int paramInt)
  {
    return new zzu.zza[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */