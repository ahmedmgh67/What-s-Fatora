package com.google.api.services.urlshortener.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class UrlHistory
  extends GenericJson
{
  @Key
  private List<Url> items;
  @Key
  private Integer itemsPerPage;
  @Key
  private String kind;
  @Key
  private String nextPageToken;
  @Key
  private Integer totalItems;
  
  static
  {
    Data.nullOf(Url.class);
  }
  
  public UrlHistory clone()
  {
    return (UrlHistory)super.clone();
  }
  
  public List<Url> getItems()
  {
    return this.items;
  }
  
  public Integer getItemsPerPage()
  {
    return this.itemsPerPage;
  }
  
  public String getKind()
  {
    return this.kind;
  }
  
  public String getNextPageToken()
  {
    return this.nextPageToken;
  }
  
  public Integer getTotalItems()
  {
    return this.totalItems;
  }
  
  public UrlHistory set(String paramString, Object paramObject)
  {
    return (UrlHistory)super.set(paramString, paramObject);
  }
  
  public UrlHistory setItems(List<Url> paramList)
  {
    this.items = paramList;
    return this;
  }
  
  public UrlHistory setItemsPerPage(Integer paramInteger)
  {
    this.itemsPerPage = paramInteger;
    return this;
  }
  
  public UrlHistory setKind(String paramString)
  {
    this.kind = paramString;
    return this;
  }
  
  public UrlHistory setNextPageToken(String paramString)
  {
    this.nextPageToken = paramString;
    return this;
  }
  
  public UrlHistory setTotalItems(Integer paramInteger)
  {
    this.totalItems = paramInteger;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\model\UrlHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */