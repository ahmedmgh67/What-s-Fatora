package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

public abstract interface Service
{
  public abstract void callMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage, RpcCallback<Message> paramRpcCallback);
  
  public abstract Descriptors.ServiceDescriptor getDescriptorForType();
  
  public abstract Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
  
  public abstract Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\Service.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */