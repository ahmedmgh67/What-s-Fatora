package com.bumptech.glide.manager;

import android.content.Context;

public class ConnectivityMonitorFactory
{
  public ConnectivityMonitor build(Context paramContext, ConnectivityMonitor.ConnectivityListener paramConnectivityListener)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {}
    for (int i = 1; i != 0; i = 0) {
      return new DefaultConnectivityMonitor(paramContext, paramConnectivityListener);
    }
    return new NullConnectivityMonitor();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\manager\ConnectivityMonitorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */