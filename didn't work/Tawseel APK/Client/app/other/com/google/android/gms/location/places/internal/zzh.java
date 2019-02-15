package com.google.android.gms.location.places.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompleteFilter.Builder;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzo;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public class zzh
  extends zzl<zzj>
{
  private final zzz zzblQ;
  
  private zzh(Context paramContext, Looper paramLooper, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramLooper, 65, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
    paramContext = Locale.getDefault();
    paramLooper = paramContext;
    if (paramPlacesOptions != null)
    {
      paramLooper = paramContext;
      if (paramPlacesOptions.getLocale() != null) {
        paramLooper = paramPlacesOptions.getLocale();
      }
    }
    paramContext = null;
    if ((paramPlacesOptions != null) && (paramPlacesOptions.getAccountName() != null)) {
      paramContext = paramPlacesOptions.getAccountName();
    }
    for (;;)
    {
      this.zzblQ = new zzz(paramString, paramLooper, paramContext, paramPlacesOptions.zzblu, paramPlacesOptions.zzblv);
      return;
      if (paramzzg.getAccount() != null) {
        paramContext = paramzzg.getAccount().name;
      }
    }
  }
  
  public void zza(com.google.android.gms.location.places.zzh paramzzh, String paramString)
    throws RemoteException
  {
    zzac.zzb(paramString, "placeId cannot be null");
    ((zzj)zzwW()).zza(paramString, this.zzblQ, paramzzh);
  }
  
  public void zza(com.google.android.gms.location.places.zzh paramzzh, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    boolean bool2 = true;
    zzac.zzb(paramString, "fifeUrl cannot be null");
    if (paramInt1 > 0)
    {
      bool1 = true;
      zzac.zzb(bool1, "width should be > 0");
      if (paramInt1 <= 0) {
        break label69;
      }
    }
    label69:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "height should be > 0");
      ((zzj)zzwW()).zza(paramString, paramInt1, paramInt2, paramInt3, this.zzblQ, paramzzh);
      return;
      bool1 = false;
      break;
    }
  }
  
  public void zza(zzo paramzzo, AddPlaceRequest paramAddPlaceRequest)
    throws RemoteException
  {
    zzac.zzb(paramAddPlaceRequest, "userAddedPlace == null");
    ((zzj)zzwW()).zza(paramAddPlaceRequest, this.zzblQ, paramzzo);
  }
  
  public void zza(zzo paramzzo, String paramString, @Nullable LatLngBounds paramLatLngBounds, @Nullable AutocompleteFilter paramAutocompleteFilter)
    throws RemoteException
  {
    zzac.zzb(paramzzo, "callback == null");
    if (paramString == null) {
      paramString = "";
    }
    for (;;)
    {
      if (paramAutocompleteFilter == null) {
        paramAutocompleteFilter = new AutocompleteFilter.Builder().build();
      }
      for (;;)
      {
        ((zzj)zzwW()).zza(paramString, paramLatLngBounds, paramAutocompleteFilter, this.zzblQ, paramzzo);
        return;
      }
    }
  }
  
  public void zza(zzo paramzzo, List<String> paramList)
    throws RemoteException
  {
    ((zzj)zzwW()).zzb(paramList, this.zzblQ, paramzzo);
  }
  
  protected zzj zzdk(IBinder paramIBinder)
  {
    return zzj.zza.zzdm(paramIBinder);
  }
  
  protected String zzeu()
  {
    return "com.google.android.gms.location.places.GeoDataApi";
  }
  
  protected String zzev()
  {
    return "com.google.android.gms.location.places.internal.IGooglePlacesService";
  }
  
  public static class zza
    extends Api.zza<zzh, PlacesOptions>
  {
    public zzh zza(Context paramContext, Looper paramLooper, zzg paramzzg, PlacesOptions paramPlacesOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      if (paramPlacesOptions == null) {
        paramPlacesOptions = new PlacesOptions.Builder().build();
      }
      for (;;)
      {
        String str = paramContext.getPackageName();
        if (paramPlacesOptions.zzblt != null) {
          str = paramPlacesOptions.zzblt;
        }
        return new zzh(paramContext, paramLooper, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener, str, paramPlacesOptions, null);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */