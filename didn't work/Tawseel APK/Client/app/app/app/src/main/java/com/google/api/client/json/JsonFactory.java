package com.google.api.client.json;

import com.google.api.client.util.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public abstract class JsonFactory
{
  private ByteArrayOutputStream toByteStream(Object paramObject, boolean paramBoolean)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    JsonGenerator localJsonGenerator = createJsonGenerator(localByteArrayOutputStream, Charsets.UTF_8);
    if (paramBoolean) {
      localJsonGenerator.enablePrettyPrint();
    }
    localJsonGenerator.serialize(paramObject);
    localJsonGenerator.flush();
    return localByteArrayOutputStream;
  }
  
  private String toString(Object paramObject, boolean paramBoolean)
    throws IOException
  {
    return toByteStream(paramObject, paramBoolean).toString("UTF-8");
  }
  
  public abstract JsonGenerator createJsonGenerator(OutputStream paramOutputStream, Charset paramCharset)
    throws IOException;
  
  public abstract JsonGenerator createJsonGenerator(Writer paramWriter)
    throws IOException;
  
  public final JsonObjectParser createJsonObjectParser()
  {
    return new JsonObjectParser(this);
  }
  
  public abstract JsonParser createJsonParser(InputStream paramInputStream)
    throws IOException;
  
  public abstract JsonParser createJsonParser(InputStream paramInputStream, Charset paramCharset)
    throws IOException;
  
  public abstract JsonParser createJsonParser(Reader paramReader)
    throws IOException;
  
  public abstract JsonParser createJsonParser(String paramString)
    throws IOException;
  
  public final <T> T fromInputStream(InputStream paramInputStream, Class<T> paramClass)
    throws IOException
  {
    return (T)createJsonParser(paramInputStream).parseAndClose(paramClass);
  }
  
  public final <T> T fromInputStream(InputStream paramInputStream, Charset paramCharset, Class<T> paramClass)
    throws IOException
  {
    return (T)createJsonParser(paramInputStream, paramCharset).parseAndClose(paramClass);
  }
  
  public final <T> T fromReader(Reader paramReader, Class<T> paramClass)
    throws IOException
  {
    return (T)createJsonParser(paramReader).parseAndClose(paramClass);
  }
  
  public final <T> T fromString(String paramString, Class<T> paramClass)
    throws IOException
  {
    return (T)createJsonParser(paramString).parse(paramClass);
  }
  
  public final byte[] toByteArray(Object paramObject)
    throws IOException
  {
    return toByteStream(paramObject, false).toByteArray();
  }
  
  public final String toPrettyString(Object paramObject)
    throws IOException
  {
    return toString(paramObject, true);
  }
  
  public final String toString(Object paramObject)
    throws IOException
  {
    return toString(paramObject, false);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\JsonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */