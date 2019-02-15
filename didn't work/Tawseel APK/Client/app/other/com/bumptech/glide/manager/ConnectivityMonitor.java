package com.bumptech.glide.manager;

public abstract interface ConnectivityMonitor
  extends LifecycleListener
{
  public static abstract interface ConnectivityListener
  {
    public abstract void onConnectivityChanged(boolean paramBoolean);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\manager\ConnectivityMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */