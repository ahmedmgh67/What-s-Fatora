package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public final class TypeFactory
  implements Serializable
{
  private static final Class<?> CLS_BOOL;
  private static final Class<?> CLS_CLASS;
  private static final Class<?> CLS_COMPARABLE;
  private static final Class<?> CLS_ENUM;
  private static final Class<?> CLS_INT;
  private static final Class<?> CLS_LONG;
  private static final Class<?> CLS_OBJECT;
  private static final Class<?> CLS_STRING;
  protected static final SimpleType CORE_TYPE_BOOL;
  protected static final SimpleType CORE_TYPE_CLASS = new SimpleType(CLS_CLASS);
  protected static final SimpleType CORE_TYPE_COMPARABLE;
  protected static final SimpleType CORE_TYPE_ENUM;
  protected static final SimpleType CORE_TYPE_INT;
  protected static final SimpleType CORE_TYPE_LONG;
  protected static final SimpleType CORE_TYPE_OBJECT;
  protected static final SimpleType CORE_TYPE_STRING;
  protected static final TypeBindings EMPTY_BINDINGS;
  private static final JavaType[] NO_TYPES = new JavaType[0];
  protected static final TypeFactory instance = new TypeFactory();
  private static final long serialVersionUID = 1L;
  protected final ClassLoader _classLoader;
  protected final TypeModifier[] _modifiers;
  protected final TypeParser _parser;
  protected final LRUMap<Class<?>, JavaType> _typeCache = new LRUMap(16, 100);
  
  static
  {
    EMPTY_BINDINGS = TypeBindings.emptyBindings();
    CLS_STRING = String.class;
    CLS_OBJECT = Object.class;
    CLS_COMPARABLE = Comparable.class;
    CLS_CLASS = Class.class;
    CLS_ENUM = Enum.class;
    CLS_BOOL = Boolean.TYPE;
    CLS_INT = Integer.TYPE;
    CLS_LONG = Long.TYPE;
    CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
    CORE_TYPE_INT = new SimpleType(CLS_INT);
    CORE_TYPE_LONG = new SimpleType(CLS_LONG);
    CORE_TYPE_STRING = new SimpleType(CLS_STRING);
    CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
    CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
    CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
  }
  
  private TypeFactory()
  {
    this._parser = new TypeParser(this);
    this._modifiers = null;
    this._classLoader = null;
  }
  
  protected TypeFactory(TypeParser paramTypeParser, TypeModifier[] paramArrayOfTypeModifier)
  {
    this(paramTypeParser, paramArrayOfTypeModifier, null);
  }
  
  protected TypeFactory(TypeParser paramTypeParser, TypeModifier[] paramArrayOfTypeModifier, ClassLoader paramClassLoader)
  {
    this._parser = paramTypeParser.withFactory(this);
    this._modifiers = paramArrayOfTypeModifier;
    this._classLoader = paramClassLoader;
  }
  
  private JavaType _collectionType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    Object localObject = paramTypeBindings.getTypeParameters();
    if (((List)localObject).isEmpty()) {}
    for (localObject = _unknownType();; localObject = (JavaType)((List)localObject).get(0))
    {
      return CollectionType.construct(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, (JavaType)localObject);
      if (((List)localObject).size() != 1) {
        break;
      }
    }
    throw new IllegalArgumentException("Strange Collection type " + paramClass.getName() + ": can not determine type parameters");
  }
  
  private JavaType _mapType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    Object localObject2;
    Object localObject1;
    if (paramClass == Properties.class)
    {
      localObject2 = CORE_TYPE_STRING;
      localObject1 = localObject2;
    }
    for (;;)
    {
      return MapType.construct(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, (JavaType)localObject1, (JavaType)localObject2);
      localObject2 = paramTypeBindings.getTypeParameters();
      switch (((List)localObject2).size())
      {
      case 1: 
      default: 
        throw new IllegalArgumentException("Strange Map type " + paramClass.getName() + ": can not determine type parameters");
      case 0: 
        localObject2 = _unknownType();
        localObject1 = localObject2;
        break;
      case 2: 
        localObject1 = (JavaType)((List)localObject2).get(0);
        localObject2 = (JavaType)((List)localObject2).get(1);
      }
    }
  }
  
  private JavaType _referenceType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    Object localObject = paramTypeBindings.getTypeParameters();
    if (((List)localObject).isEmpty()) {}
    for (localObject = _unknownType();; localObject = (JavaType)((List)localObject).get(0))
    {
      return ReferenceType.construct(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, (JavaType)localObject);
      if (((List)localObject).size() != 1) {
        break;
      }
    }
    throw new IllegalArgumentException("Strange Reference type " + paramClass.getName() + ": can not determine type parameters");
  }
  
  public static TypeFactory defaultInstance()
  {
    return instance;
  }
  
  public static Class<?> rawClass(Type paramType)
  {
    if ((paramType instanceof Class)) {
      return (Class)paramType;
    }
    return defaultInstance().constructType(paramType).getRawClass();
  }
  
  public static JavaType unknownType()
  {
    return defaultInstance()._unknownType();
  }
  
  protected JavaType _constructSimple(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    if (paramTypeBindings.isEmpty())
    {
      JavaType localJavaType = _findWellKnownSimple(paramClass);
      if (localJavaType != null) {
        return localJavaType;
      }
    }
    return _newSimpleType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType);
  }
  
  protected Class<?> _findPrimitive(String paramString)
  {
    if ("int".equals(paramString)) {
      return Integer.TYPE;
    }
    if ("long".equals(paramString)) {
      return Long.TYPE;
    }
    if ("float".equals(paramString)) {
      return Float.TYPE;
    }
    if ("double".equals(paramString)) {
      return Double.TYPE;
    }
    if ("boolean".equals(paramString)) {
      return Boolean.TYPE;
    }
    if ("byte".equals(paramString)) {
      return Byte.TYPE;
    }
    if ("char".equals(paramString)) {
      return Character.TYPE;
    }
    if ("short".equals(paramString)) {
      return Short.TYPE;
    }
    if ("void".equals(paramString)) {
      return Void.TYPE;
    }
    return null;
  }
  
  protected JavaType _findWellKnownSimple(Class<?> paramClass)
  {
    if (paramClass.isPrimitive())
    {
      if (paramClass == CLS_BOOL) {
        return CORE_TYPE_BOOL;
      }
      if (paramClass == CLS_INT) {
        return CORE_TYPE_INT;
      }
      if (paramClass == CLS_LONG) {
        return CORE_TYPE_LONG;
      }
    }
    else
    {
      if (paramClass == CLS_STRING) {
        return CORE_TYPE_STRING;
      }
      if (paramClass == CLS_OBJECT) {
        return CORE_TYPE_OBJECT;
      }
    }
    return null;
  }
  
  protected JavaType _fromAny(ClassStack paramClassStack, Type paramType, TypeBindings paramTypeBindings)
  {
    Object localObject;
    TypeModifier[] arrayOfTypeModifier;
    int j;
    int i;
    if ((paramType instanceof Class))
    {
      paramClassStack = _fromClass(paramClassStack, (Class)paramType, EMPTY_BINDINGS);
      localObject = paramClassStack;
      if (this._modifiers != null)
      {
        localObject = paramClassStack.getBindings();
        paramTypeBindings = (TypeBindings)localObject;
        if (localObject == null) {
          paramTypeBindings = EMPTY_BINDINGS;
        }
        arrayOfTypeModifier = this._modifiers;
        j = arrayOfTypeModifier.length;
        i = 0;
      }
    }
    else
    {
      for (;;)
      {
        localObject = paramClassStack;
        if (i >= j) {
          break label287;
        }
        TypeModifier localTypeModifier = arrayOfTypeModifier[i];
        localObject = localTypeModifier.modifyType(paramClassStack, paramType, paramTypeBindings, this);
        if (localObject == null)
        {
          throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", new Object[] { localTypeModifier, localTypeModifier.getClass().getName(), paramClassStack }));
          if ((paramType instanceof ParameterizedType))
          {
            paramClassStack = _fromParamType(paramClassStack, (ParameterizedType)paramType, paramTypeBindings);
            break;
          }
          if ((paramType instanceof JavaType)) {
            return (JavaType)paramType;
          }
          if ((paramType instanceof GenericArrayType))
          {
            paramClassStack = _fromArrayType(paramClassStack, (GenericArrayType)paramType, paramTypeBindings);
            break;
          }
          if ((paramType instanceof TypeVariable))
          {
            paramClassStack = _fromVariable(paramClassStack, (TypeVariable)paramType, paramTypeBindings);
            break;
          }
          if ((paramType instanceof WildcardType))
          {
            paramClassStack = _fromWildcard(paramClassStack, (WildcardType)paramType, paramTypeBindings);
            break;
          }
          paramTypeBindings = new StringBuilder().append("Unrecognized Type: ");
          if (paramType == null) {}
          for (paramClassStack = "[null]";; paramClassStack = paramType.toString()) {
            throw new IllegalArgumentException(paramClassStack);
          }
        }
        paramClassStack = (ClassStack)localObject;
        i += 1;
      }
    }
    label287:
    return (JavaType)localObject;
  }
  
  protected JavaType _fromArrayType(ClassStack paramClassStack, GenericArrayType paramGenericArrayType, TypeBindings paramTypeBindings)
  {
    return ArrayType.construct(_fromAny(paramClassStack, paramGenericArrayType.getGenericComponentType(), paramTypeBindings), paramTypeBindings);
  }
  
  protected JavaType _fromClass(ClassStack paramClassStack, Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    Object localObject1 = _findWellKnownSimple(paramClass);
    if (localObject1 != null) {
      return (JavaType)localObject1;
    }
    if ((paramTypeBindings == null) || (paramTypeBindings.isEmpty())) {}
    for (int i = 1; i != 0; i = 0)
    {
      localObject2 = (JavaType)this._typeCache.get(paramClass);
      localObject1 = localObject2;
      if (localObject2 == null) {
        break;
      }
      return (JavaType)localObject2;
    }
    if (paramClassStack == null) {}
    for (Object localObject2 = new ClassStack(paramClass); paramClass.isArray(); localObject2 = paramClassStack.child(paramClass))
    {
      paramClassStack = ArrayType.construct(_fromAny((ClassStack)localObject2, paramClass.getComponentType(), paramTypeBindings), paramTypeBindings);
      ((ClassStack)localObject2).resolveSelfReferences(paramClassStack);
      if (i != 0) {
        this._typeCache.putIfAbsent(paramClass, paramClassStack);
      }
      return paramClassStack;
      localObject2 = paramClassStack.find(paramClass);
      if (localObject2 != null)
      {
        paramClassStack = new ResolvedRecursiveType(paramClass, EMPTY_BINDINGS);
        ((ClassStack)localObject2).addSelfReference(paramClassStack);
        return paramClassStack;
      }
    }
    JavaType localJavaType;
    JavaType[] arrayOfJavaType;
    if (paramClass.isInterface())
    {
      localJavaType = null;
      arrayOfJavaType = _resolveSuperInterfaces((ClassStack)localObject2, paramClass, paramTypeBindings);
      label187:
      if (paramClass != Properties.class) {
        break label299;
      }
      localObject1 = MapType.construct(paramClass, paramTypeBindings, localJavaType, arrayOfJavaType, CORE_TYPE_STRING, CORE_TYPE_STRING);
    }
    for (;;)
    {
      paramClassStack = (ClassStack)localObject1;
      if (localObject1 != null) {
        break;
      }
      localObject1 = _fromWellKnownClass((ClassStack)localObject2, paramClass, paramTypeBindings, localJavaType, arrayOfJavaType);
      paramClassStack = (ClassStack)localObject1;
      if (localObject1 != null) {
        break;
      }
      localObject1 = _fromWellKnownInterface((ClassStack)localObject2, paramClass, paramTypeBindings, localJavaType, arrayOfJavaType);
      paramClassStack = (ClassStack)localObject1;
      if (localObject1 != null) {
        break;
      }
      paramClassStack = _newSimpleType(paramClass, paramTypeBindings, localJavaType, arrayOfJavaType);
      break;
      localJavaType = _resolveSuperClass((ClassStack)localObject2, paramClass, paramTypeBindings);
      arrayOfJavaType = _resolveSuperInterfaces((ClassStack)localObject2, paramClass, paramTypeBindings);
      break label187;
      label299:
      if (localJavaType != null) {
        localObject1 = localJavaType.refine(paramClass, paramTypeBindings, localJavaType, arrayOfJavaType);
      }
    }
  }
  
  protected JavaType _fromParamType(ClassStack paramClassStack, ParameterizedType paramParameterizedType, TypeBindings paramTypeBindings)
  {
    Class localClass = (Class)paramParameterizedType.getRawType();
    if (localClass == CLS_ENUM) {
      return CORE_TYPE_ENUM;
    }
    if (localClass == CLS_COMPARABLE) {
      return CORE_TYPE_COMPARABLE;
    }
    if (localClass == CLS_CLASS) {
      return CORE_TYPE_CLASS;
    }
    paramParameterizedType = paramParameterizedType.getActualTypeArguments();
    int i;
    if (paramParameterizedType == null)
    {
      i = 0;
      if (i != 0) {
        break label86;
      }
    }
    label86:
    JavaType[] arrayOfJavaType;
    for (paramParameterizedType = EMPTY_BINDINGS;; paramParameterizedType = TypeBindings.create(localClass, arrayOfJavaType))
    {
      return _fromClass(paramClassStack, localClass, paramParameterizedType);
      i = paramParameterizedType.length;
      break;
      arrayOfJavaType = new JavaType[i];
      int j = 0;
      while (j < i)
      {
        arrayOfJavaType[j] = _fromAny(paramClassStack, paramParameterizedType[j], paramTypeBindings);
        j += 1;
      }
    }
  }
  
  protected JavaType _fromVariable(ClassStack paramClassStack, TypeVariable<?> paramTypeVariable, TypeBindings paramTypeBindings)
  {
    String str = paramTypeVariable.getName();
    JavaType localJavaType = paramTypeBindings.findBoundType(str);
    if (localJavaType != null) {
      return localJavaType;
    }
    if (paramTypeBindings.hasUnbound(str)) {
      return CORE_TYPE_OBJECT;
    }
    paramTypeBindings = paramTypeBindings.withUnboundVariable(str);
    return _fromAny(paramClassStack, paramTypeVariable.getBounds()[0], paramTypeBindings);
  }
  
  protected JavaType _fromWellKnownClass(ClassStack paramClassStack, Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    if (paramClass == Map.class) {
      return _mapType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType);
    }
    if (paramClass == Collection.class) {
      return _collectionType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType);
    }
    if (paramClass == AtomicReference.class) {
      return _referenceType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType);
    }
    return null;
  }
  
  protected JavaType _fromWellKnownInterface(ClassStack paramClassStack, Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    int j = paramArrayOfJavaType.length;
    int i = 0;
    while (i < j)
    {
      paramClassStack = paramArrayOfJavaType[i].refine(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType);
      if (paramClassStack != null) {
        return paramClassStack;
      }
      i += 1;
    }
    return null;
  }
  
  protected JavaType _fromWildcard(ClassStack paramClassStack, WildcardType paramWildcardType, TypeBindings paramTypeBindings)
  {
    return _fromAny(paramClassStack, paramWildcardType.getUpperBounds()[0], paramTypeBindings);
  }
  
  protected JavaType _newSimpleType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return new SimpleType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType);
  }
  
  protected JavaType _resolveSuperClass(ClassStack paramClassStack, Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    paramClass = ClassUtil.getGenericSuperclass(paramClass);
    if (paramClass == null) {
      return null;
    }
    return _fromAny(paramClassStack, paramClass, paramTypeBindings);
  }
  
  protected JavaType[] _resolveSuperInterfaces(ClassStack paramClassStack, Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    Type[] arrayOfType = ClassUtil.getGenericInterfaces(paramClass);
    if ((arrayOfType == null) || (arrayOfType.length == 0))
    {
      paramClass = NO_TYPES;
      return paramClass;
    }
    int j = arrayOfType.length;
    JavaType[] arrayOfJavaType = new JavaType[j];
    int i = 0;
    for (;;)
    {
      paramClass = arrayOfJavaType;
      if (i >= j) {
        break;
      }
      arrayOfJavaType[i] = _fromAny(paramClassStack, arrayOfType[i], paramTypeBindings);
      i += 1;
    }
  }
  
  protected JavaType _unknownType()
  {
    return CORE_TYPE_OBJECT;
  }
  
  protected Class<?> classForName(String paramString)
    throws ClassNotFoundException
  {
    return Class.forName(paramString);
  }
  
  protected Class<?> classForName(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ClassNotFoundException
  {
    return Class.forName(paramString, true, paramClassLoader);
  }
  
  public void clearCache()
  {
    this._typeCache.clear();
  }
  
  public ArrayType constructArrayType(JavaType paramJavaType)
  {
    return ArrayType.construct(paramJavaType, null);
  }
  
  public ArrayType constructArrayType(Class<?> paramClass)
  {
    return ArrayType.construct(_fromAny(null, paramClass, null), null);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> paramClass, JavaType paramJavaType)
  {
    paramClass = _fromClass(null, paramClass, TypeBindings.createIfNeeded(paramClass, paramJavaType));
    if ((paramClass instanceof CollectionLikeType)) {
      return (CollectionLikeType)paramClass;
    }
    return CollectionLikeType.upgradeFrom(paramClass, paramJavaType);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> paramClass1, Class<?> paramClass2)
  {
    return constructCollectionLikeType(paramClass1, _fromClass(null, paramClass2, EMPTY_BINDINGS));
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> paramClass, JavaType paramJavaType)
  {
    return (CollectionType)_fromClass(null, paramClass, TypeBindings.create(paramClass, paramJavaType));
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> paramClass, Class<?> paramClass1)
  {
    return constructCollectionType(paramClass, _fromClass(null, paramClass1, EMPTY_BINDINGS));
  }
  
  public JavaType constructFromCanonical(String paramString)
    throws IllegalArgumentException
  {
    return this._parser.parse(paramString);
  }
  
  public JavaType constructGeneralizedType(JavaType paramJavaType, Class<?> paramClass)
  {
    Class localClass = paramJavaType.getRawClass();
    if (localClass == paramClass) {
      return paramJavaType;
    }
    JavaType localJavaType = paramJavaType.findSuperType(paramClass);
    if (localJavaType == null)
    {
      if (!paramClass.isAssignableFrom(localClass)) {
        throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", new Object[] { paramClass.getName(), paramJavaType }));
      }
      throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", new Object[] { paramClass.getName(), paramJavaType }));
    }
    return localJavaType;
  }
  
  public MapLikeType constructMapLikeType(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    paramClass = _fromClass(null, paramClass, TypeBindings.createIfNeeded(paramClass, new JavaType[] { paramJavaType1, paramJavaType2 }));
    if ((paramClass instanceof MapLikeType)) {
      return (MapLikeType)paramClass;
    }
    return MapLikeType.upgradeFrom(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public MapLikeType constructMapLikeType(Class<?> paramClass1, Class<?> paramClass2, Class<?> paramClass3)
  {
    return constructMapLikeType(paramClass1, _fromClass(null, paramClass2, EMPTY_BINDINGS), _fromClass(null, paramClass3, EMPTY_BINDINGS));
  }
  
  public MapType constructMapType(Class<? extends Map> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return (MapType)_fromClass(null, paramClass, TypeBindings.create(paramClass, new JavaType[] { paramJavaType1, paramJavaType2 }));
  }
  
  public MapType constructMapType(Class<? extends Map> paramClass, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass == Properties.class)
    {
      paramClass2 = CORE_TYPE_STRING;
      paramClass1 = paramClass2;
    }
    for (;;)
    {
      return constructMapType(paramClass, paramClass1, paramClass2);
      paramClass1 = _fromClass(null, paramClass1, EMPTY_BINDINGS);
      paramClass2 = _fromClass(null, paramClass2, EMPTY_BINDINGS);
    }
  }
  
  public JavaType constructParametricType(Class<?> paramClass, JavaType... paramVarArgs)
  {
    return _fromClass(null, paramClass, TypeBindings.create(paramClass, paramVarArgs));
  }
  
  public JavaType constructParametricType(Class<?> paramClass, Class<?>... paramVarArgs)
  {
    int j = paramVarArgs.length;
    JavaType[] arrayOfJavaType = new JavaType[j];
    int i = 0;
    while (i < j)
    {
      arrayOfJavaType[i] = _fromClass(null, paramVarArgs[i], null);
      i += 1;
    }
    return constructParametricType(paramClass, arrayOfJavaType);
  }
  
  public JavaType constructParametrizedType(Class<?> paramClass1, Class<?> paramClass2, JavaType... paramVarArgs)
  {
    return constructParametricType(paramClass1, paramVarArgs);
  }
  
  public JavaType constructParametrizedType(Class<?> paramClass1, Class<?> paramClass2, Class<?>... paramVarArgs)
  {
    return constructParametricType(paramClass1, paramVarArgs);
  }
  
  public CollectionLikeType constructRawCollectionLikeType(Class<?> paramClass)
  {
    return constructCollectionLikeType(paramClass, unknownType());
  }
  
  public CollectionType constructRawCollectionType(Class<? extends Collection> paramClass)
  {
    return constructCollectionType(paramClass, unknownType());
  }
  
  public MapLikeType constructRawMapLikeType(Class<?> paramClass)
  {
    return constructMapLikeType(paramClass, unknownType(), unknownType());
  }
  
  public MapType constructRawMapType(Class<? extends Map> paramClass)
  {
    return constructMapType(paramClass, unknownType(), unknownType());
  }
  
  public JavaType constructReferenceType(Class<?> paramClass, JavaType paramJavaType)
  {
    return ReferenceType.construct(paramClass, null, null, null, paramJavaType);
  }
  
  @Deprecated
  public JavaType constructSimpleType(Class<?> paramClass1, Class<?> paramClass2, JavaType[] paramArrayOfJavaType)
  {
    return constructSimpleType(paramClass1, paramArrayOfJavaType);
  }
  
  public JavaType constructSimpleType(Class<?> paramClass, JavaType[] paramArrayOfJavaType)
  {
    return _fromClass(null, paramClass, TypeBindings.create(paramClass, paramArrayOfJavaType));
  }
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass)
  {
    Object localObject = paramJavaType.getRawClass();
    if (localObject == paramClass) {
      return paramJavaType;
    }
    if (localObject == Object.class) {
      paramJavaType = _fromClass(null, paramClass, TypeBindings.emptyBindings());
    }
    for (;;)
    {
      return paramJavaType;
      if (!((Class)localObject).isAssignableFrom(paramClass)) {
        throw new IllegalArgumentException(String.format("Class %s not subtype of %s", new Object[] { paramClass.getName(), paramJavaType }));
      }
      if (paramJavaType.getBindings().isEmpty())
      {
        paramJavaType = _fromClass(null, paramClass, TypeBindings.emptyBindings());
      }
      else
      {
        if (paramJavaType.isContainerType()) {
          if (paramJavaType.isMapLikeType())
          {
            if ((paramClass == HashMap.class) || (paramClass == LinkedHashMap.class) || (paramClass == EnumMap.class) || (paramClass == TreeMap.class)) {
              paramJavaType = _fromClass(null, paramClass, TypeBindings.create(paramClass, paramJavaType.getKeyType(), paramJavaType.getContentType()));
            }
          }
          else if (paramJavaType.isCollectionLikeType())
          {
            if ((paramClass == ArrayList.class) || (paramClass == LinkedList.class) || (paramClass == HashSet.class) || (paramClass == TreeSet.class))
            {
              paramJavaType = _fromClass(null, paramClass, TypeBindings.create(paramClass, paramJavaType.getContentType()));
              continue;
            }
            if (localObject == EnumSet.class) {
              break;
            }
          }
        }
        if (paramClass.getTypeParameters().length != 0) {
          break label235;
        }
        paramJavaType = _fromClass(null, paramClass, TypeBindings.emptyBindings());
      }
    }
    label235:
    if (paramJavaType.isInterface()) {}
    for (localObject = paramJavaType.refine(paramClass, TypeBindings.emptyBindings(), null, new JavaType[] { paramJavaType });; localObject = paramJavaType.refine(paramClass, TypeBindings.emptyBindings(), paramJavaType, NO_TYPES))
    {
      paramJavaType = (JavaType)localObject;
      if (localObject != null) {
        break;
      }
      paramJavaType = _fromClass(null, paramClass, TypeBindings.emptyBindings());
      break;
    }
  }
  
  public JavaType constructType(TypeReference<?> paramTypeReference)
  {
    return _fromAny(null, paramTypeReference.getType(), EMPTY_BINDINGS);
  }
  
  public JavaType constructType(Type paramType)
  {
    return _fromAny(null, paramType, EMPTY_BINDINGS);
  }
  
  @Deprecated
  public JavaType constructType(Type paramType, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (paramJavaType = TypeBindings.emptyBindings();; paramJavaType = paramJavaType.getBindings()) {
      return _fromAny(null, paramType, paramJavaType);
    }
  }
  
  public JavaType constructType(Type paramType, TypeBindings paramTypeBindings)
  {
    return _fromAny(null, paramType, paramTypeBindings);
  }
  
  @Deprecated
  public JavaType constructType(Type paramType, Class<?> paramClass)
  {
    if (paramClass == null) {}
    for (paramClass = TypeBindings.emptyBindings();; paramClass = constructType(paramClass).getBindings()) {
      return _fromAny(null, paramType, paramClass);
    }
  }
  
  public Class<?> findClass(String paramString)
    throws ClassNotFoundException
  {
    if (paramString.indexOf('.') < 0)
    {
      localClass = _findPrimitive(paramString);
      if (localClass != null) {
        return localClass;
      }
    }
    Class localClass = null;
    ClassLoader localClassLoader2 = getClassLoader();
    ClassLoader localClassLoader1 = localClassLoader2;
    if (localClassLoader2 == null) {
      localClassLoader1 = Thread.currentThread().getContextClassLoader();
    }
    Throwable localThrowable;
    if (localClassLoader1 != null) {
      try
      {
        localClass = classForName(paramString, true, localClassLoader1);
        return localClass;
      }
      catch (Exception localException1)
      {
        localThrowable = ClassUtil.getRootCause(localException1);
      }
    }
    try
    {
      paramString = classForName(paramString);
      return paramString;
    }
    catch (Exception localException2)
    {
      paramString = localThrowable;
      if (localThrowable == null) {
        paramString = ClassUtil.getRootCause(localException2);
      }
      if ((paramString instanceof RuntimeException)) {
        throw ((RuntimeException)paramString);
      }
      throw new ClassNotFoundException(paramString.getMessage(), paramString);
    }
  }
  
  public JavaType[] findTypeParameters(JavaType paramJavaType, Class<?> paramClass)
  {
    paramJavaType = paramJavaType.findSuperType(paramClass);
    if (paramJavaType == null) {
      return NO_TYPES;
    }
    return paramJavaType.getBindings().typeParameterArray();
  }
  
  @Deprecated
  public JavaType[] findTypeParameters(Class<?> paramClass1, Class<?> paramClass2)
  {
    return findTypeParameters(constructType(paramClass1), paramClass2);
  }
  
  @Deprecated
  public JavaType[] findTypeParameters(Class<?> paramClass1, Class<?> paramClass2, TypeBindings paramTypeBindings)
  {
    return findTypeParameters(constructType(paramClass1, paramTypeBindings), paramClass2);
  }
  
  public ClassLoader getClassLoader()
  {
    return this._classLoader;
  }
  
  public JavaType moreSpecificType(JavaType paramJavaType1, JavaType paramJavaType2)
  {
    JavaType localJavaType;
    if (paramJavaType1 == null) {
      localJavaType = paramJavaType2;
    }
    Class localClass1;
    Class localClass2;
    do
    {
      do
      {
        do
        {
          return localJavaType;
          localJavaType = paramJavaType1;
        } while (paramJavaType2 == null);
        localClass1 = paramJavaType1.getRawClass();
        localClass2 = paramJavaType2.getRawClass();
        localJavaType = paramJavaType1;
      } while (localClass1 == localClass2);
      localJavaType = paramJavaType1;
    } while (!localClass1.isAssignableFrom(localClass2));
    return paramJavaType2;
  }
  
  public JavaType uncheckedSimpleType(Class<?> paramClass)
  {
    return _constructSimple(paramClass, EMPTY_BINDINGS, null, null);
  }
  
  public TypeFactory withClassLoader(ClassLoader paramClassLoader)
  {
    return new TypeFactory(this._parser, this._modifiers, paramClassLoader);
  }
  
  public TypeFactory withModifier(TypeModifier paramTypeModifier)
  {
    if (paramTypeModifier == null) {
      return new TypeFactory(this._parser, this._modifiers, this._classLoader);
    }
    if (this._modifiers == null)
    {
      TypeParser localTypeParser = this._parser;
      ClassLoader localClassLoader = this._classLoader;
      return new TypeFactory(localTypeParser, new TypeModifier[] { paramTypeModifier }, localClassLoader);
    }
    return new TypeFactory(this._parser, (TypeModifier[])ArrayBuilders.insertInListNoDup(this._modifiers, paramTypeModifier), this._classLoader);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\TypeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */