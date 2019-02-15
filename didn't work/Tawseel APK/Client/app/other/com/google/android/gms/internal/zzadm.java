package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzadm
  implements Parcelable.Creator<zzadl>
{
  static void zza(zzadl paramzzadl, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzadl.getVersionCode());
    zzc.zzc(paramParcel, 2, paramzzadl.getStatusCode());
    zzc.zza(paramParcel, 3, paramzzadl.zzzb(), paramInt, false);
    zzc.zza(paramParcel, 4, paramzzadl.getThrottleEndTimeMillis());
    zzc.zzJ(paramParcel, i);
  }
  
  public zzadl zzbj(Parcel paramParcel)
  {
    int i = 0;
    int k = zzb.zzaU(paramParcel);
    DataHolder localDataHolder = null;
    long l = 0L;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 2: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 3: 
        localDataHolder = (DataHolder)zzb.zza(paramParcel, m, DataHolder.CREATOR);
        break;
      case 4: 
        l = zzb.zzi(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zzadl(j, i, localDataHolder, l);
  }
  
  public zzadl[] zzdr(int paramInt)
  {
    return new zzadl[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */