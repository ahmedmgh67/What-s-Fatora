package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzacs
  implements Parcelable.Creator<zzacr>
{
  static void zza(zzacr paramzzacr, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzacr.getVersionCode());
    zzc.zza(paramParcel, 2, paramzzacr.zzya(), false);
    zzc.zza(paramParcel, 3, paramzzacr.zzyb(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzacr zzbe(Parcel paramParcel)
  {
    zzaco localzzaco = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    Parcel localParcel = null;
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
        localParcel = zzb.zzF(paramParcel, k);
        break;
      case 3: 
        localzzaco = (zzaco)zzb.zza(paramParcel, k, zzaco.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzacr(i, localParcel, localzzaco);
  }
  
  public zzacr[] zzdg(int paramInt)
  {
    return new zzacr[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */