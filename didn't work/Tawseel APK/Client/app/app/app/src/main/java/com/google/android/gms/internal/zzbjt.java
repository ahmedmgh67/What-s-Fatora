package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zzbjt
  extends zza
{
  public static final Parcelable.Creator<zzbjt> CREATOR = new zzbju();
  public final int mVersionCode;
  private List<zzbjr> zzbWA;
  
  public zzbjt()
  {
    this.mVersionCode = 1;
    this.zzbWA = new ArrayList();
  }
  
  zzbjt(int paramInt, List<zzbjr> paramList)
  {
    this.mVersionCode = paramInt;
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      this.zzbWA = Collections.unmodifiableList(paramList);
      return;
    }
    this.zzbWA = Collections.emptyList();
  }
  
  public static zzbjt zzUx()
  {
    return new zzbjt();
  }
  
  public static zzbjt zza(zzbjt paramzzbjt)
  {
    paramzzbjt = paramzzbjt.zzUp();
    zzbjt localzzbjt = new zzbjt();
    if (paramzzbjt != null) {
      localzzbjt.zzUp().addAll(paramzzbjt);
    }
    return localzzbjt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbju.zza(this, paramParcel, paramInt);
  }
  
  public List<zzbjr> zzUp()
  {
    return this.zzbWA;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */