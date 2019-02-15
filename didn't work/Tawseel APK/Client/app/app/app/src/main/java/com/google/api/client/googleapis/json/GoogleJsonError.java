package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonObjectParser.Builder;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GoogleJsonError
  extends GenericJson
{
  @Key
  private int code;
  @Key
  private List<ErrorInfo> errors;
  @Key
  private String message;
  
  static
  {
    Data.nullOf(ErrorInfo.class);
  }
  
  public static GoogleJsonError parse(JsonFactory paramJsonFactory, HttpResponse paramHttpResponse)
    throws IOException
  {
    return (GoogleJsonError)new JsonObjectParser.Builder(paramJsonFactory).setWrapperKeys(Collections.singleton("error")).build().parseAndClose(paramHttpResponse.getContent(), paramHttpResponse.getContentCharset(), GoogleJsonError.class);
  }
  
  public GoogleJsonError clone()
  {
    return (GoogleJsonError)super.clone();
  }
  
  public final int getCode()
  {
    return this.code;
  }
  
  public final List<ErrorInfo> getErrors()
  {
    return this.errors;
  }
  
  public final String getMessage()
  {
    return this.message;
  }
  
  public GoogleJsonError set(String paramString, Object paramObject)
  {
    return (GoogleJsonError)super.set(paramString, paramObject);
  }
  
  public final void setCode(int paramInt)
  {
    this.code = paramInt;
  }
  
  public final void setErrors(List<ErrorInfo> paramList)
  {
    this.errors = paramList;
  }
  
  public final void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public static class ErrorInfo
    extends GenericJson
  {
    @Key
    private String domain;
    @Key
    private String location;
    @Key
    private String locationType;
    @Key
    private String message;
    @Key
    private String reason;
    
    public ErrorInfo clone()
    {
      return (ErrorInfo)super.clone();
    }
    
    public final String getDomain()
    {
      return this.domain;
    }
    
    public final String getLocation()
    {
      return this.location;
    }
    
    public final String getLocationType()
    {
      return this.locationType;
    }
    
    public final String getMessage()
    {
      return this.message;
    }
    
    public final String getReason()
    {
      return this.reason;
    }
    
    public ErrorInfo set(String paramString, Object paramObject)
    {
      return (ErrorInfo)super.set(paramString, paramObject);
    }
    
    public final void setDomain(String paramString)
    {
      this.domain = paramString;
    }
    
    public final void setLocation(String paramString)
    {
      this.location = paramString;
    }
    
    public final void setLocationType(String paramString)
    {
      this.locationType = paramString;
    }
    
    public final void setMessage(String paramString)
    {
      this.message = paramString;
    }
    
    public final void setReason(String paramString)
    {
      this.reason = paramString;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\json\GoogleJsonError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */