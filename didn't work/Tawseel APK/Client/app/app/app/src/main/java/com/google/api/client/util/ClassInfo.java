package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.WeakHashMap;

public final class ClassInfo
{
  private static final Map<Class<?>, ClassInfo> CACHE = new WeakHashMap();
  private static final Map<Class<?>, ClassInfo> CACHE_IGNORE_CASE = new WeakHashMap();
  private final Class<?> clazz;
  private final boolean ignoreCase;
  private final IdentityHashMap<String, FieldInfo> nameToFieldInfoMap = new IdentityHashMap();
  final List<String> names;
  
  private ClassInfo(Class<?> paramClass, boolean paramBoolean)
  {
    this.clazz = paramClass;
    this.ignoreCase = paramBoolean;
    if ((!paramBoolean) || (!paramClass.isEnum())) {}
    TreeSet localTreeSet;
    Field localField;
    FieldInfo localFieldInfo;
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "cannot ignore case on an enum: " + paramClass);
      localTreeSet = new TreeSet(new Comparator()
      {
        public int compare(String paramAnonymousString1, String paramAnonymousString2)
        {
          if (paramAnonymousString1 == paramAnonymousString2) {
            return 0;
          }
          if (paramAnonymousString1 == null) {
            return -1;
          }
          if (paramAnonymousString2 == null) {
            return 1;
          }
          return paramAnonymousString1.compareTo(paramAnonymousString2);
        }
      });
      Field[] arrayOfField = paramClass.getDeclaredFields();
      int j = arrayOfField.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label270;
        }
        localField = arrayOfField[i];
        localFieldInfo = FieldInfo.of(localField);
        if (localFieldInfo != null) {
          break;
        }
        i += 1;
      }
    }
    String str = localFieldInfo.getName();
    Object localObject1 = str;
    if (paramBoolean) {
      localObject1 = str.toLowerCase().intern();
    }
    Object localObject2 = (FieldInfo)this.nameToFieldInfoMap.get(localObject1);
    if (localObject2 == null)
    {
      bool = true;
      label177:
      if (!paramBoolean) {
        break label253;
      }
      str = "case-insensitive ";
      label185:
      if (localObject2 != null) {
        break label260;
      }
    }
    label253:
    label260:
    for (localObject2 = null;; localObject2 = ((FieldInfo)localObject2).getField())
    {
      Preconditions.checkArgument(bool, "two fields have the same %sname <%s>: %s and %s", new Object[] { str, localObject1, localField, localObject2 });
      this.nameToFieldInfoMap.put(localObject1, localFieldInfo);
      localTreeSet.add(localObject1);
      break;
      bool = false;
      break label177;
      str = "";
      break label185;
    }
    label270:
    paramClass = paramClass.getSuperclass();
    if (paramClass != null)
    {
      paramClass = of(paramClass, paramBoolean);
      localTreeSet.addAll(paramClass.names);
      paramClass = paramClass.nameToFieldInfoMap.entrySet().iterator();
      while (paramClass.hasNext())
      {
        localObject1 = (Map.Entry)paramClass.next();
        str = (String)((Map.Entry)localObject1).getKey();
        if (!this.nameToFieldInfoMap.containsKey(str)) {
          this.nameToFieldInfoMap.put(str, ((Map.Entry)localObject1).getValue());
        }
      }
    }
    if (localTreeSet.isEmpty()) {}
    for (paramClass = Collections.emptyList();; paramClass = Collections.unmodifiableList(new ArrayList(localTreeSet)))
    {
      this.names = paramClass;
      return;
    }
  }
  
  public static ClassInfo of(Class<?> paramClass)
  {
    return of(paramClass, false);
  }
  
  public static ClassInfo of(Class<?> paramClass, boolean paramBoolean)
  {
    if (paramClass == null) {
      return null;
    }
    if (paramBoolean) {}
    for (;;)
    {
      synchronized (CACHE_IGNORE_CASE)
      {
        ClassInfo localClassInfo2 = (ClassInfo)???.get(paramClass);
        ClassInfo localClassInfo1 = localClassInfo2;
        if (localClassInfo2 == null)
        {
          localClassInfo1 = new ClassInfo(paramClass, paramBoolean);
          ???.put(paramClass, localClassInfo1);
        }
        return localClassInfo1;
      }
      ??? = CACHE;
    }
  }
  
  public Field getField(String paramString)
  {
    paramString = getFieldInfo(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.getField();
  }
  
  public FieldInfo getFieldInfo(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (this.ignoreCase) {
        str = paramString.toLowerCase();
      }
      str = str.intern();
    }
    return (FieldInfo)this.nameToFieldInfoMap.get(str);
  }
  
  public Collection<FieldInfo> getFieldInfos()
  {
    return Collections.unmodifiableCollection(this.nameToFieldInfoMap.values());
  }
  
  public final boolean getIgnoreCase()
  {
    return this.ignoreCase;
  }
  
  public Collection<String> getNames()
  {
    return this.names;
  }
  
  public Class<?> getUnderlyingClass()
  {
    return this.clazz;
  }
  
  public boolean isEnum()
  {
    return this.clazz.isEnum();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ClassInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */