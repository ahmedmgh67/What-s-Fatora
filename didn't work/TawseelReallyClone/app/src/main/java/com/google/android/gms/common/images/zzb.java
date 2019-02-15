package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzb
  implements Parcelable.Creator<WebImage>
{
  static void zza(WebImage paramWebImage, Parcel paramParcel, int paramInt)
  {
    int i = zzc.zzaV(paramParcel);
    zzc.zzc(paramParcel, 1, paramWebImage.mVersionCode);
    zzc.zza(paramParcel, 2, paramWebImage.getUrl(), paramInt, false);
    zzc.zzc(paramParcel, 3, paramWebImage.getWidth());
    zzc.zzc(paramParcel, 4, paramWebImage.getHeight());
    zzc.zzJ(paramParcel, i);
  }
  
  public WebImage zzaL(Parcel paramParcel)
  {
    int i = 0;
    int m = com.google.android.gms.common.internal.safeparcel.zzb.zzaU(paramParcel);
    Uri localUri = null;
    int j = 0;
    int k = 0;
    if (paramParcel.dataPosition() < m)
    {
      int n = com.google.android.gms.common.internal.safeparcel.zzb.zzaT(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zzb.zzcW(n))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zzb.zzb(paramParcel, n);
      }
      for (;;)
      {
        break;
        k = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, n);
        continue;
        localUri = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, n, Uri.CREATOR);
        continue;
        j = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, n);
        continue;
        i = com.google.android.gms.common.internal.safeparcel.zzb.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zzb.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new WebImage(k, localUri, j, i);
  }
  
  public WebImage[] zzcJ(int paramInt)
  {
    return new WebImage[paramInt];
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\images\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */