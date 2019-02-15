package com.google.android.gms.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.GetTokenResult;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzbrb
{
  public static boolean equals(@Nullable Object paramObject1, @Nullable Object paramObject2)
  {
    return zzaa.equal(paramObject1, paramObject2);
  }
  
  @Nullable
  public static Uri zzg(@NonNull FirebaseApp paramFirebaseApp, @Nullable String paramString)
    throws UnsupportedEncodingException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (paramString.toLowerCase().startsWith("gs://"))
    {
      paramFirebaseApp = String.valueOf(zzbqx.zzjJ(zzbqx.zzjL(paramString.substring(5))));
      if (paramFirebaseApp.length() != 0) {}
      for (paramFirebaseApp = "gs://".concat(paramFirebaseApp);; paramFirebaseApp = new String("gs://")) {
        return Uri.parse(paramFirebaseApp);
      }
    }
    paramString = Uri.parse(paramString);
    String str = paramString.getScheme().toLowerCase();
    if ((equals(str, "http")) || (equals(str, "https")))
    {
      str = paramString.getAuthority().toLowerCase();
      for (;;)
      {
        int i;
        try
        {
          i = str.indexOf(zzh(paramFirebaseApp));
          paramFirebaseApp = zzbqx.zzjK(paramString.getEncodedPath());
          if ((i != 0) || (!paramFirebaseApp.startsWith("/"))) {
            break label269;
          }
          i = paramFirebaseApp.indexOf("/b/", 0);
          int j = paramFirebaseApp.indexOf("/", i + 3);
          int k = paramFirebaseApp.indexOf("/o/", 0);
          if ((i == -1) || (j == -1)) {
            break label251;
          }
          paramString = paramFirebaseApp.substring(i + 3, j);
          if (k != -1)
          {
            paramFirebaseApp = paramFirebaseApp.substring(k + 3);
            zzac.zzh(paramString, "No bucket specified");
            return new Uri.Builder().scheme("gs").authority(paramString).encodedPath(paramFirebaseApp).build();
          }
        }
        catch (RemoteException paramFirebaseApp)
        {
          throw new UnsupportedEncodingException("Could not parse Url because the Storage network layer did not load");
        }
        paramFirebaseApp = "";
        continue;
        label251:
        Log.w("StorageUtil", "Only URLs to firebasestorage.googleapis.com are supported.");
        throw new IllegalArgumentException("Only URLs to firebasestorage.googleapis.com are supported.");
        label269:
        if (i <= 1) {
          break;
        }
        paramString = paramString.getAuthority().substring(0, i - 1);
      }
      Log.w("StorageUtil", "Only URLs to firebasestorage.googleapis.com are supported.");
      throw new IllegalArgumentException("Only URLs to firebasestorage.googleapis.com are supported.");
    }
    paramFirebaseApp = String.valueOf(str);
    if (paramFirebaseApp.length() != 0) {}
    for (paramFirebaseApp = "FirebaseStorage is unable to support the scheme:".concat(paramFirebaseApp);; paramFirebaseApp = new String("FirebaseStorage is unable to support the scheme:"))
    {
      Log.w("StorageUtil", paramFirebaseApp);
      throw new IllegalArgumentException("Uri scheme");
    }
  }
  
  private static String zzh(FirebaseApp paramFirebaseApp)
    throws RemoteException
  {
    return zzbre.zzj(paramFirebaseApp).zzabp();
  }
  
  @Nullable
  public static String zzi(FirebaseApp paramFirebaseApp)
  {
    paramFirebaseApp = paramFirebaseApp.getToken(false);
    try
    {
      paramFirebaseApp = ((GetTokenResult)Tasks.await(paramFirebaseApp, 30000L, TimeUnit.MILLISECONDS)).getToken();
      if (!TextUtils.isEmpty(paramFirebaseApp)) {
        return paramFirebaseApp;
      }
      Log.w("StorageUtil", "no auth token for request");
    }
    catch (InterruptedException paramFirebaseApp)
    {
      for (;;)
      {
        paramFirebaseApp = String.valueOf(paramFirebaseApp);
        Log.e("StorageUtil", String.valueOf(paramFirebaseApp).length() + 20 + "error getting token " + paramFirebaseApp);
      }
    }
    catch (ExecutionException paramFirebaseApp)
    {
      for (;;) {}
    }
    catch (TimeoutException paramFirebaseApp)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static long zzjM(@Nullable String paramString)
  {
    if (paramString == null) {
      return 0L;
    }
    paramString = paramString.replaceAll("Z$", "-0000");
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
    try
    {
      long l = localSimpleDateFormat.parse(paramString).getTime();
      return l;
    }
    catch (ParseException localParseException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {}
    }
    for (paramString = "unable to parse datetime:".concat(paramString);; paramString = new String("unable to parse datetime:"))
    {
      Log.w("StorageUtil", paramString, localParseException);
      return 0L;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */