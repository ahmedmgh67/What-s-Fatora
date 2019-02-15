package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbjq
  implements Parcelable.Creator<zzbjp>
{
  static void zza(zzbjp paramzzbjp, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjp.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzbjp.zzUs(), false);
    zzc.zza(paramParcel, 3, paramzzbjp.getAccessToken(), false);
    zzc.zza(paramParcel, 4, Long.valueOf(paramzzbjp.zzUt()), false);
    zzc.zza(paramParcel, 5, paramzzbjp.zzUu(), false);
    zzc.zza(paramParcel, 6, Long.valueOf(paramzzbjp.zzUv()), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzbjp zzkZ(Parcel paramParcel)
  {
    Long localLong1 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str1 = null;
    Long localLong2 = null;
    String str2 = null;
    String str3 = null;
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
        str3 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        localLong2 = zzb.zzj(paramParcel, k);
        break;
      case 5: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 6: 
        localLong1 = zzb.zzj(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjp(i, str3, str2, localLong2, str1, localLong1);
  }
  
  public zzbjp[] zzpB(int paramInt)
  {
    return new zzbjp[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */