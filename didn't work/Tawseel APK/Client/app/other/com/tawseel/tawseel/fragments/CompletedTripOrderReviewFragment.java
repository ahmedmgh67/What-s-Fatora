package com.tawseel.tawseel.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.LabelsRepository;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.Formatter;
import com.tawseel.tawseel.models.Order;
import com.tawseel.tawseel.models.Row;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CompletedTripOrderReviewFragment
  extends BaseFragment
{
  private ExpandableListView expListView;
  private Order order;
  
  private void prepareListData()
  {
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    localArrayList.add(getString(2131099827));
    localArrayList.add(getString(2131099794));
    localArrayList.add(getString(2131099715));
    if ((getArguments() != null) && (getArguments().getParcelable("order") != null)) {
      this.order = ((Order)getArguments().getParcelable("order"));
    }
    Object localObject2;
    Object localObject1;
    if (this.order != null)
    {
      localObject2 = new LabelsRepository(getActivity(), ((MainActivity)getActivity()).getTypeString(this.order.senderType));
      Object localObject3 = new ArrayList();
      localObject1 = new Row();
      ((Row)localObject1).title = getResources().getString(2131099695);
      double d = Math.round(this.order.distance.doubleValue() * 100.0D) / 100L;
      ((Row)localObject1).value = (String.valueOf(d) + " " + getResources().getString(2131099751));
      ((List)localObject3).add(localObject1);
      Row localRow = new Row();
      localRow.title = getResources().getString(2131099696);
      if ((this.order.startTime.doubleValue() == 0.0D) || (this.order.endTime.doubleValue() == 0.0D)) {
        break label1186;
      }
      localObject1 = new Date(this.order.startTime.longValue());
      Date localDate = new Date(this.order.endTime.longValue());
      Log.e("Tawseel", "date2" + this.order.endTime.longValue());
      long l = Formatter.getInstance(CustomApplication.getAppContext()).getDateDiff((Date)localObject1, localDate, TimeUnit.MINUTES);
      localObject1 = Formatter.getInstance(CustomApplication.getAppContext()).formatTime(Long.valueOf(l).intValue());
      localRow.value = ((String)localObject1);
      ((List)localObject3).add(localRow);
      localObject1 = new Row();
      ((Row)localObject1).title = getResources().getString(2131099851);
      ((Row)localObject1).value = Formatter.getInstance(getActivity()).formatCurrency(this.order.fare.doubleValue());
      ((List)localObject3).add(localObject1);
      localHashMap.put(localArrayList.get(0), localObject3);
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
      localHashMap.put(localArrayList.get(1), localObject1);
      localObject1 = new ArrayList();
      localObject2 = new Row();
      ((Row)localObject2).title = getResources().getString(2131099808);
      if (!this.order.recipientType.equals("1")) {
        break label1194;
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
      localHashMap.put(localArrayList.get(2), localObject1);
      localObject1 = new com.tawseel.tawseel.adapters.ExpandableListAdapter(getContext(), localArrayList, localHashMap);
      this.expListView.setAdapter((android.widget.ExpandableListAdapter)localObject1);
      this.expListView.expandGroup(0);
      return;
      label1186:
      localObject1 = "-";
      break;
      label1194:
      if (this.order.recipientType.equals("2")) {
        ((Row)localObject2).value = getResources().getString(2131099801);
      }
    }
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ((MainActivity)getActivity()).getBack();
    if (paramBundle != null) {
      paramBundle.setVisibility(0);
    }
    paramBundle = getMainActivity().getDeclineTextView();
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
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493013);
    paramBundle = (Button)paramLayoutInflater.findViewById(2131493113);
    paramViewGroup.setVisibility(8);
    paramBundle.setVisibility(8);
    this.expListView.setIndicatorBounds(getDisplayWidth() - GetPixelFromDips(60.0F), getDisplayWidth() - GetPixelFromDips(10.0F));
    this.expListView.setDivider(ResourcesCompat.getDrawable(getResources(), 2130837689, null));
    prepareListData();
    return paramLayoutInflater;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\CompletedTripOrderReviewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */