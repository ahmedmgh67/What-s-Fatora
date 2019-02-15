package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;

public abstract interface Message
  extends MessageLite, MessageOrBuilder
{
  public abstract boolean equals(Object paramObject);
  
  public abstract Parser<? extends Message> getParserForType();
  
  public abstract int hashCode();
  
  public abstract Builder newBuilderForType();
  
  public abstract Builder toBuilder();
  
  public abstract String toString();
  
  public static abstract interface Builder
    extends MessageLite.Builder, MessageOrBuilder
  {
    public abstract Builder addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject);
    
    public abstract Message build();
    
    public abstract Message buildPartial();
    
    public abstract Builder clear();
    
    public abstract Builder clearField(Descriptors.FieldDescriptor paramFieldDescriptor);
    
    public abstract Builder clearOneof(Descriptors.OneofDescriptor paramOneofDescriptor);
    
    public abstract Builder clone();
    
    public abstract Descriptors.Descriptor getDescriptorForType();
    
    public abstract Builder getFieldBuilder(Descriptors.FieldDescriptor paramFieldDescriptor);
    
    public abstract boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException;
    
    public abstract boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;
    
    public abstract Builder mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException;
    
    public abstract Builder mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException;
    
    public abstract Builder mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException;
    
    public abstract Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;
    
    public abstract Builder mergeFrom(Message paramMessage);
    
    public abstract Builder mergeFrom(InputStream paramInputStream)
      throws IOException;
    
    public abstract Builder mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;
    
    public abstract Builder mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException;
    
    public abstract Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException;
    
    public abstract Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException;
    
    public abstract Builder mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException;
    
    public abstract Builder mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet);
    
    public abstract Builder newBuilderForField(Descriptors.FieldDescriptor paramFieldDescriptor);
    
    public abstract Builder setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject);
    
    public abstract Builder setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject);
    
    public abstract Builder setUnknownFields(UnknownFieldSet paramUnknownFieldSet);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\Message.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */