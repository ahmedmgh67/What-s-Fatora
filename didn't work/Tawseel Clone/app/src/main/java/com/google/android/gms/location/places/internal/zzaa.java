package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzaa
  implements Parcelable.Creator<zzz>
{
  static void zza(zzz paramzzz, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzz.zzbmE, false);
    zzc.zza(paramParcel, 2, paramzzz.zzbmF, false);
    zzc.zza(paramParcel, 3, paramzzz.zzaIv, false);
    zzc.zza(paramParcel, 4, paramzzz.zzblu, false);
    zzc.zzc(paramParcel, 6, paramzzz.zzbmG);
    zzc.zzc(paramParcel, 7, paramzzz.zzbmH);
    zzc.zzc(paramParcel, 1000, paramzzz.versionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzz zzhl(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int m = zzb.zzaU(paramParcel);
    int j = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(n))
      {
      default: 
        zzb.zzb(paramParcel, n);
        break;
      case 1: 
        str4 = zzb.zzq(paramParcel, n);
        break;
      case 2: 
        str3 = zzb.zzq(paramParcel, n);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, n);
        break;
      case 4: 
        str1 = zzb.zzq(paramParcel, n);
        break;
      case 6: 
        j = zzb.zzg(paramParcel, n);
        break;
      case 7: 
        i = zzb.zzg(paramParcel, n);
        break;
      case 1000: 
        k = zzb.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new zzz(k, str4, str3, str2, str1, j, i);
  }
  
  public zzz[] zzkO(int paramInt)
  {
    return new zzz[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */