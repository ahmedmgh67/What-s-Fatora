package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class RemoteMessage
  extends com.google.android.gms.common.internal.safeparcel.zza
{
  public static final Parcelable.Creator<RemoteMessage> CREATOR = new zzc();
  final int mVersionCode;
  private Map<String, String> zzabb;
  Bundle zzahb;
  private Notification zzcjr;
  
  RemoteMessage(int paramInt, Bundle paramBundle)
  {
    this.mVersionCode = paramInt;
    this.zzahb = paramBundle;
  }
  
  RemoteMessage(Bundle paramBundle)
  {
    this(1, paramBundle);
  }
  
  public String getCollapseKey()
  {
    return this.zzahb.getString("collapse_key");
  }
  
  public Map<String, String> getData()
  {
    if (this.zzabb == null)
    {
      this.zzabb = new ArrayMap();
      Iterator localIterator = this.zzahb.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = this.zzahb.get(str);
        if ((localObject instanceof String))
        {
          localObject = (String)localObject;
          if ((!str.startsWith("google.")) && (!str.startsWith("gcm.")) && (!str.equals("from")) && (!str.equals("message_type")) && (!str.equals("collapse_key"))) {
            this.zzabb.put(str, localObject);
          }
        }
      }
    }
    return this.zzabb;
  }
  
  public String getFrom()
  {
    return this.zzahb.getString("from");
  }
  
  public String getMessageId()
  {
    String str2 = this.zzahb.getString("google.message_id");
    String str1 = str2;
    if (str2 == null) {
      str1 = this.zzahb.getString("message_id");
    }
    return str1;
  }
  
  public String getMessageType()
  {
    return this.zzahb.getString("message_type");
  }
  
  public Notification getNotification()
  {
    if ((this.zzcjr == null) && (zza.zzE(this.zzahb))) {
      this.zzcjr = new Notification(this.zzahb, null);
    }
    return this.zzcjr;
  }
  
  public long getSentTime()
  {
    Object localObject = this.zzahb.get("google.sent_time");
    if ((localObject instanceof Long)) {
      return ((Long)localObject).longValue();
    }
    if ((localObject instanceof String)) {
      try
      {
        long l = Long.parseLong((String)localObject);
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localObject = String.valueOf(localObject);
        Log.w("FirebaseMessaging", String.valueOf(localObject).length() + 19 + "Invalid sent time: " + (String)localObject);
      }
    }
    return 0L;
  }
  
  public String getTo()
  {
    return this.zzahb.getString("google.to");
  }
  
  public int getTtl()
  {
    Object localObject = this.zzahb.get("google.ttl");
    if ((localObject instanceof Integer)) {
      return ((Integer)localObject).intValue();
    }
    if ((localObject instanceof String)) {
      try
      {
        int i = Integer.parseInt((String)localObject);
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localObject = String.valueOf(localObject);
        Log.w("FirebaseMessaging", String.valueOf(localObject).length() + 13 + "Invalid TTL: " + (String)localObject);
      }
    }
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  void zzL(Intent paramIntent)
  {
    paramIntent.putExtras(this.zzahb);
  }
  
  public static class Builder
  {
    private final Map<String, String> zzabb = new ArrayMap();
    private final Bundle zzahb = new Bundle();
    
    public Builder(String paramString)
    {
      if (TextUtils.isEmpty(paramString))
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {}
        for (paramString = "Invalid to: ".concat(paramString);; paramString = new String("Invalid to: ")) {
          throw new IllegalArgumentException(paramString);
        }
      }
      this.zzahb.putString("google.to", paramString);
    }
    
    public Builder addData(String paramString1, String paramString2)
    {
      this.zzabb.put(paramString1, paramString2);
      return this;
    }
    
    public RemoteMessage build()
    {
      Bundle localBundle = new Bundle();
      Object localObject = this.zzabb.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        localBundle.putString((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      localBundle.putAll(this.zzahb);
      localObject = FirebaseInstanceId.getInstance().getToken();
      if (localObject != null) {
        this.zzahb.putString("from", (String)localObject);
      }
      for (;;)
      {
        return new RemoteMessage(localBundle);
        this.zzahb.remove("from");
      }
    }
    
    public Builder clearData()
    {
      this.zzabb.clear();
      return this;
    }
    
    public Builder setCollapseKey(String paramString)
    {
      this.zzahb.putString("collapse_key", paramString);
      return this;
    }
    
    public Builder setData(Map<String, String> paramMap)
    {
      this.zzabb.clear();
      this.zzabb.putAll(paramMap);
      return this;
    }
    
    public Builder setMessageId(String paramString)
    {
      this.zzahb.putString("google.message_id", paramString);
      return this;
    }
    
    public Builder setMessageType(String paramString)
    {
      this.zzahb.putString("message_type", paramString);
      return this;
    }
    
    public Builder setTtl(int paramInt)
    {
      this.zzahb.putString("google.ttl", String.valueOf(paramInt));
      return this;
    }
  }
  
  public static class Notification
  {
    private final String mTag;
    private final String zzFU;
    private final String zzaPp;
    private final String zzalD;
    private final String zzcjs;
    private final String[] zzcjt;
    private final String zzcju;
    private final String[] zzcjv;
    private final String zzcjw;
    private final String zzcjx;
    private final String zzcjy;
    
    private Notification(Bundle paramBundle)
    {
      this.zzalD = zza.zzf(paramBundle, "gcm.n.title");
      this.zzcjs = zza.zzh(paramBundle, "gcm.n.title");
      this.zzcjt = zzj(paramBundle, "gcm.n.title");
      this.zzFU = zza.zzf(paramBundle, "gcm.n.body");
      this.zzcju = zza.zzh(paramBundle, "gcm.n.body");
      this.zzcjv = zzj(paramBundle, "gcm.n.body");
      this.zzcjw = zza.zzf(paramBundle, "gcm.n.icon");
      this.zzcjx = zza.zzU(paramBundle);
      this.mTag = zza.zzf(paramBundle, "gcm.n.tag");
      this.zzaPp = zza.zzf(paramBundle, "gcm.n.color");
      this.zzcjy = zza.zzf(paramBundle, "gcm.n.click_action");
    }
    
    private String[] zzj(Bundle paramBundle, String paramString)
    {
      paramBundle = zza.zzi(paramBundle, paramString);
      if (paramBundle == null) {
        return null;
      }
      paramString = new String[paramBundle.length];
      int i = 0;
      while (i < paramBundle.length)
      {
        paramString[i] = String.valueOf(paramBundle[i]);
        i += 1;
      }
      return paramString;
    }
    
    public String getBody()
    {
      return this.zzFU;
    }
    
    public String[] getBodyLocalizationArgs()
    {
      return this.zzcjv;
    }
    
    public String getBodyLocalizationKey()
    {
      return this.zzcju;
    }
    
    public String getClickAction()
    {
      return this.zzcjy;
    }
    
    public String getColor()
    {
      return this.zzaPp;
    }
    
    public String getIcon()
    {
      return this.zzcjw;
    }
    
    public String getSound()
    {
      return this.zzcjx;
    }
    
    public String getTag()
    {
      return this.mTag;
    }
    
    public String getTitle()
    {
      return this.zzalD;
    }
    
    public String[] getTitleLocalizationArgs()
    {
      return this.zzcjt;
    }
    
    public String getTitleLocalizationKey()
    {
      return this.zzcjs;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\messaging\RemoteMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */