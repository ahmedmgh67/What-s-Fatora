package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public class OAuth2Utils
{
  private static final int COMPUTE_PING_CONNECTION_TIMEOUT_MS = 500;
  private static final String DEFAULT_METADATA_SERVER_URL = "http://169.254.169.254";
  private static final Logger LOGGER = Logger.getLogger(OAuth2Utils.class.getName());
  private static final int MAX_COMPUTE_PING_TRIES = 3;
  static final Charset UTF_8 = Charset.forName("UTF-8");
  
  static <T extends Throwable> T exceptionWithCause(T paramT, Throwable paramThrowable)
  {
    paramT.initCause(paramThrowable);
    return paramT;
  }
  
  public static String getMetadataServerUrl()
  {
    return getMetadataServerUrl(SystemEnvironmentProvider.INSTANCE);
  }
  
  static String getMetadataServerUrl(SystemEnvironmentProvider paramSystemEnvironmentProvider)
  {
    paramSystemEnvironmentProvider = paramSystemEnvironmentProvider.getEnv("GCE_METADATA_HOST");
    if (paramSystemEnvironmentProvider != null) {
      return "http://" + paramSystemEnvironmentProvider;
    }
    return "http://169.254.169.254";
  }
  
  static boolean headersContainValue(HttpHeaders paramHttpHeaders, String paramString1, String paramString2)
  {
    paramHttpHeaders = paramHttpHeaders.get(paramString1);
    if ((paramHttpHeaders instanceof Collection))
    {
      paramHttpHeaders = ((Collection)paramHttpHeaders).iterator();
      while (paramHttpHeaders.hasNext())
      {
        paramString1 = paramHttpHeaders.next();
        if (((paramString1 instanceof String)) && (((String)paramString1).equals(paramString2))) {
          return true;
        }
      }
    }
    return false;
  }
  
  static boolean runningOnComputeEngine(HttpTransport paramHttpTransport, SystemEnvironmentProvider paramSystemEnvironmentProvider)
  {
    if (Boolean.parseBoolean(paramSystemEnvironmentProvider.getEnv("NO_GCE_CHECK"))) {
      return false;
    }
    paramSystemEnvironmentProvider = new GenericUrl(getMetadataServerUrl(paramSystemEnvironmentProvider));
    int i = 1;
    while (i <= 3) {
      try
      {
        Object localObject1 = paramHttpTransport.createRequestFactory().buildGetRequest(paramSystemEnvironmentProvider);
        ((HttpRequest)localObject1).setConnectTimeout(500);
        localObject1 = ((HttpRequest)localObject1).execute();
        try
        {
          boolean bool = headersContainValue(((HttpResponse)localObject1).getHeaders(), "Metadata-Flavor", "Google");
          return bool;
        }
        finally
        {
          ((HttpResponse)localObject1).disconnect();
        }
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        i += 1;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          LOGGER.log(Level.WARNING, "Failed to detect whether we are running on Google Compute Engine.", localIOException);
        }
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\OAuth2Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */