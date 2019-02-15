package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

public abstract interface RpcController
{
  public abstract String errorText();
  
  public abstract boolean failed();
  
  public abstract boolean isCanceled();
  
  public abstract void notifyOnCancel(RpcCallback<Object> paramRpcCallback);
  
  public abstract void reset();
  
  public abstract void setFailed(String paramString);
  
  public abstract void startCancel();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\RpcController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */