package com.tawseel.tawseel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.activities.BaseActivity;
import com.tawseel.tawseel.activities.MainActivity;

public class AboutUsFragment
  extends BaseFragment
{
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903095, paramViewGroup, false);
    setActionBarTitle("");
    paramViewGroup = ((MainActivity)getActivity()).getDeclineTextView();
    if ((paramViewGroup != null) && (paramViewGroup.getVisibility() == 0)) {
      paramViewGroup.setVisibility(8);
    }
    paramViewGroup = ((MainActivity)getActivity()).getBack();
    if (paramViewGroup != null) {
      paramViewGroup.setVisibility(0);
    }
    paramViewGroup = (TextView)paramLayoutInflater.findViewById(2131493062);
    paramBundle = (TextView)paramLayoutInflater.findViewById(2131493060);
    paramViewGroup.setText("v 3.0.650(650)");
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          ((BaseActivity)AboutUsFragment.this.getActivity()).goToUrl(Settings.TermsUrl);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    getMainActivity().checkNavigationViewItem(2131493207);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\AboutUsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */