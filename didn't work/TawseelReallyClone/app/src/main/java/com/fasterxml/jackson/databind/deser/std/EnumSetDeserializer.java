package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public class EnumSetDeserializer
  extends StdDeserializer<EnumSet<?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final Class<Enum> _enumClass;
  protected JsonDeserializer<Enum<?>> _enumDeserializer;
  protected final JavaType _enumType;
  protected final Boolean _unwrapSingle;
  
  public EnumSetDeserializer(JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(EnumSet.class);
    this._enumType = paramJavaType;
    this._enumClass = paramJavaType.getRawClass();
    if (!this._enumClass.isEnum()) {
      throw new IllegalArgumentException("Type " + paramJavaType + " not Java Enum type");
    }
    this._enumDeserializer = paramJsonDeserializer;
    this._unwrapSingle = null;
  }
  
  protected EnumSetDeserializer(EnumSetDeserializer paramEnumSetDeserializer, JsonDeserializer<?> paramJsonDeserializer, Boolean paramBoolean)
  {
    super(EnumSet.class);
    this._enumType = paramEnumSetDeserializer._enumType;
    this._enumClass = paramEnumSetDeserializer._enumClass;
    this._enumDeserializer = paramJsonDeserializer;
    this._unwrapSingle = paramBoolean;
  }
  
  private EnumSet constructSet()
  {
    return EnumSet.noneOf(this._enumClass);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Boolean localBoolean = findFormatFeature(paramDeserializationContext, paramBeanProperty, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    JsonDeserializer localJsonDeserializer = this._enumDeserializer;
    if (localJsonDeserializer == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer(this._enumType, paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer, paramBeanProperty, this._enumType)) {
      return withResolved(paramDeserializationContext, localBoolean);
    }
  }
  
  public EnumSet<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (!paramJsonParser.isExpectedStartArrayToken())
    {
      localObject = handleNonArray(paramJsonParser, paramDeserializationContext);
      return (EnumSet<?>)localObject;
    }
    EnumSet localEnumSet = constructSet();
    for (;;)
    {
      try
      {
        JsonToken localJsonToken = paramJsonParser.nextToken();
        localObject = localEnumSet;
        if (localJsonToken == JsonToken.END_ARRAY) {
          break;
        }
        if (localJsonToken == JsonToken.VALUE_NULL) {
          throw paramDeserializationContext.mappingException(this._enumClass);
        }
      }
      catch (Exception paramJsonParser)
      {
        throw JsonMappingException.wrapWithPath(paramJsonParser, localEnumSet, localEnumSet.size());
      }
      localObject = (Enum)this._enumDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      if (localObject != null) {
        localEnumSet.add(localObject);
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  protected EnumSet<?> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if ((this._unwrapSingle == Boolean.TRUE) || ((this._unwrapSingle == null) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {}
    for (int i = 1; i == 0; i = 0) {
      throw paramDeserializationContext.mappingException(EnumSet.class);
    }
    EnumSet localEnumSet = constructSet();
    if (paramJsonParser.hasToken(JsonToken.VALUE_NULL)) {
      throw paramDeserializationContext.mappingException(this._enumClass);
    }
    try
    {
      paramJsonParser = (Enum)this._enumDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser != null) {
        localEnumSet.add(paramJsonParser);
      }
      return localEnumSet;
    }
    catch (Exception paramJsonParser)
    {
      throw JsonMappingException.wrapWithPath(paramJsonParser, localEnumSet, localEnumSet.size());
    }
  }
  
  public boolean isCachable()
  {
    return this._enumType.getValueHandler() == null;
  }
  
  public EnumSetDeserializer withDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    if (this._enumDeserializer == paramJsonDeserializer) {
      return this;
    }
    return new EnumSetDeserializer(this, paramJsonDeserializer, this._unwrapSingle);
  }
  
  public EnumSetDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer, Boolean paramBoolean)
  {
    if ((this._unwrapSingle == paramBoolean) && (this._enumDeserializer == paramJsonDeserializer)) {
      return this;
    }
    return new EnumSetDeserializer(this, paramJsonDeserializer, paramBoolean);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\EnumSetDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */