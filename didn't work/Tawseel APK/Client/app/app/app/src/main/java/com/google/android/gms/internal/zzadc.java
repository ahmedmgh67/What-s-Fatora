package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzadc
  implements Parcelable.Creator<zzadb>
{
  static void zza(zzadb paramzzadb, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzadb.getVersionCode());
    zzc.zza(paramParcel, 2, paramzzadb.getName(), false);
    zzc.zza(paramParcel, 3, paramzzadb.getValue(), false);
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public zzadb zzbg(Parcel paramParcel)
  {
    String str2 = null;
    int j = zzb.zzaU(paramParcel);
    int i = 0;
    String str1 = null;
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
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str2 = zzb.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new zzadb(i, str1, str2);
  }
  
  public zzadb[] zzdm(int paramInt)
  {
    return new zzadb[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */