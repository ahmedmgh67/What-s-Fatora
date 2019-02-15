package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzb
  implements Parcelable.Creator<zza>
{
  static void zza(zza paramzza, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zza(paramParcel, 1, paramzza.zzblD, false);
    zzc.zza(paramParcel, 2, paramzza.zzblg, false);
    zzc.zza(paramParcel, 3, paramzza.zzbkA, false);
    zzc.zzc(paramParcel, 4, paramzza.zzblE, false);
    zzc.zzc(paramParcel, 5, paramzza.zzblF);
    zzc.zza(paramParcel, 6, paramzza.zzblG, false);
    zzc.zzc(paramParcel, 7, paramzza.zzblH, false);
    zzc.zzc(paramParcel, 1000, paramzza.mVersionCode);
    zzc.zza(paramParcel, 8, paramzza.zzblI, false);
    zzc.zzc(paramParcel, 9, paramzza.zzblJ, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zza zzhe(Parcel paramParcel)
  {
    int i = 0;
    ArrayList localArrayList1 = null;
    int k = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(paramParcel);
    String str1 = null;
    ArrayList localArrayList2 = null;
    String str2 = null;
    ArrayList localArrayList3 = null;
    String str3 = null;
    ArrayList localArrayList4 = null;
    String str4 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(paramParcel, m);
        break;
      case 1: 
        str3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, m);
        break;
      case 2: 
        str4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, m);
        break;
      case 3: 
        localArrayList4 = com.google.android.gms.common.internal.safeparcel.zzb.zzD(paramParcel, m);
        break;
      case 4: 
        localArrayList3 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, m, zza.zza.CREATOR);
        break;
      case 5: 
        i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, m);
        break;
      case 6: 
        str2 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, m);
        break;
      case 7: 
        localArrayList2 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, m, zza.zza.CREATOR);
        break;
      case 1000: 
        j = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, m);
        break;
      case 8: 
        str1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(paramParcel, m);
        break;
      case 9: 
        localArrayList1 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, m, zza.zza.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zza(j, str4, localArrayList4, i, str3, localArrayList3, str2, localArrayList2, str1, localArrayList1);
  }
  
  public zza[] zzkG(int paramInt)
  {
    return new zza[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */