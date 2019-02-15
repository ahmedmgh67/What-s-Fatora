package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.events.TimeBasedFileRollOverRunnable;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class EnabledSessionAnalyticsManagerStrategy
  implements SessionAnalyticsManagerStrategy
{
  static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
  ApiKey apiKey = new ApiKey();
  private final Context context;
  boolean customEventsEnabled = true;
  EventFilter eventFilter = new KeepAllEventFilter();
  private final ScheduledExecutorService executorService;
  private final SessionAnalyticsFilesManager filesManager;
  FilesSender filesSender;
  private final HttpRequestFactory httpRequestFactory;
  private final Kit kit;
  final SessionEventMetadata metadata;
  boolean predefinedEventsEnabled = true;
  private final AtomicReference<ScheduledFuture<?>> rolloverFutureRef = new AtomicReference();
  volatile int rolloverIntervalSeconds = -1;
  
  public EnabledSessionAnalyticsManagerStrategy(Kit paramKit, Context paramContext, ScheduledExecutorService paramScheduledExecutorService, SessionAnalyticsFilesManager paramSessionAnalyticsFilesManager, HttpRequestFactory paramHttpRequestFactory, SessionEventMetadata paramSessionEventMetadata)
  {
    this.kit = paramKit;
    this.context = paramContext;
    this.executorService = paramScheduledExecutorService;
    this.filesManager = paramSessionAnalyticsFilesManager;
    this.httpRequestFactory = paramHttpRequestFactory;
    this.metadata = paramSessionEventMetadata;
  }
  
  public void cancelTimeBasedFileRollOver()
  {
    if (this.rolloverFutureRef.get() != null)
    {
      CommonUtils.logControlled(this.context, "Cancelling time-based rollover because no events are currently being generated.");
      ((ScheduledFuture)this.rolloverFutureRef.get()).cancel(false);
      this.rolloverFutureRef.set(null);
    }
  }
  
  public void deleteAllEvents()
  {
    this.filesManager.deleteAllEventsFiles();
  }
  
  public void processEvent(SessionEvent.Builder paramBuilder)
  {
    paramBuilder = paramBuilder.build(this.metadata);
    if ((!this.customEventsEnabled) && (SessionEvent.Type.CUSTOM.equals(paramBuilder.type)))
    {
      Fabric.getLogger().d("Answers", "Custom events tracking disabled - skipping event: " + paramBuilder);
      return;
    }
    if ((!this.predefinedEventsEnabled) && (SessionEvent.Type.PREDEFINED.equals(paramBuilder.type)))
    {
      Fabric.getLogger().d("Answers", "Predefined events tracking disabled - skipping event: " + paramBuilder);
      return;
    }
    if (this.eventFilter.skipEvent(paramBuilder))
    {
      Fabric.getLogger().d("Answers", "Skipping filtered event: " + paramBuilder);
      return;
    }
    try
    {
      this.filesManager.writeEvent(paramBuilder);
      scheduleTimeBasedRollOverIfNeeded();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Fabric.getLogger().e("Answers", "Failed to write event: " + paramBuilder, localIOException);
      }
    }
  }
  
  public boolean rollFileOver()
  {
    try
    {
      boolean bool = this.filesManager.rollFileOver();
      return bool;
    }
    catch (IOException localIOException)
    {
      CommonUtils.logControlledError(this.context, "Failed to roll file over.", localIOException);
    }
    return false;
  }
  
  void scheduleTimeBasedFileRollOver(long paramLong1, long paramLong2)
  {
    if (this.rolloverFutureRef.get() == null) {}
    for (int i = 1;; i = 0)
    {
      TimeBasedFileRollOverRunnable localTimeBasedFileRollOverRunnable;
      if (i != 0)
      {
        localTimeBasedFileRollOverRunnable = new TimeBasedFileRollOverRunnable(this.context, this);
        CommonUtils.logControlled(this.context, "Scheduling time based file roll over every " + paramLong2 + " seconds");
      }
      try
      {
        this.rolloverFutureRef.set(this.executorService.scheduleAtFixedRate(localTimeBasedFileRollOverRunnable, paramLong1, paramLong2, TimeUnit.SECONDS));
        return;
      }
      catch (RejectedExecutionException localRejectedExecutionException)
      {
        CommonUtils.logControlledError(this.context, "Failed to schedule time based file roll over", localRejectedExecutionException);
      }
    }
  }
  
  public void scheduleTimeBasedRollOverIfNeeded()
  {
    if (this.rolloverIntervalSeconds != -1) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0) {
        scheduleTimeBasedFileRollOver(this.rolloverIntervalSeconds, this.rolloverIntervalSeconds);
      }
      return;
    }
  }
  
  public void sendEvents()
  {
    if (this.filesSender == null)
    {
      CommonUtils.logControlled(this.context, "skipping files send because we don't yet know the target endpoint");
      return;
    }
    CommonUtils.logControlled(this.context, "Sending all files");
    int j = 0;
    List localList = this.filesManager.getBatchOfFilesToSend();
    for (;;)
    {
      k = j;
      i = j;
      try
      {
        if (localList.size() > 0)
        {
          i = j;
          CommonUtils.logControlled(this.context, String.format(Locale.US, "attempt to send batch of %d files", new Object[] { Integer.valueOf(localList.size()) }));
          i = j;
          boolean bool = this.filesSender.send(localList);
          k = j;
          if (bool)
          {
            i = j;
            k = j + localList.size();
            i = k;
            this.filesManager.deleteSentFiles(localList);
          }
          if (bool) {
            break label147;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          CommonUtils.logControlledError(this.context, "Failed to send batch of analytics files to server: " + localException.getMessage(), localException);
          k = i;
        }
      }
      if (k != 0) {
        break;
      }
      this.filesManager.deleteOldestInRollOverIfOverMax();
      return;
      label147:
      i = k;
      localList = this.filesManager.getBatchOfFilesToSend();
      j = k;
    }
  }
  
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString)
  {
    this.filesSender = AnswersRetryFilesSender.build(new SessionAnalyticsFilesSender(this.kit, paramString, paramAnalyticsSettingsData.analyticsURL, this.httpRequestFactory, this.apiKey.getValue(this.context)));
    this.filesManager.setAnalyticsSettingsData(paramAnalyticsSettingsData);
    this.customEventsEnabled = paramAnalyticsSettingsData.trackCustomEvents;
    Logger localLogger = Fabric.getLogger();
    StringBuilder localStringBuilder = new StringBuilder().append("Custom event tracking ");
    if (this.customEventsEnabled)
    {
      paramString = "enabled";
      localLogger.d("Answers", paramString);
      this.predefinedEventsEnabled = paramAnalyticsSettingsData.trackPredefinedEvents;
      localLogger = Fabric.getLogger();
      localStringBuilder = new StringBuilder().append("Predefined event tracking ");
      if (!this.predefinedEventsEnabled) {
        break label218;
      }
    }
    label218:
    for (paramString = "enabled";; paramString = "disabled")
    {
      localLogger.d("Answers", paramString);
      if (paramAnalyticsSettingsData.samplingRate > 1)
      {
        Fabric.getLogger().d("Answers", "Event sampling enabled");
        this.eventFilter = new SamplingEventFilter(paramAnalyticsSettingsData.samplingRate);
      }
      this.rolloverIntervalSeconds = paramAnalyticsSettingsData.flushIntervalSeconds;
      scheduleTimeBasedFileRollOver(0L, this.rolloverIntervalSeconds);
      return;
      paramString = "disabled";
      break;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\EnabledSessionAnalyticsManagerStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */