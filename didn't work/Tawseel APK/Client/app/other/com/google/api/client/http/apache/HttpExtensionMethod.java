package com.google.api.client.http.apache;

import com.google.api.client.util.Preconditions;
import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

final class HttpExtensionMethod
  extends HttpEntityEnclosingRequestBase
{
  private final String methodName;
  
  public HttpExtensionMethod(String paramString1, String paramString2)
  {
    this.methodName = ((String)Preconditions.checkNotNull(paramString1));
    setURI(URI.create(paramString2));
  }
  
  public String getMethod()
  {
    return this.methodName;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\apache\HttpExtensionMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */