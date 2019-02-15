package com.google.android.gms.internal;

import android.os.Process;

class zzacv
  implements Runnable
{
  private final int mPriority;
  private final Runnable zzv;
  
  public zzacv(Runnable paramRunnable, int paramInt)
  {
    this.zzv = paramRunnable;
    this.mPriority = paramInt;
  }
  
  public void run()
  {
    Process.setThreadPriority(this.mPriority);
    this.zzv.run();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */