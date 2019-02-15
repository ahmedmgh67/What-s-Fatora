package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Beta
public class GooglePublicKeysManager
{
  private static final Pattern MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");
  private static final long REFRESH_SKEW_MILLIS = 300000L;
  private final Clock clock;
  private long expirationTimeMilliseconds;
  private final JsonFactory jsonFactory;
  private final Lock lock = new ReentrantLock();
  private final String publicCertsEncodedUrl;
  private List<PublicKey> publicKeys;
  private final HttpTransport transport;
  
  protected GooglePublicKeysManager(Builder paramBuilder)
  {
    this.transport = paramBuilder.transport;
    this.jsonFactory = paramBuilder.jsonFactory;
    this.clock = paramBuilder.clock;
    this.publicCertsEncodedUrl = paramBuilder.publicCertsEncodedUrl;
  }
  
  public GooglePublicKeysManager(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
  {
    this(new Builder(paramHttpTransport, paramJsonFactory));
  }
  
  long getCacheTimeInSec(HttpHeaders paramHttpHeaders)
  {
    long l2 = 0L;
    long l1 = l2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramHttpHeaders.getCacheControl() != null)
    {
      arrayOfString = paramHttpHeaders.getCacheControl().split(",");
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      l1 = l2;
      if (i < j)
      {
        Object localObject = arrayOfString[i];
        localObject = MAX_AGE_PATTERN.matcher((CharSequence)localObject);
        if (((Matcher)localObject).matches()) {
          l1 = Long.valueOf(((Matcher)localObject).group(1)).longValue();
        }
      }
      else
      {
        l2 = l1;
        if (paramHttpHeaders.getAge() != null) {
          l2 = l1 - paramHttpHeaders.getAge().longValue();
        }
        return Math.max(0L, l2);
      }
      i += 1;
    }
  }
  
  public final Clock getClock()
  {
    return this.clock;
  }
  
  public final long getExpirationTimeMilliseconds()
  {
    return this.expirationTimeMilliseconds;
  }
  
  public final JsonFactory getJsonFactory()
  {
    return this.jsonFactory;
  }
  
  public final String getPublicCertsEncodedUrl()
  {
    return this.publicCertsEncodedUrl;
  }
  
  public final List<PublicKey> getPublicKeys()
    throws GeneralSecurityException, IOException
  {
    this.lock.lock();
    try
    {
      if ((this.publicKeys == null) || (this.clock.currentTimeMillis() + 300000L > this.expirationTimeMilliseconds)) {
        refresh();
      }
      List localList = this.publicKeys;
      return localList;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public final HttpTransport getTransport()
  {
    return this.transport;
  }
  
  public GooglePublicKeysManager refresh()
    throws GeneralSecurityException, IOException
  {
    this.lock.lock();
    JsonParser localJsonParser;
    for (;;)
    {
      try
      {
        this.publicKeys = new ArrayList();
        CertificateFactory localCertificateFactory = SecurityUtils.getX509CertificateFactory();
        Object localObject1 = this.transport.createRequestFactory().buildGetRequest(new GenericUrl(this.publicCertsEncodedUrl)).execute();
        this.expirationTimeMilliseconds = (this.clock.currentTimeMillis() + getCacheTimeInSec(((HttpResponse)localObject1).getHeaders()) * 1000L);
        localJsonParser = this.jsonFactory.createJsonParser(((HttpResponse)localObject1).getContent());
        JsonToken localJsonToken = localJsonParser.getCurrentToken();
        localObject1 = localJsonToken;
        if (localJsonToken == null) {
          localObject1 = localJsonParser.nextToken();
        }
        if (localObject1 == JsonToken.START_OBJECT)
        {
          bool = true;
          Preconditions.checkArgument(bool);
          try
          {
            if (localJsonParser.nextToken() == JsonToken.END_OBJECT) {
              break;
            }
            localJsonParser.nextToken();
            localObject1 = (X509Certificate)localCertificateFactory.generateCertificate(new ByteArrayInputStream(StringUtils.getBytesUtf8(localJsonParser.getText())));
            this.publicKeys.add(((X509Certificate)localObject1).getPublicKey());
            continue;
            localObject3 = finally;
          }
          finally
          {
            localJsonParser.close();
          }
        }
        boolean bool = false;
      }
      finally
      {
        this.lock.unlock();
      }
    }
    this.publicKeys = Collections.unmodifiableList(this.publicKeys);
    localJsonParser.close();
    this.lock.unlock();
    return this;
  }
  
  @Beta
  public static class Builder
  {
    Clock clock = Clock.SYSTEM;
    final JsonFactory jsonFactory;
    String publicCertsEncodedUrl = "https://www.googleapis.com/oauth2/v1/certs";
    final HttpTransport transport;
    
    public Builder(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    {
      this.transport = ((HttpTransport)Preconditions.checkNotNull(paramHttpTransport));
      this.jsonFactory = ((JsonFactory)Preconditions.checkNotNull(paramJsonFactory));
    }
    
    public GooglePublicKeysManager build()
    {
      return new GooglePublicKeysManager(this);
    }
    
    public final Clock getClock()
    {
      return this.clock;
    }
    
    public final JsonFactory getJsonFactory()
    {
      return this.jsonFactory;
    }
    
    public final String getPublicCertsEncodedUrl()
    {
      return this.publicCertsEncodedUrl;
    }
    
    public final HttpTransport getTransport()
    {
      return this.transport;
    }
    
    public Builder setClock(Clock paramClock)
    {
      this.clock = ((Clock)Preconditions.checkNotNull(paramClock));
      return this;
    }
    
    public Builder setPublicCertsEncodedUrl(String paramString)
    {
      this.publicCertsEncodedUrl = ((String)Preconditions.checkNotNull(paramString));
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GooglePublicKeysManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */