package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.util.Locale;

public enum zzbrj
  implements zzbrk
{
  private zzbrj() {}
  
  private static String zza(char paramChar, String paramString, int paramInt)
  {
    if (paramInt < paramString.length())
    {
      paramString = String.valueOf(paramString.substring(paramInt));
      return String.valueOf(paramString).length() + 1 + paramChar + paramString;
    }
    return String.valueOf(paramChar);
  }
  
  private static String zzaz(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString1.length())
    {
      char c = paramString1.charAt(i);
      if ((Character.isUpperCase(c)) && (localStringBuilder.length() != 0)) {
        localStringBuilder.append(paramString2);
      }
      localStringBuilder.append(c);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static String zzjQ(String paramString)
  {
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    char c = paramString.charAt(0);
    String str;
    if ((i >= paramString.length() - 1) || (Character.isLetter(c)))
    {
      if (i != paramString.length()) {
        break label70;
      }
      str = localStringBuilder.toString();
    }
    label70:
    do
    {
      return str;
      localStringBuilder.append(c);
      i += 1;
      c = paramString.charAt(i);
      break;
      str = paramString;
    } while (Character.isUpperCase(c));
    return zza(Character.toUpperCase(c), paramString, i + 1);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */