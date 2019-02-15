package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public final class GoogleClientSecrets
  extends GenericJson
{
  @Key
  private Details installed;
  @Key
  private Details web;
  
  public static GoogleClientSecrets load(JsonFactory paramJsonFactory, Reader paramReader)
    throws IOException
  {
    return (GoogleClientSecrets)paramJsonFactory.fromReader(paramReader, GoogleClientSecrets.class);
  }
  
  public GoogleClientSecrets clone()
  {
    return (GoogleClientSecrets)super.clone();
  }
  
  public Details getDetails()
  {
    boolean bool = true;
    int i;
    int j;
    if (this.web == null)
    {
      i = 1;
      if (this.installed != null) {
        break label46;
      }
      j = 1;
      label20:
      if (i == j) {
        break label51;
      }
    }
    for (;;)
    {
      Preconditions.checkArgument(bool);
      if (this.web != null) {
        break label56;
      }
      return this.installed;
      i = 0;
      break;
      label46:
      j = 0;
      break label20;
      label51:
      bool = false;
    }
    label56:
    return this.web;
  }
  
  public Details getInstalled()
  {
    return this.installed;
  }
  
  public Details getWeb()
  {
    return this.web;
  }
  
  public GoogleClientSecrets set(String paramString, Object paramObject)
  {
    return (GoogleClientSecrets)super.set(paramString, paramObject);
  }
  
  public GoogleClientSecrets setInstalled(Details paramDetails)
  {
    this.installed = paramDetails;
    return this;
  }
  
  public GoogleClientSecrets setWeb(Details paramDetails)
  {
    this.web = paramDetails;
    return this;
  }
  
  public static final class Details
    extends GenericJson
  {
    @Key("auth_uri")
    private String authUri;
    @Key("client_id")
    private String clientId;
    @Key("client_secret")
    private String clientSecret;
    @Key("redirect_uris")
    private List<String> redirectUris;
    @Key("token_uri")
    private String tokenUri;
    
    public Details clone()
    {
      return (Details)super.clone();
    }
    
    public String getAuthUri()
    {
      return this.authUri;
    }
    
    public String getClientId()
    {
      return this.clientId;
    }
    
    public String getClientSecret()
    {
      return this.clientSecret;
    }
    
    public List<String> getRedirectUris()
    {
      return this.redirectUris;
    }
    
    public String getTokenUri()
    {
      return this.tokenUri;
    }
    
    public Details set(String paramString, Object paramObject)
    {
      return (Details)super.set(paramString, paramObject);
    }
    
    public Details setAuthUri(String paramString)
    {
      this.authUri = paramString;
      return this;
    }
    
    public Details setClientId(String paramString)
    {
      this.clientId = paramString;
      return this;
    }
    
    public Details setClientSecret(String paramString)
    {
      this.clientSecret = paramString;
      return this;
    }
    
    public Details setRedirectUris(List<String> paramList)
    {
      this.redirectUris = paramList;
      return this;
    }
    
    public Details setTokenUri(String paramString)
    {
      this.tokenUri = paramString;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleClientSecrets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */