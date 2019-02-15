package com.google.api.client.googleapis.auth.oauth2;

class SystemEnvironmentProvider
{
  static final SystemEnvironmentProvider INSTANCE = new SystemEnvironmentProvider();
  
  String getEnv(String paramString)
  {
    return System.getenv(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\SystemEnvironmentProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */