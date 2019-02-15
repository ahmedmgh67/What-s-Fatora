package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class VirtualAnnotatedMember
  extends AnnotatedMember
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _declaringClass;
  protected final String _name;
  protected final Class<?> _rawType;
  
  public VirtualAnnotatedMember(TypeResolutionContext paramTypeResolutionContext, Class<?> paramClass1, String paramString, Class<?> paramClass2)
  {
    super(paramTypeResolutionContext, null);
    this._declaringClass = paramClass1;
    this._rawType = paramClass2;
    this._name = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if ((paramObject == null) || (paramObject.getClass() != getClass())) {
        return false;
      }
      paramObject = (VirtualAnnotatedMember)paramObject;
    } while ((((VirtualAnnotatedMember)paramObject)._declaringClass == this._declaringClass) && (((VirtualAnnotatedMember)paramObject)._name.equals(this._name)));
    return false;
  }
  
  public Field getAnnotated()
  {
    return null;
  }
  
  public int getAnnotationCount()
  {
    return 0;
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._declaringClass;
  }
  
  public String getFullName()
  {
    return getDeclaringClass().getName() + "#" + getName();
  }
  
  public Member getMember()
  {
    return null;
  }
  
  public int getModifiers()
  {
    return 0;
  }
  
  public String getName()
  {
    return this._name;
  }
  
  public Class<?> getRawType()
  {
    return this._rawType;
  }
  
  public JavaType getType()
  {
    return this._typeContext.resolveType(this._rawType);
  }
  
  public Object getValue(Object paramObject)
    throws IllegalArgumentException
  {
    throw new IllegalArgumentException("Can not get virtual property '" + this._name + "'");
  }
  
  public int hashCode()
  {
    return this._name.hashCode();
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws IllegalArgumentException
  {
    throw new IllegalArgumentException("Can not set virtual property '" + this._name + "'");
  }
  
  public String toString()
  {
    return "[field " + getFullName() + "]";
  }
  
  public Annotated withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\VirtualAnnotatedMember.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */