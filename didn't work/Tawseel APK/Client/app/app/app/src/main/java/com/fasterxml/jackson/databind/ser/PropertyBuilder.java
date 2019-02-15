package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;

public class PropertyBuilder
{
  private static final Object NO_DEFAULT_MARKER = Boolean.FALSE;
  protected final AnnotationIntrospector _annotationIntrospector;
  protected final BeanDescription _beanDesc;
  protected final SerializationConfig _config;
  protected Object _defaultBean;
  protected final JsonInclude.Value _defaultInclusion;
  
  public PropertyBuilder(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription)
  {
    this._config = paramSerializationConfig;
    this._beanDesc = paramBeanDescription;
    this._defaultInclusion = paramBeanDescription.findPropertyInclusion(paramSerializationConfig.getDefaultPropertyInclusion());
    this._annotationIntrospector = this._config.getAnnotationIntrospector();
  }
  
  protected Object _throwWrapped(Exception paramException, String paramString, Object paramObject)
  {
    while (paramException.getCause() != null) {
      paramException = paramException.getCause();
    }
    if ((paramException instanceof Error)) {
      throw ((Error)paramException);
    }
    if ((paramException instanceof RuntimeException)) {
      throw ((RuntimeException)paramException);
    }
    throw new IllegalArgumentException("Failed to get property '" + paramString + "' of default " + paramObject.getClass().getName() + " instance");
  }
  
  protected BeanPropertyWriter buildWriter(SerializerProvider paramSerializerProvider, BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, JsonSerializer<?> paramJsonSerializer, TypeSerializer paramTypeSerializer1, TypeSerializer paramTypeSerializer2, AnnotatedMember paramAnnotatedMember, boolean paramBoolean)
    throws JsonMappingException
  {
    Object localObject2 = findSerializationType(paramAnnotatedMember, paramBoolean, paramJavaType);
    Object localObject1 = localObject2;
    if (paramTypeSerializer2 != null)
    {
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = paramJavaType;
      }
      if (((JavaType)localObject1).getContentType() == null) {
        throw new IllegalStateException("Problem trying to create BeanPropertyWriter for property '" + paramBeanPropertyDefinition.getName() + "' (of type " + this._beanDesc.getType() + "); serialization type " + localObject1 + " has no content");
      }
      localObject1 = ((JavaType)localObject1).withContentTypeHandler(paramTypeSerializer2);
      ((JavaType)localObject1).getContentType();
    }
    localObject2 = null;
    boolean bool = false;
    paramBoolean = false;
    JsonInclude.Include localInclude = this._defaultInclusion.withOverrides(paramBeanPropertyDefinition.findInclusion()).getValueInclusion();
    paramTypeSerializer2 = localInclude;
    if (localInclude == JsonInclude.Include.USE_DEFAULTS) {
      paramTypeSerializer2 = JsonInclude.Include.ALWAYS;
    }
    switch (paramTypeSerializer2)
    {
    }
    for (bool = paramBoolean;; bool = true)
    {
      paramBoolean = bool;
      paramTypeSerializer2 = (TypeSerializer)localObject2;
      if (paramJavaType.isContainerType())
      {
        paramBoolean = bool;
        paramTypeSerializer2 = (TypeSerializer)localObject2;
        if (!this._config.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS))
        {
          paramTypeSerializer2 = BeanPropertyWriter.MARKER_FOR_EMPTY;
          paramBoolean = bool;
        }
      }
      for (;;)
      {
        paramBeanPropertyDefinition = new BeanPropertyWriter(paramBeanPropertyDefinition, paramAnnotatedMember, this._beanDesc.getClassAnnotations(), paramJavaType, paramJsonSerializer, paramTypeSerializer1, (JavaType)localObject1, paramBoolean, paramTypeSerializer2);
        paramJavaType = this._annotationIntrospector.findNullSerializer(paramAnnotatedMember);
        if (paramJavaType != null) {
          paramBeanPropertyDefinition.assignNullSerializer(paramSerializerProvider.serializerInstance(paramAnnotatedMember, paramJavaType));
        }
        paramJavaType = this._annotationIntrospector.findUnwrappingNameTransformer(paramAnnotatedMember);
        paramSerializerProvider = paramBeanPropertyDefinition;
        if (paramJavaType != null) {
          paramSerializerProvider = paramBeanPropertyDefinition.unwrappingWriter(paramJavaType);
        }
        return paramSerializerProvider;
        if (localObject1 == null)
        {
          paramTypeSerializer2 = paramJavaType;
          label331:
          if (this._defaultInclusion.getValueInclusion() != JsonInclude.Include.NON_DEFAULT) {
            break label380;
          }
        }
        label380:
        for (localObject2 = getPropertyDefaultValue(paramBeanPropertyDefinition.getName(), paramAnnotatedMember, paramTypeSerializer2);; localObject2 = getDefaultValue(paramTypeSerializer2))
        {
          if (localObject2 != null) {
            break label391;
          }
          paramBoolean = true;
          paramTypeSerializer2 = (TypeSerializer)localObject2;
          break;
          paramTypeSerializer2 = (TypeSerializer)localObject1;
          break label331;
        }
        label391:
        paramBoolean = bool;
        paramTypeSerializer2 = (TypeSerializer)localObject2;
        if (localObject2.getClass().isArray())
        {
          paramTypeSerializer2 = ArrayBuilders.getArrayComparator(localObject2);
          paramBoolean = bool;
          continue;
          bool = true;
          paramBoolean = bool;
          paramTypeSerializer2 = (TypeSerializer)localObject2;
          if (paramJavaType.isReferenceType())
          {
            paramTypeSerializer2 = BeanPropertyWriter.MARKER_FOR_EMPTY;
            paramBoolean = bool;
            continue;
            paramBoolean = true;
            paramTypeSerializer2 = BeanPropertyWriter.MARKER_FOR_EMPTY;
          }
        }
      }
    }
  }
  
  protected JavaType findSerializationType(Annotated paramAnnotated, boolean paramBoolean, JavaType paramJavaType)
    throws JsonMappingException
  {
    JavaType localJavaType = this._annotationIntrospector.refineSerializationType(this._config, paramAnnotated, paramJavaType);
    Object localObject = paramJavaType;
    if (localJavaType != paramJavaType)
    {
      localObject = localJavaType.getRawClass();
      paramJavaType = paramJavaType.getRawClass();
      if (((Class)localObject).isAssignableFrom(paramJavaType))
      {
        paramBoolean = true;
        localObject = localJavaType;
      }
    }
    else
    {
      paramAnnotated = this._annotationIntrospector.findSerializationTyping(paramAnnotated);
      bool = paramBoolean;
      if (paramAnnotated != null)
      {
        bool = paramBoolean;
        if (paramAnnotated != JsonSerialize.Typing.DEFAULT_TYPING) {
          if (paramAnnotated != JsonSerialize.Typing.STATIC) {
            break label165;
          }
        }
      }
    }
    label165:
    for (boolean bool = true;; bool = false)
    {
      if (!bool) {
        break label171;
      }
      return ((JavaType)localObject).withStaticTyping();
      if (paramJavaType.isAssignableFrom((Class)localObject)) {
        break;
      }
      throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + paramAnnotated.getName() + "': class " + ((Class)localObject).getName() + " not a super-type of (declared) class " + paramJavaType.getName());
    }
    label171:
    return null;
  }
  
  public Annotations getClassAnnotations()
  {
    return this._beanDesc.getClassAnnotations();
  }
  
  protected Object getDefaultBean()
  {
    Object localObject2 = this._defaultBean;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = NO_DEFAULT_MARKER;
      }
      this._defaultBean = localObject1;
    }
    if (localObject1 == NO_DEFAULT_MARKER) {
      return null;
    }
    return this._defaultBean;
  }
  
  protected Object getDefaultValue(JavaType paramJavaType)
  {
    Class localClass1 = paramJavaType.getRawClass();
    Class localClass2 = ClassUtil.primitiveType(localClass1);
    if (localClass2 != null) {
      return ClassUtil.defaultValue(localClass2);
    }
    if ((paramJavaType.isContainerType()) || (paramJavaType.isReferenceType())) {
      return JsonInclude.Include.NON_EMPTY;
    }
    if (localClass1 == String.class) {
      return "";
    }
    return null;
  }
  
  protected Object getPropertyDefaultValue(String paramString, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    Object localObject = getDefaultBean();
    if (localObject == null) {
      return getDefaultValue(paramJavaType);
    }
    try
    {
      paramAnnotatedMember = paramAnnotatedMember.getValue(localObject);
      return paramAnnotatedMember;
    }
    catch (Exception paramAnnotatedMember) {}
    return _throwWrapped(paramAnnotatedMember, paramString, localObject);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\PropertyBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */