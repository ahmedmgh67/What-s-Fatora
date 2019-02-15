package com.google.api.client.extensions.android.json;

import android.annotation.TargetApi;
import android.util.JsonReader;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
@Beta
class AndroidJsonParser
  extends JsonParser
{
  private List<String> currentNameStack = new ArrayList();
  private String currentText;
  private com.google.api.client.json.JsonToken currentToken;
  private final AndroidJsonFactory factory;
  private final JsonReader reader;
  
  AndroidJsonParser(AndroidJsonFactory paramAndroidJsonFactory, JsonReader paramJsonReader)
  {
    this.factory = paramAndroidJsonFactory;
    this.reader = paramJsonReader;
    paramJsonReader.setLenient(true);
  }
  
  private void checkNumber()
  {
    if ((this.currentToken == com.google.api.client.json.JsonToken.VALUE_NUMBER_INT) || (this.currentToken == com.google.api.client.json.JsonToken.VALUE_NUMBER_FLOAT)) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      return;
    }
  }
  
  public void close()
    throws IOException
  {
    this.reader.close();
  }
  
  public BigInteger getBigIntegerValue()
  {
    checkNumber();
    return new BigInteger(this.currentText);
  }
  
  public byte getByteValue()
  {
    checkNumber();
    return Byte.valueOf(this.currentText).byteValue();
  }
  
  public String getCurrentName()
  {
    if (this.currentNameStack.isEmpty()) {
      return null;
    }
    return (String)this.currentNameStack.get(this.currentNameStack.size() - 1);
  }
  
  public com.google.api.client.json.JsonToken getCurrentToken()
  {
    return this.currentToken;
  }
  
  public BigDecimal getDecimalValue()
  {
    checkNumber();
    return new BigDecimal(this.currentText);
  }
  
  public double getDoubleValue()
  {
    checkNumber();
    return Double.valueOf(this.currentText).doubleValue();
  }
  
  public JsonFactory getFactory()
  {
    return this.factory;
  }
  
  public float getFloatValue()
  {
    checkNumber();
    return Float.valueOf(this.currentText).floatValue();
  }
  
  public int getIntValue()
  {
    checkNumber();
    return Integer.valueOf(this.currentText).intValue();
  }
  
  public long getLongValue()
  {
    checkNumber();
    return Long.valueOf(this.currentText).longValue();
  }
  
  public short getShortValue()
  {
    checkNumber();
    return Short.valueOf(this.currentText).shortValue();
  }
  
  public String getText()
  {
    return this.currentText;
  }
  
  public com.google.api.client.json.JsonToken nextToken()
    throws IOException
  {
    if (this.currentToken != null) {
      switch (1.$SwitchMap$com$google$api$client$json$JsonToken[this.currentToken.ordinal()])
      {
      }
    }
    try
    {
      for (;;)
      {
        android.util.JsonToken localJsonToken = this.reader.peek();
        switch (localJsonToken)
        {
        default: 
          this.currentText = null;
          this.currentToken = null;
          return this.currentToken;
          this.reader.beginArray();
          this.currentNameStack.add(null);
          continue;
          this.reader.beginObject();
          this.currentNameStack.add(null);
        }
      }
    }
    catch (EOFException localEOFException)
    {
      for (;;)
      {
        Object localObject = android.util.JsonToken.END_DOCUMENT;
        continue;
        this.currentText = "[";
        this.currentToken = com.google.api.client.json.JsonToken.START_ARRAY;
        continue;
        this.currentText = "]";
        this.currentToken = com.google.api.client.json.JsonToken.END_ARRAY;
        this.currentNameStack.remove(this.currentNameStack.size() - 1);
        this.reader.endArray();
        continue;
        this.currentText = "{";
        this.currentToken = com.google.api.client.json.JsonToken.START_OBJECT;
        continue;
        this.currentText = "}";
        this.currentToken = com.google.api.client.json.JsonToken.END_OBJECT;
        this.currentNameStack.remove(this.currentNameStack.size() - 1);
        this.reader.endObject();
        continue;
        if (this.reader.nextBoolean())
        {
          this.currentText = "true";
          this.currentToken = com.google.api.client.json.JsonToken.VALUE_TRUE;
        }
        else
        {
          this.currentText = "false";
          this.currentToken = com.google.api.client.json.JsonToken.VALUE_FALSE;
          continue;
          this.currentText = "null";
          this.currentToken = com.google.api.client.json.JsonToken.VALUE_NULL;
          this.reader.nextNull();
          continue;
          this.currentText = this.reader.nextString();
          this.currentToken = com.google.api.client.json.JsonToken.VALUE_STRING;
          continue;
          this.currentText = this.reader.nextString();
          if (this.currentText.indexOf('.') == -1) {}
          for (localObject = com.google.api.client.json.JsonToken.VALUE_NUMBER_INT;; localObject = com.google.api.client.json.JsonToken.VALUE_NUMBER_FLOAT)
          {
            this.currentToken = ((com.google.api.client.json.JsonToken)localObject);
            break;
          }
          this.currentText = this.reader.nextName();
          this.currentToken = com.google.api.client.json.JsonToken.FIELD_NAME;
          this.currentNameStack.set(this.currentNameStack.size() - 1, this.currentText);
        }
      }
    }
  }
  
  public JsonParser skipChildren()
    throws IOException
  {
    if (this.currentToken != null) {}
    switch (1.$SwitchMap$com$google$api$client$json$JsonToken[this.currentToken.ordinal()])
    {
    default: 
      return this;
    case 1: 
      this.reader.skipValue();
      this.currentText = "]";
      this.currentToken = com.google.api.client.json.JsonToken.END_ARRAY;
      return this;
    }
    this.reader.skipValue();
    this.currentText = "}";
    this.currentToken = com.google.api.client.json.JsonToken.END_OBJECT;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\extensions\android\json\AndroidJsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */