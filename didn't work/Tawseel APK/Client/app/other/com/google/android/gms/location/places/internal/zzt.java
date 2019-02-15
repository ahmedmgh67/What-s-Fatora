package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzt
  implements Parcelable.Creator<zzs>
{
  static void zza(zzs paramzzs, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzs.name, false);
    zzc.zza(paramParcel, 2, paramzzs.address, false);
    zzc.zza(paramParcel, 3, paramzzs.zzbmk, false);
    zzc.zza(paramParcel, 4, paramzzs.zzbml, false);
    zzc.zzb(paramParcel, 5, paramzzs.zzbmm, false);
    zzc.zzc(paramParcel, 1000, paramzzs.versionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzs zzhj(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        str4 = zzb.zzq(paramParcel, k);
        break;
      case 2: 
        str3 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        localArrayList = zzb.zzE(paramParcel, k);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzs(i, str4, str3, str2, str1, localArrayList);
  }
  
  public zzs[] zzkM(int paramInt)
  {
    return new zzs[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */