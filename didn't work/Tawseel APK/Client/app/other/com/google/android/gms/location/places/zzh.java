package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.internal.zzk.zza;

public class zzh
  extends zzk.zza
{
  private final zzb zzbkU;
  private final zza zzbkV;
  
  public zzh(zza paramzza)
  {
    this.zzbkU = null;
    this.zzbkV = paramzza;
  }
  
  public zzh(zzb paramzzb)
  {
    this.zzbkU = paramzzb;
    this.zzbkV = null;
  }
  
  public void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult)
    throws RemoteException
  {
    this.zzbkU.zzb(paramPlacePhotoMetadataResult);
  }
  
  public void zza(PlacePhotoResult paramPlacePhotoResult)
    throws RemoteException
  {
    this.zzbkV.zzb(paramPlacePhotoResult);
  }
  
  public static abstract class zza<A extends Api.zze>
    extends zzo.zzb<PlacePhotoResult, A>
  {
    public zza(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlacePhotoResult zzbp(Status paramStatus)
    {
      return new PlacePhotoResult(paramStatus, null);
    }
  }
  
  public static abstract class zzb<A extends Api.zze>
    extends zzo.zzb<PlacePhotoMetadataResult, A>
  {
    public zzb(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlacePhotoMetadataResult zzbq(Status paramStatus)
    {
      return new PlacePhotoMetadataResult(paramStatus, null);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */