package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zzbjn
  extends zza
{
  public static final Parcelable.Creator<zzbjn> CREATOR = new zzbjo();
  @zzbjd
  public final int mVersionCode;
  private List<zzbjl> zzbWu;
  
  public zzbjn()
  {
    this.mVersionCode = 1;
    this.zzbWu = new ArrayList();
  }
  
  zzbjn(int paramInt, List<zzbjl> paramList)
  {
    this.mVersionCode = paramInt;
    if (paramList == null) {}
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList))
    {
      this.zzbWu = paramList;
      return;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjo.zza(this, paramParcel, paramInt);
  }
  
  public List<zzbjl> zzUr()
  {
    return this.zzbWu;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */