package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.ArrayList;

public class zzo
  implements Parcelable.Creator<PlaceEntity>
{
  static void zza(PlaceEntity paramPlaceEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramPlaceEntity.getId(), false);
    zzc.zza(paramParcel, 2, paramPlaceEntity.zzIj(), false);
    zzc.zza(paramParcel, 3, paramPlaceEntity.zzIl(), paramInt, false);
    zzc.zza(paramParcel, 4, paramPlaceEntity.getLatLng(), paramInt, false);
    zzc.zza(paramParcel, 5, paramPlaceEntity.zzIe());
    zzc.zza(paramParcel, 6, paramPlaceEntity.getViewport(), paramInt, false);
    zzc.zza(paramParcel, 7, paramPlaceEntity.zzIk(), false);
    zzc.zzc(paramParcel, 1000, paramPlaceEntity.mVersionCode);
    zzc.zza(paramParcel, 8, paramPlaceEntity.getWebsiteUri(), paramInt, false);
    zzc.zza(paramParcel, 9, paramPlaceEntity.zzIh());
    zzc.zza(paramParcel, 10, paramPlaceEntity.getRating());
    zzc.zzc(paramParcel, 11, paramPlaceEntity.getPriceLevel());
    zzc.zza(paramParcel, 13, paramPlaceEntity.zzId(), false);
    zzc.zza(paramParcel, 14, (String)paramPlaceEntity.getAddress(), false);
    zzc.zza(paramParcel, 15, (String)paramPlaceEntity.getPhoneNumber(), false);
    zzc.zza(paramParcel, 16, paramPlaceEntity.zzIf(), false);
    zzc.zzb(paramParcel, 17, paramPlaceEntity.zzIg(), false);
    zzc.zza(paramParcel, 19, (String)paramPlaceEntity.getName(), false);
    zzc.zza(paramParcel, 20, paramPlaceEntity.getPlaceTypes(), false);
    zzc.zza(paramParcel, 21, paramPlaceEntity.zzIi(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public PlaceEntity zzhh(Parcel paramParcel)
  {
    int k = zzb.zzaU(paramParcel);
    int j = 0;
    String str6 = null;
    ArrayList localArrayList3 = null;
    ArrayList localArrayList2 = null;
    Bundle localBundle = null;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    String str2 = null;
    ArrayList localArrayList1 = null;
    LatLng localLatLng = null;
    float f2 = 0.0F;
    LatLngBounds localLatLngBounds = null;
    String str1 = null;
    Uri localUri = null;
    boolean bool = false;
    float f1 = 0.0F;
    int i = 0;
    zzs localzzs = null;
    zzu localzzu = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        str6 = zzb.zzq(paramParcel, m);
        break;
      case 2: 
        localBundle = zzb.zzs(paramParcel, m);
        break;
      case 3: 
        localzzs = (zzs)zzb.zza(paramParcel, m, zzs.CREATOR);
        break;
      case 4: 
        localLatLng = (LatLng)zzb.zza(paramParcel, m, LatLng.CREATOR);
        break;
      case 5: 
        f2 = zzb.zzl(paramParcel, m);
        break;
      case 6: 
        localLatLngBounds = (LatLngBounds)zzb.zza(paramParcel, m, LatLngBounds.CREATOR);
        break;
      case 7: 
        str1 = zzb.zzq(paramParcel, m);
        break;
      case 1000: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 8: 
        localUri = (Uri)zzb.zza(paramParcel, m, Uri.CREATOR);
        break;
      case 9: 
        bool = zzb.zzc(paramParcel, m);
        break;
      case 10: 
        f1 = zzb.zzl(paramParcel, m);
        break;
      case 11: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 13: 
        localArrayList2 = zzb.zzD(paramParcel, m);
        break;
      case 14: 
        str4 = zzb.zzq(paramParcel, m);
        break;
      case 15: 
        str3 = zzb.zzq(paramParcel, m);
        break;
      case 16: 
        str2 = zzb.zzq(paramParcel, m);
        break;
      case 17: 
        localArrayList1 = zzb.zzE(paramParcel, m);
        break;
      case 19: 
        str5 = zzb.zzq(paramParcel, m);
        break;
      case 20: 
        localArrayList3 = zzb.zzD(paramParcel, m);
        break;
      case 21: 
        localzzu = (zzu)zzb.zza(paramParcel, m, zzu.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new PlaceEntity(j, str6, localArrayList3, localArrayList2, localBundle, str5, str4, str3, str2, localArrayList1, localLatLng, f2, localLatLngBounds, str1, localUri, bool, f1, i, localzzs, localzzu);
  }
  
  public PlaceEntity[] zzkK(int paramInt)
  {
    return new PlaceEntity[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */