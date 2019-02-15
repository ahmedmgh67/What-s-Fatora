package com.firebase.geofire;

import android.os.Handler;
import android.os.Looper;

class AndroidEventRaiser
  implements EventRaiser
{
  private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
  
  public void raiseEvent(Runnable paramRunnable)
  {
    this.mainThreadHandler.post(paramRunnable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\AndroidEventRaiser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */