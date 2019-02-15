package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbjs
  implements Parcelable.Creator<zzbjr>
{
  static void zza(zzbjr paramzzbjr, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjr.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzbjr.zzUw(), false);
    zzc.zza(paramParcel, 3, paramzzbjr.getDisplayName(), false);
    zzc.zza(paramParcel, 4, paramzzbjr.zzUb(), false);
    zzc.zza(paramParcel, 5, paramzzbjr.getProviderId(), false);
    zzc.zza(paramParcel, 6, paramzzbjr.getRawUserInfo(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzbjr zzla(Parcel paramParcel)
  {
    String str1 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
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
        str3 = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 6: 
        str1 = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjr(i, str5, str4, str3, str2, str1);
  }
  
  public zzbjr[] zzpC(int paramInt)
  {
    return new zzbjr[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */