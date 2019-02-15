package com.tawseel.tawseel;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.multidex.MultiDex;
import com.crashlytics.android.Crashlytics;
import com.tawseel.tawseel.helpers.FontsOverride;
import com.tawseel.tawseel.models.Rider;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.Locale;

public class CustomApplication
  extends Application
{
  private static Context context;
  private static Rider currentUser;
  
  public static Context getAppContext()
  {
    return context;
  }
  
  public static Rider getCurrentUser()
  {
    return currentUser;
  }
  
  public static void removeCurrentUser()
  {
    currentUser = null;
  }
  
  public static boolean setCurrentUser(Rider paramRider)
  {
    boolean bool = true;
    if (currentUser != null) {
      bool = false;
    }
    currentUser = paramRider;
    return bool;
  }
  
  private void setLanguage()
  {
    Locale localLocale = new Locale("ar");
    Locale.setDefault(localLocale);
    Configuration localConfiguration = new Configuration();
    localConfiguration.locale = localLocale;
    if (Build.VERSION.SDK_INT >= 17) {
      localConfiguration.setLayoutDirection(localLocale);
    }
    getApplicationContext().getResources().updateConfiguration(localConfiguration, null);
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    MultiDex.install(this);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    setLanguage();
  }
  
  public void onCreate()
  {
    super.onCreate();
    Fabric.with(this, new Kit[] { new Crashlytics() });
    setLanguage();
    context = getApplicationContext();
    FontsOverride.setDefaultFont(this, "MONOSPACE", "DroidKufi-Regular.ttf");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\CustomApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */