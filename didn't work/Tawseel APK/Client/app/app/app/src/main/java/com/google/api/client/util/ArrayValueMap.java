package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ArrayValueMap
{
  private final Object destination;
  private final Map<Field, ArrayValue> fieldMap = ArrayMap.create();
  private final Map<String, ArrayValue> keyMap = ArrayMap.create();
  
  public ArrayValueMap(Object paramObject)
  {
    this.destination = paramObject;
  }
  
  public void put(String paramString, Class<?> paramClass, Object paramObject)
  {
    ArrayValue localArrayValue2 = (ArrayValue)this.keyMap.get(paramString);
    ArrayValue localArrayValue1 = localArrayValue2;
    if (localArrayValue2 == null)
    {
      localArrayValue1 = new ArrayValue(paramClass);
      this.keyMap.put(paramString, localArrayValue1);
    }
    localArrayValue1.addValue(paramClass, paramObject);
  }
  
  public void put(Field paramField, Class<?> paramClass, Object paramObject)
  {
    ArrayValue localArrayValue2 = (ArrayValue)this.fieldMap.get(paramField);
    ArrayValue localArrayValue1 = localArrayValue2;
    if (localArrayValue2 == null)
    {
      localArrayValue1 = new ArrayValue(paramClass);
      this.fieldMap.put(paramField, localArrayValue1);
    }
    localArrayValue1.addValue(paramClass, paramObject);
  }
  
  public void setValues()
  {
    Iterator localIterator = this.keyMap.entrySet().iterator();
    Map.Entry localEntry;
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      ((Map)this.destination).put(localEntry.getKey(), ((ArrayValue)localEntry.getValue()).toArray());
    }
    localIterator = this.fieldMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      FieldInfo.setFieldValue((Field)localEntry.getKey(), this.destination, ((ArrayValue)localEntry.getValue()).toArray());
    }
  }
  
  static class ArrayValue
  {
    final Class<?> componentType;
    final ArrayList<Object> values = new ArrayList();
    
    ArrayValue(Class<?> paramClass)
    {
      this.componentType = paramClass;
    }
    
    void addValue(Class<?> paramClass, Object paramObject)
    {
      if (paramClass == this.componentType) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool);
        this.values.add(paramObject);
        return;
      }
    }
    
    Object toArray()
    {
      return Types.toArray(this.values, this.componentType);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ArrayValueMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */