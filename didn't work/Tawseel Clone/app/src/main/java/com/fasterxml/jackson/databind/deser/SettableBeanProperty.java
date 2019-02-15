package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty
  extends ConcreteBeanPropertyBase
  implements Serializable
{
  protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
  protected final transient Annotations _contextAnnotations;
  protected String _managedReferenceName;
  protected ObjectIdInfo _objectIdInfo;
  protected final PropertyName _propName;
  protected int _propertyIndex = -1;
  protected final JavaType _type;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  protected ViewMatcher _viewMatcher;
  protected final PropertyName _wrapperName;
  
  protected SettableBeanProperty(PropertyName paramPropertyName, JavaType paramJavaType, PropertyMetadata paramPropertyMetadata, JsonDeserializer<Object> paramJsonDeserializer)
  {
    super(paramPropertyMetadata);
    if (paramPropertyName == null) {}
    for (this._propName = PropertyName.NO_NAME;; this._propName = paramPropertyName.internSimpleName())
    {
      this._type = paramJavaType;
      this._wrapperName = null;
      this._contextAnnotations = null;
      this._viewMatcher = null;
      this._valueTypeDeserializer = null;
      this._valueDeserializer = paramJsonDeserializer;
      return;
    }
  }
  
  protected SettableBeanProperty(PropertyName paramPropertyName1, JavaType paramJavaType, PropertyName paramPropertyName2, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, PropertyMetadata paramPropertyMetadata)
  {
    super(paramPropertyMetadata);
    if (paramPropertyName1 == null) {}
    for (this._propName = PropertyName.NO_NAME;; this._propName = paramPropertyName1.internSimpleName())
    {
      this._type = paramJavaType;
      this._wrapperName = paramPropertyName2;
      this._contextAnnotations = paramAnnotations;
      this._viewMatcher = null;
      paramPropertyName1 = paramTypeDeserializer;
      if (paramTypeDeserializer != null) {
        paramPropertyName1 = paramTypeDeserializer.forProperty(this);
      }
      this._valueTypeDeserializer = paramPropertyName1;
      this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
      return;
    }
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    super(paramSettableBeanProperty);
    this._propName = paramSettableBeanProperty._propName;
    this._type = paramSettableBeanProperty._type;
    this._wrapperName = paramSettableBeanProperty._wrapperName;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueDeserializer = paramSettableBeanProperty._valueDeserializer;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    this._viewMatcher = paramSettableBeanProperty._viewMatcher;
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(paramSettableBeanProperty);
    this._propName = paramSettableBeanProperty._propName;
    this._type = paramSettableBeanProperty._type;
    this._wrapperName = paramSettableBeanProperty._wrapperName;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    if (paramJsonDeserializer == null) {}
    for (this._valueDeserializer = MISSING_VALUE_DESERIALIZER;; this._valueDeserializer = paramJsonDeserializer)
    {
      this._viewMatcher = paramSettableBeanProperty._viewMatcher;
      return;
    }
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty, PropertyName paramPropertyName)
  {
    super(paramSettableBeanProperty);
    this._propName = paramPropertyName;
    this._type = paramSettableBeanProperty._type;
    this._wrapperName = paramSettableBeanProperty._wrapperName;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueDeserializer = paramSettableBeanProperty._valueDeserializer;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    this._viewMatcher = paramSettableBeanProperty._viewMatcher;
  }
  
  protected SettableBeanProperty(BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations)
  {
    this(paramBeanPropertyDefinition.getFullName(), paramJavaType, paramBeanPropertyDefinition.getWrapperName(), paramTypeDeserializer, paramAnnotations, paramBeanPropertyDefinition.getMetadata());
  }
  
  @Deprecated
  protected SettableBeanProperty(String paramString, JavaType paramJavaType, PropertyName paramPropertyName, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, boolean paramBoolean)
  {
    this(new PropertyName(paramString), paramJavaType, paramPropertyName, paramTypeDeserializer, paramAnnotations, PropertyMetadata.construct(paramBoolean, null, null, null));
  }
  
  protected IOException _throwAsIOE(JsonParser paramJsonParser, Exception paramException)
    throws IOException
  {
    if ((paramException instanceof IOException)) {
      throw ((IOException)paramException);
    }
    if ((paramException instanceof RuntimeException)) {
      throw ((RuntimeException)paramException);
    }
    while (paramException.getCause() != null) {
      paramException = paramException.getCause();
    }
    throw JsonMappingException.from(paramJsonParser, paramException.getMessage(), paramException);
  }
  
  @Deprecated
  protected IOException _throwAsIOE(Exception paramException)
    throws IOException
  {
    return _throwAsIOE((JsonParser)null, paramException);
  }
  
  protected void _throwAsIOE(JsonParser paramJsonParser, Exception paramException, Object paramObject)
    throws IOException
  {
    if ((paramException instanceof IllegalArgumentException))
    {
      StringBuilder localStringBuilder;
      if (paramObject == null)
      {
        paramObject = "[NULL]";
        localStringBuilder = new StringBuilder("Problem deserializing property '").append(getName());
        localStringBuilder.append("' (expected type: ").append(getType());
        localStringBuilder.append("; actual type: ").append((String)paramObject).append(")");
        paramObject = paramException.getMessage();
        if (paramObject == null) {
          break label107;
        }
        localStringBuilder.append(", problem: ").append((String)paramObject);
      }
      for (;;)
      {
        throw JsonMappingException.from(paramJsonParser, localStringBuilder.toString(), paramException);
        paramObject = paramObject.getClass().getName();
        break;
        label107:
        localStringBuilder.append(" (no error message provided)");
      }
    }
    _throwAsIOE(paramJsonParser, paramException);
  }
  
  protected void _throwAsIOE(Exception paramException, Object paramObject)
    throws IOException
  {
    _throwAsIOE((JsonParser)null, paramException, paramObject);
  }
  
  public void assignIndex(int paramInt)
  {
    if (this._propertyIndex != -1) {
      throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + paramInt);
    }
    this._propertyIndex = paramInt;
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if (isRequired())
    {
      paramJsonObjectFormatVisitor.property(this);
      return;
    }
    paramJsonObjectFormatVisitor.optionalProperty(this);
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      return this._valueDeserializer.getNullValue(paramDeserializationContext);
    }
    if (this._valueTypeDeserializer != null) {
      return this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer);
    }
    return this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public abstract void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException;
  
  public abstract Object deserializeSetAndReturn(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException;
  
  public abstract <A extends Annotation> A getAnnotation(Class<A> paramClass);
  
  public <A extends Annotation> A getContextAnnotation(Class<A> paramClass)
  {
    return this._contextAnnotations.get(paramClass);
  }
  
  public int getCreatorIndex()
  {
    return -1;
  }
  
  protected final Class<?> getDeclaringClass()
  {
    return getMember().getDeclaringClass();
  }
  
  public PropertyName getFullName()
  {
    return this._propName;
  }
  
  public Object getInjectableValueId()
  {
    return null;
  }
  
  public String getManagedReferenceName()
  {
    return this._managedReferenceName;
  }
  
  public abstract AnnotatedMember getMember();
  
  public final String getName()
  {
    return this._propName.getSimpleName();
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    return this._objectIdInfo;
  }
  
  public int getPropertyIndex()
  {
    return this._propertyIndex;
  }
  
  public JavaType getType()
  {
    return this._type;
  }
  
  public JsonDeserializer<Object> getValueDeserializer()
  {
    JsonDeserializer localJsonDeserializer2 = this._valueDeserializer;
    JsonDeserializer localJsonDeserializer1 = localJsonDeserializer2;
    if (localJsonDeserializer2 == MISSING_VALUE_DESERIALIZER) {
      localJsonDeserializer1 = null;
    }
    return localJsonDeserializer1;
  }
  
  public TypeDeserializer getValueTypeDeserializer()
  {
    return this._valueTypeDeserializer;
  }
  
  public PropertyName getWrapperName()
  {
    return this._wrapperName;
  }
  
  public boolean hasValueDeserializer()
  {
    return (this._valueDeserializer != null) && (this._valueDeserializer != MISSING_VALUE_DESERIALIZER);
  }
  
  public boolean hasValueTypeDeserializer()
  {
    return this._valueTypeDeserializer != null;
  }
  
  public boolean hasViews()
  {
    return this._viewMatcher != null;
  }
  
  public abstract void set(Object paramObject1, Object paramObject2)
    throws IOException;
  
  public abstract Object setAndReturn(Object paramObject1, Object paramObject2)
    throws IOException;
  
  public void setManagedReferenceName(String paramString)
  {
    this._managedReferenceName = paramString;
  }
  
  public void setObjectIdInfo(ObjectIdInfo paramObjectIdInfo)
  {
    this._objectIdInfo = paramObjectIdInfo;
  }
  
  public void setViews(Class<?>[] paramArrayOfClass)
  {
    if (paramArrayOfClass == null)
    {
      this._viewMatcher = null;
      return;
    }
    this._viewMatcher = ViewMatcher.construct(paramArrayOfClass);
  }
  
  public String toString()
  {
    return "[property '" + getName() + "']";
  }
  
  public boolean visibleInView(Class<?> paramClass)
  {
    return (this._viewMatcher == null) || (this._viewMatcher.isVisibleForView(paramClass));
  }
  
  public abstract SettableBeanProperty withName(PropertyName paramPropertyName);
  
  @Deprecated
  public SettableBeanProperty withName(String paramString)
  {
    return withName(new PropertyName(paramString));
  }
  
  public SettableBeanProperty withSimpleName(String paramString)
  {
    if (this._propName == null) {}
    for (paramString = new PropertyName(paramString); paramString == this._propName; paramString = this._propName.withSimpleName(paramString)) {
      return this;
    }
    return withName(paramString);
  }
  
  public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\SettableBeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */