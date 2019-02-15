package com.google.api.client.testing.http;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.StreamingContent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@Beta
public class MockLowLevelHttpRequest
  extends LowLevelHttpRequest
{
  private final Map<String, List<String>> headersMap = new HashMap();
  private MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
  private String url;
  
  public MockLowLevelHttpRequest() {}
  
  public MockLowLevelHttpRequest(String paramString)
  {
    this.url = paramString;
  }
  
  public void addHeader(String paramString1, String paramString2)
    throws IOException
  {
    String str = paramString1.toLowerCase();
    List localList = (List)this.headersMap.get(str);
    paramString1 = localList;
    if (localList == null)
    {
      paramString1 = new ArrayList();
      this.headersMap.put(str, paramString1);
    }
    paramString1.add(paramString2);
  }
  
  public LowLevelHttpResponse execute()
    throws IOException
  {
    return this.response;
  }
  
  public String getContentAsString()
    throws IOException
  {
    if (getStreamingContent() == null) {
      return "";
    }
    Object localObject2 = new ByteArrayOutputStream();
    getStreamingContent().writeTo((OutputStream)localObject2);
    String str = getContentEncoding();
    Object localObject1 = localObject2;
    if (str != null)
    {
      localObject1 = localObject2;
      if (str.contains("gzip"))
      {
        localObject2 = new GZIPInputStream(new ByteArrayInputStream(((ByteArrayOutputStream)localObject2).toByteArray()));
        localObject1 = new ByteArrayOutputStream();
        IOUtils.copy((InputStream)localObject2, (OutputStream)localObject1);
      }
    }
    localObject2 = getContentType();
    if (localObject2 != null)
    {
      localObject2 = new HttpMediaType((String)localObject2);
      if ((localObject2 != null) && (((HttpMediaType)localObject2).getCharsetParameter() != null)) {
        break label129;
      }
    }
    label129:
    for (localObject2 = Charsets.ISO_8859_1;; localObject2 = ((HttpMediaType)localObject2).getCharsetParameter())
    {
      return ((ByteArrayOutputStream)localObject1).toString(((Charset)localObject2).name());
      localObject2 = null;
      break;
    }
  }
  
  public String getFirstHeaderValue(String paramString)
  {
    paramString = (List)this.headersMap.get(paramString.toLowerCase());
    if (paramString == null) {
      return null;
    }
    return (String)paramString.get(0);
  }
  
  public List<String> getHeaderValues(String paramString)
  {
    paramString = (List)this.headersMap.get(paramString.toLowerCase());
    if (paramString == null) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(paramString);
  }
  
  public Map<String, List<String>> getHeaders()
  {
    return Collections.unmodifiableMap(this.headersMap);
  }
  
  public MockLowLevelHttpResponse getResponse()
  {
    return this.response;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public MockLowLevelHttpRequest setResponse(MockLowLevelHttpResponse paramMockLowLevelHttpResponse)
  {
    this.response = paramMockLowLevelHttpResponse;
    return this;
  }
  
  public MockLowLevelHttpRequest setUrl(String paramString)
  {
    this.url = paramString;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\MockLowLevelHttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */