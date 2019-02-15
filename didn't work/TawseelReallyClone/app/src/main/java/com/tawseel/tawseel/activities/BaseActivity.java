package com.tawseel.tawseel.activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tawseel.tawseel.CustomAlertDialog.Builder;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.Security;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.fragments.EnRouteTripFragment;
import com.tawseel.tawseel.fragments.InProgressFragment;
import com.tawseel.tawseel.fragments.IntroFragment;
import com.tawseel.tawseel.fragments.InvoiceFragment;
import com.tawseel.tawseel.fragments.LoadingInvoiceFragment;
import com.tawseel.tawseel.fragments.OrderReviewFragment;
import com.tawseel.tawseel.fragments.PickupLocationFragment;
import com.tawseel.tawseel.fragments.RatingFragment;
import com.tawseel.tawseel.fragments.RecipientDetailsFragment;
import com.tawseel.tawseel.fragments.RecipientLocationFragment;
import com.tawseel.tawseel.fragments.RequestDriverFragment;
import com.tawseel.tawseel.fragments.SenderDetailsFragment;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.FirebaseRemoteConfigHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Rider;
import java.util.Locale;

public abstract class BaseActivity
  extends AppCompatActivity
{
  private static final String TAG = BaseActivity.class.getSimpleName();
  protected FirebaseAuth mAuth;
  private Dialog mNetworkErrorDialog;
  public NavigationView navigationView;
  BroadcastReceiver networkStateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      boolean bool1 = false;
      boolean bool2 = paramAnonymousIntent.getBooleanExtra("noConnectivity", false);
      paramAnonymousContext = new StringBuilder().append("Network connected: ");
      if (!bool2) {
        bool1 = true;
      }
      Log.w("Network Listener", bool1);
      if (bool2)
      {
        BaseActivity.this.showNetworkErrorDialog();
        return;
      }
      HelperMethods.dismissDialog(BaseActivity.this.mNetworkErrorDialog);
    }
  };
  
  private void showNetworkErrorDialog()
  {
    if (this.mNetworkErrorDialog != null) {
      HelperMethods.dismissDialog(this.mNetworkErrorDialog);
    }
    this.mNetworkErrorDialog = new Dialog(this, 2131230941);
    this.mNetworkErrorDialog.setContentView(2130903093);
    this.mNetworkErrorDialog.getWindow().setLayout(-1, -1);
    this.mNetworkErrorDialog.setCancelable(false);
    this.mNetworkErrorDialog.show();
  }
  
  protected void addClientStatusListener()
  {
    FirebaseHelper.getInstance().addClientStatusListener(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        Security.getInstance(BaseActivity.this).logout(new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String) {}
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            if (paramAnonymous2String.equals("Success"))
            {
              Log.e("LoggedOUt", "LoggedOUt");
              paramAnonymous2Object = new Intent(BaseActivity.this, MessageAndActionActivity.class);
              ((Intent)paramAnonymous2Object).putExtra("key", "blocked");
              BaseActivity.this.startActivity((Intent)paramAnonymous2Object);
              BaseActivity.this.finish();
            }
          }
        });
      }
    });
  }
  
  protected void authenticateToFirebase(final GenericCallback paramGenericCallback)
  {
    if (Preferences.getInstance(CustomApplication.getAppContext()).getFirebaseHeader2() != null) {
      this.mAuth.signInWithCustomToken(Preferences.getInstance(CustomApplication.getAppContext()).getFirebaseHeader2()).addOnCompleteListener(this, new OnCompleteListener()
      {
        public void onComplete(@NonNull Task<AuthResult> paramAnonymousTask)
        {
          Log.d("Tawseel", "signInWithCustomToken:onComplete:" + paramAnonymousTask.isSuccessful());
          if (!paramAnonymousTask.isSuccessful())
          {
            Log.w("Tawseel", "signInWithCustomToken", paramAnonymousTask.getException());
            paramGenericCallback.onError("Error", "Authentication failed");
            return;
          }
          paramGenericCallback.onSuccess("Success", "Authenticated");
        }
      });
    }
  }
  
  public void clearStackFragments()
  {
    try
    {
      Log.d(TAG, "# of fragments back before cleared> " + getSupportFragmentManager().getBackStackEntryCount());
      getSupportFragmentManager().popBackStackImmediate(null, 1);
      getSupportFragmentManager().popBackStackImmediate(MainActivity.NAVIGATION_TAG, 1);
      Log.d(TAG, "# of fragments back after cleared> " + getSupportFragmentManager().getBackStackEntryCount());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void firebaseAuthentication(String paramString, final GenericCallback paramGenericCallback)
  {
    Preferences.getInstance(CustomApplication.getAppContext()).setFirebaseHeader2(paramString);
    authenticateToFirebase(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        FirebaseHelper.getInstance().getClientData(new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            BaseActivity.2.this.val$callback.onError(Boolean.valueOf(false), "Error");
            Log.e("Tawseel", "login:getClientData:Error ");
          }
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            if (paramAnonymous2String.equals("Success"))
            {
              Log.e("getClientData", "login:getClientData:Success ");
              Log.e("getClientData", paramAnonymous2Object.toString());
              if (CustomApplication.setCurrentUser((Rider)paramAnonymous2Object)) {
                BaseActivity.this.addClientStatusListener();
              }
              if (!Settings.isFetched) {
                FirebaseRemoteConfigHelper.getInstance().fetchRemoteConfig(new GenericCallback()
                {
                  public void onError(Object paramAnonymous3Object, String paramAnonymous3String) {}
                  
                  public void onSuccess(Object paramAnonymous3Object, String paramAnonymous3String) {}
                });
              }
              BaseActivity.2.this.val$callback.onSuccess(Boolean.valueOf(true), "Success");
              return;
            }
            Log.e("Tawseel", "login:getClientData:Failure " + paramAnonymous2Object);
            paramAnonymous2String = "";
            paramAnonymous2Object = (String)paramAnonymous2Object;
            int i = -1;
            switch (((String)paramAnonymous2Object).hashCode())
            {
            default: 
              switch (i)
              {
              default: 
                paramAnonymous2Object = paramAnonymous2String;
              }
              break;
            }
            for (;;)
            {
              if (paramAnonymous2Object == "") {
                break label381;
              }
              Log.e("Tawseel", "login:getClientData:Failure " + (String)paramAnonymous2Object);
              BaseActivity.2.this.val$callback.onSuccess(paramAnonymous2Object, "failed");
              return;
              if (!((String)paramAnonymous2Object).equals("documents_not_uploaded")) {
                break;
              }
              i = 0;
              break;
              if (!((String)paramAnonymous2Object).equals("blocked")) {
                break;
              }
              i = 1;
              break;
              if (!((String)paramAnonymous2Object).equals("inactive")) {
                break;
              }
              i = 2;
              break;
              if (!((String)paramAnonymous2Object).equals("not_registered")) {
                break;
              }
              i = 3;
              break;
              paramAnonymous2Object = BaseActivity.this.getResources().getString(2131099719);
              continue;
              paramAnonymous2Object = BaseActivity.this.getResources().getString(2131099690);
              continue;
              paramAnonymous2Object = BaseActivity.this.getResources().getString(2131099691);
              continue;
              paramAnonymous2Object = BaseActivity.this.getResources().getString(2131099692);
            }
            label381:
            BaseActivity.2.this.val$callback.onError(Boolean.valueOf(false), "Error");
          }
        });
      }
    });
  }
  
  public int getStatusBarHeight()
  {
    int i = 0;
    int j = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  public void goToIntroFragment()
  {
    replaceFragment(new IntroFragment(), false);
  }
  
  public void goToInvoiceFragment(Bundle paramBundle)
  {
    Log.e("goToInvoiceFragment", "goToInvoiceFragment");
    hideKeyboardIfShown();
    LoadingInvoiceFragment localLoadingInvoiceFragment = new LoadingInvoiceFragment();
    if (paramBundle != null) {
      localLoadingInvoiceFragment.setArguments(paramBundle);
    }
    replaceFragment(localLoadingInvoiceFragment, false);
  }
  
  public void goToUrl(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    startActivity(localIntent);
  }
  
  public void hideKeyboardIfShown()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    Log.d("Tawseel", "keyboard is showing");
    View localView = getCurrentFocus();
    Log.d("Tawseel", "view " + localView);
    if (localView != null) {
      localInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = new Locale("ar");
    Locale.setDefault(paramConfiguration);
    Configuration localConfiguration = new Configuration();
    localConfiguration.locale = paramConfiguration;
    getBaseContext().getResources().updateConfiguration(localConfiguration, getBaseContext().getResources().getDisplayMetrics());
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mAuth = FirebaseAuth.getInstance();
    setStatusBarColor();
    Log.d("Tawseel", "onCreate BaseActivity");
    paramBundle = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(this.networkStateReceiver, paramBundle);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.networkStateReceiver);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    Fragment localFragment = getSupportFragmentManager().findFragmentById(2131493024);
    if ((localFragment instanceof SenderDetailsFragment)) {
      localFragment.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    }
    do
    {
      return;
      if ((localFragment instanceof RecipientDetailsFragment))
      {
        localFragment.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
        return;
      }
      if ((localFragment instanceof PickupLocationFragment))
      {
        localFragment.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
        return;
      }
    } while (!(localFragment instanceof RecipientLocationFragment));
    localFragment.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
  }
  
  protected void onRestart()
  {
    Locale localLocale = new Locale("ar");
    Locale.setDefault(localLocale);
    Configuration localConfiguration = new Configuration();
    localConfiguration.locale = localLocale;
    getBaseContext().getResources().updateConfiguration(localConfiguration, getBaseContext().getResources().getDisplayMetrics());
    super.onRestart();
  }
  
  protected void onResume()
  {
    Locale localLocale = new Locale("ar");
    Locale.setDefault(localLocale);
    Configuration localConfiguration = new Configuration();
    localConfiguration.locale = localLocale;
    getBaseContext().getResources().updateConfiguration(localConfiguration, getBaseContext().getResources().getDisplayMetrics());
    super.onResume();
  }
  
  public void replaceFragment(Bundle paramBundle)
  {
    hideKeyboardIfShown();
    Object localObject2 = getSupportFragmentManager().findFragmentById(2131493024);
    boolean bool2 = false;
    Object localObject1 = null;
    boolean bool1;
    if (localObject2 == null)
    {
      localObject2 = new IntroFragment();
      bool1 = bool2;
      localObject1 = localObject2;
      if (paramBundle != null)
      {
        ((Fragment)localObject2).setArguments(paramBundle);
        localObject1 = localObject2;
        bool1 = bool2;
      }
    }
    for (;;)
    {
      replaceFragment((Fragment)localObject1, bool1);
      return;
      if ((localObject2 instanceof PickupLocationFragment))
      {
        localObject1 = new RecipientDetailsFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = true;
      }
      else if ((localObject2 instanceof IntroFragment))
      {
        localObject1 = new SenderDetailsFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = true;
      }
      else if ((localObject2 instanceof SenderDetailsFragment))
      {
        localObject1 = new PickupLocationFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = true;
      }
      else if ((localObject2 instanceof RecipientDetailsFragment))
      {
        localObject1 = new RecipientLocationFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = true;
      }
      else if ((localObject2 instanceof RecipientLocationFragment))
      {
        localObject1 = new OrderReviewFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = true;
      }
      else if ((localObject2 instanceof OrderReviewFragment))
      {
        localObject1 = new RequestDriverFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = true;
      }
      else if ((localObject2 instanceof RequestDriverFragment))
      {
        localObject1 = new EnRouteTripFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = false;
      }
      else if ((localObject2 instanceof EnRouteTripFragment))
      {
        localObject1 = new InProgressFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = false;
      }
      else if ((localObject2 instanceof InProgressFragment))
      {
        localObject1 = new LoadingInvoiceFragment();
        if (paramBundle != null) {
          ((Fragment)localObject1).setArguments(paramBundle);
        }
        bool1 = false;
      }
      else
      {
        bool1 = bool2;
        if ((localObject2 instanceof InvoiceFragment))
        {
          localObject1 = new RatingFragment();
          if (paramBundle != null) {
            ((Fragment)localObject1).setArguments(paramBundle);
          }
          bool1 = true;
        }
      }
    }
  }
  
  public void replaceFragment(Fragment paramFragment, String paramString)
  {
    try
    {
      getSupportFragmentManager().beginTransaction().replace(2131493024, paramFragment).addToBackStack(paramString).commitAllowingStateLoss();
      return;
    }
    catch (Exception paramFragment)
    {
      paramFragment.printStackTrace();
    }
  }
  
  public void replaceFragment(Fragment paramFragment, boolean paramBoolean)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.setCustomAnimations(2130968594, 2130968595);
    localFragmentTransaction.replace(2131493024, paramFragment);
    if (paramBoolean) {
      localFragmentTransaction.addToBackStack(null);
    }
    for (;;)
    {
      try
      {
        localFragmentTransaction.commitAllowingStateLoss();
        return;
      }
      catch (Exception paramFragment)
      {
        paramFragment.printStackTrace();
      }
      clearStackFragments();
    }
  }
  
  public void setStatusBarColor()
  {
    View localView = null;
    if (Build.VERSION.SDK_INT >= 19)
    {
      Window localWindow = getWindow();
      localWindow.setFlags(67108864, 67108864);
      int i = getStatusBarHeight();
      localView = new View(this);
      localView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
      localView.getLayoutParams().height = i;
      ((ViewGroup)localWindow.getDecorView()).addView(localView);
    }
    if ("http://tawseel.sa/".contains("development")) {
      if (localView != null) {
        localView.setBackgroundColor(getResources().getColor(2131427422));
      }
    }
    do
    {
      do
      {
        return;
        if (!"http://tawseel.sa/".contains("staging")) {
          break;
        }
      } while (localView == null);
      localView.setBackgroundColor(getResources().getColor(2131427405));
      return;
    } while (localView == null);
    localView.setBackgroundColor(getResources().getColor(2131427406));
  }
  
  public void showAlertWithMessage(String paramString)
  {
    new CustomAlertDialog.Builder(this).setMessage(paramString).setPositiveButton(17039370, null).show();
  }
  
  public AlertDialog showAndGetAlertWithMessage(String paramString)
  {
    return new CustomAlertDialog.Builder(this).setMessage(paramString).setPositiveButton(17039370, null).show();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */