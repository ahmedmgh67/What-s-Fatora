package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Types;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

@Beta
final class AuthKeyValueParser
  implements ObjectParser
{
  public static final AuthKeyValueParser INSTANCE = new AuthKeyValueParser();
  
  public String getContentType()
  {
    return "text/plain";
  }
  
  public <T> T parse(HttpResponse paramHttpResponse, Class<T> paramClass)
    throws IOException
  {
    paramHttpResponse.setContentLoggingLimit(0);
    paramHttpResponse = paramHttpResponse.getContent();
    try
    {
      paramClass = parse(paramHttpResponse, paramClass);
      return paramClass;
    }
    finally
    {
      paramHttpResponse.close();
    }
  }
  
  public <T> T parse(InputStream paramInputStream, Class<T> paramClass)
    throws IOException
  {
    ClassInfo localClassInfo = ClassInfo.of(paramClass);
    Object localObject1 = Types.newInstance(paramClass);
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    for (;;)
    {
      paramInputStream = localBufferedReader.readLine();
      if (paramInputStream == null) {
        return (T)localObject1;
      }
      int i = paramInputStream.indexOf('=');
      Object localObject2 = paramInputStream.substring(0, i);
      paramInputStream = paramInputStream.substring(i + 1);
      Field localField = localClassInfo.getField((String)localObject2);
      if (localField != null)
      {
        localObject2 = localField.getType();
        if ((localObject2 == Boolean.TYPE) || (localObject2 == Boolean.class)) {
          paramInputStream = Boolean.valueOf(paramInputStream);
        }
        for (;;)
        {
          FieldInfo.setFieldValue(localField, localObject1, paramInputStream);
          break;
        }
      }
      if (GenericData.class.isAssignableFrom(paramClass)) {
        ((GenericData)localObject1).set((String)localObject2, paramInputStream);
      } else if (Map.class.isAssignableFrom(paramClass)) {
        ((Map)localObject1).put(localObject2, paramInputStream);
      }
    }
  }
  
  public <T> T parseAndClose(InputStream paramInputStream, Charset paramCharset, Class<T> paramClass)
    throws IOException
  {
    return (T)parseAndClose(new InputStreamReader(paramInputStream, paramCharset), paramClass);
  }
  
  public Object parseAndClose(InputStream paramInputStream, Charset paramCharset, Type paramType)
  {
    throw new UnsupportedOperationException("Type-based parsing is not yet supported -- use Class<T> instead");
  }
  
  public <T> T parseAndClose(Reader paramReader, Class<T> paramClass)
    throws IOException
  {
    for (;;)
    {
      Object localObject2;
      Object localObject1;
      Object localObject3;
      try
      {
        ClassInfo localClassInfo = ClassInfo.of(paramClass);
        localObject2 = Types.newInstance(paramClass);
        BufferedReader localBufferedReader = new BufferedReader(paramReader);
        localObject1 = localBufferedReader.readLine();
        if (localObject1 == null) {
          return (T)localObject2;
        }
        int i = ((String)localObject1).indexOf('=');
        localObject3 = ((String)localObject1).substring(0, i);
        localObject1 = ((String)localObject1).substring(i + 1);
        Field localField = localClassInfo.getField((String)localObject3);
        if (localField != null)
        {
          localObject3 = localField.getType();
          if ((localObject3 == Boolean.TYPE) || (localObject3 == Boolean.class))
          {
            localObject1 = Boolean.valueOf((String)localObject1);
            FieldInfo.setFieldValue(localField, localObject2, localObject1);
            continue;
          }
          continue;
        }
      }
      finally
      {
        paramReader.close();
      }
      if (GenericData.class.isAssignableFrom(paramClass)) {
        ((GenericData)localObject2).set((String)localObject3, localObject1);
      } else if (Map.class.isAssignableFrom(paramClass)) {
        ((Map)localObject2).put(localObject3, localObject1);
      }
    }
  }
  
  public Object parseAndClose(Reader paramReader, Type paramType)
  {
    throw new UnsupportedOperationException("Type-based parsing is not yet supported -- use Class<T> instead");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\clientlogin\AuthKeyValueParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */