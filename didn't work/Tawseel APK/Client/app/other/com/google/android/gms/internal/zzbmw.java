package com.google.android.gms.internal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class zzbmw
  implements zzbmg
{
  private final ThreadPoolExecutor zzcee;
  
  public zzbmw(final ThreadFactory paramThreadFactory, final zzbmv paramzzbmv)
  {
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    this.zzcee = new ThreadPoolExecutor(1, 1, 3L, TimeUnit.SECONDS, localLinkedBlockingQueue, new ThreadFactory()
    {
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = paramThreadFactory.newThread(paramAnonymousRunnable);
        paramzzbmv.zza(paramAnonymousRunnable, "FirebaseDatabaseEventTarget");
        paramzzbmv.zza(paramAnonymousRunnable, true);
        return paramAnonymousRunnable;
      }
    });
  }
  
  public void restart()
  {
    this.zzcee.setCorePoolSize(1);
  }
  
  public void shutdown()
  {
    this.zzcee.setCorePoolSize(0);
  }
  
  public void zzq(Runnable paramRunnable)
  {
    this.zzcee.execute(paramRunnable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */