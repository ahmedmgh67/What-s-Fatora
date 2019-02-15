package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzase
  implements Parcelable.Creator<zzasd>
{
  static void zza(zzasd paramzzasd, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzasd.getPlaceId(), false);
    zzc.zzb(paramParcel, 2, paramzzasd.zzIr(), false);
    zzc.zzc(paramParcel, 1000, paramzzasd.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzasd zzhn(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
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
      case 2: 
        localArrayList = zzb.zzE(paramParcel, k);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzasd(i, str, localArrayList);
  }
  
  public zzasd[] zzkQ(int paramInt)
  {
    return new zzasd[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */