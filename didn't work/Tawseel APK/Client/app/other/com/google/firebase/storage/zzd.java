package com.google.firebase.storage;

import android.support.annotation.NonNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class zzd
{
  public static zzd zzcli = new zzd();
  private static BlockingQueue<Runnable> zzclj = new LinkedBlockingQueue(128);
  private static final ThreadPoolExecutor zzclk = new ThreadPoolExecutor(5, 5, 5L, TimeUnit.SECONDS, zzclj, new zza("Command-"));
  private static BlockingQueue<Runnable> zzcll = new LinkedBlockingQueue(128);
  private static final ThreadPoolExecutor zzclm = new ThreadPoolExecutor(2, 2, 5L, TimeUnit.SECONDS, zzcll, new zza("Upload-"));
  private static BlockingQueue<Runnable> zzcln = new LinkedBlockingQueue(128);
  private static final ThreadPoolExecutor zzclo = new ThreadPoolExecutor(3, 3, 5L, TimeUnit.SECONDS, zzcln, new zza("Download-"));
  private static BlockingQueue<Runnable> zzclp = new LinkedBlockingQueue(128);
  private static final ThreadPoolExecutor zzclq = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, zzclp, new zza("Callbacks-"));
  
  static
  {
    zzclk.allowCoreThreadTimeOut(true);
    zzclm.allowCoreThreadTimeOut(true);
    zzclo.allowCoreThreadTimeOut(true);
    zzclq.allowCoreThreadTimeOut(true);
  }
  
  public static zzd zzaaW()
  {
    return zzcli;
  }
  
  public void zzt(Runnable paramRunnable)
  {
    zzclk.execute(paramRunnable);
  }
  
  public void zzu(Runnable paramRunnable)
  {
    zzclm.execute(paramRunnable);
  }
  
  public void zzv(Runnable paramRunnable)
  {
    zzclo.execute(paramRunnable);
  }
  
  public void zzw(Runnable paramRunnable)
  {
    zzclq.execute(paramRunnable);
  }
  
  static class zza
    implements ThreadFactory
  {
    private final AtomicInteger zzbfT = new AtomicInteger(1);
    private final String zzclr;
    
    zza(@NonNull String paramString)
    {
      this.zzclr = paramString;
    }
    
    public Thread newThread(@NonNull Runnable paramRunnable)
    {
      String str = this.zzclr;
      int i = this.zzbfT.getAndIncrement();
      paramRunnable = new Thread(paramRunnable, String.valueOf(str).length() + 27 + "FirebaseStorage-" + str + i);
      paramRunnable.setDaemon(false);
      paramRunnable.setPriority(9);
      return paramRunnable;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */