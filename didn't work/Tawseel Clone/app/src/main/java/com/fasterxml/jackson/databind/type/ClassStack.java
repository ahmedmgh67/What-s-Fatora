package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.ArrayList;
import java.util.Iterator;

public final class ClassStack
{
  protected final Class<?> _current;
  protected final ClassStack _parent;
  private ArrayList<ResolvedRecursiveType> _selfRefs;
  
  private ClassStack(ClassStack paramClassStack, Class<?> paramClass)
  {
    this._parent = paramClassStack;
    this._current = paramClass;
  }
  
  public ClassStack(Class<?> paramClass)
  {
    this(null, paramClass);
  }
  
  public void addSelfReference(ResolvedRecursiveType paramResolvedRecursiveType)
  {
    if (this._selfRefs == null) {
      this._selfRefs = new ArrayList();
    }
    this._selfRefs.add(paramResolvedRecursiveType);
  }
  
  public ClassStack child(Class<?> paramClass)
  {
    return new ClassStack(this, paramClass);
  }
  
  public ClassStack find(Class<?> paramClass)
  {
    if (this._current == paramClass) {
      return this;
    }
    for (ClassStack localClassStack = this._parent; localClassStack != null; localClassStack = localClassStack._parent) {
      if (localClassStack._current == paramClass) {
        return localClassStack;
      }
    }
    return null;
  }
  
  public void resolveSelfReferences(JavaType paramJavaType)
  {
    if (this._selfRefs != null)
    {
      Iterator localIterator = this._selfRefs.iterator();
      while (localIterator.hasNext()) {
        ((ResolvedRecursiveType)localIterator.next()).setReference(paramJavaType);
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = localStringBuilder1.append("[ClassStack (self-refs: ");
    if (this._selfRefs == null) {}
    for (Object localObject = "0";; localObject = String.valueOf(this._selfRefs.size()))
    {
      localStringBuilder2.append((String)localObject).append(')');
      for (localObject = this; localObject != null; localObject = ((ClassStack)localObject)._parent) {
        localStringBuilder1.append(' ').append(((ClassStack)localObject)._current.getName());
      }
    }
    localStringBuilder1.append(']');
    return localStringBuilder1.toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\ClassStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */