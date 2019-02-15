package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.reflect.Method;

public class zzbji
{
  @Nullable
  public static String getProperty(@NonNull String paramString)
  {
    try
    {
      paramString = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[] { String.class }).invoke(null, new Object[] { paramString });
      if ((paramString != null) && (String.class.isAssignableFrom(paramString.getClass())))
      {
        paramString = (String)paramString;
        return paramString;
      }
    }
    catch (Exception paramString) {}
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbji.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */