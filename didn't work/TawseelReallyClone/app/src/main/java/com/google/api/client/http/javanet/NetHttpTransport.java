package com.google.api.client.http.javanet;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.SslUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class NetHttpTransport
  extends HttpTransport
{
  private static final String[] SUPPORTED_METHODS = { "DELETE", "GET", "HEAD", "OPTIONS", "POST", "PUT", "TRACE" };
  private final ConnectionFactory connectionFactory;
  private final HostnameVerifier hostnameVerifier;
  private final SSLSocketFactory sslSocketFactory;
  
  static
  {
    Arrays.sort(SUPPORTED_METHODS);
  }
  
  public NetHttpTransport()
  {
    this((ConnectionFactory)null, null, null);
  }
  
  NetHttpTransport(ConnectionFactory paramConnectionFactory, SSLSocketFactory paramSSLSocketFactory, HostnameVerifier paramHostnameVerifier)
  {
    Object localObject = paramConnectionFactory;
    if (paramConnectionFactory == null) {
      localObject = new DefaultConnectionFactory();
    }
    this.connectionFactory = ((ConnectionFactory)localObject);
    this.sslSocketFactory = paramSSLSocketFactory;
    this.hostnameVerifier = paramHostnameVerifier;
  }
  
  NetHttpTransport(Proxy paramProxy, SSLSocketFactory paramSSLSocketFactory, HostnameVerifier paramHostnameVerifier)
  {
    this(new DefaultConnectionFactory(paramProxy), paramSSLSocketFactory, paramHostnameVerifier);
  }
  
  protected NetHttpRequest buildRequest(String paramString1, String paramString2)
    throws IOException
  {
    Preconditions.checkArgument(supportsMethod(paramString1), "HTTP method %s not supported", new Object[] { paramString1 });
    paramString2 = new URL(paramString2);
    paramString2 = this.connectionFactory.openConnection(paramString2);
    paramString2.setRequestMethod(paramString1);
    if ((paramString2 instanceof HttpsURLConnection))
    {
      paramString1 = (HttpsURLConnection)paramString2;
      if (this.hostnameVerifier != null) {
        paramString1.setHostnameVerifier(this.hostnameVerifier);
      }
      if (this.sslSocketFactory != null) {
        paramString1.setSSLSocketFactory(this.sslSocketFactory);
      }
    }
    return new NetHttpRequest(paramString2);
  }
  
  public boolean supportsMethod(String paramString)
  {
    return Arrays.binarySearch(SUPPORTED_METHODS, paramString) >= 0;
  }
  
  public static final class Builder
  {
    private ConnectionFactory connectionFactory;
    private HostnameVerifier hostnameVerifier;
    private Proxy proxy;
    private SSLSocketFactory sslSocketFactory;
    
    public NetHttpTransport build()
    {
      if (this.proxy == null) {
        return new NetHttpTransport(this.connectionFactory, this.sslSocketFactory, this.hostnameVerifier);
      }
      return new NetHttpTransport(this.proxy, this.sslSocketFactory, this.hostnameVerifier);
    }
    
    @Beta
    public Builder doNotValidateCertificate()
      throws GeneralSecurityException
    {
      this.hostnameVerifier = SslUtils.trustAllHostnameVerifier();
      this.sslSocketFactory = SslUtils.trustAllSSLContext().getSocketFactory();
      return this;
    }
    
    public HostnameVerifier getHostnameVerifier()
    {
      return this.hostnameVerifier;
    }
    
    public SSLSocketFactory getSslSocketFactory()
    {
      return this.sslSocketFactory;
    }
    
    public Builder setConnectionFactory(ConnectionFactory paramConnectionFactory)
    {
      this.connectionFactory = paramConnectionFactory;
      return this;
    }
    
    public Builder setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
    {
      this.hostnameVerifier = paramHostnameVerifier;
      return this;
    }
    
    public Builder setProxy(Proxy paramProxy)
    {
      this.proxy = paramProxy;
      return this;
    }
    
    public Builder setSslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
    {
      this.sslSocketFactory = paramSSLSocketFactory;
      return this;
    }
    
    public Builder trustCertificates(KeyStore paramKeyStore)
      throws GeneralSecurityException
    {
      SSLContext localSSLContext = SslUtils.getTlsSslContext();
      SslUtils.initSslContext(localSSLContext, paramKeyStore, SslUtils.getPkixTrustManagerFactory());
      return setSslSocketFactory(localSSLContext.getSocketFactory());
    }
    
    public Builder trustCertificatesFromJavaKeyStore(InputStream paramInputStream, String paramString)
      throws GeneralSecurityException, IOException
    {
      KeyStore localKeyStore = SecurityUtils.getJavaKeyStore();
      SecurityUtils.loadKeyStore(localKeyStore, paramInputStream, paramString);
      return trustCertificates(localKeyStore);
    }
    
    public Builder trustCertificatesFromStream(InputStream paramInputStream)
      throws GeneralSecurityException, IOException
    {
      KeyStore localKeyStore = SecurityUtils.getJavaKeyStore();
      localKeyStore.load(null, null);
      SecurityUtils.loadKeyStoreFromCertificates(localKeyStore, SecurityUtils.getX509CertificateFactory(), paramInputStream);
      return trustCertificates(localKeyStore);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\javanet\NetHttpTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */