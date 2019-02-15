package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.tawseel.tawseel.Constants.TripStatus;
import com.tawseel.tawseel.CustomAlertDialog.Builder;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.activities.BaseActivity;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.Formatter;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Driver;
import com.tawseel.tawseel.models.Order;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class EnRouteTripFragment
  extends BaseTripFragment
  implements OnMapReadyCallback
{
  private static final String TAG = EnRouteTripFragment.class.getSimpleName();
  private AlertDialog canceldByRiderDialog;
  private TextView carID;
  private TextView decline;
  private AlertDialog dialogCancelledDriver;
  private AlertDialog dialogGotPackage;
  private int dialogMessage = 0;
  private AlertDialog dialogNoShowSender;
  private CircleImageView driverImage;
  private TextView driverName;
  private TextView driverRate;
  private Boolean firstCall = Boolean.valueOf(true);
  private Boolean goToInvoice = Boolean.valueOf(false);
  private Boolean goToNextFragment = Boolean.valueOf(false);
  private MapView mapView;
  private Boolean newTripFlag = Boolean.valueOf(false);
  private ProgressDialog progress;
  private Boolean restartTripFlag = Boolean.valueOf(false);
  private double startLat;
  private double startLng;
  
  private void addPickupMarker()
  {
    this.startLat = this.order.pickupLat.doubleValue();
    this.startLng = this.order.pickupLng.doubleValue();
    this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.startLat, this.startLng), 15.0F));
    this.googleMap.addMarker(new MarkerOptions().position(new LatLng(this.startLat, this.startLng)).icon(BitmapDescriptorFactory.fromResource(2130837670)));
  }
  
  private void bindDriverData(final Long paramLong, final GenericCallback paramGenericCallback)
  {
    if (paramLong != null) {
      FirebaseHelper.getInstance().getDriverData(paramLong, new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString)
        {
          paramGenericCallback.onError(Boolean.valueOf(false), "Failure");
        }
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          paramAnonymousObject = (Driver)paramAnonymousObject;
          if (paramAnonymousObject != null)
          {
            paramGenericCallback.onSuccess(Boolean.valueOf(true), "Success");
            EnRouteTripFragment.this.driverName.setText(((Driver)paramAnonymousObject).name);
            EnRouteTripFragment.this.driverRate.setText(String.valueOf(Math.floor(((Driver)paramAnonymousObject).getRatingAverage() * 100.0D) / 100.0D));
            EnRouteTripFragment.this.carID.setText(((Driver)paramAnonymousObject).carPlateNumber);
            if (((Driver)paramAnonymousObject).hasProfile) {
              FirebaseHelper.getInstance().getDriverImageFromStorage(paramLong, new GenericCallback()
              {
                public void onError(Object paramAnonymous2Object, String paramAnonymous2String) {}
                
                public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
                {
                  paramAnonymous2Object = (byte[])paramAnonymous2Object;
                  paramAnonymous2Object = BitmapFactory.decodeByteArray((byte[])paramAnonymous2Object, 0, paramAnonymous2Object.length);
                  EnRouteTripFragment.this.driverImage.setImageBitmap((Bitmap)paramAnonymous2Object);
                }
              });
            }
            return;
          }
          paramGenericCallback.onError(Boolean.valueOf(false), "Failure");
        }
      });
    }
  }
  
  private void getOrderDetails(final GenericCallback paramGenericCallback)
  {
    if ((getArguments() != null) && (getArguments().getParcelable("order") != null))
    {
      this.order = ((Order)getArguments().getParcelable("order"));
      Log.d("Tawseel", "order.orderID " + this.order);
      if (this.order != null) {
        FirebaseHelper.getInstance().getOrderById(this.order.orderID, new GenericCallback()
        {
          public void onError(Object paramAnonymousObject, String paramAnonymousString)
          {
            paramGenericCallback.onError(Boolean.valueOf(false), "Failure");
          }
          
          public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
          {
            EnRouteTripFragment.this.order = ((Order)paramAnonymousObject);
            Log.e("Tawseel", "EnRoute:order: " + EnRouteTripFragment.this.order);
            Log.e("Tawseel", "Enroute:order: " + EnRouteTripFragment.this.order.pickupAddress);
            if (EnRouteTripFragment.this.googleMap != null) {
              EnRouteTripFragment.this.addPickupMarker();
            }
            EnRouteTripFragment.this.bindDriverData(EnRouteTripFragment.this.order.driverID, new GenericCallback()
            {
              public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
              {
                EnRouteTripFragment.6.this.val$callback.onError(Boolean.valueOf(false), "Failure");
              }
              
              public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
              {
                EnRouteTripFragment.6.this.val$callback.onSuccess(Boolean.valueOf(true), "Success");
              }
            });
          }
        });
      }
    }
  }
  
  private void goToInvoiceFragment()
  {
    try
    {
      removeOrderListener();
      Bundle localBundle = new Bundle();
      localBundle.putString("orderId", this.order.orderID);
      localBundle.putBoolean("showNextButton", false);
      ((BaseActivity)getActivity()).goToInvoiceFragment(localBundle);
      return;
    }
    catch (Exception localException)
    {
      this.goToInvoice = Boolean.valueOf(true);
      localException.printStackTrace();
    }
  }
  
  private void goToNextFragment()
  {
    try
    {
      removeOrderListener();
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("order", this.order);
      ((BaseActivity)getActivity()).replaceFragment(localBundle);
      return;
    }
    catch (Exception localException)
    {
      this.goToNextFragment = Boolean.valueOf(true);
    }
  }
  
  private void goToSenderDetails()
  {
    try
    {
      removeOrderListener();
      Log.e("here goToSenderDetails", this.order.driverID + "");
      ((MainActivity)getActivity()).restartOrder(this.order);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.restartTripFlag = Boolean.valueOf(true);
    }
  }
  
  private void hideOpenedDialogs()
  {
    HelperMethods.dismissDialog(this.dialogNoShowSender);
    HelperMethods.dismissDialog(this.canceldByRiderDialog);
    HelperMethods.dismissDialog(this.dialogCancelledDriver);
    HelperMethods.dismissDialog(this.dialogGotPackage);
  }
  
  private void startNewOrder()
  {
    try
    {
      removeOrderListener();
      ((MainActivity)getActivity()).newOrder();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.newTripFlag = Boolean.valueOf(true);
    }
  }
  
  private ProgressDialog startProgress()
  {
    ProgressDialog localProgressDialog = new ProgressDialog(getActivity());
    localProgressDialog.setMessage(getString(2131099752));
    localProgressDialog.setCancelable(false);
    localProgressDialog.show();
    return localProgressDialog;
  }
  
  private void stopProgress(ProgressDialog paramProgressDialog)
  {
    if ((getActivity() != null) && (paramProgressDialog != null) && (paramProgressDialog.isShowing())) {
      HelperMethods.dismissDialog(paramProgressDialog);
    }
  }
  
  protected void analyseChild(DataSnapshot paramDataSnapshot)
  {
    if (paramDataSnapshot.getKey().equals("status"))
    {
      paramDataSnapshot = paramDataSnapshot.getValue().toString();
      Log.d(TAG, "FirebaseHelper> " + paramDataSnapshot);
      if (paramDataSnapshot.equals(Constants.TripStatus.DRIVER_CANCELLED.getValue())) {
        hideOpenedDialogs();
      }
    }
    do
    {
      do
      {
        do
        {
          try
          {
            this.dialogCancelledDriver = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099724)).setPositiveButton(17039370, null).show();
            goToSenderDetails();
            return;
          }
          catch (NullPointerException paramDataSnapshot)
          {
            for (;;)
            {
              this.dialogMessage = 2131099724;
            }
          }
          if (paramDataSnapshot.equals(Constants.TripStatus.DRIVER_EARLY_CANCELLED.getValue()))
          {
            hideOpenedDialogs();
            try
            {
              this.dialogCancelledDriver = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099724)).setPositiveButton(17039370, null).show();
              goToSenderDetails();
              return;
            }
            catch (NullPointerException paramDataSnapshot)
            {
              for (;;)
              {
                this.dialogMessage = 2131099724;
              }
            }
          }
          if (paramDataSnapshot.equals(Constants.TripStatus.DELIVERED.getValue()))
          {
            this.decline.setVisibility(8);
            hideOpenedDialogs();
            try
            {
              this.dialogGotPackage = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099723)).setPositiveButton(17039370, null).show();
              goToNextFragment();
              return;
            }
            catch (NullPointerException paramDataSnapshot)
            {
              for (;;)
              {
                this.dialogMessage = 2131099723;
              }
            }
          }
          if (paramDataSnapshot.equals(Constants.TripStatus.DROP_OFF.getValue()))
          {
            this.decline.setVisibility(8);
            hideOpenedDialogs();
            try
            {
              this.dialogGotPackage = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099723)).setPositiveButton(17039370, null).show();
              goToNextFragment();
              return;
            }
            catch (NullPointerException paramDataSnapshot)
            {
              for (;;)
              {
                this.dialogMessage = 2131099723;
              }
            }
          }
          if (paramDataSnapshot.equals(Constants.TripStatus.NO_SENDER.getValue()))
          {
            this.decline.setVisibility(8);
            hideOpenedDialogs();
            try
            {
              this.dialogNoShowSender = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099774)).setPositiveButton(17039370, null).show();
              startNewOrder();
              return;
            }
            catch (NullPointerException paramDataSnapshot)
            {
              for (;;)
              {
                this.dialogMessage = 2131099774;
              }
            }
          }
          if (paramDataSnapshot.equals(Constants.TripStatus.RETURNED_TO_SENDER.getValue()))
          {
            this.decline.setVisibility(8);
            hideOpenedDialogs();
            try
            {
              this.dialogGotPackage = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099723)).setPositiveButton(17039370, null).show();
              goToNextFragment();
              return;
            }
            catch (NullPointerException paramDataSnapshot)
            {
              for (;;)
              {
                this.dialogMessage = 2131099723;
              }
            }
          }
        } while (!paramDataSnapshot.equals(Constants.TripStatus.RETURNING_TO_SENDER.getValue()));
        this.decline.setVisibility(8);
        hideOpenedDialogs();
        try
        {
          this.dialogGotPackage = new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(2131099723)).setPositiveButton(17039370, null).show();
          goToNextFragment();
          return;
        }
        catch (NullPointerException paramDataSnapshot)
        {
          for (;;)
          {
            this.dialogMessage = 2131099723;
          }
        }
        if (!paramDataSnapshot.getKey().equals("driverCurrentLocation")) {
          break;
        }
        paramDataSnapshot = (ArrayList)((HashMap)paramDataSnapshot.getValue()).get("l");
        this.driverLat = Double.valueOf(((Number)paramDataSnapshot.get(0)).doubleValue());
        this.driverLng = Double.valueOf(((Number)paramDataSnapshot.get(1)).doubleValue());
        drawCurrentDriverLocation();
      } while (!this.firstCall.booleanValue());
      this.firstCall = Boolean.valueOf(false);
      drawPath(this.startLat, this.startLng, this.driverLat.doubleValue(), this.driverLng.doubleValue());
      return;
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
    paramBundle = ((MainActivity)getActivity()).getBack();
    if (paramBundle != null) {
      paramBundle.setVisibility(8);
    }
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    setActionBarTitle(2131099828);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903097, paramViewGroup, false);
    this.decline = ((MainActivity)getActivity()).getDeclineTextView();
    ((MainActivity)getActivity()).getBack().setVisibility(8);
    this.timeDiff = ((TextView)paramLayoutInflater.findViewById(2131493056));
    this.minUnit = ((TextView)paramLayoutInflater.findViewById(2131493057));
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493071);
    Button localButton = (Button)paramLayoutInflater.findViewById(2131493072);
    this.driverImage = ((CircleImageView)paramLayoutInflater.findViewById(2131493051));
    this.driverName = ((TextView)paramLayoutInflater.findViewById(2131493052));
    this.carID = ((TextView)paramLayoutInflater.findViewById(2131493053));
    this.driverRate = ((TextView)paramLayoutInflater.findViewById(2131493054));
    this.mapView = ((MapView)paramLayoutInflater.findViewById(2131493069));
    this.progress = startProgress();
    if (Build.VERSION.SDK_INT >= 17) {
      this.mapView.setLayoutDirection(0);
    }
    this.mapView.onCreate(paramBundle);
    this.mapView.getMapAsync(this);
    this.decline.setVisibility(0);
    this.decline.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EnRouteTripFragment.access$002(EnRouteTripFragment.this, new CustomAlertDialog.Builder(EnRouteTripFragment.this.getActivity()).setTitle(EnRouteTripFragment.this.getResources().getString(2131099713)).setMessage(EnRouteTripFragment.this.getResources().getString(2131099708)).setPositiveButton(EnRouteTripFragment.this.getResources().getString(2131099866), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if (EnRouteTripFragment.this.order != null)
            {
              EnRouteTripFragment.access$102(EnRouteTripFragment.this, EnRouteTripFragment.this.startProgress());
              paramAnonymous2DialogInterface = Constants.TripStatus.CLIENT_EARLY_CANCELLED.getValue();
              Object localObject = new Date();
              Date localDate = new Date(EnRouteTripFragment.this.order.acceptedAt.longValue());
              if (Math.abs(Formatter.getInstance(CustomApplication.getAppContext()).getDateDiff((Date)localObject, localDate, TimeUnit.SECONDS)) > Settings.cancellationPenaltyPeriodInSeconds) {
                paramAnonymous2DialogInterface = Constants.TripStatus.CLIENT_CANCELLED.getValue();
              }
              localObject = new ArrayList();
              ((ArrayList)localObject).add(Constants.TripStatus.PICKING_UP.getValue());
              ((ArrayList)localObject).add(Constants.TripStatus.WAITING_AT_PICKUP.getValue());
              FirebaseHelper.getInstance().cancelTrip(EnRouteTripFragment.this.order.orderID, EnRouteTripFragment.this.order.driverID, (ArrayList)localObject, paramAnonymous2DialogInterface, EnRouteTripFragment.this.getMainActivity().getLocation(), new GenericCallback()
              {
                public void onError(Object paramAnonymous3Object, String paramAnonymous3String)
                {
                  EnRouteTripFragment.this.stopProgress(EnRouteTripFragment.this.progress);
                }
                
                public void onSuccess(Object paramAnonymous3Object, String paramAnonymous3String)
                {
                  EnRouteTripFragment.this.stopProgress(EnRouteTripFragment.this.progress);
                  EnRouteTripFragment.this.hideOpenedDialogs();
                  if (paramAnonymous3Object.toString().equals(Constants.TripStatus.CLIENT_CANCELLED.getValue())) {
                    EnRouteTripFragment.this.goToInvoiceFragment();
                  }
                  while (!paramAnonymous3Object.toString().equals(Constants.TripStatus.CLIENT_EARLY_CANCELLED.getValue())) {
                    return;
                  }
                  EnRouteTripFragment.this.goToSenderDetails();
                }
              }, EnRouteTripFragment.this.getActivity());
            }
          }
        }).setNegativeButton(EnRouteTripFragment.this.getResources().getString(2131099771), null).setIcon(17301543).show());
      }
    });
    paramViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EnRouteTripFragment.this.onCallButtonClick();
      }
    });
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (EnRouteTripFragment.this.order != null)
        {
          paramAnonymousView = new Bundle();
          paramAnonymousView.putParcelable("order", EnRouteTripFragment.this.order);
          ((MainActivity)EnRouteTripFragment.this.getActivity()).goToOnTripOrderReview(paramAnonymousView);
        }
      }
    });
    this.mapTypeButton = ((ImageView)paramLayoutInflater.findViewById(2131493075));
    this.mapTypeLayout = ((RelativeLayout)paramLayoutInflater.findViewById(2131493074));
    this.mapTypeLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EnRouteTripFragment.this.changeMapType();
      }
    });
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    this.mapView.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    removeOrderListener();
  }
  
  public void onMapReady(GoogleMap paramGoogleMap)
  {
    this.googleMap = paramGoogleMap;
    if ((getActivity() != null) && (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0))
    {
      Log.e("onmapready", "leyts go");
      getOrderDetails(new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString)
        {
          EnRouteTripFragment.this.stopProgress(EnRouteTripFragment.this.progress);
          if (EnRouteTripFragment.this.order != null) {
            EnRouteTripFragment.this.addOrderListener();
          }
        }
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          EnRouteTripFragment.this.stopProgress(EnRouteTripFragment.this.progress);
          EnRouteTripFragment.this.addOrderListener();
        }
      });
    }
  }
  
  public void onPause()
  {
    this.mapView.onPause();
    AlertDialog localAlertDialog = ((MainActivity)getActivity()).getDriverAcceptedDialog();
    if ((localAlertDialog != null) && (localAlertDialog.isShowing())) {
      HelperMethods.dismissDialog(localAlertDialog);
    }
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.mapView.onResume();
    if (this.newTripFlag.booleanValue())
    {
      hideOpenedDialogs();
      ((MainActivity)getActivity()).newOrder();
    }
    for (;;)
    {
      if (this.dialogMessage != 0)
      {
        new CustomAlertDialog.Builder(getActivity()).setMessage(getResources().getString(this.dialogMessage)).setPositiveButton(17039370, null).show();
        this.dialogMessage = 0;
      }
      return;
      if (this.restartTripFlag.booleanValue())
      {
        hideOpenedDialogs();
        ((MainActivity)getActivity()).restartOrder(this.order);
      }
      else if (this.goToInvoice.booleanValue())
      {
        hideOpenedDialogs();
        goToInvoiceFragment();
      }
      else if (this.goToNextFragment.booleanValue())
      {
        hideOpenedDialogs();
        goToNextFragment();
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\EnRouteTripFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */