package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer
  extends StdSerializer<JsonSerializable>
{
  private static final AtomicReference<ObjectMapper> _mapperReference = new AtomicReference();
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer()
  {
    super(JsonSerializable.class);
  }
  
  private static final ObjectMapper _getObjectMapper()
  {
    try
    {
      ObjectMapper localObjectMapper2 = (ObjectMapper)_mapperReference.get();
      ObjectMapper localObjectMapper1 = localObjectMapper2;
      if (localObjectMapper2 == null)
      {
        localObjectMapper1 = new ObjectMapper();
        _mapperReference.set(localObjectMapper1);
      }
      return localObjectMapper1;
    }
    finally {}
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  /* Error */
  public com.fasterxml.jackson.databind.JsonNode getSchema(SerializerProvider paramSerializerProvider, java.lang.reflect.Type paramType)
    throws JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 61	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:createObjectNode	()Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   4: astore 10
    //   6: ldc 63
    //   8: astore 8
    //   10: aconst_null
    //   11: astore 9
    //   13: aconst_null
    //   14: astore 7
    //   16: aconst_null
    //   17: astore 6
    //   19: aload 6
    //   21: astore 5
    //   23: aload 9
    //   25: astore_3
    //   26: aload 8
    //   28: astore 4
    //   30: aload_2
    //   31: ifnull +114 -> 145
    //   34: aload_2
    //   35: invokestatic 69	com/fasterxml/jackson/databind/type/TypeFactory:rawClass	(Ljava/lang/reflect/Type;)Ljava/lang/Class;
    //   38: astore_2
    //   39: aload 6
    //   41: astore 5
    //   43: aload 9
    //   45: astore_3
    //   46: aload 8
    //   48: astore 4
    //   50: aload_2
    //   51: ldc 71
    //   53: invokevirtual 77	java/lang/Class:isAnnotationPresent	(Ljava/lang/Class;)Z
    //   56: ifeq +89 -> 145
    //   59: aload_2
    //   60: ldc 71
    //   62: invokevirtual 81	java/lang/Class:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   65: checkcast 71	com/fasterxml/jackson/databind/jsonschema/JsonSerializableSchema
    //   68: astore 9
    //   70: aload 9
    //   72: invokeinterface 85 1 0
    //   77: astore 8
    //   79: aload 7
    //   81: astore_2
    //   82: ldc 87
    //   84: aload 9
    //   86: invokeinterface 90 1 0
    //   91: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   94: ifne +11 -> 105
    //   97: aload 9
    //   99: invokeinterface 90 1 0
    //   104: astore_2
    //   105: aload 6
    //   107: astore 5
    //   109: aload_2
    //   110: astore_3
    //   111: aload 8
    //   113: astore 4
    //   115: ldc 87
    //   117: aload 9
    //   119: invokeinterface 99 1 0
    //   124: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   127: ifne +18 -> 145
    //   130: aload 9
    //   132: invokeinterface 99 1 0
    //   137: astore 5
    //   139: aload 8
    //   141: astore 4
    //   143: aload_2
    //   144: astore_3
    //   145: aload 10
    //   147: ldc 101
    //   149: aload 4
    //   151: invokevirtual 107	com/fasterxml/jackson/databind/node/ObjectNode:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   154: pop
    //   155: aload_3
    //   156: ifnull +18 -> 174
    //   159: aload 10
    //   161: ldc 109
    //   163: invokestatic 111	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:_getObjectMapper	()Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   166: aload_3
    //   167: invokevirtual 115	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   170: invokevirtual 118	com/fasterxml/jackson/databind/node/ObjectNode:set	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   173: pop
    //   174: aload 5
    //   176: ifnull +19 -> 195
    //   179: aload 10
    //   181: ldc 120
    //   183: invokestatic 111	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:_getObjectMapper	()Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   186: aload 5
    //   188: invokevirtual 115	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   191: invokevirtual 118	com/fasterxml/jackson/databind/node/ObjectNode:set	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   194: pop
    //   195: aload 10
    //   197: areturn
    //   198: astore_2
    //   199: aload_1
    //   200: ldc 122
    //   202: invokestatic 126	com/fasterxml/jackson/databind/JsonMappingException:from	(Lcom/fasterxml/jackson/databind/SerializerProvider;Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   205: athrow
    //   206: astore_2
    //   207: aload_1
    //   208: ldc -128
    //   210: invokestatic 126	com/fasterxml/jackson/databind/JsonMappingException:from	(Lcom/fasterxml/jackson/databind/SerializerProvider;Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   213: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	214	0	this	SerializableSerializer
    //   0	214	1	paramSerializerProvider	SerializerProvider
    //   0	214	2	paramType	java.lang.reflect.Type
    //   25	142	3	localObject1	Object
    //   28	122	4	str1	String
    //   21	166	5	localObject2	Object
    //   17	89	6	localObject3	Object
    //   14	66	7	localObject4	Object
    //   8	132	8	str2	String
    //   11	120	9	localJsonSerializableSchema	com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema
    //   4	192	10	localObjectNode	com.fasterxml.jackson.databind.node.ObjectNode
    // Exception table:
    //   from	to	target	type
    //   159	174	198	java/io/IOException
    //   179	195	206	java/io/IOException
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, JsonSerializable paramJsonSerializable)
  {
    if ((paramJsonSerializable instanceof JsonSerializable.Base)) {
      return ((JsonSerializable.Base)paramJsonSerializable).isEmpty(paramSerializerProvider);
    }
    return false;
  }
  
  public void serialize(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonSerializable.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramJsonSerializable.serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\std\SerializableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */