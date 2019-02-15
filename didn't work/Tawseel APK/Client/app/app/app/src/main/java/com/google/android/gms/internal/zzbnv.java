package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbnv<T>
{
  public T value;
  public Map<zzbos, zzbnv<T>> zzcdw = new HashMap();
  
  String toString(String paramString)
  {
    String str1 = String.valueOf(this.value);
    str1 = String.valueOf(paramString).length() + 10 + String.valueOf(str1).length() + paramString + "<value>: " + str1 + "\n";
    Object localObject;
    if (this.zzcdw.isEmpty())
    {
      localObject = String.valueOf(str1).length() + 7 + String.valueOf(paramString).length() + str1 + paramString + "<empty>";
      return (String)localObject;
    }
    Iterator localIterator = this.zzcdw.entrySet().iterator();
    for (;;)
    {
      localObject = str1;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (Map.Entry)localIterator.next();
      str1 = String.valueOf(str1);
      String str2 = String.valueOf(((Map.Entry)localObject).getKey());
      localObject = String.valueOf(((zzbnv)((Map.Entry)localObject).getValue()).toString(String.valueOf(paramString).concat("\t")));
      str1 = String.valueOf(str1).length() + 3 + String.valueOf(paramString).length() + String.valueOf(str2).length() + String.valueOf(localObject).length() + str1 + paramString + str2 + ":\n" + (String)localObject + "\n";
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */