package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzg;
import java.util.Iterator;
import java.util.Set;

public class FirebaseMessagingService
  extends com.google.firebase.iid.zzb
{
  static void zzD(Bundle paramBundle)
  {
    paramBundle = paramBundle.keySet().iterator();
    while (paramBundle.hasNext())
    {
      String str = (String)paramBundle.next();
      if ((str != null) && (str.startsWith("google.c."))) {
        paramBundle.remove();
      }
    }
  }
  
  private void zzK(Intent paramIntent)
  {
    PendingIntent localPendingIntent = (PendingIntent)paramIntent.getParcelableExtra("pending_intent");
    if (localPendingIntent != null) {}
    try
    {
      localPendingIntent.send();
      if (zzW(paramIntent.getExtras())) {
        zzb.zzj(this, paramIntent);
      }
      return;
    }
    catch (PendingIntent.CanceledException localCanceledException)
    {
      for (;;)
      {
        Log.e("FirebaseMessaging", "Notification pending intent canceled");
      }
    }
  }
  
  static boolean zzW(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return false;
    }
    return "1".equals(paramBundle.getString("google.c.a.e"));
  }
  
  private void zzn(Intent paramIntent)
  {
    String str2 = paramIntent.getStringExtra("message_type");
    String str1 = str2;
    if (str2 == null) {
      str1 = "gcm";
    }
    int i = -1;
    switch (str1.hashCode())
    {
    default: 
      switch (i)
      {
      default: 
        paramIntent = String.valueOf(str1);
        if (paramIntent.length() == 0) {}
        break;
      }
      break;
    }
    for (paramIntent = "Received message with unknown type: ".concat(paramIntent);; paramIntent = new String("Received message with unknown type: "))
    {
      Log.w("FirebaseMessaging", paramIntent);
      return;
      if (!str1.equals("gcm")) {
        break;
      }
      i = 0;
      break;
      if (!str1.equals("deleted_messages")) {
        break;
      }
      i = 1;
      break;
      if (!str1.equals("send_event")) {
        break;
      }
      i = 2;
      break;
      if (!str1.equals("send_error")) {
        break;
      }
      i = 3;
      break;
      if (zzW(paramIntent.getExtras())) {
        zzb.zzi(this, paramIntent);
      }
      zzo(paramIntent);
      return;
      onDeletedMessages();
      return;
      onMessageSent(paramIntent.getStringExtra("google.message_id"));
      return;
      onSendError(zzp(paramIntent), new SendException(paramIntent.getStringExtra("error")));
      return;
    }
  }
  
  private void zzo(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    localBundle.remove("android.support.content.wakelockid");
    if (zza.zzE(localBundle))
    {
      if (!zza.zzbc(this))
      {
        zza.zzca(this).zzT(localBundle);
        return;
      }
      if (zzW(localBundle)) {
        zzb.zzl(this, paramIntent);
      }
    }
    onMessageReceived(new RemoteMessage(localBundle));
  }
  
  private String zzp(Intent paramIntent)
  {
    String str2 = paramIntent.getStringExtra("google.message_id");
    String str1 = str2;
    if (str2 == null) {
      str1 = paramIntent.getStringExtra("message_id");
    }
    return str1;
  }
  
  @WorkerThread
  public void onDeletedMessages() {}
  
  @WorkerThread
  public void onMessageReceived(RemoteMessage paramRemoteMessage) {}
  
  @WorkerThread
  public void onMessageSent(String paramString) {}
  
  @WorkerThread
  public void onSendError(String paramString, Exception paramException) {}
  
  protected Intent zzF(Intent paramIntent)
  {
    return zzg.zzaaj().zzaal();
  }
  
  public boolean zzH(Intent paramIntent)
  {
    if ("com.google.firebase.messaging.NOTIFICATION_OPEN".equals(paramIntent.getAction()))
    {
      zzK(paramIntent);
      return true;
    }
    return false;
  }
  
  public void zzm(Intent paramIntent)
  {
    String str2 = paramIntent.getAction();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    int i;
    switch (str1.hashCode())
    {
    default: 
      i = -1;
      switch (i)
      {
      default: 
        label50:
        paramIntent = String.valueOf(paramIntent.getAction());
        if (paramIntent.length() == 0) {}
        break;
      }
      break;
    }
    for (paramIntent = "Unknown intent action: ".concat(paramIntent);; paramIntent = new String("Unknown intent action: "))
    {
      Log.d("FirebaseMessaging", paramIntent);
      do
      {
        return;
        if (!str1.equals("com.google.android.c2dm.intent.RECEIVE")) {
          break;
        }
        i = 0;
        break label50;
        if (!str1.equals("com.google.firebase.messaging.NOTIFICATION_DISMISS")) {
          break;
        }
        i = 1;
        break label50;
        zzn(paramIntent);
        return;
      } while (!zzW(paramIntent.getExtras()));
      zzb.zzk(this, paramIntent);
      return;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\messaging\FirebaseMessagingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */