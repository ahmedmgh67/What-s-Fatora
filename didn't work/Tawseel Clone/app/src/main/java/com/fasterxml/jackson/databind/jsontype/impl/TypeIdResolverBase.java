package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class TypeIdResolverBase
  implements TypeIdResolver
{
  protected final JavaType _baseType;
  protected final TypeFactory _typeFactory;
  
  protected TypeIdResolverBase()
  {
    this(null, null);
  }
  
  protected TypeIdResolverBase(JavaType paramJavaType, TypeFactory paramTypeFactory)
  {
    this._baseType = paramJavaType;
    this._typeFactory = paramTypeFactory;
  }
  
  public String getDescForKnownTypeIds()
  {
    return null;
  }
  
  public String idFromBaseType()
  {
    return idFromValueAndType(null, this._baseType.getRawClass());
  }
  
  public void init(JavaType paramJavaType) {}
  
  public JavaType typeFromId(DatabindContext paramDatabindContext, String paramString)
  {
    throw new IllegalStateException("Sub-class " + getClass().getName() + " MUST implement " + "`typeFromId(DatabindContext,String)");
  }
  
  @Deprecated
  public JavaType typeFromId(String paramString)
  {
    return typeFromId(null, paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\jsontype\impl\TypeIdResolverBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */