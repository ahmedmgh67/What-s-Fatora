package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.io.InputStream;

public final class InputStreamContent
  extends AbstractInputStreamContent
{
  private final InputStream inputStream;
  private long length = -1L;
  private boolean retrySupported;
  
  public InputStreamContent(String paramString, InputStream paramInputStream)
  {
    super(paramString);
    this.inputStream = ((InputStream)Preconditions.checkNotNull(paramInputStream));
  }
  
  public InputStream getInputStream()
  {
    return this.inputStream;
  }
  
  public long getLength()
  {
    return this.length;
  }
  
  public boolean retrySupported()
  {
    return this.retrySupported;
  }
  
  public InputStreamContent setCloseInputStream(boolean paramBoolean)
  {
    return (InputStreamContent)super.setCloseInputStream(paramBoolean);
  }
  
  public InputStreamContent setLength(long paramLong)
  {
    this.length = paramLong;
    return this;
  }
  
  public InputStreamContent setRetrySupported(boolean paramBoolean)
  {
    this.retrySupported = paramBoolean;
    return this;
  }
  
  public InputStreamContent setType(String paramString)
  {
    return (InputStreamContent)super.setType(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\InputStreamContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */