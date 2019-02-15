package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public final class AnnotatedField
  extends AnnotatedMember
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final transient Field _field;
  protected Serialization _serialization;
  
  protected AnnotatedField(Serialization paramSerialization)
  {
    super(null, null);
    this._field = null;
    this._serialization = paramSerialization;
  }
  
  public AnnotatedField(TypeResolutionContext paramTypeResolutionContext, Field paramField, AnnotationMap paramAnnotationMap)
  {
    super(paramTypeResolutionContext, paramAnnotationMap);
    this._field = paramField;
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
    } while (((AnnotatedField)paramObject)._field == this._field);
    return false;
  }
  
  public Field getAnnotated()
  {
    return this._field;
  }
  
  public int getAnnotationCount()
  {
    return this._annotations.size();
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._field.getDeclaringClass();
  }
  
  public String getFullName()
  {
    return getDeclaringClass().getName() + "#" + getName();
  }
  
  public Member getMember()
  {
    return this._field;
  }
  
  public int getModifiers()
  {
    return this._field.getModifiers();
  }
  
  public String getName()
  {
    return this._field.getName();
  }
  
  public Class<?> getRawType()
  {
    return this._field.getType();
  }
  
  public JavaType getType()
  {
    return this._typeContext.resolveType(this._field.getGenericType());
  }
  
  public Object getValue(Object paramObject)
    throws IllegalArgumentException
  {
    try
    {
      paramObject = this._field.get(paramObject);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      throw new IllegalArgumentException("Failed to getValue() for field " + getFullName() + ": " + ((IllegalAccessException)paramObject).getMessage(), (Throwable)paramObject);
    }
  }
  
  public int hashCode()
  {
    return this._field.getName().hashCode();
  }
  
  public boolean isTransient()
  {
    return Modifier.isTransient(getModifiers());
  }
  
  Object readResolve()
  {
    Class localClass = this._serialization.clazz;
    try
    {
      Object localObject = localClass.getDeclaredField(this._serialization.name);
      if (!((Field)localObject).isAccessible()) {
        ClassUtil.checkAndFixAccess((Member)localObject, false);
      }
      localObject = new AnnotatedField(null, (Field)localObject, null);
      return localObject;
    }
    catch (Exception localException)
    {
      throw new IllegalArgumentException("Could not find method '" + this._serialization.name + "' from Class '" + localClass.getName());
    }
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws IllegalArgumentException
  {
    try
    {
      this._field.set(paramObject1, paramObject2);
      return;
    }
    catch (IllegalAccessException paramObject1)
    {
      throw new IllegalArgumentException("Failed to setValue() for field " + getFullName() + ": " + ((IllegalAccessException)paramObject1).getMessage(), (Throwable)paramObject1);
    }
  }
  
  public String toString()
  {
    return "[field " + getFullName() + "]";
  }
  
  public AnnotatedField withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return new AnnotatedField(this._typeContext, this._field, paramAnnotationMap);
  }
  
  Object writeReplace()
  {
    return new AnnotatedField(new Serialization(this._field));
  }
  
  private static final class Serialization
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected Class<?> clazz;
    protected String name;
    
    public Serialization(Field paramField)
    {
      this.clazz = paramField.getDeclaringClass();
      this.name = paramField.getName();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\AnnotatedField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */