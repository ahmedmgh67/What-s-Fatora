package com.tawseel.tawseel.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
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
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Rider;
import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public class RegistrationActivity
  extends BaseActivity
{
  private EditText email;
  private EditText fullName;
  private EditText phone;
  private ProgressDialog progress;
  
  private void refreshStrokeColor()
  {
    ((GradientDrawable)this.fullName.getBackground()).setStroke(1, getResources().getColor(2131427366));
    ((GradientDrawable)this.phone.getBackground()).setStroke(1, getResources().getColor(2131427366));
    ((GradientDrawable)this.email.getBackground()).setStroke(1, getResources().getColor(2131427366));
  }
  
  private Boolean validData()
  {
    String str = PhoneUtils.validatePhoneNo(this.phone.getText().toString());
    boolean bool = HelperMethods.isValidEmail(this.email.getText().toString().trim());
    if ((this.fullName.getText() == null) || (this.fullName.getText().toString().isEmpty()))
    {
      ((GradientDrawable)this.fullName.getBackground()).setStroke(1, getResources().getColor(2131427421));
      showAlertWithMessage(getString(2131099850));
      return Boolean.valueOf(false);
    }
    if ((this.phone.getText() == null) || (this.phone.getText().toString().isEmpty()))
    {
      ((GradientDrawable)this.phone.getBackground()).setStroke(1, getResources().getColor(2131427421));
      showAlertWithMessage(getString(2131099862) + " " + getString(2131099793));
      return Boolean.valueOf(false);
    }
    if (str.isEmpty())
    {
      ((GradientDrawable)this.phone.getBackground()).setStroke(1, getResources().getColor(2131427421));
      showAlertWithMessage(getString(2131099777));
      return Boolean.valueOf(false);
    }
    if ((this.email == null) || (this.email.getText().toString().isEmpty()))
    {
      ((GradientDrawable)this.email.getBackground()).setStroke(1, getResources().getColor(2131427421));
      showAlertWithMessage(getString(2131099729));
      return Boolean.valueOf(false);
    }
    if (!Boolean.valueOf(bool).booleanValue())
    {
      ((GradientDrawable)this.email.getBackground()).setStroke(1, getResources().getColor(2131427421));
      showAlertWithMessage(getString(2131099861));
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(true);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903072);
    Object localObject = (Toolbar)findViewById(2131493018);
    paramBundle = (TextView)((Toolbar)localObject).findViewById(2131492950);
    localObject = (TextView)((Toolbar)localObject).findViewById(2131493020);
    paramBundle.setText(2131099769);
    ((TextView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (RegistrationActivity.this.getSupportFragmentManager().getBackStackEntryCount() == 0)
        {
          paramAnonymousView = new Intent(RegistrationActivity.this, LoginSMSActivity.class);
          RegistrationActivity.this.startActivity(paramAnonymousView);
          RegistrationActivity.this.finish();
          return;
        }
        RegistrationActivity.this.onBackPressed();
      }
    });
    this.fullName = ((EditText)findViewById(2131493011));
    this.phone = ((EditText)findViewById(2131492999));
    this.email = ((EditText)findViewById(2131493012));
    paramBundle = (Button)findViewById(2131493013);
    localObject = (TextView)findViewById(2131493014);
    String str = "+" + Preferences.getInstance(CustomApplication.getAppContext()).getUserPhone();
    this.phone.setText(str);
    refreshStrokeColor();
    ((TextView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegistrationActivity.this.goToUrl(Settings.TermsUrl);
      }
    });
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegistrationActivity.this.refreshStrokeColor();
        if (RegistrationActivity.this.validData().booleanValue())
        {
          RegistrationActivity.access$202(RegistrationActivity.this, new ProgressDialog(RegistrationActivity.this));
          RegistrationActivity.this.progress.setMessage(RegistrationActivity.this.getString(2131099752));
          RegistrationActivity.this.progress.setCancelable(false);
          RegistrationActivity.this.progress.show();
          Security.getInstance(RegistrationActivity.this).registerLogin(RegistrationActivity.this.fullName.getText().toString(), Preferences.getInstance(RegistrationActivity.this).getUserPhone(), RegistrationActivity.this.email.getText().toString().trim(), new GenericCallback()
          {
            public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
            {
              HelperMethods.dismissDialog(RegistrationActivity.this.progress);
              Toast.makeText(RegistrationActivity.this.getApplicationContext(), paramAnonymous2String + "", 1).show();
            }
            
            public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
            {
              HelperMethods.dismissDialog(RegistrationActivity.this.progress);
              if (paramAnonymous2String.equals("Success")) {
                FirebaseHelper.getInstance().getClientData(new GenericCallback()
                {
                  public void onError(Object paramAnonymous3Object, String paramAnonymous3String) {}
                  
                  public void onSuccess(Object paramAnonymous3Object, String paramAnonymous3String)
                  {
                    if (paramAnonymous3String.equals("Success"))
                    {
                      RegistrationActivity.this.addClientStatusListener();
                      Log.e("getClientData", "login:getClientData:Success ");
                      Log.e("getClientData", paramAnonymous3Object.toString());
                      CustomApplication.setCurrentUser((Rider)paramAnonymous3Object);
                      paramAnonymous3Object = new Intent(RegistrationActivity.this, MainActivity.class);
                      ((Intent)paramAnonymous3Object).addFlags(268468224);
                      RegistrationActivity.this.startActivity((Intent)paramAnonymous3Object);
                      RegistrationActivity.this.overridePendingTransition(2130968587, 2130968596);
                      RegistrationActivity.this.finish();
                    }
                  }
                });
              }
            }
          }, RegistrationActivity.this);
        }
      }
    });
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\RegistrationActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */