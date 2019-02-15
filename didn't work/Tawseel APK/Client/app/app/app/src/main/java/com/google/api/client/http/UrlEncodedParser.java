package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UrlEncodedParser
  implements ObjectParser
{
  public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
  public static final String MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded").setCharsetParameter(Charsets.UTF_8).build();
  
  public static void parse(Reader paramReader, Object paramObject)
    throws IOException
  {
    Object localObject1 = paramObject.getClass();
    ClassInfo localClassInfo = ClassInfo.of((Class)localObject1);
    List localList = Arrays.asList(new Type[] { localObject1 });
    GenericData localGenericData;
    ArrayValueMap localArrayValueMap;
    Object localObject3;
    Object localObject2;
    int i;
    if (GenericData.class.isAssignableFrom((Class)localObject1))
    {
      localGenericData = (GenericData)paramObject;
      if (!Map.class.isAssignableFrom((Class)localObject1)) {
        break label147;
      }
      localObject1 = (Map)paramObject;
      localArrayValueMap = new ArrayValueMap(paramObject);
      localObject3 = new StringWriter();
      localObject2 = new StringWriter();
      i = 1;
    }
    for (;;)
    {
      int j = paramReader.read();
      switch (j)
      {
      default: 
        if (i != 0)
        {
          ((StringWriter)localObject3).write(j);
          continue;
          localGenericData = null;
          break;
          localObject1 = null;
        }
        break;
      case -1: 
      case 38: 
        Object localObject4 = CharEscapers.decodeUri(((StringWriter)localObject3).toString());
        String str;
        FieldInfo localFieldInfo;
        if (((String)localObject4).length() != 0)
        {
          str = CharEscapers.decodeUri(((StringWriter)localObject2).toString());
          localFieldInfo = localClassInfo.getFieldInfo((String)localObject4);
          if (localFieldInfo == null) {
            break label387;
          }
          localObject4 = Data.resolveWildcardTypeOrTypeVariable(localList, localFieldInfo.getGenericType());
          if (!Types.isArray((Type)localObject4)) {
            break label279;
          }
          localObject2 = Types.getRawArrayComponentType(localList, Types.getArrayComponentType((Type)localObject4));
          localArrayValueMap.put(localFieldInfo.getField(), (Class)localObject2, parseValue((Type)localObject2, localList, str));
        }
        while (localObject1 == null) {
          for (;;)
          {
            i = 1;
            localObject3 = new StringWriter();
            localObject2 = new StringWriter();
            if (j != -1) {
              break;
            }
            localArrayValueMap.setValues();
            return;
            if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(localList, (Type)localObject4), Iterable.class))
            {
              localObject3 = (Collection)localFieldInfo.getValue(paramObject);
              localObject2 = localObject3;
              if (localObject3 == null)
              {
                localObject2 = Data.newCollectionInstance((Type)localObject4);
                localFieldInfo.setValue(paramObject, localObject2);
              }
              if (localObject4 == Object.class) {}
              for (localObject3 = null;; localObject3 = Types.getIterableParameter((Type)localObject4))
              {
                ((Collection)localObject2).add(parseValue((Type)localObject3, localList, str));
                break;
              }
            }
            localFieldInfo.setValue(paramObject, parseValue((Type)localObject4, localList, str));
          }
        }
        localObject3 = (ArrayList)((Map)localObject1).get(localObject4);
        localObject2 = localObject3;
        if (localObject3 == null)
        {
          localObject2 = new ArrayList();
          if (localGenericData == null) {
            break label450;
          }
          localGenericData.set((String)localObject4, localObject2);
        }
        for (;;)
        {
          ((ArrayList)localObject2).add(str);
          break;
          ((Map)localObject1).put(localObject4, localObject2);
        }
      case 61: 
        label147:
        label279:
        label387:
        label450:
        i = 0;
        continue;
        ((StringWriter)localObject2).write(j);
      }
    }
  }
  
  public static void parse(String paramString, Object paramObject)
  {
    if (paramString == null) {
      return;
    }
    try
    {
      parse(new StringReader(paramString), paramObject);
      return;
    }
    catch (IOException paramString)
    {
      throw Throwables.propagate(paramString);
    }
  }
  
  private static Object parseValue(Type paramType, List<Type> paramList, String paramString)
  {
    return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(paramList, paramType), paramString);
  }
  
  public <T> T parseAndClose(InputStream paramInputStream, Charset paramCharset, Class<T> paramClass)
    throws IOException
  {
    return (T)parseAndClose(new InputStreamReader(paramInputStream, paramCharset), paramClass);
  }
  
  public Object parseAndClose(InputStream paramInputStream, Charset paramCharset, Type paramType)
    throws IOException
  {
    return parseAndClose(new InputStreamReader(paramInputStream, paramCharset), paramType);
  }
  
  public <T> T parseAndClose(Reader paramReader, Class<T> paramClass)
    throws IOException
  {
    return (T)parseAndClose(paramReader, paramClass);
  }
  
  public Object parseAndClose(Reader paramReader, Type paramType)
    throws IOException
  {
    Preconditions.checkArgument(paramType instanceof Class, "dataType has to be of type Class<?>");
    paramType = Types.newInstance((Class)paramType);
    parse(new BufferedReader(paramReader), paramType);
    return paramType;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\UrlEncodedParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */