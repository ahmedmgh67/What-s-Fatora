package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzad;

public class zzaxz
  extends zza
{
  public static final Parcelable.Creator<zzaxz> CREATOR = new zzaya();
  final int mVersionCode;
  final zzad zzbCr;
  
  zzaxz(int paramInt, zzad paramzzad)
  {
    this.mVersionCode = paramInt;
    this.zzbCr = paramzzad;
  }
  
  public zzaxz(zzad paramzzad)
  {
    this(1, paramzzad);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzaya.zza(this, paramParcel, paramInt);
  }
  
  public zzad zzOo()
  {
    return this.zzbCr;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaxz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */