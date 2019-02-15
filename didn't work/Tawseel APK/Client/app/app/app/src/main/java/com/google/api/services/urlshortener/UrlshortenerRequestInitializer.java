package com.google.api.services.urlshortener;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer;
import java.io.IOException;

public class UrlshortenerRequestInitializer
  extends CommonGoogleJsonClientRequestInitializer
{
  public UrlshortenerRequestInitializer() {}
  
  public UrlshortenerRequestInitializer(String paramString)
  {
    super(paramString);
  }
  
  public UrlshortenerRequestInitializer(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public final void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> paramAbstractGoogleJsonClientRequest)
    throws IOException
  {
    super.initializeJsonRequest(paramAbstractGoogleJsonClientRequest);
    initializeUrlshortenerRequest((UrlshortenerRequest)paramAbstractGoogleJsonClientRequest);
  }
  
  protected void initializeUrlshortenerRequest(UrlshortenerRequest<?> paramUrlshortenerRequest)
    throws IOException
  {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\UrlshortenerRequestInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */