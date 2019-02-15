package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class HttpResponse
{
  private InputStream content;
  private final String contentEncoding;
  private int contentLoggingLimit;
  private boolean contentRead;
  private final String contentType;
  private boolean loggingEnabled;
  private final HttpMediaType mediaType;
  private final HttpRequest request;
  LowLevelHttpResponse response;
  private final int statusCode;
  private final String statusMessage;
  
  HttpResponse(HttpRequest paramHttpRequest, LowLevelHttpResponse paramLowLevelHttpResponse)
    throws IOException
  {
    this.request = paramHttpRequest;
    this.contentLoggingLimit = paramHttpRequest.getContentLoggingLimit();
    this.loggingEnabled = paramHttpRequest.isLoggingEnabled();
    this.response = paramLowLevelHttpResponse;
    this.contentEncoding = paramLowLevelHttpResponse.getContentEncoding();
    int j = paramLowLevelHttpResponse.getStatusCode();
    int i = j;
    if (j < 0) {
      i = 0;
    }
    this.statusCode = i;
    Object localObject1 = paramLowLevelHttpResponse.getReasonPhrase();
    this.statusMessage = ((String)localObject1);
    Logger localLogger = HttpTransport.LOGGER;
    StringBuilder localStringBuilder;
    if ((this.loggingEnabled) && (localLogger.isLoggable(Level.CONFIG)))
    {
      i = 1;
      localStringBuilder = null;
      if (i != 0)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("-------------- RESPONSE --------------").append(StringUtils.LINE_SEPARATOR);
        localObject3 = paramLowLevelHttpResponse.getStatusLine();
        if (localObject3 == null) {
          break label238;
        }
        localStringBuilder.append((String)localObject3);
        label148:
        localStringBuilder.append(StringUtils.LINE_SEPARATOR);
      }
      Object localObject3 = paramHttpRequest.getResponseHeaders();
      if (i == 0) {
        break label269;
      }
      localObject1 = localStringBuilder;
      label171:
      ((HttpHeaders)localObject3).fromHttpResponse(paramLowLevelHttpResponse, (StringBuilder)localObject1);
      localObject1 = paramLowLevelHttpResponse.getContentType();
      paramLowLevelHttpResponse = (LowLevelHttpResponse)localObject1;
      if (localObject1 == null) {
        paramLowLevelHttpResponse = paramHttpRequest.getResponseHeaders().getContentType();
      }
      this.contentType = paramLowLevelHttpResponse;
      if (paramLowLevelHttpResponse != null) {
        break label275;
      }
    }
    label238:
    label269:
    label275:
    for (paramHttpRequest = (HttpRequest)localObject2;; paramHttpRequest = new HttpMediaType(paramLowLevelHttpResponse))
    {
      this.mediaType = paramHttpRequest;
      if (i != 0) {
        localLogger.config(localStringBuilder.toString());
      }
      return;
      i = 0;
      break;
      localStringBuilder.append(this.statusCode);
      if (localObject1 == null) {
        break label148;
      }
      localStringBuilder.append(' ').append((String)localObject1);
      break label148;
      localObject1 = null;
      break label171;
    }
  }
  
  private boolean hasMessageBody()
    throws IOException
  {
    boolean bool = true;
    int i = getStatusCode();
    if ((getRequest().getRequestMethod().equals("HEAD")) || (i / 100 == 1) || (i == 204) || (i == 304))
    {
      ignore();
      bool = false;
    }
    return bool;
  }
  
  public void disconnect()
    throws IOException
  {
    ignore();
    this.response.disconnect();
  }
  
  public void download(OutputStream paramOutputStream)
    throws IOException
  {
    IOUtils.copy(getContent(), paramOutputStream);
  }
  
  /* Error */
  public InputStream getContent()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 176	com/google/api/client/http/HttpResponse:contentRead	Z
    //   4: ifne +119 -> 123
    //   7: aload_0
    //   8: getfield 47	com/google/api/client/http/HttpResponse:response	Lcom/google/api/client/http/LowLevelHttpResponse;
    //   11: invokevirtual 177	com/google/api/client/http/LowLevelHttpResponse:getContent	()Ljava/io/InputStream;
    //   14: astore_1
    //   15: aload_1
    //   16: ifnull +102 -> 118
    //   19: aload_1
    //   20: astore_3
    //   21: aload_1
    //   22: astore_2
    //   23: aload_0
    //   24: getfield 55	com/google/api/client/http/HttpResponse:contentEncoding	Ljava/lang/String;
    //   27: astore 4
    //   29: aload 4
    //   31: ifnull +137 -> 168
    //   34: aload_1
    //   35: astore_3
    //   36: aload_1
    //   37: astore_2
    //   38: aload 4
    //   40: ldc -77
    //   42: invokevirtual 183	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   45: ifeq +123 -> 168
    //   48: aload_1
    //   49: astore_3
    //   50: aload_1
    //   51: astore_2
    //   52: new 185	java/util/zip/GZIPInputStream
    //   55: dup
    //   56: aload_1
    //   57: invokespecial 188	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   60: astore_1
    //   61: getstatic 71	com/google/api/client/http/HttpTransport:LOGGER	Ljava/util/logging/Logger;
    //   64: astore_2
    //   65: aload_0
    //   66: getfield 45	com/google/api/client/http/HttpResponse:loggingEnabled	Z
    //   69: ifeq +96 -> 165
    //   72: aload_2
    //   73: getstatic 77	java/util/logging/Level:CONFIG	Ljava/util/logging/Level;
    //   76: invokevirtual 83	java/util/logging/Logger:isLoggable	(Ljava/util/logging/Level;)Z
    //   79: ifeq +86 -> 165
    //   82: new 190	com/google/api/client/util/LoggingInputStream
    //   85: dup
    //   86: aload_1
    //   87: aload_2
    //   88: getstatic 77	java/util/logging/Level:CONFIG	Ljava/util/logging/Level;
    //   91: aload_0
    //   92: getfield 39	com/google/api/client/http/HttpResponse:contentLoggingLimit	I
    //   95: invokespecial 193	com/google/api/client/util/LoggingInputStream:<init>	(Ljava/io/InputStream;Ljava/util/logging/Logger;Ljava/util/logging/Level;I)V
    //   98: astore_2
    //   99: aload_2
    //   100: astore_1
    //   101: aload_1
    //   102: astore_3
    //   103: aload_1
    //   104: astore_2
    //   105: aload_0
    //   106: aload_1
    //   107: putfield 195	com/google/api/client/http/HttpResponse:content	Ljava/io/InputStream;
    //   110: iconst_1
    //   111: ifne +7 -> 118
    //   114: aload_1
    //   115: invokevirtual 200	java/io/InputStream:close	()V
    //   118: aload_0
    //   119: iconst_1
    //   120: putfield 176	com/google/api/client/http/HttpResponse:contentRead	Z
    //   123: aload_0
    //   124: getfield 195	com/google/api/client/http/HttpResponse:content	Ljava/io/InputStream;
    //   127: areturn
    //   128: astore_1
    //   129: iconst_0
    //   130: ifne -12 -> 118
    //   133: aload_3
    //   134: invokevirtual 200	java/io/InputStream:close	()V
    //   137: goto -19 -> 118
    //   140: astore_1
    //   141: iconst_0
    //   142: ifne +7 -> 149
    //   145: aload_2
    //   146: invokevirtual 200	java/io/InputStream:close	()V
    //   149: aload_1
    //   150: athrow
    //   151: astore_3
    //   152: aload_1
    //   153: astore_2
    //   154: aload_3
    //   155: astore_1
    //   156: goto -15 -> 141
    //   159: astore_2
    //   160: aload_1
    //   161: astore_3
    //   162: goto -33 -> 129
    //   165: goto -64 -> 101
    //   168: goto -107 -> 61
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	171	0	this	HttpResponse
    //   14	101	1	localObject1	Object
    //   128	1	1	localEOFException1	java.io.EOFException
    //   140	13	1	localObject2	Object
    //   155	6	1	localObject3	Object
    //   22	132	2	localObject4	Object
    //   159	1	2	localEOFException2	java.io.EOFException
    //   20	114	3	localObject5	Object
    //   151	4	3	localObject6	Object
    //   161	1	3	localObject7	Object
    //   27	12	4	str	String
    // Exception table:
    //   from	to	target	type
    //   23	29	128	java/io/EOFException
    //   38	48	128	java/io/EOFException
    //   52	61	128	java/io/EOFException
    //   105	110	128	java/io/EOFException
    //   23	29	140	finally
    //   38	48	140	finally
    //   52	61	140	finally
    //   105	110	140	finally
    //   61	99	151	finally
    //   61	99	159	java/io/EOFException
  }
  
  public Charset getContentCharset()
  {
    if ((this.mediaType == null) || (this.mediaType.getCharsetParameter() == null)) {
      return Charsets.ISO_8859_1;
    }
    return this.mediaType.getCharsetParameter();
  }
  
  public String getContentEncoding()
  {
    return this.contentEncoding;
  }
  
  public int getContentLoggingLimit()
  {
    return this.contentLoggingLimit;
  }
  
  public String getContentType()
  {
    return this.contentType;
  }
  
  public HttpHeaders getHeaders()
  {
    return this.request.getResponseHeaders();
  }
  
  public HttpMediaType getMediaType()
  {
    return this.mediaType;
  }
  
  public HttpRequest getRequest()
  {
    return this.request;
  }
  
  public int getStatusCode()
  {
    return this.statusCode;
  }
  
  public String getStatusMessage()
  {
    return this.statusMessage;
  }
  
  public HttpTransport getTransport()
  {
    return this.request.getTransport();
  }
  
  public void ignore()
    throws IOException
  {
    InputStream localInputStream = getContent();
    if (localInputStream != null) {
      localInputStream.close();
    }
  }
  
  public boolean isLoggingEnabled()
  {
    return this.loggingEnabled;
  }
  
  public boolean isSuccessStatusCode()
  {
    return HttpStatusCodes.isSuccess(this.statusCode);
  }
  
  public <T> T parseAs(Class<T> paramClass)
    throws IOException
  {
    if (!hasMessageBody()) {
      return null;
    }
    return (T)this.request.getParser().parseAndClose(getContent(), getContentCharset(), paramClass);
  }
  
  public Object parseAs(Type paramType)
    throws IOException
  {
    if (!hasMessageBody()) {
      return null;
    }
    return this.request.getParser().parseAndClose(getContent(), getContentCharset(), paramType);
  }
  
  public String parseAsString()
    throws IOException
  {
    InputStream localInputStream = getContent();
    if (localInputStream == null) {
      return "";
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    IOUtils.copy(localInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toString(getContentCharset().name());
  }
  
  public HttpResponse setContentLoggingLimit(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "The content logging limit must be non-negative.");
      this.contentLoggingLimit = paramInt;
      return this;
    }
  }
  
  public HttpResponse setLoggingEnabled(boolean paramBoolean)
  {
    this.loggingEnabled = paramBoolean;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */