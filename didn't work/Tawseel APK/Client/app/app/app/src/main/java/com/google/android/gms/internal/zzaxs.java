package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

public class zzaxs
  extends zza
{
  public static final Parcelable.Creator<zzaxs> CREATOR = new zzaxt();
  final int mVersionCode;
  final boolean zzbCn;
  final List<Scope> zzbCo;
  
  zzaxs(int paramInt, boolean paramBoolean, List<Scope> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzbCn = paramBoolean;
    this.zzbCo = paramList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzaxt.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaxs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */