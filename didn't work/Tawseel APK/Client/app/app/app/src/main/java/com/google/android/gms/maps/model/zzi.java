package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzi
  implements Parcelable.Creator<PolygonOptions>
{
  static void zza(PolygonOptions paramPolygonOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramPolygonOptions.getVersionCode());
    zzc.zzc(paramParcel, 2, paramPolygonOptions.getPoints(), false);
    zzc.zzd(paramParcel, 3, paramPolygonOptions.zzIW(), false);
    zzc.zza(paramParcel, 4, paramPolygonOptions.getStrokeWidth());
    zzc.zzc(paramParcel, 5, paramPolygonOptions.getStrokeColor());
    zzc.zzc(paramParcel, 6, paramPolygonOptions.getFillColor());
    zzc.zza(paramParcel, 7, paramPolygonOptions.getZIndex());
    zzc.zza(paramParcel, 8, paramPolygonOptions.isVisible());
    zzc.zza(paramParcel, 9, paramPolygonOptions.isGeodesic());
    zzc.zza(paramParcel, 10, paramPolygonOptions.isClickable());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public PolygonOptions zzhB(Parcel paramParcel)
  {
    float f1 = 0.0F;
    boolean bool1 = false;
    int m = zzb.zzaU(paramParcel);
    ArrayList localArrayList1 = null;
    ArrayList localArrayList2 = new ArrayList();
    boolean bool2 = false;
    boolean bool3 = false;
    int i = 0;
    int j = 0;
    float f2 = 0.0F;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(n))
      {
      default: 
        zzb.zzb(paramParcel, n);
        break;
      case 1: 
        k = zzb.zzg(paramParcel, n);
        break;
      case 2: 
        localArrayList1 = zzb.zzc(paramParcel, n, LatLng.CREATOR);
        break;
      case 3: 
        zzb.zza(paramParcel, n, localArrayList2, getClass().getClassLoader());
        break;
      case 4: 
        f2 = zzb.zzl(paramParcel, n);
        break;
      case 5: 
        j = zzb.zzg(paramParcel, n);
        break;
      case 6: 
        i = zzb.zzg(paramParcel, n);
        break;
      case 7: 
        f1 = zzb.zzl(paramParcel, n);
        break;
      case 8: 
        bool3 = zzb.zzc(paramParcel, n);
        break;
      case 9: 
        bool2 = zzb.zzc(paramParcel, n);
        break;
      case 10: 
        bool1 = zzb.zzc(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new PolygonOptions(k, localArrayList1, localArrayList2, f2, j, i, f1, bool3, bool2, bool1);
  }
  
  public PolygonOptions[] zzlf(int paramInt)
  {
    return new PolygonOptions[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */