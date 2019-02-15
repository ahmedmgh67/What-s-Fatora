package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class CollectionType
  extends CollectionLikeType
{
  private static final long serialVersionUID = 1L;
  
  protected CollectionType(TypeBase paramTypeBase, JavaType paramJavaType)
  {
    super(paramTypeBase, paramJavaType);
  }
  
  private CollectionType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2, paramObject1, paramObject2, paramBoolean);
  }
  
  @Deprecated
  public static CollectionType construct(Class<?> paramClass, JavaType paramJavaType)
  {
    Object localObject = paramClass.getTypeParameters();
    if ((localObject == null) || (localObject.length != 1)) {}
    for (localObject = TypeBindings.emptyBindings();; localObject = TypeBindings.create(paramClass, paramJavaType)) {
      return new CollectionType(paramClass, (TypeBindings)localObject, _bogusSuperClass(paramClass), null, paramJavaType, null, null, false);
    }
  }
  
  public static CollectionType construct(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2)
  {
    return new CollectionType(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2, null, null, false);
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new CollectionType(paramClass, this._bindings, this._superClass, this._superInterfaces, this._elementType, null, null, this._asStatic);
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return new CollectionType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public String toString()
  {
    return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    if (this._elementType == paramJavaType) {
      return this;
    }
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, paramJavaType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withContentTypeHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withContentValueHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withStaticTyping()
  {
    if (this._asStatic) {
      return this;
    }
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
  }
  
  public CollectionType withTypeHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public CollectionType withValueHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\CollectionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */