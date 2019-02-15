package com.google.android.gms.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import java.util.concurrent.ScheduledExecutorService;

public class zzbmc
{
  protected long cacheSize = 10485760L;
  protected FirebaseApp zzbYm;
  protected zzboq zzbZw;
  protected boolean zzbZx;
  protected String zzbZz;
  protected zzbmg zzcbK;
  protected zzbly zzcbL;
  protected zzbmo zzcbM;
  protected String zzcbN;
  protected zzboq.zza zzcbO = zzboq.zza.zzcgG;
  private boolean zzcbP = false;
  private zzbmk zzcbQ;
  private boolean zzcbx = false;
  
  private ScheduledExecutorService zzVJ()
  {
    zzbmo localzzbmo = zzWR();
    if (!(localzzbmo instanceof zzbqa)) {
      throw new RuntimeException("Custom run loops are not supported!");
    }
    return ((zzbqa)localzzbmo).zzVJ();
  }
  
  private zzbmk zzWI()
  {
    if (this.zzcbQ == null)
    {
      if (!zzbpv.zzZW()) {
        break label22;
      }
      zzWJ();
    }
    for (;;)
    {
      return this.zzcbQ;
      label22:
      if (zzbmh.isActive())
      {
        zzbmh localzzbmh = zzbmh.zzcbW;
        localzzbmh.initialize();
        this.zzcbQ = localzzbmh;
      }
      else
      {
        this.zzcbQ = zzbmi.zzcca;
      }
    }
  }
  
  private void zzWJ()
  {
    try
    {
      this.zzcbQ = new zzbkx(this.zzbYm);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void zzWL()
  {
    zzWU();
    zzWI();
    zzWX();
    zzWW();
    zzWV();
    zzWZ();
    zzWY();
  }
  
  private void zzWM()
  {
    this.zzcbK.restart();
    this.zzcbM.restart();
  }
  
  private void zzWU()
  {
    if (this.zzbZw == null) {
      this.zzbZw = zzWI().zza(this, this.zzcbO, null);
    }
  }
  
  private void zzWV()
  {
    if (this.zzcbM == null) {
      this.zzcbM = this.zzcbQ.zzb(this);
    }
  }
  
  private void zzWW()
  {
    if (this.zzcbK == null) {
      this.zzcbK = zzWI().zza(this);
    }
  }
  
  private void zzWX()
  {
    if (this.zzbZz == null) {
      this.zzbZz = zziY(zzWI().zzc(this));
    }
  }
  
  private void zzWY()
  {
    if (this.zzcbL == null) {
      this.zzcbL = zzWI().zza(zzVJ());
    }
  }
  
  private void zzWZ()
  {
    if (this.zzcbN == null) {
      this.zzcbN = "default";
    }
  }
  
  private static zzblm zza(zzbly paramzzbly)
  {
    new zzblm()
    {
      public void zza(boolean paramAnonymousBoolean, final zzblm.zza paramAnonymouszza)
      {
        zzbmc.this.zza(paramAnonymousBoolean, new zzbly.zza()
        {
          public void onError(String paramAnonymous2String)
          {
            paramAnonymouszza.onError(paramAnonymous2String);
          }
          
          public void zziM(String paramAnonymous2String)
          {
            paramAnonymouszza.zziM(paramAnonymous2String);
          }
        });
      }
    };
  }
  
  private String zziY(String paramString)
  {
    return "Firebase/" + "5" + "/" + FirebaseDatabase.getSdkVersion() + "/" + paramString;
  }
  
  public boolean isFrozen()
  {
    return this.zzcbx;
  }
  
  void stop()
  {
    this.zzcbP = true;
    this.zzcbK.shutdown();
    this.zzcbM.shutdown();
  }
  
  public zzboq zzVH()
  {
    return this.zzbZw;
  }
  
  public boolean zzVK()
  {
    return this.zzbZx;
  }
  
  public void zzWK()
  {
    if (this.zzcbP)
    {
      zzWM();
      this.zzcbP = false;
    }
  }
  
  protected void zzWN()
  {
    if (isFrozen()) {
      throw new DatabaseException("Modifications to DatabaseConfig objects must occur before they are in use");
    }
  }
  
  public zzbln zzWO()
  {
    return new zzbln(zzVH(), zza(zzWT()), zzVJ(), zzVK(), FirebaseDatabase.getSdkVersion(), zzjQ());
  }
  
  public long zzWP()
  {
    return this.cacheSize;
  }
  
  public zzbmg zzWQ()
  {
    return this.zzcbK;
  }
  
  public zzbmo zzWR()
  {
    return this.zzcbM;
  }
  
  public String zzWS()
  {
    return this.zzcbN;
  }
  
  public zzbly zzWT()
  {
    return this.zzcbL;
  }
  
  public zzboq.zza zzWu()
  {
    return this.zzcbO;
  }
  
  void zzWz()
  {
    try
    {
      if (!this.zzcbx)
      {
        this.zzcbx = true;
        zzWL();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public zzblr zza(zzblp paramzzblp, zzblr.zza paramzza)
  {
    return zzWI().zza(this, zzWO(), paramzzblp, paramzza);
  }
  
  public zzbop zziW(String paramString)
  {
    return new zzbop(this.zzbZw, paramString);
  }
  
  zzbnn zziX(String paramString)
  {
    if (this.zzbZx)
    {
      zzbnn localzzbnn = this.zzcbQ.zza(this, paramString);
      paramString = localzzbnn;
      if (localzzbnn == null) {
        throw new IllegalArgumentException("You have enabled persistence, but persistence is not supported on this platform.");
      }
    }
    else
    {
      paramString = new zzbnm();
    }
    return paramString;
  }
  
  public String zzjQ()
  {
    return this.zzbZz;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */