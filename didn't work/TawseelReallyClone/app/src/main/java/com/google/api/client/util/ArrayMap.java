package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public class ArrayMap<K, V>
  extends AbstractMap<K, V>
  implements Cloneable
{
  private Object[] data;
  int size;
  
  public static <K, V> ArrayMap<K, V> create()
  {
    return new ArrayMap();
  }
  
  public static <K, V> ArrayMap<K, V> create(int paramInt)
  {
    ArrayMap localArrayMap = create();
    localArrayMap.ensureCapacity(paramInt);
    return localArrayMap;
  }
  
  private int getDataIndexOfKey(Object paramObject)
  {
    int j = this.size;
    Object[] arrayOfObject = this.data;
    int i = 0;
    while (i < j << 1)
    {
      Object localObject = arrayOfObject[i];
      if (paramObject == null)
      {
        if (localObject != null) {}
      }
      else {
        while (paramObject.equals(localObject)) {
          return i;
        }
      }
      i += 2;
    }
    return -2;
  }
  
  public static <K, V> ArrayMap<K, V> of(Object... paramVarArgs)
  {
    ArrayMap localArrayMap = create(1);
    int i = paramVarArgs.length;
    if (1 == i % 2) {
      throw new IllegalArgumentException("missing value for last key: " + paramVarArgs[(i - 1)]);
    }
    localArrayMap.size = (paramVarArgs.length / 2);
    Object[] arrayOfObject = new Object[i];
    localArrayMap.data = arrayOfObject;
    System.arraycopy(paramVarArgs, 0, arrayOfObject, 0, i);
    return localArrayMap;
  }
  
  private V removeFromDataIndexOfKey(int paramInt)
  {
    int i = this.size << 1;
    if ((paramInt < 0) || (paramInt >= i)) {
      return null;
    }
    Object localObject = valueAtDataIndex(paramInt + 1);
    Object[] arrayOfObject = this.data;
    int j = i - paramInt - 2;
    if (j != 0) {
      System.arraycopy(arrayOfObject, paramInt + 2, arrayOfObject, paramInt, j);
    }
    this.size -= 1;
    setData(i - 2, null, null);
    return (V)localObject;
  }
  
  private void setData(int paramInt, K paramK, V paramV)
  {
    Object[] arrayOfObject = this.data;
    arrayOfObject[paramInt] = paramK;
    arrayOfObject[(paramInt + 1)] = paramV;
  }
  
  private void setDataCapacity(int paramInt)
  {
    if (paramInt == 0) {
      this.data = null;
    }
    int i;
    Object[] arrayOfObject1;
    Object[] arrayOfObject2;
    do
    {
      do
      {
        return;
        i = this.size;
        arrayOfObject1 = this.data;
      } while ((i != 0) && (paramInt == arrayOfObject1.length));
      arrayOfObject2 = new Object[paramInt];
      this.data = arrayOfObject2;
    } while (i == 0);
    System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, i << 1);
  }
  
  private V valueAtDataIndex(int paramInt)
  {
    if (paramInt < 0) {
      return null;
    }
    return (V)this.data[paramInt];
  }
  
  public final void add(K paramK, V paramV)
  {
    set(this.size, paramK, paramV);
  }
  
  public void clear()
  {
    this.size = 0;
    this.data = null;
  }
  
  public ArrayMap<K, V> clone()
  {
    try
    {
      ArrayMap localArrayMap = (ArrayMap)super.clone();
      Object[] arrayOfObject1 = this.data;
      if (arrayOfObject1 != null)
      {
        int i = arrayOfObject1.length;
        Object[] arrayOfObject2 = new Object[i];
        localArrayMap.data = arrayOfObject2;
        System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, i);
      }
      return localArrayMap;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException) {}
    return null;
  }
  
  public final boolean containsKey(Object paramObject)
  {
    return -2 != getDataIndexOfKey(paramObject);
  }
  
  public final boolean containsValue(Object paramObject)
  {
    int j = this.size;
    Object[] arrayOfObject = this.data;
    int i = 1;
    while (i < j << 1)
    {
      Object localObject = arrayOfObject[i];
      if (paramObject == null)
      {
        if (localObject != null) {}
      }
      else {
        while (paramObject.equals(localObject)) {
          return true;
        }
      }
      i += 2;
    }
    return false;
  }
  
  public final void ensureCapacity(int paramInt)
  {
    if (paramInt < 0) {
      throw new IndexOutOfBoundsException();
    }
    Object[] arrayOfObject = this.data;
    int j = paramInt << 1;
    if (arrayOfObject == null) {}
    for (paramInt = 0;; paramInt = arrayOfObject.length)
    {
      if (j > paramInt)
      {
        int i = paramInt / 2 * 3 + 1;
        paramInt = i;
        if (i % 2 != 0) {
          paramInt = i + 1;
        }
        i = paramInt;
        if (paramInt < j) {
          i = j;
        }
        setDataCapacity(i);
      }
      return;
    }
  }
  
  public final Set<Map.Entry<K, V>> entrySet()
  {
    return new EntrySet();
  }
  
  public final V get(Object paramObject)
  {
    return (V)valueAtDataIndex(getDataIndexOfKey(paramObject) + 1);
  }
  
  public final int getIndexOfKey(K paramK)
  {
    return getDataIndexOfKey(paramK) >> 1;
  }
  
  public final K getKey(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      return null;
    }
    return (K)this.data[(paramInt << 1)];
  }
  
  public final V getValue(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      return null;
    }
    return (V)valueAtDataIndex((paramInt << 1) + 1);
  }
  
  public final V put(K paramK, V paramV)
  {
    int j = getIndexOfKey(paramK);
    int i = j;
    if (j == -1) {
      i = this.size;
    }
    return (V)set(i, paramK, paramV);
  }
  
  public final V remove(int paramInt)
  {
    return (V)removeFromDataIndexOfKey(paramInt << 1);
  }
  
  public final V remove(Object paramObject)
  {
    return (V)removeFromDataIndexOfKey(getDataIndexOfKey(paramObject));
  }
  
  public final V set(int paramInt, V paramV)
  {
    int i = this.size;
    if ((paramInt < 0) || (paramInt >= i)) {
      throw new IndexOutOfBoundsException();
    }
    paramInt = (paramInt << 1) + 1;
    Object localObject = valueAtDataIndex(paramInt);
    this.data[paramInt] = paramV;
    return (V)localObject;
  }
  
  public final V set(int paramInt, K paramK, V paramV)
  {
    if (paramInt < 0) {
      throw new IndexOutOfBoundsException();
    }
    int i = paramInt + 1;
    ensureCapacity(i);
    paramInt <<= 1;
    Object localObject = valueAtDataIndex(paramInt + 1);
    setData(paramInt, paramK, paramV);
    if (i > this.size) {
      this.size = i;
    }
    return (V)localObject;
  }
  
  public final int size()
  {
    return this.size;
  }
  
  public final void trim()
  {
    setDataCapacity(this.size << 1);
  }
  
  final class Entry
    implements Map.Entry<K, V>
  {
    private int index;
    
    Entry(int paramInt)
    {
      this.index = paramInt;
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
      } while ((Objects.equal(getKey(), ((Map.Entry)paramObject).getKey())) && (Objects.equal(getValue(), ((Map.Entry)paramObject).getValue())));
      return false;
    }
    
    public K getKey()
    {
      return (K)ArrayMap.this.getKey(this.index);
    }
    
    public V getValue()
    {
      return (V)ArrayMap.this.getValue(this.index);
    }
    
    public int hashCode()
    {
      return getKey().hashCode() ^ getValue().hashCode();
    }
    
    public V setValue(V paramV)
    {
      return (V)ArrayMap.this.set(this.index, paramV);
    }
  }
  
  final class EntryIterator
    implements Iterator<Map.Entry<K, V>>
  {
    private int nextIndex;
    private boolean removed;
    
    EntryIterator() {}
    
    public boolean hasNext()
    {
      return this.nextIndex < ArrayMap.this.size;
    }
    
    public Map.Entry<K, V> next()
    {
      int i = this.nextIndex;
      if (i == ArrayMap.this.size) {
        throw new NoSuchElementException();
      }
      this.nextIndex += 1;
      return new ArrayMap.Entry(ArrayMap.this, i);
    }
    
    public void remove()
    {
      int i = this.nextIndex - 1;
      if ((this.removed) || (i < 0)) {
        throw new IllegalArgumentException();
      }
      ArrayMap.this.remove(i);
      this.removed = true;
    }
  }
  
  final class EntrySet
    extends AbstractSet<Map.Entry<K, V>>
  {
    EntrySet() {}
    
    public Iterator<Map.Entry<K, V>> iterator()
    {
      return new ArrayMap.EntryIterator(ArrayMap.this);
    }
    
    public int size()
    {
      return ArrayMap.this.size;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ArrayMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */