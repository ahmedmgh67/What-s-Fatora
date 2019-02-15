package com.google.api.client.extensions.android.json;

import android.annotation.TargetApi;
import android.util.JsonWriter;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@TargetApi(11)
@Beta
class AndroidJsonGenerator
  extends JsonGenerator
{
  private final AndroidJsonFactory factory;
  private final JsonWriter writer;
  
  AndroidJsonGenerator(AndroidJsonFactory paramAndroidJsonFactory, JsonWriter paramJsonWriter)
  {
    this.factory = paramAndroidJsonFactory;
    this.writer = paramJsonWriter;
    paramJsonWriter.setLenient(true);
  }
  
  public void close()
    throws IOException
  {
    this.writer.close();
  }
  
  public void enablePrettyPrint()
    throws IOException
  {
    this.writer.setIndent("  ");
  }
  
  public void flush()
    throws IOException
  {
    this.writer.flush();
  }
  
  public JsonFactory getFactory()
  {
    return this.factory;
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException
  {
    this.writer.value(paramBoolean);
  }
  
  public void writeEndArray()
    throws IOException
  {
    this.writer.endArray();
  }
  
  public void writeEndObject()
    throws IOException
  {
    this.writer.endObject();
  }
  
  public void writeFieldName(String paramString)
    throws IOException
  {
    this.writer.name(paramString);
  }
  
  public void writeNull()
    throws IOException
  {
    this.writer.nullValue();
  }
  
  public void writeNumber(double paramDouble)
    throws IOException
  {
    this.writer.value(paramDouble);
  }
  
  public void writeNumber(float paramFloat)
    throws IOException
  {
    this.writer.value(paramFloat);
  }
  
  public void writeNumber(int paramInt)
    throws IOException
  {
    this.writer.value(paramInt);
  }
  
  public void writeNumber(long paramLong)
    throws IOException
  {
    this.writer.value(paramLong);
  }
  
  public void writeNumber(String paramString)
    throws IOException
  {
    this.writer.value(new StringNumber(paramString));
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException
  {
    this.writer.value(paramBigDecimal);
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException
  {
    this.writer.value(paramBigInteger);
  }
  
  public void writeStartArray()
    throws IOException
  {
    this.writer.beginArray();
  }
  
  public void writeStartObject()
    throws IOException
  {
    this.writer.beginObject();
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    this.writer.value(paramString);
  }
  
  static final class StringNumber
    extends Number
  {
    private static final long serialVersionUID = 1L;
    private final String encodedValue;
    
    StringNumber(String paramString)
    {
      this.encodedValue = paramString;
    }
    
    public double doubleValue()
    {
      return 0.0D;
    }
    
    public float floatValue()
    {
      return 0.0F;
    }
    
    public int intValue()
    {
      return 0;
    }
    
    public long longValue()
    {
      return 0L;
    }
    
    public String toString()
    {
      return this.encodedValue;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\extensions\android\json\AndroidJsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */