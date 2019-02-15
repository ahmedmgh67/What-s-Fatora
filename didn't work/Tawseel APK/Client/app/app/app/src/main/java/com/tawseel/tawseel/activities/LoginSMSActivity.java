package com.tawseel.tawseel.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.crashlytics.android.Crashlytics;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.Security;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.helpers.AppVersionHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public class LoginSMSActivity
  extends BaseActivity
{
  private EditText mPhoneView;
  private ProgressDialog mProgressDialog;
  
  private void attemptLogin()
  {
    this.mPhoneView.setError(null);
    final String str2 = this.mPhoneView.getText().toString();
    if (TextUtils.isEmpty(str2))
    {
      this.mPhoneView.setError(getString(2131099738));
      this.mPhoneView.requestFocus();
      HelperMethods.dismissDialog(this.mProgressDialog);
      return;
    }
    final String str1 = PhoneUtils.validatePhoneNo(str2);
    Log.d("com.phone", str2);
    if (str1.isEmpty())
    {
      this.mPhoneView.setError(getString(2131099864));
      this.mPhoneView.requestFocus();
      HelperMethods.dismissDialog(this.mProgressDialog);
      return;
    }
    str2 = "+" + str1;
    Security.getInstance(this).loginSMS(str2, new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(LoginSMSActivity.this.mProgressDialog);
        paramAnonymousObject = new Intent(LoginSMSActivity.this, ConnectionSMSActivity.class);
        ((Intent)paramAnonymousObject).putExtra("phone", str2);
        LoginSMSActivity.this.startActivity((Intent)paramAnonymousObject);
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(LoginSMSActivity.this.mProgressDialog);
        if (!paramAnonymousString.equals("Success"))
        {
          LoginSMSActivity.this.mPhoneView.setError(paramAnonymousObject.toString());
          LoginSMSActivity.this.mPhoneView.requestFocus();
          return;
        }
        Crashlytics.setString("CLIENTID", str1);
        paramAnonymousObject = new Intent(LoginSMSActivity.this, LoginVerifyPhoneActivity.class);
        LoginSMSActivity.this.startActivity((Intent)paramAnonymousObject);
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.e("LoginSMSActivity", "onCreate");
    setContentView(2130903068);
    this.mPhoneView = ((EditText)findViewById(2131492999));
    paramBundle = PhoneUtils.validatePhoneNo(Settings.Contact_Us_Number);
    if (!paramBundle.isEmpty()) {
      this.mPhoneView.setHint(getString(2131099743) + " " + paramBundle);
    }
    if (!Preferences.getInstance(getApplicationContext()).getUserPhone().isEmpty())
    {
      String str = Preferences.getInstance(getApplicationContext()).getUserPhone();
      paramBundle = str;
      if (str.startsWith("966")) {
        paramBundle = str.substring("966".length());
      }
      this.mPhoneView.setText(paramBundle);
    }
    ((Button)findViewById(2131493001)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView.setEnabled(false);
        LoginSMSActivity.this.hideKeyboardIfShown();
        LoginSMSActivity.access$002(LoginSMSActivity.this, HelperMethods.getProgressDialog(LoginSMSActivity.this));
        LoginSMSActivity.this.mProgressDialog.show();
        final AppVersionHelper localAppVersionHelper = new AppVersionHelper(LoginSMSActivity.this);
        localAppVersionHelper.isUpdated(new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            LoginSMSActivity.this.attemptLogin();
          }
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            if (!((Boolean)paramAnonymous2Object).booleanValue())
            {
              localAppVersionHelper.showUpdateVersionAlert();
              return;
            }
            LoginSMSActivity.this.attemptLogin();
          }
        });
        paramAnonymousView.setEnabled(true);
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\LoginSMSActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */