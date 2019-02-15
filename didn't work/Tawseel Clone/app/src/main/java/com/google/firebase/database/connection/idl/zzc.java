package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzblp;
import com.google.android.gms.internal.zzboq.zza;
import java.util.List;

public class zzc
  extends zza
{
  public static final Parcelable.Creator<zzc> CREATOR = new zzd();
  final int versionCode;
  final boolean zzbZx;
  final String zzbZz;
  final zzf zzcaQ;
  final int zzcaR;
  final List<String> zzcaS;
  final String zzcaT;
  
  public zzc(int paramInt1, zzf paramzzf, int paramInt2, List<String> paramList, boolean paramBoolean, String paramString1, String paramString2)
  {
    this.versionCode = paramInt1;
    this.zzcaQ = paramzzf;
    this.zzcaR = paramInt2;
    this.zzcaS = paramList;
    this.zzbZx = paramBoolean;
    this.zzcaT = paramString1;
    this.zzbZz = paramString2;
  }
  
  public zzc(zzblp paramzzblp, zzboq.zza paramzza, List<String> paramList, boolean paramBoolean, String paramString1, String paramString2)
  {
    int i;
    switch (1.zzcaU[paramzza.ordinal()])
    {
    default: 
      i = 0;
    }
    for (;;)
    {
      this.versionCode = 1;
      this.zzcaQ = zzf.zza(paramzzblp);
      this.zzcaR = i;
      this.zzcaS = paramList;
      this.zzbZx = paramBoolean;
      this.zzcaT = paramString1;
      this.zzbZz = paramString2;
      return;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public zzboq.zza zzWu()
  {
    switch (this.zzcaR)
    {
    default: 
      return zzboq.zza.zzcgJ;
    case 0: 
      return zzboq.zza.zzcgJ;
    case 1: 
      return zzboq.zza.zzcgF;
    case 2: 
      return zzboq.zza.zzcgG;
    case 3: 
      return zzboq.zza.zzcgH;
    }
    return zzboq.zza.zzcgI;
  }
  
  public List<String> zzWv()
  {
    return this.zzcaS;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */