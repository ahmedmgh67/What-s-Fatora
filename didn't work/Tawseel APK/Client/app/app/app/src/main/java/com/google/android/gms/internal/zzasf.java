package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

public class zzasf
  extends zza
  implements Result
{
  public static final Parcelable.Creator<zzasf> CREATOR = new zzasg();
  final int mVersionCode;
  private final Status zzahq;
  private final List<zzasd> zzbmJ;
  
  zzasf(int paramInt, Status paramStatus, List<zzasd> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzahq = paramStatus;
    this.zzbmJ = paramList;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasg.zza(this, paramParcel, paramInt);
  }
  
  public List<zzasd> zzIs()
  {
    return this.zzbmJ;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */