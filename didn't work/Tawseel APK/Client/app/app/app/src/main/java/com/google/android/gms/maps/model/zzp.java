package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzp
  implements Parcelable.Creator<TileOverlayOptions>
{
  static void zza(TileOverlayOptions paramTileOverlayOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramTileOverlayOptions.getVersionCode());
    zzc.zza(paramParcel, 2, paramTileOverlayOptions.zzIX(), false);
    zzc.zza(paramParcel, 3, paramTileOverlayOptions.isVisible());
    zzc.zza(paramParcel, 4, paramTileOverlayOptions.getZIndex());
    zzc.zza(paramParcel, 5, paramTileOverlayOptions.getFadeIn());
    zzc.zza(paramParcel, 6, paramTileOverlayOptions.getTransparency());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public TileOverlayOptions zzhI(Parcel paramParcel)
  {
    boolean bool2 = false;
    float f1 = 0.0F;
    int j = zzb.zzaU(paramParcel);
    IBinder localIBinder = null;
    boolean bool1 = true;
    float f2 = 0.0F;
    int i = 0;
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
        bool2 = zzb.zzc(paramParcel, k);
        break;
      case 4: 
        f2 = zzb.zzl(paramParcel, k);
        break;
      case 5: 
        bool1 = zzb.zzc(paramParcel, k);
        break;
      case 6: 
        f1 = zzb.zzl(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new TileOverlayOptions(i, localIBinder, bool2, f2, bool1, f1);
  }
  
  public TileOverlayOptions[] zzlm(int paramInt)
  {
    return new TileOverlayOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */