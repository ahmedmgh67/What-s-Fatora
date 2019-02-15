package com.crashlytics.android.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

class ManifestUnityVersionProvider
  implements UnityVersionProvider
{
  static final String FABRIC_UNITY_CRASHLYTICS_VERSION_KEY = "io.fabric.unity.crashlytics.version";
  private final Context context;
  private final String packageName;
  
  public ManifestUnityVersionProvider(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.packageName = paramString;
  }
  
  public String getUnityVersion()
  {
    String str = null;
    Object localObject = this.context.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationInfo(this.packageName, 128).metaData;
      if (localObject != null) {
        str = ((Bundle)localObject).getString("io.fabric.unity.crashlytics.version");
      }
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\ManifestUnityVersionProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */