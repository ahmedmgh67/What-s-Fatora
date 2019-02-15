package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;

public class zzbkw
  implements zzbmg
{
  private final Handler handler = new Handler(Looper.getMainLooper());
  
  public void restart() {}
  
  public void shutdown() {}
  
  public void zzq(Runnable paramRunnable)
  {
    this.handler.post(paramRunnable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */