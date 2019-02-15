package com.tawseel.tawseel;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.network.APIConnector;
import java.io.IOException;
import org.json.JSONObject;
import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public class Security
{
  private static final String TAG = Security.class.getSimpleName();
  private static Security sInstance;
  private Activity mContext;
  
  private Security(Activity paramActivity)
  {
    this.mContext = paramActivity;
  }
  
  public static Security getInstance(Activity paramActivity)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new Security(paramActivity);
      }
      paramActivity = sInstance;
      return paramActivity;
    }
    finally {}
  }
  
  public void loginSMS(final String paramString, final GenericCallback paramGenericCallback)
  {
    APIConnector.getInstance(this.mContext).sendVerificationToNumber(PhoneUtils.validatePhoneNo(paramString), new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (paramAnonymousObject != null) {
          try
          {
            paramAnonymousString = (JSONObject)paramAnonymousObject;
            paramAnonymousObject = "";
            paramAnonymousString = paramAnonymousString.getString("error");
            if (paramAnonymousString.equals("invalidPhoneNumber")) {
              paramAnonymousObject = Security.this.mContext.getResources().getString(2131099864);
            }
            for (;;)
            {
              paramGenericCallback.onSuccess(paramAnonymousObject, "Failed");
              return;
              paramAnonymousString = new JSONObject(paramAnonymousString).getString("accountStatus");
              if (paramAnonymousString.equals("inactive")) {
                paramAnonymousObject = Security.this.mContext.getResources().getString(2131099691);
              } else if (paramAnonymousString.equals("blocked")) {
                paramAnonymousObject = Security.this.mContext.getResources().getString(2131099690);
              }
            }
            paramGenericCallback.onError(Boolean.valueOf(false), "Error");
          }
          catch (Exception paramAnonymousObject)
          {
            ((Exception)paramAnonymousObject).printStackTrace();
            paramGenericCallback.onError(Boolean.valueOf(false), "Error");
            return;
          }
        }
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        Preferences.getInstance(CustomApplication.getAppContext()).setUserPhone(paramString);
        paramGenericCallback.onSuccess(Boolean.valueOf(true), "Success");
      }
    });
  }
  
  public void loginVerifyPhone(String paramString1, String paramString2, final GenericCallback paramGenericCallback)
  {
    APIConnector.getInstance(this.mContext).loginWithVerificationCode(PhoneUtils.validatePhoneNo(paramString1), paramString2, new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        if (paramAnonymousObject != null) {
          try
          {
            paramAnonymousString = (JSONObject)paramAnonymousObject;
            paramAnonymousObject = "Error";
            paramAnonymousString = paramAnonymousString.getString("error");
            if (paramAnonymousString.equals("wrongPassCode")) {
              paramAnonymousObject = Security.this.mContext.getResources().getString(2131099865);
            }
            for (;;)
            {
              paramGenericCallback.onSuccess(paramAnonymousObject, "Failed");
              return;
              paramAnonymousString = new JSONObject(paramAnonymousString).getString("accountStatus");
              if (paramAnonymousString.equals("inactive")) {
                paramAnonymousObject = Security.this.mContext.getResources().getString(2131099691);
              } else if (paramAnonymousString.equals("blocked")) {
                paramAnonymousObject = Security.this.mContext.getResources().getString(2131099690);
              }
            }
            paramGenericCallback.onError(Boolean.valueOf(false), "Error");
          }
          catch (Exception paramAnonymousObject)
          {
            ((Exception)paramAnonymousObject).printStackTrace();
            paramGenericCallback.onError(Boolean.valueOf(false), "Error");
            return;
          }
        }
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        paramAnonymousObject = (JSONObject)paramAnonymousObject;
        try
        {
          paramAnonymousObject = ((JSONObject)paramAnonymousObject).getString("firebaseToken");
          paramGenericCallback.onSuccess(paramAnonymousObject, "SuccessWithToken");
          Log.d("com.test", "success getting firebase token " + (String)paramAnonymousObject);
          return;
        }
        catch (Exception paramAnonymousObject)
        {
          ((Exception)paramAnonymousObject).printStackTrace();
          paramGenericCallback.onError(Boolean.valueOf(false), "Error");
        }
      }
    });
  }
  
  public void logout(GenericCallback paramGenericCallback)
  {
    FirebaseHelper.getInstance().removeClientStatusListener();
    Preferences.getInstance(this.mContext).removeUserId();
    Preferences.getInstance(this.mContext).removeRiderId();
    Preferences.getInstance(this.mContext).removeUserEmail();
    Preferences.getInstance(this.mContext).removeUserPassword();
    Preferences.getInstance(this.mContext).removeAPIKey();
    Preferences.getInstance(this.mContext).removeCookie();
    Preferences.getInstance(this.mContext).removeFireBaseHeader();
    Preferences.getInstance(this.mContext).removeFireBaseHeader2();
    Preferences.getInstance(this.mContext).setIsAvailable(false);
    CustomApplication.removeCurrentUser();
    paramGenericCallback.onSuccess("Logout", "Success");
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Log.d(Security.TAG, "deleting firebase instance id....");
          FirebaseInstanceId.getInstance().deleteInstanceId();
          Log.d(Security.TAG, "deleting firebase instance id success");
          return;
        }
        catch (IOException localIOException)
        {
          Log.e(Security.TAG, "deleting firebase instance id failed");
          localIOException.printStackTrace();
        }
      }
    }).start();
    FirebaseAuth.getInstance().signOut();
  }
  
  public void registerLogin(String paramString1, String paramString2, String paramString3, final GenericCallback paramGenericCallback, Activity paramActivity)
  {
    FirebaseHelper.getInstance().registerClient(paramString1, paramString2, paramString3, new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        paramGenericCallback.onError(paramAnonymousObject, paramAnonymousString);
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        paramGenericCallback.onSuccess("true", "Success");
      }
    }, paramActivity);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\Security.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */