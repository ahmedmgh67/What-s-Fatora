package com.google.api.client.extensions.android;

import android.os.Build.VERSION;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;

@Beta
public class AndroidUtils
{
  public static void checkMinimumSdkLevel(int paramInt)
  {
    Preconditions.checkArgument(isMinimumSdkLevel(paramInt), "running on Android SDK level %s but requires minimum %s", new Object[] { Integer.valueOf(Build.VERSION.SDK_INT), Integer.valueOf(paramInt) });
  }
  
  public static boolean isMinimumSdkLevel(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\extensions\android\AndroidUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */