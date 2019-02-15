package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;

public class Answers
  extends Kit<Boolean>
{
  static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  public static final String TAG = "Answers";
  SessionAnalyticsManager analyticsManager;
  
  public static Answers getInstance()
  {
    return (Answers)Fabric.getKit(Answers.class);
  }
  
  protected Boolean doInBackground()
  {
    try
    {
      SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
      if (localSettingsData == null)
      {
        Fabric.getLogger().e("Answers", "Failed to retrieve settings");
        return Boolean.valueOf(false);
      }
      if (localSettingsData.featuresData.collectAnalytics)
      {
        Fabric.getLogger().d("Answers", "Analytics collection enabled");
        this.analyticsManager.setAnalyticsSettingsData(localSettingsData.analyticsSettingsData, getOverridenSpiEndpoint());
        return Boolean.valueOf(true);
      }
      Fabric.getLogger().d("Answers", "Analytics collection disabled");
      this.analyticsManager.disable();
      return Boolean.valueOf(false);
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Answers", "Error dealing with settings", localException);
    }
    return Boolean.valueOf(false);
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android:answers";
  }
  
  String getOverridenSpiEndpoint()
  {
    return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  public String getVersion()
  {
    return "1.3.13.dev";
  }
  
  public void logAddToCart(AddToCartEvent paramAddToCartEvent)
  {
    if (paramAddToCartEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramAddToCartEvent);
    }
  }
  
  public void logContentView(ContentViewEvent paramContentViewEvent)
  {
    if (paramContentViewEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramContentViewEvent);
    }
  }
  
  public void logCustom(CustomEvent paramCustomEvent)
  {
    if (paramCustomEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onCustom(paramCustomEvent);
    }
  }
  
  public void logInvite(InviteEvent paramInviteEvent)
  {
    if (paramInviteEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramInviteEvent);
    }
  }
  
  public void logLevelEnd(LevelEndEvent paramLevelEndEvent)
  {
    if (paramLevelEndEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramLevelEndEvent);
    }
  }
  
  public void logLevelStart(LevelStartEvent paramLevelStartEvent)
  {
    if (paramLevelStartEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramLevelStartEvent);
    }
  }
  
  public void logLogin(LoginEvent paramLoginEvent)
  {
    if (paramLoginEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramLoginEvent);
    }
  }
  
  public void logPurchase(PurchaseEvent paramPurchaseEvent)
  {
    if (paramPurchaseEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramPurchaseEvent);
    }
  }
  
  public void logRating(RatingEvent paramRatingEvent)
  {
    if (paramRatingEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramRatingEvent);
    }
  }
  
  public void logSearch(SearchEvent paramSearchEvent)
  {
    if (paramSearchEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramSearchEvent);
    }
  }
  
  public void logShare(ShareEvent paramShareEvent)
  {
    if (paramShareEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramShareEvent);
    }
  }
  
  public void logSignUp(SignUpEvent paramSignUpEvent)
  {
    if (paramSignUpEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramSignUpEvent);
    }
  }
  
  public void logStartCheckout(StartCheckoutEvent paramStartCheckoutEvent)
  {
    if (paramStartCheckoutEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramStartCheckoutEvent);
    }
  }
  
  public void onException(Crash.FatalException paramFatalException)
  {
    if (this.analyticsManager != null) {
      this.analyticsManager.onCrash(paramFatalException.getSessionId(), paramFatalException.getExceptionName());
    }
  }
  
  public void onException(Crash.LoggedException paramLoggedException)
  {
    if (this.analyticsManager != null) {
      this.analyticsManager.onError(paramLoggedException.getSessionId());
    }
  }
  
  @SuppressLint({"NewApi"})
  protected boolean onPreExecute()
  {
    try
    {
      Context localContext = getContext();
      PackageManager localPackageManager = localContext.getPackageManager();
      String str2 = localContext.getPackageName();
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str2, 0);
      String str3 = Integer.toString(localPackageInfo.versionCode);
      String str1;
      if (localPackageInfo.versionName == null)
      {
        str1 = "0.0";
        if (Build.VERSION.SDK_INT < 9) {
          break label101;
        }
      }
      label101:
      for (long l = localPackageInfo.firstInstallTime;; l = new File(localPackageManager.getApplicationInfo(str2, 0).sourceDir).lastModified())
      {
        this.analyticsManager = SessionAnalyticsManager.build(this, localContext, getIdManager(), str3, str1, l);
        this.analyticsManager.enable();
        return true;
        str1 = localPackageInfo.versionName;
        break;
      }
      return false;
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Answers", "Error retrieving app properties", localException);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\Answers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */