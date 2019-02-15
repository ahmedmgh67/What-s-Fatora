package com.tawseel.tawseel.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.Security;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.helpers.AppVersionHelper;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.FirebaseRemoteConfigHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public class LoginVerifyPhoneActivity
  extends BaseActivity
{
  private EditText mPhoneTokenView;
  private ProgressDialog mProgressDialog;
  
  private void attemptLogin()
  {
    this.mPhoneTokenView.setError(null);
    final String str1 = this.mPhoneTokenView.getText().toString();
    String str2 = Preferences.getInstance(CustomApplication.getAppContext()).getUserPhone();
    Log.e("Tawseel", "phone - Login" + str2);
    Log.e("Tawseel", "phoneToken - Login" + str1);
    int i = 0;
    EditText localEditText = null;
    if (TextUtils.isEmpty(str1))
    {
      this.mPhoneTokenView.setError(getString(2131099738));
      localEditText = this.mPhoneTokenView;
      i = 1;
    }
    if (i != 0)
    {
      localEditText.requestFocus();
      HelperMethods.dismissDialog(this.mProgressDialog);
      return;
    }
    Security.getInstance(this).loginVerifyPhone(str2, str1, new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(LoginVerifyPhoneActivity.this.mProgressDialog);
        paramAnonymousObject = new Intent(LoginVerifyPhoneActivity.this, ConnectionSMSActivity.class);
        ((Intent)paramAnonymousObject).putExtra("phoneToken", str1);
        LoginVerifyPhoneActivity.this.startActivity((Intent)paramAnonymousObject);
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (paramAnonymousString.equals("SuccessWithToken"))
        {
          paramAnonymousObject = paramAnonymousObject.toString();
          LoginVerifyPhoneActivity.this.firebaseAuthentication((String)paramAnonymousObject, new GenericCallback()
          {
            public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
            {
              HelperMethods.dismissDialog(LoginVerifyPhoneActivity.this.mProgressDialog);
              Log.e("CnctnSmsAct frbsauth", "onerror " + paramAnonymous2String);
              Toast.makeText(LoginVerifyPhoneActivity.this, LoginVerifyPhoneActivity.this.getString(2131099709), 1).show();
            }
            
            public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
            {
              HelperMethods.dismissDialog(LoginVerifyPhoneActivity.this.mProgressDialog);
              if (paramAnonymous2String.equals("Success"))
              {
                FirebaseHelper.getInstance().updateDeviceInfo();
                paramAnonymous2Object = new Intent(LoginVerifyPhoneActivity.this, MainActivity.class);
                ((Intent)paramAnonymous2Object).addFlags(268468224);
                LoginVerifyPhoneActivity.this.startActivity((Intent)paramAnonymous2Object);
                LoginVerifyPhoneActivity.this.finish();
                return;
              }
              if ((paramAnonymous2Object.toString().equals("not_registered")) || (paramAnonymous2Object.equals(LoginVerifyPhoneActivity.this.getString(2131099692))))
              {
                Log.e("setting phonetoken", LoginVerifyPhoneActivity.4.this.val$phoneToken);
                Preferences.getInstance(CustomApplication.getAppContext()).setUserPhoneToken(LoginVerifyPhoneActivity.4.this.val$phoneToken);
                paramAnonymous2Object = new Intent(LoginVerifyPhoneActivity.this, RegistrationActivity.class);
                ((Intent)paramAnonymous2Object).putExtra("phoneToken", LoginVerifyPhoneActivity.4.this.val$phoneToken);
                LoginVerifyPhoneActivity.this.startActivity((Intent)paramAnonymous2Object);
                return;
              }
              if (paramAnonymous2Object.toString().equals(LoginVerifyPhoneActivity.this.getString(2131099719)))
              {
                Log.e("login", "no docs");
                return;
              }
              if (paramAnonymous2Object.toString().equals(LoginVerifyPhoneActivity.this.getString(2131099691)))
              {
                Log.e("login", "waiting activation");
                return;
              }
              LoginVerifyPhoneActivity.this.mPhoneTokenView.setError(paramAnonymous2Object.toString());
              LoginVerifyPhoneActivity.this.mPhoneTokenView.requestFocus();
              paramAnonymous2Object = Toast.makeText(LoginVerifyPhoneActivity.this.getApplicationContext(), paramAnonymous2Object.toString(), 1);
              ((Toast)paramAnonymous2Object).setGravity(49, 0, 0);
              ((Toast)paramAnonymous2Object).show();
            }
          });
        }
        while (!paramAnonymousString.equals("Failed")) {
          return;
        }
        HelperMethods.dismissDialog(LoginVerifyPhoneActivity.this.mProgressDialog);
        LoginVerifyPhoneActivity.this.mPhoneTokenView.setError(paramAnonymousObject.toString());
        LoginVerifyPhoneActivity.this.mPhoneTokenView.requestFocus();
        paramAnonymousObject = Toast.makeText(LoginVerifyPhoneActivity.this.getApplicationContext(), paramAnonymousObject.toString(), 1);
        ((Toast)paramAnonymousObject).setGravity(49, 0, 0);
        ((Toast)paramAnonymousObject).show();
      }
    });
  }
  
  public void goToCallActivity(String paramString)
  {
    paramString = PhoneUtils.validatePhoneNo(paramString);
    if (!paramString.isEmpty()) {}
    try
    {
      paramString = "+" + paramString;
      paramString = "tel:" + paramString.trim();
      Intent localIntent = new Intent("android.intent.action.DIAL");
      localIntent.setData(Uri.parse(paramString));
      startActivity(localIntent);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903069);
    this.mPhoneTokenView = ((EditText)findViewById(2131493003));
    ((TextView)findViewById(2131492999)).setText("+" + Preferences.getInstance(CustomApplication.getAppContext()).getUserPhone());
    FirebaseRemoteConfigHelper.getInstance().fetchRemoteConfig(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString) {}
    });
    ((TextView)findViewById(2131493005)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!Settings.Contact_Us_Number.equals("123456")) {
          LoginVerifyPhoneActivity.this.goToCallActivity(Settings.Contact_Us_Number);
        }
      }
    });
    ((Button)findViewById(2131493001)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView.setEnabled(false);
        LoginVerifyPhoneActivity.this.hideKeyboardIfShown();
        LoginVerifyPhoneActivity.access$002(LoginVerifyPhoneActivity.this, HelperMethods.getProgressDialog(LoginVerifyPhoneActivity.this));
        LoginVerifyPhoneActivity.this.mProgressDialog.show();
        LoginVerifyPhoneActivity.this.attemptLogin();
        final AppVersionHelper localAppVersionHelper = new AppVersionHelper(LoginVerifyPhoneActivity.this);
        localAppVersionHelper.isUpdated(new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String) {}
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            if (!((Boolean)paramAnonymous2Object).booleanValue()) {
              localAppVersionHelper.showUpdateVersionAlert();
            }
          }
        });
        paramAnonymousView.setEnabled(true);
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\LoginVerifyPhoneActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */