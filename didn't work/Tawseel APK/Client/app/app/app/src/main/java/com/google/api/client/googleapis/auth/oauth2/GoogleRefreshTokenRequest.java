package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.RefreshTokenRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import java.io.IOException;
import java.util.Collection;

public class GoogleRefreshTokenRequest
  extends RefreshTokenRequest
{
  public GoogleRefreshTokenRequest(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, String paramString1, String paramString2, String paramString3)
  {
    super(paramHttpTransport, paramJsonFactory, new GenericUrl("https://accounts.google.com/o/oauth2/token"), paramString1);
    setClientAuthentication(new ClientParametersAuthentication(paramString2, paramString3));
  }
  
  public GoogleTokenResponse execute()
    throws IOException
  {
    return (GoogleTokenResponse)executeUnparsed().parseAs(GoogleTokenResponse.class);
  }
  
  public GoogleRefreshTokenRequest set(String paramString, Object paramObject)
  {
    return (GoogleRefreshTokenRequest)super.set(paramString, paramObject);
  }
  
  public GoogleRefreshTokenRequest setClientAuthentication(HttpExecuteInterceptor paramHttpExecuteInterceptor)
  {
    return (GoogleRefreshTokenRequest)super.setClientAuthentication(paramHttpExecuteInterceptor);
  }
  
  public GoogleRefreshTokenRequest setGrantType(String paramString)
  {
    return (GoogleRefreshTokenRequest)super.setGrantType(paramString);
  }
  
  public GoogleRefreshTokenRequest setRefreshToken(String paramString)
  {
    return (GoogleRefreshTokenRequest)super.setRefreshToken(paramString);
  }
  
  public GoogleRefreshTokenRequest setRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
  {
    return (GoogleRefreshTokenRequest)super.setRequestInitializer(paramHttpRequestInitializer);
  }
  
  public GoogleRefreshTokenRequest setScopes(Collection<String> paramCollection)
  {
    return (GoogleRefreshTokenRequest)super.setScopes(paramCollection);
  }
  
  public GoogleRefreshTokenRequest setTokenServerUrl(GenericUrl paramGenericUrl)
  {
    return (GoogleRefreshTokenRequest)super.setTokenServerUrl(paramGenericUrl);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleRefreshTokenRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */