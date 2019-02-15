package com.google.api.client.googleapis.testing.json;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockHttpTransport.Builder;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public final class GoogleJsonResponseExceptionFactoryTesting
{
  public static GoogleJsonResponseException newMock(JsonFactory paramJsonFactory, int paramInt, String paramString)
    throws IOException
  {
    paramString = new MockLowLevelHttpResponse().setStatusCode(paramInt).setReasonPhrase(paramString);
    paramString = new MockHttpTransport.Builder().setLowLevelHttpResponse(paramString).build().createRequestFactory().buildGetRequest(HttpTesting.SIMPLE_GENERIC_URL);
    paramString.setThrowExceptionOnExecuteError(false);
    return GoogleJsonResponseException.from(paramJsonFactory, paramString.execute());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\json\GoogleJsonResponseExceptionFactoryTesting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */