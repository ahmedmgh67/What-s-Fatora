package com.crashlytics.android.answers;

import android.app.Activity;
import android.os.Bundle;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;

class AnswersLifecycleCallbacks
  extends ActivityLifecycleManager.Callbacks
{
  private final SessionAnalyticsManager analyticsManager;
  private final BackgroundManager backgroundManager;
  
  public AnswersLifecycleCallbacks(SessionAnalyticsManager paramSessionAnalyticsManager, BackgroundManager paramBackgroundManager)
  {
    this.analyticsManager = paramSessionAnalyticsManager;
    this.backgroundManager = paramBackgroundManager;
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityDestroyed(Activity paramActivity) {}
  
  public void onActivityPaused(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.PAUSE);
    this.backgroundManager.onActivityPaused();
  }
  
  public void onActivityResumed(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.RESUME);
    this.backgroundManager.onActivityResumed();
  }
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.START);
  }
  
  public void onActivityStopped(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.STOP);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\AnswersLifecycleCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */