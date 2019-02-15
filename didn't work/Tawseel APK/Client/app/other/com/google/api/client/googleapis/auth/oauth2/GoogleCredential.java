package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.Credential.Builder;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebSignature.Header;
import com.google.api.client.json.webtoken.JsonWebToken.Payload;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.PemReader;
import com.google.api.client.util.PemReader.Section;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;
import java.util.Collections;

public class GoogleCredential
  extends Credential
{
  static final String SERVICE_ACCOUNT_FILE_TYPE = "service_account";
  static final String USER_FILE_TYPE = "authorized_user";
  @Beta
  private static DefaultCredentialProvider defaultCredentialProvider = new DefaultCredentialProvider();
  private String serviceAccountId;
  private PrivateKey serviceAccountPrivateKey;
  private String serviceAccountPrivateKeyId;
  private Collection<String> serviceAccountScopes;
  private String serviceAccountUser;
  
  public GoogleCredential()
  {
    this(new Builder());
  }
  
  protected GoogleCredential(Builder paramBuilder)
  {
    super(paramBuilder);
    if (paramBuilder.serviceAccountPrivateKey == null)
    {
      if ((paramBuilder.serviceAccountId == null) && (paramBuilder.serviceAccountScopes == null) && (paramBuilder.serviceAccountUser == null)) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool);
        return;
      }
    }
    this.serviceAccountId = ((String)Preconditions.checkNotNull(paramBuilder.serviceAccountId));
    this.serviceAccountScopes = Collections.unmodifiableCollection(paramBuilder.serviceAccountScopes);
    this.serviceAccountPrivateKey = paramBuilder.serviceAccountPrivateKey;
    this.serviceAccountPrivateKeyId = paramBuilder.serviceAccountPrivateKeyId;
    this.serviceAccountUser = paramBuilder.serviceAccountUser;
  }
  
  @Beta
  public static GoogleCredential fromStream(InputStream paramInputStream)
    throws IOException
  {
    return fromStream(paramInputStream, Utils.getDefaultTransport(), Utils.getDefaultJsonFactory());
  }
  
  @Beta
  public static GoogleCredential fromStream(InputStream paramInputStream, HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    throws IOException
  {
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(paramHttpTransport);
    Preconditions.checkNotNull(paramJsonFactory);
    paramInputStream = (GenericJson)new JsonObjectParser(paramJsonFactory).parseAndClose(paramInputStream, OAuth2Utils.UTF_8, GenericJson.class);
    String str = (String)paramInputStream.get("type");
    if (str == null) {
      throw new IOException("Error reading credentials from stream, 'type' field not specified.");
    }
    if ("authorized_user".equals(str)) {
      return fromStreamUser(paramInputStream, paramHttpTransport, paramJsonFactory);
    }
    if ("service_account".equals(str)) {
      return fromStreamServiceAccount(paramInputStream, paramHttpTransport, paramJsonFactory);
    }
    throw new IOException(String.format("Error reading credentials from stream, 'type' value '%s' not recognized. Expecting '%s' or '%s'.", new Object[] { str, "authorized_user", "service_account" }));
  }
  
  @Beta
  private static GoogleCredential fromStreamServiceAccount(GenericJson paramGenericJson, HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    throws IOException
  {
    Object localObject1 = (String)paramGenericJson.get("client_id");
    String str1 = (String)paramGenericJson.get("client_email");
    Object localObject2 = (String)paramGenericJson.get("private_key");
    String str2 = (String)paramGenericJson.get("private_key_id");
    if ((localObject1 == null) || (str1 == null) || (localObject2 == null) || (str2 == null)) {
      throw new IOException("Error reading service account credential from stream, expecting  'client_id', 'client_email', 'private_key' and 'private_key_id'.");
    }
    localObject1 = privateKeyFromPkcs8((String)localObject2);
    localObject2 = Collections.emptyList();
    paramHttpTransport = new Builder().setTransport(paramHttpTransport).setJsonFactory(paramJsonFactory).setServiceAccountId(str1).setServiceAccountScopes((Collection)localObject2).setServiceAccountPrivateKey((PrivateKey)localObject1).setServiceAccountPrivateKeyId(str2);
    paramGenericJson = (String)paramGenericJson.get("token_uri");
    if (paramGenericJson != null) {
      paramHttpTransport.setTokenServerEncodedUrl(paramGenericJson);
    }
    return paramHttpTransport.build();
  }
  
  @Beta
  private static GoogleCredential fromStreamUser(GenericJson paramGenericJson, HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    throws IOException
  {
    String str1 = (String)paramGenericJson.get("client_id");
    String str2 = (String)paramGenericJson.get("client_secret");
    paramGenericJson = (String)paramGenericJson.get("refresh_token");
    if ((str1 == null) || (str2 == null) || (paramGenericJson == null)) {
      throw new IOException("Error reading user credential from stream,  expecting 'client_id', 'client_secret' and 'refresh_token'.");
    }
    paramHttpTransport = new Builder().setClientSecrets(str1, str2).setTransport(paramHttpTransport).setJsonFactory(paramJsonFactory).build();
    paramHttpTransport.setRefreshToken(paramGenericJson);
    paramHttpTransport.refreshToken();
    return paramHttpTransport;
  }
  
  @Beta
  public static GoogleCredential getApplicationDefault()
    throws IOException
  {
    return getApplicationDefault(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory());
  }
  
  @Beta
  public static GoogleCredential getApplicationDefault(HttpTransport paramHttpTransport, JsonFactory paramJsonFactory)
    throws IOException
  {
    Preconditions.checkNotNull(paramHttpTransport);
    Preconditions.checkNotNull(paramJsonFactory);
    return defaultCredentialProvider.getDefaultCredential(paramHttpTransport, paramJsonFactory);
  }
  
  @Beta
  private static PrivateKey privateKeyFromPkcs8(String paramString)
    throws IOException
  {
    paramString = PemReader.readFirstSectionAndClose(new StringReader(paramString), "PRIVATE KEY");
    if (paramString == null) {
      throw new IOException("Invalid PKCS8 data.");
    }
    paramString = new PKCS8EncodedKeySpec(paramString.getBase64DecodedBytes());
    try
    {
      paramString = SecurityUtils.getRsaKeyFactory().generatePrivate(paramString);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw ((IOException)OAuth2Utils.exceptionWithCause(new IOException("Unexpected exception reading PKCS data"), paramString));
    }
    catch (InvalidKeySpecException paramString)
    {
      for (;;) {}
    }
  }
  
  @Beta
  public GoogleCredential createScoped(Collection<String> paramCollection)
  {
    if (this.serviceAccountPrivateKey == null) {
      return this;
    }
    return new Builder().setServiceAccountPrivateKey(this.serviceAccountPrivateKey).setServiceAccountPrivateKeyId(this.serviceAccountPrivateKeyId).setServiceAccountId(this.serviceAccountId).setServiceAccountUser(this.serviceAccountUser).setServiceAccountScopes(paramCollection).setTokenServerEncodedUrl(getTokenServerEncodedUrl()).setTransport(getTransport()).setJsonFactory(getJsonFactory()).setClock(getClock()).build();
  }
  
  @Beta
  public boolean createScopedRequired()
  {
    if (this.serviceAccountPrivateKey == null) {}
    while ((this.serviceAccountScopes != null) && (!this.serviceAccountScopes.isEmpty())) {
      return false;
    }
    return true;
  }
  
  @Beta
  protected TokenResponse executeRefreshToken()
    throws IOException
  {
    if (this.serviceAccountPrivateKey == null) {
      return super.executeRefreshToken();
    }
    Object localObject1 = new JsonWebSignature.Header();
    ((JsonWebSignature.Header)localObject1).setAlgorithm("RS256");
    ((JsonWebSignature.Header)localObject1).setType("JWT");
    ((JsonWebSignature.Header)localObject1).setKeyId(this.serviceAccountPrivateKeyId);
    Object localObject2 = new JsonWebToken.Payload();
    long l = getClock().currentTimeMillis();
    ((JsonWebToken.Payload)localObject2).setIssuer(this.serviceAccountId);
    ((JsonWebToken.Payload)localObject2).setAudience(getTokenServerEncodedUrl());
    ((JsonWebToken.Payload)localObject2).setIssuedAtTimeSeconds(Long.valueOf(l / 1000L));
    ((JsonWebToken.Payload)localObject2).setExpirationTimeSeconds(Long.valueOf(l / 1000L + 3600L));
    ((JsonWebToken.Payload)localObject2).setSubject(this.serviceAccountUser);
    ((JsonWebToken.Payload)localObject2).put("scope", Joiner.on(' ').join(this.serviceAccountScopes));
    try
    {
      localObject1 = JsonWebSignature.signUsingRsaSha256(this.serviceAccountPrivateKey, getJsonFactory(), (JsonWebSignature.Header)localObject1, (JsonWebToken.Payload)localObject2);
      localObject2 = new TokenRequest(getTransport(), getJsonFactory(), new GenericUrl(getTokenServerEncodedUrl()), "urn:ietf:params:oauth:grant-type:jwt-bearer");
      ((TokenRequest)localObject2).put("assertion", localObject1);
      localObject1 = ((TokenRequest)localObject2).execute();
      return (TokenResponse)localObject1;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      localObject2 = new IOException();
      ((IOException)localObject2).initCause(localGeneralSecurityException);
      throw ((Throwable)localObject2);
    }
  }
  
  public final String getServiceAccountId()
  {
    return this.serviceAccountId;
  }
  
  public final PrivateKey getServiceAccountPrivateKey()
  {
    return this.serviceAccountPrivateKey;
  }
  
  @Beta
  public final String getServiceAccountPrivateKeyId()
  {
    return this.serviceAccountPrivateKeyId;
  }
  
  public final Collection<String> getServiceAccountScopes()
  {
    return this.serviceAccountScopes;
  }
  
  public final String getServiceAccountScopesAsString()
  {
    if (this.serviceAccountScopes == null) {
      return null;
    }
    return Joiner.on(' ').join(this.serviceAccountScopes);
  }
  
  public final String getServiceAccountUser()
  {
    return this.serviceAccountUser;
  }
  
  public GoogleCredential setAccessToken(String paramString)
  {
    return (GoogleCredential)super.setAccessToken(paramString);
  }
  
  public GoogleCredential setExpirationTimeMilliseconds(Long paramLong)
  {
    return (GoogleCredential)super.setExpirationTimeMilliseconds(paramLong);
  }
  
  public GoogleCredential setExpiresInSeconds(Long paramLong)
  {
    return (GoogleCredential)super.setExpiresInSeconds(paramLong);
  }
  
  public GoogleCredential setFromTokenResponse(TokenResponse paramTokenResponse)
  {
    return (GoogleCredential)super.setFromTokenResponse(paramTokenResponse);
  }
  
  public GoogleCredential setRefreshToken(String paramString)
  {
    if (paramString != null) {
      if ((getJsonFactory() == null) || (getTransport() == null) || (getClientAuthentication() == null)) {
        break label43;
      }
    }
    label43:
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "Please use the Builder and call setJsonFactory, setTransport and setClientSecrets");
      return (GoogleCredential)super.setRefreshToken(paramString);
    }
  }
  
  public static class Builder
    extends Credential.Builder
  {
    String serviceAccountId;
    PrivateKey serviceAccountPrivateKey;
    String serviceAccountPrivateKeyId;
    Collection<String> serviceAccountScopes;
    String serviceAccountUser;
    
    public Builder()
    {
      super();
      setTokenServerEncodedUrl("https://accounts.google.com/o/oauth2/token");
    }
    
    public Builder addRefreshListener(CredentialRefreshListener paramCredentialRefreshListener)
    {
      return (Builder)super.addRefreshListener(paramCredentialRefreshListener);
    }
    
    public GoogleCredential build()
    {
      return new GoogleCredential(this);
    }
    
    public final String getServiceAccountId()
    {
      return this.serviceAccountId;
    }
    
    public final PrivateKey getServiceAccountPrivateKey()
    {
      return this.serviceAccountPrivateKey;
    }
    
    @Beta
    public final String getServiceAccountPrivateKeyId()
    {
      return this.serviceAccountPrivateKeyId;
    }
    
    public final Collection<String> getServiceAccountScopes()
    {
      return this.serviceAccountScopes;
    }
    
    public final String getServiceAccountUser()
    {
      return this.serviceAccountUser;
    }
    
    public Builder setClientAuthentication(HttpExecuteInterceptor paramHttpExecuteInterceptor)
    {
      return (Builder)super.setClientAuthentication(paramHttpExecuteInterceptor);
    }
    
    public Builder setClientSecrets(GoogleClientSecrets paramGoogleClientSecrets)
    {
      paramGoogleClientSecrets = paramGoogleClientSecrets.getDetails();
      setClientAuthentication(new ClientParametersAuthentication(paramGoogleClientSecrets.getClientId(), paramGoogleClientSecrets.getClientSecret()));
      return this;
    }
    
    public Builder setClientSecrets(String paramString1, String paramString2)
    {
      setClientAuthentication(new ClientParametersAuthentication(paramString1, paramString2));
      return this;
    }
    
    public Builder setClock(Clock paramClock)
    {
      return (Builder)super.setClock(paramClock);
    }
    
    public Builder setJsonFactory(JsonFactory paramJsonFactory)
    {
      return (Builder)super.setJsonFactory(paramJsonFactory);
    }
    
    public Builder setRefreshListeners(Collection<CredentialRefreshListener> paramCollection)
    {
      return (Builder)super.setRefreshListeners(paramCollection);
    }
    
    public Builder setRequestInitializer(HttpRequestInitializer paramHttpRequestInitializer)
    {
      return (Builder)super.setRequestInitializer(paramHttpRequestInitializer);
    }
    
    public Builder setServiceAccountId(String paramString)
    {
      this.serviceAccountId = paramString;
      return this;
    }
    
    public Builder setServiceAccountPrivateKey(PrivateKey paramPrivateKey)
    {
      this.serviceAccountPrivateKey = paramPrivateKey;
      return this;
    }
    
    public Builder setServiceAccountPrivateKeyFromP12File(File paramFile)
      throws GeneralSecurityException, IOException
    {
      this.serviceAccountPrivateKey = SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(), new FileInputStream(paramFile), "notasecret", "privatekey", "notasecret");
      return this;
    }
    
    @Beta
    public Builder setServiceAccountPrivateKeyFromPemFile(File paramFile)
      throws GeneralSecurityException, IOException
    {
      paramFile = PemReader.readFirstSectionAndClose(new FileReader(paramFile), "PRIVATE KEY").getBase64DecodedBytes();
      this.serviceAccountPrivateKey = SecurityUtils.getRsaKeyFactory().generatePrivate(new PKCS8EncodedKeySpec(paramFile));
      return this;
    }
    
    @Beta
    public Builder setServiceAccountPrivateKeyId(String paramString)
    {
      this.serviceAccountPrivateKeyId = paramString;
      return this;
    }
    
    public Builder setServiceAccountScopes(Collection<String> paramCollection)
    {
      this.serviceAccountScopes = paramCollection;
      return this;
    }
    
    public Builder setServiceAccountUser(String paramString)
    {
      this.serviceAccountUser = paramString;
      return this;
    }
    
    public Builder setTokenServerEncodedUrl(String paramString)
    {
      return (Builder)super.setTokenServerEncodedUrl(paramString);
    }
    
    public Builder setTokenServerUrl(GenericUrl paramGenericUrl)
    {
      return (Builder)super.setTokenServerUrl(paramGenericUrl);
    }
    
    public Builder setTransport(HttpTransport paramHttpTransport)
    {
      return (Builder)super.setTransport(paramHttpTransport);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */