package com.google.api.client.googleapis.testing.services.json;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Beta;

@Beta
public class MockGoogleJsonClientRequest<T>
  extends AbstractGoogleJsonClientRequest<T>
{
  public MockGoogleJsonClientRequest(AbstractGoogleJsonClient paramAbstractGoogleJsonClient, String paramString1, String paramString2, Object paramObject, Class<T> paramClass)
  {
    super(paramAbstractGoogleJsonClient, paramString1, paramString2, paramObject, paramClass);
  }
  
  public MockGoogleJsonClient getAbstractGoogleClient()
  {
    return (MockGoogleJsonClient)super.getAbstractGoogleClient();
  }
  
  public MockGoogleJsonClientRequest<T> setDisableGZipContent(boolean paramBoolean)
  {
    return (MockGoogleJsonClientRequest)super.setDisableGZipContent(paramBoolean);
  }
  
  public MockGoogleJsonClientRequest<T> setRequestHeaders(HttpHeaders paramHttpHeaders)
  {
    return (MockGoogleJsonClientRequest)super.setRequestHeaders(paramHttpHeaders);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\services\json\MockGoogleJsonClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */