package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ValueEventListener;
import com.tawseel.tawseel.Constants.TripStatus;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.adapters.CurrentHistoryAdapter;
import com.tawseel.tawseel.adapters.HistoryAdapter;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Order;
import java.util.Collections;
import java.util.List;

public class HistoryFragment
  extends BaseFragment
{
  CurrentHistoryAdapter currentHistoryAdapter;
  private ValueEventListener currentTripsValueEventListener;
  private ValueEventListener endedTripsListener;
  HistoryAdapter historyAdapter;
  ProgressDialog progressDialog;
  ListView stickyList;
  
  private void getCurrentHistoryAndBindItsAdapter()
  {
    showProgressDialog();
    this.currentTripsValueEventListener = FirebaseHelper.getInstance().getCurrentTrips(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
      }
      
      public void onSuccess(final Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
        if (paramAnonymousString.equals("success")) {
          try
          {
            paramAnonymousObject = (List)paramAnonymousObject;
            if ((paramAnonymousObject == null) || (((List)paramAnonymousObject).size() <= 0))
            {
              HistoryFragment.this.getMainActivity().showAlertWithMessage(HistoryFragment.this.getString(2131099773));
              return;
            }
            paramAnonymousObject = HistoryFragment.this.sortOrdersDescendingOrderByDates((List)paramAnonymousObject);
            HistoryFragment.this.currentHistoryAdapter = new CurrentHistoryAdapter(HistoryFragment.this.getActivity(), 2130903078, (List)paramAnonymousObject);
            HistoryFragment.this.stickyList.setAdapter(HistoryFragment.this.currentHistoryAdapter);
            HistoryFragment.this.stickyList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
              public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
              {
                if ((paramAnonymousObject != null) && (paramAnonymousObject.get(paramAnonymous2Int) != null))
                {
                  paramAnonymous2AdapterView = new Bundle();
                  paramAnonymous2AdapterView.putParcelable("order", (Parcelable)paramAnonymousObject.get(paramAnonymous2Int));
                  if ((!((Order)paramAnonymousObject.get(paramAnonymous2Int)).status.equals(Constants.TripStatus.PICKING_UP.getValue())) && (!((Order)paramAnonymousObject.get(paramAnonymous2Int)).status.equals(Constants.TripStatus.WAITING_AT_PICKUP.getValue()))) {
                    break label118;
                  }
                  HistoryFragment.this.getMainActivity().goToOngoingFromHistory(paramAnonymous2AdapterView);
                }
                label118:
                do
                {
                  return;
                  if (((Order)paramAnonymousObject.get(paramAnonymous2Int)).status.equals(Constants.TripStatus.DROP_OFF.getValue()))
                  {
                    HistoryFragment.this.getMainActivity().goToInProgressFromHistory(paramAnonymous2AdapterView);
                    return;
                  }
                } while (!((Order)paramAnonymousObject.get(paramAnonymous2Int)).status.equals(Constants.TripStatus.RETURNING_TO_SENDER.getValue()));
                HistoryFragment.this.getMainActivity().goToInProgressFromHistory(paramAnonymous2AdapterView);
              }
            });
            return;
          }
          catch (Exception paramAnonymousObject)
          {
            HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
            ((Exception)paramAnonymousObject).printStackTrace();
            return;
          }
        }
        Toast.makeText(HistoryFragment.this.getActivity(), HistoryFragment.this.getString(2131099709), 1).show();
      }
    });
  }
  
  private void getPreviousHistoryAndBindItsAdapter()
  {
    showProgressDialog();
    this.endedTripsListener = FirebaseHelper.getInstance().getEndedTrips(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
      }
      
      public void onSuccess(final Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
        if (paramAnonymousString.equals("success")) {
          try
          {
            paramAnonymousObject = (List)paramAnonymousObject;
            if ((paramAnonymousObject == null) || (((List)paramAnonymousObject).size() <= 0))
            {
              HistoryFragment.this.getMainActivity().showAlertWithMessage(HistoryFragment.this.getString(2131099775));
              return;
            }
            paramAnonymousObject = HistoryFragment.this.sortOrdersDescendingOrderByDates((List)paramAnonymousObject);
            HistoryFragment.this.historyAdapter = new HistoryAdapter(HistoryFragment.this.getActivity(), 2130903136, (List)paramAnonymousObject);
            HistoryFragment.this.stickyList.setAdapter(HistoryFragment.this.historyAdapter);
            HistoryFragment.this.stickyList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
              public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long)
              {
                if ((paramAnonymousObject != null) && (!paramAnonymousObject.isEmpty()))
                {
                  HistoryFragment.this.getMainActivity().hideKeyboardIfShown();
                  paramAnonymous2AdapterView = new InvoiceFragment();
                  paramAnonymous2View = new Bundle();
                  paramAnonymous2View.putBoolean("fromHistory", true);
                  paramAnonymous2View.putString("orderId", ((Order)paramAnonymousObject.get(paramAnonymous2Int)).orderID);
                  paramAnonymous2AdapterView.setArguments(paramAnonymous2View);
                  HistoryFragment.this.getMainActivity().replaceFragment(paramAnonymous2AdapterView, true);
                }
              }
            });
            return;
          }
          catch (Exception paramAnonymousObject)
          {
            HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
            ((Exception)paramAnonymousObject).printStackTrace();
            return;
          }
        }
        Toast.makeText(HistoryFragment.this.getActivity(), HistoryFragment.this.getString(2131099709), 1).show();
      }
    });
  }
  
  private void showProgressDialog()
  {
    if (getActivity() != null)
    {
      this.progressDialog = new ProgressDialog(getActivity());
      this.progressDialog.setMessage(getString(2131099752));
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      this.progressDialog.show();
    }
  }
  
  private List<Order> sortOrdersDescendingOrderByDates(List<Order> paramList)
  {
    Collections.sort(paramList);
    return paramList;
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getMainActivity().getDeclineTextView();
    if ((paramBundle != null) && (paramBundle.getVisibility() == 0)) {
      paramBundle.setVisibility(8);
    }
    if ((getMainActivity().getBack() != null) && (getMainActivity().getBack().getVisibility() == 8)) {
      getMainActivity().getBack().setVisibility(0);
    }
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable final ViewGroup paramViewGroup, @Nullable final Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903098, paramViewGroup, false);
    setActionBarTitle(2131099821);
    this.stickyList = ((ListView)paramLayoutInflater.findViewById(2131493078));
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131493076);
    paramBundle = (Button)paramLayoutInflater.findViewById(2131493077);
    paramViewGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramViewGroup.setBackgroundResource(2130837596);
        paramBundle.setBackgroundResource(2130837595);
        if (HistoryFragment.this.getActivity() != null)
        {
          paramViewGroup.setTextColor(ContextCompat.getColor(HistoryFragment.this.getActivity(), 2131427434));
          paramBundle.setTextColor(ContextCompat.getColor(HistoryFragment.this.getActivity(), 2131427406));
        }
        if (HistoryFragment.this.currentHistoryAdapter != null) {
          HistoryFragment.this.currentHistoryAdapter.clear();
        }
        try
        {
          HistoryFragment.this.getPreviousHistoryAndBindItsAdapter();
          return;
        }
        catch (OutOfMemoryError paramAnonymousView)
        {
          HelperMethods.dismissDialog(HistoryFragment.this.progressDialog);
        }
      }
    });
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramBundle.setBackgroundResource(2130837596);
        paramViewGroup.setBackgroundResource(2130837595);
        if (HistoryFragment.this.getActivity() != null)
        {
          paramBundle.setTextColor(ContextCompat.getColor(HistoryFragment.this.getActivity(), 2131427434));
          paramViewGroup.setTextColor(ContextCompat.getColor(HistoryFragment.this.getActivity(), 2131427406));
        }
        if (HistoryFragment.this.historyAdapter != null) {
          HistoryFragment.this.historyAdapter.clear();
        }
        HistoryFragment.this.getCurrentHistoryAndBindItsAdapter();
      }
    });
    paramBundle.performClick();
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    getMainActivity().checkNavigationViewItem(2131493204);
  }
  
  public void onStop()
  {
    super.onStop();
    FirebaseHelper.getInstance().removeEndedTrips(this.endedTripsListener);
    FirebaseHelper.getInstance().removeCurrentTripsListener(this.currentTripsValueEventListener);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\HistoryFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */