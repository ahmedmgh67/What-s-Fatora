package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Activity;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;
import java.util.concurrent.ExecutorService;

@TargetApi(14)
class ActivityLifecycleCheckForUpdatesController
  extends AbstractCheckForUpdatesController
{
  private final ActivityLifecycleManager.Callbacks callbacks = new ActivityLifecycleManager.Callbacks()
  {
    public void onActivityStarted(Activity paramAnonymousActivity)
    {
      if (ActivityLifecycleCheckForUpdatesController.this.signalExternallyReady()) {
        ActivityLifecycleCheckForUpdatesController.this.executorService.submit(new Runnable()
        {
          public void run()
          {
            ActivityLifecycleCheckForUpdatesController.this.checkForUpdates();
          }
        });
      }
    }
  };
  private final ExecutorService executorService;
  
  public ActivityLifecycleCheckForUpdatesController(ActivityLifecycleManager paramActivityLifecycleManager, ExecutorService paramExecutorService)
  {
    this.executorService = paramExecutorService;
    paramActivityLifecycleManager.registerCallbacks(this.callbacks);
  }
  
  public boolean isActivityLifecycleTriggered()
  {
    return true;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\beta\ActivityLifecycleCheckForUpdatesController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */