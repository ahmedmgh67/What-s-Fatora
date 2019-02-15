package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

public abstract interface HttpEncoding
{
  public abstract void encode(StreamingContent paramStreamingContent, OutputStream paramOutputStream)
    throws IOException;
  
  public abstract String getName();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */