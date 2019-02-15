package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class RawValue
  implements JsonSerializable
{
  protected Object _value;
  
  public RawValue(SerializableString paramSerializableString)
  {
    this._value = paramSerializableString;
  }
  
  public RawValue(JsonSerializable paramJsonSerializable)
  {
    this._value = paramJsonSerializable;
  }
  
  protected RawValue(Object paramObject, boolean paramBoolean)
  {
    this._value = paramObject;
  }
  
  public RawValue(String paramString)
  {
    this._value = paramString;
  }
  
  protected void _serialize(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._value instanceof SerializableString))
    {
      paramJsonGenerator.writeRawValue((SerializableString)this._value);
      return;
    }
    paramJsonGenerator.writeRawValue(String.valueOf(this._value));
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof RawValue)) {
        return false;
      }
      paramObject = (RawValue)paramObject;
    } while ((this._value == ((RawValue)paramObject)._value) || ((this._value != null) && (this._value.equals(((RawValue)paramObject)._value))));
    return false;
  }
  
  public int hashCode()
  {
    if (this._value == null) {
      return 0;
    }
    return this._value.hashCode();
  }
  
  public Object rawValue()
  {
    return this._value;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._value instanceof JsonSerializable))
    {
      paramJsonGenerator.writeObject(this._value);
      return;
    }
    _serialize(paramJsonGenerator);
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((this._value instanceof JsonSerializable))
    {
      ((JsonSerializable)this._value).serialize(paramJsonGenerator, paramSerializerProvider);
      return;
    }
    _serialize(paramJsonGenerator);
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if ((this._value instanceof JsonSerializable)) {
      ((JsonSerializable)this._value).serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
    }
    while (!(this._value instanceof SerializableString)) {
      return;
    }
    serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public String toString()
  {
    if (this._value == null) {}
    for (String str = "NULL";; str = this._value.getClass().getName()) {
      return String.format("[RawValue of type %s]", new Object[] { str });
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\RawValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */