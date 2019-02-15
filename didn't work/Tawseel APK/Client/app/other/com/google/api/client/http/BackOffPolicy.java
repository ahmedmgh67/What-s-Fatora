package com.google.api.client.http;

import com.google.api.client.util.Beta;
import java.io.IOException;

@Deprecated
@Beta
public abstract interface BackOffPolicy
{
  public static final long STOP = -1L;
  
  public abstract long getNextBackOffMillis()
    throws IOException;
  
  public abstract boolean isBackOffRequired(int paramInt);
  
  public abstract void reset();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\BackOffPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */