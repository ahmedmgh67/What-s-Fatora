package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractHttpContent
  implements HttpContent
{
  private long computedLength = -1L;
  private HttpMediaType mediaType;
  
  protected AbstractHttpContent(HttpMediaType paramHttpMediaType)
  {
    this.mediaType = paramHttpMediaType;
  }
  
  protected AbstractHttpContent(String paramString) {}
  
  public static long computeLength(HttpContent paramHttpContent)
    throws IOException
  {
    if (!paramHttpContent.retrySupported()) {
      return -1L;
    }
    return IOUtils.computeLength(paramHttpContent);
  }
  
  protected long computeLength()
    throws IOException
  {
    return computeLength(this);
  }
  
  protected final Charset getCharset()
  {
    if ((this.mediaType == null) || (this.mediaType.getCharsetParameter() == null)) {
      return Charsets.UTF_8;
    }
    return this.mediaType.getCharsetParameter();
  }
  
  public long getLength()
    throws IOException
  {
    if (this.computedLength == -1L) {
      this.computedLength = computeLength();
    }
    return this.computedLength;
  }
  
  public final HttpMediaType getMediaType()
  {
    return this.mediaType;
  }
  
  public String getType()
  {
    if (this.mediaType == null) {
      return null;
    }
    return this.mediaType.build();
  }
  
  public boolean retrySupported()
  {
    return true;
  }
  
  public AbstractHttpContent setMediaType(HttpMediaType paramHttpMediaType)
  {
    this.mediaType = paramHttpMediaType;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\AbstractHttpContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */