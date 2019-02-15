package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class zzacu
  implements ThreadFactory
{
  private final int mPriority;
  private final String zzaHh;
  private final AtomicInteger zzaHi = new AtomicInteger();
  private final ThreadFactory zzaHj = Executors.defaultThreadFactory();
  
  public zzacu(String paramString)
  {
    this(paramString, 0);
  }
  
  public zzacu(String paramString, int paramInt)
  {
    this.zzaHh = ((String)zzac.zzb(paramString, "Name must not be null"));
    this.mPriority = paramInt;
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = this.zzaHj.newThread(new zzacv(paramRunnable, this.mPriority));
    String str = this.zzaHh;
    int i = this.zzaHi.getAndIncrement();
    paramRunnable.setName(String.valueOf(str).length() + 13 + str + "[" + i + "]");
    return paramRunnable;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */