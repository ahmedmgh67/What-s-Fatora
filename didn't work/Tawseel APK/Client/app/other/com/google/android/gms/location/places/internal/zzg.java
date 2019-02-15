package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzh.zzb;
import com.google.android.gms.location.places.zzo;
import com.google.android.gms.location.places.zzo.zza;
import com.google.android.gms.location.places.zzo.zzc;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;
import java.util.List;

public class zzg
  implements GeoDataApi
{
  public PendingResult<PlaceBuffer> addPlace(GoogleApiClient paramGoogleApiClient, final AddPlaceRequest paramAddPlaceRequest)
  {
    paramGoogleApiClient.zzb(new zzo.zzc(Places.GEO_DATA_API, paramGoogleApiClient)
    {
      protected void zza(zzh paramAnonymouszzh)
        throws RemoteException
      {
        paramAnonymouszzh.zza(new zzo(this), paramAddPlaceRequest);
      }
    });
  }
  
  public PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(GoogleApiClient paramGoogleApiClient, final String paramString, final LatLngBounds paramLatLngBounds, final AutocompleteFilter paramAutocompleteFilter)
  {
    paramGoogleApiClient.zza(new zzo.zza(Places.GEO_DATA_API, paramGoogleApiClient)
    {
      protected void zza(zzh paramAnonymouszzh)
        throws RemoteException
      {
        paramAnonymouszzh.zza(new zzo(this), paramString, paramLatLngBounds, paramAutocompleteFilter);
      }
    });
  }
  
  public PendingResult<PlaceBuffer> getPlaceById(GoogleApiClient paramGoogleApiClient, final String... paramVarArgs)
  {
    boolean bool = true;
    if ((paramVarArgs != null) && (paramVarArgs.length >= 1)) {}
    for (;;)
    {
      zzac.zzas(bool);
      paramGoogleApiClient.zza(new zzo.zzc(Places.GEO_DATA_API, paramGoogleApiClient)
      {
        protected void zza(zzh paramAnonymouszzh)
          throws RemoteException
        {
          List localList = Arrays.asList(paramVarArgs);
          paramAnonymouszzh.zza(new zzo(this), localList);
        }
      });
      bool = false;
    }
  }
  
  public PendingResult<PlacePhotoMetadataResult> getPlacePhotos(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zza(new zzh.zzb(Places.GEO_DATA_API, paramGoogleApiClient)
    {
      protected void zza(zzh paramAnonymouszzh)
        throws RemoteException
      {
        paramAnonymouszzh.zza(new com.google.android.gms.location.places.zzh(this), paramString);
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */