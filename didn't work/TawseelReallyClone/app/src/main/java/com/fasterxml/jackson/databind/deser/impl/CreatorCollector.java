package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreatorCollector
{
  protected static final int C_ARRAY_DELEGATE = 8;
  protected static final int C_BOOLEAN = 5;
  protected static final int C_DEFAULT = 0;
  protected static final int C_DELEGATE = 6;
  protected static final int C_DOUBLE = 4;
  protected static final int C_INT = 2;
  protected static final int C_LONG = 3;
  protected static final int C_PROPS = 7;
  protected static final int C_STRING = 1;
  protected static final String[] TYPE_DESCS = { "default", "String", "int", "long", "double", "boolean", "delegate", "property-based" };
  protected SettableBeanProperty[] _arrayDelegateArgs;
  protected final BeanDescription _beanDesc;
  protected final boolean _canFixAccess;
  protected final AnnotatedWithParams[] _creators = new AnnotatedWithParams[9];
  protected SettableBeanProperty[] _delegateArgs;
  protected int _explicitCreators = 0;
  protected final boolean _forceAccess;
  protected boolean _hasNonDefaultCreator = false;
  protected AnnotatedParameter _incompleteParameter;
  protected SettableBeanProperty[] _propertyBasedArgs;
  
  public CreatorCollector(BeanDescription paramBeanDescription, MapperConfig<?> paramMapperConfig)
  {
    this._beanDesc = paramBeanDescription;
    this._canFixAccess = paramMapperConfig.canOverrideAccessModifiers();
    this._forceAccess = paramMapperConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
  }
  
  private JavaType _computeDelegateType(AnnotatedWithParams paramAnnotatedWithParams, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    if ((!this._hasNonDefaultCreator) || (paramAnnotatedWithParams == null)) {
      return null;
    }
    int k = 0;
    int j = k;
    int i;
    int m;
    if (paramArrayOfSettableBeanProperty != null)
    {
      i = 0;
      m = paramArrayOfSettableBeanProperty.length;
    }
    for (;;)
    {
      j = k;
      if (i < m)
      {
        if (paramArrayOfSettableBeanProperty[i] == null) {
          j = i;
        }
      }
      else {
        return paramAnnotatedWithParams.getParameterType(j);
      }
      i += 1;
    }
  }
  
  private <T extends AnnotatedMember> T _fixAccess(T paramT)
  {
    if ((paramT != null) && (this._canFixAccess)) {
      ClassUtil.checkAndFixAccess((Member)paramT.getAnnotated(), this._forceAccess);
    }
    return paramT;
  }
  
  @Deprecated
  public void addBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 5, paramBoolean);
  }
  
  public void addDelegatingCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    if (paramAnnotatedWithParams.getParameterType(0).isCollectionLikeType())
    {
      verifyNonDup(paramAnnotatedWithParams, 8, paramBoolean);
      this._arrayDelegateArgs = paramArrayOfSettableBeanProperty;
      return;
    }
    verifyNonDup(paramAnnotatedWithParams, 6, paramBoolean);
    this._delegateArgs = paramArrayOfSettableBeanProperty;
  }
  
  @Deprecated
  public void addDelegatingCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty)
  {
    addDelegatingCreator(paramAnnotatedWithParams, false, paramArrayOfCreatorProperty);
  }
  
  @Deprecated
  public void addDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 4, paramBoolean);
  }
  
  public void addIncompeteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    if (this._incompleteParameter == null) {
      this._incompleteParameter = paramAnnotatedParameter;
    }
  }
  
  @Deprecated
  public void addIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addIntCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 2, paramBoolean);
  }
  
  @Deprecated
  public void addLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addLongCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 3, paramBoolean);
  }
  
  public void addPropertyCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    verifyNonDup(paramAnnotatedWithParams, 7, paramBoolean);
    if (paramArrayOfSettableBeanProperty.length > 1)
    {
      paramAnnotatedWithParams = new HashMap();
      int i = 0;
      int j = paramArrayOfSettableBeanProperty.length;
      if (i < j)
      {
        String str = paramArrayOfSettableBeanProperty[i].getName();
        if ((str.length() == 0) && (paramArrayOfSettableBeanProperty[i].getInjectableValueId() != null)) {}
        Integer localInteger;
        do
        {
          i += 1;
          break;
          localInteger = (Integer)paramAnnotatedWithParams.put(str, Integer.valueOf(i));
        } while (localInteger == null);
        throw new IllegalArgumentException("Duplicate creator property \"" + str + "\" (index " + localInteger + " vs " + i + ")");
      }
    }
    this._propertyBasedArgs = paramArrayOfSettableBeanProperty;
  }
  
  @Deprecated
  public void addPropertyCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty)
  {
    addPropertyCreator(paramAnnotatedWithParams, false, paramArrayOfCreatorProperty);
  }
  
  @Deprecated
  public void addStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addStringCreator(paramAnnotatedWithParams, false);
  }
  
  public void addStringCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 1, paramBoolean);
  }
  
  public ValueInstantiator constructValueInstantiator(DeserializationConfig paramDeserializationConfig)
  {
    JavaType localJavaType1 = _computeDelegateType(this._creators[6], this._delegateArgs);
    JavaType localJavaType2 = _computeDelegateType(this._creators[8], this._arrayDelegateArgs);
    JavaType localJavaType3 = this._beanDesc.getType();
    if (!this._hasNonDefaultCreator)
    {
      Class localClass = localJavaType3.getRawClass();
      if ((localClass == Collection.class) || (localClass == List.class) || (localClass == ArrayList.class)) {
        return new Vanilla(1);
      }
      if ((localClass == Map.class) || (localClass == LinkedHashMap.class)) {
        return new Vanilla(2);
      }
      if (localClass == HashMap.class) {
        return new Vanilla(3);
      }
    }
    paramDeserializationConfig = new StdValueInstantiator(paramDeserializationConfig, localJavaType3);
    paramDeserializationConfig.configureFromObjectSettings(this._creators[0], this._creators[6], localJavaType1, this._delegateArgs, this._creators[7], this._propertyBasedArgs);
    paramDeserializationConfig.configureFromArraySettings(this._creators[8], localJavaType2, this._arrayDelegateArgs);
    paramDeserializationConfig.configureFromStringCreator(this._creators[1]);
    paramDeserializationConfig.configureFromIntCreator(this._creators[2]);
    paramDeserializationConfig.configureFromLongCreator(this._creators[3]);
    paramDeserializationConfig.configureFromDoubleCreator(this._creators[4]);
    paramDeserializationConfig.configureFromBooleanCreator(this._creators[5]);
    paramDeserializationConfig.configureIncompleteParameter(this._incompleteParameter);
    return paramDeserializationConfig;
  }
  
  public boolean hasDefaultCreator()
  {
    boolean bool = false;
    if (this._creators[0] != null) {
      bool = true;
    }
    return bool;
  }
  
  public boolean hasDelegatingCreator()
  {
    return this._creators[6] != null;
  }
  
  public boolean hasPropertyBasedCreator()
  {
    return this._creators[7] != null;
  }
  
  public void setDefaultCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._creators[0] = ((AnnotatedWithParams)_fixAccess(paramAnnotatedWithParams));
  }
  
  protected void verifyNonDup(AnnotatedWithParams paramAnnotatedWithParams, int paramInt, boolean paramBoolean)
  {
    int i = 1;
    int j = 1 << paramInt;
    this._hasNonDefaultCreator = true;
    AnnotatedWithParams localAnnotatedWithParams = this._creators[paramInt];
    if (localAnnotatedWithParams != null)
    {
      if ((this._explicitCreators & j) != 0) {
        if (paramBoolean) {}
      }
      Class localClass1;
      Class localClass2;
      do
      {
        return;
        i = 1;
        do
        {
          if ((i == 0) || (localAnnotatedWithParams.getClass() != paramAnnotatedWithParams.getClass())) {
            break label159;
          }
          localClass1 = localAnnotatedWithParams.getRawParameterType(0);
          localClass2 = paramAnnotatedWithParams.getRawParameterType(0);
          if (localClass1 != localClass2) {
            break;
          }
          throw new IllegalArgumentException("Conflicting " + TYPE_DESCS[paramInt] + " creators: already had explicitly marked " + localAnnotatedWithParams + ", encountered " + paramAnnotatedWithParams);
        } while (!paramBoolean);
        for (;;)
        {
          i = 0;
        }
      } while (localClass2.isAssignableFrom(localClass1));
    }
    label159:
    if (paramBoolean) {
      this._explicitCreators |= j;
    }
    this._creators[paramInt] = ((AnnotatedWithParams)_fixAccess(paramAnnotatedWithParams));
  }
  
  protected static final class Vanilla
    extends ValueInstantiator
    implements Serializable
  {
    public static final int TYPE_COLLECTION = 1;
    public static final int TYPE_HASH_MAP = 3;
    public static final int TYPE_MAP = 2;
    private static final long serialVersionUID = 1L;
    private final int _type;
    
    public Vanilla(int paramInt)
    {
      this._type = paramInt;
    }
    
    public boolean canCreateUsingDefault()
    {
      return true;
    }
    
    public boolean canInstantiate()
    {
      return true;
    }
    
    public Object createUsingDefault(DeserializationContext paramDeserializationContext)
      throws IOException
    {
      switch (this._type)
      {
      default: 
        throw new IllegalStateException("Unknown type " + this._type);
      case 1: 
        return new ArrayList();
      case 2: 
        return new LinkedHashMap();
      }
      return new HashMap();
    }
    
    public String getValueTypeDesc()
    {
      switch (this._type)
      {
      default: 
        return Object.class.getName();
      case 1: 
        return ArrayList.class.getName();
      case 2: 
        return LinkedHashMap.class.getName();
      }
      return HashMap.class.getName();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\impl\CreatorCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */