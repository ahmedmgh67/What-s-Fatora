package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public class zza
  implements Parcelable.Creator<GoogleMapOptions>
{
  static void zza(GoogleMapOptions paramGoogleMapOptions, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramGoogleMapOptions.getVersionCode());
    zzc.zza(paramParcel, 2, paramGoogleMapOptions.zzIA());
    zzc.zza(paramParcel, 3, paramGoogleMapOptions.zzIB());
    zzc.zzc(paramParcel, 4, paramGoogleMapOptions.getMapType());
    zzc.zza(paramParcel, 5, paramGoogleMapOptions.getCamera(), paramInt, false);
    zzc.zza(paramParcel, 6, paramGoogleMapOptions.zzIC());
    zzc.zza(paramParcel, 7, paramGoogleMapOptions.zzID());
    zzc.zza(paramParcel, 8, paramGoogleMapOptions.zzIE());
    zzc.zza(paramParcel, 9, paramGoogleMapOptions.zzIF());
    zzc.zza(paramParcel, 10, paramGoogleMapOptions.zzIG());
    zzc.zza(paramParcel, 11, paramGoogleMapOptions.zzIH());
    zzc.zza(paramParcel, 12, paramGoogleMapOptions.zzII());
    zzc.zza(paramParcel, 14, paramGoogleMapOptions.zzIJ());
    zzc.zza(paramParcel, 15, paramGoogleMapOptions.zzIK());
    zzc.zza(paramParcel, 16, paramGoogleMapOptions.getMinZoomPreference(), false);
    zzc.zza(paramParcel, 17, paramGoogleMapOptions.getMaxZoomPreference(), false);
    zzc.zza(paramParcel, 18, paramGoogleMapOptions.getLatLngBoundsForCameraTarget(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public GoogleMapOptions zzhr(Parcel paramParcel)
  {
    int k = zzb.zzaU(paramParcel);
    int j = 0;
    byte b11 = -1;
    byte b10 = -1;
    int i = 0;
    CameraPosition localCameraPosition = null;
    byte b9 = -1;
    byte b8 = -1;
    byte b7 = -1;
    byte b6 = -1;
    byte b5 = -1;
    byte b4 = -1;
    byte b3 = -1;
    byte b2 = -1;
    byte b1 = -1;
    Float localFloat2 = null;
    Float localFloat1 = null;
    LatLngBounds localLatLngBounds = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      case 13: 
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 2: 
        b11 = zzb.zze(paramParcel, m);
        break;
      case 3: 
        b10 = zzb.zze(paramParcel, m);
        break;
      case 4: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 5: 
        localCameraPosition = (CameraPosition)zzb.zza(paramParcel, m, CameraPosition.CREATOR);
        break;
      case 6: 
        b9 = zzb.zze(paramParcel, m);
        break;
      case 7: 
        b8 = zzb.zze(paramParcel, m);
        break;
      case 8: 
        b7 = zzb.zze(paramParcel, m);
        break;
      case 9: 
        b6 = zzb.zze(paramParcel, m);
        break;
      case 10: 
        b5 = zzb.zze(paramParcel, m);
        break;
      case 11: 
        b4 = zzb.zze(paramParcel, m);
        break;
      case 12: 
        b3 = zzb.zze(paramParcel, m);
        break;
      case 14: 
        b2 = zzb.zze(paramParcel, m);
        break;
      case 15: 
        b1 = zzb.zze(paramParcel, m);
        break;
      case 16: 
        localFloat2 = zzb.zzm(paramParcel, m);
        break;
      case 17: 
        localFloat1 = zzb.zzm(paramParcel, m);
        break;
      case 18: 
        localLatLngBounds = (LatLngBounds)zzb.zza(paramParcel, m, LatLngBounds.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new GoogleMapOptions(j, b11, b10, i, localCameraPosition, b9, b8, b7, b6, b5, b4, b3, b2, b1, localFloat2, localFloat1, localLatLngBounds);
  }
  
  public GoogleMapOptions[] zzkV(int paramInt)
  {
    return new GoogleMapOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */