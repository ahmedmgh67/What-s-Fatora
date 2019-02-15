package com.tawseel.tawseel.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tawseel.tawseel.Constants.TripStatus;
import com.tawseel.tawseel.models.Order;
import java.util.List;

public class CurrentHistoryAdapter
  extends ArrayAdapter
{
  private LayoutInflater inflater;
  private int resourceID;
  
  public CurrentHistoryAdapter(Context paramContext, int paramInt, List<Order> paramList)
  {
    super(paramContext, paramInt, paramList);
    this.inflater = LayoutInflater.from(paramContext);
    this.resourceID = paramInt;
  }
  
  public Context getContext()
  {
    return super.getContext();
  }
  
  public int getCount()
  {
    return super.getCount();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    Order localOrder;
    if (paramView == null)
    {
      localObject = new ViewHolder();
      paramView = this.inflater.inflate(this.resourceID, paramViewGroup, false);
      ((ViewHolder)localObject).address_destination = ((TextView)paramView.findViewById(2131493033));
      ((ViewHolder)localObject).address_pickup = ((TextView)paramView.findViewById(2131493029));
      ((ViewHolder)localObject).fare = ((TextView)paramView.findViewById(2131493027));
      ((ViewHolder)localObject).date = ((TextView)paramView.findViewById(2131493035));
      ((ViewHolder)localObject).status = ((TextView)paramView.findViewById(2131493037));
      ((ViewHolder)localObject).ryal = ((TextView)paramView.findViewById(2131493026));
      ((ViewHolder)localObject).tripID = ((TextView)paramView.findViewById(2131493034));
      ((ViewHolder)localObject).statusCircle = ((ImageView)paramView.findViewById(2131493036));
      if (this.resourceID == 2130903078) {
        ((ViewHolder)localObject).time = ((TextView)paramView.findViewById(2131493031));
      }
      paramView.setTag(localObject);
      paramViewGroup = (ViewGroup)localObject;
      localOrder = (Order)getItem(paramInt);
      if ((!localOrder.status.equals(Constants.TripStatus.PICKING_UP.getValue())) && (!localOrder.status.equals(Constants.TripStatus.WAITING_AT_PICKUP.getValue()))) {
        break label455;
      }
      paramViewGroup.fare.setTextColor(ContextCompat.getColor(getContext(), 2131427341));
      paramViewGroup.ryal.setTextColor(ContextCompat.getColor(getContext(), 2131427341));
      paramViewGroup.time.setTextColor(ContextCompat.getColor(getContext(), 2131427341));
      paramViewGroup.status.setTextColor(ContextCompat.getColor(getContext(), 2131427341));
      paramViewGroup.statusCircle.setImageResource(2130837591);
      paramViewGroup.status.setText(getContext().getString(2131099855));
    }
    for (;;)
    {
      paramViewGroup.address_destination.setText(localOrder.dropOffAddress);
      paramViewGroup.address_pickup.setText(localOrder.pickupAddress);
      paramViewGroup.date.setText(localOrder.getItemDateString());
      paramViewGroup.fare.setText(String.valueOf(localOrder.maxCost.intValue()) + " - " + String.valueOf(Math.round(localOrder.estimateCost.doubleValue())));
      localObject = localOrder.orderID;
      if (localOrder.orderID.length() >= 5) {
        localObject = localOrder.orderID.substring(localOrder.orderID.length() - 5);
      }
      paramViewGroup.tripID.setText((CharSequence)localObject);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label455:
      if (localOrder.status.equals(Constants.TripStatus.DROP_OFF.getValue()))
      {
        paramViewGroup.fare.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.ryal.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.time.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.status.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.statusCircle.setImageResource(2130837639);
        paramViewGroup.status.setText(getContext().getString(2131099854));
      }
      else if (localOrder.status.equals(Constants.TripStatus.RETURNING_TO_SENDER.getValue()))
      {
        paramViewGroup.fare.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.ryal.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.time.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.status.setTextColor(ContextCompat.getColor(getContext(), 2131427387));
        paramViewGroup.statusCircle.setImageResource(2130837639);
        paramViewGroup.status.setText(getContext().getString(2131099772));
      }
    }
  }
  
  class ViewHolder
  {
    TextView address_destination;
    TextView address_pickup;
    TextView date;
    TextView fare;
    TextView ryal;
    TextView status;
    ImageView statusCircle;
    TextView time;
    TextView tripID;
    
    ViewHolder() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\adapters\CurrentHistoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */