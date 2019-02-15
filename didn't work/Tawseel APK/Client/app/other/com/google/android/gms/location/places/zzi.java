package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzi
  implements Parcelable.Creator<PlaceFilter>
{
  static void zza(PlaceFilter paramPlaceFilter, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramPlaceFilter.zzbkI, false);
    zzc.zza(paramParcel, 3, paramPlaceFilter.zzbkY);
    zzc.zzc(paramParcel, 4, paramPlaceFilter.zzbkJ, false);
    zzc.zzb(paramParcel, 6, paramPlaceFilter.zzbkH, false);
    zzc.zzc(paramParcel, 1000, paramPlaceFilter.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public PlaceFilter zzgY(Parcel paramParcel)
  {
    boolean bool = false;
    ArrayList localArrayList1 = null;
    int j = zzb.zzaU(paramParcel);
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
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
        localArrayList3 = zzb.zzD(paramParcel, k);
        break;
      case 3: 
        bool = zzb.zzc(paramParcel, k);
        break;
      case 4: 
        localArrayList1 = zzb.zzc(paramParcel, k, zzp.CREATOR);
        break;
      case 6: 
        localArrayList2 = zzb.zzE(paramParcel, k);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new PlaceFilter(i, localArrayList3, bool, localArrayList2, localArrayList1);
  }
  
  public PlaceFilter[] zzkz(int paramInt)
  {
    return new PlaceFilter[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */