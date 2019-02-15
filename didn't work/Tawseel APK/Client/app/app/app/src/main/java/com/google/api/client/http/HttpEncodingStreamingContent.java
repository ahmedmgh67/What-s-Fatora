package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

public final class HttpEncodingStreamingContent
  implements StreamingContent
{
  private final StreamingContent content;
  private final HttpEncoding encoding;
  
  public HttpEncodingStreamingContent(StreamingContent paramStreamingContent, HttpEncoding paramHttpEncoding)
  {
    this.content = ((StreamingContent)Preconditions.checkNotNull(paramStreamingContent));
    this.encoding = ((HttpEncoding)Preconditions.checkNotNull(paramHttpEncoding));
  }
  
  public StreamingContent getContent()
  {
    return this.content;
  }
  
  public HttpEncoding getEncoding()
  {
    return this.encoding;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    this.encoding.encode(this.content, paramOutputStream);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpEncodingStreamingContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */