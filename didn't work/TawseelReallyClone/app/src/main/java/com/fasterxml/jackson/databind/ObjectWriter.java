package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectWriter
  implements Versioned, Serializable
{
  protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
  private static final long serialVersionUID = 1L;
  protected final SerializationConfig _config;
  protected final JsonFactory _generatorFactory;
  protected final GeneratorSettings _generatorSettings;
  protected final Prefetch _prefetch;
  protected final SerializerFactory _serializerFactory;
  protected final DefaultSerializerProvider _serializerProvider;
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._generatorFactory = paramObjectMapper._jsonFactory;
    this._generatorSettings = GeneratorSettings.empty;
    this._prefetch = Prefetch.empty;
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, FormatSchema paramFormatSchema)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._generatorFactory = paramObjectMapper._jsonFactory;
    if (paramFormatSchema == null) {}
    for (paramObjectMapper = GeneratorSettings.empty;; paramObjectMapper = new GeneratorSettings(null, paramFormatSchema, null, null))
    {
      this._generatorSettings = paramObjectMapper;
      this._prefetch = Prefetch.empty;
      return;
    }
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, JavaType paramJavaType, PrettyPrinter paramPrettyPrinter)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._generatorFactory = paramObjectMapper._jsonFactory;
    if (paramPrettyPrinter == null) {}
    for (paramObjectMapper = GeneratorSettings.empty;; paramObjectMapper = new GeneratorSettings(paramPrettyPrinter, null, null, null))
    {
      this._generatorSettings = paramObjectMapper;
      if ((paramJavaType != null) && (!paramJavaType.hasRawClass(Object.class))) {
        break;
      }
      this._prefetch = Prefetch.empty;
      return;
    }
    paramObjectMapper = paramJavaType.withStaticTyping();
    this._prefetch = Prefetch.empty.forRootType(this, paramObjectMapper);
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, JsonFactory paramJsonFactory)
  {
    this._config = paramObjectWriter._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, paramJsonFactory.requiresPropertyOrdering());
    this._serializerProvider = paramObjectWriter._serializerProvider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._generatorFactory = paramObjectWriter._generatorFactory;
    this._generatorSettings = paramObjectWriter._generatorSettings;
    this._prefetch = paramObjectWriter._prefetch;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectWriter._serializerProvider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._generatorFactory = paramObjectWriter._generatorFactory;
    this._generatorSettings = paramObjectWriter._generatorSettings;
    this._prefetch = paramObjectWriter._prefetch;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig, GeneratorSettings paramGeneratorSettings, Prefetch paramPrefetch)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectWriter._serializerProvider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._generatorFactory = paramObjectWriter._generatorFactory;
    this._generatorSettings = paramGeneratorSettings;
    this._prefetch = paramPrefetch;
  }
  
  /* Error */
  private final void _writeCloseable(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: checkcast 114	java/io/Closeable
    //   4: astore 5
    //   6: aload 5
    //   8: astore_3
    //   9: aload_1
    //   10: astore 4
    //   12: aload_0
    //   13: getfield 68	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   16: aload_1
    //   17: aload_2
    //   18: aload_0
    //   19: invokevirtual 117	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   22: invokevirtual 121	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   25: aconst_null
    //   26: astore_2
    //   27: aload 5
    //   29: astore_3
    //   30: aload_2
    //   31: astore 4
    //   33: aload_1
    //   34: invokevirtual 126	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   37: aconst_null
    //   38: astore_3
    //   39: aload_2
    //   40: astore 4
    //   42: aload 5
    //   44: invokeinterface 127 1 0
    //   49: iconst_0
    //   50: ifeq +15 -> 65
    //   53: getstatic 133	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   56: astore_1
    //   57: new 135	java/lang/NullPointerException
    //   60: dup
    //   61: invokespecial 136	java/lang/NullPointerException:<init>	()V
    //   64: athrow
    //   65: iconst_0
    //   66: ifeq +11 -> 77
    //   69: new 135	java/lang/NullPointerException
    //   72: dup
    //   73: invokespecial 136	java/lang/NullPointerException:<init>	()V
    //   76: athrow
    //   77: return
    //   78: astore_1
    //   79: aload 4
    //   81: ifnull +17 -> 98
    //   84: aload 4
    //   86: getstatic 133	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   89: invokevirtual 140	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   92: pop
    //   93: aload 4
    //   95: invokevirtual 126	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   98: aload_3
    //   99: ifnull +9 -> 108
    //   102: aload_3
    //   103: invokeinterface 127 1 0
    //   108: aload_1
    //   109: athrow
    //   110: astore_1
    //   111: return
    //   112: astore_2
    //   113: goto -15 -> 98
    //   116: astore_2
    //   117: goto -9 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	ObjectWriter
    //   0	120	1	paramJsonGenerator	JsonGenerator
    //   0	120	2	paramObject	Object
    //   8	95	3	localCloseable1	Closeable
    //   10	84	4	localObject	Object
    //   4	39	5	localCloseable2	Closeable
    // Exception table:
    //   from	to	target	type
    //   12	25	78	finally
    //   33	37	78	finally
    //   42	49	78	finally
    //   69	77	110	java/io/IOException
    //   93	98	112	java/io/IOException
    //   102	108	116	java/io/IOException
  }
  
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    _configureGenerator(paramJsonGenerator);
    if ((this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE)) && ((paramObject instanceof Closeable))) {
      _writeCloseable(paramJsonGenerator, paramObject);
    }
    for (;;)
    {
      return;
      int i = 0;
      try
      {
        this._prefetch.serialize(paramJsonGenerator, paramObject, _serializerProvider());
        i = 1;
        paramJsonGenerator.close();
        if (1 != 0) {
          continue;
        }
        paramJsonGenerator.disable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
        try
        {
          paramJsonGenerator.close();
          return;
        }
        catch (IOException paramJsonGenerator)
        {
          return;
        }
        try
        {
          paramJsonGenerator.close();
          throw ((Throwable)paramObject);
        }
        catch (IOException paramJsonGenerator)
        {
          for (;;) {}
        }
      }
      finally
      {
        if (i == 0) {
          paramJsonGenerator.disable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
        }
      }
    }
  }
  
  protected final void _configureGenerator(JsonGenerator paramJsonGenerator)
  {
    this._config.initialize(paramJsonGenerator);
    this._generatorSettings.initialize(paramJsonGenerator);
  }
  
  protected ObjectWriter _new(GeneratorSettings paramGeneratorSettings, Prefetch paramPrefetch)
  {
    return new ObjectWriter(this, this._config, paramGeneratorSettings, paramPrefetch);
  }
  
  protected ObjectWriter _new(ObjectWriter paramObjectWriter, JsonFactory paramJsonFactory)
  {
    return new ObjectWriter(paramObjectWriter, paramJsonFactory);
  }
  
  protected ObjectWriter _new(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig)
  {
    return new ObjectWriter(paramObjectWriter, paramSerializationConfig);
  }
  
  protected SequenceWriter _newSequenceWriter(boolean paramBoolean1, JsonGenerator paramJsonGenerator, boolean paramBoolean2)
    throws IOException
  {
    _configureGenerator(paramJsonGenerator);
    return new SequenceWriter(_serializerProvider(), paramJsonGenerator, paramBoolean2, this._prefetch).init(paramBoolean1);
  }
  
  protected DefaultSerializerProvider _serializerProvider()
  {
    return this._serializerProvider.createInstance(this._config, this._serializerFactory);
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!this._generatorFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + this._generatorFactory.getFormatName());
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("type must be provided");
    }
    _serializerProvider().acceptJsonFormatVisitor(paramJavaType, paramJsonFormatVisitorWrapper);
  }
  
  public void acceptJsonFormatVisitor(Class<?> paramClass, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    acceptJsonFormatVisitor(this._config.constructType(paramClass), paramJsonFormatVisitorWrapper);
  }
  
  public boolean canSerialize(Class<?> paramClass)
  {
    return _serializerProvider().hasSerializerFor(paramClass, null);
  }
  
  public boolean canSerialize(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    return _serializerProvider().hasSerializerFor(paramClass, paramAtomicReference);
  }
  
  public ObjectWriter forType(TypeReference<?> paramTypeReference)
  {
    return forType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectWriter forType(JavaType paramJavaType)
  {
    paramJavaType = this._prefetch.forRootType(this, paramJavaType);
    if (paramJavaType == this._prefetch) {
      return this;
    }
    return _new(this._generatorSettings, paramJavaType);
  }
  
  public ObjectWriter forType(Class<?> paramClass)
  {
    if (paramClass == Object.class) {
      return forType((JavaType)null);
    }
    return forType(this._config.constructType(paramClass));
  }
  
  public ContextAttributes getAttributes()
  {
    return this._config.getAttributes();
  }
  
  public SerializationConfig getConfig()
  {
    return this._config;
  }
  
  public JsonFactory getFactory()
  {
    return this._generatorFactory;
  }
  
  public TypeFactory getTypeFactory()
  {
    return this._config.getTypeFactory();
  }
  
  public boolean hasPrefetchedSerializer()
  {
    return this._prefetch.hasSerializer();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return this._generatorFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return this._config.isEnabled(paramMapperFeature);
  }
  
  public boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return this._config.isEnabled(paramSerializationFeature);
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public ObjectWriter with(Base64Variant paramBase64Variant)
  {
    paramBase64Variant = this._config.with(paramBase64Variant);
    if (paramBase64Variant == this._config) {
      return this;
    }
    return _new(this, paramBase64Variant);
  }
  
  public ObjectWriter with(FormatFeature paramFormatFeature)
  {
    paramFormatFeature = this._config.with(paramFormatFeature);
    if (paramFormatFeature == this._config) {
      return this;
    }
    return _new(this, paramFormatFeature);
  }
  
  public ObjectWriter with(FormatSchema paramFormatSchema)
  {
    GeneratorSettings localGeneratorSettings = this._generatorSettings.with(paramFormatSchema);
    if (localGeneratorSettings == this._generatorSettings) {
      return this;
    }
    _verifySchemaType(paramFormatSchema);
    return _new(localGeneratorSettings, this._prefetch);
  }
  
  public ObjectWriter with(JsonFactory paramJsonFactory)
  {
    if (paramJsonFactory == this._generatorFactory) {
      return this;
    }
    return _new(this, paramJsonFactory);
  }
  
  public ObjectWriter with(JsonGenerator.Feature paramFeature)
  {
    paramFeature = this._config.with(paramFeature);
    if (paramFeature == this._config) {
      return this;
    }
    return _new(this, paramFeature);
  }
  
  public ObjectWriter with(PrettyPrinter paramPrettyPrinter)
  {
    paramPrettyPrinter = this._generatorSettings.with(paramPrettyPrinter);
    if (paramPrettyPrinter == this._generatorSettings) {
      return this;
    }
    return _new(paramPrettyPrinter, this._prefetch);
  }
  
  public ObjectWriter with(CharacterEscapes paramCharacterEscapes)
  {
    paramCharacterEscapes = this._generatorSettings.with(paramCharacterEscapes);
    if (paramCharacterEscapes == this._generatorSettings) {
      return this;
    }
    return _new(paramCharacterEscapes, this._prefetch);
  }
  
  public ObjectWriter with(SerializationFeature paramSerializationFeature)
  {
    paramSerializationFeature = this._config.with(paramSerializationFeature);
    if (paramSerializationFeature == this._config) {
      return this;
    }
    return _new(this, paramSerializationFeature);
  }
  
  public ObjectWriter with(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    paramSerializationFeature = this._config.with(paramSerializationFeature, paramVarArgs);
    if (paramSerializationFeature == this._config) {
      return this;
    }
    return _new(this, paramSerializationFeature);
  }
  
  public ObjectWriter with(ContextAttributes paramContextAttributes)
  {
    paramContextAttributes = this._config.with(paramContextAttributes);
    if (paramContextAttributes == this._config) {
      return this;
    }
    return _new(this, paramContextAttributes);
  }
  
  public ObjectWriter with(FilterProvider paramFilterProvider)
  {
    if (paramFilterProvider == this._config.getFilterProvider()) {
      return this;
    }
    return _new(this, this._config.withFilters(paramFilterProvider));
  }
  
  public ObjectWriter with(DateFormat paramDateFormat)
  {
    paramDateFormat = this._config.with(paramDateFormat);
    if (paramDateFormat == this._config) {
      return this;
    }
    return _new(this, paramDateFormat);
  }
  
  public ObjectWriter with(Locale paramLocale)
  {
    paramLocale = this._config.with(paramLocale);
    if (paramLocale == this._config) {
      return this;
    }
    return _new(this, paramLocale);
  }
  
  public ObjectWriter with(TimeZone paramTimeZone)
  {
    paramTimeZone = this._config.with(paramTimeZone);
    if (paramTimeZone == this._config) {
      return this;
    }
    return _new(this, paramTimeZone);
  }
  
  public ObjectWriter withAttribute(Object paramObject1, Object paramObject2)
  {
    paramObject1 = (SerializationConfig)this._config.withAttribute(paramObject1, paramObject2);
    if (paramObject1 == this._config) {
      return this;
    }
    return _new(this, (SerializationConfig)paramObject1);
  }
  
  public ObjectWriter withAttributes(Map<?, ?> paramMap)
  {
    paramMap = (SerializationConfig)this._config.withAttributes(paramMap);
    if (paramMap == this._config) {
      return this;
    }
    return _new(this, paramMap);
  }
  
  public ObjectWriter withDefaultPrettyPrinter()
  {
    return with(this._config.getDefaultPrettyPrinter());
  }
  
  public ObjectWriter withFeatures(FormatFeature... paramVarArgs)
  {
    paramVarArgs = this._config.withFeatures(paramVarArgs);
    if (paramVarArgs == this._config) {
      return this;
    }
    return _new(this, paramVarArgs);
  }
  
  public ObjectWriter withFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    paramVarArgs = this._config.withFeatures(paramVarArgs);
    if (paramVarArgs == this._config) {
      return this;
    }
    return _new(this, paramVarArgs);
  }
  
  public ObjectWriter withFeatures(SerializationFeature... paramVarArgs)
  {
    paramVarArgs = this._config.withFeatures(paramVarArgs);
    if (paramVarArgs == this._config) {
      return this;
    }
    return _new(this, paramVarArgs);
  }
  
  public ObjectWriter withRootName(PropertyName paramPropertyName)
  {
    paramPropertyName = this._config.withRootName(paramPropertyName);
    if (paramPropertyName == this._config) {
      return this;
    }
    return _new(this, paramPropertyName);
  }
  
  public ObjectWriter withRootName(String paramString)
  {
    paramString = (SerializationConfig)this._config.withRootName(paramString);
    if (paramString == this._config) {
      return this;
    }
    return _new(this, paramString);
  }
  
  public ObjectWriter withRootValueSeparator(SerializableString paramSerializableString)
  {
    paramSerializableString = this._generatorSettings.withRootValueSeparator(paramSerializableString);
    if (paramSerializableString == this._generatorSettings) {
      return this;
    }
    return _new(paramSerializableString, this._prefetch);
  }
  
  public ObjectWriter withRootValueSeparator(String paramString)
  {
    paramString = this._generatorSettings.withRootValueSeparator(paramString);
    if (paramString == this._generatorSettings) {
      return this;
    }
    return _new(paramString, this._prefetch);
  }
  
  @Deprecated
  public ObjectWriter withSchema(FormatSchema paramFormatSchema)
  {
    return with(paramFormatSchema);
  }
  
  @Deprecated
  public ObjectWriter withType(TypeReference<?> paramTypeReference)
  {
    return forType(paramTypeReference);
  }
  
  @Deprecated
  public ObjectWriter withType(JavaType paramJavaType)
  {
    return forType(paramJavaType);
  }
  
  @Deprecated
  public ObjectWriter withType(Class<?> paramClass)
  {
    return forType(paramClass);
  }
  
  public ObjectWriter withView(Class<?> paramClass)
  {
    paramClass = this._config.withView(paramClass);
    if (paramClass == this._config) {
      return this;
    }
    return _new(this, paramClass);
  }
  
  public ObjectWriter without(FormatFeature paramFormatFeature)
  {
    paramFormatFeature = this._config.without(paramFormatFeature);
    if (paramFormatFeature == this._config) {
      return this;
    }
    return _new(this, paramFormatFeature);
  }
  
  public ObjectWriter without(JsonGenerator.Feature paramFeature)
  {
    paramFeature = this._config.without(paramFeature);
    if (paramFeature == this._config) {
      return this;
    }
    return _new(this, paramFeature);
  }
  
  public ObjectWriter without(SerializationFeature paramSerializationFeature)
  {
    paramSerializationFeature = this._config.without(paramSerializationFeature);
    if (paramSerializationFeature == this._config) {
      return this;
    }
    return _new(this, paramSerializationFeature);
  }
  
  public ObjectWriter without(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    paramSerializationFeature = this._config.without(paramSerializationFeature, paramVarArgs);
    if (paramSerializationFeature == this._config) {
      return this;
    }
    return _new(this, paramSerializationFeature);
  }
  
  public ObjectWriter withoutAttribute(Object paramObject)
  {
    paramObject = (SerializationConfig)this._config.withoutAttribute(paramObject);
    if (paramObject == this._config) {
      return this;
    }
    return _new(this, (SerializationConfig)paramObject);
  }
  
  public ObjectWriter withoutFeatures(FormatFeature... paramVarArgs)
  {
    paramVarArgs = this._config.withoutFeatures(paramVarArgs);
    if (paramVarArgs == this._config) {
      return this;
    }
    return _new(this, paramVarArgs);
  }
  
  public ObjectWriter withoutFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    paramVarArgs = this._config.withoutFeatures(paramVarArgs);
    if (paramVarArgs == this._config) {
      return this;
    }
    return _new(this, paramVarArgs);
  }
  
  public ObjectWriter withoutFeatures(SerializationFeature... paramVarArgs)
  {
    paramVarArgs = this._config.withoutFeatures(paramVarArgs);
    if (paramVarArgs == this._config) {
      return this;
    }
    return _new(this, paramVarArgs);
  }
  
  public ObjectWriter withoutRootName()
  {
    SerializationConfig localSerializationConfig = this._config.withRootName(PropertyName.NO_NAME);
    if (localSerializationConfig == this._config) {
      return this;
    }
    return _new(this, localSerializationConfig);
  }
  
  /* Error */
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 146	com/fasterxml/jackson/databind/ObjectWriter:_configureGenerator	(Lcom/fasterxml/jackson/core/JsonGenerator;)V
    //   5: aload_0
    //   6: getfield 46	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   9: getstatic 152	com/fasterxml/jackson/databind/SerializationFeature:CLOSE_CLOSEABLE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   12: invokevirtual 156	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   15: ifeq +90 -> 105
    //   18: aload_2
    //   19: instanceof 114
    //   22: ifeq +83 -> 105
    //   25: aload_2
    //   26: checkcast 114	java/io/Closeable
    //   29: astore 4
    //   31: aload 4
    //   33: astore_3
    //   34: aload_0
    //   35: getfield 68	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   38: aload_1
    //   39: aload_2
    //   40: aload_0
    //   41: invokevirtual 117	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   44: invokevirtual 121	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   47: aload 4
    //   49: astore_3
    //   50: aload_0
    //   51: getfield 46	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   54: getstatic 462	com/fasterxml/jackson/databind/SerializationFeature:FLUSH_AFTER_WRITE_VALUE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   57: invokevirtual 156	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   60: ifeq +10 -> 70
    //   63: aload 4
    //   65: astore_3
    //   66: aload_1
    //   67: invokevirtual 465	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   70: aconst_null
    //   71: astore_3
    //   72: aload 4
    //   74: invokeinterface 127 1 0
    //   79: iconst_0
    //   80: ifeq +11 -> 91
    //   83: new 135	java/lang/NullPointerException
    //   86: dup
    //   87: invokespecial 136	java/lang/NullPointerException:<init>	()V
    //   90: athrow
    //   91: return
    //   92: astore_1
    //   93: aload_3
    //   94: ifnull +9 -> 103
    //   97: aload_3
    //   98: invokeinterface 127 1 0
    //   103: aload_1
    //   104: athrow
    //   105: aload_0
    //   106: getfield 68	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   109: aload_1
    //   110: aload_2
    //   111: aload_0
    //   112: invokevirtual 117	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   115: invokevirtual 121	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   118: aload_0
    //   119: getfield 46	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   122: getstatic 462	com/fasterxml/jackson/databind/SerializationFeature:FLUSH_AFTER_WRITE_VALUE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   125: invokevirtual 156	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   128: ifeq -37 -> 91
    //   131: aload_1
    //   132: invokevirtual 465	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   135: return
    //   136: astore_1
    //   137: return
    //   138: astore_2
    //   139: goto -36 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	ObjectWriter
    //   0	142	1	paramJsonGenerator	JsonGenerator
    //   0	142	2	paramObject	Object
    //   33	65	3	localCloseable1	Closeable
    //   29	44	4	localCloseable2	Closeable
    // Exception table:
    //   from	to	target	type
    //   34	47	92	finally
    //   50	63	92	finally
    //   66	70	92	finally
    //   72	79	92	finally
    //   83	91	136	java/io/IOException
    //   97	103	138	java/io/IOException
  }
  
  public void writeValue(File paramFile, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(OutputStream paramOutputStream, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(Writer paramWriter, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._generatorFactory.createGenerator(paramWriter), paramObject);
  }
  
  public byte[] writeValueAsBytes(Object paramObject)
    throws JsonProcessingException
  {
    ByteArrayBuilder localByteArrayBuilder = new ByteArrayBuilder(this._generatorFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(this._generatorFactory.createGenerator(localByteArrayBuilder, JsonEncoding.UTF8), paramObject);
      paramObject = localByteArrayBuilder.toByteArray();
      localByteArrayBuilder.release();
      return (byte[])paramObject;
    }
    catch (JsonProcessingException paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (IOException paramObject)
    {
      throw JsonMappingException.fromUnexpectedIOE((IOException)paramObject);
    }
  }
  
  public String writeValueAsString(Object paramObject)
    throws JsonProcessingException
  {
    SegmentedStringWriter localSegmentedStringWriter = new SegmentedStringWriter(this._generatorFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(this._generatorFactory.createGenerator(localSegmentedStringWriter), paramObject);
      return localSegmentedStringWriter.getAndClear();
    }
    catch (JsonProcessingException paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (IOException paramObject)
    {
      throw JsonMappingException.fromUnexpectedIOE((IOException)paramObject);
    }
  }
  
  public SequenceWriter writeValues(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    _configureGenerator(paramJsonGenerator);
    return _newSequenceWriter(false, paramJsonGenerator, false);
  }
  
  public SequenceWriter writeValues(File paramFile)
    throws IOException
  {
    return _newSequenceWriter(false, this._generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValues(OutputStream paramOutputStream)
    throws IOException
  {
    return _newSequenceWriter(false, this._generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValues(Writer paramWriter)
    throws IOException
  {
    return _newSequenceWriter(false, this._generatorFactory.createGenerator(paramWriter), true);
  }
  
  public SequenceWriter writeValuesAsArray(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    return _newSequenceWriter(true, paramJsonGenerator, false);
  }
  
  public SequenceWriter writeValuesAsArray(File paramFile)
    throws IOException
  {
    return _newSequenceWriter(true, this._generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValuesAsArray(OutputStream paramOutputStream)
    throws IOException
  {
    return _newSequenceWriter(true, this._generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValuesAsArray(Writer paramWriter)
    throws IOException
  {
    return _newSequenceWriter(true, this._generatorFactory.createGenerator(paramWriter), true);
  }
  
  public static final class GeneratorSettings
    implements Serializable
  {
    public static final GeneratorSettings empty = new GeneratorSettings(null, null, null, null);
    private static final long serialVersionUID = 1L;
    public final CharacterEscapes characterEscapes;
    public final PrettyPrinter prettyPrinter;
    public final SerializableString rootValueSeparator;
    public final FormatSchema schema;
    
    public GeneratorSettings(PrettyPrinter paramPrettyPrinter, FormatSchema paramFormatSchema, CharacterEscapes paramCharacterEscapes, SerializableString paramSerializableString)
    {
      this.prettyPrinter = paramPrettyPrinter;
      this.schema = paramFormatSchema;
      this.characterEscapes = paramCharacterEscapes;
      this.rootValueSeparator = paramSerializableString;
    }
    
    public void initialize(JsonGenerator paramJsonGenerator)
    {
      PrettyPrinter localPrettyPrinter2 = this.prettyPrinter;
      if (this.prettyPrinter != null)
      {
        if (localPrettyPrinter2 != ObjectWriter.NULL_PRETTY_PRINTER) {
          break label73;
        }
        paramJsonGenerator.setPrettyPrinter(null);
      }
      for (;;)
      {
        if (this.characterEscapes != null) {
          paramJsonGenerator.setCharacterEscapes(this.characterEscapes);
        }
        if (this.schema != null) {
          paramJsonGenerator.setSchema(this.schema);
        }
        if (this.rootValueSeparator != null) {
          paramJsonGenerator.setRootValueSeparator(this.rootValueSeparator);
        }
        return;
        label73:
        PrettyPrinter localPrettyPrinter1 = localPrettyPrinter2;
        if ((localPrettyPrinter2 instanceof Instantiatable)) {
          localPrettyPrinter1 = (PrettyPrinter)((Instantiatable)localPrettyPrinter2).createInstance();
        }
        paramJsonGenerator.setPrettyPrinter(localPrettyPrinter1);
      }
    }
    
    public GeneratorSettings with(FormatSchema paramFormatSchema)
    {
      if (this.schema == paramFormatSchema) {
        return this;
      }
      return new GeneratorSettings(this.prettyPrinter, paramFormatSchema, this.characterEscapes, this.rootValueSeparator);
    }
    
    public GeneratorSettings with(PrettyPrinter paramPrettyPrinter)
    {
      PrettyPrinter localPrettyPrinter = paramPrettyPrinter;
      if (paramPrettyPrinter == null) {
        localPrettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
      }
      if (localPrettyPrinter == this.prettyPrinter) {
        return this;
      }
      return new GeneratorSettings(localPrettyPrinter, this.schema, this.characterEscapes, this.rootValueSeparator);
    }
    
    public GeneratorSettings with(CharacterEscapes paramCharacterEscapes)
    {
      if (this.characterEscapes == paramCharacterEscapes) {
        return this;
      }
      return new GeneratorSettings(this.prettyPrinter, this.schema, paramCharacterEscapes, this.rootValueSeparator);
    }
    
    public GeneratorSettings withRootValueSeparator(SerializableString paramSerializableString)
    {
      if (paramSerializableString == null)
      {
        if (this.rootValueSeparator != null) {}
      }
      else {
        while ((this.rootValueSeparator != null) && (paramSerializableString.getValue().equals(this.rootValueSeparator.getValue()))) {
          return this;
        }
      }
      return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, paramSerializableString);
    }
    
    public GeneratorSettings withRootValueSeparator(String paramString)
    {
      if (paramString == null)
      {
        if (this.rootValueSeparator != null) {}
      }
      else {
        while (paramString.equals(this.rootValueSeparator)) {
          return this;
        }
      }
      PrettyPrinter localPrettyPrinter = this.prettyPrinter;
      FormatSchema localFormatSchema = this.schema;
      CharacterEscapes localCharacterEscapes = this.characterEscapes;
      if (paramString == null) {}
      for (paramString = null;; paramString = new SerializedString(paramString)) {
        return new GeneratorSettings(localPrettyPrinter, localFormatSchema, localCharacterEscapes, paramString);
      }
    }
  }
  
  public static final class Prefetch
    implements Serializable
  {
    public static final Prefetch empty = new Prefetch(null, null, null);
    private static final long serialVersionUID = 1L;
    private final JavaType rootType;
    private final TypeSerializer typeSerializer;
    private final JsonSerializer<Object> valueSerializer;
    
    private Prefetch(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, TypeSerializer paramTypeSerializer)
    {
      this.rootType = paramJavaType;
      this.valueSerializer = paramJsonSerializer;
      this.typeSerializer = paramTypeSerializer;
    }
    
    public Prefetch forRootType(ObjectWriter paramObjectWriter, JavaType paramJavaType)
    {
      int j = 1;
      int i = j;
      if (paramJavaType != null)
      {
        if (paramJavaType.isJavaLangObject()) {
          i = j;
        }
      }
      else
      {
        if (i == 0) {
          break label59;
        }
        if ((this.rootType != null) && (this.valueSerializer != null)) {
          break label45;
        }
      }
      label45:
      label59:
      while (paramJavaType.equals(this.rootType))
      {
        return this;
        i = 0;
        break;
        return new Prefetch(null, null, this.typeSerializer);
      }
      if (paramObjectWriter.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH))
      {
        paramObjectWriter = paramObjectWriter._serializerProvider();
        try
        {
          paramObjectWriter = paramObjectWriter.findTypedValueSerializer(paramJavaType, true, null);
          if ((paramObjectWriter instanceof TypeWrappedSerializer)) {
            return new Prefetch(paramJavaType, null, ((TypeWrappedSerializer)paramObjectWriter).typeSerializer());
          }
          paramObjectWriter = new Prefetch(paramJavaType, paramObjectWriter, null);
          return paramObjectWriter;
        }
        catch (JsonProcessingException paramObjectWriter) {}
      }
      return new Prefetch(null, null, this.typeSerializer);
    }
    
    public final TypeSerializer getTypeSerializer()
    {
      return this.typeSerializer;
    }
    
    public final JsonSerializer<Object> getValueSerializer()
    {
      return this.valueSerializer;
    }
    
    public boolean hasSerializer()
    {
      return (this.valueSerializer != null) || (this.typeSerializer != null);
    }
    
    public void serialize(JsonGenerator paramJsonGenerator, Object paramObject, DefaultSerializerProvider paramDefaultSerializerProvider)
      throws IOException
    {
      if (this.typeSerializer != null)
      {
        paramDefaultSerializerProvider.serializePolymorphic(paramJsonGenerator, paramObject, this.rootType, this.valueSerializer, this.typeSerializer);
        return;
      }
      if (this.valueSerializer != null)
      {
        paramDefaultSerializerProvider.serializeValue(paramJsonGenerator, paramObject, this.rootType, this.valueSerializer);
        return;
      }
      paramDefaultSerializerProvider.serializeValue(paramJsonGenerator, paramObject);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ObjectWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */