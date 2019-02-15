package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class StdSerializer<T>
  extends JsonSerializer<T>
  implements JsonFormatVisitable, SchemaAware, Serializable
{
  private static final Object CONVERTING_CONTENT_CONVERTER_LOCK = new Object();
  private static final long serialVersionUID = 1L;
  protected final Class<T> _handledType;
  
  protected StdSerializer(JavaType paramJavaType)
  {
    this._handledType = paramJavaType.getRawClass();
  }
  
  protected StdSerializer(StdSerializer<?> paramStdSerializer)
  {
    this._handledType = paramStdSerializer._handledType;
  }
  
  protected StdSerializer(Class<T> paramClass)
  {
    this._handledType = paramClass;
  }
  
  protected StdSerializer(Class<?> paramClass, boolean paramBoolean)
  {
    this._handledType = paramClass;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  protected ObjectNode createObjectNode()
  {
    return JsonNodeFactory.instance.objectNode();
  }
  
  protected ObjectNode createSchemaNode(String paramString)
  {
    ObjectNode localObjectNode = createObjectNode();
    localObjectNode.put("type", paramString);
    return localObjectNode;
  }
  
  protected ObjectNode createSchemaNode(String paramString, boolean paramBoolean)
  {
    paramString = createSchemaNode(paramString);
    if (!paramBoolean) {
      if (paramBoolean) {
        break label26;
      }
    }
    label26:
    for (paramBoolean = true;; paramBoolean = false)
    {
      paramString.put("required", paramBoolean);
      return paramString;
    }
  }
  
  protected JsonSerializer<?> findConvertingContentSerializer(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    if (paramSerializerProvider.getAttribute(CONVERTING_CONTENT_CONVERTER_LOCK) != null) {
      return paramJsonSerializer;
    }
    Object localObject1 = paramSerializerProvider.getAnnotationIntrospector();
    if ((localObject1 != null) && (paramBeanProperty != null))
    {
      Object localObject2 = paramBeanProperty.getMember();
      if (localObject2 != null)
      {
        paramSerializerProvider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, Boolean.TRUE);
        try
        {
          localObject1 = ((AnnotationIntrospector)localObject1).findSerializationContentConverter((AnnotatedMember)localObject2);
          paramSerializerProvider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, null);
          if (localObject1 != null)
          {
            localObject1 = paramSerializerProvider.converterInstance(paramBeanProperty.getMember(), localObject1);
            localObject2 = ((Converter)localObject1).getOutputType(paramSerializerProvider.getTypeFactory());
            paramBeanProperty = paramJsonSerializer;
            if (paramJsonSerializer == null)
            {
              paramBeanProperty = paramJsonSerializer;
              if (!((JavaType)localObject2).isJavaLangObject()) {
                paramBeanProperty = paramSerializerProvider.findValueSerializer((JavaType)localObject2);
              }
            }
            return new StdDelegatingSerializer((Converter)localObject1, (JavaType)localObject2, paramBeanProperty);
          }
        }
        finally
        {
          paramSerializerProvider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, null);
        }
      }
    }
    return paramJsonSerializer;
  }
  
  protected Boolean findFormatFeature(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty, Class<?> paramClass, JsonFormat.Feature paramFeature)
  {
    paramSerializerProvider = findFormatOverrides(paramSerializerProvider, paramBeanProperty, paramClass);
    if (paramSerializerProvider != null) {
      return paramSerializerProvider.getFeature(paramFeature);
    }
    return null;
  }
  
  protected JsonFormat.Value findFormatOverrides(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty, Class<?> paramClass)
  {
    if (paramBeanProperty != null) {
      return paramBeanProperty.findPropertyFormat(paramSerializerProvider.getConfig(), paramClass);
    }
    return paramSerializerProvider.getDefaultPropertyFormat(paramClass);
  }
  
  protected PropertyFilter findPropertyFilter(SerializerProvider paramSerializerProvider, Object paramObject1, Object paramObject2)
    throws JsonMappingException
  {
    FilterProvider localFilterProvider = paramSerializerProvider.getFilterProvider();
    if (localFilterProvider == null) {
      throw JsonMappingException.from(paramSerializerProvider, "Can not resolve PropertyFilter with id '" + paramObject1 + "'; no FilterProvider configured");
    }
    return localFilterProvider.findPropertyFilter(paramObject1, paramObject2);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    return createSchemaNode("string");
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType, boolean paramBoolean)
    throws JsonMappingException
  {
    paramSerializerProvider = (ObjectNode)getSchema(paramSerializerProvider, paramType);
    if (!paramBoolean) {
      if (paramBoolean) {
        break label30;
      }
    }
    label30:
    for (paramBoolean = true;; paramBoolean = false)
    {
      paramSerializerProvider.put("required", paramBoolean);
      return paramSerializerProvider;
    }
  }
  
  public Class<T> handledType()
  {
    return this._handledType;
  }
  
  protected boolean isDefaultSerializer(JsonSerializer<?> paramJsonSerializer)
  {
    return ClassUtil.isJacksonStdImpl(paramJsonSerializer);
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException;
  
  protected void visitArrayFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType1, JsonSerializer<?> paramJsonSerializer, JavaType paramJavaType2)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType1);
      if ((paramJsonFormatVisitorWrapper != null) && (paramJsonSerializer != null)) {
        paramJsonFormatVisitorWrapper.itemsFormat(paramJsonSerializer, paramJavaType2);
      }
    }
  }
  
  protected void visitArrayFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, JsonFormatTypes paramJsonFormatTypes)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.itemsFormat(paramJsonFormatTypes);
      }
    }
  }
  
  protected void visitFloatFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, JsonParser.NumberType paramNumberType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(paramNumberType);
      }
    }
  }
  
  protected void visitIntFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, JsonParser.NumberType paramNumberType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if ((paramJsonFormatVisitorWrapper != null) && (paramNumberType != null)) {
        paramJsonFormatVisitorWrapper.numberType(paramNumberType);
      }
    }
  }
  
  protected void visitIntFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, JsonParser.NumberType paramNumberType, JsonValueFormat paramJsonValueFormat)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null)
      {
        if (paramNumberType != null) {
          paramJsonFormatVisitorWrapper.numberType(paramNumberType);
        }
        if (paramJsonValueFormat != null) {
          paramJsonFormatVisitorWrapper.format(paramJsonValueFormat);
        }
      }
    }
  }
  
  protected void visitStringFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null) {
      paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
    }
  }
  
  protected void visitStringFormat(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, JsonValueFormat paramJsonValueFormat)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.format(paramJsonValueFormat);
      }
    }
  }
  
  public void wrapAndThrow(SerializerProvider paramSerializerProvider, Throwable paramThrowable, Object paramObject, int paramInt)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramSerializerProvider == null) || (paramSerializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonMappingException))) {
        break label98;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    label98:
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramInt);
  }
  
  public void wrapAndThrow(SerializerProvider paramSerializerProvider, Throwable paramThrowable, Object paramObject, String paramString)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramSerializerProvider == null) || (paramSerializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonMappingException))) {
        break label98;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    label98:
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\std\StdSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */