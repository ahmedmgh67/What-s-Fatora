package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedParameter
  extends AnnotatedMember
{
  private static final long serialVersionUID = 1L;
  protected final int _index;
  protected final AnnotatedWithParams _owner;
  protected final JavaType _type;
  
  public AnnotatedParameter(AnnotatedWithParams paramAnnotatedWithParams, JavaType paramJavaType, AnnotationMap paramAnnotationMap, int paramInt) {}
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if ((paramObject == null) || (paramObject.getClass() != getClass())) {
        return false;
      }
      paramObject = (AnnotatedParameter)paramObject;
    } while ((((AnnotatedParameter)paramObject)._owner.equals(this._owner)) && (((AnnotatedParameter)paramObject)._index == this._index));
    return false;
  }
  
  public AnnotatedElement getAnnotated()
  {
    return null;
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._owner.getDeclaringClass();
  }
  
  public int getIndex()
  {
    return this._index;
  }
  
  public Member getMember()
  {
    return this._owner.getMember();
  }
  
  public int getModifiers()
  {
    return this._owner.getModifiers();
  }
  
  public String getName()
  {
    return "";
  }
  
  public AnnotatedWithParams getOwner()
  {
    return this._owner;
  }
  
  public Type getParameterType()
  {
    return this._type;
  }
  
  public Class<?> getRawType()
  {
    return this._type.getRawClass();
  }
  
  public JavaType getType()
  {
    return this._typeContext.resolveType(this._type);
  }
  
  public Object getValue(Object paramObject)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Cannot call getValue() on constructor parameter of " + getDeclaringClass().getName());
  }
  
  public int hashCode()
  {
    return this._owner.hashCode() + this._index;
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Cannot call setValue() on constructor parameter of " + getDeclaringClass().getName());
  }
  
  public String toString()
  {
    return "[parameter #" + getIndex() + ", annotations: " + this._annotations + "]";
  }
  
  public AnnotatedParameter withAnnotations(AnnotationMap paramAnnotationMap)
  {
    if (paramAnnotationMap == this._annotations) {
      return this;
    }
    return this._owner.replaceParameterAnnotations(this._index, paramAnnotationMap);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\AnnotatedParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */