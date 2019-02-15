package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

class AnswersPreferenceManager
{
  static final String PREFKEY_ANALYTICS_LAUNCHED = "analytics_launched";
  static final String PREF_STORE_NAME = "settings";
  private final PreferenceStore prefStore;
  
  AnswersPreferenceManager(PreferenceStore paramPreferenceStore)
  {
    this.prefStore = paramPreferenceStore;
  }
  
  public static AnswersPreferenceManager build(Context paramContext)
  {
    return new AnswersPreferenceManager(new PreferenceStoreImpl(paramContext, "settings"));
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public boolean hasAnalyticsLaunched()
  {
    return this.prefStore.get().getBoolean("analytics_launched", false);
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public void setAnalyticsLaunched()
  {
    this.prefStore.save(this.prefStore.edit().putBoolean("analytics_launched", true));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\AnswersPreferenceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */