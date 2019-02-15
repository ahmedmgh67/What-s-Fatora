package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class PropertyBasedCreator
{
  protected final SettableBeanProperty[] _allProperties;
  protected final int _propertyCount;
  protected final HashMap<String, SettableBeanProperty> _propertyLookup;
  protected final ValueInstantiator _valueInstantiator;
  
  protected PropertyBasedCreator(ValueInstantiator paramValueInstantiator, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    this._valueInstantiator = paramValueInstantiator;
    this._propertyLookup = new HashMap();
    int j = paramArrayOfSettableBeanProperty.length;
    this._propertyCount = j;
    this._allProperties = new SettableBeanProperty[j];
    int i = 0;
    while (i < j)
    {
      paramValueInstantiator = paramArrayOfSettableBeanProperty[i];
      this._allProperties[i] = paramValueInstantiator;
      this._propertyLookup.put(paramValueInstantiator.getName(), paramValueInstantiator);
      i += 1;
    }
  }
  
  public static PropertyBasedCreator construct(DeserializationContext paramDeserializationContext, ValueInstantiator paramValueInstantiator, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
    throws JsonMappingException
  {
    int j = paramArrayOfSettableBeanProperty.length;
    SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[j];
    int i = 0;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty2 = paramArrayOfSettableBeanProperty[i];
      SettableBeanProperty localSettableBeanProperty1 = localSettableBeanProperty2;
      if (!localSettableBeanProperty2.hasValueDeserializer()) {
        localSettableBeanProperty1 = localSettableBeanProperty2.withValueDeserializer(paramDeserializationContext.findContextualValueDeserializer(localSettableBeanProperty2.getType(), localSettableBeanProperty2));
      }
      arrayOfSettableBeanProperty[i] = localSettableBeanProperty1;
      i += 1;
    }
    return new PropertyBasedCreator(paramValueInstantiator, arrayOfSettableBeanProperty);
  }
  
  public Object build(DeserializationContext paramDeserializationContext, PropertyValueBuffer paramPropertyValueBuffer)
    throws IOException
  {
    Object localObject2 = this._valueInstantiator.createFromObjectWith(paramDeserializationContext, paramPropertyValueBuffer.getParameters(this._allProperties));
    Object localObject1 = localObject2;
    if (localObject2 != null)
    {
      localObject2 = paramPropertyValueBuffer.handleIdValue(paramDeserializationContext, localObject2);
      for (paramDeserializationContext = paramPropertyValueBuffer.buffered();; paramDeserializationContext = paramDeserializationContext.next)
      {
        localObject1 = localObject2;
        if (paramDeserializationContext == null) {
          break;
        }
        paramDeserializationContext.assign(localObject2);
      }
    }
    return localObject1;
  }
  
  public SettableBeanProperty findCreatorProperty(int paramInt)
  {
    Iterator localIterator = this._propertyLookup.values().iterator();
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
      if (localSettableBeanProperty.getPropertyIndex() == paramInt) {
        return localSettableBeanProperty;
      }
    }
    return null;
  }
  
  public SettableBeanProperty findCreatorProperty(String paramString)
  {
    return (SettableBeanProperty)this._propertyLookup.get(paramString);
  }
  
  public Collection<SettableBeanProperty> properties()
  {
    return this._propertyLookup.values();
  }
  
  public PropertyValueBuffer startBuilding(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, ObjectIdReader paramObjectIdReader)
  {
    return new PropertyValueBuffer(paramJsonParser, paramDeserializationContext, this._propertyCount, paramObjectIdReader);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\impl\PropertyBasedCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */