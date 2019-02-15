package com.tawseel.tawseel.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tawseel.tawseel.Constants.TripStatus;
import com.tawseel.tawseel.LabelsRepository;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.adapters.ExpandableListAdapter;
import com.tawseel.tawseel.models.Order;
import com.tawseel.tawseel.models.Row;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnTripOrderReviewFragment
  extends BaseFragment
{
  ExpandableListView expListView;
  ExpandableListAdapter listAdapter;
  HashMap<String, List<Row>> listDataChild;
  List<String> listDataHeader;
  private Order order;
  
  private String getStatusString(String paramString)
  {
    String str = "";
    if (paramString.equals(Constants.TripStatus.PICKING_UP.getValue())) {
      str = getString(2131099855);
    }
    do
    {
      return str;
      if (paramString.equals(Constants.TripStatus.WAITING_AT_PICKUP.getValue())) {
        return getString(2131099855);
      }
      if (paramString.equals(Constants.TripStatus.DROP_OFF.getValue())) {
        return getString(2131099854);
      }
    } while (!paramString.equals(Constants.TripStatus.RETURNING_TO_SENDER.getValue()));
    return getString(2131099772);
  }
  
  public int GetPixelFromDips(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.order = ((Order)getArguments().getParcelable("order"));
    }
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
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    setActionBarTitle(2131099826);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903102, paramViewGroup, false);
    this.expListView = ((ExpandableListView)paramLayoutInflater.findViewById(2131493114));
    ((LinearLayout)paramLayoutInflater.findViewById(2131493070)).setVisibility(8);
    ((Button)paramLayoutInflater.findViewById(2131493013)).setVisibility(8);
    ((Button)paramLayoutInflater.findViewById(2131493113)).setVisibility(8);
    paramViewGroup = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(paramViewGroup);
    int i = paramViewGroup.widthPixels;
    this.expListView.setIndicatorBounds(i - GetPixelFromDips(60.0F), i - GetPixelFromDips(10.0F));
    this.expListView.setDivider(ResourcesCompat.getDrawable(getResources(), 2130837689, null));
    prepareListData();
    paramLayoutInflater.findViewById(2131493103).setVisibility(8);
    return paramLayoutInflater;
  }
  
  public void prepareListData()
  {
    this.listDataHeader = new ArrayList();
    this.listDataChild = new HashMap();
    this.listDataHeader.add(getString(2131099827));
    this.listDataHeader.add(getString(2131099794));
    this.listDataHeader.add(getString(2131099715));
    Object localObject2;
    Object localObject1;
    if (this.order != null)
    {
      localObject2 = new LabelsRepository(getActivity(), ((MainActivity)getActivity()).getTypeString(this.order.senderType));
      Object localObject3 = new ArrayList();
      localObject1 = new Row();
      ((Row)localObject1).title = getResources().getString(2131099786);
      ((Row)localObject1).value = getStatusString(this.order.status);
      ((List)localObject3).add(localObject1);
      localObject1 = new Row();
      ((Row)localObject1).title = getResources().getString(2131099739);
      double d = Math.round(this.order.estimateDistance.doubleValue() * 100.0D) / 100L;
      ((Row)localObject1).value = (String.valueOf(d) + " " + getResources().getString(2131099751));
      ((List)localObject3).add(localObject1);
      Row localRow = new Row();
      localRow.title = getResources().getString(2131099741);
      if ((this.order.estimateTime.doubleValue() < 3.0D) || (this.order.estimateTime.doubleValue() > 10.0D)) {
        break label1195;
      }
      localObject1 = this.order.estimateTime + " " + getString(2131099764);
      localRow.value = ((String)localObject1);
      ((List)localObject3).add(localRow);
      localObject1 = new Row();
      ((Row)localObject1).title = getResources().getString(2131099740);
      ((Row)localObject1).value = (String.valueOf(this.order.maxCost.intValue()) + " - " + String.valueOf(this.order.estimateCost.intValue()) + " " + getResources().getString(2131099817));
      ((List)localObject3).add(localObject1);
      this.listDataChild.put(this.listDataHeader.get(0), localObject3);
      localObject1 = new ArrayList();
      localObject3 = new Row();
      ((Row)localObject3).title = getResources().getString(2131099837);
      ((Row)localObject3).value = ((MainActivity)getActivity()).getTypeStringForOrderReview(this.order.senderType);
      ((List)localObject1).add(localObject3);
      localObject3 = new Row();
      ((Row)localObject3).title = ((LabelsRepository)localObject2).getSenderNameLabel();
      ((Row)localObject3).value = this.order.senderName;
      ((List)localObject1).add(localObject3);
      localObject3 = new Row();
      ((Row)localObject3).title = getResources().getString(2131099793);
      ((Row)localObject3).value = this.order.senderPhone;
      ((List)localObject1).add(localObject3);
      localObject3 = new Row();
      ((Row)localObject3).title = getResources().getString(2131099699);
      ((Row)localObject3).value = this.order.pickupAddress;
      ((List)localObject1).add(localObject3);
      localObject3 = new Row();
      ((Row)localObject3).title = getResources().getString(2131099779);
      ((Row)localObject3).value = this.order.pickupNotes;
      ((List)localObject1).add(localObject3);
      if ((this.order.hasImage != null) && (this.order.hasImage.booleanValue()))
      {
        localObject3 = new Row();
        ((Row)localObject3).title = ((LabelsRepository)localObject2).getPackageImageLabel();
        Log.d("Tawseel", "OrderReview " + this.order.packageImageUrl);
        ((Row)localObject3).value = this.order.orderID;
        ((List)localObject1).add(localObject3);
      }
      localObject3 = new Row();
      ((Row)localObject3).title = ((LabelsRepository)localObject2).getPackageDetailsLabel();
      ((Row)localObject3).value = this.order.description;
      ((List)localObject1).add(localObject3);
      this.listDataChild.put(this.listDataHeader.get(1), localObject1);
      localObject1 = new ArrayList();
      localObject2 = new Row();
      ((Row)localObject2).title = getResources().getString(2131099808);
      if (!this.order.recipientType.equals("1")) {
        break label1235;
      }
      ((Row)localObject2).value = getResources().getString(2131099800);
    }
    for (;;)
    {
      ((List)localObject1).add(localObject2);
      localObject2 = new Row();
      ((Row)localObject2).title = getResources().getString(2131099807);
      ((Row)localObject2).value = this.order.recipientName;
      ((List)localObject1).add(localObject2);
      localObject2 = new Row();
      ((Row)localObject2).title = getResources().getString(2131099793);
      ((Row)localObject2).value = this.order.recipientPhone;
      ((List)localObject1).add(localObject2);
      localObject2 = new Row();
      ((Row)localObject2).title = getResources().getString(2131099699);
      ((Row)localObject2).value = this.order.dropOffAddress;
      ((List)localObject1).add(localObject2);
      localObject2 = new Row();
      ((Row)localObject2).title = getResources().getString(2131099780);
      ((Row)localObject2).value = this.order.dropOffNotes;
      ((List)localObject1).add(localObject2);
      this.listDataChild.put(this.listDataHeader.get(2), localObject1);
      this.listAdapter = new ExpandableListAdapter(getContext(), this.listDataHeader, this.listDataChild);
      this.expListView.setAdapter(this.listAdapter);
      this.expListView.expandGroup(0);
      return;
      label1195:
      localObject1 = this.order.estimateTime + " " + getString(2131099763);
      break;
      label1235:
      if (this.order.recipientType.equals("2")) {
        ((Row)localObject2).value = getResources().getString(2131099801);
      } else {
        ((Row)localObject2).value = ((MainActivity)getActivity()).getTypeStringForOrderReview(this.order.recipientType);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\OnTripOrderReviewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */