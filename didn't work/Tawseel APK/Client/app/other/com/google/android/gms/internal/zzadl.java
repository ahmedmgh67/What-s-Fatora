package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzadl
  extends zza
{
  public static final Parcelable.Creator<zzadl> CREATOR = new zzadm();
  private final int mVersionCode;
  private final DataHolder zzaHE;
  private final long zzaHx;
  private final int zzauz;
  
  zzadl(int paramInt1, int paramInt2, DataHolder paramDataHolder, long paramLong)
  {
    this.mVersionCode = paramInt1;
    this.zzauz = paramInt2;
    this.zzaHE = paramDataHolder;
    this.zzaHx = paramLong;
  }
  
  public int getStatusCode()
  {
    return this.zzauz;
  }
  
  public long getThrottleEndTimeMillis()
  {
    return this.zzaHx;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzadm.zza(this, paramParcel, paramInt);
  }
  
  public DataHolder zzzb()
  {
    return this.zzaHE;
  }
  
  public void zzzc()
  {
    if ((this.zzaHE != null) && (!this.zzaHE.isClosed())) {
      this.zzaHE.close();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */