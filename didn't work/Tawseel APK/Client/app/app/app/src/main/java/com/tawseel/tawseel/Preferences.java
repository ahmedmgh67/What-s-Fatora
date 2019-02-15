package com.tawseel.tawseel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences
{
  private static Preferences sInstance;
  SharedPreferences.Editor editor;
  SharedPreferences preferences;
  private final String sharedPrefName = "UserPref";
  
  private Preferences(Context paramContext)
  {
    this.preferences = paramContext.getSharedPreferences("UserPref", 0);
    this.editor = this.preferences.edit();
  }
  
  public static Preferences getInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new Preferences(paramContext);
      }
      paramContext = sInstance;
      return paramContext;
    }
    finally {}
  }
  
  public void cacheBooleanValue(String paramString, boolean paramBoolean)
  {
    this.editor.putBoolean(paramString, paramBoolean);
    this.editor.commit();
  }
  
  public void cacheStringValue(String paramString1, String paramString2)
  {
    this.editor.putString(paramString1, paramString2);
    this.editor.commit();
  }
  
  public boolean getCashedBoolean(String paramString)
  {
    return this.preferences.getBoolean(paramString, false);
  }
  
  public String getCashedString(String paramString)
  {
    return this.preferences.getString(paramString, null);
  }
  
  public String getFirebaseHeader2()
  {
    return this.preferences.getString("firebaseHeader2", "");
  }
  
  public String getUserPhone()
  {
    return this.preferences.getString("userPhone", "");
  }
  
  public void removeAPIKey()
  {
    this.editor.remove("apiKey");
    this.editor.commit();
  }
  
  public void removeCookie()
  {
    this.editor.remove("cookie");
    this.editor.commit();
  }
  
  public void removeFireBaseHeader()
  {
    this.editor.remove("firebaseHeader");
    this.editor.commit();
  }
  
  public void removeFireBaseHeader2()
  {
    this.editor.remove("firebaseHeader2");
    this.editor.commit();
  }
  
  public void removeRiderId()
  {
    this.editor.remove("riderId");
    this.editor.commit();
  }
  
  public void removeUserData()
  {
    removeUserPhone();
    removeUserPhoneToken();
  }
  
  public void removeUserEmail()
  {
    this.editor.remove("email");
    this.editor.commit();
  }
  
  public void removeUserId()
  {
    this.editor.remove("userId");
    this.editor.commit();
  }
  
  public void removeUserPassword()
  {
    this.editor.remove("password");
    this.editor.commit();
  }
  
  public void removeUserPhone()
  {
    this.editor.remove("userPhone");
    this.editor.commit();
  }
  
  public void removeUserPhoneToken()
  {
    this.editor.remove("phoneToken");
    this.editor.commit();
  }
  
  public void saveCookie(String paramString)
  {
    if (paramString == null) {
      return;
    }
    this.editor.putString("cookie", paramString);
    this.editor.commit();
  }
  
  public void setFirebaseHeader2(String paramString)
  {
    this.editor.putString("firebaseHeader2", paramString);
    this.editor.commit();
  }
  
  public void setIsAvailable(boolean paramBoolean)
  {
    this.editor.putBoolean("isAvailable", paramBoolean);
    this.editor.commit();
  }
  
  public void setUserPhone(String paramString)
  {
    this.editor.putString("userPhone", paramString.replace("+", ""));
    this.editor.commit();
  }
  
  public void setUserPhoneToken(String paramString)
  {
    this.editor.putString("phoneToken", paramString);
    this.editor.commit();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\Preferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */