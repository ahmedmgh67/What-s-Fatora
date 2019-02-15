package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Array;

public final class ArrayType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _componentType;
  protected final Object _emptyArray;
  
  protected ArrayType(JavaType paramJavaType, TypeBindings paramTypeBindings, Object paramObject1, Object paramObject2, Object paramObject3, boolean paramBoolean)
  {
    super(paramObject1.getClass(), paramTypeBindings, null, null, paramJavaType.hashCode(), paramObject2, paramObject3, paramBoolean);
    this._componentType = paramJavaType;
    this._emptyArray = paramObject1;
  }
  
  private JavaType _reportUnsupported()
  {
    throw new UnsupportedOperationException("Can not narrow or widen array types");
  }
  
  public static ArrayType construct(JavaType paramJavaType, TypeBindings paramTypeBindings)
  {
    return construct(paramJavaType, paramTypeBindings, null, null);
  }
  
  public static ArrayType construct(JavaType paramJavaType, TypeBindings paramTypeBindings, Object paramObject1, Object paramObject2)
  {
    return new ArrayType(paramJavaType, paramTypeBindings, Array.newInstance(paramJavaType.getRawClass(), 0), paramObject1, paramObject2, false);
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    return _reportUnsupported();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (paramObject.getClass() != getClass());
    paramObject = (ArrayType)paramObject;
    return this._componentType.equals(((ArrayType)paramObject)._componentType);
  }
  
  public JavaType getContentType()
  {
    return this._componentType;
  }
  
  public Object getContentTypeHandler()
  {
    return this._componentType.getTypeHandler();
  }
  
  public Object getContentValueHandler()
  {
    return this._componentType.getValueHandler();
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append('[');
    return this._componentType.getErasedSignature(paramStringBuilder);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append('[');
    return this._componentType.getGenericSignature(paramStringBuilder);
  }
  
  public boolean hasGenericTypes()
  {
    return this._componentType.hasGenericTypes();
  }
  
  public boolean isAbstract()
  {
    return false;
  }
  
  public boolean isArrayType()
  {
    return true;
  }
  
  public boolean isConcrete()
  {
    return true;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return null;
  }
  
  public String toString()
  {
    return "[array type, component type: " + this._componentType + "]";
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    Object localObject = Array.newInstance(paramJavaType.getRawClass(), 0);
    return new ArrayType(paramJavaType, this._bindings, localObject, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withContentTypeHandler(Object paramObject)
  {
    if (paramObject == this._componentType.getTypeHandler()) {
      return this;
    }
    return new ArrayType(this._componentType.withTypeHandler(paramObject), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withContentValueHandler(Object paramObject)
  {
    if (paramObject == this._componentType.getValueHandler()) {
      return this;
    }
    return new ArrayType(this._componentType.withValueHandler(paramObject), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ArrayType withStaticTyping()
  {
    if (this._asStatic) {
      return this;
    }
    return new ArrayType(this._componentType.withStaticTyping(), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, true);
  }
  
  public ArrayType withTypeHandler(Object paramObject)
  {
    if (paramObject == this._typeHandler) {
      return this;
    }
    return new ArrayType(this._componentType, this._bindings, this._emptyArray, this._valueHandler, paramObject, this._asStatic);
  }
  
  public ArrayType withValueHandler(Object paramObject)
  {
    if (paramObject == this._valueHandler) {
      return this;
    }
    return new ArrayType(this._componentType, this._bindings, this._emptyArray, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\ArrayType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */