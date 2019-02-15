package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzacm
  implements Parcelable.Creator<zzack.zza>
{
  static void zza(zzack.zza paramzza, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzza.getVersionCode());
    zzc.zzc(paramParcel, 2, paramzza.zzxL());
    zzc.zza(paramParcel, 3, paramzza.zzxM());
    zzc.zzc(paramParcel, 4, paramzza.zzxN());
    zzc.zza(paramParcel, 5, paramzza.zzxO());
    zzc.zza(paramParcel, 6, paramzza.zzxP(), false);
    zzc.zzc(paramParcel, 7, paramzza.zzxQ());
    zzc.zza(paramParcel, 8, paramzza.zzxS(), false);
    zzc.zza(paramParcel, 9, paramzza.zzxU(), paramInt, false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzack.zza zzba(Parcel paramParcel)
  {
    zzacf localzzacf = null;
    int i = 0;
    int n = zzb.zzaU(paramParcel);
    String str1 = null;
    String str2 = null;
    boolean bool1 = false;
    int j = 0;
    boolean bool2 = false;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(i1))
      {
      default: 
        zzb.zzb(paramParcel, i1);
        break;
      case 1: 
        m = zzb.zzg(paramParcel, i1);
        break;
      case 2: 
        k = zzb.zzg(paramParcel, i1);
        break;
      case 3: 
        bool2 = zzb.zzc(paramParcel, i1);
        break;
      case 4: 
        j = zzb.zzg(paramParcel, i1);
        break;
      case 5: 
        bool1 = zzb.zzc(paramParcel, i1);
        break;
      case 6: 
        str2 = zzb.zzq(paramParcel, i1);
        break;
      case 7: 
        i = zzb.zzg(paramParcel, i1);
        break;
      case 8: 
        str1 = zzb.zzq(paramParcel, i1);
        break;
      case 9: 
        localzzacf = (zzacf)zzb.zza(paramParcel, i1, zzacf.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new zzb.zza(37 + "Overread allowed size end=" + n, paramParcel);
    }
    return new zzack.zza(m, k, bool2, j, bool1, str2, i, str1, localzzacf);
  }
  
  public zzack.zza[] zzdc(int paramInt)
  {
    return new zzack.zza[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */