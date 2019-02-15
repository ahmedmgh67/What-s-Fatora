package com.google.api.client.http.apache;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;

final class ContentEntity
  extends AbstractHttpEntity
{
  private final long contentLength;
  private final StreamingContent streamingContent;
  
  ContentEntity(long paramLong, StreamingContent paramStreamingContent)
  {
    this.contentLength = paramLong;
    this.streamingContent = ((StreamingContent)Preconditions.checkNotNull(paramStreamingContent));
  }
  
  public InputStream getContent()
  {
    throw new UnsupportedOperationException();
  }
  
  public long getContentLength()
  {
    return this.contentLength;
  }
  
  public boolean isRepeatable()
  {
    return false;
  }
  
  public boolean isStreaming()
  {
    return true;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (this.contentLength != 0L) {
      this.streamingContent.writeTo(paramOutputStream);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\apache\ContentEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */