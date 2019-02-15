package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.tawseel.tawseel.CustomAlertDialog.Builder;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.AppVersionHelper;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Order;
import java.util.HashMap;

public class IntroFragment
  extends BaseFragment
  implements OnMapReadyCallback, LocationListener, GeoQueryEventListener, GoogleMap.OnCameraChangeListener
{
  private static final int INITIAL_ZOOM_LEVEL = 15;
  private static final int LOCATOIN_REQ_PERMISSION = 1;
  private static final String TAG = IntroFragment.class.getSimpleName();
  private static final double myCircleRadius = Settings.DriversSearchRadius;
  private Button button;
  private Boolean dragged = Boolean.valueOf(false);
  private HashMap<String, Marker> driversMarkers;
  private GeoQuery geoQuery;
  private GoogleMap googleMap;
  private double lat;
  private double lng;
  private View locationButton;
  RelativeLayout mainLayout;
  private ImageView mapTypeButton;
  private MapView mapView;
  private ProgressDialog progress;
  
  private void animateMarkerTo(final Marker paramMarker, final double paramDouble1, double paramDouble2, int paramInt)
  {
    final Handler localHandler = new Handler();
    localHandler.post(new Runnable()
    {
      public void run()
      {
        float f1 = (float)(SystemClock.uptimeMillis() - this.val$start) / 1000.0F;
        float f2 = paramDouble1.getInterpolation(f1);
        double d1 = this.val$lat;
        double d2 = paramMarker.latitude;
        double d3 = f2;
        double d4 = paramMarker.latitude;
        double d5 = localHandler;
        double d6 = paramMarker.longitude;
        double d7 = f2;
        double d8 = paramMarker.longitude;
        this.val$marker.setPosition(new LatLng((d1 - d2) * d3 + d4, (d5 - d6) * d7 + d8));
        if (f1 < 1.0F) {
          this.val$handler.postDelayed(this, this.val$delayTime);
        }
      }
    });
  }
  
  private void goToNextFragment()
  {
    this.button.setEnabled(false);
    getMainActivity().setOrder(new Order());
    getMainActivity().replaceFragment(null);
    final AppVersionHelper localAppVersionHelper = new AppVersionHelper(getActivity());
    localAppVersionHelper.isUpdated(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (IntroFragment.this.getActivity() != null) {
          IntroFragment.this.button.setEnabled(true);
        }
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (IntroFragment.this.getActivity() != null)
        {
          if (!((Boolean)paramAnonymousObject).booleanValue()) {
            localAppVersionHelper.showUpdateVersionAlert();
          }
          IntroFragment.this.button.setEnabled(true);
        }
      }
    });
  }
  
  private void initCurrentLocation()
  {
    for (;;)
    {
      try
      {
        Log.d(TAG, "init current location");
        GoogleMap localGoogleMap = this.googleMap;
        if (localGoogleMap == null) {
          return;
        }
        if ((ActivityCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) && (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_COARSE_LOCATION") != 0))
        {
          Log.d(TAG, "permission requested");
          requestPermissions(new String[] { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" }, 1);
          continue;
        }
        localLocation = ((MainActivity)getActivity()).getLocation();
      }
      finally {}
      Location localLocation;
      this.googleMap.setMyLocationEnabled(true);
      if (localLocation != null)
      {
        this.lat = localLocation.getLatitude();
        this.lng = localLocation.getLongitude();
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.lat, this.lng), 15.0F));
        this.googleMap.setOnCameraChangeListener(this);
        Log.d(TAG, "onMapReady:setCenterLocationToGeoFire before " + this.geoQuery);
        this.geoQuery = FirebaseHelper.getInstance().setCenterLocationToGeoFire(this, this.lat, this.lng, myCircleRadius);
        Log.d(TAG, "onMapReady:setCenterLocationToGeoFire after " + this.geoQuery);
      }
      else
      {
        Toast.makeText(getActivity(), getText(2131099754), 1).show();
        getMainActivity().showGapsAlertDialogIfNotEnable();
        this.googleMap.setOnCameraChangeListener(this);
      }
    }
  }
  
  private void showMessage()
  {
    if (getActivity() != null) {
      new CustomAlertDialog.Builder(getActivity()).setMessage(getString(2131099847)).setNegativeButton(getString(2131099765), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          try
          {
            ((MainActivity)IntroFragment.this.getActivity()).goToHistoryFromIntro();
            return;
          }
          catch (IllegalStateException paramAnonymousDialogInterface)
          {
            paramAnonymousDialogInterface.printStackTrace();
          }
        }
      }).setPositiveButton(getString(2131099866), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          IntroFragment.this.goToNextFragment();
        }
      }).setCancelable(false).show();
    }
  }
  
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    Log.d("Tawseel", "onActivityCreated1");
  }
  
  public void onCameraChange(CameraPosition paramCameraPosition)
  {
    this.dragged = Boolean.valueOf(true);
    Log.d("Tawseel", "onCameraChange");
    paramCameraPosition = paramCameraPosition.target;
    this.lat = paramCameraPosition.latitude;
    this.lng = paramCameraPosition.longitude;
    Log.d(TAG, "onCameraChange:setCenterLocationToGeoFire before " + this.geoQuery);
    this.geoQuery = FirebaseHelper.getInstance().setCenterLocationToGeoFire(this, this.lat, this.lng, myCircleRadius);
    Log.d(TAG, "onCameraChange:setCenterLocationToGeoFire after " + this.geoQuery);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ((MainActivity)getActivity()).setPackageImageFilePath(null);
    paramBundle = ((MainActivity)getActivity()).getDeclineTextView();
    if ((paramBundle != null) && (paramBundle.getVisibility() == 0)) {
      paramBundle.setVisibility(8);
    }
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable final ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    Log.d(TAG, "onCreateView");
    paramLayoutInflater = paramLayoutInflater.inflate(2130903100, paramViewGroup, false);
    setActionBarTitle(2131099819);
    paramViewGroup = ((MainActivity)getActivity()).getBack();
    if (paramViewGroup != null) {
      paramViewGroup.setVisibility(8);
    }
    this.driversMarkers = new HashMap();
    this.mainLayout = ((RelativeLayout)paramLayoutInflater.findViewById(2131493068));
    this.mapView = ((MapView)paramLayoutInflater.findViewById(2131493069));
    if (Build.VERSION.SDK_INT >= 17) {
      this.mapView.setLayoutDirection(0);
    }
    this.mapView.onCreate(paramBundle);
    this.mapView.getMapAsync(this);
    this.button = ((Button)paramLayoutInflater.findViewById(2131493071));
    this.button.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        IntroFragment.this.button.setEnabled(false);
        IntroFragment.access$102(IntroFragment.this, new ProgressDialog(IntroFragment.this.getActivity()));
        IntroFragment.this.progress.setMessage(IntroFragment.this.getString(2131099752));
        IntroFragment.this.progress.setCancelable(false);
        IntroFragment.this.progress.show();
        IntroFragment.this.button.setEnabled(true);
        FirebaseHelper.getInstance().isThereAnOrderRunning(new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            HelperMethods.dismissDialog(IntroFragment.this.progress);
          }
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            HelperMethods.dismissDialog(IntroFragment.this.progress);
            if ((paramAnonymous2Object instanceof Boolean))
            {
              if (((Boolean)paramAnonymous2Object).booleanValue()) {
                IntroFragment.this.showMessage();
              }
            }
            else {
              return;
            }
            IntroFragment.this.goToNextFragment();
          }
        });
      }
    });
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493081);
    paramViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramViewGroup.setEnabled(false);
        ((MainActivity)IntroFragment.this.getActivity()).goToHistoryFromIntro();
      }
    });
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    this.mapView.onDestroy();
    super.onDestroy();
  }
  
  public void onGeoQueryError(DatabaseError paramDatabaseError)
  {
    if (getActivity() != null) {
      new CustomAlertDialog.Builder(getActivity()).setTitle("Error").setMessage("There was an unexpected error querying GeoFire: " + paramDatabaseError.getMessage()).setPositiveButton(17039370, null).setIcon(17301543).show();
    }
  }
  
  public void onGeoQueryReady() {}
  
  public void onKeyEntered(String paramString, GeoLocation paramGeoLocation)
  {
    Log.d(TAG, "onKeyEntered " + paramString);
    Marker localMarker = (Marker)this.driversMarkers.get(paramString);
    if (localMarker != null)
    {
      animateMarkerTo(localMarker, paramGeoLocation.latitude, paramGeoLocation.longitude, 16);
      return;
    }
    paramGeoLocation = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(paramGeoLocation.latitude, paramGeoLocation.longitude)).icon(BitmapDescriptorFactory.fromResource(2130837600)));
    paramGeoLocation.setAnchor(0.5F, 0.5F);
    this.driversMarkers.put(paramString, paramGeoLocation);
  }
  
  public void onKeyExited(String paramString)
  {
    Log.d(TAG, "onKeyExited " + paramString);
    Marker localMarker = (Marker)this.driversMarkers.get(paramString);
    if (localMarker != null)
    {
      localMarker.setVisible(false);
      localMarker.remove();
      this.driversMarkers.remove(paramString);
    }
  }
  
  public void onKeyMoved(String paramString, GeoLocation paramGeoLocation)
  {
    paramString = (Marker)this.driversMarkers.get(paramString);
    if (paramString != null) {
      animateMarkerTo(paramString, paramGeoLocation.latitude, paramGeoLocation.longitude, 16);
    }
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    if (!this.dragged.booleanValue())
    {
      this.lat = paramLocation.getLatitude();
      this.lng = paramLocation.getLongitude();
      Log.d("Tawseel", "onLocationChanged");
      this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.lat, this.lng), 15.0F));
      Log.d(TAG, "onLocationChanged:setCenterLocationToGeoFire before " + this.geoQuery);
      this.geoQuery = FirebaseHelper.getInstance().setCenterLocationToGeoFire(this, this.lat, this.lng, myCircleRadius);
      Log.d(TAG, "onLocationChanged:setCenterLocationToGeoFire after " + this.geoQuery);
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    if (this.mapView != null) {
      this.mapView.onLowMemory();
    }
  }
  
  public void onMapReady(final GoogleMap paramGoogleMap)
  {
    this.googleMap = paramGoogleMap;
    Log.d("Tawseel", "onMapReady");
    final RelativeLayout.LayoutParams localLayoutParams;
    if ((this.mapView != null) && (this.mapView.findViewById(Integer.parseInt("1")) != null) && (this.mapView.findViewById(Integer.parseInt("1")).getParent() != null))
    {
      this.locationButton = ((View)this.mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
      localLayoutParams = (RelativeLayout.LayoutParams)this.locationButton.getLayoutParams();
      localLayoutParams.addRule(9, 0);
      localLayoutParams.addRule(11, -1);
      localLayoutParams.addRule(10, 0);
      localLayoutParams.addRule(12, -1);
      if (Build.VERSION.SDK_INT >= 17) {
        break label177;
      }
      this.locationButton.post(new Runnable()
      {
        public void run()
        {
          int i = IntroFragment.this.locationButton.getWidth();
          if (IntroFragment.this.getActivity() != null)
          {
            Display localDisplay = IntroFragment.this.getActivity().getWindowManager().getDefaultDisplay();
            Point localPoint = new Point();
            localDisplay.getSize(localPoint);
            i = localPoint.x;
          }
          localLayoutParams.setMargins(i - IntroFragment.this.locationButton.getWidth() - 30, 0, 30, 180);
        }
      });
    }
    for (;;)
    {
      this.locationButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          if (Build.VERSION.SDK_INT >= 16) {
            IntroFragment.this.locationButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
          }
          final Object localObject = (RelativeLayout.LayoutParams)IntroFragment.this.locationButton.getLayoutParams();
          ((RelativeLayout.LayoutParams)localObject).addRule(9, 0);
          ((RelativeLayout.LayoutParams)localObject).addRule(11, -1);
          ((RelativeLayout.LayoutParams)localObject).addRule(10, 0);
          ((RelativeLayout.LayoutParams)localObject).addRule(12, -1);
          if (Build.VERSION.SDK_INT < 17) {
            IntroFragment.this.locationButton.post(new Runnable()
            {
              public void run()
              {
                int i = IntroFragment.this.locationButton.getWidth();
                if (IntroFragment.this.getActivity() != null)
                {
                  Display localDisplay = IntroFragment.this.getActivity().getWindowManager().getDefaultDisplay();
                  Point localPoint = new Point();
                  localDisplay.getSize(localPoint);
                  i = localPoint.x;
                }
                localObject.setMargins(i - IntroFragment.this.locationButton.getWidth() - 30, 0, 30, 180);
              }
            });
          }
          for (;;)
          {
            RelativeLayout.LayoutParams localLayoutParams;
            if (IntroFragment.this.getActivity() != null)
            {
              Log.d("Tawseel", "width1" + IntroFragment.this.locationButton.getWidth());
              Log.d("Tawseel", "height1" + IntroFragment.this.locationButton.getHeight());
              localLayoutParams = new RelativeLayout.LayoutParams(IntroFragment.this.locationButton.getWidth(), IntroFragment.this.locationButton.getHeight());
              localLayoutParams.addRule(12, -1);
              localLayoutParams.setMargins(0, 0, 30, IntroFragment.this.locationButton.getHeight() + 180 + 10);
              localObject = new RelativeLayout(IntroFragment.this.getActivity());
              ((RelativeLayout)localObject).setBackgroundResource(2130837654);
              ((RelativeLayout)localObject).setLayoutParams(localLayoutParams);
            }
            try
            {
              IntroFragment.this.mainLayout.addView((View)localObject);
              localLayoutParams = new RelativeLayout.LayoutParams(IntroFragment.this.locationButton.getWidth() / 2, IntroFragment.this.locationButton.getHeight() / 2);
              localLayoutParams.addRule(15, -1);
              localLayoutParams.addRule(14, -1);
              IntroFragment.access$502(IntroFragment.this, new ImageView(IntroFragment.this.getActivity()));
              IntroFragment.this.mapTypeButton.setLayoutParams(localLayoutParams);
              IntroFragment.this.mapTypeButton.setImageResource(2130837681);
              ((RelativeLayout)localObject).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  if (IntroFragment.5.this.val$googleMap != null)
                  {
                    if (IntroFragment.5.this.val$googleMap.getMapType() != 2) {
                      break label51;
                    }
                    IntroFragment.5.this.val$googleMap.setMapType(1);
                    IntroFragment.this.mapTypeButton.setImageResource(2130837681);
                  }
                  label51:
                  while (IntroFragment.5.this.val$googleMap.getMapType() != 1) {
                    return;
                  }
                  IntroFragment.5.this.val$googleMap.setMapType(2);
                  IntroFragment.this.mapTypeButton.setImageResource(2130837687);
                }
              });
              try
              {
                ((RelativeLayout)localObject).addView(IntroFragment.this.mapTypeButton);
                return;
              }
              catch (IllegalStateException localIllegalStateException1)
              {
                localIllegalStateException1.printStackTrace();
              }
              ((RelativeLayout.LayoutParams)localObject).setMargins(0, 0, 30, 180);
            }
            catch (IllegalStateException localIllegalStateException2)
            {
              for (;;)
              {
                localIllegalStateException2.printStackTrace();
              }
            }
          }
        }
      });
      initCurrentLocation();
      return;
      label177:
      localLayoutParams.setMargins(0, 0, 30, 180);
    }
  }
  
  public void onPause()
  {
    this.mapView.onPause();
    super.onPause();
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    Log.d(TAG, "on request permission result");
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if ((paramInt == 1) && (paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0)) {
      initCurrentLocation();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    this.mapView.onResume();
    getMainActivity().checkNavigationViewItem(2131493202);
    if (this.geoQuery != null) {}
    try
    {
      this.geoQuery.addGeoQueryEventListener(this);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  
  public void onStop()
  {
    super.onStop();
    this.mapView.onStop();
    if (this.geoQuery != null) {
      this.geoQuery.removeAllListeners();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\IntroFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */