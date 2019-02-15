package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.openidconnect.IdTokenVerifier;
import com.google.api.client.auth.openidconnect.IdTokenVerifier.Builder;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Beta
public class GoogleIdTokenVerifier
  extends IdTokenVerifier
{
  private final GooglePublicKeysManager publicKeys;
  
  protected GoogleIdTokenVerifier(Builder paramBuilder)
  {
    super(paramBuilder);
    this.publicKeys = paramBuilder.publicKeys;
  }
  
  public GoogleIdTokenVerifier(GooglePublicKeysManager paramGooglePublicKeysManager)
  {
    this(new Builder(paramGooglePublicKeysManager));
  }
  
  public GoogleIdTokenVerifier(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
  {
    this(new Builder(paramHttpTransport, paramJsonFactory));
  }
  
  @Deprecated
  public final long getExpirationTimeMilliseconds()
  {
    return this.publicKeys.getExpirationTimeMilliseconds();
  }
  
  public final JsonFactory getJsonFactory()
  {
    return this.publicKeys.getJsonFactory();
  }
  
  @Deprecated
  public final String getPublicCertsEncodedUrl()
  {
    return this.publicKeys.getPublicCertsEncodedUrl();
  }
  
  @Deprecated
  public final List<PublicKey> getPublicKeys()
    throws GeneralSecurityException, IOException
  {
    return this.publicKeys.getPublicKeys();
  }
  
  public final GooglePublicKeysManager getPublicKeysManager()
  {
    return this.publicKeys;
  }
  
  public final HttpTransport getTransport()
  {
    return this.publicKeys.getTransport();
  }
  
  @Deprecated
  public GoogleIdTokenVerifier loadPublicCerts()
    throws GeneralSecurityException, IOException
  {
    this.publicKeys.refresh();
    return this;
  }
  
  public GoogleIdToken verify(String paramString)
    throws GeneralSecurityException, IOException
  {
    paramString = GoogleIdToken.parse(getJsonFactory(), paramString);
    if (verify(paramString)) {
      return paramString;
    }
    return null;
  }
  
  public boolean verify(GoogleIdToken paramGoogleIdToken)
    throws GeneralSecurityException, IOException
  {
    if (!super.verify(paramGoogleIdToken)) {}
    Iterator localIterator;
    do
    {
      while (!localIterator.hasNext())
      {
        return false;
        localIterator = this.publicKeys.getPublicKeys().iterator();
      }
    } while (!paramGoogleIdToken.verifySignature((PublicKey)localIterator.next()));
    return true;
  }
  
  @Beta
  public static class Builder
    extends IdTokenVerifier.Builder
  {
    GooglePublicKeysManager publicKeys;
    
    public Builder(GooglePublicKeysManager paramGooglePublicKeysManager)
    {
      this.publicKeys = ((GooglePublicKeysManager)Preconditions.checkNotNull(paramGooglePublicKeysManager));
      setIssuers(Arrays.asList(new String[] { "accounts.google.com", "https://accounts.google.com" }));
    }
    
    public Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    {
      this(new GooglePublicKeysManager(paramHttpTransport, paramJsonFactory));
    }
    
    public GoogleIdTokenVerifier build()
    {
      return new GoogleIdTokenVerifier(this);
    }
    
    public final JsonFactory getJsonFactory()
    {
      return this.publicKeys.getJsonFactory();
    }
    
    public final GooglePublicKeysManager getPublicCerts()
    {
      return this.publicKeys;
    }
    
    @Deprecated
    public final String getPublicCertsEncodedUrl()
    {
      return this.publicKeys.getPublicCertsEncodedUrl();
    }
    
    public final HttpTransport getTransport()
    {
      return this.publicKeys.getTransport();
    }
    
    public Builder setAcceptableTimeSkewSeconds(long paramLong)
    {
      return (Builder)super.setAcceptableTimeSkewSeconds(paramLong);
    }
    
    public Builder setAudience(Collection<String> paramCollection)
    {
      return (Builder)super.setAudience(paramCollection);
    }
    
    public Builder setClock(Clock paramClock)
    {
      return (Builder)super.setClock(paramClock);
    }
    
    public Builder setIssuer(String paramString)
    {
      return (Builder)super.setIssuer(paramString);
    }
    
    public Builder setIssuers(Collection<String> paramCollection)
    {
      return (Builder)super.setIssuers(paramCollection);
    }
    
    @Deprecated
    public Builder setPublicCertsEncodedUrl(String paramString)
    {
      this.publicKeys = new GooglePublicKeysManager.Builder(getTransport(), getJsonFactory()).setPublicCertsEncodedUrl(paramString).setClock(this.publicKeys.getClock()).build();
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleIdTokenVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */