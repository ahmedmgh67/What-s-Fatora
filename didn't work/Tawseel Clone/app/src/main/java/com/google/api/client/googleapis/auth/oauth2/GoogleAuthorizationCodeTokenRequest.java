package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Collection;

public class GoogleAuthorizationCodeTokenRequest
  extends AuthorizationCodeTokenRequest
{
  public GoogleAuthorizationCodeTokenRequest(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this(paramHttpTransport, paramJsonFactory, "https://accounts.google.com/o/oauth2/token", paramString1, paramString2, paramString3, paramString4);
  }
  
  public GoogleAuthorizationCodeTokenRequest(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramHttpTransport, paramJsonFactory, new GenericUrl(paramString1), paramString4);
    setClientAuthentication(new ClientParametersAuthentication(paramString2, paramString3));
    setRedirectUri(paramString5);
  }
  
  public GoogleTokenResponse execute()
    throws IOException
  {
    return (GoogleTokenResponse)executeUnparsed().parseAs(GoogleTokenResponse.class);
  }
  
  public GoogleAuthorizationCodeTokenRequest set(String paramString, Object paramObject)
  {
    return (GoogleAuthorizationCodeTokenRequest)super.set(paramString, paramObject);
  }
  
  public GoogleAuthorizationCodeTokenRequest setClientAuthentication(HttpExecuteInterceptor paramHttpExecuteInterceptor)
  {
    Preconditions.checkNotNull(paramHttpExecuteInterceptor);
    return (GoogleAuthorizationCodeTokenRequest)super.setClientAuthentication(paramHttpExecuteInterceptor);
  }
  
  public GoogleAuthorizationCodeTokenRequest setCode(String paramString)
  {
    return (GoogleAuthorizationCodeTokenRequest)super.setCode(paramString);
  }
  
  public GoogleAuthorizationCodeTokenRequest setGrantType(String paramString)
  {
    return (GoogleAuthorizationCodeTokenRequest)super.setGrantType(paramString);
  }
  
  public GoogleAuthorizationCodeTokenRequest setRedirectUri(String paramString)
  {
    Preconditions.checkNotNull(paramString);
    return (GoogleAuthorizationCodeTokenRequest)super.setRedirectUri(paramString);
  }
  
  public GoogleAuthorizationCodeTokenRequest setRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
  {
    return (GoogleAuthorizationCodeTokenRequest)super.setRequestInitializer(paramHttpRequestInitializer);
  }
  
  public GoogleAuthorizationCodeTokenRequest setScopes(Collection<String> paramCollection)
  {
    return (GoogleAuthorizationCodeTokenRequest)super.setScopes(paramCollection);
  }
  
  public GoogleAuthorizationCodeTokenRequest setTokenServerUrl(GenericUrl paramGenericUrl)
  {
    return (GoogleAuthorizationCodeTokenRequest)super.setTokenServerUrl(paramGenericUrl);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleAuthorizationCodeTokenRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */