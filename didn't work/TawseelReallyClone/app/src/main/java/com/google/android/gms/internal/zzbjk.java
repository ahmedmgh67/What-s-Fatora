package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbjk
  implements Parcelable.Creator<zzbjj>
{
  static void zza(zzbjj paramzzbjj, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjj.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzbjj.zzUm(), false);
    zzc.zza(paramParcel, 3, paramzzbjj.isRegistered());
    zzc.zza(paramParcel, 4, paramzzbjj.getProviderId(), false);
    zzc.zza(paramParcel, 5, paramzzbjj.zzUn());
    zzc.zza(paramParcel, 6, paramzzbjj.zzUo(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzbjj zzkW(Parcel paramParcel)
  {
    zzbjx localzzbjx = null;
    boolean bool1 = false;
    int j = zzb.zzaU(paramParcel);
    String str1 = null;
    boolean bool2 = false;
    String str2 = null;
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
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        bool2 = zzb.zzc(paramParcel, k);
        break;
      case 4: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        bool1 = zzb.zzc(paramParcel, k);
        break;
      case 6: 
        localzzbjx = (zzbjx)zzb.zza(paramParcel, k, zzbjx.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjj(i, str2, bool2, str1, bool1, localzzbjx);
  }
  
  public zzbjj[] zzpy(int paramInt)
  {
    return new zzbjj[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */