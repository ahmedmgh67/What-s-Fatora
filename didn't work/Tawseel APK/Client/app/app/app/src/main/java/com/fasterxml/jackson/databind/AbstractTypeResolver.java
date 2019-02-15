package com.fasterxml.jackson.databind;

public abstract class AbstractTypeResolver
{
  public JavaType findTypeMapping(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    return null;
  }
  
  public JavaType resolveAbstractType(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
  {
    return resolveAbstractType(paramDeserializationConfig, paramBeanDescription.getType());
  }
  
  public JavaType resolveAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\AbstractTypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */