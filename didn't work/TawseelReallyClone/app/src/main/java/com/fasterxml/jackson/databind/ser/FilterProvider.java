package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public abstract class FilterProvider
{
  @Deprecated
  public abstract BeanPropertyFilter findFilter(Object paramObject);
  
  public PropertyFilter findPropertyFilter(Object paramObject1, Object paramObject2)
  {
    paramObject1 = findFilter(paramObject1);
    if (paramObject1 == null) {
      return null;
    }
    return SimpleBeanPropertyFilter.from((BeanPropertyFilter)paramObject1);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\FilterProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */