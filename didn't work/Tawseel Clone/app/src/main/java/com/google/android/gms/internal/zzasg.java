package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzasg
  implements Parcelable.Creator<zzasf>
{
  static void zza(zzasf paramzzasf, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzzasf.getStatus(), paramInt, false);
    zzc.zzc(paramParcel, 2, paramzzasf.zzIs(), false);
    zzc.zzc(paramParcel, 1000, paramzzasf.mVersionCode);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzasf zzho(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
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
        localArrayList = zzb.zzc(paramParcel, k, zzasd.CREATOR);
        continue;
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzasf(i, localStatus, localArrayList);
  }
  
  public zzasf[] zzkR(int paramInt)
  {
    return new zzasf[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */