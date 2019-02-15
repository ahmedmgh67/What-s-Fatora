package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public abstract class ConcreteBeanPropertyBase
  implements BeanProperty, Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient JsonFormat.Value _format;
  protected final PropertyMetadata _metadata;
  
  protected ConcreteBeanPropertyBase(PropertyMetadata paramPropertyMetadata)
  {
    PropertyMetadata localPropertyMetadata = paramPropertyMetadata;
    if (paramPropertyMetadata == null) {
      localPropertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
    }
    this._metadata = localPropertyMetadata;
  }
  
  protected ConcreteBeanPropertyBase(ConcreteBeanPropertyBase paramConcreteBeanPropertyBase)
  {
    this._metadata = paramConcreteBeanPropertyBase._metadata;
    this._format = paramConcreteBeanPropertyBase._format;
  }
  
  @Deprecated
  public final JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector)
  {
    JsonFormat.Value localValue2 = this._format;
    Object localObject = localValue2;
    if (localValue2 == null)
    {
      JsonFormat.Value localValue1 = localValue2;
      if (paramAnnotationIntrospector != null)
      {
        localObject = getMember();
        localValue1 = localValue2;
        if (localObject != null) {
          localValue1 = paramAnnotationIntrospector.findFormat((Annotated)localObject);
        }
      }
      localObject = localValue1;
      if (localValue1 == null) {
        localObject = EMPTY_FORMAT;
      }
    }
    return (JsonFormat.Value)localObject;
  }
  
  public JsonFormat.Value findPropertyFormat(MapperConfig<?> paramMapperConfig, Class<?> paramClass)
  {
    paramClass = paramMapperConfig.getDefaultPropertyFormat(paramClass);
    paramMapperConfig = paramMapperConfig.getAnnotationIntrospector();
    AnnotatedMember localAnnotatedMember = getMember();
    if ((paramMapperConfig == null) || (localAnnotatedMember == null)) {}
    do
    {
      return paramClass;
      paramMapperConfig = paramMapperConfig.findFormat(localAnnotatedMember);
    } while (paramMapperConfig == null);
    return paramClass.withOverrides(paramMapperConfig);
  }
  
  public JsonInclude.Value findPropertyInclusion(MapperConfig<?> paramMapperConfig, Class<?> paramClass)
  {
    paramClass = paramMapperConfig.getDefaultPropertyInclusion(paramClass);
    paramMapperConfig = paramMapperConfig.getAnnotationIntrospector();
    AnnotatedMember localAnnotatedMember = getMember();
    if ((paramMapperConfig == null) || (localAnnotatedMember == null)) {}
    do
    {
      return paramClass;
      paramMapperConfig = paramMapperConfig.findPropertyInclusion(localAnnotatedMember);
    } while (paramMapperConfig == null);
    return paramClass.withOverrides(paramMapperConfig);
  }
  
  public PropertyMetadata getMetadata()
  {
    return this._metadata;
  }
  
  public boolean isRequired()
  {
    return this._metadata.isRequired();
  }
  
  public boolean isVirtual()
  {
    return false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\introspect\ConcreteBeanPropertyBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */