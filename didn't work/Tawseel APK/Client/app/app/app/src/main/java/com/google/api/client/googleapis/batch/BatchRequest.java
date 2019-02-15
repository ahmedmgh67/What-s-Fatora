package com.google.api.client.googleapis.batch;

import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.http.MultipartContent.Part;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BatchRequest
{
  private GenericUrl batchUrl = new GenericUrl("https://www.googleapis.com/batch");
  private final HttpRequestFactory requestFactory;
  List<RequestInfo<?, ?>> requestInfos = new ArrayList();
  private Sleeper sleeper = Sleeper.DEFAULT;
  
  public BatchRequest(HttpTransport paramHttpTransport, HttpRequestInitializer paramHttpRequestInitializer)
  {
    if (paramHttpRequestInitializer == null) {}
    for (paramHttpTransport = paramHttpTransport.createRequestFactory();; paramHttpTransport = paramHttpTransport.createRequestFactory(paramHttpRequestInitializer))
    {
      this.requestFactory = paramHttpTransport;
      return;
    }
  }
  
  public void execute()
    throws IOException
  {
    boolean bool;
    HttpRequest localHttpRequest;
    int j;
    BackOffPolicy localBackOffPolicy;
    int i;
    if (!this.requestInfos.isEmpty())
    {
      bool = true;
      Preconditions.checkState(bool);
      localHttpRequest = this.requestFactory.buildPostRequest(this.batchUrl, null);
      localHttpRequest.setInterceptor(new BatchInterceptor(localHttpRequest.getInterceptor()));
      j = localHttpRequest.getNumberOfRetries();
      localBackOffPolicy = localHttpRequest.getBackOffPolicy();
      i = j;
      if (localBackOffPolicy != null)
      {
        localBackOffPolicy.reset();
        i = j;
      }
    }
    for (;;)
    {
      if (i > 0) {}
      Object localObject3;
      for (bool = true;; bool = false)
      {
        localObject2 = new MultipartContent();
        ((MultipartContent)localObject2).getMediaType().setSubType("mixed");
        j = 1;
        localObject3 = this.requestInfos.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          RequestInfo localRequestInfo = (RequestInfo)((Iterator)localObject3).next();
          ((MultipartContent)localObject2).addPart(new MultipartContent.Part(new HttpHeaders().setAcceptEncoding(null).set("Content-ID", Integer.valueOf(j)), new HttpRequestContent(localRequestInfo.request)));
          j += 1;
        }
        bool = false;
        break;
      }
      localHttpRequest.setContent((HttpContent)localObject2);
      Object localObject2 = localHttpRequest.execute();
      try
      {
        localObject3 = "--" + ((HttpResponse)localObject2).getMediaType().getParameter("boundary");
        localObject3 = new BatchUnparsedResponse(((HttpResponse)localObject2).getContent(), (String)localObject3, this.requestInfos, bool);
        while (((BatchUnparsedResponse)localObject3).hasNext) {
          ((BatchUnparsedResponse)localObject3).parseNextResponse();
        }
      }
      finally
      {
        ((HttpResponse)localObject2).disconnect();
      }
      localObject2 = ((BatchUnparsedResponse)localObject3).unsuccessfulRequestInfos;
      long l;
      if (!((List)localObject2).isEmpty())
      {
        this.requestInfos = ((List)localObject2);
        if ((((BatchUnparsedResponse)localObject3).backOffRequired) && (localBackOffPolicy != null))
        {
          l = localBackOffPolicy.getNextBackOffMillis();
          if (l == -1L) {}
        }
      }
      try
      {
        this.sleeper.sleep(l);
        i -= 1;
        if (bool) {
          continue;
        }
        this.requestInfos.clear();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  public GenericUrl getBatchUrl()
  {
    return this.batchUrl;
  }
  
  public Sleeper getSleeper()
  {
    return this.sleeper;
  }
  
  public <T, E> BatchRequest queue(HttpRequest paramHttpRequest, Class<T> paramClass, Class<E> paramClass1, BatchCallback<T, E> paramBatchCallback)
    throws IOException
  {
    Preconditions.checkNotNull(paramHttpRequest);
    Preconditions.checkNotNull(paramBatchCallback);
    Preconditions.checkNotNull(paramClass);
    Preconditions.checkNotNull(paramClass1);
    this.requestInfos.add(new RequestInfo(paramBatchCallback, paramClass, paramClass1, paramHttpRequest));
    return this;
  }
  
  public BatchRequest setBatchUrl(GenericUrl paramGenericUrl)
  {
    this.batchUrl = paramGenericUrl;
    return this;
  }
  
  public BatchRequest setSleeper(Sleeper paramSleeper)
  {
    this.sleeper = ((Sleeper)Preconditions.checkNotNull(paramSleeper));
    return this;
  }
  
  public int size()
  {
    return this.requestInfos.size();
  }
  
  class BatchInterceptor
    implements HttpExecuteInterceptor
  {
    private HttpExecuteInterceptor originalInterceptor;
    
    BatchInterceptor(HttpExecuteInterceptor paramHttpExecuteInterceptor)
    {
      this.originalInterceptor = paramHttpExecuteInterceptor;
    }
    
    public void intercept(HttpRequest paramHttpRequest)
      throws IOException
    {
      if (this.originalInterceptor != null) {
        this.originalInterceptor.intercept(paramHttpRequest);
      }
      paramHttpRequest = BatchRequest.this.requestInfos.iterator();
      while (paramHttpRequest.hasNext())
      {
        BatchRequest.RequestInfo localRequestInfo = (BatchRequest.RequestInfo)paramHttpRequest.next();
        HttpExecuteInterceptor localHttpExecuteInterceptor = localRequestInfo.request.getInterceptor();
        if (localHttpExecuteInterceptor != null) {
          localHttpExecuteInterceptor.intercept(localRequestInfo.request);
        }
      }
    }
  }
  
  static class RequestInfo<T, E>
  {
    final BatchCallback<T, E> callback;
    final Class<T> dataClass;
    final Class<E> errorClass;
    final HttpRequest request;
    
    RequestInfo(BatchCallback<T, E> paramBatchCallback, Class<T> paramClass, Class<E> paramClass1, HttpRequest paramHttpRequest)
    {
      this.callback = paramBatchCallback;
      this.dataClass = paramClass;
      this.errorClass = paramClass1;
      this.request = paramHttpRequest;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\batch\BatchRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */