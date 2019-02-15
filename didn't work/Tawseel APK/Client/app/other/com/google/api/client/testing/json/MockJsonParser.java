package com.google.api.client.testing.json;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@Beta
public class MockJsonParser
  extends JsonParser
{
  private final JsonFactory factory;
  private boolean isClosed;
  
  MockJsonParser(JsonFactory paramJsonFactory)
  {
    this.factory = paramJsonFactory;
  }
  
  public void close()
    throws IOException
  {
    this.isClosed = true;
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException
  {
    return null;
  }
  
  public byte getByteValue()
    throws IOException
  {
    return 0;
  }
  
  public String getCurrentName()
    throws IOException
  {
    return null;
  }
  
  public JsonToken getCurrentToken()
  {
    return null;
  }
  
  public BigDecimal getDecimalValue()
    throws IOException
  {
    return null;
  }
  
  public double getDoubleValue()
    throws IOException
  {
    return 0.0D;
  }
  
  public JsonFactory getFactory()
  {
    return this.factory;
  }
  
  public float getFloatValue()
    throws IOException
  {
    return 0.0F;
  }
  
  public int getIntValue()
    throws IOException
  {
    return 0;
  }
  
  public long getLongValue()
    throws IOException
  {
    return 0L;
  }
  
  public short getShortValue()
    throws IOException
  {
    return 0;
  }
  
  public String getText()
    throws IOException
  {
    return null;
  }
  
  public boolean isClosed()
  {
    return this.isClosed;
  }
  
  public JsonToken nextToken()
    throws IOException
  {
    return null;
  }
  
  public JsonParser skipChildren()
    throws IOException
  {
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\json\MockJsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */