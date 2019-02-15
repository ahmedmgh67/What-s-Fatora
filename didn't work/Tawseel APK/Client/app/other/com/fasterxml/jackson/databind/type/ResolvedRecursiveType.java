package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ResolvedRecursiveType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  protected JavaType _referencedType;
  
  public ResolvedRecursiveType(Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    super(paramClass, paramTypeBindings, null, null, 0, null, null, false);
  }
  
  @Deprecated
  protected JavaType _narrow(Class<?> paramClass)
  {
    return this;
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
    return ((ResolvedRecursiveType)paramObject).getSelfReferencedType().equals(getSelfReferencedType());
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return this._referencedType.getErasedSignature(paramStringBuilder);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    return this._referencedType.getGenericSignature(paramStringBuilder);
  }
  
  public JavaType getSelfReferencedType()
  {
    return this._referencedType;
  }
  
  public boolean isContainerType()
  {
    return false;
  }
  
  public JavaType refine(Class<?> paramClass, TypeBindings paramTypeBindings, JavaType paramJavaType, JavaType[] paramArrayOfJavaType)
  {
    return null;
  }
  
  public void setReference(JavaType paramJavaType)
  {
    if (this._referencedType != null) {
      throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + paramJavaType);
    }
    this._referencedType = paramJavaType;
  }
  
  public String toString()
  {
    return 40 + "[resolved recursive type -> " + this._referencedType + ']';
  }
  
  public JavaType withContentType(JavaType paramJavaType)
  {
    return this;
  }
  
  public JavaType withContentTypeHandler(Object paramObject)
  {
    return this;
  }
  
  public JavaType withContentValueHandler(Object paramObject)
  {
    return this;
  }
  
  public JavaType withStaticTyping()
  {
    return this;
  }
  
  public JavaType withTypeHandler(Object paramObject)
  {
    return this;
  }
  
  public JavaType withValueHandler(Object paramObject)
  {
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\ResolvedRecursiveType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */