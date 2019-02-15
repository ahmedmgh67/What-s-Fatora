package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

final class DataMap
  extends AbstractMap<String, Object>
{
  final ClassInfo classInfo;
  final Object object;
  
  DataMap(Object paramObject, boolean paramBoolean)
  {
    this.object = paramObject;
    this.classInfo = ClassInfo.of(paramObject.getClass(), paramBoolean);
    if (!this.classInfo.isEnum()) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      Preconditions.checkArgument(paramBoolean);
      return;
    }
  }
  
  public boolean containsKey(Object paramObject)
  {
    return get(paramObject) != null;
  }
  
  public EntrySet entrySet()
  {
    return new EntrySet();
  }
  
  public Object get(Object paramObject)
  {
    if (!(paramObject instanceof String)) {}
    do
    {
      return null;
      paramObject = this.classInfo.getFieldInfo((String)paramObject);
    } while (paramObject == null);
    return ((FieldInfo)paramObject).getValue(this.object);
  }
  
  public Object put(String paramString, Object paramObject)
  {
    FieldInfo localFieldInfo = this.classInfo.getFieldInfo(paramString);
    Preconditions.checkNotNull(localFieldInfo, "no field of key " + paramString);
    paramString = localFieldInfo.getValue(this.object);
    localFieldInfo.setValue(this.object, Preconditions.checkNotNull(paramObject));
    return paramString;
  }
  
  final class Entry
    implements Map.Entry<String, Object>
  {
    private final FieldInfo fieldInfo;
    private Object fieldValue;
    
    Entry(FieldInfo paramFieldInfo, Object paramObject)
    {
      this.fieldInfo = paramFieldInfo;
      this.fieldValue = Preconditions.checkNotNull(paramObject);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof Map.Entry)) {
          return false;
        }
        paramObject = (Map.Entry)paramObject;
      } while ((getKey().equals(((Map.Entry)paramObject).getKey())) && (getValue().equals(((Map.Entry)paramObject).getValue())));
      return false;
    }
    
    public String getKey()
    {
      String str2 = this.fieldInfo.getName();
      String str1 = str2;
      if (DataMap.this.classInfo.getIgnoreCase()) {
        str1 = str2.toLowerCase();
      }
      return str1;
    }
    
    public Object getValue()
    {
      return this.fieldValue;
    }
    
    public int hashCode()
    {
      return getKey().hashCode() ^ getValue().hashCode();
    }
    
    public Object setValue(Object paramObject)
    {
      Object localObject = this.fieldValue;
      this.fieldValue = Preconditions.checkNotNull(paramObject);
      this.fieldInfo.setValue(DataMap.this.object, paramObject);
      return localObject;
    }
  }
  
  final class EntryIterator
    implements Iterator<Map.Entry<String, Object>>
  {
    private FieldInfo currentFieldInfo;
    private boolean isComputed;
    private boolean isRemoved;
    private FieldInfo nextFieldInfo;
    private Object nextFieldValue;
    private int nextKeyIndex = -1;
    
    EntryIterator() {}
    
    public boolean hasNext()
    {
      if (!this.isComputed)
      {
        this.isComputed = true;
        for (this.nextFieldValue = null; this.nextFieldValue == null; this.nextFieldValue = this.nextFieldInfo.getValue(DataMap.this.object))
        {
          int i = this.nextKeyIndex + 1;
          this.nextKeyIndex = i;
          if (i >= DataMap.this.classInfo.names.size()) {
            break;
          }
          this.nextFieldInfo = DataMap.this.classInfo.getFieldInfo((String)DataMap.this.classInfo.names.get(this.nextKeyIndex));
        }
      }
      return this.nextFieldValue != null;
    }
    
    public Map.Entry<String, Object> next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.currentFieldInfo = this.nextFieldInfo;
      Object localObject = this.nextFieldValue;
      this.isComputed = false;
      this.isRemoved = false;
      this.nextFieldInfo = null;
      this.nextFieldValue = null;
      return new DataMap.Entry(DataMap.this, this.currentFieldInfo, localObject);
    }
    
    public void remove()
    {
      if ((this.currentFieldInfo != null) && (!this.isRemoved)) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkState(bool);
        this.isRemoved = true;
        this.currentFieldInfo.setValue(DataMap.this.object, null);
        return;
      }
    }
  }
  
  final class EntrySet
    extends AbstractSet<Map.Entry<String, Object>>
  {
    EntrySet() {}
    
    public void clear()
    {
      Iterator localIterator = DataMap.this.classInfo.names.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        DataMap.this.classInfo.getFieldInfo(str).setValue(DataMap.this.object, null);
      }
    }
    
    public boolean isEmpty()
    {
      Iterator localIterator = DataMap.this.classInfo.names.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (DataMap.this.classInfo.getFieldInfo(str).getValue(DataMap.this.object) != null) {
          return false;
        }
      }
      return true;
    }
    
    public DataMap.EntryIterator iterator()
    {
      return new DataMap.EntryIterator(DataMap.this);
    }
    
    public int size()
    {
      int i = 0;
      Iterator localIterator = DataMap.this.classInfo.names.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (DataMap.this.classInfo.getFieldInfo(str).getValue(DataMap.this.object) != null) {
          i += 1;
        }
      }
      return i;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\DataMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */