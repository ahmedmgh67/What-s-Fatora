package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzk
  implements Parcelable.Creator<zzj>
{
  static void zza(zzj paramzzj, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramzzj.version);
    zzc.zzc(paramParcel, 2, paramzzj.zzaEm);
    zzc.zzc(paramParcel, 3, paramzzj.zzaEn);
    zzc.zza(paramParcel, 4, paramzzj.zzaEo, false);
    zzc.zza(paramParcel, 5, paramzzj.zzaEp, false);
    zzc.zza(paramParcel, 6, paramzzj.zzaEq, paramInt, false);
    zzc.zza(paramParcel, 7, paramzzj.zzaEr, false);
    zzc.zza(paramParcel, 8, paramzzj.zzaEs, paramInt, false);
    zzc.zza(paramParcel, 9, paramzzj.zzaEt);
    zzc.zzJ(paramParcel, i);
  }
  
  public zzj zzaO(Parcel paramParcel)
  {
    int i = 0;
    Account localAccount = null;
    int m = zzb.zzaU(paramParcel);
    long l = 0L;
    Bundle localBundle = null;
    Scope[] arrayOfScope = null;
    IBinder localIBinder = null;
    String str = null;
    int j = 0;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zzb.zzaT(paramParcel);
      switch (zzb.zzcW(n))
      {
      default: 
        zzb.zzb(paramParcel, n);
        break;
      case 1: 
        k = zzb.zzg(paramParcel, n);
        break;
      case 2: 
        j = zzb.zzg(paramParcel, n);
        break;
      case 3: 
        i = zzb.zzg(paramParcel, n);
        break;
      case 4: 
        str = zzb.zzq(paramParcel, n);
        break;
      case 5: 
        localIBinder = zzb.zzr(paramParcel, n);
        break;
      case 6: 
        arrayOfScope = (Scope[])zzb.zzb(paramParcel, n, Scope.CREATOR);
        break;
      case 7: 
        localBundle = zzb.zzs(paramParcel, n);
        break;
      case 8: 
        localAccount = (Account)zzb.zza(paramParcel, n, Account.CREATOR);
        break;
      case 9: 
        l = zzb.zzi(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new zzj(k, j, i, str, localIBinder, arrayOfScope, localBundle, localAccount, l);
  }
  
  public zzj[] zzcO(int paramInt)
  {
    return new zzj[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */