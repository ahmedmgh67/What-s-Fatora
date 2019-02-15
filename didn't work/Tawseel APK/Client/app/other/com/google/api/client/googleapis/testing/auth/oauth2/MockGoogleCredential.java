package com.google.api.client.googleapis.testing.auth.oauth2;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockHttpTransport.Builder;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import java.io.IOException;

@Beta
public class MockGoogleCredential
  extends GoogleCredential
{
  public static final String ACCESS_TOKEN = "access_xyz";
  private static final String DEFAULT_TOKEN_RESPONSE_JSON = String.format("{\"access_token\": \"%s\", \"expires_in\":  %s, \"refresh_token\": \"%s\", \"token_type\": \"%s\"}", new Object[] { "access_xyz", "3600", "refresh123", "Bearer" });
  private static final String EXPIRES_IN_SECONDS = "3600";
  public static final String REFRESH_TOKEN = "refresh123";
  private static final String TOKEN_RESPONSE = "{\"access_token\": \"%s\", \"expires_in\":  %s, \"refresh_token\": \"%s\", \"token_type\": \"%s\"}";
  private static final String TOKEN_TYPE = "Bearer";
  
  public MockGoogleCredential(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  public static MockHttpTransport newMockHttpTransportWithSampleTokenResponse()
  {
    Object localObject = new MockLowLevelHttpResponse().setContentType("application/json; charset=UTF-8").setContent(DEFAULT_TOKEN_RESPONSE_JSON);
    localObject = new MockLowLevelHttpRequest().setResponse((MockLowLevelHttpResponse)localObject);
    return new MockHttpTransport.Builder().setLowLevelHttpRequest((MockLowLevelHttpRequest)localObject).build();
  }
  
  @Beta
  public static class Builder
    extends GoogleCredential.Builder
  {
    public MockGoogleCredential build()
    {
      if (getTransport() == null) {
        setTransport(new MockHttpTransport.Builder().build());
      }
      if (getClientAuthentication() == null) {
        setClientAuthentication(new MockGoogleCredential.MockClientAuthentication(null));
      }
      if (getJsonFactory() == null) {
        setJsonFactory(new JacksonFactory());
      }
      return new MockGoogleCredential(this);
    }
    
    public Builder setClientAuthentication(HttpExecuteInterceptor paramHttpExecuteInterceptor)
    {
      return (Builder)super.setClientAuthentication(paramHttpExecuteInterceptor);
    }
    
    public Builder setClock(Clock paramClock)
    {
      return (Builder)super.setClock(paramClock);
    }
    
    public Builder setJsonFactory(JsonFactory paramJsonFactory)
    {
      return (Builder)super.setJsonFactory(paramJsonFactory);
    }
    
    public Builder setTransport(HttpTransport paramHttpTransport)
    {
      return (Builder)super.setTransport(paramHttpTransport);
    }
  }
  
  @Beta
  private static class MockClientAuthentication
    implements HttpExecuteInterceptor
  {
    public void intercept(HttpRequest paramHttpRequest)
      throws IOException
    {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\auth\oauth2\MockGoogleCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */