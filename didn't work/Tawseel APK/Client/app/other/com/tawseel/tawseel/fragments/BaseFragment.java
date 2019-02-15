package com.tawseel.tawseel.fragments;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.tawseel.tawseel.activities.MainActivity;

public abstract class BaseFragment
  extends Fragment
{
  private static final String TAG = BaseFragment.class.getSimpleName();
  private int displayWidth;
  
  protected int GetPixelFromDips(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  protected int getDisplayWidth()
  {
    if (this.displayWidth == 0)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      this.displayWidth = localDisplayMetrics.widthPixels;
    }
    return this.displayWidth;
  }
  
  protected MainActivity getMainActivity()
  {
    if (getActivity() == null) {
      return null;
    }
    return (MainActivity)super.getActivity();
  }
  
  public void onResume()
  {
    super.onResume();
    Log.d(TAG, "number of back fragments " + getActivity().getSupportFragmentManager().getBackStackEntryCount());
  }
  
  public void setActionBarTitle(int paramInt)
  {
    ((MainActivity)getActivity()).setCustomTitle(paramInt);
  }
  
  public void setActionBarTitle(String paramString)
  {
    ((MainActivity)getActivity()).setCustomTitle(paramString);
  }
  
  public String splitPhoneCode(String paramString)
  {
    String str = paramString;
    if (paramString.startsWith("+966")) {
      str = paramString.replace("+966", "");
    }
    while (!paramString.startsWith("966")) {
      return str;
    }
    return paramString.replace("966", "");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\BaseFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */