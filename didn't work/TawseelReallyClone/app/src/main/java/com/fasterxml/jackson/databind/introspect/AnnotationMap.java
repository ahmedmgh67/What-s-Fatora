package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public final class AnnotationMap
  implements Annotations
{
  protected HashMap<Class<?>, Annotation> _annotations;
  
  public AnnotationMap() {}
  
  private AnnotationMap(HashMap<Class<?>, Annotation> paramHashMap)
  {
    this._annotations = paramHashMap;
  }
  
  public static AnnotationMap merge(AnnotationMap paramAnnotationMap1, AnnotationMap paramAnnotationMap2)
  {
    if ((paramAnnotationMap1 == null) || (paramAnnotationMap1._annotations == null) || (paramAnnotationMap1._annotations.isEmpty())) {
      localObject = paramAnnotationMap2;
    }
    do
    {
      do
      {
        do
        {
          return (AnnotationMap)localObject;
          localObject = paramAnnotationMap1;
        } while (paramAnnotationMap2 == null);
        localObject = paramAnnotationMap1;
      } while (paramAnnotationMap2._annotations == null);
      localObject = paramAnnotationMap1;
    } while (paramAnnotationMap2._annotations.isEmpty());
    Object localObject = new HashMap();
    paramAnnotationMap2 = paramAnnotationMap2._annotations.values().iterator();
    while (paramAnnotationMap2.hasNext())
    {
      Annotation localAnnotation = (Annotation)paramAnnotationMap2.next();
      ((HashMap)localObject).put(localAnnotation.annotationType(), localAnnotation);
    }
    paramAnnotationMap1 = paramAnnotationMap1._annotations.values().iterator();
    while (paramAnnotationMap1.hasNext())
    {
      paramAnnotationMap2 = (Annotation)paramAnnotationMap1.next();
      ((HashMap)localObject).put(paramAnnotationMap2.annotationType(), paramAnnotationMap2);
    }
    return new AnnotationMap((HashMap)localObject);
  }
  
  protected final boolean _add(Annotation paramAnnotation)
  {
    if (this._annotations == null) {
      this._annotations = new HashMap();
    }
    Annotation localAnnotation = (Annotation)this._annotations.put(paramAnnotation.annotationType(), paramAnnotation);
    return (localAnnotation == null) || (!localAnnotation.equals(paramAnnotation));
  }
  
  public boolean add(Annotation paramAnnotation)
  {
    return _add(paramAnnotation);
  }
  
  public boolean addIfNotPresent(Annotation paramAnnotation)
  {
    if ((this._annotations == null) || (!this._annotations.containsKey(paramAnnotation.annotationType())))
    {
      _add(paramAnnotation);
      return true;
    }
    return false;
  }
  
  public Iterable<Annotation> annotations()
  {
    if ((this._annotations == null) || (this._annotations.size() == 0)) {
      return Collections.emptyList();
    }
    return this._annotations.values();
  }
  
  public <A extends Annotation> A get(Class<A> paramClass)
  {
    if (this._annotations == null) {
      return null;
    }
    return (Annotation)this._annotations.get(paramClass);
  }
  
  public boolean has(Class<?> paramClass)
  {
    if (this._annotations == null) {
      return false;
    }
    return this._annotations.containsKey(paramClass);
  }
  
  public boolean hasOneOf(Class<? extends Annotation>[] paramArrayOfClass)
  {
    if (this._annotations != null)
    {
      int i = 0;
      int j = paramArrayOfClass.length;
      while (i < j)
      {
        if (this._annotations.containsKey(paramArrayOfClass[i])) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public int size()
  {
    if (this._annotations == null) {
      return 0;
    }
    return this._annotations.size();
  }
  
  public String toString()
  {
    if (this._annotations == null) {
      return "[null]";
    }
    return this._annotations.toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\AnnotationMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */