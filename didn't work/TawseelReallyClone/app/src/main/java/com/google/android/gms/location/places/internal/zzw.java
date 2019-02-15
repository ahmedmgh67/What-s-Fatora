package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzh.zza;

public class zzw
  implements PlacePhotoMetadata
{
  private int mIndex;
  private final int zzLT;
  private final int zzLU;
  private final String zzbmy;
  private final CharSequence zzbmz;
  
  public zzw(String paramString, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3)
  {
    this.zzbmy = paramString;
    this.zzLT = paramInt1;
    this.zzLU = paramInt2;
    this.zzbmz = paramCharSequence;
    this.mIndex = paramInt3;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzw)) {
        return false;
      }
      paramObject = (zzw)paramObject;
    } while ((((zzw)paramObject).zzLT == this.zzLT) && (((zzw)paramObject).zzLU == this.zzLU) && (zzaa.equal(((zzw)paramObject).zzbmy, this.zzbmy)) && (zzaa.equal(((zzw)paramObject).zzbmz, this.zzbmz)));
    return false;
  }
  
  public CharSequence getAttributions()
  {
    return this.zzbmz;
  }
  
  public int getMaxHeight()
  {
    return this.zzLU;
  }
  
  public int getMaxWidth()
  {
    return this.zzLT;
  }
  
  public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient)
  {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, final int paramInt1, final int paramInt2)
  {
    paramGoogleApiClient.zza(new zzh.zza(Places.GEO_DATA_API, paramGoogleApiClient)
    {
      protected void zza(zzh paramAnonymouszzh)
        throws RemoteException
      {
        paramAnonymouszzh.zza(new com.google.android.gms.location.places.zzh(this), zzw.zza(zzw.this), paramInt1, paramInt2, zzw.zzb(zzw.this));
      }
    });
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.zzLT), Integer.valueOf(this.zzLU), this.zzbmy, this.zzbmz });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public PlacePhotoMetadata zzIp()
  {
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */