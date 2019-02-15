package com.google.api.client.json;

import com.google.api.client.util.Beta;
import java.lang.reflect.Field;
import java.util.Collection;

@Beta
public class CustomizeJsonParser
{
  public void handleUnrecognizedKey(Object paramObject, String paramString) {}
  
  public Collection<Object> newInstanceForArray(Object paramObject, Field paramField)
  {
    return null;
  }
  
  public Object newInstanceForObject(Object paramObject, Class<?> paramClass)
  {
    return null;
  }
  
  public boolean stopAt(Object paramObject, String paramString)
  {
    return false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\CustomizeJsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */