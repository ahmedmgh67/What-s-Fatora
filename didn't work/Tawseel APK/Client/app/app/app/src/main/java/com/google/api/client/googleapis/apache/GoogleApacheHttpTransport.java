package com.google.api.client.googleapis.apache;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport.Builder;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class GoogleApacheHttpTransport
{
  public static ApacheHttpTransport newTrustedTransport()
    throws GeneralSecurityException, IOException
  {
    return new ApacheHttpTransport.Builder().trustCertificates(GoogleUtils.getCertificateTrustStore()).build();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\apache\GoogleApacheHttpTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */