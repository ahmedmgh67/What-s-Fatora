package com.google.api.client.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

public class FieldInfo
{
  private static final Map<Field, FieldInfo> CACHE = new WeakHashMap();
  private final Field field;
  private final boolean isPrimitive;
  private final String name;
  
  FieldInfo(Field paramField, String paramString)
  {
    this.field = paramField;
    if (paramString == null) {}
    for (paramField = null;; paramField = paramString.intern())
    {
      this.name = paramField;
      this.isPrimitive = Data.isPrimitive(getType());
      return;
    }
  }
  
  public static Object getFieldValue(Field paramField, Object paramObject)
  {
    try
    {
      paramField = paramField.get(paramObject);
      return paramField;
    }
    catch (IllegalAccessException paramField)
    {
      throw new IllegalArgumentException(paramField);
    }
  }
  
  /* Error */
  public static FieldInfo of(Enum<?> paramEnum)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: invokevirtual 68	java/lang/Object:getClass	()Ljava/lang/Class;
    //   6: aload_0
    //   7: invokevirtual 72	java/lang/Enum:name	()Ljava/lang/String;
    //   10: invokevirtual 78	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   13: invokestatic 81	com/google/api/client/util/FieldInfo:of	(Ljava/lang/reflect/Field;)Lcom/google/api/client/util/FieldInfo;
    //   16: astore_2
    //   17: aload_2
    //   18: ifnull +19 -> 37
    //   21: iload_1
    //   22: ldc 83
    //   24: iconst_1
    //   25: anewarray 4	java/lang/Object
    //   28: dup
    //   29: iconst_0
    //   30: aload_0
    //   31: aastore
    //   32: invokestatic 89	com/google/api/client/util/Preconditions:checkArgument	(ZLjava/lang/String;[Ljava/lang/Object;)V
    //   35: aload_2
    //   36: areturn
    //   37: iconst_0
    //   38: istore_1
    //   39: goto -18 -> 21
    //   42: astore_0
    //   43: new 91	java/lang/RuntimeException
    //   46: dup
    //   47: aload_0
    //   48: invokespecial 92	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	paramEnum	Enum<?>
    //   1	38	1	bool	boolean
    //   16	20	2	localFieldInfo	FieldInfo
    // Exception table:
    //   from	to	target	type
    //   2	17	42	java/lang/NoSuchFieldException
    //   21	35	42	java/lang/NoSuchFieldException
  }
  
  public static FieldInfo of(Field paramField)
  {
    if (paramField == null) {
      return null;
    }
    for (;;)
    {
      Object localObject1;
      synchronized (CACHE)
      {
        Object localObject2 = (FieldInfo)CACHE.get(paramField);
        boolean bool = paramField.isEnumConstant();
        localObject1 = localObject2;
        if (localObject2 == null) {
          if (!bool)
          {
            localObject1 = localObject2;
            if (Modifier.isStatic(paramField.getModifiers())) {}
          }
          else
          {
            if (!bool) {
              break label149;
            }
            localObject1 = (Value)paramField.getAnnotation(Value.class);
            if (localObject1 == null) {
              break label127;
            }
            localObject1 = ((Value)localObject1).value();
            localObject2 = localObject1;
            if ("##default".equals(localObject1)) {
              localObject2 = paramField.getName();
            }
            localObject1 = new FieldInfo(paramField, (String)localObject2);
            CACHE.put(paramField, localObject1);
          }
        }
        return (FieldInfo)localObject1;
      }
      label127:
      if ((NullValue)paramField.getAnnotation(NullValue.class) != null)
      {
        localObject1 = null;
      }
      else
      {
        return null;
        label149:
        localObject1 = (Key)paramField.getAnnotation(Key.class);
        if (localObject1 == null) {
          return null;
        }
        localObject1 = ((Key)localObject1).value();
        paramField.setAccessible(true);
      }
    }
  }
  
  public static void setFieldValue(Field paramField, Object paramObject1, Object paramObject2)
  {
    if (Modifier.isFinal(paramField.getModifiers()))
    {
      Object localObject = getFieldValue(paramField, paramObject1);
      if (paramObject2 == null)
      {
        if (localObject == null) {}
      }
      else {
        while (!paramObject2.equals(localObject)) {
          throw new IllegalArgumentException("expected final value <" + localObject + "> but was <" + paramObject2 + "> on " + paramField.getName() + " field in " + paramObject1.getClass().getName());
        }
      }
      return;
    }
    try
    {
      paramField.set(paramObject1, paramObject2);
      return;
    }
    catch (SecurityException paramField)
    {
      throw new IllegalArgumentException(paramField);
    }
    catch (IllegalAccessException paramField)
    {
      throw new IllegalArgumentException(paramField);
    }
  }
  
  public <T extends Enum<T>> T enumValue()
  {
    return Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
  }
  
  public ClassInfo getClassInfo()
  {
    return ClassInfo.of(this.field.getDeclaringClass());
  }
  
  public Field getField()
  {
    return this.field;
  }
  
  public Type getGenericType()
  {
    return this.field.getGenericType();
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Class<?> getType()
  {
    return this.field.getType();
  }
  
  public Object getValue(Object paramObject)
  {
    return getFieldValue(this.field, paramObject);
  }
  
  public boolean isFinal()
  {
    return Modifier.isFinal(this.field.getModifiers());
  }
  
  public boolean isPrimitive()
  {
    return this.isPrimitive;
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
  {
    setFieldValue(this.field, paramObject1, paramObject2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\FieldInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */