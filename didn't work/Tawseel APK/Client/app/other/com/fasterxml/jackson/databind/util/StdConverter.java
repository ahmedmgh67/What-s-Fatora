package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class StdConverter<IN, OUT>
  implements Converter<IN, OUT>
{
  protected JavaType _findConverterType(TypeFactory paramTypeFactory)
  {
    paramTypeFactory = paramTypeFactory.constructType(getClass()).findSuperType(Converter.class);
    if ((paramTypeFactory == null) || (paramTypeFactory.containedTypeCount() < 2)) {
      throw new IllegalStateException("Can not find OUT type parameter for Converter of type " + getClass().getName());
    }
    return paramTypeFactory;
  }
  
  public abstract OUT convert(IN paramIN);
  
  public JavaType getInputType(TypeFactory paramTypeFactory)
  {
    return _findConverterType(paramTypeFactory).containedType(0);
  }
  
  public JavaType getOutputType(TypeFactory paramTypeFactory)
  {
    return _findConverterType(paramTypeFactory).containedType(1);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\StdConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */