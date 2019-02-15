package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzj
  implements Parcelable.Creator<PolylineOptions>
{
  static void zza(PolylineOptions paramPolylineOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramPolylineOptions.getVersionCode());
    zzc.zzc(paramParcel, 2, paramPolylineOptions.getPoints(), false);
    zzc.zza(paramParcel, 3, paramPolylineOptions.getWidth());
    zzc.zzc(paramParcel, 4, paramPolylineOptions.getColor());
    zzc.zza(paramParcel, 5, paramPolylineOptions.getZIndex());
    zzc.zza(paramParcel, 6, paramPolylineOptions.isVisible());
    zzc.zza(paramParcel, 7, paramPolylineOptions.isGeodesic());
    zzc.zza(paramParcel, 8, paramPolylineOptions.isClickable());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public PolylineOptions zzhC(Parcel paramParcel)
  {
    float f1 = 0.0F;
    boolean bool1 = false;
    int k = zzb.zzaU(paramParcel);
    ArrayList localArrayList = null;
    boolean bool2 = false;
    boolean bool3 = false;
    int i = 0;
    float f2 = 0.0F;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 2: 
        localArrayList = zzb.zzc(paramParcel, m, LatLng.CREATOR);
        break;
      case 3: 
        f2 = zzb.zzl(paramParcel, m);
        break;
      case 4: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 5: 
        f1 = zzb.zzl(paramParcel, m);
        break;
      case 6: 
        bool3 = zzb.zzc(paramParcel, m);
        break;
      case 7: 
        bool2 = zzb.zzc(paramParcel, m);
        break;
      case 8: 
        bool1 = zzb.zzc(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new PolylineOptions(j, localArrayList, f2, i, f1, bool3, bool2, bool1);
  }
  
  public PolylineOptions[] zzlg(int paramInt)
  {
    return new PolylineOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */