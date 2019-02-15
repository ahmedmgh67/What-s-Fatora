package com.google.api.client.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Types
{
  private static Type getActualParameterAtPosition(Type paramType, Class<?> paramClass, int paramInt)
  {
    paramClass = getSuperParameterizedType(paramType, paramClass);
    if (paramClass == null) {
      paramType = null;
    }
    Type localType;
    do
    {
      return paramType;
      localType = paramClass.getActualTypeArguments()[paramInt];
      if (!(localType instanceof TypeVariable)) {
        break;
      }
      paramClass = resolveTypeVariable(Arrays.asList(new Type[] { paramType }), (TypeVariable)localType);
      paramType = paramClass;
    } while (paramClass != null);
    return localType;
  }
  
  public static Type getArrayComponentType(Type paramType)
  {
    if ((paramType instanceof GenericArrayType)) {
      return ((GenericArrayType)paramType).getGenericComponentType();
    }
    return ((Class)paramType).getComponentType();
  }
  
  public static Type getBound(WildcardType paramWildcardType)
  {
    Type[] arrayOfType = paramWildcardType.getLowerBounds();
    if (arrayOfType.length != 0) {
      return arrayOfType[0];
    }
    return paramWildcardType.getUpperBounds()[0];
  }
  
  public static Type getIterableParameter(Type paramType)
  {
    return getActualParameterAtPosition(paramType, Iterable.class, 0);
  }
  
  public static Type getMapValueParameter(Type paramType)
  {
    return getActualParameterAtPosition(paramType, Map.class, 1);
  }
  
  public static Class<?> getRawArrayComponentType(List<Type> paramList, Type paramType)
  {
    Type localType = paramType;
    if ((paramType instanceof TypeVariable)) {
      localType = resolveTypeVariable(paramList, (TypeVariable)paramType);
    }
    if ((localType instanceof GenericArrayType)) {
      return Array.newInstance(getRawArrayComponentType(paramList, getArrayComponentType(localType)), 0).getClass();
    }
    if ((localType instanceof Class)) {
      return (Class)localType;
    }
    if ((localType instanceof ParameterizedType)) {
      return getRawClass((ParameterizedType)localType);
    }
    if (localType == null) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "wildcard type is not supported: %s", new Object[] { localType });
      return Object.class;
    }
  }
  
  public static Class<?> getRawClass(ParameterizedType paramParameterizedType)
  {
    return (Class)paramParameterizedType.getRawType();
  }
  
  public static ParameterizedType getSuperParameterizedType(Type paramType, Class<?> paramClass)
  {
    Object localObject = paramType;
    if (!(paramType instanceof Class))
    {
      if ((paramType instanceof ParameterizedType)) {
        localObject = paramType;
      }
    }
    else if ((localObject != null) && (localObject != Object.class))
    {
      if ((localObject instanceof Class)) {
        paramType = (Class)localObject;
      }
      label46:
      do
      {
        localObject = paramType.getGenericSuperclass();
        break;
        paramType = (ParameterizedType)localObject;
        localObject = getRawClass(paramType);
        if (localObject == paramClass) {
          return paramType;
        }
        paramType = (Type)localObject;
      } while (!paramClass.isInterface());
      Type[] arrayOfType = ((Class)localObject).getGenericInterfaces();
      int j = arrayOfType.length;
      int i = 0;
      for (;;)
      {
        paramType = (Type)localObject;
        if (i >= j) {
          break label46;
        }
        Type localType = arrayOfType[i];
        if ((localType instanceof Class)) {}
        for (paramType = (Class)localType;; paramType = getRawClass((ParameterizedType)localType))
        {
          if (!paramClass.isAssignableFrom(paramType)) {
            break label153;
          }
          localObject = localType;
          break;
        }
        label153:
        i += 1;
      }
    }
    return null;
  }
  
  private static IllegalArgumentException handleExceptionForNewInstance(Exception paramException, Class<?> paramClass)
  {
    StringBuilder localStringBuilder = new StringBuilder("unable to create new instance of class ").append(paramClass.getName());
    Object localObject = new ArrayList();
    int i;
    if (paramClass.isArray())
    {
      ((ArrayList)localObject).add("because it is an array");
      i = 0;
      paramClass = ((ArrayList)localObject).iterator();
      label49:
      if (!paramClass.hasNext()) {
        break label244;
      }
      localObject = (String)paramClass.next();
      if (i == 0) {
        break label239;
      }
      localStringBuilder.append(" and");
    }
    for (;;)
    {
      localStringBuilder.append(" ").append((String)localObject);
      break label49;
      if (paramClass.isPrimitive())
      {
        ((ArrayList)localObject).add("because it is primitive");
        break;
      }
      if (paramClass == Void.class)
      {
        ((ArrayList)localObject).add("because it is void");
        break;
      }
      if (Modifier.isInterface(paramClass.getModifiers())) {
        ((ArrayList)localObject).add("because it is an interface");
      }
      for (;;)
      {
        if ((paramClass.getEnclosingClass() != null) && (!Modifier.isStatic(paramClass.getModifiers()))) {
          ((ArrayList)localObject).add("because it is not static");
        }
        if (Modifier.isPublic(paramClass.getModifiers())) {
          break label215;
        }
        ((ArrayList)localObject).add("possibly because it is not public");
        break;
        if (Modifier.isAbstract(paramClass.getModifiers())) {
          ((ArrayList)localObject).add("because it is abstract");
        }
      }
      try
      {
        label215:
        paramClass.getConstructor(new Class[0]);
      }
      catch (NoSuchMethodException paramClass)
      {
        ((ArrayList)localObject).add("because it has no accessible default constructor");
      }
      break;
      label239:
      i = 1;
    }
    label244:
    return new IllegalArgumentException(localStringBuilder.toString(), paramException);
  }
  
  public static boolean isArray(Type paramType)
  {
    return ((paramType instanceof GenericArrayType)) || (((paramType instanceof Class)) && (((Class)paramType).isArray()));
  }
  
  public static boolean isAssignableToOrFrom(Class<?> paramClass1, Class<?> paramClass2)
  {
    return (paramClass1.isAssignableFrom(paramClass2)) || (paramClass2.isAssignableFrom(paramClass1));
  }
  
  public static <T> Iterable<T> iterableOf(Object paramObject)
  {
    if ((paramObject instanceof Iterable)) {
      return (Iterable)paramObject;
    }
    Class localClass = paramObject.getClass();
    Preconditions.checkArgument(localClass.isArray(), "not an array or Iterable: %s", new Object[] { localClass });
    if (!localClass.getComponentType().isPrimitive()) {
      return Arrays.asList((Object[])paramObject);
    }
    new Iterable()
    {
      public Iterator<T> iterator()
      {
        new Iterator()
        {
          int index = 0;
          final int length = Array.getLength(Types.1.this.val$value);
          
          public boolean hasNext()
          {
            return this.index < this.length;
          }
          
          public T next()
          {
            if (!hasNext()) {
              throw new NoSuchElementException();
            }
            Object localObject = Types.1.this.val$value;
            int i = this.index;
            this.index = (i + 1);
            return (T)Array.get(localObject, i);
          }
          
          public void remove()
          {
            throw new UnsupportedOperationException();
          }
        };
      }
    };
  }
  
  public static <T> T newInstance(Class<T> paramClass)
  {
    try
    {
      Object localObject = paramClass.newInstance();
      return (T)localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw handleExceptionForNewInstance(localIllegalAccessException, paramClass);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw handleExceptionForNewInstance(localInstantiationException, paramClass);
    }
  }
  
  public static Type resolveTypeVariable(List<Type> paramList, TypeVariable<?> paramTypeVariable)
  {
    Object localObject = paramTypeVariable.getGenericDeclaration();
    if ((localObject instanceof Class))
    {
      Class localClass = (Class)localObject;
      int i = paramList.size();
      for (ParameterizedType localParameterizedType = null; localParameterizedType == null; localParameterizedType = getSuperParameterizedType((Type)paramList.get(i), localClass))
      {
        i -= 1;
        if (i < 0) {
          break;
        }
      }
      if (localParameterizedType != null)
      {
        localObject = ((GenericDeclaration)localObject).getTypeParameters();
        i = 0;
        for (;;)
        {
          if ((i >= localObject.length) || (localObject[i].equals(paramTypeVariable)))
          {
            paramTypeVariable = localParameterizedType.getActualTypeArguments()[i];
            if (!(paramTypeVariable instanceof TypeVariable)) {
              break;
            }
            paramList = resolveTypeVariable(paramList, (TypeVariable)paramTypeVariable);
            if (paramList == null) {
              break;
            }
            return paramList;
          }
          i += 1;
        }
        return paramTypeVariable;
      }
    }
    return null;
  }
  
  public static Object toArray(Collection<?> paramCollection, Class<?> paramClass)
  {
    if (paramClass.isPrimitive())
    {
      paramClass = Array.newInstance(paramClass, paramCollection.size());
      int i = 0;
      Iterator localIterator = paramCollection.iterator();
      for (;;)
      {
        paramCollection = paramClass;
        if (!localIterator.hasNext()) {
          break;
        }
        Array.set(paramClass, i, localIterator.next());
        i += 1;
      }
    }
    paramCollection = paramCollection.toArray((Object[])Array.newInstance(paramClass, paramCollection.size()));
    return paramCollection;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Types.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */