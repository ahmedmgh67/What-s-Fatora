package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzadi
  implements Parcelable.Creator<zzadh>
{
  static void zza(zzadh paramzzadh, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzadh.getVersionCode());
    zzc.zza(paramParcel, 2, paramzzadh.getName(), false);
    zzc.zza(paramParcel, 3, paramzzadh.getValue(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzadh zzbh(Parcel paramParcel)
  {
    String str2 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
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
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzadh(i, str1, str2);
  }
  
  public zzadh[] zzdp(int paramInt)
  {
    return new zzadh[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */