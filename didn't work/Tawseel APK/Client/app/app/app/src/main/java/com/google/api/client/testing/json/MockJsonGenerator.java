package com.google.api.client.testing.json;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@Beta
public class MockJsonGenerator
  extends JsonGenerator
{
  private final JsonFactory factory;
  
  MockJsonGenerator(JsonFactory paramJsonFactory)
  {
    this.factory = paramJsonFactory;
  }
  
  public void close()
    throws IOException
  {}
  
  public void flush()
    throws IOException
  {}
  
  public JsonFactory getFactory()
  {
    return this.factory;
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException
  {}
  
  public void writeEndArray()
    throws IOException
  {}
  
  public void writeEndObject()
    throws IOException
  {}
  
  public void writeFieldName(String paramString)
    throws IOException
  {}
  
  public void writeNull()
    throws IOException
  {}
  
  public void writeNumber(double paramDouble)
    throws IOException
  {}
  
  public void writeNumber(float paramFloat)
    throws IOException
  {}
  
  public void writeNumber(int paramInt)
    throws IOException
  {}
  
  public void writeNumber(long paramLong)
    throws IOException
  {}
  
  public void writeNumber(String paramString)
    throws IOException
  {}
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException
  {}
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException
  {}
  
  public void writeStartArray()
    throws IOException
  {}
  
  public void writeStartObject()
    throws IOException
  {}
  
  public void writeString(String paramString)
    throws IOException
  {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\json\MockJsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */