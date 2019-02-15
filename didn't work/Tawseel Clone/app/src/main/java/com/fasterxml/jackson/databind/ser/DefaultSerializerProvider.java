package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DefaultSerializerProvider
  extends SerializerProvider
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
  protected transient Map<Object, WritableObjectId> _seenObjectIds;
  
  protected DefaultSerializerProvider() {}
  
  protected DefaultSerializerProvider(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    super(paramSerializerProvider, paramSerializationConfig, paramSerializerFactory);
  }
  
  protected DefaultSerializerProvider(DefaultSerializerProvider paramDefaultSerializerProvider)
  {
    super(paramDefaultSerializerProvider);
  }
  
  protected Map<Object, WritableObjectId> _createObjectIdMap()
  {
    if (isEnabled(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)) {
      return new HashMap();
    }
    return new IdentityHashMap();
  }
  
  protected void _serializeNull(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    Object localObject = getDefaultNullValueSerializer();
    try
    {
      ((JsonSerializer)localObject).serialize(null, paramJsonGenerator, this);
      return;
    }
    catch (IOException paramJsonGenerator)
    {
      throw paramJsonGenerator;
    }
    catch (Exception localException)
    {
      String str = localException.getMessage();
      localObject = str;
      if (str == null) {
        localObject = "[no message for " + localException.getClass().getName() + "]";
      }
      throw JsonMappingException.from(paramJsonGenerator, (String)localObject, localException);
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("A class must be provided");
    }
    paramJsonFormatVisitorWrapper.setProvider(this);
    findValueSerializer(paramJavaType, null).acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType);
  }
  
  public int cachedSerializersCount()
  {
    return this._serializerCache.size();
  }
  
  public DefaultSerializerProvider copy()
  {
    throw new IllegalStateException("DefaultSerializerProvider sub-class not overriding copy()");
  }
  
  public abstract DefaultSerializerProvider createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory);
  
  public WritableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    if (this._seenObjectIds == null) {
      this._seenObjectIds = _createObjectIdMap();
    }
    Object localObject2;
    Object localObject1;
    do
    {
      localObject2 = null;
      if (this._objectIdGenerators != null) {
        break;
      }
      this._objectIdGenerators = new ArrayList(8);
      localObject1 = localObject2;
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = paramObjectIdGenerator.newForSerialization(this);
        this._objectIdGenerators.add(localObject2);
      }
      paramObjectIdGenerator = new WritableObjectId((ObjectIdGenerator)localObject2);
      this._seenObjectIds.put(paramObject, paramObjectIdGenerator);
      return paramObjectIdGenerator;
      localObject1 = (WritableObjectId)this._seenObjectIds.get(paramObject);
    } while (localObject1 == null);
    return (WritableObjectId)localObject1;
    int i = 0;
    int j = this._objectIdGenerators.size();
    for (;;)
    {
      localObject1 = localObject2;
      if (i >= j) {
        break;
      }
      localObject1 = (ObjectIdGenerator)this._objectIdGenerators.get(i);
      if (((ObjectIdGenerator)localObject1).canUseFor(paramObjectIdGenerator)) {
        break;
      }
      i += 1;
    }
  }
  
  public void flushCachedSerializers()
  {
    this._serializerCache.flush();
  }
  
  @Deprecated
  public JsonSchema generateJsonSchema(Class<?> paramClass)
    throws JsonMappingException
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("A class must be provided");
    }
    Object localObject = findValueSerializer(paramClass, null);
    if ((localObject instanceof SchemaAware)) {}
    for (localObject = ((SchemaAware)localObject).getSchema(this, null); !(localObject instanceof ObjectNode); localObject = JsonSchema.getDefaultSchemaNode()) {
      throw new IllegalArgumentException("Class " + paramClass.getName() + " would not be serialized as a JSON object and therefore has no schema");
    }
    return new JsonSchema((ObjectNode)localObject);
  }
  
  public boolean hasSerializerFor(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    if ((paramClass == Object.class) && (!this._config.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS))) {}
    for (;;)
    {
      return true;
      try
      {
        paramClass = _findExplicitUntypedSerializer(paramClass);
        if (paramClass != null) {
          continue;
        }
        return false;
      }
      catch (JsonMappingException paramClass)
      {
        if (paramAtomicReference != null) {
          paramAtomicReference.set(paramClass);
        }
        return false;
      }
      catch (RuntimeException paramClass)
      {
        for (;;)
        {
          if (paramAtomicReference == null) {
            throw paramClass;
          }
          paramAtomicReference.set(paramClass);
        }
      }
    }
  }
  
  public void serializePolymorphic(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if ((paramJavaType != null) && (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass()))) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      Object localObject = paramJsonSerializer;
      boolean bool1;
      if (paramJsonSerializer == null)
      {
        if ((paramJavaType != null) && (paramJavaType.isContainerType())) {
          localObject = findValueSerializer(paramJavaType, null);
        }
      }
      else
      {
        paramJavaType = this._config.getFullRootName();
        if (paramJavaType != null) {
          break label162;
        }
        boolean bool2 = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        bool1 = bool2;
        if (bool2)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._config.findRootName(paramObject.getClass()).simpleAsEncoded(this._config));
          bool1 = bool2;
        }
      }
      try
      {
        ((JsonSerializer)localObject).serializeWithType(paramObject, paramJsonGenerator, this, paramTypeSerializer);
        if (!bool1) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          localObject = findValueSerializer(paramObject.getClass(), null);
          break;
          if (paramJavaType.isEmpty())
          {
            bool1 = false;
          }
          else
          {
            bool1 = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(paramJavaType.getSimpleName());
          }
        }
      }
      catch (Exception paramJsonSerializer)
      {
        label162:
        paramJavaType = paramJsonSerializer.getMessage();
        paramObject = paramJavaType;
        if (paramJavaType == null) {
          paramObject = "[no message for " + paramJsonSerializer.getClass().getName() + "]";
        }
        throw JsonMappingException.from(paramJsonGenerator, (String)paramObject, paramJsonSerializer);
      }
    }
  }
  
  @Deprecated
  public void serializePolymorphic(JsonGenerator paramJsonGenerator, Object paramObject, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (paramObject == null) {}
    for (JavaType localJavaType = null;; localJavaType = this._config.constructType(paramObject.getClass()))
    {
      serializePolymorphic(paramJsonGenerator, paramObject, localJavaType, null, paramTypeSerializer);
      return;
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      Object localObject = findTypedValueSerializer(paramObject.getClass(), true, null);
      PropertyName localPropertyName = this._config.getFullRootName();
      boolean bool1;
      if (localPropertyName == null)
      {
        boolean bool2 = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        bool1 = bool2;
        if (bool2)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._config.findRootName(paramObject.getClass()).simpleAsEncoded(this._config));
          bool1 = bool2;
        }
      }
      try
      {
        ((JsonSerializer)localObject).serialize(paramObject, paramJsonGenerator, this);
        if (!bool1) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          if (localPropertyName.isEmpty())
          {
            bool1 = false;
          }
          else
          {
            bool1 = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(localPropertyName.getSimpleName());
          }
        }
      }
      catch (Exception localException)
      {
        localObject = localException.getMessage();
        paramObject = localObject;
        if (localObject == null) {
          paramObject = "[no message for " + localException.getClass().getName() + "]";
        }
        throw new JsonMappingException(paramJsonGenerator, (String)paramObject, localException);
      }
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass())) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      paramJavaType = findTypedValueSerializer(paramJavaType, true, null);
      PropertyName localPropertyName = this._config.getFullRootName();
      boolean bool1;
      if (localPropertyName == null)
      {
        boolean bool2 = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        bool1 = bool2;
        if (bool2)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._config.findRootName(paramObject.getClass()).simpleAsEncoded(this._config));
          bool1 = bool2;
        }
      }
      try
      {
        paramJavaType.serialize(paramObject, paramJsonGenerator, this);
        if (!bool1) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          if (localPropertyName.isEmpty())
          {
            bool1 = false;
          }
          else
          {
            bool1 = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(localPropertyName.getSimpleName());
          }
        }
      }
      catch (Exception localException)
      {
        paramJavaType = localException.getMessage();
        paramObject = paramJavaType;
        if (paramJavaType == null) {
          paramObject = "[no message for " + localException.getClass().getName() + "]";
        }
        throw JsonMappingException.from(paramJsonGenerator, (String)paramObject, localException);
      }
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if ((paramJavaType != null) && (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass()))) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      Object localObject = paramJsonSerializer;
      if (paramJsonSerializer == null) {
        localObject = findTypedValueSerializer(paramJavaType, true, null);
      }
      paramJsonSerializer = this._config.getFullRootName();
      boolean bool1;
      if (paramJsonSerializer == null)
      {
        boolean bool2 = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        bool1 = bool2;
        if (bool2)
        {
          paramJsonGenerator.writeStartObject();
          if (paramJavaType != null) {
            break label144;
          }
          paramJavaType = this._config.findRootName(paramObject.getClass());
          paramJsonGenerator.writeFieldName(paramJavaType.simpleAsEncoded(this._config));
          bool1 = bool2;
        }
      }
      try
      {
        ((JsonSerializer)localObject).serialize(paramObject, paramJsonGenerator, this);
        if (!bool1) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          paramJavaType = this._config.findRootName(paramJavaType);
          break;
          if (paramJsonSerializer.isEmpty())
          {
            bool1 = false;
          }
          else
          {
            bool1 = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(paramJsonSerializer.getSimpleName());
          }
        }
      }
      catch (Exception paramJsonSerializer)
      {
        label144:
        paramJavaType = paramJsonSerializer.getMessage();
        paramObject = paramJavaType;
        if (paramJavaType == null) {
          paramObject = "[no message for " + paramJsonSerializer.getClass().getName() + "]";
        }
        throw JsonMappingException.from(paramJsonGenerator, (String)paramObject, paramJsonSerializer);
      }
    }
  }
  
  public JsonSerializer<Object> serializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    if (paramObject == null) {}
    Class localClass;
    do
    {
      return null;
      if ((paramObject instanceof JsonSerializer))
      {
        paramAnnotated = (JsonSerializer)paramObject;
        return _handleResolvable(paramAnnotated);
      }
      if (!(paramObject instanceof Class)) {
        throw new IllegalStateException("AnnotationIntrospector returned serializer definition of type " + paramObject.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
      }
      localClass = (Class)paramObject;
    } while ((localClass == JsonSerializer.None.class) || (ClassUtil.isBogusClass(localClass)));
    if (!JsonSerializer.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<JsonSerializer>");
    }
    paramObject = this._config.getHandlerInstantiator();
    if (paramObject == null) {}
    for (paramObject = null;; paramObject = ((HandlerInstantiator)paramObject).serializerInstance(this._config, paramAnnotated, localClass))
    {
      paramAnnotated = (Annotated)paramObject;
      if (paramObject != null) {
        break;
      }
      paramAnnotated = (JsonSerializer)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
      break;
    }
  }
  
  public static final class Impl
    extends DefaultSerializerProvider
  {
    private static final long serialVersionUID = 1L;
    
    public Impl() {}
    
    protected Impl(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
    {
      super(paramSerializationConfig, paramSerializerFactory);
    }
    
    public Impl(Impl paramImpl)
    {
      super();
    }
    
    public DefaultSerializerProvider copy()
    {
      if (getClass() != Impl.class) {
        return super.copy();
      }
      return new Impl(this);
    }
    
    public Impl createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
    {
      return new Impl(this, paramSerializationConfig, paramSerializerFactory);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\DefaultSerializerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */