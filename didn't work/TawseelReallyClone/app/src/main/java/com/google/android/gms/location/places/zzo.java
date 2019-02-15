package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzasl;
import com.google.android.gms.internal.zzzv.zza;
import com.google.android.gms.location.places.internal.zzl.zza;

public class zzo
  extends zzl.zza
{
  private static final String TAG = zzo.class.getSimpleName();
  private final zzd zzblo;
  private final zza zzblp;
  private final zze zzblq;
  private final zzf zzblr;
  private final zzc zzbls;
  
  public zzo(zza paramzza)
  {
    this.zzblo = null;
    this.zzblp = paramzza;
    this.zzblq = null;
    this.zzblr = null;
    this.zzbls = null;
  }
  
  public zzo(zzc paramzzc)
  {
    this.zzblo = null;
    this.zzblp = null;
    this.zzblq = null;
    this.zzblr = null;
    this.zzbls = paramzzc;
  }
  
  public zzo(zzd paramzzd)
  {
    this.zzblo = paramzzd;
    this.zzblp = null;
    this.zzblq = null;
    this.zzblr = null;
    this.zzbls = null;
  }
  
  public zzo(zzf paramzzf)
  {
    this.zzblo = null;
    this.zzblp = null;
    this.zzblq = null;
    this.zzblr = paramzzf;
    this.zzbls = null;
  }
  
  public void zzam(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (this.zzblo != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "placeEstimator cannot be null");
      if (paramDataHolder != null) {
        break;
      }
      if (Log.isLoggable(TAG, 6)) {
        Log.e(TAG, "onPlaceEstimated received null DataHolder", new Throwable());
      }
      this.zzblo.zzA(Status.zzayj);
      return;
    }
    Bundle localBundle = paramDataHolder.zzwy();
    if (localBundle == null) {}
    for (int i = 100;; i = PlaceLikelihoodBuffer.zzM(localBundle))
    {
      paramDataHolder = new PlaceLikelihoodBuffer(paramDataHolder, i);
      this.zzblo.zzb(paramDataHolder);
      return;
    }
  }
  
  public void zzan(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(TAG, 6)) {
        Log.e(TAG, "onAutocompletePrediction received null DataHolder", new Throwable());
      }
      this.zzblp.zzA(Status.zzayj);
      return;
    }
    this.zzblp.zzb(new AutocompletePredictionBuffer(paramDataHolder));
  }
  
  public void zzao(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(TAG, 6)) {
        Log.e(TAG, "onPlaceUserDataFetched received null DataHolder", new Throwable());
      }
      paramDataHolder = Status.zzayj;
      throw new NullPointerException();
    }
    new zzasl(paramDataHolder);
    throw new NullPointerException();
  }
  
  public void zzap(DataHolder paramDataHolder)
    throws RemoteException
  {
    paramDataHolder = new PlaceBuffer(paramDataHolder);
    this.zzbls.zzb(paramDataHolder);
  }
  
  public void zzbr(Status paramStatus)
    throws RemoteException
  {
    this.zzblr.zzb(paramStatus);
  }
  
  public static abstract class zza<A extends Api.zze>
    extends zzo.zzb<AutocompletePredictionBuffer, A>
  {
    public zza(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected AutocompletePredictionBuffer zzbs(Status paramStatus)
    {
      return new AutocompletePredictionBuffer(DataHolder.zzcD(paramStatus.getStatusCode()));
    }
  }
  
  public static abstract class zzb<R extends Result, A extends Api.zze>
    extends zzzv.zza<R, A>
  {
    public zzb(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  public static abstract class zzc<A extends Api.zze>
    extends zzo.zzb<PlaceBuffer, A>
  {
    public zzc(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlaceBuffer zzbt(Status paramStatus)
    {
      return new PlaceBuffer(DataHolder.zzcD(paramStatus.getStatusCode()));
    }
  }
  
  public static abstract class zzd<A extends Api.zze>
    extends zzo.zzb<PlaceLikelihoodBuffer, A>
  {
    public zzd(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlaceLikelihoodBuffer zzbu(Status paramStatus)
    {
      return new PlaceLikelihoodBuffer(DataHolder.zzcD(paramStatus.getStatusCode()), 100);
    }
  }
  
  @Deprecated
  public static abstract class zze<A extends Api.zze>
    extends zzo.zzb<zzasl, A>
  {}
  
  public static abstract class zzf<A extends Api.zze>
    extends zzo.zzb<Status, A>
  {
    public zzf(Api paramApi, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */