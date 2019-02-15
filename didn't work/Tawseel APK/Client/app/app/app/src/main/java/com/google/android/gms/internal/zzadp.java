package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzadp
  extends zza
{
  public static final Parcelable.Creator<zzadp> CREATOR = new zzadq();
  private final int mVersionCode;
  private final Bundle zzaHF;
  
  zzadp(int paramInt, Bundle paramBundle)
  {
    this.mVersionCode = paramInt;
    this.zzaHF = paramBundle;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzadq.zza(this, paramParcel, paramInt);
  }
  
  public Bundle zzzd()
  {
    return this.zzaHF;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */