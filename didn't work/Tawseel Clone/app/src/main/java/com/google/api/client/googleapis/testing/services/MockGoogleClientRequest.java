package com.google.api.client.googleapis.testing.services;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Beta;

@Beta
public class MockGoogleClientRequest<T>
  extends AbstractGoogleClientRequest<T>
{
  public MockGoogleClientRequest(AbstractGoogleClient paramAbstractGoogleClient, String paramString1, String paramString2, HttpContent paramHttpContent, Class<T> paramClass)
  {
    super(paramAbstractGoogleClient, paramString1, paramString2, paramHttpContent, paramClass);
  }
  
  public MockGoogleClientRequest<T> set(String paramString, Object paramObject)
  {
    return (MockGoogleClientRequest)super.set(paramString, paramObject);
  }
  
  public MockGoogleClientRequest<T> setDisableGZipContent(boolean paramBoolean)
  {
    return (MockGoogleClientRequest)super.setDisableGZipContent(paramBoolean);
  }
  
  public MockGoogleClientRequest<T> setRequestHeaders(HttpHeaders paramHttpHeaders)
  {
    return (MockGoogleClientRequest)super.setRequestHeaders(paramHttpHeaders);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\services\MockGoogleClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */