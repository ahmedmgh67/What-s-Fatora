package com.google.api.services.urlshortener.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public final class StringCount
  extends GenericJson
{
  @JsonString
  @Key
  private Long count;
  @Key
  private String id;
  
  public StringCount clone()
  {
    return (StringCount)super.clone();
  }
  
  public Long getCount()
  {
    return this.count;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public StringCount set(String paramString, Object paramObject)
  {
    return (StringCount)super.set(paramString, paramObject);
  }
  
  public StringCount setCount(Long paramLong)
  {
    this.count = paramLong;
    return this;
  }
  
  public StringCount setId(String paramString)
  {
    this.id = paramString;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\model\StringCount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */