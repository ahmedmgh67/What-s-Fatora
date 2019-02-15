package com.tawseel.tawseel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.tawseel.tawseel.Constants.ClientPresenceStatus;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.helpers.AppVersionHelper;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.models.Rider;
import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public class SplashActivity
  extends BaseActivity
{
  private Boolean firstCall = Boolean.valueOf(false);
  private FirebaseAuth.AuthStateListener mAuthListener;
  
  private void promptLogin()
  {
    startActivity(new Intent(this, LoginSMSActivity.class));
    finish();
  }
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903073);
    paramBundle = new AppVersionHelper(this);
    paramBundle.isUpdated(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (!((Boolean)paramAnonymousObject).booleanValue()) {
          paramBundle.showUpdateVersionAlert();
        }
      }
    });
    if (Preferences.getInstance(CustomApplication.getAppContext()).getUserPhone().equals(""))
    {
      promptLogin();
      return;
    }
    Crashlytics.setString("CLIENTID", PhoneUtils.validatePhoneNo(Preferences.getInstance(CustomApplication.getAppContext()).getUserPhone()));
    this.mAuthListener = new FirebaseAuth.AuthStateListener()
    {
      public void onAuthStateChanged(@NonNull FirebaseAuth paramAnonymousFirebaseAuth)
      {
        paramAnonymousFirebaseAuth = paramAnonymousFirebaseAuth.getCurrentUser();
        if (!SplashActivity.this.firstCall.booleanValue())
        {
          if (paramAnonymousFirebaseAuth != null)
          {
            FirebaseHelper.getInstance().getClientData(new GenericCallback()
            {
              public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
              {
                paramAnonymous2Object = new Intent(SplashActivity.this, LoginSMSActivity.class);
                SplashActivity.this.startActivity((Intent)paramAnonymous2Object);
                SplashActivity.this.finish();
              }
              
              public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
              {
                if (paramAnonymous2String.equals("Success"))
                {
                  if (CustomApplication.setCurrentUser((Rider)paramAnonymous2Object)) {
                    SplashActivity.this.addClientStatusListener();
                  }
                  FirebaseHelper.getInstance().updateDeviceInfo();
                  paramAnonymous2Object = new Intent(SplashActivity.this, MainActivity.class);
                  ((Intent)paramAnonymous2Object).addFlags(268468224);
                  SplashActivity.this.startActivity((Intent)paramAnonymous2Object);
                  SplashActivity.this.finish();
                }
                while (!paramAnonymous2String.equals("Failure")) {
                  return;
                }
                if (paramAnonymous2Object.toString().equals(Constants.ClientPresenceStatus.BLOCKED.getValue()))
                {
                  Toast.makeText(SplashActivity.this, SplashActivity.this.getString(2131099690), 1).show();
                  paramAnonymous2Object = new Intent(SplashActivity.this, MessageAndActionActivity.class);
                  ((Intent)paramAnonymous2Object).putExtra("key", "blocked");
                  SplashActivity.this.startActivity((Intent)paramAnonymous2Object);
                  SplashActivity.this.finish();
                  return;
                }
                SplashActivity.this.promptLogin();
              }
            });
            return;
          }
          SplashActivity.this.promptLogin();
          return;
        }
        SplashActivity.access$002(SplashActivity.this, Boolean.valueOf(false));
      }
    };
  }
  
  public void onStart()
  {
    super.onStart();
    this.mAuth.addAuthStateListener(this.mAuthListener);
  }
  
  public void onStop()
  {
    super.onStop();
    if (this.mAuthListener != null) {
      this.mAuth.removeAuthStateListener(this.mAuthListener);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\SplashActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */