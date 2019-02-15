package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.events.FileRollOverManager;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

abstract interface SessionAnalyticsManagerStrategy
  extends FileRollOverManager
{
  public abstract void deleteAllEvents();
  
  public abstract void processEvent(SessionEvent.Builder paramBuilder);
  
  public abstract void sendEvents();
  
  public abstract void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\SessionAnalyticsManagerStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */