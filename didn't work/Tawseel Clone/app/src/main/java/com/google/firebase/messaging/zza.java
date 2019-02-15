package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzs;
import com.google.firebase.iid.zzg;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

class zza
{
  static zza zzcjm;
  private final Context mContext;
  private Bundle zzaCt;
  private final AtomicInteger zzcjn = new AtomicInteger((int)SystemClock.elapsedRealtime());
  
  private zza(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }
  
  static boolean zzE(Bundle paramBundle)
  {
    return ("1".equals(zzf(paramBundle, "gcm.n.e"))) || (zzf(paramBundle, "gcm.n.icon") != null);
  }
  
  private int zzGc()
  {
    return this.zzcjn.incrementAndGet();
  }
  
  private Notification zzH(Bundle paramBundle)
  {
    String str1 = zzg(paramBundle, "gcm.n.title");
    String str2 = zzg(paramBundle, "gcm.n.body");
    int i = zzeF(zzf(paramBundle, "gcm.n.icon"));
    Integer localInteger = zzjE(zzf(paramBundle, "gcm.n.color"));
    Uri localUri = zzeG(zzU(paramBundle));
    PendingIntent localPendingIntent3 = zzI(paramBundle);
    PendingIntent localPendingIntent2 = null;
    PendingIntent localPendingIntent1 = localPendingIntent3;
    if (FirebaseMessagingService.zzW(paramBundle))
    {
      localPendingIntent1 = zza(paramBundle, localPendingIntent3);
      localPendingIntent2 = zzV(paramBundle);
    }
    paramBundle = new NotificationCompat.Builder(this.mContext).setAutoCancel(true).setSmallIcon(i);
    if (!TextUtils.isEmpty(str1)) {
      paramBundle.setContentTitle(str1);
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(str2))
      {
        paramBundle.setContentText(str2);
        paramBundle.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
      }
      if (localInteger != null) {
        paramBundle.setColor(localInteger.intValue());
      }
      if (localUri != null) {
        paramBundle.setSound(localUri);
      }
      if (localPendingIntent1 != null) {
        paramBundle.setContentIntent(localPendingIntent1);
      }
      if (localPendingIntent2 != null) {
        paramBundle.setDeleteIntent(localPendingIntent2);
      }
      return paramBundle.build();
      paramBundle.setContentTitle(this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager()));
    }
  }
  
  private PendingIntent zzI(Bundle paramBundle)
  {
    Object localObject = zzf(paramBundle, "gcm.n.click_action");
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = new Intent((String)localObject);
      ((Intent)localObject).setPackage(this.mContext.getPackageName());
      ((Intent)localObject).setFlags(268435456);
    }
    label164:
    for (;;)
    {
      paramBundle = new Bundle(paramBundle);
      FirebaseMessagingService.zzD(paramBundle);
      ((Intent)localObject).putExtras(paramBundle);
      paramBundle = paramBundle.keySet().iterator();
      while (paramBundle.hasNext())
      {
        String str = (String)paramBundle.next();
        if ((str.startsWith("gcm.n.")) || (str.startsWith("gcm.notification.")))
        {
          ((Intent)localObject).removeExtra(str);
          continue;
          localObject = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
          if (localObject != null) {
            break label164;
          }
          Log.w("FirebaseMessaging", "No activity found to launch app");
          return null;
        }
      }
      return PendingIntent.getActivity(this.mContext, zzGc(), (Intent)localObject, 1073741824);
    }
  }
  
  static String zzU(Bundle paramBundle)
  {
    String str2 = zzf(paramBundle, "gcm.n.sound2");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = zzf(paramBundle, "gcm.n.sound");
    }
    return str1;
  }
  
  private PendingIntent zzV(Bundle paramBundle)
  {
    Intent localIntent = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
    zza(localIntent, paramBundle);
    return zzg.zzb(this.mContext, zzGc(), localIntent, 1073741824);
  }
  
  private PendingIntent zza(Bundle paramBundle, PendingIntent paramPendingIntent)
  {
    Intent localIntent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
    zza(localIntent, paramBundle);
    localIntent.putExtra("pending_intent", paramPendingIntent);
    return zzg.zzb(this.mContext, zzGc(), localIntent, 1073741824);
  }
  
  private void zza(Intent paramIntent, Bundle paramBundle)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str.startsWith("google.c.a.")) || (str.equals("from"))) {
        paramIntent.putExtra(str, paramBundle.getString(str));
      }
    }
  }
  
  private void zza(String paramString, Notification paramNotification)
  {
    if (Log.isLoggable("FirebaseMessaging", 3)) {
      Log.d("FirebaseMessaging", "Showing notification");
    }
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
    {
      long l = SystemClock.uptimeMillis();
      str = 37 + "GCM-Notification:" + l;
    }
    localNotificationManager.notify(str, 0, paramNotification);
  }
  
  private Bundle zzaar()
  {
    if (this.zzaCt != null) {
      return this.zzaCt;
    }
    Object localObject = null;
    try
    {
      ApplicationInfo localApplicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
      localObject = localApplicationInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    if ((localObject != null) && (((ApplicationInfo)localObject).metaData != null))
    {
      this.zzaCt = ((ApplicationInfo)localObject).metaData;
      return this.zzaCt;
    }
    return Bundle.EMPTY;
  }
  
  static boolean zzbc(Context paramContext)
  {
    if (((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {}
    int i;
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      while (!paramContext.hasNext())
      {
        do
        {
          return false;
          if (!zzs.zzyH()) {
            SystemClock.sleep(10L);
          }
          i = Process.myPid();
          paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
        } while (paramContext == null);
        paramContext = paramContext.iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    } while (localRunningAppProcessInfo.pid != i);
    if (localRunningAppProcessInfo.importance == 100) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static zza zzca(Context paramContext)
  {
    try
    {
      if (zzcjm == null) {
        zzcjm = new zza(paramContext);
      }
      paramContext = zzcjm;
      return paramContext;
    }
    finally {}
  }
  
  private static String zzeE(String paramString)
  {
    return paramString.substring("gcm.n.".length());
  }
  
  private int zzeF(String paramString)
  {
    Resources localResources;
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      localResources = this.mContext.getResources();
      i = localResources.getIdentifier(paramString, "drawable", this.mContext.getPackageName());
      if (i == 0) {}
    }
    int j;
    do
    {
      do
      {
        return i;
        j = localResources.getIdentifier(paramString, "mipmap", this.mContext.getPackageName());
        i = j;
      } while (j != 0);
      Log.w("FirebaseMessaging", String.valueOf(paramString).length() + 61 + "Icon resource " + paramString + " not found. Notification will use default icon.");
      i = zzaar().getInt("com.google.firebase.messaging.default_notification_icon", 0);
      j = i;
      if (i == 0) {
        j = this.mContext.getApplicationInfo().icon;
      }
      i = j;
    } while (j != 0);
    return 17301651;
  }
  
  private Uri zzeG(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if ((!"default".equals(paramString)) && (this.mContext.getResources().getIdentifier(paramString, "raw", this.mContext.getPackageName()) != 0))
    {
      String str1 = String.valueOf("android.resource://");
      String str2 = String.valueOf(this.mContext.getPackageName());
      return Uri.parse(String.valueOf(str1).length() + 5 + String.valueOf(str2).length() + String.valueOf(paramString).length() + str1 + str2 + "/raw/" + paramString);
    }
    return RingtoneManager.getDefaultUri(2);
  }
  
  static String zzf(Bundle paramBundle, String paramString)
  {
    String str2 = paramBundle.getString(paramString);
    String str1 = str2;
    if (str2 == null) {
      str1 = paramBundle.getString(paramString.replace("gcm.n.", "gcm.notification."));
    }
    return str1;
  }
  
  private String zzg(Bundle paramBundle, String paramString)
  {
    String str = zzf(paramBundle, paramString);
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    str = zzh(paramBundle, paramString);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    Resources localResources = this.mContext.getResources();
    int i = localResources.getIdentifier(str, "string", this.mContext.getPackageName());
    if (i == 0)
    {
      paramBundle = String.valueOf(paramString);
      paramString = String.valueOf("_loc_key");
      if (paramString.length() != 0) {}
      for (paramBundle = paramBundle.concat(paramString);; paramBundle = new String(paramBundle))
      {
        paramBundle = String.valueOf(zzeE(paramBundle));
        Log.w("FirebaseMessaging", String.valueOf(paramBundle).length() + 49 + String.valueOf(str).length() + paramBundle + " resource not found: " + str + " Default value will be used.");
        return null;
      }
    }
    paramBundle = zzi(paramBundle, paramString);
    if (paramBundle == null) {
      return localResources.getString(i);
    }
    try
    {
      paramString = localResources.getString(i, paramBundle);
      return paramString;
    }
    catch (MissingFormatArgumentException paramString)
    {
      paramBundle = String.valueOf(Arrays.toString(paramBundle));
      Log.w("FirebaseMessaging", String.valueOf(str).length() + 58 + String.valueOf(paramBundle).length() + "Missing format argument for " + str + ": " + paramBundle + " Default value will be used.", paramString);
    }
    return null;
  }
  
  static String zzh(Bundle paramBundle, String paramString)
  {
    paramString = String.valueOf(paramString);
    String str = String.valueOf("_loc_key");
    if (str.length() != 0) {}
    for (paramString = paramString.concat(str);; paramString = new String(paramString)) {
      return zzf(paramBundle, paramString);
    }
  }
  
  static Object[] zzi(Bundle paramBundle, String paramString)
  {
    Object localObject = String.valueOf(paramString);
    String str = String.valueOf("_loc_args");
    if (str.length() != 0)
    {
      localObject = ((String)localObject).concat(str);
      str = zzf(paramBundle, (String)localObject);
      if (!TextUtils.isEmpty(str)) {
        break label59;
      }
      paramBundle = null;
    }
    for (;;)
    {
      return paramBundle;
      localObject = new String((String)localObject);
      break;
      try
      {
        label59:
        JSONArray localJSONArray = new JSONArray(str);
        localObject = new String[localJSONArray.length()];
        int i = 0;
        for (;;)
        {
          paramBundle = (Bundle)localObject;
          if (i >= localObject.length) {
            break;
          }
          localObject[i] = localJSONArray.opt(i);
          i += 1;
        }
        paramBundle = paramBundle.concat(paramString);
      }
      catch (JSONException paramBundle)
      {
        paramBundle = String.valueOf(paramString);
        paramString = String.valueOf("_loc_args");
        if (paramString.length() == 0) {}
      }
    }
    for (;;)
    {
      paramBundle = String.valueOf(zzeE(paramBundle));
      Log.w("FirebaseMessaging", String.valueOf(paramBundle).length() + 41 + String.valueOf(str).length() + "Malformed " + paramBundle + ": " + str + "  Default value will be used.");
      return null;
      paramBundle = new String(paramBundle);
    }
  }
  
  private Integer zzjE(String paramString)
  {
    if (Build.VERSION.SDK_INT < 21) {}
    int i;
    do
    {
      return null;
      if (!TextUtils.isEmpty(paramString)) {
        try
        {
          i = Color.parseColor(paramString);
          return Integer.valueOf(i);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          Log.w("FirebaseMessaging", String.valueOf(paramString).length() + 54 + "Color " + paramString + " not valid. Notification will use default color.");
        }
      }
      i = zzaar().getInt("com.google.firebase.messaging.default_notification_color", 0);
    } while (i == 0);
    try
    {
      i = ContextCompat.getColor(this.mContext, i);
      return Integer.valueOf(i);
    }
    catch (Resources.NotFoundException paramString)
    {
      Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
    }
    return null;
  }
  
  void zzT(Bundle paramBundle)
  {
    Notification localNotification = zzH(paramBundle);
    zza(zzf(paramBundle, "gcm.n.tag"), localNotification);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\messaging\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */