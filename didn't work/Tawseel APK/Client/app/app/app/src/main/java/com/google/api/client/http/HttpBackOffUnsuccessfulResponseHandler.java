package com.google.api.client.http;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.BackOffUtils;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;

@Beta
public class HttpBackOffUnsuccessfulResponseHandler
  implements HttpUnsuccessfulResponseHandler
{
  private final BackOff backOff;
  private BackOffRequired backOffRequired = BackOffRequired.ON_SERVER_ERROR;
  private Sleeper sleeper = Sleeper.DEFAULT;
  
  public HttpBackOffUnsuccessfulResponseHandler(BackOff paramBackOff)
  {
    this.backOff = ((BackOff)Preconditions.checkNotNull(paramBackOff));
  }
  
  public final BackOff getBackOff()
  {
    return this.backOff;
  }
  
  public final BackOffRequired getBackOffRequired()
  {
    return this.backOffRequired;
  }
  
  public final Sleeper getSleeper()
  {
    return this.sleeper;
  }
  
  public final boolean handleResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean) {}
    while (!this.backOffRequired.isRequired(paramHttpResponse)) {
      return false;
    }
    try
    {
      paramBoolean = BackOffUtils.next(this.sleeper, this.backOff);
      return paramBoolean;
    }
    catch (InterruptedException paramHttpRequest) {}
    return false;
  }
  
  public HttpBackOffUnsuccessfulResponseHandler setBackOffRequired(BackOffRequired paramBackOffRequired)
  {
    this.backOffRequired = ((BackOffRequired)Preconditions.checkNotNull(paramBackOffRequired));
    return this;
  }
  
  public HttpBackOffUnsuccessfulResponseHandler setSleeper(Sleeper paramSleeper)
  {
    this.sleeper = ((Sleeper)Preconditions.checkNotNull(paramSleeper));
    return this;
  }
  
  @Beta
  public static abstract interface BackOffRequired
  {
    public static final BackOffRequired ALWAYS = new BackOffRequired()
    {
      public boolean isRequired(HttpResponse paramAnonymousHttpResponse)
      {
        return true;
      }
    };
    public static final BackOffRequired ON_SERVER_ERROR = new BackOffRequired()
    {
      public boolean isRequired(HttpResponse paramAnonymousHttpResponse)
      {
        return paramAnonymousHttpResponse.getStatusCode() / 100 == 5;
      }
    };
    
    public abstract boolean isRequired(HttpResponse paramHttpResponse);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpBackOffUnsuccessfulResponseHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */