package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbjw
  implements Parcelable.Creator<zzbjv>
{
  static void zza(zzbjv paramzzbjv, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjv.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzbjv.getEmail(), false);
    zzc.zza(paramParcel, 3, paramzzbjv.zzUy(), false);
    zzc.zza(paramParcel, 4, paramzzbjv.zzUz(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzbjv zzlc(Parcel paramParcel)
  {
    String str3 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str2 = null;
    String str1 = null;
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
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        str3 = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjv(i, str1, str2, str3);
  }
  
  public zzbjv[] zzpE(int paramInt)
  {
    return new zzbjv[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */