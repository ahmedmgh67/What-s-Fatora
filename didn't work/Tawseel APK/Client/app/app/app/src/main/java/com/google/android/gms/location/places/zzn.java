package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzn
  implements Parcelable.Creator<zzm>
{
  static void zza(zzm paramzzm, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 2, paramzzm.zzHM(), paramInt, false);
    zzc.zza(paramParcel, 3, paramzzm.getInterval());
    zzc.zzc(paramParcel, 4, paramzzm.getPriority());
    zzc.zza(paramParcel, 5, paramzzm.getExpirationTime());
    zzc.zza(paramParcel, 6, paramzzm.zzHT());
    zzc.zza(paramParcel, 7, paramzzm.zzHU());
    zzc.zzc(paramParcel, 1000, paramzzm.mVersionCode);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzm zzhc(Parcel paramParcel)
  {
    boolean bool1 = false;
    int k = zzb.zzaU(paramParcel);
    PlaceFilter localPlaceFilter = null;
    long l2 = -1L;
    int i = -1;
    long l1 = Long.MAX_VALUE;
    boolean bool2 = false;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 2: 
        localPlaceFilter = (PlaceFilter)zzb.zza(paramParcel, m, PlaceFilter.CREATOR);
        break;
      case 3: 
        l2 = zzb.zzi(paramParcel, m);
        break;
      case 4: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 5: 
        l1 = zzb.zzi(paramParcel, m);
        break;
      case 6: 
        bool2 = zzb.zzc(paramParcel, m);
        break;
      case 7: 
        bool1 = zzb.zzc(paramParcel, m);
        break;
      case 1000: 
        j = zzb.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zzm(j, localPlaceFilter, l2, i, l1, bool2, bool1);
  }
  
  public zzm[] zzkE(int paramInt)
  {
    return new zzm[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */