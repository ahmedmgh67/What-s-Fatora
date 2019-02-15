package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import java.util.ArrayList;

public class zzd
  implements Parcelable.Creator<zzc>
{
  static void zza(zzc paramzzc, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzc.zzaV(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzc.zzc(paramParcel, 1, paramzzc.versionCode);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 2, paramzzc.zzcaQ, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zzc(paramParcel, 3, paramzzc.zzcaR);
    com.google.android.gms.common.internal.safeparcel.zzc.zzb(paramParcel, 4, paramzzc.zzcaS, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 5, paramzzc.zzbZx);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 6, paramzzc.zzcaT, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zza(paramParcel, 7, paramzzc.zzbZz, false);
    com.google.android.gms.common.internal.safeparcel.zzc.zzJ(paramParcel, i);
  }
  
  public zzc zzlh(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int k = zzb.zzaU(paramParcel);
    String str2 = null;
    ArrayList localArrayList = null;
    int i = 0;
    zzf localzzf = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(m))
      {
      default: 
        zzb.zzb(paramParcel, m);
        break;
      case 1: 
        j = zzb.zzg(paramParcel, m);
        break;
      case 2: 
        localzzf = (zzf)zzb.zza(paramParcel, m, zzf.CREATOR);
        break;
      case 3: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 4: 
        localArrayList = zzb.zzE(paramParcel, m);
        break;
      case 5: 
        bool = zzb.zzc(paramParcel, m);
        break;
      case 6: 
        str2 = zzb.zzq(paramParcel, m);
        break;
      case 7: 
        str1 = zzb.zzq(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zzc(j, localzzf, i, localArrayList, bool, str2, str1);
  }
  
  public zzc[] zzpL(int paramInt)
  {
    return new zzc[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */