package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.Collections;
import java.util.List;

public class zzu
  extends zza
{
  public static final Parcelable.Creator<zzu> CREATOR = new zzv();
  public final int mVersionCode;
  final List<zza> zzbmn;
  final List<zzb> zzbmo;
  
  zzu(int paramInt, List<zza> paramList, List<zzb> paramList1)
  {
    this.mVersionCode = paramInt;
    this.zzbmn = Collections.unmodifiableList(paramList);
    this.zzbmo = Collections.unmodifiableList(paramList1);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzv.zza(this, paramParcel, paramInt);
  }
  
  public static class zza
    extends zza
  {
    public static final Parcelable.Creator<zza> CREATOR = new zzd();
    final int mVersionCode;
    final int zzbmp;
    final int zzbmq;
    
    zza(int paramInt1, int paramInt2, int paramInt3)
    {
      this.mVersionCode = paramInt1;
      this.zzbmp = paramInt2;
      this.zzbmq = paramInt3;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzd.zza(this, paramParcel, paramInt);
    }
  }
  
  public static class zzb
    extends zza
  {
    public static final Parcelable.Creator<zzb> CREATOR = new zze();
    final int mVersionCode;
    final int zzbmr;
    final int zzbms;
    final int zzbmt;
    final int zzbmu;
    final int zzbmv;
    final int zzbmw;
    final List<zzu.zza> zzbmx;
    
    zzb(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, List<zzu.zza> paramList)
    {
      this.mVersionCode = paramInt1;
      this.zzbmr = paramInt2;
      this.zzbms = paramInt3;
      this.zzbmt = paramInt4;
      this.zzbmu = paramInt5;
      this.zzbmv = paramInt6;
      this.zzbmw = paramInt7;
      this.zzbmx = Collections.unmodifiableList(paramList);
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zze.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */