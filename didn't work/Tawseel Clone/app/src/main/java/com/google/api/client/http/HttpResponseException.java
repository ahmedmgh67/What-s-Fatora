package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;

public class HttpResponseException
  extends IOException
{
  private static final long serialVersionUID = -1875819453475890043L;
  private final String content;
  private final transient HttpHeaders headers;
  private final int statusCode;
  private final String statusMessage;
  
  public HttpResponseException(HttpResponse paramHttpResponse)
  {
    this(new Builder(paramHttpResponse));
  }
  
  protected HttpResponseException(Builder paramBuilder)
  {
    super(paramBuilder.message);
    this.statusCode = paramBuilder.statusCode;
    this.statusMessage = paramBuilder.statusMessage;
    this.headers = paramBuilder.headers;
    this.content = paramBuilder.content;
  }
  
  public static StringBuilder computeMessageBuffer(HttpResponse paramHttpResponse)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramHttpResponse.getStatusCode();
    if (i != 0) {
      localStringBuilder.append(i);
    }
    paramHttpResponse = paramHttpResponse.getStatusMessage();
    if (paramHttpResponse != null)
    {
      if (i != 0) {
        localStringBuilder.append(' ');
      }
      localStringBuilder.append(paramHttpResponse);
    }
    return localStringBuilder;
  }
  
  public final String getContent()
  {
    return this.content;
  }
  
  public HttpHeaders getHeaders()
  {
    return this.headers;
  }
  
  public final int getStatusCode()
  {
    return this.statusCode;
  }
  
  public final String getStatusMessage()
  {
    return this.statusMessage;
  }
  
  public final boolean isSuccessStatusCode()
  {
    return HttpStatusCodes.isSuccess(this.statusCode);
  }
  
  public static class Builder
  {
    String content;
    HttpHeaders headers;
    String message;
    int statusCode;
    String statusMessage;
    
    public Builder(int paramInt, String paramString, HttpHeaders paramHttpHeaders)
    {
      setStatusCode(paramInt);
      setStatusMessage(paramString);
      setHeaders(paramHttpHeaders);
    }
    
    public Builder(HttpResponse paramHttpResponse)
    {
      this(paramHttpResponse.getStatusCode(), paramHttpResponse.getStatusMessage(), paramHttpResponse.getHeaders());
      try
      {
        this.content = paramHttpResponse.parseAsString();
        if (this.content.length() == 0) {
          this.content = null;
        }
        paramHttpResponse = HttpResponseException.computeMessageBuffer(paramHttpResponse);
        if (this.content != null) {
          paramHttpResponse.append(StringUtils.LINE_SEPARATOR).append(this.content);
        }
        this.message = paramHttpResponse.toString();
        return;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          localIOException.printStackTrace();
        }
      }
    }
    
    public HttpResponseException build()
    {
      return new HttpResponseException(this);
    }
    
    public final String getContent()
    {
      return this.content;
    }
    
    public HttpHeaders getHeaders()
    {
      return this.headers;
    }
    
    public final String getMessage()
    {
      return this.message;
    }
    
    public final int getStatusCode()
    {
      return this.statusCode;
    }
    
    public final String getStatusMessage()
    {
      return this.statusMessage;
    }
    
    public Builder setContent(String paramString)
    {
      this.content = paramString;
      return this;
    }
    
    public Builder setHeaders(HttpHeaders paramHttpHeaders)
    {
      this.headers = ((HttpHeaders)Preconditions.checkNotNull(paramHttpHeaders));
      return this;
    }
    
    public Builder setMessage(String paramString)
    {
      this.message = paramString;
      return this;
    }
    
    public Builder setStatusCode(int paramInt)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool);
        this.statusCode = paramInt;
        return this;
      }
    }
    
    public Builder setStatusMessage(String paramString)
    {
      this.statusMessage = paramString;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpResponseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */