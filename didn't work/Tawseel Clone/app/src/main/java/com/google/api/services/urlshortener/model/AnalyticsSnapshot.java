package com.google.api.services.urlshortener.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;
import java.util.List;

public final class AnalyticsSnapshot
  extends GenericJson
{
  @Key
  private List<StringCount> browsers;
  @Key
  private List<StringCount> countries;
  @JsonString
  @Key
  private Long longUrlClicks;
  @Key
  private List<StringCount> platforms;
  @Key
  private List<StringCount> referrers;
  @JsonString
  @Key
  private Long shortUrlClicks;
  
  public AnalyticsSnapshot clone()
  {
    return (AnalyticsSnapshot)super.clone();
  }
  
  public List<StringCount> getBrowsers()
  {
    return this.browsers;
  }
  
  public List<StringCount> getCountries()
  {
    return this.countries;
  }
  
  public Long getLongUrlClicks()
  {
    return this.longUrlClicks;
  }
  
  public List<StringCount> getPlatforms()
  {
    return this.platforms;
  }
  
  public List<StringCount> getReferrers()
  {
    return this.referrers;
  }
  
  public Long getShortUrlClicks()
  {
    return this.shortUrlClicks;
  }
  
  public AnalyticsSnapshot set(String paramString, Object paramObject)
  {
    return (AnalyticsSnapshot)super.set(paramString, paramObject);
  }
  
  public AnalyticsSnapshot setBrowsers(List<StringCount> paramList)
  {
    this.browsers = paramList;
    return this;
  }
  
  public AnalyticsSnapshot setCountries(List<StringCount> paramList)
  {
    this.countries = paramList;
    return this;
  }
  
  public AnalyticsSnapshot setLongUrlClicks(Long paramLong)
  {
    this.longUrlClicks = paramLong;
    return this;
  }
  
  public AnalyticsSnapshot setPlatforms(List<StringCount> paramList)
  {
    this.platforms = paramList;
    return this;
  }
  
  public AnalyticsSnapshot setReferrers(List<StringCount> paramList)
  {
    this.referrers = paramList;
    return this;
  }
  
  public AnalyticsSnapshot setShortUrlClicks(Long paramLong)
  {
    this.shortUrlClicks = paramLong;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\model\AnalyticsSnapshot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */