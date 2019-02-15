package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzacn
  implements Parcelable.Creator<zzaco.zzb>
{
  static void zza(zzaco.zzb paramzzb, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzb.versionCode);
    zzc.zza(paramParcel, 2, paramzzb.zzaA, false);
    zzc.zza(paramParcel, 3, paramzzb.zzaFO, paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzaco.zzb zzbb(Parcel paramParcel)
  {
    zzack.zza localzza = null;
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
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        str = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        localzza = (zzack.zza)zzb.zza(paramParcel, k, zzack.zza.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzaco.zzb(i, str, localzza);
  }
  
  public zzaco.zzb[] zzdd(int paramInt)
  {
    return new zzaco.zzb[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */