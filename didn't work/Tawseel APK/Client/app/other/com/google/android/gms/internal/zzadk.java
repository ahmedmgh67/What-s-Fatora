package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzadk
  implements Parcelable.Creator<zzadj>
{
  static void zza(zzadj paramzzadj, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzadj.mVersionCode);
    zzc.zza(paramParcel, 2, paramzzadj.getPackageName(), false);
    zzc.zza(paramParcel, 3, paramzzadj.zzyV());
    zzc.zza(paramParcel, 4, paramzzadj.zzyW(), paramInt, false);
    zzc.zza(paramParcel, 5, paramzzadj.zzyX(), false);
    zzc.zza(paramParcel, 6, paramzzadj.getAppInstanceId(), false);
    zzc.zza(paramParcel, 7, paramzzadj.zzyY(), false);
    zzc.zzb(paramParcel, 8, paramzzadj.zzyZ(), false);
    zzc.zzc(paramParcel, 9, paramzzadj.zzyS());
    zzc.zzc(paramParcel, 10, paramzzadj.zzza(), false);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzadj zzbi(Parcel paramParcel)
  {
    int i = 0;
    ArrayList localArrayList1 = null;
    int k = zzb.zzaU(paramParcel);
    long l = 0L;
    ArrayList localArrayList2 = null;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    DataHolder localDataHolder = null;
    String str4 = null;
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
        str4 = zzb.zzq(paramParcel, m);
        break;
      case 3: 
        l = zzb.zzi(paramParcel, m);
        break;
      case 4: 
        localDataHolder = (DataHolder)zzb.zza(paramParcel, m, DataHolder.CREATOR);
        break;
      case 5: 
        str3 = zzb.zzq(paramParcel, m);
        break;
      case 6: 
        str2 = zzb.zzq(paramParcel, m);
        break;
      case 7: 
        str1 = zzb.zzq(paramParcel, m);
        break;
      case 8: 
        localArrayList2 = zzb.zzE(paramParcel, m);
        break;
      case 9: 
        i = zzb.zzg(paramParcel, m);
        break;
      case 10: 
        localArrayList1 = zzb.zzc(paramParcel, m, zzadb.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zzb.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new zzadj(j, str4, l, localDataHolder, str3, str2, str1, localArrayList2, i, localArrayList1);
  }
  
  public zzadj[] zzdq(int paramInt)
  {
    return new zzadj[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */