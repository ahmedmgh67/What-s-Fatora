package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BeanPropertyMap
  implements Iterable<SettableBeanProperty>, Serializable
{
  private static final long serialVersionUID = 2L;
  protected final boolean _caseInsensitive;
  private Object[] _hashArea;
  private int _hashMask;
  private SettableBeanProperty[] _propsInOrder;
  private int _size;
  private int _spillCount;
  
  public BeanPropertyMap(boolean paramBoolean, Collection<SettableBeanProperty> paramCollection)
  {
    this._caseInsensitive = paramBoolean;
    this._propsInOrder = ((SettableBeanProperty[])paramCollection.toArray(new SettableBeanProperty[paramCollection.size()]));
    init(paramCollection);
  }
  
  private final SettableBeanProperty _find2(String paramString, int paramInt, Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return null;
      int i = this._hashMask + 1;
      paramInt = (paramInt >> 1) + i << 1;
      paramObject = this._hashArea[paramInt];
      if (paramString.equals(paramObject)) {
        return (SettableBeanProperty)this._hashArea[(paramInt + 1)];
      }
      if (paramObject != null)
      {
        i = (i >> 1) + i << 1;
        int j = this._spillCount;
        paramInt = i;
        while (paramInt < i + j)
        {
          paramObject = this._hashArea[paramInt];
          if ((paramObject == paramString) || (paramString.equals(paramObject))) {
            return (SettableBeanProperty)this._hashArea[(paramInt + 1)];
          }
          paramInt += 2;
        }
      }
    }
  }
  
  private final int _findFromOrdered(SettableBeanProperty paramSettableBeanProperty)
  {
    int i = 0;
    int j = this._propsInOrder.length;
    while (i < j)
    {
      if (this._propsInOrder[i] == paramSettableBeanProperty) {
        return i;
      }
      i += 1;
    }
    throw new IllegalStateException("Illegal state: property '" + paramSettableBeanProperty.getName() + "' missing from _propsInOrder");
  }
  
  private final int _findIndexInHash(String paramString)
  {
    int i = _hashCode(paramString);
    int j = i << 1;
    if (paramString.equals(this._hashArea[j])) {
      return j + 1;
    }
    j = this._hashMask + 1;
    i = (i >> 1) + j << 1;
    if (paramString.equals(this._hashArea[i])) {
      return i + 1;
    }
    j = (j >> 1) + j << 1;
    int k = this._spillCount;
    i = j;
    while (i < j + k)
    {
      if (paramString.equals(this._hashArea[i])) {
        return i + 1;
      }
      i += 2;
    }
    return -1;
  }
  
  private final int _hashCode(String paramString)
  {
    return paramString.hashCode() & this._hashMask;
  }
  
  public static BeanPropertyMap construct(Collection<SettableBeanProperty> paramCollection, boolean paramBoolean)
  {
    return new BeanPropertyMap(paramBoolean, paramCollection);
  }
  
  private static final int findSize(int paramInt)
  {
    int j;
    if (paramInt <= 5)
    {
      j = 8;
      return j;
    }
    if (paramInt <= 12) {
      return 16;
    }
    int i = 32;
    for (;;)
    {
      j = i;
      if (i >= paramInt + (paramInt >> 2)) {
        break;
      }
      i += i;
    }
  }
  
  private List<SettableBeanProperty> properties()
  {
    ArrayList localArrayList = new ArrayList(this._size);
    int i = 1;
    int j = this._hashArea.length;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if (localSettableBeanProperty != null) {
        localArrayList.add(localSettableBeanProperty);
      }
      i += 2;
    }
    return localArrayList;
  }
  
  protected SettableBeanProperty _rename(SettableBeanProperty paramSettableBeanProperty, NameTransformer paramNameTransformer)
  {
    if (paramSettableBeanProperty == null) {
      return paramSettableBeanProperty;
    }
    SettableBeanProperty localSettableBeanProperty = paramSettableBeanProperty.withSimpleName(paramNameTransformer.transform(paramSettableBeanProperty.getName()));
    JsonDeserializer localJsonDeserializer = localSettableBeanProperty.getValueDeserializer();
    paramSettableBeanProperty = localSettableBeanProperty;
    if (localJsonDeserializer != null)
    {
      paramNameTransformer = localJsonDeserializer.unwrappingDeserializer(paramNameTransformer);
      paramSettableBeanProperty = localSettableBeanProperty;
      if (paramNameTransformer != localJsonDeserializer) {
        paramSettableBeanProperty = localSettableBeanProperty.withValueDeserializer(paramNameTransformer);
      }
    }
    return paramSettableBeanProperty;
  }
  
  public BeanPropertyMap assignIndexes()
  {
    int j = 1;
    int m = this._hashArea.length;
    int i = 0;
    if (j < m)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[j];
      if (localSettableBeanProperty == null) {
        break label54;
      }
      int k = i + 1;
      localSettableBeanProperty.assignIndex(i);
      i = k;
    }
    label54:
    for (;;)
    {
      j += 2;
      break;
      return this;
    }
  }
  
  public SettableBeanProperty find(int paramInt)
  {
    int i = 1;
    int j = this._hashArea.length;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if ((localSettableBeanProperty != null) && (paramInt == localSettableBeanProperty.getPropertyIndex())) {
        return localSettableBeanProperty;
      }
      i += 2;
    }
    return null;
  }
  
  public SettableBeanProperty find(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Can not pass null property name");
    }
    String str = paramString;
    if (this._caseInsensitive) {
      str = paramString.toLowerCase();
    }
    int i = str.hashCode() & this._hashMask;
    int j = i << 1;
    paramString = this._hashArea[j];
    if ((paramString == str) || (str.equals(paramString))) {
      return (SettableBeanProperty)this._hashArea[(j + 1)];
    }
    return _find2(str, i, paramString);
  }
  
  public boolean findDeserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException
  {
    SettableBeanProperty localSettableBeanProperty = find(paramString);
    if (localSettableBeanProperty == null) {
      return false;
    }
    try
    {
      localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
      return true;
    }
    catch (Exception paramJsonParser)
    {
      for (;;)
      {
        wrapAndThrow(paramJsonParser, paramObject, paramString, paramDeserializationContext);
      }
    }
  }
  
  public SettableBeanProperty[] getPropertiesInInsertionOrder()
  {
    return this._propsInOrder;
  }
  
  protected final String getPropertyName(SettableBeanProperty paramSettableBeanProperty)
  {
    if (this._caseInsensitive) {
      return paramSettableBeanProperty.getName().toLowerCase();
    }
    return paramSettableBeanProperty.getName();
  }
  
  protected void init(Collection<SettableBeanProperty> paramCollection)
  {
    this._size = paramCollection.size();
    int n = findSize(this._size);
    this._hashMask = (n - 1);
    Object localObject = new Object[((n >> 1) + n) * 2];
    int k = 0;
    Iterator localIterator = paramCollection.iterator();
    paramCollection = (Collection<SettableBeanProperty>)localObject;
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
      if (localSettableBeanProperty != null)
      {
        String str = getPropertyName(localSettableBeanProperty);
        int i1 = _hashCode(str);
        int m = i1 << 1;
        localObject = paramCollection;
        int i = m;
        int j = k;
        if (paramCollection[m] != null)
        {
          m = (i1 >> 1) + n << 1;
          localObject = paramCollection;
          i = m;
          j = k;
          if (paramCollection[m] != null)
          {
            m = ((n >> 1) + n << 1) + k;
            k += 2;
            localObject = paramCollection;
            i = m;
            j = k;
            if (m >= paramCollection.length)
            {
              localObject = Arrays.copyOf(paramCollection, paramCollection.length + 4);
              j = k;
              i = m;
            }
          }
        }
        localObject[i] = str;
        localObject[(i + 1)] = localSettableBeanProperty;
        paramCollection = (Collection<SettableBeanProperty>)localObject;
        k = j;
      }
    }
    this._hashArea = paramCollection;
    this._spillCount = k;
  }
  
  public Iterator<SettableBeanProperty> iterator()
  {
    return properties().iterator();
  }
  
  public void remove(SettableBeanProperty paramSettableBeanProperty)
  {
    ArrayList localArrayList = new ArrayList(this._size);
    String str = getPropertyName(paramSettableBeanProperty);
    boolean bool2 = false;
    int i = 1;
    int j = this._hashArea.length;
    if (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      boolean bool1;
      if (localSettableBeanProperty == null) {
        bool1 = bool2;
      }
      for (;;)
      {
        i += 2;
        bool2 = bool1;
        break;
        bool1 = bool2;
        if (!bool2)
        {
          bool2 = str.equals(localSettableBeanProperty.getName());
          bool1 = bool2;
          if (bool2)
          {
            this._propsInOrder[_findFromOrdered(localSettableBeanProperty)] = null;
            bool1 = bool2;
            continue;
          }
        }
        localArrayList.add(localSettableBeanProperty);
      }
    }
    if (!bool2) {
      throw new NoSuchElementException("No entry '" + paramSettableBeanProperty.getName() + "' found, can't remove");
    }
    init(localArrayList);
  }
  
  public BeanPropertyMap renameAll(NameTransformer paramNameTransformer)
  {
    if ((paramNameTransformer == null) || (paramNameTransformer == NameTransformer.NOP)) {
      return this;
    }
    int j = this._propsInOrder.length;
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    if (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = this._propsInOrder[i];
      if (localSettableBeanProperty == null) {
        localArrayList.add(localSettableBeanProperty);
      }
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(_rename(localSettableBeanProperty, paramNameTransformer));
      }
    }
    return new BeanPropertyMap(this._caseInsensitive, localArrayList);
  }
  
  public void replace(SettableBeanProperty paramSettableBeanProperty)
  {
    Object localObject = getPropertyName(paramSettableBeanProperty);
    int i = _findIndexInHash((String)localObject);
    if (i >= 0)
    {
      localObject = (SettableBeanProperty)this._hashArea[i];
      this._hashArea[i] = paramSettableBeanProperty;
      this._propsInOrder[_findFromOrdered(localObject)] = paramSettableBeanProperty;
      return;
    }
    throw new NoSuchElementException("No entry '" + (String)localObject + "' found, can't replace");
  }
  
  public int size()
  {
    return this._size;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Properties=[");
    int i = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(localSettableBeanProperty.getName());
      localStringBuilder.append('(');
      localStringBuilder.append(localSettableBeanProperty.getType());
      localStringBuilder.append(')');
      i += 1;
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public BeanPropertyMap withProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    String str = getPropertyName(paramSettableBeanProperty);
    int i = 1;
    int j = this._hashArea.length;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if ((localSettableBeanProperty != null) && (localSettableBeanProperty.getName().equals(str)))
      {
        this._hashArea[i] = paramSettableBeanProperty;
        this._propsInOrder[_findFromOrdered(localSettableBeanProperty)] = paramSettableBeanProperty;
        return this;
      }
      i += 2;
    }
    int m = _hashCode(str);
    int k = this._hashMask + 1;
    j = m << 1;
    i = j;
    if (this._hashArea[j] != null)
    {
      j = (m >> 1) + k << 1;
      i = j;
      if (this._hashArea[j] != null)
      {
        j = ((k >> 1) + k << 1) + this._spillCount;
        this._spillCount += 2;
        i = j;
        if (j >= this._hashArea.length)
        {
          this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + 4);
          i = j;
        }
      }
    }
    this._hashArea[i] = str;
    this._hashArea[(i + 1)] = paramSettableBeanProperty;
    i = this._propsInOrder.length;
    this._propsInOrder = ((SettableBeanProperty[])Arrays.copyOf(this._propsInOrder, i + 1));
    this._propsInOrder[i] = paramSettableBeanProperty;
    return this;
  }
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonProcessingException))) {
        break label100;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    label100:
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\impl\BeanPropertyMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */