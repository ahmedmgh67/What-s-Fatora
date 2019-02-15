package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzbmy
{
  private final List<String> zzcel = new ArrayList();
  private int zzcem = 0;
  
  private zzbmy(zzbmj paramzzbmj)
    throws DatabaseException
  {
    paramzzbmj = paramzzbmj.iterator();
    while (paramzzbmj.hasNext())
    {
      zzbos localzzbos = (zzbos)paramzzbmj.next();
      this.zzcel.add(localzzbos.asString());
    }
    this.zzcem = Math.max(1, this.zzcel.size());
    while (i < this.zzcel.size())
    {
      int j = this.zzcem;
      this.zzcem = (zza((CharSequence)this.zzcel.get(i)) + j);
      i += 1;
    }
    zzSQ();
  }
  
  private void zzSQ()
    throws DatabaseException
  {
    String str1;
    if (this.zzcem > 768)
    {
      str1 = String.valueOf("Data has a key path longer than 768 bytes (");
      int i = this.zzcem;
      throw new DatabaseException(String.valueOf(str1).length() + 13 + str1 + i + ").");
    }
    if (this.zzcel.size() > 32)
    {
      str1 = String.valueOf("Path specified exceeds the maximum depth that can be written (32) or object contains a cycle ");
      String str2 = String.valueOf(zzXH());
      if (str2.length() != 0) {}
      for (str1 = str1.concat(str2);; str1 = new String(str1)) {
        throw new DatabaseException(str1);
      }
    }
  }
  
  private String zzXG()
  {
    String str = (String)this.zzcel.remove(this.zzcel.size() - 1);
    this.zzcem -= zza(str);
    if (this.zzcel.size() > 0) {
      this.zzcem -= 1;
    }
    return str;
  }
  
  private String zzXH()
  {
    if (this.zzcel.size() == 0) {
      return "";
    }
    String str = String.valueOf(zze("/", this.zzcel));
    return String.valueOf(str).length() + 10 + "in path '" + str + "'";
  }
  
  private static int zza(CharSequence paramCharSequence)
  {
    int j = 0;
    int k = paramCharSequence.length();
    int i = 0;
    if (j < k)
    {
      char c = paramCharSequence.charAt(j);
      if (c <= '') {
        i += 1;
      }
      for (;;)
      {
        j += 1;
        break;
        if (c <= '߿')
        {
          i += 2;
        }
        else if (Character.isHighSurrogate(c))
        {
          i += 4;
          j += 1;
        }
        else
        {
          i += 3;
        }
      }
    }
    return i;
  }
  
  public static void zza(zzbmj paramzzbmj, Object paramObject)
    throws DatabaseException
  {
    new zzbmy(paramzzbmj).zzap(paramObject);
  }
  
  private void zzap(Object paramObject)
    throws DatabaseException
  {
    if ((paramObject instanceof Map))
    {
      paramObject = (Map)paramObject;
      Iterator localIterator = ((Map)paramObject).keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (!str.startsWith("."))
        {
          zzja(str);
          zzap(((Map)paramObject).get(str));
          zzXG();
        }
      }
    }
    if ((paramObject instanceof List))
    {
      paramObject = (List)paramObject;
      int i = 0;
      while (i < ((List)paramObject).size())
      {
        zzja(Integer.toString(i));
        zzap(((List)paramObject).get(i));
        zzXG();
        i += 1;
      }
    }
  }
  
  private static String zze(String paramString, List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramList.size())
    {
      if (i > 0) {
        localStringBuilder.append(paramString);
      }
      localStringBuilder.append((String)paramList.get(i));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void zzja(String paramString)
    throws DatabaseException
  {
    if (this.zzcel.size() > 0) {
      this.zzcem += 1;
    }
    this.zzcel.add(paramString);
    this.zzcem += zza(paramString);
    zzSQ();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */