package com.google.firebase.remoteconfig;

public class FirebaseRemoteConfigSettings
{
  private final boolean zzcjH;
  
  private FirebaseRemoteConfigSettings(Builder paramBuilder)
  {
    this.zzcjH = Builder.zza(paramBuilder);
  }
  
  public boolean isDeveloperModeEnabled()
  {
    return this.zzcjH;
  }
  
  public static class Builder
  {
    private boolean zzcjH = false;
    
    public FirebaseRemoteConfigSettings build()
    {
      return new FirebaseRemoteConfigSettings(this, null);
    }
    
    public Builder setDeveloperModeEnabled(boolean paramBoolean)
    {
      this.zzcjH = paramBoolean;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\remoteconfig\FirebaseRemoteConfigSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */