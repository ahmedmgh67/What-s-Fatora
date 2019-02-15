package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class zzb
  implements Parcelable.Creator<StreetViewPanoramaOptions>
{
  static void zza(StreetViewPanoramaOptions paramStreetViewPanoramaOptions, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramStreetViewPanoramaOptions.getVersionCode());
    zzc.zza(paramParcel, 2, paramStreetViewPanoramaOptions.getStreetViewPanoramaCamera(), paramInt, false);
    zzc.zza(paramParcel, 3, paramStreetViewPanoramaOptions.getPanoramaId(), false);
    zzc.zza(paramParcel, 4, paramStreetViewPanoramaOptions.getPosition(), paramInt, false);
    zzc.zza(paramParcel, 5, paramStreetViewPanoramaOptions.getRadius(), false);
    zzc.zza(paramParcel, 6, paramStreetViewPanoramaOptions.zzIN());
    zzc.zza(paramParcel, 7, paramStreetViewPanoramaOptions.zzIF());
    zzc.zza(paramParcel, 8, paramStreetViewPanoramaOptions.zzIO());
    zzc.zza(paramParcel, 9, paramStreetViewPanoramaOptions.zzIP());
    zzc.zza(paramParcel, 10, paramStreetViewPanoramaOptions.zzIB());
    zzc.zzJ(paramParcel, i);
  }
  
  public StreetViewPanoramaOptions zzhs(Parcel paramParcel)
  {
    Integer localInteger = null;
    byte b1 = 0;
    int j = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(paramParcel);
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    byte b5 = 0;
    LatLng localLatLng = null;
    String str = null;
    StreetViewPanoramaCamera localStreetViewPanoramaCamera = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, k);
        break;
      case 2: 
        localStreetViewPanoramaCamera = (StreetViewPanoramaCamera)com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, k, StreetViewPanoramaCamera.CREATOR);
        break;
      case 3: 
        str = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, k);
        break;
      case 4: 
        localLatLng = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 5: 
        localInteger = com.google.android.gms.common.internal.safeparcel.zzb.zzh(paramParcel, k);
        break;
      case 6: 
        b5 = com.google.android.gms.common.internal.safeparcel.zzb.zze(paramParcel, k);
        break;
      case 7: 
        b4 = com.google.android.gms.common.internal.safeparcel.zzb.zze(paramParcel, k);
        break;
      case 8: 
        b3 = com.google.android.gms.common.internal.safeparcel.zzb.zze(paramParcel, k);
        break;
      case 9: 
        b2 = com.google.android.gms.common.internal.safeparcel.zzb.zze(paramParcel, k);
        break;
      case 10: 
        b1 = com.google.android.gms.common.internal.safeparcel.zzb.zze(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new StreetViewPanoramaOptions(i, localStreetViewPanoramaCamera, str, localLatLng, localInteger, b5, b4, b3, b2, b1);
  }
  
  public StreetViewPanoramaOptions[] zzkW(int paramInt)
  {
    return new StreetViewPanoramaOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */