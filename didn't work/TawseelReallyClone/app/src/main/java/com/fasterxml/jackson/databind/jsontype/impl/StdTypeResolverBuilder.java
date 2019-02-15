package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.Collection;

public class StdTypeResolverBuilder
  implements TypeResolverBuilder<StdTypeResolverBuilder>
{
  protected TypeIdResolver _customIdResolver;
  protected Class<?> _defaultImpl;
  protected JsonTypeInfo.Id _idType;
  protected JsonTypeInfo.As _includeAs;
  protected boolean _typeIdVisible = false;
  protected String _typeProperty;
  
  public static StdTypeResolverBuilder noTypeInfoBuilder()
  {
    return new StdTypeResolverBuilder().init(JsonTypeInfo.Id.NONE, null);
  }
  
  public TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
  {
    if (this._idType == JsonTypeInfo.Id.NONE) {
      return null;
    }
    paramDeserializationConfig = idResolver(paramDeserializationConfig, paramJavaType, paramCollection, false, true);
    switch (1.$SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$As[this._includeAs.ordinal()])
    {
    default: 
      throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
    case 1: 
      return new AsArrayTypeDeserializer(paramJavaType, paramDeserializationConfig, this._typeProperty, this._typeIdVisible, this._defaultImpl);
    case 2: 
    case 5: 
      return new AsPropertyTypeDeserializer(paramJavaType, paramDeserializationConfig, this._typeProperty, this._typeIdVisible, this._defaultImpl, this._includeAs);
    case 3: 
      return new AsWrapperTypeDeserializer(paramJavaType, paramDeserializationConfig, this._typeProperty, this._typeIdVisible, this._defaultImpl);
    }
    return new AsExternalTypeDeserializer(paramJavaType, paramDeserializationConfig, this._typeProperty, this._typeIdVisible, this._defaultImpl);
  }
  
  public TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
  {
    if (this._idType == JsonTypeInfo.Id.NONE) {
      return null;
    }
    paramSerializationConfig = idResolver(paramSerializationConfig, paramJavaType, paramCollection, true, false);
    switch (1.$SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$As[this._includeAs.ordinal()])
    {
    default: 
      throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
    case 1: 
      return new AsArrayTypeSerializer(paramSerializationConfig, null);
    case 2: 
      return new AsPropertyTypeSerializer(paramSerializationConfig, null, this._typeProperty);
    case 3: 
      return new AsWrapperTypeSerializer(paramSerializationConfig, null);
    case 4: 
      return new AsExternalTypeSerializer(paramSerializationConfig, null, this._typeProperty);
    }
    return new AsExistingPropertyTypeSerializer(paramSerializationConfig, null, this._typeProperty);
  }
  
  public StdTypeResolverBuilder defaultImpl(Class<?> paramClass)
  {
    this._defaultImpl = paramClass;
    return this;
  }
  
  public Class<?> getDefaultImpl()
  {
    return this._defaultImpl;
  }
  
  public String getTypeProperty()
  {
    return this._typeProperty;
  }
  
  protected TypeIdResolver idResolver(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, Collection<NamedType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this._customIdResolver != null) {
      return this._customIdResolver;
    }
    if (this._idType == null) {
      throw new IllegalStateException("Can not build, 'init()' not yet called");
    }
    switch (this._idType)
    {
    default: 
      throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this._idType);
    case ???: 
      return new ClassNameIdResolver(paramJavaType, paramMapperConfig.getTypeFactory());
    case ???: 
      return new MinimalClassNameIdResolver(paramJavaType, paramMapperConfig.getTypeFactory());
    case ???: 
      return TypeNameIdResolver.construct(paramMapperConfig, paramJavaType, paramCollection, paramBoolean1, paramBoolean2);
    }
    return null;
  }
  
  public StdTypeResolverBuilder inclusion(JsonTypeInfo.As paramAs)
  {
    if (paramAs == null) {
      throw new IllegalArgumentException("includeAs can not be null");
    }
    this._includeAs = paramAs;
    return this;
  }
  
  public StdTypeResolverBuilder init(JsonTypeInfo.Id paramId, TypeIdResolver paramTypeIdResolver)
  {
    if (paramId == null) {
      throw new IllegalArgumentException("idType can not be null");
    }
    this._idType = paramId;
    this._customIdResolver = paramTypeIdResolver;
    this._typeProperty = paramId.getDefaultPropertyName();
    return this;
  }
  
  public boolean isTypeIdVisible()
  {
    return this._typeIdVisible;
  }
  
  public StdTypeResolverBuilder typeIdVisibility(boolean paramBoolean)
  {
    this._typeIdVisible = paramBoolean;
    return this;
  }
  
  public StdTypeResolverBuilder typeProperty(String paramString)
  {
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() != 0) {}
    }
    else
    {
      str = this._idType.getDefaultPropertyName();
    }
    this._typeProperty = str;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\jsontype\impl\StdTypeResolverBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */