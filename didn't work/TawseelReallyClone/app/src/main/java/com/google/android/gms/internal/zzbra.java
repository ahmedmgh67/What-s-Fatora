package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.storage.zzd;
import java.util.concurrent.Executor;

public class zzbra
{
  static boolean zzcma = false;
  private final Handler mHandler;
  private final Executor zzbDK;
  
  public zzbra(@Nullable Executor paramExecutor)
  {
    this.zzbDK = paramExecutor;
    if (this.zzbDK == null)
    {
      if (Looper.myLooper() != null)
      {
        this.mHandler = new Handler();
        return;
      }
      this.mHandler = null;
      return;
    }
    this.mHandler = null;
  }
  
  public void zzx(@NonNull Runnable paramRunnable)
  {
    zzac.zzw(paramRunnable);
    if (this.mHandler == null)
    {
      if (this.zzbDK != null)
      {
        this.zzbDK.execute(paramRunnable);
        return;
      }
      zzd.zzaaW().zzw(paramRunnable);
      return;
    }
    this.mHandler.post(paramRunnable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */