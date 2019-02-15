package com.google.firebase.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServerValue
{
  public static final Map<String, String> TIMESTAMP = zziG("timestamp");
  
  private static Map<String, String> zziG(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(".sv", paramString);
    return Collections.unmodifiableMap(localHashMap);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\ServerValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */