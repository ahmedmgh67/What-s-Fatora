package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Map;

public class MapLikeType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _keyType;
  protected final JavaType _valueType;
  
  protected MapLikeType(TypeBase paramTypeBase, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    super(paramTypeBase);
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
  }
  
  protected MapLikeType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2, JavaType paramJavaType3, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2.hashCode() ^ paramJavaType3.hashCode(), paramObject1, paramObject2, paramBoolean);
    this._keyType = paramJavaType2;
    this._valueType = paramJavaType3;
  }
  
  @Deprecated
  public static MapLikeType construct(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    Object localObject = paramClass.getTypeParameters();
    if ((localObject == null) || (localObject.length != 2)) {}
    for (localObject = TypeBindings.emptyBindings();; localObject = TypeBindings.create(paramClass, paramJavaType1, paramJavaType2)) {
      return new MapLikeType(paramClass, (TypeBindings)localObject, _bogusSuperClass(paramClass), null, paramJavaType1, paramJavaType2, null, null, false);
    }
  }
  
  public static MapLikeType upgradeFrom(JavaType paramJavaType1, JavaType paramJavaType2, JavaType paramJavaType3)
  {
    if ((paramJavaType1 instanceof TypeBase)) {
      return new MapLikeType((TypeBase)paramJavaType1, paramJavaType2, paramJavaType3);
    }
    throw new IllegalArgumentException("Can not upgrade from an instance of " + paramJavaType1.getClass());
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new MapLikeType(paramClass, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    if (this._keyType != null)
    {
      localStringBuilder.append('<');
      localStringBuilder.append(this._keyType.toCanonical());
      localStringBuilder.append(',');
      localStringBuilder.append(this._valueType.toCanonical());
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (paramObject.getClass() != getClass()) {
        return false;
      }
      paramObject = (MapLikeType)paramObject;
    } while ((this._class == ((MapLikeType)paramObject)._class) && (this._keyType.equals(((MapLikeType)paramObject)._keyType)) && (this._valueType.equals(((MapLikeType)paramObject)._valueType)));
    return false;
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
  }
  
  public Object getContentTypeHandler()
  {
    return this._valueType.getTypeHandler();
  }
  
  public Object getContentValueHandler()
  {
    return this._valueType.getValueHandler();
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._keyType.getGenericSignature(paramStringBuilder);
    this._valueType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public JavaType getKeyType()
  {
    return this._keyType;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public boolean isMapLikeType()
  {
    return true;
  }
  
  public boolean isTrueMapType()
  {
    return Map.class.isAssignableFrom(this._class);
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return new MapLikeType(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public String toString()
  {
    return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    if (this._valueType == paramJavaType) {
      return this;
    }
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, paramJavaType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withContentTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withContentValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withKeyType(JavaType paramJavaType)
  {
    if (paramJavaType == this._keyType) {
      return this;
    }
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, paramJavaType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withKeyTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withTypeHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withKeyValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.withValueHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withStaticTyping()
  {
    if (this._asStatic) {
      return this;
    }
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
  }
  
  public MapLikeType withTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public MapLikeType withValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\MapLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */