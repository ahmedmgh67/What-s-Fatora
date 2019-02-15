package com.google.api.client.http.apache;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.SslUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public final class ApacheHttpTransport
  extends HttpTransport
{
  private final HttpClient httpClient;
  
  public ApacheHttpTransport()
  {
    this(newDefaultHttpClient());
  }
  
  public ApacheHttpTransport(HttpClient paramHttpClient)
  {
    this.httpClient = paramHttpClient;
    paramHttpClient = paramHttpClient.getParams();
    HttpProtocolParams.setVersion(paramHttpClient, HttpVersion.HTTP_1_1);
    paramHttpClient.setBooleanParameter("http.protocol.handle-redirects", false);
  }
  
  public static DefaultHttpClient newDefaultHttpClient()
  {
    return newDefaultHttpClient(SSLSocketFactory.getSocketFactory(), newDefaultHttpParams(), ProxySelector.getDefault());
  }
  
  static DefaultHttpClient newDefaultHttpClient(SSLSocketFactory paramSSLSocketFactory, HttpParams paramHttpParams, ProxySelector paramProxySelector)
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", paramSSLSocketFactory, 443));
    paramSSLSocketFactory = new DefaultHttpClient(new ThreadSafeClientConnManager(paramHttpParams, localSchemeRegistry), paramHttpParams);
    paramSSLSocketFactory.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
    if (paramProxySelector != null) {
      paramSSLSocketFactory.setRoutePlanner(new ProxySelectorRoutePlanner(localSchemeRegistry, paramProxySelector));
    }
    return paramSSLSocketFactory;
  }
  
  static HttpParams newDefaultHttpParams()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 200);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(20));
    return localBasicHttpParams;
  }
  
  protected ApacheHttpRequest buildRequest(String paramString1, String paramString2)
  {
    if (paramString1.equals("DELETE")) {
      paramString1 = new HttpDelete(paramString2);
    }
    for (;;)
    {
      return new ApacheHttpRequest(this.httpClient, paramString1);
      if (paramString1.equals("GET")) {
        paramString1 = new HttpGet(paramString2);
      } else if (paramString1.equals("HEAD")) {
        paramString1 = new HttpHead(paramString2);
      } else if (paramString1.equals("POST")) {
        paramString1 = new HttpPost(paramString2);
      } else if (paramString1.equals("PUT")) {
        paramString1 = new HttpPut(paramString2);
      } else if (paramString1.equals("TRACE")) {
        paramString1 = new HttpTrace(paramString2);
      } else if (paramString1.equals("OPTIONS")) {
        paramString1 = new HttpOptions(paramString2);
      } else {
        paramString1 = new HttpExtensionMethod(paramString1, paramString2);
      }
    }
  }
  
  public HttpClient getHttpClient()
  {
    return this.httpClient;
  }
  
  public void shutdown()
  {
    this.httpClient.getConnectionManager().shutdown();
  }
  
  public boolean supportsMethod(String paramString)
  {
    return true;
  }
  
  public static final class Builder
  {
    private HttpParams params = ApacheHttpTransport.newDefaultHttpParams();
    private ProxySelector proxySelector = ProxySelector.getDefault();
    private SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
    
    public ApacheHttpTransport build()
    {
      return new ApacheHttpTransport(ApacheHttpTransport.newDefaultHttpClient(this.socketFactory, this.params, this.proxySelector));
    }
    
    @Beta
    public Builder doNotValidateCertificate()
      throws GeneralSecurityException
    {
      this.socketFactory = new SSLSocketFactoryExtension(SslUtils.trustAllSSLContext());
      this.socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      return this;
    }
    
    public HttpParams getHttpParams()
    {
      return this.params;
    }
    
    public SSLSocketFactory getSSLSocketFactory()
    {
      return this.socketFactory;
    }
    
    public Builder setProxy(HttpHost paramHttpHost)
    {
      ConnRouteParams.setDefaultProxy(this.params, paramHttpHost);
      if (paramHttpHost != null) {
        this.proxySelector = null;
      }
      return this;
    }
    
    public Builder setProxySelector(ProxySelector paramProxySelector)
    {
      this.proxySelector = paramProxySelector;
      if (paramProxySelector != null) {
        ConnRouteParams.setDefaultProxy(this.params, null);
      }
      return this;
    }
    
    public Builder setSocketFactory(SSLSocketFactory paramSSLSocketFactory)
    {
      this.socketFactory = ((SSLSocketFactory)Preconditions.checkNotNull(paramSSLSocketFactory));
      return this;
    }
    
    public Builder trustCertificates(KeyStore paramKeyStore)
      throws GeneralSecurityException
    {
      SSLContext localSSLContext = SslUtils.getTlsSslContext();
      SslUtils.initSslContext(localSSLContext, paramKeyStore, SslUtils.getPkixTrustManagerFactory());
      return setSocketFactory(new SSLSocketFactoryExtension(localSSLContext));
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


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\apache\ApacheHttpTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */