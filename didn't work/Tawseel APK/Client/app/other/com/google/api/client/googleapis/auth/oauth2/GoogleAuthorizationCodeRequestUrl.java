package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import java.util.Iterator;

public class GoogleAuthorizationCodeRequestUrl
  extends AuthorizationCodeRequestUrl
{
  @Key("access_type")
  private String accessType;
  @Key("approval_prompt")
  private String approvalPrompt;
  
  public GoogleAuthorizationCodeRequestUrl(GoogleClientSecrets paramGoogleClientSecrets, String paramString, Collection<String> paramCollection)
  {
    this(paramGoogleClientSecrets.getDetails().getClientId(), paramString, paramCollection);
  }
  
  public GoogleAuthorizationCodeRequestUrl(String paramString1, String paramString2, String paramString3, Collection<String> paramCollection)
  {
    super(paramString1, paramString2);
    setRedirectUri(paramString3);
    setScopes(paramCollection);
  }
  
  public GoogleAuthorizationCodeRequestUrl(String paramString1, String paramString2, Collection<String> paramCollection)
  {
    this("https://accounts.google.com/o/oauth2/auth", paramString1, paramString2, paramCollection);
  }
  
  public GoogleAuthorizationCodeRequestUrl clone()
  {
    return (GoogleAuthorizationCodeRequestUrl)super.clone();
  }
  
  public final String getAccessType()
  {
    return this.accessType;
  }
  
  public final String getApprovalPrompt()
  {
    return this.approvalPrompt;
  }
  
  public GoogleAuthorizationCodeRequestUrl set(String paramString, Object paramObject)
  {
    return (GoogleAuthorizationCodeRequestUrl)super.set(paramString, paramObject);
  }
  
  public GoogleAuthorizationCodeRequestUrl setAccessType(String paramString)
  {
    this.accessType = paramString;
    return this;
  }
  
  public GoogleAuthorizationCodeRequestUrl setApprovalPrompt(String paramString)
  {
    this.approvalPrompt = paramString;
    return this;
  }
  
  public GoogleAuthorizationCodeRequestUrl setClientId(String paramString)
  {
    return (GoogleAuthorizationCodeRequestUrl)super.setClientId(paramString);
  }
  
  public GoogleAuthorizationCodeRequestUrl setRedirectUri(String paramString)
  {
    Preconditions.checkNotNull(paramString);
    return (GoogleAuthorizationCodeRequestUrl)super.setRedirectUri(paramString);
  }
  
  public GoogleAuthorizationCodeRequestUrl setResponseTypes(Collection<String> paramCollection)
  {
    return (GoogleAuthorizationCodeRequestUrl)super.setResponseTypes(paramCollection);
  }
  
  public GoogleAuthorizationCodeRequestUrl setScopes(Collection<String> paramCollection)
  {
    Preconditions.checkArgument(paramCollection.iterator().hasNext());
    return (GoogleAuthorizationCodeRequestUrl)super.setScopes(paramCollection);
  }
  
  public GoogleAuthorizationCodeRequestUrl setState(String paramString)
  {
    return (GoogleAuthorizationCodeRequestUrl)super.setState(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleAuthorizationCodeRequestUrl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */