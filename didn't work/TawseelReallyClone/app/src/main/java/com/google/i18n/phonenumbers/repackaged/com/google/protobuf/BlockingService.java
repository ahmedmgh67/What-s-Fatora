package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

public abstract interface BlockingService
{
  public abstract Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage)
    throws ServiceException;
  
  public abstract Descriptors.ServiceDescriptor getDescriptorForType();
  
  public abstract Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
  
  public abstract Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\BlockingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */