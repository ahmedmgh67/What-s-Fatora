package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer
  extends StdDeserializer<String[]>
  implements ContextualDeserializer
{
  public static final StringArrayDeserializer instance = new StringArrayDeserializer();
  private static final long serialVersionUID = 2L;
  protected JsonDeserializer<String> _elementDeserializer;
  protected final Boolean _unwrapSingle;
  
  public StringArrayDeserializer()
  {
    this(null, null);
  }
  
  protected StringArrayDeserializer(JsonDeserializer<?> paramJsonDeserializer, Boolean paramBoolean)
  {
    super(String[].class);
    this._elementDeserializer = paramJsonDeserializer;
    this._unwrapSingle = paramBoolean;
  }
  
  private final String[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject2 = null;
    Object localObject1 = null;
    int i;
    if ((this._unwrapSingle == Boolean.TRUE) || ((this._unwrapSingle == null) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))))
    {
      i = 1;
      if (i == 0) {
        break label80;
      }
      if (!paramJsonParser.hasToken(JsonToken.VALUE_NULL)) {
        break label70;
      }
      paramJsonParser = (JsonParser)localObject1;
      label52:
      localObject1 = new String[] { paramJsonParser };
    }
    label70:
    label80:
    do
    {
      return (String[])localObject1;
      i = 0;
      break;
      paramJsonParser = _parseString(paramJsonParser, paramDeserializationContext);
      break label52;
      if ((!paramJsonParser.hasToken(JsonToken.VALUE_STRING)) || (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT))) {
        break label114;
      }
      localObject1 = localObject2;
    } while (paramJsonParser.getText().length() == 0);
    label114:
    throw paramDeserializationContext.mappingException(this._valueClass);
  }
  
  protected final String[] _deserializeCustom(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    JsonDeserializer localJsonDeserializer = this._elementDeserializer;
    int i = 0;
    label146:
    label161:
    for (;;)
    {
      try
      {
        if (paramJsonParser.nextTextValue() != null) {
          break label146;
        }
        localObject = paramJsonParser.getCurrentToken();
        JsonToken localJsonToken = JsonToken.END_ARRAY;
        if (localObject == localJsonToken)
        {
          paramJsonParser = (String[])localObjectBuffer.completeAndClearBuffer(arrayOfObject, i, String.class);
          paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
          return paramJsonParser;
        }
        if (localObject == JsonToken.VALUE_NULL)
        {
          localObject = (String)localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (i < arrayOfObject.length) {
            break label161;
          }
          arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
          i = 0;
          int j = i + 1;
          arrayOfObject[i] = localObject;
          i = j;
          continue;
        }
        localObject = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      catch (Exception paramJsonParser)
      {
        throw JsonMappingException.wrapWithPath(paramJsonParser, String.class, i);
      }
      continue;
      Object localObject = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._elementDeserializer);
    JavaType localJavaType = paramDeserializationContext.constructType(String.class);
    if (localJsonDeserializer == null) {}
    for (localJsonDeserializer = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localJsonDeserializer = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer, paramBeanProperty, localJavaType))
    {
      paramBeanProperty = findFormatFeature(paramDeserializationContext, paramBeanProperty, String[].class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      paramDeserializationContext = localJsonDeserializer;
      if (localJsonDeserializer != null)
      {
        paramDeserializationContext = localJsonDeserializer;
        if (isDefaultDeserializer(localJsonDeserializer)) {
          paramDeserializationContext = null;
        }
      }
      if ((this._elementDeserializer != paramDeserializationContext) || (this._unwrapSingle != paramBeanProperty)) {
        break;
      }
      return this;
    }
    return new StringArrayDeserializer(paramDeserializationContext, paramBeanProperty);
  }
  
  public String[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    if (this._elementDeserializer != null) {
      return _deserializeCustom(paramJsonParser, paramDeserializationContext);
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object localObject1 = localObjectBuffer.resetAndStart();
    int i = 0;
    try
    {
      Object localObject3 = paramJsonParser.nextTextValue();
      Object localObject2 = localObject3;
      if (localObject3 == null)
      {
        JsonToken localJsonToken = paramJsonParser.getCurrentToken();
        localObject2 = JsonToken.END_ARRAY;
        if (localJsonToken == localObject2)
        {
          paramJsonParser = (String[])localObjectBuffer.completeAndClearBuffer((Object[])localObject1, i, String.class);
          paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
          return paramJsonParser;
        }
        localObject2 = localObject3;
        if (localJsonToken != JsonToken.VALUE_NULL) {
          localObject2 = _parseString(paramJsonParser, paramDeserializationContext);
        }
      }
      if (i >= localObject1.length)
      {
        localObject3 = localObjectBuffer.appendCompletedChunk((Object[])localObject1);
        i = 0;
        localObject1 = localObject3;
      }
      for (;;)
      {
        int j = i + 1;
        localObject1[i] = localObject2;
        i = j;
        break;
      }
    }
    catch (Exception paramJsonParser)
    {
      throw JsonMappingException.wrapWithPath(paramJsonParser, localObject1, localObjectBuffer.bufferedSize() + i);
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\StringArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */