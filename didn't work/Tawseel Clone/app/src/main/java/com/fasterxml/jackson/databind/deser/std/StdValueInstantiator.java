package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@JacksonStdImpl
public class StdValueInstantiator
  extends ValueInstantiator
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected SettableBeanProperty[] _arrayDelegateArguments;
  protected AnnotatedWithParams _arrayDelegateCreator;
  protected JavaType _arrayDelegateType;
  protected SettableBeanProperty[] _constructorArguments;
  protected AnnotatedWithParams _defaultCreator;
  protected SettableBeanProperty[] _delegateArguments;
  protected AnnotatedWithParams _delegateCreator;
  protected JavaType _delegateType;
  protected AnnotatedWithParams _fromBooleanCreator;
  protected AnnotatedWithParams _fromDoubleCreator;
  protected AnnotatedWithParams _fromIntCreator;
  protected AnnotatedWithParams _fromLongCreator;
  protected AnnotatedWithParams _fromStringCreator;
  protected AnnotatedParameter _incompleteParameter;
  protected final String _valueTypeDesc;
  protected AnnotatedWithParams _withArgsCreator;
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (paramDeserializationConfig = "UNKNOWN TYPE";; paramDeserializationConfig = paramJavaType.toString())
    {
      this._valueTypeDesc = paramDeserializationConfig;
      return;
    }
  }
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
  {
    if (paramClass == null) {}
    for (paramDeserializationConfig = "UNKNOWN TYPE";; paramDeserializationConfig = paramClass.getName())
    {
      this._valueTypeDesc = paramDeserializationConfig;
      return;
    }
  }
  
  protected StdValueInstantiator(StdValueInstantiator paramStdValueInstantiator)
  {
    this._valueTypeDesc = paramStdValueInstantiator._valueTypeDesc;
    this._defaultCreator = paramStdValueInstantiator._defaultCreator;
    this._constructorArguments = paramStdValueInstantiator._constructorArguments;
    this._withArgsCreator = paramStdValueInstantiator._withArgsCreator;
    this._delegateType = paramStdValueInstantiator._delegateType;
    this._delegateCreator = paramStdValueInstantiator._delegateCreator;
    this._delegateArguments = paramStdValueInstantiator._delegateArguments;
    this._arrayDelegateType = paramStdValueInstantiator._arrayDelegateType;
    this._arrayDelegateCreator = paramStdValueInstantiator._arrayDelegateCreator;
    this._arrayDelegateArguments = paramStdValueInstantiator._arrayDelegateArguments;
    this._fromStringCreator = paramStdValueInstantiator._fromStringCreator;
    this._fromIntCreator = paramStdValueInstantiator._fromIntCreator;
    this._fromLongCreator = paramStdValueInstantiator._fromLongCreator;
    this._fromDoubleCreator = paramStdValueInstantiator._fromDoubleCreator;
    this._fromBooleanCreator = paramStdValueInstantiator._fromBooleanCreator;
  }
  
  private Object _createUsingDelegate(AnnotatedWithParams paramAnnotatedWithParams, SettableBeanProperty[] paramArrayOfSettableBeanProperty, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (paramAnnotatedWithParams == null) {
      throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc());
    }
    if (paramArrayOfSettableBeanProperty == null) {}
    try
    {
      return paramAnnotatedWithParams.call1(paramObject);
    }
    catch (Throwable paramAnnotatedWithParams)
    {
      throw rewrapCtorProblem(paramDeserializationContext, paramAnnotatedWithParams);
    }
    int j = paramArrayOfSettableBeanProperty.length;
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    break label99;
    Object localObject;
    arrayOfObject[i] = paramDeserializationContext.findInjectableValue(((SettableBeanProperty)localObject).getInjectableValueId(), (BeanProperty)localObject, null);
    label99:
    label131:
    for (;;)
    {
      paramAnnotatedWithParams = paramAnnotatedWithParams.call(arrayOfObject);
      return paramAnnotatedWithParams;
      for (;;)
      {
        if (i >= j) {
          break label131;
        }
        localObject = paramArrayOfSettableBeanProperty[i];
        if (localObject != null) {
          break;
        }
        arrayOfObject[i] = paramObject;
        i += 1;
      }
    }
  }
  
  public boolean canCreateFromBoolean()
  {
    return this._fromBooleanCreator != null;
  }
  
  public boolean canCreateFromDouble()
  {
    return this._fromDoubleCreator != null;
  }
  
  public boolean canCreateFromInt()
  {
    return this._fromIntCreator != null;
  }
  
  public boolean canCreateFromLong()
  {
    return this._fromLongCreator != null;
  }
  
  public boolean canCreateFromObjectWith()
  {
    return this._withArgsCreator != null;
  }
  
  public boolean canCreateFromString()
  {
    return this._fromStringCreator != null;
  }
  
  public boolean canCreateUsingArrayDelegate()
  {
    return this._arrayDelegateType != null;
  }
  
  public boolean canCreateUsingDefault()
  {
    return this._defaultCreator != null;
  }
  
  public boolean canCreateUsingDelegate()
  {
    return this._delegateType != null;
  }
  
  public void configureFromArraySettings(AnnotatedWithParams paramAnnotatedWithParams, JavaType paramJavaType, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    this._arrayDelegateCreator = paramAnnotatedWithParams;
    this._arrayDelegateType = paramJavaType;
    this._arrayDelegateArguments = paramArrayOfSettableBeanProperty;
  }
  
  public void configureFromBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromBooleanCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromDoubleCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromIntCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromLongCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromObjectSettings(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, JavaType paramJavaType, SettableBeanProperty[] paramArrayOfSettableBeanProperty1, AnnotatedWithParams paramAnnotatedWithParams3, SettableBeanProperty[] paramArrayOfSettableBeanProperty2)
  {
    this._defaultCreator = paramAnnotatedWithParams1;
    this._delegateCreator = paramAnnotatedWithParams2;
    this._delegateType = paramJavaType;
    this._delegateArguments = paramArrayOfSettableBeanProperty1;
    this._withArgsCreator = paramAnnotatedWithParams3;
    this._constructorArguments = paramArrayOfSettableBeanProperty2;
  }
  
  public void configureFromStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromStringCreator = paramAnnotatedWithParams;
  }
  
  public void configureIncompleteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    this._incompleteParameter = paramAnnotatedParameter;
  }
  
  public Object createFromBoolean(DeserializationContext paramDeserializationContext, boolean paramBoolean)
    throws IOException
  {
    if (this._fromBooleanCreator == null) {
      throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Boolean value (%s); no single-boolean/Boolean-arg constructor/factory method", new Object[] { getValueTypeDesc(), Boolean.valueOf(paramBoolean) });
    }
    try
    {
      Object localObject = this._fromBooleanCreator.call1(Boolean.valueOf(paramBoolean));
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw rewrapCtorProblem(paramDeserializationContext, localThrowable);
    }
  }
  
  public Object createFromDouble(DeserializationContext paramDeserializationContext, double paramDouble)
    throws IOException
  {
    if (this._fromDoubleCreator == null) {
      throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Floating-point number (%s); no one-double/Double-arg constructor/factory method", new Object[] { getValueTypeDesc(), Double.valueOf(paramDouble) });
    }
    try
    {
      Object localObject = this._fromDoubleCreator.call1(Double.valueOf(paramDouble));
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw rewrapCtorProblem(paramDeserializationContext, localThrowable);
    }
  }
  
  public Object createFromInt(DeserializationContext paramDeserializationContext, int paramInt)
    throws IOException
  {
    try
    {
      if (this._fromIntCreator != null) {
        return this._fromIntCreator.call1(Integer.valueOf(paramInt));
      }
      if (this._fromLongCreator != null)
      {
        Object localObject = this._fromLongCreator.call1(Long.valueOf(paramInt));
        return localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      throw rewrapCtorProblem(paramDeserializationContext, localThrowable);
    }
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Integral number (%s); no single-int-arg constructor/factory method", new Object[] { getValueTypeDesc(), Integer.valueOf(paramInt) });
  }
  
  public Object createFromLong(DeserializationContext paramDeserializationContext, long paramLong)
    throws IOException
  {
    if (this._fromLongCreator == null) {
      throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Long integral number (%s); no single-long-arg constructor/factory method", new Object[] { getValueTypeDesc(), Long.valueOf(paramLong) });
    }
    try
    {
      Object localObject = this._fromLongCreator.call1(Long.valueOf(paramLong));
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw rewrapCtorProblem(paramDeserializationContext, localThrowable);
    }
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
    throws IOException
  {
    if (this._withArgsCreator == null) {
      throw new IllegalStateException("No with-args constructor for " + getValueTypeDesc());
    }
    try
    {
      paramArrayOfObject = this._withArgsCreator.call(paramArrayOfObject);
      return paramArrayOfObject;
    }
    catch (Throwable paramArrayOfObject)
    {
      throw rewrapCtorProblem(paramDeserializationContext, paramArrayOfObject);
    }
  }
  
  public Object createFromString(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    if (this._fromStringCreator == null) {
      return _createFromStringFallbacks(paramDeserializationContext, paramString);
    }
    try
    {
      paramString = this._fromStringCreator.call1(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw rewrapCtorProblem(paramDeserializationContext, paramString);
    }
  }
  
  public Object createUsingArrayDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._arrayDelegateCreator == null) {
      return createUsingDelegate(paramDeserializationContext, paramObject);
    }
    return _createUsingDelegate(this._arrayDelegateCreator, this._arrayDelegateArguments, paramDeserializationContext, paramObject);
  }
  
  public Object createUsingDefault(DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._defaultCreator == null) {
      throw new IllegalStateException("No default constructor for " + getValueTypeDesc());
    }
    try
    {
      Object localObject = this._defaultCreator.call();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw rewrapCtorProblem(paramDeserializationContext, localThrowable);
    }
  }
  
  public Object createUsingDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    return _createUsingDelegate(this._delegateCreator, this._delegateArguments, paramDeserializationContext, paramObject);
  }
  
  public AnnotatedWithParams getArrayDelegateCreator()
  {
    return this._arrayDelegateCreator;
  }
  
  public JavaType getArrayDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return this._arrayDelegateType;
  }
  
  public AnnotatedWithParams getDefaultCreator()
  {
    return this._defaultCreator;
  }
  
  public AnnotatedWithParams getDelegateCreator()
  {
    return this._delegateCreator;
  }
  
  public JavaType getDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return this._delegateType;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    return this._constructorArguments;
  }
  
  public AnnotatedParameter getIncompleteParameter()
  {
    return this._incompleteParameter;
  }
  
  public String getValueTypeDesc()
  {
    return this._valueTypeDesc;
  }
  
  public AnnotatedWithParams getWithArgsCreator()
  {
    return this._withArgsCreator;
  }
  
  protected JsonMappingException rewrapCtorProblem(DeserializationContext paramDeserializationContext, Throwable paramThrowable)
  {
    Object localObject;
    if (!(paramThrowable instanceof ExceptionInInitializerError))
    {
      localObject = paramThrowable;
      if (!(paramThrowable instanceof InvocationTargetException)) {}
    }
    else
    {
      Throwable localThrowable = paramThrowable.getCause();
      localObject = paramThrowable;
      if (localThrowable != null) {
        localObject = localThrowable;
      }
    }
    return wrapAsJsonMappingException(paramDeserializationContext, (Throwable)localObject);
  }
  
  protected JsonMappingException unwrapAndWrapException(DeserializationContext paramDeserializationContext, Throwable paramThrowable)
  {
    for (Object localObject = paramThrowable; localObject != null; localObject = ((Throwable)localObject).getCause()) {
      if ((localObject instanceof JsonMappingException)) {
        return (JsonMappingException)localObject;
      }
    }
    localObject = String.format("Instantiation of %s value failed (%s): %s", new Object[] { getValueTypeDesc(), paramThrowable.getClass().getName(), paramThrowable.getMessage() });
    return JsonMappingException.from(paramDeserializationContext.getParser(), (String)localObject, paramThrowable);
  }
  
  protected JsonMappingException wrapAsJsonMappingException(DeserializationContext paramDeserializationContext, Throwable paramThrowable)
  {
    if ((paramThrowable instanceof JsonMappingException)) {
      return (JsonMappingException)paramThrowable;
    }
    String str = String.format("Instantiation of %s value failed (%s): %s", new Object[] { getValueTypeDesc(), paramThrowable.getClass().getName(), paramThrowable.getMessage() });
    return JsonMappingException.from(paramDeserializationContext.getParser(), str, paramThrowable);
  }
  
  @Deprecated
  protected JsonMappingException wrapException(Throwable paramThrowable)
  {
    for (Throwable localThrowable = paramThrowable; localThrowable != null; localThrowable = localThrowable.getCause()) {
      if ((localThrowable instanceof JsonMappingException)) {
        return (JsonMappingException)localThrowable;
      }
    }
    return new JsonMappingException(null, "Instantiation of " + getValueTypeDesc() + " value failed: " + paramThrowable.getMessage(), paramThrowable);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\deser\std\StdValueInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */