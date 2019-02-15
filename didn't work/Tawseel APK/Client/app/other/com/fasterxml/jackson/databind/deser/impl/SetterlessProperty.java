package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class SetterlessProperty
  extends SettableBeanProperty
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedMethod _annotated;
  protected final Method _getter;
  
  protected SetterlessProperty(SetterlessProperty paramSetterlessProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(paramSetterlessProperty, paramJsonDeserializer);
    this._annotated = paramSetterlessProperty._annotated;
    this._getter = paramSetterlessProperty._getter;
  }
  
  protected SetterlessProperty(SetterlessProperty paramSetterlessProperty, PropertyName paramPropertyName)
  {
    super(paramSetterlessProperty, paramPropertyName);
    this._annotated = paramSetterlessProperty._annotated;
    this._getter = paramSetterlessProperty._getter;
  }
  
  public SetterlessProperty(BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedMethod paramAnnotatedMethod)
  {
    super(paramBeanPropertyDefinition, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedMethod;
    this._getter = paramAnnotatedMethod.getAnnotated();
  }
  
  public final void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      return;
    }
    if (this._valueTypeDeserializer != null) {
      throw JsonMappingException.from(paramJsonParser, "Problem deserializing 'setterless' property (\"" + getName() + "\"): no way to handle typed deser with setterless yet");
    }
    try
    {
      paramObject = this._getter.invoke(paramObject, new Object[0]);
      if (paramObject == null) {
        throw JsonMappingException.from(paramJsonParser, "Problem deserializing 'setterless' property '" + getName() + "': get method returned null");
      }
    }
    catch (Exception paramDeserializationContext)
    {
      _throwAsIOE(paramJsonParser, paramDeserializationContext);
      return;
    }
    this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object deserializeSetAndReturn(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
    return paramObject;
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    return this._annotated.getAnnotation(paramClass);
  }
  
  public AnnotatedMember getMember()
  {
    return this._annotated;
  }
  
  public final void set(Object paramObject1, Object paramObject2)
    throws IOException
  {
    throw new UnsupportedOperationException("Should never call 'set' on setterless property");
  }
  
  public Object setAndReturn(Object paramObject1, Object paramObject2)
    throws IOException
  {
    set(paramObject1, paramObject2);
    return null;
  }
  
  public SetterlessProperty withName(PropertyName paramPropertyName)
  {
    return new SetterlessProperty(this, paramPropertyName);
  }
  
  public SetterlessProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return new SetterlessProperty(this, paramJsonDeserializer);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\impl\SetterlessProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */