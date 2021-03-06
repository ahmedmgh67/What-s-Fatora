package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;

public class BeanAsArrayBuilderDeserializer
  extends BeanDeserializerBase
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedMethod _buildMethod;
  protected final BeanDeserializerBase _delegate;
  protected final SettableBeanProperty[] _orderedProperties;
  
  public BeanAsArrayBuilderDeserializer(BeanDeserializerBase paramBeanDeserializerBase, SettableBeanProperty[] paramArrayOfSettableBeanProperty, AnnotatedMethod paramAnnotatedMethod)
  {
    super(paramBeanDeserializerBase);
    this._delegate = paramBeanDeserializerBase;
    this._orderedProperties = paramArrayOfSettableBeanProperty;
    this._buildMethod = paramAnnotatedMethod;
  }
  
  protected Object _deserializeFromNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw paramDeserializationContext.mappingException("Can not deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", new Object[] { this._beanType.getRawClass().getName(), paramJsonParser.getCurrentToken() });
  }
  
  protected Object _deserializeNonVanilla(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1;
    if (this._nonStandardCreation)
    {
      localObject1 = _deserializeWithCreator(paramJsonParser, paramDeserializationContext);
      return localObject1;
    }
    Object localObject2 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (this._injectables != null) {
      injectValues(paramDeserializationContext, localObject2);
    }
    Class localClass;
    label55:
    SettableBeanProperty[] arrayOfSettableBeanProperty;
    int i;
    int j;
    if (this._needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      arrayOfSettableBeanProperty = this._orderedProperties;
      i = 0;
      j = arrayOfSettableBeanProperty.length;
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
        break;
      }
      if (i == j)
      {
        if (this._ignoreAllUnknown) {
          break label190;
        }
        throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most %d properties (in JSON Array)", new Object[] { Integer.valueOf(j) });
        localClass = null;
        break label55;
      }
      localObject1 = arrayOfSettableBeanProperty[i];
      i += 1;
      if ((localObject1 != null) && ((localClass == null) || (((SettableBeanProperty)localObject1).visibleInView(localClass)))) {
        try
        {
          ((SettableBeanProperty)localObject1).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject2);
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, localObject2, ((SettableBeanProperty)localObject1).getName(), paramDeserializationContext);
        }
      } else {
        paramJsonParser.skipChildren();
      }
    }
    for (;;)
    {
      label190:
      localObject1 = localObject2;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
        break;
      }
      paramJsonParser.skipChildren();
    }
  }
  
  protected final Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, this._objectIdReader);
    SettableBeanProperty[] arrayOfSettableBeanProperty = this._orderedProperties;
    int j = arrayOfSettableBeanProperty.length;
    int i = 0;
    Object localObject1 = null;
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
    {
      SettableBeanProperty localSettableBeanProperty1;
      label57:
      Object localObject2;
      if (i < j)
      {
        localSettableBeanProperty1 = arrayOfSettableBeanProperty[i];
        if (localSettableBeanProperty1 != null) {
          break label88;
        }
        paramJsonParser.skipChildren();
        localObject2 = localObject1;
      }
      for (;;)
      {
        i += 1;
        localObject1 = localObject2;
        break;
        localSettableBeanProperty1 = null;
        break label57;
        label88:
        Object localObject3;
        if (localObject1 != null)
        {
          try
          {
            localObject2 = localSettableBeanProperty1.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
          }
          catch (Exception localException1)
          {
            wrapAndThrow(localException1, localObject1, localSettableBeanProperty1.getName(), paramDeserializationContext);
            localObject3 = localObject1;
          }
        }
        else
        {
          String str = localSettableBeanProperty1.getName();
          SettableBeanProperty localSettableBeanProperty2 = localPropertyBasedCreator.findCreatorProperty(str);
          Object localObject4;
          if (localSettableBeanProperty2 != null)
          {
            localObject3 = localObject1;
            if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty2, localSettableBeanProperty2.deserialize(paramJsonParser, paramDeserializationContext))) {
              try
              {
                localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
                localObject1 = localObject3;
                localObject3 = localObject1;
                if (localObject1.getClass() == this._beanType.getRawClass()) {
                  continue;
                }
                throw paramDeserializationContext.mappingException("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", new Object[] { this._beanType.getRawClass().getName(), localObject1.getClass().getName() });
              }
              catch (Exception localException2)
              {
                wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
                localObject4 = localObject1;
              }
            }
          }
          else
          {
            localObject4 = localObject1;
            if (!localPropertyValueBuffer.readIdProperty(str))
            {
              localPropertyValueBuffer.bufferProperty(localSettableBeanProperty1, localSettableBeanProperty1.deserialize(paramJsonParser, paramDeserializationContext));
              localObject4 = localObject1;
            }
          }
        }
      }
    }
    paramJsonParser = (JsonParser)localObject1;
    if (localObject1 == null) {}
    try
    {
      paramJsonParser = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
      return paramJsonParser;
    }
    catch (Exception paramJsonParser)
    {
      wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
    }
    return null;
  }
  
  protected Object _deserializeWithCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._delegateDeserializer != null) {
      return this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (this._propertyBasedCreator != null) {
      return _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext);
    }
    if (this._beanType.isAbstract()) {
      throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
    }
    throw JsonMappingException.from(paramJsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
  }
  
  protected BeanAsArrayBuilderDeserializer asArrayDeserializer()
  {
    return this;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return finishBuild(paramDeserializationContext, _deserializeFromNonArray(paramJsonParser, paramDeserializationContext));
    }
    if (!this._vanillaProcessing) {
      return finishBuild(paramDeserializationContext, _deserializeNonVanilla(paramJsonParser, paramDeserializationContext));
    }
    Object localObject1 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
    SettableBeanProperty[] arrayOfSettableBeanProperty = this._orderedProperties;
    int i = 0;
    int j = arrayOfSettableBeanProperty.length;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return finishBuild(paramDeserializationContext, localObject1);
    }
    if (i == j)
    {
      if (!this._ignoreAllUnknown) {
        throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most %d properties (in JSON Array)", new Object[] { Integer.valueOf(j) });
      }
    }
    else
    {
      SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
      if (localSettableBeanProperty != null) {}
      for (;;)
      {
        try
        {
          Object localObject2 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
          localObject1 = localObject2;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, localObject1, localSettableBeanProperty.getName(), paramDeserializationContext);
          continue;
        }
        i += 1;
        break;
        paramJsonParser.skipChildren();
      }
    }
    while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
      paramJsonParser.skipChildren();
    }
    return finishBuild(paramDeserializationContext, localObject1);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    SettableBeanProperty[] arrayOfSettableBeanProperty = this._orderedProperties;
    int i = 0;
    int j = arrayOfSettableBeanProperty.length;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return finishBuild(paramDeserializationContext, paramObject);
    }
    if (i == j)
    {
      if (!this._ignoreAllUnknown) {
        throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most %d properties (in JSON Array)", new Object[] { Integer.valueOf(j) });
      }
    }
    else
    {
      SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
      if (localSettableBeanProperty != null) {}
      for (;;)
      {
        try
        {
          Object localObject = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
          paramObject = localObject;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramObject, localSettableBeanProperty.getName(), paramDeserializationContext);
          continue;
        }
        i += 1;
        break;
        paramJsonParser.skipChildren();
      }
    }
    while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
      paramJsonParser.skipChildren();
    }
    return finishBuild(paramDeserializationContext, paramObject);
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserializeFromNonArray(paramJsonParser, paramDeserializationContext);
  }
  
  protected final Object finishBuild(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    try
    {
      paramObject = this._buildMethod.getMember().invoke(paramObject, new Object[0]);
      return paramObject;
    }
    catch (Exception paramObject)
    {
      wrapInstantiationProblem((Throwable)paramObject, paramDeserializationContext);
    }
    return null;
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    return this._delegate.unwrappingDeserializer(paramNameTransformer);
  }
  
  public BeanAsArrayBuilderDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BeanAsArrayBuilderDeserializer(this._delegate.withIgnorableProperties(paramHashSet), this._orderedProperties, this._buildMethod);
  }
  
  public BeanAsArrayBuilderDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BeanAsArrayBuilderDeserializer(this._delegate.withObjectIdReader(paramObjectIdReader), this._orderedProperties, this._buildMethod);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\impl\BeanAsArrayBuilderDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */