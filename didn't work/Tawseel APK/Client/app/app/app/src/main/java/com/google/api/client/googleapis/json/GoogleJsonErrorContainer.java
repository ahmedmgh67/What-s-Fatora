package com.google.api.client.googleapis.json;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class GoogleJsonErrorContainer
  extends GenericJson
{
  @Key
  private GoogleJsonError error;
  
  public GoogleJsonErrorContainer clone()
  {
    return (GoogleJsonErrorContainer)super.clone();
  }
  
  public final GoogleJsonError getError()
  {
    return this.error;
  }
  
  public GoogleJsonErrorContainer set(String paramString, Object paramObject)
  {
    return (GoogleJsonErrorContainer)super.set(paramString, paramObject);
  }
  
  public final void setError(GoogleJsonError paramGoogleJsonError)
  {
    this.error = paramGoogleJsonError;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\json\GoogleJsonErrorContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */