package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

@JacksonStdImpl
public class EnumDeserializer
  extends StdScalarDeserializer<Object>
{
  private static final long serialVersionUID = 1L;
  protected Object[] _enumsByIndex;
  protected final CompactStringObjectMap _lookupByName;
  protected CompactStringObjectMap _lookupByToString;
  
  public EnumDeserializer(EnumResolver paramEnumResolver)
  {
    super(paramEnumResolver.getEnumClass());
    this._lookupByName = paramEnumResolver.constructLookup();
    this._enumsByIndex = paramEnumResolver.getRawEnums();
  }
  
  private final Object _deserializeAltString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, CompactStringObjectMap paramCompactStringObjectMap, String paramString)
    throws IOException
  {
    paramString = paramString.trim();
    if (paramString.length() == 0) {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
        break label100;
      }
    }
    label100:
    while (paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
    {
      return null;
      int i = paramString.charAt(0);
      if ((i >= 48) && (i <= 57)) {
        try
        {
          i = Integer.parseInt(paramString);
          if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            _failOnNumber(paramDeserializationContext, paramJsonParser, i);
          }
          if ((i >= 0) && (i <= this._enumsByIndex.length))
          {
            paramJsonParser = this._enumsByIndex[i];
            return paramJsonParser;
          }
        }
        catch (NumberFormatException paramJsonParser) {}
      }
    }
    throw paramDeserializationContext.weirdStringException(paramString, _enumClass(), "value not one of declared Enum instance names: " + paramCompactStringObjectMap.keys());
  }
  
  public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass, AnnotatedMethod paramAnnotatedMethod)
  {
    Class localClass = paramAnnotatedMethod.getRawParameterType(0);
    if (paramDeserializationConfig.canOverrideAccessModifiers()) {
      ClassUtil.checkAndFixAccess(paramAnnotatedMethod.getMember(), paramDeserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }
    return new FactoryBasedDeserializer(paramClass, paramAnnotatedMethod, localClass);
  }
  
  protected Object _deserializeOther(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    paramJsonParser.getCurrentToken();
    Object localObject;
    if ((paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) && (paramJsonParser.isExpectedStartArrayToken()))
    {
      paramJsonParser.nextToken();
      localObject = deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + _enumClass().getName() + "' value but there was more than a single value in the array");
      }
    }
    else
    {
      throw paramDeserializationContext.mappingException(_enumClass());
    }
    return localObject;
  }
  
  protected Class<?> _enumClass()
  {
    return handledType();
  }
  
  protected void _failOnNumber(DeserializationContext paramDeserializationContext, JsonParser paramJsonParser, int paramInt)
    throws IOException
  {
    throw InvalidFormatException.from(paramJsonParser, String.format("Not allowed to deserialize Enum value out of JSON number (%d): disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow", new Object[] { Integer.valueOf(paramInt) }), Integer.valueOf(paramInt), _enumClass());
  }
  
  protected CompactStringObjectMap _getToStringLookup()
  {
    CompactStringObjectMap localCompactStringObjectMap2 = this._lookupByToString;
    CompactStringObjectMap localCompactStringObjectMap1 = localCompactStringObjectMap2;
    if (localCompactStringObjectMap2 == null) {}
    try
    {
      localCompactStringObjectMap1 = EnumResolver.constructUnsafeUsingToString(_enumClass()).constructLookup();
      this._lookupByToString = localCompactStringObjectMap1;
      return localCompactStringObjectMap1;
    }
    finally {}
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = paramJsonParser.getCurrentToken();
    if ((localObject1 == JsonToken.VALUE_STRING) || (localObject1 == JsonToken.FIELD_NAME))
    {
      if (paramDeserializationContext.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {}
      for (localObject1 = _getToStringLookup();; localObject1 = this._lookupByName)
      {
        String str = paramJsonParser.getText();
        Object localObject3 = ((CompactStringObjectMap)localObject1).find(str);
        Object localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = _deserializeAltString(paramJsonParser, paramDeserializationContext, (CompactStringObjectMap)localObject1, str);
        }
        return localObject2;
      }
    }
    if (localObject1 == JsonToken.VALUE_NUMBER_INT)
    {
      int i = paramJsonParser.getIntValue();
      if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
        _failOnNumber(paramDeserializationContext, paramJsonParser, i);
      }
      if ((i >= 0) && (i <= this._enumsByIndex.length)) {
        return this._enumsByIndex[i];
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
        throw paramDeserializationContext.weirdNumberException(Integer.valueOf(i), _enumClass(), "index value outside legal index range [0.." + (this._enumsByIndex.length - 1) + "]");
      }
      return null;
    }
    return _deserializeOther(paramJsonParser, paramDeserializationContext);
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  protected static class FactoryBasedDeserializer
    extends StdDeserializer<Object>
    implements ContextualDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer<?> _deser;
    protected final Method _factory;
    protected final Class<?> _inputType;
    
    protected FactoryBasedDeserializer(FactoryBasedDeserializer paramFactoryBasedDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    {
      super();
      this._inputType = paramFactoryBasedDeserializer._inputType;
      this._factory = paramFactoryBasedDeserializer._factory;
      this._deser = paramJsonDeserializer;
    }
    
    public FactoryBasedDeserializer(Class<?> paramClass1, AnnotatedMethod paramAnnotatedMethod, Class<?> paramClass2)
    {
      super();
      this._factory = paramAnnotatedMethod.getAnnotated();
      this._inputType = paramClass2;
      this._deser = null;
    }
    
    public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
      throws JsonMappingException
    {
      FactoryBasedDeserializer localFactoryBasedDeserializer = this;
      if (this._deser == null)
      {
        localFactoryBasedDeserializer = this;
        if (this._inputType != String.class) {
          localFactoryBasedDeserializer = new FactoryBasedDeserializer(this, paramDeserializationContext.findContextualValueDeserializer(paramDeserializationContext.constructType(this._inputType), paramBeanProperty));
        }
      }
      return localFactoryBasedDeserializer;
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      if (this._deser != null) {
        paramJsonParser = this._deser.deserialize(paramJsonParser, paramDeserializationContext);
      }
      for (;;)
      {
        try
        {
          paramJsonParser = this._factory.invoke(this._valueClass, new Object[] { paramJsonParser });
          return paramJsonParser;
        }
        catch (Exception paramJsonParser)
        {
          JsonToken localJsonToken;
          paramJsonParser = ClassUtil.getRootCause(paramJsonParser);
          if (!(paramJsonParser instanceof IOException)) {
            continue;
          }
          throw ((IOException)paramJsonParser);
          throw paramDeserializationContext.instantiationException(this._valueClass, paramJsonParser);
        }
        localJsonToken = paramJsonParser.getCurrentToken();
        if ((localJsonToken == JsonToken.VALUE_STRING) || (localJsonToken == JsonToken.FIELD_NAME)) {
          paramJsonParser = paramJsonParser.getText();
        } else {
          paramJsonParser = paramJsonParser.getValueAsString();
        }
      }
    }
    
    public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      if (this._deser == null) {
        return deserialize(paramJsonParser, paramDeserializationContext);
      }
      return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\EnumDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */