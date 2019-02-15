package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzblo
{
  public static String zzT(List<String> paramList)
  {
    if (paramList.isEmpty()) {
      return "/";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    int i = 1;
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (i == 0) {
        localStringBuilder.append("/");
      }
      i = 0;
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }
  
  public static void zzaW(boolean paramBoolean)
  {
    zzc(paramBoolean, "", new Object[0]);
  }
  
  public static Long zzao(Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return Long.valueOf(((Integer)paramObject).intValue());
    }
    if ((paramObject instanceof Long)) {
      return (Long)paramObject;
    }
    return null;
  }
  
  public static void zzc(boolean paramBoolean, String paramString, Object... paramVarArgs)
  {
    if (!paramBoolean)
    {
      paramString = String.valueOf(String.format(paramString, paramVarArgs));
      if (paramString.length() != 0) {}
      for (paramString = "hardAssert failed: ".concat(paramString);; paramString = new String("hardAssert failed: ")) {
        throw new AssertionError(paramString);
      }
    }
  }
  
  public static List<String> zziN(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split("/");
    int i = 0;
    while (i < paramString.length)
    {
      if (!paramString[i].isEmpty()) {
        localArrayList.add(paramString[i]);
      }
      i += 1;
    }
    return localArrayList;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */