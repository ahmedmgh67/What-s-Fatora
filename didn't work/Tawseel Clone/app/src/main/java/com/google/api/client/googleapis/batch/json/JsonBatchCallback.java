package com.google.api.client.googleapis.batch.json;

import com.google.api.client.googleapis.batch.BatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer;
import com.google.api.client.http.HttpHeaders;
import java.io.IOException;

public abstract class JsonBatchCallback<T>
  implements BatchCallback<T, GoogleJsonErrorContainer>
{
  public abstract void onFailure(GoogleJsonError paramGoogleJsonError, HttpHeaders paramHttpHeaders)
    throws IOException;
  
  public final void onFailure(GoogleJsonErrorContainer paramGoogleJsonErrorContainer, HttpHeaders paramHttpHeaders)
    throws IOException
  {
    onFailure(paramGoogleJsonErrorContainer.getError(), paramHttpHeaders);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\batch\json\JsonBatchCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */