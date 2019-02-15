package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebSignature.Header;
import com.google.api.client.json.webtoken.JsonWebSignature.Parser;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Beta
public class GoogleIdToken
  extends IdToken
{
  public GoogleIdToken(JsonWebSignature.Header paramHeader, Payload paramPayload, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    super(paramHeader, paramPayload, paramArrayOfByte1, paramArrayOfByte2);
  }
  
  public static GoogleIdToken parse(JsonFactory paramJsonFactory, String paramString)
    throws IOException
  {
    paramJsonFactory = JsonWebSignature.parser(paramJsonFactory).setPayloadClass(Payload.class).parse(paramString);
    return new GoogleIdToken(paramJsonFactory.getHeader(), (Payload)paramJsonFactory.getPayload(), paramJsonFactory.getSignatureBytes(), paramJsonFactory.getSignedContentBytes());
  }
  
  public Payload getPayload()
  {
    return (Payload)super.getPayload();
  }
  
  public boolean verify(GoogleIdTokenVerifier paramGoogleIdTokenVerifier)
    throws GeneralSecurityException, IOException
  {
    return paramGoogleIdTokenVerifier.verify(this);
  }
  
  @Beta
  public static class Payload
    extends IdToken.Payload
  {
    @Key("email")
    private String email;
    @Key("email_verified")
    private Object emailVerified;
    @Key("hd")
    private String hostedDomain;
    
    public Payload clone()
    {
      return (Payload)super.clone();
    }
    
    public String getEmail()
    {
      return this.email;
    }
    
    public Boolean getEmailVerified()
    {
      if (this.emailVerified == null) {
        return null;
      }
      if ((this.emailVerified instanceof Boolean)) {
        return (Boolean)this.emailVerified;
      }
      return Boolean.valueOf((String)this.emailVerified);
    }
    
    public String getHostedDomain()
    {
      return this.hostedDomain;
    }
    
    @Deprecated
    public String getIssuee()
    {
      return getAuthorizedParty();
    }
    
    @Deprecated
    public String getUserId()
    {
      return getSubject();
    }
    
    public Payload set(String paramString, Object paramObject)
    {
      return (Payload)super.set(paramString, paramObject);
    }
    
    public Payload setAccessTokenHash(String paramString)
    {
      return (Payload)super.setAccessTokenHash(paramString);
    }
    
    public Payload setAudience(Object paramObject)
    {
      return (Payload)super.setAudience(paramObject);
    }
    
    public Payload setAuthorizationTimeSeconds(Long paramLong)
    {
      return (Payload)super.setAuthorizationTimeSeconds(paramLong);
    }
    
    public Payload setAuthorizedParty(String paramString)
    {
      return (Payload)super.setAuthorizedParty(paramString);
    }
    
    public Payload setClassReference(String paramString)
    {
      return (Payload)super.setClassReference(paramString);
    }
    
    public Payload setEmail(String paramString)
    {
      this.email = paramString;
      return this;
    }
    
    public Payload setEmailVerified(Boolean paramBoolean)
    {
      this.emailVerified = paramBoolean;
      return this;
    }
    
    public Payload setExpirationTimeSeconds(Long paramLong)
    {
      return (Payload)super.setExpirationTimeSeconds(paramLong);
    }
    
    public Payload setHostedDomain(String paramString)
    {
      this.hostedDomain = paramString;
      return this;
    }
    
    public Payload setIssuedAtTimeSeconds(Long paramLong)
    {
      return (Payload)super.setIssuedAtTimeSeconds(paramLong);
    }
    
    @Deprecated
    public Payload setIssuee(String paramString)
    {
      return setAuthorizedParty(paramString);
    }
    
    public Payload setIssuer(String paramString)
    {
      return (Payload)super.setIssuer(paramString);
    }
    
    public Payload setJwtId(String paramString)
    {
      return (Payload)super.setJwtId(paramString);
    }
    
    public Payload setMethodsReferences(List<String> paramList)
    {
      return (Payload)super.setMethodsReferences(paramList);
    }
    
    public Payload setNonce(String paramString)
    {
      return (Payload)super.setNonce(paramString);
    }
    
    public Payload setNotBeforeTimeSeconds(Long paramLong)
    {
      return (Payload)super.setNotBeforeTimeSeconds(paramLong);
    }
    
    public Payload setSubject(String paramString)
    {
      return (Payload)super.setSubject(paramString);
    }
    
    public Payload setType(String paramString)
    {
      return (Payload)super.setType(paramString);
    }
    
    @Deprecated
    public Payload setUserId(String paramString)
    {
      return setSubject(paramString);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleIdToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */