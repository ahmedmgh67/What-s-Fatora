package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class zzb
  implements Parcelable.Creator<AddPlaceRequest>
{
  static void zza(AddPlaceRequest paramAddPlaceRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramAddPlaceRequest.getName(), false);
    zzc.zza(paramParcel, 2, paramAddPlaceRequest.getLatLng(), paramInt, false);
    zzc.zza(paramParcel, 3, paramAddPlaceRequest.getAddress(), false);
    zzc.zza(paramParcel, 4, paramAddPlaceRequest.getPlaceTypes(), false);
    zzc.zza(paramParcel, 5, paramAddPlaceRequest.getPhoneNumber(), false);
    zzc.zza(paramParcel, 6, paramAddPlaceRequest.getWebsiteUri(), paramInt, false);
    zzc.zzc(paramParcel, 1000, paramAddPlaceRequest.mVersionCode);
    zzc.zzJ(paramParcel, i);
  }
  
  public AddPlaceRequest zzgU(Parcel paramParcel)
  {
    Uri localUri = null;
    int j = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(paramParcel);
    int i = 0;
    String str1 = null;
    ArrayList localArrayList = null;
    String str2 = null;
    LatLng localLatLng = null;
    String str3 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(paramParcel, k);
        break;
      case 1: 
        str3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, k);
        break;
      case 2: 
        localLatLng = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, k, LatLng.CREATOR);
        break;
      case 3: 
        str2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, k);
        break;
      case 4: 
        localArrayList = com.google.android.gms.common.internal.safeparcel.zzb.zzD(paramParcel, k);
        break;
      case 5: 
        str1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, k);
        break;
      case 6: 
        localUri = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, k, Uri.CREATOR);
        break;
      case 1000: 
        i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new AddPlaceRequest(i, str3, localLatLng, str2, localArrayList, str1, localUri);
  }
  
  public AddPlaceRequest[] zzkv(int paramInt)
  {
    return new AddPlaceRequest[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */