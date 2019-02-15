package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer
  extends ContainerDeserializerBase<Map<Object, Object>>
  implements ContextualDeserializer, ResolvableDeserializer
{
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected final boolean _hasDefaultCreator;
  protected HashSet<String> _ignorableProperties;
  protected final KeyDeserializer _keyDeserializer;
  protected final JavaType _mapType;
  protected PropertyBasedCreator _propertyBasedCreator;
  protected boolean _standardStringKey;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    this._mapType = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    this._hasDefaultCreator = paramValueInstantiator.canCreateUsingDefault();
    this._delegateDeserializer = null;
    this._propertyBasedCreator = null;
    this._standardStringKey = _isStdKeyDeser(paramJavaType, paramKeyDeserializer);
  }
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer)
  {
    super(paramMapDeserializer._mapType);
    this._mapType = paramMapDeserializer._mapType;
    this._keyDeserializer = paramMapDeserializer._keyDeserializer;
    this._valueDeserializer = paramMapDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramMapDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramMapDeserializer._valueInstantiator;
    this._propertyBasedCreator = paramMapDeserializer._propertyBasedCreator;
    this._delegateDeserializer = paramMapDeserializer._delegateDeserializer;
    this._hasDefaultCreator = paramMapDeserializer._hasDefaultCreator;
    this._ignorableProperties = paramMapDeserializer._ignorableProperties;
    this._standardStringKey = paramMapDeserializer._standardStringKey;
  }
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, HashSet<String> paramHashSet)
  {
    super(paramMapDeserializer._mapType);
    this._mapType = paramMapDeserializer._mapType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramMapDeserializer._valueInstantiator;
    this._propertyBasedCreator = paramMapDeserializer._propertyBasedCreator;
    this._delegateDeserializer = paramMapDeserializer._delegateDeserializer;
    this._hasDefaultCreator = paramMapDeserializer._hasDefaultCreator;
    this._ignorableProperties = paramHashSet;
    this._standardStringKey = _isStdKeyDeser(this._mapType, paramKeyDeserializer);
  }
  
  private void handleUnresolvedReference(JsonParser paramJsonParser, MapReferringAccumulator paramMapReferringAccumulator, Object paramObject, UnresolvedForwardReference paramUnresolvedForwardReference)
    throws JsonMappingException
  {
    if (paramMapReferringAccumulator == null) {
      throw JsonMappingException.from(paramJsonParser, "Unresolved forward reference but no identity info.", paramUnresolvedForwardReference);
    }
    paramJsonParser = paramMapReferringAccumulator.handleUnresolvedReference(paramUnresolvedForwardReference, paramObject);
    paramUnresolvedForwardReference.getRoid().appendReferring(paramJsonParser);
  }
  
  public Map<Object, Object> _deserializeUsingCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, null);
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    Object localObject1;
    Object localObject2;
    if (paramJsonParser.isExpectedStartObjectToken())
    {
      localObject1 = paramJsonParser.nextFieldName();
      if (localObject1 == null) {
        break label273;
      }
      localObject2 = paramJsonParser.nextToken();
      if ((this._ignorableProperties == null) || (!this._ignorableProperties.contains(localObject1))) {
        break label104;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      localObject1 = paramJsonParser.nextFieldName();
      break;
      if (paramJsonParser.hasToken(JsonToken.FIELD_NAME))
      {
        localObject1 = paramJsonParser.getCurrentName();
        break;
      }
      localObject1 = null;
      break;
      label104:
      Object localObject3 = localPropertyBasedCreator.findCreatorProperty((String)localObject1);
      if (localObject3 != null)
      {
        if (!localPropertyValueBuffer.assignParameter((SettableBeanProperty)localObject3, ((SettableBeanProperty)localObject3).deserialize(paramJsonParser, paramDeserializationContext))) {
          continue;
        }
        paramJsonParser.nextToken();
        try
        {
          localObject2 = (Map)localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
          _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject2);
          return (Map<Object, Object>)localObject2;
        }
        catch (Exception paramJsonParser)
        {
          wrapAndThrow(paramJsonParser, this._mapType.getRawClass(), (String)localObject1);
          return null;
        }
      }
      localObject3 = this._keyDeserializer.deserializeKey((String)localObject1, paramDeserializationContext);
      try
      {
        if (localObject2 == JsonToken.VALUE_NULL)
        {
          localObject2 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          localObject1 = localObject2;
        }
        for (;;)
        {
          localPropertyValueBuffer.bufferMapProperty(localObject3, localObject1);
          break;
          if (localTypeDeserializer == null)
          {
            localObject2 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
            localObject1 = localObject2;
          }
          else
          {
            localObject2 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
            localObject1 = localObject2;
          }
        }
        try
        {
          paramJsonParser = (Map)localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
          return paramJsonParser;
        }
        catch (Exception paramJsonParser)
        {
          wrapAndThrow(paramJsonParser, this._mapType.getRawClass(), (String)localObject1);
        }
      }
      catch (Exception paramJsonParser)
      {
        wrapAndThrow(paramJsonParser, this._mapType.getRawClass(), (String)localObject1);
        return null;
      }
    }
    label273:
    return null;
  }
  
  protected final boolean _isStdKeyDeser(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer)
  {
    if (paramKeyDeserializer == null) {}
    do
    {
      do
      {
        return true;
        paramJavaType = paramJavaType.getKeyType();
      } while (paramJavaType == null);
      paramJavaType = paramJavaType.getRawClass();
    } while (((paramJavaType == String.class) || (paramJavaType == Object.class)) && (isDefaultKeyDeserializer(paramKeyDeserializer)));
    return false;
  }
  
  protected final void _readAndBind(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException
  {
    KeyDeserializer localKeyDeserializer = this._keyDeserializer;
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    MapReferringAccumulator localMapReferringAccumulator = null;
    int i;
    Object localObject1;
    label70:
    Object localObject3;
    Object localObject2;
    if (localJsonDeserializer.getObjectIdReader() != null)
    {
      i = 1;
      if (i != 0) {
        localMapReferringAccumulator = new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), paramMap);
      }
      if (!paramJsonParser.isExpectedStartObjectToken()) {
        break label130;
      }
      localObject1 = paramJsonParser.nextFieldName();
      if (localObject1 == null) {
        break label144;
      }
      localObject3 = localKeyDeserializer.deserializeKey((String)localObject1, paramDeserializationContext);
      localObject2 = paramJsonParser.nextToken();
      if ((this._ignorableProperties == null) || (!this._ignorableProperties.contains(localObject1))) {
        break label178;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      localObject1 = paramJsonParser.nextFieldName();
      break label70;
      i = 0;
      break;
      label130:
      localObject1 = paramJsonParser.getCurrentToken();
      if (localObject1 == JsonToken.END_OBJECT) {
        label144:
        return;
      }
      if (localObject1 != JsonToken.FIELD_NAME) {
        throw paramDeserializationContext.mappingException(this._mapType.getRawClass(), paramJsonParser.getCurrentToken());
      }
      localObject1 = paramJsonParser.getCurrentName();
      break label70;
      try
      {
        label178:
        if (localObject2 == JsonToken.VALUE_NULL)
        {
          localObject2 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (i == 0) {
            break label258;
          }
          localMapReferringAccumulator.put(localObject3, localObject2);
        }
      }
      catch (UnresolvedForwardReference localUnresolvedForwardReference)
      {
        for (;;)
        {
          handleUnresolvedReference(paramJsonParser, localMapReferringAccumulator, localObject3, localUnresolvedForwardReference);
          break;
          if (localTypeDeserializer == null) {
            localObject2 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject2 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        paramMap.put(localObject3, localObject2);
      }
      catch (Exception localException)
      {
        label258:
        wrapAndThrow(localException, paramMap, localUnresolvedForwardReference);
      }
    }
  }
  
  protected final void _readAndBindStringMap(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException
  {
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    MapReferringAccumulator localMapReferringAccumulator = null;
    int i;
    Object localObject1;
    label64:
    Object localObject2;
    if (localJsonDeserializer.getObjectIdReader() != null)
    {
      i = 1;
      if (i != 0) {
        localMapReferringAccumulator = new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), paramMap);
      }
      if (!paramJsonParser.isExpectedStartObjectToken()) {
        break label114;
      }
      localObject1 = paramJsonParser.nextFieldName();
      if (localObject1 == null) {
        break label128;
      }
      localObject2 = paramJsonParser.nextToken();
      if ((this._ignorableProperties == null) || (!this._ignorableProperties.contains(localObject1))) {
        break label162;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      localObject1 = paramJsonParser.nextFieldName();
      break label64;
      i = 0;
      break;
      label114:
      localObject1 = paramJsonParser.getCurrentToken();
      if (localObject1 == JsonToken.END_OBJECT) {
        label128:
        return;
      }
      if (localObject1 != JsonToken.FIELD_NAME) {
        throw paramDeserializationContext.mappingException(this._mapType.getRawClass(), paramJsonParser.getCurrentToken());
      }
      localObject1 = paramJsonParser.getCurrentName();
      break label64;
      try
      {
        label162:
        if (localObject2 == JsonToken.VALUE_NULL)
        {
          localObject2 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (i == 0) {
            break label242;
          }
          localMapReferringAccumulator.put(localObject1, localObject2);
        }
      }
      catch (UnresolvedForwardReference localUnresolvedForwardReference)
      {
        Object localObject3;
        for (;;)
        {
          handleUnresolvedReference(paramJsonParser, localMapReferringAccumulator, localObject1, localUnresolvedForwardReference);
          break;
          if (localTypeDeserializer == null) {
            localObject3 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject3 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        paramMap.put(localObject1, localObject3);
      }
      catch (Exception localException)
      {
        label242:
        wrapAndThrow(localException, paramMap, (String)localObject1);
      }
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = this._keyDeserializer;
    Object localObject1;
    Object localObject3;
    label72:
    Object localObject4;
    HashSet localHashSet;
    if (localObject2 == null)
    {
      localObject1 = paramDeserializationContext.findKeyDeserializer(this._mapType.getKeyType(), paramBeanProperty);
      localObject3 = this._valueDeserializer;
      localObject2 = localObject3;
      if (paramBeanProperty != null) {
        localObject2 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, (JsonDeserializer)localObject3);
      }
      localObject3 = this._mapType.getContentType();
      if (localObject2 != null) {
        break label224;
      }
      localObject2 = paramDeserializationContext.findContextualValueDeserializer((JavaType)localObject3, paramBeanProperty);
      localObject4 = this._valueTypeDeserializer;
      localObject3 = localObject4;
      if (localObject4 != null) {
        localObject3 = ((TypeDeserializer)localObject4).forProperty(paramBeanProperty);
      }
      localHashSet = this._ignorableProperties;
      paramDeserializationContext = paramDeserializationContext.getAnnotationIntrospector();
      localObject4 = localHashSet;
      if (paramDeserializationContext == null) {
        break label251;
      }
      localObject4 = localHashSet;
      if (paramBeanProperty == null) {
        break label251;
      }
      paramBeanProperty = paramBeanProperty.getMember();
      localObject4 = localHashSet;
      if (paramBeanProperty == null) {
        break label251;
      }
      paramBeanProperty = paramDeserializationContext.findPropertiesToIgnore(paramBeanProperty, false);
      localObject4 = localHashSet;
      if (paramBeanProperty == null) {
        break label251;
      }
      if (localHashSet != null) {
        break label238;
      }
    }
    label224:
    label238:
    for (paramDeserializationContext = new HashSet();; paramDeserializationContext = new HashSet(localHashSet))
    {
      int j = paramBeanProperty.length;
      int i = 0;
      for (;;)
      {
        localObject4 = paramDeserializationContext;
        if (i >= j) {
          break;
        }
        paramDeserializationContext.add(paramBeanProperty[i]);
        i += 1;
      }
      localObject1 = localObject2;
      if (!(localObject2 instanceof ContextualKeyDeserializer)) {
        break;
      }
      localObject1 = ((ContextualKeyDeserializer)localObject2).createContextual(paramDeserializationContext, paramBeanProperty);
      break;
      localObject2 = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty, (JavaType)localObject3);
      break label72;
    }
    label251:
    return withResolved((KeyDeserializer)localObject1, (TypeDeserializer)localObject3, (JsonDeserializer)localObject2, (HashSet)localObject4);
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._propertyBasedCreator != null) {
      return _deserializeUsingCreator(paramJsonParser, paramDeserializationContext);
    }
    if (this._delegateDeserializer != null) {
      return (Map)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (!this._hasDefaultCreator) {
      throw paramDeserializationContext.instantiationException(getMapClass(), "No default constructor found");
    }
    Object localObject = paramJsonParser.getCurrentToken();
    if ((localObject != JsonToken.START_OBJECT) && (localObject != JsonToken.FIELD_NAME) && (localObject != JsonToken.END_OBJECT))
    {
      if (localObject == JsonToken.VALUE_STRING) {
        return (Map)this._valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
      }
      return (Map)_deserializeFromEmpty(paramJsonParser, paramDeserializationContext);
    }
    localObject = (Map)this._valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (this._standardStringKey)
    {
      _readAndBindStringMap(paramJsonParser, paramDeserializationContext, (Map)localObject);
      return (Map<Object, Object>)localObject;
    }
    _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject);
    return (Map<Object, Object>)localObject;
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException
  {
    paramJsonParser.setCurrentValue(paramMap);
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken != JsonToken.START_OBJECT) && (localJsonToken != JsonToken.FIELD_NAME)) {
      throw paramDeserializationContext.mappingException(getMapClass());
    }
    if (this._standardStringKey)
    {
      _readAndBindStringMap(paramJsonParser, paramDeserializationContext, paramMap);
      return paramMap;
    }
    _readAndBind(paramJsonParser, paramDeserializationContext, paramMap);
    return paramMap;
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
  
  public final Class<?> getMapClass()
  {
    return this._mapType.getRawClass();
  }
  
  public JavaType getValueType()
  {
    return this._mapType;
  }
  
  public boolean isCachable()
  {
    return (this._valueDeserializer == null) && (this._keyDeserializer == null) && (this._valueTypeDeserializer == null) && (this._ignorableProperties == null);
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    Object localObject;
    if (this._valueInstantiator.canCreateUsingDelegate())
    {
      localObject = this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
      if (localObject == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      this._delegateDeserializer = findDeserializer(paramDeserializationContext, (JavaType)localObject, null);
    }
    if (this._valueInstantiator.canCreateFromObjectWith())
    {
      localObject = this._valueInstantiator.getFromObjectArguments(paramDeserializationContext.getConfig());
      this._propertyBasedCreator = PropertyBasedCreator.construct(paramDeserializationContext, this._valueInstantiator, (SettableBeanProperty[])localObject);
    }
    this._standardStringKey = _isStdKeyDeser(this._mapType, this._keyDeserializer);
  }
  
  public void setIgnorableProperties(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    for (paramArrayOfString = null;; paramArrayOfString = ArrayBuilders.arrayToSet(paramArrayOfString))
    {
      this._ignorableProperties = paramArrayOfString;
      return;
    }
  }
  
  protected MapDeserializer withResolved(KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer, HashSet<String> paramHashSet)
  {
    if ((this._keyDeserializer == paramKeyDeserializer) && (this._valueDeserializer == paramJsonDeserializer) && (this._valueTypeDeserializer == paramTypeDeserializer) && (this._ignorableProperties == paramHashSet)) {
      return this;
    }
    return new MapDeserializer(this, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer, paramHashSet);
  }
  
  @Deprecated
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject)
    throws IOException
  {
    wrapAndThrow(paramThrowable, paramObject, null);
  }
  
  static final class MapReferring
    extends ReadableObjectId.Referring
  {
    private final MapDeserializer.MapReferringAccumulator _parent;
    public final Object key;
    public final Map<Object, Object> next = new LinkedHashMap();
    
    MapReferring(MapDeserializer.MapReferringAccumulator paramMapReferringAccumulator, UnresolvedForwardReference paramUnresolvedForwardReference, Class<?> paramClass, Object paramObject)
    {
      super(paramClass);
      this._parent = paramMapReferringAccumulator;
      this.key = paramObject;
    }
    
    public void handleResolvedForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      this._parent.resolveForwardReference(paramObject1, paramObject2);
    }
  }
  
  private static final class MapReferringAccumulator
  {
    private List<MapDeserializer.MapReferring> _accumulator = new ArrayList();
    private Map<Object, Object> _result;
    private final Class<?> _valueType;
    
    public MapReferringAccumulator(Class<?> paramClass, Map<Object, Object> paramMap)
    {
      this._valueType = paramClass;
      this._result = paramMap;
    }
    
    public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference paramUnresolvedForwardReference, Object paramObject)
    {
      paramUnresolvedForwardReference = new MapDeserializer.MapReferring(this, paramUnresolvedForwardReference, this._valueType, paramObject);
      this._accumulator.add(paramUnresolvedForwardReference);
      return paramUnresolvedForwardReference;
    }
    
    public void put(Object paramObject1, Object paramObject2)
    {
      if (this._accumulator.isEmpty())
      {
        this._result.put(paramObject1, paramObject2);
        return;
      }
      ((MapDeserializer.MapReferring)this._accumulator.get(this._accumulator.size() - 1)).next.put(paramObject1, paramObject2);
    }
    
    public void resolveForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      Iterator localIterator = this._accumulator.iterator();
      MapDeserializer.MapReferring localMapReferring;
      for (Map localMap = this._result; localIterator.hasNext(); localMap = localMapReferring.next)
      {
        localMapReferring = (MapDeserializer.MapReferring)localIterator.next();
        if (localMapReferring.hasId(paramObject1))
        {
          localIterator.remove();
          localMap.put(localMapReferring.key, paramObject2);
          localMap.putAll(localMapReferring.next);
          return;
        }
      }
      throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + paramObject1 + "] that wasn't previously seen as unresolved.");
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\MapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */