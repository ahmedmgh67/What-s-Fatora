package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import java.io.IOException;

public class CommonGoogleJsonClientRequestInitializer
  extends CommonGoogleClientRequestInitializer
{
  public CommonGoogleJsonClientRequestInitializer() {}
  
  public CommonGoogleJsonClientRequestInitializer(String paramString)
  {
    super(paramString);
  }
  
  public CommonGoogleJsonClientRequestInitializer(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public final void initialize(AbstractGoogleClientRequest<?> paramAbstractGoogleClientRequest)
    throws IOException
  {
    super.initialize(paramAbstractGoogleClientRequest);
    initializeJsonRequest((AbstractGoogleJsonClientRequest)paramAbstractGoogleClientRequest);
  }
  
  protected void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> paramAbstractGoogleJsonClientRequest)
    throws IOException
  {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\json\CommonGoogleJsonClientRequestInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */