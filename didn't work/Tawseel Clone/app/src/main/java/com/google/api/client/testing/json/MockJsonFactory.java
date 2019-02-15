package com.google.api.client.testing.json;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

@Beta
public class MockJsonFactory
  extends JsonFactory
{
  public JsonGenerator createJsonGenerator(OutputStream paramOutputStream, Charset paramCharset)
    throws IOException
  {
    return new MockJsonGenerator(this);
  }
  
  public JsonGenerator createJsonGenerator(Writer paramWriter)
    throws IOException
  {
    return new MockJsonGenerator(this);
  }
  
  public JsonParser createJsonParser(InputStream paramInputStream)
    throws IOException
  {
    return new MockJsonParser(this);
  }
  
  public JsonParser createJsonParser(InputStream paramInputStream, Charset paramCharset)
    throws IOException
  {
    return new MockJsonParser(this);
  }
  
  public JsonParser createJsonParser(Reader paramReader)
    throws IOException
  {
    return new MockJsonParser(this);
  }
  
  public JsonParser createJsonParser(String paramString)
    throws IOException
  {
    return new MockJsonParser(this);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\json\MockJsonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */