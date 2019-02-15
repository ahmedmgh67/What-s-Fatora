package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public final class TypeWrappedSerializer
  extends JsonSerializer<Object>
{
  protected final JsonSerializer<Object> _serializer;
  protected final TypeSerializer _typeSerializer;
  
  public TypeWrappedSerializer(TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    this._typeSerializer = paramTypeSerializer;
    this._serializer = paramJsonSerializer;
  }
  
  public Class<Object> handledType()
  {
    return Object.class;
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    this._serializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    this._serializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
  
  public TypeSerializer typeSerializer()
  {
    return this._typeSerializer;
  }
  
  public JsonSerializer<Object> valueSerializer()
  {
    return this._serializer;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\impl\TypeWrappedSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */