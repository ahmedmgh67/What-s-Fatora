package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzadq
  implements Parcelable.Creator<zzadp>
{
  static void zza(zzadp paramzzadp, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzadp.getVersionCode());
    zzc.zza(paramParcel, 2, paramzzadp.zzzd(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzadp zzbk(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    Bundle localBundle = null;
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
        localBundle = zzb.zzs(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzadp(i, localBundle);
  }
  
  public zzadp[] zzds(int paramInt)
  {
    return new zzadp[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */