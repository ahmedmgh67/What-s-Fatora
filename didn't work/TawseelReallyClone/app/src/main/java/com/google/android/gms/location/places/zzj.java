package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzj
  implements Parcelable.Creator<PlacePhotoMetadataResult>
{
  static void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramPlacePhotoMetadataResult.getStatus(), paramInt, false);
    zzc.zza(paramParcel, 2, paramPlacePhotoMetadataResult.zzbld, paramInt, false);
    zzc.zzc(paramParcel, 1000, paramPlacePhotoMetadataResult.mVersionCode);
    zzc.zzJ(paramParcel, i);
  }
  
  public PlacePhotoMetadataResult zzgZ(Parcel paramParcel)
  {
    DataHolder localDataHolder = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    Status localStatus = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
      }
      for (;;)
      {
        break;
        localStatus = (Status)zzb.zza(paramParcel, k, Status.CREATOR);
        continue;
        localDataHolder = (DataHolder)zzb.zza(paramParcel, k, DataHolder.CREATOR);
        continue;
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new PlacePhotoMetadataResult(i, localStatus, localDataHolder);
  }
  
  public PlacePhotoMetadataResult[] zzkB(int paramInt)
  {
    return new PlacePhotoMetadataResult[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */