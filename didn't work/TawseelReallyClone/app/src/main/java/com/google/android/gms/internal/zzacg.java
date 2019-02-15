package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzacg
  implements Parcelable.Creator<zzacf>
{
  static void zza(zzacf paramzzacf, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzacf.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzacf.zzxH(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzacf zzaX(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    zzach localzzach = null;
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
        localzzach = (zzach)zzb.zza(paramParcel, k, zzach.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzacf(i, localzzach);
  }
  
  public zzacf[] zzcZ(int paramInt)
  {
    return new zzacf[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */