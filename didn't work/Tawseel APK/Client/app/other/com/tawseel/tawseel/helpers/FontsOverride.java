package com.tawseel.tawseel.helpers;

import android.content.Context;
import android.graphics.Typeface;
import java.lang.reflect.Field;

public final class FontsOverride
{
  protected static void replaceFont(String paramString, Typeface paramTypeface)
  {
    try
    {
      paramString = Typeface.class.getDeclaredField(paramString);
      paramString.setAccessible(true);
      paramString.set(null, paramTypeface);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void setDefaultFont(Context paramContext, String paramString1, String paramString2)
  {
    replaceFont(paramString1, Typeface.createFromAsset(paramContext.getAssets(), paramString2));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\helpers\FontsOverride.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */