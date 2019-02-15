package com.google.android.gms.internal;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class zzblw
{
  private final ScheduledExecutorService zzbYl;
  private final zzbop zzbYx;
  private final long zzcbh;
  private final long zzcbi;
  private final double zzcbj;
  private final double zzcbk;
  private final Random zzcbl = new Random();
  private ScheduledFuture<?> zzcbm;
  private long zzcbn;
  private boolean zzcbo = true;
  
  private zzblw(ScheduledExecutorService paramScheduledExecutorService, zzbop paramzzbop, long paramLong1, long paramLong2, double paramDouble1, double paramDouble2)
  {
    this.zzbYl = paramScheduledExecutorService;
    this.zzbYx = paramzzbop;
    this.zzcbh = paramLong1;
    this.zzcbi = paramLong2;
    this.zzcbk = paramDouble1;
    this.zzcbj = paramDouble2;
  }
  
  public void cancel()
  {
    if (this.zzcbm != null)
    {
      this.zzbYx.zzi("Cancelling existing retry attempt", new Object[0]);
      this.zzcbm.cancel(false);
      this.zzcbm = null;
    }
    for (;;)
    {
      this.zzcbn = 0L;
      return;
      this.zzbYx.zzi("No existing retry attempt to cancel", new Object[0]);
    }
  }
  
  public void zzUk()
  {
    this.zzcbo = true;
    this.zzcbn = 0L;
  }
  
  public void zzWx()
  {
    this.zzcbn = this.zzcbi;
  }
  
  public void zzr(final Runnable paramRunnable)
  {
    long l = 0L;
    paramRunnable = new Runnable()
    {
      public void run()
      {
        zzblw.zza(zzblw.this, null);
        paramRunnable.run();
      }
    };
    if (this.zzcbm != null)
    {
      this.zzbYx.zzi("Cancelling previous scheduled retry", new Object[0]);
      this.zzcbm.cancel(false);
      this.zzcbm = null;
    }
    if (this.zzcbo)
    {
      this.zzcbo = false;
      this.zzbYx.zzi("Scheduling retry in %dms", new Object[] { Long.valueOf(l) });
      this.zzcbm = this.zzbYl.schedule(paramRunnable, l, TimeUnit.MILLISECONDS);
      return;
    }
    if (this.zzcbn == 0L) {}
    for (this.zzcbn = this.zzcbh;; this.zzcbn = Math.min((this.zzcbn * this.zzcbk), this.zzcbi))
    {
      l = ((1.0D - this.zzcbj) * this.zzcbn + this.zzcbj * this.zzcbn * this.zzcbl.nextDouble());
      break;
    }
  }
  
  public static class zza
  {
    private final zzbop zzbYx;
    private long zzcbh = 1000L;
    private double zzcbj = 0.5D;
    private double zzcbk = 1.3D;
    private final ScheduledExecutorService zzcbq;
    private long zzcbr = 30000L;
    
    public zza(ScheduledExecutorService paramScheduledExecutorService, zzboq paramzzboq, String paramString)
    {
      this.zzcbq = paramScheduledExecutorService;
      this.zzbYx = new zzbop(paramzzboq, paramString);
    }
    
    public zzblw zzWy()
    {
      return new zzblw(this.zzcbq, this.zzbYx, this.zzcbh, this.zzcbr, this.zzcbk, this.zzcbj, null);
    }
    
    public zza zzaI(long paramLong)
    {
      this.zzcbh = paramLong;
      return this;
    }
    
    public zza zzaJ(long paramLong)
    {
      this.zzcbr = paramLong;
      return this;
    }
    
    public zza zzj(double paramDouble)
    {
      this.zzcbk = paramDouble;
      return this;
    }
    
    public zza zzk(double paramDouble)
    {
      if ((paramDouble < 0.0D) || (paramDouble > 1.0D)) {
        throw new IllegalArgumentException(47 + "Argument out of range: " + paramDouble);
      }
      this.zzcbj = paramDouble;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */