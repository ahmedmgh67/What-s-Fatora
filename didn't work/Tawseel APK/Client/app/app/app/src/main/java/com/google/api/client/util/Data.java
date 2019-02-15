package com.google.api.client.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Data
{
  public static final BigDecimal NULL_BIG_DECIMAL;
  public static final BigInteger NULL_BIG_INTEGER;
  public static final Boolean NULL_BOOLEAN = new Boolean(true);
  public static final Byte NULL_BYTE;
  private static final ConcurrentHashMap<Class<?>, Object> NULL_CACHE;
  public static final Character NULL_CHARACTER;
  public static final DateTime NULL_DATE_TIME;
  public static final Double NULL_DOUBLE;
  public static final Float NULL_FLOAT;
  public static final Integer NULL_INTEGER;
  public static final Long NULL_LONG;
  public static final Short NULL_SHORT;
  public static final String NULL_STRING = new String();
  
  static
  {
    NULL_CHARACTER = new Character('\000');
    NULL_BYTE = new Byte((byte)0);
    NULL_SHORT = new Short((short)0);
    NULL_INTEGER = new Integer(0);
    NULL_FLOAT = new Float(0.0F);
    NULL_LONG = new Long(0L);
    NULL_DOUBLE = new Double(0.0D);
    NULL_BIG_INTEGER = new BigInteger("0");
    NULL_BIG_DECIMAL = new BigDecimal("0");
    NULL_DATE_TIME = new DateTime(0L);
    NULL_CACHE = new ConcurrentHashMap();
    NULL_CACHE.put(Boolean.class, NULL_BOOLEAN);
    NULL_CACHE.put(String.class, NULL_STRING);
    NULL_CACHE.put(Character.class, NULL_CHARACTER);
    NULL_CACHE.put(Byte.class, NULL_BYTE);
    NULL_CACHE.put(Short.class, NULL_SHORT);
    NULL_CACHE.put(Integer.class, NULL_INTEGER);
    NULL_CACHE.put(Float.class, NULL_FLOAT);
    NULL_CACHE.put(Long.class, NULL_LONG);
    NULL_CACHE.put(Double.class, NULL_DOUBLE);
    NULL_CACHE.put(BigInteger.class, NULL_BIG_INTEGER);
    NULL_CACHE.put(BigDecimal.class, NULL_BIG_DECIMAL);
    NULL_CACHE.put(DateTime.class, NULL_DATE_TIME);
  }
  
  public static <T> T clone(T paramT)
  {
    if ((paramT == null) || (isPrimitive(paramT.getClass()))) {
      return paramT;
    }
    if ((paramT instanceof GenericData)) {
      return ((GenericData)paramT).clone();
    }
    Object localObject = paramT.getClass();
    if (((Class)localObject).isArray()) {
      localObject = Array.newInstance(((Class)localObject).getComponentType(), Array.getLength(paramT));
    }
    for (;;)
    {
      deepCopy(paramT, localObject);
      return (T)localObject;
      if ((paramT instanceof ArrayMap))
      {
        localObject = ((ArrayMap)paramT).clone();
      }
      else
      {
        if ("java.util.Arrays$ArrayList".equals(((Class)localObject).getName()))
        {
          paramT = ((List)paramT).toArray();
          deepCopy(paramT, paramT);
          return Arrays.asList(paramT);
        }
        localObject = Types.newInstance((Class)localObject);
      }
    }
  }
  
  public static void deepCopy(Object paramObject1, Object paramObject2)
  {
    Object localObject1 = paramObject1.getClass();
    if (localObject1 == paramObject2.getClass())
    {
      bool = true;
      Preconditions.checkArgument(bool);
      if (!((Class)localObject1).isArray()) {
        break label104;
      }
      if (Array.getLength(paramObject1) != Array.getLength(paramObject2)) {
        break label98;
      }
    }
    int i;
    label98:
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      i = 0;
      paramObject1 = Types.iterableOf(paramObject1).iterator();
      while (((Iterator)paramObject1).hasNext())
      {
        Array.set(paramObject2, i, clone(((Iterator)paramObject1).next()));
        i += 1;
      }
      bool = false;
      break;
    }
    label104:
    if (Collection.class.isAssignableFrom((Class)localObject1))
    {
      paramObject1 = (Collection)paramObject1;
      if (ArrayList.class.isAssignableFrom((Class)localObject1)) {
        ((ArrayList)paramObject2).ensureCapacity(((Collection)paramObject1).size());
      }
      paramObject2 = (Collection)paramObject2;
      paramObject1 = ((Collection)paramObject1).iterator();
      while (((Iterator)paramObject1).hasNext()) {
        ((Collection)paramObject2).add(clone(((Iterator)paramObject1).next()));
      }
    }
    bool = GenericData.class.isAssignableFrom((Class)localObject1);
    if ((bool) || (!Map.class.isAssignableFrom((Class)localObject1)))
    {
      if (bool) {}
      for (localObject1 = ((GenericData)paramObject1).classInfo;; localObject1 = ClassInfo.of((Class)localObject1))
      {
        Iterator localIterator = ((ClassInfo)localObject1).names.iterator();
        while (localIterator.hasNext())
        {
          FieldInfo localFieldInfo = ((ClassInfo)localObject1).getFieldInfo((String)localIterator.next());
          if ((!localFieldInfo.isFinal()) && ((!bool) || (!localFieldInfo.isPrimitive())))
          {
            Object localObject2 = localFieldInfo.getValue(paramObject1);
            if (localObject2 != null) {
              localFieldInfo.setValue(paramObject2, clone(localObject2));
            }
          }
        }
      }
    }
    if (ArrayMap.class.isAssignableFrom((Class)localObject1))
    {
      paramObject2 = (ArrayMap)paramObject2;
      paramObject1 = (ArrayMap)paramObject1;
      int j = ((ArrayMap)paramObject1).size();
      i = 0;
      while (i < j)
      {
        ((ArrayMap)paramObject2).set(i, clone(((ArrayMap)paramObject1).getValue(i)));
        i += 1;
      }
    }
    paramObject2 = (Map)paramObject2;
    paramObject1 = ((Map)paramObject1).entrySet().iterator();
    while (((Iterator)paramObject1).hasNext())
    {
      localObject1 = (Map.Entry)((Iterator)paramObject1).next();
      ((Map)paramObject2).put(((Map.Entry)localObject1).getKey(), clone(((Map.Entry)localObject1).getValue()));
    }
  }
  
  public static boolean isNull(Object paramObject)
  {
    return (paramObject != null) && (paramObject == NULL_CACHE.get(paramObject.getClass()));
  }
  
  public static boolean isPrimitive(Type paramType)
  {
    Type localType = paramType;
    if ((paramType instanceof WildcardType)) {
      localType = Types.getBound((WildcardType)paramType);
    }
    if (!(localType instanceof Class)) {}
    do
    {
      return false;
      paramType = (Class)localType;
    } while ((!paramType.isPrimitive()) && (paramType != Character.class) && (paramType != String.class) && (paramType != Integer.class) && (paramType != Long.class) && (paramType != Short.class) && (paramType != Byte.class) && (paramType != Float.class) && (paramType != Double.class) && (paramType != BigInteger.class) && (paramType != BigDecimal.class) && (paramType != DateTime.class) && (paramType != Boolean.class));
    return true;
  }
  
  public static boolean isValueOfPrimitiveType(Object paramObject)
  {
    return (paramObject == null) || (isPrimitive(paramObject.getClass()));
  }
  
  public static Map<String, Object> mapOf(Object paramObject)
  {
    if ((paramObject == null) || (isNull(paramObject))) {
      return Collections.emptyMap();
    }
    if ((paramObject instanceof Map)) {
      return (Map)paramObject;
    }
    return new DataMap(paramObject, false);
  }
  
  public static Collection<Object> newCollectionInstance(Type paramType)
  {
    Object localObject = paramType;
    if ((paramType instanceof WildcardType)) {
      localObject = Types.getBound((WildcardType)paramType);
    }
    paramType = (Type)localObject;
    if ((localObject instanceof ParameterizedType)) {
      paramType = ((ParameterizedType)localObject).getRawType();
    }
    if ((paramType instanceof Class)) {}
    for (localObject = (Class)paramType; (paramType == null) || ((paramType instanceof GenericArrayType)) || ((localObject != null) && ((((Class)localObject).isArray()) || (((Class)localObject).isAssignableFrom(ArrayList.class)))); localObject = null) {
      return new ArrayList();
    }
    if (localObject == null) {
      throw new IllegalArgumentException("unable to create new instance of type: " + paramType);
    }
    if (((Class)localObject).isAssignableFrom(HashSet.class)) {
      return new HashSet();
    }
    if (((Class)localObject).isAssignableFrom(TreeSet.class)) {
      return new TreeSet();
    }
    return (Collection)Types.newInstance((Class)localObject);
  }
  
  public static Map<String, Object> newMapInstance(Class<?> paramClass)
  {
    if ((paramClass == null) || (paramClass.isAssignableFrom(ArrayMap.class))) {
      return ArrayMap.create();
    }
    if (paramClass.isAssignableFrom(TreeMap.class)) {
      return new TreeMap();
    }
    return (Map)Types.newInstance(paramClass);
  }
  
  public static <T> T nullOf(Class<?> paramClass)
  {
    Object localObject = NULL_CACHE.get(paramClass);
    if (localObject == null) {
      for (;;)
      {
        synchronized (NULL_CACHE)
        {
          localObject = NULL_CACHE.get(paramClass);
          if (localObject == null)
          {
            if (paramClass.isArray())
            {
              int i = 0;
              localObject = paramClass;
              Class localClass = ((Class)localObject).getComponentType();
              int j = i + 1;
              localObject = localClass;
              i = j;
              if (localClass.isArray()) {
                continue;
              }
              localObject = Array.newInstance(localClass, new int[j]);
              NULL_CACHE.put(paramClass, localObject);
              return (T)localObject;
            }
            if (paramClass.isEnum())
            {
              localObject = ClassInfo.of(paramClass).getFieldInfo(null);
              Preconditions.checkNotNull(localObject, "enum missing constant with @NullValue annotation: %s", new Object[] { paramClass });
              localObject = ((FieldInfo)localObject).enumValue();
            }
            else
            {
              localObject = Types.newInstance(paramClass);
            }
          }
        }
      }
    }
    return (T)localObject;
  }
  
  public static Object parsePrimitiveValue(Type paramType, String paramString)
  {
    Class localClass;
    if ((paramType instanceof Class)) {
      localClass = (Class)paramType;
    }
    while ((paramType == null) || (localClass != null)) {
      if (localClass == Void.class)
      {
        return null;
        localClass = null;
      }
      else
      {
        if ((paramString == null) || (localClass == null) || (localClass.isAssignableFrom(String.class))) {
          return paramString;
        }
        if ((localClass == Character.class) || (localClass == Character.TYPE))
        {
          if (paramString.length() != 1) {
            throw new IllegalArgumentException("expected type Character/char but got " + localClass);
          }
          return Character.valueOf(paramString.charAt(0));
        }
        if ((localClass == Boolean.class) || (localClass == Boolean.TYPE)) {
          return Boolean.valueOf(paramString);
        }
        if ((localClass == Byte.class) || (localClass == Byte.TYPE)) {
          return Byte.valueOf(paramString);
        }
        if ((localClass == Short.class) || (localClass == Short.TYPE)) {
          return Short.valueOf(paramString);
        }
        if ((localClass == Integer.class) || (localClass == Integer.TYPE)) {
          return Integer.valueOf(paramString);
        }
        if ((localClass == Long.class) || (localClass == Long.TYPE)) {
          return Long.valueOf(paramString);
        }
        if ((localClass == Float.class) || (localClass == Float.TYPE)) {
          return Float.valueOf(paramString);
        }
        if ((localClass == Double.class) || (localClass == Double.TYPE)) {
          return Double.valueOf(paramString);
        }
        if (localClass == DateTime.class) {
          return DateTime.parseRfc3339(paramString);
        }
        if (localClass == BigInteger.class) {
          return new BigInteger(paramString);
        }
        if (localClass == BigDecimal.class) {
          return new BigDecimal(paramString);
        }
        if (localClass.isEnum()) {
          return ClassInfo.of(localClass).getFieldInfo(paramString).enumValue();
        }
      }
    }
    throw new IllegalArgumentException("expected primitive class, but got: " + paramType);
  }
  
  public static Type resolveWildcardTypeOrTypeVariable(List<Type> paramList, Type paramType)
  {
    Type localType1 = paramType;
    if ((paramType instanceof WildcardType)) {
      localType1 = Types.getBound((WildcardType)paramType);
    }
    while ((localType1 instanceof TypeVariable))
    {
      Type localType2 = Types.resolveTypeVariable(paramList, (TypeVariable)localType1);
      paramType = localType1;
      if (localType2 != null) {
        paramType = localType2;
      }
      localType1 = paramType;
      if ((paramType instanceof TypeVariable)) {
        localType1 = ((TypeVariable)paramType).getBounds()[0];
      }
    }
    return localType1;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Data.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */