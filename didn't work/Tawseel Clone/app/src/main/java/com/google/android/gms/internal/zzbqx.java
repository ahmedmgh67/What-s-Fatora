package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class zzbqx
{
  @NonNull
  public static String zzjJ(@Nullable String paramString)
    throws UnsupportedEncodingException
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return zzjK(URLEncoder.encode(paramString, "UTF8"));
  }
  
  @NonNull
  public static String zzjK(@NonNull String paramString)
  {
    zzac.zzw(paramString);
    return paramString.replace("%2F", "/");
  }
  
  @NonNull
  public static String zzjL(@NonNull String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      localObject = "";
    }
    do
    {
      return (String)localObject;
      if ((paramString.startsWith("/")) || (paramString.endsWith("/"))) {
        break;
      }
      localObject = paramString;
    } while (!paramString.contains("//"));
    Object localObject = new StringBuilder();
    paramString = paramString.split("/");
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      CharSequence localCharSequence = paramString[i];
      if (!TextUtils.isEmpty(localCharSequence))
      {
        if (((StringBuilder)localObject).length() <= 0) {
          break label105;
        }
        ((StringBuilder)localObject).append("/").append(localCharSequence);
      }
      for (;;)
      {
        i += 1;
        break;
        label105:
        ((StringBuilder)localObject).append(localCharSequence);
      }
    }
    return ((StringBuilder)localObject).toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */