package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public abstract class Annotated
{
  public abstract Iterable<Annotation> annotations();
  
  public abstract boolean equals(Object paramObject);
  
  protected abstract AnnotationMap getAllAnnotations();
  
  public abstract AnnotatedElement getAnnotated();
  
  public abstract <A extends Annotation> A getAnnotation(Class<A> paramClass);
  
  @Deprecated
  public Type getGenericType()
  {
    return getRawType();
  }
  
  protected abstract int getModifiers();
  
  public abstract String getName();
  
  public abstract Class<?> getRawType();
  
  public abstract JavaType getType();
  
  @Deprecated
  public final JavaType getType(TypeBindings paramTypeBindings)
  {
    return getType();
  }
  
  public abstract boolean hasAnnotation(Class<?> paramClass);
  
  public abstract boolean hasOneOf(Class<? extends Annotation>[] paramArrayOfClass);
  
  public abstract int hashCode();
  
  public final boolean isPublic()
  {
    return Modifier.isPublic(getModifiers());
  }
  
  public abstract String toString();
  
  public abstract Annotated withAnnotations(AnnotationMap paramAnnotationMap);
  
  public final Annotated withFallBackAnnotationsFrom(Annotated paramAnnotated)
  {
    return withAnnotations(AnnotationMap.merge(getAllAnnotations(), paramAnnotated.getAllAnnotations()));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\Annotated.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */