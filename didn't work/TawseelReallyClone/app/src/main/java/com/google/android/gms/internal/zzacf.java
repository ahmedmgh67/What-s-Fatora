package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzacf
  extends zza
{
  public static final Parcelable.Creator<zzacf> CREATOR = new zzacg();
  final int mVersionCode;
  private final zzach zzaFu;
  
  zzacf(int paramInt, zzach paramzzach)
  {
    this.mVersionCode = paramInt;
    this.zzaFu = paramzzach;
  }
  
  private zzacf(zzach paramzzach)
  {
    this.mVersionCode = 1;
    this.zzaFu = paramzzach;
  }
  
  public static zzacf zza(zzack.zzb<?, ?> paramzzb)
  {
    if ((paramzzb instanceof zzach)) {
      return new zzacf((zzach)paramzzb);
    }
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzacg.zza(this, paramParcel, paramInt);
  }
  
  zzach zzxH()
  {
    return this.zzaFu;
  }
  
  public zzack.zzb<?, ?> zzxI()
  {
    if (this.zzaFu != null) {
      return this.zzaFu;
    }
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */