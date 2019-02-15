package com.firebase.geofire;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadEventRaiser
  implements EventRaiser
{
  private final ExecutorService executorService = Executors.newSingleThreadExecutor();
  
  public void raiseEvent(Runnable paramRunnable)
  {
    this.executorService.submit(paramRunnable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\ThreadEventRaiser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */