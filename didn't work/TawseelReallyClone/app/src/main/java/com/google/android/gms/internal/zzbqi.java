package com.google.android.gms.internal;

import android.util.Log;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.ThrowOnExtraProperties;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class zzbqi
{
  private static final ConcurrentMap<Class<?>, zza<?>> zzciE = new ConcurrentHashMap();
  
  public static <T> T zza(Object paramObject, GenericTypeIndicator<T> paramGenericTypeIndicator)
  {
    paramGenericTypeIndicator = paramGenericTypeIndicator.getClass().getGenericSuperclass();
    if ((paramGenericTypeIndicator instanceof ParameterizedType))
    {
      ParameterizedType localParameterizedType = (ParameterizedType)paramGenericTypeIndicator;
      if (!localParameterizedType.getRawType().equals(GenericTypeIndicator.class))
      {
        paramObject = String.valueOf(paramGenericTypeIndicator);
        throw new DatabaseException(String.valueOf(paramObject).length() + 47 + "Not a direct subclass of GenericTypeIndicator: " + (String)paramObject);
      }
      return (T)zza(paramObject, localParameterizedType.getActualTypeArguments()[0]);
    }
    paramObject = String.valueOf(paramGenericTypeIndicator);
    throw new DatabaseException(String.valueOf(paramObject).length() + 47 + "Not a direct subclass of GenericTypeIndicator: " + (String)paramObject);
  }
  
  public static <T> T zza(Object paramObject, Class<T> paramClass)
  {
    return (T)zzb(paramObject, paramClass);
  }
  
  private static <T> T zza(Object paramObject, ParameterizedType paramParameterizedType)
  {
    Object localObject1 = (Class)paramParameterizedType.getRawType();
    if (List.class.isAssignableFrom((Class)localObject1))
    {
      localObject1 = paramParameterizedType.getActualTypeArguments()[0];
      if ((paramObject instanceof List))
      {
        paramObject = (List)paramObject;
        paramParameterizedType = new ArrayList(((List)paramObject).size());
        localObject2 = ((List)paramObject).iterator();
        for (;;)
        {
          paramObject = paramParameterizedType;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramParameterizedType.add(zza(((Iterator)localObject2).next(), (Type)localObject1));
        }
      }
      paramObject = String.valueOf(paramObject.getClass());
      throw new DatabaseException(String.valueOf(paramObject).length() + 47 + "Expected a List while deserializing, but got a " + (String)paramObject);
    }
    if (Map.class.isAssignableFrom((Class)localObject1))
    {
      localObject1 = paramParameterizedType.getActualTypeArguments()[0];
      paramParameterizedType = paramParameterizedType.getActualTypeArguments()[1];
      if (!localObject1.equals(String.class))
      {
        paramObject = String.valueOf(localObject1);
        throw new DatabaseException(String.valueOf(paramObject).length() + 70 + "Only Maps with string keys are supported, but found Map with key type " + (String)paramObject);
      }
      localObject1 = zzay(paramObject);
      paramObject = new HashMap();
      localObject1 = ((Map)localObject1).entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        ((HashMap)paramObject).put((String)((Map.Entry)localObject2).getKey(), zza(((Map.Entry)localObject2).getValue(), paramParameterizedType));
      }
      return (T)paramObject;
    }
    if (Collection.class.isAssignableFrom((Class)localObject1)) {
      throw new DatabaseException("Collections are not supported, please use Lists instead");
    }
    paramObject = zzay(paramObject);
    localObject1 = zzi((Class)localObject1);
    Object localObject2 = new HashMap();
    TypeVariable[] arrayOfTypeVariable = zza.zza((zza)localObject1).getTypeParameters();
    paramParameterizedType = paramParameterizedType.getActualTypeArguments();
    if (paramParameterizedType.length != arrayOfTypeVariable.length) {
      throw new IllegalStateException("Mismatched lengths for type variables and actual types");
    }
    int i = 0;
    while (i < arrayOfTypeVariable.length)
    {
      ((HashMap)localObject2).put(arrayOfTypeVariable[i], paramParameterizedType[i]);
      i += 1;
    }
    return (T)((zza)localObject1).zze((Map)paramObject, (Map)localObject2);
  }
  
  private static <T> T zza(Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramType instanceof ParameterizedType)) {
      return (T)zza(paramObject, (ParameterizedType)paramType);
    }
    if ((paramType instanceof Class)) {
      return (T)zzb(paramObject, (Class)paramType);
    }
    if ((paramType instanceof WildcardType)) {
      throw new DatabaseException("Generic wildcard types are not supported");
    }
    if ((paramType instanceof GenericArrayType)) {
      throw new DatabaseException("Generic Arrays are not supported, please use Lists instead");
    }
    paramObject = String.valueOf(paramType);
    throw new IllegalStateException(String.valueOf(paramObject).length() + 26 + "Unknown type encountered: " + (String)paramObject);
  }
  
  private static Long zzaA(Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return Long.valueOf(((Integer)paramObject).longValue());
    }
    if ((paramObject instanceof Long)) {
      return (Long)paramObject;
    }
    if ((paramObject instanceof Double))
    {
      paramObject = (Double)paramObject;
      if ((((Double)paramObject).doubleValue() >= -9.223372036854776E18D) && (((Double)paramObject).doubleValue() <= 9.223372036854776E18D)) {
        return Long.valueOf(((Double)paramObject).longValue());
      }
      paramObject = String.valueOf(paramObject);
      throw new DatabaseException(String.valueOf(paramObject).length() + 89 + "Numeric value out of 64-bit long range: " + (String)paramObject + ". Did you mean to use a double instead of a long?");
    }
    paramObject = String.valueOf(paramObject.getClass().getName());
    throw new DatabaseException(String.valueOf(paramObject).length() + 42 + "Failed to convert a value of type " + (String)paramObject + " to long");
  }
  
  private static Double zzaB(Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return Double.valueOf(((Integer)paramObject).doubleValue());
    }
    if ((paramObject instanceof Long))
    {
      Long localLong = (Long)paramObject;
      Double localDouble = Double.valueOf(((Long)paramObject).doubleValue());
      if (localDouble.longValue() == localLong.longValue()) {
        return localDouble;
      }
      paramObject = String.valueOf(paramObject);
      throw new DatabaseException(String.valueOf(paramObject).length() + 97 + "Loss of precision while converting number to double: " + (String)paramObject + ". Did you mean to use a 64-bit long instead?");
    }
    if ((paramObject instanceof Double)) {
      return (Double)paramObject;
    }
    paramObject = String.valueOf(paramObject.getClass().getName());
    throw new DatabaseException(String.valueOf(paramObject).length() + 44 + "Failed to convert a value of type " + (String)paramObject + " to double");
  }
  
  private static Boolean zzaC(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return (Boolean)paramObject;
    }
    paramObject = String.valueOf(paramObject.getClass().getName());
    throw new DatabaseException(String.valueOf(paramObject).length() + 43 + "Failed to convert value of type " + (String)paramObject + " to boolean");
  }
  
  private static String zzaD(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    paramObject = String.valueOf(paramObject.getClass().getName());
    throw new DatabaseException(String.valueOf(paramObject).length() + 42 + "Failed to convert value of type " + (String)paramObject + " to String");
  }
  
  public static Map<String, Object> zzaF(Map<String, Object> paramMap)
  {
    paramMap = zzax(paramMap);
    zzbqg.zzaW(paramMap instanceof Map);
    return (Map)paramMap;
  }
  
  public static Object zzaw(Object paramObject)
  {
    return zzax(paramObject);
  }
  
  private static <T> Object zzax(T paramT)
  {
    Object localObject1;
    if (paramT == null) {
      localObject1 = null;
    }
    do
    {
      do
      {
        do
        {
          return localObject1;
          if (!(paramT instanceof Number)) {
            break;
          }
          if ((paramT instanceof Float)) {
            return Double.valueOf(((Float)paramT).doubleValue());
          }
          if ((paramT instanceof Short)) {
            throw new DatabaseException("Shorts are not supported, please use int or long");
          }
          localObject1 = paramT;
        } while (!(paramT instanceof Byte));
        throw new DatabaseException("Bytes are not supported, please use int or long");
        localObject1 = paramT;
      } while ((paramT instanceof String));
      localObject1 = paramT;
    } while ((paramT instanceof Boolean));
    if ((paramT instanceof Character)) {
      throw new DatabaseException("Characters are not supported, please strings");
    }
    if ((paramT instanceof Map))
    {
      localObject1 = new HashMap();
      paramT = ((Map)paramT).entrySet().iterator();
      while (paramT.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramT.next();
        Object localObject2 = localEntry.getKey();
        if ((localObject2 instanceof String)) {
          ((Map)localObject1).put((String)localObject2, zzax(localEntry.getValue()));
        } else {
          throw new DatabaseException("Maps with non-string keys are not supported");
        }
      }
      return localObject1;
    }
    if ((paramT instanceof Collection))
    {
      if ((paramT instanceof List))
      {
        localObject1 = (List)paramT;
        paramT = new ArrayList(((List)localObject1).size());
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext()) {
          paramT.add(zzax(((Iterator)localObject1).next()));
        }
        return paramT;
      }
      throw new DatabaseException("Serializing Collections is not supported, please use Lists instead");
    }
    if (paramT.getClass().isArray()) {
      throw new DatabaseException("Serializing Arrays is not supported, please use Lists instead");
    }
    if ((paramT instanceof Enum)) {
      return ((Enum)paramT).name();
    }
    return zzi(paramT.getClass()).zzaF(paramT);
  }
  
  private static Map<String, Object> zzay(Object paramObject)
  {
    if ((paramObject instanceof Map)) {
      return (Map)paramObject;
    }
    paramObject = String.valueOf(paramObject.getClass());
    throw new DatabaseException(String.valueOf(paramObject).length() + 46 + "Expected a Map while deserializing, but got a " + (String)paramObject);
  }
  
  private static Integer zzaz(Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return (Integer)paramObject;
    }
    if (((paramObject instanceof Long)) || ((paramObject instanceof Double)))
    {
      double d = ((Number)paramObject).doubleValue();
      if ((d >= -2.147483648E9D) && (d <= 2.147483647E9D)) {
        return Integer.valueOf(((Number)paramObject).intValue());
      }
      throw new DatabaseException(124 + "Numeric value out of 32-bit integer range: " + d + ". Did you mean to use a long or double instead of an int?");
    }
    paramObject = String.valueOf(paramObject.getClass().getName());
    throw new DatabaseException(String.valueOf(paramObject).length() + 41 + "Failed to convert a value of type " + (String)paramObject + " to int");
  }
  
  private static <T> T zzb(Object paramObject, Class<T> paramClass)
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    do
    {
      return (T)localObject;
      if ((paramClass.isPrimitive()) || (Number.class.isAssignableFrom(paramClass)) || (Boolean.class.isAssignableFrom(paramClass)) || (Character.class.isAssignableFrom(paramClass))) {
        return (T)zzc(paramObject, paramClass);
      }
      if (String.class.isAssignableFrom(paramClass)) {
        return zzaD(paramObject);
      }
      if (paramClass.isArray()) {
        throw new DatabaseException("Converting to Arrays is not supported, please use Listsinstead");
      }
      if (paramClass.getTypeParameters().length > 0)
      {
        paramObject = String.valueOf(paramClass.getName());
        throw new DatabaseException(String.valueOf(paramObject).length() + 75 + "Class " + (String)paramObject + " has generic type parameters, please use GenericTypeIndicator instead");
      }
      localObject = paramObject;
    } while (paramClass.equals(Object.class));
    if (paramClass.isEnum()) {
      return (T)zzd(paramObject, paramClass);
    }
    return (T)zze(paramObject, paramClass);
  }
  
  private static <T> T zzc(Object paramObject, Class<T> paramClass)
  {
    if ((Integer.class.isAssignableFrom(paramClass)) || (Integer.TYPE.isAssignableFrom(paramClass))) {
      return zzaz(paramObject);
    }
    if ((Boolean.class.isAssignableFrom(paramClass)) || (Boolean.TYPE.isAssignableFrom(paramClass))) {
      return zzaC(paramObject);
    }
    if ((Double.class.isAssignableFrom(paramClass)) || (Double.TYPE.isAssignableFrom(paramClass))) {
      return zzaB(paramObject);
    }
    if ((Long.class.isAssignableFrom(paramClass)) || (Long.TYPE.isAssignableFrom(paramClass))) {
      return zzaA(paramObject);
    }
    if ((Float.class.isAssignableFrom(paramClass)) || (Float.TYPE.isAssignableFrom(paramClass))) {
      return Float.valueOf(zzaB(paramObject).floatValue());
    }
    if ((Short.class.isAssignableFrom(paramClass)) || (Short.TYPE.isAssignableFrom(paramClass))) {
      throw new DatabaseException("Deserializing to shorts is not supported");
    }
    if ((Byte.class.isAssignableFrom(paramClass)) || (Byte.TYPE.isAssignableFrom(paramClass))) {
      throw new DatabaseException("Deserializing to bytes is not supported");
    }
    if ((Character.class.isAssignableFrom(paramClass)) || (Character.TYPE.isAssignableFrom(paramClass))) {
      throw new DatabaseException("Deserializing to char is not supported");
    }
    paramObject = String.valueOf(paramClass);
    throw new IllegalArgumentException(String.valueOf(paramObject).length() + 24 + "Unknown primitive type: " + (String)paramObject);
  }
  
  private static <T> T zzd(Object paramObject, Class<T> paramClass)
  {
    if ((paramObject instanceof String))
    {
      paramObject = (String)paramObject;
      try
      {
        Enum localEnum = Enum.valueOf(paramClass, (String)paramObject);
        return localEnum;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        paramClass = String.valueOf(paramClass.getName());
        throw new DatabaseException(String.valueOf(paramClass).length() + 42 + String.valueOf(paramObject).length() + "Could not find enum value of " + paramClass + " for value \"" + (String)paramObject + "\"");
      }
    }
    paramClass = String.valueOf(paramClass);
    paramObject = String.valueOf(paramObject.getClass());
    throw new DatabaseException(String.valueOf(paramClass).length() + 57 + String.valueOf(paramObject).length() + "Expected a String while deserializing to enum " + paramClass + " but got a " + (String)paramObject);
  }
  
  private static <T> T zze(Object paramObject, Class<T> paramClass)
  {
    zza localzza = zzi(paramClass);
    if ((paramObject instanceof Map)) {
      return (T)localzza.zzaG(zzay(paramObject));
    }
    paramObject = String.valueOf(paramObject.getClass().getName());
    paramClass = String.valueOf(paramClass.getName());
    throw new DatabaseException(String.valueOf(paramObject).length() + 38 + String.valueOf(paramClass).length() + "Can't convert object of type " + (String)paramObject + " to type " + paramClass);
  }
  
  private static <T> zza<T> zzi(Class<T> paramClass)
  {
    zza localzza2 = (zza)zzciE.get(paramClass);
    zza localzza1 = localzza2;
    if (localzza2 == null)
    {
      localzza1 = new zza(paramClass);
      zzciE.put(paramClass, localzza1);
    }
    return localzza1;
  }
  
  private static class zza<T>
  {
    private final Class<T> zzciF;
    private final Constructor<T> zzciG;
    private final boolean zzciH;
    private final boolean zzciI;
    private final Map<String, String> zzciJ;
    private final Map<String, Method> zzciK;
    private final Map<String, Method> zzciL;
    private final Map<String, Field> zzciM;
    
    public zza(Class<T> paramClass)
    {
      this.zzciF = paramClass;
      this.zzciH = paramClass.isAnnotationPresent(ThrowOnExtraProperties.class);
      boolean bool;
      if (!paramClass.isAnnotationPresent(IgnoreExtraProperties.class)) {
        bool = true;
      }
      int j;
      int i;
      Object localObject3;
      Object localObject4;
      Object localObject2;
      for (;;)
      {
        this.zzciI = bool;
        this.zzciJ = new HashMap();
        this.zzciL = new HashMap();
        this.zzciK = new HashMap();
        this.zzciM = new HashMap();
        try
        {
          Object localObject1 = paramClass.getDeclaredConstructor(new Class[0]);
          ((Constructor)localObject1).setAccessible(true);
          this.zzciG = ((Constructor)localObject1);
          localObject1 = paramClass.getMethods();
          j = localObject1.length;
          i = 0;
          if (i < j)
          {
            localObject3 = localObject1[i];
            if (zza((Method)localObject3))
            {
              localObject4 = zzc((Method)localObject3);
              zzjs((String)localObject4);
              ((Method)localObject3).setAccessible(true);
              if (this.zzciK.containsKey(localObject4))
              {
                paramClass = String.valueOf(((Method)localObject3).getName());
                if (paramClass.length() != 0)
                {
                  paramClass = "Found conflicting getters for name: ".concat(paramClass);
                  throw new DatabaseException(paramClass);
                  bool = false;
                }
              }
            }
          }
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          for (;;)
          {
            localObject2 = null;
            continue;
            paramClass = new String("Found conflicting getters for name: ");
            continue;
            this.zzciK.put(localObject4, localObject3);
            i += 1;
          }
          localObject2 = paramClass.getFields();
          j = localObject2.length;
          i = 0;
          while (i < j)
          {
            localObject3 = localObject2[i];
            if (zza((Field)localObject3)) {
              zzjs(zzb((Field)localObject3));
            }
            i += 1;
          }
          localObject2 = paramClass;
        }
      }
      for (;;)
      {
        localObject4 = ((Class)localObject2).getDeclaredMethods();
        j = localObject4.length;
        i = 0;
        Object localObject5;
        if (i < j)
        {
          localObject5 = localObject4[i];
          if (zzb((Method)localObject5))
          {
            String str = zzc((Method)localObject5);
            localObject3 = (String)this.zzciJ.get(str.toLowerCase());
            if (localObject3 != null)
            {
              if (!((String)localObject3).equals(str))
              {
                paramClass = String.valueOf(((Method)localObject5).getName());
                if (paramClass.length() != 0) {}
                for (paramClass = "Found setter with invalid case-sensitive name: ".concat(paramClass);; paramClass = new String("Found setter with invalid case-sensitive name: ")) {
                  throw new DatabaseException(paramClass);
                }
              }
              localObject3 = (Method)this.zzciL.get(str);
              if (localObject3 != null) {
                break label463;
              }
              ((Method)localObject5).setAccessible(true);
              this.zzciL.put(str, localObject5);
            }
          }
          label463:
          while (zza((Method)localObject5, (Method)localObject3))
          {
            i += 1;
            break;
          }
          paramClass = String.valueOf(((Method)localObject5).getName());
          localObject2 = String.valueOf(((Method)localObject3).getName());
          localObject3 = String.valueOf(((Method)localObject3).getDeclaringClass().getName());
          throw new DatabaseException(String.valueOf(paramClass).length() + 69 + String.valueOf(localObject2).length() + String.valueOf(localObject3).length() + "Found a conflicting setters with name: " + paramClass + " (conflicts with " + (String)localObject2 + " defined on " + (String)localObject3 + ")");
        }
        localObject3 = ((Class)localObject2).getDeclaredFields();
        j = localObject3.length;
        i = 0;
        while (i < j)
        {
          localObject4 = localObject3[i];
          localObject5 = zzb((Field)localObject4);
          if ((this.zzciJ.containsKey(((String)localObject5).toLowerCase())) && (!this.zzciM.containsKey(localObject5)))
          {
            ((Field)localObject4).setAccessible(true);
            this.zzciM.put(localObject5, localObject4);
          }
          i += 1;
        }
        localObject2 = ((Class)localObject2).getSuperclass();
        if ((localObject2 == null) || (localObject2.equals(Object.class)))
        {
          if (this.zzciJ.isEmpty())
          {
            paramClass = String.valueOf(paramClass.getName());
            if (paramClass.length() != 0) {}
            for (paramClass = "No properties to serialize found on class ".concat(paramClass);; paramClass = new String("No properties to serialize found on class ")) {
              throw new DatabaseException(paramClass);
            }
          }
          return;
        }
      }
    }
    
    private static String zza(AccessibleObject paramAccessibleObject)
    {
      if (paramAccessibleObject.isAnnotationPresent(PropertyName.class)) {
        return ((PropertyName)paramAccessibleObject.getAnnotation(PropertyName.class)).value();
      }
      return null;
    }
    
    private Type zza(Type paramType, Map<TypeVariable<Class<T>>, Type> paramMap)
    {
      if ((paramType instanceof TypeVariable))
      {
        Type localType = (Type)paramMap.get(paramType);
        paramMap = localType;
        if (localType == null)
        {
          paramType = String.valueOf(paramType);
          throw new IllegalStateException(String.valueOf(paramType).length() + 23 + "Could not resolve type " + paramType);
        }
      }
      else
      {
        paramMap = paramType;
      }
      return paramMap;
    }
    
    private static boolean zza(Field paramField)
    {
      if (paramField.getDeclaringClass().equals(Object.class)) {}
      while ((!Modifier.isPublic(paramField.getModifiers())) || (Modifier.isStatic(paramField.getModifiers())) || (Modifier.isTransient(paramField.getModifiers())) || (paramField.isAnnotationPresent(Exclude.class))) {
        return false;
      }
      return true;
    }
    
    private static boolean zza(Method paramMethod)
    {
      if ((!paramMethod.getName().startsWith("get")) && (!paramMethod.getName().startsWith("is"))) {}
      while ((paramMethod.getDeclaringClass().equals(Object.class)) || (!Modifier.isPublic(paramMethod.getModifiers())) || (Modifier.isStatic(paramMethod.getModifiers())) || (paramMethod.getReturnType().equals(Void.TYPE)) || (paramMethod.getParameterTypes().length != 0) || (paramMethod.isAnnotationPresent(Exclude.class))) {
        return false;
      }
      return true;
    }
    
    private static boolean zza(Method paramMethod1, Method paramMethod2)
    {
      zzbqg.zzb(paramMethod1.getDeclaringClass().isAssignableFrom(paramMethod2.getDeclaringClass()), "Expected override from a base class");
      zzbqg.zzb(paramMethod1.getReturnType().equals(Void.TYPE), "Expected void return type");
      zzbqg.zzb(paramMethod2.getReturnType().equals(Void.TYPE), "Expected void return type");
      Class[] arrayOfClass1 = paramMethod1.getParameterTypes();
      Class[] arrayOfClass2 = paramMethod2.getParameterTypes();
      if (arrayOfClass1.length == 1)
      {
        bool = true;
        zzbqg.zzb(bool, "Expected exactly one parameter");
        if (arrayOfClass2.length != 1) {
          break label125;
        }
      }
      label125:
      for (boolean bool = true;; bool = false)
      {
        zzbqg.zzb(bool, "Expected exactly one parameter");
        if ((!paramMethod1.getName().equals(paramMethod2.getName())) || (!arrayOfClass1[0].equals(arrayOfClass2[0]))) {
          break label130;
        }
        return true;
        bool = false;
        break;
      }
      label130:
      return false;
    }
    
    private static String zzb(Field paramField)
    {
      String str = zza(paramField);
      if (str != null) {
        return str;
      }
      return paramField.getName();
    }
    
    private static boolean zzb(Method paramMethod)
    {
      if (!paramMethod.getName().startsWith("set")) {}
      while ((paramMethod.getDeclaringClass().equals(Object.class)) || (Modifier.isStatic(paramMethod.getModifiers())) || (!paramMethod.getReturnType().equals(Void.TYPE)) || (paramMethod.getParameterTypes().length != 1) || (paramMethod.isAnnotationPresent(Exclude.class))) {
        return false;
      }
      return true;
    }
    
    private static String zzc(Method paramMethod)
    {
      String str = zza(paramMethod);
      if (str != null) {
        return str;
      }
      return zzjt(paramMethod.getName());
    }
    
    private void zzjs(String paramString)
    {
      String str = (String)this.zzciJ.put(paramString.toLowerCase(), paramString);
      if ((str != null) && (!paramString.equals(str)))
      {
        paramString = String.valueOf(paramString.toLowerCase());
        if (paramString.length() != 0) {}
        for (paramString = "Found two getters or fields with conflicting case sensitivity for property: ".concat(paramString);; paramString = new String("Found two getters or fields with conflicting case sensitivity for property: ")) {
          throw new DatabaseException(paramString);
        }
      }
    }
    
    private static String zzjt(String paramString)
    {
      Object localObject = null;
      int i = 0;
      if (i < 3)
      {
        String str = new String[] { "get", "set", "is" }[i];
        if (!paramString.startsWith(str)) {
          break label150;
        }
        localObject = str;
      }
      label150:
      for (;;)
      {
        i += 1;
        break;
        if (localObject == null)
        {
          paramString = String.valueOf(paramString);
          if (paramString.length() != 0) {}
          for (paramString = "Unknown Bean prefix for method: ".concat(paramString);; paramString = new String("Unknown Bean prefix for method: ")) {
            throw new IllegalArgumentException(paramString);
          }
        }
        paramString = paramString.substring(((String)localObject).length()).toCharArray();
        i = 0;
        while ((i < paramString.length) && (Character.isUpperCase(paramString[i])))
        {
          paramString[i] = Character.toLowerCase(paramString[i]);
          i += 1;
        }
        return new String(paramString);
      }
    }
    
    public Map<String, Object> zzaF(T paramT)
    {
      Object localObject;
      if (!this.zzciF.isAssignableFrom(paramT.getClass()))
      {
        paramT = String.valueOf(paramT.getClass());
        localObject = String.valueOf(this.zzciF);
        throw new IllegalArgumentException(String.valueOf(paramT).length() + 59 + String.valueOf(localObject).length() + "Can't serialize object of class " + paramT + " with BeanMapper for class " + (String)localObject);
      }
      HashMap localHashMap = new HashMap();
      Iterator localIterator = this.zzciJ.values().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (this.zzciK.containsKey(str)) {
          localObject = (Method)this.zzciK.get(str);
        }
        for (;;)
        {
          try
          {
            localObject = ((Method)localObject).invoke(paramT, new Object[0]);
            localHashMap.put(str, zzbqi.zzaE(localObject));
          }
          catch (IllegalAccessException paramT)
          {
            throw new RuntimeException(paramT);
          }
          catch (InvocationTargetException paramT)
          {
            throw new RuntimeException(paramT);
          }
          localObject = (Field)this.zzciM.get(str);
          if (localObject == null)
          {
            paramT = String.valueOf(str);
            if (paramT.length() != 0) {}
            for (paramT = "Bean property without field or getter:".concat(paramT);; paramT = new String("Bean property without field or getter:")) {
              throw new IllegalStateException(paramT);
            }
          }
          try
          {
            localObject = ((Field)localObject).get(paramT);
          }
          catch (IllegalAccessException paramT)
          {
            throw new RuntimeException(paramT);
          }
        }
      }
      return localHashMap;
    }
    
    public T zzaG(Map<String, Object> paramMap)
    {
      return (T)zze(paramMap, Collections.emptyMap());
    }
    
    public T zze(Map<String, Object> paramMap, Map<TypeVariable<Class<T>>, Type> paramMap1)
    {
      if (this.zzciG == null)
      {
        paramMap = String.valueOf(this.zzciF.getName());
        throw new DatabaseException(String.valueOf(paramMap).length() + 49 + "Class " + paramMap + " is missing a constructor with no arguments");
      }
      Object localObject2;
      for (;;)
      {
        Object localObject1;
        try
        {
          localObject2 = this.zzciG.newInstance(new Object[0]);
          Iterator localIterator = paramMap.entrySet().iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          paramMap = (Map.Entry)localIterator.next();
          localObject3 = (String)paramMap.getKey();
          if (!this.zzciL.containsKey(localObject3)) {
            break label264;
          }
          localObject1 = (Method)this.zzciL.get(localObject3);
          localObject3 = ((Method)localObject1).getGenericParameterTypes();
          if (localObject3.length != 1) {
            throw new IllegalStateException("Setter does not have exactly one parameter");
          }
        }
        catch (InstantiationException paramMap)
        {
          throw new RuntimeException(paramMap);
        }
        catch (IllegalAccessException paramMap)
        {
          throw new RuntimeException(paramMap);
        }
        catch (InvocationTargetException paramMap)
        {
          throw new RuntimeException(paramMap);
        }
        Object localObject3 = zza(localObject3[0], paramMap1);
        paramMap = zzbqi.zzb(paramMap.getValue(), (Type)localObject3);
        try
        {
          ((Method)localObject1).invoke(localObject2, new Object[] { paramMap });
        }
        catch (IllegalAccessException paramMap)
        {
          throw new RuntimeException(paramMap);
        }
        catch (InvocationTargetException paramMap)
        {
          throw new RuntimeException(paramMap);
        }
        label264:
        if (this.zzciM.containsKey(localObject3))
        {
          localObject1 = (Field)this.zzciM.get(localObject3);
          localObject3 = zza(((Field)localObject1).getGenericType(), paramMap1);
          paramMap = zzbqi.zzb(paramMap.getValue(), (Type)localObject3);
          try
          {
            ((Field)localObject1).set(localObject2, paramMap);
          }
          catch (IllegalAccessException paramMap)
          {
            throw new RuntimeException(paramMap);
          }
        }
        else
        {
          paramMap = String.valueOf(this.zzciF.getName());
          localObject1 = String.valueOf(localObject3).length() + 36 + String.valueOf(paramMap).length() + "No setter/field for " + (String)localObject3 + " found on class " + paramMap;
          paramMap = (Map<String, Object>)localObject1;
          if (this.zzciJ.containsKey(((String)localObject3).toLowerCase())) {
            paramMap = String.valueOf(localObject1).concat(" (fields/setters are case sensitive!)");
          }
          if (this.zzciH) {
            throw new DatabaseException(paramMap);
          }
          if (this.zzciI) {
            Log.w("ClassMapper", paramMap);
          }
        }
      }
      return (T)localObject2;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */