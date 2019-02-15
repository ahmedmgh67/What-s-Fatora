package com.google.api.client.json;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Throwables;
import java.io.IOException;

public class GenericJson
  extends GenericData
  implements Cloneable
{
  private JsonFactory jsonFactory;
  
  public GenericJson clone()
  {
    return (GenericJson)super.clone();
  }
  
  public final JsonFactory getFactory()
  {
    return this.jsonFactory;
  }
  
  public GenericJson set(String paramString, Object paramObject)
  {
    return (GenericJson)super.set(paramString, paramObject);
  }
  
  public final void setFactory(JsonFactory paramJsonFactory)
  {
    this.jsonFactory = paramJsonFactory;
  }
  
  public String toPrettyString()
    throws IOException
  {
    if (this.jsonFactory != null) {
      return this.jsonFactory.toPrettyString(this);
    }
    return super.toString();
  }
  
  public String toString()
  {
    if (this.jsonFactory != null) {
      try
      {
        String str = this.jsonFactory.toString(this);
        return str;
      }
      catch (IOException localIOException)
      {
        throw Throwables.propagate(localIOException);
      }
    }
    return super.toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\GenericJson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */