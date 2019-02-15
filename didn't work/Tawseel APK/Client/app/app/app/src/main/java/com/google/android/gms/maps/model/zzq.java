package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzq
  implements Parcelable.Creator<VisibleRegion>
{
  static void zza(VisibleRegion paramVisibleRegion, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramVisibleRegion.getVersionCode());
    zzc.zza(paramParcel, 2, paramVisibleRegion.nearLeft, paramInt, false);
    zzc.zza(paramParcel, 3, paramVisibleRegion.nearRight, paramInt, false);
    zzc.zza(paramParcel, 4, paramVisibleRegion.farLeft, paramInt, false);
    zzc.zza(paramParcel, 5, paramVisibleRegion.farRight, paramInt, false);
    zzc.zza(paramParcel, 6, paramVisibleRegion.latLngBounds, paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public VisibleRegion zzhJ(Parcel paramParcel)
  {
    LatLngBounds localLatLngBounds = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    LatLng localLatLng1 = null;
    LatLng localLatLng2 = null;
    LatLng localLatLng3 = null;
    LatLng localLatLng4 = null;
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
        localLatLng4 = (LatLng)zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 3: 
        localLatLng3 = (LatLng)zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 4: 
        localLatLng2 = (LatLng)zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 5: 
        localLatLng1 = (LatLng)zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 6: 
        localLatLngBounds = (LatLngBounds)zzb.zza(paramParcel, k, LatLngBounds.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new VisibleRegion(i, localLatLng4, localLatLng3, localLatLng2, localLatLng1, localLatLngBounds);
  }
  
  public VisibleRegion[] zzln(int paramInt)
  {
    return new VisibleRegion[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */