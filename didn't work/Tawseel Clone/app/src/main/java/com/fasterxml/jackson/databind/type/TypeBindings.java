package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TypeBindings
  implements Serializable
{
  private static final TypeBindings EMPTY = new TypeBindings(NO_STRINGS, NO_TYPES, null);
  private static final String[] NO_STRINGS = new String[0];
  private static final JavaType[] NO_TYPES = new JavaType[0];
  private static final long serialVersionUID = 1L;
  private final int _hashCode;
  private final String[] _names;
  private final JavaType[] _types;
  private final String[] _unboundVariables;
  
  private TypeBindings(String[] paramArrayOfString1, JavaType[] paramArrayOfJavaType, String[] paramArrayOfString2)
  {
    String[] arrayOfString = paramArrayOfString1;
    if (paramArrayOfString1 == null) {
      arrayOfString = NO_STRINGS;
    }
    this._names = arrayOfString;
    paramArrayOfString1 = paramArrayOfJavaType;
    if (paramArrayOfJavaType == null) {
      paramArrayOfString1 = NO_TYPES;
    }
    this._types = paramArrayOfString1;
    if (this._names.length != this._types.length) {
      throw new IllegalArgumentException("Mismatching names (" + this._names.length + "), types (" + this._types.length + ")");
    }
    int j = 1;
    int i = 0;
    int k = this._types.length;
    while (i < k)
    {
      j += this._types[i].hashCode();
      i += 1;
    }
    this._unboundVariables = paramArrayOfString2;
    this._hashCode = j;
  }
  
  public static TypeBindings create(Class<?> paramClass, JavaType paramJavaType)
  {
    TypeVariable[] arrayOfTypeVariable = TypeParamStash.paramsFor1(paramClass);
    if (arrayOfTypeVariable == null) {}
    for (int i = 0; i != 1; i = arrayOfTypeVariable.length) {
      throw new IllegalArgumentException("Can not create TypeBindings for class " + paramClass.getName() + " with 1 type parameter: class expects " + i);
    }
    return new TypeBindings(new String[] { arrayOfTypeVariable[0].getName() }, new JavaType[] { paramJavaType }, null);
  }
  
  public static TypeBindings create(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    TypeVariable[] arrayOfTypeVariable = TypeParamStash.paramsFor2(paramClass);
    if (arrayOfTypeVariable == null) {}
    for (int i = 0; i != 2; i = arrayOfTypeVariable.length) {
      throw new IllegalArgumentException("Can not create TypeBindings for class " + paramClass.getName() + " with 2 type parameters: class expects " + i);
    }
    return new TypeBindings(new String[] { arrayOfTypeVariable[0].getName(), arrayOfTypeVariable[1].getName() }, new JavaType[] { paramJavaType1, paramJavaType2 }, null);
  }
  
  public static TypeBindings create(Class<?> paramClass, List<JavaType> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {}
    for (paramList = NO_TYPES;; paramList = (JavaType[])paramList.toArray(new JavaType[paramList.size()])) {
      return create(paramClass, paramList);
    }
  }
  
  public static TypeBindings create(Class<?> paramClass, JavaType[] paramArrayOfJavaType)
  {
    JavaType[] arrayOfJavaType;
    TypeVariable[] arrayOfTypeVariable;
    Object localObject;
    if (paramArrayOfJavaType == null)
    {
      arrayOfJavaType = NO_TYPES;
      arrayOfTypeVariable = paramClass.getTypeParameters();
      if ((arrayOfTypeVariable != null) && (arrayOfTypeVariable.length != 0)) {
        break label161;
      }
      paramArrayOfJavaType = NO_STRINGS;
      if (paramArrayOfJavaType.length == arrayOfJavaType.length) {
        break label207;
      }
      localObject = new StringBuilder().append("Can not create TypeBindings for class ").append(paramClass.getName()).append(" with ").append(arrayOfJavaType.length).append(" type parameter");
      if (arrayOfJavaType.length != 1) {
        break label201;
      }
    }
    label161:
    label201:
    for (paramClass = "";; paramClass = "s")
    {
      throw new IllegalArgumentException(paramClass + ": class expects " + paramArrayOfJavaType.length);
      switch (paramArrayOfJavaType.length)
      {
      default: 
        arrayOfJavaType = paramArrayOfJavaType;
        break;
      case 1: 
        return create(paramClass, paramArrayOfJavaType[0]);
      case 2: 
        return create(paramClass, paramArrayOfJavaType[0], paramArrayOfJavaType[1]);
        int j = arrayOfTypeVariable.length;
        localObject = new String[j];
        int i = 0;
        for (;;)
        {
          paramArrayOfJavaType = (JavaType[])localObject;
          if (i >= j) {
            break;
          }
          localObject[i] = arrayOfTypeVariable[i].getName();
          i += 1;
        }
      }
    }
    label207:
    return new TypeBindings(paramArrayOfJavaType, arrayOfJavaType, null);
  }
  
  public static TypeBindings createIfNeeded(Class<?> paramClass, JavaType paramJavaType)
  {
    TypeVariable[] arrayOfTypeVariable = paramClass.getTypeParameters();
    if (arrayOfTypeVariable == null) {}
    for (int i = 0; i == 0; i = arrayOfTypeVariable.length) {
      return EMPTY;
    }
    if (i != 1) {
      throw new IllegalArgumentException("Can not create TypeBindings for class " + paramClass.getName() + " with 1 type parameter: class expects " + i);
    }
    return new TypeBindings(new String[] { arrayOfTypeVariable[0].getName() }, new JavaType[] { paramJavaType }, null);
  }
  
  public static TypeBindings createIfNeeded(Class<?> paramClass, JavaType[] paramArrayOfJavaType)
  {
    Object localObject = paramClass.getTypeParameters();
    if ((localObject == null) || (localObject.length == 0)) {
      return EMPTY;
    }
    JavaType[] arrayOfJavaType = paramArrayOfJavaType;
    if (paramArrayOfJavaType == null) {
      arrayOfJavaType = NO_TYPES;
    }
    int j = localObject.length;
    paramArrayOfJavaType = new String[j];
    int i = 0;
    while (i < j)
    {
      paramArrayOfJavaType[i] = localObject[i].getName();
      i += 1;
    }
    if (paramArrayOfJavaType.length != arrayOfJavaType.length)
    {
      localObject = new StringBuilder().append("Can not create TypeBindings for class ").append(paramClass.getName()).append(" with ").append(arrayOfJavaType.length).append(" type parameter");
      if (arrayOfJavaType.length == 1) {}
      for (paramClass = "";; paramClass = "s") {
        throw new IllegalArgumentException(paramClass + ": class expects " + paramArrayOfJavaType.length);
      }
    }
    return new TypeBindings(paramArrayOfJavaType, arrayOfJavaType, null);
  }
  
  public static TypeBindings emptyBindings()
  {
    return EMPTY;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    for (;;)
    {
      return true;
      if ((paramObject == null) || (paramObject.getClass() != getClass())) {
        return false;
      }
      paramObject = (TypeBindings)paramObject;
      int j = this._types.length;
      if (j != ((TypeBindings)paramObject).size()) {
        return false;
      }
      paramObject = ((TypeBindings)paramObject)._types;
      int i = 0;
      while (i < j)
      {
        if (!paramObject[i].equals(this._types[i])) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  public JavaType findBoundType(String paramString)
  {
    int i = 0;
    int j = this._names.length;
    while (i < j)
    {
      if (paramString.equals(this._names[i]))
      {
        JavaType localJavaType1 = this._types[i];
        paramString = localJavaType1;
        if ((localJavaType1 instanceof ResolvedRecursiveType))
        {
          JavaType localJavaType2 = ((ResolvedRecursiveType)localJavaType1).getSelfReferencedType();
          paramString = localJavaType1;
          if (localJavaType2 != null) {
            paramString = localJavaType2;
          }
        }
        return paramString;
      }
      i += 1;
    }
    return null;
  }
  
  public String getBoundName(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this._names.length)) {
      return null;
    }
    return this._names[paramInt];
  }
  
  public JavaType getBoundType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this._types.length)) {
      return null;
    }
    return this._types[paramInt];
  }
  
  public List<JavaType> getTypeParameters()
  {
    if (this._types.length == 0) {
      return Collections.emptyList();
    }
    return Arrays.asList(this._types);
  }
  
  public boolean hasUnbound(String paramString)
  {
    if (this._unboundVariables != null)
    {
      int i = this._unboundVariables.length;
      int j;
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        i = j;
      } while (!paramString.equals(this._unboundVariables[j]));
      return true;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this._hashCode;
  }
  
  public boolean isEmpty()
  {
    return this._types.length == 0;
  }
  
  protected Object readResolve()
  {
    TypeBindings localTypeBindings;
    if (this._names != null)
    {
      localTypeBindings = this;
      if (this._names.length != 0) {}
    }
    else
    {
      localTypeBindings = EMPTY;
    }
    return localTypeBindings;
  }
  
  public int size()
  {
    return this._types.length;
  }
  
  public String toString()
  {
    if (this._types.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('<');
    int i = 0;
    int j = this._types.length;
    while (i < j)
    {
      if (i > 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(this._types[i].getGenericSignature());
      i += 1;
    }
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
  
  protected JavaType[] typeParameterArray()
  {
    return this._types;
  }
  
  public TypeBindings withUnboundVariable(String paramString)
  {
    int i;
    if (this._unboundVariables == null)
    {
      i = 0;
      if (i != 0) {
        break label48;
      }
    }
    label48:
    for (String[] arrayOfString = new String[1];; arrayOfString = (String[])Arrays.copyOf(this._unboundVariables, i + 1))
    {
      arrayOfString[i] = paramString;
      return new TypeBindings(this._names, this._types, arrayOfString);
      i = this._unboundVariables.length;
      break;
    }
  }
  
  static class TypeParamStash
  {
    private static final TypeVariable<?>[] VARS_ABSTRACT_LIST = AbstractList.class.getTypeParameters();
    private static final TypeVariable<?>[] VARS_ARRAY_LIST;
    private static final TypeVariable<?>[] VARS_COLLECTION = Collection.class.getTypeParameters();
    private static final TypeVariable<?>[] VARS_HASH_MAP = HashMap.class.getTypeParameters();
    private static final TypeVariable<?>[] VARS_ITERABLE = Iterable.class.getTypeParameters();
    private static final TypeVariable<?>[] VARS_LINKED_HASH_MAP = LinkedHashMap.class.getTypeParameters();
    private static final TypeVariable<?>[] VARS_LIST = List.class.getTypeParameters();
    private static final TypeVariable<?>[] VARS_MAP;
    
    static
    {
      VARS_ARRAY_LIST = ArrayList.class.getTypeParameters();
      VARS_MAP = Map.class.getTypeParameters();
    }
    
    public static TypeVariable<?>[] paramsFor1(Class<?> paramClass)
    {
      if (paramClass == Collection.class) {
        return VARS_COLLECTION;
      }
      if (paramClass == List.class) {
        return VARS_LIST;
      }
      if (paramClass == ArrayList.class) {
        return VARS_ARRAY_LIST;
      }
      if (paramClass == AbstractList.class) {
        return VARS_ABSTRACT_LIST;
      }
      if (paramClass == Iterable.class) {
        return VARS_ITERABLE;
      }
      return paramClass.getTypeParameters();
    }
    
    public static TypeVariable<?>[] paramsFor2(Class<?> paramClass)
    {
      if (paramClass == Map.class) {
        return VARS_MAP;
      }
      if (paramClass == HashMap.class) {
        return VARS_HASH_MAP;
      }
      if (paramClass == LinkedHashMap.class) {
        return VARS_LINKED_HASH_MAP;
      }
      return paramClass.getTypeParameters();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\type\TypeBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */