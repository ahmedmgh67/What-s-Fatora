package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzai;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzt.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapView
  extends FrameLayout
{
  private final zzb zzbnV;
  
  public MapView(Context paramContext)
  {
    super(paramContext);
    this.zzbnV = new zzb(this, paramContext, null);
    zzIM();
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.zzbnV = new zzb(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    zzIM();
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzbnV = new zzb(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    zzIM();
  }
  
  public MapView(Context paramContext, GoogleMapOptions paramGoogleMapOptions)
  {
    super(paramContext);
    this.zzbnV = new zzb(this, paramContext, paramGoogleMapOptions);
    zzIM();
  }
  
  private void zzIM()
  {
    setClickable(true);
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback)
  {
    zzac.zzdn("getMapAsync() must be called on the main thread");
    this.zzbnV.getMapAsync(paramOnMapReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    this.zzbnV.onCreate(paramBundle);
    if (this.zzbnV.zzAY() == null) {
      zza.zzb(this);
    }
  }
  
  public final void onDestroy()
  {
    this.zzbnV.onDestroy();
  }
  
  public final void onEnterAmbient(Bundle paramBundle)
  {
    zzac.zzdn("onEnterAmbient() must be called on the main thread");
    this.zzbnV.onEnterAmbient(paramBundle);
  }
  
  public final void onExitAmbient()
  {
    zzac.zzdn("onExitAmbient() must be called on the main thread");
    this.zzbnV.onExitAmbient();
  }
  
  public final void onLowMemory()
  {
    this.zzbnV.onLowMemory();
  }
  
  public final void onPause()
  {
    this.zzbnV.onPause();
  }
  
  public final void onResume()
  {
    this.zzbnV.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    this.zzbnV.onSaveInstanceState(paramBundle);
  }
  
  public final void onStart()
  {
    this.zzbnV.onStart();
  }
  
  public final void onStop()
  {
    this.zzbnV.onStop();
  }
  
  static class zza
    implements MapLifecycleDelegate
  {
    private final ViewGroup zzbnW;
    private final IMapViewDelegate zzbnX;
    private View zzbnY;
    
    public zza(ViewGroup paramViewGroup, IMapViewDelegate paramIMapViewDelegate)
    {
      this.zzbnX = ((IMapViewDelegate)zzac.zzw(paramIMapViewDelegate));
      this.zzbnW = ((ViewGroup)zzac.zzw(paramViewGroup));
    }
    
    public void getMapAsync(final OnMapReadyCallback paramOnMapReadyCallback)
    {
      try
      {
        this.zzbnX.getMapAsync(new zzt.zza()
        {
          public void zza(IGoogleMapDelegate paramAnonymousIGoogleMapDelegate)
            throws RemoteException
          {
            paramOnMapReadyCallback.onMapReady(new GoogleMap(paramAnonymousIGoogleMapDelegate));
          }
        });
        return;
      }
      catch (RemoteException paramOnMapReadyCallback)
      {
        throw new RuntimeRemoteException(paramOnMapReadyCallback);
      }
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        this.zzbnX.onCreate(paramBundle);
        this.zzbnY = ((View)zze.zzE(this.zzbnX.getView()));
        this.zzbnW.removeAllViews();
        this.zzbnW.addView(this.zzbnY);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    public void onDestroy()
    {
      try
      {
        this.zzbnX.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public void onEnterAmbient(Bundle paramBundle)
    {
      try
      {
        this.zzbnX.onEnterAmbient(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onExitAmbient()
    {
      try
      {
        this.zzbnX.onExitAmbient();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    public void onLowMemory()
    {
      try
      {
        this.zzbnX.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onPause()
    {
      try
      {
        this.zzbnX.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onResume()
    {
      try
      {
        this.zzbnX.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        this.zzbnX.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onStart()
    {
      try
      {
        this.zzbnX.onStart();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onStop()
    {
      try
      {
        this.zzbnX.onStop();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
  }
  
  static class zzb
    extends zza<MapView.zza>
  {
    private final Context mContext;
    protected zzf<MapView.zza> zzbnT;
    private final List<OnMapReadyCallback> zzbnU = new ArrayList();
    private final ViewGroup zzbnZ;
    private final GoogleMapOptions zzboa;
    
    zzb(ViewGroup paramViewGroup, Context paramContext, GoogleMapOptions paramGoogleMapOptions)
    {
      this.zzbnZ = paramViewGroup;
      this.mContext = paramContext;
      this.zzboa = paramGoogleMapOptions;
    }
    
    public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback)
    {
      if (zzAY() != null)
      {
        ((MapView.zza)zzAY()).getMapAsync(paramOnMapReadyCallback);
        return;
      }
      this.zzbnU.add(paramOnMapReadyCallback);
    }
    
    public void onEnterAmbient(Bundle paramBundle)
    {
      if (zzAY() != null) {
        ((MapView.zza)zzAY()).onEnterAmbient(paramBundle);
      }
    }
    
    public void onExitAmbient()
    {
      if (zzAY() != null) {
        ((MapView.zza)zzAY()).onExitAmbient();
      }
    }
    
    public void zzIL()
    {
      if ((this.zzbnT != null) && (zzAY() == null)) {
        try
        {
          MapsInitializer.initialize(this.mContext);
          Object localObject = zzai.zzbq(this.mContext).zza(zze.zzA(this.mContext), this.zzboa);
          if (localObject == null) {
            return;
          }
          this.zzbnT.zza(new MapView.zza(this.zzbnZ, (IMapViewDelegate)localObject));
          localObject = this.zzbnU.iterator();
          while (((Iterator)localObject).hasNext())
          {
            OnMapReadyCallback localOnMapReadyCallback = (OnMapReadyCallback)((Iterator)localObject).next();
            ((MapView.zza)zzAY()).getMapAsync(localOnMapReadyCallback);
          }
          return;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeRemoteException(localRemoteException);
          this.zzbnU.clear();
          return;
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
      }
    }
    
    protected void zza(zzf<MapView.zza> paramzzf)
    {
      this.zzbnT = paramzzf;
      zzIL();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\MapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */