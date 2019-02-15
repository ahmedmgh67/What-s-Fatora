package com.google.api.client.http;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.PercentEscaper;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class GenericUrl
  extends GenericData
{
  private static final Escaper URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:", false);
  private String fragment;
  private String host;
  private List<String> pathParts;
  private int port = -1;
  private String scheme;
  private String userInfo;
  
  public GenericUrl() {}
  
  public GenericUrl(String paramString)
  {
    this(parseURL(paramString));
  }
  
  private GenericUrl(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.scheme = paramString1.toLowerCase();
    this.host = paramString2;
    this.port = paramInt;
    this.pathParts = toPathParts(paramString3);
    if (paramString4 != null) {}
    for (paramString1 = CharEscapers.decodeUri(paramString4);; paramString1 = null)
    {
      this.fragment = paramString1;
      if (paramString5 != null) {
        UrlEncodedParser.parse(paramString5, this);
      }
      paramString1 = (String)localObject;
      if (paramString6 != null) {
        paramString1 = CharEscapers.decodeUri(paramString6);
      }
      this.userInfo = paramString1;
      return;
    }
  }
  
  public GenericUrl(URI paramURI)
  {
    this(paramURI.getScheme(), paramURI.getHost(), paramURI.getPort(), paramURI.getRawPath(), paramURI.getRawFragment(), paramURI.getRawQuery(), paramURI.getRawUserInfo());
  }
  
  public GenericUrl(URL paramURL)
  {
    this(paramURL.getProtocol(), paramURL.getHost(), paramURL.getPort(), paramURL.getPath(), paramURL.getRef(), paramURL.getQuery(), paramURL.getUserInfo());
  }
  
  static void addQueryParams(Set<Map.Entry<String, Object>> paramSet, StringBuilder paramStringBuilder)
  {
    boolean bool1 = true;
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      Object localObject2 = (Map.Entry)paramSet.next();
      Object localObject1 = ((Map.Entry)localObject2).getValue();
      if (localObject1 != null)
      {
        localObject2 = CharEscapers.escapeUriQuery((String)((Map.Entry)localObject2).getKey());
        if ((localObject1 instanceof Collection))
        {
          localObject1 = ((Collection)localObject1).iterator();
          for (boolean bool2 = bool1;; bool2 = appendParam(bool2, paramStringBuilder, (String)localObject2, ((Iterator)localObject1).next()))
          {
            bool1 = bool2;
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
          }
        }
        bool1 = appendParam(bool1, paramStringBuilder, (String)localObject2, localObject1);
      }
    }
  }
  
  private static boolean appendParam(boolean paramBoolean, StringBuilder paramStringBuilder, String paramString, Object paramObject)
  {
    if (paramBoolean)
    {
      paramBoolean = false;
      paramStringBuilder.append('?');
    }
    for (;;)
    {
      paramStringBuilder.append(paramString);
      paramString = CharEscapers.escapeUriQuery(paramObject.toString());
      if (paramString.length() != 0) {
        paramStringBuilder.append('=').append(paramString);
      }
      return paramBoolean;
      paramStringBuilder.append('&');
    }
  }
  
  private void appendRawPathFromParts(StringBuilder paramStringBuilder)
  {
    int j = this.pathParts.size();
    int i = 0;
    while (i < j)
    {
      String str = (String)this.pathParts.get(i);
      if (i != 0) {
        paramStringBuilder.append('/');
      }
      if (str.length() != 0) {
        paramStringBuilder.append(CharEscapers.escapeUriPath(str));
      }
      i += 1;
    }
  }
  
  private static URL parseURL(String paramString)
  {
    try
    {
      paramString = new URL(paramString);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static List<String> toPathParts(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      localObject = null;
    }
    ArrayList localArrayList;
    int j;
    int i;
    do
    {
      return (List<String>)localObject;
      localArrayList = new ArrayList();
      j = 0;
      i = 1;
      localObject = localArrayList;
    } while (i == 0);
    int k = paramString.indexOf('/', j);
    if (k != -1)
    {
      i = 1;
      label53:
      if (i == 0) {
        break label90;
      }
    }
    label90:
    for (Object localObject = paramString.substring(j, k);; localObject = paramString.substring(j))
    {
      localArrayList.add(CharEscapers.decodeUri((String)localObject));
      j = k + 1;
      break;
      i = 0;
      break label53;
    }
  }
  
  private static URI toURI(String paramString)
  {
    try
    {
      paramString = new URI(paramString);
      return paramString;
    }
    catch (URISyntaxException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public void appendRawPath(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      paramString = toPathParts(paramString);
      if ((this.pathParts == null) || (this.pathParts.isEmpty())) {
        this.pathParts = paramString;
      }
    }
    else
    {
      return;
    }
    int i = this.pathParts.size();
    this.pathParts.set(i - 1, (String)this.pathParts.get(i - 1) + (String)paramString.get(0));
    this.pathParts.addAll(paramString.subList(1, paramString.size()));
  }
  
  public final String build()
  {
    return buildAuthority() + buildRelativeUrl();
  }
  
  public final String buildAuthority()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)Preconditions.checkNotNull(this.scheme));
    localStringBuilder.append("://");
    if (this.userInfo != null) {
      localStringBuilder.append(CharEscapers.escapeUriUserInfo(this.userInfo)).append('@');
    }
    localStringBuilder.append((String)Preconditions.checkNotNull(this.host));
    int i = this.port;
    if (i != -1) {
      localStringBuilder.append(':').append(i);
    }
    return localStringBuilder.toString();
  }
  
  public final String buildRelativeUrl()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.pathParts != null) {
      appendRawPathFromParts(localStringBuilder);
    }
    addQueryParams(entrySet(), localStringBuilder);
    String str = this.fragment;
    if (str != null) {
      localStringBuilder.append('#').append(URI_FRAGMENT_ESCAPER.escape(str));
    }
    return localStringBuilder.toString();
  }
  
  public GenericUrl clone()
  {
    GenericUrl localGenericUrl = (GenericUrl)super.clone();
    if (this.pathParts != null) {
      localGenericUrl.pathParts = new ArrayList(this.pathParts);
    }
    return localGenericUrl;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((!super.equals(paramObject)) || (!(paramObject instanceof GenericUrl))) {
      return false;
    }
    paramObject = (GenericUrl)paramObject;
    return build().equals(((GenericUrl)paramObject).toString());
  }
  
  public Collection<Object> getAll(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null) {
      return Collections.emptySet();
    }
    if ((paramString instanceof Collection)) {
      return Collections.unmodifiableCollection((Collection)paramString);
    }
    return Collections.singleton(paramString);
  }
  
  public Object getFirst(String paramString)
  {
    paramString = get(paramString);
    if ((paramString instanceof Collection))
    {
      paramString = ((Collection)paramString).iterator();
      if (paramString.hasNext()) {
        return paramString.next();
      }
      return null;
    }
    return paramString;
  }
  
  public String getFragment()
  {
    return this.fragment;
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public List<String> getPathParts()
  {
    return this.pathParts;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  public String getRawPath()
  {
    if (this.pathParts == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    appendRawPathFromParts(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public final String getScheme()
  {
    return this.scheme;
  }
  
  public final String getUserInfo()
  {
    return this.userInfo;
  }
  
  public int hashCode()
  {
    return build().hashCode();
  }
  
  public GenericUrl set(String paramString, Object paramObject)
  {
    return (GenericUrl)super.set(paramString, paramObject);
  }
  
  public final void setFragment(String paramString)
  {
    this.fragment = paramString;
  }
  
  public final void setHost(String paramString)
  {
    this.host = ((String)Preconditions.checkNotNull(paramString));
  }
  
  public void setPathParts(List<String> paramList)
  {
    this.pathParts = paramList;
  }
  
  public final void setPort(int paramInt)
  {
    if (paramInt >= -1) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "expected port >= -1");
      this.port = paramInt;
      return;
    }
  }
  
  public void setRawPath(String paramString)
  {
    this.pathParts = toPathParts(paramString);
  }
  
  public final void setScheme(String paramString)
  {
    this.scheme = ((String)Preconditions.checkNotNull(paramString));
  }
  
  public final void setUserInfo(String paramString)
  {
    this.userInfo = paramString;
  }
  
  public String toString()
  {
    return build();
  }
  
  public final URI toURI()
  {
    return toURI(build());
  }
  
  public final URL toURL()
  {
    return parseURL(build());
  }
  
  public final URL toURL(String paramString)
  {
    try
    {
      paramString = new URL(toURL(), paramString);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\GenericUrl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */