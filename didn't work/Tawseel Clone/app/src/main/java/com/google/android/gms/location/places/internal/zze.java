package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zze
  implements Parcelable.Creator<zzu.zzb>
{
  static void zza(zzu.zzb paramzzb, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzb.zzbmr);
    zzc.zzc(paramParcel, 2, paramzzb.zzbms);
    zzc.zzc(paramParcel, 3, paramzzb.zzbmt);
    zzc.zzc(paramParcel, 4, paramzzb.zzbmu);
    zzc.zzc(paramParcel, 5, paramzzb.zzbmv);
    zzc.zzc(paramParcel, 6, paramzzb.zzbmw);
    zzc.zzc(paramParcel, 7, paramzzb.zzbmx, false);
    zzc.zzc(paramParcel, 1000, paramzzb.mVersionCode);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzu.zzb zzhg(Parcel paramParcel)
  {
    int i = 0;
    int i3 = zzb.zzaU(paramParcel);
    ArrayList localArrayList = null;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    while (paramParcel.dataPosition() < i3)
    {
      int i4 = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(i4))
      {
      default: 
        zzb.zzb(paramParcel, i4);
        break;
      case 1: 
        i1 = zzb.zzg(paramParcel, i4);
        break;
      case 2: 
        n = zzb.zzg(paramParcel, i4);
        break;
      case 3: 
        m = zzb.zzg(paramParcel, i4);
        break;
      case 4: 
        k = zzb.zzg(paramParcel, i4);
        break;
      case 5: 
        j = zzb.zzg(paramParcel, i4);
        break;
      case 6: 
        i = zzb.zzg(paramParcel, i4);
        break;
      case 7: 
        localArrayList = zzb.zzc(paramParcel, i4, zzu.zza.CREATOR);
        break;
      case 1000: 
        i2 = zzb.zzg(paramParcel, i4);
      }
    }
    if (paramParcel.dataPosition() != i3) {
      throw new zzb.zza(37 + "Overread allowed size end=" + i3, paramParcel);
    }
    return new zzu.zzb(i2, i1, n, m, k, j, i, localArrayList);
  }
  
  public zzu.zzb[] zzkI(int paramInt)
  {
    return new zzu.zzb[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */