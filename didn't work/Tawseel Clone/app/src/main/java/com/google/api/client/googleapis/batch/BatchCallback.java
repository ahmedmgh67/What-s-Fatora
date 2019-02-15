package com.google.api.client.googleapis.batch;

import com.google.api.client.http.HttpHeaders;
import java.io.IOException;

public abstract interface BatchCallback<T, E>
{
  public abstract void onFailure(E paramE, HttpHeaders paramHttpHeaders)
    throws IOException;
  
  public abstract void onSuccess(T paramT, HttpHeaders paramHttpHeaders)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\batch\BatchCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */