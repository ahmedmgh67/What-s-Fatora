package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipEncoding
  implements HttpEncoding
{
  public void encode(StreamingContent paramStreamingContent, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = new GZIPOutputStream(new BufferedOutputStream(paramOutputStream)
    {
      public void close()
        throws IOException
      {
        try
        {
          flush();
          return;
        }
        catch (IOException localIOException) {}
      }
    });
    paramStreamingContent.writeTo(paramOutputStream);
    paramOutputStream.close();
  }
  
  public String getName()
  {
    return "gzip";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\GZipEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */