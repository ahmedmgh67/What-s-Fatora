package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzq
  implements Parcelable.Creator<zzp>
{
  static void zza(zzp paramzzp, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzp.zzTW, false);
    zzc.zzc(paramParcel, 2, paramzzp.zzblB);
    zzc.zzc(paramParcel, 1000, paramzzp.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzp zzhd(Parcel paramParcel)
  {
    int j = 0;
    int k = zzb.zzaU(paramParcel);
    String str = null;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        str = zzb.zzq(paramParcel, m);
        break;
      case 2: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zzp(i, str, j);
  }
  
  public zzp[] zzkF(int paramInt)
  {
    return new zzp[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */