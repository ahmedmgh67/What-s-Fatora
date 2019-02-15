package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class SerializationConfig
  extends MapperConfigBase<SerializationFeature, SerializationConfig>
  implements Serializable
{
  protected static final JsonInclude.Value DEFAULT_INCLUSION = JsonInclude.Value.empty();
  protected static final PrettyPrinter DEFAULT_PRETTY_PRINTER = new DefaultPrettyPrinter();
  private static final long serialVersionUID = 1L;
  protected final PrettyPrinter _defaultPrettyPrinter;
  protected final FilterProvider _filterProvider;
  protected final int _formatWriteFeatures;
  protected final int _formatWriteFeaturesToChange;
  protected final int _generatorFeatures;
  protected final int _generatorFeaturesToChange;
  protected final int _serFeatures;
  protected final JsonInclude.Value _serializationInclusion;
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super(paramSerializationConfig, paramInt1);
    this._serFeatures = paramInt2;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramInt3;
    this._generatorFeaturesToChange = paramInt4;
    this._formatWriteFeatures = paramInt5;
    this._formatWriteFeaturesToChange = paramInt6;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, JsonInclude.Value paramValue)
  {
    super(paramSerializationConfig);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramValue;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, PrettyPrinter paramPrettyPrinter)
  {
    super(paramSerializationConfig);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, PropertyName paramPropertyName)
  {
    super(paramSerializationConfig, paramPropertyName);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, BaseSettings paramBaseSettings)
  {
    super(paramSerializationConfig, paramBaseSettings);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, ContextAttributes paramContextAttributes)
  {
    super(paramSerializationConfig, paramContextAttributes);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, SimpleMixInResolver paramSimpleMixInResolver)
  {
    super(paramSerializationConfig, paramSimpleMixInResolver);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, SimpleMixInResolver paramSimpleMixInResolver, RootNameLookup paramRootNameLookup)
  {
    super(paramSerializationConfig, paramSimpleMixInResolver, paramRootNameLookup);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, SubtypeResolver paramSubtypeResolver)
  {
    super(paramSerializationConfig, paramSubtypeResolver);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, FilterProvider paramFilterProvider)
  {
    super(paramSerializationConfig);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramFilterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, Class<?> paramClass)
  {
    super(paramSerializationConfig, paramClass);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
    this._formatWriteFeatures = paramSerializationConfig._formatWriteFeatures;
    this._formatWriteFeaturesToChange = paramSerializationConfig._formatWriteFeaturesToChange;
  }
  
  public SerializationConfig(BaseSettings paramBaseSettings, SubtypeResolver paramSubtypeResolver, SimpleMixInResolver paramSimpleMixInResolver, RootNameLookup paramRootNameLookup)
  {
    super(paramBaseSettings, paramSubtypeResolver, paramSimpleMixInResolver, paramRootNameLookup);
    this._serFeatures = collectFeatureDefaults(SerializationFeature.class);
    this._filterProvider = null;
    this._defaultPrettyPrinter = DEFAULT_PRETTY_PRINTER;
    this._generatorFeatures = 0;
    this._generatorFeaturesToChange = 0;
    this._formatWriteFeatures = 0;
    this._formatWriteFeaturesToChange = 0;
    this._serializationInclusion = DEFAULT_INCLUSION;
  }
  
  private final SerializationConfig _withBase(BaseSettings paramBaseSettings)
  {
    if (this._base == paramBaseSettings) {
      return this;
    }
    return new SerializationConfig(this, paramBaseSettings);
  }
  
  public PrettyPrinter constructDefaultPrettyPrinter()
  {
    PrettyPrinter localPrettyPrinter2 = this._defaultPrettyPrinter;
    PrettyPrinter localPrettyPrinter1 = localPrettyPrinter2;
    if ((localPrettyPrinter2 instanceof Instantiatable)) {
      localPrettyPrinter1 = (PrettyPrinter)((Instantiatable)localPrettyPrinter2).createInstance();
    }
    return localPrettyPrinter1;
  }
  
  public AnnotationIntrospector getAnnotationIntrospector()
  {
    if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
      return super.getAnnotationIntrospector();
    }
    return AnnotationIntrospector.nopInstance();
  }
  
  public PrettyPrinter getDefaultPrettyPrinter()
  {
    return this._defaultPrettyPrinter;
  }
  
  public JsonFormat.Value getDefaultPropertyFormat(Class<?> paramClass)
  {
    return EMPTY_FORMAT;
  }
  
  public JsonInclude.Value getDefaultPropertyInclusion()
  {
    return this._serializationInclusion;
  }
  
  public JsonInclude.Value getDefaultPropertyInclusion(Class<?> paramClass)
  {
    return this._serializationInclusion;
  }
  
  public VisibilityChecker<?> getDefaultVisibilityChecker()
  {
    Object localObject2 = super.getDefaultVisibilityChecker();
    Object localObject1 = localObject2;
    if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
      localObject1 = ((VisibilityChecker)localObject2).withGetterVisibility(JsonAutoDetect.Visibility.NONE);
    }
    localObject2 = localObject1;
    if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
      localObject2 = ((VisibilityChecker)localObject1).withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
    }
    localObject1 = localObject2;
    if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
      localObject1 = ((VisibilityChecker)localObject2).withFieldVisibility(JsonAutoDetect.Visibility.NONE);
    }
    return (VisibilityChecker<?>)localObject1;
  }
  
  public FilterProvider getFilterProvider()
  {
    return this._filterProvider;
  }
  
  public final int getSerializationFeatures()
  {
    return this._serFeatures;
  }
  
  @Deprecated
  public JsonInclude.Include getSerializationInclusion()
  {
    JsonInclude.Include localInclude2 = this._serializationInclusion.getValueInclusion();
    JsonInclude.Include localInclude1 = localInclude2;
    if (localInclude2 == JsonInclude.Include.USE_DEFAULTS) {
      localInclude1 = JsonInclude.Include.ALWAYS;
    }
    return localInclude1;
  }
  
  public final boolean hasSerializationFeatures(int paramInt)
  {
    return (this._serFeatures & paramInt) == paramInt;
  }
  
  public void initialize(JsonGenerator paramJsonGenerator)
  {
    if ((SerializationFeature.INDENT_OUTPUT.enabledIn(this._serFeatures)) && (paramJsonGenerator.getPrettyPrinter() == null))
    {
      PrettyPrinter localPrettyPrinter = constructDefaultPrettyPrinter();
      if (localPrettyPrinter != null) {
        paramJsonGenerator.setPrettyPrinter(localPrettyPrinter);
      }
    }
    boolean bool = SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this._serFeatures);
    int k = this._generatorFeaturesToChange;
    if ((k != 0) || (bool))
    {
      int m = this._generatorFeatures;
      int j = k;
      int i = m;
      if (bool)
      {
        j = JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.getMask();
        i = m | j;
        j = k | j;
      }
      paramJsonGenerator.overrideStdFeatures(i, j);
    }
    if (this._formatWriteFeaturesToChange != 0) {
      paramJsonGenerator.overrideFormatFeatures(this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }
  }
  
  public <T extends BeanDescription> T introspect(JavaType paramJavaType)
  {
    return getClassIntrospector().forSerialization(this, paramJavaType, this);
  }
  
  public BeanDescription introspectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forDirectClassAnnotations(this, paramJavaType, this);
  }
  
  public final boolean isEnabled(JsonGenerator.Feature paramFeature, JsonFactory paramJsonFactory)
  {
    int i = paramFeature.getMask();
    if ((this._generatorFeaturesToChange & i) != 0) {
      return (this._generatorFeatures & paramFeature.getMask()) != 0;
    }
    return paramJsonFactory.isEnabled(paramFeature);
  }
  
  public final boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return (this._serFeatures & paramSerializationFeature.getMask()) != 0;
  }
  
  public String toString()
  {
    return "[SerializationConfig: flags=0x" + Integer.toHexString(this._serFeatures) + "]";
  }
  
  public boolean useRootWrapping()
  {
    if (this._rootName != null) {
      return !this._rootName.isEmpty();
    }
    return isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
  }
  
  public SerializationConfig with(Base64Variant paramBase64Variant)
  {
    return _withBase(this._base.with(paramBase64Variant));
  }
  
  public SerializationConfig with(FormatFeature paramFormatFeature)
  {
    int i = this._formatWriteFeatures | paramFormatFeature.getMask();
    int j = this._formatWriteFeaturesToChange | paramFormatFeature.getMask();
    if ((this._formatWriteFeatures == i) && (this._formatWriteFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, i, j);
  }
  
  public SerializationConfig with(JsonGenerator.Feature paramFeature)
  {
    int i = this._generatorFeatures | paramFeature.getMask();
    int j = this._generatorFeaturesToChange | paramFeature.getMask();
    if ((this._generatorFeatures == i) && (this._generatorFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i, j, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig with(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig with(MapperFeature paramMapperFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = this._mapperFeatures | paramMapperFeature.getMask(); i == this._mapperFeatures; i = this._mapperFeatures & (paramMapperFeature.getMask() ^ 0xFFFFFFFF)) {
      return this;
    }
    return new SerializationConfig(this, i, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig with(PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    return _withBase(this._base.withPropertyNamingStrategy(paramPropertyNamingStrategy));
  }
  
  public SerializationConfig with(SerializationFeature paramSerializationFeature)
  {
    int i = this._serFeatures | paramSerializationFeature.getMask();
    if (i == this._serFeatures) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, i, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig with(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    int j = this._serFeatures | paramSerializationFeature.getMask();
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j |= paramVarArgs[i].getMask();
      i += 1;
    }
    if (j == this._serFeatures) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, j, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig with(ContextAttributes paramContextAttributes)
  {
    if (paramContextAttributes == this._attributes) {
      return this;
    }
    return new SerializationConfig(this, paramContextAttributes);
  }
  
  public SerializationConfig with(HandlerInstantiator paramHandlerInstantiator)
  {
    return _withBase(this._base.withHandlerInstantiator(paramHandlerInstantiator));
  }
  
  public SerializationConfig with(ClassIntrospector paramClassIntrospector)
  {
    return _withBase(this._base.withClassIntrospector(paramClassIntrospector));
  }
  
  public SerializationConfig with(VisibilityChecker<?> paramVisibilityChecker)
  {
    return _withBase(this._base.withVisibilityChecker(paramVisibilityChecker));
  }
  
  public SerializationConfig with(SubtypeResolver paramSubtypeResolver)
  {
    if (paramSubtypeResolver == this._subtypeResolver) {
      return this;
    }
    return new SerializationConfig(this, paramSubtypeResolver);
  }
  
  public SerializationConfig with(TypeResolverBuilder<?> paramTypeResolverBuilder)
  {
    return _withBase(this._base.withTypeResolverBuilder(paramTypeResolverBuilder));
  }
  
  public SerializationConfig with(TypeFactory paramTypeFactory)
  {
    return _withBase(this._base.withTypeFactory(paramTypeFactory));
  }
  
  public SerializationConfig with(DateFormat paramDateFormat)
  {
    SerializationConfig localSerializationConfig = new SerializationConfig(this, this._base.withDateFormat(paramDateFormat));
    if (paramDateFormat == null) {
      return localSerializationConfig.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    return localSerializationConfig.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }
  
  public SerializationConfig with(Locale paramLocale)
  {
    return _withBase(this._base.with(paramLocale));
  }
  
  public SerializationConfig with(TimeZone paramTimeZone)
  {
    return _withBase(this._base.with(paramTimeZone));
  }
  
  public SerializationConfig with(MapperFeature... paramVarArgs)
  {
    int j = this._mapperFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j |= paramVarArgs[i].getMask();
      i += 1;
    }
    if (j == this._mapperFeatures) {
      return this;
    }
    return new SerializationConfig(this, j, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withAppendedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withDefaultPrettyPrinter(PrettyPrinter paramPrettyPrinter)
  {
    if (this._defaultPrettyPrinter == paramPrettyPrinter) {
      return this;
    }
    return new SerializationConfig(this, paramPrettyPrinter);
  }
  
  public SerializationConfig withFeatures(FormatFeature... paramVarArgs)
  {
    int k = this._formatWriteFeatures;
    int j = this._formatWriteFeaturesToChange;
    int m = paramVarArgs.length;
    int i = 0;
    while (i < m)
    {
      int n = paramVarArgs[i].getMask();
      k |= n;
      j |= n;
      i += 1;
    }
    if ((this._formatWriteFeatures == k) && (this._formatWriteFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, k, j);
  }
  
  public SerializationConfig withFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    int k = this._generatorFeatures;
    int j = this._generatorFeaturesToChange;
    int m = paramVarArgs.length;
    int i = 0;
    while (i < m)
    {
      int n = paramVarArgs[i].getMask();
      k |= n;
      j |= n;
      i += 1;
    }
    if ((this._generatorFeatures == k) && (this._generatorFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, k, j, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig withFeatures(SerializationFeature... paramVarArgs)
  {
    int j = this._serFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j |= paramVarArgs[i].getMask();
      i += 1;
    }
    if (j == this._serFeatures) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, j, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig withFilters(FilterProvider paramFilterProvider)
  {
    if (paramFilterProvider == this._filterProvider) {
      return this;
    }
    return new SerializationConfig(this, paramFilterProvider);
  }
  
  public SerializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withInsertedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withPropertyInclusion(JsonInclude.Value paramValue)
  {
    if (this._serializationInclusion.equals(paramValue)) {
      return this;
    }
    return new SerializationConfig(this, paramValue);
  }
  
  public SerializationConfig withRootName(PropertyName paramPropertyName)
  {
    if (paramPropertyName == null)
    {
      if (this._rootName != null) {}
    }
    else {
      while (paramPropertyName.equals(this._rootName)) {
        return this;
      }
    }
    return new SerializationConfig(this, paramPropertyName);
  }
  
  @Deprecated
  public SerializationConfig withSerializationInclusion(JsonInclude.Include paramInclude)
  {
    return withPropertyInclusion(DEFAULT_INCLUSION.withValueInclusion(paramInclude));
  }
  
  public SerializationConfig withView(Class<?> paramClass)
  {
    if (this._view == paramClass) {
      return this;
    }
    return new SerializationConfig(this, paramClass);
  }
  
  public SerializationConfig withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility)
  {
    return _withBase(this._base.withVisibility(paramPropertyAccessor, paramVisibility));
  }
  
  public SerializationConfig without(FormatFeature paramFormatFeature)
  {
    int i = this._formatWriteFeatures & (paramFormatFeature.getMask() ^ 0xFFFFFFFF);
    int j = this._formatWriteFeaturesToChange | paramFormatFeature.getMask();
    if ((this._formatWriteFeatures == i) && (this._formatWriteFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, i, j);
  }
  
  public SerializationConfig without(JsonGenerator.Feature paramFeature)
  {
    int i = this._generatorFeatures & (paramFeature.getMask() ^ 0xFFFFFFFF);
    int j = this._generatorFeaturesToChange | paramFeature.getMask();
    if ((this._generatorFeatures == i) && (this._generatorFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i, j, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig without(SerializationFeature paramSerializationFeature)
  {
    int i = this._serFeatures & (paramSerializationFeature.getMask() ^ 0xFFFFFFFF);
    if (i == this._serFeatures) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, i, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig without(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    int j = this._serFeatures & (paramSerializationFeature.getMask() ^ 0xFFFFFFFF);
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j &= (paramVarArgs[i].getMask() ^ 0xFFFFFFFF);
      i += 1;
    }
    if (j == this._serFeatures) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, j, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig without(MapperFeature... paramVarArgs)
  {
    int j = this._mapperFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j &= (paramVarArgs[i].getMask() ^ 0xFFFFFFFF);
      i += 1;
    }
    if (j == this._mapperFeatures) {
      return this;
    }
    return new SerializationConfig(this, j, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig withoutFeatures(FormatFeature... paramVarArgs)
  {
    int k = this._formatWriteFeatures;
    int j = this._formatWriteFeaturesToChange;
    int m = paramVarArgs.length;
    int i = 0;
    while (i < m)
    {
      int n = paramVarArgs[i].getMask();
      k &= (n ^ 0xFFFFFFFF);
      j |= n;
      i += 1;
    }
    if ((this._formatWriteFeatures == k) && (this._formatWriteFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, k, j);
  }
  
  public SerializationConfig withoutFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    int k = this._generatorFeatures;
    int j = this._generatorFeaturesToChange;
    int m = paramVarArgs.length;
    int i = 0;
    while (i < m)
    {
      int n = paramVarArgs[i].getMask();
      k &= (n ^ 0xFFFFFFFF);
      j |= n;
      i += 1;
    }
    if ((this._generatorFeatures == k) && (this._generatorFeaturesToChange == j)) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, k, j, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
  
  public SerializationConfig withoutFeatures(SerializationFeature... paramVarArgs)
  {
    int j = this._serFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j &= (paramVarArgs[i].getMask() ^ 0xFFFFFFFF);
      i += 1;
    }
    if (j == this._serFeatures) {
      return this;
    }
    return new SerializationConfig(this, this._mapperFeatures, j, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\SerializationConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */