package com.google.api.client.http.json;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;

public class JsonHttpContent
  extends AbstractHttpContent
{
  private final Object data;
  private final JsonFactory jsonFactory;
  private String wrapperKey;
  
  public JsonHttpContent(JsonFactory paramJsonFactory, Object paramObject)
  {
    super("application/json; charset=UTF-8");
    this.jsonFactory = ((JsonFactory)Preconditions.checkNotNull(paramJsonFactory));
    this.data = Preconditions.checkNotNull(paramObject);
  }
  
  public final Object getData()
  {
    return this.data;
  }
  
  public final JsonFactory getJsonFactory()
  {
    return this.jsonFactory;
  }
  
  public final String getWrapperKey()
  {
    return this.wrapperKey;
  }
  
  public JsonHttpContent setMediaType(HttpMediaType paramHttpMediaType)
  {
    super.setMediaType(paramHttpMediaType);
    return this;
  }
  
  public JsonHttpContent setWrapperKey(String paramString)
  {
    this.wrapperKey = paramString;
    return this;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = this.jsonFactory.createJsonGenerator(paramOutputStream, getCharset());
    if (this.wrapperKey != null)
    {
      paramOutputStream.writeStartObject();
      paramOutputStream.writeFieldName(this.wrapperKey);
    }
    paramOutputStream.serialize(this.data);
    if (this.wrapperKey != null) {
      paramOutputStream.writeEndObject();
    }
    paramOutputStream.flush();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\json\JsonHttpContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */