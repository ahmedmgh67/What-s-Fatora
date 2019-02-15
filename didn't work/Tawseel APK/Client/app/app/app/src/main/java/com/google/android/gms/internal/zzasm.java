package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzasm
  implements Parcelable.Creator<zzask>
{
  static void zza(zzask paramzzask, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzask.zzIu(), false);
    zzc.zza(paramParcel, 2, paramzzask.getPlaceId(), false);
    zzc.zzc(paramParcel, 6, paramzzask.zzIr(), false);
    zzc.zzc(paramParcel, 1000, paramzzask.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzask zzhq(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 2: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 6: 
        localArrayList = zzb.zzc(paramParcel, k, zzasi.CREATOR);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzask(i, str1, str2, localArrayList);
  }
  
  public zzask[] zzkT(int paramInt)
  {
    return new zzask[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */