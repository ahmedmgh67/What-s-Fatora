package com.google.api.client.googleapis.notifications.json;

import com.google.api.client.googleapis.notifications.TypedNotificationCallback;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public abstract class JsonNotificationCallback<T>
  extends TypedNotificationCallback<T>
{
  private static final long serialVersionUID = 1L;
  
  protected abstract JsonFactory getJsonFactory()
    throws IOException;
  
  protected final JsonObjectParser getObjectParser()
    throws IOException
  {
    return new JsonObjectParser(getJsonFactory());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\json\JsonNotificationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */