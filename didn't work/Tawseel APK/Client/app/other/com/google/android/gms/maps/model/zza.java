package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zza
  implements Parcelable.Creator<CameraPosition>
{
  static void zza(CameraPosition paramCameraPosition, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramCameraPosition.getVersionCode());
    zzc.zza(paramParcel, 2, paramCameraPosition.target, paramInt, false);
    zzc.zza(paramParcel, 3, paramCameraPosition.zoom);
    zzc.zza(paramParcel, 4, paramCameraPosition.tilt);
    zzc.zza(paramParcel, 5, paramCameraPosition.bearing);
    zzc.zzJ(paramParcel, i);
  }
  
  public CameraPosition zzht(Parcel paramParcel)
  {
    float f1 = 0.0F;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    LatLng localLatLng = null;
    float f2 = 0.0F;
    float f3 = 0.0F;
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
        localLatLng = (LatLng)zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 3: 
        f3 = zzb.zzl(paramParcel, k);
        break;
      case 4: 
        f2 = zzb.zzl(paramParcel, k);
        break;
      case 5: 
        f1 = zzb.zzl(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new CameraPosition(i, localLatLng, f3, f2, f1);
  }
  
  public CameraPosition[] zzkX(int paramInt)
  {
    return new CameraPosition[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */