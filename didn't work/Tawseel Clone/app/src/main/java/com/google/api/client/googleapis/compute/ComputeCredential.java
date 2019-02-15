package com.google.api.client.googleapis.compute;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.Credential.Builder;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Collection;

@Beta
public class ComputeCredential
  extends Credential
{
  public static final String TOKEN_SERVER_ENCODED_URL = OAuth2Utils.getMetadataServerUrl() + "/computeMetadata/v1/instance/service-accounts/default/token";
  
  protected ComputeCredential(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  public ComputeCredential(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
  {
    this(new Builder(paramHttpTransport, paramJsonFactory));
  }
  
  protected TokenResponse executeRefreshToken()
    throws IOException
  {
    Object localObject = new GenericUrl(getTokenServerEncodedUrl());
    localObject = getTransport().createRequestFactory().buildGetRequest((GenericUrl)localObject);
    ((HttpRequest)localObject).setParser(new JsonObjectParser(getJsonFactory()));
    ((HttpRequest)localObject).getHeaders().set("Metadata-Flavor", "Google");
    return (TokenResponse)((HttpRequest)localObject).execute().parseAs(TokenResponse.class);
  }
  
  @Beta
  public static class Builder
    extends Credential.Builder
  {
    public Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    {
      super();
      setTransport(paramHttpTransport);
      setJsonFactory(paramJsonFactory);
      setTokenServerEncodedUrl(ComputeCredential.TOKEN_SERVER_ENCODED_URL);
    }
    
    public Builder addRefreshListener(CredentialRefreshListener paramCredentialRefreshListener)
    {
      return (Builder)super.addRefreshListener(paramCredentialRefreshListener);
    }
    
    public ComputeCredential build()
    {
      return new ComputeCredential(this);
    }
    
    public Builder setClientAuthentication(HttpExecuteInterceptor paramHttpExecuteInterceptor)
    {
      if (paramHttpExecuteInterceptor == null) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool);
        return this;
      }
    }
    
    public Builder setClock(Clock paramClock)
    {
      return (Builder)super.setClock(paramClock);
    }
    
    public Builder setJsonFactory(JsonFactory paramJsonFactory)
    {
      return (Builder)super.setJsonFactory((JsonFactory)Preconditions.checkNotNull(paramJsonFactory));
    }
    
    public Builder setRefreshListeners(Collection<CredentialRefreshListener> paramCollection)
    {
      return (Builder)super.setRefreshListeners(paramCollection);
    }
    
    public Builder setRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
    {
      return (Builder)super.setRequestInitializer(paramHttpRequestInitializer);
    }
    
    public Builder setTokenServerEncodedUrl(String paramString)
    {
      return (Builder)super.setTokenServerEncodedUrl((String)Preconditions.checkNotNull(paramString));
    }
    
    public Builder setTokenServerUrl(GenericUrl paramGenericUrl)
    {
      return (Builder)super.setTokenServerUrl((GenericUrl)Preconditions.checkNotNull(paramGenericUrl));
    }
    
    public Builder setTransport(HttpTransport paramHttpTransport)
    {
      return (Builder)super.setTransport((HttpTransport)Preconditions.checkNotNull(paramHttpTransport));
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\compute\ComputeCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */