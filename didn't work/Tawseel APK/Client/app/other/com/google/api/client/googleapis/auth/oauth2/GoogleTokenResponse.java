package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import java.io.IOException;

public class GoogleTokenResponse
  extends TokenResponse
{
  @Key("id_token")
  private String idToken;
  
  public GoogleTokenResponse clone()
  {
    return (GoogleTokenResponse)super.clone();
  }
  
  @Beta
  public final String getIdToken()
  {
    return this.idToken;
  }
  
  @Beta
  public GoogleIdToken parseIdToken()
    throws IOException
  {
    return GoogleIdToken.parse(getFactory(), getIdToken());
  }
  
  public GoogleTokenResponse set(String paramString, Object paramObject)
  {
    return (GoogleTokenResponse)super.set(paramString, paramObject);
  }
  
  public GoogleTokenResponse setAccessToken(String paramString)
  {
    return (GoogleTokenResponse)super.setAccessToken(paramString);
  }
  
  public GoogleTokenResponse setExpiresInSeconds(Long paramLong)
  {
    return (GoogleTokenResponse)super.setExpiresInSeconds(paramLong);
  }
  
  @Beta
  public GoogleTokenResponse setIdToken(String paramString)
  {
    this.idToken = ((String)Preconditions.checkNotNull(paramString));
    return this;
  }
  
  public GoogleTokenResponse setRefreshToken(String paramString)
  {
    return (GoogleTokenResponse)super.setRefreshToken(paramString);
  }
  
  public GoogleTokenResponse setScope(String paramString)
  {
    return (GoogleTokenResponse)super.setScope(paramString);
  }
  
  public GoogleTokenResponse setTokenType(String paramString)
  {
    return (GoogleTokenResponse)super.setTokenType(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleTokenResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */