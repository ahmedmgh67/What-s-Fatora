package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSerializer
  extends StdSerializer<AtomicReference<?>>
  implements ContextualSerializer
{
  private static final long serialVersionUID = 1L;
  protected final JsonInclude.Include _contentInclusion;
  protected transient PropertySerializerMap _dynamicSerializers;
  protected final BeanProperty _property;
  protected final JavaType _referredType;
  protected final NameTransformer _unwrapper;
  protected final JsonSerializer<Object> _valueSerializer;
  protected final TypeSerializer _valueTypeSerializer;
  
  protected AtomicReferenceSerializer(AtomicReferenceSerializer paramAtomicReferenceSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, NameTransformer paramNameTransformer, JsonInclude.Include paramInclude)
  {
    super(paramAtomicReferenceSerializer);
    this._referredType = paramAtomicReferenceSerializer._referredType;
    this._dynamicSerializers = paramAtomicReferenceSerializer._dynamicSerializers;
    this._property = paramBeanProperty;
    this._valueTypeSerializer = paramTypeSerializer;
    this._valueSerializer = paramJsonSerializer;
    this._unwrapper = paramNameTransformer;
    if ((paramInclude == JsonInclude.Include.USE_DEFAULTS) || (paramInclude == JsonInclude.Include.ALWAYS))
    {
      this._contentInclusion = null;
      return;
    }
    this._contentInclusion = paramInclude;
  }
  
  public AtomicReferenceSerializer(ReferenceType paramReferenceType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(paramReferenceType);
    this._referredType = paramReferenceType.getReferencedType();
    this._property = null;
    this._valueTypeSerializer = paramTypeSerializer;
    this._valueSerializer = paramJsonSerializer;
    this._unwrapper = null;
    this._contentInclusion = null;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
  }
  
  private final JsonSerializer<Object> _findCachedSerializer(SerializerProvider paramSerializerProvider, Class<?> paramClass)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = this._dynamicSerializers.serializerFor(paramClass);
    Object localObject = localJsonSerializer;
    if (localJsonSerializer == null)
    {
      localObject = _findSerializer(paramSerializerProvider, paramClass, this._property);
      paramSerializerProvider = (SerializerProvider)localObject;
      if (this._unwrapper != null) {
        paramSerializerProvider = ((JsonSerializer)localObject).unwrappingSerializer(this._unwrapper);
      }
      this._dynamicSerializers = this._dynamicSerializers.newWith(paramClass, paramSerializerProvider);
      localObject = paramSerializerProvider;
    }
    return (JsonSerializer<Object>)localObject;
  }
  
  private final JsonSerializer<Object> _findSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return paramSerializerProvider.findTypedValueSerializer(paramJavaType, true, paramBeanProperty);
  }
  
  private final JsonSerializer<Object> _findSerializer(SerializerProvider paramSerializerProvider, Class<?> paramClass, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return paramSerializerProvider.findTypedValueSerializer(paramClass, true, paramBeanProperty);
  }
  
  protected boolean _useStatic(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty, JavaType paramJavaType)
  {
    if (paramJavaType.isJavaLangObject()) {}
    do
    {
      return false;
      if (paramJavaType.isFinal()) {
        return true;
      }
      if (paramJavaType.useStaticType()) {
        return true;
      }
      paramJavaType = paramSerializerProvider.getAnnotationIntrospector();
      if ((paramJavaType == null) || (paramBeanProperty == null) || (paramBeanProperty.getMember() == null)) {
        break;
      }
      paramBeanProperty = paramJavaType.findSerializationTyping(paramBeanProperty.getMember());
      if (paramBeanProperty == JsonSerialize.Typing.STATIC) {
        return true;
      }
    } while (paramBeanProperty == JsonSerialize.Typing.DYNAMIC);
    return paramSerializerProvider.isEnabled(MapperFeature.USE_STATIC_TYPING);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = this._valueSerializer;
    paramJavaType = localJsonSerializer;
    if (localJsonSerializer == null)
    {
      localJsonSerializer = _findSerializer(paramJsonFormatVisitorWrapper.getProvider(), this._referredType, this._property);
      paramJavaType = localJsonSerializer;
      if (this._unwrapper != null) {
        paramJavaType = localJsonSerializer.unwrappingSerializer(this._unwrapper);
      }
    }
    paramJavaType.acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, this._referredType);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = this._valueTypeSerializer;
    Object localObject2 = localObject1;
    if (localObject1 != null) {
      localObject2 = ((TypeSerializer)localObject1).forProperty(paramBeanProperty);
    }
    localObject1 = this._valueSerializer;
    if (localObject1 == null) {
      if (!_useStatic(paramSerializerProvider, paramBeanProperty, this._referredType)) {}
    }
    for (localObject1 = _findSerializer(paramSerializerProvider, this._referredType, paramBeanProperty);; localObject1 = paramSerializerProvider.handlePrimaryContextualization((JsonSerializer)localObject1, paramBeanProperty))
    {
      JsonInclude.Include localInclude = this._contentInclusion;
      Object localObject3 = localInclude;
      if (paramBeanProperty != null)
      {
        paramSerializerProvider = paramBeanProperty.findPropertyInclusion(paramSerializerProvider.getConfig(), AtomicReference.class).getContentInclusion();
        localObject3 = localInclude;
        if (paramSerializerProvider != localInclude)
        {
          localObject3 = localInclude;
          if (paramSerializerProvider != JsonInclude.Include.USE_DEFAULTS) {
            localObject3 = paramSerializerProvider;
          }
        }
      }
      return withResolved(paramBeanProperty, (TypeSerializer)localObject2, (JsonSerializer)localObject1, this._unwrapper, (JsonInclude.Include)localObject3);
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, AtomicReference<?> paramAtomicReference)
  {
    if (paramAtomicReference == null) {}
    Object localObject;
    do
    {
      return true;
      localObject = paramAtomicReference.get();
    } while (localObject == null);
    if (this._contentInclusion == null) {
      return false;
    }
    JsonSerializer localJsonSerializer2 = this._valueSerializer;
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null) {}
    try
    {
      localJsonSerializer1 = _findCachedSerializer(paramSerializerProvider, paramAtomicReference.getClass());
      return localJsonSerializer1.isEmpty(paramSerializerProvider, localObject);
    }
    catch (JsonMappingException paramSerializerProvider)
    {
      throw new RuntimeJsonMappingException(paramSerializerProvider);
    }
  }
  
  public boolean isUnwrappingSerializer()
  {
    return this._unwrapper != null;
  }
  
  public void serialize(AtomicReference<?> paramAtomicReference, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    Object localObject = paramAtomicReference.get();
    if (localObject == null)
    {
      if (this._unwrapper == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      return;
    }
    JsonSerializer localJsonSerializer = this._valueSerializer;
    paramAtomicReference = localJsonSerializer;
    if (localJsonSerializer == null) {
      paramAtomicReference = _findCachedSerializer(paramSerializerProvider, localObject.getClass());
    }
    if (this._valueTypeSerializer != null)
    {
      paramAtomicReference.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._valueTypeSerializer);
      return;
    }
    paramAtomicReference.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
  }
  
  public void serializeWithType(AtomicReference<?> paramAtomicReference, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (paramAtomicReference.get() == null)
    {
      if (this._unwrapper == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      return;
    }
    paramTypeSerializer.writeTypePrefixForScalar(paramAtomicReference, paramJsonGenerator);
    serialize(paramAtomicReference, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramAtomicReference, paramJsonGenerator);
  }
  
  public JsonSerializer<AtomicReference<?>> unwrappingSerializer(NameTransformer paramNameTransformer)
  {
    JsonSerializer localJsonSerializer2 = this._valueSerializer;
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 != null) {
      localJsonSerializer1 = localJsonSerializer2.unwrappingSerializer(paramNameTransformer);
    }
    if (this._unwrapper == null) {}
    for (;;)
    {
      return withResolved(this._property, this._valueTypeSerializer, localJsonSerializer1, paramNameTransformer, this._contentInclusion);
      paramNameTransformer = NameTransformer.chainedTransformer(paramNameTransformer, this._unwrapper);
    }
  }
  
  protected AtomicReferenceSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, NameTransformer paramNameTransformer, JsonInclude.Include paramInclude)
  {
    if ((this._property == paramBeanProperty) && (paramInclude == this._contentInclusion) && (this._valueTypeSerializer == paramTypeSerializer) && (this._valueSerializer == paramJsonSerializer) && (this._unwrapper == paramNameTransformer)) {
      return this;
    }
    return new AtomicReferenceSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramNameTransformer, paramInclude);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\std\AtomicReferenceSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */