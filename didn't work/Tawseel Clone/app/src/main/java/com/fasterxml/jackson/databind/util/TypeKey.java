package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;

public class TypeKey
{
  protected Class<?> _class;
  protected int _hashCode;
  protected boolean _isTyped;
  protected JavaType _type;
  
  public TypeKey() {}
  
  public TypeKey(JavaType paramJavaType, boolean paramBoolean)
  {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = paramBoolean;
    if (paramBoolean) {}
    for (int i = typedHash(paramJavaType);; i = untypedHash(paramJavaType))
    {
      this._hashCode = i;
      return;
    }
  }
  
  public TypeKey(TypeKey paramTypeKey)
  {
    this._hashCode = paramTypeKey._hashCode;
    this._class = paramTypeKey._class;
    this._type = paramTypeKey._type;
    this._isTyped = paramTypeKey._isTyped;
  }
  
  public TypeKey(Class<?> paramClass, boolean paramBoolean)
  {
    this._class = paramClass;
    this._type = null;
    this._isTyped = paramBoolean;
    if (paramBoolean) {}
    for (int i = typedHash(paramClass);; i = untypedHash(paramClass))
    {
      this._hashCode = i;
      return;
    }
  }
  
  public static final int typedHash(JavaType paramJavaType)
  {
    return paramJavaType.hashCode() - 2;
  }
  
  public static final int typedHash(Class<?> paramClass)
  {
    return paramClass.getName().hashCode() + 1;
  }
  
  public static final int untypedHash(JavaType paramJavaType)
  {
    return paramJavaType.hashCode() - 1;
  }
  
  public static final int untypedHash(Class<?> paramClass)
  {
    return paramClass.getName().hashCode();
  }
  
  public final boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null) {}
    do
    {
      do
      {
        return false;
        if (paramObject == this) {
          return true;
        }
      } while (paramObject.getClass() != getClass());
      paramObject = (TypeKey)paramObject;
    } while (((TypeKey)paramObject)._isTyped != this._isTyped);
    if (this._class != null)
    {
      if (((TypeKey)paramObject)._class == this._class) {}
      for (;;)
      {
        return bool;
        bool = false;
      }
    }
    return this._type.equals(((TypeKey)paramObject)._type);
  }
  
  public Class<?> getRawType()
  {
    return this._class;
  }
  
  public JavaType getType()
  {
    return this._type;
  }
  
  public final int hashCode()
  {
    return this._hashCode;
  }
  
  public boolean isTyped()
  {
    return this._isTyped;
  }
  
  public final void resetTyped(JavaType paramJavaType)
  {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = true;
    this._hashCode = typedHash(paramJavaType);
  }
  
  public final void resetTyped(Class<?> paramClass)
  {
    this._type = null;
    this._class = paramClass;
    this._isTyped = true;
    this._hashCode = typedHash(paramClass);
  }
  
  public final void resetUntyped(JavaType paramJavaType)
  {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = false;
    this._hashCode = untypedHash(paramJavaType);
  }
  
  public final void resetUntyped(Class<?> paramClass)
  {
    this._type = null;
    this._class = paramClass;
    this._isTyped = false;
    this._hashCode = untypedHash(paramClass);
  }
  
  public final String toString()
  {
    if (this._class != null) {
      return "{class: " + this._class.getName() + ", typed? " + this._isTyped + "}";
    }
    return "{type: " + this._type + ", typed? " + this._isTyped + "}";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\TypeKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */