package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzg
  implements Parcelable.Creator<zzf>
{
  static void zza(zzf paramzzf, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzf.zzHH());
    zzc.zzc(paramParcel, 2, paramzzf.zzHL());
    zzc.zza(paramParcel, 3, paramzzf.zzHM(), paramInt, false);
    zzc.zza(paramParcel, 4, paramzzf.zzHN(), paramInt, false);
    zzc.zza(paramParcel, 5, paramzzf.zzHO());
    zzc.zzc(paramParcel, 6, paramzzf.zzHP());
    zzc.zzc(paramParcel, 7, paramzzf.getPriority());
    zzc.zzc(paramParcel, 1000, paramzzf.getVersionCode());
    zzc.zzJ(paramParcel, i);
  }
  
  public zzf zzgX(Parcel paramParcel)
  {
    zzd localzzd = null;
    int j = 0;
    int i1 = zzb.zzaU(paramParcel);
    int k = -1;
    int i = 110;
    boolean bool = false;
    PlaceFilter localPlaceFilter = null;
    int m = 0;
    int n = 0;
    while (paramParcel.dataPosition() < i1)
    {
      int i2 = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(i2))
      {
      default: 
        zzb.zzb(paramParcel, i2);
        break;
      case 1: 
        m = zzb.zzg(paramParcel, i2);
        break;
      case 2: 
        k = zzb.zzg(paramParcel, i2);
        break;
      case 3: 
        localPlaceFilter = (PlaceFilter)zzb.zza(paramParcel, i2, PlaceFilter.CREATOR);
        break;
      case 4: 
        localzzd = (zzd)zzb.zza(paramParcel, i2, zzd.CREATOR);
        break;
      case 5: 
        bool = zzb.zzc(paramParcel, i2);
        break;
      case 6: 
        j = zzb.zzg(paramParcel, i2);
        break;
      case 7: 
        i = zzb.zzg(paramParcel, i2);
        break;
      case 1000: 
        n = zzb.zzg(paramParcel, i2);
      }
    }
    if (paramParcel.dataPosition() != i1) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i1, paramParcel);
    }
    return new zzf(n, m, k, localPlaceFilter, localzzd, bool, j, i);
  }
  
  public zzf[] zzky(int paramInt)
  {
    return new zzf[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */