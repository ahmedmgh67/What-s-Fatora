package com.google.api.client.http;

import java.io.IOException;

public abstract interface HttpResponseInterceptor
{
  public abstract void interceptResponse(HttpResponse paramHttpResponse)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpResponseInterceptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */