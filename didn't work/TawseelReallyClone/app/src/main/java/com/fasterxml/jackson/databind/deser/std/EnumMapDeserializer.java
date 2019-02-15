package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer
  extends ContainerDeserializerBase<EnumMap<?, ?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _enumClass;
  protected KeyDeserializer _keyDeserializer;
  protected final JavaType _mapType;
  protected JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public EnumMapDeserializer(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer, JsonDeserializer<?> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    this._mapType = paramJavaType;
    this._enumClass = paramJavaType.getKeyType().getRawClass();
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  protected EnumMap<?, ?> constructMap()
  {
    return new EnumMap(this._enumClass);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = this._keyDeserializer;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = paramDeserializationContext.findKeyDeserializer(this._mapType.getKeyType(), paramBeanProperty);
    }
    localObject2 = this._valueDeserializer;
    Object localObject3 = this._mapType.getContentType();
    if (localObject2 == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer((JavaType)localObject3, paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty, (JavaType)localObject3))
    {
      localObject3 = this._valueTypeDeserializer;
      localObject2 = localObject3;
      if (localObject3 != null) {
        localObject2 = ((TypeDeserializer)localObject3).forProperty(paramBeanProperty);
      }
      return withResolved((KeyDeserializer)localObject1, paramDeserializationContext, (TypeDeserializer)localObject2);
    }
  }
  
  public EnumMap<?, ?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      return (EnumMap)_deserializeFromEmpty(paramJsonParser, paramDeserializationContext);
    }
    localEnumMap = constructMap();
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    while (paramJsonParser.nextToken() == JsonToken.FIELD_NAME)
    {
      String str = paramJsonParser.getCurrentName();
      Enum localEnum = (Enum)this._keyDeserializer.deserializeKey(str, paramDeserializationContext);
      if (localEnum == null)
      {
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
          throw paramDeserializationContext.weirdStringException(str, this._enumClass, "value not one of declared Enum instance names for " + this._mapType.getKeyType());
        }
        paramJsonParser.nextToken();
        paramJsonParser.skipChildren();
        continue;
      }
      Object localObject = paramJsonParser.nextToken();
      try
      {
        if (localObject == JsonToken.VALUE_NULL) {
          localObject = localJsonDeserializer.getNullValue(paramDeserializationContext);
        }
        for (;;)
        {
          localEnumMap.put(localEnum, localObject);
          break;
          if (localTypeDeserializer == null) {
            localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        return localEnumMap;
      }
      catch (Exception paramJsonParser)
      {
        wrapAndThrow(paramJsonParser, localEnumMap, str);
        return null;
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._mapType.getContentType();
  }
  
  public boolean isCachable()
  {
    return (this._valueDeserializer == null) && (this._keyDeserializer == null) && (this._valueTypeDeserializer == null);
  }
  
  public EnumMapDeserializer withResolved(KeyDeserializer paramKeyDeserializer, JsonDeserializer<?> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    if ((paramKeyDeserializer == this._keyDeserializer) && (paramJsonDeserializer == this._valueDeserializer) && (paramTypeDeserializer == this._valueTypeDeserializer)) {
      return this;
    }
    return new EnumMapDeserializer(this._mapType, paramKeyDeserializer, paramJsonDeserializer, this._valueTypeDeserializer);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\EnumMapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */