package com.google.api.client.googleapis.services;

import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractGoogleClientRequest<T>
  extends GenericData
{
  public static final String USER_AGENT_SUFFIX = "Google-API-Java-Client";
  private final AbstractGoogleClient abstractGoogleClient;
  private boolean disableGZipContent;
  private MediaHttpDownloader downloader;
  private final HttpContent httpContent;
  private HttpHeaders lastResponseHeaders;
  private int lastStatusCode = -1;
  private String lastStatusMessage;
  private HttpHeaders requestHeaders = new HttpHeaders();
  private final String requestMethod;
  private Class<T> responseClass;
  private MediaHttpUploader uploader;
  private final String uriTemplate;
  
  protected AbstractGoogleClientRequest(AbstractGoogleClient paramAbstractGoogleClient, String paramString1, String paramString2, HttpContent paramHttpContent, Class<T> paramClass)
  {
    this.responseClass = ((Class)Preconditions.checkNotNull(paramClass));
    this.abstractGoogleClient = ((AbstractGoogleClient)Preconditions.checkNotNull(paramAbstractGoogleClient));
    this.requestMethod = ((String)Preconditions.checkNotNull(paramString1));
    this.uriTemplate = ((String)Preconditions.checkNotNull(paramString2));
    this.httpContent = paramHttpContent;
    paramAbstractGoogleClient = paramAbstractGoogleClient.getApplicationName();
    if (paramAbstractGoogleClient != null)
    {
      this.requestHeaders.setUserAgent(paramAbstractGoogleClient + " " + "Google-API-Java-Client");
      return;
    }
    this.requestHeaders.setUserAgent("Google-API-Java-Client");
  }
  
  private HttpRequest buildHttpRequest(boolean paramBoolean)
    throws IOException
  {
    boolean bool2 = false;
    boolean bool1;
    if (this.uploader == null)
    {
      bool1 = true;
      Preconditions.checkArgument(bool1);
      if (paramBoolean)
      {
        bool1 = bool2;
        if (!this.requestMethod.equals("GET")) {}
      }
      else
      {
        bool1 = true;
      }
      Preconditions.checkArgument(bool1);
      if (!paramBoolean) {
        break label211;
      }
    }
    label211:
    for (final Object localObject = "HEAD";; localObject = this.requestMethod)
    {
      localObject = getAbstractGoogleClient().getRequestFactory().buildRequest((String)localObject, buildHttpRequestUrl(), this.httpContent);
      new MethodOverride().intercept((HttpRequest)localObject);
      ((HttpRequest)localObject).setParser(getAbstractGoogleClient().getObjectParser());
      if ((this.httpContent == null) && ((this.requestMethod.equals("POST")) || (this.requestMethod.equals("PUT")) || (this.requestMethod.equals("PATCH")))) {
        ((HttpRequest)localObject).setContent(new EmptyContent());
      }
      ((HttpRequest)localObject).getHeaders().putAll(this.requestHeaders);
      if (!this.disableGZipContent) {
        ((HttpRequest)localObject).setEncoding(new GZipEncoding());
      }
      ((HttpRequest)localObject).setResponseInterceptor(new HttpResponseInterceptor()
      {
        public void interceptResponse(HttpResponse paramAnonymousHttpResponse)
          throws IOException
        {
          if (this.val$responseInterceptor != null) {
            this.val$responseInterceptor.interceptResponse(paramAnonymousHttpResponse);
          }
          if ((!paramAnonymousHttpResponse.isSuccessStatusCode()) && (localObject.getThrowExceptionOnExecuteError())) {
            throw AbstractGoogleClientRequest.this.newExceptionOnError(paramAnonymousHttpResponse);
          }
        }
      });
      return (HttpRequest)localObject;
      bool1 = false;
      break;
    }
  }
  
  private HttpResponse executeUnparsed(boolean paramBoolean)
    throws IOException
  {
    Object localObject;
    if (this.uploader == null) {
      localObject = buildHttpRequest(paramBoolean).execute();
    }
    HttpResponse localHttpResponse;
    do
    {
      do
      {
        this.lastResponseHeaders = ((HttpResponse)localObject).getHeaders();
        this.lastStatusCode = ((HttpResponse)localObject).getStatusCode();
        this.lastStatusMessage = ((HttpResponse)localObject).getStatusMessage();
        return (HttpResponse)localObject;
        localObject = buildHttpRequestUrl();
        paramBoolean = getAbstractGoogleClient().getRequestFactory().buildRequest(this.requestMethod, (GenericUrl)localObject, this.httpContent).getThrowExceptionOnExecuteError();
        localHttpResponse = this.uploader.setInitiationHeaders(this.requestHeaders).setDisableGZipContent(this.disableGZipContent).upload((GenericUrl)localObject);
        localHttpResponse.getRequest().setParser(getAbstractGoogleClient().getObjectParser());
        localObject = localHttpResponse;
      } while (!paramBoolean);
      localObject = localHttpResponse;
    } while (localHttpResponse.isSuccessStatusCode());
    throw newExceptionOnError(localHttpResponse);
  }
  
  public HttpRequest buildHttpRequest()
    throws IOException
  {
    return buildHttpRequest(false);
  }
  
  public GenericUrl buildHttpRequestUrl()
  {
    return new GenericUrl(UriTemplate.expand(this.abstractGoogleClient.getBaseUrl(), this.uriTemplate, this, true));
  }
  
  protected HttpRequest buildHttpRequestUsingHead()
    throws IOException
  {
    return buildHttpRequest(true);
  }
  
  protected final void checkRequiredParameter(Object paramObject, String paramString)
  {
    if ((this.abstractGoogleClient.getSuppressRequiredParameterChecks()) || (paramObject != null)) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "Required parameter %s must be specified", new Object[] { paramString });
      return;
    }
  }
  
  public T execute()
    throws IOException
  {
    return (T)executeUnparsed().parseAs(this.responseClass);
  }
  
  public void executeAndDownloadTo(OutputStream paramOutputStream)
    throws IOException
  {
    executeUnparsed().download(paramOutputStream);
  }
  
  public InputStream executeAsInputStream()
    throws IOException
  {
    return executeUnparsed().getContent();
  }
  
  protected HttpResponse executeMedia()
    throws IOException
  {
    set("alt", "media");
    return executeUnparsed();
  }
  
  protected void executeMediaAndDownloadTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (this.downloader == null)
    {
      executeMedia().download(paramOutputStream);
      return;
    }
    this.downloader.download(buildHttpRequestUrl(), this.requestHeaders, paramOutputStream);
  }
  
  protected InputStream executeMediaAsInputStream()
    throws IOException
  {
    return executeMedia().getContent();
  }
  
  public HttpResponse executeUnparsed()
    throws IOException
  {
    return executeUnparsed(false);
  }
  
  protected HttpResponse executeUsingHead()
    throws IOException
  {
    if (this.uploader == null) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      HttpResponse localHttpResponse = executeUnparsed(true);
      localHttpResponse.ignore();
      return localHttpResponse;
    }
  }
  
  public AbstractGoogleClient getAbstractGoogleClient()
  {
    return this.abstractGoogleClient;
  }
  
  public final boolean getDisableGZipContent()
  {
    return this.disableGZipContent;
  }
  
  public final HttpContent getHttpContent()
  {
    return this.httpContent;
  }
  
  public final HttpHeaders getLastResponseHeaders()
  {
    return this.lastResponseHeaders;
  }
  
  public final int getLastStatusCode()
  {
    return this.lastStatusCode;
  }
  
  public final String getLastStatusMessage()
  {
    return this.lastStatusMessage;
  }
  
  public final MediaHttpDownloader getMediaHttpDownloader()
  {
    return this.downloader;
  }
  
  public final MediaHttpUploader getMediaHttpUploader()
  {
    return this.uploader;
  }
  
  public final HttpHeaders getRequestHeaders()
  {
    return this.requestHeaders;
  }
  
  public final String getRequestMethod()
  {
    return this.requestMethod;
  }
  
  public final Class<T> getResponseClass()
  {
    return this.responseClass;
  }
  
  public final String getUriTemplate()
  {
    return this.uriTemplate;
  }
  
  protected final void initializeMediaDownload()
  {
    HttpRequestFactory localHttpRequestFactory = this.abstractGoogleClient.getRequestFactory();
    this.downloader = new MediaHttpDownloader(localHttpRequestFactory.getTransport(), localHttpRequestFactory.getInitializer());
  }
  
  protected final void initializeMediaUpload(AbstractInputStreamContent paramAbstractInputStreamContent)
  {
    HttpRequestFactory localHttpRequestFactory = this.abstractGoogleClient.getRequestFactory();
    this.uploader = new MediaHttpUploader(paramAbstractInputStreamContent, localHttpRequestFactory.getTransport(), localHttpRequestFactory.getInitializer());
    this.uploader.setInitiationRequestMethod(this.requestMethod);
    if (this.httpContent != null) {
      this.uploader.setMetadata(this.httpContent);
    }
  }
  
  protected IOException newExceptionOnError(HttpResponse paramHttpResponse)
  {
    return new HttpResponseException(paramHttpResponse);
  }
  
  public final <E> void queue(BatchRequest paramBatchRequest, Class<E> paramClass, BatchCallback<T, E> paramBatchCallback)
    throws IOException
  {
    if (this.uploader == null) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "Batching media requests is not supported");
      paramBatchRequest.queue(buildHttpRequest(), getResponseClass(), paramClass, paramBatchCallback);
      return;
    }
  }
  
  public AbstractGoogleClientRequest<T> set(String paramString, Object paramObject)
  {
    return (AbstractGoogleClientRequest)super.set(paramString, paramObject);
  }
  
  public AbstractGoogleClientRequest<T> setDisableGZipContent(boolean paramBoolean)
  {
    this.disableGZipContent = paramBoolean;
    return this;
  }
  
  public AbstractGoogleClientRequest<T> setRequestHeaders(HttpHeaders paramHttpHeaders)
  {
    this.requestHeaders = paramHttpHeaders;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\AbstractGoogleClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */