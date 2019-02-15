package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;

public class zzc
  implements Parcelable.Creator<GroundOverlayOptions>
{
  static void zza(GroundOverlayOptions paramGroundOverlayOptions, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzc.zzaV(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzc.zzc(paramParcel, 1, paramGroundOverlayOptions.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 2, paramGroundOverlayOptions.zzIT(), false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 3, paramGroundOverlayOptions.getLocation(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 4, paramGroundOverlayOptions.getWidth());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 5, paramGroundOverlayOptions.getHeight());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 6, paramGroundOverlayOptions.getBounds(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 7, paramGroundOverlayOptions.getBearing());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 8, paramGroundOverlayOptions.getZIndex());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 9, paramGroundOverlayOptions.isVisible());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 10, paramGroundOverlayOptions.getTransparency());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 11, paramGroundOverlayOptions.getAnchorU());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 12, paramGroundOverlayOptions.getAnchorV());
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 13, paramGroundOverlayOptions.isClickable());
    com.google.android.gms.common.internal.safeparcel.zzc.zzJ(paramParcel, i);
  }
  
  public GroundOverlayOptions zzhv(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    IBinder localIBinder = null;
    LatLng localLatLng = null;
    float f7 = 0.0F;
    float f6 = 0.0F;
    LatLngBounds localLatLngBounds = null;
    float f5 = 0.0F;
    float f4 = 0.0F;
    boolean bool2 = false;
    float f3 = 0.0F;
    float f2 = 0.0F;
    float f1 = 0.0F;
    boolean bool1 = false;
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
        localIBinder = zzb.zzr(paramParcel, k);
        break;
      case 3: 
        localLatLng = (LatLng)zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 4: 
        f7 = zzb.zzl(paramParcel, k);
        break;
      case 5: 
        f6 = zzb.zzl(paramParcel, k);
        break;
      case 6: 
        localLatLngBounds = (LatLngBounds)zzb.zza(paramParcel, k, LatLngBounds.CREATOR);
        break;
      case 7: 
        f5 = zzb.zzl(paramParcel, k);
        break;
      case 8: 
        f4 = zzb.zzl(paramParcel, k);
        break;
      case 9: 
        bool2 = zzb.zzc(paramParcel, k);
        break;
      case 10: 
        f3 = zzb.zzl(paramParcel, k);
        break;
      case 11: 
        f2 = zzb.zzl(paramParcel, k);
        break;
      case 12: 
        f1 = zzb.zzl(paramParcel, k);
        break;
      case 13: 
        bool1 = zzb.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new GroundOverlayOptions(i, localIBinder, localLatLng, f7, f6, localLatLngBounds, f5, f4, bool2, f3, f2, f1, bool1);
  }
  
  public GroundOverlayOptions[] zzkZ(int paramInt)
  {
    return new GroundOverlayOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */