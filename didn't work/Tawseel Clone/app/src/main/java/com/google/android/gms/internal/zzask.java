package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.List;

@Deprecated
public class zzask
  extends zza
{
  public static final Parcelable.Creator<zzask> CREATOR = new zzasm();
  final int mVersionCode;
  private final String zzaht;
  private final String zzblg;
  private final List<zzasi> zzbmI;
  
  zzask(int paramInt, String paramString1, String paramString2, List<zzasi> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzaht = paramString1;
    this.zzblg = paramString2;
    this.zzbmI = paramList;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzask)) {
        return false;
      }
      paramObject = (zzask)paramObject;
    } while ((this.zzaht.equals(((zzask)paramObject).zzaht)) && (this.zzblg.equals(((zzask)paramObject).zzblg)) && (this.zzbmI.equals(((zzask)paramObject).zzbmI)));
    return false;
  }
  
  public String getPlaceId()
  {
    return this.zzblg;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzaht, this.zzblg, this.zzbmI });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("accountName", this.zzaht).zzg("placeId", this.zzblg).zzg("placeAliases", this.zzbmI).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzasm.zza(this, paramParcel, paramInt);
  }
  
  public List<zzasi> zzIr()
  {
    return this.zzbmI;
  }
  
  public String zzIu()
  {
    return this.zzaht;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */