package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

@SuppressLint({"CommitPrefEdits"})
class PreferenceManager
{
  static final String PREF_ALWAYS_SEND_REPORTS_KEY = "always_send_reports_opt_in";
  private static final String PREF_MIGRATION_COMPLETE = "preferences_migration_complete";
  private static final boolean SHOULD_ALWAYS_SEND_REPORTS_DEFAULT = false;
  private final PreferenceStore preferenceStore;
  
  public PreferenceManager(PreferenceStore paramPreferenceStore)
  {
    this.preferenceStore = paramPreferenceStore;
  }
  
  public static PreferenceManager create(PreferenceStore paramPreferenceStore, CrashlyticsCore paramCrashlyticsCore)
  {
    if (!paramPreferenceStore.get().getBoolean("preferences_migration_complete", false))
    {
      paramCrashlyticsCore = new PreferenceStoreImpl(paramCrashlyticsCore);
      if ((paramPreferenceStore.get().contains("always_send_reports_opt_in")) || (!paramCrashlyticsCore.get().contains("always_send_reports_opt_in"))) {
        break label130;
      }
    }
    label130:
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        boolean bool = paramCrashlyticsCore.get().getBoolean("always_send_reports_opt_in", false);
        paramPreferenceStore.save(paramPreferenceStore.edit().putBoolean("always_send_reports_opt_in", bool));
      }
      paramPreferenceStore.save(paramPreferenceStore.edit().putBoolean("preferences_migration_complete", true));
      return new PreferenceManager(paramPreferenceStore);
    }
  }
  
  void setShouldAlwaysSendReports(boolean paramBoolean)
  {
    this.preferenceStore.save(this.preferenceStore.edit().putBoolean("always_send_reports_opt_in", paramBoolean));
  }
  
  boolean shouldAlwaysSendReports()
  {
    return this.preferenceStore.get().getBoolean("always_send_reports_opt_in", false);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\PreferenceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */