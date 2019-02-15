package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzg
  implements Parcelable.Creator<MarkerOptions>
{
  static void zza(MarkerOptions paramMarkerOptions, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramMarkerOptions.getVersionCode());
    zzc.zza(paramParcel, 2, paramMarkerOptions.getPosition(), paramInt, false);
    zzc.zza(paramParcel, 3, paramMarkerOptions.getTitle(), false);
    zzc.zza(paramParcel, 4, paramMarkerOptions.getSnippet(), false);
    zzc.zza(paramParcel, 5, paramMarkerOptions.zzIV(), false);
    zzc.zza(paramParcel, 6, paramMarkerOptions.getAnchorU());
    zzc.zza(paramParcel, 7, paramMarkerOptions.getAnchorV());
    zzc.zza(paramParcel, 8, paramMarkerOptions.isDraggable());
    zzc.zza(paramParcel, 9, paramMarkerOptions.isVisible());
    zzc.zza(paramParcel, 10, paramMarkerOptions.isFlat());
    zzc.zza(paramParcel, 11, paramMarkerOptions.getRotation());
    zzc.zza(paramParcel, 12, paramMarkerOptions.getInfoWindowAnchorU());
    zzc.zza(paramParcel, 13, paramMarkerOptions.getInfoWindowAnchorV());
    zzc.zza(paramParcel, 14, paramMarkerOptions.getAlpha());
    zzc.zza(paramParcel, 15, paramMarkerOptions.getZIndex());
    zzc.zzJ(paramParcel, i);
  }
  
  public MarkerOptions zzhz(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    LatLng localLatLng = null;
    String str2 = null;
    String str1 = null;
    IBinder localIBinder = null;
    float f7 = 0.0F;
    float f6 = 0.0F;
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = false;
    float f5 = 0.0F;
    float f4 = 0.5F;
    float f3 = 0.0F;
    float f2 = 1.0F;
    float f1 = 0.0F;
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
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        localIBinder = zzb.zzr(paramParcel, k);
        break;
      case 6: 
        f7 = zzb.zzl(paramParcel, k);
        break;
      case 7: 
        f6 = zzb.zzl(paramParcel, k);
        break;
      case 8: 
        bool3 = zzb.zzc(paramParcel, k);
        break;
      case 9: 
        bool2 = zzb.zzc(paramParcel, k);
        break;
      case 10: 
        bool1 = zzb.zzc(paramParcel, k);
        break;
      case 11: 
        f5 = zzb.zzl(paramParcel, k);
        break;
      case 12: 
        f4 = zzb.zzl(paramParcel, k);
        break;
      case 13: 
        f3 = zzb.zzl(paramParcel, k);
        break;
      case 14: 
        f2 = zzb.zzl(paramParcel, k);
        break;
      case 15: 
        f1 = zzb.zzl(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new MarkerOptions(i, localLatLng, str2, str1, localIBinder, f7, f6, bool3, bool2, bool1, f5, f4, f3, f2, f1);
  }
  
  public MarkerOptions[] zzld(int paramInt)
  {
    return new MarkerOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */