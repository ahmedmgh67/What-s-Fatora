package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

@Beta
@GwtCompatible(emulated=true)
public final class Enums
{
  @GwtIncompatible("java.lang.ref.WeakReference")
  private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> enumConstantCache = new WeakHashMap();
  
  @GwtIncompatible("java.lang.ref.WeakReference")
  static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(Class<T> paramClass)
  {
    synchronized (enumConstantCache)
    {
      Map localMap2 = (Map)enumConstantCache.get(paramClass);
      Map localMap1 = localMap2;
      if (localMap2 == null) {
        localMap1 = populateCache(paramClass);
      }
      return localMap1;
    }
  }
  
  @GwtIncompatible("reflection")
  public static Field getField(Enum<?> paramEnum)
  {
    Class localClass = paramEnum.getDeclaringClass();
    try
    {
      paramEnum = localClass.getDeclaredField(paramEnum.name());
      return paramEnum;
    }
    catch (NoSuchFieldException paramEnum)
    {
      throw new AssertionError(paramEnum);
    }
  }
  
  public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> paramClass, String paramString)
  {
    Preconditions.checkNotNull(paramClass);
    Preconditions.checkNotNull(paramString);
    return Platform.getEnumIfPresent(paramClass, paramString);
  }
  
  @GwtIncompatible("java.lang.ref.WeakReference")
  private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(Class<T> paramClass)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = EnumSet.allOf(paramClass).iterator();
    while (localIterator.hasNext())
    {
      Enum localEnum = (Enum)localIterator.next();
      localHashMap.put(localEnum.name(), new WeakReference(localEnum));
    }
    enumConstantCache.put(paramClass, localHashMap);
    return localHashMap;
  }
  
  public static <T extends Enum<T>> Converter<String, T> stringConverter(Class<T> paramClass)
  {
    return new StringConverter(paramClass);
  }
  
  @Deprecated
  public static <T extends Enum<T>> Function<String, T> valueOfFunction(Class<T> paramClass)
  {
    return new ValueOfFunction(paramClass, null);
  }
  
  private static final class StringConverter<T extends Enum<T>>
    extends Converter<String, T>
    implements Serializable
  {
    private static final long serialVersionUID = 0L;
    private final Class<T> enumClass;
    
    StringConverter(Class<T> paramClass)
    {
      this.enumClass = ((Class)Preconditions.checkNotNull(paramClass));
    }
    
    protected String doBackward(T paramT)
    {
      return paramT.name();
    }
    
    protected T doForward(String paramString)
    {
      return Enum.valueOf(this.enumClass, paramString);
    }
    
    public boolean equals(@Nullable Object paramObject)
    {
      if ((paramObject instanceof StringConverter))
      {
        paramObject = (StringConverter)paramObject;
        return this.enumClass.equals(((StringConverter)paramObject).enumClass);
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.enumClass.hashCode();
    }
    
    public String toString()
    {
      return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
    }
  }
  
  private static final class ValueOfFunction<T extends Enum<T>>
    implements Function<String, T>, Serializable
  {
    private static final long serialVersionUID = 0L;
    private final Class<T> enumClass;
    
    private ValueOfFunction(Class<T> paramClass)
    {
      this.enumClass = ((Class)Preconditions.checkNotNull(paramClass));
    }
    
    public T apply(String paramString)
    {
      try
      {
        paramString = Enum.valueOf(this.enumClass, paramString);
        return paramString;
      }
      catch (IllegalArgumentException paramString) {}
      return null;
    }
    
    public boolean equals(@Nullable Object paramObject)
    {
      return ((paramObject instanceof ValueOfFunction)) && (this.enumClass.equals(((ValueOfFunction)paramObject).enumClass));
    }
    
    public int hashCode()
    {
      return this.enumClass.hashCode();
    }
    
    public String toString()
    {
      return "Enums.valueOf(" + this.enumClass + ")";
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\repackaged\com\google\common\base\Enums.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */