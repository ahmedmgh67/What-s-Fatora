package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.io.IOException;

class DisabledSessionAnalyticsManagerStrategy
  implements SessionAnalyticsManagerStrategy
{
  public void cancelTimeBasedFileRollOver() {}
  
  public void deleteAllEvents() {}
  
  public void processEvent(SessionEvent.Builder paramBuilder) {}
  
  public boolean rollFileOver()
    throws IOException
  {
    return false;
  }
  
  public void scheduleTimeBasedRollOverIfNeeded() {}
  
  public void sendEvents() {}
  
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString) {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\DisabledSessionAnalyticsManagerStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */