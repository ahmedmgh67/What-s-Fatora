package com.google.api.client.googleapis.util;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Beta;

@Beta
public final class Utils
{
  public static JsonFactory getDefaultJsonFactory()
  {
    return JsonFactoryInstanceHolder.INSTANCE;
  }
  
  public static HttpTransport getDefaultTransport()
  {
    return TransportInstanceHolder.INSTANCE;
  }
  
  private static class JsonFactoryInstanceHolder
  {
    static final JsonFactory INSTANCE = new JacksonFactory();
  }
  
  private static class TransportInstanceHolder
  {
    static final HttpTransport INSTANCE = new NetHttpTransport();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\util\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */