package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClient.Builder;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonObjectParser.Builder;

public abstract class AbstractGoogleJsonClient
  extends AbstractGoogleClient
{
  protected AbstractGoogleJsonClient(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  public final JsonFactory getJsonFactory()
  {
    return getObjectParser().getJsonFactory();
  }
  
  public JsonObjectParser getObjectParser()
  {
    return (JsonObjectParser)super.getObjectParser();
  }
  
  public static abstract class Builder
    extends AbstractGoogleClient.Builder
  {
    protected Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, String paramString1, String paramString2, HttpRequestInitializer paramHttpRequestInitializer, boolean paramBoolean) {}
    
    public abstract AbstractGoogleJsonClient build();
    
    public final JsonFactory getJsonFactory()
    {
      return getObjectParser().getJsonFactory();
    }
    
    public final JsonObjectParser getObjectParser()
    {
      return (JsonObjectParser)super.getObjectParser();
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


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\json\AbstractGoogleJsonClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */