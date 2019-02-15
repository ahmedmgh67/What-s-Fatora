package com.google.api.client.http.apache;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

final class ApacheHttpRequest
  extends LowLevelHttpRequest
{
  private final HttpClient httpClient;
  private final HttpRequestBase request;
  
  ApacheHttpRequest(HttpClient paramHttpClient, HttpRequestBase paramHttpRequestBase)
  {
    this.httpClient = paramHttpClient;
    this.request = paramHttpRequestBase;
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    this.request.addHeader(paramString1, paramString2);
  }
  
  public LowLevelHttpResponse execute()
    throws IOException
  {
    if (getStreamingContent() != null)
    {
      Preconditions.checkArgument(this.request instanceof HttpEntityEnclosingRequest, "Apache HTTP client does not support %s requests with content.", new Object[] { this.request.getRequestLine().getMethod() });
      ContentEntity localContentEntity = new ContentEntity(getContentLength(), getStreamingContent());
      localContentEntity.setContentEncoding(getContentEncoding());
      localContentEntity.setContentType(getContentType());
      ((HttpEntityEnclosingRequest)this.request).setEntity(localContentEntity);
    }
    return new ApacheHttpResponse(this.request, this.httpClient.execute(this.request));
  }
  
  public void setTimeout(int paramInt1, int paramInt2)
    throws IOException
  {
    HttpParams localHttpParams = this.request.getParams();
    ConnManagerParams.setTimeout(localHttpParams, paramInt1);
    HttpConnectionParams.setConnectionTimeout(localHttpParams, paramInt1);
    HttpConnectionParams.setSoTimeout(localHttpParams, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\apache\ApacheHttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */