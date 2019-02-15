package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class SessionAnalyticsManager
  implements BackgroundManager.Listener
{
  static final String EXECUTOR_SERVICE = "Answers Events Handler";
  static final String ON_CRASH_ERROR_MSG = "onCrash called from main thread!!!";
  final BackgroundManager backgroundManager;
  final AnswersEventsHandler eventsHandler;
  private final long installedAt;
  final ActivityLifecycleManager lifecycleManager;
  final AnswersPreferenceManager preferenceManager;
  
  SessionAnalyticsManager(AnswersEventsHandler paramAnswersEventsHandler, ActivityLifecycleManager paramActivityLifecycleManager, BackgroundManager paramBackgroundManager, AnswersPreferenceManager paramAnswersPreferenceManager, long paramLong)
  {
    this.eventsHandler = paramAnswersEventsHandler;
    this.lifecycleManager = paramActivityLifecycleManager;
    this.backgroundManager = paramBackgroundManager;
    this.preferenceManager = paramAnswersPreferenceManager;
    this.installedAt = paramLong;
  }
  
  public static SessionAnalyticsManager build(Kit paramKit, Context paramContext, IdManager paramIdManager, String paramString1, String paramString2, long paramLong)
  {
    paramIdManager = new SessionMetadataCollector(paramContext, paramIdManager, paramString1, paramString2);
    paramString1 = new AnswersFilesManagerProvider(paramContext, new FileStoreImpl(paramKit));
    paramString2 = new DefaultHttpRequestFactory(Fabric.getLogger());
    ActivityLifecycleManager localActivityLifecycleManager = new ActivityLifecycleManager(paramContext);
    ScheduledExecutorService localScheduledExecutorService = ExecutorUtils.buildSingleThreadScheduledExecutorService("Answers Events Handler");
    BackgroundManager localBackgroundManager = new BackgroundManager(localScheduledExecutorService);
    return new SessionAnalyticsManager(new AnswersEventsHandler(paramKit, paramContext, paramString1, paramIdManager, paramString2, localScheduledExecutorService), localActivityLifecycleManager, localBackgroundManager, AnswersPreferenceManager.build(paramContext), paramLong);
  }
  
  public void disable()
  {
    this.lifecycleManager.resetCallbacks();
    this.eventsHandler.disable();
  }
  
  public void enable()
  {
    this.eventsHandler.enable();
    this.lifecycleManager.registerCallbacks(new AnswersLifecycleCallbacks(this, this.backgroundManager));
    this.backgroundManager.registerListener(this);
    if (isFirstLaunch())
    {
      onInstall(this.installedAt);
      this.preferenceManager.setAnalyticsLaunched();
    }
  }
  
  boolean isFirstLaunch()
  {
    return !this.preferenceManager.hasAnalyticsLaunched();
  }
  
  public void onBackground()
  {
    Fabric.getLogger().d("Answers", "Flush events when app is backgrounded");
    this.eventsHandler.flushEvents();
  }
  
  public void onCrash(String paramString1, String paramString2)
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      throw new IllegalStateException("onCrash called from main thread!!!");
    }
    Fabric.getLogger().d("Answers", "Logged crash");
    this.eventsHandler.processEventSync(SessionEvent.crashEventBuilder(paramString1, paramString2));
  }
  
  public void onCustom(CustomEvent paramCustomEvent)
  {
    Fabric.getLogger().d("Answers", "Logged custom event: " + paramCustomEvent);
    this.eventsHandler.processEventAsync(SessionEvent.customEventBuilder(paramCustomEvent));
  }
  
  public void onError(String paramString) {}
  
  public void onInstall(long paramLong)
  {
    Fabric.getLogger().d("Answers", "Logged install");
    this.eventsHandler.processEventAsyncAndFlush(SessionEvent.installEventBuilder(paramLong));
  }
  
  public void onLifecycle(Activity paramActivity, SessionEvent.Type paramType)
  {
    Fabric.getLogger().d("Answers", "Logged lifecycle event: " + paramType.name());
    this.eventsHandler.processEventAsync(SessionEvent.lifecycleEventBuilder(paramType, paramActivity));
  }
  
  public void onPredefined(PredefinedEvent paramPredefinedEvent)
  {
    Fabric.getLogger().d("Answers", "Logged predefined event: " + paramPredefinedEvent);
    this.eventsHandler.processEventAsync(SessionEvent.predefinedEventBuilder(paramPredefinedEvent));
  }
  
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString)
  {
    this.backgroundManager.setFlushOnBackground(paramAnalyticsSettingsData.flushOnBackground);
    this.eventsHandler.setAnalyticsSettingsData(paramAnalyticsSettingsData, paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\SessionAnalyticsManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */