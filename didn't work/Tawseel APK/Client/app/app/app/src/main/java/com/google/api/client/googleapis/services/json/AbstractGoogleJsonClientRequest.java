package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import java.io.IOException;

public abstract class AbstractGoogleJsonClientRequest<T>
  extends AbstractGoogleClientRequest<T>
{
  private final Object jsonContent;
  
  protected AbstractGoogleJsonClientRequest(AbstractGoogleJsonClient paramAbstractGoogleJsonClient, String paramString1, String paramString2, Object paramObject, Class<T> paramClass) {}
  
  public AbstractGoogleJsonClient getAbstractGoogleClient()
  {
    return (AbstractGoogleJsonClient)super.getAbstractGoogleClient();
  }
  
  public Object getJsonContent()
  {
    return this.jsonContent;
  }
  
  protected GoogleJsonResponseException newExceptionOnError(HttpResponse paramHttpResponse)
  {
    return GoogleJsonResponseException.from(getAbstractGoogleClient().getJsonFactory(), paramHttpResponse);
  }
  
  public final void queue(BatchRequest paramBatchRequest, JsonBatchCallback<T> paramJsonBatchCallback)
    throws IOException
  {
    super.queue(paramBatchRequest, GoogleJsonErrorContainer.class, paramJsonBatchCallback);
  }
  
  public AbstractGoogleJsonClientRequest<T> set(String paramString, Object paramObject)
  {
    return (AbstractGoogleJsonClientRequest)super.set(paramString, paramObject);
  }
  
  public AbstractGoogleJsonClientRequest<T> setDisableGZipContent(boolean paramBoolean)
  {
    return (AbstractGoogleJsonClientRequest)super.setDisableGZipContent(paramBoolean);
  }
  
  public AbstractGoogleJsonClientRequest<T> setRequestHeaders(HttpHeaders paramHttpHeaders)
  {
    return (AbstractGoogleJsonClientRequest)super.setRequestHeaders(paramHttpHeaders);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\json\AbstractGoogleJsonClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */