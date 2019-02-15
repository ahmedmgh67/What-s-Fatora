package com.google.api.client.extensions.android.http;

import com.google.api.client.extensions.android.AndroidUtils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Beta;

@Beta
public class AndroidHttp
{
  public static HttpTransport newCompatibleTransport()
  {
    if (AndroidUtils.isMinimumSdkLevel(9)) {
      return new NetHttpTransport();
    }
    return new ApacheHttpTransport();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\extensions\android\http\AndroidHttp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */