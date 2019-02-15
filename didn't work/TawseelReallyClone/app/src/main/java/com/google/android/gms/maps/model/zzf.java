package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzf
  implements Parcelable.Creator<MapStyleOptions>
{
  static void zza(MapStyleOptions paramMapStyleOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramMapStyleOptions.getVersionCode());
    zzc.zza(paramParcel, 2, paramMapStyleOptions.zzIU(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public MapStyleOptions zzhy(Parcel paramParcel)
  {
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
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        str = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new MapStyleOptions(i, str);
  }
  
  public MapStyleOptions[] zzlc(int paramInt)
  {
    return new MapStyleOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */