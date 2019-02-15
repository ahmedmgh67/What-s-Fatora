package com.google.api.client.googleapis.services;

import java.io.IOException;

public abstract interface GoogleClientRequestInitializer
{
  public abstract void initialize(AbstractGoogleClientRequest<?> paramAbstractGoogleClientRequest)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\GoogleClientRequestInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */