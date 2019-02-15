package com.google.api.services.urlshortener;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Key;

public abstract class UrlshortenerRequest<T>
  extends AbstractGoogleJsonClientRequest<T>
{
  @Key
  private String alt;
  @Key
  private String fields;
  @Key
  private String key;
  @Key("oauth_token")
  private String oauthToken;
  @Key
  private Boolean prettyPrint;
  @Key
  private String quotaUser;
  @Key
  private String userIp;
  
  public UrlshortenerRequest(Urlshortener paramUrlshortener, String paramString1, String paramString2, Object paramObject, Class<T> paramClass)
  {
    super(paramUrlshortener, paramString1, paramString2, paramObject, paramClass);
  }
  
  public final Urlshortener getAbstractGoogleClient()
  {
    return (Urlshortener)super.getAbstractGoogleClient();
  }
  
  public String getAlt()
  {
    return this.alt;
  }
  
  public String getFields()
  {
    return this.fields;
  }
  
  public String getKey()
  {
    return this.key;
  }
  
  public String getOauthToken()
  {
    return this.oauthToken;
  }
  
  public Boolean getPrettyPrint()
  {
    return this.prettyPrint;
  }
  
  public String getQuotaUser()
  {
    return this.quotaUser;
  }
  
  public String getUserIp()
  {
    return this.userIp;
  }
  
  public UrlshortenerRequest<T> set(String paramString, Object paramObject)
  {
    return (UrlshortenerRequest)super.set(paramString, paramObject);
  }
  
  public UrlshortenerRequest<T> setAlt(String paramString)
  {
    this.alt = paramString;
    return this;
  }
  
  public UrlshortenerRequest<T> setDisableGZipContent(boolean paramBoolean)
  {
    return (UrlshortenerRequest)super.setDisableGZipContent(paramBoolean);
  }
  
  public UrlshortenerRequest<T> setFields(String paramString)
  {
    this.fields = paramString;
    return this;
  }
  
  public UrlshortenerRequest<T> setKey(String paramString)
  {
    this.key = paramString;
    return this;
  }
  
  public UrlshortenerRequest<T> setOauthToken(String paramString)
  {
    this.oauthToken = paramString;
    return this;
  }
  
  public UrlshortenerRequest<T> setPrettyPrint(Boolean paramBoolean)
  {
    this.prettyPrint = paramBoolean;
    return this;
  }
  
  public UrlshortenerRequest<T> setQuotaUser(String paramString)
  {
    this.quotaUser = paramString;
    return this;
  }
  
  public UrlshortenerRequest<T> setRequestHeaders(HttpHeaders paramHttpHeaders)
  {
    return (UrlshortenerRequest)super.setRequestHeaders(paramHttpHeaders);
  }
  
  public UrlshortenerRequest<T> setUserIp(String paramString)
  {
    this.userIp = paramString;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\UrlshortenerRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */