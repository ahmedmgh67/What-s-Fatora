package com.google.api.client.testing.http;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public class MockHttpUnsuccessfulResponseHandler
  implements HttpUnsuccessfulResponseHandler
{
  private boolean isCalled;
  private boolean successfullyHandleResponse;
  
  public MockHttpUnsuccessfulResponseHandler(boolean paramBoolean)
  {
    this.successfullyHandleResponse = paramBoolean;
  }
  
  public boolean handleResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, boolean paramBoolean)
    throws IOException
  {
    this.isCalled = true;
    return this.successfullyHandleResponse;
  }
  
  public boolean isCalled()
  {
    return this.isCalled;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\MockHttpUnsuccessfulResponseHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */