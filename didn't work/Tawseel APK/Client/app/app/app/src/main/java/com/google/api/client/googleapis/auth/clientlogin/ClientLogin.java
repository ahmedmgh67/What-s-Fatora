package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Strings;
import java.io.IOException;

@Beta
public final class ClientLogin
{
  @Key
  public String accountType;
  @Key("source")
  public String applicationName;
  @Key("service")
  public String authTokenType;
  @Key("logincaptcha")
  public String captchaAnswer;
  @Key("logintoken")
  public String captchaToken;
  @Key("Passwd")
  public String password;
  public GenericUrl serverUrl = new GenericUrl("https://www.google.com");
  public HttpTransport transport;
  @Key("Email")
  public String username;
  
  public static String getAuthorizationHeaderValue(String paramString)
  {
    return "GoogleLogin auth=" + paramString;
  }
  
  public Response authenticate()
    throws IOException
  {
    Object localObject1 = this.serverUrl.clone();
    ((GenericUrl)localObject1).appendRawPath("/accounts/ClientLogin");
    localObject1 = this.transport.createRequestFactory().buildPostRequest((GenericUrl)localObject1, new UrlEncodedContent(this));
    ((HttpRequest)localObject1).setParser(AuthKeyValueParser.INSTANCE);
    ((HttpRequest)localObject1).setContentLoggingLimit(0);
    ((HttpRequest)localObject1).setThrowExceptionOnExecuteError(false);
    Object localObject2 = ((HttpRequest)localObject1).execute();
    if (((HttpResponse)localObject2).isSuccessStatusCode()) {
      return (Response)((HttpResponse)localObject2).parseAs(Response.class);
    }
    localObject1 = new HttpResponseException.Builder(((HttpResponse)localObject2).getStatusCode(), ((HttpResponse)localObject2).getStatusMessage(), ((HttpResponse)localObject2).getHeaders());
    ErrorInfo localErrorInfo = (ErrorInfo)((HttpResponse)localObject2).parseAs(ErrorInfo.class);
    String str = localErrorInfo.toString();
    localObject2 = HttpResponseException.computeMessageBuffer((HttpResponse)localObject2);
    if (!Strings.isNullOrEmpty(str))
    {
      ((StringBuilder)localObject2).append(StringUtils.LINE_SEPARATOR).append(str);
      ((HttpResponseException.Builder)localObject1).setContent(str);
    }
    ((HttpResponseException.Builder)localObject1).setMessage(((StringBuilder)localObject2).toString());
    throw new ClientLoginResponseException((HttpResponseException.Builder)localObject1, localErrorInfo);
  }
  
  public static final class ErrorInfo
  {
    @Key("CaptchaToken")
    public String captchaToken;
    @Key("CaptchaUrl")
    public String captchaUrl;
    @Key("Error")
    public String error;
    @Key("Url")
    public String url;
  }
  
  public static final class Response
    implements HttpExecuteInterceptor, HttpRequestInitializer
  {
    @Key("Auth")
    public String auth;
    
    public String getAuthorizationHeaderValue()
    {
      return ClientLogin.getAuthorizationHeaderValue(this.auth);
    }
    
    public void initialize(HttpRequest paramHttpRequest)
    {
      paramHttpRequest.setInterceptor(this);
    }
    
    public void intercept(HttpRequest paramHttpRequest)
    {
      paramHttpRequest.getHeaders().setAuthorization(getAuthorizationHeaderValue());
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\clientlogin\ClientLogin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */