package com.google.api.client.http;

import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public abstract interface HttpIOExceptionHandler
{
  public abstract boolean handleIOException(HttpRequest paramHttpRequest, boolean paramBoolean)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpIOExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */