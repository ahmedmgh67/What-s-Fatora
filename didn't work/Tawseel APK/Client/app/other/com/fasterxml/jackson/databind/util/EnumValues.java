package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EnumValues
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private transient EnumMap<?, SerializableString> _asMap;
  private final Class<Enum<?>> _enumClass;
  private final SerializableString[] _textual;
  private final Enum<?>[] _values;
  
  private EnumValues(Class<Enum<?>> paramClass, SerializableString[] paramArrayOfSerializableString)
  {
    this._enumClass = paramClass;
    this._values = ((Enum[])paramClass.getEnumConstants());
    this._textual = paramArrayOfSerializableString;
  }
  
  public static EnumValues construct(SerializationConfig paramSerializationConfig, Class<Enum<?>> paramClass)
  {
    if (paramSerializationConfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
      return constructFromToString(paramSerializationConfig, paramClass);
    }
    return constructFromName(paramSerializationConfig, paramClass);
  }
  
  public static EnumValues constructFromName(MapperConfig<?> paramMapperConfig, Class<Enum<?>> paramClass)
  {
    Object localObject = ClassUtil.findEnumType(paramClass);
    Enum[] arrayOfEnum = (Enum[])((Class)localObject).getEnumConstants();
    if (arrayOfEnum == null) {
      throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
    }
    String[] arrayOfString = paramMapperConfig.getAnnotationIntrospector().findEnumValues((Class)localObject, arrayOfEnum, new String[arrayOfEnum.length]);
    SerializableString[] arrayOfSerializableString = new SerializableString[arrayOfEnum.length];
    int i = 0;
    int j = arrayOfEnum.length;
    while (i < j)
    {
      Enum localEnum = arrayOfEnum[i];
      String str = arrayOfString[i];
      localObject = str;
      if (str == null) {
        localObject = localEnum.name();
      }
      arrayOfSerializableString[localEnum.ordinal()] = paramMapperConfig.compileString((String)localObject);
      i += 1;
    }
    return new EnumValues(paramClass, arrayOfSerializableString);
  }
  
  public static EnumValues constructFromToString(MapperConfig<?> paramMapperConfig, Class<Enum<?>> paramClass)
  {
    Enum[] arrayOfEnum = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null)
    {
      SerializableString[] arrayOfSerializableString = new SerializableString[arrayOfEnum.length];
      int j = arrayOfEnum.length;
      int i = 0;
      while (i < j)
      {
        Enum localEnum = arrayOfEnum[i];
        arrayOfSerializableString[localEnum.ordinal()] = paramMapperConfig.compileString(localEnum.toString());
        i += 1;
      }
      return new EnumValues(paramClass, arrayOfSerializableString);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public List<Enum<?>> enums()
  {
    return Arrays.asList(this._values);
  }
  
  public Class<Enum<?>> getEnumClass()
  {
    return this._enumClass;
  }
  
  public EnumMap<?, SerializableString> internalMap()
  {
    Object localObject2 = this._asMap;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new LinkedHashMap();
      localObject2 = this._values;
      int j = localObject2.length;
      int i = 0;
      while (i < j)
      {
        Object localObject3 = localObject2[i];
        ((Map)localObject1).put(localObject3, this._textual[localObject3.ordinal()]);
        i += 1;
      }
      localObject1 = new EnumMap((Map)localObject1);
    }
    return (EnumMap<?, SerializableString>)localObject1;
  }
  
  public SerializableString serializedValueFor(Enum<?> paramEnum)
  {
    return this._textual[paramEnum.ordinal()];
  }
  
  public Collection<SerializableString> values()
  {
    return Arrays.asList(this._textual);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\EnumValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */