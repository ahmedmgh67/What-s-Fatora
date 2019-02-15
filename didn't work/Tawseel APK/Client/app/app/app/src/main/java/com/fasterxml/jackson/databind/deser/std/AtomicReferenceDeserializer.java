package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer
  extends StdDeserializer<AtomicReference<?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _referencedType;
  protected final JsonDeserializer<?> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public AtomicReferenceDeserializer(JavaType paramJavaType)
  {
    this(paramJavaType, null, null);
  }
  
  public AtomicReferenceDeserializer(JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(AtomicReference.class);
    this._referencedType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    if (localObject == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer(this._referencedType, paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject, paramBeanProperty, this._referencedType))
    {
      localObject = localTypeDeserializer;
      if (localTypeDeserializer != null) {
        localObject = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withResolved((TypeDeserializer)localObject, paramDeserializationContext);
    }
  }
  
  public AtomicReference<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._valueTypeDeserializer == null) {}
    for (paramJsonParser = this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);; paramJsonParser = this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer)) {
      return new AtomicReference(paramJsonParser);
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_NULL) {
      return getNullValue(paramDeserializationContext);
    }
    if ((localJsonToken != null) && (localJsonToken.isScalarValue())) {
      return deserialize(paramJsonParser, paramDeserializationContext);
    }
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  @Deprecated
  public AtomicReference<?> getNullValue()
  {
    return new AtomicReference();
  }
  
  public AtomicReference<?> getNullValue(DeserializationContext paramDeserializationContext)
  {
    return new AtomicReference();
  }
  
  public AtomicReferenceDeserializer withResolved(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    if ((paramJsonDeserializer == this._valueDeserializer) && (paramTypeDeserializer == this._valueTypeDeserializer)) {
      return this;
    }
    return new AtomicReferenceDeserializer(this._referencedType, paramTypeDeserializer, paramJsonDeserializer);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\AtomicReferenceDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */