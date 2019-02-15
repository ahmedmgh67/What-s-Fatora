package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzbqh
{
  private static final Pattern zzciC = Pattern.compile("[\\[\\]\\.#$]");
  private static final Pattern zzciD = Pattern.compile("[\\[\\]\\.#\\$\\/\\u0000-\\u001F\\u007F]");
  
  private static boolean zzP(zzbmj paramzzbmj)
  {
    paramzzbmj = paramzzbmj.zzXi();
    return (paramzzbmj == null) || (!paramzzbmj.asString().startsWith("."));
  }
  
  public static void zzQ(zzbmj paramzzbmj)
    throws DatabaseException
  {
    if (!zzP(paramzzbmj))
    {
      paramzzbmj = String.valueOf(paramzzbmj.toString());
      if (paramzzbmj.length() != 0) {}
      for (paramzzbmj = "Invalid write location: ".concat(paramzzbmj);; paramzzbmj = new String("Invalid write location: ")) {
        throw new DatabaseException(paramzzbmj);
      }
    }
  }
  
  public static void zzav(Object paramObject)
  {
    if ((paramObject instanceof Map))
    {
      paramObject = (Map)paramObject;
      if (!((Map)paramObject).containsKey(".sv")) {}
    }
    for (;;)
    {
      return;
      paramObject = ((Map)paramObject).entrySet().iterator();
      while (((Iterator)paramObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)paramObject).next();
        zzjr((String)localEntry.getKey());
        zzav(localEntry.getValue());
      }
      continue;
      if ((paramObject instanceof List))
      {
        paramObject = ((List)paramObject).iterator();
        while (((Iterator)paramObject).hasNext()) {
          zzav(((Iterator)paramObject).next());
        }
      }
    }
  }
  
  public static Map<zzbmj, zzbpe> zzc(zzbmj paramzzbmj, Map<String, Object> paramMap)
    throws DatabaseException
  {
    TreeMap localTreeMap = new TreeMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      paramMap = (Map.Entry)localIterator.next();
      zzbmj localzzbmj = new zzbmj((String)paramMap.getKey());
      Object localObject = paramMap.getValue();
      zzbmy.zza(paramzzbmj.zzh(localzzbmj), localObject);
      if (!localzzbmj.isEmpty()) {}
      for (paramMap = localzzbmj.zzXl().asString(); (paramMap.equals(".sv")) || (paramMap.equals(".value")); paramMap = "")
      {
        paramzzbmj = String.valueOf(localzzbmj);
        throw new DatabaseException(String.valueOf(paramzzbmj).length() + 40 + String.valueOf(paramMap).length() + "Path '" + paramzzbmj + "' contains disallowed child name: " + paramMap);
      }
      if ((paramMap.equals(".priority")) && (!zzbpi.zzq(zzbpf.zzar(localObject))))
      {
        paramzzbmj = String.valueOf(localzzbmj);
        throw new DatabaseException(String.valueOf(paramzzbmj).length() + 83 + "Path '" + paramzzbmj + "' contains invalid priority (must be a string, double, ServerValue, or null).");
      }
      zzav(localObject);
      localTreeMap.put(localzzbmj, zzbpf.zzar(localObject));
    }
    localIterator = localTreeMap.keySet().iterator();
    for (paramzzbmj = null; localIterator.hasNext(); paramzzbmj = paramMap)
    {
      paramMap = (zzbmj)localIterator.next();
      if ((paramzzbmj == null) || (paramzzbmj.zzj(paramMap) < 0)) {}
      for (boolean bool = true;; bool = false)
      {
        zzbqg.zzaW(bool);
        if ((paramzzbmj == null) || (!paramzzbmj.zzi(paramMap))) {
          break;
        }
        paramzzbmj = String.valueOf(paramzzbmj);
        paramMap = String.valueOf(paramMap);
        throw new DatabaseException(String.valueOf(paramzzbmj).length() + 42 + String.valueOf(paramMap).length() + "Path '" + paramzzbmj + "' is an ancestor of '" + paramMap + "' in an update.");
      }
    }
    return localTreeMap;
  }
  
  private static boolean zzjl(String paramString)
  {
    return !zzciC.matcher(paramString).find();
  }
  
  public static void zzjm(String paramString)
    throws DatabaseException
  {
    if (!zzjl(paramString)) {
      throw new DatabaseException(String.valueOf(paramString).length() + 101 + "Invalid Firebase Database path: " + paramString + ". Firebase Database paths must not contain '.', '#', '$', '[', or ']'");
    }
  }
  
  public static void zzjn(String paramString)
    throws DatabaseException
  {
    if (paramString.startsWith(".info"))
    {
      zzjm(paramString.substring(5));
      return;
    }
    if (paramString.startsWith("/.info"))
    {
      zzjm(paramString.substring(6));
      return;
    }
    zzjm(paramString);
  }
  
  private static boolean zzjo(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0) && ((paramString.equals(".value")) || (paramString.equals(".priority")) || ((!paramString.startsWith(".")) && (!zzciD.matcher(paramString).find())));
  }
  
  private static boolean zzjp(String paramString)
  {
    return (paramString.equals(".info")) || (!zzciD.matcher(paramString).find());
  }
  
  public static void zzjq(String paramString)
    throws DatabaseException
  {
    if ((paramString != null) && (!zzjp(paramString))) {
      throw new DatabaseException(String.valueOf(paramString).length() + 68 + "Invalid key: " + paramString + ". Keys must not contain '/', '.', '#', '$', '[', or ']'");
    }
  }
  
  public static void zzjr(String paramString)
    throws DatabaseException
  {
    if (!zzjo(paramString)) {
      throw new DatabaseException(String.valueOf(paramString).length() + 68 + "Invalid key: " + paramString + ". Keys must not contain '/', '.', '#', '$', '[', or ']'");
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */