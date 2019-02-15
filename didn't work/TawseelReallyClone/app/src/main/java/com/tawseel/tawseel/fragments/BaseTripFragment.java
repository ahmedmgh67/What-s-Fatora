package com.tawseel.tawseel.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.directions.route.Route;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.models.Order;
import java.util.Timer;
import sa.tawseel.tawseelcommon.maps.DirectionsHelper;
import sa.tawseel.tawseelcommon.maps.DirectionsHelper.GettingRouteListener;
import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public abstract class BaseTripFragment
  extends BaseFragment
{
  protected static final int INITIAL_ZOOM_LEVEL = 15;
  protected Double driverLat;
  protected Double driverLng;
  protected Marker driverMarker;
  protected DatabaseReference firebaseTripStatusRef;
  protected GoogleMap googleMap;
  protected ImageView mapTypeButton;
  protected RelativeLayout mapTypeLayout;
  protected TextView minUnit;
  protected Order order;
  protected TextView timeDiff;
  private ChildEventListener tripStatusListener;
  protected Timer updateRemainingTimeTimer;
  
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
  
  protected void addOrderListener()
  {
    if (this.order != null)
    {
      this.firebaseTripStatusRef = FirebaseHelper.getInstance().getReference().child("v2").child("orders").child(String.valueOf(this.order.orderID));
      this.tripStatusListener = new ChildEventListener()
      {
        public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
        
        public void onChildAdded(DataSnapshot paramAnonymousDataSnapshot, String paramAnonymousString)
        {
          BaseTripFragment.this.analyseChild(paramAnonymousDataSnapshot);
        }
        
        public void onChildChanged(DataSnapshot paramAnonymousDataSnapshot, String paramAnonymousString)
        {
          BaseTripFragment.this.analyseChild(paramAnonymousDataSnapshot);
        }
        
        public void onChildMoved(DataSnapshot paramAnonymousDataSnapshot, String paramAnonymousString) {}
        
        public void onChildRemoved(DataSnapshot paramAnonymousDataSnapshot)
        {
          if ((paramAnonymousDataSnapshot.getKey().equals("driverCurrentLocation")) && (BaseTripFragment.this.driverMarker != null))
          {
            BaseTripFragment.this.driverLat = null;
            BaseTripFragment.this.driverLng = null;
            BaseTripFragment.this.driverMarker.setVisible(false);
            BaseTripFragment.this.driverMarker.remove();
          }
        }
      };
      this.firebaseTripStatusRef.addChildEventListener(this.tripStatusListener);
    }
  }
  
  protected abstract void analyseChild(DataSnapshot paramDataSnapshot);
  
  protected void changeMapType()
  {
    if (this.googleMap != null)
    {
      if (this.googleMap.getMapType() != 2) {
        break label36;
      }
      this.googleMap.setMapType(1);
      this.mapTypeButton.setImageResource(2130837681);
    }
    label36:
    while (this.googleMap.getMapType() != 1) {
      return;
    }
    this.googleMap.setMapType(2);
    this.mapTypeButton.setImageResource(2130837687);
  }
  
  protected void drawCurrentDriverLocation()
  {
    if (this.driverMarker == null)
    {
      this.driverMarker = this.googleMap.addMarker(new MarkerOptions().position(new LatLng(this.driverLat.doubleValue(), this.driverLng.doubleValue())).icon(BitmapDescriptorFactory.fromResource(2130837600)));
      this.driverMarker.setAnchor(0.5F, 0.5F);
      return;
    }
    animateMarkerTo(this.driverMarker, this.driverLat.doubleValue(), this.driverLng.doubleValue(), 16);
  }
  
  protected void drawPath(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    if ((this.googleMap != null) && (paramDouble1 != 0.0D) && (paramDouble2 != 0.0D) && (paramDouble3 != 0.0D) && (paramDouble4 != 0.0D)) {
      DirectionsHelper.getInstance(getString(2131099886)).getRoute(paramDouble1, paramDouble2, paramDouble3, paramDouble4, new DirectionsHelper.GettingRouteListener()
      {
        public void onFailure() {}
        
        public void onRouteObtained(Route paramAnonymousRoute)
        {
          BaseTripFragment.this.googleMap.addPolyline(new PolylineOptions().addAll(paramAnonymousRoute.getPoints()).width(5.0F).color(Color.parseColor("#CC1085C4")).geodesic(true));
        }
      });
    }
  }
  
  protected void onCallButtonClick()
  {
    if ((this.order != null) && (this.order.driverID != null))
    {
      String str = PhoneUtils.validatePhoneNo(this.order.driverID + "");
      if (!str.isEmpty())
      {
        str = "+" + str;
        str = "tel:" + str.trim();
        Intent localIntent = new Intent("android.intent.action.DIAL");
        localIntent.setData(Uri.parse(str));
        startActivity(localIntent);
      }
    }
  }
  
  protected void removeOrderListener()
  {
    if (this.firebaseTripStatusRef != null) {
      this.firebaseTripStatusRef.removeEventListener(this.tripStatusListener);
    }
  }
  
  protected void setEstimateTime()
  {
    if ((this.order.remainingTime != null) && (getActivity() != null))
    {
      int i = (int)(this.order.remainingTime.longValue() / 60L);
      this.timeDiff.setText(String.valueOf(i));
      if ((i >= 3) && (i <= 10)) {
        this.minUnit.setText(getString(2131099764));
      }
    }
    else
    {
      return;
    }
    this.minUnit.setText(getString(2131099763));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\BaseTripFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */