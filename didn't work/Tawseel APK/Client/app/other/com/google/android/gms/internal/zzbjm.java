package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbjm
  implements Parcelable.Creator<zzbjl>
{
  static void zza(zzbjl paramzzbjl, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjl.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzbjl.getLocalId(), false);
    zzc.zza(paramParcel, 3, paramzzbjl.getEmail(), false);
    zzc.zza(paramParcel, 4, paramzzbjl.isEmailVerified());
    zzc.zza(paramParcel, 5, paramzzbjl.getDisplayName(), false);
    zzc.zza(paramParcel, 6, paramzzbjl.zzUb(), false);
    zzc.zza(paramParcel, 7, paramzzbjl.zzUq(), paramInt, false);
    zzc.zza(paramParcel, 8, paramzzbjl.getPassword(), false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzbjl zzkX(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int j = zzb.zzaU(paramParcel);
    zzbjt localzzbjt = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
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
        str5 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str4 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        bool = zzb.zzc(paramParcel, k);
        break;
      case 5: 
        str3 = zzb.zzq(paramParcel, k);
        break;
      case 6: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 7: 
        localzzbjt = (zzbjt)zzb.zza(paramParcel, k, zzbjt.CREATOR);
        break;
      case 8: 
        str1 = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjl(i, str5, str4, bool, str3, str2, localzzbjt, str1);
  }
  
  public zzbjl[] zzpz(int paramInt)
  {
    return new zzbjl[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */