package com.google.api.client.googleapis;

import com.google.api.client.util.SecurityUtils;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

public final class GoogleUtils
{
  public static final Integer BUGFIX_VERSION = Integer.valueOf(0);
  public static final Integer MAJOR_VERSION = Integer.valueOf(1);
  public static final Integer MINOR_VERSION = Integer.valueOf(22);
  public static final String VERSION = (MAJOR_VERSION + "." + MINOR_VERSION + "." + BUGFIX_VERSION + "").toString();
  static KeyStore certTrustStore;
  
  public static KeyStore getCertificateTrustStore()
    throws IOException, GeneralSecurityException
  {
    try
    {
      if (certTrustStore == null)
      {
        certTrustStore = SecurityUtils.getJavaKeyStore();
        localObject1 = GoogleUtils.class.getResourceAsStream("google.jks");
        SecurityUtils.loadKeyStore(certTrustStore, (InputStream)localObject1, "notasecret");
      }
      Object localObject1 = certTrustStore;
      return (KeyStore)localObject1;
    }
    finally {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\GoogleUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */