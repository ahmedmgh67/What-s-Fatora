package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzblt;
import java.util.List;

class zzn
  extends zza
{
  public static final Parcelable.Creator<zzn> CREATOR = new zzo();
  final int versionCode;
  final List<String> zzcay;
  final List<String> zzcaz;
  
  public zzn(int paramInt, List<String> paramList1, List<String> paramList2)
  {
    this.versionCode = paramInt;
    this.zzcay = paramList1;
    this.zzcaz = paramList2;
  }
  
  public static zzblt zza(zzn paramzzn, Object paramObject)
  {
    return new zzblt(paramzzn.zzcay, paramzzn.zzcaz, paramObject);
  }
  
  public static zzn zza(zzblt paramzzblt)
  {
    return new zzn(1, paramzzblt.zzWm(), paramzzblt.zzWn());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */