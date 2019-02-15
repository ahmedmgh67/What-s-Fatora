package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class MapProperty
  extends PropertyWriter
{
  private static final long serialVersionUID = 1L;
  protected Object _key;
  protected JsonSerializer<Object> _keySerializer;
  protected final BeanProperty _property;
  protected final TypeSerializer _typeSerializer;
  protected JsonSerializer<Object> _valueSerializer;
  
  public MapProperty(TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {}
  
  public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if (this._property != null) {
      this._property.depositSchemaProperty(paramJsonObjectFormatVisitor, paramSerializerProvider);
    }
  }
  
  @Deprecated
  public void depositSchemaProperty(ObjectNode paramObjectNode, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {}
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    if (this._property == null) {
      return null;
    }
    return this._property.getAnnotation(paramClass);
  }
  
  public <A extends Annotation> A getContextAnnotation(Class<A> paramClass)
  {
    if (this._property == null) {
      return null;
    }
    return this._property.getContextAnnotation(paramClass);
  }
  
  public PropertyName getFullName()
  {
    return new PropertyName(getName());
  }
  
  public AnnotatedMember getMember()
  {
    if (this._property == null) {
      return null;
    }
    return this._property.getMember();
  }
  
  public String getName()
  {
    if ((this._key instanceof String)) {
      return (String)this._key;
    }
    return String.valueOf(this._key);
  }
  
  public JavaType getType()
  {
    if (this._property == null) {
      return TypeFactory.unknownType();
    }
    return this._property.getType();
  }
  
  public PropertyName getWrapperName()
  {
    if (this._property == null) {
      return null;
    }
    return this._property.getWrapperName();
  }
  
  public void reset(Object paramObject, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2)
  {
    this._key = paramObject;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
  }
  
  public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (this._typeSerializer == null)
    {
      this._valueSerializer.serialize(paramObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    this._valueSerializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    this._keySerializer.serialize(this._key, paramJsonGenerator, paramSerializerProvider);
    if (this._typeSerializer == null)
    {
      this._valueSerializer.serialize(paramObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    this._valueSerializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
  }
  
  public void serializeAsOmittedField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (!paramJsonGenerator.canOmitFields()) {
      paramJsonGenerator.writeOmittedField(getName());
    }
  }
  
  public void serializeAsPlaceholder(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    paramJsonGenerator.writeNull();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\std\MapProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */