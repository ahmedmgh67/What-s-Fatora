package com.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class MemberKey
{
  static final Class<?>[] NO_CLASSES = new Class[0];
  final Class<?>[] _argTypes;
  final String _name;
  
  public MemberKey(String paramString, Class<?>[] paramArrayOfClass)
  {
    this._name = paramString;
    paramString = paramArrayOfClass;
    if (paramArrayOfClass == null) {
      paramString = NO_CLASSES;
    }
    this._argTypes = paramString;
  }
  
  public MemberKey(Constructor<?> paramConstructor)
  {
    this("", paramConstructor.getParameterTypes());
  }
  
  public MemberKey(Method paramMethod)
  {
    this(paramMethod.getName(), paramMethod.getParameterTypes());
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    for (;;)
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (paramObject.getClass() != getClass()) {
        return false;
      }
      paramObject = (MemberKey)paramObject;
      if (!this._name.equals(((MemberKey)paramObject)._name)) {
        return false;
      }
      paramObject = ((MemberKey)paramObject)._argTypes;
      int j = this._argTypes.length;
      if (paramObject.length != j) {
        return false;
      }
      int i = 0;
      while (i < j)
      {
        if (paramObject[i] != this._argTypes[i]) {
          break label92;
        }
        i += 1;
      }
    }
    label92:
    return false;
  }
  
  public int hashCode()
  {
    return this._name.hashCode() + this._argTypes.length;
  }
  
  public String toString()
  {
    return this._name + "(" + this._argTypes.length + "-args)";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\MemberKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */