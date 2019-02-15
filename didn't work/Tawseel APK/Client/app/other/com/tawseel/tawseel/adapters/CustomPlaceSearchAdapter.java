package com.tawseel.tawseel.adapters;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.tawseel.tawseel.fragments.CustomPlaceSearchFragment.OnSearchPlaceSelectedListener;
import java.util.List;

public class CustomPlaceSearchAdapter
  extends RecyclerView.Adapter<CustomSearchPlaceViewHolder>
  implements View.OnClickListener
{
  private final List<AutocompletePrediction> autocompletePredictionList;
  private final CustomPlaceSearchFragment.OnSearchPlaceSelectedListener onSearchPlaceSelectedListener;
  
  public CustomPlaceSearchAdapter(List<AutocompletePrediction> paramList, CustomPlaceSearchFragment.OnSearchPlaceSelectedListener paramOnSearchPlaceSelectedListener)
  {
    this.autocompletePredictionList = paramList;
    this.onSearchPlaceSelectedListener = paramOnSearchPlaceSelectedListener;
  }
  
  public int getItemCount()
  {
    return this.autocompletePredictionList.size();
  }
  
  public void onBindViewHolder(CustomSearchPlaceViewHolder paramCustomSearchPlaceViewHolder, int paramInt)
  {
    paramCustomSearchPlaceViewHolder.mainPlaceTextView.setText(((AutocompletePrediction)this.autocompletePredictionList.get(paramInt)).getPrimaryText(null));
    paramCustomSearchPlaceViewHolder.secondaryPlaceTextView.setText(((AutocompletePrediction)this.autocompletePredictionList.get(paramInt)).getSecondaryText(null));
    paramCustomSearchPlaceViewHolder.containerLayout.setTag(this.autocompletePredictionList.get(paramInt));
    paramCustomSearchPlaceViewHolder.containerLayout.setOnClickListener(this);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131493021)
    {
      paramView = (AutocompletePrediction)paramView.getTag();
      this.onSearchPlaceSelectedListener.onSearchPlaceSelected(paramView.getPlaceId());
    }
  }
  
  public CustomSearchPlaceViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    return new CustomSearchPlaceViewHolder(LinearLayout.inflate(paramViewGroup.getContext(), 2130903076, null));
  }
  
  class CustomSearchPlaceViewHolder
    extends RecyclerView.ViewHolder
  {
    View containerLayout;
    TextView mainPlaceTextView;
    TextView secondaryPlaceTextView;
    
    public CustomSearchPlaceViewHolder(View paramView)
    {
      super();
      this.mainPlaceTextView = ((TextView)paramView.findViewById(2131493022));
      this.secondaryPlaceTextView = ((TextView)paramView.findViewById(2131493023));
      this.containerLayout = paramView.findViewById(2131493021);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\adapters\CustomPlaceSearchAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */