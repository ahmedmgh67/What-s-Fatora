package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public class zzp
  extends zza
  implements PlaceLikelihood
{
  public static final Parcelable.Creator<zzp> CREATOR = new zzq();
  final int mVersionCode;
  final PlaceEntity zzbmi;
  final float zzbmj;
  
  zzp(int paramInt, PlaceEntity paramPlaceEntity, float paramFloat)
  {
    this.mVersionCode = paramInt;
    this.zzbmi = paramPlaceEntity;
    this.zzbmj = paramFloat;
  }
  
  public static zzp zza(PlaceEntity paramPlaceEntity, float paramFloat)
  {
    return new zzp(0, (PlaceEntity)zzac.zzw(paramPlaceEntity), paramFloat);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzp)) {
        return false;
      }
      paramObject = (zzp)paramObject;
    } while ((this.zzbmi.equals(((zzp)paramObject).zzbmi)) && (this.zzbmj == ((zzp)paramObject).zzbmj));
    return false;
  }
  
  public float getLikelihood()
  {
    return this.zzbmj;
  }
  
  public Place getPlace()
  {
    return this.zzbmi;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzbmi, Float.valueOf(this.zzbmj) });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("place", this.zzbmi).zzg("likelihood", Float.valueOf(this.zzbmj)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
  
  public PlaceLikelihood zzIo()
  {
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */