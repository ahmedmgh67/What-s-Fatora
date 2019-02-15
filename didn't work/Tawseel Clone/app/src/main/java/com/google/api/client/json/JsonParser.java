package com.google.api.client.json;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class JsonParser
{
  private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap();
  private static final Lock lock = new ReentrantLock();
  
  private static Field getCachedTypemapFieldFor(Class<?> paramClass)
  {
    if (paramClass == null) {
      return null;
    }
    lock.lock();
    for (;;)
    {
      try
      {
        if (cachedTypemapFields.containsKey(paramClass))
        {
          paramClass = (Field)cachedTypemapFields.get(paramClass);
          return paramClass;
        }
        Object localObject1 = null;
        Iterator localIterator = ClassInfo.of(paramClass).getFieldInfos().iterator();
        if (localIterator.hasNext())
        {
          Field localField = ((FieldInfo)localIterator.next()).getField();
          Object localObject2 = (JsonPolymorphicTypeMap)localField.getAnnotation(JsonPolymorphicTypeMap.class);
          if (localObject2 == null) {
            continue;
          }
          if (localObject1 == null)
          {
            bool = true;
            Preconditions.checkArgument(bool, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", new Object[] { paramClass });
            Preconditions.checkArgument(Data.isPrimitive(localField.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", new Object[] { paramClass, localField.getType() });
            localObject2 = ((JsonPolymorphicTypeMap)localObject2).typeDefinitions();
            HashSet localHashSet = Sets.newHashSet();
            if (localObject2.length <= 0) {
              break label278;
            }
            bool = true;
            Preconditions.checkArgument(bool, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
            int j = localObject2.length;
            int i = 0;
            localObject1 = localField;
            if (i >= j) {
              continue;
            }
            localObject1 = localObject2[i];
            Preconditions.checkArgument(localHashSet.add(((JsonPolymorphicTypeMap.TypeDef)localObject1).key()), "Class contains two @TypeDef annotations with identical key: %s", new Object[] { ((JsonPolymorphicTypeMap.TypeDef)localObject1).key() });
            i += 1;
            continue;
          }
        }
        else
        {
          cachedTypemapFields.put(paramClass, localObject1);
          return (Field)localObject1;
        }
      }
      finally
      {
        lock.unlock();
      }
      boolean bool = false;
      continue;
      label278:
      bool = false;
    }
  }
  
  private void parse(ArrayList<Type> paramArrayList, Object paramObject, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    if ((paramObject instanceof GenericJson)) {
      ((GenericJson)paramObject).setFactory(getFactory());
    }
    Object localObject2 = startParsingObjectOrArray();
    Class localClass = paramObject.getClass();
    ClassInfo localClassInfo = ClassInfo.of(localClass);
    boolean bool = GenericData.class.isAssignableFrom(localClass);
    Object localObject1 = localObject2;
    if (!bool)
    {
      localObject1 = localObject2;
      if (Map.class.isAssignableFrom(localClass))
      {
        parseMap(null, (Map)paramObject, Types.getMapValueParameter(localClass), paramArrayList, paramCustomizeJsonParser);
        return;
        label86:
        localObject2 = ((FieldInfo)localObject1).getField();
        int i = paramArrayList.size();
        paramArrayList.add(((Field)localObject2).getGenericType());
        localObject2 = parseValue((Field)localObject2, ((FieldInfo)localObject1).getGenericType(), paramArrayList, paramObject, paramCustomizeJsonParser, true);
        paramArrayList.remove(i);
        ((FieldInfo)localObject1).setValue(paramObject, localObject2);
      }
    }
    for (;;)
    {
      localObject1 = nextToken();
      if (localObject1 != JsonToken.FIELD_NAME) {
        break;
      }
      localObject2 = getText();
      nextToken();
      if ((paramCustomizeJsonParser != null) && (paramCustomizeJsonParser.stopAt(paramObject, (String)localObject2))) {
        break;
      }
      localObject1 = localClassInfo.getFieldInfo((String)localObject2);
      if (localObject1 != null)
      {
        if ((!((FieldInfo)localObject1).isFinal()) || (((FieldInfo)localObject1).isPrimitive())) {
          break label86;
        }
        throw new IllegalArgumentException("final array/object fields are not supported");
      }
      if (bool)
      {
        ((GenericData)paramObject).set((String)localObject2, parseValue(null, null, paramArrayList, paramObject, paramCustomizeJsonParser, true));
      }
      else
      {
        if (paramCustomizeJsonParser != null) {
          paramCustomizeJsonParser.handleUnrecognizedKey(paramObject, (String)localObject2);
        }
        skipChildren();
      }
    }
  }
  
  private <T> void parseArray(Field paramField, Collection<T> paramCollection, Type paramType, ArrayList<Type> paramArrayList, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    for (JsonToken localJsonToken = startParsingObjectOrArray(); localJsonToken != JsonToken.END_ARRAY; localJsonToken = nextToken()) {
      paramCollection.add(parseValue(paramField, paramType, paramArrayList, paramCollection, paramCustomizeJsonParser, true));
    }
  }
  
  private void parseMap(Field paramField, Map<String, Object> paramMap, Type paramType, ArrayList<Type> paramArrayList, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    for (Object localObject = startParsingObjectOrArray();; localObject = nextToken())
    {
      if (localObject == JsonToken.FIELD_NAME)
      {
        localObject = getText();
        nextToken();
        if ((paramCustomizeJsonParser == null) || (!paramCustomizeJsonParser.stopAt(paramMap, (String)localObject))) {}
      }
      else
      {
        return;
      }
      paramMap.put(localObject, parseValue(paramField, paramType, paramArrayList, paramMap, paramCustomizeJsonParser, true));
    }
  }
  
  private final Object parseValue(Field paramField, Type paramType, ArrayList<Type> paramArrayList, Object paramObject, CustomizeJsonParser paramCustomizeJsonParser, boolean paramBoolean)
    throws IOException
  {
    Type localType = Data.resolveWildcardTypeOrTypeVariable(paramArrayList, paramType);
    Object localObject1;
    if ((localType instanceof Class))
    {
      paramType = (Class)localType;
      localObject1 = paramType;
      if ((localType instanceof ParameterizedType)) {
        localObject1 = Types.getRawClass((ParameterizedType)localType);
      }
      if (localObject1 != Void.class) {
        break label66;
      }
      skipChildren();
      paramObject = null;
    }
    label66:
    boolean bool;
    label278:
    Object localObject2;
    label425:
    label454:
    int i;
    label501:
    label514:
    int j;
    label564:
    label585:
    label594:
    do
    {
      return paramObject;
      paramType = null;
      break;
      paramType = getCurrentToken();
      try
      {
        switch (getCurrentToken())
        {
        case ???: 
          throw new IllegalArgumentException("unexpected JSON node type: " + paramType);
        }
      }
      catch (IllegalArgumentException paramType)
      {
        paramArrayList = new StringBuilder();
        paramObject = getCurrentName();
        if (paramObject != null) {
          paramArrayList.append("key ").append((String)paramObject);
        }
        if (paramField != null)
        {
          if (paramObject != null) {
            paramArrayList.append(", ");
          }
          paramArrayList.append("field ").append(paramField);
        }
        throw new IllegalArgumentException(paramArrayList.toString(), paramType);
      }
      bool = Types.isArray(localType);
      if ((localType == null) || (bool)) {
        break label1332;
      }
      if ((localObject1 == null) || (!Types.isAssignableToOrFrom((Class)localObject1, Collection.class))) {
        break label1338;
      }
      break label1332;
      Preconditions.checkArgument(paramBoolean, "expected collection or array type but got %s", new Object[] { localType });
      localObject2 = null;
      paramType = (Type)localObject2;
      if (paramCustomizeJsonParser != null)
      {
        paramType = (Type)localObject2;
        if (paramField != null) {
          paramType = paramCustomizeJsonParser.newInstanceForArray(paramObject, paramField);
        }
      }
      paramObject = paramType;
      if (paramType == null) {
        paramObject = Data.newCollectionInstance(localType);
      }
      localObject2 = null;
      if (bool) {
        paramType = Types.getArrayComponentType(localType);
      }
      for (;;)
      {
        paramType = Data.resolveWildcardTypeOrTypeVariable(paramArrayList, paramType);
        parseArray(paramField, (Collection)paramObject, paramType, paramArrayList, paramCustomizeJsonParser);
        if (!bool) {
          break;
        }
        return Types.toArray((Collection)paramObject, Types.getRawArrayComponentType(paramArrayList, paramType));
        paramType = (Type)localObject2;
        if (localObject1 != null)
        {
          paramType = (Type)localObject2;
          if (Iterable.class.isAssignableFrom((Class)localObject1)) {
            paramType = Types.getIterableParameter(localType);
          }
        }
      }
      if (Types.isArray(localType)) {
        break label1347;
      }
      bool = true;
      Preconditions.checkArgument(bool, "expected object or map type but got %s", new Object[] { localType });
      if (!paramBoolean) {
        break label1353;
      }
      localObject2 = getCachedTypemapFieldFor((Class)localObject1);
      Object localObject3 = null;
      paramType = (Type)localObject3;
      if (localObject1 != null)
      {
        paramType = (Type)localObject3;
        if (paramCustomizeJsonParser != null) {
          paramType = paramCustomizeJsonParser.newInstanceForObject(paramObject, (Class)localObject1);
        }
      }
      if ((localObject1 == null) || (!Types.isAssignableToOrFrom((Class)localObject1, Map.class))) {
        break label1359;
      }
      i = 1;
      if (localObject2 == null) {
        break label1365;
      }
      paramType = new GenericJson();
      for (;;)
      {
        j = paramArrayList.size();
        if (localType != null) {
          paramArrayList.add(localType);
        }
        if ((i == 0) || (GenericData.class.isAssignableFrom((Class)localObject1))) {
          break;
        }
        if (!Map.class.isAssignableFrom((Class)localObject1)) {
          break label1382;
        }
        paramObject = Types.getMapValueParameter(localType);
        if (paramObject == null) {
          break;
        }
        parseMap(paramField, (Map)paramType, (Type)paramObject, paramArrayList, paramCustomizeJsonParser);
        return paramType;
        paramType = Data.newMapInstance((Class)localObject1);
        continue;
        paramType = Types.newInstance((Class)localObject1);
      }
      parse(paramArrayList, paramType, paramCustomizeJsonParser);
      if (localType != null) {
        paramArrayList.remove(j);
      }
      paramObject = paramType;
    } while (localObject2 == null);
    paramObject = ((GenericJson)paramType).get(((Field)localObject2).getName());
    if (paramObject != null)
    {
      paramBoolean = true;
      label653:
      Preconditions.checkArgument(paramBoolean, "No value specified for @JsonPolymorphicTypeMap field");
      localObject1 = paramObject.toString();
      paramObject = (JsonPolymorphicTypeMap)((Field)localObject2).getAnnotation(JsonPolymorphicTypeMap.class);
      paramCustomizeJsonParser = null;
      localObject2 = ((JsonPolymorphicTypeMap)paramObject).typeDefinitions();
      j = localObject2.length;
      i = 0;
      label700:
      paramObject = paramCustomizeJsonParser;
      if (i < j)
      {
        paramObject = localObject2[i];
        if (!((JsonPolymorphicTypeMap.TypeDef)paramObject).key().equals(localObject1)) {
          break label1405;
        }
        paramObject = ((JsonPolymorphicTypeMap.TypeDef)paramObject).ref();
        break label1388;
        label745:
        Preconditions.checkArgument(paramBoolean, "No TypeDef annotation found with key: " + (String)localObject1);
        paramCustomizeJsonParser = getFactory();
        paramType = paramCustomizeJsonParser.createJsonParser(paramCustomizeJsonParser.toString(paramType));
        paramType.startParsing();
        return paramType.parseValue(paramField, (Type)paramObject, paramArrayList, null, null, false);
        if ((localType == null) || (localObject1 == Boolean.TYPE)) {
          break label1420;
        }
        if ((localObject1 == null) || (!((Class)localObject1).isAssignableFrom(Boolean.class))) {
          break label1426;
        }
        break label1420;
        label838:
        Preconditions.checkArgument(paramBoolean, "expected type Boolean or boolean but got %s", new Object[] { localType });
        if (paramType == JsonToken.VALUE_TRUE) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
        if (paramField == null) {
          break label1432;
        }
        if (paramField.getAnnotation(JsonString.class) != null) {
          break label1438;
        }
        break label1432;
        label887:
        Preconditions.checkArgument(paramBoolean, "number type formatted as a JSON number cannot use @JsonString annotation");
        if ((localObject1 == null) || (((Class)localObject1).isAssignableFrom(BigDecimal.class))) {
          return getDecimalValue();
        }
        if (localObject1 == BigInteger.class) {
          return getBigIntegerValue();
        }
        if ((localObject1 == Double.class) || (localObject1 == Double.TYPE)) {
          return Double.valueOf(getDoubleValue());
        }
        if ((localObject1 == Long.class) || (localObject1 == Long.TYPE)) {
          return Long.valueOf(getLongValue());
        }
        if ((localObject1 == Float.class) || (localObject1 == Float.TYPE)) {
          return Float.valueOf(getFloatValue());
        }
        if ((localObject1 == Integer.class) || (localObject1 == Integer.TYPE)) {
          return Integer.valueOf(getIntValue());
        }
        if ((localObject1 == Short.class) || (localObject1 == Short.TYPE)) {
          return Short.valueOf(getShortValue());
        }
        if ((localObject1 == Byte.class) || (localObject1 == Byte.TYPE)) {
          return Byte.valueOf(getByteValue());
        }
        throw new IllegalArgumentException("expected numeric type but got " + localType);
        paramType = getText().trim().toLowerCase(Locale.US);
        if (((localObject1 != Float.TYPE) && (localObject1 != Float.class) && (localObject1 != Double.TYPE) && (localObject1 != Double.class)) || ((!paramType.equals("nan")) && (!paramType.equals("infinity")) && (!paramType.equals("-infinity"))))
        {
          if ((localObject1 == null) || (!Number.class.isAssignableFrom((Class)localObject1))) {
            break label1444;
          }
          if ((paramField == null) || (paramField.getAnnotation(JsonString.class) == null)) {
            break label1450;
          }
          break label1444;
          label1211:
          Preconditions.checkArgument(paramBoolean, "number field formatted as a JSON string must use the @JsonString annotation");
        }
        return Data.parsePrimitiveValue(localType, getText());
        if (localObject1 == null) {
          break label1456;
        }
        if (((Class)localObject1).isPrimitive()) {
          break label1462;
        }
        break label1456;
      }
    }
    for (;;)
    {
      Preconditions.checkArgument(paramBoolean, "primitive number field but found a JSON null");
      if ((localObject1 != null) && ((((Class)localObject1).getModifiers() & 0x600) != 0))
      {
        if (Types.isAssignableToOrFrom((Class)localObject1, Collection.class)) {
          return Data.nullOf(Data.newCollectionInstance(localType).getClass());
        }
        if (Types.isAssignableToOrFrom((Class)localObject1, Map.class)) {
          return Data.nullOf(Data.newMapInstance((Class)localObject1).getClass());
        }
      }
      paramType = Data.nullOf(Types.getRawArrayComponentType(paramArrayList, localType));
      return paramType;
      label1332:
      label1338:
      label1347:
      label1353:
      label1359:
      label1365:
      do
      {
        break label514;
        break;
        paramBoolean = true;
        break label278;
        paramBoolean = false;
        break label278;
        return paramObject;
        bool = false;
        break label425;
        localObject2 = null;
        break label454;
        i = 0;
        break label501;
      } while (paramType != null);
      if (i != 0) {
        break label585;
      }
      if (localObject1 != null) {
        break label594;
      }
      break label585;
      label1382:
      paramObject = null;
      break label564;
      label1388:
      if (paramObject != null)
      {
        paramBoolean = true;
        break label745;
        paramBoolean = false;
        break label653;
        label1405:
        i += 1;
        break label700;
      }
      paramBoolean = false;
      break label745;
      label1420:
      paramBoolean = true;
      break label838;
      label1426:
      paramBoolean = false;
      break label838;
      label1432:
      paramBoolean = true;
      break label887;
      label1438:
      paramBoolean = false;
      break label887;
      label1444:
      paramBoolean = true;
      break label1211;
      label1450:
      paramBoolean = false;
      break label1211;
      label1456:
      paramBoolean = true;
      continue;
      label1462:
      paramBoolean = false;
    }
  }
  
  private JsonToken startParsing()
    throws IOException
  {
    JsonToken localJsonToken2 = getCurrentToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == null) {
      localJsonToken1 = nextToken();
    }
    if (localJsonToken1 != null) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "no JSON input found");
      return localJsonToken1;
    }
  }
  
  private JsonToken startParsingObjectOrArray()
    throws IOException
  {
    JsonToken localJsonToken = startParsing();
    switch (localJsonToken)
    {
    default: 
      return localJsonToken;
    case ???: 
      localJsonToken = nextToken();
      if ((localJsonToken == JsonToken.FIELD_NAME) || (localJsonToken == JsonToken.END_OBJECT)) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool, localJsonToken);
        return localJsonToken;
      }
    }
    return nextToken();
  }
  
  public abstract void close()
    throws IOException;
  
  public abstract BigInteger getBigIntegerValue()
    throws IOException;
  
  public abstract byte getByteValue()
    throws IOException;
  
  public abstract String getCurrentName()
    throws IOException;
  
  public abstract JsonToken getCurrentToken();
  
  public abstract BigDecimal getDecimalValue()
    throws IOException;
  
  public abstract double getDoubleValue()
    throws IOException;
  
  public abstract JsonFactory getFactory();
  
  public abstract float getFloatValue()
    throws IOException;
  
  public abstract int getIntValue()
    throws IOException;
  
  public abstract long getLongValue()
    throws IOException;
  
  public abstract short getShortValue()
    throws IOException;
  
  public abstract String getText()
    throws IOException;
  
  public abstract JsonToken nextToken()
    throws IOException;
  
  public final <T> T parse(Class<T> paramClass)
    throws IOException
  {
    return (T)parse(paramClass, null);
  }
  
  @Beta
  public final <T> T parse(Class<T> paramClass, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    return (T)parse(paramClass, false, paramCustomizeJsonParser);
  }
  
  public Object parse(Type paramType, boolean paramBoolean)
    throws IOException
  {
    return parse(paramType, paramBoolean, null);
  }
  
  @Beta
  public Object parse(Type paramType, boolean paramBoolean, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    try
    {
      if (!Void.class.equals(paramType)) {
        startParsing();
      }
      paramType = parseValue(null, paramType, new ArrayList(), null, paramCustomizeJsonParser, true);
      return paramType;
    }
    finally
    {
      if (paramBoolean) {
        close();
      }
    }
  }
  
  public final void parse(Object paramObject)
    throws IOException
  {
    parse(paramObject, null);
  }
  
  @Beta
  public final void parse(Object paramObject, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObject.getClass());
    parse(localArrayList, paramObject, paramCustomizeJsonParser);
  }
  
  public final <T> T parseAndClose(Class<T> paramClass)
    throws IOException
  {
    return (T)parseAndClose(paramClass, null);
  }
  
  @Beta
  public final <T> T parseAndClose(Class<T> paramClass, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    try
    {
      paramClass = parse(paramClass, paramCustomizeJsonParser);
      return paramClass;
    }
    finally
    {
      close();
    }
  }
  
  public final void parseAndClose(Object paramObject)
    throws IOException
  {
    parseAndClose(paramObject, null);
  }
  
  @Beta
  public final void parseAndClose(Object paramObject, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    try
    {
      parse(paramObject, paramCustomizeJsonParser);
      return;
    }
    finally
    {
      close();
    }
  }
  
  public final <T> Collection<T> parseArray(Class<?> paramClass, Class<T> paramClass1)
    throws IOException
  {
    return parseArray(paramClass, paramClass1, null);
  }
  
  @Beta
  public final <T> Collection<T> parseArray(Class<?> paramClass, Class<T> paramClass1, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    paramClass = Data.newCollectionInstance(paramClass);
    parseArray(paramClass, paramClass1, paramCustomizeJsonParser);
    return paramClass;
  }
  
  public final <T> void parseArray(Collection<? super T> paramCollection, Class<T> paramClass)
    throws IOException
  {
    parseArray(paramCollection, paramClass, null);
  }
  
  @Beta
  public final <T> void parseArray(Collection<? super T> paramCollection, Class<T> paramClass, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    parseArray(null, paramCollection, paramClass, new ArrayList(), paramCustomizeJsonParser);
  }
  
  public final <T> Collection<T> parseArrayAndClose(Class<?> paramClass, Class<T> paramClass1)
    throws IOException
  {
    return parseArrayAndClose(paramClass, paramClass1, null);
  }
  
  @Beta
  public final <T> Collection<T> parseArrayAndClose(Class<?> paramClass, Class<T> paramClass1, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    try
    {
      paramClass = parseArray(paramClass, paramClass1, paramCustomizeJsonParser);
      return paramClass;
    }
    finally
    {
      close();
    }
  }
  
  public final <T> void parseArrayAndClose(Collection<? super T> paramCollection, Class<T> paramClass)
    throws IOException
  {
    parseArrayAndClose(paramCollection, paramClass, null);
  }
  
  @Beta
  public final <T> void parseArrayAndClose(Collection<? super T> paramCollection, Class<T> paramClass, CustomizeJsonParser paramCustomizeJsonParser)
    throws IOException
  {
    try
    {
      parseArray(paramCollection, paramClass, paramCustomizeJsonParser);
      return;
    }
    finally
    {
      close();
    }
  }
  
  public abstract JsonParser skipChildren()
    throws IOException;
  
  public final String skipToKey(Set<String> paramSet)
    throws IOException
  {
    for (Object localObject = startParsingObjectOrArray(); localObject == JsonToken.FIELD_NAME; localObject = nextToken())
    {
      localObject = getText();
      nextToken();
      if (paramSet.contains(localObject)) {
        return (String)localObject;
      }
      skipChildren();
    }
    return null;
  }
  
  public final void skipToKey(String paramString)
    throws IOException
  {
    skipToKey(Collections.singleton(paramString));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\JsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */