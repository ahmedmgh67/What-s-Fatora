package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ReferenceType
  extends SimpleType
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _referencedType;
  
  protected ReferenceType(TypeBase paramTypeBase, JavaType paramJavaType)
  {
    super(paramTypeBase);
    this._referencedType = paramJavaType;
  }
  
  protected ReferenceType(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2.hashCode(), paramObject1, paramObject2, paramBoolean);
    this._referencedType = paramJavaType2;
  }
  
  @Deprecated
  public static ReferenceType construct(Class<?> paramClass, JavaType paramJavaType)
  {
    return new ReferenceType(paramClass, TypeBindings.emptyBindings(), null, null, paramJavaType, null, null, false);
  }
  
  public static ReferenceType construct(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType1, JavaType[] paramArrayOfJavaType, JavaType paramJavaType2)
  {
    return new ReferenceType(paramClass, paramTypeBindings, paramJavaType1, paramArrayOfJavaType, paramJavaType2, null, null, false);
  }
  
  public static ReferenceType upgradeFrom(JavaType paramJavaType1, JavaType paramJavaType2)
  {
    if (paramJavaType2 == null) {
      throw new IllegalArgumentException("Missing referencedType");
    }
    if ((paramJavaType1 instanceof TypeBase)) {
      return new ReferenceType((TypeBase)paramJavaType1, paramJavaType2);
    }
    throw new IllegalArgumentException("Can not upgrade from an instance of " + paramJavaType1.getClass());
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new ReferenceType(paramClass, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    localStringBuilder.append('<');
    localStringBuilder.append(this._referencedType.toCanonical());
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
      paramObject = (ReferenceType)paramObject;
      bool1 = bool2;
    } while (((ReferenceType)paramObject)._class != this._class);
    return this._referencedType.equals(((ReferenceType)paramObject)._referencedType);
  }
  
  public JavaType getContentType()
  {
    return this._referencedType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    paramStringBuilder = this._referencedType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(';');
    return paramStringBuilder;
  }
  
  public JavaType getReferencedType()
  {
    return this._referencedType;
  }
  
  public boolean isReferenceType()
  {
    return true;
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return new ReferenceType(paramClass, this._bindings, paramJavaType, paramArrayOfJavaType, this._referencedType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public String toString()
  {
    return 40 + "[reference type, class " + buildCanonicalName() + '<' + this._referencedType + '>' + ']';
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    if (this._referencedType == paramJavaType) {
      return this;
    }
    return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, paramJavaType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ReferenceType withContentTypeHandler(Object paramObject)
  {
    if (paramObject == this._referencedType.getTypeHandler()) {
      return this;
    }
    return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ReferenceType withContentValueHandler(Object paramObject)
  {
    if (paramObject == this._referencedType.getValueHandler()) {
      return this;
    }
    return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public ReferenceType withStaticTyping()
  {
    if (this._asStatic) {
      return this;
    }
    return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
  }
  
  public ReferenceType withTypeHandler(Object paramObject)
  {
    if (paramObject == this._typeHandler) {
      return this;
    }
    return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public ReferenceType withValueHandler(Object paramObject)
  {
    if (paramObject == this._valueHandler) {
      return this;
    }
    return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\ReferenceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */