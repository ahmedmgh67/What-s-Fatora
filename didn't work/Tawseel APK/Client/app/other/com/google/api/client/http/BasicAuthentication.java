package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.io.IOException;

public final class BasicAuthentication
  implements HttpRequestInitializer, HttpExecuteInterceptor
{
  private final String password;
  private final String username;
  
  public BasicAuthentication(String paramString1, String paramString2)
  {
    this.username = ((String)Preconditions.checkNotNull(paramString1));
    this.password = ((String)Preconditions.checkNotNull(paramString2));
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void initialize(HttpRequest paramHttpRequest)
    throws IOException
  {
    paramHttpRequest.setInterceptor(this);
  }
  
  public void intercept(HttpRequest paramHttpRequest)
    throws IOException
  {
    paramHttpRequest.getHeaders().setBasicAuthentication(this.username, this.password);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\BasicAuthentication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */