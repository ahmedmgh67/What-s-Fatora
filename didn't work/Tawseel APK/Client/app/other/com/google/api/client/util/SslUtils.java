package com.google.api.client.util;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class SslUtils
{
  public static KeyManagerFactory getDefaultKeyManagerFactory()
    throws NoSuchAlgorithmException
  {
    return KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
  }
  
  public static TrustManagerFactory getDefaultTrustManagerFactory()
    throws NoSuchAlgorithmException
  {
    return TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
  }
  
  public static KeyManagerFactory getPkixKeyManagerFactory()
    throws NoSuchAlgorithmException
  {
    return KeyManagerFactory.getInstance("PKIX");
  }
  
  public static TrustManagerFactory getPkixTrustManagerFactory()
    throws NoSuchAlgorithmException
  {
    return TrustManagerFactory.getInstance("PKIX");
  }
  
  public static SSLContext getSslContext()
    throws NoSuchAlgorithmException
  {
    return SSLContext.getInstance("SSL");
  }
  
  public static SSLContext getTlsSslContext()
    throws NoSuchAlgorithmException
  {
    return SSLContext.getInstance("TLS");
  }
  
  public static SSLContext initSslContext(SSLContext paramSSLContext, KeyStore paramKeyStore, TrustManagerFactory paramTrustManagerFactory)
    throws GeneralSecurityException
  {
    paramTrustManagerFactory.init(paramKeyStore);
    paramSSLContext.init(null, paramTrustManagerFactory.getTrustManagers(), null);
    return paramSSLContext;
  }
  
  @Beta
  public static HostnameVerifier trustAllHostnameVerifier()
  {
    new HostnameVerifier()
    {
      public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
      {
        return true;
      }
    };
  }
  
  @Beta
  public static SSLContext trustAllSSLContext()
    throws GeneralSecurityException
  {
    X509TrustManager local1 = new X509TrustManager()
    {
      public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
        throws CertificateException
      {}
      
      public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
        throws CertificateException
      {}
      
      public X509Certificate[] getAcceptedIssuers()
      {
        return null;
      }
    };
    SSLContext localSSLContext = getTlsSslContext();
    localSSLContext.init(null, new TrustManager[] { local1 }, null);
    return localSSLContext;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\SslUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */