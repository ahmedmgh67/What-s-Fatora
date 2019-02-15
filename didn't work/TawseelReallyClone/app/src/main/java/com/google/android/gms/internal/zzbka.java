package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbka
  implements Parcelable.Creator<zzbjz>
{
  static void zza(zzbjz paramzzbjz, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzbjz.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzbjz.zzUC(), false);
    zzc.zza(paramParcel, 3, paramzzbjz.zzUD(), false);
    zzc.zza(paramParcel, 4, paramzzbjz.getIdToken(), false);
    zzc.zza(paramParcel, 5, paramzzbjz.getAccessToken(), false);
    zzc.zza(paramParcel, 6, paramzzbjz.getProviderId(), false);
    zzc.zza(paramParcel, 7, paramzzbjz.getEmail(), false);
    zzc.zza(paramParcel, 8, paramzzbjz.zzgc(), false);
    zzc.zza(paramParcel, 9, paramzzbjz.zzUE(), false);
    zzc.zza(paramParcel, 10, paramzzbjz.zzUF());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzbjz zzle(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int j = zzb.zzaU(paramParcel);
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
    String str8 = null;
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
        str8 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str7 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        str6 = zzb.zzq(paramParcel, k);
        break;
      case 5: 
        str5 = zzb.zzq(paramParcel, k);
        break;
      case 6: 
        str4 = zzb.zzq(paramParcel, k);
        break;
      case 7: 
        str3 = zzb.zzq(paramParcel, k);
        break;
      case 8: 
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 9: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 10: 
        bool = zzb.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzbjz(i, str8, str7, str6, str5, str4, str3, str2, str1, bool);
  }
  
  public zzbjz[] zzpG(int paramInt)
  {
    return new zzbjz[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbka.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */