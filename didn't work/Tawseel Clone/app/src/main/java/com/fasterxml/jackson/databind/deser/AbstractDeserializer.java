package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class AbstractDeserializer
  extends JsonDeserializer<Object>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final boolean _acceptBoolean;
  protected final boolean _acceptDouble;
  protected final boolean _acceptInt;
  protected final boolean _acceptString;
  protected final Map<String, SettableBeanProperty> _backRefProperties;
  protected final JavaType _baseType;
  protected final ObjectIdReader _objectIdReader;
  
  protected AbstractDeserializer(BeanDescription paramBeanDescription)
  {
    this._baseType = paramBeanDescription.getType();
    this._objectIdReader = null;
    this._backRefProperties = null;
    paramBeanDescription = this._baseType.getRawClass();
    this._acceptString = paramBeanDescription.isAssignableFrom(String.class);
    if ((paramBeanDescription == Boolean.TYPE) || (paramBeanDescription.isAssignableFrom(Boolean.class)))
    {
      bool1 = true;
      this._acceptBoolean = bool1;
      if ((paramBeanDescription != Integer.TYPE) && (!paramBeanDescription.isAssignableFrom(Integer.class))) {
        break label119;
      }
    }
    label119:
    for (boolean bool1 = true;; bool1 = false)
    {
      this._acceptInt = bool1;
      if (paramBeanDescription != Double.TYPE)
      {
        bool1 = bool2;
        if (!paramBeanDescription.isAssignableFrom(Double.class)) {}
      }
      else
      {
        bool1 = true;
      }
      this._acceptDouble = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public AbstractDeserializer(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, Map<String, SettableBeanProperty> paramMap)
  {
    this._baseType = paramBeanDescription.getType();
    this._objectIdReader = paramBeanDeserializerBuilder.getObjectIdReader();
    this._backRefProperties = paramMap;
    paramBeanDeserializerBuilder = this._baseType.getRawClass();
    this._acceptString = paramBeanDeserializerBuilder.isAssignableFrom(String.class);
    if ((paramBeanDeserializerBuilder == Boolean.TYPE) || (paramBeanDeserializerBuilder.isAssignableFrom(Boolean.class)))
    {
      bool1 = true;
      this._acceptBoolean = bool1;
      if ((paramBeanDeserializerBuilder != Integer.TYPE) && (!paramBeanDeserializerBuilder.isAssignableFrom(Integer.class))) {
        break label132;
      }
    }
    label132:
    for (boolean bool1 = true;; bool1 = false)
    {
      this._acceptInt = bool1;
      if (paramBeanDeserializerBuilder != Double.TYPE)
      {
        bool1 = bool2;
        if (!paramBeanDeserializerBuilder.isAssignableFrom(Double.class)) {}
      }
      else
      {
        bool1 = true;
      }
      this._acceptDouble = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public static AbstractDeserializer constructForNonPOJO(BeanDescription paramBeanDescription)
  {
    return new AbstractDeserializer(paramBeanDescription);
  }
  
  protected Object _deserializeFromObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = this._objectIdReader.readObjectReference(paramJsonParser, paramDeserializationContext);
    paramDeserializationContext = paramDeserializationContext.findObjectId(localObject1, this._objectIdReader.generator, this._objectIdReader.resolver);
    Object localObject2 = paramDeserializationContext.resolve();
    if (localObject2 == null) {
      throw new UnresolvedForwardReference(paramJsonParser, "Could not resolve Object Id [" + localObject1 + "] -- unresolved forward-reference?", paramJsonParser.getCurrentLocation(), paramDeserializationContext);
    }
    return localObject2;
  }
  
  protected Object _deserializeIfNatural(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    switch (paramJsonParser.getCurrentTokenId())
    {
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return null;
            } while (!this._acceptString);
            return paramJsonParser.getText();
          } while (!this._acceptInt);
          return Integer.valueOf(paramJsonParser.getIntValue());
        } while (!this._acceptDouble);
        return Double.valueOf(paramJsonParser.getDoubleValue());
      } while (!this._acceptBoolean);
      return Boolean.TRUE;
    } while (!this._acceptBoolean);
    return Boolean.FALSE;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw paramDeserializationContext.instantiationException(this._baseType.getRawClass(), "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    Object localObject2;
    Object localObject1;
    if (this._objectIdReader != null)
    {
      localObject2 = paramJsonParser.getCurrentToken();
      if (localObject2 != null) {
        if (((JsonToken)localObject2).isScalarValue()) {
          localObject1 = _deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
        }
      }
    }
    do
    {
      return localObject1;
      localObject1 = localObject2;
      if (localObject2 == JsonToken.START_OBJECT) {
        localObject1 = paramJsonParser.nextToken();
      }
      if ((localObject1 == JsonToken.FIELD_NAME) && (this._objectIdReader.maySerializeAsObject()) && (this._objectIdReader.isValidReferencePropertyName(paramJsonParser.getCurrentName(), paramJsonParser))) {
        return _deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
      }
      localObject2 = _deserializeIfNatural(paramJsonParser, paramDeserializationContext);
      localObject1 = localObject2;
    } while (localObject2 != null);
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    if (this._backRefProperties == null) {
      return null;
    }
    return (SettableBeanProperty)this._backRefProperties.get(paramString);
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return this._objectIdReader;
  }
  
  public Class<?> handledType()
  {
    return this._baseType.getRawClass();
  }
  
  public boolean isCachable()
  {
    return true;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\AbstractDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */