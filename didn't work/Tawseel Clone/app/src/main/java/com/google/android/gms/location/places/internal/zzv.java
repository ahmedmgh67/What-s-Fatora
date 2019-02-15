package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzv
  implements Parcelable.Creator<zzu>
{
  static void zza(zzu paramzzu, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzu.zzbmn, false);
    zzc.zzc(paramParcel, 2, paramzzu.zzbmo, false);
    zzc.zzc(paramParcel, 1000, paramzzu.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzu zzhk(Parcel paramParcel)
  {
    ArrayList localArrayList2 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    ArrayList localArrayList1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(k))
      {
      default: 
        zzb.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList1 = zzb.zzc(paramParcel, k, zzu.zza.CREATOR);
        break;
      case 2: 
        localArrayList2 = zzb.zzc(paramParcel, k, zzu.zzb.CREATOR);
        break;
      case 1000: 
        i = zzb.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzu(i, localArrayList1, localArrayList2);
  }
  
  public zzu[] zzkN(int paramInt)
  {
    return new zzu[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */