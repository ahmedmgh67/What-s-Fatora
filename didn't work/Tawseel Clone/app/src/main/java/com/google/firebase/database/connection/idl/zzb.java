package com.google.firebase.database.connection.idl;

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
    zzc.zzc(paramParcel, 1, paramzza.versionCode);
    zzc.zzb(paramParcel, 2, paramzza.zzbZh, false);
    zzc.zzb(paramParcel, 3, paramzza.zzbZi, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zza zzlg(Parcel paramParcel)
  {
    ArrayList localArrayList2 = null;
    int j = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(paramParcel);
    int i = 0;
    ArrayList localArrayList1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(paramParcel, k);
        break;
      case 1: 
        i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, k);
        break;
      case 2: 
        localArrayList1 = com.google.android.gms.common.internal.safeparcel.zzb.zzE(paramParcel, k);
        break;
      case 3: 
        localArrayList2 = com.google.android.gms.common.internal.safeparcel.zzb.zzE(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zza(i, localArrayList1, localArrayList2);
  }
  
  public zza[] zzpK(int paramInt)
  {
    return new zza[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */