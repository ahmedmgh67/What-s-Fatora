package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.Set;

public final class zzf
  extends zza
{
  public static final Parcelable.Creator<zzf> CREATOR = new zzg();
  private int mPriority = 110;
  private final int mVersionCode;
  private final int zzbja;
  private final int zzbkP;
  @Deprecated
  private final PlaceFilter zzbkQ;
  private final zzd zzbkR;
  private final boolean zzbkS;
  private final int zzbkT;
  
  zzf(int paramInt1, int paramInt2, int paramInt3, PlaceFilter paramPlaceFilter, zzd paramzzd, boolean paramBoolean, int paramInt4, int paramInt5)
  {
    this.mVersionCode = paramInt1;
    this.zzbja = paramInt2;
    this.zzbkP = paramInt3;
    if (paramzzd != null) {
      this.zzbkR = paramzzd;
    }
    for (;;)
    {
      this.zzbkQ = null;
      this.zzbkS = paramBoolean;
      this.zzbkT = paramInt4;
      this.mPriority = paramInt5;
      return;
      if (paramPlaceFilter != null)
      {
        if ((paramPlaceFilter.getPlaceIds() != null) && (!paramPlaceFilter.getPlaceIds().isEmpty())) {
          this.zzbkR = zzd.zzm(paramPlaceFilter.getPlaceIds());
        } else if ((paramPlaceFilter.zzHQ() != null) && (!paramPlaceFilter.zzHQ().isEmpty())) {
          this.zzbkR = zzd.zzn(paramPlaceFilter.zzHQ());
        } else {
          this.zzbkR = null;
        }
      }
      else {
        this.zzbkR = null;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzf)) {
        return false;
      }
      paramObject = (zzf)paramObject;
    } while ((this.zzbja == ((zzf)paramObject).zzbja) && (this.zzbkP == ((zzf)paramObject).zzbkP) && (zzaa.equal(this.zzbkR, ((zzf)paramObject).zzbkR)) && (this.mPriority == ((zzf)paramObject).mPriority));
    return false;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.zzbja), Integer.valueOf(this.zzbkP), this.zzbkR, Integer.valueOf(this.mPriority) });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("transitionTypes", Integer.valueOf(this.zzbja)).zzg("loiteringTimeMillis", Integer.valueOf(this.zzbkP)).zzg("nearbyAlertFilter", this.zzbkR).zzg("priority", Integer.valueOf(this.mPriority)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public int zzHH()
  {
    return this.zzbja;
  }
  
  public int zzHL()
  {
    return this.zzbkP;
  }
  
  @Deprecated
  public PlaceFilter zzHM()
  {
    return null;
  }
  
  public zzd zzHN()
  {
    return this.zzbkR;
  }
  
  public boolean zzHO()
  {
    return this.zzbkS;
  }
  
  public int zzHP()
  {
    return this.zzbkT;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */