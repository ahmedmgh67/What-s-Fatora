package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zze
  implements Parcelable.Creator<zzd>
{
  static void zza(zzd paramzzd, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzb(paramParcel, 1, paramzzd.zzbkH, false);
    zzc.zza(paramParcel, 2, paramzzd.zzbkI, false);
    zzc.zzc(paramParcel, 3, paramzzd.zzbkJ, false);
    zzc.zza(paramParcel, 4, paramzzd.zzbkK, false);
    zzc.zza(paramParcel, 5, paramzzd.zzbkL);
    zzc.zzc(paramParcel, 1000, paramzzd.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzd zzgW(Parcel paramParcel)
  {
    boolean bool = false;
    String str = null;
    int j = zzb.zzaU(paramParcel);
    ArrayList localArrayList1 = null;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
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
        localArrayList3 = zzb.zzE(paramParcel, k);
        break;
      case 2: 
        localArrayList2 = zzb.zzD(paramParcel, k);
        break;
      case 3: 
        localArrayList1 = zzb.zzc(paramParcel, k, zzp.CREATOR);
        break;
      case 4: 
        str = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        bool = zzb.zzc(paramParcel, k);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzd(i, localArrayList3, localArrayList2, localArrayList1, str, bool);
  }
  
  public zzd[] zzkx(int paramInt)
  {
    return new zzd[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */