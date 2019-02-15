package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberSerializer
  extends StdScalarSerializer<Number>
{
  public static final NumberSerializer instance = new NumberSerializer(Number.class);
  protected final boolean _isInt;
  
  public NumberSerializer(Class<? extends Number> paramClass)
  {
    super(paramClass, false);
    if (paramClass == BigInteger.class) {
      bool = true;
    }
    this._isInt = bool;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (this._isInt)
    {
      visitIntFormat(paramJsonFormatVisitorWrapper, paramJavaType, JsonParser.NumberType.BIG_INTEGER);
      return;
    }
    if (handledType() == BigDecimal.class)
    {
      visitFloatFormat(paramJsonFormatVisitorWrapper, paramJavaType, JsonParser.NumberType.BIG_DECIMAL);
      return;
    }
    paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (this._isInt) {}
    for (paramSerializerProvider = "integer";; paramSerializerProvider = "number") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramNumber instanceof BigDecimal))
    {
      paramJsonGenerator.writeNumber((BigDecimal)paramNumber);
      return;
    }
    if ((paramNumber instanceof BigInteger))
    {
      paramJsonGenerator.writeNumber((BigInteger)paramNumber);
      return;
    }
    if ((paramNumber instanceof Long))
    {
      paramJsonGenerator.writeNumber(paramNumber.longValue());
      return;
    }
    if ((paramNumber instanceof Double))
    {
      paramJsonGenerator.writeNumber(paramNumber.doubleValue());
      return;
    }
    if ((paramNumber instanceof Float))
    {
      paramJsonGenerator.writeNumber(paramNumber.floatValue());
      return;
    }
    if (((paramNumber instanceof Integer)) || ((paramNumber instanceof Byte)) || ((paramNumber instanceof Short)))
    {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
      return;
    }
    paramJsonGenerator.writeNumber(paramNumber.toString());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\std\NumberSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */