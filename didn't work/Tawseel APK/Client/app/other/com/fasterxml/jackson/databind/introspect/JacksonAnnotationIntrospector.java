package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter.None;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.beans.ConstructorProperties;
import java.beans.Transient;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class JacksonAnnotationIntrospector
  extends AnnotationIntrospector
  implements Serializable
{
  private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_DESER;
  private static final Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_SER = (Class[])new Class[] { JsonSerialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonRawValue.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class };
  private static final Java7Support _jdk7Helper;
  private static final long serialVersionUID = 1L;
  protected transient LRUMap<Class<?>, Boolean> _annotationsInside = new LRUMap(48, 48);
  
  static
  {
    ANNOTATIONS_TO_INFER_DESER = (Class[])new Class[] { JsonDeserialize.class, JsonView.class, JsonFormat.class, JsonTypeInfo.class, JsonUnwrapped.class, JsonBackReference.class, JsonManagedReference.class };
    Object localObject = null;
    try
    {
      Java7Support localJava7Support = (Java7Support)Java7Support.class.newInstance();
      localObject = localJava7Support;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Logger.getLogger(JacksonAnnotationIntrospector.class.getName()).warning("Unable to load JDK7 annotation types; will have to skip");
      }
    }
    _jdk7Helper = (Java7Support)localObject;
  }
  
  private final Boolean _findSortAlpha(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonPropertyOrder)_findAnnotation(paramAnnotated, JsonPropertyOrder.class);
    if ((paramAnnotated != null) && (paramAnnotated.alphabetic())) {
      return Boolean.TRUE;
    }
    return null;
  }
  
  protected Class<?> _classIfExplicit(Class<?> paramClass)
  {
    Class<?> localClass;
    if (paramClass != null)
    {
      localClass = paramClass;
      if (!ClassUtil.isBogusClass(paramClass)) {}
    }
    else
    {
      localClass = null;
    }
    return localClass;
  }
  
  protected Class<?> _classIfExplicit(Class<?> paramClass1, Class<?> paramClass2)
  {
    Class localClass = _classIfExplicit(paramClass1);
    if (localClass != null)
    {
      paramClass1 = localClass;
      if (localClass != paramClass2) {}
    }
    else
    {
      paramClass1 = null;
    }
    return paramClass1;
  }
  
  protected StdTypeResolverBuilder _constructNoTypeResolverBuilder()
  {
    return StdTypeResolverBuilder.noTypeInfoBuilder();
  }
  
  protected StdTypeResolverBuilder _constructStdTypeResolverBuilder()
  {
    return new StdTypeResolverBuilder();
  }
  
  protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Attr paramAttr, MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    if (paramAttr.required()) {}
    for (PropertyMetadata localPropertyMetadata = PropertyMetadata.STD_REQUIRED;; localPropertyMetadata = PropertyMetadata.STD_OPTIONAL)
    {
      String str = paramAttr.value();
      PropertyName localPropertyName2 = _propertyName(paramAttr.propName(), paramAttr.propNamespace());
      PropertyName localPropertyName1 = localPropertyName2;
      if (!localPropertyName2.hasSimpleName()) {
        localPropertyName1 = PropertyName.construct(str);
      }
      return AttributePropertyWriter.construct(str, SimpleBeanPropertyDefinition.construct(paramMapperConfig, new VirtualAnnotatedMember(paramAnnotatedClass, paramAnnotatedClass.getRawType(), str, paramJavaType.getRawClass()), localPropertyName1, localPropertyMetadata, paramAttr.include()), paramAnnotatedClass.getAnnotations(), paramJavaType);
    }
  }
  
  protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Prop paramProp, MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass)
  {
    Object localObject1;
    Object localObject2;
    JavaType localJavaType;
    Class localClass;
    if (paramProp.required())
    {
      localObject1 = PropertyMetadata.STD_REQUIRED;
      localObject2 = _propertyName(paramProp.name(), paramProp.namespace());
      localJavaType = paramMapperConfig.constructType(paramProp.type());
      localObject2 = SimpleBeanPropertyDefinition.construct(paramMapperConfig, new VirtualAnnotatedMember(paramAnnotatedClass, paramAnnotatedClass.getRawType(), ((PropertyName)localObject2).getSimpleName(), localJavaType.getRawClass()), (PropertyName)localObject2, (PropertyMetadata)localObject1, paramProp.include());
      localClass = paramProp.value();
      paramProp = paramMapperConfig.getHandlerInstantiator();
      if (paramProp != null) {
        break label142;
      }
    }
    label142:
    for (paramProp = null;; paramProp = paramProp.virtualPropertyWriterInstance(paramMapperConfig, localClass))
    {
      localObject1 = paramProp;
      if (paramProp == null) {
        localObject1 = (VirtualBeanPropertyWriter)ClassUtil.createInstance(localClass, paramMapperConfig.canOverrideAccessModifiers());
      }
      return ((VirtualBeanPropertyWriter)localObject1).withConfig(paramMapperConfig, paramAnnotatedClass, (BeanPropertyDefinition)localObject2, localJavaType);
      localObject1 = PropertyMetadata.STD_OPTIONAL;
      break;
    }
  }
  
  protected PropertyName _findConstructorName(Annotated paramAnnotated)
  {
    if ((paramAnnotated instanceof AnnotatedParameter))
    {
      paramAnnotated = (AnnotatedParameter)paramAnnotated;
      if ((paramAnnotated.getOwner() != null) && (_jdk7Helper != null))
      {
        paramAnnotated = _jdk7Helper.findConstructorName(paramAnnotated);
        if (paramAnnotated != null) {
          return paramAnnotated;
        }
      }
    }
    return null;
  }
  
  protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> paramMapperConfig, Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonTypeInfo localJsonTypeInfo = (JsonTypeInfo)_findAnnotation(paramAnnotated, JsonTypeInfo.class);
    Object localObject = (JsonTypeResolver)_findAnnotation(paramAnnotated, JsonTypeResolver.class);
    JsonTypeIdResolver localJsonTypeIdResolver;
    if (localObject != null)
    {
      if (localJsonTypeInfo == null) {
        return null;
      }
      localObject = paramMapperConfig.typeResolverBuilderInstance(paramAnnotated, ((JsonTypeResolver)localObject).value());
      localJsonTypeIdResolver = (JsonTypeIdResolver)_findAnnotation(paramAnnotated, JsonTypeIdResolver.class);
      if (localJsonTypeIdResolver != null) {
        break label232;
      }
    }
    label232:
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.typeIdResolverInstance(paramAnnotated, localJsonTypeIdResolver.value()))
    {
      if (paramMapperConfig != null) {
        paramMapperConfig.init(paramJavaType);
      }
      localObject = ((TypeResolverBuilder)localObject).init(localJsonTypeInfo.use(), paramMapperConfig);
      paramJavaType = localJsonTypeInfo.include();
      paramMapperConfig = paramJavaType;
      if (paramJavaType == JsonTypeInfo.As.EXTERNAL_PROPERTY)
      {
        paramMapperConfig = paramJavaType;
        if ((paramAnnotated instanceof AnnotatedClass)) {
          paramMapperConfig = JsonTypeInfo.As.PROPERTY;
        }
      }
      paramAnnotated = ((TypeResolverBuilder)localObject).inclusion(paramMapperConfig).typeProperty(localJsonTypeInfo.property());
      paramJavaType = localJsonTypeInfo.defaultImpl();
      paramMapperConfig = paramAnnotated;
      if (paramJavaType != JsonTypeInfo.None.class)
      {
        paramMapperConfig = paramAnnotated;
        if (!paramJavaType.isAnnotation()) {
          paramMapperConfig = paramAnnotated.defaultImpl(paramJavaType);
        }
      }
      return paramMapperConfig.typeIdVisibility(localJsonTypeInfo.visible());
      if (localJsonTypeInfo == null) {
        return null;
      }
      if (localJsonTypeInfo.use() == JsonTypeInfo.Id.NONE) {
        return _constructNoTypeResolverBuilder();
      }
      localObject = _constructStdTypeResolverBuilder();
      break;
    }
  }
  
  protected boolean _isIgnorable(Annotated paramAnnotated)
  {
    JsonIgnore localJsonIgnore = (JsonIgnore)_findAnnotation(paramAnnotated, JsonIgnore.class);
    if (localJsonIgnore != null) {
      return localJsonIgnore.value();
    }
    if (_jdk7Helper != null)
    {
      paramAnnotated = _jdk7Helper.findTransient(paramAnnotated);
      if (paramAnnotated != null) {
        return paramAnnotated.booleanValue();
      }
    }
    return false;
  }
  
  protected PropertyName _propertyName(String paramString1, String paramString2)
  {
    if (paramString1.isEmpty()) {
      return PropertyName.USE_DEFAULT;
    }
    if ((paramString2 == null) || (paramString2.isEmpty())) {
      return PropertyName.construct(paramString1);
    }
    return PropertyName.construct(paramString1, paramString2);
  }
  
  public void findAndAddVirtualProperties(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, List<BeanPropertyWriter> paramList)
  {
    JsonAppend localJsonAppend = (JsonAppend)_findAnnotation(paramAnnotatedClass, JsonAppend.class);
    if (localJsonAppend == null) {
      return;
    }
    boolean bool = localJsonAppend.prepend();
    Object localObject2 = null;
    JsonAppend.Attr[] arrayOfAttr = localJsonAppend.attrs();
    int i = 0;
    int j = arrayOfAttr.length;
    if (i < j)
    {
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = paramMapperConfig.constructType(Object.class);
      }
      localObject2 = _constructVirtualProperty(arrayOfAttr[i], paramMapperConfig, paramAnnotatedClass, (JavaType)localObject1);
      if (bool) {
        paramList.add(i, localObject2);
      }
      for (;;)
      {
        i += 1;
        localObject2 = localObject1;
        break;
        paramList.add(localObject2);
      }
    }
    Object localObject1 = localJsonAppend.props();
    i = 0;
    j = localObject1.length;
    label145:
    if (i < j)
    {
      localObject2 = _constructVirtualProperty(localObject1[i], paramMapperConfig, paramAnnotatedClass);
      if (!bool) {
        break label189;
      }
      paramList.add(i, localObject2);
    }
    for (;;)
    {
      i += 1;
      break label145;
      break;
      label189:
      paramList.add(localObject2);
    }
  }
  
  public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker<?> paramVisibilityChecker)
  {
    paramAnnotatedClass = (JsonAutoDetect)_findAnnotation(paramAnnotatedClass, JsonAutoDetect.class);
    if (paramAnnotatedClass == null) {
      return paramVisibilityChecker;
    }
    return paramVisibilityChecker.with(paramAnnotatedClass);
  }
  
  public String findClassDescription(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonClassDescription)_findAnnotation(paramAnnotatedClass, JsonClassDescription.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return paramAnnotatedClass.value();
  }
  
  public Object findContentDeserializer(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.contentUsing();
      if (paramAnnotated != JsonDeserializer.None.class) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public Object findContentSerializer(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.contentUsing();
      if (paramAnnotated != JsonSerializer.None.class) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public JsonCreator.Mode findCreatorBinding(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonCreator)_findAnnotation(paramAnnotated, JsonCreator.class);
    if (paramAnnotated == null) {
      return null;
    }
    return paramAnnotated.mode();
  }
  
  public Object findDeserializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    paramAnnotatedMember = (JsonDeserialize)_findAnnotation(paramAnnotatedMember, JsonDeserialize.class);
    if (paramAnnotatedMember == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotatedMember.contentConverter(), Converter.None.class);
  }
  
  @Deprecated
  public Class<?> findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.contentAs());
  }
  
  public Object findDeserializationConverter(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.converter(), Converter.None.class);
  }
  
  @Deprecated
  public Class<?> findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.keyAs());
  }
  
  @Deprecated
  public Class<?> findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.as());
  }
  
  public Object findDeserializer(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.using();
      if (paramAnnotated != JsonDeserializer.None.class) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public String findEnumValue(Enum<?> paramEnum)
  {
    try
    {
      Object localObject = paramEnum.getClass().getField(paramEnum.name());
      if (localObject != null)
      {
        localObject = (JsonProperty)((Field)localObject).getAnnotation(JsonProperty.class);
        if (localObject != null)
        {
          localObject = ((JsonProperty)localObject).value();
          if (localObject != null)
          {
            boolean bool = ((String)localObject).isEmpty();
            if (!bool) {
              return (String)localObject;
            }
          }
        }
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      return paramEnum.name();
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
  }
  
  public String[] findEnumValues(Class<?> paramClass, Enum<?>[] paramArrayOfEnum, String[] paramArrayOfString)
  {
    Object localObject1 = null;
    Field[] arrayOfField = ClassUtil.getDeclaredFields(paramClass);
    int j = arrayOfField.length;
    int i = 0;
    paramClass = (Class<?>)localObject1;
    if (i < j)
    {
      Field localField = arrayOfField[i];
      if (!localField.isEnumConstant()) {
        localObject1 = paramClass;
      }
      for (;;)
      {
        i += 1;
        paramClass = (Class<?>)localObject1;
        break;
        Object localObject2 = (JsonProperty)localField.getAnnotation(JsonProperty.class);
        localObject1 = paramClass;
        if (localObject2 != null)
        {
          localObject2 = ((JsonProperty)localObject2).value();
          localObject1 = paramClass;
          if (!((String)localObject2).isEmpty())
          {
            localObject1 = paramClass;
            if (paramClass == null) {
              localObject1 = new HashMap();
            }
            ((HashMap)localObject1).put(localField.getName(), localObject2);
          }
        }
      }
    }
    if (paramClass != null)
    {
      i = 0;
      j = paramArrayOfEnum.length;
      while (i < j)
      {
        localObject1 = (String)paramClass.get(paramArrayOfEnum[i].name());
        if (localObject1 != null) {
          paramArrayOfString[i] = localObject1;
        }
        i += 1;
      }
    }
    return paramArrayOfString;
  }
  
  public Object findFilterId(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonFilter)_findAnnotation(paramAnnotated, JsonFilter.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.value();
      if (paramAnnotated.length() > 0) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public JsonFormat.Value findFormat(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonFormat)_findAnnotation(paramAnnotated, JsonFormat.class);
    if (paramAnnotated == null) {
      return null;
    }
    return new JsonFormat.Value(paramAnnotated);
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonIgnoreProperties)_findAnnotation(paramAnnotatedClass, JsonIgnoreProperties.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return Boolean.valueOf(paramAnnotatedClass.ignoreUnknown());
  }
  
  public String findImplicitPropertyName(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject = (JacksonInject)_findAnnotation(paramAnnotatedMember, JacksonInject.class);
    if (localObject == null) {
      localObject = null;
    }
    String str;
    do
    {
      return localObject;
      str = ((JacksonInject)localObject).value();
      localObject = str;
    } while (str.length() != 0);
    if (!(paramAnnotatedMember instanceof AnnotatedMethod)) {
      return paramAnnotatedMember.getRawType().getName();
    }
    localObject = (AnnotatedMethod)paramAnnotatedMember;
    if (((AnnotatedMethod)localObject).getParameterCount() == 0) {
      return paramAnnotatedMember.getRawType().getName();
    }
    return ((AnnotatedMethod)localObject).getRawParameterType(0).getName();
  }
  
  public Object findKeyDeserializer(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.keyUsing();
      if (paramAnnotated != KeyDeserializer.None.class) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public Object findKeySerializer(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.keyUsing();
      if (paramAnnotated != JsonSerializer.None.class) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public PropertyName findNameForDeserialization(Annotated paramAnnotated)
  {
    Object localObject = (JsonSetter)_findAnnotation(paramAnnotated, JsonSetter.class);
    if (localObject != null) {
      localObject = PropertyName.construct(((JsonSetter)localObject).value());
    }
    PropertyName localPropertyName;
    do
    {
      return (PropertyName)localObject;
      localObject = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
      if (localObject != null) {
        return PropertyName.construct(((JsonProperty)localObject).value());
      }
      localPropertyName = _findConstructorName(paramAnnotated);
      localObject = localPropertyName;
    } while (localPropertyName != null);
    if (_hasOneOf(paramAnnotated, ANNOTATIONS_TO_INFER_DESER)) {
      return PropertyName.USE_DEFAULT;
    }
    return null;
  }
  
  public PropertyName findNameForSerialization(Annotated paramAnnotated)
  {
    Object localObject = (JsonGetter)_findAnnotation(paramAnnotated, JsonGetter.class);
    if (localObject != null) {
      localObject = PropertyName.construct(((JsonGetter)localObject).value());
    }
    PropertyName localPropertyName;
    do
    {
      return (PropertyName)localObject;
      localObject = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
      if (localObject != null) {
        return PropertyName.construct(((JsonProperty)localObject).value());
      }
      localPropertyName = _findConstructorName(paramAnnotated);
      localObject = localPropertyName;
    } while (localPropertyName != null);
    if (_hasOneOf(paramAnnotated, ANNOTATIONS_TO_INFER_SER)) {
      return PropertyName.USE_DEFAULT;
    }
    return null;
  }
  
  public Object findNamingStrategy(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonNaming)_findAnnotation(paramAnnotatedClass, JsonNaming.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return paramAnnotatedClass.value();
  }
  
  public Object findNullSerializer(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.nullsUsing();
      if (paramAnnotated != JsonSerializer.None.class) {
        return paramAnnotated;
      }
    }
    return null;
  }
  
  public ObjectIdInfo findObjectIdInfo(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonIdentityInfo)_findAnnotation(paramAnnotated, JsonIdentityInfo.class);
    if ((paramAnnotated == null) || (paramAnnotated.generator() == ObjectIdGenerators.None.class)) {
      return null;
    }
    return new ObjectIdInfo(PropertyName.construct(paramAnnotated.property()), paramAnnotated.scope(), paramAnnotated.generator(), paramAnnotated.resolver());
  }
  
  public ObjectIdInfo findObjectReferenceInfo(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    JsonIdentityReference localJsonIdentityReference = (JsonIdentityReference)_findAnnotation(paramAnnotated, JsonIdentityReference.class);
    paramAnnotated = paramObjectIdInfo;
    if (localJsonIdentityReference != null) {
      paramAnnotated = paramObjectIdInfo.withAlwaysAsId(localJsonIdentityReference.alwaysAsId());
    }
    return paramAnnotated;
  }
  
  public Class<?> findPOJOBuilder(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonDeserialize)_findAnnotation(paramAnnotatedClass, JsonDeserialize.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotatedClass.builder());
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonPOJOBuilder)_findAnnotation(paramAnnotatedClass, JsonPOJOBuilder.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return new JsonPOJOBuilder.Value(paramAnnotatedClass);
  }
  
  @Deprecated
  public String[] findPropertiesToIgnore(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonIgnoreProperties)_findAnnotation(paramAnnotated, JsonIgnoreProperties.class);
    if (paramAnnotated == null) {
      return null;
    }
    return paramAnnotated.value();
  }
  
  public String[] findPropertiesToIgnore(Annotated paramAnnotated, boolean paramBoolean)
  {
    paramAnnotated = (JsonIgnoreProperties)_findAnnotation(paramAnnotated, JsonIgnoreProperties.class);
    if (paramAnnotated == null) {}
    do
    {
      return null;
      if (!paramBoolean) {
        break;
      }
    } while (paramAnnotated.allowGetters());
    while (!paramAnnotated.allowSetters()) {
      return paramAnnotated.value();
    }
    return null;
  }
  
  public JsonProperty.Access findPropertyAccess(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
    if (paramAnnotated != null) {
      return paramAnnotated.access();
    }
    return null;
  }
  
  public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    if (paramJavaType.getContentType() == null) {
      throw new IllegalArgumentException("Must call method with a container or reference type (got " + paramJavaType + ")");
    }
    return _findTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public String findPropertyDefaultValue(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
    if (paramAnnotated == null) {
      return null;
    }
    String str = paramAnnotated.defaultValue();
    paramAnnotated = str;
    if (str.isEmpty()) {
      paramAnnotated = null;
    }
    return paramAnnotated;
  }
  
  public String findPropertyDescription(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonPropertyDescription)_findAnnotation(paramAnnotated, JsonPropertyDescription.class);
    if (paramAnnotated == null) {
      return null;
    }
    return paramAnnotated.value();
  }
  
  public JsonInclude.Value findPropertyInclusion(Annotated paramAnnotated)
  {
    JsonInclude localJsonInclude = (JsonInclude)_findAnnotation(paramAnnotated, JsonInclude.class);
    JsonInclude.Include localInclude2;
    JsonInclude.Include localInclude1;
    if (localJsonInclude == null)
    {
      localInclude2 = JsonInclude.Include.USE_DEFAULTS;
      localInclude1 = localInclude2;
      if (localInclude2 == JsonInclude.Include.USE_DEFAULTS)
      {
        paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
        localInclude1 = localInclude2;
        if (paramAnnotated != null) {
          paramAnnotated = paramAnnotated.include();
        }
      }
      switch (paramAnnotated)
      {
      default: 
        localInclude1 = localInclude2;
        label94:
        if (localJsonInclude != null) {
          break;
        }
      }
    }
    for (paramAnnotated = JsonInclude.Include.USE_DEFAULTS;; paramAnnotated = localJsonInclude.content())
    {
      return JsonInclude.Value.construct(localInclude1, paramAnnotated);
      localInclude2 = localJsonInclude.value();
      break;
      localInclude1 = JsonInclude.Include.ALWAYS;
      break label94;
      localInclude1 = JsonInclude.Include.NON_NULL;
      break label94;
      localInclude1 = JsonInclude.Include.NON_DEFAULT;
      break label94;
      localInclude1 = JsonInclude.Include.NON_EMPTY;
      break label94;
    }
  }
  
  public Integer findPropertyIndex(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
    if (paramAnnotated != null)
    {
      int i = paramAnnotated.index();
      if (i != -1) {
        return Integer.valueOf(i);
      }
    }
    return null;
  }
  
  public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    if (paramJavaType.isContainerType()) {
      return null;
    }
    return _findTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember)
  {
    JsonManagedReference localJsonManagedReference = (JsonManagedReference)_findAnnotation(paramAnnotatedMember, JsonManagedReference.class);
    if (localJsonManagedReference != null) {
      return AnnotationIntrospector.ReferenceProperty.managed(localJsonManagedReference.value());
    }
    paramAnnotatedMember = (JsonBackReference)_findAnnotation(paramAnnotatedMember, JsonBackReference.class);
    if (paramAnnotatedMember != null) {
      return AnnotationIntrospector.ReferenceProperty.back(paramAnnotatedMember.value());
    }
    return null;
  }
  
  public PropertyName findRootName(AnnotatedClass paramAnnotatedClass)
  {
    JsonRootName localJsonRootName = (JsonRootName)_findAnnotation(paramAnnotatedClass, JsonRootName.class);
    if (localJsonRootName == null) {
      return null;
    }
    String str = localJsonRootName.namespace();
    paramAnnotatedClass = str;
    if (str != null)
    {
      paramAnnotatedClass = str;
      if (str.length() == 0) {
        paramAnnotatedClass = null;
      }
    }
    return PropertyName.construct(localJsonRootName.value(), paramAnnotatedClass);
  }
  
  public Object findSerializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    paramAnnotatedMember = (JsonSerialize)_findAnnotation(paramAnnotatedMember, JsonSerialize.class);
    if (paramAnnotatedMember == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotatedMember.contentConverter(), Converter.None.class);
  }
  
  @Deprecated
  public Class<?> findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.contentAs());
  }
  
  public Object findSerializationConverter(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.converter(), Converter.None.class);
  }
  
  public JsonInclude.Include findSerializationInclusion(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    Object localObject = (JsonInclude)_findAnnotation(paramAnnotated, JsonInclude.class);
    if (localObject != null)
    {
      localObject = ((JsonInclude)localObject).value();
      if (localObject != JsonInclude.Include.USE_DEFAULTS) {
        return (JsonInclude.Include)localObject;
      }
    }
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated != null) {
      paramAnnotated = paramAnnotated.include();
    }
    switch (paramAnnotated)
    {
    default: 
      return paramInclude;
    case ???: 
      return JsonInclude.Include.ALWAYS;
    case ???: 
      return JsonInclude.Include.NON_NULL;
    case ???: 
      return JsonInclude.Include.NON_DEFAULT;
    }
    return JsonInclude.Include.NON_EMPTY;
  }
  
  @Deprecated
  public JsonInclude.Include findSerializationInclusionForContent(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    paramAnnotated = (JsonInclude)_findAnnotation(paramAnnotated, JsonInclude.class);
    if (paramAnnotated != null)
    {
      paramAnnotated = paramAnnotated.content();
      if (paramAnnotated != JsonInclude.Include.USE_DEFAULTS) {
        return paramAnnotated;
      }
    }
    return paramInclude;
  }
  
  @Deprecated
  public Class<?> findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.keyAs());
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonPropertyOrder)_findAnnotation(paramAnnotatedClass, JsonPropertyOrder.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return paramAnnotatedClass.value();
  }
  
  public Boolean findSerializationSortAlphabetically(Annotated paramAnnotated)
  {
    return _findSortAlpha(paramAnnotated);
  }
  
  @Deprecated
  public Class<?> findSerializationType(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return _classIfExplicit(paramAnnotated.as());
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (paramAnnotated == null) {
      return null;
    }
    return paramAnnotated.typing();
  }
  
  public Object findSerializer(Annotated paramAnnotated)
  {
    Object localObject = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (localObject != null)
    {
      localObject = ((JsonSerialize)localObject).using();
      if (localObject != JsonSerializer.None.class) {
        return localObject;
      }
    }
    localObject = (JsonRawValue)_findAnnotation(paramAnnotated, JsonRawValue.class);
    if ((localObject != null) && (((JsonRawValue)localObject).value())) {
      return new RawSerializer(paramAnnotated.getRawType());
    }
    return null;
  }
  
  public List<NamedType> findSubtypes(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonSubTypes)_findAnnotation(paramAnnotated, JsonSubTypes.class);
    if (paramAnnotated == null)
    {
      paramAnnotated = null;
      return paramAnnotated;
    }
    JsonSubTypes.Type[] arrayOfType = paramAnnotated.value();
    ArrayList localArrayList = new ArrayList(arrayOfType.length);
    int j = arrayOfType.length;
    int i = 0;
    for (;;)
    {
      paramAnnotated = localArrayList;
      if (i >= j) {
        break;
      }
      paramAnnotated = arrayOfType[i];
      localArrayList.add(new NamedType(paramAnnotated.value(), paramAnnotated.name()));
      i += 1;
    }
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonTypeName)_findAnnotation(paramAnnotatedClass, JsonTypeName.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return paramAnnotatedClass.value();
  }
  
  public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    return _findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
  }
  
  public NameTransformer findUnwrappingNameTransformer(AnnotatedMember paramAnnotatedMember)
  {
    paramAnnotatedMember = (JsonUnwrapped)_findAnnotation(paramAnnotatedMember, JsonUnwrapped.class);
    if ((paramAnnotatedMember == null) || (!paramAnnotatedMember.enabled())) {
      return null;
    }
    return NameTransformer.simpleTransformer(paramAnnotatedMember.prefix(), paramAnnotatedMember.suffix());
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonValueInstantiator)_findAnnotation(paramAnnotatedClass, JsonValueInstantiator.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return paramAnnotatedClass.value();
  }
  
  public Class<?>[] findViews(Annotated paramAnnotated)
  {
    paramAnnotated = (JsonView)_findAnnotation(paramAnnotated, JsonView.class);
    if (paramAnnotated == null) {
      return null;
    }
    return paramAnnotated.value();
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return _hasAnnotation(paramAnnotatedMethod, JsonAnyGetter.class);
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return _hasAnnotation(paramAnnotatedMethod, JsonAnySetter.class);
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    paramAnnotatedMethod = (JsonValue)_findAnnotation(paramAnnotatedMethod, JsonValue.class);
    return (paramAnnotatedMethod != null) && (paramAnnotatedMethod.value());
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated)
  {
    boolean bool2 = false;
    JsonCreator localJsonCreator = (JsonCreator)_findAnnotation(paramAnnotated, JsonCreator.class);
    boolean bool1;
    if (localJsonCreator != null)
    {
      bool1 = bool2;
      if (localJsonCreator.mode() != JsonCreator.Mode.DISABLED) {
        bool1 = true;
      }
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramAnnotated instanceof AnnotatedConstructor));
        bool1 = bool2;
      } while (_jdk7Helper == null);
      paramAnnotated = _jdk7Helper.hasCreatorAnnotation(paramAnnotated);
      bool1 = bool2;
    } while (paramAnnotated == null);
    return paramAnnotated.booleanValue();
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember)
  {
    return _isIgnorable(paramAnnotatedMember);
  }
  
  public Boolean hasRequiredMarker(AnnotatedMember paramAnnotatedMember)
  {
    paramAnnotatedMember = (JsonProperty)_findAnnotation(paramAnnotatedMember, JsonProperty.class);
    if (paramAnnotatedMember != null) {
      return Boolean.valueOf(paramAnnotatedMember.required());
    }
    return null;
  }
  
  public boolean isAnnotationBundle(Annotation paramAnnotation)
  {
    Class localClass = paramAnnotation.annotationType();
    Boolean localBoolean = (Boolean)this._annotationsInside.get(localClass);
    paramAnnotation = localBoolean;
    if (localBoolean == null) {
      if (localClass.getAnnotation(JacksonAnnotationsInside.class) == null) {
        break label61;
      }
    }
    label61:
    for (boolean bool = true;; bool = false)
    {
      paramAnnotation = Boolean.valueOf(bool);
      this._annotationsInside.putIfAbsent(localClass, paramAnnotation);
      return paramAnnotation.booleanValue();
    }
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass)
  {
    paramAnnotatedClass = (JsonIgnoreType)_findAnnotation(paramAnnotatedClass, JsonIgnoreType.class);
    if (paramAnnotatedClass == null) {
      return null;
    }
    return Boolean.valueOf(paramAnnotatedClass.value());
  }
  
  public Boolean isTypeId(AnnotatedMember paramAnnotatedMember)
  {
    return Boolean.valueOf(_hasAnnotation(paramAnnotatedMember, JsonTypeId.class));
  }
  
  protected Object readResolve()
  {
    if (this._annotationsInside == null) {
      this._annotationsInside = new LRUMap(48, 48);
    }
    return this;
  }
  
  public AnnotatedMethod resolveSetterConflict(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod1, AnnotatedMethod paramAnnotatedMethod2)
  {
    paramMapperConfig = paramAnnotatedMethod1.getRawParameterType(0);
    Class localClass = paramAnnotatedMethod2.getRawParameterType(0);
    if (paramMapperConfig.isPrimitive()) {
      if (localClass.isPrimitive()) {
        break label40;
      }
    }
    label40:
    do
    {
      return paramAnnotatedMethod1;
      if (localClass.isPrimitive()) {
        return paramAnnotatedMethod2;
      }
      if (paramMapperConfig != String.class) {
        break;
      }
    } while (localClass != String.class);
    while (localClass != String.class) {
      return null;
    }
    return paramAnnotatedMethod2;
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  private static class Java7Support
  {
    private final Class<?> _bogus = ConstructorProperties.class;
    
    public PropertyName findConstructorName(AnnotatedParameter paramAnnotatedParameter)
    {
      Object localObject = paramAnnotatedParameter.getOwner();
      if (localObject != null)
      {
        localObject = (ConstructorProperties)((AnnotatedWithParams)localObject).getAnnotation(ConstructorProperties.class);
        if (localObject != null)
        {
          localObject = ((ConstructorProperties)localObject).value();
          int i = paramAnnotatedParameter.getIndex();
          if (i < localObject.length) {
            return PropertyName.construct(localObject[i]);
          }
        }
      }
      return null;
    }
    
    public Boolean findTransient(Annotated paramAnnotated)
    {
      paramAnnotated = (Transient)paramAnnotated.getAnnotation(Transient.class);
      if (paramAnnotated != null) {
        return Boolean.valueOf(paramAnnotated.value());
      }
      return null;
    }
    
    public Boolean hasCreatorAnnotation(Annotated paramAnnotated)
    {
      if ((ConstructorProperties)paramAnnotated.getAnnotation(ConstructorProperties.class) != null) {
        return Boolean.TRUE;
      }
      return null;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\JacksonAnnotationIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */