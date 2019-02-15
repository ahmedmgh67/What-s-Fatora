package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import java.lang.annotation.Annotation;

public abstract interface BeanProperty
  extends Named
{
  public static final JsonFormat.Value EMPTY_FORMAT = new JsonFormat.Value();
  public static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();
  
  public abstract void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor, SerializerProvider paramSerializerProvider)
    throws JsonMappingException;
  
  public abstract JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract JsonFormat.Value findPropertyFormat(MapperConfig<?> paramMapperConfig, Class<?> paramClass);
  
  public abstract JsonInclude.Value findPropertyInclusion(MapperConfig<?> paramMapperConfig, Class<?> paramClass);
  
  public abstract <A extends Annotation> A getAnnotation(Class<A> paramClass);
  
  public abstract <A extends Annotation> A getContextAnnotation(Class<A> paramClass);
  
  public abstract PropertyName getFullName();
  
  public abstract AnnotatedMember getMember();
  
  public abstract PropertyMetadata getMetadata();
  
  public abstract String getName();
  
  public abstract JavaType getType();
  
  public abstract PropertyName getWrapperName();
  
  public abstract boolean isRequired();
  
  public abstract boolean isVirtual();
  
  public static class Std
    implements BeanProperty
  {
    protected final Annotations _contextAnnotations;
    protected final AnnotatedMember _member;
    protected final PropertyMetadata _metadata;
    protected final PropertyName _name;
    protected final JavaType _type;
    protected final PropertyName _wrapperName;
    
    public Std(Std paramStd, JavaType paramJavaType)
    {
      this(paramStd._name, paramJavaType, paramStd._wrapperName, paramStd._contextAnnotations, paramStd._member, paramStd._metadata);
    }
    
    public Std(PropertyName paramPropertyName1, JavaType paramJavaType, PropertyName paramPropertyName2, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, PropertyMetadata paramPropertyMetadata)
    {
      this._name = paramPropertyName1;
      this._type = paramJavaType;
      this._wrapperName = paramPropertyName2;
      this._metadata = paramPropertyMetadata;
      this._member = paramAnnotatedMember;
      this._contextAnnotations = paramAnnotations;
    }
    
    @Deprecated
    public Std(String paramString, JavaType paramJavaType, PropertyName paramPropertyName, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, boolean paramBoolean) {}
    
    public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor, SerializerProvider paramSerializerProvider)
    {
      throw new UnsupportedOperationException("Instances of " + getClass().getName() + " should not get visited");
    }
    
    @Deprecated
    public JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector)
    {
      if ((this._member != null) && (paramAnnotationIntrospector != null))
      {
        paramAnnotationIntrospector = paramAnnotationIntrospector.findFormat(this._member);
        if (paramAnnotationIntrospector != null) {
          return paramAnnotationIntrospector;
        }
      }
      return EMPTY_FORMAT;
    }
    
    public JsonFormat.Value findPropertyFormat(MapperConfig<?> paramMapperConfig, Class<?> paramClass)
    {
      paramClass = paramMapperConfig.getDefaultPropertyFormat(paramClass);
      paramMapperConfig = paramMapperConfig.getAnnotationIntrospector();
      if ((paramMapperConfig == null) || (this._member == null)) {}
      do
      {
        return paramClass;
        paramMapperConfig = paramMapperConfig.findFormat(this._member);
      } while (paramMapperConfig == null);
      return paramClass.withOverrides(paramMapperConfig);
    }
    
    public JsonInclude.Value findPropertyInclusion(MapperConfig<?> paramMapperConfig, Class<?> paramClass)
    {
      paramClass = paramMapperConfig.getDefaultPropertyInclusion(paramClass);
      paramMapperConfig = paramMapperConfig.getAnnotationIntrospector();
      if ((paramMapperConfig == null) || (this._member == null)) {}
      do
      {
        return paramClass;
        paramMapperConfig = paramMapperConfig.findPropertyInclusion(this._member);
      } while (paramMapperConfig == null);
      return paramClass.withOverrides(paramMapperConfig);
    }
    
    public <A extends Annotation> A getAnnotation(Class<A> paramClass)
    {
      if (this._member == null) {
        return null;
      }
      return this._member.getAnnotation(paramClass);
    }
    
    public <A extends Annotation> A getContextAnnotation(Class<A> paramClass)
    {
      if (this._contextAnnotations == null) {
        return null;
      }
      return this._contextAnnotations.get(paramClass);
    }
    
    public PropertyName getFullName()
    {
      return this._name;
    }
    
    public AnnotatedMember getMember()
    {
      return this._member;
    }
    
    public PropertyMetadata getMetadata()
    {
      return this._metadata;
    }
    
    public String getName()
    {
      return this._name.getSimpleName();
    }
    
    public JavaType getType()
    {
      return this._type;
    }
    
    public PropertyName getWrapperName()
    {
      return this._wrapperName;
    }
    
    public boolean isRequired()
    {
      return this._metadata.isRequired();
    }
    
    public boolean isVirtual()
    {
      return false;
    }
    
    public Std withType(JavaType paramJavaType)
    {
      return new Std(this, paramJavaType);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\BeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */