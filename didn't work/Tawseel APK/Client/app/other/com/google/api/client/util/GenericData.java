package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GenericData
  extends AbstractMap<String, Object>
  implements Cloneable
{
  final ClassInfo classInfo;
  Map<String, Object> unknownFields = ArrayMap.create();
  
  public GenericData()
  {
    this(EnumSet.noneOf(Flags.class));
  }
  
  public GenericData(EnumSet<Flags> paramEnumSet)
  {
    this.classInfo = ClassInfo.of(getClass(), paramEnumSet.contains(Flags.IGNORE_CASE));
  }
  
  public GenericData clone()
  {
    try
    {
      GenericData localGenericData = (GenericData)super.clone();
      Data.deepCopy(this, localGenericData);
      localGenericData.unknownFields = ((Map)Data.clone(this.unknownFields));
      return localGenericData;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new IllegalStateException(localCloneNotSupportedException);
    }
  }
  
  public Set<Map.Entry<String, Object>> entrySet()
  {
    return new EntrySet();
  }
  
  public final Object get(Object paramObject)
  {
    if (!(paramObject instanceof String)) {
      return null;
    }
    String str = (String)paramObject;
    paramObject = this.classInfo.getFieldInfo(str);
    if (paramObject != null) {
      return ((FieldInfo)paramObject).getValue(this);
    }
    paramObject = str;
    if (this.classInfo.getIgnoreCase()) {
      paramObject = str.toLowerCase();
    }
    return this.unknownFields.get(paramObject);
  }
  
  public final ClassInfo getClassInfo()
  {
    return this.classInfo;
  }
  
  public final Map<String, Object> getUnknownKeys()
  {
    return this.unknownFields;
  }
  
  public final Object put(String paramString, Object paramObject)
  {
    Object localObject = this.classInfo.getFieldInfo(paramString);
    if (localObject != null)
    {
      paramString = ((FieldInfo)localObject).getValue(this);
      ((FieldInfo)localObject).setValue(this, paramObject);
      return paramString;
    }
    localObject = paramString;
    if (this.classInfo.getIgnoreCase()) {
      localObject = paramString.toLowerCase();
    }
    return this.unknownFields.put(localObject, paramObject);
  }
  
  public final void putAll(Map<? extends String, ?> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      set((String)localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public final Object remove(Object paramObject)
  {
    if (!(paramObject instanceof String)) {
      return null;
    }
    String str = (String)paramObject;
    if (this.classInfo.getFieldInfo(str) != null) {
      throw new UnsupportedOperationException();
    }
    paramObject = str;
    if (this.classInfo.getIgnoreCase()) {
      paramObject = str.toLowerCase();
    }
    return this.unknownFields.remove(paramObject);
  }
  
  public GenericData set(String paramString, Object paramObject)
  {
    Object localObject = this.classInfo.getFieldInfo(paramString);
    if (localObject != null)
    {
      ((FieldInfo)localObject).setValue(this, paramObject);
      return this;
    }
    localObject = paramString;
    if (this.classInfo.getIgnoreCase()) {
      localObject = paramString.toLowerCase();
    }
    this.unknownFields.put(localObject, paramObject);
    return this;
  }
  
  public final void setUnknownKeys(Map<String, Object> paramMap)
  {
    this.unknownFields = paramMap;
  }
  
  final class EntryIterator
    implements Iterator<Map.Entry<String, Object>>
  {
    private final Iterator<Map.Entry<String, Object>> fieldIterator;
    private boolean startedUnknown;
    private final Iterator<Map.Entry<String, Object>> unknownIterator;
    
    EntryIterator(DataMap.EntrySet paramEntrySet)
    {
      this.fieldIterator = paramEntrySet.iterator();
      this.unknownIterator = GenericData.this.unknownFields.entrySet().iterator();
    }
    
    public boolean hasNext()
    {
      return (this.fieldIterator.hasNext()) || (this.unknownIterator.hasNext());
    }
    
    public Map.Entry<String, Object> next()
    {
      if (!this.startedUnknown)
      {
        if (this.fieldIterator.hasNext()) {
          return (Map.Entry)this.fieldIterator.next();
        }
        this.startedUnknown = true;
      }
      return (Map.Entry)this.unknownIterator.next();
    }
    
    public void remove()
    {
      if (this.startedUnknown) {
        this.unknownIterator.remove();
      }
      this.fieldIterator.remove();
    }
  }
  
  final class EntrySet
    extends AbstractSet<Map.Entry<String, Object>>
  {
    private final DataMap.EntrySet dataEntrySet = new DataMap(GenericData.this, GenericData.this.classInfo.getIgnoreCase()).entrySet();
    
    EntrySet() {}
    
    public void clear()
    {
      GenericData.this.unknownFields.clear();
      this.dataEntrySet.clear();
    }
    
    public Iterator<Map.Entry<String, Object>> iterator()
    {
      return new GenericData.EntryIterator(GenericData.this, this.dataEntrySet);
    }
    
    public int size()
    {
      return GenericData.this.unknownFields.size() + this.dataEntrySet.size();
    }
  }
  
  public static enum Flags
  {
    IGNORE_CASE;
    
    private Flags() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\GenericData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */