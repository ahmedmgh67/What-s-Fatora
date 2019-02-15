package com.google.api.client.json.webtoken;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Objects.ToStringHelper;
import com.google.api.client.util.Preconditions;
import java.util.Collections;
import java.util.List;

public class JsonWebToken
{
  private final Header header;
  private final Payload payload;
  
  public JsonWebToken(Header paramHeader, Payload paramPayload)
  {
    this.header = ((Header)Preconditions.checkNotNull(paramHeader));
    this.payload = ((Payload)Preconditions.checkNotNull(paramPayload));
  }
  
  public Header getHeader()
  {
    return this.header;
  }
  
  public Payload getPayload()
  {
    return this.payload;
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("header", this.header).add("payload", this.payload).toString();
  }
  
  public static class Header
    extends GenericJson
  {
    @Key("cty")
    private String contentType;
    @Key("typ")
    private String type;
    
    public Header clone()
    {
      return (Header)super.clone();
    }
    
    public final String getContentType()
    {
      return this.contentType;
    }
    
    public final String getType()
    {
      return this.type;
    }
    
    public Header set(String paramString, Object paramObject)
    {
      return (Header)super.set(paramString, paramObject);
    }
    
    public Header setContentType(String paramString)
    {
      this.contentType = paramString;
      return this;
    }
    
    public Header setType(String paramString)
    {
      this.type = paramString;
      return this;
    }
  }
  
  public static class Payload
    extends GenericJson
  {
    @Key("aud")
    private Object audience;
    @Key("exp")
    private Long expirationTimeSeconds;
    @Key("iat")
    private Long issuedAtTimeSeconds;
    @Key("iss")
    private String issuer;
    @Key("jti")
    private String jwtId;
    @Key("nbf")
    private Long notBeforeTimeSeconds;
    @Key("sub")
    private String subject;
    @Key("typ")
    private String type;
    
    public Payload clone()
    {
      return (Payload)super.clone();
    }
    
    public final Object getAudience()
    {
      return this.audience;
    }
    
    public final List<String> getAudienceAsList()
    {
      if (this.audience == null) {
        return Collections.emptyList();
      }
      if ((this.audience instanceof String)) {
        return Collections.singletonList((String)this.audience);
      }
      return (List)this.audience;
    }
    
    public final Long getExpirationTimeSeconds()
    {
      return this.expirationTimeSeconds;
    }
    
    public final Long getIssuedAtTimeSeconds()
    {
      return this.issuedAtTimeSeconds;
    }
    
    public final String getIssuer()
    {
      return this.issuer;
    }
    
    public final String getJwtId()
    {
      return this.jwtId;
    }
    
    public final Long getNotBeforeTimeSeconds()
    {
      return this.notBeforeTimeSeconds;
    }
    
    public final String getSubject()
    {
      return this.subject;
    }
    
    public final String getType()
    {
      return this.type;
    }
    
    public Payload set(String paramString, Object paramObject)
    {
      return (Payload)super.set(paramString, paramObject);
    }
    
    public Payload setAudience(Object paramObject)
    {
      this.audience = paramObject;
      return this;
    }
    
    public Payload setExpirationTimeSeconds(Long paramLong)
    {
      this.expirationTimeSeconds = paramLong;
      return this;
    }
    
    public Payload setIssuedAtTimeSeconds(Long paramLong)
    {
      this.issuedAtTimeSeconds = paramLong;
      return this;
    }
    
    public Payload setIssuer(String paramString)
    {
      this.issuer = paramString;
      return this;
    }
    
    public Payload setJwtId(String paramString)
    {
      this.jwtId = paramString;
      return this;
    }
    
    public Payload setNotBeforeTimeSeconds(Long paramLong)
    {
      this.notBeforeTimeSeconds = paramLong;
      return this;
    }
    
    public Payload setSubject(String paramString)
    {
      this.subject = paramString;
      return this;
    }
    
    public Payload setType(String paramString)
    {
      this.type = paramString;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\webtoken\JsonWebToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */