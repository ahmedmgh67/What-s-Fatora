package com.google.api.client.googleapis.services;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Strings;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class AbstractGoogleClient
{
  static final Logger LOGGER = Logger.getLogger(AbstractGoogleClient.class.getName());
  private final String applicationName;
  private final GoogleClientRequestInitializer googleClientRequestInitializer;
  private final ObjectParser objectParser;
  private final HttpRequestFactory requestFactory;
  private final String rootUrl;
  private final String servicePath;
  private boolean suppressPatternChecks;
  private boolean suppressRequiredParameterChecks;
  
  protected AbstractGoogleClient(Builder paramBuilder)
  {
    this.googleClientRequestInitializer = paramBuilder.googleClientRequestInitializer;
    this.rootUrl = normalizeRootUrl(paramBuilder.rootUrl);
    this.servicePath = normalizeServicePath(paramBuilder.servicePath);
    if (Strings.isNullOrEmpty(paramBuilder.applicationName)) {
      LOGGER.warning("Application name is not set. Call Builder#setApplicationName.");
    }
    this.applicationName = paramBuilder.applicationName;
    if (paramBuilder.httpRequestInitializer == null) {}
    for (HttpRequestFactory localHttpRequestFactory = paramBuilder.transport.createRequestFactory();; localHttpRequestFactory = paramBuilder.transport.createRequestFactory(paramBuilder.httpRequestInitializer))
    {
      this.requestFactory = localHttpRequestFactory;
      this.objectParser = paramBuilder.objectParser;
      this.suppressPatternChecks = paramBuilder.suppressPatternChecks;
      this.suppressRequiredParameterChecks = paramBuilder.suppressRequiredParameterChecks;
      return;
    }
  }
  
  static String normalizeRootUrl(String paramString)
  {
    Preconditions.checkNotNull(paramString, "root URL cannot be null.");
    String str = paramString;
    if (!paramString.endsWith("/")) {
      str = paramString + "/";
    }
    return str;
  }
  
  static String normalizeServicePath(String paramString)
  {
    Preconditions.checkNotNull(paramString, "service path cannot be null");
    Object localObject;
    if (paramString.length() == 1)
    {
      Preconditions.checkArgument("/".equals(paramString), "service path must equal \"/\" if it is of length 1.");
      localObject = "";
    }
    String str;
    do
    {
      do
      {
        return (String)localObject;
        localObject = paramString;
      } while (paramString.length() <= 0);
      str = paramString;
      if (!paramString.endsWith("/")) {
        str = paramString + "/";
      }
      localObject = str;
    } while (!str.startsWith("/"));
    return str.substring(1);
  }
  
  public final BatchRequest batch()
  {
    return batch(null);
  }
  
  public final BatchRequest batch(HttpRequestInitializer paramHttpRequestInitializer)
  {
    paramHttpRequestInitializer = new BatchRequest(getRequestFactory().getTransport(), paramHttpRequestInitializer);
    paramHttpRequestInitializer.setBatchUrl(new GenericUrl(getRootUrl() + "batch"));
    return paramHttpRequestInitializer;
  }
  
  public final String getApplicationName()
  {
    return this.applicationName;
  }
  
  public final String getBaseUrl()
  {
    return this.rootUrl + this.servicePath;
  }
  
  public final GoogleClientRequestInitializer getGoogleClientRequestInitializer()
  {
    return this.googleClientRequestInitializer;
  }
  
  public ObjectParser getObjectParser()
  {
    return this.objectParser;
  }
  
  public final HttpRequestFactory getRequestFactory()
  {
    return this.requestFactory;
  }
  
  public final String getRootUrl()
  {
    return this.rootUrl;
  }
  
  public final String getServicePath()
  {
    return this.servicePath;
  }
  
  public final boolean getSuppressPatternChecks()
  {
    return this.suppressPatternChecks;
  }
  
  public final boolean getSuppressRequiredParameterChecks()
  {
    return this.suppressRequiredParameterChecks;
  }
  
  protected void initialize(AbstractGoogleClientRequest<?> paramAbstractGoogleClientRequest)
    throws IOException
  {
    if (getGoogleClientRequestInitializer() != null) {
      getGoogleClientRequestInitializer().initialize(paramAbstractGoogleClientRequest);
    }
  }
  
  public static abstract class Builder
  {
    String applicationName;
    GoogleClientRequestInitializer googleClientRequestInitializer;
    HttpRequestInitializer httpRequestInitializer;
    final ObjectParser objectParser;
    String rootUrl;
    String servicePath;
    boolean suppressPatternChecks;
    boolean suppressRequiredParameterChecks;
    final HttpTransport transport;
    
    protected Builder(HttpTransport paramHttpTransport, String paramString1, String paramString2, ObjectParser paramObjectParser, HttpRequestInitializer paramHttpRequestInitializer)
    {
      this.transport = ((HttpTransport)Preconditions.checkNotNull(paramHttpTransport));
      this.objectParser = paramObjectParser;
      setRootUrl(paramString1);
      setServicePath(paramString2);
      this.httpRequestInitializer = paramHttpRequestInitializer;
    }
    
    public abstract AbstractGoogleClient build();
    
    public final String getApplicationName()
    {
      return this.applicationName;
    }
    
    public final GoogleClientRequestInitializer getGoogleClientRequestInitializer()
    {
      return this.googleClientRequestInitializer;
    }
    
    public final HttpRequestInitializer getHttpRequestInitializer()
    {
      return this.httpRequestInitializer;
    }
    
    public ObjectParser getObjectParser()
    {
      return this.objectParser;
    }
    
    public final String getRootUrl()
    {
      return this.rootUrl;
    }
    
    public final String getServicePath()
    {
      return this.servicePath;
    }
    
    public final boolean getSuppressPatternChecks()
    {
      return this.suppressPatternChecks;
    }
    
    public final boolean getSuppressRequiredParameterChecks()
    {
      return this.suppressRequiredParameterChecks;
    }
    
    public final HttpTransport getTransport()
    {
      return this.transport;
    }
    
    public Builder setApplicationName(String paramString)
    {
      this.applicationName = paramString;
      return this;
    }
    
    public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer paramGoogleClientRequestInitializer)
    {
      this.googleClientRequestInitializer = paramGoogleClientRequestInitializer;
      return this;
    }
    
    public Builder setHttpRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
    {
      this.httpRequestInitializer = paramHttpRequestInitializer;
      return this;
    }
    
    public Builder setRootUrl(String paramString)
    {
      this.rootUrl = AbstractGoogleClient.normalizeRootUrl(paramString);
      return this;
    }
    
    public Builder setServicePath(String paramString)
    {
      this.servicePath = AbstractGoogleClient.normalizeServicePath(paramString);
      return this;
    }
    
    public Builder setSuppressAllChecks(boolean paramBoolean)
    {
      return setSuppressPatternChecks(true).setSuppressRequiredParameterChecks(true);
    }
    
    public Builder setSuppressPatternChecks(boolean paramBoolean)
    {
      this.suppressPatternChecks = paramBoolean;
      return this;
    }
    
    public Builder setSuppressRequiredParameterChecks(boolean paramBoolean)
    {
      this.suppressRequiredParameterChecks = paramBoolean;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\services\AbstractGoogleClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */