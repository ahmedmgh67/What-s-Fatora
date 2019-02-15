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
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@JacksonStdImpl
public class CollectionDeserializer
  extends ContainerDeserializerBase<Collection<Object>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = -1L;
  protected final JavaType _collectionType;
  protected final JsonDeserializer<Object> _delegateDeserializer;
  protected final Boolean _unwrapSingle;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator)
  {
    this(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramValueInstantiator, null, null);
  }
  
  protected CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer1, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator, JsonDeserializer<Object> paramJsonDeserializer2, Boolean paramBoolean)
  {
    super(paramJavaType);
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer1;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    this._delegateDeserializer = paramJsonDeserializer2;
    this._unwrapSingle = paramBoolean;
  }
  
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer)
  {
    super(paramCollectionDeserializer._collectionType);
    this._collectionType = paramCollectionDeserializer._collectionType;
    this._valueDeserializer = paramCollectionDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramCollectionDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramCollectionDeserializer._valueInstantiator;
    this._delegateDeserializer = paramCollectionDeserializer._delegateDeserializer;
    this._unwrapSingle = paramCollectionDeserializer._unwrapSingle;
  }
  
  public CollectionDeserializer createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (this._valueInstantiator != null)
    {
      localObject1 = localObject2;
      if (this._valueInstantiator.canCreateUsingDelegate())
      {
        localObject1 = this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
        if (localObject1 == null) {
          throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._collectionType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
        }
        localObject1 = findDeserializer(paramDeserializationContext, (JavaType)localObject1, paramBeanProperty);
      }
    }
    Boolean localBoolean = findFormatFeature(paramDeserializationContext, paramBeanProperty, Collection.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    localObject2 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._valueDeserializer);
    Object localObject3 = this._collectionType.getContentType();
    if (localObject2 == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer((JavaType)localObject3, paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty, (JavaType)localObject3))
    {
      localObject3 = this._valueTypeDeserializer;
      localObject2 = localObject3;
      if (localObject3 != null) {
        localObject2 = ((TypeDeserializer)localObject3).forProperty(paramBeanProperty);
      }
      return withResolved((JsonDeserializer)localObject1, paramDeserializationContext, (TypeDeserializer)localObject2, localBoolean);
    }
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._delegateDeserializer != null) {
      return (Collection)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (paramJsonParser.hasToken(JsonToken.VALUE_STRING))
    {
      String str = paramJsonParser.getText();
      if (str.length() == 0) {
        return (Collection)this._valueInstantiator.createFromString(paramDeserializationContext, str);
      }
    }
    return deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault(paramDeserializationContext));
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException
  {
    Object localObject1;
    if (!paramJsonParser.isExpectedStartArrayToken())
    {
      localObject1 = handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection);
      return (Collection<Object>)localObject1;
    }
    paramJsonParser.setCurrentValue(paramCollection);
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    CollectionReferringAccumulator localCollectionReferringAccumulator;
    if (localJsonDeserializer.getObjectIdReader() == null) {
      localCollectionReferringAccumulator = null;
    }
    for (;;)
    {
      Object localObject3 = paramJsonParser.nextToken();
      localObject1 = paramCollection;
      if (localObject3 == JsonToken.END_ARRAY) {
        break;
      }
      try
      {
        if (localObject3 == JsonToken.VALUE_NULL)
        {
          localObject1 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (localCollectionReferringAccumulator == null) {
            break label165;
          }
          localCollectionReferringAccumulator.add(localObject1);
        }
      }
      catch (UnresolvedForwardReference localUnresolvedForwardReference)
      {
        for (;;)
        {
          if (localCollectionReferringAccumulator != null) {
            break label212;
          }
          throw JsonMappingException.from(paramJsonParser, "Unresolved forward reference but no identity info", localUnresolvedForwardReference);
          localCollectionReferringAccumulator = new CollectionReferringAccumulator(this._collectionType.getContentType().getRawClass(), paramCollection);
          break;
          if (localTypeDeserializer == null) {
            localObject2 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject2 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        paramCollection.add(localObject2);
      }
      catch (Exception paramJsonParser)
      {
        Object localObject2;
        label165:
        if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
        for (int i = 1;; i = 0)
        {
          if ((i != 0) || (!(paramJsonParser instanceof RuntimeException))) {
            break label240;
          }
          throw ((RuntimeException)paramJsonParser);
          label212:
          localObject3 = localCollectionReferringAccumulator.handleUnresolvedReference((UnresolvedForwardReference)localObject2);
          ((UnresolvedForwardReference)localObject2).getRoid().appendReferring((ReadableObjectId.Referring)localObject3);
          break;
        }
        label240:
        throw JsonMappingException.wrapWithPath(paramJsonParser, paramCollection, paramCollection.size());
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._collectionType.getContentType();
  }
  
  /* Error */
  protected final Collection<Object> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 53	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_unwrapSingle	Ljava/lang/Boolean;
    //   4: getstatic 310	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   7: if_acmpeq +20 -> 27
    //   10: aload_0
    //   11: getfield 53	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_unwrapSingle	Ljava/lang/Boolean;
    //   14: ifnonnull +33 -> 47
    //   17: aload_2
    //   18: getstatic 312	com/fasterxml/jackson/databind/DeserializationFeature:ACCEPT_SINGLE_VALUE_AS_ARRAY	Lcom/fasterxml/jackson/databind/DeserializationFeature;
    //   21: invokevirtual 275	com/fasterxml/jackson/databind/DeserializationContext:isEnabled	(Lcom/fasterxml/jackson/databind/DeserializationFeature;)Z
    //   24: ifeq +23 -> 47
    //   27: iconst_1
    //   28: istore 4
    //   30: iload 4
    //   32: ifne +21 -> 53
    //   35: aload_2
    //   36: aload_0
    //   37: getfield 43	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_collectionType	Lcom/fasterxml/jackson/databind/JavaType;
    //   40: invokevirtual 255	com/fasterxml/jackson/databind/JavaType:getRawClass	()Ljava/lang/Class;
    //   43: invokevirtual 316	com/fasterxml/jackson/databind/DeserializationContext:mappingException	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   46: athrow
    //   47: iconst_0
    //   48: istore 4
    //   50: goto -20 -> 30
    //   53: aload_0
    //   54: getfield 45	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_valueDeserializer	Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   57: astore 5
    //   59: aload_0
    //   60: getfield 47	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_valueTypeDeserializer	Lcom/fasterxml/jackson/databind/jsontype/TypeDeserializer;
    //   63: astore 6
    //   65: aload_1
    //   66: invokevirtual 319	com/fasterxml/jackson/core/JsonParser:getCurrentToken	()Lcom/fasterxml/jackson/core/JsonToken;
    //   69: astore 7
    //   71: aload 7
    //   73: getstatic 240	com/fasterxml/jackson/core/JsonToken:VALUE_NULL	Lcom/fasterxml/jackson/core/JsonToken;
    //   76: if_acmpne +20 -> 96
    //   79: aload 5
    //   81: aload_2
    //   82: invokevirtual 243	com/fasterxml/jackson/databind/JsonDeserializer:getNullValue	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   85: astore_1
    //   86: aload_3
    //   87: aload_1
    //   88: invokeinterface 265 2 0
    //   93: pop
    //   94: aload_3
    //   95: areturn
    //   96: aload 6
    //   98: ifnonnull +14 -> 112
    //   101: aload 5
    //   103: aload_1
    //   104: aload_2
    //   105: invokevirtual 178	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   108: astore_1
    //   109: goto -23 -> 86
    //   112: aload 5
    //   114: aload_1
    //   115: aload_2
    //   116: aload 6
    //   118: invokevirtual 262	com/fasterxml/jackson/databind/JsonDeserializer:deserializeWithType	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/jsontype/TypeDeserializer;)Ljava/lang/Object;
    //   121: astore_1
    //   122: goto -36 -> 86
    //   125: astore_1
    //   126: aload_1
    //   127: ldc 99
    //   129: aload_3
    //   130: invokeinterface 294 1 0
    //   135: invokestatic 298	com/fasterxml/jackson/databind/JsonMappingException:wrapWithPath	(Ljava/lang/Throwable;Ljava/lang/Object;I)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	CollectionDeserializer
    //   0	139	1	paramJsonParser	JsonParser
    //   0	139	2	paramDeserializationContext	DeserializationContext
    //   0	139	3	paramCollection	Collection<Object>
    //   28	21	4	i	int
    //   57	56	5	localJsonDeserializer	JsonDeserializer
    //   63	54	6	localTypeDeserializer	TypeDeserializer
    //   69	3	7	localJsonToken	JsonToken
    // Exception table:
    //   from	to	target	type
    //   71	86	125	java/lang/Exception
    //   101	109	125	java/lang/Exception
    //   112	122	125	java/lang/Exception
  }
  
  public boolean isCachable()
  {
    return (this._valueDeserializer == null) && (this._valueTypeDeserializer == null) && (this._delegateDeserializer == null);
  }
  
  @Deprecated
  protected CollectionDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer)
  {
    return withResolved(paramJsonDeserializer1, paramJsonDeserializer2, paramTypeDeserializer, this._unwrapSingle);
  }
  
  protected CollectionDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer, Boolean paramBoolean)
  {
    if ((paramJsonDeserializer1 == this._delegateDeserializer) && (paramJsonDeserializer2 == this._valueDeserializer) && (paramTypeDeserializer == this._valueTypeDeserializer) && (this._unwrapSingle == paramBoolean)) {
      return this;
    }
    return new CollectionDeserializer(this._collectionType, paramJsonDeserializer2, paramTypeDeserializer, this._valueInstantiator, paramJsonDeserializer1, paramBoolean);
  }
  
  private static final class CollectionReferring
    extends ReadableObjectId.Referring
  {
    private final CollectionDeserializer.CollectionReferringAccumulator _parent;
    public final List<Object> next = new ArrayList();
    
    CollectionReferring(CollectionDeserializer.CollectionReferringAccumulator paramCollectionReferringAccumulator, UnresolvedForwardReference paramUnresolvedForwardReference, Class<?> paramClass)
    {
      super(paramClass);
      this._parent = paramCollectionReferringAccumulator;
    }
    
    public void handleResolvedForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      this._parent.resolveForwardReference(paramObject1, paramObject2);
    }
  }
  
  public static final class CollectionReferringAccumulator
  {
    private List<CollectionDeserializer.CollectionReferring> _accumulator = new ArrayList();
    private final Class<?> _elementType;
    private final Collection<Object> _result;
    
    public CollectionReferringAccumulator(Class<?> paramClass, Collection<Object> paramCollection)
    {
      this._elementType = paramClass;
      this._result = paramCollection;
    }
    
    public void add(Object paramObject)
    {
      if (this._accumulator.isEmpty())
      {
        this._result.add(paramObject);
        return;
      }
      ((CollectionDeserializer.CollectionReferring)this._accumulator.get(this._accumulator.size() - 1)).next.add(paramObject);
    }
    
    public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference paramUnresolvedForwardReference)
    {
      paramUnresolvedForwardReference = new CollectionDeserializer.CollectionReferring(this, paramUnresolvedForwardReference, this._elementType);
      this._accumulator.add(paramUnresolvedForwardReference);
      return paramUnresolvedForwardReference;
    }
    
    public void resolveForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      Iterator localIterator = this._accumulator.iterator();
      CollectionDeserializer.CollectionReferring localCollectionReferring;
      for (Object localObject = this._result; localIterator.hasNext(); localObject = localCollectionReferring.next)
      {
        localCollectionReferring = (CollectionDeserializer.CollectionReferring)localIterator.next();
        if (localCollectionReferring.hasId(paramObject1))
        {
          localIterator.remove();
          ((Collection)localObject).add(paramObject2);
          ((Collection)localObject).addAll(localCollectionReferring.next);
          return;
        }
      }
      throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + paramObject1 + "] that wasn't previously seen as unresolved.");
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\CollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */