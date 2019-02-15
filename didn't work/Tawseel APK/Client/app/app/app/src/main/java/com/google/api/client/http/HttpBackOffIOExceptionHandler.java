package com.google.api.client.http;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.BackOffUtils;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;

@Beta
public class HttpBackOffIOExceptionHandler
  implements HttpIOExceptionHandler
{
  private final BackOff backOff;
  private Sleeper sleeper = Sleeper.DEFAULT;
  
  public HttpBackOffIOExceptionHandler(BackOff paramBackOff)
  {
    this.backOff = ((BackOff)Preconditions.checkNotNull(paramBackOff));
  }
  
  public final BackOff getBackOff()
  {
    return this.backOff;
  }
  
  public final Sleeper getSleeper()
  {
    return this.sleeper;
  }
  
  public boolean handleIOException(HttpRequest paramHttpRequest, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean) {
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
  
  public HttpBackOffIOExceptionHandler setSleeper(Sleeper paramSleeper)
  {
    this.sleeper = ((Sleeper)Preconditions.checkNotNull(paramSleeper));
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpBackOffIOExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */