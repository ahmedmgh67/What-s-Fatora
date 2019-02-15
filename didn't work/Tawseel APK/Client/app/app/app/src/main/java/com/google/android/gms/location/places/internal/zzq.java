package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzq
  implements Parcelable.Creator<zzp>
{
  static void zza(zzp paramzzp, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzp.zzbmi, paramInt, false);
    zzc.zza(paramParcel, 2, paramzzp.zzbmj);
    zzc.zzc(paramParcel, 1000, paramzzp.mVersionCode);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzp zzhi(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    PlaceEntity localPlaceEntity = null;
    float f = 0.0F;
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
        localPlaceEntity = (PlaceEntity)zzb.zza(paramParcel, k, PlaceEntity.CREATOR);
        continue;
        f = zzb.zzl(paramParcel, k);
        continue;
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzp(i, localPlaceEntity, f);
  }
  
  public zzp[] zzkL(int paramInt)
  {
    return new zzp[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */