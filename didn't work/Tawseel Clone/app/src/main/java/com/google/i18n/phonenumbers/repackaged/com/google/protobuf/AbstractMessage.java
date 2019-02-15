package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractMessage
  extends AbstractMessageLite
  implements Message
{
  private int memoizedSize = -1;
  
  private static boolean compareBytes(Object paramObject1, Object paramObject2)
  {
    if (((paramObject1 instanceof byte[])) && ((paramObject2 instanceof byte[]))) {
      return Arrays.equals((byte[])paramObject1, (byte[])paramObject2);
    }
    return toByteString(paramObject1).equals(toByteString(paramObject2));
  }
  
  static boolean compareFields(Map<Descriptors.FieldDescriptor, Object> paramMap1, Map<Descriptors.FieldDescriptor, Object> paramMap2)
  {
    if (paramMap1.size() != paramMap2.size()) {
      return false;
    }
    Iterator localIterator = paramMap1.keySet().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject2 = (Descriptors.FieldDescriptor)localIterator.next();
        if (!paramMap2.containsKey(localObject2)) {
          break;
        }
        Object localObject3 = paramMap1.get(localObject2);
        Object localObject1 = paramMap2.get(localObject2);
        if (((Descriptors.FieldDescriptor)localObject2).getType() == Descriptors.FieldDescriptor.Type.BYTES)
        {
          if (((Descriptors.FieldDescriptor)localObject2).isRepeated())
          {
            localObject2 = (List)localObject3;
            localObject1 = (List)localObject1;
            if (((List)localObject2).size() != ((List)localObject1).size()) {
              break;
            }
            int i = 0;
            for (;;)
            {
              if (i >= ((List)localObject2).size()) {
                break label170;
              }
              if (!compareBytes(((List)localObject2).get(i), ((List)localObject1).get(i))) {
                break;
              }
              i += 1;
            }
            label170:
            continue;
          }
          if (compareBytes(localObject3, localObject1)) {
            continue;
          }
          return false;
        }
        if (!localObject3.equals(localObject1)) {
          return false;
        }
      }
    }
    return true;
  }
  
  protected static int hashFields(int paramInt, Map<Descriptors.FieldDescriptor, Object> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject = (Map.Entry)paramMap.next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
      localObject = ((Map.Entry)localObject).getValue();
      paramInt = paramInt * 37 + localFieldDescriptor.getNumber();
      if (localFieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
        paramInt = paramInt * 53 + localObject.hashCode();
      } else if (localFieldDescriptor.isRepeated()) {
        paramInt = paramInt * 53 + Internal.hashEnumList((List)localObject);
      } else {
        paramInt = paramInt * 53 + Internal.hashEnum((Internal.EnumLite)localObject);
      }
    }
    return paramInt;
  }
  
  private static ByteString toByteString(Object paramObject)
  {
    if ((paramObject instanceof byte[])) {
      return ByteString.copyFrom((byte[])paramObject);
    }
    return (ByteString)paramObject;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof Message)) {
        return false;
      }
      paramObject = (Message)paramObject;
      if (getDescriptorForType() != ((Message)paramObject).getDescriptorForType()) {
        return false;
      }
    } while ((compareFields(getAllFields(), ((Message)paramObject).getAllFields())) && (getUnknownFields().equals(((Message)paramObject).getUnknownFields())));
    return false;
  }
  
  public List<String> findInitializationErrors()
  {
    return MessageReflection.findMissingFields(this);
  }
  
  public String getInitializationErrorString()
  {
    return MessageReflection.delimitWithCommas(findInitializationErrors());
  }
  
  public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor)
  {
    throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
  }
  
  public int getSerializedSize()
  {
    int i = this.memoizedSize;
    if (i != -1) {
      return i;
    }
    this.memoizedSize = MessageReflection.getSerializedSize(this);
    return this.memoizedSize;
  }
  
  public boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
  {
    throw new UnsupportedOperationException("hasOneof() is not implemented.");
  }
  
  public int hashCode()
  {
    int j = this.memoizedHashCode;
    int i = j;
    if (j == 0)
    {
      i = hashFields(getDescriptorForType().hashCode() + 779, getAllFields()) * 29 + getUnknownFields().hashCode();
      this.memoizedHashCode = i;
    }
    return i;
  }
  
  public boolean isInitialized()
  {
    return MessageReflection.isInitialized(this);
  }
  
  UninitializedMessageException newUninitializedMessageException()
  {
    return Builder.newUninitializedMessageException(this);
  }
  
  public final String toString()
  {
    return TextFormat.printToString(this);
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    MessageReflection.writeMessageTo(this, paramCodedOutputStream, false);
  }
  
  public static abstract class Builder<BuilderType extends Builder>
    extends AbstractMessageLite.Builder<BuilderType>
    implements Message.Builder
  {
    protected static UninitializedMessageException newUninitializedMessageException(Message paramMessage)
    {
      return new UninitializedMessageException(MessageReflection.findMissingFields(paramMessage));
    }
    
    public BuilderType clear()
    {
      Iterator localIterator = getAllFields().entrySet().iterator();
      while (localIterator.hasNext()) {
        clearField((Descriptors.FieldDescriptor)((Map.Entry)localIterator.next()).getKey());
      }
      return this;
    }
    
    public BuilderType clearOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      throw new UnsupportedOperationException("clearOneof() is not implemented.");
    }
    
    public abstract BuilderType clone();
    
    public List<String> findInitializationErrors()
    {
      return MessageReflection.findMissingFields(this);
    }
    
    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
    }
    
    public String getInitializationErrorString()
    {
      return MessageReflection.delimitWithCommas(findInitializationErrors());
    }
    
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }
    
    public boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }
    
    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      return super.mergeDelimitedFrom(paramInputStream);
    }
    
    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return super.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramByteString);
    }
    
    public BuilderType mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramByteString, paramExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream, ExtensionRegistry.getEmptyRegistry());
    }
    
    public BuilderType mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
      int i = paramCodedInputStream.readTag();
      if (i == 0) {}
      for (;;)
      {
        setUnknownFields(localBuilder.build());
        return this;
        MessageReflection.BuilderAdapter localBuilderAdapter = new MessageReflection.BuilderAdapter(this);
        if (MessageReflection.mergeFieldFrom(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, getDescriptorForType(), localBuilderAdapter, i)) {
          break;
        }
      }
    }
    
    public BuilderType mergeFrom(Message paramMessage)
    {
      if (paramMessage.getDescriptorForType() != getDescriptorForType()) {
        throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
      }
      Iterator localIterator = paramMessage.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (Map.Entry)localIterator.next();
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
        if (localFieldDescriptor.isRepeated())
        {
          localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
          while (((Iterator)localObject).hasNext()) {
            addRepeatedField(localFieldDescriptor, ((Iterator)localObject).next());
          }
        }
        else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          Message localMessage = (Message)getField(localFieldDescriptor);
          if (localMessage == localMessage.getDefaultInstanceForType()) {
            setField(localFieldDescriptor, ((Map.Entry)localObject).getValue());
          } else {
            setField(localFieldDescriptor, localMessage.newBuilderForType().mergeFrom(localMessage).mergeFrom((Message)((Map.Entry)localObject).getValue()).build());
          }
        }
        else
        {
          setField(localFieldDescriptor, ((Map.Entry)localObject).getValue());
        }
      }
      mergeUnknownFields(paramMessage.getUnknownFields());
      return this;
    }
    
    public BuilderType mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      return (Builder)super.mergeFrom(paramInputStream);
    }
    
    public BuilderType mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return (Builder)super.mergeFrom(paramInputStream, paramExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte);
    }
    
    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramInt1, paramInt2, paramExtensionRegistryLite);
    }
    
    public BuilderType mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramExtensionRegistryLite);
    }
    
    public BuilderType mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(paramUnknownFieldSet).build());
      return this;
    }
    
    public String toString()
    {
      return TextFormat.printToString(this);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\AbstractMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */