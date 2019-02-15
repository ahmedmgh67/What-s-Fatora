package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.Serializable;

public abstract class VirtualBeanPropertyWriter
  extends BeanPropertyWriter
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  protected VirtualBeanPropertyWriter() {}
  
  protected VirtualBeanPropertyWriter(BeanPropertyDefinition paramBeanPropertyDefinition, Annotations paramAnnotations, JavaType paramJavaType)
  {
    this(paramBeanPropertyDefinition, paramAnnotations, paramJavaType, null, null, null, paramBeanPropertyDefinition.findInclusion());
  }
  
  protected VirtualBeanPropertyWriter(BeanPropertyDefinition paramBeanPropertyDefinition, Annotations paramAnnotations, JavaType paramJavaType1, JsonSerializer<?> paramJsonSerializer, TypeSerializer paramTypeSerializer, JavaType paramJavaType2, JsonInclude.Value paramValue)
  {
    super(paramBeanPropertyDefinition, paramBeanPropertyDefinition.getPrimaryMember(), paramAnnotations, paramJavaType1, paramJsonSerializer, paramTypeSerializer, paramJavaType2, _suppressNulls(paramValue), _suppressableValue(paramValue));
  }
  
  protected VirtualBeanPropertyWriter(VirtualBeanPropertyWriter paramVirtualBeanPropertyWriter)
  {
    super(paramVirtualBeanPropertyWriter);
  }
  
  protected VirtualBeanPropertyWriter(VirtualBeanPropertyWriter paramVirtualBeanPropertyWriter, PropertyName paramPropertyName)
  {
    super(paramVirtualBeanPropertyWriter, paramPropertyName);
  }
  
  protected static boolean _suppressNulls(JsonInclude.Value paramValue)
  {
    if (paramValue == null) {}
    do
    {
      return false;
      paramValue = paramValue.getValueInclusion();
    } while ((paramValue == JsonInclude.Include.ALWAYS) || (paramValue == JsonInclude.Include.USE_DEFAULTS));
    return true;
  }
  
  protected static Object _suppressableValue(JsonInclude.Value paramValue)
  {
    if (paramValue == null) {
      return Boolean.valueOf(false);
    }
    paramValue = paramValue.getValueInclusion();
    if ((paramValue == JsonInclude.Include.ALWAYS) || (paramValue == JsonInclude.Include.NON_NULL) || (paramValue == JsonInclude.Include.USE_DEFAULTS)) {
      return null;
    }
    return MARKER_FOR_EMPTY;
  }
  
  public boolean isVirtual()
  {
    return true;
  }
  
  public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = value(paramObject, paramJsonGenerator, paramSerializerProvider);
    if (localObject == null) {
      if (this._nullSerializer != null) {
        this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
    }
    JsonSerializer localJsonSerializer1;
    do
    {
      return;
      paramJsonGenerator.writeNull();
      return;
      JsonSerializer localJsonSerializer2 = this._serializer;
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null)
      {
        Class localClass = localObject.getClass();
        PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
        localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null) {
          localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        }
      }
      if (this._suppressableValue != null) {
        if (MARKER_FOR_EMPTY == this._suppressableValue)
        {
          if (localJsonSerializer1.isEmpty(paramSerializerProvider, localObject)) {
            serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
          }
        }
        else if (this._suppressableValue.equals(localObject))
        {
          serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
          return;
        }
      }
    } while ((localObject == paramObject) && (_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer1)));
    if (this._typeSerializer == null)
    {
      localJsonSerializer1.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    localJsonSerializer1.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = value(paramObject, paramJsonGenerator, paramSerializerProvider);
    if (localObject == null) {
      if (this._nullSerializer != null)
      {
        paramJsonGenerator.writeFieldName(this._name);
        this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
    }
    JsonSerializer localJsonSerializer1;
    label168:
    do
    {
      return;
      while ((localObject == paramObject) && (_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer1))) {
        do
        {
          JsonSerializer localJsonSerializer2 = this._serializer;
          localJsonSerializer1 = localJsonSerializer2;
          if (localJsonSerializer2 == null)
          {
            Class localClass = localObject.getClass();
            PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
            localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
            localJsonSerializer1 = localJsonSerializer2;
            if (localJsonSerializer2 == null) {
              localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
            }
          }
          if (this._suppressableValue == null) {
            break;
          }
          if (MARKER_FOR_EMPTY != this._suppressableValue) {
            break label168;
          }
        } while (localJsonSerializer1.isEmpty(paramSerializerProvider, localObject));
      }
      paramJsonGenerator.writeFieldName(this._name);
      if (this._typeSerializer != null) {
        break;
      }
      localJsonSerializer1.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      return;
    } while (!this._suppressableValue.equals(localObject));
    return;
    localJsonSerializer1.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
  }
  
  protected abstract Object value(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception;
  
  public abstract VirtualBeanPropertyWriter withConfig(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\VirtualBeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */