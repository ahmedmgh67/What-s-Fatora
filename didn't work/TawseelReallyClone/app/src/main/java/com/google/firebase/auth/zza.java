package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zza
  implements Parcelable.Creator<UserProfileChangeRequest>
{
  static void zza(UserProfileChangeRequest paramUserProfileChangeRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramUserProfileChangeRequest.mVersionCode);
    zzc.zza(paramParcel, 2, paramUserProfileChangeRequest.getDisplayName(), false);
    zzc.zza(paramParcel, 3, paramUserProfileChangeRequest.zzUb(), false);
    zzc.zza(paramParcel, 4, paramUserProfileChangeRequest.zzUc());
    zzc.zza(paramParcel, 5, paramUserProfileChangeRequest.zzUd());
    zzc.zzJ(paramParcel, paramInt);
  }
  
  public UserProfileChangeRequest zzkV(Parcel paramParcel)
  {
    String str1 = null;
    boolean bool1 = false;
    int j = zzb.zzaU(paramParcel);
    boolean bool2 = false;
    String str2 = null;
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
        str2 = zzb.zzq(paramParcel, k);
        break;
      case 3: 
        str1 = zzb.zzq(paramParcel, k);
        break;
      case 4: 
        bool2 = zzb.zzc(paramParcel, k);
        break;
      case 5: 
        bool1 = zzb.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zzb.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new UserProfileChangeRequest(i, str2, str1, bool2, bool1);
  }
  
  public UserProfileChangeRequest[] zzpv(int paramInt)
  {
    return new UserProfileChangeRequest[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */