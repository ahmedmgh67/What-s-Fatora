package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.HelperMethods;

public class LoadingInvoiceFragment
  extends BaseFragment
{
  String orderId = "";
  ProgressDialog progress;
  Boolean showNextButton = Boolean.valueOf(true);
  
  private void startFareListener()
  {
    FirebaseHelper.getInstance().checkOnFareAdded(this.orderId, new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (LoadingInvoiceFragment.this.getActivity() != null) {
          Toast.makeText(LoadingInvoiceFragment.this.getActivity(), LoadingInvoiceFragment.this.getString(2131099709), 1).show();
        }
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        LoadingInvoiceFragment.this.makeInvoiceTransaction();
        HelperMethods.dismissDialog(LoadingInvoiceFragment.this.progress);
      }
    });
  }
  
  private ProgressDialog startProgress()
  {
    this.progress = new ProgressDialog(getActivity());
    this.progress.setMessage(getString(2131099753));
    this.progress.setCancelable(false);
    this.progress.show();
    return this.progress;
  }
  
  private void stopFareListener()
  {
    FirebaseHelper.getInstance().removeFareListener();
  }
  
  protected void makeInvoiceTransaction()
  {
    MainActivity localMainActivity = getMainActivity();
    String str = this.orderId;
    if (!this.showNextButton.booleanValue()) {}
    for (boolean bool = true;; bool = false)
    {
      localMainActivity.replaceFragment(InvoiceFragment.newInstance(str, bool, false), false);
      return;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      paramBundle = getArguments();
      this.orderId = paramBundle.getString("orderId", "");
      this.showNextButton = Boolean.valueOf(paramBundle.getBoolean("showNextButton", true));
    }
    if (isAdded()) {
      this.progress = startProgress();
    }
    setActionBarTitle("");
  }
  
  public void onDestroyView()
  {
    HelperMethods.dismissDialog(this.progress);
    super.onDestroyView();
  }
  
  public void onPause()
  {
    super.onPause();
    stopFareListener();
  }
  
  public void onResume()
  {
    super.onResume();
    setActionBarTitle("");
    TextView localTextView = ((MainActivity)getActivity()).getBack();
    if (localTextView != null) {
      localTextView.setVisibility(8);
    }
    localTextView = ((MainActivity)getActivity()).getDeclineTextView();
    if ((localTextView != null) && (localTextView.getVisibility() == 0)) {
      localTextView.setVisibility(8);
    }
    startFareListener();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\LoadingInvoiceFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */