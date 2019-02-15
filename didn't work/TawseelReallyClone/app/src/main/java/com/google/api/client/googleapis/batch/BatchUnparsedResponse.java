package com.google.api.client.googleapis.batch;

import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.ByteStreams;
import com.google.api.client.util.ObjectParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class BatchUnparsedResponse
{
  boolean backOffRequired;
  private final String boundary;
  private int contentId = 0;
  boolean hasNext = true;
  private final InputStream inputStream;
  private final List<BatchRequest.RequestInfo<?, ?>> requestInfos;
  private final boolean retryAllowed;
  List<BatchRequest.RequestInfo<?, ?>> unsuccessfulRequestInfos = new ArrayList();
  
  BatchUnparsedResponse(InputStream paramInputStream, String paramString, List<BatchRequest.RequestInfo<?, ?>> paramList, boolean paramBoolean)
    throws IOException
  {
    this.boundary = paramString;
    this.requestInfos = paramList;
    this.retryAllowed = paramBoolean;
    this.inputStream = paramInputStream;
    checkForFinalBoundary(readLine());
  }
  
  private void checkForFinalBoundary(String paramString)
    throws IOException
  {
    if (paramString.equals(this.boundary + "--"))
    {
      this.hasNext = false;
      this.inputStream.close();
    }
  }
  
  private HttpResponse getFakeResponse(int paramInt, InputStream paramInputStream, List<String> paramList1, List<String> paramList2)
    throws IOException
  {
    paramInputStream = new FakeResponseHttpTransport(paramInt, paramInputStream, paramList1, paramList2).createRequestFactory().buildPostRequest(new GenericUrl("http://google.com/"), null);
    paramInputStream.setLoggingEnabled(false);
    paramInputStream.setThrowExceptionOnExecuteError(false);
    return paramInputStream.execute();
  }
  
  private <A, T, E> A getParsedDataClass(Class<A> paramClass, HttpResponse paramHttpResponse, BatchRequest.RequestInfo<T, E> paramRequestInfo)
    throws IOException
  {
    if (paramClass == Void.class) {
      return null;
    }
    return (A)paramRequestInfo.request.getParser().parseAndClose(paramHttpResponse.getContent(), paramHttpResponse.getContentCharset(), paramClass);
  }
  
  private <T, E> void parseAndCallback(BatchRequest.RequestInfo<T, E> paramRequestInfo, int paramInt, HttpResponse paramHttpResponse)
    throws IOException
  {
    BatchCallback localBatchCallback = paramRequestInfo.callback;
    HttpHeaders localHttpHeaders = paramHttpResponse.getHeaders();
    HttpUnsuccessfulResponseHandler localHttpUnsuccessfulResponseHandler = paramRequestInfo.request.getUnsuccessfulResponseHandler();
    BackOffPolicy localBackOffPolicy = paramRequestInfo.request.getBackOffPolicy();
    this.backOffRequired = false;
    if (HttpStatusCodes.isSuccess(paramInt)) {
      if (localBatchCallback != null) {}
    }
    label196:
    label240:
    do
    {
      return;
      localBatchCallback.onSuccess(getParsedDataClass(paramRequestInfo.dataClass, paramHttpResponse, paramRequestInfo), localHttpHeaders);
      return;
      HttpContent localHttpContent = paramRequestInfo.request.getContent();
      boolean bool1;
      boolean bool2;
      int i;
      if ((this.retryAllowed) && ((localHttpContent == null) || (localHttpContent.retrySupported())))
      {
        bool1 = true;
        bool2 = false;
        i = 0;
        if (localHttpUnsuccessfulResponseHandler != null) {
          bool2 = localHttpUnsuccessfulResponseHandler.handleResponse(paramRequestInfo.request, paramHttpResponse, bool1);
        }
        paramInt = i;
        if (!bool2)
        {
          if (!paramRequestInfo.request.handleRedirect(paramHttpResponse.getStatusCode(), paramHttpResponse.getHeaders())) {
            break label196;
          }
          paramInt = 1;
        }
      }
      for (;;)
      {
        if ((!bool1) || ((!bool2) && (!this.backOffRequired) && (paramInt == 0))) {
          break label240;
        }
        this.unsuccessfulRequestInfos.add(paramRequestInfo);
        return;
        bool1 = false;
        break;
        paramInt = i;
        if (bool1)
        {
          paramInt = i;
          if (localBackOffPolicy != null)
          {
            paramInt = i;
            if (localBackOffPolicy.isBackOffRequired(paramHttpResponse.getStatusCode()))
            {
              this.backOffRequired = true;
              paramInt = i;
            }
          }
        }
      }
    } while (localBatchCallback == null);
    localBatchCallback.onFailure(getParsedDataClass(paramRequestInfo.errorClass, paramHttpResponse, paramRequestInfo), localHttpHeaders);
  }
  
  private String readLine()
    throws IOException
  {
    return trimCrlf(readRawLine());
  }
  
  private String readRawLine()
    throws IOException
  {
    int i = this.inputStream.read();
    if (i == -1) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      if (i != -1)
      {
        localStringBuilder.append((char)i);
        if (i != 10) {}
      }
      else
      {
        return localStringBuilder.toString();
      }
      i = this.inputStream.read();
    }
  }
  
  private static InputStream trimCrlf(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    int i = j;
    if (j > 0)
    {
      i = j;
      if (paramArrayOfByte[(j - 1)] == 10) {
        i = j - 1;
      }
    }
    j = i;
    if (i > 0)
    {
      j = i;
      if (paramArrayOfByte[(i - 1)] == 13) {
        j = i - 1;
      }
    }
    return new ByteArrayInputStream(paramArrayOfByte, 0, j);
  }
  
  private static String trimCrlf(String paramString)
  {
    String str;
    if (paramString.endsWith("\r\n")) {
      str = paramString.substring(0, paramString.length() - 2);
    }
    do
    {
      return str;
      str = paramString;
    } while (!paramString.endsWith("\n"));
    return paramString.substring(0, paramString.length() - 1);
  }
  
  void parseNextResponse()
    throws IOException
  {
    this.contentId += 1;
    String str;
    do
    {
      str = readLine();
    } while ((str != null) && (!str.equals("")));
    int i = Integer.parseInt(readLine().split(" ")[1]);
    Object localObject2 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    long l = -1L;
    Object localObject1;
    for (;;)
    {
      str = readLine();
      if ((str == null) || (str.equals(""))) {
        break;
      }
      localObject1 = str.split(": ", 2);
      str = localObject1[0];
      localObject1 = localObject1[1];
      ((List)localObject2).add(str);
      localArrayList.add(localObject1);
      if ("Content-Length".equalsIgnoreCase(str.trim())) {
        l = Long.parseLong((String)localObject1);
      }
    }
    if (l == -1L)
    {
      localObject1 = new ByteArrayOutputStream();
      for (;;)
      {
        str = readRawLine();
        if ((str == null) || (str.startsWith(this.boundary))) {
          break;
        }
        ((ByteArrayOutputStream)localObject1).write(str.getBytes("ISO-8859-1"));
      }
      localObject1 = trimCrlf(((ByteArrayOutputStream)localObject1).toByteArray());
      str = trimCrlf(str);
    }
    for (;;)
    {
      localObject2 = getFakeResponse(i, (InputStream)localObject1, (List)localObject2, localArrayList);
      parseAndCallback((BatchRequest.RequestInfo)this.requestInfos.get(this.contentId - 1), i, (HttpResponse)localObject2);
      while ((((InputStream)localObject1).skip(l) > 0L) || (((InputStream)localObject1).read() != -1)) {}
      if (l != -1L) {}
      for (str = readLine(); (str != null) && (str.length() == 0); str = readLine()) {}
      localObject1 = new FilterInputStream(ByteStreams.limit(this.inputStream, l))
      {
        public void close() {}
      };
    }
    checkForFinalBoundary(str);
  }
  
  private static class FakeLowLevelHttpRequest
    extends LowLevelHttpRequest
  {
    private List<String> headerNames;
    private List<String> headerValues;
    private InputStream partContent;
    private int statusCode;
    
    FakeLowLevelHttpRequest(InputStream paramInputStream, int paramInt, List<String> paramList1, List<String> paramList2)
    {
      this.partContent = paramInputStream;
      this.statusCode = paramInt;
      this.headerNames = paramList1;
      this.headerValues = paramList2;
    }
    
    public void addHeader(String paramString1, String paramString2) {}
    
    public LowLevelHttpResponse execute()
    {
      return new BatchUnparsedResponse.FakeLowLevelHttpResponse(this.partContent, this.statusCode, this.headerNames, this.headerValues);
    }
  }
  
  private static class FakeLowLevelHttpResponse
    extends LowLevelHttpResponse
  {
    private List<String> headerNames = new ArrayList();
    private List<String> headerValues = new ArrayList();
    private InputStream partContent;
    private int statusCode;
    
    FakeLowLevelHttpResponse(InputStream paramInputStream, int paramInt, List<String> paramList1, List<String> paramList2)
    {
      this.partContent = paramInputStream;
      this.statusCode = paramInt;
      this.headerNames = paramList1;
      this.headerValues = paramList2;
    }
    
    public InputStream getContent()
    {
      return this.partContent;
    }
    
    public String getContentEncoding()
    {
      return null;
    }
    
    public long getContentLength()
    {
      return 0L;
    }
    
    public String getContentType()
    {
      return null;
    }
    
    public int getHeaderCount()
    {
      return this.headerNames.size();
    }
    
    public String getHeaderName(int paramInt)
    {
      return (String)this.headerNames.get(paramInt);
    }
    
    public String getHeaderValue(int paramInt)
    {
      return (String)this.headerValues.get(paramInt);
    }
    
    public String getReasonPhrase()
    {
      return null;
    }
    
    public int getStatusCode()
    {
      return this.statusCode;
    }
    
    public String getStatusLine()
    {
      return null;
    }
  }
  
  private static class FakeResponseHttpTransport
    extends HttpTransport
  {
    private List<String> headerNames;
    private List<String> headerValues;
    private InputStream partContent;
    private int statusCode;
    
    FakeResponseHttpTransport(int paramInt, InputStream paramInputStream, List<String> paramList1, List<String> paramList2)
    {
      this.statusCode = paramInt;
      this.partContent = paramInputStream;
      this.headerNames = paramList1;
      this.headerValues = paramList2;
    }
    
    protected LowLevelHttpRequest buildRequest(String paramString1, String paramString2)
    {
      return new BatchUnparsedResponse.FakeLowLevelHttpRequest(this.partContent, this.statusCode, this.headerNames, this.headerValues);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\batch\BatchUnparsedResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */