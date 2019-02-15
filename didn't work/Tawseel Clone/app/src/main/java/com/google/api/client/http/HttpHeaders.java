package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Base64;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.GenericData.Flags;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpHeaders
  extends GenericData
{
  @Key("Accept")
  private List<String> accept;
  @Key("Accept-Encoding")
  private List<String> acceptEncoding = new ArrayList(Collections.singleton("gzip"));
  @Key("Age")
  private List<Long> age;
  @Key("WWW-Authenticate")
  private List<String> authenticate;
  @Key("Authorization")
  private List<String> authorization;
  @Key("Cache-Control")
  private List<String> cacheControl;
  @Key("Content-Encoding")
  private List<String> contentEncoding;
  @Key("Content-Length")
  private List<Long> contentLength;
  @Key("Content-MD5")
  private List<String> contentMD5;
  @Key("Content-Range")
  private List<String> contentRange;
  @Key("Content-Type")
  private List<String> contentType;
  @Key("Cookie")
  private List<String> cookie;
  @Key("Date")
  private List<String> date;
  @Key("ETag")
  private List<String> etag;
  @Key("Expires")
  private List<String> expires;
  @Key("If-Match")
  private List<String> ifMatch;
  @Key("If-Modified-Since")
  private List<String> ifModifiedSince;
  @Key("If-None-Match")
  private List<String> ifNoneMatch;
  @Key("If-Range")
  private List<String> ifRange;
  @Key("If-Unmodified-Since")
  private List<String> ifUnmodifiedSince;
  @Key("Last-Modified")
  private List<String> lastModified;
  @Key("Location")
  private List<String> location;
  @Key("MIME-Version")
  private List<String> mimeVersion;
  @Key("Range")
  private List<String> range;
  @Key("Retry-After")
  private List<String> retryAfter;
  @Key("User-Agent")
  private List<String> userAgent;
  
  public HttpHeaders()
  {
    super(EnumSet.of(GenericData.Flags.IGNORE_CASE));
  }
  
  private static void addHeader(Logger paramLogger, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, LowLevelHttpRequest paramLowLevelHttpRequest, String paramString, Object paramObject, Writer paramWriter)
    throws IOException
  {
    if ((paramObject == null) || (Data.isNull(paramObject))) {}
    String str;
    do
    {
      return;
      str = toStringValue(paramObject);
      paramObject = str;
      Object localObject;
      if (!"Authorization".equalsIgnoreCase(paramString))
      {
        localObject = paramObject;
        if (!"Cookie".equalsIgnoreCase(paramString)) {}
      }
      else if (paramLogger != null)
      {
        localObject = paramObject;
        if (paramLogger.isLoggable(Level.ALL)) {}
      }
      else
      {
        localObject = "<Not Logged>";
      }
      if (paramStringBuilder1 != null)
      {
        paramStringBuilder1.append(paramString).append(": ");
        paramStringBuilder1.append((String)localObject);
        paramStringBuilder1.append(StringUtils.LINE_SEPARATOR);
      }
      if (paramStringBuilder2 != null) {
        paramStringBuilder2.append(" -H '").append(paramString).append(": ").append((String)localObject).append("'");
      }
      if (paramLowLevelHttpRequest != null) {
        paramLowLevelHttpRequest.addHeader(paramString, str);
      }
    } while (paramWriter == null);
    paramWriter.write(paramString);
    paramWriter.write(": ");
    paramWriter.write(str);
    paramWriter.write("\r\n");
  }
  
  private <T> List<T> getAsList(T paramT)
  {
    if (paramT == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramT);
    return localArrayList;
  }
  
  private <T> T getFirstHeaderValue(List<T> paramList)
  {
    if (paramList == null) {
      return null;
    }
    return (T)paramList.get(0);
  }
  
  private static Object parseValue(Type paramType, List<Type> paramList, String paramString)
  {
    return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(paramList, paramType), paramString);
  }
  
  static void serializeHeaders(HttpHeaders paramHttpHeaders, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, Logger paramLogger, LowLevelHttpRequest paramLowLevelHttpRequest)
    throws IOException
  {
    serializeHeaders(paramHttpHeaders, paramStringBuilder1, paramStringBuilder2, paramLogger, paramLowLevelHttpRequest, null);
  }
  
  static void serializeHeaders(HttpHeaders paramHttpHeaders, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, Logger paramLogger, LowLevelHttpRequest paramLowLevelHttpRequest, Writer paramWriter)
    throws IOException
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramHttpHeaders.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (Map.Entry)localIterator.next();
      Object localObject2 = (String)((Map.Entry)localObject1).getKey();
      Preconditions.checkArgument(localHashSet.add(localObject2), "multiple headers of the same name (headers are case insensitive): %s", new Object[] { localObject2 });
      Object localObject3 = ((Map.Entry)localObject1).getValue();
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        localObject2 = paramHttpHeaders.getClassInfo().getFieldInfo((String)localObject2);
        if (localObject2 != null) {
          localObject1 = ((FieldInfo)localObject2).getName();
        }
        localObject2 = localObject3.getClass();
        if (((localObject3 instanceof Iterable)) || (((Class)localObject2).isArray()))
        {
          localObject2 = Types.iterableOf(localObject3).iterator();
          while (((Iterator)localObject2).hasNext()) {
            addHeader(paramLogger, paramStringBuilder1, paramStringBuilder2, paramLowLevelHttpRequest, (String)localObject1, ((Iterator)localObject2).next(), paramWriter);
          }
        }
        else
        {
          addHeader(paramLogger, paramStringBuilder1, paramStringBuilder2, paramLowLevelHttpRequest, (String)localObject1, localObject3, paramWriter);
        }
      }
    }
    if (paramWriter != null) {
      paramWriter.flush();
    }
  }
  
  public static void serializeHeadersForMultipartRequests(HttpHeaders paramHttpHeaders, StringBuilder paramStringBuilder, Logger paramLogger, Writer paramWriter)
    throws IOException
  {
    serializeHeaders(paramHttpHeaders, paramStringBuilder, null, paramLogger, null, paramWriter);
  }
  
  private static String toStringValue(Object paramObject)
  {
    if ((paramObject instanceof Enum)) {
      return FieldInfo.of((Enum)paramObject).getName();
    }
    return paramObject.toString();
  }
  
  public HttpHeaders clone()
  {
    return (HttpHeaders)super.clone();
  }
  
  public final void fromHttpHeaders(HttpHeaders paramHttpHeaders)
  {
    try
    {
      ParseHeaderState localParseHeaderState = new ParseHeaderState(this, null);
      serializeHeaders(paramHttpHeaders, null, null, null, new HeaderParsingFakeLevelHttpRequest(this, localParseHeaderState));
      localParseHeaderState.finish();
      return;
    }
    catch (IOException paramHttpHeaders)
    {
      throw Throwables.propagate(paramHttpHeaders);
    }
  }
  
  public final void fromHttpResponse(LowLevelHttpResponse paramLowLevelHttpResponse, StringBuilder paramStringBuilder)
    throws IOException
  {
    clear();
    paramStringBuilder = new ParseHeaderState(this, paramStringBuilder);
    int j = paramLowLevelHttpResponse.getHeaderCount();
    int i = 0;
    while (i < j)
    {
      parseHeader(paramLowLevelHttpResponse.getHeaderName(i), paramLowLevelHttpResponse.getHeaderValue(i), paramStringBuilder);
      i += 1;
    }
    paramStringBuilder.finish();
  }
  
  public final String getAccept()
  {
    return (String)getFirstHeaderValue(this.accept);
  }
  
  public final String getAcceptEncoding()
  {
    return (String)getFirstHeaderValue(this.acceptEncoding);
  }
  
  public final Long getAge()
  {
    return (Long)getFirstHeaderValue(this.age);
  }
  
  public final String getAuthenticate()
  {
    return (String)getFirstHeaderValue(this.authenticate);
  }
  
  public final List<String> getAuthenticateAsList()
  {
    return this.authenticate;
  }
  
  public final String getAuthorization()
  {
    return (String)getFirstHeaderValue(this.authorization);
  }
  
  public final List<String> getAuthorizationAsList()
  {
    return this.authorization;
  }
  
  public final String getCacheControl()
  {
    return (String)getFirstHeaderValue(this.cacheControl);
  }
  
  public final String getContentEncoding()
  {
    return (String)getFirstHeaderValue(this.contentEncoding);
  }
  
  public final Long getContentLength()
  {
    return (Long)getFirstHeaderValue(this.contentLength);
  }
  
  public final String getContentMD5()
  {
    return (String)getFirstHeaderValue(this.contentMD5);
  }
  
  public final String getContentRange()
  {
    return (String)getFirstHeaderValue(this.contentRange);
  }
  
  public final String getContentType()
  {
    return (String)getFirstHeaderValue(this.contentType);
  }
  
  public final String getCookie()
  {
    return (String)getFirstHeaderValue(this.cookie);
  }
  
  public final String getDate()
  {
    return (String)getFirstHeaderValue(this.date);
  }
  
  public final String getETag()
  {
    return (String)getFirstHeaderValue(this.etag);
  }
  
  public final String getExpires()
  {
    return (String)getFirstHeaderValue(this.expires);
  }
  
  public String getFirstHeaderStringValue(String paramString)
  {
    paramString = get(paramString.toLowerCase());
    if (paramString == null) {
      return null;
    }
    Object localObject = paramString.getClass();
    if (((paramString instanceof Iterable)) || (((Class)localObject).isArray()))
    {
      localObject = Types.iterableOf(paramString).iterator();
      if (((Iterator)localObject).hasNext()) {
        return toStringValue(((Iterator)localObject).next());
      }
    }
    return toStringValue(paramString);
  }
  
  public List<String> getHeaderStringValues(String paramString)
  {
    paramString = get(paramString.toLowerCase());
    if (paramString == null) {
      return Collections.emptyList();
    }
    Object localObject = paramString.getClass();
    if (((paramString instanceof Iterable)) || (((Class)localObject).isArray()))
    {
      localObject = new ArrayList();
      paramString = Types.iterableOf(paramString).iterator();
      while (paramString.hasNext()) {
        ((List)localObject).add(toStringValue(paramString.next()));
      }
      return Collections.unmodifiableList((List)localObject);
    }
    return Collections.singletonList(toStringValue(paramString));
  }
  
  public final String getIfMatch()
  {
    return (String)getFirstHeaderValue(this.ifMatch);
  }
  
  public final String getIfModifiedSince()
  {
    return (String)getFirstHeaderValue(this.ifModifiedSince);
  }
  
  public final String getIfNoneMatch()
  {
    return (String)getFirstHeaderValue(this.ifNoneMatch);
  }
  
  public final String getIfRange()
  {
    return (String)getFirstHeaderValue(this.ifRange);
  }
  
  public final String getIfUnmodifiedSince()
  {
    return (String)getFirstHeaderValue(this.ifUnmodifiedSince);
  }
  
  public final String getLastModified()
  {
    return (String)getFirstHeaderValue(this.lastModified);
  }
  
  public final String getLocation()
  {
    return (String)getFirstHeaderValue(this.location);
  }
  
  public final String getMimeVersion()
  {
    return (String)getFirstHeaderValue(this.mimeVersion);
  }
  
  public final String getRange()
  {
    return (String)getFirstHeaderValue(this.range);
  }
  
  public final String getRetryAfter()
  {
    return (String)getFirstHeaderValue(this.retryAfter);
  }
  
  public final String getUserAgent()
  {
    return (String)getFirstHeaderValue(this.userAgent);
  }
  
  void parseHeader(String paramString1, String paramString2, ParseHeaderState paramParseHeaderState)
  {
    Object localObject1 = paramParseHeaderState.context;
    Object localObject2 = paramParseHeaderState.classInfo;
    ArrayValueMap localArrayValueMap = paramParseHeaderState.arrayValueMap;
    paramParseHeaderState = paramParseHeaderState.logger;
    if (paramParseHeaderState != null) {
      paramParseHeaderState.append(paramString1 + ": " + paramString2).append(StringUtils.LINE_SEPARATOR);
    }
    localObject2 = ((ClassInfo)localObject2).getFieldInfo(paramString1);
    if (localObject2 != null)
    {
      Type localType = Data.resolveWildcardTypeOrTypeVariable((List)localObject1, ((FieldInfo)localObject2).getGenericType());
      if (Types.isArray(localType))
      {
        paramString1 = Types.getRawArrayComponentType((List)localObject1, Types.getArrayComponentType(localType));
        localArrayValueMap.put(((FieldInfo)localObject2).getField(), paramString1, parseValue(paramString1, (List)localObject1, paramString2));
        return;
      }
      if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType((List)localObject1, localType), Iterable.class))
      {
        paramParseHeaderState = (Collection)((FieldInfo)localObject2).getValue(this);
        paramString1 = paramParseHeaderState;
        if (paramParseHeaderState == null)
        {
          paramString1 = Data.newCollectionInstance(localType);
          ((FieldInfo)localObject2).setValue(this, paramString1);
        }
        if (localType == Object.class) {}
        for (paramParseHeaderState = null;; paramParseHeaderState = Types.getIterableParameter(localType))
        {
          paramString1.add(parseValue(paramParseHeaderState, (List)localObject1, paramString2));
          return;
        }
      }
      ((FieldInfo)localObject2).setValue(this, parseValue(localType, (List)localObject1, paramString2));
      return;
    }
    localObject1 = (ArrayList)get(paramString1);
    paramParseHeaderState = (ParseHeaderState)localObject1;
    if (localObject1 == null)
    {
      paramParseHeaderState = new ArrayList();
      set(paramString1, paramParseHeaderState);
    }
    paramParseHeaderState.add(paramString2);
  }
  
  public HttpHeaders set(String paramString, Object paramObject)
  {
    return (HttpHeaders)super.set(paramString, paramObject);
  }
  
  public HttpHeaders setAccept(String paramString)
  {
    this.accept = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setAcceptEncoding(String paramString)
  {
    this.acceptEncoding = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setAge(Long paramLong)
  {
    this.age = getAsList(paramLong);
    return this;
  }
  
  public HttpHeaders setAuthenticate(String paramString)
  {
    this.authenticate = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setAuthorization(String paramString)
  {
    return setAuthorization(getAsList(paramString));
  }
  
  public HttpHeaders setAuthorization(List<String> paramList)
  {
    this.authorization = paramList;
    return this;
  }
  
  public HttpHeaders setBasicAuthentication(String paramString1, String paramString2)
  {
    paramString1 = Base64.encodeBase64String(StringUtils.getBytesUtf8((String)Preconditions.checkNotNull(paramString1) + ":" + (String)Preconditions.checkNotNull(paramString2)));
    return setAuthorization("Basic " + paramString1);
  }
  
  public HttpHeaders setCacheControl(String paramString)
  {
    this.cacheControl = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setContentEncoding(String paramString)
  {
    this.contentEncoding = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setContentLength(Long paramLong)
  {
    this.contentLength = getAsList(paramLong);
    return this;
  }
  
  public HttpHeaders setContentMD5(String paramString)
  {
    this.contentMD5 = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setContentRange(String paramString)
  {
    this.contentRange = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setContentType(String paramString)
  {
    this.contentType = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setCookie(String paramString)
  {
    this.cookie = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setDate(String paramString)
  {
    this.date = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setETag(String paramString)
  {
    this.etag = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setExpires(String paramString)
  {
    this.expires = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setIfMatch(String paramString)
  {
    this.ifMatch = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setIfModifiedSince(String paramString)
  {
    this.ifModifiedSince = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setIfNoneMatch(String paramString)
  {
    this.ifNoneMatch = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setIfRange(String paramString)
  {
    this.ifRange = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setIfUnmodifiedSince(String paramString)
  {
    this.ifUnmodifiedSince = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setLastModified(String paramString)
  {
    this.lastModified = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setLocation(String paramString)
  {
    this.location = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setMimeVersion(String paramString)
  {
    this.mimeVersion = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setRange(String paramString)
  {
    this.range = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setRetryAfter(String paramString)
  {
    this.retryAfter = getAsList(paramString);
    return this;
  }
  
  public HttpHeaders setUserAgent(String paramString)
  {
    this.userAgent = getAsList(paramString);
    return this;
  }
  
  private static class HeaderParsingFakeLevelHttpRequest
    extends LowLevelHttpRequest
  {
    private final HttpHeaders.ParseHeaderState state;
    private final HttpHeaders target;
    
    HeaderParsingFakeLevelHttpRequest(HttpHeaders paramHttpHeaders, HttpHeaders.ParseHeaderState paramParseHeaderState)
    {
      this.target = paramHttpHeaders;
      this.state = paramParseHeaderState;
    }
    
    public void addHeader(String paramString1, String paramString2)
    {
      this.target.parseHeader(paramString1, paramString2, this.state);
    }
    
    public LowLevelHttpResponse execute()
      throws IOException
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static final class ParseHeaderState
  {
    final ArrayValueMap arrayValueMap;
    final ClassInfo classInfo;
    final List<Type> context;
    final StringBuilder logger;
    
    public ParseHeaderState(HttpHeaders paramHttpHeaders, StringBuilder paramStringBuilder)
    {
      Class localClass = paramHttpHeaders.getClass();
      this.context = Arrays.asList(new Type[] { localClass });
      this.classInfo = ClassInfo.of(localClass, true);
      this.logger = paramStringBuilder;
      this.arrayValueMap = new ArrayValueMap(paramHttpHeaders);
    }
    
    void finish()
    {
      this.arrayValueMap.setValues();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpHeaders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */