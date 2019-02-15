package com.google.api.client.testing.http;

import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.util.TestableByteArrayInputStream;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Beta
public class MockLowLevelHttpResponse
  extends LowLevelHttpResponse
{
  private InputStream content;
  private String contentEncoding;
  private long contentLength = -1L;
  private String contentType;
  private List<String> headerNames = new ArrayList();
  private List<String> headerValues = new ArrayList();
  private boolean isDisconnected;
  private String reasonPhrase;
  private int statusCode = 200;
  
  public MockLowLevelHttpResponse addHeader(String paramString1, String paramString2)
  {
    this.headerNames.add(Preconditions.checkNotNull(paramString1));
    this.headerValues.add(Preconditions.checkNotNull(paramString2));
    return this;
  }
  
  public void disconnect()
    throws IOException
  {
    this.isDisconnected = true;
    super.disconnect();
  }
  
  public InputStream getContent()
    throws IOException
  {
    return this.content;
  }
  
  public String getContentEncoding()
  {
    return this.contentEncoding;
  }
  
  public long getContentLength()
  {
    return this.contentLength;
  }
  
  public final String getContentType()
  {
    return this.contentType;
  }
  
  public int getHeaderCount()
  {
    return this.headerNames.size();
  }
  
  public String getHeaderName(int paramInt)
  {
    return (String)this.headerNames.get(paramInt);
  }
  
  public final List<String> getHeaderNames()
  {
    return this.headerNames;
  }
  
  public String getHeaderValue(int paramInt)
  {
    return (String)this.headerValues.get(paramInt);
  }
  
  public final List<String> getHeaderValues()
  {
    return this.headerValues;
  }
  
  public String getReasonPhrase()
  {
    return this.reasonPhrase;
  }
  
  public int getStatusCode()
  {
    return this.statusCode;
  }
  
  public String getStatusLine()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.statusCode);
    if (this.reasonPhrase != null) {
      localStringBuilder.append(this.reasonPhrase);
    }
    return localStringBuilder.toString();
  }
  
  public boolean isDisconnected()
  {
    return this.isDisconnected;
  }
  
  public MockLowLevelHttpResponse setContent(InputStream paramInputStream)
  {
    this.content = paramInputStream;
    return this;
  }
  
  public MockLowLevelHttpResponse setContent(String paramString)
  {
    if (paramString == null) {
      return setZeroContent();
    }
    return setContent(StringUtils.getBytesUtf8(paramString));
  }
  
  public MockLowLevelHttpResponse setContent(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return setZeroContent();
    }
    this.content = new TestableByteArrayInputStream(paramArrayOfByte);
    setContentLength(paramArrayOfByte.length);
    return this;
  }
  
  public MockLowLevelHttpResponse setContentEncoding(String paramString)
  {
    this.contentEncoding = paramString;
    return this;
  }
  
  public MockLowLevelHttpResponse setContentLength(long paramLong)
  {
    this.contentLength = paramLong;
    if (paramLong >= -1L) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      return this;
    }
  }
  
  public MockLowLevelHttpResponse setContentType(String paramString)
  {
    this.contentType = paramString;
    return this;
  }
  
  public MockLowLevelHttpResponse setHeaderNames(List<String> paramList)
  {
    this.headerNames = ((List)Preconditions.checkNotNull(paramList));
    return this;
  }
  
  public MockLowLevelHttpResponse setHeaderValues(List<String> paramList)
  {
    this.headerValues = ((List)Preconditions.checkNotNull(paramList));
    return this;
  }
  
  public MockLowLevelHttpResponse setReasonPhrase(String paramString)
  {
    this.reasonPhrase = paramString;
    return this;
  }
  
  public MockLowLevelHttpResponse setStatusCode(int paramInt)
  {
    this.statusCode = paramInt;
    return this;
  }
  
  public MockLowLevelHttpResponse setZeroContent()
  {
    this.content = null;
    setContentLength(0L);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\MockLowLevelHttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */