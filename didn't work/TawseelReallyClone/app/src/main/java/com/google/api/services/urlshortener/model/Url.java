package com.google.api.services.urlshortener.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Url
  extends GenericJson
{
  @Key
  private AnalyticsSummary analytics;
  @Key
  private String created;
  @Key
  private String id;
  @Key
  private String kind;
  @Key
  private String longUrl;
  @Key
  private String status;
  
  public Url clone()
  {
    return (Url)super.clone();
  }
  
  public AnalyticsSummary getAnalytics()
  {
    return this.analytics;
  }
  
  public String getCreated()
  {
    return this.created;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getKind()
  {
    return this.kind;
  }
  
  public String getLongUrl()
  {
    return this.longUrl;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public Url set(String paramString, Object paramObject)
  {
    return (Url)super.set(paramString, paramObject);
  }
  
  public Url setAnalytics(AnalyticsSummary paramAnalyticsSummary)
  {
    this.analytics = paramAnalyticsSummary;
    return this;
  }
  
  public Url setCreated(String paramString)
  {
    this.created = paramString;
    return this;
  }
  
  public Url setId(String paramString)
  {
    this.id = paramString;
    return this;
  }
  
  public Url setKind(String paramString)
  {
    this.kind = paramString;
    return this;
  }
  
  public Url setLongUrl(String paramString)
  {
    this.longUrl = paramString;
    return this;
  }
  
  public Url setStatus(String paramString)
  {
    this.status = paramString;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\model\Url.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */