package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zzbjx
  extends zza
{
  public static final Parcelable.Creator<zzbjx> CREATOR = new zzbjy();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("values")
  private List<String> zzbWD;
  
  public zzbjx()
  {
    this(null);
  }
  
  zzbjx(int paramInt, List<String> paramList)
  {
    this.mVersionCode = paramInt;
    if ((paramList == null) || (paramList.isEmpty()))
    {
      this.zzbWD = Collections.emptyList();
      return;
    }
    this.zzbWD = Collections.unmodifiableList(paramList);
  }
  
  public zzbjx(@Nullable List<String> paramList)
  {
    this.mVersionCode = 1;
    this.zzbWD = new ArrayList();
    if ((paramList != null) && (!paramList.isEmpty())) {
      this.zzbWD.addAll(paramList);
    }
  }
  
  public static zzbjx zzUB()
  {
    return new zzbjx(null);
  }
  
  public static zzbjx zza(zzbjx paramzzbjx)
  {
    if (paramzzbjx != null) {}
    for (paramzzbjx = paramzzbjx.zzUA();; paramzzbjx = null) {
      return new zzbjx(paramzzbjx);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjy.zza(this, paramParcel, paramInt);
  }
  
  public List<String> zzUA()
  {
    return this.zzbWD;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */