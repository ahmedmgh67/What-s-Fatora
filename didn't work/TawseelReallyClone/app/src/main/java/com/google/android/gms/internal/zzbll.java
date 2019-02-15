package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

class zzbll
  implements zzblv.zza
{
  private static long zzbZj = 0L;
  private final zzbop zzbYx;
  private zzblp zzbZk;
  private zzblv zzbZl;
  private zza zzbZm;
  private zzc zzbZn;
  
  public zzbll(zzbln paramzzbln, zzblp paramzzblp, String paramString1, zza paramzza, String paramString2)
  {
    long l = zzbZj;
    zzbZj = 1L + l;
    this.zzbZk = paramzzblp;
    this.zzbZm = paramzza;
    this.zzbYx = new zzbop(paramzzbln.zzVH(), "Connection", 25 + "conn_" + l);
    this.zzbZn = zzc.zzbZr;
    this.zzbZl = new zzblv(paramzzbln, paramzzblp, paramString1, this, paramString2);
  }
  
  private void zzat(Map<String, Object> paramMap)
  {
    zzbop localzzbop;
    if (this.zzbYx.zzYT())
    {
      localzzbop = this.zzbYx;
      str = String.valueOf(paramMap.toString());
      if (str.length() == 0) {
        break label57;
      }
    }
    label57:
    for (String str = "received data message: ".concat(str);; str = new String("received data message: "))
    {
      localzzbop.zzi(str, new Object[0]);
      this.zzbZm.zzat(paramMap);
      return;
    }
  }
  
  private void zzau(Map<String, Object> paramMap)
  {
    Object localObject2;
    if (this.zzbYx.zzYT())
    {
      localObject2 = this.zzbYx;
      localObject1 = String.valueOf(paramMap.toString());
      if (((String)localObject1).length() == 0) {
        break label87;
      }
      localObject1 = "Got control message: ".concat((String)localObject1);
    }
    for (;;)
    {
      ((zzbop)localObject2).zzi((String)localObject1, new Object[0]);
      try
      {
        localObject2 = (String)paramMap.get("t");
        if (localObject2 == null) {
          break label259;
        }
        if (((String)localObject2).equals("s"))
        {
          zziI((String)paramMap.get("d"));
          return;
          label87:
          localObject1 = new String("Got control message: ");
        }
        else
        {
          if (!((String)localObject2).equals("r")) {
            break label177;
          }
          zziJ((String)paramMap.get("d"));
          return;
        }
      }
      catch (ClassCastException paramMap)
      {
        if (!this.zzbYx.zzYT()) {
          break label172;
        }
      }
    }
    Object localObject1 = this.zzbYx;
    paramMap = String.valueOf(paramMap.toString());
    if (paramMap.length() != 0) {}
    for (paramMap = "Failed to parse control message: ".concat(paramMap);; paramMap = new String("Failed to parse control message: "))
    {
      ((zzbop)localObject1).zzi(paramMap, new Object[0]);
      label172:
      close();
      return;
      label177:
      if (((String)localObject2).equals("h"))
      {
        zzav((Map)paramMap.get("d"));
        return;
      }
      if (!this.zzbYx.zzYT()) {
        break;
      }
      localObject1 = this.zzbYx;
      paramMap = String.valueOf(localObject2);
      if (paramMap.length() != 0) {}
      for (paramMap = "Ignoring unknown control message: ".concat(paramMap);; paramMap = new String("Ignoring unknown control message: "))
      {
        ((zzbop)localObject1).zzi(paramMap, new Object[0]);
        return;
      }
      label259:
      if (this.zzbYx.zzYT())
      {
        localObject1 = this.zzbYx;
        paramMap = String.valueOf(paramMap.toString());
        if (paramMap.length() == 0) {
          break label310;
        }
      }
      label310:
      for (paramMap = "Got invalid control message: ".concat(paramMap);; paramMap = new String("Got invalid control message: "))
      {
        ((zzbop)localObject1).zzi(paramMap, new Object[0]);
        close();
        return;
      }
    }
  }
  
  private void zzav(Map<String, Object> paramMap)
  {
    long l = ((Long)paramMap.get("ts")).longValue();
    String str = (String)paramMap.get("h");
    this.zzbZm.zziK(str);
    paramMap = (String)paramMap.get("s");
    if (this.zzbZn == zzc.zzbZr) {
      zzi(l, paramMap);
    }
  }
  
  private void zzb(Map<String, Object> paramMap, boolean paramBoolean)
  {
    if (this.zzbZn != zzc.zzbZs)
    {
      this.zzbYx.zzi("Tried to send on an unconnected connection", new Object[0]);
      return;
    }
    if (paramBoolean) {
      this.zzbYx.zzi("Sending data (contents hidden)", new Object[0]);
    }
    for (;;)
    {
      this.zzbZl.send(paramMap);
      return;
      this.zzbYx.zzi("Sending data: %s", new Object[] { paramMap });
    }
  }
  
  private void zzi(long paramLong, String paramString)
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("realtime connection established", new Object[0]);
    }
    this.zzbZn = zzc.zzbZs;
    this.zzbZm.zzj(paramLong, paramString);
  }
  
  private void zziI(String paramString)
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("Connection shutdown command received. Shutting down...", new Object[0]);
    }
    this.zzbZm.zziL(paramString);
    close();
  }
  
  private void zziJ(String paramString)
  {
    if (this.zzbYx.zzYT())
    {
      zzbop localzzbop = this.zzbYx;
      String str = String.valueOf(this.zzbZk.getHost());
      localzzbop.zzi(String.valueOf(str).length() + 62 + String.valueOf(paramString).length() + "Got a reset; killing connection to " + str + "; Updating internalHost to " + paramString, new Object[0]);
    }
    this.zzbZm.zziK(paramString);
    zza(zzb.zzbZo);
  }
  
  public void close()
  {
    zza(zzb.zzbZp);
  }
  
  public void open()
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("Opening a connection", new Object[0]);
    }
    this.zzbZl.open();
  }
  
  public void zza(zzb paramzzb)
  {
    if (this.zzbZn != zzc.zzbZt)
    {
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("closing realtime connection", new Object[0]);
      }
      this.zzbZn = zzc.zzbZt;
      if (this.zzbZl != null)
      {
        this.zzbZl.close();
        this.zzbZl = null;
      }
      this.zzbZm.zzb(paramzzb);
    }
  }
  
  public void zza(Map<String, Object> paramMap, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("t", "d");
    localHashMap.put("d", paramMap);
    zzb(localHashMap, paramBoolean);
  }
  
  public void zzaV(boolean paramBoolean)
  {
    this.zzbZl = null;
    if ((!paramBoolean) && (this.zzbZn == zzc.zzbZr)) {
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Realtime connection failed", new Object[0]);
      }
    }
    for (;;)
    {
      close();
      return;
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Realtime connection lost", new Object[0]);
      }
    }
  }
  
  public void zzas(Map<String, Object> paramMap)
  {
    for (;;)
    {
      String str;
      try
      {
        str = (String)paramMap.get("t");
        if (str == null) {
          break label178;
        }
        if (str.equals("d"))
        {
          zzat((Map)paramMap.get("d"));
          return;
        }
        if (str.equals("c"))
        {
          zzau((Map)paramMap.get("d"));
          return;
        }
      }
      catch (ClassCastException paramMap)
      {
        if (this.zzbYx.zzYT())
        {
          localzzbop = this.zzbYx;
          paramMap = String.valueOf(paramMap.toString());
          if (paramMap.length() == 0) {
            break label244;
          }
          paramMap = "Failed to parse server message: ".concat(paramMap);
          localzzbop.zzi(paramMap, new Object[0]);
        }
        close();
        return;
      }
      if (!this.zzbYx.zzYT()) {
        break;
      }
      zzbop localzzbop = this.zzbYx;
      paramMap = String.valueOf(str);
      if (paramMap.length() != 0) {}
      for (paramMap = "Ignoring unknown server message type: ".concat(paramMap);; paramMap = new String("Ignoring unknown server message type: "))
      {
        localzzbop.zzi(paramMap, new Object[0]);
        return;
      }
      label178:
      if (this.zzbYx.zzYT())
      {
        localzzbop = this.zzbYx;
        paramMap = String.valueOf(paramMap.toString());
        if (paramMap.length() == 0) {
          break label230;
        }
      }
      label230:
      for (paramMap = "Failed to parse server message: missing message type:".concat(paramMap);; paramMap = new String("Failed to parse server message: missing message type:"))
      {
        localzzbop.zzi(paramMap, new Object[0]);
        close();
        return;
      }
      label244:
      paramMap = new String("Failed to parse server message: ");
    }
  }
  
  public static abstract interface zza
  {
    public abstract void zzat(Map<String, Object> paramMap);
    
    public abstract void zzb(zzbll.zzb paramzzb);
    
    public abstract void zziK(String paramString);
    
    public abstract void zziL(String paramString);
    
    public abstract void zzj(long paramLong, String paramString);
  }
  
  public static enum zzb
  {
    private zzb() {}
  }
  
  private static enum zzc
  {
    private zzc() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */