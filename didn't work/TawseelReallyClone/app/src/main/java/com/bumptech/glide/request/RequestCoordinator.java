package com.bumptech.glide.request;

public abstract interface RequestCoordinator
{
  public abstract boolean canNotifyStatusChanged(Request paramRequest);
  
  public abstract boolean canSetImage(Request paramRequest);
  
  public abstract boolean isAnyResourceSet();
  
  public abstract void onRequestSuccess(Request paramRequest);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\RequestCoordinator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */