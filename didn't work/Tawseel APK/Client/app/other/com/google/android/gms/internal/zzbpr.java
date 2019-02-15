package com.google.android.gms.internal;

import android.util.Base64;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

class zzbpr
{
  private String protocol = null;
  private URI zzchO = null;
  private String zzcid = null;
  private Map<String, String> zzcie = null;
  
  public zzbpr(URI paramURI, String paramString, Map<String, String> paramMap)
  {
    this.zzchO = paramURI;
    this.protocol = paramString;
    this.zzcie = paramMap;
    this.zzcid = zzZP();
  }
  
  private int zzC(int paramInt1, int paramInt2)
  {
    return (int)(Math.random() * paramInt2 + paramInt1);
  }
  
  private String zzZP()
  {
    byte[] arrayOfByte = new byte[16];
    int i = 0;
    while (i < 16)
    {
      arrayOfByte[i] = ((byte)zzC(0, 255));
      i += 1;
    }
    return Base64.encodeToString(arrayOfByte, 2);
  }
  
  private String zza(LinkedHashMap<String, String> paramLinkedHashMap)
  {
    String str1 = new String();
    Iterator localIterator = paramLinkedHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      str1 = String.valueOf(str1);
      String str3 = (String)paramLinkedHashMap.get(str2);
      str1 = String.valueOf(str1).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length() + str1 + str2 + ": " + str3 + "\r\n";
    }
    return str1;
  }
  
  public byte[] zzZO()
  {
    Object localObject1 = this.zzchO.getPath();
    String str1 = this.zzchO.getQuery();
    localObject1 = String.valueOf(localObject1);
    if (str1 == null)
    {
      str1 = "";
      str1 = String.valueOf(str1);
      if (str1.length() == 0) {
        break label308;
      }
    }
    Object localObject2;
    label308:
    for (str1 = ((String)localObject1).concat(str1);; str1 = new String((String)localObject1))
    {
      localObject2 = this.zzchO.getHost();
      localObject1 = localObject2;
      if (this.zzchO.getPort() != -1)
      {
        localObject1 = String.valueOf(localObject2);
        int i = this.zzchO.getPort();
        localObject1 = String.valueOf(localObject1).length() + 12 + (String)localObject1 + ":" + i;
      }
      localObject2 = new LinkedHashMap();
      ((LinkedHashMap)localObject2).put("Host", localObject1);
      ((LinkedHashMap)localObject2).put("Upgrade", "websocket");
      ((LinkedHashMap)localObject2).put("Connection", "Upgrade");
      ((LinkedHashMap)localObject2).put("Sec-WebSocket-Version", "13");
      ((LinkedHashMap)localObject2).put("Sec-WebSocket-Key", this.zzcid);
      if (this.protocol != null) {
        ((LinkedHashMap)localObject2).put("Sec-WebSocket-Protocol", this.protocol);
      }
      if (this.zzcie == null) {
        break label320;
      }
      localObject1 = this.zzcie.keySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        String str2 = (String)((Iterator)localObject1).next();
        if (!((LinkedHashMap)localObject2).containsKey(str2)) {
          ((LinkedHashMap)localObject2).put(str2, (String)this.zzcie.get(str2));
        }
      }
      str1 = String.valueOf(str1);
      if (str1.length() != 0)
      {
        str1 = "?".concat(str1);
        break;
      }
      str1 = new String("?");
      break;
    }
    label320:
    str1 = String.valueOf(String.valueOf(str1).length() + 15 + "GET " + str1 + " HTTP/1.1\r\n");
    localObject1 = String.valueOf(zza((LinkedHashMap)localObject2));
    if (((String)localObject1).length() != 0) {}
    for (str1 = str1.concat((String)localObject1);; str1 = new String(str1))
    {
      str1 = String.valueOf(str1).concat("\r\n");
      localObject1 = new byte[str1.getBytes().length];
      System.arraycopy(str1.getBytes(), 0, localObject1, 0, str1.getBytes().length);
      return (byte[])localObject1;
    }
  }
  
  public void zzd(HashMap<String, String> paramHashMap)
  {
    if (!((String)paramHashMap.get("Upgrade")).toLowerCase(Locale.US).equals("websocket")) {
      throw new zzbpq("connection failed: missing header field in server handshake: Upgrade");
    }
    if (!((String)paramHashMap.get("Connection")).toLowerCase(Locale.US).equals("upgrade")) {
      throw new zzbpq("connection failed: missing header field in server handshake: Connection");
    }
  }
  
  public void zzjd(String paramString)
  {
    int i = Integer.valueOf(paramString.substring(9, 12)).intValue();
    if (i == 407) {
      throw new zzbpq("connection failed: proxy authentication not supported");
    }
    if (i == 404) {
      throw new zzbpq("connection failed: 404 not found");
    }
    if (i != 101) {
      throw new zzbpq(50 + "connection failed: unknown status code " + i);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */