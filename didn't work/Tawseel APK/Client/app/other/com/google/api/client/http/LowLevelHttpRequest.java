package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;

public abstract class LowLevelHttpRequest
{
  private String contentEncoding;
  private long contentLength = -1L;
  private String contentType;
  private StreamingContent streamingContent;
  
  public abstract void addHeader(String paramString1, String paramString2)
    throws IOException;
  
  public abstract LowLevelHttpResponse execute()
    throws IOException;
  
  public final String getContentEncoding()
  {
    return this.contentEncoding;
  }
  
  public final long getContentLength()
  {
    return this.contentLength;
  }
  
  public final String getContentType()
  {
    return this.contentType;
  }
  
  public final StreamingContent getStreamingContent()
  {
    return this.streamingContent;
  }
  
  public final void setContentEncoding(String paramString)
    throws IOException
  {
    this.contentEncoding = paramString;
  }
  
  public final void setContentLength(long paramLong)
    throws IOException
  {
    this.contentLength = paramLong;
  }
  
  public final void setContentType(String paramString)
    throws IOException
  {
    this.contentType = paramString;
  }
  
  public final void setStreamingContent(StreamingContent paramStreamingContent)
    throws IOException
  {
    this.streamingContent = paramStreamingContent;
  }
  
  public void setTimeout(int paramInt1, int paramInt2)
    throws IOException
  {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\LowLevelHttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */