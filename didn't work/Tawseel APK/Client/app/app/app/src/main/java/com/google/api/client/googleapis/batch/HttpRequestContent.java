package com.google.api.client.googleapis.batch;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

class HttpRequestContent
  extends AbstractHttpContent
{
  static final String NEWLINE = "\r\n";
  private final HttpRequest request;
  
  HttpRequestContent(HttpRequest paramHttpRequest)
  {
    super("application/http");
    this.request = paramHttpRequest;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, getCharset());
    localOutputStreamWriter.write(this.request.getRequestMethod());
    localOutputStreamWriter.write(" ");
    localOutputStreamWriter.write(this.request.getUrl().build());
    localOutputStreamWriter.write("\r\n");
    HttpHeaders localHttpHeaders = new HttpHeaders();
    localHttpHeaders.fromHttpHeaders(this.request.getHeaders());
    localHttpHeaders.setAcceptEncoding(null).setUserAgent(null).setContentEncoding(null).setContentType(null).setContentLength(null);
    HttpContent localHttpContent = this.request.getContent();
    if (localHttpContent != null)
    {
      localHttpHeaders.setContentType(localHttpContent.getType());
      long l = localHttpContent.getLength();
      if (l != -1L) {
        localHttpHeaders.setContentLength(Long.valueOf(l));
      }
    }
    HttpHeaders.serializeHeadersForMultipartRequests(localHttpHeaders, null, null, localOutputStreamWriter);
    localOutputStreamWriter.write("\r\n");
    localOutputStreamWriter.flush();
    if (localHttpContent != null) {
      localHttpContent.writeTo(paramOutputStream);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\batch\HttpRequestContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */