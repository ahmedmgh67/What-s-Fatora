package com.google.api.client.http;

import java.io.IOException;

public final class HttpRequestFactory
{
  private final HttpRequestInitializer initializer;
  private final HttpTransport transport;
  
  HttpRequestFactory(HttpTransport paramHttpTransport, HttpRequestInitializer paramHttpRequestInitializer)
  {
    this.transport = paramHttpTransport;
    this.initializer = paramHttpRequestInitializer;
  }
  
  public HttpRequest buildDeleteRequest(GenericUrl paramGenericUrl)
    throws IOException
  {
    return buildRequest("DELETE", paramGenericUrl, null);
  }
  
  public HttpRequest buildGetRequest(GenericUrl paramGenericUrl)
    throws IOException
  {
    return buildRequest("GET", paramGenericUrl, null);
  }
  
  public HttpRequest buildHeadRequest(GenericUrl paramGenericUrl)
    throws IOException
  {
    return buildRequest("HEAD", paramGenericUrl, null);
  }
  
  public HttpRequest buildPatchRequest(GenericUrl paramGenericUrl, HttpContent paramHttpContent)
    throws IOException
  {
    return buildRequest("PATCH", paramGenericUrl, paramHttpContent);
  }
  
  public HttpRequest buildPostRequest(GenericUrl paramGenericUrl, HttpContent paramHttpContent)
    throws IOException
  {
    return buildRequest("POST", paramGenericUrl, paramHttpContent);
  }
  
  public HttpRequest buildPutRequest(GenericUrl paramGenericUrl, HttpContent paramHttpContent)
    throws IOException
  {
    return buildRequest("PUT", paramGenericUrl, paramHttpContent);
  }
  
  public HttpRequest buildRequest(String paramString, GenericUrl paramGenericUrl, HttpContent paramHttpContent)
    throws IOException
  {
    HttpRequest localHttpRequest = this.transport.buildRequest();
    if (this.initializer != null) {
      this.initializer.initialize(localHttpRequest);
    }
    localHttpRequest.setRequestMethod(paramString);
    if (paramGenericUrl != null) {
      localHttpRequest.setUrl(paramGenericUrl);
    }
    if (paramHttpContent != null) {
      localHttpRequest.setContent(paramHttpContent);
    }
    return localHttpRequest;
  }
  
  public HttpRequestInitializer getInitializer()
  {
    return this.initializer;
  }
  
  public HttpTransport getTransport()
  {
    return this.transport;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpRequestFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */