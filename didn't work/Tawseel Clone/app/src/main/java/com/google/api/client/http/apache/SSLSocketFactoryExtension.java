package com.google.api.client.http.apache;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.ssl.X509HostnameVerifier;

final class SSLSocketFactoryExtension
  extends org.apache.http.conn.ssl.SSLSocketFactory
{
  private final javax.net.ssl.SSLSocketFactory socketFactory;
  
  SSLSocketFactoryExtension(SSLContext paramSSLContext)
    throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException
  {
    super((KeyStore)null);
    this.socketFactory = paramSSLContext.getSocketFactory();
  }
  
  public Socket createSocket()
    throws IOException
  {
    return this.socketFactory.createSocket();
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    paramSocket = (SSLSocket)this.socketFactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    getHostnameVerifier().verify(paramString, paramSocket);
    return paramSocket;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\apache\SSLSocketFactoryExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */