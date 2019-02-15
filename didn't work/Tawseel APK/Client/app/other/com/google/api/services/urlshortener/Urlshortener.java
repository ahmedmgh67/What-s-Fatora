package com.google.api.services.urlshortener;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.urlshortener.model.Url;
import com.google.api.services.urlshortener.model.UrlHistory;
import java.io.IOException;

public class Urlshortener
  extends AbstractGoogleJsonClient
{
  public static final String DEFAULT_BASE_URL = "https://www.googleapis.com/urlshortener/v1/";
  public static final String DEFAULT_ROOT_URL = "https://www.googleapis.com/";
  public static final String DEFAULT_SERVICE_PATH = "urlshortener/v1/";
  
  static
  {
    if ((GoogleUtils.MAJOR_VERSION.intValue() == 1) && (GoogleUtils.MINOR_VERSION.intValue() >= 15)) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkState(bool, "You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.22.0 of the URL Shortener API library.", new Object[] { GoogleUtils.VERSION });
      return;
    }
  }
  
  public Urlshortener(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, HttpRequestInitializer paramHttpRequestInitializer)
  {
    this(new Builder(paramHttpTransport, paramJsonFactory, paramHttpRequestInitializer));
  }
  
  Urlshortener(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  protected void initialize(AbstractGoogleClientRequest<?> paramAbstractGoogleClientRequest)
    throws IOException
  {
    super.initialize(paramAbstractGoogleClientRequest);
  }
  
  public Url url()
  {
    return new Url();
  }
  
  public static final class Builder
    extends AbstractGoogleJsonClient.Builder
  {
    public Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory, HttpRequestInitializer paramHttpRequestInitializer)
    {
      super(paramJsonFactory, "https://www.googleapis.com/", "urlshortener/v1/", paramHttpRequestInitializer, false);
    }
    
    public Urlshortener build()
    {
      return new Urlshortener(this);
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
    
    public Builder setUrlshortenerRequestInitializer(UrlshortenerRequestInitializer paramUrlshortenerRequestInitializer)
    {
      return (Builder)super.setGoogleClientRequestInitializer(paramUrlshortenerRequestInitializer);
    }
  }
  
  public class Url
  {
    public Url() {}
    
    public Get get(String paramString)
      throws IOException
    {
      paramString = new Get(paramString);
      Urlshortener.this.initialize(paramString);
      return paramString;
    }
    
    public Insert insert(Url paramUrl)
      throws IOException
    {
      paramUrl = new Insert(paramUrl);
      Urlshortener.this.initialize(paramUrl);
      return paramUrl;
    }
    
    public List list()
      throws IOException
    {
      List localList = new List();
      Urlshortener.this.initialize(localList);
      return localList;
    }
    
    public class Get
      extends UrlshortenerRequest<Url>
    {
      private static final String REST_PATH = "url";
      @Key
      private String projection;
      @Key
      private String shortUrl;
      
      protected Get(String paramString)
      {
        super("GET", "url", null, Url.class);
        this.shortUrl = ((String)Preconditions.checkNotNull(paramString, "Required parameter shortUrl must be specified."));
      }
      
      public HttpRequest buildHttpRequestUsingHead()
        throws IOException
      {
        return super.buildHttpRequestUsingHead();
      }
      
      public HttpResponse executeUsingHead()
        throws IOException
      {
        return super.executeUsingHead();
      }
      
      public String getProjection()
      {
        return this.projection;
      }
      
      public String getShortUrl()
      {
        return this.shortUrl;
      }
      
      public Get set(String paramString, Object paramObject)
      {
        return (Get)super.set(paramString, paramObject);
      }
      
      public Get setAlt(String paramString)
      {
        return (Get)super.setAlt(paramString);
      }
      
      public Get setFields(String paramString)
      {
        return (Get)super.setFields(paramString);
      }
      
      public Get setKey(String paramString)
      {
        return (Get)super.setKey(paramString);
      }
      
      public Get setOauthToken(String paramString)
      {
        return (Get)super.setOauthToken(paramString);
      }
      
      public Get setPrettyPrint(Boolean paramBoolean)
      {
        return (Get)super.setPrettyPrint(paramBoolean);
      }
      
      public Get setProjection(String paramString)
      {
        this.projection = paramString;
        return this;
      }
      
      public Get setQuotaUser(String paramString)
      {
        return (Get)super.setQuotaUser(paramString);
      }
      
      public Get setShortUrl(String paramString)
      {
        this.shortUrl = paramString;
        return this;
      }
      
      public Get setUserIp(String paramString)
      {
        return (Get)super.setUserIp(paramString);
      }
    }
    
    public class Insert
      extends UrlshortenerRequest<Url>
    {
      private static final String REST_PATH = "url";
      
      protected Insert(Url paramUrl)
      {
        super("POST", "url", paramUrl, Url.class);
      }
      
      public Insert set(String paramString, Object paramObject)
      {
        return (Insert)super.set(paramString, paramObject);
      }
      
      public Insert setAlt(String paramString)
      {
        return (Insert)super.setAlt(paramString);
      }
      
      public Insert setFields(String paramString)
      {
        return (Insert)super.setFields(paramString);
      }
      
      public Insert setKey(String paramString)
      {
        return (Insert)super.setKey(paramString);
      }
      
      public Insert setOauthToken(String paramString)
      {
        return (Insert)super.setOauthToken(paramString);
      }
      
      public Insert setPrettyPrint(Boolean paramBoolean)
      {
        return (Insert)super.setPrettyPrint(paramBoolean);
      }
      
      public Insert setQuotaUser(String paramString)
      {
        return (Insert)super.setQuotaUser(paramString);
      }
      
      public Insert setUserIp(String paramString)
      {
        return (Insert)super.setUserIp(paramString);
      }
    }
    
    public class List
      extends UrlshortenerRequest<UrlHistory>
    {
      private static final String REST_PATH = "url/history";
      @Key
      private String projection;
      @Key("start-token")
      private String startToken;
      
      protected List()
      {
        super("GET", "url/history", null, UrlHistory.class);
      }
      
      public HttpRequest buildHttpRequestUsingHead()
        throws IOException
      {
        return super.buildHttpRequestUsingHead();
      }
      
      public HttpResponse executeUsingHead()
        throws IOException
      {
        return super.executeUsingHead();
      }
      
      public String getProjection()
      {
        return this.projection;
      }
      
      public String getStartToken()
      {
        return this.startToken;
      }
      
      public List set(String paramString, Object paramObject)
      {
        return (List)super.set(paramString, paramObject);
      }
      
      public List setAlt(String paramString)
      {
        return (List)super.setAlt(paramString);
      }
      
      public List setFields(String paramString)
      {
        return (List)super.setFields(paramString);
      }
      
      public List setKey(String paramString)
      {
        return (List)super.setKey(paramString);
      }
      
      public List setOauthToken(String paramString)
      {
        return (List)super.setOauthToken(paramString);
      }
      
      public List setPrettyPrint(Boolean paramBoolean)
      {
        return (List)super.setPrettyPrint(paramBoolean);
      }
      
      public List setProjection(String paramString)
      {
        this.projection = paramString;
        return this;
      }
      
      public List setQuotaUser(String paramString)
      {
        return (List)super.setQuotaUser(paramString);
      }
      
      public List setStartToken(String paramString)
      {
        this.startToken = paramString;
        return this;
      }
      
      public List setUserIp(String paramString)
      {
        return (List)super.setUserIp(paramString);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\Urlshortener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */