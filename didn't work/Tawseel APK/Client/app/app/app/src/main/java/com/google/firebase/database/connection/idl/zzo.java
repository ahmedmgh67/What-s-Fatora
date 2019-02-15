package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzo
  implements Parcelable.Creator<zzn>
{
  static void zza(zzn paramzzn, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzn.versionCode);
    zzc.zzb(paramParcel, 2, paramzzn.zzcay, false);
    zzc.zzb(paramParcel, 3, paramzzn.zzcaz, false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzn zzlj(Parcel paramParcel)
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
        i = zzb.zzg(paramParcel, k);
        break;
      case 2: 
        localArrayList1 = zzb.zzE(paramParcel, k);
        break;
      case 3: 
        localArrayList2 = zzb.zzE(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzn(i, localArrayList1, localArrayList2);
  }
  
  public zzn[] zzpN(int paramInt)
  {
    return new zzn[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */