package com.google.api.services.urlshortener.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class AnalyticsSummary
  extends GenericJson
{
  @Key
  private AnalyticsSnapshot allTime;
  @Key
  private AnalyticsSnapshot day;
  @Key
  private AnalyticsSnapshot month;
  @Key
  private AnalyticsSnapshot twoHours;
  @Key
  private AnalyticsSnapshot week;
  
  public AnalyticsSummary clone()
  {
    return (AnalyticsSummary)super.clone();
  }
  
  public AnalyticsSnapshot getAllTime()
  {
    return this.allTime;
  }
  
  public AnalyticsSnapshot getDay()
  {
    return this.day;
  }
  
  public AnalyticsSnapshot getMonth()
  {
    return this.month;
  }
  
  public AnalyticsSnapshot getTwoHours()
  {
    return this.twoHours;
  }
  
  public AnalyticsSnapshot getWeek()
  {
    return this.week;
  }
  
  public AnalyticsSummary set(String paramString, Object paramObject)
  {
    return (AnalyticsSummary)super.set(paramString, paramObject);
  }
  
  public AnalyticsSummary setAllTime(AnalyticsSnapshot paramAnalyticsSnapshot)
  {
    this.allTime = paramAnalyticsSnapshot;
    return this;
  }
  
  public AnalyticsSummary setDay(AnalyticsSnapshot paramAnalyticsSnapshot)
  {
    this.day = paramAnalyticsSnapshot;
    return this;
  }
  
  public AnalyticsSummary setMonth(AnalyticsSnapshot paramAnalyticsSnapshot)
  {
    this.month = paramAnalyticsSnapshot;
    return this;
  }
  
  public AnalyticsSummary setTwoHours(AnalyticsSnapshot paramAnalyticsSnapshot)
  {
    this.twoHours = paramAnalyticsSnapshot;
    return this;
  }
  
  public AnalyticsSummary setWeek(AnalyticsSnapshot paramAnalyticsSnapshot)
  {
    this.week = paramAnalyticsSnapshot;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\model\AnalyticsSummary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */