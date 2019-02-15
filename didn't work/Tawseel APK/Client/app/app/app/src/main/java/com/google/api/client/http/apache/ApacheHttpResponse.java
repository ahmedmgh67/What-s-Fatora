package com.google.api.client.http.apache;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;

final class ApacheHttpResponse
  extends LowLevelHttpResponse
{
  private final Header[] allHeaders;
  private final HttpRequestBase request;
  private final HttpResponse response;
  
  ApacheHttpResponse(HttpRequestBase paramHttpRequestBase, HttpResponse paramHttpResponse)
  {
    this.request = paramHttpRequestBase;
    this.response = paramHttpResponse;
    this.allHeaders = paramHttpResponse.getAllHeaders();
  }
  
  public void disconnect()
  {
    this.request.abort();
  }
  
  public InputStream getContent()
    throws IOException
  {
    HttpEntity localHttpEntity = this.response.getEntity();
    if (localHttpEntity == null) {
      return null;
    }
    return localHttpEntity.getContent();
  }
  
  public String getContentEncoding()
  {
    Object localObject = this.response.getEntity();
    if (localObject != null)
    {
      localObject = ((HttpEntity)localObject).getContentEncoding();
      if (localObject != null) {
        return ((Header)localObject).getValue();
      }
    }
    return null;
  }
  
  public long getContentLength()
  {
    HttpEntity localHttpEntity = this.response.getEntity();
    if (localHttpEntity == null) {
      return -1L;
    }
    return localHttpEntity.getContentLength();
  }
  
  public String getContentType()
  {
    Object localObject = this.response.getEntity();
    if (localObject != null)
    {
      localObject = ((HttpEntity)localObject).getContentType();
      if (localObject != null) {
        return ((Header)localObject).getValue();
      }
    }
    return null;
  }
  
  public int getHeaderCount()
  {
    return this.allHeaders.length;
  }
  
  public String getHeaderName(int paramInt)
  {
    return this.allHeaders[paramInt].getName();
  }
  
  public String getHeaderValue(int paramInt)
  {
    return this.allHeaders[paramInt].getValue();
  }
  
  public String getHeaderValue(String paramString)
  {
    return this.response.getLastHeader(paramString).getValue();
  }
  
  public String getReasonPhrase()
  {
    StatusLine localStatusLine = this.response.getStatusLine();
    if (localStatusLine == null) {
      return null;
    }
    return localStatusLine.getReasonPhrase();
  }
  
  public int getStatusCode()
  {
    StatusLine localStatusLine = this.response.getStatusLine();
    if (localStatusLine == null) {
      return 0;
    }
    return localStatusLine.getStatusCode();
  }
  
  public String getStatusLine()
  {
    StatusLine localStatusLine = this.response.getStatusLine();
    if (localStatusLine == null) {
      return null;
    }
    return localStatusLine.toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\apache\ApacheHttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */