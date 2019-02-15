package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public class zzbkm
  implements Executor
{
  private static zzbkm zzbWV = new zzbkm();
  private Handler mHandler = new Handler(Looper.getMainLooper());
  
  public static zzbkm zzUK()
  {
    return zzbWV;
  }
  
  public void execute(@NonNull Runnable paramRunnable)
  {
    this.mHandler.post(paramRunnable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */