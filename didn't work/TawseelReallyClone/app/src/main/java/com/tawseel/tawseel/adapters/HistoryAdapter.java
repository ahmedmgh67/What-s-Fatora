package com.tawseel.tawseel.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tawseel.tawseel.helpers.Formatter;
import com.tawseel.tawseel.models.Order;
import java.util.List;

public class HistoryAdapter
  extends ArrayAdapter
{
  private LayoutInflater inflater;
  private int resourceID;
  
  public HistoryAdapter(Context paramContext, int paramInt, List<Order> paramList)
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
    if (paramView == null)
    {
      localObject = new ViewHolder();
      paramView = this.inflater.inflate(this.resourceID, paramViewGroup, false);
      ((ViewHolder)localObject).address_destination = ((TextView)paramView.findViewById(2131493033));
      ((ViewHolder)localObject).address_pickup = ((TextView)paramView.findViewById(2131493029));
      ((ViewHolder)localObject).fare = ((TextView)paramView.findViewById(2131493027));
      ((ViewHolder)localObject).date = ((TextView)paramView.findViewById(2131493035));
      ((ViewHolder)localObject).status = ((TextView)paramView.findViewById(2131493037));
      ((ViewHolder)localObject).tripID = ((TextView)paramView.findViewById(2131493034));
      paramView.setTag(localObject);
    }
    for (paramViewGroup = (ViewGroup)localObject;; paramViewGroup = (ViewHolder)paramView.getTag())
    {
      Order localOrder = (Order)getItem(paramInt);
      Log.e("order " + paramInt, localOrder.toString());
      paramViewGroup.address_destination.setText(localOrder.dropOffAddress);
      paramViewGroup.address_pickup.setText(localOrder.pickupAddress);
      paramViewGroup.fare.setText(String.valueOf(Formatter.roundToTwoDecimal(localOrder.fare.doubleValue())));
      paramViewGroup.date.setText(localOrder.getItemDateString());
      paramViewGroup.status.setText(localOrder.status);
      localObject = localOrder.orderID;
      if (localOrder.orderID.length() >= 5) {
        localObject = localOrder.orderID.substring(localOrder.orderID.length() - 5);
      }
      paramViewGroup.tripID.setText((CharSequence)localObject);
      return paramView;
    }
  }
  
  class ViewHolder
  {
    TextView address_destination;
    TextView address_pickup;
    TextView date;
    TextView fare;
    TextView status;
    TextView tripID;
    
    ViewHolder() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\adapters\HistoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */