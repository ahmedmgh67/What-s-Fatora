package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import java.util.ArrayList;

public class zzc
  implements Parcelable.Creator<AutocompleteFilter>
{
  static void zza(AutocompleteFilter paramAutocompleteFilter, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.zzc.zzaV(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 1, paramAutocompleteFilter.zzbkD);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 2, paramAutocompleteFilter.zzbkE, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 3, paramAutocompleteFilter.zzbkF, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zzc(paramParcel, 1000, paramAutocompleteFilter.mVersionCode);
    com.google.android.gms.common.internal.safeparcel.zzc.zzJ(paramParcel, paramInt);
  }
  
  public AutocompleteFilter zzgV(Parcel paramParcel)
  {
    String str = null;
    boolean bool = false;
    int j = zzb.zzaU(paramParcel);
    ArrayList localArrayList = null;
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
        bool = zzb.zzc(paramParcel, k);
        break;
      case 2: 
        localArrayList = zzb.zzD(paramParcel, k);
        break;
      case 3: 
        str = zzb.zzq(paramParcel, k);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new AutocompleteFilter(i, bool, localArrayList, str);
  }
  
  public AutocompleteFilter[] zzkw(int paramInt)
  {
    return new AutocompleteFilter[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */