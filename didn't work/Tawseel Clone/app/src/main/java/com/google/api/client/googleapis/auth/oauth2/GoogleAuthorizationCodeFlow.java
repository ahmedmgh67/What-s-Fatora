package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.Builder;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.CredentialCreatedListener;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential.AccessMethod;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.CredentialStore;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.io.IOException;
import java.util.Collection;

public class GoogleAuthorizationCodeFlow
  extends AuthorizationCodeFlow
{
  private final String accessType;
  private final String approvalPrompt;
  
  protected GoogleAuthorizationCodeFlow(Builder paramBuilder)
  {
    super(paramBuilder);
    this.accessType = paramBuilder.accessType;
    this.approvalPrompt = paramBuilder.approvalPrompt;
  }
  
  public GoogleAuthorizationCodeFlow(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, String paramString1, String paramString2, Collection<String> paramCollection)
  {
    this(new Builder(paramHttpTransport, paramJsonFactory, paramString1, paramString2, paramCollection));
  }
  
  public final String getAccessType()
  {
    return this.accessType;
  }
  
  public final String getApprovalPrompt()
  {
    return this.approvalPrompt;
  }
  
  public GoogleAuthorizationCodeRequestUrl newAuthorizationUrl()
  {
    return new GoogleAuthorizationCodeRequestUrl(getAuthorizationServerEncodedUrl(), getClientId(), "", getScopes()).setAccessType(this.accessType).setApprovalPrompt(this.approvalPrompt);
  }
  
  public GoogleAuthorizationCodeTokenRequest newTokenRequest(String paramString)
  {
    return new GoogleAuthorizationCodeTokenRequest(getTransport(), getJsonFactory(), getTokenServerEncodedUrl(), "", "", paramString, "").setClientAuthentication(getClientAuthentication()).setRequestInitializer(getRequestInitializer()).setScopes(getScopes());
  }
  
  public static class Builder
    extends AuthorizationCodeFlow.Builder
  {
    String accessType;
    String approvalPrompt;
    
    public Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, GoogleClientSecrets paramGoogleClientSecrets, Collection<String> paramCollection)
    {
      super(paramHttpTransport, paramJsonFactory, new GenericUrl("https://accounts.google.com/o/oauth2/token"), new ClientParametersAuthentication(paramGoogleClientSecrets.getDetails().getClientId(), paramGoogleClientSecrets.getDetails().getClientSecret()), paramGoogleClientSecrets.getDetails().getClientId(), "https://accounts.google.com/o/oauth2/auth");
      setScopes(paramCollection);
    }
    
    public Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, String paramString1, String paramString2, Collection<String> paramCollection)
    {
      super(paramHttpTransport, paramJsonFactory, new GenericUrl("https://accounts.google.com/o/oauth2/token"), new ClientParametersAuthentication(paramString1, paramString2), paramString1, "https://accounts.google.com/o/oauth2/auth");
      setScopes(paramCollection);
    }
    
    public Builder addRefreshListener(CredentialRefreshListener paramCredentialRefreshListener)
    {
      return (Builder)super.addRefreshListener(paramCredentialRefreshListener);
    }
    
    public GoogleAuthorizationCodeFlow build()
    {
      return new GoogleAuthorizationCodeFlow(this);
    }
    
    public final String getAccessType()
    {
      return this.accessType;
    }
    
    public final String getApprovalPrompt()
    {
      return this.approvalPrompt;
    }
    
    public Builder setAccessType(String paramString)
    {
      this.accessType = paramString;
      return this;
    }
    
    public Builder setApprovalPrompt(String paramString)
    {
      this.approvalPrompt = paramString;
      return this;
    }
    
    public Builder setAuthorizationServerEncodedUrl(String paramString)
    {
      return (Builder)super.setAuthorizationServerEncodedUrl(paramString);
    }
    
    public Builder setClientAuthentication(HttpExecuteInterceptor paramHttpExecuteInterceptor)
    {
      return (Builder)super.setClientAuthentication(paramHttpExecuteInterceptor);
    }
    
    public Builder setClientId(String paramString)
    {
      return (Builder)super.setClientId(paramString);
    }
    
    public Builder setClock(Clock paramClock)
    {
      return (Builder)super.setClock(paramClock);
    }
    
    public Builder setCredentialCreatedListener(AuthorizationCodeFlow.CredentialCreatedListener paramCredentialCreatedListener)
    {
      return (Builder)super.setCredentialCreatedListener(paramCredentialCreatedListener);
    }
    
    public Builder setCredentialDataStore(DataStore<StoredCredential> paramDataStore)
    {
      return (Builder)super.setCredentialDataStore(paramDataStore);
    }
    
    @Deprecated
    @Beta
    public Builder setCredentialStore(CredentialStore paramCredentialStore)
    {
      return (Builder)super.setCredentialStore(paramCredentialStore);
    }
    
    public Builder setDataStoreFactory(DataStoreFactory paramDataStoreFactory)
      throws IOException
    {
      return (Builder)super.setDataStoreFactory(paramDataStoreFactory);
    }
    
    public Builder setJsonFactory(JsonFactory paramJsonFactory)
    {
      return (Builder)super.setJsonFactory(paramJsonFactory);
    }
    
    public Builder setMethod(Credential.AccessMethod paramAccessMethod)
    {
      return (Builder)super.setMethod(paramAccessMethod);
    }
    
    public Builder setRefreshListeners(Collection<CredentialRefreshListener> paramCollection)
    {
      return (Builder)super.setRefreshListeners(paramCollection);
    }
    
    public Builder setRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
    {
      return (Builder)super.setRequestInitializer(paramHttpRequestInitializer);
    }
    
    public Builder setScopes(Collection<String> paramCollection)
    {
      if (!paramCollection.isEmpty()) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkState(bool);
        return (Builder)super.setScopes(paramCollection);
      }
    }
    
    public Builder setTokenServerUrl(GenericUrl paramGenericUrl)
    {
      return (Builder)super.setTokenServerUrl(paramGenericUrl);
    }
    
    public Builder setTransport(HttpTransport paramHttpTransport)
    {
      return (Builder)super.setTransport(paramHttpTransport);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleAuthorizationCodeFlow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */