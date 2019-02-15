package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExtensionRegistry
  extends ExtensionRegistryLite
{
  private static final ExtensionRegistry EMPTY = new ExtensionRegistry(true);
  private final Map<String, ExtensionInfo> immutableExtensionsByName;
  private final Map<DescriptorIntPair, ExtensionInfo> immutableExtensionsByNumber;
  private final Map<String, ExtensionInfo> mutableExtensionsByName;
  private final Map<DescriptorIntPair, ExtensionInfo> mutableExtensionsByNumber;
  
  private ExtensionRegistry()
  {
    this.immutableExtensionsByName = new HashMap();
    this.mutableExtensionsByName = new HashMap();
    this.immutableExtensionsByNumber = new HashMap();
    this.mutableExtensionsByNumber = new HashMap();
  }
  
  private ExtensionRegistry(ExtensionRegistry paramExtensionRegistry)
  {
    super(paramExtensionRegistry);
    this.immutableExtensionsByName = Collections.unmodifiableMap(paramExtensionRegistry.immutableExtensionsByName);
    this.mutableExtensionsByName = Collections.unmodifiableMap(paramExtensionRegistry.mutableExtensionsByName);
    this.immutableExtensionsByNumber = Collections.unmodifiableMap(paramExtensionRegistry.immutableExtensionsByNumber);
    this.mutableExtensionsByNumber = Collections.unmodifiableMap(paramExtensionRegistry.mutableExtensionsByNumber);
  }
  
  ExtensionRegistry(boolean paramBoolean)
  {
    super(ExtensionRegistryLite.getEmptyRegistry());
    this.immutableExtensionsByName = Collections.emptyMap();
    this.mutableExtensionsByName = Collections.emptyMap();
    this.immutableExtensionsByNumber = Collections.emptyMap();
    this.mutableExtensionsByNumber = Collections.emptyMap();
  }
  
  private void add(ExtensionInfo paramExtensionInfo, Extension.ExtensionType paramExtensionType)
  {
    if (!paramExtensionInfo.descriptor.isExtension()) {
      throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field.");
    }
    switch (paramExtensionType)
    {
    default: 
      return;
    case ???: 
      paramExtensionType = this.immutableExtensionsByName;
    }
    for (Object localObject = this.immutableExtensionsByNumber;; localObject = this.mutableExtensionsByNumber)
    {
      paramExtensionType.put(paramExtensionInfo.descriptor.getFullName(), paramExtensionInfo);
      ((Map)localObject).put(new DescriptorIntPair(paramExtensionInfo.descriptor.getContainingType(), paramExtensionInfo.descriptor.getNumber()), paramExtensionInfo);
      localObject = paramExtensionInfo.descriptor;
      if ((!((Descriptors.FieldDescriptor)localObject).getContainingType().getOptions().getMessageSetWireFormat()) || (((Descriptors.FieldDescriptor)localObject).getType() != Descriptors.FieldDescriptor.Type.MESSAGE) || (!((Descriptors.FieldDescriptor)localObject).isOptional()) || (((Descriptors.FieldDescriptor)localObject).getExtensionScope() != ((Descriptors.FieldDescriptor)localObject).getMessageType())) {
        break;
      }
      paramExtensionType.put(((Descriptors.FieldDescriptor)localObject).getMessageType().getFullName(), paramExtensionInfo);
      return;
      paramExtensionType = this.mutableExtensionsByName;
    }
  }
  
  public static ExtensionRegistry getEmptyRegistry()
  {
    return EMPTY;
  }
  
  static ExtensionInfo newExtensionInfo(Extension<?, ?> paramExtension)
  {
    if (paramExtension.getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
    {
      if (paramExtension.getMessageDefaultInstance() == null)
      {
        paramExtension = String.valueOf(paramExtension.getDescriptor().getFullName());
        if (paramExtension.length() != 0) {}
        for (paramExtension = "Registered message-type extension had null default instance: ".concat(paramExtension);; paramExtension = new String("Registered message-type extension had null default instance: ")) {
          throw new IllegalStateException(paramExtension);
        }
      }
      return new ExtensionInfo(paramExtension.getDescriptor(), (Message)paramExtension.getMessageDefaultInstance(), null);
    }
    return new ExtensionInfo(paramExtension.getDescriptor(), null, null);
  }
  
  public static ExtensionRegistry newInstance()
  {
    return new ExtensionRegistry();
  }
  
  public void add(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
      throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension.");
    }
    paramFieldDescriptor = new ExtensionInfo(paramFieldDescriptor, null, null);
    add(paramFieldDescriptor, Extension.ExtensionType.IMMUTABLE);
    add(paramFieldDescriptor, Extension.ExtensionType.MUTABLE);
  }
  
  public void add(Descriptors.FieldDescriptor paramFieldDescriptor, Message paramMessage)
  {
    if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
      throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension.");
    }
    add(new ExtensionInfo(paramFieldDescriptor, paramMessage, null), Extension.ExtensionType.IMMUTABLE);
  }
  
  public void add(Extension<?, ?> paramExtension)
  {
    if ((paramExtension.getExtensionType() != Extension.ExtensionType.IMMUTABLE) && (paramExtension.getExtensionType() != Extension.ExtensionType.MUTABLE)) {
      return;
    }
    add(newExtensionInfo(paramExtension), paramExtension.getExtensionType());
  }
  
  public ExtensionInfo findExtensionByName(String paramString)
  {
    return findImmutableExtensionByName(paramString);
  }
  
  public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor paramDescriptor, int paramInt)
  {
    return findImmutableExtensionByNumber(paramDescriptor, paramInt);
  }
  
  public ExtensionInfo findImmutableExtensionByName(String paramString)
  {
    return (ExtensionInfo)this.immutableExtensionsByName.get(paramString);
  }
  
  public ExtensionInfo findImmutableExtensionByNumber(Descriptors.Descriptor paramDescriptor, int paramInt)
  {
    return (ExtensionInfo)this.immutableExtensionsByNumber.get(new DescriptorIntPair(paramDescriptor, paramInt));
  }
  
  public ExtensionInfo findMutableExtensionByName(String paramString)
  {
    return (ExtensionInfo)this.mutableExtensionsByName.get(paramString);
  }
  
  public ExtensionInfo findMutableExtensionByNumber(Descriptors.Descriptor paramDescriptor, int paramInt)
  {
    return (ExtensionInfo)this.mutableExtensionsByNumber.get(new DescriptorIntPair(paramDescriptor, paramInt));
  }
  
  public Set<ExtensionInfo> getAllImmutableExtensionsByExtendedType(String paramString)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.immutableExtensionsByNumber.keySet().iterator();
    while (localIterator.hasNext())
    {
      DescriptorIntPair localDescriptorIntPair = (DescriptorIntPair)localIterator.next();
      if (localDescriptorIntPair.descriptor.getFullName().equals(paramString)) {
        localHashSet.add(this.immutableExtensionsByNumber.get(localDescriptorIntPair));
      }
    }
    return localHashSet;
  }
  
  public Set<ExtensionInfo> getAllMutableExtensionsByExtendedType(String paramString)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.mutableExtensionsByNumber.keySet().iterator();
    while (localIterator.hasNext())
    {
      DescriptorIntPair localDescriptorIntPair = (DescriptorIntPair)localIterator.next();
      if (localDescriptorIntPair.descriptor.getFullName().equals(paramString)) {
        localHashSet.add(this.mutableExtensionsByNumber.get(localDescriptorIntPair));
      }
    }
    return localHashSet;
  }
  
  public ExtensionRegistry getUnmodifiable()
  {
    return new ExtensionRegistry(this);
  }
  
  private static final class DescriptorIntPair
  {
    private final Descriptors.Descriptor descriptor;
    private final int number;
    
    DescriptorIntPair(Descriptors.Descriptor paramDescriptor, int paramInt)
    {
      this.descriptor = paramDescriptor;
      this.number = paramInt;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof DescriptorIntPair)) {}
      do
      {
        return false;
        paramObject = (DescriptorIntPair)paramObject;
      } while ((this.descriptor != ((DescriptorIntPair)paramObject).descriptor) || (this.number != ((DescriptorIntPair)paramObject).number));
      return true;
    }
    
    public int hashCode()
    {
      return this.descriptor.hashCode() * 65535 + this.number;
    }
  }
  
  public static final class ExtensionInfo
  {
    public final Message defaultInstance;
    public final Descriptors.FieldDescriptor descriptor;
    
    private ExtensionInfo(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      this.descriptor = paramFieldDescriptor;
      this.defaultInstance = null;
    }
    
    private ExtensionInfo(Descriptors.FieldDescriptor paramFieldDescriptor, Message paramMessage)
    {
      this.descriptor = paramFieldDescriptor;
      this.defaultInstance = paramMessage;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\ExtensionRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */