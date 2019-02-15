package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzblv
{
  private static long zzcaB = 0L;
  private final ScheduledExecutorService zzbYl;
  private final zzbop zzbYx;
  private zzb zzcaC;
  private boolean zzcaD = false;
  private boolean zzcaE = false;
  private long zzcaF = 0L;
  private zzblx zzcaG;
  private zza zzcaH;
  private ScheduledFuture<?> zzcaI;
  private ScheduledFuture<?> zzcaJ;
  private final zzbln zzcaK;
  
  public zzblv(zzbln paramzzbln, zzblp paramzzblp, String paramString1, zza paramzza, String paramString2)
  {
    this.zzcaK = paramzzbln;
    this.zzbYl = paramzzbln.zzVJ();
    this.zzcaH = paramzza;
    long l = zzcaB;
    zzcaB = 1L + l;
    this.zzbYx = new zzbop(paramzzbln.zzVH(), "WebSocket", 23 + "ws_" + l);
    this.zzcaC = zza(paramzzblp, paramString1, paramString2);
  }
  
  private boolean isBuffering()
  {
    return this.zzcaG != null;
  }
  
  private void shutdown()
  {
    this.zzcaE = true;
    this.zzcaH.zzaV(this.zzcaD);
  }
  
  private static String[] zzF(String paramString, int paramInt)
  {
    int i = 0;
    if (paramString.length() <= paramInt) {
      return new String[] { paramString };
    }
    ArrayList localArrayList = new ArrayList();
    while (i < paramString.length())
    {
      localArrayList.add(paramString.substring(i, Math.min(i + paramInt, paramString.length())));
      i += paramInt;
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  private void zzWp()
  {
    if (!this.zzcaE)
    {
      if (this.zzcaI == null) {
        break label106;
      }
      this.zzcaI.cancel(false);
      if (this.zzbYx.zzYT())
      {
        zzbop localzzbop = this.zzbYx;
        long l = this.zzcaI.getDelay(TimeUnit.MILLISECONDS);
        localzzbop.zzi(48 + "Reset keepAlive. Remaining: " + l, new Object[0]);
      }
    }
    for (;;)
    {
      this.zzcaI = this.zzbYl.schedule(zzWq(), 45000L, TimeUnit.MILLISECONDS);
      return;
      label106:
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Reset keepAlive", new Object[0]);
      }
    }
  }
  
  private Runnable zzWq()
  {
    new Runnable()
    {
      public void run()
      {
        if (zzblv.zzg(zzblv.this) != null)
        {
          zzblv.zzg(zzblv.this).zziT("0");
          zzblv.zzc(zzblv.this);
        }
      }
    };
  }
  
  private void zzWr()
  {
    if (!this.zzcaE)
    {
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("closing itself", new Object[0]);
      }
      shutdown();
    }
    this.zzcaC = null;
    if (this.zzcaI != null) {
      this.zzcaI.cancel(false);
    }
  }
  
  private void zzWs()
  {
    if ((!this.zzcaD) && (!this.zzcaE))
    {
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("timed out on connect", new Object[0]);
      }
      this.zzcaC.close();
    }
  }
  
  private zzb zza(zzblp paramzzblp, String paramString1, String paramString2)
  {
    if (paramString1 != null) {}
    for (;;)
    {
      paramzzblp = zzblp.zza(paramString1, paramzzblp.isSecure(), paramzzblp.getNamespace(), paramString2);
      paramString1 = new HashMap();
      paramString1.put("User-Agent", this.zzcaK.zzjQ());
      return new zzc(new zzbpo(paramzzblp, null, paramString1), null);
      paramString1 = paramzzblp.getHost();
    }
  }
  
  private void zziQ(String paramString)
  {
    this.zzcaG.zziU(paramString);
    this.zzcaF -= 1L;
    if (this.zzcaF == 0L) {}
    Object localObject;
    try
    {
      this.zzcaG.zzWz();
      paramString = zzbpx.zzjf(this.zzcaG.toString());
      this.zzcaG = null;
      if (this.zzbYx.zzYT())
      {
        zzbop localzzbop = this.zzbYx;
        localObject = String.valueOf(paramString);
        localzzbop.zzi(String.valueOf(localObject).length() + 36 + "handleIncomingFrame complete frame: " + (String)localObject, new Object[0]);
      }
      this.zzcaH.zzas(paramString);
      return;
    }
    catch (IOException localIOException)
    {
      localObject = this.zzbYx;
      paramString = String.valueOf(this.zzcaG.toString());
      if (paramString.length() != 0) {}
      for (paramString = "Error parsing frame: ".concat(paramString);; paramString = new String("Error parsing frame: "))
      {
        ((zzbop)localObject).zzd(paramString, localIOException);
        close();
        shutdown();
        return;
      }
    }
    catch (ClassCastException localClassCastException)
    {
      localObject = this.zzbYx;
      paramString = String.valueOf(this.zzcaG.toString());
      if (paramString.length() == 0) {}
    }
    for (paramString = "Error parsing frame (cast error): ".concat(paramString);; paramString = new String("Error parsing frame (cast error): "))
    {
      ((zzbop)localObject).zzd(paramString, localClassCastException);
      close();
      shutdown();
      return;
    }
  }
  
  private String zziR(String paramString)
  {
    if (paramString.length() <= 6) {
      try
      {
        int i = Integer.parseInt(paramString);
        if (i > 0) {
          zzpJ(i);
        }
        return null;
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    zzpJ(1);
    return paramString;
  }
  
  private void zziS(String paramString)
  {
    if (!this.zzcaE)
    {
      zzWp();
      if (!isBuffering()) {
        break label24;
      }
      zziQ(paramString);
    }
    label24:
    do
    {
      return;
      paramString = zziR(paramString);
    } while (paramString == null);
    zziQ(paramString);
  }
  
  private void zzpJ(int paramInt)
  {
    this.zzcaF = paramInt;
    this.zzcaG = new zzblx();
    if (this.zzbYx.zzYT())
    {
      zzbop localzzbop = this.zzbYx;
      long l = this.zzcaF;
      localzzbop.zzi(41 + "HandleNewFrameCount: " + l, new Object[0]);
    }
  }
  
  public void close()
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("websocket is being closed", new Object[0]);
    }
    this.zzcaE = true;
    this.zzcaC.close();
    if (this.zzcaJ != null) {
      this.zzcaJ.cancel(true);
    }
    if (this.zzcaI != null) {
      this.zzcaI.cancel(true);
    }
  }
  
  public void open()
  {
    this.zzcaC.connect();
    this.zzcaJ = this.zzbYl.schedule(new Runnable()
    {
      public void run()
      {
        zzblv.zzf(zzblv.this);
      }
    }, 30000L, TimeUnit.MILLISECONDS);
  }
  
  public void send(Map<String, Object> paramMap)
  {
    zzWp();
    for (;;)
    {
      try
      {
        arrayOfString = zzF(zzbpx.zzaE(paramMap), 16384);
        if (arrayOfString.length <= 1) {
          continue;
        }
        localObject = this.zzcaC;
        i = arrayOfString.length;
        ((zzb)localObject).zziT(11 + i);
      }
      catch (IOException localIOException)
      {
        String[] arrayOfString;
        Object localObject = this.zzbYx;
        paramMap = String.valueOf(paramMap.toString());
        if (paramMap.length() == 0) {
          continue;
        }
        paramMap = "Failed to serialize message: ".concat(paramMap);
        ((zzbop)localObject).zzd(paramMap, localIOException);
        shutdown();
        return;
        paramMap = new String("Failed to serialize message: ");
        continue;
        int i = 0;
        continue;
      }
      if (i >= arrayOfString.length) {
        continue;
      }
      this.zzcaC.zziT(arrayOfString[i]);
      i += 1;
    }
  }
  
  public static abstract interface zza
  {
    public abstract void zzaV(boolean paramBoolean);
    
    public abstract void zzas(Map<String, Object> paramMap);
  }
  
  private static abstract interface zzb
  {
    public abstract void close();
    
    public abstract void connect();
    
    public abstract void zziT(String paramString);
  }
  
  private class zzc
    implements zzblv.zzb, zzbpp
  {
    private zzbpo zzcaM;
    
    private zzc(zzbpo paramzzbpo)
    {
      this.zzcaM = paramzzbpo;
      this.zzcaM.zza(this);
    }
    
    private void shutdown()
    {
      this.zzcaM.close();
      try
      {
        this.zzcaM.zzZL();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        zzblv.zzb(zzblv.this).zzd("Interrupted while shutting down websocket threads", localInterruptedException);
      }
    }
    
    public void close()
    {
      this.zzcaM.close();
    }
    
    public void connect()
    {
      try
      {
        this.zzcaM.connect();
        return;
      }
      catch (zzbpq localzzbpq)
      {
        if (zzblv.zzb(zzblv.this).zzYT()) {
          zzblv.zzb(zzblv.this).zza("Error connecting", localzzbpq, new Object[0]);
        }
        shutdown();
      }
    }
    
    public void onClose()
    {
      zzblv.zzd(zzblv.this).execute(new Runnable()
      {
        public void run()
        {
          if (zzblv.zzb(zzblv.this).zzYT()) {
            zzblv.zzb(zzblv.this).zzi("closed", new Object[0]);
          }
          zzblv.zze(zzblv.this);
        }
      });
    }
    
    public void zzWt()
    {
      zzblv.zzd(zzblv.this).execute(new Runnable()
      {
        public void run()
        {
          zzblv.zza(zzblv.this).cancel(false);
          zzblv.zza(zzblv.this, true);
          if (zzblv.zzb(zzblv.this).zzYT()) {
            zzblv.zzb(zzblv.this).zzi("websocket opened", new Object[0]);
          }
          zzblv.zzc(zzblv.this);
        }
      });
    }
    
    public void zza(final zzbpq paramzzbpq)
    {
      zzblv.zzd(zzblv.this).execute(new Runnable()
      {
        public void run()
        {
          if ((paramzzbpq.getCause() != null) && ((paramzzbpq.getCause() instanceof EOFException))) {
            zzblv.zzb(zzblv.this).zzi("WebSocket reached EOF.", new Object[0]);
          }
          for (;;)
          {
            zzblv.zze(zzblv.this);
            return;
            zzblv.zzb(zzblv.this).zza("WebSocket error.", paramzzbpq, new Object[0]);
          }
        }
      });
    }
    
    public void zza(zzbps paramzzbps)
    {
      final String str = paramzzbps.getText();
      zzbop localzzbop;
      if (zzblv.zzb(zzblv.this).zzYT())
      {
        localzzbop = zzblv.zzb(zzblv.this);
        paramzzbps = String.valueOf(str);
        if (paramzzbps.length() == 0) {
          break label76;
        }
      }
      label76:
      for (paramzzbps = "ws message: ".concat(paramzzbps);; paramzzbps = new String("ws message: "))
      {
        localzzbop.zzi(paramzzbps, new Object[0]);
        zzblv.zzd(zzblv.this).execute(new Runnable()
        {
          public void run()
          {
            zzblv.zza(zzblv.this, str);
          }
        });
        return;
      }
    }
    
    public void zziT(String paramString)
    {
      this.zzcaM.zziT(paramString);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */