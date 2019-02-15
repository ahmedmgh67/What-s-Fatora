package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpMediaType
{
  private static final Pattern FULL_MEDIA_TYPE_REGEX;
  private static final Pattern PARAMETER_REGEX;
  private static final Pattern TOKEN_REGEX;
  private static final Pattern TYPE_REGEX = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
  private String cachedBuildResult;
  private final SortedMap<String, String> parameters = new TreeMap();
  private String subType = "octet-stream";
  private String type = "application";
  
  static
  {
    TOKEN_REGEX = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    FULL_MEDIA_TYPE_REGEX = Pattern.compile("\\s*(" + "[^\\s/=;\"]+" + ")/(" + "[^\\s/=;\"]+" + ")" + "\\s*(" + ";.*" + ")?", 32);
    String str = "\"([^\"]*)\"" + "|" + "[^\\s;\"]*";
    PARAMETER_REGEX = Pattern.compile("\\s*;\\s*(" + "[^\\s/=;\"]+" + ")" + "=(" + str + ")");
  }
  
  public HttpMediaType(String paramString)
  {
    fromString(paramString);
  }
  
  public HttpMediaType(String paramString1, String paramString2)
  {
    setType(paramString1);
    setSubType(paramString2);
  }
  
  public static boolean equalsIgnoreParameters(String paramString1, String paramString2)
  {
    return ((paramString1 == null) && (paramString2 == null)) || ((paramString1 != null) && (paramString2 != null) && (new HttpMediaType(paramString1).equalsIgnoreParameters(new HttpMediaType(paramString2))));
  }
  
  private HttpMediaType fromString(String paramString)
  {
    paramString = FULL_MEDIA_TYPE_REGEX.matcher(paramString);
    Preconditions.checkArgument(paramString.matches(), "Type must be in the 'maintype/subtype; parameter=value' format");
    setType(paramString.group(1));
    setSubType(paramString.group(2));
    paramString = paramString.group(3);
    if (paramString != null)
    {
      Matcher localMatcher = PARAMETER_REGEX.matcher(paramString);
      while (localMatcher.find())
      {
        String str2 = localMatcher.group(1);
        String str1 = localMatcher.group(3);
        paramString = str1;
        if (str1 == null) {
          paramString = localMatcher.group(2);
        }
        setParameter(str2, paramString);
      }
    }
    return this;
  }
  
  static boolean matchesToken(String paramString)
  {
    return TOKEN_REGEX.matcher(paramString).matches();
  }
  
  private static String quoteString(String paramString)
  {
    paramString = paramString.replace("\\", "\\\\").replace("\"", "\\\"");
    return "\"" + paramString + "\"";
  }
  
  public String build()
  {
    if (this.cachedBuildResult != null) {
      return this.cachedBuildResult;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.type);
    localStringBuilder.append('/');
    localStringBuilder.append(this.subType);
    if (this.parameters != null)
    {
      Iterator localIterator = this.parameters.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (Map.Entry)localIterator.next();
        String str = (String)((Map.Entry)localObject).getValue();
        localStringBuilder.append("; ");
        localStringBuilder.append((String)((Map.Entry)localObject).getKey());
        localStringBuilder.append("=");
        localObject = str;
        if (!matchesToken(str)) {
          localObject = quoteString(str);
        }
        localStringBuilder.append((String)localObject);
      }
    }
    this.cachedBuildResult = localStringBuilder.toString();
    return this.cachedBuildResult;
  }
  
  public void clearParameters()
  {
    this.cachedBuildResult = null;
    this.parameters.clear();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof HttpMediaType)) {}
    do
    {
      return false;
      paramObject = (HttpMediaType)paramObject;
    } while ((!equalsIgnoreParameters((HttpMediaType)paramObject)) || (!this.parameters.equals(((HttpMediaType)paramObject).parameters)));
    return true;
  }
  
  public boolean equalsIgnoreParameters(HttpMediaType paramHttpMediaType)
  {
    return (paramHttpMediaType != null) && (getType().equalsIgnoreCase(paramHttpMediaType.getType())) && (getSubType().equalsIgnoreCase(paramHttpMediaType.getSubType()));
  }
  
  public Charset getCharsetParameter()
  {
    String str = getParameter("charset");
    if (str == null) {
      return null;
    }
    return Charset.forName(str);
  }
  
  public String getParameter(String paramString)
  {
    return (String)this.parameters.get(paramString.toLowerCase());
  }
  
  public Map<String, String> getParameters()
  {
    return Collections.unmodifiableMap(this.parameters);
  }
  
  public String getSubType()
  {
    return this.subType;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public int hashCode()
  {
    return build().hashCode();
  }
  
  public HttpMediaType removeParameter(String paramString)
  {
    this.cachedBuildResult = null;
    this.parameters.remove(paramString.toLowerCase());
    return this;
  }
  
  public HttpMediaType setCharsetParameter(Charset paramCharset)
  {
    if (paramCharset == null) {}
    for (paramCharset = null;; paramCharset = paramCharset.name())
    {
      setParameter("charset", paramCharset);
      return this;
    }
  }
  
  public HttpMediaType setParameter(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      removeParameter(paramString1);
      return this;
    }
    Preconditions.checkArgument(TOKEN_REGEX.matcher(paramString1).matches(), "Name contains reserved characters");
    this.cachedBuildResult = null;
    this.parameters.put(paramString1.toLowerCase(), paramString2);
    return this;
  }
  
  public HttpMediaType setSubType(String paramString)
  {
    Preconditions.checkArgument(TYPE_REGEX.matcher(paramString).matches(), "Subtype contains reserved characters");
    this.subType = paramString;
    this.cachedBuildResult = null;
    return this;
  }
  
  public HttpMediaType setType(String paramString)
  {
    Preconditions.checkArgument(TYPE_REGEX.matcher(paramString).matches(), "Type contains reserved characters");
    this.type = paramString;
    this.cachedBuildResult = null;
    return this;
  }
  
  public String toString()
  {
    return build();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpMediaType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */