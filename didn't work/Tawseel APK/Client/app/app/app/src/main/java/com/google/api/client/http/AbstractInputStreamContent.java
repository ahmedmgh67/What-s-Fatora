package com.google.api.client.http;

import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractInputStreamContent
  implements HttpContent
{
  private boolean closeInputStream = true;
  private String type;
  
  public AbstractInputStreamContent(String paramString)
  {
    setType(paramString);
  }
  
  public final boolean getCloseInputStream()
  {
    return this.closeInputStream;
  }
  
  public abstract InputStream getInputStream()
    throws IOException;
  
  public String getType()
  {
    return this.type;
  }
  
  public AbstractInputStreamContent setCloseInputStream(boolean paramBoolean)
  {
    this.closeInputStream = paramBoolean;
    return this;
  }
  
  public AbstractInputStreamContent setType(String paramString)
  {
    this.type = paramString;
    return this;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    IOUtils.copy(getInputStream(), paramOutputStream, this.closeInputStream);
    paramOutputStream.flush();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\AbstractInputStreamContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */