package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.BrowserClientRequestUrl;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import java.util.Iterator;

public class GoogleBrowserClientRequestUrl
  extends BrowserClientRequestUrl
{
  @Key("approval_prompt")
  private String approvalPrompt;
  
  public GoogleBrowserClientRequestUrl(GoogleClientSecrets paramGoogleClientSecrets, String paramString, Collection<String> paramCollection)
  {
    this(paramGoogleClientSecrets.getDetails().getClientId(), paramString, paramCollection);
  }
  
  public GoogleBrowserClientRequestUrl(String paramString1, String paramString2, Collection<String> paramCollection)
  {
    super("https://accounts.google.com/o/oauth2/auth", paramString1);
    setRedirectUri(paramString2);
    setScopes(paramCollection);
  }
  
  public GoogleBrowserClientRequestUrl clone()
  {
    return (GoogleBrowserClientRequestUrl)super.clone();
  }
  
  public final String getApprovalPrompt()
  {
    return this.approvalPrompt;
  }
  
  public GoogleBrowserClientRequestUrl set(String paramString, Object paramObject)
  {
    return (GoogleBrowserClientRequestUrl)super.set(paramString, paramObject);
  }
  
  public GoogleBrowserClientRequestUrl setApprovalPrompt(String paramString)
  {
    this.approvalPrompt = paramString;
    return this;
  }
  
  public GoogleBrowserClientRequestUrl setClientId(String paramString)
  {
    return (GoogleBrowserClientRequestUrl)super.setClientId(paramString);
  }
  
  public GoogleBrowserClientRequestUrl setRedirectUri(String paramString)
  {
    return (GoogleBrowserClientRequestUrl)super.setRedirectUri(paramString);
  }
  
  public GoogleBrowserClientRequestUrl setResponseTypes(Collection<String> paramCollection)
  {
    return (GoogleBrowserClientRequestUrl)super.setResponseTypes(paramCollection);
  }
  
  public GoogleBrowserClientRequestUrl setScopes(Collection<String> paramCollection)
  {
    Preconditions.checkArgument(paramCollection.iterator().hasNext());
    return (GoogleBrowserClientRequestUrl)super.setScopes(paramCollection);
  }
  
  public GoogleBrowserClientRequestUrl setState(String paramString)
  {
    return (GoogleBrowserClientRequestUrl)super.setState(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\GoogleBrowserClientRequestUrl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */