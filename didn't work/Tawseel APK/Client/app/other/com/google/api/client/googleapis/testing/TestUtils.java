package com.google.api.client.googleapis.testing;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class TestUtils
{
  private static final String UTF_8 = "UTF-8";
  
  public static Map<String, String> parseQuery(String paramString)
    throws IOException
  {
    HashMap localHashMap = new HashMap();
    paramString = Splitter.on('&').split(paramString).iterator();
    while (paramString.hasNext())
    {
      Object localObject = (String)paramString.next();
      localObject = Lists.newArrayList(Splitter.on('=').split((CharSequence)localObject));
      if (((List)localObject).size() != 2) {
        throw new IOException("Invalid Query String");
      }
      localHashMap.put(URLDecoder.decode((String)((List)localObject).get(0), "UTF-8"), URLDecoder.decode((String)((List)localObject).get(1), "UTF-8"));
    }
    return localHashMap;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\TestUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */