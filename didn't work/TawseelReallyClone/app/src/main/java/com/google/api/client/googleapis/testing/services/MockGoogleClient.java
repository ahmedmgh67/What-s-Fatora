package com.google.api.client.googleapis.testing.services;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClient.Builder;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;

@Beta
public class MockGoogleClient
  extends AbstractGoogleClient
{
  protected MockGoogleClient(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  public MockGoogleClient(HttpTransport paramHttpTransport, String paramString1, String paramString2, ObjectParser paramObjectParser, HttpRequestInitializer paramHttpRequestInitializer)
  {
    this(new Builder(paramHttpTransport, paramString1, paramString2, paramObjectParser, paramHttpRequestInitializer));
  }
  
  @Beta
  public static class Builder
    extends AbstractGoogleClient.Builder
  {
    public Builder(HttpTransport paramHttpTransport, String paramString1, String paramString2, ObjectParser paramObjectParser, HttpRequestInitializer paramHttpRequestInitializer)
    {
      super(paramString1, paramString2, paramObjectParser, paramHttpRequestInitializer);
    }
    
    public MockGoogleClient build()
    {
      return new MockGoogleClient(this);
    }
    
    public Builder setApplicationName(String paramString)
    {
      return (Builder)super.setApplicationName(paramString);
    }
    
    public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer paramGoogleClientRequestInitializer)
    {
      return (Builder)super.setGoogleClientRequestInitializer(paramGoogleClientRequestInitializer);
    }
    
    public Builder setHttpRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
    {
      return (Builder)super.setHttpRequestInitializer(paramHttpRequestInitializer);
    }
    
    public Builder setRootUrl(String paramString)
    {
      return (Builder)super.setRootUrl(paramString);
    }
    
    public Builder setServicePath(String paramString)
    {
      return (Builder)super.setServicePath(paramString);
    }
    
    public Builder setSuppressAllChecks(boolean paramBoolean)
    {
      return (Builder)super.setSuppressAllChecks(paramBoolean);
    }
    
    public Builder setSuppressPatternChecks(boolean paramBoolean)
    {
      return (Builder)super.setSuppressPatternChecks(paramBoolean);
    }
    
    public Builder setSuppressRequiredParameterChecks(boolean paramBoolean)
    {
      return (Builder)super.setSuppressRequiredParameterChecks(paramBoolean);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\services\MockGoogleClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */