package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzk
  implements Parcelable.Creator<PlacePhotoResult>
{
  static void zza(PlacePhotoResult paramPlacePhotoResult, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramPlacePhotoResult.getStatus(), paramInt, false);
    zzc.zza(paramParcel, 2, paramPlacePhotoResult.zzblf, paramInt, false);
    zzc.zzc(paramParcel, 1000, paramPlacePhotoResult.mVersionCode);
    zzc.zzJ(paramParcel, i);
  }
  
  public PlacePhotoResult zzha(Parcel paramParcel)
  {
    BitmapTeleporter localBitmapTeleporter = null;
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
        localBitmapTeleporter = (BitmapTeleporter)zzb.zza(paramParcel, k, BitmapTeleporter.CREATOR);
        continue;
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new PlacePhotoResult(i, localStatus, localBitmapTeleporter);
  }
  
  public PlacePhotoResult[] zzkC(int paramInt)
  {
    return new PlacePhotoResult[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */