package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class MapType
  extends MapLikeType
{
  private static final long serialVersionUID = 1L;
  
  protected MapType(TypeBase paramTypeBase, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    super(paramTypeBase, paramJavaType1, paramJavaType2);
  }
  
  private MapType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2, JavaType paramJavaType3, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2, paramJavaType3, paramObject1, paramObject2, paramBoolean);
  }
  
  @Deprecated
  public static MapType construct(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    Object localObject = paramClass.getTypeParameters();
    if ((localObject == null) || (localObject.length != 2)) {}
    for (localObject = TypeBindings.emptyBindings();; localObject = TypeBindings.create(paramClass, paramJavaType1, paramJavaType2)) {
      return new MapType(paramClass, (TypeBindings)localObject, _bogusSuperClass(paramClass), null, paramJavaType1, paramJavaType2, null, null, false);
    }
  }
  
  public static MapType construct(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2, JavaType paramJavaType3)
  {
    return new MapType(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2, paramJavaType3, null, null, false);
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new MapType(paramClass, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return new MapType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public String toString()
  {
    return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    if (this._valueType == paramJavaType) {
      return this;
    }
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, paramJavaType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapType withContentTypeHandler(Object paramObject)
  {
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapType withContentValueHandler(Object paramObject)
  {
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapType withKeyType(JavaType paramJavaType)
  {
    if (paramJavaType == this._keyType) {
      return this;
    }
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, paramJavaType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapType withKeyTypeHandler(Object paramObject)
  {
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withTypeHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapType withKeyValueHandler(Object paramObject)
  {
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withValueHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapType withStaticTyping()
  {
    if (this._asStatic) {
      return this;
    }
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withStaticTyping(), this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
  }
  
  public MapType withTypeHandler(Object paramObject)
  {
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public MapType withValueHandler(Object paramObject)
  {
    return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\MapType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */