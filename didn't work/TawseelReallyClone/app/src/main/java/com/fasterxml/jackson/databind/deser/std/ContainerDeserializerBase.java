package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ContainerDeserializerBase<T>
  extends StdDeserializer<T>
{
  protected ContainerDeserializerBase(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    JsonDeserializer localJsonDeserializer = getContentDeserializer();
    if (localJsonDeserializer == null) {
      throw new IllegalArgumentException("Can not handle managed/back reference '" + paramString + "': type: container deserializer of type " + getClass().getName() + " returned null for 'getContentDeserializer()'");
    }
    return localJsonDeserializer.findBackReference(paramString);
  }
  
  public abstract JsonDeserializer<Object> getContentDeserializer();
  
  public abstract JavaType getContentType();
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if (((paramThrowable instanceof IOException)) && (!(paramThrowable instanceof JsonMappingException))) {
      throw ((IOException)paramThrowable);
    }
    String str = paramString;
    if (paramString == null) {
      str = "N/A";
    }
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, str);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\ContainerDeserializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */