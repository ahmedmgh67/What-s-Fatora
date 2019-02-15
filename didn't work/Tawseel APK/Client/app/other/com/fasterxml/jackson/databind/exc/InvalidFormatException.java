package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;

public class InvalidFormatException
  extends JsonMappingException
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _targetType;
  protected final Object _value;
  
  public InvalidFormatException(JsonParser paramJsonParser, String paramString, Object paramObject, Class<?> paramClass)
  {
    super(paramJsonParser, paramString);
    this._value = paramObject;
    this._targetType = paramClass;
  }
  
  @Deprecated
  public InvalidFormatException(String paramString, JsonLocation paramJsonLocation, Object paramObject, Class<?> paramClass)
  {
    super(null, paramString, paramJsonLocation);
    this._value = paramObject;
    this._targetType = paramClass;
  }
  
  @Deprecated
  public InvalidFormatException(String paramString, Object paramObject, Class<?> paramClass)
  {
    super(null, paramString);
    this._value = paramObject;
    this._targetType = paramClass;
  }
  
  public static InvalidFormatException from(JsonParser paramJsonParser, String paramString, Object paramObject, Class<?> paramClass)
  {
    return new InvalidFormatException(paramJsonParser, paramString, paramObject, paramClass);
  }
  
  public Class<?> getTargetType()
  {
    return this._targetType;
  }
  
  public Object getValue()
  {
    return this._value;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\exc\InvalidFormatException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */