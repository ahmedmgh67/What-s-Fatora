package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;

public class SimpleType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  
  protected SimpleType(TypeBase paramTypeBase)
  {
    super(paramTypeBase);
  }
  
  protected SimpleType(Class<?> paramClass)
  {
    this(paramClass, TypeBindings.emptyBindings(), null, null);
  }
  
  protected SimpleType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    this(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, null, null, false);
  }
  
  protected SimpleType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType, int paramInt, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, paramInt, paramObject1, paramObject2, paramBoolean);
  }
  
  protected SimpleType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramTypeBindings, paramJavaType, paramArrayOfJavaType, 0, paramObject1, paramObject2, paramBoolean);
  }
  
  private static JavaType _buildSuperClass(Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    if (paramClass == null) {
      return null;
    }
    if (paramClass == Object.class) {
      return TypeFactory.unknownType();
    }
    return new SimpleType(paramClass, paramTypeBindings, _buildSuperClass(paramClass.getSuperclass(), paramTypeBindings), null, null, null, false);
  }
  
  @Deprecated
  public static SimpleType construct(Class<?> paramClass)
  {
    if (Map.class.isAssignableFrom(paramClass)) {
      throw new IllegalArgumentException("Can not construct SimpleType for a Map (class: " + paramClass.getName() + ")");
    }
    if (Collection.class.isAssignableFrom(paramClass)) {
      throw new IllegalArgumentException("Can not construct SimpleType for a Collection (class: " + paramClass.getName() + ")");
    }
    if (paramClass.isArray()) {
      throw new IllegalArgumentException("Can not construct SimpleType for an array (class: " + paramClass.getName() + ")");
    }
    TypeBindings localTypeBindings = TypeBindings.emptyBindings();
    return new SimpleType(paramClass, localTypeBindings, _buildSuperClass(paramClass.getSuperclass(), localTypeBindings), null, null, null, false);
  }
  
  public static SimpleType constructUnsafe(Class<?> paramClass)
  {
    return new SimpleType(paramClass, null, null, null, null, null, false);
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    if (this._class == paramClass) {
      return this;
    }
    if (!this._class.isAssignableFrom(paramClass)) {
      return new SimpleType(paramClass, this._bindings, this, this._superInterfaces, this._valueHandler, this._typeHandler, this._asStatic);
    }
    Object localObject1 = paramClass.getSuperclass();
    if (localObject1 == this._class) {
      return new SimpleType(paramClass, this._bindings, this, this._superInterfaces, this._valueHandler, this._typeHandler, this._asStatic);
    }
    if ((localObject1 != null) && (this._class.isAssignableFrom((Class)localObject1)))
    {
      localObject1 = _narrow((Class)localObject1);
      return new SimpleType(paramClass, this._bindings, (JavaType)localObject1, null, this._valueHandler, this._typeHandler, this._asStatic);
    }
    localObject1 = paramClass.getInterfaces();
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = localObject1[i];
      Object localObject3;
      boolean bool;
      if (localObject2 == this._class)
      {
        localObject1 = this._bindings;
        localObject2 = this._valueHandler;
        localObject3 = this._typeHandler;
        bool = this._asStatic;
        return new SimpleType(paramClass, (TypeBindings)localObject1, null, new JavaType[] { this }, localObject2, localObject3, bool);
      }
      if (this._class.isAssignableFrom((Class)localObject2))
      {
        localObject1 = _narrow((Class)localObject2);
        localObject2 = this._bindings;
        localObject3 = this._valueHandler;
        Object localObject4 = this._typeHandler;
        bool = this._asStatic;
        return new SimpleType(paramClass, (TypeBindings)localObject2, null, new JavaType[] { localObject1 }, localObject3, localObject4, bool);
      }
      i += 1;
    }
    throw new IllegalArgumentException("Internal error: Can not resolve sub-type for Class " + paramClass.getName() + " to " + this._class.getName());
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    int j = this._bindings.size();
    if (j > 0)
    {
      localStringBuilder.append('<');
      int i = 0;
      while (i < j)
      {
        JavaType localJavaType = containedType(i);
        if (i > 0) {
          localStringBuilder.append(',');
        }
        localStringBuilder.append(localJavaType.toCanonical());
        i += 1;
      }
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
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
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == null);
        bool1 = bool2;
      } while (paramObject.getClass() != getClass());
      paramObject = (SimpleType)paramObject;
      bool1 = bool2;
    } while (((SimpleType)paramObject)._class != this._class);
    return this._bindings.equals(((SimpleType)paramObject)._bindings);
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    int j = this._bindings.size();
    StringBuilder localStringBuilder = paramStringBuilder;
    if (j > 0)
    {
      paramStringBuilder.append('<');
      int i = 0;
      while (i < j)
      {
        paramStringBuilder = containedType(i).getGenericSignature(paramStringBuilder);
        i += 1;
      }
      paramStringBuilder.append('>');
      localStringBuilder = paramStringBuilder;
    }
    localStringBuilder.append(';');
    return localStringBuilder;
  }
  
  public boolean isContainerType()
  {
    return false;
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return null;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(40);
    localStringBuilder.append("[simple type, class ").append(buildCanonicalName()).append(']');
    return localStringBuilder.toString();
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContentType()");
  }
  
  public JavaType withContentTypeHandler(Object paramObject)
  {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
  }
  
  public SimpleType withContentValueHandler(Object paramObject)
  {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
  }
  
  public SimpleType withStaticTyping()
  {
    if (this._asStatic) {
      return this;
    }
    return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, this._typeHandler, true);
  }
  
  public SimpleType withTypeHandler(Object paramObject)
  {
    if (this._typeHandler == paramObject) {
      return this;
    }
    return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, paramObject, this._asStatic);
  }
  
  public SimpleType withValueHandler(Object paramObject)
  {
    if (paramObject == this._valueHandler) {
      return this;
    }
    return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\SimpleType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */