package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class zzbpx
{
  private static void zza(Object paramObject, JSONStringer paramJSONStringer)
    throws IOException, JSONException
  {
    if ((paramObject instanceof Map))
    {
      paramJSONStringer.object();
      paramObject = ((Map)paramObject).entrySet().iterator();
      while (((Iterator)paramObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)paramObject).next();
        paramJSONStringer.key((String)localEntry.getKey());
        zza(localEntry.getValue(), paramJSONStringer);
      }
      paramJSONStringer.endObject();
      return;
    }
    if ((paramObject instanceof Collection))
    {
      paramObject = (Collection)paramObject;
      paramJSONStringer.array();
      paramObject = ((Collection)paramObject).iterator();
      while (((Iterator)paramObject).hasNext()) {
        zza(((Iterator)paramObject).next(), paramJSONStringer);
      }
      paramJSONStringer.endArray();
      return;
    }
    paramJSONStringer.value(paramObject);
  }
  
  public static String zzaE(Map<String, Object> paramMap)
    throws IOException
  {
    return zzat(paramMap);
  }
  
  public static String zzat(Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      return "null";
    }
    if ((paramObject instanceof String)) {
      return JSONObject.quote((String)paramObject);
    }
    if ((paramObject instanceof Number)) {
      try
      {
        paramObject = JSONObject.numberToString((Number)paramObject);
        return (String)paramObject;
      }
      catch (JSONException paramObject)
      {
        throw new IOException("Could not serialize number", (Throwable)paramObject);
      }
    }
    if ((paramObject instanceof Boolean))
    {
      if (((Boolean)paramObject).booleanValue()) {
        return "true";
      }
      return "false";
    }
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      zza(paramObject, localJSONStringer);
      paramObject = localJSONStringer.toString();
      return (String)paramObject;
    }
    catch (JSONException paramObject)
    {
      throw new IOException("Failed to serialize JSON", (Throwable)paramObject);
    }
  }
  
  private static Object zzau(Object paramObject)
    throws JSONException
  {
    Object localObject;
    if ((paramObject instanceof JSONObject)) {
      localObject = zzt((JSONObject)paramObject);
    }
    do
    {
      return localObject;
      if ((paramObject instanceof JSONArray)) {
        return zze((JSONArray)paramObject);
      }
      localObject = paramObject;
    } while (!paramObject.equals(JSONObject.NULL));
    return null;
  }
  
  private static List<Object> zze(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList(paramJSONArray.length());
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(zzau(paramJSONArray.get(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  public static Map<String, Object> zzjf(String paramString)
    throws IOException
  {
    try
    {
      paramString = zzt(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new IOException(paramString);
    }
  }
  
  public static Object zzjg(String paramString)
    throws IOException
  {
    try
    {
      paramString = zzau(new JSONTokener(paramString).nextValue());
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new IOException(paramString);
    }
  }
  
  private static Map<String, Object> zzt(JSONObject paramJSONObject)
    throws JSONException
  {
    HashMap localHashMap = new HashMap(paramJSONObject.length());
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, zzau(paramJSONObject.get(str)));
    }
    return localHashMap;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */