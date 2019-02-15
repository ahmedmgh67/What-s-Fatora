package com.google.api.client.googleapis;

import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import java.io.IOException;

public final class MethodOverride
  implements HttpExecuteInterceptor, HttpRequestInitializer
{
  public static final String HEADER = "X-HTTP-Method-Override";
  static final int MAX_URL_LENGTH = 2048;
  private final boolean overrideAllMethods;
  
  public MethodOverride()
  {
    this(false);
  }
  
  MethodOverride(boolean paramBoolean)
  {
    this.overrideAllMethods = paramBoolean;
  }
  
  private boolean overrideThisMethod(HttpRequest paramHttpRequest)
    throws IOException
  {
    boolean bool2 = true;
    String str = paramHttpRequest.getRequestMethod();
    boolean bool1;
    if (str.equals("POST")) {
      bool1 = false;
    }
    do
    {
      return bool1;
      if (!str.equals("GET")) {
        break;
      }
      bool1 = bool2;
    } while (paramHttpRequest.getUrl().build().length() > 2048);
    while (!this.overrideAllMethods)
    {
      bool1 = bool2;
      if (!paramHttpRequest.getTransport().supportsMethod(str)) {
        break;
      }
      return false;
    }
    return true;
  }
  
  public void initialize(HttpRequest paramHttpRequest)
  {
    paramHttpRequest.setInterceptor(this);
  }
  
  public void intercept(HttpRequest paramHttpRequest)
    throws IOException
  {
    if (overrideThisMethod(paramHttpRequest))
    {
      String str = paramHttpRequest.getRequestMethod();
      paramHttpRequest.setRequestMethod("POST");
      paramHttpRequest.getHeaders().set("X-HTTP-Method-Override", str);
      if (!str.equals("GET")) {
        break label67;
      }
      paramHttpRequest.setContent(new UrlEncodedContent(paramHttpRequest.getUrl().clone()));
      paramHttpRequest.getUrl().clear();
    }
    label67:
    while (paramHttpRequest.getContent() != null) {
      return;
    }
    paramHttpRequest.setContent(new EmptyContent());
  }
  
  public static final class Builder
  {
    private boolean overrideAllMethods;
    
    public MethodOverride build()
    {
      return new MethodOverride(this.overrideAllMethods);
    }
    
    public boolean getOverrideAllMethods()
    {
      return this.overrideAllMethods;
    }
    
    public Builder setOverrideAllMethods(boolean paramBoolean)
    {
      this.overrideAllMethods = paramBoolean;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\MethodOverride.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */