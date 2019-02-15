package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzbjy
  implements Parcelable.Creator<zzbjx>
{
  static void zza(zzbjx paramzzbjx, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjx.mVersionCode);
    zzc.zzb(paramParcel, 2, paramzzbjx.zzUA(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzbjx zzld(Parcel paramParcel)
  {
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        localArrayList = zzb.zzE(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjx(i, localArrayList);
  }
  
  public zzbjx[] zzpF(int paramInt)
  {
    return new zzbjx[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */