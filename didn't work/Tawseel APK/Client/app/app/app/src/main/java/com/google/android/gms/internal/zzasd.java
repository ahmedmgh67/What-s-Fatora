package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.List;

public class zzasd
  extends zza
{
  public static final Parcelable.Creator<zzasd> CREATOR = new zzase();
  final int mVersionCode;
  private final String zzblg;
  private final List<String> zzbmI;
  
  zzasd(int paramInt, @NonNull String paramString, @NonNull List<String> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzblg = paramString;
    this.zzbmI = paramList;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzasd)) {
        return false;
      }
      paramObject = (zzasd)paramObject;
    } while ((this.zzblg.equals(((zzasd)paramObject).zzblg)) && (this.zzbmI.equals(((zzasd)paramObject).zzbmI)));
    return false;
  }
  
  public String getPlaceId()
  {
    return this.zzblg;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzblg, this.zzbmI });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("placeId", this.zzblg).zzg("placeAliases", this.zzbmI).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzase.zza(this, paramParcel, paramInt);
  }
  
  public List<String> zzIr()
  {
    return this.zzbmI;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */