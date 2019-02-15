package com.google.api.client.googleapis.auth.clientlogin;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpResponseException.Builder;
import com.google.api.client.util.Beta;

@Beta
public class ClientLoginResponseException
  extends HttpResponseException
{
  private static final long serialVersionUID = 4974317674023010928L;
  private final transient ClientLogin.ErrorInfo details;
  
  ClientLoginResponseException(HttpResponseException.Builder paramBuilder, ClientLogin.ErrorInfo paramErrorInfo)
  {
    super(paramBuilder);
    this.details = paramErrorInfo;
  }
  
  public final ClientLogin.ErrorInfo getDetails()
  {
    return this.details;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\clientlogin\ClientLoginResponseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */