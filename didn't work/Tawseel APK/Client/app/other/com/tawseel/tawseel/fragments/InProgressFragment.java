package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.urlshortener.Urlshortener;
import com.google.api.services.urlshortener.Urlshortener.Builder;
import com.google.api.services.urlshortener.Urlshortener.Url;
import com.google.api.services.urlshortener.Urlshortener.Url.Insert;
import com.google.api.services.urlshortener.model.Url;
import com.google.firebase.database.DataSnapshot;
import com.tawseel.tawseel.Constants.TripStatus;
import com.tawseel.tawseel.CustomAlertDialog.Builder;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.activities.BaseActivity;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Driver;
import com.tawseel.tawseel.models.Order;
import com.tawseel.tawseel.models.Rider;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InProgressFragment
  extends BaseTripFragment
  implements OnMapReadyCallback
{
  private TextView carID;
  private int dialogMessage = 0;
  private CircleImageView driverImage;
  private TextView driverName;
  private TextView driverRate;
  private Double endLat;
  private Double endLng;
  private Boolean goToNextFragment = Boolean.valueOf(false);
  private MapView mapView;
  private ProgressDialog progress;
  private Double startLat;
  private Double startLng;
  
  private void addMarkers()
  {
    this.startLat = this.order.pickupLat;
    this.startLng = this.order.pickupLng;
    this.endLat = this.order.dropOffLat;
    this.endLng = this.order.dropOffLng;
    this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.startLat.doubleValue(), this.startLng.doubleValue()), 15.0F));
    this.googleMap.addMarker(new MarkerOptions().position(new LatLng(this.startLat.doubleValue(), this.startLng.doubleValue())).icon(BitmapDescriptorFactory.fromResource(2130837670)));
    this.googleMap.addMarker(new MarkerOptions().position(new LatLng(this.endLat.doubleValue(), this.endLng.doubleValue())).icon(BitmapDescriptorFactory.fromResource(2130837631)));
  }
  
  private void bindDriverData(final Long paramLong)
  {
    if (paramLong != null) {
      FirebaseHelper.getInstance().getDriverData(paramLong, new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          paramAnonymousObject = (Driver)paramAnonymousObject;
          if (paramAnonymousObject != null)
          {
            InProgressFragment.this.driverName.setText(((Driver)paramAnonymousObject).name);
            InProgressFragment.this.driverRate.setText(String.valueOf(Math.floor(((Driver)paramAnonymousObject).getRatingAverage() * 100.0D) / 100.0D));
            InProgressFragment.this.carID.setText(((Driver)paramAnonymousObject).carPlateNumber);
            if (((Driver)paramAnonymousObject).hasProfile) {
              FirebaseHelper.getInstance().getDriverImageFromStorage(paramLong, new GenericCallback()
              {
                public void onError(Object paramAnonymous2Object, String paramAnonymous2String) {}
                
                public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
                {
                  paramAnonymous2Object = (byte[])paramAnonymous2Object;
                  paramAnonymous2Object = BitmapFactory.decodeByteArray((byte[])paramAnonymous2Object, 0, paramAnonymous2Object.length);
                  InProgressFragment.this.driverImage.setImageBitmap((Bitmap)paramAnonymous2Object);
                }
              });
            }
          }
        }
      });
    }
  }
  
  private void getOrderDetails()
  {
    if ((getArguments() != null) && (getArguments().getParcelable("order") != null)) {
      this.order = ((Order)getArguments().getParcelable("order"));
    }
  }
  
  private void goToNextFragment()
  {
    try
    {
      removeOrderListener();
      Bundle localBundle = new Bundle();
      localBundle.putString("orderId", this.order.orderID);
      localBundle.putBoolean("showNextButton", true);
      ((BaseActivity)getActivity()).replaceFragment(localBundle);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.goToNextFragment = Boolean.valueOf(true);
    }
  }
  
  protected void analyseChild(DataSnapshot paramDataSnapshot)
  {
    if (paramDataSnapshot.getKey().equals("status"))
    {
      paramDataSnapshot = paramDataSnapshot.getValue().toString();
      if (!paramDataSnapshot.equals(Constants.TripStatus.DELIVERED.getValue())) {}
    }
    do
    {
      try
      {
        new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099722)).setPositiveButton(17039370, null).show();
        goToNextFragment();
        if (!paramDataSnapshot.equals(Constants.TripStatus.RETURNED_TO_SENDER.getValue())) {}
      }
      catch (NullPointerException localNullPointerException)
      {
        do
        {
          try
          {
            new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099816)).setPositiveButton(17039370, null).show();
            goToNextFragment();
            return;
            localNullPointerException = localNullPointerException;
            this.dialogMessage = 2131099722;
          }
          catch (NullPointerException paramDataSnapshot)
          {
            for (;;)
            {
              this.dialogMessage = 2131099816;
            }
          }
          if (paramDataSnapshot.equals(Constants.TripStatus.RETURNING_TO_SENDER.getValue())) {
            try
            {
              new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099772)).setPositiveButton(17039370, null).show();
              return;
            }
            catch (NullPointerException paramDataSnapshot)
            {
              this.dialogMessage = 2131099772;
              return;
            }
          }
        } while (!paramDataSnapshot.equals(Constants.TripStatus.WAITING_AT_PICKUP.getValue()));
        return;
      }
      if (paramDataSnapshot.getKey().equals("driverCurrentLocation"))
      {
        paramDataSnapshot = (HashMap)paramDataSnapshot.getValue();
        Log.d("Tawseel", "InProgress:driverLocation " + paramDataSnapshot);
        Log.d("Tawseel", "InProgress:driverLocation " + this.order.driverID);
        paramDataSnapshot = (ArrayList)paramDataSnapshot.get("l");
        this.driverLat = ((Double)paramDataSnapshot.get(0));
        this.driverLng = ((Double)paramDataSnapshot.get(1));
        Log.d("Tawseel", "driverLat " + this.driverLat);
        Log.d("Tawseel", "driverLng " + this.driverLng);
        drawCurrentDriverLocation();
        return;
      }
    } while (!paramDataSnapshot.getKey().equals("remainingTime"));
    try
    {
      if ((paramDataSnapshot.getValue() instanceof Number)) {
        this.order.remainingTime = ((Long)paramDataSnapshot.getValue());
      }
      for (;;)
      {
        setEstimateTime();
        return;
        if ((paramDataSnapshot.getValue() instanceof String)) {
          this.order.remainingTime = Long.valueOf((String)paramDataSnapshot.getValue());
        }
      }
    }
    catch (Exception paramDataSnapshot)
    {
      for (;;)
      {
        paramDataSnapshot.printStackTrace();
      }
    }
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.order = ((Order)getArguments().getParcelable("order"));
    }
    paramBundle = ((MainActivity)getActivity()).getDeclineTextView();
    if ((paramBundle != null) && (paramBundle.getVisibility() == 0)) {
      paramBundle.setVisibility(8);
    }
    paramBundle = ((MainActivity)getActivity()).getBack();
    if (paramBundle != null) {
      paramBundle.setVisibility(8);
    }
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    setActionBarTitle(2131099829);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903099, paramViewGroup, false);
    this.progress = new ProgressDialog(getActivity());
    this.progress.setMessage(getString(2131099752));
    this.progress.setCancelable(false);
    this.timeDiff = ((TextView)paramLayoutInflater.findViewById(2131493056));
    this.minUnit = ((TextView)paramLayoutInflater.findViewById(2131493057));
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493080);
    Button localButton = (Button)paramLayoutInflater.findViewById(2131493079);
    this.driverImage = ((CircleImageView)paramLayoutInflater.findViewById(2131493051));
    this.driverName = ((TextView)paramLayoutInflater.findViewById(2131493052));
    this.carID = ((TextView)paramLayoutInflater.findViewById(2131493053));
    this.driverRate = ((TextView)paramLayoutInflater.findViewById(2131493054));
    this.mapView = ((MapView)paramLayoutInflater.findViewById(2131493069));
    getOrderDetails();
    if ((this.order != null) && (this.order.driverID != null)) {
      bindDriverData(this.order.driverID);
    }
    if (Build.VERSION.SDK_INT >= 17) {
      this.mapView.setLayoutDirection(0);
    }
    this.mapView.onCreate(paramBundle);
    this.mapView.getMapAsync(this);
    paramViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        InProgressFragment.this.onCallButtonClick();
      }
    });
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (InProgressFragment.this.order != null) {
          new InProgressFragment.GetShortenUrlAsyncTask(InProgressFragment.this, "http://tawseel.sa/shareTripV2.html?id=" + InProgressFragment.this.order.orderID).execute(new Void[0]);
        }
      }
    });
    this.mapTypeButton = ((ImageView)paramLayoutInflater.findViewById(2131493075));
    this.mapTypeLayout = ((RelativeLayout)paramLayoutInflater.findViewById(2131493074));
    this.mapTypeLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        InProgressFragment.this.changeMapType();
      }
    });
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    this.mapView.onDestroy();
    super.onDestroy();
  }
  
  public void onMapReady(GoogleMap paramGoogleMap)
  {
    this.googleMap = paramGoogleMap;
    addOrderListener();
    if ((getActivity() != null) && (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0))
    {
      addMarkers();
      drawPath(this.startLat.doubleValue(), this.startLng.doubleValue(), this.endLat.doubleValue(), this.endLng.doubleValue());
    }
  }
  
  public void onPause()
  {
    this.mapView.onPause();
    HelperMethods.dismissDialog(((MainActivity)getActivity()).getDriverAcceptedDialog());
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.mapView.onResume();
    if (this.goToNextFragment.booleanValue()) {
      goToNextFragment();
    }
    if (this.dialogMessage != 0)
    {
      new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(this.dialogMessage)).setPositiveButton(17039370, null).show();
      this.dialogMessage = 0;
    }
  }
  
  private class GetShortenUrlAsyncTask
    extends AsyncTask<Void, Void, String>
  {
    String longUrl;
    Url url;
    Urlshortener urlshortener;
    
    public GetShortenUrlAsyncTask(String paramString)
    {
      this.longUrl = paramString;
    }
    
    protected String doInBackground(Void... paramVarArgs)
    {
      try
      {
        paramVarArgs = this.urlshortener.url().insert(this.url);
        paramVarArgs.setKey(InProgressFragment.this.getString(2131099886));
        this.url = ((Url)paramVarArgs.execute());
        paramVarArgs = this.url.getId();
        return paramVarArgs;
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return this.longUrl;
    }
    
    protected void onPostExecute(String paramString)
    {
      HelperMethods.dismissDialog(InProgressFragment.this.progress);
      if (InProgressFragment.this.getActivity() != null)
      {
        String str = CustomApplication.getCurrentUser().name;
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", str + " " + InProgressFragment.this.getResources().getText(2131099840) + " " + paramString);
        localIntent.setType("text/plain");
        InProgressFragment.this.startActivity(Intent.createChooser(localIntent, InProgressFragment.this.getResources().getText(2131099833)));
      }
    }
    
    protected void onPreExecute()
    {
      InProgressFragment.this.progress.show();
      this.urlshortener = new Urlshortener.Builder(AndroidHttp.newCompatibleTransport(), AndroidJsonFactory.getDefaultInstance(), null).build();
      this.url = new Url();
      this.url.setLongUrl(this.longUrl);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\InProgressFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */