package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@JacksonStdImpl
public class StdKeyDeserializer
  extends KeyDeserializer
  implements Serializable
{
  public static final int TYPE_BOOLEAN = 1;
  public static final int TYPE_BYTE = 2;
  public static final int TYPE_CALENDAR = 11;
  public static final int TYPE_CHAR = 4;
  public static final int TYPE_CLASS = 15;
  public static final int TYPE_CURRENCY = 16;
  public static final int TYPE_DATE = 10;
  public static final int TYPE_DOUBLE = 8;
  public static final int TYPE_FLOAT = 7;
  public static final int TYPE_INT = 5;
  public static final int TYPE_LOCALE = 9;
  public static final int TYPE_LONG = 6;
  public static final int TYPE_SHORT = 3;
  public static final int TYPE_URI = 13;
  public static final int TYPE_URL = 14;
  public static final int TYPE_UUID = 12;
  private static final long serialVersionUID = 1L;
  protected final FromStringDeserializer<?> _deser;
  protected final Class<?> _keyClass;
  protected final int _kind;
  
  protected StdKeyDeserializer(int paramInt, Class<?> paramClass)
  {
    this(paramInt, paramClass, null);
  }
  
  protected StdKeyDeserializer(int paramInt, Class<?> paramClass, FromStringDeserializer<?> paramFromStringDeserializer)
  {
    this._kind = paramInt;
    this._keyClass = paramClass;
    this._deser = paramFromStringDeserializer;
  }
  
  public static StdKeyDeserializer forType(Class<?> paramClass)
  {
    if ((paramClass == String.class) || (paramClass == Object.class)) {
      return StringKD.forType(paramClass);
    }
    int i;
    if (paramClass == UUID.class) {
      i = 12;
    }
    for (;;)
    {
      return new StdKeyDeserializer(i, paramClass);
      if (paramClass == Integer.class)
      {
        i = 5;
      }
      else if (paramClass == Long.class)
      {
        i = 6;
      }
      else if (paramClass == Date.class)
      {
        i = 10;
      }
      else if (paramClass == Calendar.class)
      {
        i = 11;
      }
      else if (paramClass == Boolean.class)
      {
        i = 1;
      }
      else if (paramClass == Byte.class)
      {
        i = 2;
      }
      else if (paramClass == Character.class)
      {
        i = 4;
      }
      else if (paramClass == Short.class)
      {
        i = 3;
      }
      else if (paramClass == Float.class)
      {
        i = 7;
      }
      else if (paramClass == Double.class)
      {
        i = 8;
      }
      else if (paramClass == URI.class)
      {
        i = 13;
      }
      else if (paramClass == URL.class)
      {
        i = 14;
      }
      else
      {
        if (paramClass != Class.class) {
          break;
        }
        i = 15;
      }
    }
    if (paramClass == Locale.class) {
      return new StdKeyDeserializer(9, paramClass, FromStringDeserializer.findDeserializer(Locale.class));
    }
    if (paramClass == Currency.class) {
      return new StdKeyDeserializer(16, paramClass, FromStringDeserializer.findDeserializer(Currency.class));
    }
    return null;
  }
  
  protected Object _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws Exception
  {
    switch (this._kind)
    {
    default: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 16: 
    case 10: 
    case 11: 
      do
      {
        return null;
        if ("true".equals(paramString)) {
          return Boolean.TRUE;
        }
        if ("false".equals(paramString)) {
          return Boolean.FALSE;
        }
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "value not 'true' or 'false'");
        int i = _parseInt(paramString);
        if ((i < -128) || (i > 255)) {
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "overflow, value can not be represented as 8-bit value");
        }
        return Byte.valueOf((byte)i);
        i = _parseInt(paramString);
        if ((i < 32768) || (i > 32767)) {
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "overflow, value can not be represented as 16-bit value");
        }
        return Short.valueOf((short)i);
        if (paramString.length() == 1) {
          return Character.valueOf(paramString.charAt(0));
        }
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "can only convert 1-character Strings");
        return Integer.valueOf(_parseInt(paramString));
        return Long.valueOf(_parseLong(paramString));
        return Float.valueOf((float)_parseDouble(paramString));
        return Double.valueOf(_parseDouble(paramString));
        try
        {
          Object localObject1 = this._deser._deserialize(paramString, paramDeserializationContext);
          return localObject1;
        }
        catch (IOException localIOException1)
        {
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "unable to parse key as locale");
        }
        try
        {
          Object localObject2 = this._deser._deserialize(paramString, paramDeserializationContext);
          return localObject2;
        }
        catch (IOException localIOException2)
        {
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "unable to parse key as currency");
        }
        return paramDeserializationContext.parseDate(paramString);
        paramString = paramDeserializationContext.parseDate(paramString);
      } while (paramString == null);
      return paramDeserializationContext.constructCalendar(paramString);
    case 12: 
      return UUID.fromString(paramString);
    case 13: 
      return URI.create(paramString);
    case 14: 
      return new URL(paramString);
    }
    try
    {
      Class localClass = paramDeserializationContext.findClass(paramString);
      return localClass;
    }
    catch (Exception localException)
    {
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "unable to parse key as Class");
    }
  }
  
  protected double _parseDouble(String paramString)
    throws IllegalArgumentException
  {
    return NumberInput.parseDouble(paramString);
  }
  
  protected int _parseInt(String paramString)
    throws IllegalArgumentException
  {
    return Integer.parseInt(paramString);
  }
  
  protected long _parseLong(String paramString)
    throws IllegalArgumentException
  {
    return Long.parseLong(paramString);
  }
  
  public Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1;
    if (paramString == null) {
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      try
      {
        Object localObject2 = _parse(paramString, paramDeserializationContext);
        localObject1 = localObject2;
        if (localObject2 == null) {
          if ((this._keyClass.isEnum()) && (paramDeserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))) {
            return null;
          }
        }
      }
      catch (Exception localException)
      {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation: " + localException.getMessage());
      }
    }
    throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation");
  }
  
  public Class<?> getKeyClass()
  {
    return this._keyClass;
  }
  
  static final class DelegatingKD
    extends KeyDeserializer
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer<?> _delegate;
    protected final Class<?> _keyClass;
    
    protected DelegatingKD(Class<?> paramClass, JsonDeserializer<?> paramJsonDeserializer)
    {
      this._keyClass = paramClass;
      this._delegate = paramJsonDeserializer;
    }
    
    public final Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Object localObject1;
      if (paramString == null) {
        localObject1 = null;
      }
      for (;;)
      {
        return localObject1;
        try
        {
          Object localObject2 = this._delegate.deserialize(paramDeserializationContext.getParser(), paramDeserializationContext);
          localObject1 = localObject2;
          if (localObject2 != null) {
            continue;
          }
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation");
        }
        catch (Exception localException)
        {
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation: " + localException.getMessage());
        }
      }
    }
    
    public Class<?> getKeyClass()
    {
      return this._keyClass;
    }
  }
  
  @JacksonStdImpl
  static final class EnumKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final EnumResolver _byNameResolver;
    protected EnumResolver _byToStringResolver;
    protected final AnnotatedMethod _factory;
    
    protected EnumKD(EnumResolver paramEnumResolver, AnnotatedMethod paramAnnotatedMethod)
    {
      super(paramEnumResolver.getEnumClass());
      this._byNameResolver = paramEnumResolver;
      this._factory = paramAnnotatedMethod;
    }
    
    private EnumResolver _getToStringResolver()
    {
      EnumResolver localEnumResolver1 = this._byToStringResolver;
      if (localEnumResolver1 == null) {
        try
        {
          localEnumResolver1 = EnumResolver.constructUnsafeUsingToString(this._byNameResolver.getEnumClass());
          return localEnumResolver1;
        }
        finally {}
      }
      return localEnumResolver2;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      if (this._factory != null) {
        try
        {
          Object localObject1 = this._factory.call1(paramString);
          return localObject1;
        }
        catch (Exception localException)
        {
          ClassUtil.unwrapAndThrowAsIAE(localException);
        }
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {}
      for (EnumResolver localEnumResolver = _getToStringResolver();; localEnumResolver = this._byNameResolver)
      {
        Enum localEnum = localEnumResolver.findEnum(paramString);
        Object localObject2 = localEnum;
        if (localEnum != null) {
          break;
        }
        localObject2 = localEnum;
        if (paramDeserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
          break;
        }
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not one of values excepted for Enum class: " + localEnumResolver.getEnumIds());
      }
    }
  }
  
  static final class StringCtorKeyDeserializer
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final Constructor<?> _ctor;
    
    public StringCtorKeyDeserializer(Constructor<?> paramConstructor)
    {
      super(paramConstructor.getDeclaringClass());
      this._ctor = paramConstructor;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws Exception
    {
      return this._ctor.newInstance(new Object[] { paramString });
    }
  }
  
  static final class StringFactoryKeyDeserializer
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    final Method _factoryMethod;
    
    public StringFactoryKeyDeserializer(Method paramMethod)
    {
      super(paramMethod.getDeclaringClass());
      this._factoryMethod = paramMethod;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws Exception
    {
      return this._factoryMethod.invoke(null, new Object[] { paramString });
    }
  }
  
  @JacksonStdImpl
  static final class StringKD
    extends StdKeyDeserializer
  {
    private static final StringKD sObject = new StringKD(Object.class);
    private static final StringKD sString = new StringKD(String.class);
    private static final long serialVersionUID = 1L;
    
    private StringKD(Class<?> paramClass)
    {
      super(paramClass);
    }
    
    public static StringKD forType(Class<?> paramClass)
    {
      if (paramClass == String.class) {
        return sString;
      }
      if (paramClass == Object.class) {
        return sObject;
      }
      return new StringKD(paramClass);
    }
    
    public Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      return paramString;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\StdKeyDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */