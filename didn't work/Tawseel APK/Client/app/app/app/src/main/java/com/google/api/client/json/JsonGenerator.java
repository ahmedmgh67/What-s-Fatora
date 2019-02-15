package com.google.api.client.json;

import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class JsonGenerator
{
  private void serialize(boolean paramBoolean, Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      return;
    }
    Object localObject1 = paramObject.getClass();
    if (Data.isNull(paramObject))
    {
      writeNull();
      return;
    }
    if ((paramObject instanceof String))
    {
      writeString((String)paramObject);
      return;
    }
    if ((paramObject instanceof Number))
    {
      if (paramBoolean)
      {
        writeString(paramObject.toString());
        return;
      }
      if ((paramObject instanceof BigDecimal))
      {
        writeNumber((BigDecimal)paramObject);
        return;
      }
      if ((paramObject instanceof BigInteger))
      {
        writeNumber((BigInteger)paramObject);
        return;
      }
      if ((paramObject instanceof Long))
      {
        writeNumber(((Long)paramObject).longValue());
        return;
      }
      if ((paramObject instanceof Float))
      {
        float f = ((Number)paramObject).floatValue();
        if ((!Float.isInfinite(f)) && (!Float.isNaN(f))) {}
        for (paramBoolean = true;; paramBoolean = false)
        {
          Preconditions.checkArgument(paramBoolean);
          writeNumber(f);
          return;
        }
      }
      if (((paramObject instanceof Integer)) || ((paramObject instanceof Short)) || ((paramObject instanceof Byte)))
      {
        writeNumber(((Number)paramObject).intValue());
        return;
      }
      double d = ((Number)paramObject).doubleValue();
      if ((!Double.isInfinite(d)) && (!Double.isNaN(d))) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        Preconditions.checkArgument(paramBoolean);
        writeNumber(d);
        return;
      }
    }
    if ((paramObject instanceof Boolean))
    {
      writeBoolean(((Boolean)paramObject).booleanValue());
      return;
    }
    if ((paramObject instanceof DateTime))
    {
      writeString(((DateTime)paramObject).toStringRfc3339());
      return;
    }
    if (((paramObject instanceof Iterable)) || (((Class)localObject1).isArray()))
    {
      writeStartArray();
      paramObject = Types.iterableOf(paramObject).iterator();
      while (((Iterator)paramObject).hasNext()) {
        serialize(paramBoolean, ((Iterator)paramObject).next());
      }
      writeEndArray();
      return;
    }
    if (((Class)localObject1).isEnum())
    {
      paramObject = FieldInfo.of((Enum)paramObject).getName();
      if (paramObject == null)
      {
        writeNull();
        return;
      }
      writeString((String)paramObject);
      return;
    }
    writeStartObject();
    int i;
    if (((paramObject instanceof Map)) && (!(paramObject instanceof GenericData)))
    {
      i = 1;
      if (i == 0) {
        break label482;
      }
    }
    Object localObject3;
    label482:
    for (localObject1 = null;; localObject1 = ClassInfo.of((Class)localObject1))
    {
      paramObject = Data.mapOf(paramObject).entrySet().iterator();
      for (;;)
      {
        if (!((Iterator)paramObject).hasNext()) {
          break label528;
        }
        localObject3 = (Map.Entry)((Iterator)paramObject).next();
        Object localObject2 = ((Map.Entry)localObject3).getValue();
        if (localObject2 != null)
        {
          localObject3 = (String)((Map.Entry)localObject3).getKey();
          if (i == 0) {
            break;
          }
          bool = paramBoolean;
          writeFieldName((String)localObject3);
          serialize(bool, localObject2);
        }
      }
      i = 0;
      break;
    }
    Field localField = ((ClassInfo)localObject1).getField((String)localObject3);
    if ((localField != null) && (localField.getAnnotation(JsonString.class) != null)) {}
    for (boolean bool = true;; bool = false) {
      break;
    }
    label528:
    writeEndObject();
  }
  
  public abstract void close()
    throws IOException;
  
  public void enablePrettyPrint()
    throws IOException
  {}
  
  public abstract void flush()
    throws IOException;
  
  public abstract JsonFactory getFactory();
  
  public final void serialize(Object paramObject)
    throws IOException
  {
    serialize(false, paramObject);
  }
  
  public abstract void writeBoolean(boolean paramBoolean)
    throws IOException;
  
  public abstract void writeEndArray()
    throws IOException;
  
  public abstract void writeEndObject()
    throws IOException;
  
  public abstract void writeFieldName(String paramString)
    throws IOException;
  
  public abstract void writeNull()
    throws IOException;
  
  public abstract void writeNumber(double paramDouble)
    throws IOException;
  
  public abstract void writeNumber(float paramFloat)
    throws IOException;
  
  public abstract void writeNumber(int paramInt)
    throws IOException;
  
  public abstract void writeNumber(long paramLong)
    throws IOException;
  
  public abstract void writeNumber(String paramString)
    throws IOException;
  
  public abstract void writeNumber(BigDecimal paramBigDecimal)
    throws IOException;
  
  public abstract void writeNumber(BigInteger paramBigInteger)
    throws IOException;
  
  public abstract void writeStartArray()
    throws IOException;
  
  public abstract void writeStartObject()
    throws IOException;
  
  public abstract void writeString(String paramString)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\JsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */