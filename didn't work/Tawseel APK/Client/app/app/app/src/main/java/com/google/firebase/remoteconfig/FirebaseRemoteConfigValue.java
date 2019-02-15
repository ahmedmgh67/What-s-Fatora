package com.google.firebase.remoteconfig;

public abstract interface FirebaseRemoteConfigValue
{
  public abstract boolean asBoolean()
    throws IllegalArgumentException;
  
  public abstract byte[] asByteArray();
  
  public abstract double asDouble()
    throws IllegalArgumentException;
  
  public abstract long asLong()
    throws IllegalArgumentException;
  
  public abstract String asString();
  
  public abstract int getSource();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\remoteconfig\FirebaseRemoteConfigValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */