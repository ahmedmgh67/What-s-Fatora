package com.google.android.gms.location.places.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzo;
import java.util.Locale;

public class zzn
  extends zzl<zzi>
{
  private final zzz zzblQ;
  private final Locale zzblw = Locale.getDefault();
  
  private zzn(Context paramContext, Looper paramLooper, zzg paramzzg, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramLooper, 67, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
    paramContext = null;
    if (paramzzg.getAccount() != null) {
      paramContext = paramzzg.getAccount().name;
    }
    this.zzblQ = new zzz(paramString, this.zzblw, paramContext, paramPlacesOptions.zzblu, paramPlacesOptions.zzblv);
  }
  
  public void zza(zzo paramzzo, PlaceFilter paramPlaceFilter)
    throws RemoteException
  {
    PlaceFilter localPlaceFilter = paramPlaceFilter;
    if (paramPlaceFilter == null) {
      localPlaceFilter = PlaceFilter.zzHR();
    }
    ((zzi)zzwW()).zza(localPlaceFilter, this.zzblQ, paramzzo);
  }
  
  public void zza(zzo paramzzo, PlaceReport paramPlaceReport)
    throws RemoteException
  {
    zzac.zzw(paramPlaceReport);
    ((zzi)zzwW()).zza(paramPlaceReport, this.zzblQ, paramzzo);
  }
  
  protected zzi zzdp(IBinder paramIBinder)
  {
    return zzi.zza.zzdl(paramIBinder);
  }
  
  protected String zzeu()
  {
    return "com.google.android.gms.location.places.PlaceDetectionApi";
  }
  
  protected String zzev()
  {
    return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
  }
  
  public static class zza
    extends Api.zza<zzn, PlacesOptions>
  {
    public zzn zzb(Context paramContext, Looper paramLooper, zzg paramzzg, PlacesOptions paramPlacesOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
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
        return new zzn(paramContext, paramLooper, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener, str, paramPlacesOptions, null);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */