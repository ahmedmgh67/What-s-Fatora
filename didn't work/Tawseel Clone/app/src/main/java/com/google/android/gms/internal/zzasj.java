package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzasj
  implements Parcelable.Creator<zzasi>
{
  static void zza(zzasi paramzzasi, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzasi.zzIt(), false);
    zzc.zzc(paramParcel, 1000, paramzzasi.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzasi zzhp(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        str = zzb.zzq(paramParcel, k);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzasi(i, str);
  }
  
  public zzasi[] zzkS(int paramInt)
  {
    return new zzasi[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */