package com.google.api.client.json;

import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JsonObjectParser
  implements ObjectParser
{
  private final JsonFactory jsonFactory;
  private final Set<String> wrapperKeys;
  
  public JsonObjectParser(JsonFactory paramJsonFactory)
  {
    this(new Builder(paramJsonFactory));
  }
  
  protected JsonObjectParser(Builder paramBuilder)
  {
    this.jsonFactory = paramBuilder.jsonFactory;
    this.wrapperKeys = new HashSet(paramBuilder.wrapperKeys);
  }
  
  /* Error */
  private void initializeParser(JsonParser paramJsonParser)
    throws IOException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 38	com/google/api/client/json/JsonObjectParser:wrapperKeys	Ljava/util/Set;
    //   6: invokeinterface 48 1 0
    //   11: ifeq +4 -> 15
    //   14: return
    //   15: aload_1
    //   16: aload_0
    //   17: getfield 38	com/google/api/client/json/JsonObjectParser:wrapperKeys	Ljava/util/Set;
    //   20: invokevirtual 54	com/google/api/client/json/JsonParser:skipToKey	(Ljava/util/Set;)Ljava/lang/String;
    //   23: ifnull +39 -> 62
    //   26: aload_1
    //   27: invokevirtual 58	com/google/api/client/json/JsonParser:getCurrentToken	()Lcom/google/api/client/json/JsonToken;
    //   30: getstatic 64	com/google/api/client/json/JsonToken:END_OBJECT	Lcom/google/api/client/json/JsonToken;
    //   33: if_acmpeq +29 -> 62
    //   36: iload_2
    //   37: ldc 66
    //   39: iconst_1
    //   40: anewarray 4	java/lang/Object
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: getfield 38	com/google/api/client/json/JsonObjectParser:wrapperKeys	Ljava/util/Set;
    //   49: aastore
    //   50: invokestatic 72	com/google/api/client/util/Preconditions:checkArgument	(ZLjava/lang/String;[Ljava/lang/Object;)V
    //   53: iconst_0
    //   54: ifeq -40 -> 14
    //   57: aload_1
    //   58: invokevirtual 75	com/google/api/client/json/JsonParser:close	()V
    //   61: return
    //   62: iconst_0
    //   63: istore_2
    //   64: goto -28 -> 36
    //   67: astore_3
    //   68: iconst_1
    //   69: ifeq +7 -> 76
    //   72: aload_1
    //   73: invokevirtual 75	com/google/api/client/json/JsonParser:close	()V
    //   76: aload_3
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	JsonObjectParser
    //   0	78	1	paramJsonParser	JsonParser
    //   1	63	2	bool	boolean
    //   67	10	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   15	36	67	finally
    //   36	53	67	finally
  }
  
  public final JsonFactory getJsonFactory()
  {
    return this.jsonFactory;
  }
  
  public Set<String> getWrapperKeys()
  {
    return Collections.unmodifiableSet(this.wrapperKeys);
  }
  
  public <T> T parseAndClose(InputStream paramInputStream, Charset paramCharset, Class<T> paramClass)
    throws IOException
  {
    return (T)parseAndClose(paramInputStream, paramCharset, paramClass);
  }
  
  public Object parseAndClose(InputStream paramInputStream, Charset paramCharset, Type paramType)
    throws IOException
  {
    paramInputStream = this.jsonFactory.createJsonParser(paramInputStream, paramCharset);
    initializeParser(paramInputStream);
    return paramInputStream.parse(paramType, true);
  }
  
  public <T> T parseAndClose(Reader paramReader, Class<T> paramClass)
    throws IOException
  {
    return (T)parseAndClose(paramReader, paramClass);
  }
  
  public Object parseAndClose(Reader paramReader, Type paramType)
    throws IOException
  {
    paramReader = this.jsonFactory.createJsonParser(paramReader);
    initializeParser(paramReader);
    return paramReader.parse(paramType, true);
  }
  
  public static class Builder
  {
    final JsonFactory jsonFactory;
    Collection<String> wrapperKeys = Sets.newHashSet();
    
    public Builder(JsonFactory paramJsonFactory)
    {
      this.jsonFactory = ((JsonFactory)Preconditions.checkNotNull(paramJsonFactory));
    }
    
    public JsonObjectParser build()
    {
      return new JsonObjectParser(this);
    }
    
    public final JsonFactory getJsonFactory()
    {
      return this.jsonFactory;
    }
    
    public final Collection<String> getWrapperKeys()
    {
      return this.wrapperKeys;
    }
    
    public Builder setWrapperKeys(Collection<String> paramCollection)
    {
      this.wrapperKeys = paramCollection;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\JsonObjectParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */