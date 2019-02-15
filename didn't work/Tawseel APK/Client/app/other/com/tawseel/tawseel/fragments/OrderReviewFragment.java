package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.directions.route.Route;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.LabelsRepository;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.activities.BaseActivity;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.adapters.ExpandableListAdapter;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.Formatter;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Order;
import com.tawseel.tawseel.models.Promotion;
import com.tawseel.tawseel.models.Row;
import com.tawseel.tawseel.network.APIConnector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import sa.tawseel.tawseelcommon.maps.DirectionsHelper;
import sa.tawseel.tawseelcommon.maps.DirectionsHelper.GettingRouteListener;

public class OrderReviewFragment
  extends BaseFragment
{
  private static final String TAG = OrderReviewFragment.class.getSimpleName();
  private ExpandableListView expListView;
  private ExpandableListAdapter listAdapter;
  private HashMap<String, List<Row>> listDataChild;
  private List<String> listDataHeader;
  private ProgressDialog mProgressDialog;
  private Row promoCodeRow;
  private Order savedOrder;
  
  private void prepareListData()
  {
    startProgress();
    this.listDataHeader = new ArrayList();
    this.listDataChild = new HashMap();
    final Order localOrder = ((MainActivity)getActivity()).getOrder();
    final LabelsRepository localLabelsRepository = new LabelsRepository(getActivity(), ((MainActivity)getActivity()).getTypeString(localOrder.senderType));
    this.listDataHeader.add(getString(2131099827));
    this.listDataHeader.add(getResources().getString(2131099794));
    this.listDataHeader.add(getResources().getString(2131099715));
    Log.d(TAG, "api key >>> >" + getString(2131099886));
    DirectionsHelper.getInstance(getString(2131099886)).getRoute(localOrder.pickupLat.doubleValue(), localOrder.pickupLng.doubleValue(), localOrder.dropOffLat.doubleValue(), localOrder.dropOffLng.doubleValue(), new DirectionsHelper.GettingRouteListener()
    {
      public void onFailure()
      {
        if (OrderReviewFragment.this.getActivity() != null)
        {
          Toast.makeText(OrderReviewFragment.this.getActivity(), OrderReviewFragment.this.getString(2131099737), 1).show();
          HelperMethods.dismissDialog(OrderReviewFragment.this.mProgressDialog);
        }
      }
      
      public void onRouteObtained(Route paramAnonymousRoute)
      {
        for (;;)
        {
          try
          {
            d1 = paramAnonymousRoute.getDurationValue() / 60.0D;
            d2 = paramAnonymousRoute.getDistanceValue() / 1000.0D;
            double d3 = Math.max(d2 - Settings.freeKMs, 0.0D);
            localObject = Double.valueOf(Settings.minimumFareInSAR);
            paramAnonymousRoute = (Route)localObject;
            if (d3 >= Settings.freeKMs) {
              paramAnonymousRoute = Double.valueOf(((Double)localObject).doubleValue() + (d3 - Settings.freeKMs) * Settings.farePerKMInSAR);
            }
            paramAnonymousRoute = Double.valueOf(Math.round(Double.valueOf(paramAnonymousRoute.doubleValue() + Settings.farePerMinInSAR * d1).doubleValue() * 100.0D) / 100.0D);
            localObject = Double.valueOf(Math.round(paramAnonymousRoute.doubleValue() * (1.0D + Settings.maximumPercentageOfEstimatedFare / 100.0D) * 100.0D) / 100.0D);
            if (paramAnonymousRoute != null) {
              continue;
            }
            onFailure();
          }
          catch (Exception paramAnonymousRoute)
          {
            double d1;
            double d2;
            Object localObject;
            ArrayList localArrayList;
            Row localRow;
            onFailure();
            paramAnonymousRoute.printStackTrace();
            continue;
            paramAnonymousRoute = Math.round(d1) + " " + OrderReviewFragment.this.getString(2131099763);
            continue;
            if (!localOrder.recipientType.equals("2")) {
              continue;
            }
            ((Row)localObject).value = OrderReviewFragment.this.getResources().getString(2131099801);
            continue;
          }
          OrderReviewFragment.this.setOrder();
          return;
          if (OrderReviewFragment.this.getActivity() != null)
          {
            localOrder.estimateCost = paramAnonymousRoute;
            localOrder.estimateTime = Double.valueOf(d1);
            localOrder.estimateDistance = Double.valueOf(d2);
            localOrder.maxCost = ((Double)localObject);
            ((MainActivity)OrderReviewFragment.this.getActivity()).setOrder(localOrder);
            OrderReviewFragment.access$002(OrderReviewFragment.this, localOrder);
            localObject = Math.round(((Double)localObject).doubleValue()) + "-" + Math.round(paramAnonymousRoute.doubleValue()) + " " + OrderReviewFragment.this.getResources().getString(2131099817);
            localArrayList = new ArrayList();
            paramAnonymousRoute = new Row();
            paramAnonymousRoute.title = OrderReviewFragment.this.getResources().getString(2131099739);
            paramAnonymousRoute.value = Formatter.getInstance(OrderReviewFragment.this.getActivity()).formatDistance(d2);
            localArrayList.add(paramAnonymousRoute);
            localRow = new Row();
            localRow.title = OrderReviewFragment.this.getResources().getString(2131099741);
            if ((d1 < 3.0D) || (d1 > 10.0D)) {
              continue;
            }
            paramAnonymousRoute = Math.round(d1) + " " + OrderReviewFragment.this.getString(2131099764);
            localRow.value = paramAnonymousRoute;
            localArrayList.add(localRow);
            paramAnonymousRoute = new Row();
            paramAnonymousRoute.title = OrderReviewFragment.this.getResources().getString(2131099740);
            paramAnonymousRoute.value = ((String)localObject);
            OrderReviewFragment.access$202(OrderReviewFragment.this, paramAnonymousRoute);
            localArrayList.add(paramAnonymousRoute);
            OrderReviewFragment.this.listDataChild.put(OrderReviewFragment.this.listDataHeader.get(0), localArrayList);
            paramAnonymousRoute = new ArrayList();
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099837);
            ((Row)localObject).value = ((MainActivity)OrderReviewFragment.this.getActivity()).getTypeStringForOrderReview(localOrder.senderType);
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = localLabelsRepository.getSenderNameLabel();
            ((Row)localObject).value = localOrder.senderName;
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099793);
            ((Row)localObject).value = localOrder.senderPhone;
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099699);
            ((Row)localObject).value = localOrder.pickupAddress;
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099779);
            ((Row)localObject).value = localOrder.pickupNotes;
            paramAnonymousRoute.add(localObject);
            if ((localOrder.hasImage != null) && (localOrder.hasImage.booleanValue()))
            {
              localObject = new Row();
              ((Row)localObject).title = localLabelsRepository.getPackageImageLabel();
              Log.d("Tawseel", "OrderReview " + localOrder.packageImageUrl);
              ((Row)localObject).value = localOrder.orderID;
              paramAnonymousRoute.add(localObject);
            }
            localObject = new Row();
            ((Row)localObject).title = localLabelsRepository.getPackageDetailsLabel();
            ((Row)localObject).value = localOrder.description;
            paramAnonymousRoute.add(localObject);
            OrderReviewFragment.this.listDataChild.put(OrderReviewFragment.this.listDataHeader.get(1), paramAnonymousRoute);
            paramAnonymousRoute = new ArrayList();
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099808);
            if (!localOrder.recipientType.equals("1")) {
              continue;
            }
            ((Row)localObject).value = OrderReviewFragment.this.getResources().getString(2131099800);
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099807);
            ((Row)localObject).value = localOrder.recipientName;
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099793);
            ((Row)localObject).value = localOrder.recipientPhone;
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099699);
            ((Row)localObject).value = localOrder.dropOffAddress;
            paramAnonymousRoute.add(localObject);
            localObject = new Row();
            ((Row)localObject).title = OrderReviewFragment.this.getResources().getString(2131099780);
            ((Row)localObject).value = localOrder.dropOffNotes;
            paramAnonymousRoute.add(localObject);
            OrderReviewFragment.this.listDataChild.put(OrderReviewFragment.this.listDataHeader.get(2), paramAnonymousRoute);
            OrderReviewFragment.access$302(OrderReviewFragment.this, new ExpandableListAdapter(OrderReviewFragment.this.getActivity(), OrderReviewFragment.this.listDataHeader, OrderReviewFragment.this.listDataChild));
            OrderReviewFragment.this.expListView.setAdapter(OrderReviewFragment.this.listAdapter);
            OrderReviewFragment.this.expListView.expandGroup(0);
          }
        }
      }
    });
  }
  
  private void sendPromotionRequest(final String paramString)
  {
    startProgress();
    if ((paramString != null) && (paramString.isEmpty()))
    {
      Toast.makeText(getActivity(), getString(2131099776), 0).show();
      HelperMethods.dismissDialog(this.mProgressDialog);
      return;
    }
    final Order localOrder = ((MainActivity)getActivity()).getOrder();
    if ((localOrder.orderID != null) && (!localOrder.orderID.isEmpty()))
    {
      APIConnector.getInstance(getActivity()).sendPromotionRequest(paramString, localOrder.orderID, new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString)
        {
          Log.e(OrderReviewFragment.TAG, paramAnonymousString + " < promo err");
          HelperMethods.dismissDialog(OrderReviewFragment.this.mProgressDialog);
        }
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          localOrder.promotion = new Promotion((JSONObject)paramAnonymousObject);
          if (localOrder.promotion.getDiscountPercentage() > 0.0D) {}
          for (OrderReviewFragment.this.promoCodeRow.newValue = (Math.round(localOrder.maxCost.doubleValue() * (1.0D - localOrder.promotion.getDiscountPercentage())) + "-" + Math.round(localOrder.estimateCost.doubleValue() * (1.0D - localOrder.promotion.getDiscountPercentage())) + " " + OrderReviewFragment.this.getString(2131099817));; OrderReviewFragment.this.promoCodeRow.newValue = null)
          {
            OrderReviewFragment.this.listAdapter.notifyDataSetChanged();
            if ((paramString != null) && (localOrder.promotion.getDiscountPercentage() == 0.0D)) {
              Toast.makeText(OrderReviewFragment.this.getActivity(), OrderReviewFragment.this.getString(2131099776), 0).show();
            }
            HelperMethods.dismissDialog(OrderReviewFragment.this.mProgressDialog);
            return;
          }
        }
      });
      return;
    }
    HelperMethods.dismissDialog(this.mProgressDialog);
  }
  
  private void setOrder()
  {
    if (getMainActivity() == null) {
      return;
    }
    final Order localOrder = getMainActivity().getOrder();
    Location localLocation = getMainActivity().getLocation();
    if (localLocation != null) {
      localOrder.clientLat = Double.valueOf(localLocation.getLatitude());
    }
    for (localOrder.clientLng = Double.valueOf(localLocation.getLongitude()); localOrder.orderID == null; localOrder.clientLng = Double.valueOf(0.0D))
    {
      FirebaseHelper.getInstance().setOrder(localOrder, new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString)
        {
          Log.e("Tawseel", "onError:setOrder " + paramAnonymousObject);
          if (OrderReviewFragment.this.getActivity() != null)
          {
            HelperMethods.dismissDialog(OrderReviewFragment.this.mProgressDialog);
            Toast.makeText(OrderReviewFragment.this.getActivity(), OrderReviewFragment.this.getString(2131099785), 1).show();
            OrderReviewFragment.this.getActivity().onBackPressed();
          }
        }
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          Log.d("Tawseel", "onSuccess:setOrder " + paramAnonymousObject);
          localOrder.orderID = ((String)paramAnonymousObject);
          if (OrderReviewFragment.this.getMainActivity() != null)
          {
            OrderReviewFragment.this.getMainActivity().setOrder(localOrder);
            OrderReviewFragment.this.listAdapter.notifyDataSetChanged();
            OrderReviewFragment.this.sendPromotionRequest(null);
          }
        }
      }, getActivity());
      return;
      localOrder.clientLat = Double.valueOf(0.0D);
    }
    this.listAdapter.notifyDataSetChanged();
    sendPromotionRequest(null);
  }
  
  private void startProgress()
  {
    if (this.mProgressDialog == null)
    {
      this.mProgressDialog = new ProgressDialog(getActivity());
      this.mProgressDialog.setMessage(getString(2131099752));
      this.mProgressDialog.setCancelable(false);
      this.mProgressDialog.show();
    }
  }
  
  public int GetPixelFromDips(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ((MainActivity)getActivity()).getBack();
    if (paramBundle != null) {
      paramBundle.setVisibility(0);
    }
    paramBundle = ((MainActivity)getActivity()).getDeclineTextView();
    if ((paramBundle != null) && (paramBundle.getVisibility() == 0)) {
      paramBundle.setVisibility(8);
    }
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable final ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    setActionBarTitle(2131099826);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903102, paramViewGroup, false);
    this.expListView = ((ExpandableListView)paramLayoutInflater.findViewById(2131493114));
    this.expListView.setDivider(ResourcesCompat.getDrawable(getResources(), 2130837689, null));
    paramViewGroup = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(paramViewGroup);
    int i = paramViewGroup.widthPixels;
    this.expListView.setIndicatorBounds(i - GetPixelFromDips(60.0F), i - GetPixelFromDips(10.0F));
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493113);
    paramBundle = (ImageView)paramLayoutInflater.findViewById(2131493107);
    paramViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        OrderReviewFragment.this.getMainActivity().cancelNewTrip();
      }
    });
    paramBundle.setImageDrawable(ResourcesCompat.getDrawable(getResources(), 2130837668, null));
    prepareListData();
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493013);
    paramViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramViewGroup.setEnabled(false);
        if ((OrderReviewFragment.this.savedOrder != null) && (OrderReviewFragment.this.savedOrder.estimateTime != null) && (OrderReviewFragment.this.savedOrder.estimateCost != null)) {}
        try
        {
          ((BaseActivity)OrderReviewFragment.this.getActivity()).replaceFragment(null);
        }
        catch (Exception paramAnonymousView)
        {
          for (;;)
          {
            try
            {
              paramViewGroup.setEnabled(true);
              return;
            }
            catch (Exception paramAnonymousView)
            {
              paramAnonymousView.printStackTrace();
            }
            paramAnonymousView = paramAnonymousView;
            paramAnonymousView.printStackTrace();
          }
        }
      }
    });
    paramViewGroup = (EditText)paramLayoutInflater.findViewById(2131493104);
    paramLayoutInflater.findViewById(2131493105).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((MainActivity)OrderReviewFragment.this.getActivity()).hideKeyboardIfShown();
        paramAnonymousView = paramViewGroup.getText().toString();
        OrderReviewFragment.this.sendPromotionRequest(paramAnonymousView);
      }
    });
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    ((MainActivity)getActivity()).setPopupWindow(null);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\OrderReviewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */