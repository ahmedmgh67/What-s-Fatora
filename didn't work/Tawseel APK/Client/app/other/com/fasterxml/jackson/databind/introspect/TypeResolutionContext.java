package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

public abstract interface TypeResolutionContext
{
  public abstract JavaType resolveType(Type paramType);
  
  public static class Basic
    implements TypeResolutionContext
  {
    private final TypeBindings _bindings;
    private final TypeFactory _typeFactory;
    
    public Basic(TypeFactory paramTypeFactory, TypeBindings paramTypeBindings)
    {
      this._typeFactory = paramTypeFactory;
      this._bindings = paramTypeBindings;
    }
    
    public JavaType resolveType(Type paramType)
    {
      return this._typeFactory.constructType(paramType, this._bindings);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\TypeResolutionContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */