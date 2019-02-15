package com.tawseel.tawseel.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.Security;
import com.tawseel.tawseel.helpers.FirebaseHelper;

public class ConnectionSMSActivity
  extends BaseActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903067);
    ((Button)findViewById(2131492993)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(final View paramAnonymousView)
      {
        paramAnonymousView.setEnabled(false);
        if ((ConnectionSMSActivity.this.getIntent() != null) && (ConnectionSMSActivity.this.getIntent().getExtras() != null) && (ConnectionSMSActivity.this.getIntent().getExtras().getString("phone") != null) && (!ConnectionSMSActivity.this.getIntent().getExtras().getString("phone").isEmpty()))
        {
          str = ConnectionSMSActivity.this.getIntent().getExtras().getString("phone");
          Security.getInstance(ConnectionSMSActivity.this).loginSMS(str, new GenericCallback()
          {
            public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
            {
              paramAnonymousView.setEnabled(true);
            }
            
            public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
            {
              paramAnonymousView.setEnabled(true);
              if (!paramAnonymous2String.equals("Success"))
              {
                ConnectionSMSActivity.this.finish();
                if (paramAnonymous2Object.toString().equals(ConnectionSMSActivity.this.getResources().getString(2131099690)))
                {
                  paramAnonymous2Object = new Intent(ConnectionSMSActivity.this, MessageAndActionActivity.class);
                  ((Intent)paramAnonymous2Object).putExtra("key", "blocked");
                  ConnectionSMSActivity.this.startActivity((Intent)paramAnonymous2Object);
                  ConnectionSMSActivity.this.finish();
                }
                return;
              }
              paramAnonymous2Object = new Intent(ConnectionSMSActivity.this, LoginVerifyPhoneActivity.class);
              ConnectionSMSActivity.this.startActivity((Intent)paramAnonymous2Object);
              ConnectionSMSActivity.this.finish();
            }
          });
        }
        while ((ConnectionSMSActivity.this.getIntent() == null) || (ConnectionSMSActivity.this.getIntent().getExtras() == null) || (ConnectionSMSActivity.this.getIntent().getExtras().getString("phoneToken") == null) || (ConnectionSMSActivity.this.getIntent().getExtras().getString("phoneToken").isEmpty())) {
          return;
        }
        String str = ConnectionSMSActivity.this.getIntent().getExtras().getString("phoneToken");
        Security.getInstance(ConnectionSMSActivity.this).loginVerifyPhone(Preferences.getInstance(CustomApplication.getAppContext()).getUserPhone(), str, new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            paramAnonymousView.setEnabled(true);
          }
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            paramAnonymousView.setEnabled(true);
            if (paramAnonymous2String.equals("SuccessWithToken"))
            {
              paramAnonymous2Object = paramAnonymous2Object.toString();
              ConnectionSMSActivity.this.firebaseAuthentication((String)paramAnonymous2Object, new GenericCallback()
              {
                public void onError(Object paramAnonymous3Object, String paramAnonymous3String)
                {
                  Log.e("CnctnSmsAct frbsauth", "onerror " + paramAnonymous3String);
                  Toast.makeText(ConnectionSMSActivity.this, ConnectionSMSActivity.this.getString(2131099709), 1).show();
                }
                
                public void onSuccess(Object paramAnonymous3Object, String paramAnonymous3String)
                {
                  if (paramAnonymous3String.equals("Success"))
                  {
                    FirebaseHelper.getInstance().updateDeviceInfo();
                    paramAnonymous3Object = new Intent(ConnectionSMSActivity.this, MainActivity.class);
                    ((Intent)paramAnonymous3Object).addFlags(268468224);
                    ConnectionSMSActivity.this.startActivity((Intent)paramAnonymous3Object);
                    ConnectionSMSActivity.this.finish();
                    return;
                  }
                  ConnectionSMSActivity.this.finish();
                  paramAnonymous3Object = Toast.makeText(ConnectionSMSActivity.this.getApplicationContext(), paramAnonymous3Object.toString(), 1);
                  ((Toast)paramAnonymous3Object).setGravity(49, 0, 0);
                  ((Toast)paramAnonymous3Object).show();
                  Log.e("CnctnSmsAct frbsauth", "onsuccess " + paramAnonymous3String);
                }
              });
            }
            while (!paramAnonymous2String.equals("Failed")) {
              return;
            }
            paramAnonymous2Object = Toast.makeText(ConnectionSMSActivity.this.getApplicationContext(), paramAnonymous2Object.toString(), 1);
            ((Toast)paramAnonymous2Object).setGravity(49, 0, 0);
            ((Toast)paramAnonymous2Object).show();
          }
        });
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\ConnectionSMSActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */