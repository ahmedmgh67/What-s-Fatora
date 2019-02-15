package com.google.android.gms.location.places;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;

public final class zzm
  extends zza
{
  public static final Parcelable.Creator<zzm> CREATOR = new zzn();
  private final int mPriority;
  final int mVersionCode;
  private final long zzbjb;
  private final long zzbjw;
  private final PlaceFilter zzblh;
  private final boolean zzbli;
  private final boolean zzblj;
  
  public zzm(int paramInt1, PlaceFilter paramPlaceFilter, long paramLong1, int paramInt2, long paramLong2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt1;
    this.zzblh = paramPlaceFilter;
    this.zzbjw = paramLong1;
    this.mPriority = paramInt2;
    this.zzbjb = paramLong2;
    this.zzbli = paramBoolean1;
    this.zzblj = paramBoolean2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzm)) {
        return false;
      }
      paramObject = (zzm)paramObject;
    } while ((zzaa.equal(this.zzblh, ((zzm)paramObject).zzblh)) && (this.zzbjw == ((zzm)paramObject).zzbjw) && (this.mPriority == ((zzm)paramObject).mPriority) && (this.zzbjb == ((zzm)paramObject).zzbjb) && (this.zzbli == ((zzm)paramObject).zzbli));
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.zzbjb;
  }
  
  public long getInterval()
  {
    return this.zzbjw;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzblh, Long.valueOf(this.zzbjw), Integer.valueOf(this.mPriority), Long.valueOf(this.zzbjb), Boolean.valueOf(this.zzbli) });
  }
  
  @SuppressLint({"DefaultLocale"})
  public String toString()
  {
    return zzaa.zzv(this).zzg("filter", this.zzblh).zzg("interval", Long.valueOf(this.zzbjw)).zzg("priority", Integer.valueOf(this.mPriority)).zzg("expireAt", Long.valueOf(this.zzbjb)).zzg("receiveFailures", Boolean.valueOf(this.zzbli)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzn.zza(this, paramParcel, paramInt);
  }
  
  @Deprecated
  public PlaceFilter zzHM()
  {
    return this.zzblh;
  }
  
  public boolean zzHT()
  {
    return this.zzbli;
  }
  
  public boolean zzHU()
  {
    return this.zzblj;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */