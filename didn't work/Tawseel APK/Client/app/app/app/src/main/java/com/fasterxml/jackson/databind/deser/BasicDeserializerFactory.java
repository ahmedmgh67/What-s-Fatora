package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory
  extends DeserializerFactory
  implements Serializable
{
  private static final Class<?> CLASS_CHAR_BUFFER;
  private static final Class<?> CLASS_ITERABLE;
  private static final Class<?> CLASS_MAP_ENTRY;
  private static final Class<?> CLASS_OBJECT = Object.class;
  private static final Class<?> CLASS_STRING = String.class;
  protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME;
  static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
  static final HashMap<String, Class<? extends Map>> _mapFallbacks;
  protected final DeserializerFactoryConfig _factoryConfig;
  
  static
  {
    CLASS_CHAR_BUFFER = CharSequence.class;
    CLASS_ITERABLE = Iterable.class;
    CLASS_MAP_ENTRY = Map.Entry.class;
    UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    _mapFallbacks = new HashMap();
    _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
    _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
    _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
    _mapFallbacks.put(NavigableMap.class.getName(), TreeMap.class);
    _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
    _collectionFallbacks = new HashMap();
    _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
    _collectionFallbacks.put(List.class.getName(), ArrayList.class);
    _collectionFallbacks.put(Set.class.getName(), HashSet.class);
    _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
    _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
    _collectionFallbacks.put("java.util.Deque", LinkedList.class);
    _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
  }
  
  protected BasicDeserializerFactory(DeserializerFactoryConfig paramDeserializerFactoryConfig)
  {
    this._factoryConfig = paramDeserializerFactoryConfig;
  }
  
  private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Class localClass = paramJavaType.getRawClass();
    Object localObject1 = localDeserializationConfig.introspect(paramJavaType);
    Object localObject2 = findKeyDeserializerFromAnnotation(paramDeserializationContext, ((BeanDescription)localObject1).getClassInfo());
    if (localObject2 != null) {
      return (KeyDeserializer)localObject2;
    }
    localObject2 = _findCustomEnumDeserializer(localClass, localDeserializationConfig, (BeanDescription)localObject1);
    if (localObject2 != null) {
      return StdKeyDeserializers.constructDelegatingKeyDeserializer(localDeserializationConfig, paramJavaType, (JsonDeserializer)localObject2);
    }
    localObject2 = findDeserializerFromAnnotation(paramDeserializationContext, ((BeanDescription)localObject1).getClassInfo());
    if (localObject2 != null) {
      return StdKeyDeserializers.constructDelegatingKeyDeserializer(localDeserializationConfig, paramJavaType, (JsonDeserializer)localObject2);
    }
    paramJavaType = constructEnumResolver(localClass, localDeserializationConfig, ((BeanDescription)localObject1).findJsonValueMethod());
    localObject2 = localDeserializationConfig.getAnnotationIntrospector();
    localObject1 = ((BeanDescription)localObject1).getFactoryMethods().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)((Iterator)localObject1).next();
      if (((AnnotationIntrospector)localObject2).hasCreatorAnnotation(localAnnotatedMethod))
      {
        if ((localAnnotatedMethod.getParameterCount() == 1) && (localAnnotatedMethod.getRawReturnType().isAssignableFrom(localClass)))
        {
          if (localAnnotatedMethod.getRawParameterType(0) != String.class) {
            throw new IllegalArgumentException("Parameter #0 type for factory method (" + localAnnotatedMethod + ") not suitable, must be java.lang.String");
          }
          if (localDeserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(localAnnotatedMethod.getMember(), paramDeserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
          }
          return StdKeyDeserializers.constructEnumKeyDeserializer(paramJavaType, localAnnotatedMethod);
        }
        throw new IllegalArgumentException("Unsuitable method (" + localAnnotatedMethod + ") decorated with @JsonCreator (for Enum type " + localClass.getName() + ")");
      }
    }
    return StdKeyDeserializers.constructEnumKeyDeserializer(paramJavaType);
  }
  
  private ValueInstantiator _findStdValueInstantiator(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (paramBeanDescription.getBeanClass() == JsonLocation.class) {
      return new JsonLocationInstantiator();
    }
    return null;
  }
  
  private JavaType _mapAbstractType2(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    if (this._factoryConfig.hasAbstractTypeResolvers())
    {
      Iterator localIterator = this._factoryConfig.abstractTypeResolvers().iterator();
      while (localIterator.hasNext())
      {
        JavaType localJavaType = ((AbstractTypeResolver)localIterator.next()).findTypeMapping(paramDeserializationConfig, paramJavaType);
        if ((localJavaType != null) && (localJavaType.getRawClass() != localClass)) {
          return localJavaType;
        }
      }
    }
    return null;
  }
  
  protected void _addDeserializerConstructors(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> paramMap)
    throws JsonMappingException
  {
    Object localObject1 = paramBeanDescription.findDefaultConstructor();
    if ((localObject1 != null) && ((!paramCreatorCollector.hasDefaultCreator()) || (paramAnnotationIntrospector.hasCreatorAnnotation((Annotated)localObject1)))) {
      paramCreatorCollector.setDefaultCreator((AnnotatedWithParams)localObject1);
    }
    localObject1 = null;
    Iterator localIterator = paramBeanDescription.getConstructors().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedConstructor localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
      boolean bool = paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedConstructor);
      BeanPropertyDefinition[] arrayOfBeanPropertyDefinition = (BeanPropertyDefinition[])paramMap.get(localAnnotatedConstructor);
      int i3 = localAnnotatedConstructor.getParameterCount();
      Object localObject2;
      label116:
      Object localObject3;
      if (i3 == 1)
      {
        if (arrayOfBeanPropertyDefinition == null)
        {
          localObject2 = null;
          if (!_checkIfCreatorPropertyBased(paramAnnotationIntrospector, localAnnotatedConstructor, (BeanPropertyDefinition)localObject2)) {
            break label201;
          }
          if (localObject2 != null) {
            break label191;
          }
        }
        label191:
        for (localObject2 = null;; localObject2 = ((BeanPropertyDefinition)localObject2).getFullName())
        {
          localObject3 = localAnnotatedConstructor.getParameter(0);
          paramCreatorCollector.addPropertyCreator(localAnnotatedConstructor, bool, new SettableBeanProperty[] { constructCreatorProperty(paramDeserializationContext, paramBeanDescription, (PropertyName)localObject2, 0, (AnnotatedParameter)localObject3, paramAnnotationIntrospector.findInjectableValueId((AnnotatedMember)localObject3)) });
          break;
          localObject2 = arrayOfBeanPropertyDefinition[0];
          break label116;
        }
        label201:
        _handleSingleArgumentConstructor(paramDeserializationContext, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localAnnotatedConstructor, bool, paramVisibilityChecker.isCreatorVisible(localAnnotatedConstructor));
        if (localObject2 != null) {
          ((POJOPropertyBuilder)localObject2).removeConstructors();
        }
      }
      else
      {
        localObject2 = null;
        SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[i3];
        int n = 0;
        int k = 0;
        int j = 0;
        int m = 0;
        label287:
        label304:
        int i;
        if (m < i3)
        {
          AnnotatedParameter localAnnotatedParameter = localAnnotatedConstructor.getParameter(m);
          Object localObject4;
          PropertyName localPropertyName;
          int i2;
          int i1;
          if (arrayOfBeanPropertyDefinition == null)
          {
            localObject3 = null;
            localObject4 = paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter);
            if (localObject3 != null) {
              break label389;
            }
            localPropertyName = null;
            if ((localObject3 == null) || (!((BeanPropertyDefinition)localObject3).isExplicitlyNamed())) {
              break label399;
            }
            i = n + 1;
            arrayOfSettableBeanProperty[m] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, m, localAnnotatedParameter, localObject4);
            localObject3 = localObject2;
            i2 = j;
            i1 = k;
          }
          for (;;)
          {
            m += 1;
            n = i;
            k = i1;
            j = i2;
            localObject2 = localObject3;
            break;
            localObject3 = arrayOfBeanPropertyDefinition[m];
            break label287;
            label389:
            localPropertyName = ((BeanPropertyDefinition)localObject3).getFullName();
            break label304;
            label399:
            if (localObject4 != null)
            {
              i2 = j + 1;
              arrayOfSettableBeanProperty[m] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, m, localAnnotatedParameter, localObject4);
              i = n;
              i1 = k;
              localObject3 = localObject2;
            }
            else if (paramAnnotationIntrospector.findUnwrappingNameTransformer(localAnnotatedParameter) != null)
            {
              arrayOfSettableBeanProperty[m] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, UNWRAPPED_CREATOR_PARAM_NAME, m, localAnnotatedParameter, null);
              i = n + 1;
              i1 = k;
              i2 = j;
              localObject3 = localObject2;
            }
            else if ((bool) && (localPropertyName != null) && (!localPropertyName.isEmpty()))
            {
              i1 = k + 1;
              arrayOfSettableBeanProperty[m] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, m, localAnnotatedParameter, localObject4);
              i = n;
              i2 = j;
              localObject3 = localObject2;
            }
            else
            {
              i = n;
              i1 = k;
              i2 = j;
              localObject3 = localObject2;
              if (localObject2 == null)
              {
                localObject3 = localAnnotatedParameter;
                i = n;
                i1 = k;
                i2 = j;
              }
            }
          }
        }
        if ((bool) || (n > 0) || (j > 0))
        {
          if (n + k + j == i3)
          {
            paramCreatorCollector.addPropertyCreator(localAnnotatedConstructor, bool, arrayOfSettableBeanProperty);
            continue;
          }
          if ((n == 0) && (j + 1 == i3))
          {
            paramCreatorCollector.addDelegatingCreator(localAnnotatedConstructor, bool, arrayOfSettableBeanProperty);
            continue;
          }
          localObject3 = _findImplicitParamName((AnnotatedParameter)localObject2, paramAnnotationIntrospector);
          if ((localObject3 == null) || (((PropertyName)localObject3).isEmpty()))
          {
            i = ((AnnotatedParameter)localObject2).getIndex();
            if ((i == 0) && (ClassUtil.isNonStaticInnerClass(localAnnotatedConstructor.getDeclaringClass()))) {
              throw new IllegalArgumentException("Non-static inner classes like " + localAnnotatedConstructor.getDeclaringClass().getName() + " can not use @JsonCreator for constructors");
            }
            throw new IllegalArgumentException("Argument #" + i + " of constructor " + localAnnotatedConstructor + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
          }
        }
        if (!paramCreatorCollector.hasDefaultCreator())
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new LinkedList();
          }
          ((List)localObject2).add(localAnnotatedConstructor);
          localObject1 = localObject2;
        }
      }
    }
    if ((localObject1 != null) && (!paramCreatorCollector.hasDelegatingCreator()) && (!paramCreatorCollector.hasPropertyBasedCreator())) {
      _checkImplicitlyNamedConstructors(paramDeserializationContext, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, (List)localObject1);
    }
  }
  
  protected void _addDeserializerFactoryMethods(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> paramMap)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Iterator localIterator = paramBeanDescription.getFactoryMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      boolean bool = paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedMethod);
      int i3 = localAnnotatedMethod.getParameterCount();
      if (i3 == 0)
      {
        if (bool) {
          paramCreatorCollector.setDefaultCreator(localAnnotatedMethod);
        }
      }
      else
      {
        BeanPropertyDefinition[] arrayOfBeanPropertyDefinition = (BeanPropertyDefinition[])paramMap.get(localAnnotatedMethod);
        Object localObject1;
        if (i3 == 1)
        {
          if (arrayOfBeanPropertyDefinition == null) {}
          for (localObject1 = null;; localObject1 = arrayOfBeanPropertyDefinition[0])
          {
            if (_checkIfCreatorPropertyBased(paramAnnotationIntrospector, localAnnotatedMethod, (BeanPropertyDefinition)localObject1)) {
              break label150;
            }
            _handleSingleArgumentFactory(localDeserializationConfig, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localAnnotatedMethod, bool);
            break;
          }
        }
        if (bool)
        {
          label150:
          localObject1 = null;
          SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[i3];
          int n = 0;
          int m = 0;
          int j = 0;
          int k = 0;
          if (k < i3)
          {
            AnnotatedParameter localAnnotatedParameter = localAnnotatedMethod.getParameter(k);
            Object localObject2;
            label196:
            Object localObject3;
            PropertyName localPropertyName;
            label213:
            int i1;
            int i2;
            int i;
            if (arrayOfBeanPropertyDefinition == null)
            {
              localObject2 = null;
              localObject3 = paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter);
              if (localObject2 != null) {
                break label298;
              }
              localPropertyName = null;
              if ((localObject2 == null) || (!((BeanPropertyDefinition)localObject2).isExplicitlyNamed())) {
                break label308;
              }
              i1 = m + 1;
              arrayOfSettableBeanProperty[k] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, k, localAnnotatedParameter, localObject3);
              localObject2 = localObject1;
              i2 = j;
              i = n;
            }
            for (;;)
            {
              k += 1;
              m = i1;
              n = i;
              j = i2;
              localObject1 = localObject2;
              break;
              localObject2 = arrayOfBeanPropertyDefinition[k];
              break label196;
              label298:
              localPropertyName = ((BeanPropertyDefinition)localObject2).getFullName();
              break label213;
              label308:
              if (localObject3 != null)
              {
                i2 = j + 1;
                arrayOfSettableBeanProperty[k] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, k, localAnnotatedParameter, localObject3);
                i1 = m;
                i = n;
                localObject2 = localObject1;
              }
              else if (paramAnnotationIntrospector.findUnwrappingNameTransformer(localAnnotatedParameter) != null)
              {
                arrayOfSettableBeanProperty[k] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, UNWRAPPED_CREATOR_PARAM_NAME, k, localAnnotatedParameter, null);
                i = n + 1;
                i1 = m;
                i2 = j;
                localObject2 = localObject1;
              }
              else if ((bool) && (localPropertyName != null) && (!localPropertyName.isEmpty()))
              {
                i = n + 1;
                arrayOfSettableBeanProperty[k] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, k, localAnnotatedParameter, localObject3);
                i1 = m;
                i2 = j;
                localObject2 = localObject1;
              }
              else
              {
                i1 = m;
                i = n;
                i2 = j;
                localObject2 = localObject1;
                if (localObject1 == null)
                {
                  localObject2 = localAnnotatedParameter;
                  i1 = m;
                  i = n;
                  i2 = j;
                }
              }
            }
          }
          if ((bool) || (m > 0) || (j > 0)) {
            if (m + n + j == i3) {
              paramCreatorCollector.addPropertyCreator(localAnnotatedMethod, bool, arrayOfSettableBeanProperty);
            } else if ((m == 0) && (j + 1 == i3)) {
              paramCreatorCollector.addDelegatingCreator(localAnnotatedMethod, bool, arrayOfSettableBeanProperty);
            } else {
              throw new IllegalArgumentException("Argument #" + ((AnnotatedParameter)localObject1).getIndex() + " of factory method " + localAnnotatedMethod + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
            }
          }
        }
      }
    }
  }
  
  protected boolean _checkIfCreatorPropertyBased(AnnotationIntrospector paramAnnotationIntrospector, AnnotatedWithParams paramAnnotatedWithParams, BeanPropertyDefinition paramBeanPropertyDefinition)
  {
    JsonCreator.Mode localMode = paramAnnotationIntrospector.findCreatorBinding(paramAnnotatedWithParams);
    if (localMode == JsonCreator.Mode.PROPERTIES) {}
    do
    {
      do
      {
        return true;
        if (localMode == JsonCreator.Mode.DELEGATING) {
          return false;
        }
      } while (((paramBeanPropertyDefinition != null) && (paramBeanPropertyDefinition.isExplicitlyNamed())) || (paramAnnotationIntrospector.findInjectableValueId(paramAnnotatedWithParams.getParameter(0)) != null));
      if (paramBeanPropertyDefinition == null) {
        break;
      }
      paramAnnotationIntrospector = paramBeanPropertyDefinition.getName();
    } while ((paramAnnotationIntrospector != null) && (!paramAnnotationIntrospector.isEmpty()) && (paramBeanPropertyDefinition.couldSerialize()));
    return false;
  }
  
  protected void _checkImplicitlyNamedConstructors(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, List<AnnotatedConstructor> paramList)
    throws JsonMappingException
  {
    Object localObject2 = null;
    Object localObject1 = null;
    Iterator localIterator = paramList.iterator();
    paramList = (List<AnnotatedConstructor>)localObject2;
    for (;;)
    {
      localObject2 = paramList;
      AnnotatedConstructor localAnnotatedConstructor;
      int j;
      int i;
      if (localIterator.hasNext())
      {
        localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
        if (!paramVisibilityChecker.isCreatorVisible(localAnnotatedConstructor)) {
          continue;
        }
        j = localAnnotatedConstructor.getParameterCount();
        localObject2 = new SettableBeanProperty[j];
        i = 0;
        for (;;)
        {
          if (i >= j) {
            break label142;
          }
          AnnotatedParameter localAnnotatedParameter = localAnnotatedConstructor.getParameter(i);
          PropertyName localPropertyName = _findParamName(localAnnotatedParameter, paramAnnotationIntrospector);
          if ((localPropertyName == null) || (localPropertyName.isEmpty())) {
            break;
          }
          localObject2[i] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, localAnnotatedParameter.getIndex(), localAnnotatedParameter, null);
          i += 1;
        }
        label142:
        if (paramList != null) {
          localObject2 = null;
        }
      }
      else
      {
        if (localObject2 == null) {
          break;
        }
        paramCreatorCollector.addPropertyCreator((AnnotatedWithParams)localObject2, false, (SettableBeanProperty[])localObject1);
        paramBeanDescription = (BasicBeanDescription)paramBeanDescription;
        j = localObject1.length;
        i = 0;
        while (i < j)
        {
          paramVisibilityChecker = localObject1[i];
          paramAnnotationIntrospector = paramVisibilityChecker.getFullName();
          if (!paramBeanDescription.hasProperty(paramAnnotationIntrospector)) {
            paramBeanDescription.addProperty(SimpleBeanPropertyDefinition.construct(paramDeserializationContext.getConfig(), paramVisibilityChecker.getMember(), paramAnnotationIntrospector));
          }
          i += 1;
        }
      }
      paramList = localAnnotatedConstructor;
      localObject1 = localObject2;
    }
  }
  
  protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    CreatorCollector localCreatorCollector = new CreatorCollector(paramBeanDescription, paramDeserializationContext.getConfig());
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    VisibilityChecker localVisibilityChecker = localDeserializationConfig.getDefaultVisibilityChecker();
    localVisibilityChecker = localAnnotationIntrospector.findAutoDetectVisibility(paramBeanDescription.getClassInfo(), localVisibilityChecker);
    Map localMap = _findCreatorsFromProperties(paramDeserializationContext, paramBeanDescription);
    _addDeserializerFactoryMethods(paramDeserializationContext, paramBeanDescription, localVisibilityChecker, localAnnotationIntrospector, localCreatorCollector, localMap);
    if (paramBeanDescription.getType().isConcrete()) {
      _addDeserializerConstructors(paramDeserializationContext, paramBeanDescription, localVisibilityChecker, localAnnotationIntrospector, localCreatorCollector, localMap);
    }
    return localCreatorCollector.constructValueInstantiator(localDeserializationConfig);
  }
  
  protected Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    paramDeserializationContext = Collections.emptyMap();
    Iterator localIterator1 = paramBeanDescription.findProperties().iterator();
    paramBeanDescription = paramDeserializationContext;
    while (localIterator1.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator1.next();
      Iterator localIterator2 = localBeanPropertyDefinition.getConstructorParameters();
      paramDeserializationContext = paramBeanDescription;
      paramBeanDescription = paramDeserializationContext;
      if (localIterator2.hasNext())
      {
        paramBeanDescription = (AnnotatedParameter)localIterator2.next();
        AnnotatedWithParams localAnnotatedWithParams = paramBeanDescription.getOwner();
        BeanPropertyDefinition[] arrayOfBeanPropertyDefinition = (BeanPropertyDefinition[])paramDeserializationContext.get(localAnnotatedWithParams);
        int i = paramBeanDescription.getIndex();
        if (arrayOfBeanPropertyDefinition == null)
        {
          paramBeanDescription = paramDeserializationContext;
          if (paramDeserializationContext.isEmpty()) {
            paramBeanDescription = new LinkedHashMap();
          }
          arrayOfBeanPropertyDefinition = new BeanPropertyDefinition[localAnnotatedWithParams.getParameterCount()];
          paramBeanDescription.put(localAnnotatedWithParams, arrayOfBeanPropertyDefinition);
          paramDeserializationContext = paramBeanDescription;
          paramBeanDescription = arrayOfBeanPropertyDefinition;
        }
        do
        {
          paramBeanDescription[i] = localBeanPropertyDefinition;
          break;
          paramBeanDescription = arrayOfBeanPropertyDefinition;
        } while (arrayOfBeanPropertyDefinition[i] == null);
        throw new IllegalStateException("Conflict: parameter #" + i + " of " + localAnnotatedWithParams + " bound to more than one property; " + arrayOfBeanPropertyDefinition[i] + " vs " + localBeanPropertyDefinition);
      }
    }
    return paramBeanDescription;
  }
  
  protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findArrayDeserializer(paramArrayType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findBeanDeserializer(paramJavaType, paramDeserializationConfig, paramBeanDescription);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findCollectionDeserializer(paramCollectionType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findCollectionLikeDeserializer(paramCollectionLikeType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findEnumDeserializer(paramClass, paramDeserializationConfig, paramBeanDescription);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findMapDeserializer(paramMapType, paramDeserializationConfig, paramBeanDescription, paramKeyDeserializer, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findMapLikeDeserializer(paramMapLikeType, paramDeserializationConfig, paramBeanDescription, paramKeyDeserializer, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomReferenceDeserializer(ReferenceType paramReferenceType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findReferenceDeserializer(paramReferenceType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findTreeNodeDeserializer(paramClass, paramDeserializationConfig, paramBeanDescription);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  @Deprecated
  protected PropertyName _findExplicitParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    if ((paramAnnotatedParameter != null) && (paramAnnotationIntrospector != null)) {
      return paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
    }
    return null;
  }
  
  protected PropertyName _findImplicitParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    paramAnnotatedParameter = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
    if ((paramAnnotatedParameter != null) && (!paramAnnotatedParameter.isEmpty())) {
      return PropertyName.construct(paramAnnotatedParameter);
    }
    return null;
  }
  
  protected AnnotatedMethod _findJsonValueFor(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    if (paramJavaType == null) {
      return null;
    }
    return paramDeserializationConfig.introspect(paramJavaType).findJsonValueMethod();
  }
  
  protected PropertyName _findParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    if ((paramAnnotatedParameter != null) && (paramAnnotationIntrospector != null))
    {
      PropertyName localPropertyName = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
      if (localPropertyName != null) {
        return localPropertyName;
      }
      paramAnnotatedParameter = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
      if ((paramAnnotatedParameter != null) && (!paramAnnotatedParameter.isEmpty())) {
        return PropertyName.construct(paramAnnotatedParameter);
      }
    }
    return null;
  }
  
  protected JavaType _findRemappedType(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
    throws JsonMappingException
  {
    JavaType localJavaType = mapAbstractType(paramDeserializationConfig, paramDeserializationConfig.constructType(paramClass));
    if (localJavaType != null)
    {
      paramDeserializationConfig = localJavaType;
      if (!localJavaType.hasRawClass(paramClass)) {}
    }
    else
    {
      paramDeserializationConfig = null;
    }
    return paramDeserializationConfig;
  }
  
  protected boolean _handleSingleArgumentConstructor(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedConstructor paramAnnotatedConstructor, boolean paramBoolean1, boolean paramBoolean2)
    throws JsonMappingException
  {
    paramDeserializationContext = paramAnnotatedConstructor.getRawParameterType(0);
    if ((paramDeserializationContext == String.class) || (paramDeserializationContext == CharSequence.class)) {
      if ((paramBoolean1) || (paramBoolean2)) {
        paramCreatorCollector.addStringCreator(paramAnnotatedConstructor, paramBoolean1);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return true;
            if ((paramDeserializationContext != Integer.TYPE) && (paramDeserializationContext != Integer.class)) {
              break;
            }
          } while ((!paramBoolean1) && (!paramBoolean2));
          paramCreatorCollector.addIntCreator(paramAnnotatedConstructor, paramBoolean1);
          return true;
          if ((paramDeserializationContext != Long.TYPE) && (paramDeserializationContext != Long.class)) {
            break;
          }
        } while ((!paramBoolean1) && (!paramBoolean2));
        paramCreatorCollector.addLongCreator(paramAnnotatedConstructor, paramBoolean1);
        return true;
        if ((paramDeserializationContext != Double.TYPE) && (paramDeserializationContext != Double.class)) {
          break;
        }
      } while ((!paramBoolean1) && (!paramBoolean2));
      paramCreatorCollector.addDoubleCreator(paramAnnotatedConstructor, paramBoolean1);
      return true;
      if ((paramDeserializationContext != Boolean.TYPE) && (paramDeserializationContext != Boolean.class)) {
        break;
      }
    } while ((!paramBoolean1) && (!paramBoolean2));
    paramCreatorCollector.addBooleanCreator(paramAnnotatedConstructor, paramBoolean1);
    return true;
    if (paramBoolean1)
    {
      paramCreatorCollector.addDelegatingCreator(paramAnnotatedConstructor, paramBoolean1, null);
      return true;
    }
    return false;
  }
  
  protected boolean _handleSingleArgumentFactory(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
    throws JsonMappingException
  {
    paramDeserializationConfig = paramAnnotatedMethod.getRawParameterType(0);
    if ((paramDeserializationConfig == String.class) || (paramDeserializationConfig == CharSequence.class)) {
      if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
        paramCreatorCollector.addStringCreator(paramAnnotatedMethod, paramBoolean);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return true;
            if ((paramDeserializationConfig != Integer.TYPE) && (paramDeserializationConfig != Integer.class)) {
              break;
            }
          } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
          paramCreatorCollector.addIntCreator(paramAnnotatedMethod, paramBoolean);
          return true;
          if ((paramDeserializationConfig != Long.TYPE) && (paramDeserializationConfig != Long.class)) {
            break;
          }
        } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
        paramCreatorCollector.addLongCreator(paramAnnotatedMethod, paramBoolean);
        return true;
        if ((paramDeserializationConfig != Double.TYPE) && (paramDeserializationConfig != Double.class)) {
          break;
        }
      } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
      paramCreatorCollector.addDoubleCreator(paramAnnotatedMethod, paramBoolean);
      return true;
      if ((paramDeserializationConfig != Boolean.TYPE) && (paramDeserializationConfig != Boolean.class)) {
        break;
      }
    } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
    paramCreatorCollector.addBooleanCreator(paramAnnotatedMethod, paramBoolean);
    return true;
    if (paramBoolean)
    {
      paramCreatorCollector.addDelegatingCreator(paramAnnotatedMethod, paramBoolean, null);
      return true;
    }
    return false;
  }
  
  @Deprecated
  protected boolean _hasExplicitParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramAnnotatedParameter != null)
    {
      bool1 = bool2;
      if (paramAnnotationIntrospector != null)
      {
        paramAnnotatedParameter = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
        bool1 = bool2;
        if (paramAnnotatedParameter != null)
        {
          bool1 = bool2;
          if (paramAnnotatedParameter.hasSimpleName()) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  protected CollectionType _mapAbstractCollectionType(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig)
  {
    Class localClass = paramJavaType.getRawClass();
    localClass = (Class)_collectionFallbacks.get(localClass.getName());
    if (localClass == null) {
      return null;
    }
    return (CollectionType)paramDeserializationConfig.constructSpecializedType(paramJavaType, localClass);
  }
  
  public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof ValueInstantiator)) {
      return (ValueInstantiator)paramObject;
    }
    if (!(paramObject instanceof Class)) {
      throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + paramObject.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
    }
    paramObject = (Class)paramObject;
    if (ClassUtil.isBogusClass((Class)paramObject)) {
      return null;
    }
    if (!ValueInstantiator.class.isAssignableFrom((Class)paramObject)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + ((Class)paramObject).getName() + "; expected Class<ValueInstantiator>");
    }
    HandlerInstantiator localHandlerInstantiator = paramDeserializationConfig.getHandlerInstantiator();
    if (localHandlerInstantiator != null)
    {
      paramAnnotated = localHandlerInstantiator.valueInstantiatorInstance(paramDeserializationConfig, paramAnnotated, (Class)paramObject);
      if (paramAnnotated != null) {
        return paramAnnotated;
      }
    }
    return (ValueInstantiator)ClassUtil.createInstance((Class)paramObject, paramDeserializationConfig.canOverrideAccessModifiers());
  }
  
  protected SettableBeanProperty constructCreatorProperty(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, PropertyName paramPropertyName, int paramInt, AnnotatedParameter paramAnnotatedParameter, Object paramObject)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject2 = paramDeserializationContext.getAnnotationIntrospector();
    if (localObject2 == null)
    {
      localObject1 = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      Object localObject5 = paramBeanDescription.resolveType(paramAnnotatedParameter.getParameterType());
      Object localObject3 = new BeanProperty.Std(paramPropertyName, (JavaType)localObject5, ((AnnotationIntrospector)localObject2).findWrapperName(paramAnnotatedParameter), paramBeanDescription.getClassAnnotations(), paramAnnotatedParameter, (PropertyMetadata)localObject1);
      Object localObject4 = resolveType(paramDeserializationContext, paramBeanDescription, (JavaType)localObject5, paramAnnotatedParameter);
      localObject2 = localObject3;
      if (localObject4 != localObject5) {
        localObject2 = ((BeanProperty.Std)localObject3).withType((JavaType)localObject4);
      }
      localObject5 = findDeserializerFromAnnotation(paramDeserializationContext, paramAnnotatedParameter);
      JavaType localJavaType = modifyTypeByAnnotation(paramDeserializationContext, paramAnnotatedParameter, (JavaType)localObject4);
      localObject4 = (TypeDeserializer)localJavaType.getTypeHandler();
      localObject3 = localObject4;
      if (localObject4 == null) {
        localObject3 = findTypeDeserializer(localDeserializationConfig, localJavaType);
      }
      paramPropertyName = new CreatorProperty(paramPropertyName, localJavaType, ((BeanProperty.Std)localObject2).getWrapperName(), (TypeDeserializer)localObject3, paramBeanDescription.getClassAnnotations(), paramAnnotatedParameter, paramInt, paramObject, (PropertyMetadata)localObject1);
      paramBeanDescription = paramPropertyName;
      if (localObject5 != null) {
        paramBeanDescription = paramPropertyName.withValueDeserializer(paramDeserializationContext.handlePrimaryContextualization((JsonDeserializer)localObject5, paramPropertyName, localJavaType));
      }
      return paramBeanDescription;
    }
    Object localObject1 = ((AnnotationIntrospector)localObject2).hasRequiredMarker(paramAnnotatedParameter);
    if ((localObject1 != null) && (((Boolean)localObject1).booleanValue())) {}
    for (boolean bool = true;; bool = false)
    {
      localObject1 = PropertyMetadata.construct(bool, ((AnnotationIntrospector)localObject2).findPropertyDescription(paramAnnotatedParameter), ((AnnotationIntrospector)localObject2).findPropertyIndex(paramAnnotatedParameter), ((AnnotationIntrospector)localObject2).findPropertyDefaultValue(paramAnnotatedParameter));
      break;
    }
  }
  
  protected EnumResolver constructEnumResolver(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, AnnotatedMethod paramAnnotatedMethod)
  {
    if (paramAnnotatedMethod != null)
    {
      paramAnnotatedMethod = paramAnnotatedMethod.getAnnotated();
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(paramAnnotatedMethod, paramDeserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
      }
      return EnumResolver.constructUnsafeUsingMethod(paramClass, paramAnnotatedMethod);
    }
    return EnumResolver.constructUnsafe(paramClass, paramDeserializationConfig.getAnnotationIntrospector());
  }
  
  public JsonDeserializer<?> createArrayDeserializer(DeserializationContext paramDeserializationContext, ArrayType paramArrayType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JavaType localJavaType = paramArrayType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType.getValueHandler();
    paramDeserializationContext = (TypeDeserializer)localJavaType.getTypeHandler();
    Object localObject1 = paramDeserializationContext;
    if (paramDeserializationContext == null) {
      localObject1 = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    Object localObject2 = _findCustomArrayDeserializer(paramArrayType, localDeserializationConfig, paramBeanDescription, (TypeDeserializer)localObject1, localJsonDeserializer);
    paramDeserializationContext = (DeserializationContext)localObject2;
    if (localObject2 == null)
    {
      if (localJsonDeserializer == null)
      {
        paramDeserializationContext = localJavaType.getRawClass();
        if (localJavaType.isPrimitive()) {
          return PrimitiveArrayDeserializers.forType(paramDeserializationContext);
        }
        if (paramDeserializationContext == String.class) {
          return StringArrayDeserializer.instance;
        }
      }
      paramDeserializationContext = new ObjectArrayDeserializer(paramArrayType, localJsonDeserializer, (TypeDeserializer)localObject1);
    }
    localObject1 = paramDeserializationContext;
    if (this._factoryConfig.hasDeserializerModifiers())
    {
      localObject2 = this._factoryConfig.deserializerModifiers().iterator();
      for (;;)
      {
        localObject1 = paramDeserializationContext;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject2).next()).modifyArrayDeserializer(localDeserializationConfig, paramArrayType, paramBeanDescription, paramDeserializationContext);
      }
    }
    return (JsonDeserializer<?>)localObject1;
  }
  
  public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext paramDeserializationContext, CollectionType paramCollectionType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType = paramCollectionType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType.getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = (TypeDeserializer)localJavaType.getTypeHandler();
    Object localObject5 = localObject1;
    if (localObject1 == null) {
      localObject5 = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    localObject1 = _findCustomCollectionDeserializer(paramCollectionType, localDeserializationConfig, paramBeanDescription, (TypeDeserializer)localObject5, localJsonDeserializer);
    Object localObject2 = localObject1;
    Object localObject3;
    if (localObject1 == null)
    {
      localObject3 = paramCollectionType.getRawClass();
      localObject2 = localObject1;
      if (localJsonDeserializer == null)
      {
        localObject2 = localObject1;
        if (EnumSet.class.isAssignableFrom((Class)localObject3)) {
          localObject2 = new EnumSetDeserializer(localJavaType, null);
        }
      }
    }
    localObject1 = localObject2;
    Object localObject6 = paramCollectionType;
    Object localObject7 = paramBeanDescription;
    BeanDescription localBeanDescription;
    if (localObject2 == null)
    {
      Object localObject4;
      if (!paramCollectionType.isInterface())
      {
        localObject4 = localObject2;
        localObject3 = paramCollectionType;
        localBeanDescription = paramBeanDescription;
        if (!paramCollectionType.isAbstract()) {}
      }
      else
      {
        localObject3 = _mapAbstractCollectionType(paramCollectionType, localDeserializationConfig);
        if (localObject3 != null) {
          break label274;
        }
        if (paramCollectionType.getTypeHandler() == null) {
          throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + paramCollectionType);
        }
        localObject4 = AbstractDeserializer.constructForNonPOJO(paramBeanDescription);
        localBeanDescription = paramBeanDescription;
        localObject3 = paramCollectionType;
      }
      for (;;)
      {
        localObject1 = localObject4;
        localObject6 = localObject3;
        localObject7 = localBeanDescription;
        if (localObject4 != null) {
          break label322;
        }
        paramDeserializationContext = findValueInstantiator(paramDeserializationContext, localBeanDescription);
        if ((paramDeserializationContext.canCreateUsingDefault()) || (((CollectionType)localObject3).getRawClass() != ArrayBlockingQueue.class)) {
          break;
        }
        return new ArrayBlockingQueueDeserializer((JavaType)localObject3, localJsonDeserializer, (TypeDeserializer)localObject5, paramDeserializationContext);
        label274:
        localBeanDescription = localDeserializationConfig.introspectForCreation((JavaType)localObject3);
        localObject4 = localObject2;
      }
      if (localJavaType.getRawClass() != String.class) {
        break label385;
      }
      localObject1 = new StringCollectionDeserializer((JavaType)localObject3, localJsonDeserializer, paramDeserializationContext);
      localObject7 = localBeanDescription;
      localObject6 = localObject3;
    }
    for (;;)
    {
      label322:
      paramDeserializationContext = (DeserializationContext)localObject1;
      if (!this._factoryConfig.hasDeserializerModifiers()) {
        break;
      }
      paramCollectionType = this._factoryConfig.deserializerModifiers().iterator();
      for (;;)
      {
        paramDeserializationContext = (DeserializationContext)localObject1;
        if (!paramCollectionType.hasNext()) {
          break;
        }
        localObject1 = ((BeanDeserializerModifier)paramCollectionType.next()).modifyCollectionDeserializer(localDeserializationConfig, (CollectionType)localObject6, (BeanDescription)localObject7, (JsonDeserializer)localObject1);
      }
      label385:
      localObject1 = new CollectionDeserializer((JavaType)localObject3, localJsonDeserializer, (TypeDeserializer)localObject5, paramDeserializationContext);
      localObject6 = localObject3;
      localObject7 = localBeanDescription;
    }
    return paramDeserializationContext;
  }
  
  public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext paramDeserializationContext, CollectionLikeType paramCollectionLikeType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType = paramCollectionLikeType.getContentType();
    Object localObject2 = (JsonDeserializer)localJavaType.getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = (TypeDeserializer)localJavaType.getTypeHandler();
    paramDeserializationContext = (DeserializationContext)localObject1;
    if (localObject1 == null) {
      paramDeserializationContext = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    paramDeserializationContext = _findCustomCollectionLikeDeserializer(paramCollectionLikeType, localDeserializationConfig, paramBeanDescription, paramDeserializationContext, (JsonDeserializer)localObject2);
    localObject1 = paramDeserializationContext;
    if (paramDeserializationContext != null)
    {
      localObject1 = paramDeserializationContext;
      if (this._factoryConfig.hasDeserializerModifiers())
      {
        localObject2 = this._factoryConfig.deserializerModifiers().iterator();
        for (;;)
        {
          localObject1 = paramDeserializationContext;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject2).next()).modifyCollectionLikeDeserializer(localDeserializationConfig, paramCollectionLikeType, paramBeanDescription, paramDeserializationContext);
        }
      }
    }
    return (JsonDeserializer<?>)localObject1;
  }
  
  public JsonDeserializer<?> createEnumDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Class localClass = paramJavaType.getRawClass();
    JsonDeserializer localJsonDeserializer = _findCustomEnumDeserializer(localClass, localDeserializationConfig, paramBeanDescription);
    Object localObject1 = localJsonDeserializer;
    Object localObject2;
    if (localJsonDeserializer == null)
    {
      localObject1 = paramBeanDescription.getFactoryMethods().iterator();
      do
      {
        localObject2 = localJsonDeserializer;
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (AnnotatedMethod)((Iterator)localObject1).next();
      } while (!paramDeserializationContext.getAnnotationIntrospector().hasCreatorAnnotation((Annotated)localObject2));
      if ((((AnnotatedMethod)localObject2).getParameterCount() != 1) || (!((AnnotatedMethod)localObject2).getRawReturnType().isAssignableFrom(localClass))) {
        break label208;
      }
      localObject2 = EnumDeserializer.deserializerForCreator(localDeserializationConfig, localClass, (AnnotatedMethod)localObject2);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new EnumDeserializer(constructEnumResolver(localClass, localDeserializationConfig, paramBeanDescription.findJsonValueMethod()));
      }
    }
    paramDeserializationContext = (DeserializationContext)localObject1;
    if (this._factoryConfig.hasDeserializerModifiers())
    {
      localObject2 = this._factoryConfig.deserializerModifiers().iterator();
      for (;;)
      {
        paramDeserializationContext = (DeserializationContext)localObject1;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject1 = ((BeanDeserializerModifier)((Iterator)localObject2).next()).modifyEnumDeserializer(localDeserializationConfig, paramJavaType, paramBeanDescription, (JsonDeserializer)localObject1);
      }
      label208:
      throw new IllegalArgumentException("Unsuitable method (" + localObject2 + ") decorated with @JsonCreator (for Enum type " + localClass.getName() + ")");
    }
    return paramDeserializationContext;
  }
  
  public KeyDeserializer createKeyDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = null;
    Object localObject2 = null;
    if (this._factoryConfig.hasKeyDeserializers())
    {
      BeanDescription localBeanDescription = localDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass());
      Iterator localIterator = this._factoryConfig.keyDeserializers().iterator();
      localObject1 = localObject2;
      while (localIterator.hasNext())
      {
        localObject2 = ((KeyDeserializers)localIterator.next()).findKeyDeserializer(paramJavaType, localDeserializationConfig, localBeanDescription);
        localObject1 = localObject2;
        if (localObject2 != null) {
          localObject1 = localObject2;
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      if (paramJavaType.isEnumType()) {
        return _createEnumKeyDeserializer(paramDeserializationContext, paramJavaType);
      }
      localObject2 = StdKeyDeserializers.findStringBasedKeyDeserializer(localDeserializationConfig, paramJavaType);
    }
    paramDeserializationContext = (DeserializationContext)localObject2;
    if (localObject2 != null)
    {
      paramDeserializationContext = (DeserializationContext)localObject2;
      if (this._factoryConfig.hasDeserializerModifiers())
      {
        localObject1 = this._factoryConfig.deserializerModifiers().iterator();
        for (;;)
        {
          paramDeserializationContext = (DeserializationContext)localObject2;
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          localObject2 = ((BeanDeserializerModifier)((Iterator)localObject1).next()).modifyKeyDeserializer(localDeserializationConfig, paramJavaType, (KeyDeserializer)localObject2);
        }
      }
    }
    return paramDeserializationContext;
  }
  
  public JsonDeserializer<?> createMapDeserializer(DeserializationContext paramDeserializationContext, MapType paramMapType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject3 = paramMapType.getKeyType();
    Object localObject2 = paramMapType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)((JavaType)localObject2).getValueHandler();
    KeyDeserializer localKeyDeserializer = (KeyDeserializer)((JavaType)localObject3).getValueHandler();
    Object localObject1 = (TypeDeserializer)((JavaType)localObject2).getTypeHandler();
    Object localObject5 = localObject1;
    if (localObject1 == null) {
      localObject5 = findTypeDeserializer(localDeserializationConfig, (JavaType)localObject2);
    }
    localObject2 = _findCustomMapDeserializer(paramMapType, localDeserializationConfig, paramBeanDescription, localKeyDeserializer, (TypeDeserializer)localObject5, localJsonDeserializer);
    localObject1 = localObject2;
    Object localObject6 = paramMapType;
    Object localObject7 = paramBeanDescription;
    Object localObject4;
    MapType localMapType;
    if (localObject2 == null)
    {
      Class localClass = paramMapType.getRawClass();
      if (EnumMap.class.isAssignableFrom(localClass))
      {
        localObject1 = ((JavaType)localObject3).getRawClass();
        if ((localObject1 == null) || (!((Class)localObject1).isEnum())) {
          throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
        }
        localObject2 = new EnumMapDeserializer(paramMapType, null, localJsonDeserializer, (TypeDeserializer)localObject5);
      }
      localObject1 = localObject2;
      localObject6 = paramMapType;
      localObject7 = paramBeanDescription;
      if (localObject2 == null) {
        if (!paramMapType.isInterface())
        {
          localObject4 = localObject2;
          localMapType = paramMapType;
          localObject3 = paramBeanDescription;
          if (!paramMapType.isAbstract()) {}
        }
        else
        {
          localObject1 = (Class)_mapFallbacks.get(localClass.getName());
          if (localObject1 == null) {
            break label378;
          }
          localMapType = (MapType)localDeserializationConfig.constructSpecializedType(paramMapType, (Class)localObject1);
          localObject3 = localDeserializationConfig.introspectForCreation(localMapType);
          localObject4 = localObject2;
        }
      }
    }
    for (;;)
    {
      localObject1 = localObject4;
      localObject6 = localMapType;
      localObject7 = localObject3;
      if (localObject4 == null)
      {
        localObject1 = new MapDeserializer(localMapType, findValueInstantiator(paramDeserializationContext, (BeanDescription)localObject3), localKeyDeserializer, localJsonDeserializer, (TypeDeserializer)localObject5);
        ((MapDeserializer)localObject1).setIgnorableProperties(localDeserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(((BeanDescription)localObject3).getClassInfo(), false));
        localObject7 = localObject3;
        localObject6 = localMapType;
      }
      paramDeserializationContext = (DeserializationContext)localObject1;
      if (!this._factoryConfig.hasDeserializerModifiers()) {
        break;
      }
      paramMapType = this._factoryConfig.deserializerModifiers().iterator();
      for (;;)
      {
        paramDeserializationContext = (DeserializationContext)localObject1;
        if (!paramMapType.hasNext()) {
          break;
        }
        localObject1 = ((BeanDeserializerModifier)paramMapType.next()).modifyMapDeserializer(localDeserializationConfig, (MapType)localObject6, (BeanDescription)localObject7, (JsonDeserializer)localObject1);
      }
      label378:
      if (paramMapType.getTypeHandler() == null) {
        throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + paramMapType);
      }
      localObject4 = AbstractDeserializer.constructForNonPOJO(paramBeanDescription);
      localMapType = paramMapType;
      localObject3 = paramBeanDescription;
    }
    return paramDeserializationContext;
  }
  
  public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext paramDeserializationContext, MapLikeType paramMapLikeType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject1 = paramMapLikeType.getKeyType();
    Object localObject2 = paramMapLikeType.getContentType();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)((JavaType)localObject2).getValueHandler();
    KeyDeserializer localKeyDeserializer = (KeyDeserializer)((JavaType)localObject1).getValueHandler();
    localObject1 = (TypeDeserializer)((JavaType)localObject2).getTypeHandler();
    paramDeserializationContext = (DeserializationContext)localObject1;
    if (localObject1 == null) {
      paramDeserializationContext = findTypeDeserializer(localDeserializationConfig, (JavaType)localObject2);
    }
    paramDeserializationContext = _findCustomMapLikeDeserializer(paramMapLikeType, localDeserializationConfig, paramBeanDescription, localKeyDeserializer, paramDeserializationContext, localJsonDeserializer);
    localObject1 = paramDeserializationContext;
    if (paramDeserializationContext != null)
    {
      localObject1 = paramDeserializationContext;
      if (this._factoryConfig.hasDeserializerModifiers())
      {
        localObject2 = this._factoryConfig.deserializerModifiers().iterator();
        for (;;)
        {
          localObject1 = paramDeserializationContext;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject2).next()).modifyMapLikeDeserializer(localDeserializationConfig, paramMapLikeType, paramBeanDescription, paramDeserializationContext);
        }
      }
    }
    return (JsonDeserializer<?>)localObject1;
  }
  
  public JsonDeserializer<?> createReferenceDeserializer(DeserializationContext paramDeserializationContext, ReferenceType paramReferenceType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject3 = paramReferenceType.getContentType();
    Object localObject2 = (JsonDeserializer)((JavaType)localObject3).getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = (TypeDeserializer)((JavaType)localObject3).getTypeHandler();
    paramDeserializationContext = (DeserializationContext)localObject1;
    if (localObject1 == null) {
      paramDeserializationContext = findTypeDeserializer(localDeserializationConfig, (JavaType)localObject3);
    }
    localObject1 = _findCustomReferenceDeserializer(paramReferenceType, localDeserializationConfig, paramBeanDescription, paramDeserializationContext, (JsonDeserializer)localObject2);
    if ((localObject1 == null) && (AtomicReference.class.isAssignableFrom(paramReferenceType.getRawClass()))) {
      return new AtomicReferenceDeserializer(paramReferenceType.getReferencedType(), paramDeserializationContext, (JsonDeserializer)localObject1);
    }
    localObject2 = localObject1;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (this._factoryConfig.hasDeserializerModifiers())
      {
        localObject3 = this._factoryConfig.deserializerModifiers().iterator();
        for (paramDeserializationContext = (DeserializationContext)localObject1;; paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject3).next()).modifyReferenceDeserializer(localDeserializationConfig, paramReferenceType, paramBeanDescription, paramDeserializationContext))
        {
          localObject2 = paramDeserializationContext;
          if (!((Iterator)localObject3).hasNext()) {
            break;
          }
        }
      }
    }
    return (JsonDeserializer<?>)localObject2;
  }
  
  public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    paramJavaType = paramJavaType.getRawClass();
    paramDeserializationConfig = _findCustomTreeNodeDeserializer(paramJavaType, paramDeserializationConfig, paramBeanDescription);
    if (paramDeserializationConfig != null) {
      return paramDeserializationConfig;
    }
    return JsonNodeDeserializer.getDeserializer(paramJavaType);
  }
  
  public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject3 = paramJavaType.getRawClass();
    Object localObject1;
    if (localObject3 == CLASS_OBJECT)
    {
      paramJavaType = paramDeserializationContext.getConfig();
      if (this._factoryConfig.hasAbstractTypeResolvers())
      {
        paramDeserializationContext = _findRemappedType(paramJavaType, List.class);
        paramJavaType = _findRemappedType(paramJavaType, Map.class);
        localObject1 = new UntypedObjectDeserializer(paramDeserializationContext, paramJavaType);
      }
    }
    String str;
    label315:
    do
    {
      Object localObject2;
      do
      {
        return (JsonDeserializer<?>)localObject1;
        paramJavaType = null;
        paramDeserializationContext = null;
        break;
        if ((localObject3 == CLASS_STRING) || (localObject3 == CLASS_CHAR_BUFFER)) {
          return StringDeserializer.instance;
        }
        if (localObject3 == CLASS_ITERABLE)
        {
          localObject1 = paramDeserializationContext.getTypeFactory();
          paramJavaType = ((TypeFactory)localObject1).findTypeParameters(paramJavaType, CLASS_ITERABLE);
          if ((paramJavaType == null) || (paramJavaType.length != 1)) {}
          for (paramJavaType = TypeFactory.unknownType();; paramJavaType = paramJavaType[0]) {
            return createCollectionDeserializer(paramDeserializationContext, ((TypeFactory)localObject1).constructCollectionType(Collection.class, paramJavaType), paramBeanDescription);
          }
        }
        if (localObject3 == CLASS_MAP_ENTRY)
        {
          localObject1 = paramJavaType.containedType(0);
          paramBeanDescription = (BeanDescription)localObject1;
          if (localObject1 == null) {
            paramBeanDescription = TypeFactory.unknownType();
          }
          localObject2 = paramJavaType.containedType(1);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = TypeFactory.unknownType();
          }
          localObject3 = (TypeDeserializer)((JavaType)localObject1).getTypeHandler();
          localObject2 = localObject3;
          if (localObject3 == null) {
            localObject2 = findTypeDeserializer(paramDeserializationContext.getConfig(), (JavaType)localObject1);
          }
          paramDeserializationContext = (JsonDeserializer)((JavaType)localObject1).getValueHandler();
          return new MapEntryDeserializer(paramJavaType, (KeyDeserializer)paramBeanDescription.getValueHandler(), paramDeserializationContext, (TypeDeserializer)localObject2);
        }
        str = ((Class)localObject3).getName();
        if ((!((Class)localObject3).isPrimitive()) && (!str.startsWith("java."))) {
          break label315;
        }
        localObject1 = NumberDeserializers.find((Class)localObject3, str);
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = DateDeserializers.find((Class)localObject3, str);
        }
        localObject1 = localObject2;
      } while (localObject2 != null);
      if (localObject3 == TokenBuffer.class) {
        return new TokenBufferDeserializer();
      }
      paramDeserializationContext = findOptionalStdDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
      localObject1 = paramDeserializationContext;
    } while (paramDeserializationContext != null);
    return JdkDeserializers.find((Class)localObject3, str);
  }
  
  protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findDeserializer(paramAnnotated);
    if (localObject == null) {
      return null;
    }
    return paramDeserializationContext.deserializerInstance(paramAnnotated, localObject);
  }
  
  protected KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findKeyDeserializer(paramAnnotated);
    if (localObject == null) {
      return null;
    }
    return paramDeserializationContext.keyDeserializerInstance(paramAnnotated, localObject);
  }
  
  protected JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    return OptionalHandlerFactory.instance.findDeserializer(paramJavaType, paramDeserializationContext.getConfig(), paramBeanDescription);
  }
  
  public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    TypeResolverBuilder localTypeResolverBuilder = paramDeserializationConfig.getAnnotationIntrospector().findPropertyContentTypeResolver(paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    paramJavaType = paramJavaType.getContentType();
    if (localTypeResolverBuilder == null) {
      return findTypeDeserializer(paramDeserializationConfig, paramJavaType);
    }
    return localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(paramDeserializationConfig, paramAnnotatedMember, paramJavaType));
  }
  
  public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    TypeResolverBuilder localTypeResolverBuilder = paramDeserializationConfig.getAnnotationIntrospector().findPropertyTypeResolver(paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {
      return findTypeDeserializer(paramDeserializationConfig, paramJavaType);
    }
    return localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(paramDeserializationConfig, paramAnnotatedMember, paramJavaType));
  }
  
  public TypeDeserializer findTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    Object localObject2 = paramDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass()).getClassInfo();
    Object localObject1 = paramDeserializationConfig.getAnnotationIntrospector().findTypeResolver(paramDeserializationConfig, (AnnotatedClass)localObject2, paramJavaType);
    Collection localCollection = null;
    if (localObject1 == null)
    {
      localObject2 = paramDeserializationConfig.getDefaultTyper(paramJavaType);
      localObject1 = localObject2;
      if (localObject2 == null) {
        return null;
      }
    }
    else
    {
      localCollection = paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(paramDeserializationConfig, (AnnotatedClass)localObject2);
    }
    localObject2 = localObject1;
    if (((TypeResolverBuilder)localObject1).getDefaultImpl() == null)
    {
      localObject2 = localObject1;
      if (paramJavaType.isAbstract())
      {
        JavaType localJavaType = mapAbstractType(paramDeserializationConfig, paramJavaType);
        localObject2 = localObject1;
        if (localJavaType != null)
        {
          localObject2 = localObject1;
          if (localJavaType.getRawClass() != paramJavaType.getRawClass()) {
            localObject2 = ((TypeResolverBuilder)localObject1).defaultImpl(localJavaType.getRawClass());
          }
        }
      }
    }
    return ((TypeResolverBuilder)localObject2).buildTypeDeserializer(paramDeserializationConfig, paramJavaType, localCollection);
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject2 = null;
    Object localObject1 = paramBeanDescription.getClassInfo();
    Object localObject3 = paramDeserializationContext.getAnnotationIntrospector().findValueInstantiator((AnnotatedClass)localObject1);
    if (localObject3 != null) {
      localObject2 = _valueInstantiatorInstance(localDeserializationConfig, (Annotated)localObject1, localObject3);
    }
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = _findStdValueInstantiator(localDeserializationConfig, paramBeanDescription);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = _constructDefaultValueInstantiator(paramDeserializationContext, paramBeanDescription);
      }
    }
    localObject2 = localObject1;
    if (this._factoryConfig.hasValueInstantiators())
    {
      localObject3 = this._factoryConfig.valueInstantiators().iterator();
      ValueInstantiators localValueInstantiators;
      do
      {
        localObject2 = localObject1;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        localValueInstantiators = (ValueInstantiators)((Iterator)localObject3).next();
        localObject2 = localValueInstantiators.findValueInstantiator(localDeserializationConfig, paramBeanDescription, (ValueInstantiator)localObject1);
        localObject1 = localObject2;
      } while (localObject2 != null);
      throw JsonMappingException.from(paramDeserializationContext.getParser(), "Broken registered ValueInstantiators (of type " + localValueInstantiators.getClass().getName() + "): returned null ValueInstantiator");
    }
    if (((ValueInstantiator)localObject2).getIncompleteParameter() != null)
    {
      paramDeserializationContext = ((ValueInstantiator)localObject2).getIncompleteParameter();
      paramBeanDescription = paramDeserializationContext.getOwner();
      throw new IllegalArgumentException("Argument #" + paramDeserializationContext.getIndex() + " of constructor " + paramBeanDescription + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
    }
    return (ValueInstantiator)localObject2;
  }
  
  public DeserializerFactoryConfig getFactoryConfig()
  {
    return this._factoryConfig;
  }
  
  public JavaType mapAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    for (;;)
    {
      JavaType localJavaType = _mapAbstractType2(paramDeserializationConfig, paramJavaType);
      if (localJavaType == null) {
        return paramJavaType;
      }
      Class localClass1 = paramJavaType.getRawClass();
      Class localClass2 = localJavaType.getRawClass();
      if ((localClass1 == localClass2) || (!localClass1.isAssignableFrom(localClass2))) {
        throw new IllegalArgumentException("Invalid abstract type resolution from " + paramJavaType + " to " + localJavaType + ": latter is not a subtype of former");
      }
      paramJavaType = localJavaType;
    }
  }
  
  protected <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, T paramT)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    if (localAnnotationIntrospector == null) {
      return paramT;
    }
    Object localObject1 = paramT;
    if (paramT.isMapLikeType())
    {
      localObject2 = paramT.getKeyType();
      localObject1 = paramT;
      if (localObject2 != null)
      {
        localObject1 = paramT;
        if (((JavaType)localObject2).getValueHandler() == null)
        {
          localObject2 = paramDeserializationContext.keyDeserializerInstance(paramAnnotated, localAnnotationIntrospector.findKeyDeserializer(paramAnnotated));
          localObject1 = paramT;
          if (localObject2 != null)
          {
            localObject1 = ((MapLikeType)paramT).withKeyValueHandler(localObject2);
            ((JavaType)localObject1).getKeyType();
          }
        }
      }
    }
    Object localObject2 = ((JavaType)localObject1).getContentType();
    paramT = (T)localObject1;
    if (localObject2 != null)
    {
      paramT = (T)localObject1;
      if (((JavaType)localObject2).getValueHandler() == null)
      {
        localObject2 = paramDeserializationContext.deserializerInstance(paramAnnotated, localAnnotationIntrospector.findContentDeserializer(paramAnnotated));
        paramT = (T)localObject1;
        if (localObject2 != null) {
          paramT = ((JavaType)localObject1).withContentValueHandler(localObject2);
        }
      }
    }
    return localAnnotationIntrospector.refineDeserializationType(paramDeserializationContext.getConfig(), paramAnnotated, paramT);
  }
  
  protected JavaType resolveType(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    if (localAnnotationIntrospector == null) {
      return paramJavaType;
    }
    Object localObject = paramJavaType;
    if (paramJavaType.isMapLikeType())
    {
      localObject = paramJavaType;
      if (paramJavaType.getKeyType() != null)
      {
        paramBeanDescription = paramDeserializationContext.keyDeserializerInstance(paramAnnotatedMember, localAnnotationIntrospector.findKeyDeserializer(paramAnnotatedMember));
        localObject = paramJavaType;
        if (paramBeanDescription != null)
        {
          localObject = ((MapLikeType)paramJavaType).withKeyValueHandler(paramBeanDescription);
          ((JavaType)localObject).getKeyType();
        }
      }
    }
    paramBeanDescription = (BeanDescription)localObject;
    if (((JavaType)localObject).getContentType() != null)
    {
      paramBeanDescription = paramDeserializationContext.deserializerInstance(paramAnnotatedMember, localAnnotationIntrospector.findContentDeserializer(paramAnnotatedMember));
      paramJavaType = (JavaType)localObject;
      if (paramBeanDescription != null) {
        paramJavaType = ((JavaType)localObject).withContentValueHandler(paramBeanDescription);
      }
      paramBeanDescription = paramJavaType;
      if ((paramAnnotatedMember instanceof AnnotatedMember))
      {
        localObject = findPropertyContentTypeDeserializer(paramDeserializationContext.getConfig(), paramJavaType, paramAnnotatedMember);
        paramBeanDescription = paramJavaType;
        if (localObject != null) {
          paramBeanDescription = paramJavaType.withContentTypeHandler(localObject);
        }
      }
    }
    if ((paramAnnotatedMember instanceof AnnotatedMember)) {}
    for (paramDeserializationContext = findPropertyTypeDeserializer(paramDeserializationContext.getConfig(), paramBeanDescription, paramAnnotatedMember);; paramDeserializationContext = findTypeDeserializer(paramDeserializationContext.getConfig(), paramBeanDescription))
    {
      paramJavaType = paramBeanDescription;
      if (paramDeserializationContext != null) {
        paramJavaType = paramBeanDescription.withTypeHandler(paramDeserializationContext);
      }
      return paramJavaType;
    }
  }
  
  public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver)
  {
    return withConfig(this._factoryConfig.withAbstractTypeResolver(paramAbstractTypeResolver));
  }
  
  public final DeserializerFactory withAdditionalDeserializers(Deserializers paramDeserializers)
  {
    return withConfig(this._factoryConfig.withAdditionalDeserializers(paramDeserializers));
  }
  
  public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers)
  {
    return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(paramKeyDeserializers));
  }
  
  protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig paramDeserializerFactoryConfig);
  
  public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier)
  {
    return withConfig(this._factoryConfig.withDeserializerModifier(paramBeanDeserializerModifier));
  }
  
  public final DeserializerFactory withValueInstantiators(ValueInstantiators paramValueInstantiators)
  {
    return withConfig(this._factoryConfig.withValueInstantiators(paramValueInstantiators));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\BasicDeserializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */