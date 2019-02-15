package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class GeneratedMessage
  extends AbstractMessage
  implements Serializable
{
  protected static boolean alwaysUseFieldBuilders = false;
  private static final long serialVersionUID = 1L;
  
  protected GeneratedMessage() {}
  
  protected GeneratedMessage(Builder<?> paramBuilder) {}
  
  static void enableAlwaysUseFieldBuildersForTesting()
  {
    alwaysUseFieldBuilders = true;
  }
  
  private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable()
  {
    TreeMap localTreeMap = new TreeMap();
    Iterator localIterator = internalGetFieldAccessorTable().descriptor.getFields().iterator();
    while (localIterator.hasNext())
    {
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
      if (localFieldDescriptor.isRepeated())
      {
        List localList = (List)getField(localFieldDescriptor);
        if (!localList.isEmpty()) {
          localTreeMap.put(localFieldDescriptor, localList);
        }
      }
      else if (hasField(localFieldDescriptor))
      {
        localTreeMap.put(localFieldDescriptor, getField(localFieldDescriptor));
      }
    }
    return localTreeMap;
  }
  
  private static Method getMethodOrDie(Class paramClass, String paramString, Class... paramVarArgs)
  {
    try
    {
      paramVarArgs = paramClass.getMethod(paramString, paramVarArgs);
      return paramVarArgs;
    }
    catch (NoSuchMethodException paramVarArgs)
    {
      paramClass = String.valueOf(paramClass.getName());
      throw new RuntimeException(String.valueOf(paramClass).length() + 45 + String.valueOf(paramString).length() + "Generated message class \"" + paramClass + "\" missing method \"" + paramString + "\".", paramVarArgs);
    }
  }
  
  private static Object invokeOrDie(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
    try
    {
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      return paramMethod;
    }
    catch (IllegalAccessException paramMethod)
    {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", paramMethod);
    }
    catch (InvocationTargetException paramMethod)
    {
      paramMethod = paramMethod.getCause();
      if ((paramMethod instanceof RuntimeException)) {
        throw ((RuntimeException)paramMethod);
      }
      if ((paramMethod instanceof Error)) {
        throw ((Error)paramMethod);
      }
      throw new RuntimeException("Unexpected exception thrown by generated accessor method.", paramMethod);
    }
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class paramClass, Message paramMessage)
  {
    return new GeneratedExtension(null, paramClass, paramMessage, Extension.ExtensionType.IMMUTABLE);
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class paramClass, Message paramMessage, final String paramString1, final String paramString2)
  {
    new GeneratedExtension(new CachedDescriptorRetriever(paramClass)
    {
      protected Descriptors.FieldDescriptor loadDescriptor()
      {
        try
        {
          Descriptors.FieldDescriptor localFieldDescriptor = ((Descriptors.FileDescriptor)this.val$singularType.getClassLoader().loadClass(paramString1).getField("descriptor").get(null)).findExtensionByName(paramString2);
          return localFieldDescriptor;
        }
        catch (Exception localException)
        {
          String str = paramString1;
          throw new RuntimeException(String.valueOf(str).length() + 62 + "Cannot load descriptors: " + str + " is not a valid descriptor class name", localException);
        }
      }
    }, paramClass, paramMessage, Extension.ExtensionType.MUTABLE);
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(Message paramMessage1, final int paramInt, Class paramClass, Message paramMessage2)
  {
    new GeneratedExtension(new CachedDescriptorRetriever(paramMessage1)
    {
      public Descriptors.FieldDescriptor loadDescriptor()
      {
        return (Descriptors.FieldDescriptor)this.val$scope.getDescriptorForType().getExtensions().get(paramInt);
      }
    }, paramClass, paramMessage2, Extension.ExtensionType.IMMUTABLE);
  }
  
  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(Message paramMessage1, final String paramString, Class paramClass, Message paramMessage2)
  {
    new GeneratedExtension(new CachedDescriptorRetriever(paramMessage1)
    {
      protected Descriptors.FieldDescriptor loadDescriptor()
      {
        return this.val$scope.getDescriptorForType().findFieldByName(paramString);
      }
    }, paramClass, paramMessage2, Extension.ExtensionType.MUTABLE);
  }
  
  public Map<Descriptors.FieldDescriptor, Object> getAllFields()
  {
    return Collections.unmodifiableMap(getAllFieldsMutable());
  }
  
  public Descriptors.Descriptor getDescriptorForType()
  {
    return internalGetFieldAccessorTable().descriptor;
  }
  
  public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).get(this);
  }
  
  public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor)
  {
    return internalGetFieldAccessorTable().getOneof(paramOneofDescriptor).get(this);
  }
  
  public Parser<? extends GeneratedMessage> getParserForType()
  {
    throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
  }
  
  public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeated(this, paramInt);
  }
  
  public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeatedCount(this);
  }
  
  public UnknownFieldSet getUnknownFields()
  {
    throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
  }
  
  public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).has(this);
  }
  
  public boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
  {
    return internalGetFieldAccessorTable().getOneof(paramOneofDescriptor).has(this);
  }
  
  protected abstract FieldAccessorTable internalGetFieldAccessorTable();
  
  public boolean isInitialized()
  {
    Iterator localIterator = getDescriptorForType().getFields().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject = (Descriptors.FieldDescriptor)localIterator.next();
        if ((((Descriptors.FieldDescriptor)localObject).isRequired()) && (!hasField((Descriptors.FieldDescriptor)localObject))) {
          return false;
        }
        if (((Descriptors.FieldDescriptor)localObject).getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          if (((Descriptors.FieldDescriptor)localObject).isRepeated())
          {
            localObject = ((List)getField((Descriptors.FieldDescriptor)localObject)).iterator();
            if (!((Iterator)localObject).hasNext()) {
              continue;
            }
            if (((Message)((Iterator)localObject).next()).isInitialized()) {
              break;
            }
            return false;
          }
          if ((hasField((Descriptors.FieldDescriptor)localObject)) && (!((Message)getField((Descriptors.FieldDescriptor)localObject)).isInitialized())) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  protected void makeExtensionsImmutable() {}
  
  protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);
  
  protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
    throws IOException
  {
    return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
  }
  
  protected Object writeReplace()
    throws ObjectStreamException
  {
    return new GeneratedMessageLite.SerializedForm(this);
  }
  
  public static abstract class Builder<BuilderType extends Builder>
    extends AbstractMessage.Builder<BuilderType>
  {
    private GeneratedMessage.BuilderParent builderParent;
    private boolean isClean;
    private Builder<BuilderType>.BuilderParentImpl meAsParent;
    private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
    
    protected Builder()
    {
      this(null);
    }
    
    protected Builder(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      this.builderParent = paramBuilderParent;
    }
    
    private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable()
    {
      TreeMap localTreeMap = new TreeMap();
      Iterator localIterator = GeneratedMessage.FieldAccessorTable.access$000(internalGetFieldAccessorTable()).getFields().iterator();
      while (localIterator.hasNext())
      {
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
        if (localFieldDescriptor.isRepeated())
        {
          List localList = (List)getField(localFieldDescriptor);
          if (!localList.isEmpty()) {
            localTreeMap.put(localFieldDescriptor, localList);
          }
        }
        else if (hasField(localFieldDescriptor))
        {
          localTreeMap.put(localFieldDescriptor, getField(localFieldDescriptor));
        }
      }
      return localTreeMap;
    }
    
    public BuilderType addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).addRepeated(this, paramObject);
      return this;
    }
    
    public BuilderType clear()
    {
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
      onChanged();
      return this;
    }
    
    public BuilderType clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).clear(this);
      return this;
    }
    
    public BuilderType clearOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramOneofDescriptor).clear(this);
      return this;
    }
    
    public BuilderType clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }
    
    void dispose()
    {
      this.builderParent = null;
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      return Collections.unmodifiableMap(getAllFieldsMutable());
    }
    
    public Descriptors.Descriptor getDescriptorForType()
    {
      return GeneratedMessage.FieldAccessorTable.access$000(internalGetFieldAccessorTable());
    }
    
    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      Object localObject2 = GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).get(this);
      Object localObject1 = localObject2;
      if (paramFieldDescriptor.isRepeated()) {
        localObject1 = Collections.unmodifiableList((List)localObject2);
      }
      return localObject1;
    }
    
    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).getBuilder(this);
    }
    
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramOneofDescriptor).get(this);
    }
    
    protected GeneratedMessage.BuilderParent getParentForChildren()
    {
      if (this.meAsParent == null) {
        this.meAsParent = new BuilderParentImpl(null);
      }
      return this.meAsParent;
    }
    
    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      return GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).getRepeated(this, paramInt);
    }
    
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).getRepeatedCount(this);
    }
    
    public final UnknownFieldSet getUnknownFields()
    {
      return this.unknownFields;
    }
    
    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).has(this);
    }
    
    public boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramOneofDescriptor).has(this);
    }
    
    protected abstract GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable();
    
    protected boolean isClean()
    {
      return this.isClean;
    }
    
    public boolean isInitialized()
    {
      Iterator localIterator = getDescriptorForType().getFields().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          Object localObject = (Descriptors.FieldDescriptor)localIterator.next();
          if ((((Descriptors.FieldDescriptor)localObject).isRequired()) && (!hasField((Descriptors.FieldDescriptor)localObject))) {
            return false;
          }
          if (((Descriptors.FieldDescriptor)localObject).getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          {
            if (((Descriptors.FieldDescriptor)localObject).isRepeated())
            {
              localObject = ((List)getField((Descriptors.FieldDescriptor)localObject)).iterator();
              if (!((Iterator)localObject).hasNext()) {
                continue;
              }
              if (((Message)((Iterator)localObject).next()).isInitialized()) {
                break;
              }
              return false;
            }
            if ((hasField((Descriptors.FieldDescriptor)localObject)) && (!((Message)getField((Descriptors.FieldDescriptor)localObject)).isInitialized())) {
              return false;
            }
          }
        }
      }
      return true;
    }
    
    protected void markClean()
    {
      this.isClean = true;
    }
    
    public final BuilderType mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(paramUnknownFieldSet).build();
      onChanged();
      return this;
    }
    
    public Message.Builder newBuilderForField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).newBuilder();
    }
    
    protected void onBuilt()
    {
      if (this.builderParent != null) {
        markClean();
      }
    }
    
    protected final void onChanged()
    {
      if ((this.isClean) && (this.builderParent != null))
      {
        this.builderParent.markDirty();
        this.isClean = false;
      }
    }
    
    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
    }
    
    public BuilderType setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).set(this, paramObject);
      return this;
    }
    
    public BuilderType setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      GeneratedMessage.FieldAccessorTable.access$200(internalGetFieldAccessorTable(), paramFieldDescriptor).setRepeated(this, paramInt, paramObject);
      return this;
    }
    
    public final BuilderType setUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = paramUnknownFieldSet;
      onChanged();
      return this;
    }
    
    private class BuilderParentImpl
      implements GeneratedMessage.BuilderParent
    {
      private BuilderParentImpl() {}
      
      public void markDirty()
      {
        GeneratedMessage.Builder.this.onChanged();
      }
    }
  }
  
  protected static abstract interface BuilderParent
  {
    public abstract void markDirty();
  }
  
  private static abstract class CachedDescriptorRetriever
    implements GeneratedMessage.ExtensionDescriptorRetriever
  {
    private volatile Descriptors.FieldDescriptor descriptor;
    
    public Descriptors.FieldDescriptor getDescriptor()
    {
      if (this.descriptor == null) {}
      try
      {
        if (this.descriptor == null) {
          this.descriptor = loadDescriptor();
        }
        return this.descriptor;
      }
      finally {}
    }
    
    protected abstract Descriptors.FieldDescriptor loadDescriptor();
  }
  
  public static abstract class ExtendableBuilder<MessageType extends GeneratedMessage.ExtendableMessage, BuilderType extends ExtendableBuilder>
    extends GeneratedMessage.Builder<BuilderType>
    implements GeneratedMessage.ExtendableMessageOrBuilder<MessageType>
  {
    private FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.emptySet();
    
    protected ExtendableBuilder() {}
    
    protected ExtendableBuilder(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      super();
    }
    
    private FieldSet<Descriptors.FieldDescriptor> buildExtensions()
    {
      this.extensions.makeImmutable();
      return this.extensions;
    }
    
    private void ensureExtensionsIsMutable()
    {
      if (this.extensions.isImmutable()) {
        this.extensions = this.extensions.clone();
      }
    }
    
    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != getDescriptorForType()) {
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
      }
    }
    
    private void verifyExtensionContainingType(Extension<MessageType, ?> paramExtension)
    {
      if (paramExtension.getDescriptor().getContainingType() != getDescriptorForType())
      {
        paramExtension = paramExtension.getDescriptor().getContainingType().getFullName();
        String str = getDescriptorForType().getFullName();
        throw new IllegalArgumentException(String.valueOf(paramExtension).length() + 62 + String.valueOf(str).length() + "Extension is for type \"" + paramExtension + "\" which does not match message type \"" + str + "\".");
      }
    }
    
    public final <Type> BuilderType addExtension(Extension<MessageType, List<Type>> paramExtension, Type paramType)
    {
      verifyExtensionContainingType(paramExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      this.extensions.addRepeatedField(localFieldDescriptor, paramExtension.singularToReflectionType(paramType));
      onChanged();
      return this;
    }
    
    public BuilderType addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.addRepeatedField(paramFieldDescriptor, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.addRepeatedField(paramFieldDescriptor, paramObject);
    }
    
    public BuilderType clear()
    {
      this.extensions = FieldSet.emptySet();
      return (ExtendableBuilder)super.clear();
    }
    
    public final <Type> BuilderType clearExtension(Extension<MessageType, ?> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      ensureExtensionsIsMutable();
      this.extensions.clearField(paramExtension.getDescriptor());
      onChanged();
      return this;
    }
    
    public BuilderType clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.clearField(paramFieldDescriptor);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.clearField(paramFieldDescriptor);
    }
    
    public BuilderType clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }
    
    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      Map localMap = super.getAllFieldsMutable();
      localMap.putAll(this.extensions.getAllFields());
      return Collections.unmodifiableMap(localMap);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, Type> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      Object localObject = this.extensions.getField(localFieldDescriptor);
      if (localObject == null)
      {
        if (localFieldDescriptor.isRepeated()) {
          return Collections.emptyList();
        }
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
          return paramExtension.getMessageDefaultInstance();
        }
        return (Type)paramExtension.fromReflectionType(localFieldDescriptor.getDefaultValue());
      }
      return (Type)paramExtension.fromReflectionType(localObject);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, List<Type>> paramExtension, int paramInt)
    {
      verifyExtensionContainingType(paramExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      return (Type)paramExtension.singularFromReflectionType(this.extensions.getRepeatedField(localFieldDescriptor, paramInt));
    }
    
    public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      paramExtension = paramExtension.getDescriptor();
      return this.extensions.getRepeatedFieldCount(paramExtension);
    }
    
    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        Object localObject2 = this.extensions.getField(paramFieldDescriptor);
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
          if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            localObject1 = DynamicMessage.getDefaultInstance(paramFieldDescriptor.getMessageType());
          }
        }
        else {
          return localObject1;
        }
        return paramFieldDescriptor.getDefaultValue();
      }
      return super.getField(paramFieldDescriptor);
    }
    
    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedField(paramFieldDescriptor, paramInt);
      }
      return super.getRepeatedField(paramFieldDescriptor, paramInt);
    }
    
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedFieldCount(paramFieldDescriptor);
      }
      return super.getRepeatedFieldCount(paramFieldDescriptor);
    }
    
    public final <Type> boolean hasExtension(Extension<MessageType, Type> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      return this.extensions.hasField(paramExtension.getDescriptor());
    }
    
    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.hasField(paramFieldDescriptor);
      }
      return super.hasField(paramFieldDescriptor);
    }
    
    void internalSetExtensionSet(FieldSet<Descriptors.FieldDescriptor> paramFieldSet)
    {
      this.extensions = paramFieldSet;
    }
    
    public boolean isInitialized()
    {
      return (super.isInitialized()) && (extensionsAreInitialized());
    }
    
    protected final void mergeExtensionFields(GeneratedMessage.ExtendableMessage paramExtendableMessage)
    {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(GeneratedMessage.ExtendableMessage.access$500(paramExtendableMessage));
      onChanged();
    }
    
    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return MessageReflection.mergeFieldFrom(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, getDescriptorForType(), new MessageReflection.BuilderAdapter(this), paramInt);
    }
    
    public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> paramExtension, int paramInt, Type paramType)
    {
      verifyExtensionContainingType(paramExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      this.extensions.setRepeatedField(localFieldDescriptor, paramInt, paramExtension.singularToReflectionType(paramType));
      onChanged();
      return this;
    }
    
    public final <Type> BuilderType setExtension(Extension<MessageType, Type> paramExtension, Type paramType)
    {
      verifyExtensionContainingType(paramExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      this.extensions.setField(localFieldDescriptor, paramExtension.toReflectionType(paramType));
      onChanged();
      return this;
    }
    
    public BuilderType setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setField(paramFieldDescriptor, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.setField(paramFieldDescriptor, paramObject);
    }
    
    public BuilderType setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
    }
  }
  
  public static abstract class ExtendableMessage<MessageType extends ExtendableMessage>
    extends GeneratedMessage
    implements GeneratedMessage.ExtendableMessageOrBuilder<MessageType>
  {
    private final FieldSet<Descriptors.FieldDescriptor> extensions;
    
    protected ExtendableMessage()
    {
      this.extensions = FieldSet.newFieldSet();
    }
    
    protected ExtendableMessage(GeneratedMessage.ExtendableBuilder<MessageType, ?> paramExtendableBuilder)
    {
      super();
      this.extensions = paramExtendableBuilder.buildExtensions();
    }
    
    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != getDescriptorForType()) {
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
      }
    }
    
    private void verifyExtensionContainingType(Extension<MessageType, ?> paramExtension)
    {
      if (paramExtension.getDescriptor().getContainingType() != getDescriptorForType())
      {
        paramExtension = paramExtension.getDescriptor().getContainingType().getFullName();
        String str = getDescriptorForType().getFullName();
        throw new IllegalArgumentException(String.valueOf(paramExtension).length() + 62 + String.valueOf(str).length() + "Extension is for type \"" + paramExtension + "\" which does not match message type \"" + str + "\".");
      }
    }
    
    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }
    
    protected int extensionsSerializedSize()
    {
      return this.extensions.getSerializedSize();
    }
    
    protected int extensionsSerializedSizeAsMessageSet()
    {
      return this.extensions.getMessageSetSerializedSize();
    }
    
    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      Map localMap = super.getAllFieldsMutable();
      localMap.putAll(getExtensionFields());
      return Collections.unmodifiableMap(localMap);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, Type> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      Object localObject = this.extensions.getField(localFieldDescriptor);
      if (localObject == null)
      {
        if (localFieldDescriptor.isRepeated()) {
          return Collections.emptyList();
        }
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
          return paramExtension.getMessageDefaultInstance();
        }
        return (Type)paramExtension.fromReflectionType(localFieldDescriptor.getDefaultValue());
      }
      return (Type)paramExtension.fromReflectionType(localObject);
    }
    
    public final <Type> Type getExtension(Extension<MessageType, List<Type>> paramExtension, int paramInt)
    {
      verifyExtensionContainingType(paramExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtension.getDescriptor();
      return (Type)paramExtension.singularFromReflectionType(this.extensions.getRepeatedField(localFieldDescriptor, paramInt));
    }
    
    public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      paramExtension = paramExtension.getDescriptor();
      return this.extensions.getRepeatedFieldCount(paramExtension);
    }
    
    protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields()
    {
      return this.extensions.getAllFields();
    }
    
    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        Object localObject2 = this.extensions.getField(paramFieldDescriptor);
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
          if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            localObject1 = DynamicMessage.getDefaultInstance(paramFieldDescriptor.getMessageType());
          }
        }
        else {
          return localObject1;
        }
        return paramFieldDescriptor.getDefaultValue();
      }
      return super.getField(paramFieldDescriptor);
    }
    
    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedField(paramFieldDescriptor, paramInt);
      }
      return super.getRepeatedField(paramFieldDescriptor, paramInt);
    }
    
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedFieldCount(paramFieldDescriptor);
      }
      return super.getRepeatedFieldCount(paramFieldDescriptor);
    }
    
    public final <Type> boolean hasExtension(Extension<MessageType, Type> paramExtension)
    {
      verifyExtensionContainingType(paramExtension);
      return this.extensions.hasField(paramExtension.getDescriptor());
    }
    
    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.hasField(paramFieldDescriptor);
      }
      return super.hasField(paramFieldDescriptor);
    }
    
    public boolean isInitialized()
    {
      return (super.isInitialized()) && (extensionsAreInitialized());
    }
    
    protected void makeExtensionsImmutable()
    {
      this.extensions.makeImmutable();
    }
    
    protected ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter()
    {
      return new ExtensionWriter(false, null);
    }
    
    protected ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter()
    {
      return new ExtensionWriter(true, null);
    }
    
    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return MessageReflection.mergeFieldFrom(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), paramInt);
    }
    
    protected class ExtensionWriter
    {
      private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessage.ExtendableMessage.this.extensions.iterator();
      private final boolean messageSetWireFormat;
      private Map.Entry<Descriptors.FieldDescriptor, Object> next;
      
      private ExtensionWriter(boolean paramBoolean)
      {
        if (this.iter.hasNext()) {
          this.next = ((Map.Entry)this.iter.next());
        }
        this.messageSetWireFormat = paramBoolean;
      }
      
      public void writeUntil(int paramInt, CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        while ((this.next != null) && (((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < paramInt))
        {
          Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)this.next.getKey();
          if ((this.messageSetWireFormat) && (localFieldDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptor.isRepeated())) {
            if ((this.next instanceof LazyField.LazyEntry)) {
              paramCodedOutputStream.writeRawMessageSetExtension(localFieldDescriptor.getNumber(), ((LazyField.LazyEntry)this.next).getField().toByteString());
            }
          }
          for (;;)
          {
            if (!this.iter.hasNext()) {
              break label165;
            }
            this.next = ((Map.Entry)this.iter.next());
            break;
            paramCodedOutputStream.writeMessageSetExtension(localFieldDescriptor.getNumber(), (Message)this.next.getValue());
            continue;
            FieldSet.writeField(localFieldDescriptor, this.next.getValue(), paramCodedOutputStream);
          }
          label165:
          this.next = null;
        }
      }
    }
  }
  
  public static abstract interface ExtendableMessageOrBuilder<MessageType extends GeneratedMessage.ExtendableMessage>
    extends MessageOrBuilder
  {
    public abstract Message getDefaultInstanceForType();
    
    public abstract <Type> Type getExtension(Extension<MessageType, Type> paramExtension);
    
    public abstract <Type> Type getExtension(Extension<MessageType, List<Type>> paramExtension, int paramInt);
    
    public abstract <Type> int getExtensionCount(Extension<MessageType, List<Type>> paramExtension);
    
    public abstract <Type> boolean hasExtension(Extension<MessageType, Type> paramExtension);
  }
  
  static abstract interface ExtensionDescriptorRetriever
  {
    public abstract Descriptors.FieldDescriptor getDescriptor();
  }
  
  public static final class FieldAccessorTable
  {
    private String[] camelCaseNames;
    private final Descriptors.Descriptor descriptor;
    private final FieldAccessor[] fields;
    private volatile boolean initialized;
    private final OneofAccessor[] oneofs;
    
    public FieldAccessorTable(Descriptors.Descriptor paramDescriptor, String[] paramArrayOfString)
    {
      this.descriptor = paramDescriptor;
      this.camelCaseNames = paramArrayOfString;
      this.fields = new FieldAccessor[paramDescriptor.getFields().size()];
      this.oneofs = new OneofAccessor[paramDescriptor.getOneofs().size()];
      this.initialized = false;
    }
    
    public FieldAccessorTable(Descriptors.Descriptor paramDescriptor, String[] paramArrayOfString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
    {
      this(paramDescriptor, paramArrayOfString);
      ensureFieldAccessorsInitialized(paramClass, paramClass1);
    }
    
    private FieldAccessor getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != this.descriptor) {
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
      }
      if (paramFieldDescriptor.isExtension()) {
        throw new IllegalArgumentException("This type does not have extensions.");
      }
      return this.fields[paramFieldDescriptor.getIndex()];
    }
    
    private OneofAccessor getOneof(Descriptors.OneofDescriptor paramOneofDescriptor)
    {
      if (paramOneofDescriptor.getContainingType() != this.descriptor) {
        throw new IllegalArgumentException("OneofDescriptor does not match message type.");
      }
      return this.oneofs[paramOneofDescriptor.getIndex()];
    }
    
    private static boolean supportFieldPresence(Descriptors.FileDescriptor paramFileDescriptor)
    {
      return true;
    }
    
    public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
    {
      if (this.initialized) {
        return this;
      }
      try
      {
        if (this.initialized) {
          return this;
        }
      }
      finally {}
      int j = this.fields.length;
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)this.descriptor.getFields().get(i);
          String str = null;
          if (localFieldDescriptor.getContainingOneof() != null) {
            str = this.camelCaseNames[(localFieldDescriptor.getContainingOneof().getIndex() + j)];
          }
          if (localFieldDescriptor.isRepeated())
          {
            if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
              this.fields[i] = new RepeatedMessageFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
            } else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
              this.fields[i] = new RepeatedEnumFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
            } else {
              this.fields[i] = new RepeatedFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
            }
          }
          else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            this.fields[i] = new SingularMessageFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1, str);
          } else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
            this.fields[i] = new SingularEnumFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1, str);
          } else {
            this.fields[i] = new SingularFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1, str);
          }
        }
        else
        {
          int k = this.oneofs.length;
          i = 0;
          while (i < k)
          {
            this.oneofs[i] = new OneofAccessor(this.descriptor, this.camelCaseNames[(i + j)], paramClass, paramClass1);
            i += 1;
          }
          this.initialized = true;
          this.camelCaseNames = null;
          return this;
        }
        i += 1;
      }
    }
    
    private static abstract interface FieldAccessor
    {
      public abstract void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject);
      
      public abstract void clear(GeneratedMessage.Builder paramBuilder);
      
      public abstract Object get(GeneratedMessage.Builder paramBuilder);
      
      public abstract Object get(GeneratedMessage paramGeneratedMessage);
      
      public abstract Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder);
      
      public abstract Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt);
      
      public abstract Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt);
      
      public abstract int getRepeatedCount(GeneratedMessage.Builder paramBuilder);
      
      public abstract int getRepeatedCount(GeneratedMessage paramGeneratedMessage);
      
      public abstract boolean has(GeneratedMessage.Builder paramBuilder);
      
      public abstract boolean has(GeneratedMessage paramGeneratedMessage);
      
      public abstract Message.Builder newBuilder();
      
      public abstract void set(GeneratedMessage.Builder paramBuilder, Object paramObject);
      
      public abstract void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject);
    }
    
    private static class OneofAccessor
    {
      private final Method caseMethod;
      private final Method caseMethodBuilder;
      private final Method clearMethod;
      private final Descriptors.Descriptor descriptor;
      
      OneofAccessor(Descriptors.Descriptor paramDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        this.descriptor = paramDescriptor;
        this.caseMethod = GeneratedMessage.getMethodOrDie(paramClass, String.valueOf(paramString).length() + 7 + "get" + paramString + "Case", new Class[0]);
        this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, String.valueOf(paramString).length() + 7 + "get" + paramString + "Case", new Class[0]);
        paramDescriptor = String.valueOf(paramString);
        if (paramDescriptor.length() != 0) {}
        for (paramDescriptor = "clear".concat(paramDescriptor);; paramDescriptor = new String("clear"))
        {
          this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass1, paramDescriptor, new Class[0]);
          return;
        }
      }
      
      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }
      
      public Descriptors.FieldDescriptor get(GeneratedMessage.Builder paramBuilder)
      {
        int i = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, paramBuilder, new Object[0])).getNumber();
        if (i > 0) {
          return this.descriptor.findFieldByNumber(i);
        }
        return null;
      }
      
      public Descriptors.FieldDescriptor get(GeneratedMessage paramGeneratedMessage)
      {
        int i = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, paramGeneratedMessage, new Object[0])).getNumber();
        if (i > 0) {
          return this.descriptor.findFieldByNumber(i);
        }
        return null;
      }
      
      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, paramBuilder, new Object[0])).getNumber() != 0;
      }
      
      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, paramGeneratedMessage, new Object[0])).getNumber() != 0;
      }
    }
    
    private static final class RepeatedEnumFieldAccessor
      extends GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
    {
      private final Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      private final Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      
      RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        super(paramString, paramClass, paramClass1);
      }
      
      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.addRepeated(paramBuilder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
      
      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        ArrayList localArrayList = new ArrayList();
        paramBuilder = ((List)super.get(paramBuilder)).iterator();
        while (paramBuilder.hasNext())
        {
          Object localObject = paramBuilder.next();
          localArrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, localObject, new Object[0]));
        }
        return Collections.unmodifiableList(localArrayList);
      }
      
      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        ArrayList localArrayList = new ArrayList();
        paramGeneratedMessage = ((List)super.get(paramGeneratedMessage)).iterator();
        while (paramGeneratedMessage.hasNext())
        {
          Object localObject = paramGeneratedMessage.next();
          localArrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, localObject, new Object[0]));
        }
        return Collections.unmodifiableList(localArrayList);
      }
      
      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(paramBuilder, paramInt), new Object[0]);
      }
      
      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(paramGeneratedMessage, paramInt), new Object[0]);
      }
      
      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        super.setRepeated(paramBuilder, paramInt, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
    }
    
    private static class RepeatedFieldAccessor
      implements GeneratedMessage.FieldAccessorTable.FieldAccessor
    {
      protected final Method addRepeatedMethod;
      protected final Method clearMethod;
      protected final Method getCountMethod;
      protected final Method getCountMethodBuilder;
      protected final Method getMethod;
      protected final Method getMethodBuilder;
      protected final Method getRepeatedMethod;
      protected final Method getRepeatedMethodBuilder;
      protected final Method setRepeatedMethod;
      protected final Class type;
      
      RepeatedFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        this.getMethod = GeneratedMessage.getMethodOrDie(paramClass, String.valueOf(paramString).length() + 7 + "get" + paramString + "List", new Class[0]);
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, String.valueOf(paramString).length() + 7 + "get" + paramString + "List", new Class[0]);
        paramFieldDescriptor = String.valueOf(paramString);
        if (paramFieldDescriptor.length() != 0)
        {
          paramFieldDescriptor = "get".concat(paramFieldDescriptor);
          this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass, paramFieldDescriptor, new Class[] { Integer.TYPE });
          paramFieldDescriptor = String.valueOf(paramString);
          if (paramFieldDescriptor.length() == 0) {
            break label411;
          }
          paramFieldDescriptor = "get".concat(paramFieldDescriptor);
          label154:
          this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[] { Integer.TYPE });
          this.type = this.getRepeatedMethod.getReturnType();
          paramFieldDescriptor = String.valueOf(paramString);
          if (paramFieldDescriptor.length() == 0) {
            break label424;
          }
          paramFieldDescriptor = "set".concat(paramFieldDescriptor);
          label204:
          this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[] { Integer.TYPE, this.type });
          paramFieldDescriptor = String.valueOf(paramString);
          if (paramFieldDescriptor.length() == 0) {
            break label437;
          }
          paramFieldDescriptor = "add".concat(paramFieldDescriptor);
          label250:
          this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[] { this.type });
          this.getCountMethod = GeneratedMessage.getMethodOrDie(paramClass, String.valueOf(paramString).length() + 8 + "get" + paramString + "Count", new Class[0]);
          this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, String.valueOf(paramString).length() + 8 + "get" + paramString + "Count", new Class[0]);
          paramFieldDescriptor = String.valueOf(paramString);
          if (paramFieldDescriptor.length() == 0) {
            break label450;
          }
        }
        label411:
        label424:
        label437:
        label450:
        for (paramFieldDescriptor = "clear".concat(paramFieldDescriptor);; paramFieldDescriptor = new String("clear"))
        {
          this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[0]);
          return;
          paramFieldDescriptor = new String("get");
          break;
          paramFieldDescriptor = new String("get");
          break label154;
          paramFieldDescriptor = new String("set");
          break label204;
          paramFieldDescriptor = new String("add");
          break label250;
        }
      }
      
      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.addRepeatedMethod, paramBuilder, new Object[] { paramObject });
      }
      
      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }
      
      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, paramBuilder, new Object[0]);
      }
      
      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getMethod, paramGeneratedMessage, new Object[0]);
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }
      
      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, paramBuilder, new Object[] { Integer.valueOf(paramInt) });
      }
      
      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, paramGeneratedMessage, new Object[] { Integer.valueOf(paramInt) });
      }
      
      public int getRepeatedCount(GeneratedMessage.Builder paramBuilder)
      {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, paramBuilder, new Object[0])).intValue();
      }
      
      public int getRepeatedCount(GeneratedMessage paramGeneratedMessage)
      {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, paramGeneratedMessage, new Object[0])).intValue();
      }
      
      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }
      
      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }
      
      public Message.Builder newBuilder()
      {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
      
      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        clear(paramBuilder);
        paramObject = ((List)paramObject).iterator();
        while (((Iterator)paramObject).hasNext()) {
          addRepeated(paramBuilder, ((Iterator)paramObject).next());
        }
      }
      
      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.setRepeatedMethod, paramBuilder, new Object[] { Integer.valueOf(paramInt), paramObject });
      }
    }
    
    private static final class RepeatedMessageFieldAccessor
      extends GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
    {
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
      
      RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        super(paramString, paramClass, paramClass1);
      }
      
      private Object coerceType(Object paramObject)
      {
        if (this.type.isInstance(paramObject)) {
          return paramObject;
        }
        return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)paramObject).build();
      }
      
      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.addRepeated(paramBuilder, coerceType(paramObject));
      }
      
      public Message.Builder newBuilder()
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
      
      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        super.setRepeated(paramBuilder, paramInt, coerceType(paramObject));
      }
    }
    
    private static final class SingularEnumFieldAccessor
      extends GeneratedMessage.FieldAccessorTable.SingularFieldAccessor
    {
      private Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      private Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      
      SingularEnumFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString1, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1, String paramString2)
      {
        super(paramString1, paramClass, paramClass1, paramString2);
      }
      
      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(paramBuilder), new Object[0]);
      }
      
      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(paramGeneratedMessage), new Object[0]);
      }
      
      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.set(paramBuilder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
    }
    
    private static class SingularFieldAccessor
      implements GeneratedMessage.FieldAccessorTable.FieldAccessor
    {
      protected final Method caseMethod;
      protected final Method caseMethodBuilder;
      protected final Method clearMethod;
      protected final Descriptors.FieldDescriptor field;
      protected final Method getMethod;
      protected final Method getMethodBuilder;
      protected final boolean hasHasMethod;
      protected final Method hasMethod;
      protected final Method hasMethodBuilder;
      protected final boolean isOneofField;
      protected final Method setMethod;
      protected final Class<?> type;
      
      SingularFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString1, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1, String paramString2)
      {
        this.field = paramFieldDescriptor;
        boolean bool;
        if (paramFieldDescriptor.getContainingOneof() != null)
        {
          bool = true;
          this.isOneofField = bool;
          if ((!GeneratedMessage.FieldAccessorTable.supportFieldPresence(paramFieldDescriptor.getFile())) && ((this.isOneofField) || (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE))) {
            break label421;
          }
          bool = true;
          label58:
          this.hasHasMethod = bool;
          paramFieldDescriptor = String.valueOf(paramString1);
          if (paramFieldDescriptor.length() == 0) {
            break label427;
          }
          paramFieldDescriptor = "get".concat(paramFieldDescriptor);
          label83:
          this.getMethod = GeneratedMessage.getMethodOrDie(paramClass, paramFieldDescriptor, new Class[0]);
          paramFieldDescriptor = String.valueOf(paramString1);
          if (paramFieldDescriptor.length() == 0) {
            break label440;
          }
          paramFieldDescriptor = "get".concat(paramFieldDescriptor);
          label115:
          this.getMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[0]);
          this.type = this.getMethod.getReturnType();
          paramFieldDescriptor = String.valueOf(paramString1);
          if (paramFieldDescriptor.length() == 0) {
            break label453;
          }
          paramFieldDescriptor = "set".concat(paramFieldDescriptor);
          label159:
          this.setMethod = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[] { this.type });
          if (!this.hasHasMethod) {
            break label479;
          }
          paramFieldDescriptor = String.valueOf(paramString1);
          if (paramFieldDescriptor.length() == 0) {
            break label466;
          }
          paramFieldDescriptor = "has".concat(paramFieldDescriptor);
          label206:
          paramFieldDescriptor = GeneratedMessage.getMethodOrDie(paramClass, paramFieldDescriptor, new Class[0]);
          label216:
          this.hasMethod = paramFieldDescriptor;
          if (!this.hasHasMethod) {
            break label497;
          }
          paramFieldDescriptor = String.valueOf(paramString1);
          if (paramFieldDescriptor.length() == 0) {
            break label484;
          }
          paramFieldDescriptor = "has".concat(paramFieldDescriptor);
          label247:
          paramFieldDescriptor = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[0]);
          label258:
          this.hasMethodBuilder = paramFieldDescriptor;
          paramFieldDescriptor = String.valueOf(paramString1);
          if (paramFieldDescriptor.length() == 0) {
            break label502;
          }
          paramFieldDescriptor = "clear".concat(paramFieldDescriptor);
          label282:
          this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass1, paramFieldDescriptor, new Class[0]);
          if (!this.isOneofField) {
            break label515;
          }
        }
        label421:
        label427:
        label440:
        label453:
        label466:
        label479:
        label484:
        label497:
        label502:
        label515:
        for (paramFieldDescriptor = GeneratedMessage.getMethodOrDie(paramClass, String.valueOf(paramString2).length() + 7 + "get" + paramString2 + "Case", new Class[0]);; paramFieldDescriptor = null)
        {
          this.caseMethod = paramFieldDescriptor;
          paramFieldDescriptor = (Descriptors.FieldDescriptor)localObject;
          if (this.isOneofField) {
            paramFieldDescriptor = GeneratedMessage.getMethodOrDie(paramClass1, String.valueOf(paramString2).length() + 7 + "get" + paramString2 + "Case", new Class[0]);
          }
          this.caseMethodBuilder = paramFieldDescriptor;
          return;
          bool = false;
          break;
          bool = false;
          break label58;
          paramFieldDescriptor = new String("get");
          break label83;
          paramFieldDescriptor = new String("get");
          break label115;
          paramFieldDescriptor = new String("set");
          break label159;
          paramFieldDescriptor = new String("has");
          break label206;
          paramFieldDescriptor = null;
          break label216;
          paramFieldDescriptor = new String("has");
          break label247;
          paramFieldDescriptor = null;
          break label258;
          paramFieldDescriptor = new String("clear");
          break label282;
        }
      }
      
      private int getOneofFieldNumber(GeneratedMessage.Builder paramBuilder)
      {
        return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, paramBuilder, new Object[0])).getNumber();
      }
      
      private int getOneofFieldNumber(GeneratedMessage paramGeneratedMessage)
      {
        return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, paramGeneratedMessage, new Object[0])).getNumber();
      }
      
      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
      }
      
      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }
      
      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, paramBuilder, new Object[0]);
      }
      
      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getMethod, paramGeneratedMessage, new Object[0]);
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }
      
      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }
      
      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }
      
      public int getRepeatedCount(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }
      
      public int getRepeatedCount(GeneratedMessage paramGeneratedMessage)
      {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }
      
      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        if (!this.hasHasMethod)
        {
          if (this.isOneofField) {
            if (getOneofFieldNumber(paramBuilder) != this.field.getNumber()) {}
          }
          while (!get(paramBuilder).equals(this.field.getDefaultValue()))
          {
            return true;
            return false;
          }
          return false;
        }
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, paramBuilder, new Object[0])).booleanValue();
      }
      
      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        if (!this.hasHasMethod)
        {
          if (this.isOneofField) {
            if (getOneofFieldNumber(paramGeneratedMessage) != this.field.getNumber()) {}
          }
          while (!get(paramGeneratedMessage).equals(this.field.getDefaultValue()))
          {
            return true;
            return false;
          }
          return false;
        }
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, paramGeneratedMessage, new Object[0])).booleanValue();
      }
      
      public Message.Builder newBuilder()
      {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }
      
      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.setMethod, paramBuilder, new Object[] { paramObject });
      }
      
      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
      }
    }
    
    private static final class SingularMessageFieldAccessor
      extends GeneratedMessage.FieldAccessorTable.SingularFieldAccessor
    {
      private final Method getBuilderMethodBuilder;
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
      
      SingularMessageFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString1, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1, String paramString2)
      {
        super(paramString1, paramClass, paramClass1, paramString2);
        this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, String.valueOf(paramString1).length() + 10 + "get" + paramString1 + "Builder", new Class[0]);
      }
      
      private Object coerceType(Object paramObject)
      {
        if (this.type.isInstance(paramObject)) {
          return paramObject;
        }
        return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)paramObject).buildPartial();
      }
      
      public Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder)
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, paramBuilder, new Object[0]);
      }
      
      public Message.Builder newBuilder()
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }
      
      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.set(paramBuilder, coerceType(paramObject));
      }
    }
  }
  
  public static class GeneratedExtension<ContainingType extends Message, Type>
    extends Extension<ContainingType, Type>
  {
    private GeneratedMessage.ExtensionDescriptorRetriever descriptorRetriever;
    private final Method enumGetValueDescriptor;
    private final Method enumValueOf;
    private final Extension.ExtensionType extensionType;
    private final Message messageDefaultInstance;
    private final Class singularType;
    
    GeneratedExtension(GeneratedMessage.ExtensionDescriptorRetriever paramExtensionDescriptorRetriever, Class paramClass, Message paramMessage, Extension.ExtensionType paramExtensionType)
    {
      if ((Message.class.isAssignableFrom(paramClass)) && (!paramClass.isInstance(paramMessage)))
      {
        paramExtensionDescriptorRetriever = String.valueOf(paramClass.getName());
        if (paramExtensionDescriptorRetriever.length() != 0) {}
        for (paramExtensionDescriptorRetriever = "Bad messageDefaultInstance for ".concat(paramExtensionDescriptorRetriever);; paramExtensionDescriptorRetriever = new String("Bad messageDefaultInstance for ")) {
          throw new IllegalArgumentException(paramExtensionDescriptorRetriever);
        }
      }
      this.descriptorRetriever = paramExtensionDescriptorRetriever;
      this.singularType = paramClass;
      this.messageDefaultInstance = paramMessage;
      if (ProtocolMessageEnum.class.isAssignableFrom(paramClass)) {
        this.enumValueOf = GeneratedMessage.getMethodOrDie(paramClass, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
      }
      for (this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(paramClass, "getValueDescriptor", new Class[0]);; this.enumGetValueDescriptor = null)
      {
        this.extensionType = paramExtensionType;
        return;
        this.enumValueOf = null;
      }
    }
    
    protected Object fromReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      if (localFieldDescriptor.isRepeated())
      {
        Object localObject;
        if (localFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          localObject = paramObject;
          if (localFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {}
        }
        else
        {
          localObject = new ArrayList();
          paramObject = ((List)paramObject).iterator();
          while (((Iterator)paramObject).hasNext()) {
            ((List)localObject).add(singularFromReflectionType(((Iterator)paramObject).next()));
          }
        }
        return localObject;
      }
      return singularFromReflectionType(paramObject);
    }
    
    public Type getDefaultValue()
    {
      if (isRepeated()) {
        return Collections.emptyList();
      }
      if (getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        return this.messageDefaultInstance;
      }
      return (Type)singularFromReflectionType(getDescriptor().getDefaultValue());
    }
    
    public Descriptors.FieldDescriptor getDescriptor()
    {
      if (this.descriptorRetriever == null) {
        throw new IllegalStateException("getDescriptor() called before internalInit()");
      }
      return this.descriptorRetriever.getDescriptor();
    }
    
    protected Extension.ExtensionType getExtensionType()
    {
      return this.extensionType;
    }
    
    public WireFormat.FieldType getLiteType()
    {
      return getDescriptor().getLiteType();
    }
    
    public Message getMessageDefaultInstance()
    {
      return this.messageDefaultInstance;
    }
    
    public int getNumber()
    {
      return getDescriptor().getNumber();
    }
    
    public void internalInit(final Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (this.descriptorRetriever != null) {
        throw new IllegalStateException("Already initialized.");
      }
      this.descriptorRetriever = new GeneratedMessage.ExtensionDescriptorRetriever()
      {
        public Descriptors.FieldDescriptor getDescriptor()
        {
          return paramFieldDescriptor;
        }
      };
    }
    
    public boolean isRepeated()
    {
      return getDescriptor().isRepeated();
    }
    
    protected Object singularFromReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      switch (GeneratedMessage.4.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[localFieldDescriptor.getJavaType().ordinal()])
      {
      default: 
      case 1: 
        do
        {
          return paramObject;
        } while (this.singularType.isInstance(paramObject));
        return this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)paramObject).build();
      }
      return GeneratedMessage.invokeOrDie(this.enumValueOf, null, new Object[] { (Descriptors.EnumValueDescriptor)paramObject });
    }
    
    protected Object singularToReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      switch (GeneratedMessage.4.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[localFieldDescriptor.getJavaType().ordinal()])
      {
      default: 
        return paramObject;
      }
      return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, paramObject, new Object[0]);
    }
    
    protected Object toReflectionType(Object paramObject)
    {
      Object localObject = getDescriptor();
      if (((Descriptors.FieldDescriptor)localObject).isRepeated())
      {
        if (((Descriptors.FieldDescriptor)localObject).getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
        {
          localObject = new ArrayList();
          Iterator localIterator = ((List)paramObject).iterator();
          for (;;)
          {
            paramObject = localObject;
            if (!localIterator.hasNext()) {
              break;
            }
            ((List)localObject).add(singularToReflectionType(localIterator.next()));
          }
        }
        return paramObject;
      }
      return singularToReflectionType(paramObject);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\GeneratedMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */