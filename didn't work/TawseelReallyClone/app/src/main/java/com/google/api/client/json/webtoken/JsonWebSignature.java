package com.google.api.client.json.webtoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class JsonWebSignature
  extends JsonWebToken
{
  private final byte[] signatureBytes;
  private final byte[] signedContentBytes;
  
  public JsonWebSignature(Header paramHeader, JsonWebToken.Payload paramPayload, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    super(paramHeader, paramPayload);
    this.signatureBytes = ((byte[])Preconditions.checkNotNull(paramArrayOfByte1));
    this.signedContentBytes = ((byte[])Preconditions.checkNotNull(paramArrayOfByte2));
  }
  
  private static X509TrustManager getDefaultX509TrustManager()
  {
    try
    {
      Object localObject1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      ((TrustManagerFactory)localObject1).init((KeyStore)null);
      localObject1 = ((TrustManagerFactory)localObject1).getTrustManagers();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        if ((localObject2 instanceof X509TrustManager))
        {
          localObject1 = (X509TrustManager)localObject2;
          return (X509TrustManager)localObject1;
        }
        i += 1;
      }
      return null;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      return null;
    }
    catch (KeyStoreException localKeyStoreException) {}
    return null;
  }
  
  public static JsonWebSignature parse(JsonFactory paramJsonFactory, String paramString)
    throws IOException
  {
    return parser(paramJsonFactory).parse(paramString);
  }
  
  public static Parser parser(JsonFactory paramJsonFactory)
  {
    return new Parser(paramJsonFactory);
  }
  
  public static String signUsingRsaSha256(PrivateKey paramPrivateKey, JsonFactory paramJsonFactory, Header paramHeader, JsonWebToken.Payload paramPayload)
    throws GeneralSecurityException, IOException
  {
    paramJsonFactory = Base64.encodeBase64URLSafeString(paramJsonFactory.toByteArray(paramHeader)) + "." + Base64.encodeBase64URLSafeString(paramJsonFactory.toByteArray(paramPayload));
    paramHeader = StringUtils.getBytesUtf8(paramJsonFactory);
    paramPrivateKey = SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), paramPrivateKey, paramHeader);
    return paramJsonFactory + "." + Base64.encodeBase64URLSafeString(paramPrivateKey);
  }
  
  public Header getHeader()
  {
    return (Header)super.getHeader();
  }
  
  public final byte[] getSignatureBytes()
  {
    return this.signatureBytes;
  }
  
  public final byte[] getSignedContentBytes()
  {
    return this.signedContentBytes;
  }
  
  @Beta
  public final X509Certificate verifySignature()
    throws GeneralSecurityException
  {
    X509TrustManager localX509TrustManager = getDefaultX509TrustManager();
    if (localX509TrustManager == null) {
      return null;
    }
    return verifySignature(localX509TrustManager);
  }
  
  @Beta
  public final X509Certificate verifySignature(X509TrustManager paramX509TrustManager)
    throws GeneralSecurityException
  {
    List localList = getHeader().getX509Certificates();
    if ((localList == null) || (localList.isEmpty())) {}
    while (!"RS256".equals(getHeader().getAlgorithm())) {
      return null;
    }
    return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), paramX509TrustManager, localList, this.signatureBytes, this.signedContentBytes);
  }
  
  public final boolean verifySignature(PublicKey paramPublicKey)
    throws GeneralSecurityException
  {
    if ("RS256".equals(getHeader().getAlgorithm())) {
      return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), paramPublicKey, this.signatureBytes, this.signedContentBytes);
    }
    return false;
  }
  
  public static class Header
    extends JsonWebToken.Header
  {
    @Key("alg")
    private String algorithm;
    @Key("crit")
    private List<String> critical;
    @Key("jwk")
    private String jwk;
    @Key("jku")
    private String jwkUrl;
    @Key("kid")
    private String keyId;
    @Key("x5c")
    private List<String> x509Certificates;
    @Key("x5t")
    private String x509Thumbprint;
    @Key("x5u")
    private String x509Url;
    
    public Header clone()
    {
      return (Header)super.clone();
    }
    
    public final String getAlgorithm()
    {
      return this.algorithm;
    }
    
    public final List<String> getCritical()
    {
      return this.critical;
    }
    
    public final String getJwk()
    {
      return this.jwk;
    }
    
    public final String getJwkUrl()
    {
      return this.jwkUrl;
    }
    
    public final String getKeyId()
    {
      return this.keyId;
    }
    
    @Deprecated
    public final String getX509Certificate()
    {
      if ((this.x509Certificates == null) || (this.x509Certificates.isEmpty())) {
        return null;
      }
      return (String)this.x509Certificates.get(0);
    }
    
    public final List<String> getX509Certificates()
    {
      return this.x509Certificates;
    }
    
    public final String getX509Thumbprint()
    {
      return this.x509Thumbprint;
    }
    
    public final String getX509Url()
    {
      return this.x509Url;
    }
    
    public Header set(String paramString, Object paramObject)
    {
      return (Header)super.set(paramString, paramObject);
    }
    
    public Header setAlgorithm(String paramString)
    {
      this.algorithm = paramString;
      return this;
    }
    
    public Header setCritical(List<String> paramList)
    {
      this.critical = paramList;
      return this;
    }
    
    public Header setJwk(String paramString)
    {
      this.jwk = paramString;
      return this;
    }
    
    public Header setJwkUrl(String paramString)
    {
      this.jwkUrl = paramString;
      return this;
    }
    
    public Header setKeyId(String paramString)
    {
      this.keyId = paramString;
      return this;
    }
    
    public Header setType(String paramString)
    {
      super.setType(paramString);
      return this;
    }
    
    @Deprecated
    public Header setX509Certificate(String paramString)
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramString);
      this.x509Certificates = localArrayList;
      return this;
    }
    
    public Header setX509Certificates(List<String> paramList)
    {
      this.x509Certificates = paramList;
      return this;
    }
    
    public Header setX509Thumbprint(String paramString)
    {
      this.x509Thumbprint = paramString;
      return this;
    }
    
    public Header setX509Url(String paramString)
    {
      this.x509Url = paramString;
      return this;
    }
  }
  
  public static final class Parser
  {
    private Class<? extends JsonWebSignature.Header> headerClass = JsonWebSignature.Header.class;
    private final JsonFactory jsonFactory;
    private Class<? extends JsonWebToken.Payload> payloadClass = JsonWebToken.Payload.class;
    
    public Parser(JsonFactory paramJsonFactory)
    {
      this.jsonFactory = ((JsonFactory)Preconditions.checkNotNull(paramJsonFactory));
    }
    
    public Class<? extends JsonWebSignature.Header> getHeaderClass()
    {
      return this.headerClass;
    }
    
    public JsonFactory getJsonFactory()
    {
      return this.jsonFactory;
    }
    
    public Class<? extends JsonWebToken.Payload> getPayloadClass()
    {
      return this.payloadClass;
    }
    
    public JsonWebSignature parse(String paramString)
      throws IOException
    {
      boolean bool2 = true;
      int i = paramString.indexOf('.');
      Object localObject;
      label52:
      label73:
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      if (i != -1)
      {
        bool1 = true;
        Preconditions.checkArgument(bool1);
        localObject = Base64.decodeBase64(paramString.substring(0, i));
        int j = paramString.indexOf('.', i + 1);
        if (j == -1) {
          break label197;
        }
        bool1 = true;
        Preconditions.checkArgument(bool1);
        if (paramString.indexOf('.', j + 1) != -1) {
          break label203;
        }
        bool1 = true;
        Preconditions.checkArgument(bool1);
        arrayOfByte1 = Base64.decodeBase64(paramString.substring(i + 1, j));
        arrayOfByte2 = Base64.decodeBase64(paramString.substring(j + 1));
        paramString = StringUtils.getBytesUtf8(paramString.substring(0, j));
        localObject = (JsonWebSignature.Header)this.jsonFactory.fromInputStream(new ByteArrayInputStream((byte[])localObject), this.headerClass);
        if (((JsonWebSignature.Header)localObject).getAlgorithm() == null) {
          break label209;
        }
      }
      label197:
      label203:
      label209:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        Preconditions.checkArgument(bool1);
        return new JsonWebSignature((JsonWebSignature.Header)localObject, (JsonWebToken.Payload)this.jsonFactory.fromInputStream(new ByteArrayInputStream(arrayOfByte1), this.payloadClass), arrayOfByte2, paramString);
        bool1 = false;
        break;
        bool1 = false;
        break label52;
        bool1 = false;
        break label73;
      }
    }
    
    public Parser setHeaderClass(Class<? extends JsonWebSignature.Header> paramClass)
    {
      this.headerClass = paramClass;
      return this;
    }
    
    public Parser setPayloadClass(Class<? extends JsonWebToken.Payload> paramClass)
    {
      this.payloadClass = paramClass;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\webtoken\JsonWebSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */